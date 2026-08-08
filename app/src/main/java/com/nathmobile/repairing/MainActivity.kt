package com.nathmobile.repairing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    // ⚠️ Yahan "919758149149" ki jagah apna asli mobile number daalein
    private val shopPhoneNumber = "919758149149" 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnWhatsapp = findViewById<Button>(R.id.btnWhatsapp)
        val cardShop = findViewById<CardView>(R.id.cardShop)

        // 1. CALL US Button
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$shopPhoneNumber")
            }
            startActivity(intent)
        }

        // 2. WHATSAPP Button
        btnWhatsapp.setOnClickListener {
            openWhatsAppWithMessage("Hi, mujhe mobile repair ke baare mein enquiry karni hai.")
        }

        // 3. VISIT OUR SHOP (Google Maps Link)
        cardShop.setOnClickListener {
            val mapUrl = "https://www.google.com/maps/search/?api=1&query=Nath+Mobile+Repairing"
            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
            startActivity(mapIntent)
        }
    }

    private fun openWhatsAppWithMessage(message: String) {
        val url = "https://wa.me/$shopPhoneNumber?text=${Uri.encode(message)}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
