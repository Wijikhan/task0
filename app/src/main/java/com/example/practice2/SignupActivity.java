package com.example.practice2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    EditText name , email , password ;
    Button signupButton;
    String emailvalid;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

         sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
         myEdit = sharedPreferences.edit();

        setTitle("Sign Up");

        name = (EditText) findViewById(R.id.name);

        email = (EditText) findViewById(R.id.email);

        password = (EditText) findViewById(R.id.password);

         emailvalid  = email.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        signupButton = (Button) findViewById(R.id.signupButton);



       signupButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {





               if (name.getText().toString().trim().equals("")  || email.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
               {
                   Toast.makeText(getApplicationContext(), " Please Fill the form completely to signUp", Toast.LENGTH_SHORT).show();
               }
               else

               {


                   if(isValidEmailId(email.getText().toString().trim().toLowerCase()))
                   {


                       myEdit.putString("name", name.getText().toString());
                       myEdit.putString("email", email.getText().toString());
                       myEdit.putString("password", password.getText().toString());
                       myEdit.putBoolean("logout", true);
                       myEdit.commit();
                       Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                       startActivity(intent);
                       finish();




                   }

                   else

                   {
                       Toast.makeText(getApplicationContext(), " email pattern not valid ", Toast.LENGTH_SHORT).show();
                   }



               }













           }
       });



    }

    public void viewvalue(View view)
    {
        Log.d("view", String.valueOf(sharedPreferences.getBoolean("logout", false)));
        Log.d("view", sharedPreferences.getString("name", " Empty value "));

        Log.d("view", sharedPreferences.getString("email", " Empty value "));
        Log.d("view", sharedPreferences.getString("password", " Empty value "));


    }



    private boolean isValidEmailId(String email){


        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}