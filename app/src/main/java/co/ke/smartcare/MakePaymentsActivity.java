package co.ke.smartcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import co.ke.smartcare.apiutilities.ApiService;
import co.ke.smartcare.apiutilities.ApiUtility;
import co.ke.smartcare.apiutilities.SessionManager;
import co.ke.smartcare.dataclasses.UploadInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakePaymentsActivity extends AppCompatActivity {
    private Button pay;
    private EditText amount;
    private TextView account;
    private ApiService apiService;
    private SharedPreferences sharedPreferences;
    private int userId;
    private double amt;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_payments);
        getSupportActionBar().hide();

        pay = findViewById(R.id.pay);
        amount = findViewById(R.id.amount);
        account = findViewById(R.id.account);

        apiService = ApiUtility.getAPIService();
        sharedPreferences = this.getSharedPreferences("smartCareData", 0);

        userId = sharedPreferences.getInt("userId", 0);
        account.setText(sharedPreferences.getString("account", ""));


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                amt = Double.valueOf(amount.getText().toString());
                sendPayment();
                Toast.makeText(MakePaymentsActivity.this, "Connecting to the server ... ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendPayment(){

        apiService.makePayment( userId, amt ).enqueue(new Callback<UploadInfo>() {
            @Override
            public void onResponse( Call<UploadInfo> call, Response<UploadInfo> response ) {
                
                UploadInfo uploadInfo = response.body();
                
                if( uploadInfo != null ){
                    if( uploadInfo.getStatus().equals("success") ){

                        replyMessage(uploadInfo.getMessage());
                    }else{

                        Toast.makeText(MakePaymentsActivity.this, uploadInfo.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(MakePaymentsActivity.this, "The server did not respond ... ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure( Call<UploadInfo> call, Throwable t ) {

                Toast.makeText(MakePaymentsActivity.this, "Error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public  void replyMessage(String message){
        new AlertDialog.Builder(this)
                .setTitle("Payment Upload Info")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent load = new Intent(getApplicationContext(), HomePageActivity.class);
                        startActivity(load);
                    }
                })
                .show();

    }
}
