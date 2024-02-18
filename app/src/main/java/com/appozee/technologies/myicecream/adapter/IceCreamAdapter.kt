package com.appozee.technologies.myicecream.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.appozee.technologies.myicecream.R
import com.appozee.technologies.myicecream.model.IceCreamFlavor

class IceCreamAdapter(private val iceCreamList: List<IceCreamFlavor>) :
    RecyclerView.Adapter<IceCreamAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flavorName: TextView = itemView.findViewById(R.id.tvFlavorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ice_cream, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val iceCream = iceCreamList[position]
        holder.flavorName.text = iceCream.name
    }

    override fun getItemCount(): Int {
        return iceCreamList.size
    }
}