package com.ismip.mkksms.productscanner

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.ismip.mkksms.productscanner.adapters.SlideAdapter
import kotlinx.android.synthetic.main.activity_menu.*

class MenuActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        vievpager.adapter = SlideAdapter(this)
    }
}