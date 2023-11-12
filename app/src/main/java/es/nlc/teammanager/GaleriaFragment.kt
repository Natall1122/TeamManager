package es.nlc.teammanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.nlc.teammanager.databinding.FragmentGaleriaBinding

class GaleriaFragment : Fragment() {
    private lateinit var binding: FragmentGaleriaBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGaleriaBinding.inflate(inflater, container, false)
        return binding.root
    }
}