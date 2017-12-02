package com.slapapp.slambook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private EditText mPhone;
    private Button mAction;
    Button mEmailSignInButton;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "SlamPref" ;
    public   String email ;
    public   String phone ;
    public   String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailView = (EditText) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPhone = (EditText) findViewById(R.id.phone_new);

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mAction = (Button) findViewById(R.id.action_button);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPhone.getVisibility()==View.VISIBLE)
                    attemptRegister();
                 else
                    attemptLogin();
            }
        });

        mEmailView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("WriteForm.java:","Write=\t"+mEmailView.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mPhone.getVisibility()==View.VISIBLE) {
                    mPhone.setVisibility(View.GONE);
                    mAction.setText("REGISTER HERE");
                }
                else {
                    mPhone.setVisibility(View.VISIBLE);
                    mAction.setText("LOGIN HERE");
                }
            }
        });

    }

    //METHODS TO CHECK EMAIL AND PASSWORD:-
    private void attemptRegister() {
            cancel=false;
            if(mPasswordView.getText().length()<4 ){
            mPasswordView.setError("Password is too Short");
                cancel=true;
            }
        if(mPhone.getText().length()<10 ){
            mPhone.setError("Phone Number should contain atleast 10 digits");
            cancel=true;
        }
        if(!cancel){
            email=mEmailView.getText().toString()+"email";
            phone=mEmailView.getText().toString()+"phone";
            password=mEmailView.getText().toString()+"password";
            String emailvalue  = mEmailView.getText().toString();
            String phonevalue  = mPhone.getText().toString();
            String pswdvalue  = mPasswordView.getText().toString();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putString(email, emailvalue);
            editor.putString(phone, phonevalue);
            editor.putString(password, pswdvalue);
            editor.commit();
            Toast.makeText(LoginActivity.this,"Successfully Registered..! continue..!",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(LoginActivity.this,UserHome.class);
            intent.putExtra("emailFor",emailvalue);
            startActivity(intent);

        }
    }
    boolean cancel;

    //LOGIN CHECK:-
    private void attemptLogin() {


        mEmailView.setError(null);
        mPasswordView.setError(null);


        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        cancel = false;
        View focusView = null;
        String vEmail;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            vEmail=sharedpreferences.getString(mEmailView.getText().toString()+"email","");
            String vPass=sharedpreferences.getString(mEmailView.getText().toString()+"password","");
            if(!mEmailView.getText().toString().equals(vEmail)||!mPasswordView.getText().toString().equals(vPass)||vPass.equals("")||vEmail.equals("")){
                Toast.makeText(getApplicationContext(),"Invalid UserName or Password Can't Login",Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(LoginActivity.this, UserHome.class);
                intent.putExtra("emailFor",mEmailView.getText().toString());
                startActivity(intent);
                finish();
            }
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

}

