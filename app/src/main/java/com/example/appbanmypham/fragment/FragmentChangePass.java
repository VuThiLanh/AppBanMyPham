package com.example.appbanmypham.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.appbanmypham.Activity.DangNhap;
import com.example.appbanmypham.BuildConfig;
import com.example.appbanmypham.MainActivity;
import com.example.appbanmypham.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static com.example.appbanmypham.MainActivity.MY_REQUEST_CODE;

public class FragmentChangePass extends Fragment {
    private  View mView;
    private ImageView imgAvatarUpdate;
    private Button btnCapNhat;
    EditText ed_tendn,ed_email;
    private  Uri mUri ;
    private MainActivity mMainActivity;
    ProgressDialog progressDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView= inflater.inflate(R.layout.fragment_changepass, container, false);
        unitUI();
        mMainActivity=(MainActivity) getActivity();
        setUserInformation();
        initListener();
        return  mView;
    }

    private void initListener() {
        imgAvatarUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRequestPermission();
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUpdateProfile();
            }
        });
    }
    private  void onClickUpdateProfile(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user==null){
            return;
        }
        String strfullname=ed_tendn.getText().toString().trim();
        progressDialog.show();
        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName(strfullname)
                .setPhotoUri(mUri)
                .build();
        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            AlertDialog ad = new AlertDialog.Builder(getActivity()).create();
                            ad.setTitle("Thông báo");
                            String msg = String.format("Cập nhật thành công !");
                            ad.setMessage(msg);
                            ad.setIcon(android.R.drawable.ic_dialog_info);
                            ad.setButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                }
                            });
                            ad.show();
                            mMainActivity.showInformation();
                        }
                    }
                });
    }
    private  void onClickRequestPermission(){
        if(mMainActivity==null){
            return;
        }
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            mMainActivity.openGallary();
            return;
        }
        if(getActivity().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_GRANTED){
            mMainActivity.openGallary();
        }
        else{
            String [] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            getActivity().requestPermissions(permissions,MY_REQUEST_CODE);

        }
    }
    private void setUserInformation() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            return;
        }
        ed_tendn.setText(user.getDisplayName());
        ed_email.setText(user.getEmail());
        Glide.with(getActivity()).load(user.getPhotoUrl()).error(R.drawable.avatardefault).into(imgAvatarUpdate);
    }

    private void   unitUI(){
        imgAvatarUpdate=mView.findViewById(R.id.img_update_avatar);
        btnCapNhat=mView.findViewById(R.id.btnCapNhat);
        ed_email=mView.findViewById(R.id.ed_email_update);
        ed_tendn=mView.findViewById(R.id.ed_full_name);
        progressDialog = new ProgressDialog(getActivity());

    }
    public  void setBitMapImageView(Bitmap bitMapImageView){
        imgAvatarUpdate.setImageBitmap(bitMapImageView);
    }

    public void setmUri(Uri mUri) {
        this.mUri = mUri;
    }
}
