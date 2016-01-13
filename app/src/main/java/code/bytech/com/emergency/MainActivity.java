package code.bytech.com.emergency;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.*;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import code.bytech.com.emergency.adapters.MyAdapter;
import code.bytech.com.emergency.events.DrawerItemClicked;
import code.bytech.com.emergency.fragments.*;
import code.bytech.com.emergency.utils.CheckNet;
import code.bytech.com.emergency.utils.EventBus;
import com.squareup.otto.Subscribe;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;


public class MainActivity extends AppCompatActivity {


    private CheckNet checkNet;
    private String currentFragmentTitle;
    String TITLES[]={"Home","Hospitali","Zima Moto","Polisi","Umeme","Huduma Ya Kwanza"};
    int ICONS[] ={R.drawable.home,R.drawable.hospital,R.drawable.fire,R.drawable.police,R.drawable.electro,R.drawable.first_aid};

    String name="Emergency App";
    String email="emergencyapp@gmail.com";
    int profile=R.drawable.circle;


    private Toolbar toolbar;

    private Switch aSwitch;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        checkNet=new CheckNet(getApplicationContext());
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        recyclerView=(RecyclerView)findViewById(R.id.RecyclerView);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
       adapter=new MyAdapter(TITLES,ICONS,name,email,profile,this);
       // adapter=new NavigationAdapter(TITLES,ICONS,name,email,profile,this);

        recyclerView.setAdapter(adapter);


        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);

        toggle= new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_opened,R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
           //     getSupportActionBar().setTitle(R.string.drawer_opened);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
             //   getSupportActionBar().setTitle(R.string.drawer_closed);
            }
        };

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        
        displayInitialFragment();
    }

    private void displayInitialFragment() {
     getSupportFragmentManager().beginTransaction().replace(R.id.frame, contentFragment.getInstance());
        currentFragmentTitle="home";
    }

//    @Override
//    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onPostCreate(savedInstanceState, persistentState);
//        toggle.syncState();
//    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem item=menu.findItem(R.id.switchId);

        aSwitch=(Switch)item.getActionView();//.findViewById(R.id.switchAB);


        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    if (checkNet.hasActiveInternetConnection()){
                        Intent mapIntent=new Intent(MainActivity.this,MapsActivity.class);
                        startActivity(mapIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Thingy is Off",Toast.LENGTH_LONG).show();
                    }
                    //  Toast.makeText(getApplicationContext(),"Thingy is On",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Thingy is Off",Toast.LENGTH_LONG).show();
                }
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (toggle.onOptionsItemSelected(item))
            return true;
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getInstance().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getInstance().unregister(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Subscribe
    public void onDrawerItemClickedEvent(DrawerItemClicked event){
        drawerLayout.closeDrawers();

        if (event==null || TextUtils.isEmpty(event.item )|| event.item.equalsIgnoreCase(currentFragmentTitle)){
            return ;
        }
        Toast.makeText(this,"Geeks "+event.item,Toast.LENGTH_SHORT).show();

        if (event.item.equalsIgnoreCase("hospitali")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, HospitalFragment.getInstance()).commit();
            getSupportActionBar().setTitle(event.item);
        }else if (event.item.equalsIgnoreCase("home")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,contentFragment.getInstance()).commit();
            getSupportActionBar().setTitle(event.item);
        }else if(event.item.equalsIgnoreCase("zima moto")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, ZimaMotoFragment.getInstance()).commit();
            getSupportActionBar().setTitle(event.item);
        }else if(event.item.equalsIgnoreCase("polisi")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, PolisiFragment.getInstance()).commit();
            getSupportActionBar().setTitle(event.item);
        }else if(event.item.equalsIgnoreCase("umeme")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, UmemeFragment.getInstance()).commit();
            getSupportActionBar().setTitle(event.item);
        }else if(event.item.equalsIgnoreCase("huduma ya kwanza")){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame, FirstAidFragment.getInstance()).commit();
            getSupportActionBar().setTitle(event.item);
        }

        else  {
            return;

        }
        currentFragmentTitle=event.item;
    }
}
