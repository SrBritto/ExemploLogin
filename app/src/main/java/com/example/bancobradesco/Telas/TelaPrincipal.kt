package com.example.bancobradesco.Telas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancobradesco.R
import com.example.bancobradesco.databinding.ActivityTelaCadastroBinding
import com.example.bancobradesco.databinding.ActivityTelaPrincipalBinding
import com.google.firebase.auth.FirebaseAuth

class TelaPrincipal : AppCompatActivity() {

    private lateinit var binding: ActivityTelaPrincipalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTelaPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()


        binding.btnLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var intent=Intent(this,TelaLogin::class.java)
            startActivity(intent)
            finish()
        }

    }
}