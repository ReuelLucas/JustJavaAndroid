package com.example.android.justjava;





import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity=0;
    int total;

    public Cafezinho c = new Cafezinho();
    DatabaseReference dataBase = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myRef = dataBase.child("Quantidade");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected  void onStart() {
    super.onStart();
    myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
        int qtd = dataSnapshot.getValue(int.class);
            TextView quantityFinalTextView = (TextView) findViewById(R.id.quantity_Atual);
            quantityFinalTextView.setText("" + qtd);
            total=qtd;

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    }

    public void increment(View view){
        quantity ++;
        display(quantity);

    }

    public void decrement(View view){
        if(quantity!=0)
        quantity --;
       display(quantity);

    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        displayPrice(quantity*5);
        myRef.setValue(quantity+total);
        Toast.makeText( MainActivity.this, (quantity*5) + "Reais", Toast.LENGTH_LONG).show();
        quantity=0;
        display(quantity);
        displayPrice(0);


    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));

    }


}