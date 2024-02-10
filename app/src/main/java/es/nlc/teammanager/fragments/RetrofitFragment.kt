package es.nlc.teammanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.nlc.teammanager.R
import es.nlc.teammanager.adapters.JokesAdapter
import es.nlc.teammanager.clases.Jokes
import es.nlc.teammanager.clases.RetrofitObject
import es.nlc.teammanager.databinding.FragmentRememberBinding
import es.nlc.teammanager.databinding.FragmentRetrofitBinding
import es.nlc.teammanager.jokesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    val jokes = mutableListOf<Jokes>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)


        lifecycleScope.launch(Dispatchers.IO){
            val call = RetrofitObject.getInstance()
                .create(jokesService::class.java).getJokes()

            withContext(Dispatchers.Main){
                if(call.isSuccessful) {
                    val info = call.body()!!
                    jokes.clear()
                    jokes.addAll(info)
                    binding.recView.adapter?.notifyDataSetChanged()
                }
            }
        }
        setReciclerView()
        return binding.root
    }

    fun setReciclerView(){

        val productsAdapter = JokesAdapter( context, jokes)

        binding.recView.adapter = productsAdapter
        binding.recView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}