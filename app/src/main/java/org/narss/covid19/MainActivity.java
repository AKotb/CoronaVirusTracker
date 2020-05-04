package org.narss.covid19;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;

import org.narss.covid19.dbhelper.DBHelper;
import org.narss.covid19.model.Hospital;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import android.database.SQLException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, GoogleMap.OnPolygonClickListener {

    protected GoogleApiClient mGoogleApiClient;
    DBHelper db;
    public List<Hospital> hospitalList;
    int x = 0;
    int y = 0;
    private PopupWindow mPopupWindow;
    private Button closeBtn;
    private ConstraintLayout mConstraintLayout;
    Projection projection;
    boolean addHospitals;
    boolean addAreas;
    boolean addLabs;
    boolean clearMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buildGoogleApiClient();
        mGoogleApiClient.connect();
        closeBtn = (Button) findViewById(R.id.btn_close);

        db = new DBHelper(this);

        //Create to Database
        try {
            db.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        //Connect to Database
        try {
            db.openDataBase();
        } catch(SQLException sqle) {
            throw sqle;
        }

        hospitalList = db.getHospitalList();
        addHospitals = false;
        addAreas = false;
        addLabs = false;
        clearMap = false;
    }
    //----------------------------------------------------------------------------------------------
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onMapReady(GoogleMap googleMap) {
        UiSettings mapSettings;
        projection = googleMap.getProjection();
        mapSettings = googleMap.getUiSettings();

        googleMap.setOnPolygonClickListener(this);
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(26.8206, 30.8025), 6));
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(true);
        mapSettings.setZoomControlsEnabled(true);
        mapSettings.setMyLocationButtonEnabled(true);

        if(addHospitals)
        {
            for(int i=0; i<hospitalList.size(); i++)
            {
                LatLng hospitalLocation = new LatLng(hospitalList.get(i).getLat(), hospitalList.get(i).getLon());
                googleMap.addMarker(new MarkerOptions().position(hospitalLocation)
                        .title(hospitalList.get(i).getName() + " - " + hospitalList.get(i).getGovernorate()).icon(BitmapDescriptorFactory.fromResource(R.drawable.hospital)));
            }
        }

        if(addAreas)
        {
            //Bahtim
            googleMap.addPolygon(new PolygonOptions()
                    .add(new LatLng(30.137, 31.263), new LatLng(30.135, 31.27), new LatLng(30.133, 31.278), new LatLng(30.137, 31.282),
                            new LatLng(30.139, 31.282), new LatLng(30.141, 31.278), new LatLng(30.1421, 31.275), new LatLng(30.141, 31.273)
                            , new LatLng(30.143, 31.264))
                    .strokeColor(Color.RED)
                    .fillColor(Color.LTGRAY)
                    .clickable(true)).setTag("Area Name: Bahtim - Al Qalyubia Governorate\n" +
                    "Quarantine Start Date: 8-4-2020\n" +
                    "Quarantine End Date: 5-5-2020\n");

            //El-Hayatim
            googleMap.addPolygon(new PolygonOptions()
                    .add(new LatLng(30.917407, 31.112357), new LatLng(30.917959, 31.108967), new LatLng(30.920168, 31.107078), new LatLng(30.922413, 31.090296), new LatLng(30.904261, 31.091973),
                            new LatLng(30.903525, 31.100985), new LatLng(30.901095, 31.101243), new LatLng(30.900948, 31.103431), new LatLng(30.903268, 31.105577), new LatLng(30.909675, 31.105147),
                            new LatLng(30.910485, 31.111327), new LatLng(30.917407, 31.112357))
                    .strokeColor(Color.RED)
                    .fillColor(Color.LTGRAY)
                    .clickable(true)).setTag("Area Name: El-Hayatim - Al Gharbia Governorate\n" +
                    "Quarantine Start Date: 6-4-2020\n" +
                    "Quarantine End Date: 3-5-2020\n");

            //Moatamedia
            googleMap.addPolygon(new PolygonOptions()
                    .add(new LatLng(30.059543, 31.190027), new LatLng(30.063242, 31.170409), new LatLng(30.056042, 31.169868), new LatLng(30.056096, 31.167575),
                            new LatLng(30.050738, 31.166553), new LatLng(30.044043, 31.165115), new LatLng(30.040181, 31.171765), new LatLng(30.046858, 31.177060)
                            , new LatLng(30.050612, 31.177873), new LatLng(30.051911, 31.183189), new LatLng(30.046028, 31.188421), new LatLng(30.044007, 31.192278)
                            , new LatLng(30.044458, 31.193446), new LatLng(30.059543, 31.190027))
                    .strokeColor(Color.RED)
                    .fillColor(Color.LTGRAY)
                    .clickable(true)).setTag("Area Name: Moatamedia - Al Giza Governorate\n" +
                    "Quarantine Start Date: 13-4-2020\n" +
                    "Quarantine End Date: 10-5-2020\n");

            //Shobra Elbahow
            googleMap.addPolygon(new PolygonOptions()
                    .add(new LatLng(30.963856, 31.348804), new LatLng(30.962583, 31.34708), new LatLng(30.959262, 31.347823), new LatLng(30.957465, 31.340715),
                            new LatLng(30.949028, 31.335306), new LatLng(30.948209, 31.344932), new LatLng(30.954009, 31.355487),
                            new LatLng(30.96231, 31.353471), new LatLng(30.963856, 31.348804))
                    .strokeColor(Color.RED)
                    .fillColor(Color.LTGRAY)
                    .clickable(true)).setTag("Area Name: Shobra El-bahow - Al Dakahlia Governorate\n" +
                    "Quarantine Start Date: 16-4-2020\n" +
                    "Quarantine End Date: 13-5-2020\n");
        }
        if(clearMap)
        {
            googleMap.clear();
        }
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onConnectionSuspended(int i) {
        /*txtView = (TextView)findViewById(R.id.txt_view);
        txtView.setText("Connection suspended");*/
        mGoogleApiClient.connect();
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onConnected(Bundle bundle) {
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*txtView = (TextView)findViewById(R.id.txt_view);
        txtView.setText("Connection Failed");*/
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_add_stops:
                addQuarantineHospitals();
                return true;
            case R.id.menu_swap_stops:
                addQuarantinedAreas();
                return true;
            case R.id.menu_find_route:
                //getDistanceTo();
                return true;
            case R.id.menu_show_result:
                //addCentralLabs();
                return true;
            case R.id.menu_clear:
                clear();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    //----------------------------------------------------------------------------------------------
    public void clear() {
        clearMap = true;
        addHospitals = false;
        addAreas = false;
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    //----------------------------------------------------------------------------------------------
    public void addQuarantineHospitals(){
        clearMap = false;
        addHospitals = true;
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    //----------------------------------------------------------------------------------------------
    public void addQuarantinedAreas(){
        clearMap = false;
        addAreas = true;
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    //----------------------------------------------------------------------------------------------
    @Override
    public void onPolygonClick(Polygon polygon) {

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(polygon.getTag().toString());
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.cl);

        //PopupWindow(View contentView, int width, int height)
        mPopupWindow = new PopupWindow(
                layout, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        mPopupWindow.setTouchable(true);
        if(Build.VERSION.SDK_INT>=21){
            mPopupWindow.setElevation(5.0f);
        }
        closeBtn = (Button) layout.findViewById(R.id.btn_close);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Dismiss the popup window
                mPopupWindow.dismiss();
            }
        });

        mPopupWindow.showAtLocation(mConstraintLayout, Gravity.CENTER,x,y);
    }
    //----------------------------------------------------------------------------------------------
}