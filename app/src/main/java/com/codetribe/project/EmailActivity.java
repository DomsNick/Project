package com.codetribe.project;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailActivity extends AppCompatActivity {

    private Button sentEmail;
    private EditText emailto,emailcc,emailmessage;
    String[] TO={"kdmachabs@gmail.com"};
    String[] CC={"trizzKonnect123@yahoo.co.za"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        sentEmail=(Button)findViewById(R.id.btnsentEmail);
        emailto=(EditText)findViewById(R.id.edtemailTo);
        emailcc=(EditText)findViewById(R.id.edtemailcc);
        emailmessage=(EditText)findViewById(R.id.edtemailmessage);


        emailto.setText(TO[0]);
        emailto.setEnabled(false);
        emailcc.setText(CC[0]);
        emailcc.setEnabled(false);


        sentEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailSent();

            }
        });


    }

    protected void EmailSent()
    {


        Intent emailintent=new Intent(Intent.ACTION_SEND);
        emailintent.setData(Uri.parse("khutso:"));
        emailintent.setType("text/plain");

        emailintent.putExtra(Intent.EXTRA_EMAIL,TO);
        emailintent.putExtra(Intent.EXTRA_CC,CC);
        emailintent.putExtra(Intent.EXTRA_SUBJECT,"New Event Requests");
        emailintent.putExtra(Intent.EXTRA_TEXT,emailmessage.getText().toString());


        try{

            startActivity(Intent.createChooser(emailintent,"Sent email..."));
            finish();

            Toast.makeText(EmailActivity.this,"Continue..",Toast.LENGTH_SHORT).show();

        }catch (android.content.ActivityNotFoundException ex)
        {

            Toast.makeText(EmailActivity.this,"No Client installed",Toast.LENGTH_SHORT).show();
        }


    }
}
