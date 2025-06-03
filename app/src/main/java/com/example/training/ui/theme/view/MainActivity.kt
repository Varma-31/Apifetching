package com.example.training.ui.theme.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training.R
import com.example.training.ui.theme.viewModel.SchoolViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: SchoolViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.schools.observe(this) { schoolList ->
            recyclerView.adapter = SchoolAdapter(schoolList)
        }

        viewModel.fetchSchools()

    }
}