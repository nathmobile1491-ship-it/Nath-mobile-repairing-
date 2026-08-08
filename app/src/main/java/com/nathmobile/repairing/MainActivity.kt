package com.example.nathmobilerepairing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    // ⚠️ Yahan "91XXXXXXXXXX" ki jagah apna asli mobile number daalein
    private val shopPhoneNumber = "91XXXXXXXXXX" 

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnWhatsapp = findViewById<Button>(R.id.btnWhatsapp)
        val cardShop = findViewById<CardView>(R.id.cardShop)

        val cardDisplay = findViewById<CardView>(R.id.cardDisplay)
        val cardBattery = findViewById<CardView>(R.id.cardBattery)

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

        // 3. Service Cards Enquiry
        cardDisplay.setOnClickListener {
            openWhatsAppWithMessage("Hi, mujhe Display & Touch Replacement ka price jaan na hai.")
        }

        cardBattery.setOnClickListener {
            openWhatsAppWithMessage("Hi, mujhe Battery & Charging Jack repair ka estimate chahiye.")
        }

        // 4. VISIT OUR SHOP (Fixed Google Maps Link)
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
