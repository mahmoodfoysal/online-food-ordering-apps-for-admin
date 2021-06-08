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

public class MAIN extends AppCompatActivity {

    EditText email,password;
    Button loginbtn;
    Button Registerbtn;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        email=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        loginbtn=(Button)findViewById(R.id.button);
        Registerbtn=(Button)findViewById(R.id.button2);
        firebaseAuth=FirebaseAuth.getInstance();
        authStateListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){
                    Toast.makeText(MAIN.this,"Sign In ",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MAIN.this,"Create A new Account",Toast.LENGTH_SHORT).show();
                }
            }
        };
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignin();
            }
        });
        Registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a=new Intent(MAIN.this,RegisterActivity.class);
                startActivity(a);
            }
        });

    }
    private void startSignin(){
        String e=email.getText().toString().trim();
        String p=password.getText().toString().trim();
        if(TextUtils.isEmpty(e)||TextUtils.isEmpty(p)){
            Toast.makeText(MAIN.this,"Field is empty",Toast.LENGTH_SHORT).show();
        }
        else{
            firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(MAIN.this,"Sign In Problem",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Intent i=new Intent(MAIN.this,Foodmanue.class);
                        startActivity(i);
                        Toast.makeText(MAIN.this,"Sign In",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
