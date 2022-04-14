package com.isaacdelosreyes.aboutapp.utils.extensions

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.fragment.app.FragmentActivity

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