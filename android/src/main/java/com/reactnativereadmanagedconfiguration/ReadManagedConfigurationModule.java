package com.reactnativereadmanagedconfiguration;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;

import android.util.Log;
import android.os.Bundle;
import android.content.Context;
import android.content.RestrictionsManager;

@ReactModule(name = ReadManagedConfigurationModule.NAME)
public class ReadManagedConfigurationModule extends ReactContextBaseJavaModule {
    public static final String NAME = "ReadManagedConfiguration";

    public ReadManagedConfigurationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }


    @ReactMethod
    public void getAllRestrictions(Promise promise) {
        try {
            RestrictionsManager restrictionsManager =
            // (RestrictionsManager) this.reactContext.getSystemService(Context.RESTRICTIONS_SERVICE);
            (RestrictionsManager) getReactApplicationContext().getSystemService(Context.RESTRICTIONS_SERVICE);

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

