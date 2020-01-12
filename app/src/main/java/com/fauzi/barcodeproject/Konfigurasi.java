package com.fauzi.barcodeproject;

public class Konfigurasi {
        //Dibawah ini merupakan Pengalamatan dimana Lokasi Skrip CRUD PHP disimpan
        //Pada tutorial Kali ini, karena kita membuat localhost maka alamatnya tertuju ke IP komputer
        //dimana File PHP tersebut berada
        //PENTING! JANGAN LUPA GANTI IP SESUAI DENGAN IP KOMPUTER DIMANA DATA PHP BERADA
        //192.168.101.46 CIRADIO
        //192.168.43.243 IP HP SAMSUNG
//        public static final String URL_ADD="http://192.168.101.46/administrator/android/tambah.php";
//        public static final String URL_ADD_PESERTA="http://192.168.101.46/administrator/android/tambahpeserta.php";
//        public static final String URL_GET_ALL = "http://192.168.101.46/administrator/android/tampilsemua.php";
//        public static final String URL_GET_EMP = "http://192.168.101.46/administrator/android/tampil.php?id=";
//        public static final String URL_UPDATE_EMP = "http://192.168.101.46/administrator/android/update.php";
//        public static final String URL_DELETE_EMP = "http://192.168.101.46/administrator/android/hapus.php?id=";

        public static final String URL_ADD="http://192.168.43.243/administrator/android/tambah.php";
        public static final String URL_ADD_PESERTA="http://192.168.43.243/administrator/android/tambahpeserta.php";
        public static final String URL_ADD_ABSEN="http://192.168.43.243/administrator/android/absen.php";
        public static final String URL_ADD_ABSEN_PULANG="http://192.168.43.243/administrator/android/absenpulang.php";
        public static final String URL_GET_ALL = "http://192.168.43.243/administrator/android/tampilsemua.php";
        public static final String URL_GET_PESERTA = "http://192.168.43.243/administrator/android/tampil.php?id=";
        public static final String URL_GET_EMP = "http://192.168.43.243/administrator/android/tampil.php?id=";
        public static final String URL_UPDATE_EMP = "http://192.168.43.243/administrator/android/update.php";
        public static final String URL_DELETE_EMP = "http://192.168.43.243/administrator/android/hapus.php?id=";

        //Dibawah ini merupakan Kunci yang akan digunakan untuk mengirim permintaan ke Skrip PHP
        public static final String KEY_EMP_ID = "id";
        public static final String KEY_EMP_NOMOR = "nomor";
        public static final String KEY_EMP_NAMA = "nama";
        public static final String KEY_EMP_JENIS = "jenis";
        public static final String KEY_EMP_NO = "no";
        public static final String KEY_EMP_KELOMPOK = "kelompok";
        public static final String KEY_EMP_REGULER = "reguler";
        public static final String KEY_EMP_MENTOR = "mentor";



        //desg itu variabel untuk posisi

        //JSON Tags
        public static final String TAG_JSON_ARRAY="result";
        public static final String TAG_ID = "id";
        public static final String TAG_NAMA = "nama";
        public static final String TAG_JENIS = "jenis";
        public static final String TAG_NO = "no";
        public static final String TAG_KELOMPOK = "kelompok";
        public static final String TAG_REGULER = "reguler";

        //ID karyawan
        //emp itu singkatan dari Employee
        public static final String EMP_ID = "emp_id";
    }
