package com.aweiz.www.hereiam;

import android.app.Activity;
import android.location.Location;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Dawei on 11/4/2015.
 */
public class LocationResponseHandler extends AsyncHttpResponseHandler {

    Activity activity;
    Location location;
    public LocationResponseHandler(Activity activity, Location location){
        this.activity = activity;
        this.location = location;
    }
    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
       TextView txtLat = (TextView)activity.findViewById(R.id.textview1);
       txtLat.setText("位置已发送-" + "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
        TextView txtLat = (TextView)activity.findViewById(R.id.textview1);
        txtLat.setText("位置已发送-" + "Latitude:" + location.getLatitude() + ", Longitude:" + location.getLongitude());
    }
}
