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

    private val defaultBookingId = "NMR-950854"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setPadding(16, 16, 16, 30)
        root.setBackgroundColor(
            Color.rgb(244, 246, 249)
        )

        // HEADER
        val header = TextView(this)
        header.text = "🔐 Nath Mobile Repairing\nAdmin Panel"
        header.textSize = 24f
        header.setTextColor(Color.WHITE)
        header.gravity = Gravity.CENTER
        header.setPadding(15, 30, 15, 30)
        header.setBackgroundColor(
            Color.rgb(103, 58, 183)
        )

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
        booking.textSize = 16f
        booking.setTextColor(
            Color.rgb(50, 50, 50)
        )
        booking.setPadding(20, 20, 20, 20)
        booking.setBackgroundColor(Color.WHITE)

        root.addView(booking)

        // STATUS
        statusText = TextView(this)
        statusText.textSize = 19f
        statusText.gravity = Gravity.CENTER
        statusText.setPadding(15, 25, 15, 25)

        root.addView(statusText)

        // LOAD BOOKING
        loadBooking(booking)

        // CONFIRM
        val confirmButton = Button(this)
        confirmButton.text = "✅ CONFIRM BOOKING"

        confirmButton.setOnClickListener {
            saveStatus(
                getCurrentBookingId(booking),
                "Booking Confirmed"
            )
        }

        root.addView(confirmButton)

        // PICKED UP
        val pickupButton = Button(this)
        pickupButton.text = "🚚 PICKED UP"

        pickupButton.setOnClickListener {
            saveStatus(
                getCurrentBookingId(booking),
                "Picked Up"
            )
        }

        root.addView(pickupButton)

        // IN REPAIR
        val repairButton = Button(this)
        repairButton.text = "🔧 IN REPAIR"

        repairButton.setOnClickListener {
            saveStatus(
                getCurrentBookingId(booking),
                "Repair In Progress"
            )
        }

        root.addView(repairButton)

        // READY
        val readyButton = Button(this)
        readyButton.text = "📦 READY FOR DELIVERY"

        readyButton.setOnClickListener {
            saveStatus(
                getCurrentBookingId(booking),
                "Ready for Delivery"
            )
        }

        root.addView(readyButton)

        // DELIVERED
        val deliveredButton = Button(this)
        deliveredButton.text = "🏠 DELIVERED"

        deliveredButton.setOnClickListener {
            saveStatus(
                getCurrentBookingId(booking),
                "Delivered"
            )
        }

        root.addView(deliveredButton)

        // RESET
        val resetButton = Button(this)
        resetButton.text = "🔄 RESET STATUS"

        resetButton.setOnClickListener {
            saveStatus(
                getCurrentBookingId(booking),
                "Booking Received"
            )
        }

        root.addView(resetButton)

        setContentView(root)
    }

    private fun loadBooking(
        bookingView: TextView
    ) {

        val preferences =
            getSharedPreferences(
                "repair_data",
                Context.MODE_PRIVATE
            )

        val bookingId =
            preferences.getString(
                "booking_id",
                null
            )

        if (bookingId == null) {

            bookingView.text =
                "📭 No new booking found.\n\n" +
                "Customer booking karega to yahan details dikhegi."

            showStatus(
                defaultBookingId,
                "Booking Received"
            )

            return
        }

        val name =
            preferences.getString(
                "customer_name",
                ""
            )

        val phone =
            preferences.getString(
                "customer_phone",
                ""
            )

        val brand =
            preferences.getString(
                "brand",
                ""
            )

        val model =
            preferences.getString(
                "model",
                ""
            )

        val problem =
            preferences.getString(
                "problem",
                ""
            )

        val address =
            preferences.getString(
                "address",
                ""
            )

        val landmark =
            preferences.getString(
                "landmark",
                ""
            )

        val pickupDate =
            preferences.getString(
                "pickup_date",
                ""
            )

        val pickupTime =
            preferences.getString(
                "pickup_time",
                ""
            )

        bookingView.text =
            "Booking ID: $bookingId\n\n" +
            "Customer: $name\n" +
            "Phone: $phone\n\n" +
            "Brand: $brand\n" +
            "Model: $model\n\n" +
            "Problem:\n$problem\n\n" +
            "Address:\n$address\n\n" +
            "Landmark: $landmark\n\n" +
            "Pickup Date: $pickupDate\n" +
            "Pickup Time: $pickupTime"

        val savedStatus =
            preferences.getString(
                "status_$bookingId",
                "Booking Received"
            )

        showStatus(
            bookingId,
            savedStatus ?: "Booking Received"
        )
    }

    private fun getCurrentBookingId(
        bookingView: TextView
    ): String {

        val preferences =
            getSharedPreferences(
                "repair_data",
                Context.MODE_PRIVATE
            )

        return preferences.getString(
            "booking_id",
            defaultBookingId
        ) ?: defaultBookingId
    }

    private fun saveStatus(
        bookingId: String,
        status: String
    ) {

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

        showStatus(
            bookingId,
            status
        )
    }

    private fun showStatus(
        bookingId: String,
        status: String
    ) {

        val icon = when (status) {

            "Booking Confirmed" -> "🟢"

            "Picked Up" -> "🚚"

            "Repair In Progress" -> "🔧"

            "Ready for Delivery" -> "📦"

            "Delivered" -> "🏠"

            else -> "🟡"
        }

        statusText.text =
            "CURRENT STATUS\n\n" +
            "$icon $status"

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
