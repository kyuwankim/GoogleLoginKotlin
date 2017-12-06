package com.example.dataking.googleloginkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GooglemapActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onMapReady(googlemap: GoogleMap?) {

        var sydney : LatLng = LatLng(-33.852, 151.211)

        googlemap!!.addMarker(MarkerOptions().position(sydney).title("Marker"))
        googlemap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_googlemap)



    }
}
