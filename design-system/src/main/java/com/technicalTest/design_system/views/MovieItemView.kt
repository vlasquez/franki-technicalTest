package com.technicaltest.design_system.theme.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.technicalTest.design_system.theme.AppTheme
import com.technicalTest.design_system.theme.AppTypography
import com.technicalTest.design_system.theme.Layout
import com.technicalTest.design_system.theme.Padding

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MovieItemView(
    isGridView: Boolean,
    posterUrl: String?,
    movieTitle: String,
    onItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .height(MOVIE_CARD_HEIGHT.dp)
            .fillMaxWidth()
            .padding(Padding.Small.S)
            .clickable {
                onItemClick()
            },
        elevation = CardDefaults.cardElevation(Layout.Spacing.Small.S)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Layout.Spacing.Small.S)
        ) {
            GlideImage(
                model = posterUrl,
                contentDescription = "Movie Poster",
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = Padding.Small.L,
                            topEnd = Padding.Small.L
                        )
                    )
                    .height(MOVIE_IMAGE_HEIGHT.dp)
                    .fillMaxWidth()
            ) {
                it.centerCrop()
            }
            Text(
                textAlign = if (isGridView) TextAlign.Start else TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = Padding.Small.S)
                    .fillMaxWidth(),
                text = movieTitle,
                style = if (isGridView) AppTypography.bodySmall else AppTypography.bodyLarge
            )
        }
    }
}

@Composable
@Preview
private fun MoviePreview() {
    AppTheme {
        MovieItemView(
            isGridView = true,
            posterUrl = "https://image.tmdb.org/t/p/w500/z1p34vh7dEOnLDmyCrlUVLuoDzd.jpg",
            movieTitle = "The Godfather",
            onItemClick = {}
        )
    }
}

const val MOVIE_IMAGE_HEIGHT = 200
const val MOVIE_CARD_HEIGHT = 300