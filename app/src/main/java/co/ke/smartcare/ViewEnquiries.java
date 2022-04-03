package co.ke.smartcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import co.ke.smartcare.dataclasses.Enquiry;

public class ViewEnquiries extends AppCompatActivity implements EnquiriesFragment.OnListFragmentInteractionListener {

    private final String TAG = ViewEnquiries.class.getName();
    private int userId;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_enquiries);
        getSupportActionBar().hide();

        sharedPreferences = this.getSharedPreferences("smartCareData", 0);
        userId = sharedPreferences.getInt("userId", 0);

        startSalesFragment();
    }


    public void startSalesFragment(){
        Log.i(TAG, "Calling fragment... ");
        EnquiriesFragment fragment = EnquiriesFragment.newInstance(1, userId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.enquiryFragment, fragment, "Show Enquiries");
        transaction.commit();
        Log.e(TAG,"Error");
    }

    @Override
    public void onListFragmentInteraction( Enquiry enquiry ) {
        Intent intent = new Intent(ViewEnquiries.this, ViewEnquiry.class);
        intent.putExtra("title", enquiry.getEnquiryTitle());
        intent.putExtra("description", enquiry.getEnquiry());
        intent.putExtra("date", enquiry.getEnquiryDate());
        intent.putExtra("Id", enquiry.getEnquiryID());

        Log.i("Load enquiry", String.valueOf(enquiry.getEnquiryID()));
        startActivity(intent);
    }
}
