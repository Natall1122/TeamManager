package es.nlc.teammanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import es.nlc.teammanager.adapters.EventsAdapter
import es.nlc.teammanager.clases.events
import es.nlc.teammanager.databinding.FragmentPrincipalBinding

class PrincipalFragment : Fragment() {
    private lateinit var binding: FragmentPrincipalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPrincipalBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        return binding.root
    }

    private fun setUpRecyclerView() {
        val products = mutableListOf<events>(
            events(getString(R.string.entrene), "14/11" , "20:30", "22:00", 15, 2, 3),
            events(getString(R.string.entrene), "16/11" , "20:30", "22:00", 17, 2, 1 ),
            events(getString(R.string.partit), "19/11" , "17:30", "19:15", 16, 3, 1 )
        )


        val productClickFunction = { product: events ->
            Toast.makeText(context, product.nom, Toast.LENGTH_SHORT).show()
        }


        val productsAdapter = EventsAdapter( context, products, productClickFunction)

        binding.recView.adapter = productsAdapter
        binding.recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)



    }
}