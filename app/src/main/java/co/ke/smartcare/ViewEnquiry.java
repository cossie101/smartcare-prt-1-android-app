package co.ke.smartcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import co.ke.smartcare.dataclasses.Status;

public class ViewEnquiry extends AppCompatActivity implements EnquiryStatusFragment.OnListFragmentInteractionListener {

    private TextView title, description, enquiryDate;
    private int enquiryId;
    private final String TAG = ViewEnquiries.class.getName();

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enquiry);
        getSupportActionBar().hide();

        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        enquiryDate =  findViewById(R.id.enquiry_date);


        Intent intent = getIntent();

        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        enquiryDate.setText(intent.getStringExtra("date"));

        enquiryId = intent.getIntExtra("Id", 0);
        startSalesFragment();

    }


    public void startSalesFragment(){
        Log.i(TAG, "Calling fragment... ");
        EnquiryStatusFragment fragment = EnquiryStatusFragment.newInstance(1, enquiryId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.statusFragment, fragment, "Show enquiry status ... ");
        transaction.commit();
        Log.e(TAG,"Error");
    }

    @Override
    public void onListFragmentInteraction( Status enquiry ) {
        /*Intent intent = new Intent(ViewEnquiries.this, ViewEnquiry.class);
        intent.putExtra("title", enquiry.getEnquiryTitle());
        intent.putExtra("description", enquiry.getEnquiry());
        intent.putExtra("date", enquiry.getEnquiryDate());
        intent.putExtra("enquiryId", enquiry.getEnquiryID());
        startActivity(intent);*/
    }
}
