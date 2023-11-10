package es.nlc.teammanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import es.nlc.teammanager.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), DialogFragment.DialogListener, LoginFragment.OnButtonsFragmentListener {


    private var registrat = false
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDialogClick() {
        Snackbar.make(binding.root, getString(R.string.creat), Snackbar.LENGTH_SHORT).show()
        registrat = true
    }

    override fun onButtonClicked() {
        if (registrat == true){
            val main = Intent(this, MainActivity::class.java)
            startActivity(main)
        }else{
            Snackbar.make(binding.root, getString(R.string.noreg), Snackbar.LENGTH_SHORT).show()
        }

    }


}