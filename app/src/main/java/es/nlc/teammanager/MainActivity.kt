package es.nlc.teammanager

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import es.nlc.teammanager.databinding.ActivityMainBinding
import es.nlc.teammanager.fragments.ConfigFragment
import es.nlc.teammanager.fragments.EquipFragment
import es.nlc.teammanager.fragments.EventsFragment
import es.nlc.teammanager.fragments.GaleriaFragment
import es.nlc.teammanager.fragments.PrincipalFragment

class MainActivity : AppCompatActivity(), ConfigFragment.onButtonsListener, EventsFragment.OnButtonsClickedListener, NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener, GaleriaFragment.OnButtonsFragmentListener{

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        binding.bottomNavigation.setOnItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        return when(item.itemId){
            R.id.action_settings ->{ supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ConfigFragment>(R.id.fragment_container)
                addToBackStack(null)
            }
                true
            }
            else -> false
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.item_event -> {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<EventsFragment>(R.id.fragment_container)
                    addToBackStack(null)
                }
                true
            }
            R.id.item_home -> {
                supportFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace<PrincipalFragment>(R.id.fragment_container)
                    addToBackStack(null)
                }
                true
            }
            R.id.item_equip ->{ supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<EquipFragment>(R.id.fragment_container)
                addToBackStack(null)
            }
                true
            }
            R.id.item_galeria ->{ supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<GaleriaFragment>(R.id.fragment_container)
                addToBackStack(null)
            }
                true
            }else -> false
        }
    }

    companion object {
        private const val MY_PERMISSION_REQUEST_CODE = 123
    }

    override fun onAddButtonClicked() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_MEDIA_IMAGES
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_MEDIA_IMAGES),
                MY_PERMISSION_REQUEST_CODE
            )
        } else {
            Intent(this, PermissionsActivity::class.java).apply {
                putExtra("GRANTED", true)
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == MY_PERMISSION_REQUEST_CODE) {
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                startActivity(
                    Intent(this, PermissionsActivity::class.java).apply {
                        putExtra("GRANTED", true)
                    }
                )
            }else {
                startActivity(Intent(this, PermissionsActivity::class.java))
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onButtonClicked() {
        Toast.makeText(this,"WE'RE WORKING ON THIS FUNCIONALITY, SORRY", Toast.LENGTH_SHORT).show()
    }

    override fun onDeleteClicked() {
        Toast.makeText(this, "DELETING ACOUNT...", Toast.LENGTH_SHORT).show()
    }

    override fun onBackClicked(){
        val main = Intent(this, LoginActivity::class.java)
        startActivity(main)
    }


}