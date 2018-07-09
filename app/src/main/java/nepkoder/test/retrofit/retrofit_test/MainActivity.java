package nepkoder.test.retrofit.retrofit_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button send, register;
    private TextView success;
    ApiService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        send = (Button) findViewById(R.id.send);
        success = (TextView) findViewById(R.id.success);
        register = (Button) findViewById(R.id.register);

        service = ApiClient.getRetrofitInstance().create(ApiService.class);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String e = email.getText().toString();
                String p = password.getText().toString();
                sendUser(e,p);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    createAccount(email.getText().toString(),password.getText().toString());
            }
        });

    }

    // for login user
    public void sendUser(String email, String password) {
        service.loginUser(email,password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody resp = response.body();

                if (response.isSuccessful() && resp!= null) {
                    if (resp.getStatus().equalsIgnoreCase("success")) {
//                    System.out.println(response.message());
                        success.setText(resp.getStatus());
                        Toast.makeText(getApplicationContext(),resp.getMessage(),Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),resp.getMessage(),Toast.LENGTH_LONG).show();
                    }
//                    System.out.println(res.getEmail());
//                    System.out.println(response.message().toString());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {
                System.out.println("failed");

            }
        });
    }

    // for register
    public void createAccount(String u, String p) {
        service.createUser(u,p).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody myResponse = response.body();
                if (myResponse!=null && response.isSuccessful()) {

                    if (myResponse.getStatus().equalsIgnoreCase("success")) {

                        Toast.makeText(getApplicationContext(), myResponse.getMessage(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"User already exists!!",Toast.LENGTH_LONG).show();
                    }
// System.out.println(response.body().toString());
                    success.setText(myResponse.getStatus());
//                    System.out.println(myResponse.getMessage());
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseBody> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"Cannot fetch data",Toast.LENGTH_LONG).show();

            }
        });
    }
}
