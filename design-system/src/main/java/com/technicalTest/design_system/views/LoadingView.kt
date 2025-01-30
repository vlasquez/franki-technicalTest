package com.technicalTest.design_system.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.technicalTest.design_system.theme.AppTheme
import com.technicalTest.design_system.theme.Layout

@Composable
fun LoadingAnimation() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(Layout.Spacing.Medium.L)
        )
    }
}

@Preview
@Composable
fun LoadingViewPreview() {
    AppTheme {
        LoadingAnimation()
    }
}