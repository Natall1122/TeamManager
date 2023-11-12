package es.nlc.teammanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.nlc.teammanager.databinding.FragmentEquipBinding

class EquipFragment : Fragment() {
    private lateinit var binding: FragmentEquipBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEquipBinding.inflate(inflater, container, false)
        return binding.root
    }
}