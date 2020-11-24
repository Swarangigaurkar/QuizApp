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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity implements View.OnClickListener
{

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(this);
        buttonRegister=(Button) findViewById(R.id.ButtonRegister1);
        editTextEmail=(EditText) findViewById(R.id.editTextEmail1);
        editTextPassword=(EditText) findViewById(R.id.editTextPassword1);
        textViewSignin=(TextView) findViewById(R.id.textviewSignin);
        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registeruser()
    {
     final   String email=editTextEmail.getText().toString().trim();
        String pass=editTextPassword.getText().toString().trim();

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
        progressDialog.setMessage("registering user...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Register.this,"Registered Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Register.this,MainActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            finish();
                        }
                        else
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Register.this,"Failed to Register",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    @Override
    public void onClick(View view)
    {
        if(view==buttonRegister)
        {
            registeruser();
        }
        if (view==textViewSignin)
        {
            //will open login activity
            startActivity(new Intent(this,LoginActivity.class));
        }
    }
}
