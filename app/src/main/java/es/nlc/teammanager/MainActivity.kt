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
import es.nlc.teammanager.fragments.ChatFragment
import es.nlc.teammanager.fragments.EquipFragment
import es.nlc.teammanager.fragments.EventsFragment
import es.nlc.teammanager.fragments.GaleriaFragment
import es.nlc.teammanager.fragments.PrincipalFragment
import es.nlc.teammanager.fragments.RetrofitFragment

class MainActivity : AppCompatActivity(), EventsFragment.OnButtonsClickedListener, NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener, GaleriaFragment.OnButtonsFragmentListener{

    private lateinit var binding: ActivityMainBinding
    private val authManager = AuthManager()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        binding.bottomNavigation.setOnItemSelectedListener(this)

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                12345)
        }else {
            Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId){
        R.id.action_settings ->{
            startActivity(
                Intent(this, SettingsActivity::class.java).apply {  }
            )
            true
        }
        R.id.action_close ->{
            authManager.logOut()
            Toast.makeText(this, R.string.logOut, Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this, LoginActivity::class.java).apply { }
            )
            true
        }
        R.id.action_delete ->{
            Toast.makeText(this, getString(R.string.del), Toast.LENGTH_SHORT).show()
            authManager.deleteUser()
            startActivity(
                Intent(this, LoginActivity::class.java).apply { }
            )
            true
        }else -> {
            false
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
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
            }R.id.item_gats ->{ supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<RetrofitFragment>(R.id.fragment_container)
                addToBackStack(null)
            }
                true
            }R.id.item_chat ->{ supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<ChatFragment>(R.id.fragment_container)
                addToBackStack(null)
            }
                true
            }
            else -> false
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
        Toast.makeText(this,getString(R.string.working), Toast.LENGTH_SHORT).show()
    }


}