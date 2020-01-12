package com.fauzi.barcodeproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

//import androidmads.library.qrgenearator.QRGContents;
//import androidmads.library.qrgenearator.QRGEncoder;
//import androidmads.library.qrgenearator.QRGSaver;

public class MainActivity extends AppCompatActivity {
//    private TextView textViewNama, textViewTinggi;
//    EditText editText;
//    String text2Qr;
//    String TAG = "GenerateQRCode";
//    ImageView qrImage;
//    Button generate , save , scan;
//    String savePath = Environment.getExternalStorageDirectory().getPath() + "/QrCode/";
//    Bitmap bitmap;
//    QRGEncoder qrgEncoder;
//    //qr code scanner object
//    private IntentIntegrator intentIntegrator;
//
//    protected boolean shouldAskPermissions() {
//        return (Build.VERSION.SDK_INT >
//                Build.VERSION_CODES.M);
//    }
//
//    @TargetApi(23)
//    protected void askPermissions() {
//        String[] permissions = {
//                "android.permission.READ_EXTERNAL_STORAGE",
//                "android.permission.WRITE_EXTERNAL_STORAGE"
//        };
//        int requestCode = 200;
//        requestPermissions(permissions, requestCode);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        if (shouldAskPermissions()) {
//            askPermissions();
//        }
//
//        final String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//        File dir = new File(extStorageDirectory, "QrCode");
//        if (!dir.exists()) {
//            dir.mkdir();
//        }
//
//        editText = findViewById(R.id.edtValue);
//        generate = findViewById(R.id.start);
//        save = findViewById(R.id.save);
//        scan = findViewById(R.id.scann);
//        qrImage = findViewById(R.id.qrImage);
//
//        generate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                text2Qr = editText.getText().toString().trim();
//
//                QRGEncoder qrgEncoder = new QRGEncoder(text2Qr, null, QRGContents.Type.TEXT, 500);
//                try {
//                    // Getting QR-Code as Bitmap
//                    bitmap = qrgEncoder.encodeAsBitmap();
//                    // Setting Bitmap to ImageView
//                    qrImage.setImageBitmap(bitmap);
//                } catch (WriterException e) {
//                    Log.v(TAG, e.toString());
//                }
//            }
//        });
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try {
//                    QRGSaver.save(savePath, text2Qr, bitmap, QRGContents.ImageType.IMAGE_JPEG);
//                    Toast.makeText(MainActivity.this, "Berhasil Disimpan di" + savePath, Toast.LENGTH_SHORT).show();
//                } catch (WriterException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        scan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, Scan.class);
//                startActivity(intent);
//
//            }
//        });
//    }

    //        private Button buttonScan;
//        private TextView textViewNama, textViewNomor, textViewJenis;
//        private Button buttonAdd, buttonScanPulang;
//        private Button buttonTabel;
    private Button masuk, pulang, register, logout;


    //qr code scanner object
//    private IntentIntegrator intentIntegrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masuk = findViewById(R.id.buttonScan);
        pulang = findViewById(R.id.buttonScanPulang);
        register = findViewById(R.id.buttonTabel);
        logout = findViewById(R.id.logout);


        masuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Scan.class);
                startActivity(i);

            }
        });
        pulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,ScanPulang.class);
                startActivity(i);


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,MainActivityTabel.class);
                startActivity(i);

            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();

            }
        });


    }
    private void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Yakin Pengin Metu?")
                .setCancelable(false)//tidak bisa tekan tombol back
                //jika pilih yess
                .setPositiveButton("Ya Jeh", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                //jika pilih no
                .setNegativeButton("Ora Gah", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();
    }
}

            // initialize object
//            buttonScan = (Button) findViewById(R.id.buttonScan);
//            buttonScanPulang = (Button) findViewById(R.id.buttonScanPulang);
//            textViewNomor = (TextView) findViewById(R.id.textViewNomor);
//            textViewNama = (TextView) findViewById(R.id.textViewNama);
//            textViewJenis = (TextView) findViewById(R.id.textViewJenis);
//            buttonAdd = (Button) findViewById(R.id.buttonAdd);
//            buttonTabel = (Button) findViewById(R.id.buttonTabel);

            //Setting listeners to button

//            buttonTabel.setOnClickListener(this);

            // attaching onclickListener
//            buttonScan.setOnClickListener(this);
//            buttonScanPulang.setOnClickListener(this);
//        }
        //Dibawah ini merupakan perintah untuk Menambahkan Pegawai (CREATE)
//        private void addEmployee(){
//
//            final String nomor = textViewNomor.getText().toString().trim();
//            final String nama = textViewNama.getText().toString().trim();
//            final String jenis = textViewJenis.getText().toString().trim();
//
//            class AddEmployee extends AsyncTask<Void,Void,String> {
//
//                ProgressDialog loading;
//
//                @Override
//                protected void onPreExecute() {
//                    super.onPreExecute();
//                    loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
//                }
//
//                @Override
//                protected void onPostExecute(String s) {
//                    super.onPostExecute(s);
//                    loading.dismiss();
//                    Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
//                }
//
//                @Override
//                protected String doInBackground(Void... v) {
//                    HashMap<String,String> params = new HashMap<>();
//                    params.put(Konfigurasi.KEY_EMP_NOMOR,nomor);
//                    params.put(Konfigurasi.KEY_EMP_NAMA,nama);
//                    params.put(Konfigurasi.KEY_EMP_JENIS,jenis);
//
//                    RequestHandler rh = new RequestHandler();
//                    String res = rh.sendPostRequest(Konfigurasi.URL_ADD, params);
//                    return res;
//                }
//            }
//
//            AddEmployee ae = new AddEmployee();
//            ae.execute();
//        }
//        // Mendapatkan hasil scan
//
//
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//            if (result != null) {
//                if (result.getContents() == null) {
//                    Toast.makeText(this, "Hasil tidak ditemukan", Toast.LENGTH_SHORT).show();
//                } else {
//                    // jika qrcode berisi data
//                    try {
//                        // converting the data json
//                        JSONObject object = new JSONObject(result.getContents());
//                        // atur nilai ke textviews
//                        textViewNomor.setText(object.getString("nomor"));
//                        textViewNama.setText(object.getString("nama"));
//                        textViewJenis.setText(object.getString("jenis"));
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        // jika format encoded tidak sesuai maka hasil
//                        // ditampilkan ke toast
//                        Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//            } else {
//                super.onActivityResult(requestCode, resultCode, data);
//            }
//        }


//        @Override
//        public void onClick(View v) {
//            // inisialisasi IntentIntegrator(scanQR)
//            if (v == buttonScan) {
//                Intent intent = new Intent(MainActivity.this, Scan.class);
//                startActivity(intent);
//
//            }
//            if (v == buttonScanPulang) {
//                Intent intent = new Intent(MainActivity.this, ScanPulang.class);
//                startActivity(intent);
//            }
//
//            if (v == buttonTabel) {
//                startActivity(new Intent(MainActivity.this, MainActivityTabel.class));
//            }
//        }
//    }
