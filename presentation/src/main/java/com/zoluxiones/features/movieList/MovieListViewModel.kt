package com.zoluxiones.features.movieList

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.domain.model.Failure
import com.zoluxiones.domain.usecases.base.GetMoviesByPageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesByPageUseCase: GetMoviesByPageUseCase
) : ViewModel() {

    private var remoteDataSourceIsAvailable: Boolean = false
    private var currentMovies: MutableList<Movie> = mutableListOf()
    private var currentPage: Int = 1
    private var lastPage: Int? = null

    val viewState: MutableState<ViewState> = mutableStateOf(ViewState.EmptyScreen)

    private val errorState: MutableLiveData<String> = MutableLiveData()


    fun getFirstMoviesPage() {

        viewModelScope.launch {
            getMoviesByPageUseCase.run(GetMoviesByPageUseCase.Params(1))
                .either(
                    {
                        errorState.value = handleError(it)
                    },
                    {
                        lastPage = it.totaPages
                        remoteDataSourceIsAvailable = it.comesFromRemote

                        if (!remoteDataSourceIsAvailable)
                            errorState.value = "No estas conectado a internet"
                        currentMovies.addAll(it.data)
                        viewState.value =
                            ViewState.Loaded(data = currentMovies, loadingMore = false)
                    })
        }
    }

    fun getNextMoviesPage() {
        if (currentPage <= lastPage!! && remoteDataSourceIsAvailable) {
            viewModelScope.launch {
                viewState.value = ViewState.Loaded(data = currentMovies, loadingMore = true)
               delay(400)
                currentPage += 1
                getMoviesByPageUseCase.run(GetMoviesByPageUseCase.Params(currentPage))
                    .either(
                        {
                            errorState.value = handleError(it)
                        },
                        {
                            currentMovies.addAll(it.data)
                            viewState.value =
                                ViewState.Loaded(data = currentMovies, loadingMore = false)
                        })
            }
        }
    }

    private fun handleError(error: Failure): String {
//TODO se podria utilizar cada caso para realizar una accion diferente
//TODO por ejemplo forzar el cierre de sesion en caso el token no sea valido
        return when (error) {
            is Failure.INTERNAL_SERVER_ERROR -> error.message
            is Failure.LOCAL_DB_ERROR -> error.message
            is Failure.MALFORMED_REQUEST_ERROR -> error.message
            is Failure.MAPPER_ERROR -> error.message
            is Failure.NOT_FOUND_ERROR -> error.message
            is Failure.SERVICE_UNAVAILABLE_ERROR -> error.message
            is Failure.UNAUTHORIZED_ERROR -> error.message
            is Failure.UNKNOWN_ERROR -> error.message
        }

    }


}
