package com.ajbell.technicaltest

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.fragment.fragment
import androidx.navigation.navArgument
import com.ajbell.technicaltest.detail.MarketDetailScreen
import com.ajbell.technicaltest.list.MarketListEvent
import com.ajbell.technicaltest.list.MarketListFragment

@Composable
fun MarketAppNavHost(navController: NavHostController, mainActivity: MainActivity) {

    NavHost(navController = navController, startDestination = "marketListFragment") {

        composable("marketListFragment") {
            FragmentInComposeExample(mainActivity)
        }
        composable(
            "marketDetailScreen/{market}",
            arguments = listOf(navArgument("market") {
                type = NavType.ParcelableType(MarketListEvent.ShowMarketDetail::class.java)
            })
        ) { backStackEntry ->
            val marketDetail = backStackEntry.arguments?.getParcelable<MarketListEvent.ShowMarketDetail>("market")
//            MarketDetailScreen(navController, marketDetail)
        }

    }
}

@Composable
fun FragmentInComposeExample(activity: MainActivity) {
    AndroidView(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        factory = { context ->
            // Create the FragmentContainerView programmatically
            FragmentContainerView(context).apply {
                id = android.R.id.content // Give the view an ID
                // Add the Fragment to the FragmentManager
                activity.supportFragmentManager.commit {
                    replace(this@apply.id, MarketListFragment())
                }
            }
        }
    )
}