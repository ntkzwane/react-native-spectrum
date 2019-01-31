# react-native-spectrum

## Getting started

`$ npm install react-native-spectrum --save`

### Mostly automatic installation

`$ react-native link react-native-spectrum`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-spectrum` and add `RNSpectrum.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNSpectrum.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.ntkzwane.spectrum.RNSpectrumPackage;` to the imports at the top of the file
  - Add `new RNSpectrumPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-spectrum'
  	project(':react-native-spectrum').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-spectrum/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
    implementation project(':react-native-spectrum')
  	```


## Usage
```javascript
import RNSpectrum from 'react-native-spectrum';

// TODO: What to do with the module?
RNSpectrum;
```
