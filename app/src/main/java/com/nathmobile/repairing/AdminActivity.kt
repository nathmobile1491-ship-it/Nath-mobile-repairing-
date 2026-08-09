package com.nathmobile.repairing

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {

    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setBackgroundColor(Color.rgb(244, 246, 249))

        val header = TextView(this)
        header.text = "🔐 Nath Mobile - Admin Panel"
        header.textSize = 24f
        header.setTextColor(Color.WHITE)
        header.gravity = Gravity.CENTER
        header.setPadding(20, 35, 20, 35)
        header.setBackgroundColor(Color.rgb(33, 33, 33))

        root.addView(header)

        val title = TextView(this)
        title.text = "📋 Repair Booking"
        title.textSize = 22f
        title.setTextColor(Color.DKGRAY)
        title.setPadding(20, 30, 20, 15)

        root.addView(title)

        val booking = TextView(this)

        booking.text =
            "Booking ID: NMR-950854\n\n" +
            "Customer Name: adi\n" +
            "Phone: 7037746519\n" +
            "Brand: Oppo\n" +
            "Model: Ip12 Pro\n" +
            "Problem: Battery Change\n" +
            "Pickup Address: 2828 Lakhi Bagh\n" +
            "Landmark: Sham Shan Ghat\n" +
            "Pickup Date: 13/08/2026\n" +
            "Pickup Time: 16:41"

        booking.textSize = 17f
        booking.setTextColor(Color.DKGRAY)
        booking.setPadding(20, 20, 20, 20)
        booking.setBackgroundColor(Color.WHITE)

        root.addView(booking)

        statusText = TextView(this)

        statusText.text = "Status: Booking Received ✅"
        statusText.textSize = 19f
        statusText.setTextColor(Color.rgb(46, 125, 50))
        statusText.gravity = Gravity.CENTER
        statusText.setPadding(20, 25, 20, 25)

        root.addView(statusText)

        val confirmButton = Button(this)
        confirmButton.text = "✅ CONFIRM BOOKING"

        confirmButton.setOnClickListener {
            statusText.text = "Status: Booking Confirmed ✅"
        }

        root.addView(confirmButton)

        val pickupButton = Button(this)
        pickupButton.text = "🚚 PICKED UP"

        pickupButton.setOnClickListener {
            statusText.text = "Status: Picked Up 🚚"
        }

        root.addView(pickupButton)

        val repairButton = Button(this)
        repairButton.text = "🔧 IN REPAIR"

        repairButton.setOnClickListener {
            statusText.text = "Status: In Repair 🔧"
        }

        root.addView(repairButton)

        val repairedButton = Button(this)
        repairedButton.text = "✅ REPAIRED"

        repairedButton.setOnClickListener {
            statusText.text = "Status: Repaired ✅"
        }

        root.addView(repairedButton)

        val deliveredButton = Button(this)
        deliveredButton.text = "🏠 DELIVERED"

        deliveredButton.setOnClickListener {
            statusText.text = "Status: Delivered 🏠"
        }

        root.addView(deliveredButton)

        setContentView(root)
    }
}
