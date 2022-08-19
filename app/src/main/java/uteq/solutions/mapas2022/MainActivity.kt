package uteq.solutions.mapas2022

import android.graphics.Color
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMapClickListener {
    lateinit var mMap:GoogleMap
    var puntos:ArrayList<LatLng> = ArrayList<LatLng>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment: SupportMapFragment = getSupportFragmentManager()
            .findFragmentById(R.id.map) as SupportMapFragment

        mapFragment.getMapAsync(this);

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        val camUpd1: CameraUpdate =
            CameraUpdateFactory.newLatLngZoom(
                LatLng(-1.0799, -79.50133), 25F);

        mMap.moveCamera(camUpd1);

        mMap.setOnMapClickListener(this)
    }

    override fun onMapClick(point: LatLng) {
        puntos.add(point)
        mMap.addMarker(
            MarkerOptions().position(point)
            .title("Lugar"+ puntos.size));

        if(puntos.size==4){
            val lineas: PolylineOptions = PolylineOptions()

            for(punto:LatLng in puntos)
                lineas.add(punto)

            lineas.add(puntos.get(0))
            lineas.width(8f)
            lineas.color(Color.RED)
            mMap.addPolyline(lineas)
            puntos.clear()
        }

    }
}