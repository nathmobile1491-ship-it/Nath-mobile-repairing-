package com.nathmobile.repairing

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {

    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scrollView = ScrollView(this)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(16, 16, 16, 30)
        root.setBackgroundColor(Color.rgb(244, 246, 249))

        // HEADER
        val header = TextView(this)
        header.text = "🔐 Nath Mobile Repairing\nAdmin Panel"
        header.textSize = 24f
        header.setTextColor(Color.WHITE)
        header.gravity = Gravity.CENTER
        header.setPadding(15, 30, 15, 30)
        header.setBackgroundColor(Color.rgb(103, 58, 183))

        root.addView(header)

        // TITLE
        val title = TextView(this)
        title.text = "📋 Repair Booking"
        title.textSize = 22f
        title.setTextColor(Color.DKGRAY)
        title.setPadding(5, 25, 5, 15)

        root.addView(title)

        // BOOKING CARD
        val booking = TextView(this)

        booking.text =
            "Booking ID: NMR-950854\n\n" +
            "Customer: Adi\n" +
            "Phone: 7037746519\n" +
            "Brand: Oppo\n" +
            "Model: Ip12 Pro\n" +
            "Problem: Battery Change\n" +
            "Address: 2828 Lakhi Bagh\n" +
            "Landmark: Sham Shan Ghat\n" +
            "Pickup Date: 13/08/2026\n" +
            "Pickup Time: 16:41"

        booking.textSize = 17f
        booking.setTextColor(Color.rgb(50, 50, 50))
        booking.setPadding(20, 20, 20, 20)
        booking.setBackgroundColor(Color.WHITE)

        root.addView(booking)

        // CURRENT STATUS
        statusText = TextView(this)

        statusText.text = "CURRENT STATUS\n\n🟡 Booking Received"
        statusText.textSize = 19f
        statusText.gravity = Gravity.CENTER
        statusText.setTextColor(Color.rgb(245, 124, 0))
        statusText.setPadding(15, 25, 15, 25)

        root.addView(statusText)

        // CONFIRM
        val confirmButton = Button(this)
        confirmButton.text = "✅ CONFIRM BOOKING"

        confirmButton.setOnClickListener {
            statusText.text = "CURRENT STATUS\n\n🟢 Booking Confirmed"
            statusText.setTextColor(Color.rgb(46, 125, 50))
        }

        root.addView(confirmButton)

        // PICKUP
        val pickupButton = Button(this)
        pickupButton.text = "🚚 PICKED UP"

        pickupButton.setOnClickListener {
            statusText.text = "CURRENT STATUS\n\n🚚 Picked Up"
            statusText.setTextColor(Color.rgb(2, 119, 189))
        }

        root.addView(pickupButton)

        // REPAIR
        val repairButton = Button(this)
        repairButton.text = "🔧 IN REPAIR"

        repairButton.setOnClickListener {
            statusText.text = "CURRENT STATUS\n\n🔧 Repair In Progress"
            statusText.setTextColor(Color.rgb(123, 31, 162))
        }

        root.addView(repairButton)

        // READY
        val readyButton = Button(this)
        readyButton.text = "✅ READY FOR DELIVERY"

        readyButton.setOnClickListener {
            statusText.text = "CURRENT STATUS\n\n✅ Ready for Delivery"
            statusText.setTextColor(Color.rgb(46, 125, 50))
        }

        root.addView(readyButton)

        // DELIVERED
        val deliveredButton = Button(this)
        deliveredButton.text = "🏠 DELIVERED"

        deliveredButton.setOnClickListener {
            statusText.text = "CURRENT STATUS\n\n🏠 Delivered"
            statusText.setTextColor(Color.rgb(27, 94, 32))
        }

        root.addView(deliveredButton)

        // RESET
        val resetButton = Button(this)
        resetButton.text = "🔄 RESET STATUS"

        resetButton.setOnClickListener {
            statusText.text = "CURRENT STATUS\n\n🟡 Booking Received"
            statusText.setTextColor(Color.rgb(245, 124, 0))
        }

        root.addView(resetButton)

        scrollView.addView(root)

        setContentView(scrollView)
    }
}
