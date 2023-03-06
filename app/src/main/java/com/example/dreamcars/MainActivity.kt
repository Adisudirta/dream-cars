package com.example.dreamcars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvCars: RecyclerView
    private val cars = ArrayList<Car>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvCars = findViewById(R.id.rv_cars)
        rvCars.setHasFixedSize(true)

        cars.addAll(getListCars())
        showRecyclerList()
    }

    private fun getListCars(): ArrayList<Car> {
        val dataName = resources.getStringArray(R.array.data_car_names)
        val dataPrice = resources.getStringArray(R.array.data_car_prices)
        val dataPhoto = resources.obtainTypedArray(R.array.data_car_photos)
        val dataFuel = resources.getStringArray(R.array.data_car_fuel)
        val dataEngine = resources.getStringArray(R.array.data_car_engine)
        val dataPassengers = resources.getStringArray(R.array.data_car_passengers)
        val dataDescription = resources.getStringArray(R.array.data_car_descriptions)

        val listCar = ArrayList<Car>()
        for (i in dataName.indices) {
            val car = Car(dataPhoto.getResourceId(i, -1), dataName[i], dataPrice[i], dataFuel[i], dataEngine[i], dataPassengers[i], dataDescription[i])
            listCar.add(car)
        }
        return listCar
    }

    private fun showRecyclerList() {
        rvCars.layoutManager = LinearLayoutManager(this)
        val listCarAdapter = ListCarAdapter(cars)
        rvCars.adapter = listCarAdapter

        listCarAdapter.setOnItemClickCallback(object : ListCarAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Car) {
                val moveIntent = Intent(this@MainActivity, DetailCarActivity::class.java)
                moveIntent.putExtra(DetailCarActivity.EXTRA_CAR, data)
                startActivity(moveIntent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.about_page){
            val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(moveIntent)
        }

        return super.onOptionsItemSelected(item)
    }
}