package com.nathmobile.repairing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    private val shopPhoneNumber = "919758149149"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnBookRepair = findViewById<Button>(R.id.btnBookRepair)
        val btnPickupDelivery = findViewById<Button>(R.id.btnPickupDelivery)
        val btnTrackRepair = findViewById<Button>(R.id.btnTrackRepair)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnWhatsapp = findViewById<Button>(R.id.btnWhatsapp)

        val cardDisplay = findViewById<CardView>(R.id.cardDisplay)
        val cardBattery = findViewById<CardView>(R.id.cardBattery)
        val cardWater = findViewById<CardView>(R.id.cardWater)
        val cardSoftware = findViewById<CardView>(R.id.cardSoftware)
        val cardAccessories = findViewById<CardView>(R.id.cardAccessories)
        val cardShop = findViewById<CardView>(R.id.cardShop)

        btnBookRepair.setOnClickListener {
            startActivity(Intent(this, BookingActivity::class.java))
        }

        btnPickupDelivery.setOnClickListener {
            startActivity(Intent(this, PickupDeliveryActivity::class.java))
        }

        btnTrackRepair.setOnClickListener {
            startActivity(Intent(this, TrackRepairActivity::class.java))
        }

        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$shopPhoneNumber")
            startActivity(intent)
        }

        btnWhatsapp.setOnClickListener {
            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe mobile repair ke baare mein enquiry karni hai."
            )
        }

        cardDisplay.setOnClickListener {
            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe Display & Touch Replacement ka price jaan na hai."
            )
        }

        cardBattery.setOnClickListener {
            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe Battery & Charging Jack repair ka estimate chahiye."
            )
        }

        cardWater.setOnClickListener {
            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mera phone paani mein gir gaya hai / motherboard repair karwana hai."
            )
        }

        cardSoftware.setOnClickListener {
            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe Software Unlocking / Flashing ke baare mein puchna hai."
            )
        }

        cardAccessories.setOnClickListener {
            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe Mobile Accessories / Glass / Cover chahiye."
            )
        }

        cardShop.setOnClickListener {
            val mapUrl =
                "https://www.google.com/maps/search/?api=1&query=Nath+Mobile+Repairing"

            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
            )
        }

        // ADMIN PANEL
        addAdminButton()
    }

    private fun addAdminButton() {

        val root = findViewById<LinearLayout>(android.R.id.content)

        val adminButton = Button(this)

        adminButton.text = "🔐 ADMIN PANEL"
        adminButton.textSize = 17f
        adminButton.setTextColor(android.graphics.Color.WHITE)
        adminButton.setBackgroundColor(
            android.graphics.Color.rgb(111, 66, 193)
        )

        adminButton.setOnClickListener {

            // Admin page
            val adminUrl =
                "https://nathmobile1491-ship-it.github.io/Nath-mobile-repairing-/admin.html"

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(adminUrl)
            )

            startActivity(intent)
        }

        root.addView(adminButton)
    }

    private fun openWhatsAppWithMessage(message: String) {

        val url =
            "https://wa.me/$shopPhoneNumber?text=${Uri.encode(message)}"

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )

        startActivity(intent)
    }
}
