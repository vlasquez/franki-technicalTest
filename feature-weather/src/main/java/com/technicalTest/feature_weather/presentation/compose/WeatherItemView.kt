package com.technicalTest.feature_weather.presentation.compose

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.imageLoader
import coil.request.ImageRequest
import com.technicalTest.design_system.theme.AppTypography
import com.technicalTest.design_system.theme.Layout
import com.technicalTest.design_system.theme.secondaryLight
import com.technicalTest.feature_weather.R
import com.technicalTest.feature_weather.domain.model.CityWeather
import com.technicalTest.feature_weather.domain.model.Coordinates
import com.technicalTest.feature_weather.domain.model.Temperature
import com.technicalTest.feature_weather.domain.model.Weather

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherItemView(
    placeholderModifier: Modifier? = null,
    cityWeather: CityWeather,
) {
    Card(
        modifier = (placeholderModifier ?: Modifier)
            .height(Layout.Spacing.Large.L)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12),
    ) {
        Row(
            modifier = Modifier
                .background(secondaryLight)
                .padding(
                    horizontal = Layout.Spacing.Small.M,
                    vertical = Layout.Spacing.Small.S
                )
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                Layout.Spacing.Small.S
            ),
        ) {
            Column(
                modifier = Modifier
                    .weight(0.3F)
                    .fillMaxHeight()
            ) {
                Text(
                    text = cityWeather.capitalName,
                    style = AppTypography.labelLarge,
                )
            }
            Column(
                modifier = Modifier.weight(0.5F),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = cityWeather.weather.weather,
                    style = AppTypography.labelLarge,
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
                Text(
                    text = cityWeather.weather.description,
                    style = AppTypography.labelLarge,
                    fontSize = 12.sp
                )
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(stringResource(id = R.string.icon_url, cityWeather.weather.icon))
                            .apply {
                                placeholder(R.drawable.ic_baseline_wb_sunny_24)
                                Log.d(
                                    "ICON",
                                    stringResource(id = R.string.icon_url, cityWeather.weather.icon)
                                )
                            }.build(),
                        imageLoader = LocalContext.current.imageLoader,
                    ), contentDescription = null,
                    contentScale = ContentScale.Fit
                )

            }
            Column(
                modifier = Modifier
                    .weight(0.3F)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = cityWeather.weather.temperature.temp,
                    style = AppTypography.labelLarge,
                    fontSize = 38.sp
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.max_temp,
                            cityWeather.weather.temperature.maxTemp
                        ),
                        style = AppTypography.labelLarge,
                        fontSize = 12.sp
                    )
                    Text(
                        text = stringResource(
                            id = R.string.min_temp,
                            cityWeather.weather.temperature.minTemp
                        ),
                        style = AppTypography.labelLarge,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
@Preview
private fun WeatherItemPreview() {
    WeatherItemView(
        cityWeather = CityWeather(
            capitalName = "Albany",
            cityCoordinates = Coordinates(lat = 42.6001, lon = -73.9662),
            weather = Weather(
                description = "overcast clouds",
                weather = "Clouds",
                cloudPercentage = 100,
                temperature = Temperature(
                    feelsLike = 5.68,
                    humidity = "72",
                    pressure = 1017,
                    temp = "5\u00B0",
                    maxTemp = "7\u00B0",
                    minTemp = "3\u00B0"
                ),
                icon = "04n"
            ),
            requestTimestamp = 1672440487
        ),
    )
}