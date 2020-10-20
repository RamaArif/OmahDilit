package com.app.omahdilit;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.app.omahdilit.api.RegisterApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.BaseResponse;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.rilixtech.widget.countrycodepicker.CountryCodePicker;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

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
    TextView register_input_address;
    @BindView(R.id.register_input_ccp)
    CountryCodePicker register_input_ccp;
    @BindView(R.id.register_input_password)
    EditText register_input_password;
    @BindView(R.id.register_image_profile)
    ImageView register_image_profile;

    String email, name, number, addres, passUser, photo, uid, pushtoken;
    Double lat,lng;

    LoadingDialog loadingDialog;

    private static int AUTOCOMPLETE_REQUEST_CODE = 2;

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

        // Initialize the SDK
        Places.initialize(getApplicationContext(), "AIzaSyDWxy8MciL_TIacdS1aPDKDaKCDZI9invA");

        // Create a new PlacesClient instance
        PlacesClient placesClient = Places.createClient(this);

        loadingDialog = new LoadingDialog(Register.this);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        register_input_email.setText(user.getEmail());
        register_input_name.setText(user.getDisplayName());
        Picasso.get().load(user.getPhotoUrl()).transform(new CropCircleTransformation()).into(register_image_profile);
        uid = user.getUid();

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                pushtoken = task.getResult().getToken();
            }
        });
    }


    @OnClick(R.id.register_input_address) void onAddressClick(){
        List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

        // Start the autocomplete intent.
        Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY, fields).setCountry("ID")
                .build(this);
        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
    }

    @OnClick(R.id.register_button_register) void register(){
         loadingDialog.startLoading();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        email = String.valueOf(register_input_email.getText());
        name = String.valueOf(register_input_name.getText());
        number = register_input_ccp.getSelectedCountryCode() + register_input_phone.getText();
        passUser = String.valueOf(register_input_password.getText());

        BitmapDrawable drawable = (BitmapDrawable) register_image_profile.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageBytes = byteArrayOutputStream.toByteArray();
        photo = Base64.encodeToString(imageBytes, Base64.DEFAULT);

//        Log.w("email", email);
//        Log.w("name", name);
//        Log.w("address", addres);
//        Log.w("number", number);
//        Log.w("pass", passUser);
//        Log.w("uid", uid);
//        Log.w("token", pushtoken);
//        Log.w("photo", photo);
//        Toast.makeText(Register.this, "convert sukses", Toast.LENGTH_SHORT).show();

        RegisterApi api = RetrofitApi.register();
        Call<BaseResponse> call = api.register(name,addres,number,email,uid,photo,pushtoken,lat,lng);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==AUTOCOMPLETE_REQUEST_CODE){
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                addres = place.getAddress();
                register_input_address.setText(addres);

                LatLng latLng = place.getLatLng();
                lat = latLng.latitude;
                lng = latLng.longitude;

//                Toast.makeText(DetailPesanan.this, "Alamat :" + place.getAddress()+", Lat Long : "+place.getLatLng(), Toast.LENGTH_LONG).show();
//                Log.i("tag", "Place: " + place.getName() + ", " + place.getId());
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                // TODO: Handle the error.
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.i("tag", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                // The user canceled the operation.
            }
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}