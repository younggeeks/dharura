package code.bytech.com.emergency.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by samjunior on 9/26/15.
 * In Project Emergency.
 */
public class CheckNet {
    private Context context;

    public CheckNet(Context context) {
        this.context = context;
    }

    public boolean hasActiveInternetConnection(){
        if (isNetworkAvailable()){
            try {
                HttpURLConnection urlConnection=(HttpURLConnection)(new URL("http://www.google.com").openConnection());
                urlConnection.setRequestProperty("User-agent","test");
                urlConnection.setRequestProperty("Connection", "close");
                urlConnection.setConnectTimeout(1500);
                return (urlConnection.getResponseCode()==200);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(context.getApplicationContext(),"Error Checking Internet Connection",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }else
        {
            Toast.makeText(context.getApplicationContext(),"No Network Available",Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private  boolean isNetworkAvailable(){
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo!=null;
    }
}
