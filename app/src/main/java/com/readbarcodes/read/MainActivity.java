package com.readbarcodes.read;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String result;
    TextView Result;
    String result2;
    TextView Result2;
    CameraSource cameraSource;
    SurfaceView surfaceView;
    final int RequestCameraPermissionId = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Result = (TextView) findViewById(R.id.Result);
        Result2 = (TextView) findViewById(R.id.Result2);

        permissaocamera();
    }

    public void permissaocamera() {
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                return;
            }
            else if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionId);
                return;
            }
            cameraSource.start(surfaceView.getHolder());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result == null) {
            result = intentResult.getContents();

            {
                handleResult();
            }

        } else if (result2 == null) {
            result2 = intentResult.getContents();

            {
                handleResult2();
            }
        }
    }

    public void scan_click(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    void handleResult() {
        Result.setText(result);

    }

    void handleResult2() {
        Result2.setText(result2);

    }

    public void limpa(View v) {
        Result.setText(null);
        Result2.setText(null);
        result = null;
        result2 = null;
    }

    public void validar(View view) {

        if (result == null && result2 == null) {
            Toast toast = Toast.makeText(getApplicationContext(), "LEIA OS BARCODES PARA VALIDÁ-LOS", Toast.LENGTH_SHORT);
            View toastView = toast.getView();
            toast.setGravity(Gravity.CENTER, 0, 250);
            TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
            toastMessage.setTextSize(20);
            toastMessage.setGravity(Gravity.CENTER);
            toastMessage.setTextColor(Color.BLACK);
            toastView.setBackgroundColor(Color.WHITE);
            toast.show(); }

            else if (result.equals(result2)) {
                Toast toast = Toast.makeText(getApplicationContext(), "BARCODES IGUAIS", Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toast.setGravity(Gravity.FILL, 0, 0);
                TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
                toastMessage.setTextSize(50);
                toastMessage.setGravity(Gravity.CENTER);
                toastMessage.setTextColor(Color.BLACK);
                toastView.setBackgroundColor(Color.GREEN);
                toast.show();
                Result.setText(null);
                Result2.setText(null);
                result = null;
                result2 = null;

            }

            else {
                Toast toast = Toast.makeText(getApplicationContext(), "BARCODES DIFERENTES", Toast.LENGTH_SHORT);
                View toastView = toast.getView();
                toast.setGravity(Gravity.FILL, 0, 0);
                TextView toastMessage = (TextView) toastView.findViewById(android.R.id.message);
                toastMessage.setTextSize(50);
                toastMessage.setGravity(Gravity.CENTER);
                toastMessage.setTextColor(Color.BLACK);
                toastView.setBackgroundColor(Color.RED);
                toast.show();
                Result.setText(null);
                Result2.setText(null);
                result = null;
                result2 = null;
            }

        }
    }
