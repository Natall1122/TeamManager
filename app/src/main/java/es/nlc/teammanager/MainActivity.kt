package es.nlc.teammanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import es.nlc.teammanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, NavigationBarView.OnItemSelectedListener{

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


}