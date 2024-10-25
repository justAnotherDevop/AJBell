package com.ajbell.technicaltest.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ajbell.technicaltest.data.GetMarketsResponse
import com.ajbell.technicaltest.util.LiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MarketDetailsViewModel: ViewModel() {

    private val _viewState = MutableStateFlow(MarketDetailsViewState())
    val viewState = _viewState.asStateFlow()

    private val _viewAction = LiveEvent<ViewAction>()
    val viewAction: LiveData<ViewAction> = _viewAction

    fun updateView(market: GetMarketsResponse.Market) {
        _viewState.update { currentState ->
            currentState.copy(
                companyName = market.companyName,
                epic = market.epic,
                currentPrice = "$${market.currentPrice}",
                currentChange = "$${market.currentChange}",
                currentChangePercentage = "%${market.currentChangePercentage}"
            )
        }
    }

    fun onBackPressed() {
        _viewAction.value = ViewAction.NavigateToHome
    }

    data class MarketDetailsViewState(
        var companyName: String = "",
        var epic: String = "",
        var currentPrice: String = "",
        var currentChange: String = "",
        var currentChangePercentage: String = "",
    )

    sealed class ViewAction {
        data object NavigateToHome : ViewAction()
    }
}