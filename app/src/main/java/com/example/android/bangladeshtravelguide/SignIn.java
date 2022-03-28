package com.example.android.bangladeshtravelguide;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;

public class SignIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
    }

    void read(View a)
    {
        String info = "";
        try
        {
            /*EditText email = (EditText)findViewById(R.id.email);
            EditText pass = (EditText)findViewById(R.id.pass);

            String totalInfo = email.getText().toString() + " " + pass.getText().toString();

            FileInputStream in = openFileInput("LoginInfo.txt");
            int size = in.available();
            byte[] buffer = new byte[size];
            in.read(buffer);
            in.close();

            info = new String(buffer);

            if(info.contains(totalInfo))
            {*/
                Intent i = new Intent(SignIn.this,MainActivity.class);
                startActivity(i);
            /*}
            else
            {
                Toast.makeText(SignIn.this,"Invalid E-mail id or password",Toast.LENGTH_LONG).show();
            }*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
