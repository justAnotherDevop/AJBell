package com.ajbell.technicaltest.data

import com.ajbell.technicaltest.InterviewApplication
import com.google.gson.GsonBuilder
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class MarketRepository @Inject constructor() {

    fun getMarkets(): GetMarketsResponse {
        val marketsJson: String = InterviewApplication.context.assets.open("markets.json")
            .bufferedReader()
            .use { it.readText() }
        return GsonBuilder().create().fromJson(marketsJson, GetMarketsResponse::class.java)
    }
}