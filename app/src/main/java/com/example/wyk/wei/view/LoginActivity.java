package com.example.wyk.wei.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wyk.wei.R;

public class LoginActivity extends AppCompatActivity {
    private Button mLoginButton;
    private Button mRegisterButton;
    private CheckBox mCheckBox;
    private EditText mName;
    private EditText mPassword;
    private String name;
    private String password;

    private static String PREFS_NAME = "account";

    private LoginBtListener mLoginBtListener;
    private RegisterListener mRegisterListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.wei_login);

        initView();

    }

    private void initView() {
        mLoginButton = findViewById(R.id.wei_login_bt);
        mRegisterButton = findViewById(R.id.wei_login_register);
        mName = findViewById(R.id.wei_login_et_name);
        mPassword = findViewById(R.id.wei_login_et_password);
        mCheckBox = findViewById(R.id.wei_login_checkbox);

        mLoginBtListener = new LoginBtListener();
        mRegisterListener = new RegisterListener();

        mLoginButton.setOnClickListener(mLoginBtListener);
        mRegisterButton.setOnClickListener(mRegisterListener);

    }

    public class LoginBtListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            name = mName.getText().toString();
            password = mPassword.getText().toString();

            if (name.equals("admin") && password.equals("123")) {
                Toast.makeText(LoginActivity.this, "login response OK", Toast.LENGTH_SHORT).show();

            }
            if (mCheckBox.isChecked()){
                //
            }

        }
    }

    public class RegisterListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Toast.makeText(LoginActivity.this, "register response OK", Toast.LENGTH_SHORT).show();
        }
    }

}
