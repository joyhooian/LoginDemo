package com.example.joyho.basiccomponents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SingUP extends AppCompatActivity {
    Button btnSignUp;
    Button btnCancel;
    EditText txtAddEmailAdd;
    EditText txtAddPwd;
    EditText txtConfPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignUp = findViewById(R.id.sign_up_button);
        btnCancel = findViewById(R.id.cancel_button );
        txtAddEmailAdd = findViewById(R.id.add_email);
        txtAddPwd = findViewById(R.id.add_pwd);
        txtConfPwd = findViewById(R.id.conf_pwd);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingUP.this, MainActivity.class);
                PasswordManager passwordManager = new PasswordManager(SingUP.this);
                if(txtAddEmailAdd.getText().toString().isEmpty() || txtAddPwd.getText().toString().isEmpty() || txtConfPwd.getText().toString().isEmpty()){
                    Toast.makeText(SingUP.this, "Input Cannot Be Empty!", Toast.LENGTH_SHORT).show();
                }else if (!txtAddPwd.getText().toString().equals(txtConfPwd.getText().toString())){
                    Toast.makeText(SingUP.this, "Twice Inputted Password Different!", Toast.LENGTH_SHORT).show();
                }else if(!passwordManager.find(txtAddEmailAdd.getText().toString()).isEmpty()){
                    Toast.makeText(SingUP.this, "Email Address Has Already Existed", Toast.LENGTH_SHORT).show();
                }else {
                    Log.d("signUp", "Confirm Login Info Complete");
                    LogInfo logInfo = new LogInfo(txtAddEmailAdd.getText().toString(), txtAddPwd.getText().toString());
                    passwordManager.addLogInfo(logInfo);
                    Log.d("signUp", "Add Login Info Complete");
                    intent.putExtra("EmailAdd", logInfo.getEmailAdd());
                    intent.putExtra("Password", logInfo.getPwd());
                    setResult(1,intent);
                    SingUP.this.finish();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SingUP.this, MainActivity.class);
                intent.putExtra("EmailAdd", "");
                intent.putExtra("Password", "");
                setResult(1,intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        intent.putExtra("EmailAdd", "");
        intent.putExtra("Password", "");
        setResult(2,intent);
        finish();
    }
}
