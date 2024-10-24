package com.ajbell.technicaltest.list

import android.os.Parcelable
import com.ajbell.technicaltest.data.GetMarketsResponse
import kotlinx.parcelize.Parcelize

/**
 * This should be a mapper. Improvement use a mapper
 */

sealed class MarketListEvent {
    @Parcelize
    class ShowMarketDetail(val market: GetMarketsResponse.Market) : Parcelable,MarketListEvent()
}