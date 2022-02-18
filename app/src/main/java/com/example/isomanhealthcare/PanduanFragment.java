package com.example.isomanhealthcare;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PanduanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PanduanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView head2, head3;

    View v;
    RecyclerView recyclerView;
    List<PanduanAccordion> panduanList;
    PanduanAdapter panduanAdapter;

    public PanduanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PanduanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PanduanFragment newInstance(String param1, String param2) {
        PanduanFragment fragment = new PanduanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_panduan, container, false);
        recyclerView = v.findViewById(R.id.recyclerViewId);

        panduanAdapter = new PanduanAdapter(panduanList, getContext());
        recyclerView.setAdapter(panduanAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        long date = System.currentTimeMillis();
        Locale locale = new Locale("id", "ID");
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy", locale);
        String dateString = sdf.format(date);
        head3 = v.findViewById(R.id.head3);
        head3.setText(dateString);

        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        panduanList = new ArrayList<>();

        panduanList.add(new PanduanAccordion("5 Tips Tetap Sehat dan Bugar Saat Isolasi Mandiri", "<strong>1. Rutin olahraga</strong><br>Orang dewasa direkomendasikan untuk melakukan 150 menit aktivitas fisik intensitas sedang atau 75 menit aktivitas fisik intensitas kuat per minggu.\n <br>" +
                "\n" +
                "Aktivitas apapun lebih baik dibandingkan tidak bergerak sama sekali. Lebih banyak aktivitas, berarti lebih banyak manfaat kesehatan fisik dan mental yang didapat.\n <br>" +
                "\n" +
                "Selama isolasi mandiri, cukup lakukan olahraga ringan di dalam rumah. Aktivitas seperti membersihkan rumah, membuat makanan, dan sebagainya juga dapat membantu tubuh untuk tetap bergerak. <br><br><strong>2. Istirahat Secara Teratur</strong><br>Tips kedua untuk tetap sehat dan bugar selama isolasi mandiri adalah beristirahat secara teratur dari duduk terus-menerus di depan layar komputer setiap 20 hingga 30 menit.\n <br>" +
                "\n" +
                "Misalnya, ambil jeda di sela-sela pekerjaanmu untuk berjalan-jalan di sekitar rumah, bermain dengan hewan peliharaan, atau menyiram tanaman. <br><br><strong>3. Latihan kekuatan</strong><br> Sebuah penelitian di Inggris pada tahun 2017 lalu menemukan, latihan kekuatan yang dilakukan di rumah, seperti press up dan sit up, memberikan manfaat yang sama seperti latihan aerobik.\n <br>" +
                "\n" +
                "Lakukan setidaknya beberapa sesi latihan berat bedan per minggu dengan setiap sesinya melibat dua hingga empat set delapan hingga 15 kali pengulangan.\n <br>" +
                "\n" +
                "Pastikan untuk beristiriahat selama dua hingga tiga menit di antara set untuk menjaga keseimbangan energi. <br><br><strong>4. Tetap terhubung dengan keluarga</strong><br> Meski tengah melakukan isolasi mandiri dan mungkin diharuskan berada di kamar yang terpisah dari anggota keluarga lain, perasaan tetap terhubung penting untuk dijaga.\n <br>" +
                "\n" +
                "Dengan cara ini, seseorang tidak akan merasa sendiri dan kesepian saat menjalani isolasi mandiri. <br><br><strong>5. Lakukan saja sesuatu!</strong><br> Tujuan isolasi mandiri adalah menjaga kesehatan dan mencegah penularan Covid-19, namun fungsi tubuh harus tetap aktif selama masa ini.\n <br>" +
                "\n" +
                "Bergeraklah sesering mungkin, entah melakukan olahraga, mengerjakan pekerjaan rumah, atau menari dengan lagu kesukaan.\n <br>" +
                "\n" +
                "Dengan tetap aktif bergerak, kesehatan dan kebugaran tubuh dapat terjaga selama isolasi mandiri. <br>"));
        panduanList.add(new PanduanAccordion("2 Kriteria Sembuh dari Covid-19 untuk OTG dan Pasien Gejala Ringan", "Dokter spesialis penyakit dalam di Surabaya, <strong>dr. RA Adaninggar, SpPD</strong> mengatakan, ada dua syarat yang harus dipenuhi agar <strong>pasien Covid-19</strong> dapat dikatakan telah sembuh.\n <br>" +
                "\n" +
                "Kedua syarat yang dimaksud adalah sudah melewati masa penularan dan gejala klinis sudah hilang.\n" +
                "\n" +
                "Menurut <strong>WHO dan Kemenkes</strong>, masa penularan Covid-19 adalah 10 hari. Oleh sebab itu, pasien dengan gejala ringan harus melakukan isolasi selama 10 hari ditambah 3 hari untuk memastikan telah bebas gejala. <br><br> Berbeda dengan OTG dan gejala ringan, pasien Covid-19 dengan gejala sedang dan berat perlu bantuan dokter untuk mengetahui sudah atau belum sembuh dari virus Corona. <br> Dokter akan memeriksa kondisi pasien secara umum dan melakukan pemeriksaan penunjang, seperti foto ronsen, pemeriksaan lab, dan terkadang pemeriksaan PCR evaluasi.<br>"));
        panduanList.add(new PanduanAccordion("Panduan Isolasi Mandiri: Syarat, Lama Durasi, dan Cara Dapatkan Obat Gratis", "<strong>Syarat isolasi mandiri</strong><br><br> Bagi pasien yang hendak melakukan isolasi mandiri harus memenuhi syarat klinis dan syarat rumah sebagai berikut: <br><br> Syarat klinis dan perilaku\n <br><br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; usia kurang dari 45 tahun\n <br>" +
                "&#160;&#160;&#160; &#149; tidak memiliki komorbid\n <br>" +
                "&#160;&#160;&#160; &#149; dapat mengakses telemedisin atau layanan kesehatan lainnya dan\n <br>" +
                "&#160;&#160;&#160; &#149; berkomitmen untuk tetap diisolasi sebelum diizinkan keluar. <br><br> Syarat rumah dan peralatan pendukung lainnya\n <br><br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; dapat tinggal di kamar terpisah, lebih baik lagi jika lantai terpisah\n <br>" +
                "&#160;&#160;&#160; &#149; ada kamar mandi di dalam rumah terpisah dengan penghuni rumah lainnya dan\n <br>" +
                "&#160;&#160;&#160; &#149; dapat mengakses pulse oksimeter. <br><br> <strong>Lama durasi</strong><br><br>\n Mengacu pada Surat Edaran Kementerian Kesehatan Nomor HK.02.01/Menkes/18/2022, durasi isolasi pasien Covid-19 berbeda-beda, bergantung pada kondisi pasien.\n<br>" +
                "\n" +
                "Pada kasus konfirmasi Covid-19 yang tidak bergejala (asimptomatik), isolasi dilakukan selama minimal 10 hari sejak pengambilan spesimen diagnosis konfirmasi.\n <br>" +
                "\n" +
                "Kemudian, pada kasus dengan gejala, isolasi dilakukan selama 10 hari sejak muncul gejala, ditambah dengan 3 hari bebas gejala demam dan gangguan pernapasan.\n <br>" +
                "\n" +
                "Dengan demikian, untuk kasus-kasus yang mengalami gejala selama 10 hari atau kurang harus menjalani isolasi selama 13 hari. <br> Apabila masih terdapat gejala setelah hari ke-10, maka isolasi mandiri masih tetap dilanjutkan sampai dengan hilangnya gejala tersebut ditambah 3 hari.\n <br>" +
                "\n" +
                "Kemudian, pada kasus konfirmasi Covid-19 yang sudah mengalami perbaikan klinis pada saat isoman/isoter, dapat dilakukan pemeriksaan nucleid acid amplufication test (NAAT), termasuk pemeriksaan RT-PCR pada hari ke-5 dan ke-6 dengan selang waktu pemeriksaan 24 jam.\n <br>" +
                "\n" +
                "Jika hasil negatif atau Ct>35 sebanyak 2 kali berturut-turut, maka dapat dinyatakan selesai isolasi/sembuh.\n <br>" +
                "\n" +
                "Selanjutnya, pada kasus konfirmasi Covid-19 yang sudah mengalami perbaikan klinis pada saat isoman/isoter tetapi tidak dilakukan pemeriksaan NAAT termasuk pemeriksaat RT-PCR pada hari ke-5 dan ke-6 dengan selang waktu 24 jam, maka pasien harus melakukan isolasi selama 13 hari. <br><br> <strong>Cara dapatkan obat gratis\n</strong><br><br> Kementerian Kesehatan juga telah menyediakan layanan konsultasi kesehatan atau telemedisin bagi pasien Covid-19 yang menjalani isolasi mandiri.\n <br> Melalui layanan tersebut, pasien virus corona, khususnya yang berdomisili di Jabodetabek, bisa mendapatkan layanan telekonsultasi dan paket obat gratis.<br>" +
                "\n Saat ini, Kemenkes telah bekerja sama dengan 17 platform telemedisin yaitu Aido Health, Alodokter, GetWell, Good Doctor, Halodoc, Homecare24, KlikDokter, KlinikGo.\n <br>" +
                "\n" +
                "Lalu ada Lekasehat, LinkSehat, Mdoc, Milvik Dokter, ProSehat, SehatQ, Trustmedis, Vascular Indonesia, dan YesDok.\n <br> Untuk mendapatkan layanan ini, pasien harus melakukan tes PCR di laboratorium yang telah terafiliasi dengan sistem New All Record (NAR) Kementerian Kesehatan.<br>" +
                "\n"));
        panduanList.add(new PanduanAccordion("Trik Tetap Bahagia Saat Isolasi Mandiri", "<strong>1. Habiskan Waktu Bersama Keluarga</strong><br>Saat anak-anak belajar dari rumah, pasangan Anda bekerja dari rumah. Ini adalah kesempatan bagus untuk menghabiskan waktu bersama keluarga. Terlebih moment seperti ini mungkin jarang terjadi. Lakukan aktivitas menyenangkan yang biasanya Anda tidak punya waktu banyak untuk hal itu. Misalnya, cobalah mengerjakan teka-teki bersama anak-anak. Selain itu, Anda bisa juga mengajak keluarga untuk barbeque memasak makanan favorit keluarga. Buat suasana nyaman dan lakukan percakapan dari hati ke hati saat santai bersama.<br><br>\n" +
                "\n <strong>2. Bersosialisasi Virtual</strong><br> Siapa bilang saat social distancing tidak bisa bersosialisasi? Jika Anda merasa terisolasi kuncinya ya sosialisasi. Merasa terhubung dengan orang lain mencegah rasa kesepian dan stres. Anda masih bisa bertegur sapa lewat teknologi virtual. Dengan menfaatkan teknologi terkini semuanya jadi semakin mudah. Redakan rasa cemas Anda dan ubah pola pikir agar hati menjadi tenang. Pertama, ganti istilah “jarak sosial” dengan “jarak fisik”. Ingatkan diri Anda bahwa kita semua masih bisa terhubung, meskipun terpisah. Selanjutnya, anggap saja Anda sedang “bersosialisasi jauh”. Pastikan Anda masih bisa berkumpul dengan teman dan keluarga secara online. Anda bisa menghubungi melalui ponsel, bercakap-cakap dengan angkat telepon dan menyapa. Sekarang juga ada aplikasi yang memudahkan percakapan sambil bertatap muka. Selain itu, Anda juga bisa hubungi kerabat melalui media sosial. Kirim email teks bisa juga sebagai perantara Anda untuk bersosialisasi. Jadi, saat isolasi mandiri Anda tidak merasa sepi dan masih bisa dikelilingi orang terdekat. <br><br> \n" +
                "\n <strong>3. Bantu Orang Lain</strong><br> Apa lagi yang bisa Anda lakukan untuk mengusir rasa kesepian? Yuk, coba lakukan hal positif dengan begitu hati pun ikut merasakan manfaatnya. Membantu orang lain tentu menguntungkan semua orang. Dengan memberi dukungan pada orang lain, Anda akan merasa menjadi bagian dan memiliki tujuan. Tawarkan bantuan untuk mengantarkan makanan kepada orang lain yang tidak dapat meninggalkan rumah. Atau coba temukan organisasi lokal yang menerima donasi.\n <br><br>" +
                "\n <strong>4. Ubah Pola Pikir</strong><br> Masa karantina atau isolasi mandiri bisa digunakan sebagai bahan renungan. Bagaimana Anda mengubah pola pikir menjadi lebih baik dari hari ke hari. Hindari terlalu memikirkan masa depan atau skenario terburuk. Ramalan bisa memicu kecemasan. Daripada mengatakan, “Saya tidak akan pernah pulih,” katakan pada diri sendiri, “Saya akan berhasil melalui ini.”Coba perhatian penuh. Tonton video tentang meditasi terpandu dan yoga. Ada banyak aplikasi yang dapat diunduh yang dapat Anda gunakan. <br><br> \n" +
                "\n <strong>5. Bersyukur</strong><br> Studi menunjukkan bahwa menemukan sesuatu untuk disyukuri setiap hari dapat meningkatkan suasana hati. Jadi catat sesuatu. Mungkin responden pertama dan pekerja layanan yang menjaga kita tetap aman. Mungkin itu keluarga, teman, dan atap di atas kepala Anda.<br><br>"));
        panduanList.add(new PanduanAccordion("Saran Ahli Gizi soal Makanan yang Harus Dikonsumsi Pasien Covid-19", "Dr Tan menjelaskan, Kementerian Kesehatan (Kemenkes) sebenarnya sudah mengeluarkan panduan yang sangat jelas terkait asupan gizi seimbang, yang harus dikonsumsi oleh setiap individu, baik yang sehat maupun yang sedang sakit.\n <br><br>" +
                "\n" +
                "Dalam panduan Kemenkes tentang makanan gizi seimbang, yaitu dalam satu porsi piring terdapat empat isi sebagai berikut.\n<br><br>" +
                "\n" +
                "&#160;&#160;&#160; 1. Makanan pokok seperti nasi, ubi, singkong, jagung dan lain sebagainya.\n<br><br>" +
                "\n" +
                "&#160;&#160;&#160; 2. Lauk-pauk seperti ikan, daging ayam, daging sapi, telur, dan lain sebagainya. \n<br><br>" +
                "\n" +
                "&#160;&#160;&#160; 3. Buah-buahan, sangat lebih baik dimakan dengan cara digigit langsung bukan di jus dengan campuran kental manis ataupun gula\n<br><br>" +
                "\n" +
                "&#160;&#160;&#160; 4. Sayuran, semua jenis sayuran, baik untuk tubuh jika dicuci bersih dan di masak dengan cara yang tepat\n<br><br>" +
                "\n" +
                "Perlu juga diingat, sayur dan buah harus dicuci dengan bersih dahulu, lauk-pauk di masak dengan matang, dan upayakan untuk menghindari gula, garam dan lemak yang berlebihan.<br><br>"));
        panduanList.add(new PanduanAccordion("Aktivitas yang Menghilangkan Rasa Jenuh Saat Isolasi Mandiri", "1. Menonton film kesukaan<br>2. Bermain game favorit<br>3. Membersihkan ruangan<br>4. Melakukan kegiatan sesuai hobi<br>5. Berkebun<br>"));
        panduanList.add(new PanduanAccordion("7 Sumber Belajar Digital yang Disediakan Gratis untuk Pembelajaran selama isolasi mandiri", "<strong>1. Rumah Belajar</strong><br>Platform ini merupakan keluaran dari Kemendikbud sendiri. Rumah Belajar merupakan portal yang menyediakan bahan belajar, fasilitas komunikasi yang mendukung interaksi antar komunitas. Rumah Belajar hadir sebagai bentuk inovasi pembelajaran di era industri 4.0 yang dapat dimanfaatkan oleh siswa dan guru Pendidikan Anak Usia Dini (PAUD), Sekolah Dasar (SD), Sekolah Menengah Pertama (SMP), Sekolah Menengah Atas/Kejuruan (SMA/SMK) sederajat.\n<br>" +
                "\n" +
                "Rumah belajar dapat diakses secara gratis, kapan pun, dimana pun dan oleh siapa pun. Adanya portal ini membantu semua kalangan dalam mendapatkan pendidikan dengan mudah, praktis, dan lengkap.\n<br>" +
                "\n" +
                "Di Rumah Belajar telah tergabung sebanyak 600 ribuan siswa dan 200 ribuan guru yang pasti nantinya akan terus bertambah. Tak hanya kelas online, di sana juga terdapat laboratorium maya, bank soal, dan bahan belajar lainnya.<br><br><strong>2. SIAJAR</strong><br>SIAJAR merupakan penghubung sekaligus pengintegrasi sumber belajar sehingga guru dapat mengelola pembelajaran dengan aman dan cepat. SIAJAR dirancang untuk memberikan kemudahan tampilan sekaligus kontrol dalam pelaksanaan kelas digital untuk SMA. Sebagai Learning Management System (LMS), SIAJAR menyediakan kelengkapan pembelajaran dari perancangan, pelaksanaan pembelajaran, sampai ke penilaian.\n<br>" +
                "\n" +
                "SIAJAR hanya memfasilitasi materi beserta soal yang dikembangkan oleh guru untuk melatih siswa dalam menghadapi ujian nasional yang sesungguhnya. Hal tersebut dikarenakan Penilaian Tengah Semester (PTS), Penilaian Akhir Tahun (PAT), dan Penilaian Akhir Semester (PAS) diselenggarakan di sekolah.\n<br>" +
                "\n" +
                "Karena dirancang untuk siswa SMA, maka tutor/gurunya lebih sedikit dibanding platform Rumah Belajar, yakni sekitar 20 ribuan pengajar, hampir 90 ribuan peserta didik, terdapat 19 ribuan kelas, dan 3 ribuan bahan ajar. Sebelum menggunakan SIAJAR, kamu wajib mendaftar terlebih dahulu baru bisa menggunakannya.<br><br><strong>3. Suara Edukasi</strong><br> Salah satu platform belajar digital yang bertajuk radio ialah Suara Edukasi. Siaran Radio Pendidikan dengan nama Suara Edukasi, seperti yang dilansir dari website resminya, telah diselenggarakan Pustekkom sejak bulan Januari 2009. Siaran Suara Edukasi bertujuan menjadi sebuah siaran radio yang dapat dijadikan sebagai media alternatif sumber belajar dalam rangka mencapai tujuan pendidikan Nasional. Suara Edukasi merupakan sebuah media massa radio dengan citra yang edukatif, membagi wawasan dan berkonsep pendidikan.\n<br><br>" +
                "\n" +
                "Terdapat berbagai program acara, antara lain:\n<br><br>" +
                "\n" +
                "<strong>a. Sapa Edu\n</strong><br><br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Menyajikan program acara menarik, menghibur, dan mendidik anak-anak.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Mengenalkan musik dan lagu daerah.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Memberi penyuluhan berupa informasi yang diberikan oleh penyiar acara.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Menanamkan kepribadian dan pengetahuan anak.\n <br><br>" +
                "\n" +
                "<strong>b. Kita Perlu Tahu\n</strong><br><br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Sebuah program yang menyajikan hiburan dan informasi pengetahuan umum.\n<br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Memutar lagu-lagu hits terkini.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Menyajikan beragam tips, motivasi, dan informasi singkat seputar isu terkini.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Memberi apresiasi berbagai pengetahuan kepada pendengar.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Memberi pengetahuan bagi pendengar dalam hal pengetahuan-pengetahuan umum.\n <br><br>" +
                "\n" +
                "<strong>c. Talk Show “Bintang Edu”\n </strong><br><br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Menyajikan informasi seperti kebijakan, kegiatan, dan peringatan hari besar nasional yang berhubungan dengan dunia pendidikan.\n<br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Memperluas informasi melalui proses diskusi.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Memberikan informasi kepada masyarakat pendidik dan siswa mengenai seputar dunia pendidikan.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Media diskusi dan seputar dunia pendidikan.\n <br><br>" +
                "\n" +
                "<strong>d. Suara Rohani (Setiap Hari Jumat)\n</strong><br><br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Mengenalkan sifat baik, buruk, terpuji, dan tercela.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Menanamkan pesan moral yang sesuai dengan jiwa agama.\n <br>" +
                "\n" +
                "&#160;&#160;&#160; &#149; Menanamkan keteladanan akhlak dan budi pekerti guna mendukung pembinaan kepribadian anak.<br><br><strong>4. Emodul dari Kemendikbud</strong><br>Mendapatkan modul pembelajaran kini lebih mudah, baik bagi tenaga pengajar maupun bagi siswa. Modul elektronik ini bisa diakses melalui website resminya dan diunduh dengan mudah.\n<br>" +
                "\n" +
                "Terdapat beberapa paket yang disediakan website tersebut. Selain modul bahan ajar secara umum, terdapat juga modul kurikulum, kewirausahaan dan prakarya, serta modul peminatan lainnya.\n<br>" +
                "\n" +
                "Dengan adanya E-modul ini yang mana terdapat ratusan file, akan mempermudah para peserta didik yang mungkin kurang mampu untuk memiliki modul cetak. Siapapun dapat menjangkau bahan belajar dengan menggunakan satu tools yakni gadget. E-modul juga memiliki sifat ramah lingkungan karena tidak mengorbankan kayu untuk menjadi ratusan bahkan ribuan eksemplar bahan ajar.<br><br><strong>5. Sumber Belajar</strong><br>Sesuai dengan namanya “Sumber Belajar” merupakan website penyedia bahan ajar gratis untuk warga Indonesia. Masih dalam rekomendasi Kemendikbud, Sumber Belajar juga mengharuskan para pengunduh untuk “Log In” terlebih dahulu sebelum mengakses seluruh fiturnya. Bahan ajar yang disediakan juga dari berbagai tingkat pendidikan, dari PAUD hingga SMA/SMK.\n<br>" +
                "\n" +
                "Hal yang menjadikan portal ini unik yakni adanya Virtual Reality (VR) berbagai tempat yang edukatif seperti museum, tempat-tempat bersejarah, beberapa kampus ternama, dan lainnya. Selain itu terdapat juga beberapa video hasil tugas siswa-siswa dari berbagai daerah, seperti cara membuat small garden di rumah atau presentasi tentang global warming.<br><br><strong>6. Setara Daring</strong><br>Tak henti-hentinya dibuat takjub oleh Kemendikbud, kini ada aplikasi Setara Daring di Playstore dan App Store. Tak hanya itu, Setara Daring juga bisa digunakan di laptop, tablet, dan lainnya.\n<br>" +
                "\n" +
                "Selain untuk belajar melalui daring, di aplikasi ini juga terdapat e-modul yang bisa diunduh secara gratis. Terdapat juga akses untuk penugasan modul bagi para siswa. Akses mata pelajaran dan kelas juga dikemas dengan begitu mudahnya sehingga dalam satu genggaman, kamu bisa belajar dengan mudah.\n<br>" +
                "\n" +
                "Lebih dari seratus ribu user telah menggunakan aplikasi ini dan belajar di sana dengan tutor yang lebih dari dari 18 ribu. Terdapat tiga paket di Setara Daring, Paket A untuk sekolah dasar, paket B untuk sekolah menengah pertama, dan paket C untuk sekolah menengah atas.<br><br><strong>7. Ipusnas</strong><br>Perlunya menambah wawasan di luar jam sekolah membantu para peserta didik untuk menjalani kehidupannya sehari-hari. Ipusnas atau perpustakaan nasional berbasis internet kini dapat diakses di Playstore dan App Store ponsel kamu. Tidak hanya bahan belajar akademik, namun juga buku fiksi, hiburan, pengetahuan umum, biografi, dan segala jenis buku ada di sana.<br>Sistem peminjamannya ialah dengan klik “pinjam” dan bisa otomatis dikembalikan jika waktunya sudah habis. Biasanya juga terdapat antrian apabila buku itu terlalu laris dipinjam. Maka dari itu, kamu wajib sering refresh aplikasi Ipusnas-mu sebelum dipinjam user lain.\n<br>" +
                "\n" +
                "Itulah 7 referensi belajar digital yang dapat diakses oleh semua orang yang bisa menjadi bahan belajar tambahan secara mandiri. Kamu juga bisa menggunakan kejarcita.id untuk mendapatkan bahan ajar digital.<br>"));

//        getUser();

        String url = getString(R.string.api_server)+"/user";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Http http = new Http(getContext(), url);
                http.setToken(true);
                http.send();

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Integer code = http.getStatusCode();
                        if (code == 200) {
                            try {
                                JSONObject response = new JSONObject(http.getResponse());
                                String nama = response.getString("name");
                                String email = response.getString("email");
                                String created_at = response.getString("created_at");
                                head2 = v.findViewById(R.id.head2);
                                head2.setText(nama);
//                                tvEmail.setText(email);
//                                tvCreated.setText(created_at);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(getContext(), "Error "+code, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }).start();
    }
}