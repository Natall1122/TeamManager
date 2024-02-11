package es.nlc.teammanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.nlc.teammanager.CatsInterface
import es.nlc.teammanager.adapters.CatsAdapter
import es.nlc.teammanager.clases.Cats
import es.nlc.teammanager.databinding.FragmentRetrofitBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    val cats = mutableListOf<String>()
    val productsAdapter = CatsAdapter( context, cats)
    val llistaTemporal = mutableListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)

        binding.buscar.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO){
                val call = RetrofitObject.getInstance()
                    .create(CatsInterface::class.java).getCats()
                val response = call.body()
                response?.forEach { element ->
                    llistaTemporal.add(element.url)
                }

                withContext(Dispatchers.Main){
                    withContext(Dispatchers.Main){
                        updateCats(llistaTemporal)
                    }
                }
            }
            setReciclerView()
        }

        return binding.root
    }

    fun setReciclerView(){
        binding.recView.adapter = productsAdapter
        binding.recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun updateCats(gats: List<String>){
        cats.clear()
        cats.addAll(gats)
        binding.recView.adapter?.notifyDataSetChanged()
    }

}