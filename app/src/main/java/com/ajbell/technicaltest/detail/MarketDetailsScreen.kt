@file:OptIn(ExperimentalMaterial3Api::class)

package com.ajbell.technicaltest.detail

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.navigation.NavController
import androidx.navigation.NavType

import com.ajbell.technicaltest.list.MarketListEvent.ShowMarketDetail

@Composable
internal fun MarketDetailScreen(navController: NavController, marketDetail: ShowMarketDetail?) {
    Log.d("MarketDetailsScreen", "details = ${marketDetail?.market.toString()}")
    Scaffold(
        topBar = {
            AppBar(navController)
        },
        content = {
            Box(modifier = Modifier.padding(it))
        }
    )
}

@Composable
private fun AppBar(navController:NavController) {
    TopAppBar(
        title = { Text(text = "Details", color = Color.White) },
        navigationIcon = @Composable {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        },
        modifier = Modifier.statusBarsPadding(),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EE),
            navigationIconContentColor = Color.White
        )
    )
}

class MarketNavType<T : Parcelable>(private val clazz: Class<T>) : NavType<T>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key)
    }


    override fun parseValue(value: String): T {
        throw IllegalStateException("Attempt to pass a string as parcelable object.")
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)

    }

}
