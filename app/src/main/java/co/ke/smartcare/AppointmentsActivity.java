package co.ke.smartcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;

import co.ke.smartcare.dataclasses.Appointment;
import co.ke.smartcare.dataclasses.Status;

public class AppointmentsActivity extends AppCompatActivity implements AppointmentFragment.OnListFragmentInteractionListener {
    private int userId;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        getSupportActionBar().hide();

        sharedPreferences = this.getSharedPreferences("smartCareData", 0);
        userId = sharedPreferences.getInt("userId", 0);

        startAppointmentsFragment();
    }

    public void startAppointmentsFragment(){
        AppointmentFragment fragment = AppointmentFragment.newInstance(1, userId);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.appointmentFragment, fragment, "Show appointments ...");
        transaction.commit();
    }

    @Override
    public void onListFragmentInteraction( Appointment appointment ) {
    }
}
