package co.ke.smartcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import co.ke.smartcare.dataclasses.Status;

public class PaymentsActivity extends AppCompatActivity implements PaymentFragment.OnListFragmentInteractionListener {
    private SharedPreferences sharedPreferences;
    private int userId;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);
        getSupportActionBar().hide();

        sharedPreferences = this.getSharedPreferences("smartCareData", 0);
        userId = sharedPreferences.getInt("userId", 0);

        startPaymentFragment();
    }


    public void startPaymentFragment(){
        PaymentFragment fragment = PaymentFragment.newInstance(1, userId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.paymentFragment, fragment, "Show payments ...");
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction( Status status ) {
    }
}
