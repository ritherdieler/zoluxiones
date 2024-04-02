package com.zoluxiones.features.movieList

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.zoluxiones.domain.entity.Movie
import com.zoluxiones.domain.model.DataOriginWrapper
import com.zoluxiones.domain.model.Either
import com.zoluxiones.domain.usecases.base.GetMoviesByPageUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MovieListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private val getMoviesByPageUseCase: GetMoviesByPageUseCase = mock()
    private val viewModel = MovieListViewModel(getMoviesByPageUseCase)

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanup() {
        Dispatchers.resetMain()
        testScope.cleanupTestCoroutines()
    }

    @Test
    fun `getFirstMoviesPage returns movies from remote`() = runBlockingTest {
        // Arrange
        val movies = listOf(
            Movie(
                id = 9919,
                poster_path = "/path/to/poster.jpg",
                title = "Test Movie",
                vote_average = 8.9,
                release_date = "2022-04-22",
                overview = "This is a test movie."
            )
        )
        val result = Either.Success(DataOriginWrapper(true, movies, 5))
        whenever(getMoviesByPageUseCase.run(GetMoviesByPageUseCase.Params(1))).thenReturn(result)

        // Act
        viewModel.getFirstMoviesPage()

        // Assert
        Assert.assertEquals(ViewState.Loaded(movies, false), viewModel.viewState.value)
        verify(getMoviesByPageUseCase).run(GetMoviesByPageUseCase.Params(1))
    }

    @Test
    fun `getFirstMoviesPage returns movies from cache`() = runBlockingTest {
        // Arrange
        val movies = listOf(
            Movie(
                id = 9919,
                poster_path = "/path/to/poster.jpg",
                title = "Test Movie",
                vote_average = 8.9,
                release_date = "2022-04-22",
                overview = "This is a test movie."
            )
        )
        val result = Either.Success(DataOriginWrapper(false, movies, 5))
        whenever(getMoviesByPageUseCase.run(GetMoviesByPageUseCase.Params(1))).thenReturn(result)

        // Act
        viewModel.getFirstMoviesPage()

        // Assert
        Assert.assertEquals(ViewState.Loaded(movies, false), viewModel.viewState.value)
        verify(getMoviesByPageUseCase).run(GetMoviesByPageUseCase.Params(1))
    }
}