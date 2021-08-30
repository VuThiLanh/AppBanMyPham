package com.example.appbanmypham.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appbanmypham.MainActivity;
import com.example.appbanmypham.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DangNhap extends AppCompatActivity {
 Button btnDangNhap, btnDangKy;
 EditText edEmail,edPass;
 ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        anhxa();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chuyensangdangky();
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangnhap();
            }
        });
    }
    private void anhxa(){
        btnDangNhap=findViewById(R.id.btnDangnhap);
        btnDangKy=findViewById(R.id.btnDangki);
        edEmail=findViewById(R.id.txtEmail);
        edPass=findViewById(R.id.txtPass);
        progressDialog = new ProgressDialog(this);
    }
    private  void  chuyensangdangky(){
        Intent intent = new Intent(DangNhap.this,DangKy.class);
        startActivity(intent);
    }
    private  void dangnhap(){
        String email = edEmail.getText().toString().trim();
        String password = edPass.getText().toString().trim();
        if (email.equals("") || password.equals("")){
            AlertDialog ad = new AlertDialog.Builder(DangNhap.this).create();
            ad.setTitle("Thông báo");
            String msg = String.format("Vui lòng nhập đầy đủ thông tin!");
            ad.setMessage(msg);
            ad.setIcon(android.R.drawable.ic_dialog_info);
            ad.setButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which)
                {
                }
            });
            ad.show();
        }
        else{
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                AlertDialog ad = new AlertDialog.Builder(DangNhap.this).create();
                                ad.setTitle("Thông báo");
                                String msg = String.format("Đăng nhập thành công !");
                                ad.setMessage(msg);
                                ad.setIcon(android.R.drawable.ic_dialog_info);
                                ad.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        Intent intent = new Intent(DangNhap.this, MainActivity.class);
                                        startActivity(intent);
                                    }
                                });
                                ad.show();
                            } else {
                                AlertDialog ad = new AlertDialog.Builder(DangNhap.this).create();
                                ad.setTitle("Thông báo");
                                String msg = String.format("Đăng nhập thất bại !");
                                ad.setMessage(msg);
                                ad.setIcon(android.R.drawable.ic_dialog_info);
                                ad.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                    }
                                });
                                ad.show();
                            }
                        }
                    });
        }
    }
}