package com.ntkzwane.spectrum;

import android.util.Log;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.spectrum.DefaultPlugins;
import com.facebook.spectrum.Spectrum;
import com.facebook.spectrum.logging.SpectrumLogcatLogger;
import com.ntkzwane.spectrum.service.SpectrumService;

import java.util.Collections;
import java.util.List;


public class RNSpectrumPackage implements ReactPackage {

    /**
     * @param reactContext react application context that can be used to create modules
     * @return list of native modules to register with the newly created catalyst instance
     */
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        Spectrum spectrumInstance = Spectrum.make(new SpectrumLogcatLogger(Log.INFO), DefaultPlugins.get()); // JPEG, PNG and WebP plugins
        return Collections.singletonList(new RNSpectrumModule(reactContext, new SpectrumService(spectrumInstance)));
    }

    /**
     * @return list of JS modules to register with the newly created catalyst instance.
     *
     * IMPORTANT: Note that only modules that needs to be accessible from the native code should be
     * listed here. Also listing a native module here doesn't imply that the JS implementation of it
     * will be automatically included in the JS bundle.
     * Deprecated from RN 0.47
     */
    @Deprecated
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    /**
     * @return a list of view managers that should be registered with {@link UIManagerModule}
     */
    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
        return Collections.emptyList();
    }
}
