package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


public class phoneinformation extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairdesk);

        //creating intent
        Intent intent = getIntent();

        //Getting data recieved from previous activity
        final Customer customer = intent.getParcelableExtra("Customer");
        final Phone phone = intent.getParcelableExtra("Phone");


        //Indexing objects from the activity.
        final CheckBox chkbCamera = findViewById(R.id.chkCamera);
        final CheckBox chkHomeButton = findViewById(R.id.chkHomeButton);
        final CheckBox chkMicrophone = findViewById(R.id.chkMicrophone);
        final CheckBox chkNetwork = findViewById(R.id.chkNetwork);
        final CheckBox chkPowerbutton = findViewById(R.id.chkPowerbutton);
        final CheckBox chkSpeakers = findViewById(R.id.chkSpeakers);
        final CheckBox chkVolumeButtons = findViewById(R.id.chkVolumeButtons);
        final CheckBox chkChargeConnection = findViewById(R.id.chkChargeConnector);
        final TextView txtbIMEI = findViewById(R.id.txtbIMEI);
        final TextView txtbSerialNumber = findViewById(R.id.txtbSerialNumber);
        final Button btnNext = findViewById(R.id.btnNext);
        final Button btnCamera = findViewById(R.id.btnCamera);
        final Spinner s = findViewById(R.id.drdBrand);
        final Spinner b = findViewById(R.id.drdModel);

        //Carma button
        btnCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Starting camera intent
                dispatchTakePictureIntent();

            }
        });

        //Trying to convert values to integers
        try {
            int imei = phone.getImei();
            txtbIMEI.setText(Integer.toString(imei));
            txtbSerialNumber.setText(phone.getSerialnumber());
        } catch (Exception e) {


        }

        //Adding brands to array
        String[] arrayBrandSpinner = new String[]{
                "Samsung", "Apple", "LG", "Nokia", "OnePlus"
        };


        //Converting strings to adapter and adding to spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayBrandSpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);

        //Adding models to array
        String[] arrayModelSpinner = new String[]{
                "iPhone SE", "iPhone 5S", "Nexus 5", "Galaxy S7 Edge", "5T", "iPhone 6S"
        };

        //Setting postition based on intent values
        try {
            if (phone.getBrand() != null) {
                int position = adapter.getPosition(phone.getBrand());
                s.setSelection(position);
            }
        } catch (NullPointerException e) {

        }

        //Converting strings to adapter and adding to spinner
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayModelSpinner);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        b.setAdapter(modelAdapter);

        //Setting postition based on intent values
        try {

            if (phone.getModel() != null) {
                int position = modelAdapter.getPosition(phone.getModel());
                b.setSelection(position);
            }
        } catch (NullPointerException e) {

        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // Code here executes on main thread after user presses button

                //Checking state of the checkboxes
                boolean cameraWorks = chkbCamera.isChecked();
                boolean homebuttonWorks = chkHomeButton.isChecked();
                boolean microphoneWorks = chkMicrophone.isChecked();
                boolean networkWorks = chkNetwork.isChecked();
                boolean powerbuttonWorks = chkPowerbutton.isChecked();
                boolean speakersWorks = chkSpeakers.isChecked();
                boolean volumebuttonWorks = chkVolumeButtons.isChecked();
                boolean chargeconnectionWorks = chkChargeConnection.isChecked();

                //Getting other values
                String serialnumber = txtbSerialNumber.toString();
                String value = txtbIMEI.getText().toString();
                int imei = Integer.parseInt(value);

                //Get brand and model from spinners
                String brand = s.getSelectedItem().toString();
                String model = b.getSelectedItem().toString();


                //MAke phones
                Phone phone = new Phone(imei, serialnumber, brand, model, cameraWorks, homebuttonWorks, microphoneWorks, networkWorks, powerbuttonWorks, speakersWorks, volumebuttonWorks, chargeconnectionWorks);

                //Create and set intent
                Intent intent = new Intent(getBaseContext(), repairinformation.class);


                //Give information to next activity
                intent.putExtra("Phone", (Parcelable) phone);
                intent.putExtra("Customer", customer);

                //Start activity
                startActivity(intent);


            }
        });


    }

    //Camera needs
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            //using the picture you recieved from the camera. For example, sending it to firebase.


            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);


        }

    }
}

