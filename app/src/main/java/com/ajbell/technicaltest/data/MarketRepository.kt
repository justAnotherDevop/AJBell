package com.ajbell.technicaltest.data

import com.ajbell.technicaltest.InterviewApplication
import com.ajbell.technicaltest.MainActivity
import com.google.gson.GsonBuilder

internal class MarketRepository {

    fun getMarkets(): GetMarketsResponse {
        val marketsJson: String = InterviewApplication.context.assets.open("markets.json")
            .bufferedReader()
            .use { it.readText() }
        return GsonBuilder().create().fromJson(marketsJson, GetMarketsResponse::class.java)
    }
}