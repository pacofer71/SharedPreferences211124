package com.example.sharedpreferences211124

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreferences211124.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var preferences: Preferences
    private var email=""
    private var vip=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.portal_id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        preferences=Preferences(this)
        comprobarSesion()
        setListeners()

    }

    private fun setListeners() {
        binding.btnLimpiar.setOnClickListener {
            limpiar()
        }
        binding.btnEnviar.setOnClickListener {
            recogerDatos()
        }
    }

    private fun recogerDatos() {
        email=binding.etEmail.text.toString().trim()
        if(!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmail.error="Se esparaba un email válido."
            return
        }
        //el email ha sido valido
        //guardamos los valores en SharedPreferences
        preferences.setEmail(email)
        vip=binding.cbVip.isChecked
        preferences.setVip(vip)
        irActivityPortal()
    }

    private fun limpiar() {
        binding.etEmail.setText("")
        binding.cbVip.isChecked=false
    }

    private fun comprobarSesion() {
        email=preferences.getEmail()
        if(email.isNotEmpty()){
            irActivityPortal()
        }
    }
    private fun irActivityPortal(){
        startActivity(Intent(this, PortalActivity::class.java))
    }
}