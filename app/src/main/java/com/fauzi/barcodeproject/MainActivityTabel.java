package com.fauzi.barcodeproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;


public class MainActivityTabel extends AppCompatActivity implements View.OnClickListener{

    EditText etId, etNama, etAsal, etAlamat, etNo;
    EditText editText;
    Spinner etKelompok, etReguler;
    Button buttonAdd, generate;
    List<String>list;
    ArrayAdapter<String>SpinnerAdapter;
    String fileName;
    String text2Qr;
    boolean sukses = true;
//    String savePath = Environment.getExternalStorageDirectory().getPath();
    Bitmap bitmap;
    QRGEncoder qrgEncoder;

        protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT >
                Build.VERSION_CODES.M);
    }


    @TargetApi(Build.VERSION_CODES.M)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabel);
        etId = findViewById(R.id.id_peserta);
        etNama = findViewById(R.id.etextViewNama);
        etAsal = findViewById(R.id.etextViewAsal);
        etAlamat = findViewById(R.id.etextViewAlamat);
        etNo = findViewById(R.id.etextViewNo);

        if (shouldAskPermissions()) {
            askPermissions();
        }

//        final String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
//        File dir = new File(extStorageDirectory, "QrCode");
//        if (!dir.exists()) {
//            dir.mkdir();
//        }



        /* Inisialisasi */
        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);
        generate = (Button) findViewById(R.id.generate);
        generate.setOnClickListener(this);

        /* Spinner Kelompok */
        etKelompok = findViewById(R.id.etextViewKelompok);
        list = new ArrayList<String >();
        list.add("Big Data");
        list.add("Programmer");
        list.add("Hacker");
        list.add("Developer");
        list.add("Designer");
        list.add("Data Science");
        list.add("Animator");
        list.add("Robotics");
        list.add("IoT Internet of Things");
        list.add("AI Artificial Intelligent");
        SpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etKelompok.setAdapter(SpinnerAdapter);

        /* Spinner Kelas Reguler */
        etReguler = findViewById(R.id.etextViewReguler);
        list = new ArrayList<String >();
        list.add("Reguler Pagi");
        list.add("Reguler Sore");
        SpinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return super.getView(position, convertView, parent);
            }
            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                return super.getDropDownView(position, convertView, parent);
            }
        };
        SpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        etReguler.setAdapter(SpinnerAdapter);
    }

    /* Kelas Untuk Input Data */
    private void addEmployee(){
        /* inisialisasii data */
        final String nomor = etNama.getText().toString().trim();
        final String nama = etAsal.getText().toString().trim();
        final String jenis = etAlamat.getText().toString().trim();
        final String no = etNo.getText().toString().trim();
        final String kelompok = String.valueOf(etKelompok.getSelectedItem());
        final String reguler = String.valueOf(etReguler.getSelectedItem());

        /* Perintah/Coding untuk Input Data ke API /MySQL */
        class AddEmployee extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivityTabel.this,"Menambahkan...","Tunggu...",false,false);
            }
            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_EMP_NOMOR,nomor);
                params.put(Konfigurasi.KEY_EMP_NAMA,nama);
                params.put(Konfigurasi.KEY_EMP_JENIS,jenis);
                params.put(Konfigurasi.KEY_EMP_NO,no);
                params.put(Konfigurasi.KEY_EMP_KELOMPOK,kelompok);
                params.put(Konfigurasi.KEY_EMP_REGULER,reguler);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Konfigurasi.URL_ADD_PESERTA, params);
                return res;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivityTabel.this,s,Toast.LENGTH_SHORT).show();
            }
        }
        /* Validasi Data Jika Kosong*/
        AddEmployee ae = new AddEmployee();
        if (nomor.equals("") | nama.equals("") | jenis.equals("")| no.equals("")| kelompok.equals("")| kelompok.equals("")) {
            Toast.makeText(MainActivityTabel.this, "Masukan Dulu Data Kamu", Toast.LENGTH_SHORT).show();
        }else {
            ae.execute();

            /* Generate nama ke Kode QR */
//            String file = savePath +"/QRPKKMB";
            text2Qr = etNama.getText().toString().trim();
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(text2Qr, BarcodeFormat.QR_CODE, 200,200);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);

//                FileOutputStream out = null;
//                File file = new File(Environment.getExternalStorageDirectory().getPath(), "QRPKKMB");
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//                String filePath = (file.getAbsolutePath() + "/" + text2Qr + ".png");
//                try {
//                    out = new FileOutputStream(filePath);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                Toast.makeText(MainActivityTabel.this, "File saved at\n" + filePath, Toast.LENGTH_SHORT).show();
                final String extStorageDirectory = Environment.getExternalStorageDirectory()+"/PKKMBQRCODE/";
                File dir = new File(extStorageDirectory);
                 if (!dir.exists()) {
                    dir.mkdir();
                }
                QRGSaver.save(extStorageDirectory, text2Qr, bitmap, QRGContents.ImageType.IMAGE_JPEG);
                Toast.makeText(MainActivityTabel.this, "Berhasil Disimpan di " + extStorageDirectory, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivityTabel.this, Generate.class);
                intent.putExtra("QR",bitmap);
                intent.putExtra("nomor", nomor); //nama
                intent.putExtra("nama", nama);  //asal
                intent.putExtra("jenis", jenis);  //alamat
                intent.putExtra("no", no);
                intent.putExtra("kelompok", kelompok);
                intent.putExtra("reguler", reguler);
                startActivity(intent);
            }catch (WriterException e){
                e.printStackTrace();
            }
            }
        }


    /* Kelas Untuk hapus Data */
    private void clear() {
        etNama.setText("");
        etAlamat.setText("");
        etAsal.setText("");
        etNo.setText("");
        etNama.requestFocus();
        etReguler.getItemAtPosition(0);
        etKelompok.getItemAtPosition(0);

    }

    /* Kelas Untuk Action Tombol*/
    @Override
    public void onClick(View view) {
        if (view == buttonAdd) {
            addEmployee();
        }
        if (view == generate) {
            clear();
        }
    }
}
