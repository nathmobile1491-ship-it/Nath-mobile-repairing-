package com.nathmobile.repairing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    // Nath Mobile Repairing ka WhatsApp/Call number
    private val shopPhoneNumber = "919758149149"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // =========================
        // MAIN BUTTONS
        // =========================

        val btnBookRepair =
            findViewById<Button>(R.id.btnBookRepair)

        val btnPickupDelivery =
            findViewById<Button>(R.id.btnPickupDelivery)

        val btnTrackRepair =
            findViewById<Button>(R.id.btnTrackRepair)

        // =========================
        // CALL & WHATSAPP
        // =========================

        val btnCall =
            findViewById<Button>(R.id.btnCall)

        val btnWhatsapp =
            findViewById<Button>(R.id.btnWhatsapp)

        // =========================
        // SERVICE CARDS
        // =========================

        val cardDisplay =
            findViewById<CardView>(R.id.cardDisplay)

        val cardBattery =
            findViewById<CardView>(R.id.cardBattery)

        val cardWater =
            findViewById<CardView>(R.id.cardWater)

        val cardSoftware =
            findViewById<CardView>(R.id.cardSoftware)

        val cardAccessories =
            findViewById<CardView>(R.id.cardAccessories)

        // =========================
        // SHOP CARD
        // =========================

        val cardShop =
            findViewById<CardView>(R.id.cardShop)


        // =========================
        // 1. BOOK A REPAIR
        // =========================

        btnBookRepair.setOnClickListener {

            val intent = Intent(
                this,
                BookingActivity::class.java
            )

            startActivity(intent)
        }


        // =========================
        // 2. PICKUP & DELIVERY
        // =========================

        btnPickupDelivery.setOnClickListener {

            val intent = Intent(
                this,
                PickupDeliveryActivity::class.java
            )

            startActivity(intent)
        }


        // =========================
        // 3. TRACK MY REPAIR
        // =========================

        btnTrackRepair.setOnClickListener {

            val intent = Intent(
                this,
                TrackRepairActivity::class.java
            )

            startActivity(intent)
        }


        // =========================
        // 4. CALL
        // =========================

        btnCall.setOnClickListener {

            val intent = Intent(
                Intent.ACTION_DIAL
            ).apply {

                data = Uri.parse(
                    "tel:$shopPhoneNumber"
                )
            }

            startActivity(intent)
        }


        // =========================
        // 5. WHATSAPP
        // =========================

        btnWhatsapp.setOnClickListener {

            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe mobile repair ke baare mein enquiry karni hai."
            )
        }


        // =========================
        // 6. DISPLAY REPAIR
        // =========================

        cardDisplay.setOnClickListener {

            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe Display & Touch Replacement ka price jaan na hai."
            )
        }


        // =========================
        // 7. BATTERY REPAIR
        // =========================

        cardBattery.setOnClickListener {

            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe Battery & Charging Jack repair ka estimate chahiye."
            )
        }


        // =========================
        // 8. WATER / MOTHERBOARD
        // =========================

        cardWater.setOnClickListener {

            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mera phone paani mein gir gaya hai / motherboard repair karwana hai."
            )
        }


        // =========================
        // 9. SOFTWARE
        // =========================

        cardSoftware.setOnClickListener {

            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe Software Unlocking / Flashing ke baare mein puchna hai."
            )
        }


        // =========================
        // 10. ACCESSORIES
        // =========================

        cardAccessories.setOnClickListener {

            openWhatsAppWithMessage(
                "Hi Nath Mobile Repairing, mujhe Mobile Accessories / Glass / Cover chahiye."
            )
        }


        // =========================
        // 11. VISIT OUR SHOP
        // =========================

        cardShop.setOnClickListener {

            val mapUrl =
                "https://www.google.com/maps/search/?api=1&query=Nath+Mobile+Repairing"

            val mapIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(mapUrl)
            )

            startActivity(mapIntent)
        }
    }


    // =========================
    // WHATSAPP FUNCTION
    // =========================

    private fun openWhatsAppWithMessage(
        message: String
    ) {

        val url =
            "https://wa.me/$shopPhoneNumber?text=${Uri.encode(message)}"

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url)
        )

        startActivity(intent)
    }
}
