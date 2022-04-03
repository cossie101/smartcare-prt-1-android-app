package co.ke.smartcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.R.layout;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import co.ke.smartcare.apiutilities.ApiService;
import co.ke.smartcare.apiutilities.ApiUtility;
import co.ke.smartcare.dataclasses.Doctor;
import co.ke.smartcare.dataclasses.UploadInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {

    private TextView title, appointment;
    private DatePicker appointmentDate;
    private ApiService apiService;
    private Spinner doctor;
    private SharedPreferences sharedPreferences;
    private Button save;
    private int patient, month, year, day;
    private String period;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        getSupportActionBar().hide();

        title = findViewById(R.id.title);
        appointment = findViewById(R.id.appointment);
        appointmentDate = findViewById(R.id.appointment_date);
        save = findViewById(R.id.save_appointment);

        //fetchDoctors();

        sharedPreferences = this.getSharedPreferences("smartCareData", 0);
        apiService = ApiUtility.getAPIService();

        patient = sharedPreferences.getInt("userId", 0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                month = appointmentDate.getMonth();
                month = month + 1;
                day = appointmentDate.getDayOfMonth();
                year = appointmentDate.getYear();
                String mn = "";

                if( month <= 9){
                    mn = "0"+month;
                }else{
                    mn = String.valueOf(month);
                }

                period = year+"-"+mn+"-"+day;

                Toast.makeText(BookingActivity.this, "Period: "+period, Toast.LENGTH_SHORT).show();
                saveBooking( patient, title.getText().toString(), appointment.getText().toString(), period);
            }
        });


    }

    public void saveBooking( int patient, String title, String appointment, String appointmentDate){

        apiService.saveAppointment(patient, title, appointment, appointmentDate).enqueue(new Callback<UploadInfo>() {
            @Override
            public void onResponse( Call<UploadInfo> call, Response<UploadInfo> response ) {
                UploadInfo uploadInfo = response.body();

                if( uploadInfo != null ){
                    if( uploadInfo.getStatus().equals("success")){
                        replyMessage(uploadInfo.getMessage());
                    }else{

                        Toast.makeText(BookingActivity.this, uploadInfo.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure( Call<UploadInfo> call, Throwable t ) {

                Toast.makeText(BookingActivity.this, "Error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void fetchDoctors(){

        apiService.fetchDocs().enqueue(new Callback<List<Doctor>>() {
            @Override
            public void onResponse( Call<List<Doctor>> call, Response<List<Doctor>> response ) {
                List<Doctor> doctors = new ArrayList<>();
                List<String> docs = new ArrayList<>();

                if( response.body() != null ){

                    doctors.addAll(response.body());

                    for( int i = 0; i <= doctors.size(); i++ ){
                        docs.add(doctors.get(i).getName());
                    }

                    ArrayAdapter<String> doctorsSpinner = new ArrayAdapter<>(getApplicationContext(), layout.simple_spinner_item, docs);
                    doctorsSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    doctor.setAdapter(doctorsSpinner);
                }else{

                    Toast.makeText(BookingActivity.this, "0 doctors have been retrieved. \nKindly, contact customer care", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure( Call<List<Doctor>> call, Throwable t ) {
                Toast.makeText(BookingActivity.this, "Error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public  void replyMessage(String message){
        new AlertDialog.Builder(this)
                .setTitle("Appointment Info")
                .setMessage(message)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent load = new Intent(BookingActivity.this, HomePageActivity.class);
                        startActivity(load);
                    }
                })
                .show();

    }
}
