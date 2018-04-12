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

    private boolean checkCameraHardware(Context context) {
        if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairdesk);


        Intent intent = getIntent();
        final Customer customer = intent.getParcelableExtra("Customer");
        final Phone phone = intent.getParcelableExtra("Phone");


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


        btnCamera.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                dispatchTakePictureIntent();

            }
        });




        try {
            int imei = phone.getImei();
            txtbIMEI.setText(Integer.toString(imei));
            txtbSerialNumber.setText(phone.getSerialnumber());
        }
        catch(Exception e) {


        }





        String[] arrayBrandSpinner = new String[]{
                "Samsung", "Apple", "LG", "Nokia", "OnePlus"
        };
        final Spinner s = findViewById(R.id.drdBrand);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayBrandSpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        s.setAdapter(adapter);

        String[] arrayModelSpinner = new String[]{
                "iPhone SE", "iPhone 5S", "Nexus 5", "Galaxy S7 Edge", "5T", "iPhone 6S"
        };
        try {
            if (phone.getBrand() != null) {
                int position = adapter.getPosition(phone.getBrand());
                s.setSelection(position);
            }
        }
        catch(NullPointerException e){

        }

        final Spinner b = findViewById(R.id.drdModel);
        ArrayAdapter<String> modelAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arrayModelSpinner);
        modelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);

        b.setAdapter(modelAdapter);

        try{

        if (phone.getModel() != null) {
            int position = modelAdapter.getPosition(phone.getModel());
            b.setSelection(position);
        }
        }
        catch(NullPointerException e){

        }


        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                // Code here executes on main thread after user presses button
                boolean cameraWorks = chkbCamera.isChecked();
                boolean homebuttonWorks = chkHomeButton.isChecked();
                boolean microphoneWorks = chkMicrophone.isChecked();
                boolean networkWorks = chkNetwork.isChecked();
                boolean powerbuttonWorks = chkPowerbutton.isChecked();
                boolean speakersWorks = chkSpeakers.isChecked();
                boolean volumebuttonWorks = chkVolumeButtons.isChecked();
                boolean chargeconnectionWorks = chkChargeConnection.isChecked();

                String serialnumber = txtbSerialNumber.toString();

                String value = txtbIMEI.getText().toString();
                int imei = Integer.parseInt(value);


                String brand = s.getSelectedItem().toString();
                String model = b.getSelectedItem().toString();

                Phone phone = new Phone(imei, serialnumber, brand, model, cameraWorks, homebuttonWorks, microphoneWorks, networkWorks, powerbuttonWorks, speakersWorks, volumebuttonWorks, chargeconnectionWorks);

                Intent intent = new Intent(getBaseContext(), repairinformation.class);

                intent.putExtra("Phone", (Parcelable) phone);


                intent.putExtra("Customer", customer);


                startActivity(intent);


            }
        });


    }

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
            //mImageView.setImageBitmap(imageBitmap);


            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);



            }

        }
    }

