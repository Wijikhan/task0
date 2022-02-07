package com.example.practice2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {
    EditText name , email , password ;
    Button EditProfileButton;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        setTitle("View or Update Profile");


        name = (EditText) findViewById(R.id.name);

        email = (EditText) findViewById(R.id.email);

        password = (EditText) findViewById(R.id.password);

   EditProfileButton = (Button) findViewById(R.id.EditAndDone);


        name.setText(sharedPreferences.getString("name", "emptyValue"));

        email.setText(sharedPreferences.getString("email", "emptyValue"));

        password.setText(sharedPreferences.getString("password", "emptyValue"));

        builder = new AlertDialog.Builder(this);
        EditProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if (EditProfileButton.getText().equals(" Edit Profile"))
                {
                    EditProfileButton.setText(" Update ");
                    name.setEnabled(true);
                //    name.setFocusable(true);
                    email.setEnabled(true);
                 //   email.setFocusable(true);
                    password.setEnabled(true);
               //     password.setFocusable(true);
                }
                else
                {
                    EditProfileButton.setText(" Edit Profile");
                    name.setEnabled(false);
                //    name.setFocusable(false);
                    email.setEnabled(false);
                //    email.setFocusable(false);
                    password.setEnabled(false);

                    myEdit.putString("name", name.getText().toString());
                    myEdit.putString("email", email.getText().toString());
                    myEdit.putString("password", password.getText().toString());
                    myEdit.apply();



                //    password.setFocusable(false);
                }

            }
        });









    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);



         getMenuInflater().inflate(R.menu.option_menu , menu);

         return  true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);


         if (item.getItemId() == R.id.logout)
         {


             myEdit.clear();
             myEdit.apply();


            builder.setMessage(" Do you want to Logout? ")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
             AlertDialog alert = builder.create();
             //Setting the title manually
             alert.setTitle("Logout");
             alert.show();


         }


         return  true;


    }
}