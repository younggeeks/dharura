package code.bytech.com.emergency.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by samjunior on 9/29/15.
 * In Project Emergency.
 */
public class PlaceJSONParser
{
    //receives a JSONOBJECT and return a list

    public List<HashMap<String,String>> parse(JSONObject jsonObject){
        JSONArray jPlaces=null;

        try {
            //Retrieving all the elements in the places array
            jPlaces=jsonObject.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jPlaces);
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jPlaces) {
        int placesCount=jPlaces.length();

        List<HashMap<String,String>> placeList=new ArrayList<HashMap<String, String>>();
        HashMap<String,String> place=null;

        //taking each place parse it and adding it to the list
        for (int i=0;i<placesCount;i++){
            try {
            place=getPlace((JSONObject)jPlaces.get(i));
                placeList.add(place);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placeList;
    }

    private HashMap<String,String> getPlace(JSONObject jPlace){
        HashMap<String,String> place=new HashMap<>();
        String placeName="-NA-";
        String vicinity="-NA-";
        String latitude="";
        String longitude="";

            try {
                //extracting place Name if available
                if (!jPlace.isNull("name")) {
                    placeName = jPlace.getString("name");
                }
                //extracting vicinity if available
                if (!jPlace.isNull("vicinity")){
                    vicinity=jPlace.getString("vicinity");
                }

                latitude=jPlace.getJSONObject("geometry").getJSONObject("location").getString("lat");
                longitude=jPlace.getJSONObject("geometry").getJSONObject("location").getString("lng");

                place.put("place_name",placeName);
                place.put("vicinity",vicinity);
                place.put("lat",latitude);
                place.put("lng",longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  place;
    }
}
