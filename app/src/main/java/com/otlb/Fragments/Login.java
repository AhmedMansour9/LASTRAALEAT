package com.otlb.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.otlb.Activites.Navigation;
import com.otlb.Model.UserRegister;
import com.otlb.Presenter.LoginPresenter;
import com.otlb.Presenter.RegisterFace_Presenter;
import com.otlb.Presenter.Registergoogle;
import com.otlb.Presenter.Tokens_Presenter;
import com.raaleat.R;
import com.otlb.SharedPrefManager;
import com.otlb.View.LoginView;
import com.otlb.View.RegisterFaceView;
import com.otlb.View.RegistergoogleView;
import com.otlb.View.Tokens_View;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment implements LoginView, RegisterFaceView, RegistergoogleView, Tokens_View {


    public Login() {
        // Required empty public constructor
    }

    ImageView loginfac,google;
    CallbackManager mCallbackManager;
    private ProgressBar progressBar;
    public FirebaseAuth mAuth;
    public GoogleApiClient googleApiClient;
    public static final int RequestSignInCode = 7;
    GoogleSignInOptions googleSignInOptions;
    EditText E_Email,E_Password;
    Button signin;
    LoginPresenter logiin;
    RegisterFace_Presenter regist;
    UserRegister userface;
    LoginResult loginResu;
    Registergoogle Registergoogl;
    SharedPreferences.Editor Shared;
    TextView Register;
    SharedPreferences.Editor shareRole;
    RelativeLayout Relativelogin;
    Tokens_Presenter tokens_presenter;
    String useer;
    String email;
    String useergoogle;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login, container, false);
        FirebaseApp.initializeApp(getContext());
         init();
//        GoogleSignOpition();
//        LoginGoogle();
//        LoginFac();
        Loginhome();
        Register();

        return view;
    }
    public void Register()
    {
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.flContent, new Register()).addToBackStack(null).commit();            }
        });
    }
    private void Loginhome() {
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!ValidateEmail()){
                    return;
                }
//                if(checknetwork.isNetworkAvailable(getBaseContext())) {
//

                    FUtilsValidation.isEmpty(E_Email,getResources().getResourceName(R.string.insertemail));
                    FUtilsValidation.isEmpty(E_Password, getResources().getResourceName(R.string.insertpassword));

                    if (!E_Email.getText().toString().equals("") && !E_Password.getText().toString().equals("")) {
                        UserRegister user = new UserRegister();
                        user.setEmail(E_Email.getText().toString());
                        user.setPassword(E_Password.getText().toString());
                        progressBar.setVisibility(View.VISIBLE);
                        email=E_Email.getText().toString();
                        logiin.Login(user);
                    }
//                }else {
//                    Toast.makeText(getActivity(),getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
//                }
            }
        });
    }


    private Boolean ValidateEmail(){
        String EMAIL=E_Email.getText().toString().trim();
        if (EMAIL.isEmpty()||!isValidEmail(EMAIL)){
            E_Email.setError(getResources().getString(R.string.invalidemail));

            return false;
        }else if(!E_Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z-Z0-9._-]+\\.+[a-z]+")){
            E_Email.setError(getResources().getString(R.string.invalidemail));
            return false;
        }
        return true;
    }
    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    private void LoginGoogle() {

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if(checknetwork.isNetworkAvailable(getBaseContext())) {
                    Intent AuthIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                    startActivityForResult(AuthIntent, RequestSignInCode);
//                }else {
//                    Toast.makeText(Login.this,getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
//
//                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestSignInCode) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (googleSignInResult.isSuccess()) {
                GoogleSignInAccount googleSignInAccount = googleSignInResult.getSignInAccount();
                FirebaseUserAuth(googleSignInAccount);
            }
        }
    }



    public void GoogleSignOpition(){
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                } /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();

    }


    public void FirebaseUserAuth(final GoogleSignInAccount googleSignInAccount) {
        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
        mAuth.signInWithCredential(authCredential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>(){
                    @Override
                    public void onComplete(@NonNull Task AuthResultTask) {
                        if (AuthResultTask.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();

                            useergoogle=user.getDisplayName();
                            final String emaail=user.getEmail();
                            UserRegister use=new UserRegister();
                            String y =googleSignInAccount.getId();

                            use.setFirstName(useergoogle);
                            use.setEmail(emaail);
                            use.setId(y);

                            progressBar.setVisibility(View.VISIBLE);
                            Registergoogl.Registergoogle(use);




                        } else {
//                            Toast.makeText(LoginPresenter.this, "Turn on Internet", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
    public void LoginFac(){
        loginfac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (checknetwork.isNetworkAvailable(getBaseContext())) {

                    LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("email", "public_profile"));

                    LoginManager.getInstance().registerCallback(mCallbackManager,
                            new FacebookCallback<LoginResult>() {
                                @Override
                                public void onSuccess(LoginResult loginResult) {
                                    Log.d("Success", "LoginPresenter");
                                    loginResu = loginResult;
                                    handleFacebookAccessToken(loginResult.getAccessToken());
                                }

                                @Override
                                public void onCancel() {
//                                Toast.makeText(loginmain.this, "LoginPresenter Cancel", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onError(FacebookException exception) {
                                    Toast.makeText(getContext(), getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
                                }
                            });
//                }else {
//                    Toast.makeText(Login.this,getResources().getString(R.string.internet), Toast.LENGTH_LONG).show();
//                }
            }
        });
    }

    private void handleFacebookAccessToken(AccessToken token) {

        progressBar.setVisibility(View.VISIBLE);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            GraphRequest data_request = GraphRequest.newMeRequest(
                                    loginResu.getAccessToken(),
                                    new GraphRequest.GraphJSONObjectCallback() {
                                        @Override
                                        public void onCompleted(
                                                JSONObject object,
                                                GraphResponse response) {
                                            try {
                                                String facebook_id = object.getString("id");
                                                progressBar.setVisibility(View.GONE);
                                                FirebaseUser user = mAuth.getCurrentUser();

                                                useer=user.getDisplayName();
                                                final String emaail=user.getEmail();
                                                final String id=user.getUid();

                                                userface = new UserRegister();
                                                userface.setFirstName(useer);
                                                userface.setEmail(emaail);
                                                userface.setId(facebook_id);
                                                progressBar.setVisibility(View.VISIBLE);
                                                regist.RegisterFace(userface);

                                            } catch (JSONException e) {
                                                // TODO Auto-generated catch block
                                            }
                                        }
                                    });
                            Bundle permission_param = new Bundle();
                            permission_param.putString("fields", "id,name,email");
                            data_request.setParameters(permission_param);
                            data_request.executeAsync();
                            data_request.executeAsync();


                        }
                    }
                });
    }

    @Override
    public void openMain(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
       String token= SharedPrefManager.getInstance(getContext()).getDeviceToken();
        tokens_presenter.UpdateToken(token,a);
        Intent inty=new Intent(getContext(), Navigation.class);
        startActivity(inty);
        getActivity().finish();

    }

    @Override
    public void OpenRole(String usertoken) {
        progressBar.setVisibility(View.GONE);
        Shared.putString("logggin",usertoken);
        Shared.apply();
        String token= SharedPrefManager.getInstance(getContext()).getDeviceToken();
        tokens_presenter.UpdateToken(token,usertoken);

        startActivity(new Intent(getContext(), Navigation.class));
        getActivity().finish();

    }



    @Override
    public void showError(String error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void Invalidemail(String password) {
        Toast.makeText(getContext(), ""+getResources().getString(R.string.invalidemail), Toast.LENGTH_SHORT).show();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainFace(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        Intent inty=new Intent(getContext(),Navigation.class);
        inty.putExtra("username",useer);
        startActivity(inty);
        getActivity().finish();
    }

    @Override
    public void showErrorFace(String error) {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openMainGoogle(String a) {
        Shared.putString("logggin",a);
        Shared.apply();
        progressBar.setVisibility(View.GONE);
        Intent inty=new Intent(getContext(),Navigation.class);
        inty.putExtra("username",useergoogle);
        startActivity(inty);
        getActivity().finish();
    }

    @Override
    public void showErrorGoogle(String error) {
        progressBar.setVisibility(View.GONE);
    }

    public void init(){
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE |
                WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        E_Email=view.findViewById(R.id.login_email);
        E_Password=view.findViewById(R.id.login_password);
        signin=view.findViewById(R.id.Btn_Sigin);
        loginfac=view.findViewById(R.id.L_Face);
        Register=view.findViewById(R.id.Crate_Account);
//        Relativelogin=view.findViewById(R.id.Relativelogin);
        Shared=getActivity().getSharedPreferences("login",MODE_PRIVATE).edit();
        shareRole=getActivity().getSharedPreferences("Role",MODE_PRIVATE).edit();
        tokens_presenter=new Tokens_Presenter(getContext(),this);
        google=view.findViewById(R.id.L_google);
        progressBar=view.findViewById(R.id.progrosslogin);
        regist=new RegisterFace_Presenter(getContext(),this);
        Registergoogl=new Registergoogle(getContext(),this);
        mCallbackManager = CallbackManager.Factory.create();
//        mAuth = FirebaseAuth.getInstance();
//        checknetwork=new CheckgbsAndNetwork(this);
        logiin=new LoginPresenter(getContext(),this);
    }

    @Override
    public void success() {

    }

    @Override
    public void Error() {

    }
}
