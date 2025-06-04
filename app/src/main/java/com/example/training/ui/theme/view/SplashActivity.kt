package com.example.training.ui.theme.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.R
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.training.databinding.ActivitySplashBinding
import com.example.training.ui.theme.viewModel.SchoolViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SchoolViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use binding to inflate the layout and set content view
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // STEP 1: Check Internet
        if (!isConnected()) {
            showNoInternetDialog()
            return
        }

        // Start observing data
        viewModel.schools.observe(this) { schools ->
            // Open MainActivity when data is fetched
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Close SplashActivity
        }

        // Trigger API call
        viewModel.loadMoreSchools()

    }
    private fun isConnected(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }

    // 🔹 Show alert dialog if no internet
    private fun showNoInternetDialog() {
        AlertDialog.Builder(this)
            .setTitle("No Internet Connection")
            .setMessage("Please connect to the internet to proceed.")
            .setCancelable(false)
            .setPositiveButton("Retry") { _, _ ->
                recreate()
            }
            .setNegativeButton("Exit") { _, _ ->
                finish()
            }
            .show()
    }
}




