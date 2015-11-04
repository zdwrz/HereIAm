package com.aweiz.www.hereiam;

import android.app.ProgressDialog;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;

public class MainActivity extends AppCompatActivity implements LocationListener {

    protected LocationManager locationManager;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        txtLat = (TextView) findViewById(R.id.textview1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    TextView txtLat;

    public void actionHereBtn(View view) {
        txtLat = (TextView) findViewById(R.id.textview1);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        pd = new ProgressDialog(this);
        pd.setMessage("loading");
        pd.show();
    }


    @Override
    public void onLocationChanged(Location location) {
        AsyncHttpClient client = new AsyncHttpClient();
        LocationResponseHandler handler = new LocationResponseHandler(this,location);
        client.get("http://www.aweiz.com/LocationServices/location/add/" + location.getLatitude() + "&&" + location.getLongitude() + "&&android_"+ Build.MODEL+"/",handler);

        locationManager.removeUpdates(this);
        if (pd != null)
        {
            pd.dismiss();
        }
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
}
