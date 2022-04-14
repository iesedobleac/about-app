package com.isaacdelosreyes.aboutapp.utils.extensions

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import com.isaacdelosreyes.aboutapp.utils.TYPE_INTENT_SHARE

fun Context.isDarkThemeOn() = resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

fun Context.getAppName() = applicationInfo
    .loadLabel(packageManager)
    .toString()

fun Context.shareLink(message: String) {
    val intent = Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, message)
        type = TYPE_INTENT_SHARE
    }, null)
    startActivity(intent)
}