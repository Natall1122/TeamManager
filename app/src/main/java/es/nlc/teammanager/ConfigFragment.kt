package es.nlc.teammanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.nlc.teammanager.databinding.FragmentConfigBinding
import es.nlc.teammanager.databinding.FragmentEventsBinding

class ConfigFragment : Fragment() {
    private lateinit var binding: FragmentConfigBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConfigBinding.inflate(inflater, container, false)
        return binding.root
    }
}