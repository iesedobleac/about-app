package com.isaacdelosreyes.aboutapp.utils.extensions

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.FragmentActivity
import androidx.window.layout.WindowMetricsCalculator

fun FragmentActivity.openDetailAppSettings() {
    val packageName = packageName
    val intent = Intent()
    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    val uri: Uri = Uri.fromParts(
        "package",
        packageName,
        null
    )
    intent.data = uri
    startActivity(intent)
}

fun Activity.getScreenWidth(): Int {
    val windowMetrics = WindowMetricsCalculator
        .getOrCreate()
        .computeCurrentWindowMetrics(this)
    val currentBounds = windowMetrics.bounds
    return currentBounds.width()
}