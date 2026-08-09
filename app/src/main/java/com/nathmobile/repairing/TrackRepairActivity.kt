package com.nathmobile.repairing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TrackRepairActivity : AppCompatActivity() {

    private val shopPhoneNumber = "9758149149"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_repair)

        val edtBookingId = findViewById<EditText>(R.id.edtBookingId)
        val btnTrack = findViewById<Button>(R.id.btnTrack)
        val btnWhatsApp = findViewById<Button>(R.id.btnWhatsApp)
        val txtStatus = findViewById<TextView>(R.id.txtStatus)

        btnTrack.setOnClickListener {

            val bookingId = edtBookingId.text.toString().trim()

            if (bookingId.isEmpty()) {
                edtBookingId.error = "Booking ID enter karein"
                edtBookingId.requestFocus()
                return@setOnClickListener
            }

            txtStatus.text =
                "Booking ID: $bookingId\n\nStatus: Repair booking received ✅\n\n" +
                "Our team will contact you shortly."

            Toast.makeText(
                this,
                "Booking found",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnWhatsApp.setOnClickListener {

            val bookingId = edtBookingId.text.toString().trim()

            val message = if (bookingId.isEmpty()) {
                "Hello Nath Mobile Repairing, mujhe apni repair booking ka status check karna hai."
            } else {
                "Hello Nath Mobile Repairing,\n\nMeri Booking ID: $bookingId\n\nPlease meri repair ka status bataiye."
            }

            val whatsappUrl =
                "https://wa.me/91$shopPhoneNumber?text=" +
                        Uri.encode(message)

            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(whatsappUrl)
                    )
                )
            } catch (e: Exception) {
                Toast.makeText(
                    this,
                    "WhatsApp open nahi ho pa raha.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
