package com.technicalTest.feature_weather.presentation.compose

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.technicalTest.design_system.theme.AppTypography
import com.technicalTest.design_system.theme.Layout
import com.technicalTest.design_system.theme.secondaryLight
import com.technicalTest.design_system.theme.surfaceLight
import com.technicalTest.feature_weather.R
import com.technicalTest.feature_weather.domain.model.CityWeather
import com.technicalTest.feature_weather.presentation.WeatherViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun WeatherView(modifier: Modifier = Modifier) {
    val viewModel: WeatherViewModel = hiltViewModel()
    val viewState by viewModel.viewState.collectAsState()
    var refreshing by remember { mutableStateOf(false) }
    val cityWeather = remember { mutableStateOf<CityWeather?>(null) }

    when (viewState) {
        is WeatherViewModel.ViewState.Loading, WeatherViewModel.ViewState.Refreshing -> {
        }

        is WeatherViewModel.ViewState.WeatherLoaded -> {
            cityWeather.value = (viewState as WeatherViewModel.ViewState.WeatherLoaded).cityWeather
        }

        is WeatherViewModel.ViewState.WeatherError -> {
            val localContext = LocalContext.current
            Toast.makeText(
                localContext,
                (viewState as WeatherViewModel.ViewState.WeatherError).message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    CityWeatherView(modifier = modifier, cityWeather = cityWeather) {
        viewModel.getWeatherByLocation(location = it)
    }
}

@Composable
fun CityWeatherView(
    modifier: Modifier,
    cityWeather: MutableState<CityWeather?>,
    onCityWeatherClicked: (String) -> Unit
) {
    val cityName = remember { mutableStateOf("") }

    Column(modifier = modifier.padding(Layout.Spacing.Small.L)) {
        OutlinedTextField(
            value = cityName.value,
            onValueChange = { cityName.value = it },
            label = { Text(stringResource(R.string.enter_city_name)) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = AppTypography.bodySmall,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = surfaceLight,
                unfocusedBorderColor = surfaceLight,
                cursorColor = surfaceLight,
                textColor = surfaceLight,
                placeholderColor = surfaceLight,
                focusedLabelColor = surfaceLight,
                unfocusedLabelColor = surfaceLight
            )
        )
        Button(
            onClick = { onCityWeatherClicked(cityName.value) },
            modifier = Modifier.padding(top = Layout.Spacing.Small.L),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = secondaryLight,
                contentColor = surfaceLight
            )
        ) {
            Text("Get Weather")
        }

        cityWeather.value?.let {
            WeatherItemView(
                cityWeather = it,
                placeholderModifier = Modifier
                    .fillMaxWidth()
                    .padding(Layout.Spacing.Small.S)
            )
        }
    }
}

