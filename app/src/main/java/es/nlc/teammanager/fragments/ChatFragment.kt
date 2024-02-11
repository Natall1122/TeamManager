package es.nlc.teammanager.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import es.nlc.teammanager.AuthManager
import es.nlc.teammanager.FirestoreManager
import es.nlc.teammanager.R
import es.nlc.teammanager.adapters.MissatgesAdapter
import es.nlc.teammanager.clases.Missatges
import es.nlc.teammanager.databinding.FragmentChatBinding
import es.nlc.teammanager.databinding.FragmentEquipBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private val authManager = AuthManager()
    private lateinit var missatges: MutableList<Missatges>
    private lateinit var mAdapter: MissatgesAdapter
    private val firestoreManager: FirestoreManager by lazy { FirestoreManager() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(inflater, container, false)

        binding.btnEnviarMiss.setOnClickListener{
            var nom = authManager.getEmail()
            var missatge = binding.missatge.text.toString()
            createNewNote(nom!!, missatge)
            binding.missatge.setText("")
        }
        setRecyclerView()
        return binding.root
    }


    private fun setRecyclerView(){
        missatges = mutableListOf()
        binding.recMiss.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        mAdapter = MissatgesAdapter(requireContext(), missatges)
        binding.recMiss.adapter = mAdapter

        lifecycleScope.launch (Dispatchers.IO){
            firestoreManager.getNotesFlow()
                .collect{ notesUpdated ->
                    missatges.clear()
                    missatges.addAll(notesUpdated)
                    withContext(Dispatchers.Main){
                        mAdapter.notifyDataSetChanged()
                    }
                }

        }
    }

    private fun createNewNote(nom: String, missatge: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val newMissatge = Missatges(nom=nom, missatge=missatge)
                val inserted = firestoreManager.addMissatge(newMissatge)

                withContext(Dispatchers.Main) {

                    if(inserted){
                        Toast.makeText(context, "Insertat", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context, "no insertat", Toast.LENGTH_SHORT).show()
                    }
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}