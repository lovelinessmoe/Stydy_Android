package xyz.javaee.study.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import xyz.javaee.study.R;

public class LoginActivity extends Activity {
    public Button bt;
    Button bt1;
    public EditText name;
    public EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt = (Button) findViewById(R.id.login_button);
        //bt1 = (Button) findViewById(R.id.login_button1);
        bt.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                name = (EditText) findViewById(R.id.name);
                password = (EditText) findViewById(R.id.password);
                String myName = name.getText().toString();
                String mypassword = password.getText().toString();// 获取文本框输入信息
                if ("".equals(myName) || "".equals(mypassword)) {
                    Toast.makeText(getApplicationContext(), "用户名或密码不能为空", Toast.LENGTH_LONG).show();
                } else {
                    System.out.println(mypassword);
                    System.out.println(myName);
                    if ("admin".equals(myName) && "123456".equals(mypassword)) {
                        Intent intent = getIntent();
                        intent.putExtra("myName",myName);
                        intent.putExtra("myPassword",mypassword);
                        setResult(1,intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}