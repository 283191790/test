package com.example.test;


import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.Contacts;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class WifiTest{
    private Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    private UiDevice uiDevice = UiDevice.getInstance(instrumentation);
    // 获取上下文
    private Context context = instrumentation.getTargetContext().getApplicationContext();
    private String TAG = "Test Debug";

    @Before
    public void setup() {
        uiDevice.pressHome();
        try {
            uiDevice.pressRecentApps();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        UiObject2 closeAll = uiDevice.wait(Until.findObject(By.res("com.android.systemui:id/recents_close_all_button")), 5000);
        if (closeAll != null) {
            closeAll.click();
        }
        uiDevice.pressHome();
        SystemClock.sleep(3000);
    }
    @Test
    public void call1() {

        // 启动测试App
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage("com.android.settings");
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(intent);
        SystemClock.sleep(3000);
        UiObject2 addButton = uiDevice.wait(Until.findObject(By.res("android:id/title").text("连接")), 5000);
        addButton.click();
        UiObject2 openwlan = uiDevice.wait(Until.findObject(By.res("android:id/title").text("WLAN")), 5000);
        openwlan.click();
        UiObject2 wifiSSID = uiDevice.wait(Until.findObject(By.res("android:id/title").text("Autotest_2.4G")), 5000);
        UiObject2 connectStatus = uiDevice.wait(Until.findObject(By.res("android:id/summary").textContains("连接")), 5000);
        if (wifiSSID != null && connectStatus != null ) {
//            Log.d(TAG, "WifiSSID and connectStatues not null");
//            Log.d(TAG, "wifiSSID parent is "+wifiSSID.getParent().toString());
//            Log.d(TAG, "connectStatues parent is "+connectStatus.getParent().toString());
            if (wifiSSID.getParent().equals(connectStatus.getParent())) {
                wifiSSID.click();
            SystemClock.sleep(5000);
            UiObject2 forget = uiDevice.wait(Until.findObject(By.res("android:id/button1")), 5000);
            forget.click();
            SystemClock.sleep(3000);
           }
        }


        UiObject2 connectwlan = uiDevice.wait(Until.findObject(By.res("android:id/title").text("Autotest_2.4G")), 5000);
        connectwlan.click();
        UiObject2 password = uiDevice.wait(Until.findObject(By.res("com.android.settings:id/password")), 5000);
        password.setText("test1234,.");
        UiObject2 wifisave = uiDevice.wait(Until.findObject(By.res("android:id/button1").text("连接")), 5000);
        wifisave.click();
        SystemClock.sleep(3000);

        uiDevice.pressHome();
    }
}
