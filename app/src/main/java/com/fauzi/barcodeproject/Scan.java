package com.fauzi.barcodeproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission.CAMERA;

public class Scan extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    private TextView textViewNama, textViewNomor, textViewJenis;
    String scanResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);

//        textViewNomor = (TextView) findViewById(R.id.textViewNomor);
//        textViewNama = (TextView) findViewById(R.id.textViewNama);
//        textViewJenis = (TextView) findViewById(R.id.textViewJenis);

        setContentView(scannerView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (checkPermission()) {
                Toast.makeText(Scan.this, "Scan Diperbolehkan!", Toast.LENGTH_SHORT).show();
            } else {
                requestPermission();
            }
        }
    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(Scan.this, CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @SuppressLint("NewApi")
    public void onRequestPermissionsResult(int requestCode, String permission[], int grantResults[]) {
        switch (requestCode) {
            case REQUEST_CAMERA:
                if (grantResults.length > 0) {
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (cameraAccepted) {
                        Toast.makeText(Scan.this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Scan.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            if (shouldShowRequestPermissionRationale(CAMERA)) {
                                displayAlertMessage("You Need to Allow acces for both Permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                                    requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                                }
                                            }
                                        });
                                return;

                            }
                        }
                    }
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }

    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(Scan.this)
                .setMessage(message)
                .setPositiveButton("OK", listener)
                .setNegativeButton("CANCEL", null)
                .create()
                .show();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (result != null) {
//            if (result.getContents() == null) {
//                Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
//            } else {
//                // jika qrcode berisi data
//                try {
//                    // converting the data json
//                    JSONObject object = new JSONObject(result.getContents());
//                    // atur nilai ke textviews
//                    textViewNomor.setText(object.getString("nomor"));
//                    textViewNama.setText(object.getString("nama"));
//                    textViewJenis.setText(object.getString("jenis"));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    // jika format encoded tidak sesuai maka hasil
//                    // ditampilkan ke toast
//                    Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
//                }
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    @Override
    public void handleResult(Result result) {


        scanResult = result.getText();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nama Peserta : ");

        builder.setPositiveButton("HADIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addEmployee();
                scannerView.resumeCameraPreview(Scan.this);

            }
        });
        builder.setNeutralButton("BUKAN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                scannerView.resumeCameraPreview(Scan.this);
            }
        });

        builder.setMessage("Nama : " + scanResult);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }



    private void addEmployee(){

        final String nama = scanResult.toString().trim();


        class AddEmployee extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Scan.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(Scan.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_NAMA,nama);


                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD_ABSEN, params);
                return res;
            }
        }

        AddEmployee ae = new AddEmployee();
        ae.execute();
    }
}

