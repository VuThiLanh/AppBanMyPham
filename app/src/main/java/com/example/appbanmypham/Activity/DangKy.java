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
import android.widget.Toast;

import com.example.appbanmypham.MainActivity;
import com.example.appbanmypham.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DangKy extends AppCompatActivity {
    EditText edemailDk,edpassDK;
    Button btnDangKiDK,btnQuayLai;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        anhxa();
        initListener();
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(DangKy.this,DangNhap.class);
                startActivity(intent);
            }
        });

    }
    private  void anhxa(){
        edemailDk=findViewById(R.id.txtEmailDK);
        edpassDK=findViewById(R.id.txtPassDK);
        btnDangKiDK=findViewById(R.id.btnDangkiDK);
        progressDialog = new ProgressDialog(this);
        btnQuayLai=findViewById(R.id.btnQuayLai);

    }
    private  void initListener(){
        btnDangKiDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDangKy();
            }
        });
    }
    private  void onClickDangKy(){
        String email = edemailDk.getText().toString().trim();
        String password = edpassDK.getText().toString().trim();
        if (email.equals("") || password.equals("")){
            AlertDialog ad = new AlertDialog.Builder(DangKy.this).create();
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
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                AlertDialog ad = new AlertDialog.Builder(DangKy.this).create();
                                ad.setTitle("Thông báo");
                                String msg = String.format("Đăng ký thành công !");
                                ad.setMessage(msg);
                                ad.setIcon(android.R.drawable.ic_dialog_info);
                                ad.setButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        Intent intent = new Intent(DangKy.this, DangNhap.class);
                                        startActivity(intent);
                                    }
                                });
                                ad.show();

                            } else {
                                AlertDialog ad = new AlertDialog.Builder(DangKy.this).create();
                                ad.setTitle("Thông báo");
                                String msg = String.format("Đăng ký thất bại !");
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