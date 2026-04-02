package com.venn.lmao.xd;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edPass = findViewById(R.id.ed_pass);
        Button btnLogin = findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {
            if (edPass.getText().toString().equals("VennLMAOXD")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                    startActivity(intent);
                } else {
                    startService(new Intent(this, FloatingService.class));
                    Toast.makeText(this, "Truy cập thành công!", 0).show();
                }
            } else {
                Toast.makeText(this, "Sai mật khẩu!", 0).show();
            }
        });
    }
                                            }
