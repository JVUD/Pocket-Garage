package com.example.pocketgarage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class RegisterPage extends AppCompatActivity {
    TextInputEditText editTextEmail, editTextPassword, confirmPassword, name;

    Button signUp;

    TextView signIn;



    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.password_confirm);
        signIn = findViewById(R.id.sign_in);
        signUp = findViewById(R.id.sign_up);
        name = findViewById(R.id.name);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterPage.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email, password, passwordConfirm, nickname;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPassword.getText());
                passwordConfirm= String.valueOf(confirmPassword.getText());
                nickname = String.valueOf(name.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(RegisterPage.this, "Error empty Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(RegisterPage.this,"Error empty password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(password.equals(passwordConfirm))){
                    Toast.makeText(RegisterPage.this,"passwords doasn't match", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(Validation.validateEmail(email))){
                    Toast.makeText(RegisterPage.this,"Email doesn't validate", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!(Validation.validateEmail(email))){
                    Toast.makeText(RegisterPage.this,"Password must contain one uppercase and digit ", Toast.LENGTH_SHORT).show();
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(RegisterPage.this, "Registration Successfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(RegisterPage.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(RegisterPage.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}