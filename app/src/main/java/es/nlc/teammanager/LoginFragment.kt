package es.nlc.teammanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.nlc.teammanager.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentLoginBinding
    private var mListener: OnButtonsFragmentListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is OnButtonsFragmentListener){
            mListener = context
        }else{
            throw Exception("The activity must implement the interface OnButtonsFragmentListener")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.btnLog.setOnClickListener(this)
        binding.btnReg.setOnClickListener {
            DialogFragment().show(requireActivity().supportFragmentManager,"")
        }
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_log -> {
                if(binding.usuari.text.toString() == "Natalia" && binding.contrasenya.text.toString() == "12345"){
                    mListener?.onButtonClicked()
                }else{
                    Toast.makeText(activity, getString(R.string.wuser), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnButtonsFragmentListener{
        fun onButtonClicked()
    }


}