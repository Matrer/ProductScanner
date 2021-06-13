package com.ismip.mkksms.productscanner.adapters

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.ismip.mkksms.productscanner.BarcodeActivity
import com.ismip.mkksms.productscanner.BasketActivity
import com.ismip.mkksms.productscanner.R
import com.ismip.mkksms.productscanner.SendBarcodeActivity


class SlideAdapter(private val context: Context) : PagerAdapter() {

    private var images = intArrayOf(
        R.drawable.ic_qr_code_scanner_white_48dp,
        R.drawable.ic_edit_white_48dp,
        R.drawable.ic_shopping_basket_white_48dp
    )

    private var titles = arrayOf(
        "Skanuj",
        "Wpisz ręcznie",
        "Koszyk"
    )

    private var lst_description = arrayOf(
        "Zeskanuj kod kreskowy",
        "Wpisz kod kreskowy ręcznie",
        "Dodane kody kreskowe"
    )

    private var lst_backgroundcolor = intArrayOf(
        Color.rgb(55, 55, 55),
        Color.rgb(55, 55, 55),
        Color.rgb(55, 55, 55)
    )

    override fun getCount(): Int {
        return titles.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.slide, container, false)
        val layoutSlider = view.findViewById<View>(R.id.slideAdapter) as LinearLayout
        val imgSlider = view.findViewById<View>(R.id.slideimage) as ImageView
        val txtTitle = view.findViewById<View>(R.id.titleOpisIcon) as TextView
        val description = view.findViewById<View>(R.id.opisTitleIcon) as TextView

        layoutSlider.setBackgroundColor(lst_backgroundcolor[position])
        imgSlider.setImageResource(images[position])
        txtTitle.text = titles[position]
        description.text = lst_description[position]
        container.addView(view)

        imgSlider.setOnClickListener {
            if (position == 0) {
                val intent = Intent(context, BarcodeActivity::class.java)
                context.startActivity(intent)
            }
            if (position == 1) {
                val intent = Intent(context, SendBarcodeActivity::class.java)
                context.startActivity(intent)
            }
            if (position == 2) {
                val intent = Intent(context, BasketActivity::class.java)
                context.startActivity(intent)
            }
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}