package com.nathmobile.repairing

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale

class PickupDeliveryActivity : AppCompatActivity() {

    private val shopPhoneNumber = "9758149149"

    private val locationPermissionCode = 1001

    private lateinit var edtAddress: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_pickup_delivery)

        val edtName =
            findViewById<EditText>(R.id.edtPickupName)

        val edtPhone =
            findViewById<EditText>(R.id.edtPickupPhone)

        edtAddress =
            findViewById<EditText>(R.id.edtPickupAddress)

        val edtLandmark =
            findViewById<EditText>(R.id.edtPickupLandmark)

        val edtDevice =
            findViewById<EditText>(R.id.edtPickupDevice)

        val edtProblem =
            findViewById<EditText>(R.id.edtPickupProblem)

        val btnLocation =
            findViewById<Button>(R.id.btnCurrentLocation)

        val btnSubmit =
            findViewById<Button>(R.id.btnPickupSubmit)


        // ==============================
        // USE MY LOCATION
        // ==============================

        btnLocation.setOnClickListener {

            if (
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ),
                    locationPermissionCode
                )

            } else {

                getCurrentLocation()
            }
        }


        // ==============================
        // REQUEST PICKUP
        // ==============================

        btnSubmit.setOnClickListener {

            val name =
                edtName.text.toString().trim()

            val phone =
                edtPhone.text.toString().trim()

            val address =
                edtAddress.text.toString().trim()

            val landmark =
                edtLandmark.text.toString().trim()

            val device =
                edtDevice.text.toString().trim()

            val problem =
                edtProblem.text.toString().trim()


            // NAME
            if (name.isEmpty()) {

                edtName.error =
                    "Name enter karein"

                edtName.requestFocus()

                return@setOnClickListener
            }


            // PHONE
            if (phone.length != 10) {

                edtPhone.error =
                    "10 digit mobile number enter karein"

                edtPhone.requestFocus()

                return@setOnClickListener
            }


            // ADDRESS
            if (address.isEmpty()) {

                edtAddress.error =
                    "Pickup address enter karein"

                edtAddress.requestFocus()

                return@setOnClickListener
            }


            // DEVICE
            if (device.isEmpty()) {

                edtDevice.error =
                    "Mobile/device model enter karein"

                edtDevice.requestFocus()

                return@setOnClickListener
            }


            // PROBLEM
            if (problem.isEmpty()) {

                edtProblem.error =
                    "Repair problem enter karein"

                edtProblem.requestFocus()

                return@setOnClickListener
            }


            // BOOKING ID
            val bookingId =
                "NMR-" +
                        (System.currentTimeMillis() % 1000000)


            // SAVE BOOKING
            getSharedPreferences(
                "repair_data",
                MODE_PRIVATE
            )
                .edit()
                .putString(
                    "last_booking_id",
                    bookingId
                )
                .putString(
                    "status_$bookingId",
                    "Pickup Requested"
                )
                .apply()


            // WHATSAPP MESSAGE
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


    // ==============================
    // GET CURRENT LOCATION
    // ==============================

    private fun getCurrentLocation() {

        Toast.makeText(
            this,
            "Location detect ho rahi hai...",
            Toast.LENGTH_SHORT
        ).show()


        val locationManager =
            getSystemService(
                LOCATION_SERVICE
            ) as LocationManager


        try {

            if (
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return
            }


            val providers =
                locationManager.getProviders(true)


            var bestLocation: Location? = null


            for (provider in providers) {

                if (
                    provider ==
                    LocationManager.GPS_PROVIDER ||
                    provider ==
                    LocationManager.NETWORK_PROVIDER
                ) {

                    val location =
                        locationManager
                            .getLastKnownLocation(provider)


                    if (location != null) {

                        if (
                            bestLocation == null ||
                            location.accuracy <
                            bestLocation!!.accuracy
                        ) {

                            bestLocation = location
                        }
                    }
                }
            }


            if (bestLocation != null) {

                showLocationAddress(
                    bestLocation!!.latitude,
                    bestLocation!!.longitude
                )

            } else {

                Toast.makeText(
                    this,
                    "Location nahi mil rahi. GPS ON karein.",
                    Toast.LENGTH_LONG
                ).show()
            }

        } catch (e: Exception) {

            Toast.makeText(
                this,
                "Location detect nahi ho paayi.",
                Toast.LENGTH_LONG
            ).show()
        }
    }


    // ==============================
    // GPS → ADDRESS
    // ==============================

    private fun showLocationAddress(
        latitude: Double,
        longitude: Double
    ) {

        try {

            val geocoder =
                Geocoder(
                    this,
                    Locale.getDefault()
                )


            val addresses =
                geocoder.getFromLocation(
                    latitude,
                    longitude,
                    1
                )


            if (
                addresses != null &&
                addresses.isNotEmpty()
            ) {

                val address =
                    addresses[0]
                        .getAddressLine(0)


                edtAddress.setText(address)


                Toast.makeText(
                    this,
                    "Current location added ✅",
                    Toast.LENGTH_LONG
                ).show()

            } else {

                edtAddress.setText(
                    "$latitude, $longitude"
                )

                Toast.makeText(
                    this,
                    "GPS location added.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        } catch (e: Exception) {

            edtAddress.setText(
                "$latitude, $longitude"
            )
        }
    }


    // ==============================
    // LOCATION PERMISSION RESULT
    // ==============================

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        super.onRequestPermissionsResult(
            requestCode,
            permissions,
            grantResults
        )


        if (
            requestCode == locationPermissionCode &&
            grantResults.isNotEmpty() &&
            grantResults.any {
                it == PackageManager.PERMISSION_GRANTED
            }
        ) {

            getCurrentLocation()

        } else {

            Toast.makeText(
                this,
                "Location permission allow karein.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}
