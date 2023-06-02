package com.diegaspar.core_ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun InitialLoading() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (initialLoading) = createRefs()
        Box(modifier = Modifier.constrainAs(initialLoading) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            CircularProgressIndicator()
        }
    }
}