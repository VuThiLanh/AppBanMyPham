package com.example.appbanmypham;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.appbanmypham.Activity.DangNhap;
import com.example.appbanmypham.fragment.FragmentChangePass;
import com.example.appbanmypham.fragment.HomeFragment;
import com.example.appbanmypham.fragment.YeuThichFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
        DrawerLayout mdrawerLayout;
        final private FragmentChangePass mfragmentChangePass = new FragmentChangePass();
        private static  final  int Fragment_home = 0;
    private static  final  int Fragment_yeuthich = 1;
    private static  final  int Fragment_changepass = 2;
    private int mCurrentFragmet = Fragment_home;
    private ImageView imgAvatar;
    private TextView tv_name,tv_email;
    private NavigationView mNavigationView;
    public static  final int MY_REQUEST_CODE=10;


    final private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK){
                Intent intent = result.getData();
                if(intent==null){
                    return;
                }
                Uri uri = intent.getData();
                mfragmentChangePass.setmUri(uri);
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                    mfragmentChangePass.setBitMapImageView(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initUI();
        mdrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,mdrawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mdrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        mNavigationView.setNavigationItemSelectedListener(this);
        replaceFragment(new HomeFragment());
        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

        showInformation();
    }
    private  void  initUI(){
        mNavigationView = findViewById(R.id.navigation_view);
        imgAvatar=mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tv_name=mNavigationView.getHeaderView(0).findViewById(R.id.tv_name);
        tv_email=mNavigationView.getHeaderView(0).findViewById(R.id.tv_email);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.nav_home){
            if(mCurrentFragmet!=Fragment_home){
                replaceFragment(new HomeFragment());
                mCurrentFragmet = Fragment_home;
            }
        }
        else if(id==R.id.nav_favourite){
            if(mCurrentFragmet!=Fragment_yeuthich){
                replaceFragment(new YeuThichFragment());
                mCurrentFragmet = Fragment_yeuthich;
            }
        }
        else if(id==R.id.nav_taikhoan){
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, DangNhap.class);
            startActivity(intent);
            finish();
        }
        else if(id==R.id.nav_changepassword){
            if(mCurrentFragmet!=Fragment_changepass){
                replaceFragment(mfragmentChangePass);
                mCurrentFragmet = Fragment_changepass;
            }
        }
        mdrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(mdrawerLayout.isDrawerOpen(GravityCompat.START)){
            mdrawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
   public   void showInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        else{
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            if (name==null){
                tv_name.setVisibility(View.GONE);
            }
            else {
                tv_name.setVisibility(View.VISIBLE);
                tv_name.setText(name);
            }
            tv_email.setText(email);
            Glide.with(this).load(photoUrl).error(R.drawable.avatardefault).into(imgAvatar);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==MY_REQUEST_CODE){
            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openGallary();
            }
        }
    }
    public  void openGallary(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Chọn ảnh"));
    }
}