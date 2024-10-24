package com.ajbell.technicaltest.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajbell.technicaltest.data.GetMarketsResponse
import com.ajbell.technicaltest.data.MarketRepository
import com.ajbell.technicaltest.util.LiveEvent
import kotlinx.coroutines.launch

internal class MarketListViewModel : ViewModel() {

    private val marketRepository = MarketRepository()

    private val _markets = MutableLiveData<List<GetMarketsResponse.Market>>()
    val markets: LiveData<List<GetMarketsResponse.Market>> = _markets

    private val _event = LiveEvent<MarketListEvent>()
    val event: LiveData<MarketListEvent> = _event

    fun onStart() {
        viewModelScope.launch {
            _markets.value = marketRepository.getMarkets().data
        }
    }

    fun onItemClick(market: GetMarketsResponse.Market) {
        _event.value = MarketListEvent.ShowMarketDetail(market)
    }
}