package es.nlc.teammanager.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import es.nlc.teammanager.AuthManager
import es.nlc.teammanager.R
import es.nlc.teammanager.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginFragment : Fragment(),  View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    private var mListener: OnButtonsFragmentListener? = null
    private val authManager = AuthManager()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is OnButtonsFragmentListener){
            mListener = context
        }else{
            throw Exception(R.string.implement.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnLog.setOnClickListener(this)
        binding.btnReg.setOnClickListener(this)
        binding.Fpass?.setOnClickListener(this)


        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_log -> {
                val email = binding.usuari.text.toString()
                val pass = binding.contrasenya.text.toString()
                if(!email.isNullOrBlank() && !pass.isNullOrBlank()){
                    lifecycleScope.launch(Dispatchers.IO){
                        val userLogged = authManager.login(email,pass)
                        withContext(Dispatchers.Main) {
                            if (userLogged != null) {
                                mListener?.onLoginButtonClicked()
                            }else{
                                Toast.makeText(activity,R.string.wrong, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }else{
                    Toast.makeText(activity, R.string.noUser, Toast.LENGTH_SHORT).show()
                }
            }
            R.id.btn_reg ->{
                mListener?.onSignUpButtonClicked()
            }
            R.id.Fpass ->{
                mListener?.onRememberClicked()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnButtonsFragmentListener{
        fun onLoginButtonClicked()
        fun onSignUpButtonClicked()
        fun onRememberClicked()
    }

}