package com.example.dreamland.foodlover;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.drm.DrmStore;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.okhttp.internal.Internal;

public class postfooditem extends AppCompatActivity {

    ImageButton additem;
    EditText post_name, post_discription, post_price,post_restauarant_name,foodid;
    Button postsubmit;
    private static final int GALLREQ=1;
    Uri uri=null;
    StorageReference storageReference = null;
    DatabaseReference mref;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLREQ && resultCode==RESULT_OK){
            uri=data.getData();
            additem=(ImageButton)findViewById(R.id.img);
            additem.setImageURI(uri);
        }
    }

    public void ImgButtonClicked(View view) {
        Intent gallaryIntent=new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        //gallaryIntent.setType("Image/*");
        startActivityForResult(gallaryIntent, GALLREQ);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postfooditem);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initNavDrawer();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Post Food Item");
        post_name = (EditText) findViewById(R.id.editText);
        post_discription = (EditText) findViewById(R.id.editText2);
        post_price = (EditText) findViewById(R.id.editText3);
        post_restauarant_name=(EditText)findViewById(R.id.editText4);
        foodid=(EditText)findViewById(R.id.editText5);
        postsubmit=(Button)findViewById(R.id.button4);
        storageReference= FirebaseStorage.getInstance().getReference("Post_Item");
        mref= FirebaseDatabase.getInstance().getReference("Post_Item");
        postsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String na=post_name.getText().toString().trim();
                final String de=post_discription.getText().toString().trim();
                final String pr=post_price.getText().toString().trim();
                final String rn=post_restauarant_name.getText().toString().trim();
                final String foid=foodid.getText().toString().trim();

                if(!TextUtils.isEmpty(na)&&!TextUtils.isEmpty(de)&&!TextUtils.isEmpty(pr)&&!TextUtils.isEmpty(rn)&&!TextUtils.isEmpty(foid)){
                    StorageReference filepath=storageReference.child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            final Uri downloadUrl=taskSnapshot.getDownloadUrl();
                            Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                            final DatabaseReference newpost=mref.push();
                            newpost.child("name").setValue(na);
                            newpost.child("description").setValue(de);
                            newpost.child("price").setValue(pr);
                            newpost.child("restaurantname").setValue(rn);
                            newpost.child("foodid").setValue(foid);
                            newpost.child("foodimage").setValue(downloadUrl.toString());
                        }
                    });
                }
            }
        });

    }
    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer2);
        navigationView = findViewById(R.id.nv2);
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
