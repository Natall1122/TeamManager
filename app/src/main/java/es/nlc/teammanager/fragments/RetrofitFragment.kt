package es.nlc.teammanager.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.map
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import es.nlc.teammanager.CatsInterface
import es.nlc.teammanager.adapters.CatsAdapter
import es.nlc.teammanager.database.AppDB
import es.nlc.teammanager.database.CatDao
import es.nlc.teammanager.database.CatEntity
import es.nlc.teammanager.databinding.FragmentRetrofitBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RetrofitFragment : Fragment() {
    private lateinit var binding: FragmentRetrofitBinding
    val cats = mutableListOf<String>()
    private lateinit var productsAdapter: CatsAdapter
    val llistaTemporal = mutableListOf<String>()
    private lateinit var catDao: CatDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRetrofitBinding.inflate(inflater, container, false)
        val appDB = AppDB.getDatabase(requireContext())
        catDao = appDB.catDao()

        setReciclerView()
        binding.buscar.setOnClickListener {
            if(isNetworkAvailable()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    val call = RetrofitObject.getInstance()
                        .create(CatsInterface::class.java).getCats()
                    val response = call.body()

                    if (isOfflineEnabled()) {
                        cats.clear()
                        cats.addAll(llistaTemporal)
                        catDao.deleteAll()
                        saveToDatabase(cats)
                    }

                    llistaTemporal.clear()
                    response?.forEach { element ->
                        llistaTemporal.add(element.url)
                    }

                    withContext(Dispatchers.Main) {
                        withContext(Dispatchers.Main) {
                            updateCats(llistaTemporal)
                        }
                    }
                }
            }else{
                lifecycleScope.launch(Dispatchers.IO) {
                    val catsString = mutableListOf<String>()
                    catDao.getAll().forEach { element ->
                        catsString.add(element.url)
                    }

                withContext(Dispatchers.Main) {
                    updateCats(catsString)
                }
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

    private fun saveToDatabase(cats: MutableList<String>) {
        val catEntities = cats.map { url ->
            CatEntity(url = url, width = 0, height = 0)
        }.toMutableList()

        lifecycleScope.launch(Dispatchers.IO) {
            catDao.insertAll(catEntities)
        }
    }


    private fun isOfflineEnabled(): Boolean {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        return sharedPreferences.getBoolean("Offline", false)
    }

    private fun isNetworkAvailable(): Boolean {
        val connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
    }


}