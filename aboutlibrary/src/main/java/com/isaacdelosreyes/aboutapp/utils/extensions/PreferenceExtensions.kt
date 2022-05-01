package com.isaacdelosreyes.aboutapp.utils.extensions

import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

fun PreferenceFragmentCompat.findPreferenceByKey(preference: String) =
    this.findPreference<Preference>(preference)
