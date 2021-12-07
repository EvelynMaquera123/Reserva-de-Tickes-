package ne.subgrupo_catorce.proyecto_final.ui.register;

import static androidx.navigation.Navigation.findNavController;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import ne.subgrupo_catorce.proyecto_final.R;
import ne.subgrupo_catorce.proyecto_final.databinding.FragmentRegisterBinding;

public class RegisterFragment extends Fragment {
    private FragmentRegisterBinding binding;
    private static final String TAG = "OverviewFragment";
    private static final int RC_SIGN_IN = 9001;
    private static final int TWITTER_RC_SIGN_IN = 140;
    @SuppressLint("NonConstantResourceId")
    //@BindView(R.id.buttonG) SignInButton mGoogleSigninButton;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        mAuth = FirebaseAuth.getInstance();
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false);
        View vista = inflater.inflate(R.layout.fragment_register, container, false);
        Button btn = vista.findViewById(R.id.btn_google);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginByGoogle();
                //findNavController(v).navigate(R.id.action_registerFragment_to_eventOverview);
            }
        });
        Button btnAdmin = vista.findViewById(R.id.btn_admin);
        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findNavController(view).navigate(R.id.action_registerFragment_to_eventsDBFragment);
            }
        });
        return vista;
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Ingresado(user);
                        } else {
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Ingresado(null);
                        }
                    }
                });
    }

    private void Ingresado(FirebaseUser user) {

        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
        if (users != null) {

            Log.i("sd", "ingreso");
            NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_eventsListFragment);

            Toast.makeText(getActivity(), "Ingreso", Toast.LENGTH_SHORT).show();
        } else {


        }

    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),

            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult res) {
                    NavHostFragment.findNavController(getParentFragment()).navigate(R.id.action_registerFragment_to_eventsListFragment);

                    if (res.getResultCode() == RC_SIGN_IN) {

                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(res.getData());
                        try {
                            // Google Sign In was successful, authenticate with Firebase
                            GoogleSignInAccount account = task.getResult(ApiException.class);
                            //NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_eventOverview);

                            Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                            firebaseAuthWithGoogle(account.getIdToken());

                        } catch (ApiException e) {
                            // Google Sign In failed, update UI appropriately
                            Log.w(TAG, "Google sign in failed", e);
                            //NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_eventOverview);
                        }
                    }
                }
            });

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);
    }

    public void loginByFacebook() {
        NavHostFragment.findNavController(this).navigate(R.id.action_registerFragment_to_eventsListFragment);
    }

    public void loginByGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);

    }
}
