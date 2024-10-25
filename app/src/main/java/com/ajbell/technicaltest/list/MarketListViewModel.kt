package com.ajbell.technicaltest.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajbell.technicaltest.data.GetMarketsResponse
import com.ajbell.technicaltest.data.MarketRepository
import com.ajbell.technicaltest.detail.MarketDetailsViewModel
import com.ajbell.technicaltest.util.LiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MarketListViewModel @Inject constructor(
    private val marketRepository: MarketRepository
): ViewModel() {

    private val _markets = MutableLiveData<List<GetMarketsResponse.Market>>()
    val markets: LiveData<List<GetMarketsResponse.Market>> = _markets

    private val _event = LiveEvent<MarketListEvent>()
    val event: LiveData<MarketListEvent> = _event

    fun onStart() {
        viewModelScope.launch {
            try {
                _markets.value = marketRepository.getMarkets().data
            } catch (throwable: Throwable) {
                Log.d(MarketDetailsViewModel::class.java.simpleName, throwable.toString())
            }
        }
    }

    fun onItemClick(market: GetMarketsResponse.Market) {
        _event.value = MarketListEvent.ShowMarketDetail(market)
    }
}