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
            Equip("Alexia", getString(R.string.mc), R.drawable.alexia),
            Equip("Aitana", getString(R.string.mc), R.drawable.aitana),
            Equip("Patri", getString(R.string.mc), R.drawable.patri),
            Equip("Mapi", getString(R.string.dfc), R.drawable.mapi),
            Equip("Claudia", getString(R.string.dc), R.drawable.claudia),
            Equip("Hansen", getString(R.string.dc), R.drawable.hansen),
            Equip("Sabrina", getString(R.string.gk), R.drawable.sabrina),
            Equip("Taylor", getString(R.string.dfc), R.drawable.taylor),
            Equip("Enid", getString(R.string.lb), R.drawable.enid),
            Equip("Wednesday", getString(R.string.lb), R.drawable.wednesday)
        )


        val equipClickFunction = { jugador: Equip ->
            Toast.makeText(context, jugador.nom, Toast.LENGTH_SHORT).show()
        }


        val equipAdapter = EquipAdapter( context, jugadors, equipClickFunction)

        binding.recEquip.adapter = equipAdapter
        binding.recEquip.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)



    }
}