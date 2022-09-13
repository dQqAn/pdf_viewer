package com.example.pdf_viewer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pdf_viewer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.storagePDF.setOnClickListener {
            val intent = Intent(this@MainActivity, StoragePdfActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.webButton.setOnClickListener {
            val intent = Intent(this@MainActivity, WebPdfActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}