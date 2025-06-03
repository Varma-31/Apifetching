package com.example.training.ui.theme.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.training.R
import com.example.training.Data.model.Name

class NameAdapter(private val names: List<Name>) : RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    // 1. ViewHolder represents one row/item view
    class NameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
    }

    // 2. Called when a new ViewHolder is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_xml, parent, false)
        return NameViewHolder(view)
    }

    // 3. Called to bind data to a ViewHolder
    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        val name = names[position]
        holder.tvName.text = name.fullName
    }

    // 4. Total number of items
    override fun getItemCount(): Int = names.size
}
