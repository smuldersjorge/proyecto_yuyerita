package net.smulders.tesis.yuyeritaandroid;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class PlaceActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        String placeId = getIntent().getExtras().getString("id");
        Place p = YuyeraSQLiteHelper.getPlace(placeId, this);

        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText(p.name);
        TextView tv4 = (TextView) findViewById(R.id.textView4);
        tv4.setText(p.address);

        //mapa
        GoogleMap mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        String lat = p.gps_lat;
        String lon = p.gps_lon;
        Double gps_lat = Double.parseDouble(lat);
        Double gps_lon = Double.parseDouble(lon);

        LatLng position = new LatLng(gps_lat,gps_lon);
        mapa.addMarker(new MarkerOptions().position(position));
        CameraPosition camPos = new CameraPosition.Builder()
                .target(position)
                .zoom(17)
                .bearing(45)
                .build();
        CameraUpdate camUpd1 = CameraUpdateFactory.newCameraPosition(camPos);
        mapa.moveCamera(camUpd1);

    }

}
