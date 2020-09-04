package com.app.omahdilit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.app.omahdilit.api.LoginApi;
import com.app.omahdilit.api.RetrofitApi;
import com.app.omahdilit.response.BaseResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {
    @BindView(R.id.login_input_email) TextInputEditText login_input_email;
    @BindView(R.id.login_input_password) TextInputEditText login_input_password;
    @BindView(R.id.login_button_email) Button login_button_email;
    @BindView(R.id.login_button_google) Button login_button_google;


    private FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListner;
    private final static int RC_SIGN_IN = 123;
    GoogleSignInClient mGoogleSignInClient;

    
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
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() == null) {

        }
        else {
            FirebaseUser user = mAuth.getCurrentUser();
            checkLogin(user.getEmail());
        }

        mAuthListner = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null) {
                    FirebaseUser user = mAuth.getCurrentUser();
                   checkLogin(user.getEmail());
                }
            }
        };

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        
    }

    @OnClick(R.id.login_button_google) void logIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {

            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(Login.this, "Login sukses", Toast.LENGTH_SHORT).show();
                            checkLogin(user.getEmail());
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Login Gagal", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                        // ...
                    }
                });
    }

    private void registered() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void unregistered(){
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
        finish();
    }

    private void checkLogin(String email){
        LoginApi api = RetrofitApi.getLogin();
        Call<BaseResponse> call = api.getLogin(email);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.body() != null){
                    Boolean unRegistered = response.body().getError();
                    if (unRegistered){
                        unregistered();
                    }
                    else {
                        registered();
                    }
                }
            }
            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(Login.this, "error : "+t,Toast.LENGTH_LONG).show();
            }
        });
    }

    @OnClick (R.id.login_button_email) void login(){
        String email, password;
        email = String.valueOf(login_input_email.getText());
        password = String.valueOf(login_input_password.getText());
        AuthCredential authCredential = EmailAuthProvider.getCredential(email,password);
        mAuth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                    checkLogin(user.getEmail());
                }
            }
        });
    }
}