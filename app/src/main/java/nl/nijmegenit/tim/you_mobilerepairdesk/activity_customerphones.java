package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class activity_customerphones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerphones);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final Customer customer = intent.getParcelableExtra("Customer");

        final String items[] = {"Telefoon 1", "Telefoon 2", "Telefoon 3", "Telefoon 4"};
        final ArrayList<Phone> phones = new ArrayList<Phone>();

        final ListView listCustomerPhones = findViewById(R.id.listPhones);

        final ArrayAdapter<Phone> itemsAdapter =
                new ArrayAdapter<Phone>(this, android.R.layout.simple_list_item_1, phones);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("phones");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {
                    String brand = (String) messageSnapshot.child("brand").getValue();
                    String model = (String) messageSnapshot.child("model").getValue();
                    int imei = messageSnapshot.child("imei").getValue(Integer.class);
                    String serialnumber = (String) messageSnapshot.child("serialnumber").getValue();
                    Phone phone = new Phone(imei, serialnumber, brand, model);
                    phones.add(phone);
                }
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("customer", "Failed to read value.", error.toException());
            }
        });



        listCustomerPhones.setAdapter(itemsAdapter);

        listCustomerPhones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(items[position]);

                Intent intent = new Intent(getBaseContext(), phoneinformation.class);

                intent.putExtra("Phone", phones.get(position));
                intent.putExtra("Customer", customer);

                startActivity(intent);

            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), phoneinformation.class);

                final Customer customer = intent.getParcelableExtra("Customer");
                intent.putExtra("Customer", customer);

                startActivity(intent);
            }
        });
    }

}
