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
import es.nlc.teammanager.databinding.FragmentRegistrationBinding

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegistrationFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentRegistrationBinding
    private var mListener: OnRegistrationListener? = null
    private val authManager = AuthManager()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is OnRegistrationListener){
            mListener = context
        }else{
            throw Exception(R.string.implement.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        binding.btnReg.setOnClickListener(this)


        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btnReg -> {
                val email = binding.user.text.toString()
                val pass = binding.cont.text.toString()
                if (!email.isNullOrBlank() && !pass.isNullOrBlank()) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val userSignedUp = authManager.signUp(email, pass)
                        withContext(Dispatchers.Main) {
                            if (userSignedUp != null) {
                                mListener?.onRegistreClicked()
                            } else {
                                Toast.makeText(activity, R.string.errorReg, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(activity, R.string.noUser, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnRegistrationListener{
        fun onRegistreClicked()
    }
}