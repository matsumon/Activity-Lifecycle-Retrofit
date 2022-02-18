package com.example.android.connectedweather.ui

import android.os.Bundle
import androidx.preference.MultiSelectListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.android.connectedweather.R
//copied from rob hess: https://github.com/osu-cs492-w22/GitHubSearchWithSettings/blob/main/app/src/main/java/com/example/android/githubsearchwithsettings/ui/SettingsFragment.kt
class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
//        val languagePref: MultiSelectListPreference? = findPreference(
//            getString(R.string.pref_language_key)
//        )
//
//        languagePref?.summaryProvider = Preference.SummaryProvider<MultiSelectListPreference> {
//            val n = it.values.size
//            if (n == 0) {
//                getString(R.string.pref_language_not_set)
//            } else {
//                resources.getQuantityString(R.plurals.pref_language_summary, n, n)
//            }
//        }
    }

}