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
import android.widget.Toast;

import co.ke.smartcare.apiutilities.ApiService;
import co.ke.smartcare.apiutilities.ApiUtility;
import co.ke.smartcare.dataclasses.UploadInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EnquiryActivity extends AppCompatActivity {
    private Button save;
    private EditText enquiry, title;
    private String en, tt;
    private int member;
    private ApiService apiService;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquiry);
        getSupportActionBar().hide();

        save = findViewById(R.id.save_enquiry);
        enquiry = findViewById(R.id.enquiry);
        title = findViewById(R.id.title);

        apiService = ApiUtility.getAPIService();
        sharedPreferences = this.getSharedPreferences("smartCareData", 0);

        member = sharedPreferences.getInt("userId", 0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                en = enquiry.getText().toString();
                tt = title.getText().toString();

                if( validEnquiry(en, tt) ){
                    saveEnquiry(member, tt, en);
                    Toast.makeText(EnquiryActivity.this, "Connecting to the server .... ", Toast.LENGTH_SHORT).show();
                }else{
                    enquiry.setError("The enquiry title and the enquiry cannot be empty");
                }
            }
        });
    }

    public Boolean validEnquiry(String enq, String ttl){
        Boolean status = false;

        if( !enq.equals("") && !ttl.equals("")){
            return true;
        }
        return status;
    }

    public void saveEnquiry(int user, String t, String e){
        apiService.saveEnquiry(user, t, e).enqueue(new Callback<UploadInfo>() {
            @Override
            public void onResponse( Call<UploadInfo> call, Response<UploadInfo> response ) {
                UploadInfo uploadInfo = response.body();

                if( uploadInfo != null){
                    if(uploadInfo.getStatus().equals("success")){
                        replyMessage(uploadInfo.getMessage());
                    }else{
                        replyNoMessage(uploadInfo.getMessage());
                    }
                }else{
                    Toast.makeText(EnquiryActivity.this, "The server did not respond .... ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure( Call<UploadInfo> call, Throwable t ) {
                Toast.makeText(EnquiryActivity.this, "Error "+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public  void replyMessage(String message){
        new AlertDialog.Builder(this)
                .setTitle("Enquiry Upload Info")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent load = new Intent(EnquiryActivity.this, HomePageActivity.class);
                        startActivity(load);
                    }
                })
                .show();

    }

    public  void replyNoMessage(String message){
        new AlertDialog.Builder(this)
                .setTitle("Enquiry Upload Info")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();

    }
}
