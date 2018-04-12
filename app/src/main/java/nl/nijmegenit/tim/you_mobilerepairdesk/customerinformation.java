package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class customerinformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerinformation);

        //Initieren data uit activity
        final TextView txtbName = findViewById(R.id.txtbName);
        final TextView txtbPhone = findViewById(R.id.txtbPhone);
        final TextView txtbEmail = findViewById(R.id.txtbEmail);
        final TextView txtbHouseNumber = findViewById(R.id.txtbHouseNumber);
        final TextView txtbPostcode = findViewById(R.id.txtbPostcode);
        final Button btnNext = findViewById(R.id.btnNext);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customer");
        myRef.setValue("Dit is de eerste klant");


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d("customer", "Value is: " + value);

                txtbName.setText(value);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("customer", "Failed to read value.", error.toException());
            }
        });

//        //Opvangen intent en intentdata
//        Intent intent = getIntent();
//        final Phone phone = intent.getParcelableExtra("SendPhoneInfo");

        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String name = null;
                String email = null;
                String housenumber = null;
                String postcode = null;
                int phonenumber = 0;
                try {
                    name = txtbName.getText().toString();
                    email = txtbEmail.getText().toString();
                    housenumber = txtbHouseNumber.getText().toString();
                    postcode = txtbPostcode.getText().toString();
                    String value = txtbPhone.getText().toString();
                    phonenumber = Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }


                final Customer customer = new Customer(name, postcode, housenumber, phonenumber, email);


                Intent intent = new Intent(getBaseContext(), activity_customerphones.class);
                intent.putExtra("Customer", customer);

                startActivity(intent);
            }
        });


    }

}
