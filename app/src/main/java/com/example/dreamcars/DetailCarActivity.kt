package com.example.dreamcars

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.example.dreamcars.databinding.ActivityDetailCarBinding

class DetailCarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailCarBinding

    companion object {
        const val EXTRA_CAR = "extra_car"
    }

    private fun showNotification(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val carInformation = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_CAR, Car::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_CAR)
        }

        supportActionBar?.title = carInformation?.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.actionShare.setOnClickListener{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Come on, book a ${carInformation?.name} now on the DreamCars platform")
            startActivity(shareIntent)
        }

        binding.btnBookingCardCar.setOnClickListener {
            showNotification("Booking sedang diproses....")
            showNotification("Booking terkirim!")
        }

        carInformation?.let {
            val (photo, name, price, fuel, engine, passengers, description) = it

            binding.apply {
                ivCarDetailBanner.setImageResource(photo)
                tvNameCarDetail.text = name
                tvPriceCarDetail.text = price
                tvFuelCarDetail.text = fuel
                tvEngineCarDetail.text = engine
                tvPassengersCarDetail.text = passengers
                tvDescriptionCarDetail.text = description
            }
        }
    }
}