package xyz.javaee.study.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import xyz.javaee.study.R;






public class RegisterActivity extends AppCompatActivity {

    //@Override
    //protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_register);



        private TextView back_w;
        private EditText write_n;
        private EditText write_p;
        private EditText read_n;
        private EditText read_p;
        private Button save_b;
        private Button read_b;

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);

            back_w = (TextView) findViewById(R.id.loginxx);
            write_n = (EditText) findViewById(R.id.name);
            write_p = (EditText) findViewById(R.id.password);
            //read_n = (EditText) findViewById(R.id.textView8);
            save_b = (Button) findViewById(R.id.button2);
            save_b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String read_c = "";
                    read_c = write_n.getText().toString();
                    if(read_c.equals("")){
                        back_w.setText("输入不能为空，保存失败！");
                    }else {
                        save(read_c);
                        back_w.setText("保存成功！");
                    }
                }
            });

        }
    public void save(String save_w){
        String filename = "data.txt";
        String content = save_w;
        FileOutputStream fos;
        try{
            fos = openFileOutput(filename,MODE_PRIVATE);
            fos.write(content.getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}










