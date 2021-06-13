package com.ismip.mkksms.productscanner

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import kotlinx.android.synthetic.main.activity_bardercode.*
import java.io.IOException


class BarcodeActivity : Activity() {
    var qrCode = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bardercode)
        buttonAccept.setOnClickListener {
            if (qrCode.length == 13) {
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("barcode", qrCode)
                applicationContext.startActivity(intent)
            } else {
                Toast.makeText(applicationContext, "Niepoprawny kod kreskowy", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        val barcodeDetector =
            BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.EAN_13).build()
        val cameraSource = CameraSource.Builder(this, barcodeDetector)
            .setAutoFocusEnabled(true)
            .setRequestedPreviewSize(640, 480)
            .build()
        cameraView.holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                if (ActivityCompat.checkSelfPermission(
                        applicationContext,
                        Manifest.permission.CAMERA
                    )
                    != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                try {
                    cameraSource.start(holder)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                cameraSource.stop()
            }
        })
        barcodeDetector.setProcessor(object : Detector.Processor<Barcode> {
            override fun release() {}
            override fun receiveDetections(detections: Detector.Detections<Barcode>) {
                if (detections.detectedItems.size() != 0) {
                    textView.post {
                        textView.text = detections.detectedItems.valueAt(0).displayValue
                        qrCode = detections.detectedItems.valueAt(0).displayValue
                        textView.text = qrCode
                    }
                }
            }
        })
    }
}