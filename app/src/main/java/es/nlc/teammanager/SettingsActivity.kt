package es.nlc.teammanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import es.nlc.teammanager.databinding.ActivitySettingsBinding
import es.nlc.teammanager.fragments.ConfigFragment

class SettingsActivity : AppCompatActivity(), ConfigFragment.onButtonsListener {
    private lateinit var binding: ActivitySettingsBinding
    private val authManager = AuthManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDeleteClicked() {
        Toast.makeText(this, getString(R.string.del), Toast.LENGTH_SHORT).show()
        authManager.deleteUser()
        startActivity(
            Intent(this, LoginActivity::class.java).apply { }
        )
    }

    override fun onBackClicked(){
        authManager.logOut()
        Toast.makeText(this, R.string.logOut, Toast.LENGTH_SHORT).show()
        startActivity(
            Intent(this, LoginActivity::class.java).apply { }
        )
    }
}