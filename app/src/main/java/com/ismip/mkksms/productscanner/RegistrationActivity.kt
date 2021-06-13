package com.ismip.mkksms.productscanner

import android.app.Activity
import android.os.Bundle
import android.util.Patterns.EMAIL_ADDRESS
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_regestration.*

class RegistrationActivity : Activity() {
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
        setContentView(R.layout.activity_regestration)
        val buttonRegisterShop = findViewById<Button>(R.id.buttonRegisterShop)
        buttonRegisterShop.setOnClickListener { registration(); }
    }

    private fun registration() {
        val password = TextPassword.text.toString().trim()
        val password2 = TextPassword2.text.toString().trim()
        val email = TextEmail.text.toString().trim()

        if (password2 != password) {
            Toast.makeText(this, "Hasła się nie zgadzają", Toast.LENGTH_LONG).show()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser!!.email
                    Toast.makeText(this, "Sukces $user", Toast.LENGTH_LONG).show()

                    finish()
                } else {

                    Toast.makeText(baseContext, "Error: ${task.exception!!.message}", Toast.LENGTH_SHORT).show()
                }
            }


    }
}