package com.zoluxiones.features

import com.zoluxiones.domain.entity.Movie

sealed class ViewState {
    object EmptyScreen : ViewState()
    data class Loaded(val data: List<Movie>, val loadingMore: Boolean) : ViewState()
    object Loading : ViewState()
}