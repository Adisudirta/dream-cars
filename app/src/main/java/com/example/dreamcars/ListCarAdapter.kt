package com.example.dreamcars

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListCarAdapter(private val cars: ArrayList<Car>): RecyclerView.Adapter<ListCarAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Car)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class ListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val carImg: ImageView = itemView.findViewById(R.id.iv_card_car)
        val carName: TextView = itemView.findViewById(R.id.tv_car_name)
        val carPrice: TextView = itemView.findViewById(R.id.tv_car_price)
        val btnBooking: Button = itemView.findViewById(R.id.btn_booking_card_car)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.card_car, parent, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = cars.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (photo, name, price) = cars[position]
        holder.carImg.setImageResource(photo)
        holder.carImg.contentDescription = name
        holder.carName.text = name
        holder.carPrice.text = price

        holder.btnBooking.setOnClickListener {
            onItemClickCallback.onItemClicked(cars[holder.adapterPosition])
        }
    }
}