package xyz.javaee.study.utils;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoginUtil {

    public static boolean saveUserInfo(Context context, String name, String password) {
        try {
            FileOutputStream fos = context.openFileOutput("data.txt", Context.MODE_APPEND);
            fos.write((name + ":" + password).getBytes());
            fos.write("\n".getBytes(StandardCharsets.UTF_8));
            fos.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static ArrayList<Map<String, String>> getUserInfo(Context context) {
        try {
            FileInputStream fis = context.openFileInput("data.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String str;
            ArrayList<Map<String, String>> maps = new ArrayList<>();
            while ((str = br.readLine()) != null) {
                Map<String, String> userMap = new HashMap<String, String>();
                String[] infos = str.split(":");
                userMap.put("name", infos[0]);
                userMap.put("password", infos[1]);
                maps.add(userMap);
            }
            fis.close();
            return maps;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
