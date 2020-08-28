package com.app.omahdilit.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.app.omahdilit.EditProfil;
import com.app.omahdilit.Login;
import com.app.omahdilit.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class ProfileFragment extends Fragment {

    private ProfileViewModel notificationsViewModel;

    @BindView(R.id.profil_image_imageuser)
    ImageView iv_profile_imageuser;
    @BindView(R.id.profil_text_nama)
    TextView profil_text_nama;
    @BindView(R.id.profil_text_email) TextView profil_text_email;
    @BindView(R.id.profile_text_phone) TextView profil_text_phone;


    FirebaseAuth.AuthStateListener mAuthStateListener;
    private GoogleSignInClient mGoogleSignInClient;
    private GoogleSignInOptions gso;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, root);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        profil_text_nama.setText(user.getDisplayName());
        profil_text_email.setText(user.getEmail());
        profil_text_phone.setText(user.getPhoneNumber());
        Picasso.get().load(user.getPhotoUrl()).transform(new CropCircleTransformation()).into(iv_profile_imageuser);

        return root;
    }

    @OnClick(R.id.profil_button_logout) void onClickLogout(){     FirebaseAuth.getInstance().signOut();
            mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    FirebaseAuth.getInstance().signOut();

                    Intent intent = new Intent(getActivity(), Login.class);
                    startActivity(intent);
                    requireActivity().finish();
                }
            });

    }

    @OnClick(R.id.profil_layout_editprofil) void onClickEditProfil(){
        Intent intent = new Intent(getActivity(), EditProfil.class);
        startActivity(intent);
    }

    public interface OnFragmentInteractionListener {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

    }
}