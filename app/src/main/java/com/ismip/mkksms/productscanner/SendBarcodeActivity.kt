package com.ismip.mkksms.productscanner

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ismip.mkksms.productscanner.dataBase.Barcode
import kotlinx.android.synthetic.main.activity_send_barcode.*

class SendBarcodeActivity : Activity() {
    private var auth: FirebaseAuth = Firebase.auth
    private var database: DatabaseReference = Firebase.database.reference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_barcode)
        try {
            val barcode = intent.getStringExtra("barcode")
            editTextCode.setText(barcode)
        } catch (ex: Exception) {
        }

        buttonAdd.setOnClickListener {
            val code = editTextCode.text.toString()
            val name = editTextName.text.toString()
            val price = editTextPrice.text.toString().toDouble()

            sendBarcode(code, name, price)
        }
    }

    private fun sendBarcode(code: String, name: String, price: Double) {
        if (code.length != 13) {
            Toast.makeText(
                this,
                "Kod o niepoprawnej długości jest ${code.length} a musi być 13 cyfr",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        if (name.isEmpty()) {
            return
        }
        val barcode = Barcode(name, price.toString())
        database.child(auth.currentUser!!.uid).child(code).setValue(barcode)
        finish()
    }
}

