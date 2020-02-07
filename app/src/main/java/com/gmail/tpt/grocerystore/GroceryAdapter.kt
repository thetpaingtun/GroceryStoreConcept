package com.gmail.tpt.grocerystore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_grocery.view.*

class GroceryAdapter(val list: List<Grocery>) : RecyclerView.Adapter<GroceryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_grocery, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cur = list[position]

        holder.img.setImageResource(cur.image)
        holder.txtPrice.text = String.format("$%.2f", cur.price)
        holder.txtName.text = cur.name
        holder.txtWeight.text = String.format("%dg", cur.weight)

    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.img
        val txtPrice = itemView.txtPrice
        val txtName = itemView.txtName
        val txtWeight = itemView.txtWeight
    }
}