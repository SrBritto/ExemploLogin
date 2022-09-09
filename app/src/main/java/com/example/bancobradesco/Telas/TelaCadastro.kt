package com.example.bancobradesco.Telas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bancobradesco.R
import com.example.bancobradesco.databinding.ActivityTelaCadastroBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.*

class TelaCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityTelaCadastroBinding
    var auth=FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityTelaCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()

        var btn_Cadastrar=binding.btnCadastrar

        btn_Cadastrar.setOnClickListener { view->



            var edt_Email=binding.edtEmail.text.toString()
            var edt_Senha=binding.edtSenha.text.toString()

            if(edt_Email.isEmpty() || edt_Senha.isEmpty()){
                var snackbar= Snackbar.make(view,"Preencha todos os campos!!!",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                auth.createUserWithEmailAndPassword(edt_Email,edt_Senha).addOnCompleteListener { cadastro->
                    if(cadastro.isSuccessful){
                        var snackbar=Snackbar.make(view,"Cadastro criado com sucesso!",Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.BLUE)
                        snackbar.show()
                    }
                }.addOnFailureListener { exception->
                    var msgErro=when(exception){

                        is FirebaseAuthWeakPasswordException->"A senha deve conter pelo menos 6 números"
                        is FirebaseAuthInvalidCredentialsException->"Digite um e-mail válido"
                        is FirebaseNetworkException->"Aparelho sem conexão com a internet "
                        is FirebaseAuthUserCollisionException->"E-mail já usado,tente outro"

                        else->"Erro ao cadastrar usuário"
                        
                    }
                }
            }

        }


    }


}