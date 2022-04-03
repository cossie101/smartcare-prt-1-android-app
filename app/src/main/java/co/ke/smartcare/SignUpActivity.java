package co.ke.smartcare;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class SignUpActivity extends AppCompatActivity {
    private EditText name, username, phone, email, idno, password, confirm;
    private Button signup;
    private ApiService apiService;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        idno = findViewById(R.id.idno);
        password = findViewById(R.id.password);
        confirm = findViewById(R.id.confirm);
        signup = findViewById(R.id.sign_up);

        apiService = ApiUtility.getAPIService();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String pass = password.getText().toString();
                String confirmPass = confirm.getText().toString();
                String n = name.getText().toString();
                String u = username.getText().toString();
                String e = email.getText().toString();
                String p = phone.getText().toString();
                String i = idno.getText().toString();

                if(checkPasswordMatch(pass, confirmPass)){
                    doSignUp(n, u, e, p, i, pass);
                    Toast.makeText(SignUpActivity.this, "Connecting to the server ... ", Toast.LENGTH_SHORT).show();
                }else{
                    password.setError("Password do not match");
                }
            }
        });



    }

    public Boolean checkPasswordMatch(String pass, String confirm_password){
        Boolean status = false;
        if( pass.equals(confirm_password)){
            status = true;
        }

        return status;

    }


    public void doSignUp(String name, String username, String email, String phone, String idno, String password){
        apiService.doSignUp(name, username, email, phone, idno, password).enqueue(new Callback<UploadInfo>() {
            @Override
            public void onResponse( Call<UploadInfo> call, Response<UploadInfo> response ) {
                UploadInfo uploadInfo = response.body();

                if( uploadInfo != null ){
                    if( uploadInfo.getStatus().equals("success")){
                        replyMessage(uploadInfo.getMessage());
                    }else{
                        Toast.makeText(SignUpActivity.this, uploadInfo.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignUpActivity.this, "No response from the server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure( Call<UploadInfo> call, Throwable t ) {
                Toast.makeText(SignUpActivity.this, "Could not connect to the server"+t.toString(), Toast.LENGTH_SHORT).show();
                Log.i("MSG", t.toString());
            }
        });
    }


    public  void replyMessage(String message){
        new AlertDialog.Builder(this)
                .setTitle("Sign Up Info")
                .setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent load = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(load);
                    }
                })
                .show();

    }


}
