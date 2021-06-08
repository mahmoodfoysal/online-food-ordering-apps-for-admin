package com.example.dreamland.foodlover;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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

public class offers_post extends AppCompatActivity {

    EditText oname,oquantity,odprice,orestaurnt,offerfoodid;
    Button postbtn;
    ImageButton addpic;

    private static final int GALLREQ=1;
    Uri uri=null;
    StorageReference storageReference = null;
    DatabaseReference offerref;

    private android.support.v4.widget.DrawerLayout DrawerLayout;
    private ActionBarDrawerToggle Toggle;
    private NavigationView navigationView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==GALLREQ && resultCode==RESULT_OK){
            uri=data.getData();
            addpic=(ImageButton)findViewById(R.id.imgoffer);
            addpic.setImageURI(uri);
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
        setContentView(R.layout.activity_offers_post);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        initNavDrawer();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Post Offers");
        oname=(EditText)findViewById(R.id.offer1);
        oquantity=(EditText)findViewById(R.id.offer2);
        odprice=(EditText)findViewById(R.id.offer3);
        orestaurnt=(EditText)findViewById(R.id.offer4);
        postbtn=(Button)findViewById(R.id.postbtn);
        offerfoodid=(EditText)findViewById(R.id.foodidtext);

        storageReference= FirebaseStorage.getInstance().getReference("Offer_post");
        offerref= FirebaseDatabase.getInstance().getReference("Offer_post");
        postbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String nameoffer=oname.getText().toString().trim();
                final String quantity=oquantity.getText().toString().trim();
                final String priceoffer=oquantity.getText().toString().trim();
                final String restaurantoffer=orestaurnt.getText().toString().trim();
                final String ofoodid=offerfoodid.getText().toString().trim();

                if(!TextUtils.isEmpty(nameoffer)&&!TextUtils.isEmpty(quantity)&&!TextUtils.isEmpty(priceoffer)&&!TextUtils.isEmpty(restaurantoffer)&&!TextUtils.isEmpty(ofoodid)){
                    StorageReference filepath=storageReference.child(uri.getLastPathSegment());
                    filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            final Uri downloadUrl=taskSnapshot.getDownloadUrl();
                            Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
                            final DatabaseReference newpost=offerref.push();
                            newpost.child("offername").setValue(nameoffer);
                            newpost.child("offerquantity").setValue(quantity);
                            newpost.child("offerprice").setValue(priceoffer);
                            newpost.child("offerestaurantname").setValue(restaurantoffer);
                            newpost.child("offerimage").setValue(downloadUrl.toString());
                            newpost.child("offerfoodid").setValue(ofoodid);
                        }
                    });
                }
            }
        });

    }
    private void initNavDrawer() {
        DrawerLayout = findViewById(R.id.navidrawer3);
        navigationView = findViewById(R.id.nv3);
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
