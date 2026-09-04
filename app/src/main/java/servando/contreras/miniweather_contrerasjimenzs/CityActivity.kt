package servando.contreras.miniweather_contrerasjimenzs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import servando.contreras.miniweather_contrerasjimenzs.utilities.WeatherService

class CityActivity : AppCompatActivity() {
    var citySelected: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_city)

        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars = false

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val service: WeatherService = WeatherService(this)
        val nextButton: Button = findViewById(R.id.btn_save_city)
        val citySelector: Spinner = findViewById(R.id.city_selecter)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            service.getCities()
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        citySelector.adapter = adapter

        citySelected = service.getCities().firstOrNull() ?: ""

        citySelector.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                p2: Int,
                p3: Long
            ) {
                citySelected = p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        nextButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).putExtra("city", citySelected)
            startActivity(intent)
        }
    }
}