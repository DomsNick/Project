package com.codetribe.project;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private Button signin;
    private EditText comfirm,mEmail,mPassword;
    private FirebaseAuth mfirebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin=(Button)findViewById(R.id.btnSign);
        comfirm=(EditText)findViewById(R.id.edtConfirmPass);
        mEmail=(EditText)findViewById(R.id.edtEmail);
        mPassword=(EditText)findViewById(R.id.edtPassword);

        comfirm.setVisibility(View.INVISIBLE);

        mfirebaseAuth= FirebaseAuth.getInstance();


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if(mEmail.getText().toString().equals(""))
                {


                    mEmail.setError((getString(R.string.error_field_required)));

                }else if(mPassword.getText().toString().equals(""))
                {
                    mEmail.setError(null);
                    mPassword.setError((getString(R.string.error_field_required)));

                } else {


                    final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait....", "Processing....", true);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"TIME_OUT",Toast.LENGTH_SHORT).show();

                        }
                    },10000);


                    (mfirebaseAuth.signInWithEmailAndPassword(mEmail.getText().toString(), mPassword.getText().toString()))
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss();
                                    if (task.isSuccessful()) {


                                        if (mEmail.getText().toString().equals("kdmachabs@gmail.com")) {

                                            Intent intent = new Intent(LoginActivity.this, AdminActivitiy.class);
                                            startActivity(intent);
                                            Toast.makeText(LoginActivity.this, "Welcome Admin", Toast.LENGTH_SHORT).show();
                                        } else {


                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            startActivity(intent);
                                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {


                                        Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }


                                }
                            });


                }



            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.loginmenu,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id=item.getItemId();
        if(id==R.id.menusignup)
        {
            Intent i = new Intent(LoginActivity.this,SignUp.class);
            startActivity(i);
            return true;

        }else if(id==R.id.menuExit)
        {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //-------------------------------->validation

    private boolean isEmailValid(String email)
    {
        return email.contains("@");
    }

    private boolean isPasswodValid(String password)
    {

        return password.length()>4;

    }




}
