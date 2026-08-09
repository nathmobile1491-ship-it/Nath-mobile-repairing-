package com.nathmobile.repairing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class PickupDeliveryActivity : AppCompatActivity() {

    private val shopPhoneNumber = "9758149149"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pickup_delivery)

        val edtName = findViewById<EditText>(R.id.edtPickupName)
        val edtPhone = findViewById<EditText>(R.id.edtPickupPhone)
        val edtAddress = findViewById<EditText>(R.id.edtPickupAddress)
        val edtLandmark = findViewById<EditText>(R.id.edtPickupLandmark)
        val edtDevice = findViewById<EditText>(R.id.edtPickupDevice)
        val edtProblem = findViewById<EditText>(R.id.edtPickupProblem)

        val btnSubmit = findViewById<Button>(R.id.btnPickupSubmit)

        btnSubmit.setOnClickListener {

            val name = edtName.text.toString().trim()
            val phone = edtPhone.text.toString().trim()
            val address = edtAddress.text.toString().trim()
            val landmark = edtLandmark.text.toString().trim()
            val device = edtDevice.text.toString().trim()
            val problem = edtProblem.text.toString().trim()

            if (name.isEmpty()) {
                edtName.error = "Name enter karein"
                return@setOnClickListener
            }

            if (phone.length != 10) {
                edtPhone.error = "10 digit mobile number enter karein"
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                edtAddress.error = "Pickup address enter karein"
                return@setOnClickListener
            }

            if (device.isEmpty()) {
                edtDevice.error = "Mobile/device model enter karein"
                return@setOnClickListener
            }

            if (problem.isEmpty()) {
                edtProblem.error = "Repair problem enter karein"
                return@setOnClickListener
            }

            val bookingId =
                "NMR-" + (System.currentTimeMillis() % 1000000)

            getSharedPreferences("repair_data", MODE_PRIVATE)
                .edit()
                .putString("last_booking_id", bookingId)
                .putString(
                    "status_$bookingId",
                    "Pickup Requested"
                )
                .apply()

            val message = """
                🚚 *PICKUP & DELIVERY REQUEST*

                *Booking ID:* $bookingId

                👤 Name: $name
                📞 Phone: $phone

                📱 Device: $device
                🔧 Problem: $problem

                📍 Pickup Address:
                $address

                🏠 Landmark:
                $landmark

                🚚 Service: Pickup & Delivery

                Please confirm the pickup request.
            """.trimIndent()

            val whatsappUrl =
                "https://wa.me/91$shopPhoneNumber?text=" +
                        Uri.encode(message)

            try {

                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(whatsappUrl)
                )

                startActivity(intent)

                Toast.makeText(
                    this,
                    "Booking ID: $bookingId",
                    Toast.LENGTH_LONG
                ).show()

            } catch (e: Exception) {

                Toast.makeText(
                    this,
                    "WhatsApp open nahi ho pa raha.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
