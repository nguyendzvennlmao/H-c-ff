package com.venn.lmao.xd;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Color;
import android.os.IBinder;
import android.view.*;
import android.widget.*;

public class FloatingService extends Service {
    private WindowManager wm;
    private View menuView, espView;
    private ImageView logo;
    private boolean isOpen = false;

    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public void onCreate() {
        super.onCreate();
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        logo = new ImageView(this);
        logo.setImageResource(android.R.drawable.ic_dialog_info);
        
        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
            150, 150, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        
        logo.setOnClickListener(v -> {
            if (!isOpen) showMenu(); else hideMenu();
            isOpen = !isOpen;
        });
        wm.addView(logo, p);
    }

    private void showMenu() {
        menuView = LayoutInflater.from(this).inflate(R.layout.layout_menu, null);
        
        // Chức năng ESP Xanh
        menuView.findViewById(R.id.sw_esp).setOnCheckedChangeListener((b, c) -> {
            if (c) {
                espView = new View(this);
                espView.setBackgroundColor(Color.argb(45, 0, 255, 0));
                WindowManager.LayoutParams ep = new WindowManager.LayoutParams(-1, -1, 
                    WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, 24, PixelFormat.TRANSLUCENT);
                wm.addView(espView, ep);
            } else if (espView != null) { wm.removeView(espView); }
        });

        // Nút mở FF
        menuView.findViewById(R.id.btn_ff).setOnClickListener(v -> {
            Intent i = getPackageManager().getLaunchIntentForPackage("com.dts.freefireth");
            if (i != null) { i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); startActivity(i); }
        });

        wm.addView(menuView, new WindowManager.LayoutParams(-2, -2, WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY, 8, PixelFormat.TRANSLUCENT));
    }

    private void hideMenu() { if (menuView != null) { wm.removeView(menuView); menuView = null; } }
                                                            }
