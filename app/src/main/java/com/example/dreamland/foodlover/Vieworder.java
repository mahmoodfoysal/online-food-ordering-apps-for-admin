package com.example.dreamland.foodlover;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Vieworder extends AppCompatActivity {
    private DatabaseReference fdatabaseref;
    private RecyclerView orderresult;
    FirebaseRecyclerAdapter<orderdata, orderviewhoolder> firebaseRecyclerAdapter;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vieworder);

        initNavDrawer();

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Orders");
        orderresult = (RecyclerView) findViewById(R.id.vieworderitem);
        orderresult.setHasFixedSize(true);
        orderresult.setLayoutManager(new LinearLayoutManager(this));
        fdatabaseref = FirebaseDatabase.getInstance().getReference().child("Order");
        Viewdata();

    }

    private void Viewdata() {
        firebaseRecyclerAdapter
                = new FirebaseRecyclerAdapter<orderdata, orderviewhoolder>(
                orderdata.class,
                R.layout.row,
                orderviewhoolder.class,
                fdatabaseref
        ) {
            @Override
            protected void populateViewHolder(orderviewhoolder viewHolder, orderdata model, int position) {
                viewHolder.setClientname(model.getClientname());
                viewHolder.setClientphone(model.getClientphone());
                viewHolder.setClientaddress(model.getClientaddress());
                viewHolder.setClientorder(model.getClientorder());
                viewHolder.setClientquantity(model.getClientquantity());

            }
        };
        orderresult.setAdapter(firebaseRecyclerAdapter);
        ///firebaseRecyclerAdapter.startListening();

    }

    public static class orderviewhoolder extends RecyclerView.ViewHolder {
        View mView;

        public orderviewhoolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setClientname(String clientname) {
            TextView vname = mView.findViewById(R.id.cusname);
            vname.setText(clientname);
        }

        public void setClientaddress(String clientaddress) {
            TextView vname = mView.findViewById(R.id.cusaddress);
            vname.setText(clientaddress);
        }

        public void setClientorder(String clientorder) {
            TextView vname = mView.findViewById(R.id.cusorder);
            vname.setText(clientorder);
        }

        public void setClientquantity(String clientquantity) {
            TextView vname = mView.findViewById(R.id.cusquan);
            vname.setText(clientquantity);
        }

        public void setClientphone(String clientphone) {
            TextView vname = mView.findViewById(R.id.cusphone);
            vname.setText(clientphone);
        }

    }
    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer4);
        navigationView = findViewById(R.id.nv4);
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

