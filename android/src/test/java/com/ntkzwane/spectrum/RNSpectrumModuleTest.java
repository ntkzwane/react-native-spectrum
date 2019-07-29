package com.ntkzwane.spectrum;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.spectrum.SpectrumException;
import com.ntkzwane.spectrum.base.MockDependentUnitTest;
import com.ntkzwane.spectrum.service.SpectrumService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@MockDependentUnitTest
class RNSpectrumModuleTest {

    @Mock
    private ReactApplicationContext reactContext;
    @Mock
    private SpectrumService spectrumService;
    @InjectMocks
    private RNSpectrumModule rnSpectrumModule;

    @Test
    void testGetName_shouldReturnCorrectModuleName() {
        // Setup fixture
        String expectedName = "RNSpectrum";

        // Exercise SUT
        String actualName = rnSpectrumModule.getName();

        // Verify results
        assertThat(actualName, is(equalTo(expectedName)));
    }

    @Test
    void testTranscode_shouldDelegateToServiceToTranscodeImage() throws FileNotFoundException, SpectrumException {
        // Setup fixture and expectations
        byte[] inputFile = {'1', '2'};
        String outputFileName = "outputFile";
        Integer quality = 4;
        Callback callback = (Object... args) -> {};

        // Exercise SUT
        rnSpectrumModule.transcode(inputFile, outputFileName, quality, callback);

        // Verify behaviour
        verify(spectrumService, times(1)).transcode(inputFile, outputFileName, quality, callback);
        verifyNoMoreInteractions(spectrumService);
    }
}