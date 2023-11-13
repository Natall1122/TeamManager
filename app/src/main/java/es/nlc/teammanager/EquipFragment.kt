package es.nlc.teammanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.nlc.teammanager.adapters.EquipAdapter
import es.nlc.teammanager.adapters.EventsAdapter
import es.nlc.teammanager.clases.Equip
import es.nlc.teammanager.clases.events
import es.nlc.teammanager.databinding.FragmentEquipBinding

class EquipFragment : Fragment() {
    private lateinit var binding: FragmentEquipBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEquipBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val jugadors = mutableListOf<Equip>(
            Equip("Alexia", "mig centre", R.drawable.alexia),
            Equip("Aitana", "mig centre", R.drawable.aitana),
            Equip("Patri", "mig centre", R.drawable.patri),
            Equip("Mapi", "central", R.drawable.mapi),
            Equip("Claudia", "delantera", R.drawable.claudia),
            Equip("Hansen", "delantera", R.drawable.hansen),
            Equip("Sabrina", "portera", R.drawable.sabrina),
            Equip("Taylor", "central", R.drawable.taylor),
            Equip("Enid", "Lateral", R.drawable.enid),
            Equip("Wednesday", "Lateral", R.drawable.wednesday)
        )


        val equipClickFunction = { jugador: Equip ->
            Toast.makeText(context, jugador.nom, Toast.LENGTH_SHORT).show()
        }


        val equipAdapter = EquipAdapter( context, jugadors, equipClickFunction)

        binding.recEquip.adapter = equipAdapter
        binding.recEquip.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)



    }
}