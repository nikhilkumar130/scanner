package com.example.scanner

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.setOnClickListener(View.OnClickListener {
            var II: IntentIntegrator = IntentIntegrator(this)
            II.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            II.setPrompt("Scanner: ")
            II.setCameraId(0);
            II.setBeepEnabled(true)
            II.setOrientationLocked(true)
            II.initiateScan()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var IntentResult: IntentResult =
            IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (IntentResult.contents != null) {

            Toast.makeText(this, "Scanned Succesfully!", Toast.LENGTH_SHORT).show()
            var AB: AlertDialog.Builder = AlertDialog.Builder(this)
            AB.setTitle("Result: ")
            AB.setMessage(IntentResult.contents)
            AB.setPositiveButton(
                "OK",
                DialogInterface.OnClickListener { dialog, which -> dialog.dismiss() })
            AB.show()
        } else {
            Toast.makeText(this, "Scanned Failed!!", Toast.LENGTH_SHORT).show()

            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}