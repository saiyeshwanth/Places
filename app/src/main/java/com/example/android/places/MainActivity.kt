package com.example.android.places
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                seekbarvalue.text = progress.toString()//setting seekbar value
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        getCurrentLocation()//no use, created just to write the code outside the


        pointer.setOnClickListener {
            val builder = PlacePicker.IntentBuilder()// add a place picker dependency
            startActivityForResult(builder.build(this), 11)// to navigate to place picker screen
        }


    }//onCreate

    @SuppressLint("MissingPermission")
    fun getCurrentLocation() {
        val lManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager//getting location object
        lManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000L, 1F, object : LocationListener {
            override fun onLocationChanged(location: Location?) {
                tv_latitude.text = location?.latitude.toString()//setting the current lats and longs to text view
                tv_longitude.text = location?.longitude.toString()
            }

            override fun onProviderDisabled(provider: String?) {
            }

            override fun onProviderEnabled(provider: String?) {
            }

            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
            }
        })


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)//to store the results
        if (resultCode == Activity.RESULT_OK) {
            val p: Place = PlacePicker.getPlace(this@MainActivity, data)
            tv_latitude.text = p.latLng.latitude.toString()//getting the lats and longs from picker
            tv_longitude.text = p.latLng.longitude.toString()

        }

}
}//Main