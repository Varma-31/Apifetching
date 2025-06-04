package com.example.training.ui.theme.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training.databinding.ActivityMainBinding
import com.example.training.ui.theme.viewModel.SchoolViewModel
import android.util.Log

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: SchoolViewModel by viewModels()
    private var isSetupDone = false
    private lateinit var adapter: SchoolAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!isInternetAvailable(this)) {
            showNoInternetDialog()
        } else {
            setupUI()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isInternetAvailable(this)) {
            showNoInternetDialog()
        } else if (!isSetupDone) {
            setupUI()
        }
    }

    private fun setupUI() {
        setupRecyclerView()
        observeData()
        isSetupDone = true
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        adapter = SchoolAdapter(emptyList())
        binding.recyclerView.adapter = adapter

        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()

                    if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                        Log.d("MainActivity", "End of list reached, loading more...")
                        viewModel.loadMoreSchools()
                    }
                }
            }
        })
    }

    private fun observeData() {
        viewModel.schools.observe(this) { schools ->
            Log.d("MainActivity", "Observed ${schools.size} schools")
            // Reset on first load (offset=0), else append
            val reset = viewModelIsReset()
            adapter.updateData(schools, reset)
        }
    }

    // Helper to check if ViewModel's offset is zero (means first load or reset)
    private fun viewModelIsReset(): Boolean {
        // Assuming we can check currentOffset via reflection or add a getter in ViewModel for this
        // For now, always false (append). If you want, add public getter for currentOffset in VM.
        return false
    }

    private fun showNoInternetDialog() {
        AlertDialog.Builder(this)
            .setTitle("No Internet Connection")
            .setMessage("Please turn on your network to continue.")
            .setCancelable(false)
            .setPositiveButton("Settings") { dialog, _ ->
                startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                dialog.dismiss()
            }
            .setNegativeButton("Exit") { dialog, _ ->
                dialog.dismiss()
                finish()
            }
            .show()
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}







