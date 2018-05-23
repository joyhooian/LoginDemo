package com.example.joyho.basiccomponents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.igexin.sdk.PushManager;

public class MainActivity extends AppCompatActivity{
    EditText textEmailAddress, textPassword;
    Button btnLogin;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textEmailAddress = findViewById(R.id.email);
        textPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.sign_in_button);
        btnSignUp = findViewById(R.id.sign_up_button2);

        PushManager.getInstance().initialize(this.getApplicationContext(), DemoService.class);
        PushManager.getInstance().registerPushIntentService(this.getApplicationContext(), DemoIntentService.class);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AfterLogin.class);
                PasswordManager logInfo = new PasswordManager(MainActivity.this);
                Log.d("login", textEmailAddress.getText().toString());
                Log.d("login", textPassword.getText().toString());

                if(textEmailAddress.getText().toString().isEmpty() || textPassword.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Input cannot be empty!", Toast.LENGTH_SHORT).show();
                }else if(logInfo.find(textEmailAddress.getText().toString()).isEmpty()){
                    Toast.makeText(MainActivity.this, "You haven't sign up with this ID",
                            Toast.LENGTH_SHORT).show();
                }else if(textPassword.getText().toString().equals(logInfo.find(textEmailAddress.getText().toString()))){
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Please Input Correct Email and Password!",
                            Toast.LENGTH_SHORT).show();
                    textPassword.setText("");
                    textEmailAddress.setText("");
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SingUP.class);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        textPassword.setText(data.getStringExtra("Password"));
        textEmailAddress.setText(data.getStringExtra("EmailAdd"));
    }
}
