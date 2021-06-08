package com.example.dreamland.foodlover;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Foodmanue extends AppCompatActivity {
    Button Postbtn, Food_order, Massagebtn, offerbtn;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodmanue);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initNavDrawer();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menu");
        Postbtn = (Button) findViewById(R.id.button1);
        Food_order = (Button) findViewById(R.id.button2);
        Massagebtn = (Button) findViewById(R.id.button4);
        offerbtn = (Button) findViewById(R.id.button5);

        Postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Foodmanue.this, "Post", Toast.LENGTH_SHORT).show();
                Intent a = new Intent(Foodmanue.this, postfooditem.class);
                startActivity(a);
            }
        });
        Food_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Foodmanue.this, "View Orders", Toast.LENGTH_SHORT).show();
                Intent b = new Intent(Foodmanue.this, Vieworder.class);
                startActivity(b);
            }
        });
        Massagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Foodmanue.this, "Massage", Toast.LENGTH_SHORT).show();
                Intent d = new Intent(Foodmanue.this, Inbox.class);
                startActivity(d);
            }
        });
        offerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Foodmanue.this, "Post Offers", Toast.LENGTH_SHORT).show();
                Intent e = new Intent(Foodmanue.this, offers_post.class);
                startActivity(e);
            }
        });

    }

    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer1);
        navigationView = findViewById(R.id.nv1);
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