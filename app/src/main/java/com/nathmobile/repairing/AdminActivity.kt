package com.nathmobile.repairing

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AdminActivity : AppCompatActivity() {

    private lateinit var statusText: TextView

    private val bookingId = "NMR-950854"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

        // BOOKING DETAILS
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

        // STATUS
        statusText = TextView(this)
        statusText.textSize = 19f
        statusText.gravity = Gravity.CENTER
        statusText.setPadding(15, 25, 15, 25)

        root.addView(statusText)

        // LOAD SAVED STATUS
        showSavedStatus()

        // CONFIRM
        val confirmButton = Button(this)
        confirmButton.text = "✅ CONFIRM BOOKING"

        confirmButton.setOnClickListener {
            saveStatus("Booking Confirmed")
        }

        root.addView(confirmButton)

        // PICKED UP
        val pickupButton = Button(this)
        pickupButton.text = "🚚 PICKED UP"

        pickupButton.setOnClickListener {
            saveStatus("Picked Up")
        }

        root.addView(pickupButton)

        // IN REPAIR
        val repairButton = Button(this)
        repairButton.text = "🔧 IN REPAIR"

        repairButton.setOnClickListener {
            saveStatus("Repair In Progress")
        }

        root.addView(repairButton)

        // READY
        val readyButton = Button(this)
        readyButton.text = "✅ READY FOR DELIVERY"

        readyButton.setOnClickListener {
            saveStatus("Ready for Delivery")
        }

        root.addView(readyButton)

        // DELIVERED
        val deliveredButton = Button(this)
        deliveredButton.text = "🏠 DELIVERED"

        deliveredButton.setOnClickListener {
            saveStatus("Delivered")
        }

        root.addView(deliveredButton)

        // RESET
        val resetButton = Button(this)
        resetButton.text = "🔄 RESET STATUS"

        resetButton.setOnClickListener {
            saveStatus("Booking Received")
        }

        root.addView(resetButton)

        setContentView(root)
    }

    private fun saveStatus(status: String) {

        val preferences =
            getSharedPreferences(
                "repair_data",
                Context.MODE_PRIVATE
            )

        preferences.edit()
            .putString(
                "status_$bookingId",
                status
            )
            .apply()

        showStatus(status)
    }

    private fun showSavedStatus() {

        val preferences =
            getSharedPreferences(
                "repair_data",
                Context.MODE_PRIVATE
            )

        val savedStatus =
            preferences.getString(
                "status_$bookingId",
                "Booking Received"
            )

        showStatus(savedStatus ?: "Booking Received")
    }

    private fun showStatus(status: String) {

        val icon = when (status) {

            "Booking Confirmed" -> "🟢"

            "Picked Up" -> "🚚"

            "Repair In Progress" -> "🔧"

            "Ready for Delivery" -> "✅"

            "Delivered" -> "🏠"

            else -> "🟡"
        }

        statusText.text =
            "CURRENT STATUS\n\n$icon $status"

        statusText.setTextColor(
            when (status) {

                "Booking Confirmed" ->
                    Color.rgb(46, 125, 50)

                "Picked Up" ->
                    Color.rgb(2, 119, 189)

                "Repair In Progress" ->
                    Color.rgb(123, 31, 162)

                "Ready for Delivery" ->
                    Color.rgb(46, 125, 50)

                "Delivered" ->
                    Color.rgb(27, 94, 32)

                else ->
                    Color.rgb(245, 124, 0)
            }
        )
    }
}
