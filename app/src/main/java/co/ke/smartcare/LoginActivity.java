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
import co.ke.smartcare.apiutilities.SessionManager;
import co.ke.smartcare.dataclasses.Login;
import co.ke.smartcare.dataclasses.UploadInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button login;
    private TextView signup;
    private EditText username, password;
    private ApiService apiService;
    private SessionManager sessionManager;


    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        login = findViewById(R.id.login);
        signup = findViewById(R.id.sign_up);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        apiService = ApiUtility.getAPIService();
        sessionManager = new SessionManager(getApplicationContext());

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                String pass = password.getText().toString();
                String user = username.getText().toString();

                Toast.makeText(LoginActivity.this, "Connecting to the server ... ", Toast.LENGTH_SHORT).show();
                doLogin(user, pass);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view ) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    public void doLogin(String username, String password){
        apiService.doLogin(username, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse( Call<Login> call, Response<Login> response ) {
                Login login = response.body();
                 if( login != null ){
                     if( login.getStatus().equals("success")){
                         if( login.getSubscription().equals("valid") ){
                             sessionManager.patientDetails(login.getUserId(), login.getName(), login.getUsername(),
                                     login.getPhone(), login.getEmail(), login.getIdno(), login.getAccount());
                             Toast.makeText(LoginActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                             Intent intent =  new Intent(LoginActivity.this, HomePageActivity.class);
                             startActivity(intent);
                         }else{
                             replyMessage(login.getUserId(), login.getPhone(), login.getMessage() );
                         }
                     }else{
                         Toast.makeText(LoginActivity.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                     }
                 }
            }

            @Override
            public void onFailure( Call<Login> call, Throwable t ) {
                Toast.makeText(LoginActivity.this, "Could not connect to server... \nCheck your connection."+t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    public  void replyMessage(final int id, final String phone, String message){
        new AlertDialog.Builder(this)
                .setTitle("Login Info")
                .setMessage(message)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("PAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent load = new Intent(LoginActivity.this, SubscriptionActivity.class);
                        load.putExtra("user", id);
                        load.putExtra("phone", phone);
                        startActivity(load);
                    }
                })
                .show();

    }
}
