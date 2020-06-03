package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import com.facebook.react.bridge.WritableNativeMap;

// import android.app.Activity;
import android.util.Log;

import android.os.Bundle;
import android.content.Context;
import android.content.RestrictionsManager;

public class ReadManagedConfigurationModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    public ReadManagedConfigurationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "ReadManagedConfiguration";
    }

    @ReactMethod
    public void getRestrictions(Callback callback) {
        RestrictionsManager restrictionsManager =
        (RestrictionsManager) this.reactContext.getSystemService(Context.RESTRICTIONS_SERVICE);

        if (restrictionsManager == null) {
            Log.i("readmanaged", "no restrictions manager");
            return;
        }
        
        Bundle appRestrictions = restrictionsManager.getApplicationRestrictions();
        if (appRestrictions.containsKey("deviceid") && appRestrictions.containsKey("secretKey")) {
                Log.i("readmanaged", "deviceid : " + appRestrictions.getString("deviceid"));
                Log.i("readmanaged", "secretkey : " + appRestrictions.getString("secretKey"));

                callback.invoke(appRestrictions.getString("deviceid"), appRestrictions.getString("secretKey"));
        }
    }

    @ReactMethod
    public void getAllRestrictions(Callback callback) {
        RestrictionsManager restrictionsManager =
        (RestrictionsManager) this.reactContext.getSystemService(Context.RESTRICTIONS_SERVICE);

        if (restrictionsManager == null) {
            Log.i("readmanaged", "no restrictions manager");
            return;
        }
        
        Bundle appRestrictions = restrictionsManager.getApplicationRestrictions();
        Log.i("readmanaged", "restrictions size " + appRestrictions.size() );
        WritableNativeMap data = new WritableNativeMap();
        for (String key : appRestrictions.keySet()){
            data.putString(key, appRestrictions.getString(key));
        }
        callback.invoke(data);
    }
}
