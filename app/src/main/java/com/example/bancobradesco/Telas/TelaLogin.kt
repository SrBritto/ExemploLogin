package com.example.bancobradesco.Telas

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.bancobradesco.R
import com.example.bancobradesco.databinding.ActivityTelaLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class TelaLogin : AppCompatActivity() {

     lateinit var binding: ActivityTelaLoginBinding
     var auth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        binding.txtCadastrar.setOnClickListener { view->
            var intent=Intent(this,TelaCadastro::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnLogin.setOnClickListener {view->
            var edtEmail=binding.edtEmail.text.toString()
            var edtSenha=binding.edtSenha.text.toString()

            if(edtEmail.isEmpty() || edtSenha.isEmpty()){
                var snackbar=Snackbar.make(view,"Preencha todos os campos!!!",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                auth.signInWithEmailAndPassword(edtEmail,edtSenha).addOnCompleteListener { autenticacao->
                    if (autenticacao.isSuccessful){
                        var intent=Intent(this,TelaPrincipal::class.java)
                        startActivity(intent)
                        finish()
                    }
                }.addOnFailureListener { exception->
                    var msgErro=when(exception){
                        is FirebaseAuthWeakPasswordException->"Digite uma senha de pelo menos 6 dígitos"
                        is FirebaseNetworkException->"Sem conexão com a internet"
                        is FirebaseAuthInvalidCredentialsException->"Digite um e-mail válido"
                        else->"Erro ao cadastrar usuário"
                    }
                }

            }
        }


    }

}

