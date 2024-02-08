package es.nlc.teammanager.fragments

import android.os.Bundle
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat
import es.nlc.teammanager.AuthManager
import es.nlc.teammanager.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsFragment : PreferenceFragmentCompat() {
    private val authManager = AuthManager()
    private lateinit var usernamePreference: EditTextPreference

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        usernamePreference = findPreference("usernameChat")!!
        usernamePreference.setOnPreferenceChangeListener { preference, newValue ->
            val newUsername = newValue as String
            GlobalScope.launch {
                authManager.setUsername(newUsername)
            }
            true
        }

        // Carrega el nom d'usuari des del Firestore i actualitza la preferència d'EditText
        GlobalScope.launch {
            val username = authManager.getUsername()
            withContext(Dispatchers.Main) {
                username?.let {
                    usernamePreference.text = it
                }
            }
        }
    }
}