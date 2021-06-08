package com.example.dreamland.foodlover;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterActivity extends AppCompatActivity{
    Button bRegister;
    FirebaseAuth mAuth;;
    EditText etName, etAge, etUsername,  etEmail, etPassword,conpass;
    DatabaseReference mref;
    StorageReference storageReference=null;
    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        startActivity(new Intent(getApplicationContext(),MAIN.class));
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Sign Up");
        etName =(EditText) findViewById(R.id.etName);
        etAge =(EditText) findViewById(R.id.etAge);
        etUsername =(EditText) findViewById(R.id.etUsername);
        etEmail =(EditText) findViewById(R.id.etEmail);
        etPassword =(EditText) findViewById(R.id.etPassword);
        bRegister =(Button) findViewById(R.id.bRegister);
        conpass=(EditText)findViewById(R.id.copass);
        mAuth=FirebaseAuth.getInstance();
        mref=FirebaseDatabase.getInstance().getReference("AdminInfo");
        storageReference= FirebaseStorage.getInstance().getReference("AdminInfo");
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=etEmail.getText().toString().trim();
                String password=etPassword.getText().toString().trim();
                String name=etName.getText().toString().trim();
                String Age=etAge.getText().toString().trim();
                String Username=etUsername.getText().toString().trim();
                if(!TextUtils.isEmpty(name)&&!TextUtils.isEmpty(Age)&&!TextUtils.isEmpty(email)&&!TextUtils.isEmpty(password)&&!TextUtils.isEmpty(Username)){
                    final DatabaseReference newPost=mref.push();
                    final DatabaseReference x=newPost.push();
                    x.child("name").setValue(name);
                    x.child("Age").setValue(Age);
                    x.child("email").setValue(email);
                    x.child("Password").setValue(password);
                    x.child("Username").setValue(Username);
                }
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(RegisterActivity.this,MAIN.class));
                            Toast.makeText(getApplicationContext(),"Created",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
