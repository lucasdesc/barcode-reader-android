# Barcode-Reader-Android

Application that reads two different barcodes using from the Zxing library and validates whether they are the same or different.

# Resources

* Request for permission to open the camera
* Scanning two-value barcodes
* Validation of scanned barcodes
* Same or different barcode alert 

# Usage

Add in the dependencies in gradle the lib version corresponding to 3.0.2:
- `implementation 'com.journeyapps:zxing-android-embedded:3.0.2@aar'`
- `implementation 'com.google.zxing:core:3.2.1'`

The camera permission can be included in the Manifest:
`<uses-permission android:name="Android.permission.CAMERA" />`
