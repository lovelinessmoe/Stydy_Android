package xyz.javaee.study.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import xyz.javaee.study.R;
import xyz.javaee.study.utils.LoginUtil;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etName;
    private String name;
    private String password;

    private EditText etPassword;
    private Button bthLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        etName = (EditText) findViewById(R.id.name);
        etPassword = (EditText) findViewById(R.id.password);
        bthLogin = (Button) findViewById(R.id.regist);
        bthLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        name = etName.getText().toString();
        password = etPassword.getText().toString();
        boolean b = LoginUtil.saveUserInfo(getApplicationContext(), name, password);
        if (b) {
            Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_LONG).show();
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_LONG).show();
        }
    }
}










