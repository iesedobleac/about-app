package com.isaacdelosreyes.aboutapp.views

import android.content.Context
import android.content.pm.PackageManager
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.preference.Preference
import androidx.preference.PreferenceViewHolder
import com.bumptech.glide.Glide
import com.isaacdelosreyes.aboutapp.R
import com.isaacdelosreyes.aboutapp.utils.EMPTY_STRING
import com.isaacdelosreyes.aboutapp.utils.extensions.getAppName
import com.isaacdelosreyes.aboutapp.utils.extensions.isDarkThemeOn

class CustomHeaderPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet,
    defStyleAttr: Int = 0
) : Preference(context, attrs, defStyleAttr) {

    init {
        widgetLayoutResource = R.layout.preference_about_header
    }

    //region override methods

    override fun onBindViewHolder(holder: PreferenceViewHolder) {
        super.onBindViewHolder(holder)
        holder.apply {
            val mainBackground = findViewById(R.id.about_header__img__background_image)
                    as AppCompatImageView
            val appNameLabel = findViewById(R.id.about_header__label__app_name)
                    as AppCompatTextView
            val appVersionLabel = findViewById(R.id.about_header__label__app_version)
                    as AppCompatTextView
            setHeaderBackgroundWhenIsDarkThemeOn(mainBackground)
            appVersionLabel.text = getVersionName()
            appNameLabel.text = this@CustomHeaderPreference.context.getAppName()
        }
    }

    //endregion

    //region private methods

    private fun getVersionName(): String = try {
        val pInfo = context.packageManager.getPackageInfo(
            context.packageName,
            0
        )
        pInfo.versionName

    } catch (e: PackageManager.NameNotFoundException) {
        EMPTY_STRING
    }

    private fun setHeaderBackgroundWhenIsDarkThemeOn(mainBackground: AppCompatImageView) {
        if (this.context.isDarkThemeOn()) {
            Glide.with(this.context)
                .load(R.drawable.header_dark)
                .into(mainBackground)

        } else {
            Glide.with(this.context)
                .load(R.drawable.header_light)
                .into(mainBackground)
        }
    }

    //endregion
}