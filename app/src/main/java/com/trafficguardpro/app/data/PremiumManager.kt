package com.trafficguardpro.app.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class PremiumManager {
    var isPremium by mutableStateOf(false)
    var showAds by mutableStateOf(true)

    fun activatePremium() {
        isPremium = true
        showAds = false
    }

    fun disableAds() {
        showAds = false
    }

    fun restorePurchase() {
        isPremium = true
        showAds = false
    }
}
