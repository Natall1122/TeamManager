package es.nlc.teammanager.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import es.nlc.teammanager.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}