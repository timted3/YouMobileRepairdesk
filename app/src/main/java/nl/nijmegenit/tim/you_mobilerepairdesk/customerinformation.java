package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

        //Indexing objects from the activity.
        final TextView txtbName = findViewById(R.id.txtbName);
        final TextView txtbPhone = findViewById(R.id.txtbPhone);
        final TextView txtbEmail = findViewById(R.id.txtbEmail);
        final TextView txtbHouseNumber = findViewById(R.id.txtbHouseNumber);
        final TextView txtbPostcode = findViewById(R.id.txtbPostcode);
        final Button btnNext = findViewById(R.id.btnNext);
        final ListView customerView = findViewById(R.id.listCustomers);

        final String items[] = {"Tim Jansen", "Thomas van de Kreeke", "Marnix van Wijland", "Robin van Renselaar"};

        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);



        txtbPostcode.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

                customerView.setAdapter(itemsAdapter);
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });







        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("customer");


        ///Button to get to the next screen
        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Making variables
                String name = null;
                String email = null;
                String housenumber = null;
                String postcode = null;


                //Filling variables
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

                //Making customer based on data from activity.
                final Customer customer = new Customer(name, postcode, housenumber, phonenumber, email);

                //Defining the next screen intent
                Intent intent = new Intent(getBaseContext(), activity_customerphones.class);

                //Giving customer to the next screen
                intent.putExtra("Customer", customer);

                startActivity(intent);
            }
        });


    }

}
