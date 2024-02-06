package es.nlc.teammanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.snackbar.Snackbar
import es.nlc.teammanager.databinding.ActivityLoginBinding
import es.nlc.teammanager.databinding.ActivityMainBinding
import es.nlc.teammanager.fragments.LoginFragment
import es.nlc.teammanager.fragments.PrincipalFragment
import es.nlc.teammanager.fragments.RegistrationFragment
import es.nlc.teammanager.fragments.RememberFragment

class LoginActivity : AppCompatActivity(), RememberFragment.OnRegistrationListener, RegistrationFragment.OnRegistrationListener, LoginFragment.OnButtonsFragmentListener{

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onLoginButtonClicked() {
        Toast.makeText(this, R.string.logged, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java).apply {
            putExtra("GRANTED", true)
        })
    }

    override fun onSignUpButtonClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RegistrationFragment>(R.id.fragment_container)
            addToBackStack(null)
        }
    }

    override fun onRememberClicked() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<RememberFragment>(R.id.fragment_container)
            addToBackStack(null)
        }
    }

    override fun onRegistreClicked() {
        Snackbar.make(binding.root, R.string.regCorrect, Snackbar.LENGTH_LONG).show()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.fragment_container)
            addToBackStack(null)
        }
    }

    override fun onRemClicked() {
        Toast.makeText(this, R.string.send, Toast.LENGTH_LONG).show()
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace<LoginFragment>(R.id.fragment_container)
            addToBackStack(null)
        }
    }


}