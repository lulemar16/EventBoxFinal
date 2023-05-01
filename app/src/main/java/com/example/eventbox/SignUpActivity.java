package com.example.eventbox;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    Button signUpButton;
    EditText et_name, et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Request to remove the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        // Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_up);

        signUpButton = findViewById(R.id.buttonSignUp);
        et_name = findViewById(R.id.username);
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserModel userModel;

                try {
                    userModel = new UserModel(-1, et_name.getText().toString(), et_email.getText().toString(), et_password.getText().toString());
                }
                catch (Exception e) {
                    userModel = new UserModel(-1, "error", "error", "error");

                }

                DataBaseHelper dataBaseHelper = new DataBaseHelper(SignUpActivity.this);

                boolean success = dataBaseHelper.addOneUser(userModel);
                Toast.makeText(SignUpActivity.this, "Success=" + success, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
