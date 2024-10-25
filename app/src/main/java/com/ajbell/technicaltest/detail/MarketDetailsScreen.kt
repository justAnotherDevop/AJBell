@file:OptIn(ExperimentalMaterial3Api::class)

package com.ajbell.technicaltest.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.findNavController
import com.ajbell.technicaltest.R

import com.ajbell.technicaltest.list.MarketListEvent.ShowMarketDetail

@Composable
internal fun MarketDetailScreen() {
    val navController = LocalView.current.findNavController()
    val details =
        navController.currentBackStackEntry?.arguments?.getParcelable<ShowMarketDetail>(
            MARKET_ARGS_KEY
        )

    val viewModel = viewModel<MarketDetailsViewModel>()
    val viewState by viewModel.viewState.collectAsState()
    val viewAction = viewModel.viewAction.observeAsState()

    when (viewAction.value) {
        MarketDetailsViewModel.ViewAction.NavigateToHome -> {
            navController.popBackStack()
        }

        else -> {
            /* Do Nothing */
        }
    }

    details?.market?.let { viewModel.updateView(it) }

    MarketDetailScreenContent(viewState, viewModel)
}

@Composable
private fun MarketDetailScreenContent(
    viewState: MarketDetailsViewModel.MarketDetailsViewState,
    viewModel: MarketDetailsViewModel,
) {
    Scaffold(
        topBar = {
            AppBar(
                onBackPressed = {
                    viewModel.onBackPressed()
                },
                marketName = viewState.companyName
            )
        },
        content = { paddingValues ->
            MarketDetailsContent(
                epic = viewState.epic,
                currentPrice = viewState.currentPrice,
                currentChange = viewState.currentChange,
                currentChangePercentage = viewState.currentChangePercentage,
                modifier = Modifier
                    .padding(paddingValues)
            )
        }
    )
}

@Composable
private fun AppBar(onBackPressed: () -> Unit, marketName: String) {
    TopAppBar(
        title = { Text(text = marketName, color = Color.White) },
        navigationIcon = @Composable {
            IconButton(onClick = { onBackPressed() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_content_desc_text)
                )
            }
        },
        modifier = Modifier.statusBarsPadding(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(id = R.color.purple_500),
            navigationIconContentColor = Color.White
        )
    )
}

@Composable
fun MarketDetailsContent(
    epic: String,
    currentPrice: String,
    currentChange: String,
    currentChangePercentage: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        val detailsRowModifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.market_details_row_padding))

        MarketDetailsRowItem(
            detailsName = stringResource(id = R.string.epic_text),
            detailsValue = epic,
            modifier = detailsRowModifier
        )

        MarketDetailsRowItem(
            detailsName = stringResource(id = R.string.current_price_text),
            detailsValue = currentPrice,
            modifier = detailsRowModifier
        )

        MarketDetailsRowItem(
            detailsName = stringResource(id = R.string.current_change_text),
            detailsValue = currentChange,
            modifier = detailsRowModifier
        )

        MarketDetailsRowItem(
            detailsName = stringResource(id = R.string.current_change_percentage),
            detailsValue = currentChangePercentage,
            modifier = detailsRowModifier
        )
    }
}

@Composable
fun MarketDetailsRowItem(
    detailsName: String,
    detailsValue: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            fontSize = dimensionResource(id = R.dimen.market_details_text_size).value.sp,
            text = detailsName,
            modifier = Modifier.weight(1f)
        )
        Text(
            fontSize = dimensionResource(id = R.dimen.market_details_text_size).value.sp,
            text = detailsValue,
        )
    }
}

@Preview(name = "MarketDetailsRowItemPreview")
@Composable
fun MarketDetailsRowItemPreview() {
    MaterialTheme {
        MarketDetailsRowItem(
            detailsName = "Market Name",
            detailsValue = "23.95",
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
                .padding(
                    dimensionResource(id = R.dimen.market_details_row_preview_padding)
                )
        )
    }
}

@Preview("MarketDetailsContentPreview")
@Composable
fun MarketDetailsContentPreview() {
    MaterialTheme {
        MarketDetailsContent(
            epic = PREVIEW_TITLE,
            currentPrice = PREVIEW_PRICE,
            currentChange = PREVIEW_CHANGE,
            currentChangePercentage = PREVIEW_PERCENTAGE,
            modifier = Modifier.background(Color.White)
        )
    }
}

@Preview("AppBarPreview")
@Composable
fun AppBarPreview() {
    MaterialTheme {
        AppBar(onBackPressed = { }, marketName = "FTSE 100")
    }
}

private const val PREVIEW_TITLE = "Preview Title"
private const val PREVIEW_PRICE = "10.5"
private const val PREVIEW_CHANGE = "2.5"
private const val PREVIEW_PERCENTAGE = "1.0"
private const val MARKET_ARGS_KEY = "market"

