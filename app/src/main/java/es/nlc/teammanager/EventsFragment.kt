package es.nlc.teammanager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.nlc.teammanager.adapters.EventsAdapter
import es.nlc.teammanager.clases.events
import es.nlc.teammanager.databinding.FragmentEventsBinding
import es.nlc.teammanager.databinding.FragmentPrincipalBinding

class EventsFragment : Fragment() {
    private lateinit var binding: FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventsBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val products = mutableListOf<events>(
            events("Entrenament", "14/11" , "20:30", "22:00", 15, 2, 3),
            events("Entrenament", "16/11" , "20:30", "22:00", 17, 2, 1 ),
            events("Partit", "19/11" , "17:30", "19:15", 16, 3, 1 ),
            events("Entrenament", "21/11" , "20:30", "22:00", 12, 5, 3),
            events("Entrenament", "23/11" , "20:30", "22:00", 16, 2, 2 ),
            events("Partit", "26/11" , "19:00", "20:45", 16, 3, 1 ),
            events("Entrenament", "28/11" , "20:30", "22:00", 15, 2, 3),
            events("Entrenament", "30/11" , "20:30", "22:00", 17, 2, 1 ),
            events("Partit", "03/12" , "17:30", "19:15", 16, 3, 1 ),
            events("Entrenament", "05/12" , "20:30", "22:00", 15, 2, 3),
            events("Entrenament", "14/12" , "20:30", "22:00", 17, 2, 1 ),
            events("Partit", "17/12" , "19:00", "20:45", 16, 3, 1 ),
            events("Entrenament", "19/12" , "20:30", "22:00", 15, 2, 3),
            events("Entrenament", "21/12" , "20:30", "22:00", 17, 2, 1 ),
            events("Partit", "14/01" , "17:30", "19:15", 16, 3, 1 ),
            events("Entrenament", "16/01" , "20:30", "22:00", 15, 2, 3),
            events("Entrenament", "18/01" , "20:30", "22:00", 17, 2, 1 ),
            events("Partit", "21/01" , "19:00", "20:45", 16, 3, 1 ),
        )


        val productClickFunction = { product: events ->
            Toast.makeText(context, product.nom, Toast.LENGTH_SHORT).show()
        }


        val productsAdapter = EventsAdapter( context, products, productClickFunction)

        binding.recView.adapter = productsAdapter
        binding.recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)



    }
}