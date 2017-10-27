package com.codetribe.project;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recycler;
    private EventAdapter adapter;
    private ArrayList<Event> eventArrayList;
    private AlertDialog.Builder builder;
    private Dialog dialog;

    private static final int PICK_IMAGE_REQUEST = 234;
    private Uri filePath;

    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this,EmailActivity.class);
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        recycler=(RecyclerView)findViewById(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        eventArrayList= new ArrayList<>();

        Event event = new Event();
        Event event1 = new Event();
        Event event2 = new Event();
        Event event3 = new Event();
        Event event4 = new Event();



        event.setEventImages(R.drawable.postone);
        event.setEventName("COOLERBOX SUNDAYS HOST");
        event.setEventinformation("PRESENT NIZEN'S B'DAY PARTY CELEBRATION ");
        event.setEventaddress("@ESIBAYENI LONGE");
        event.setEventdate("01/10/2017");

        event1.setEventImages(R.drawable.posttwo);
        event1.setEventName("SKY VODKA EVENT");
        event1.setEventinformation("SKY VODKA PRESENT EMTEE THE HUSTLER");
        event1.setEventaddress("Sunnyside jappe street EUROPA");
        event1.setEventdate("02/04/2017");

        event2.setEventImages(R.drawable.postthree);
        event2.setEventName("STUDENTS WELCOME BACK PARTY");
        event2.setEventinformation("EUROPA PRESENTS BLACK MOTION");
        event2.setEventaddress("Sunnyside ESSELEN street EUROPA");
        event2.setEventdate("26/04/2017");

        event3.setEventImages(R.drawable.postfour);
        event3.setEventName("#MMA 16");
        event3.setEventinformation("MMA 16 Awards after party 4pm-7pm hunters dry R7.95 \n " +
                "on deck:MAD T//LUCARIO//FLIRT//LUMCUE//FRENCHISE//LADIES FREE// GENTS R30.00");
        event3.setEventaddress("Sunnyside ESSELEN street EUROPA");
        event3.setEventdate("25/02/2017  SAT");

        event4.setEventImages(R.drawable.postfive);
        event4.setEventName("Soul session");
        event4.setEventinformation("Soul session wednesday \n feat. music power\n 18H00-20H00 castle lite R8.50 // Hunters Range R" +
                "R9.50// Jameson R320(18H00-23H00)");
        event4.setEventaddress("Sunnyside ESSELEN street EUROPA");
        event4.setEventdate("Every wednesday");

        eventArrayList.add(event);
        eventArrayList.add(event1);
        eventArrayList.add(event2);
        eventArrayList.add(event3);
        eventArrayList.add(event4);


        adapter= new EventAdapter(MainActivity.this,eventArrayList);

        recycler.setAdapter(adapter);

        recycler.addOnItemTouchListener(new RecyclerViewCardListener(MainActivity.this, new RecyclerViewCardListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Event eventget=eventArrayList.get(position);

                builder= new AlertDialog.Builder(MainActivity.this);
                dialog = new Dialog(MainActivity.this);

                builder.setTitle("ZEEWAVAA");
                builder.setIcon(R.drawable.logo);
                builder.setMessage("Check "+eventget.getEventName());
                builder.setNegativeButton("who's going",null);
                builder.setNeutralButton("Map", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainActivity.this,EventMap.class);
                        startActivity(intent);
                    }
                });
                builder.setPositiveButton("Going",null);



                dialog=builder.create();
                dialog.show();


            }
        }));





    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if(id ==R.id.nav_camera)
        {

            showFileChooser();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//----------------------------------------------------------------------------------------------------pic
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profilePic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
