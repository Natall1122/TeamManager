package es.nlc.teammanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import es.nlc.teammanager.CatsInterface
import es.nlc.teammanager.adapters.CatsAdapter
import es.nlc.teammanager.databinding.FragmentRetrofitBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    val cats = mutableListOf<String>()
    private lateinit var productsAdapter: CatsAdapter
    val llistaTemporal = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        setReciclerView()


        binding.buscar.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                val call = RetrofitObject.getInstance()
                    .create(CatsInterface::class.java).getCats()
                val response = call.body()
                llistaTemporal.clear()

                response?.forEach { element ->
                    llistaTemporal.add(element.url)
                }

                withContext(Dispatchers.Main){
                    updateCats(llistaTemporal)
                }
            }

        }

        return binding.root
    }

    fun setReciclerView(){
        productsAdapter = CatsAdapter(requireActivity(), cats)
        binding.recView.adapter = productsAdapter
        binding.recView.layoutManager = GridLayoutManager(context, 2)
    }

    private fun updateCats(gats: List<String>){
        cats.clear()
        cats.addAll(gats)
        binding.recView.adapter?.notifyDataSetChanged()
    }

}