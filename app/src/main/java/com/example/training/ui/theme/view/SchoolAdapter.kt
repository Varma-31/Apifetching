package com.example.training.ui.theme.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.training.Data.model.School
import com.example.training.R

class SchoolAdapter(private val schools: List<School>) : RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>() {

    class SchoolViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSchoolName: TextView = itemView.findViewById(R.id.tvSchoolName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SchoolViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_school, parent, false)
        return SchoolViewHolder(view)
    }

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.tvSchoolName.text = schools[position].school_name
    }

    override fun getItemCount() = schools.size
}