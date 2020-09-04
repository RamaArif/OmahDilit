package com.app.omahdilit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.omahdilit.api.RegisterApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.BaseResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;

    @BindView(R.id.register_input_email)
    EditText register_input_email;
    @BindView(R.id.register_input_name)
    EditText register_input_name;
    @BindView(R.id.register_input_phone)
    EditText register_input_phone;
    @BindView(R.id.register_input_address)
    EditText register_input_address;
    @BindView(R.id.register_input_ccp)
    CountryCodePicker register_input_ccp;
    @BindView(R.id.register_input_password)
    EditText register_input_password;
    @BindView(R.id.register_image_profile)
    ImageView register_image_profile;

    String email, name, number, addres, passUser, photo, uid, pushtoken;

    LoadingDialog loadingDialog;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);
    }

    private void updateUI(FirebaseUser user) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        loadingDialog = new LoadingDialog(Register.this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        register_input_email.setText(user.getEmail());
        register_input_name.setText(user.getDisplayName());
        Picasso.get().load(user.getPhotoUrl()).transform(new CropCircleTransformation()).into(register_image_profile);
        uid = user.getUid();
        user.getIdToken(true).addOnCompleteListener(new OnCompleteListener<GetTokenResult>() {
            @Override
            public void onComplete(@NonNull Task<GetTokenResult> task) {
                if (task.isSuccessful()){
                    pushtoken = task.getResult().getToken();
                }
            }
        });
    }

    @OnClick(R.id.register_button_register) void register(){
         loadingDialog.startLoading();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        email = String.valueOf(register_input_email.getText());
        name = String.valueOf(register_input_name.getText());
        number = register_input_ccp.getSelectedCountryCode() + register_input_phone.getText();
        addres = String.valueOf(register_input_address.getText());
        passUser = String.valueOf(register_input_password.getText());

        BitmapDrawable drawable = (BitmapDrawable) register_image_profile.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        photo = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        Log.w("email", email);
        Log.w("name", name);
        Log.w("address", addres);
        Log.w("number", number);
        Log.w("pass", passUser);
        Log.w("uid", uid);
        Log.w("token", pushtoken);
        Log.w("photo", photo);
//        Toast.makeText(Register.this, "convert sukses", Toast.LENGTH_SHORT).show();

        RegisterApi api = RetrofitApi.register();
        Call<BaseResponse> call = api.register(name,addres,number,email,uid,photo,pushtoken);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                Log.w("status", "api dipanggil");
                if (response.body() != null){
                    if (!response.body().getError()){
                        Toast.makeText(Register.this, "Berhasil daftar database", Toast.LENGTH_LONG).show();
                        AuthCredential authCredential = EmailAuthProvider.getCredential(email,passUser);
                        user.linkWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser currentUser = task.getResult().getUser();

                                    Intent intent = new Intent(Register.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(Register.this, "API error"+t, Toast.LENGTH_LONG).show();
                loadingDialog.dismissLoading();
            }
        });
    }
}