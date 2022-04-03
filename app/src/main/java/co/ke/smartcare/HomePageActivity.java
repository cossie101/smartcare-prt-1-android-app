package co.ke.smartcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomePageActivity extends AppCompatActivity {
    CardView make_payment, view_payment, view_enquiry, enquiries, booking, appointments;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().hide();

        make_payment = findViewById(R.id.c3);
        view_payment = findViewById(R.id.c4);
        view_enquiry = findViewById(R.id.c1);
        enquiries = findViewById(R.id.c2);
        booking = findViewById(R.id.c6);
        appointments =  findViewById(R.id.c7);

        //Creating enquiry Page
        view_enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent(HomePageActivity.this, EnquiryActivity.class);
                startActivity(intent);
            }
        });

        //Viewing enquiries page
        enquiries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent(HomePageActivity.this, ViewEnquiries.class);
                startActivity(intent);
            }
        });

        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent(HomePageActivity.this, BookingActivity.class);
                startActivity(intent);
            }
        });

        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent(HomePageActivity.this, AppointmentsActivity.class);
                startActivity(intent);
            }
        });

        make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent(HomePageActivity.this, MakePaymentsActivity.class);
                startActivity(intent);
            }
        });

        view_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent(HomePageActivity.this, PaymentsActivity.class);
                startActivity(intent);
            }
        });
    }
}
