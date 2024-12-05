package com.example.sharedpreferences211124

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.sharedpreferences211124.databinding.ActivityPortalBinding

class PortalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPortalBinding
    private lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityPortalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.portal_id)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        preferences=Preferences(this)
        recuperarPreferencias()
        setListeners()
    }

    private fun setListeners() {
        binding.btnSalir.setOnClickListener {
            finishAffinity()
        }
        binding.btnCerrarSesion.setOnClickListener {
            preferences.borrarTodo()
            finish()
        }
    }

    private fun recuperarPreferencias() {
        val email=preferences.getEmail()
        binding.tvEmail.text=email
        val vip=preferences.isVip()
        if(vip){
            binding.portalId.setBackgroundColor(getColor(R.color.color_vip))
        }else{
            binding.portalId.setBackgroundColor(getColor(R.color.color_normal))
        }
    }
}