package es.nlc.teammanager.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import es.nlc.teammanager.AuthManager
import es.nlc.teammanager.FirestoreManager
import es.nlc.teammanager.MainActivity
import es.nlc.teammanager.R
import es.nlc.teammanager.Services.PushNotificationServices
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

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())

        binding.btnEnviarMiss.setOnClickListener{
            var nomDef = authManager.getEmail().toString()
            var nom = sharedPreferences.getString("usernameChat", nomDef)
            var missatge = binding.missatge.text.toString()
            enviarMissatge(nom!!, missatge)
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

    private fun enviarMissatge(nom: String, missatge: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val newMissatge = Missatges(nom=nom, missatge=missatge)
                val inserted = firestoreManager.addMissatge(newMissatge)

                withContext(Dispatchers.Main) {

                    if(inserted){
                        Toast.makeText(context, "Insertat", Toast.LENGTH_SHORT).show()
                        enviarNotificacion("Nuevo mensaje", "Has recibido un nuevo mensaje")
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

    private fun enviarNotificacion(titulo: String, cuerpo: String) {
        val intent = Intent(context, MainActivity::class.java).apply{
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(context, 0, intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notificationBuilder = NotificationCompat.Builder(requireContext(), PushNotificationServices.CHANNEL_ID)
            .setContentTitle(titulo)
            .setContentText(cuerpo)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val vibrateEnabled = sharedPreferences.getBoolean("vibrate", false)

        if (vibrateEnabled) {
            vibrate()
        }
        val manager = ContextCompat.getSystemService(requireContext(), NotificationManager::class.java) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                PushNotificationServices.CHANNEL_ID, PushNotificationServices.CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }
        manager.notify(1,notificationBuilder.build())
    }

    private fun vibrate(){
        var vibrator: Vibrator
        if(Build.VERSION.SDK_INT>=31){
            val vibratorManager = requireActivity().getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibrator = vibratorManager.defaultVibrator
        }else{
            @Suppress("DEPRECATION")
            vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }

        val mVibratePattern = longArrayOf(0, 400, 200, 400)
        if(Build.VERSION.SDK_INT >=26){
            vibrator.vibrate(VibrationEffect.createWaveform(mVibratePattern, -1))
        }else{
            @Suppress("DEPRECATION")
            vibrator.vibrate(mVibratePattern, -1)
        }
    }


}