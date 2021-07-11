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
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

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
    public void getAllRestrictions(Promise promise) {
        try {
            RestrictionsManager restrictionsManager =
            (RestrictionsManager) this.reactContext.getSystemService(Context.RESTRICTIONS_SERVICE);

            if (restrictionsManager == null) {
                Log.i("readmanaged", "no restrictions manager");
                throw new RuntimeException("no restrictions manager");
            }
            
            Bundle appRestrictions = restrictionsManager.getApplicationRestrictions();
            WritableMap result = Arguments.fromBundle(appRestrictions);
            promise.resolve(result);
        } catch(Exception e) {
            promise.reject(e);
        }

    }
}
