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
import co.ke.smartcare.dataclasses.UploadInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscriptionActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    String mobile;
    private TextView mob;
    private EditText amount;
    private Button save;
    private ApiService apiService;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        getSupportActionBar().hide();

        sharedPreferences = this.getSharedPreferences("smartCareData", 0);
         Intent intent = getIntent();
         mobile = intent.getStringExtra("phone");
         final int user = intent.getIntExtra("user", 0);

        apiService = ApiUtility.getAPIService();

        save = findViewById(R.id.pay);
        amount = findViewById(R.id.amount);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Double am = Double.parseDouble(amount.getText().toString());
                subscribe(user, am );
                Toast.makeText(SubscriptionActivity.this, "Connecting to the server... "+user, Toast.LENGTH_SHORT).show();
            }
        });




    }


    public void subscribe(int member, double amount){
        apiService.doSubscribe(member, amount).enqueue(new Callback<UploadInfo>() {
            @Override
            public void onResponse( Call<UploadInfo> call, Response<UploadInfo> response ) {
                UploadInfo uploadInfo = response.body();

                if( uploadInfo != null){
                    if(uploadInfo.getStatus().equals("success")){
                        replyMessage( uploadInfo.getMessage() );
                    }
                }else{
                    Toast.makeText(SubscriptionActivity.this, "Server did not respond ...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure( Call<UploadInfo> call, Throwable t ) {
                Toast.makeText(SubscriptionActivity.this, "Error: "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public  void replyMessage(String message){
        new AlertDialog.Builder(this)
                .setTitle("Login Info")
                .setMessage(message)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent load = new Intent(SubscriptionActivity.this, LoginActivity.class);
                        startActivity(load);
                    }
                })
                .show();

    }
}
