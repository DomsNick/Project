package com.codetribe.project;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.util.Calendar;
import android.icu.util.TimeZone;
import android.icu.util.ULocale;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CalendarView;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

public class AdminActivitiy extends AppCompatActivity {


    private static final int PICK_IMAGE_REQUEST = 234;

    private DatePicker datePicker1;
    private EditText eventDate,eventname,eventInfo,eventAddress,eventLong,eventLati;
    private ImageView eventImage;
    private Button addEvent,viewList;

    //uri to store file
    private Uri filePath;

    //firebase objects
    private StorageReference storageReference;
    private DatabaseReference mDatabase;
    private FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activitiy);


        mfirebaseAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("zeewavaa").child("Events");

        datePicker1=(DatePicker)findViewById(R.id.datePicker);
        eventImage=(ImageView)findViewById(R.id.imguploadevent);

        addEvent=(Button)findViewById(R.id.btnaddevent);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Event event = new Event();

                event.setEventName(eventname.getText().toString());
                event.setEventinformation(eventInfo.getText().toString());
                event.setEventdate(eventDate.getText().toString());
                event.setLogitude(Double.parseDouble(eventLong.getText().toString()));
                event.setLatitude(Double.parseDouble(eventLati.getText().toString()));

                //uploadFile();

                mDatabase.push().setValue(event);


            }
        });




        eventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFileChooser();
            }
        });

        eventname=(EditText)findViewById(R.id.edtEventName);
        eventDate=(EditText) findViewById(R.id.edtdate);
        eventInfo=(EditText) findViewById(R.id.edtEventinfo);
        eventAddress=(EditText) findViewById(R.id.edtAddress);
        eventLong=(EditText) findViewById(R.id.edtlogitude);
        eventLati=(EditText) findViewById(R.id.edtlatitude);


     //  Calendar today= Calendar.getInstance();


        datePicker1.init(Calendar.DAY_OF_MONTH,
                Calendar.MONTH,
               Calendar.YEAR, new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int selectedyear, int selectedmonthOfYear, int selecteddayOfMonth) {

                        eventDate.setText(selecteddayOfMonth+"/"+(selectedmonthOfYear+1)+"/"+selectedyear);

                    }
                });






    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                eventImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        //checking if file is available
        if (filePath != null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            StorageReference sRef = storageReference.child(Constants.STORAGE_PATH_UPLOADS + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            //dismissing the progress dialog
                            progressDialog.dismiss();

                            //displaying success toast
                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();

                            //creating the upload object to store uploaded image details
                             EventImages eventImages = new EventImages(eventname.getText().toString().trim(), taskSnapshot.getDownloadUrl().toString());




                            //adding an upload to firebase database
                            String eventId = mDatabase.push().getKey();
                            mDatabase.child(eventId).setValue(eventImages);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            //display an error if no file is selected


        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id=item.getItemId();
        if(id==R.id.action_settings)
        {
            Intent i = new Intent(AdminActivitiy.this,LoginActivity.class);
            startActivity(i);
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }



}
