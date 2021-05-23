package com.example.logingg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class RegisterActivity extends AppCompatActivity {
    EditText edt_email, edt_password;
    Button btn_login, btn_register;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        edt_email = findViewById(R.id.editTextTextEmailAddress);
        edt_password = findViewById(R.id.editTextTextPassword);
        btn_register = findViewById(R.id.button2);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }
    private void register() {

        String email, password;
        email = edt_email.getText().toString().trim();
        password = edt_password.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Bạn chưa nhập email!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Bạn chưa nhập password!", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Bạn đã đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegisterActivity.this, "Đang nhập không thành công.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}