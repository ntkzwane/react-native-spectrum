package com.ntkzwane.spectrum.service;

import com.facebook.react.bridge.Callback;
import com.facebook.spectrum.EncodedImageSink;
import com.facebook.spectrum.EncodedImageSource;
import com.facebook.spectrum.Spectrum;
import com.facebook.spectrum.SpectrumException;
import com.facebook.spectrum.SpectrumResult;
import com.facebook.spectrum.options.TranscodeOptions;
import com.facebook.spectrum.requirements.EncodeRequirement;
import com.ntkzwane.spectrum.base.AbstractMockDependentUnitTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.FileNotFoundException;

import static com.facebook.spectrum.image.EncodedImageFormat.JPEG;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class SpectrumServiceTest extends AbstractMockDependentUnitTest {

    private static final String TEST_OUTPUT_FILE = "build/tmp/test-output-file";
    private static final byte[] TEST_INPUT_FILE = {'1', '2'};

    @Mock
    private Spectrum spectrumInstance;
    @InjectMocks
    private SpectrumService spectrumService;

    @Test
    @SuppressWarnings("ConstantConditions")
    void testTranscodeWhenQualityIsNotSpecified_shouldDelegateToSpectrumToTranscodeImageWithDefaultQuantity() throws FileNotFoundException, SpectrumException {
        // Setup fixture and expectations
        Integer nullQuantity = null;
        int defaultQuantity = 80;
        String callerContext = "my_callsite_identifier";

        TranscodeOptions transcodeOptions = TranscodeOptions.Builder(new EncodeRequirement(JPEG, defaultQuantity)).build();
        SpectrumResult result = new SpectrumResult("some-rule", null, null, 0L, 0L);

        when(spectrumInstance.transcode(any(EncodedImageSource.class), any(EncodedImageSink.class), eq(transcodeOptions), eq(callerContext)))
                .thenReturn(result);


        // Exercise SUT
        spectrumService.transcode(TEST_INPUT_FILE, TEST_OUTPUT_FILE, nullQuantity, null);

        // Verify behaviour
        verify(spectrumInstance, times(1)).transcode(any(EncodedImageSource.class),
                any(EncodedImageSink.class), eq(transcodeOptions), eq(callerContext));
        verifyNoMoreInteractions(spectrumInstance);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 25, 50, 75, 100})
    void testTranscodeWhenQualityIsSpecified_shouldDelegateToSpectrumToTranscodeImageWithSpecifiedQuantity(int quantity) throws FileNotFoundException, SpectrumException {
        // Setup fixture and expectations
        String callerContext = "my_callsite_identifier";

        TranscodeOptions transcodeOptions = TranscodeOptions.Builder(new EncodeRequirement(JPEG, quantity)).build();
        SpectrumResult result = new SpectrumResult("some-rule", null, null, 0L, 0L);

        when(spectrumInstance.transcode(any(EncodedImageSource.class), any(EncodedImageSink.class),
                eq(transcodeOptions), eq(callerContext)))
                .thenReturn(result);


        // Exercise SUT
        spectrumService.transcode(TEST_INPUT_FILE, TEST_OUTPUT_FILE, quantity, null);

        // Verify behaviour
        verify(spectrumInstance, times(1)).transcode(any(EncodedImageSource.class),
                any(EncodedImageSink.class), eq(transcodeOptions), eq(callerContext));
        verifyNoMoreInteractions(spectrumInstance);
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    void testTranscodeWhenCallbackIsNotSpecified_shouldDoNothing() throws FileNotFoundException, SpectrumException {
        // Setup fixture and expectations
        Callback nullCallback = null;
        int quantity = 1;

        // Exercise SUT
        spectrumService.transcode(TEST_INPUT_FILE, TEST_OUTPUT_FILE, quantity, nullCallback);

        // Verify behaviour (expect no exception)
        verify(spectrumInstance, times(1)).transcode(any(EncodedImageSource.class),
                any(EncodedImageSink.class), any(TranscodeOptions.class), any(Object.class));
    }

    @Test
    void testTranscodeWhenCallbackIsSpecified_shouldInvokeCallback(@Mock Callback callback) throws FileNotFoundException, SpectrumException {
        // Setup fixture and expectations
        int quantity = 1;

        SpectrumResult result = new SpectrumResult("some-rule", null, null, 0L, 0L);

        when(spectrumInstance.transcode(any(EncodedImageSource.class), any(EncodedImageSink.class),
                any(TranscodeOptions.class), any(Object.class)))
                .thenReturn(result);

        // Exercise SUT
        spectrumService.transcode(TEST_INPUT_FILE, TEST_OUTPUT_FILE, quantity, callback);

        // Nothing to verify, expect no exceptions
        verify(callback, times(1)).invoke(result);
        verifyNoMoreInteractions(callback);
    }
}
