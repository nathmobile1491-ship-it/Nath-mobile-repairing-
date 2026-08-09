package com.nathmobile.repairing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
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

        // BOOK A REPAIR
        btnBookRepair.setOnClickListener {
            startActivity(
                Intent(this, BookingActivity::class.java)
            )
        }

        // PICKUP & DELIVERY
        btnPickupDelivery.setOnClickListener {
            startActivity(
                Intent(this, PickupDeliveryActivity::class.java)
            )
        }

        // TRACK REPAIR
        btnTrackRepair.setOnClickListener {
            startActivity(
                Intent(this, TrackRepairActivity::class.java)
            )
        }

        // CALL
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$shopPhoneNumber")
            startActivity(intent)
        }

        // WHATSAPP
        btnWhatsapp.setOnClickListener {
            openWhatsApp(
                "Hi Nath Mobile Repairing, mujhe mobile repair ke baare mein enquiry karni hai."
            )
        }

        // DISPLAY
        cardDisplay.setOnClickListener {
            openWhatsApp(
                "Hi Nath Mobile Repairing, mujhe Display & Touch Replacement ka price jaan na hai."
            )
        }

        // BATTERY
        cardBattery.setOnClickListener {
            openWhatsApp(
                "Hi Nath Mobile Repairing, mujhe Battery & Charging Jack repair ka estimate chahiye."
            )
        }

        // WATER DAMAGE
        cardWater.setOnClickListener {
            openWhatsApp(
                "Hi Nath Mobile Repairing, mera phone paani mein gir gaya hai / motherboard repair karwana hai."
            )
        }

        // SOFTWARE
        cardSoftware.setOnClickListener {
            openWhatsApp(
                "Hi Nath Mobile Repairing, mujhe Software Unlocking / Flashing ke baare mein puchna hai."
            )
        }

        // ACCESSORIES
        cardAccessories.setOnClickListener {
            openWhatsApp(
                "Hi Nath Mobile Repairing, mujhe Mobile Accessories / Glass / Cover chahiye."
            )
        }

        // SHOP LOCATION
        cardShop.setOnClickListener {

            val mapUrl =
                "https://www.google.com/maps/search/?api=1&query=Nath+Mobile+Repairing"

            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(mapUrl)
            )

            startActivity(intent)
        }
    }

    private fun openWhatsApp(message: String) {

        val url =
            "https://wa.me/$shopPhoneNumber?text=${Uri.encode(message)}"

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )

        startActivity(intent)
    }
}
