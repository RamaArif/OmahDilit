package com.app.omahdilit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.app.omahdilit.api.RegisterApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.LoginResponse;
import com.app.omahdilit.response.RegisterRequestBody;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
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

    String emailUser, nameUser,phoneUser,Address, passUser, photoUser, uid, pushtoken, isApiSuccess="0", isFirebaseSuccess="0";

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

//        Intent intent = new Intent(Register.this, MainActivity.class);
//        startActivity(intent);
//        finish();

        emailUser = String.valueOf(register_input_email.getText());
        nameUser = String.valueOf(register_input_name.getText());
        phoneUser = register_input_ccp.getSelectedCountryCode() + String.valueOf(register_input_phone.getText());
        Address = String.valueOf(register_input_address);
        passUser = String.valueOf(register_input_password.getText());

        BitmapDrawable drawable = (BitmapDrawable) register_image_profile.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        photoUser = Base64.encodeToString(byteArrayOutputStream.toByteArray(),
                Base64.DEFAULT);
////        Toast.makeText(Register.this, "convert sukses", Toast.LENGTH_SHORT).show();

        RegisterApi api = RetrofitApi.register();
        Call<LoginResponse> call = api.register(new RegisterRequestBody(nameUser, Address,phoneUser, emailUser,photoUser,uid,pushtoken));
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.body() != null){
                    if (!response.body().getError()){
                        AuthCredential authCredential = EmailAuthProvider.getCredential(emailUser,passUser);
                        user.linkWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                            }
                        });
                        loadingDialog.dismissLoading();
                        Intent intent = new Intent(Register.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Register.this, "API error"+t, Toast.LENGTH_LONG).show();
                loadingDialog.dismissLoading();
            }
        });
    }
}