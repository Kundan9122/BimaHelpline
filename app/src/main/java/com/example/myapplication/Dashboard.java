package com.example.myapplication;

import android.Manifest;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import android.view.ContextMenu;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;


public class Dashboard extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TextView tv,tv1;

    String name;
    AlertDialog.Builder builder;
    CardView bat;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //Image button and image switcher ID


//      myDialog = new Dialog(this);
      bat = findViewById(R.id.bat);

       registerForContextMenu(bat);

        name = getIntent().getExtras().getString("Name");
        //tv1.setText(name);

        //Navigation Code
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.gmail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("email"));
                String[] s={"cricket.gear@gmail.com"}; //put gmail to send message
                intent.putExtra(Intent.EXTRA_EMAIL,s);
                intent.putExtra(Intent.EXTRA_SUBJECT,"This is a subject");
                intent.putExtra(Intent.EXTRA_TEXT,"Hi! this is the Email body");
                intent.setType("message/rfc822");
                Intent choose= Intent.createChooser(intent,"Launch Email");
                startActivity(choose);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        tv1=headerView.findViewById(R.id.tv1);
        tv1.setText(name);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Category.class));
            }
        });
    }



    @Override
    public void onBackPressed() {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {



            Toast toast= (Toast) Toast.makeText(getApplicationContext(),  name,Toast.LENGTH_SHORT);
            toast.show();
            /*tv.setText(name);*/

            drawer.closeDrawer(GravityCompat.START);
        } else {

            builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert")
                    .setMessage("Are you sure you want to logout ?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            final Intent intent = new Intent(getApplicationContext(),Login.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId())
        {
            case R.id.action_schedule:
                //Scheduleorder();

                return true;

            case R.id.action_call:
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:7004362244")); // Enter number you want to call

                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                {

                    Toast.makeText(this,"Please grant the permission to call",Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(intent);
                }
                return true;

            case  R.id.action_logout:
                builder = new AlertDialog.Builder(this);
                builder.setTitle("Alert")
                        .setMessage("Are you sure you want to logout ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final Intent intent = new Intent(getApplicationContext(),Login.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();


                return true;




            default:

                return super.onOptionsItemSelected(item);


        }

    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.action_schedule)
        {
           // Scheduleorder();

        }
        else if (id == R.id.nav_map) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse("geo: 12.972442, 77.580643"));
            Intent chooser = Intent.createChooser(i, "Launch Maps");
            startActivity(chooser); // select the app in your phone to open the location ex: google map, uber, ola ….etc

        } else if (id == R.id.nav_call) {

            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:9122607316")); // Enter number you want to call

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {

                Toast.makeText(this,"Please grant the permission to call",Toast.LENGTH_SHORT).show();
                requestPermission();
            }
            else {
                startActivity(intent);
            }
        } else if (id == R.id.nav_about) {
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Kundan9122/")); //put url you want to re-direct
            startActivity(i);

        } else if (id == R.id.nav_send) {
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setData(Uri.parse("email"));
            String[] s={"kundansingh912260@gmail.com"};
            intent.putExtra(Intent.EXTRA_EMAIL,s);
            intent.putExtra(Intent.EXTRA_SUBJECT,"This is a subject");
            intent.putExtra(Intent.EXTRA_TEXT,"Hi! this is the Email body");
            intent.setType("message/rfc822");
            Intent choose= Intent.createChooser(intent,"Launch Email");
            startActivity(choose);
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},1);
    }
}
