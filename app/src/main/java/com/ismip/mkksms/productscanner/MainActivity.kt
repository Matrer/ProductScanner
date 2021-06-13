package com.ismip.mkksms.productscanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {
    private var auth: FirebaseAuth = Firebase.auth

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            Firebase.auth.signOut()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonRegisterShop.setOnClickListener {
            Toast.makeText(applicationContext, "Rejestracja", Toast.LENGTH_LONG).show()
            openRegistrationActivity()
        }
        TextLogin.setOnClickListener { loginFunction() }
        editTextEmail.setText(UserGlobal.getUserEmail(applicationContext))
        editTextPassword.setText(UserGlobal.getPassword(applicationContext))

    }

    private fun openRegistrationActivity() {
        val intent = Intent(applicationContext, RegistrationActivity::class.java)
        startActivity(intent)
    }

    private fun loginFunction() {
        val email = editTextEmail.text.toString()
        val pass = editTextPassword.text.toString()

        if (pass.isNotEmpty() || pass.isNotEmpty()) {
            login(email, pass)
        } else {
            editTextEmail.error = "Brak loginu!"
            editTextPassword.error = "Brak hasła!"
        }

    }

    private fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(applicationContext, MenuActivity::class.java)
                    val user = auth.currentUser?.email?: "error"
                    intent.putExtra("login", user)
                    UserGlobal.save(user, password, applicationContext)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Error ${task.exception!!.message}", Toast.LENGTH_SHORT).show()
                }
            }


    }
}