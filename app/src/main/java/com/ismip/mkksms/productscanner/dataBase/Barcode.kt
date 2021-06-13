package com.ismip.mkksms.productscanner.dataBase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Barcode(val name: String? = null,
                   val price: String? = null)
