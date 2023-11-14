package es.nlc.teammanager


import android.content.Context

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import es.nlc.teammanager.adapters.GaleriaAdapter
import es.nlc.teammanager.clases.galeria
import es.nlc.teammanager.databinding.FragmentGaleriaBinding

class GaleriaFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentGaleriaBinding
    private var mListener: GaleriaFragment.OnButtonsFragmentListener? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGaleriaBinding.inflate(inflater, container, false)
        binding.floatingBTN.setOnClickListener(this)
        setUpRecyclerView()
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is GaleriaFragment.OnButtonsFragmentListener){
            mListener = context
        }else{
            throw Exception("The activity must implement the interface OnButtonsFragmentListener")
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.floatingBTN -> mListener?.onAddButtonClicked()
        }
    }
    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    interface OnButtonsFragmentListener{
        fun onAddButtonClicked()
    }

    private fun setUpRecyclerView() {
        val images = mutableListOf<galeria>(
            galeria(R.drawable.tres),
            galeria(R.drawable.quatre),
            galeria(R.drawable.cinc),
            galeria(R.drawable.sis),
            galeria(R.drawable.set),
            galeria(R.drawable.huit),
            galeria(R.drawable.nou)
        )
        val imageClickFunction = { image: galeria ->
            Toast.makeText(context, image.image, Toast.LENGTH_SHORT).show()
        }
        val imagesAdapter = GaleriaAdapter( context, images, imageClickFunction)

        binding.recGal.adapter = imagesAdapter
        binding.recGal.layoutManager = GridLayoutManager(context, 2)

    }
}