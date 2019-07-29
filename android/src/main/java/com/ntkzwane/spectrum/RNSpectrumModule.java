package com.ntkzwane.spectrum;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.spectrum.SpectrumException;
import com.ntkzwane.spectrum.service.SpectrumService;

import java.io.FileNotFoundException;

public class RNSpectrumModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;
    private final SpectrumService spectrumService;

    public RNSpectrumModule(ReactApplicationContext reactContext, SpectrumService spectrumService) {
        super(reactContext);
        this.reactContext = reactContext;
        this.spectrumService = spectrumService;
    }

    /**
     * @return The module name
     */
    @Override
    public String getName() {
        return "RNSpectrum";
    }

    /**
     * Perform the image transcoding
     * @param inputFile The input file data
     * @param outputFileName The name of the output file
     * @param quality (optional) The percentage of the quality of the output image (default to 80%)
     * @param callback (optional) Function to invoke once the transcode has completed
     * @throws FileNotFoundException If the file cannot be created
     * @throws SpectrumException For invalid input, missing support and runtime errors.
     */
    @ReactMethod
    public void transcode(@NonNull byte[] inputFile, @NonNull String outputFileName, @Nullable Integer quality, @Nullable Callback callback)
            throws FileNotFoundException, SpectrumException {
        spectrumService.transcode(inputFile, outputFileName, quality, callback);
    }
}
