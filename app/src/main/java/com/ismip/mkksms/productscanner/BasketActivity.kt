package com.ismip.mkksms.productscanner

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ismip.mkksms.productscanner.adapters.BarcodeAdapter
import com.ismip.mkksms.productscanner.dataBase.Barcode
import kotlinx.android.synthetic.main.activity_basket.*


class BasketActivity : Activity() {
    private var database: DatabaseReference = Firebase.database.reference
    private var auth: FirebaseAuth = Firebase.auth


    override fun onCreate(savedInstanceState: Bundle?) {
        val uidd = auth.currentUser!!.uid
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basket)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 2)

        buttonDelete.setOnClickListener {
            database.child(uidd).removeValue()
            updateAdapter(uidd)
        }
        updateAdapter(uidd)
    }

    private fun updateAdapter(userUIDD:String) {

        database.child(userUIDD).get().addOnSuccessListener {
            val barcodes = mutableListOf<Barcode>()
            for (ds in it.children) {
                val name = ds.child("name").getValue(String::class.java)
                val price = ds.child("price").getValue(String::class.java)
                barcodes.add(Barcode(name, price, ds.key))
            }
            recyclerView.adapter = BarcodeAdapter(barcodes, userUIDD, database)
            val sumOf = barcodes.sumOf { n -> (n.price?:"0").toDouble() }
            textViewSum.text = "$sumOf zł"

        }.addOnFailureListener {
            finish()
        }
    }

}