package com.example.training.ui.theme.view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.training.Data.model.School
import com.example.training.databinding.ItemSchoolBinding

class SchoolAdapter(private var schools: List<School>) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    inner class SchoolViewHolder(private val binding: ItemSchoolBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(school: School) {
            Log.d("AdapterBind", "Binding: ${school.schoolName ?: "No Name"}")
            binding.schoolName.text = school.schoolName ?: "No Name"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val binding = ItemSchoolBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SchoolViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bind(schools[position])
    }

    override fun getItemCount(): Int = schools.size

    fun updateData(newSchools: List<School>, reset: Boolean = false) {
        Log.d("Adapter", "Updating data with ${newSchools.size} schools. Reset = $reset")
        schools = if (reset) newSchools else schools + newSchools
        notifyDataSetChanged()
    }
}




