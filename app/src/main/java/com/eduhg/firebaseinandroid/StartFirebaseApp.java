package com.eduhg.firebaseinandroid;

import com.firebase.client.Firebase;

/**
 * Created by EduhG on 9/7/2016.
 */
public class StartFirebaseApp extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
