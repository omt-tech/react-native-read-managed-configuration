package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
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
    public void getRestrictions(ReadableArray restrictions, Promise promise) {
        try {
            RestrictionsManager restrictionsManager =
            (RestrictionsManager) this.reactContext.getSystemService(Context.RESTRICTIONS_SERVICE);

            if (restrictionsManager == null) {
                Log.i("readmanaged", "no restrictions manager");
                throw new RuntimeException("no restrictions manager");
            }

            Bundle appRestrictions = restrictionsManager.getApplicationRestrictions();
            WritableArray restrictionsValues = new WritableNativeArray();

            for (int i = 0; i < restrictions.size(); i++) {
                if (appRestrictions.containsKey(restrictions.getString(i))) {
                    // restrictionsValues[i] = appRestrictions.getString(restrictions[i]);
                    restrictionsValues.pushString(appRestrictions.getString(restrictions.getString(i)));
                } else {
                    restrictionsValues.pushNull();
                }
            }
            promise.resolve(restrictionsValues);
        } catch(Exception e) {
            promise.reject(e);
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
