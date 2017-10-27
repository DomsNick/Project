package com.codetribe.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.security.Provider;

public class SignUp extends AppCompatActivity {

    private EditText edtemail,edtpass,edtcomfirm;
    private Button register;
    private FirebaseAuth mFirebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtemail=(EditText)findViewById(R.id.edtEmail);
        edtpass=(EditText)findViewById(R.id.edtPassword);
        edtcomfirm=(EditText)findViewById(R.id.edtConfirmPass);
        register=(Button)findViewById(R.id.btnSign);


        register.setText("Sign Up");
        mFirebaseAuth=FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = ProgressDialog.show(SignUp.this,"Please wait....","Processing....",true);

                if(edtpass.getText().toString().equals(edtcomfirm.getText().toString()))
                {

                    (mFirebaseAuth.createUserWithEmailAndPassword(edtemail.getText().toString(),edtpass.getText().toString())).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            progressDialog.dismiss();
                            if(task.isSuccessful())
                            {

                                Toast.makeText(SignUp.this,"Signing up complete",Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(SignUp.this,LoginActivity.class);
                                startActivity(intent);

                            }else {

                                Toast.makeText(SignUp.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }else {

                    progressDialog.dismiss();
                    Toast.makeText(SignUp.this,"Password dont match!!!",Toast.LENGTH_SHORT).show();

                }




            }
        });





    }
}
