package com.example.test;


import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ClockTest {
    private Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
    private UiDevice uiDevice = UiDevice.getInstance(instrumentation);
    // 获取上下文
    private Context context = instrumentation.getTargetContext().getApplicationContext();

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
        Intent intent = packageManager.getLaunchIntentForPackage("com.sec.android.app.clockpackage");
        if (intent != null) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        }
        context.startActivity(intent);
        SystemClock.sleep(3000);
        UiObject2 addButton = uiDevice.wait(Until.findObject(By.res("com.sec.android.app.clockpackage:id/alarm_floating_btn")), 5000);
        addButton.click();
        UiObject2 clockName = uiDevice.wait(Until.findObject(By.res("com.sec.android.app.clockpackage:id/alarm_name_box")), 5000);
        clockName.click();
        UiObject2 clockName1 = uiDevice.wait(Until.findObject(By.res("com.sec.android.app.clockpackage:id/editTextDialogUserInput")), 5000);
        clockName1.setText("闹钟测试");
        UiObject2 clocksave = uiDevice.wait(Until.findObject(By.res("android:id/button1")), 5000);
        clocksave.click();
        UiObject2 saveButton = uiDevice.wait(Until.findObject(By.res("com.sec.android.app.clockpackage:id/Menu_Done")), 5000);
        saveButton.click();
        SystemClock.sleep(3000);
        uiDevice.pressBack();
    }
}
