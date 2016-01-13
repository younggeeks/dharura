package code.bytech.com.emergency.fragments;

import android.app.Dialog;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import code.bytech.com.emergency.R;
import code.bytech.com.emergency.utils.PlaceJSONParser;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements android.location.LocationListener {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LatLng userPos;
    private Spinner SpinnerplaceType;
    private Button btnFind;

    String[] placeType=null;
    String[] placeTypeName=null;

    double latitude=0;
    double longitude=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        placeType=getResources().getStringArray(R.array.place_type);
        placeTypeName=getResources().getStringArray(R.array.place_type_name);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,placeTypeName);

        SpinnerplaceType=(Spinner)findViewById(R.id.placeSpinner);
        SpinnerplaceType.setAdapter(adapter);

        btnFind=(Button)findViewById(R.id.btnFind);

        int status= GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        if (status!= ConnectionResult.SUCCESS){
            int requestCode=10;
            Dialog dialog=GooglePlayServicesUtil.getErrorDialog(status,this,requestCode);
            dialog.show();
        }else {
            setUpMapIfNeeded();
            //googleplay services is available


        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        Log.d("Geeks","Bad boy is called");
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            mMap.setMyLocationEnabled(true);

                LocationManager lm=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

                //criteria to retrieve the provider
                Criteria criteria=new Criteria();

                //getting the name of the best provider
                String provider=lm.getBestProvider(criteria,true);


                Location location=lm.getLastKnownLocation(provider);

                if (location!=null){
                    onLocationChanged(location);
                }

                lm.requestLocationUpdates(provider, 20000, 0, this);

                btnFind.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.d("Geeks","Clicked em up");

                        int selectedPosition = SpinnerplaceType.getSelectedItemPosition();
                        String type = placeType[selectedPosition];
                        StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
                        sb.append("location=" + latitude + "," + longitude);
                        sb.append("&radius=5000");
                        sb.append("&types=" + type);
                        sb.append("&sensor=true");
                        sb.append("&key=AIzaSyD5F82h8N4GtxQZ6X05J5VAVW7EnnH0GYA");

                        //creating new non-ui thread task to fetch json
                        PlacesTask placesTask = new PlacesTask();
                        placesTask.execute(sb.toString());

                    }
                });
            }
        }



    //method to download json from url
    private String downloadUrl(String urlStr) throws IOException{
        String data="";
        InputStream inputStream = null;
        HttpURLConnection urlConnection=null;

        try {
            URL url=new URL(urlStr);

            //creating http connection to communicate with url
            urlConnection=(HttpURLConnection)url.openConnection();

            //connecting to the url
            urlConnection.connect();

            inputStream=urlConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb=new StringBuffer();

            String line="";
            while ((line=br.readLine())!=null){
                sb.append(line);
            }
            data=sb.toString();
            br.close();

        }catch (Exception e){
            Log.d("Geeks","Exception while downloading url"+e.toString());
        }finally {
            inputStream.close();
            urlConnection.disconnect();
        }

        return data;
    }


//    private android.location.LocationListener locationListener=new android.location.LocationListener() {
//        @Override
//        public void onLocationChanged(Location location) {
//            Log.d("Geeks","it  changed");
//            userPos=new LatLng(location.getLatitude(),location.getLongitude());
//            if (mMap!=null){
//                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userPos,15));
//            }
//        }
//
//        @Override
//        public void onStatusChanged(String s, int i, Bundle bundle) {
//
//        }
//
//        @Override
//        public void onProviderEnabled(String s) {
//
//        }
//
//        @Override
//        public void onProviderDisabled(String s) {
//
//        }
//    };

//    private void setUpMap(LatLng userPos) {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("My Location"));
//    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d("Geeks","locao changed");
        latitude=location.getLatitude();
        longitude=location.getLongitude();
        LatLng latLng=new LatLng(latitude,longitude);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
//        mMap.animateCamera(CameraUpdateFactory.zoomTo(9));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Toast.makeText(MapsActivity.this, "Status Changed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(MapsActivity.this, "provider enabled", Toast.LENGTH_SHORT).show();
        
    }

    @Override
    public void onProviderDisabled(String s) {

    }


    //class to download google places
    private class PlacesTask extends AsyncTask<String,Integer,String> {
        String data=null;

        //invoked by execute() method of this object
        //windows 10
        @Override
        protected String doInBackground(String... url) {

            try {
                data=downloadUrl(url[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            ParserTask parserTask=new ParserTask();
            parserTask.execute(result);
        }
    }

    //a class to parse the google places in json format
    private class ParserTask extends AsyncTask<String,Integer,List<HashMap<String,String>>> {
        JSONObject jsonObject;
        //invoked by execute method
        @Override
        protected List<HashMap<String,String>> doInBackground(String... jSonData) {

            List<HashMap<String,String>> places=null;
            PlaceJSONParser placeJSONParser=new PlaceJSONParser();

            try {
                jsonObject=new JSONObject(jSonData[0]);

                //getting the parse data as a list construct
                places=placeJSONParser.parse(jsonObject);
              //  jsonObject= (JSONObject) placeJSONParser.parse(jsonObject);
            }catch (Exception e){

            }
            return places;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {
            mMap.clear();

            for (int i=0;i<list.size();i++){
                //creating a marker
                MarkerOptions markerOptions=new MarkerOptions();

                //getting a place from places list
                HashMap<String,String>  hmPlace=list.get(i);

                //getting latitude of the place
                double lat=Double.parseDouble(hmPlace.get("lat"));
                double lng=Double.parseDouble(hmPlace.get("lng"));

                //getting the name
                String name=hmPlace.get("place_name");

                //getting vicinity
               String vicinity= hmPlace.get("vicinity");

                LatLng latLng=new LatLng(lat,lng);

                //setting options for the marker
                markerOptions.position(latLng);

                //setting title for the marker
                markerOptions.title(name+" : "+vicinity);

                //placing the marker on touched position
                mMap.addMarker(markerOptions);
            }
        }
    }
}
