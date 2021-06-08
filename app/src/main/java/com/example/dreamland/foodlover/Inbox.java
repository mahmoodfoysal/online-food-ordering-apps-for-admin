package com.example.dreamland.foodlover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;



public class Inbox extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE=1;
    FirebaseListAdapter<clatMassage> adapter;
    RelativeLayout activity_main;
    FloatingActionButton fab;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox);

        initNavDrawer();
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Massaging");
        activity_main=(RelativeLayout)findViewById(R.id.activity_main);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText i = (EditText)findViewById(R.id.input);
                FirebaseDatabase.getInstance().getReference("Massage").push().setValue(new clatMassage(i.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                i.setText("");
            }
        });
        if(FirebaseAuth.getInstance().getCurrentUser()!=null){
            Snackbar.make(activity_main,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(),Snackbar.LENGTH_LONG).show();
            displayChatMassage();
        }
    }

    private void displayChatMassage(){
        ListView l =(ListView)findViewById(R.id.list_of_massage);
        adapter=new FirebaseListAdapter<clatMassage>(this,clatMassage.class,R.layout.list_item,FirebaseDatabase.getInstance().getReference("Massage")) {
            @Override
            protected void populateView(View v, clatMassage model, int position) {
                TextView m1,m2,m3;
                m1=(TextView)v.findViewById(R.id.massage_text);
                m2=(TextView)v.findViewById(R.id.massage_user);
                m3=(TextView)v.findViewById(R.id.massage_time);
                m1.setText(model.getMassageText());
                m2.setText(model.getMassageUser());
                m3.setText(DateFormat.format("dd-MM(HH:mm:ss)",model.getMassageTime()));

            }
        };
        l.setAdapter(adapter);

    }
    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer5);
        navigationView = findViewById(R.id.nv5);
        Toggle = new ActionBarDrawerToggle(this, DrawerLayout, R.string.opennev, R.string.closenev);
        DrawerLayout.addDrawerListener(Toggle);
        Toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.postnev:
                        Intent a = new Intent(getApplicationContext(), postfooditem.class);
                        startActivity(a);
                        break;
                    case R.id.ordernev:
                        Intent b = new Intent(getApplicationContext(), Vieworder.class);
                        startActivity(b);
                        break;
                    case R.id.massagenev:
                        Intent c = new Intent(getApplicationContext(), Inbox.class);
                        startActivity(c);
                        break;
                    case R.id.offersnev:
                        Intent d = new Intent(getApplicationContext(), offers_post.class);
                        startActivity(d);
                        break;

                    default:
                        break;
                }

                return true;
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (Toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }

}
