package th.ac.udru.pookka.udrufriend;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Criteria criteria;
    private Double latDouble, longDouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    } // main method

    @Override
    protected void onStop() {
        super.onStop();
        locationManager.removeUpdates(locationListener);
    } // on stop app, stop service

    @Override
    protected void onResume() {
        super.onResume();
//        for network

        Location networkLocation = findMylocation(LocationManager.NETWORK_PROVIDER);
        if (networkLocation != null) {
            latDouble = networkLocation.getLatitude();
            longDouble = networkLocation.getLongitude();
        }

//        for GPS
        Location gpsLocation = findMylocation(LocationManager.GPS_PROVIDER);
        if (gpsLocation != null) {
            latDouble = gpsLocation.getLatitude();
            longDouble = gpsLocation.getLongitude();
        }

        Log.d("22noV2", "lat ==>" + latDouble);
        Log.d("22noV2", "long ===>" + longDouble);


    }

    public Location findMylocation(String providerString) {

        Location location = null;

        if (locationManager.isProviderEnabled(providerString)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return null;
            }
            locationManager.requestLocationUpdates(providerString, 1000, 10, locationListener);
            location = locationManager.getLastKnownLocation(providerString);
        }



        return location;
    }


    public LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            latDouble = location.getLatitude();
            longDouble = location.getLongitude();

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng udruLatLng = new LatLng(17.397846, 102.794518);

        //set center map
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(udruLatLng,16));

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }   //onMapReady
}
