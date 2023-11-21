package es.nlc.teammanager

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.nlc.teammanager.databinding.FragmentConfigBinding


class ConfigFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentConfigBinding
    private var mListener: onButtonsListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigBinding.inflate(inflater, container, false)
        binding.delete.setOnClickListener(this)
        binding.back.setOnClickListener(this)
        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is onButtonsListener){
            mListener = context
        }else{
            throw Exception("The activity must implement the interface OnButtonsFragmentListener")
        }
    }
    override fun onClick(v: View) {
        when(v.id){
            R.id.delete -> mListener?.onDeleteClicked()
            R.id.back -> mListener?.onBackClicked()
        }
    }
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface onButtonsListener{
        fun onDeleteClicked()
        fun onBackClicked()
    }
}