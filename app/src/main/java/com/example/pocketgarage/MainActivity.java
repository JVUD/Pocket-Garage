package com.example.pocketgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;

    Button signIn;

    TextView signUp;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        signIn = findViewById(R.id.sign_in);
        signUp = findViewById(R.id.sign_up);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterPage.class);
                startActivity(intent);
                finish();
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(MainActivity.this, "Error empty Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"Error empty password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"Successful", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, HomePage.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(MainActivity.this,"Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}