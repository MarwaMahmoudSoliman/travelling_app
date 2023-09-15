package com.example.sec_project;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Button upload;
    private Button uploadImage;
    EditText uploadCaption;
    EditText uploaddes;
    EditText uploadprice;
    EditText pos_;

    ProgressBar progressBar;
    private Uri imageUri;
    private Spinner spinnerTextSize;
    String valueFromSpinner;
    String pos;
    final  private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Images");
    final private StorageReference storageReference = FirebaseStorage.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        spinnerTextSize = findViewById(R.id.spinnerTextSize);
        spinnerTextSize.setOnItemSelectedListener(this);
        String[] textSizes = getResources().getStringArray(R.array.font_sizes);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize.setAdapter(adapter);
        upload = findViewById(R.id.upButton);
        uploadImage = findViewById(R.id.uploadImage);
        uploadCaption = findViewById(R.id.uploadCaption);
        uploaddes = findViewById(R.id.des);
        uploadprice = findViewById(R.id.price);
        progressBar = findViewById(R.id.progressBar);
        pos_=findViewById(R.id.pos);
        progressBar.setVisibility(View.INVISIBLE);
        uploaddes.setHorizontallyScrolling(false);
        uploaddes.setMaxLines(Integer.MAX_VALUE);
        uploaddes.setMinLines(1);
        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
if(result.getResultCode()== Activity.RESULT_OK){
Intent data=result.getData();
imageUri=data.getData();
} else {
    Toast.makeText(UploadActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
}
                    }


                });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photo=new Intent();
                photo.setAction(Intent.ACTION_GET_CONTENT);
                photo.setType("image/*");
                activityResultLauncher.launch(photo);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (imageUri != null){
                    uploadToFirebase(imageUri);
                } else  {
                    Toast.makeText(UploadActivity.this, "Please select image", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
        private void uploadToFirebase(Uri uri) {
            String caption = uploadCaption.getText().toString();
            String des = uploaddes.getText().toString();
            String price = uploadprice.getText().toString();
            pos=pos_.getText().toString();
            String catog=valueFromSpinner;
            final StorageReference imageReference = storageReference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
            imageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {


                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {

                        @Override
                        public void onSuccess(Uri uri) {
                            DataClass dataClass = new DataClass(uri.toString(), caption, des, price,catog,pos);
                            String key = databaseReference.push().getKey();
                            databaseReference.child(key).setValue(dataClass);
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(UploadActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(UploadActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
                }

            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(UploadActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            });
        }

    private String getFileExtension(Uri fileUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinnerTextSize) {
             valueFromSpinner = parent.getItemAtPosition(position).toString();
            Toast.makeText(UploadActivity.this, valueFromSpinner, Toast.LENGTH_SHORT).show();


        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
