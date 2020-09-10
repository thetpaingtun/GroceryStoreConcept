package com.gmail.tpt.grocerystore

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_fruit.view.*

class FruitAdapter(val context: Context, val list: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    var listener: ((ImageView, Fruit) -> Unit)? = null

    private val constraint = ConstraintSet()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_fruit, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cur = list[position]

//        holder.img.setImageResource(cur.image)
        holder.img.load(cur.image)

        holder.txtPrice.text = String.format("$%.2f", cur.price)
        holder.txtName.text = cur.name
        holder.txtWeight.text = String.format("%dg", cur.weight)

        holder.card.setOnClickListener {
            listener?.invoke(holder.itemView.img, cur)
        }

        val (width, height) = context.getDrawableDimension(cur.image)

        val ratio = String.format("%d:%d", width, height)

        constraint.clone(holder.root)
        constraint.setDimensionRatio(holder.img.id, ratio)
        constraint.applyTo(holder.root)


        Logger.d("image size => ${width}x${height}")
        Logger.d("text size => ${holder.txtPrice.width}x${holder.txtPrice.height}")

        context.resources
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img = itemView.img
        val txtPrice = itemView.txtPrice
        val txtName = itemView.txtName
        val txtWeight = itemView.txtWeight
        val card = itemView.card
        val root = itemView.root
    }
}