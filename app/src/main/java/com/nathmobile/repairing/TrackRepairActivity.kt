package com.nathmobile.repairing

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TrackRepairActivity : AppCompatActivity() {

    private lateinit var statusText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)

        root.orientation = LinearLayout.VERTICAL
        root.setPadding(20, 30, 20, 30)
        root.setBackgroundColor(
            Color.rgb(244, 246, 249)
        )

        // HEADER
        val header = TextView(this)

        header.text = "🔎 Track My Repair"
        header.textSize = 28f
        header.setTextColor(
            Color.rgb(30, 136, 229)
        )
        header.gravity = Gravity.CENTER
        header.setPadding(10, 20, 10, 20)

        root.addView(header)

        // DESCRIPTION
        val description = TextView(this)

        description.text =
            "Enter your Booking ID to check your repair status."

        description.textSize = 17f
        description.setTextColor(Color.DKGRAY)
        description.gravity = Gravity.CENTER
        description.setPadding(10, 10, 10, 20)

        root.addView(description)

        // BOOKING ID
        val bookingInput = EditText(this)

        bookingInput.hint = "Enter Booking ID"
        bookingInput.textSize = 18f
        bookingInput.setSingleLine(true)

        root.addView(bookingInput)

        // CHECK BUTTON
        val checkButton = Button(this)

        checkButton.text =
            "🔎 CHECK REPAIR STATUS"

        root.addView(checkButton)

        // RESULT
        statusText = TextView(this)

        statusText.textSize = 19f
        statusText.gravity = Gravity.CENTER
        statusText.setPadding(
            15,
            30,
            15,
            30
        )

        root.addView(statusText)

        // WHATSAPP
        val whatsappButton = Button(this)

        whatsappButton.text =
            "💬 ASK ON WHATSAPP"

        root.addView(whatsappButton)

        checkButton.setOnClickListener {

            val bookingId =
                bookingInput.text
                    .toString()
                    .trim()

            if (bookingId.isEmpty()) {

                statusText.text =
                    "⚠️ Please enter Booking ID"

                statusText.setTextColor(
                    Color.rgb(198, 40, 40)
                )

                return@setOnClickListener
            }

            if (
                bookingId.equals(
                    "NMR-950854",
                    ignoreCase = true
                )
            ) {

                showBookingStatus()

            } else {

                statusText.text =
                    "❌ Booking ID not found\n\n" +
                    "Please check your Booking ID."

                statusText.setTextColor(
                    Color.rgb(198, 40, 40)
                )
            }
        }

        whatsappButton.setOnClickListener {

            val url =
                "https://wa.me/919758149149"

            val intent =
                android.content.Intent(
                    android.content.Intent.ACTION_VIEW,
                    android.net.Uri.parse(url)
                )

            startActivity(intent)
        }

        setContentView(root)
    }

    private fun showBookingStatus() {

        val preferences =
            getSharedPreferences(
                "repair_data",
                Context.MODE_PRIVATE
            )

        val status =
            preferences.getString(
                "status_NMR-950854",
                "Booking Received"
            )

        val icon = when (status) {

            "Booking Confirmed" -> "🟢"

            "Picked Up" -> "🚚"

            "Repair In Progress" -> "🔧"

            "Ready for Delivery" -> "✅"

            "Delivered" -> "🏠"

            else -> "🟡"
        }

        statusText.text =
            "Booking ID: NMR-950854\n\n" +
            "Status: $icon $status\n\n" +
            "Our team will contact you shortly."

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
