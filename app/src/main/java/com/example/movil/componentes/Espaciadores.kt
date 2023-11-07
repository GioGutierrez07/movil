package com.example.notastareas.componentes

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpaceAlto(size: Dp = 10.dp) {
    Spacer(modifier = Modifier.height(size))
}

@Composable
fun SpaceAncho(size: Dp = 10.dp) {
    Spacer(modifier = Modifier.width(size))
}

@Composable
fun SpaceAbajo(size: Dp = 10.dp){
    Spacer(modifier = Modifier.padding(bottom = size))
}

