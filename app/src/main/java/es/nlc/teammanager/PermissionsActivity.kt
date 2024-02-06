package es.nlc.teammanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class PermissionsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permissions)

        val extras = intent.extras
        val granted = extras?.getBoolean("GRANTED")?:false

        if(granted){
            Toast.makeText(this, getString(R.string.donat), Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }else{
            Toast.makeText(this, getString(R.string.denegat), Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}