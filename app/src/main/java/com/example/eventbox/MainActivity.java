package com.example.eventbox;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import com.example.eventbox.DataBaseHelper;

import android.os.Bundle;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonSignUp;
    private List<EventModel> dbEvents;
    public static UserModel current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Check if the user is already authenticated
        if (userIsAuthenticated()) {
            launchNewActivity();
            finish();
            return;
        }
        // Request to remove the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // If the user is not authenticated, show the login UI
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        buttonSignUp = findViewById(R.id.buttonSignUp);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<UserModel> dbUsers = dataBaseHelper.getEveryone();
                dataBaseHelper.addInitialEvents();
                dbEvents = dataBaseHelper.getEvents();

                for (UserModel user : dbUsers) {
                    if (user.getName().equals(username) && user.getPassword().equals(password)) {
                        // credentials are correct, do something
                        current_user = user;
                        Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                        launchNewActivity();
                    }else {
                        Toast.makeText(MainActivity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });


    }

    private boolean userIsAuthenticated() {
        // Check if the user is authenticated and return true or false accordingly
        // ...
        boolean auth = false;
        return auth;
    }

    private void launchNewActivity() {
        Intent intent = new Intent(this, HomeSideBar.class);
        startActivity(intent);
    }

    public List<EventModel> getDbEvents() {
        return dbEvents;
    }

    public static UserModel getCurrentUser(){
        return current_user;
    }
}

