package com.nathmobile.repairing

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnWhatsapp = findViewById<Button>(R.id.btnWhatsapp)

        // Call Action Button
        btnCall.setOnClickListener {
            val phoneNumber = "9758149149" // Yahan apna Mobile Number daalein
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:$phoneNumber")
            }
            startActivity(intent)
        }

        // WhatsApp Action Button
        btnWhatsapp.setOnClickListener {
            val whatsappNumber = "919758149149" // Yahan apna Mobile Number (91 ke saath) daalein
            val url = "https://api.whatsapp.com/send?phone=$whatsappNumber&text=Hello,%20mujhe%20repairing%20inquiry%20karni%20hai."
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(url)
            }
            startActivity(intent)
        }
    }
}
