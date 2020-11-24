package com.example.swarangigaurkar.quizzup;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonsignin;
    private EditText editTextemail;
    private EditText editTextpassword;
    private TextView textViewsignup;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth=FirebaseAuth.getInstance();

        FirebaseUser usr=FirebaseAuth.getInstance().getCurrentUser();
        if(usr!=null)
        {
            String email=usr.getEmail();
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("email",email);
            intent.putExtra("pos",1);
            startActivity(intent);
            finish();
        }


        progressDialog=new ProgressDialog(this);
        buttonsignin=(Button) findViewById(R.id.ButtonSignin);
        editTextemail=(EditText) findViewById(R.id.editTextEmail);
        editTextpassword=(EditText) findViewById(R.id.editTextPassword);
        textViewsignup=(TextView) findViewById(R.id.textviewSignup);

        buttonsignin.setOnClickListener(this);
        textViewsignup.setOnClickListener(this);
    }

    private void userLogin()
    {

    final    String email=editTextemail.getText().toString().trim();
        String pass=editTextpassword.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //email is empty
            Toast.makeText(this,"please enter the email",Toast.LENGTH_SHORT).show();
            //stop the function
            return;
        }
        if(TextUtils.isEmpty(pass))
        {
            //password is empty
            Toast.makeText(this,"please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        //if validations are fullfilled
        progressDialog.setMessage("signing in please wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            // start profile activity

                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            finish();

                        }
                        else
                        {
                            progressDialog.hide();
                            Toast.makeText(LoginActivity.this,"Failed to Sign In",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v)
    {
        if(v==buttonsignin)
        {
            //sign in user
            userLogin();
        }
        if(v==textViewsignup)
        {
            //go to register
            Intent intent=new Intent(LoginActivity.this,Register.class);
            startActivity(intent);
            finish();
        }
    }
}
