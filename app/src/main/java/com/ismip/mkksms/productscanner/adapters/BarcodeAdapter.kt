package com.ismip.mkksms.productscanner.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismip.mkksms.productscanner.R
import com.ismip.mkksms.productscanner.dataBase.Barcode
import kotlinx.android.synthetic.main.barcode_recycleview.view.*

class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view)


class BarcodeAdapter(private val array: Array<Barcode>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(viewGro: ViewGroup, nr: Int): MyViewHolder {
        val inflater = LayoutInflater.from(viewGro.context)
        val inflated = inflater.inflate(R.layout.barcode_recycleview, viewGro, false)
        return MyViewHolder(inflated)
    }

    override fun getItemCount(): Int {
        return array.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, nr: Int) {
        holder.view.textViewName.text = array[nr].name
        holder.view.textViewPrice.text = "${array[nr].price} zł"

        holder.view.constraint.setOnLongClickListener {

            true
        }

    }

}