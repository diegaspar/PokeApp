package com.diegaspar.pokemonlist.presentation.extensions

import androidx.compose.foundation.lazy.LazyListState

fun LazyListState.isScrolledToEnd() =
    layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1

fun LazyListState.isScrolledTo80PerCent(): Boolean {
    val visibleItemsCount = this.layoutInfo.visibleItemsInfo.size
    val percent =
        (firstVisibleItemIndex / (layoutInfo.totalItemsCount - 1 - visibleItemsCount).toFloat()) * 100f

    if (percent >= 80f) {
        return true
    }
    return false
}