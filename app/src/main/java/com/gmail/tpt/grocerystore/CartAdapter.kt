package com.gmail.tpt.grocerystore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_cart.view.*

class CartAdapter(val list: MutableList<Cart>) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_cart, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cur = list[position]

        holder.img.setImageResource(cur.drawable)
        holder.txtCount.setText(cur.count.toString())
        holder.txtName.setText(cur.name)
        holder.txtPrice.setText(String.format("$%s", cur.totalPrice))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val img = itemView.img
        val txtCount = itemView.txtCount
        val txtName = itemView.txtName
        val txtPrice = itemView.txtPrice

    }

    fun addItem(item: Cart) {
        list.add(item)
        notifyDataSetChanged()
    }

}