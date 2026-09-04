package servando.contreras.miniweather_contrerasjimenzs

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import servando.contreras.miniweather_contrerasjimenzs.utilities.WeatherService
import java.time.LocalTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val greeting = findViewById<TextView>(R.id.tvGreeting)
        val city = findViewById<TextView>(R.id.tvCity)
        val weatherIcon = findViewById<ImageView>(R.id.ivWeather)
        val temperature = findViewById<TextView>(R.id.tvTemperature)
        val tvWeather = findViewById<TextView>(R.id.tvWeather)
        val citySelected = intent.getStringExtra("city") ?: ""


        val time = LocalTime.now().hour
        greeting.text = when(time){
            in 5..11 -> getString(R.string.good_morning)
            in 12..19 -> getString(R.string.good_afternoon)
            else -> getString(R.string.good_evening)
        }
        city.text = citySelected

        val service = WeatherService(this)
        val weather = service.getWeatherForCity(citySelected)

        temperature.text = "${weather.temperature}°"
        tvWeather.text = weather.weather

        val iconRes = when (weather.weather){
            getString(R.string.snowy) -> R.drawable.ic_snowy
            getString(R.string.windy) -> R.drawable.ic_windy
            getString(R.string.stormy) -> R.drawable.ic_stormy
            getString(R.string.rainy) -> R.drawable.ic_rainy
            getString(R.string.cloudy) -> R.drawable.ic_cloudy
            getString(R.string.sunny) -> R.drawable.ic_sunny
            else -> R.drawable.ic_sunny
        }
        weatherIcon.setImageResource(iconRes)
    }
}