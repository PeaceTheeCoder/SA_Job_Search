package com.theecoder.sajobsearch.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.theecoder.sajobsearch.DetailsActivity
import com.theecoder.sajobsearch.R

class search_adapter(private var context: Context): RecyclerView.Adapter<search_adapter.ViewHolder>() {
    var selected_id:Int = 0
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val txt = itemView.findViewById<TextView>(R.id.txt)
        init {

            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val i = Intent(context, DetailsActivity::class.java)
                context.startActivity(i)

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.searches,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txt?.text = "Position"+position.toString()
    }

    override fun getItemCount(): Int {
        return 100
    }
}