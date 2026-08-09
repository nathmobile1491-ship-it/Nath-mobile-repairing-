package com.nathmobile.repairing

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class BookingActivity : AppCompatActivity() {

    private val shopPhoneNumber = "9758149149"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        val edtName = findViewById<EditText>(R.id.edtName)
        val edtPhone = findViewById<EditText>(R.id.edtPhone)
        val edtBrand = findViewById<EditText>(R.id.edtBrand)
        val edtModel = findViewById<EditText>(R.id.edtModel)
        val edtProblem = findViewById<EditText>(R.id.edtProblem)
        val edtAddress = findViewById<EditText>(R.id.edtAddress)
        val edtLandmark = findViewById<EditText>(R.id.edtLandmark)
        val edtPickupDate = findViewById<EditText>(R.id.edtPickupDate)
        val edtPickupTime = findViewById<EditText>(R.id.edtPickupTime)

        val btnAddPhoto = findViewById<Button>(R.id.btnAddPhoto)
        val btnSubmitBooking = findViewById<Button>(R.id.btnSubmitBooking)
        val checkTerms = findViewById<CheckBox>(R.id.checkTerms)

        btnAddPhoto.setOnClickListener {
            Toast.makeText(
                this,
                "Photo upload next step mein add karenge.",
                Toast.LENGTH_SHORT
            ).show()
        }

        edtPickupDate.setOnClickListener {

            val calendar = Calendar.getInstance()

            DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->

                    val date = String.format(
                        "%02d/%02d/%04d",
                        dayOfMonth,
                        month + 1,
                        year
                    )

                    edtPickupDate.setText(date)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        edtPickupTime.setOnClickListener {

            val calendar = Calendar.getInstance()

            TimePickerDialog(
                this,
                { _, hourOfDay, minute ->

                    val time = String.format(
                        "%02d:%02d",
                        hourOfDay,
                        minute
                    )

                    edtPickupTime.setText(time)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            ).show()
        }

        btnSubmitBooking.setOnClickListener {

            val name = edtName.text.toString().trim()
            val phone = edtPhone.text.toString().trim()
            val brand = edtBrand.text.toString().trim()
            val model = edtModel.text.toString().trim()
            val problem = edtProblem.text.toString().trim()
            val address = edtAddress.text.toString().trim()
            val landmark = edtLandmark.text.toString().trim()
            val pickupDate = edtPickupDate.text.toString().trim()
            val pickupTime = edtPickupTime.text.toString().trim()

            if (name.isEmpty()) {
                edtName.error = "Name enter karein"
                edtName.requestFocus()
                return@setOnClickListener
            }

            if (!phone.matches(Regex("^[0-9]{10}$"))) {
                edtPhone.error = "10 digit mobile number enter karein"
                edtPhone.requestFocus()
                return@setOnClickListener
            }

            if (brand.isEmpty()) {
                edtBrand.error = "Mobile brand enter karein"
                edtBrand.requestFocus()
                return@setOnClickListener
            }

            if (model.isEmpty()) {
                edtModel.error = "Mobile model enter karein"
                edtModel.requestFocus()
                return@setOnClickListener
            }

            if (problem.isEmpty()) {
                edtProblem.error = "Repair problem enter karein"
                edtProblem.requestFocus()
                return@setOnClickListener
            }

            if (address.isEmpty()) {
                edtAddress.error = "Pickup address enter karein"
                edtAddress.requestFocus()
                return@setOnClickListener
            }

            if (pickupDate.isEmpty()) {
                edtPickupDate.error = "Pickup date select karein"
                edtPickupDate.requestFocus()
                return@setOnClickListener
            }

            if (pickupTime.isEmpty()) {
                edtPickupTime.error = "Pickup time select karein"
                edtPickupTime.requestFocus()
                return@setOnClickListener
            }

            if (!checkTerms.isChecked) {
                Toast.makeText(
                    this,
                    "Please confirm the information.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            // UNIQUE BOOKING ID
            val bookingId =
                "NMR-" + (System.currentTimeMillis() % 1000000)

            // SAVE BOOKING LOCALLY
            val preferences = getSharedPreferences(
                "repair_data",
                Context.MODE_PRIVATE
            )

            preferences.edit()
                .putString("booking_id", bookingId)
                .putString("customer_name", name)
                .putString("customer_phone", phone)
                .putString("brand", brand)
                .putString("model", model)
                .putString("problem", problem)
                .putString("address", address)
                .putString("landmark", landmark)
                .putString("pickup_date", pickupDate)
                .putString("pickup_time", pickupTime)
                .putString("status_$bookingId", "Booking Received")
                .apply()

            // WHATSAPP MESSAGE
            val message = """
                🔧 *NEW REPAIR BOOKING*
                
                *Booking ID:* $bookingId
                
                👤 *Customer Name:* $name
                📞 *Customer Phone:* $phone
                
                📱 *Brand:* $brand
                📱 *Model:* $model
                
                🔧 *Repair Problem:*
                $problem
                
                📍 *Pickup Address:*
                $address
                
                🏠 *Landmark:*
                $landmark
                
                📅 *Pickup Date:* $pickupDate
                ⏰ *Pickup Time:* $pickupTime
                
                🚚 *Service:* Pickup & Delivery
            """.trimIndent()

            val whatsappUrl =
                "https://wa.me/91$shopPhoneNumber?text=${Uri.encode(message)}"

            try {

                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(whatsappUrl)
                    )
                )

            } catch (e: Exception) {

                Toast.makeText(
                    this,
                    "WhatsApp open nahi ho pa raha.",
                    Toast.LENGTH_LONG
                ).show()
            }

            Toast.makeText(
                this,
                "Booking Created!\nBooking ID: $bookingId",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
