package nl.nijmegenit.tim.you_mobilerepairdesk;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class repairinformation extends AppCompatActivity {

    int ticket_id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repairinformation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final Customer customer = intent.getParcelableExtra("Customer");
        final Phone phone = intent.getParcelableExtra("Phone");

        final String items[] = {"Origineel Glas/Touchscreen + LCD display", "Refurbished", "Voorcamera", "Homeknop", "Laadconnector + Microfoon", "Batterij"};
        final int itemprice[] = {10000, 7500, 3495, 5400, 4500, 3000, 2995};


        final ListView repairlist = findViewById(R.id.listRepairs);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);


        repairlist.setAdapter(itemsAdapter);

        final Button btnNext = findViewById(R.id.btnNext);

        final ArrayList<Repair> repairs = new ArrayList<Repair>();


        repairlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Repair repair = new Repair(items[position], itemprice[position]);

                repairs.add(repair);


            }
        });


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("tickets");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                int key = 0;


                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

                    String getKey = messageSnapshot.getKey();

                    int hoi = 0;

                    try {
                        key = Integer.parseInt(getKey);
                    } catch (NumberFormatException nfe){}

                    if (key > ticket_id) {
                        ticket_id = key;

                    }


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ticket_id = ticket_id + 1;


                Ticket ticket = new Ticket(customer, phone, repairs, "New", ticket_id, 1);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("tickets");

                myRef.child(String.valueOf(ticket_id)).setValue(ticket);


                //final ArrayList<Ticket> tickets = new ArrayList<Ticket>();
                //tickets.add(ticket);

                //myRef.setValue(tickets);


                // Map<String, Ticket> tickets = new HashMap<>();
                // tickets.put(ticket_id, ticket);


                // myRef.setValue(tickets);
                //myRef.updateChildren((Map<String, Object>) ticket);


                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);


            }

        });


    }


}

