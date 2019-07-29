package com.ntkzwane.spectrum.service;

import android.support.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.spectrum.EncodedImageSink;
import com.facebook.spectrum.EncodedImageSource;
import com.facebook.spectrum.Spectrum;
import com.facebook.spectrum.SpectrumException;
import com.facebook.spectrum.SpectrumResult;
import com.facebook.spectrum.options.TranscodeOptions;
import com.facebook.spectrum.requirements.EncodeRequirement;

import java.io.FileNotFoundException;

import javax.annotation.Nullable;

import static com.facebook.spectrum.image.EncodedImageFormat.JPEG;

public class SpectrumService {

    private final Spectrum spectrumInstance;

    public SpectrumService(Spectrum spectrumInstance) {
        this.spectrumInstance = spectrumInstance;
    }

    public void transcode(@NonNull byte[] inputFile, @NonNull String outputFileName, @Nullable Integer quality, @Nullable Callback callback)
            throws FileNotFoundException, SpectrumException {
        EncodedImageSource.from(inputFile);
                EncodedImageSink.from(outputFileName);
        SpectrumResult result = spectrumInstance.transcode(
                EncodedImageSource.from(inputFile),
                EncodedImageSink.from(outputFileName),
                TranscodeOptions.Builder(new EncodeRequirement(JPEG, getQualityOrDefault(quality))).build(),
                "my_callsite_identifier");

        if (callback != null) {
            callback.invoke(result);
        }
    }

    private int getQualityOrDefault(Integer quality) {
        return quality == null ? 80 : quality;
    }
}
