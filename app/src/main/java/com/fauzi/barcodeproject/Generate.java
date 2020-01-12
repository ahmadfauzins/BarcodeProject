package com.fauzi.barcodeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static com.itextpdf.text.pdf.PdfWriter.checkPdfIsoConformance;
import static com.itextpdf.text.pdf.PdfWriter.getInstance;


public class Generate extends AppCompatActivity {
    ImageView imageView;
    TextView nama, asal, alamat, no, kelompok, reguler;
    Button simpan;
    private String fileName;
    Bitmap qr;
    Bundle getform;

    private File pdfFile;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 111;
    LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate);

        linearLayout = findViewById(R.id.layview);
        imageView = findViewById(R.id.imageView);
        simpan = findViewById(R.id.simpan);

        nama = findViewById(R.id.tvNama);
//        asal  = findViewById(R.id.tvAsal);
        alamat = findViewById(R.id.tvAlamat);
        no = findViewById(R.id.tvNo);
        kelompok = findViewById(R.id.tvKelompok);
        reguler = findViewById(R.id.tvReguler);


        Bundle getform = getIntent().getExtras();
        nama.setText("Nama \t\t\t\t: " + getform.getString("nomor"));
//        asal.setText("Asal Sekolah \t\t\t : "+getform.getString("nama"));
        alamat.setText("Alamat\t\t\t\t: " + getform.getString("jenis"));
        no.setText("No HP\t\t\t\t: " + getform.getString("no"));
        kelompok.setText("Kelompok\t: " + getform.getString("kelompok"));
        reguler.setText("Reguler\t\t\t: " + getform.getString("reguler"));

        qr = getIntent().getParcelableExtra("QR");
        imageView.setImageBitmap(qr);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Generate.this,MainActivityTabel.class);
                startActivity(i);
                finish();
            }
        });
    }


//    @Override
//    public void onClick(View view) {
//        try {
//            createPDF();
//        }catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }catch (DocumentException e){
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Intent i = new Intent(Generate.this,MainActivity.class);
//        startActivity(i);
//    }
//
//    void createPDF() throws IOException, DocumentException {
//        Document document = new Document();
//
//        String directoryPath = android.os.Environment.getExternalStorageDirectory().toString();
//
//        PdfWriter.getInstance(document, new
//
//                FileOutputStream(directoryPath + "/example.pdf")); //  Change pdf's name.
//
//        document.open();
//        qr = qr;
//
//        Image qr= Image.getInstance(directoryPath + "/" + "example.jpg");  // Change image's name and extension.
//
//        float scaler = ((document.getPageSize().getWidth() - document.leftMargin()
//                - document.rightMargin() - 0) / qr.getWidth()) * 100; // 0 means you have no indentation. If you have any, change it.
//        qr.scalePercent(scaler);
//        qr.setAlignment(Image.ALIGN_CENTER | Image.ALIGN_TOP);
//
//        document.add(qr);
//        document.close();
//    }

}

//
//}

//        simpan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveImage(qr);
//            }
//        });
//        mImageView.setImageBitmap(bitmap);
//    private void saveImage(Bitmap qr) {
//
//        FileOutputStream out = null;
//        File file = new File(Environment.getExternalStorageDirectory().getPath(), "QRPKKMB");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        if (fileName.contains("/")) {
//            fileName = fileName.replace("/", "\\");
//        }
//        String filePath = (file.getAbsolutePath() + "/" + fileName + ".png");
//        try {
//            out = new FileOutputStream(filePath);
//            qr.compress(Bitmap.CompressFormat.PNG, 100, out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Toast.makeText(Generate.this, "File saved at\n" + filePath, Toast.LENGTH_SHORT).show();
//    }


    //
//    }
//    private void saveImage() {
//        fileName = nama.getText().toString().trim();
//        FileOutputStream out = null;
//        File file = new File(Environment.getExternalStorageDirectory().getPath(), "PKKMBQRCode");
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        if (fileName.contains("/")) {
//            fileName = fileName.replace("/", "\\");
//        }
//        String filePath = (file.getAbsolutePath() + "/" + fileName + ".png");
//        try {
//            out = new FileOutputStream(filePath);
//            MainActivityTabel mainActivityTabel = new MainActivityTabel();
//            qr.compress(Bitmap.CompressFormat.PNG, 100, out);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        Toast.makeText(Generate.this, "File saved at\n" + filePath, Toast.LENGTH_SHORT).show();
//    }

