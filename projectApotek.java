import java.util.Scanner;
import java.util.Random;
public class projectApotek {
    
    static String [] namaObat = {"Aspirin", "Paramex", "Komix", "Oralit", "Decolgen"};
    static int[] hargaObat = {10000, 3000, 4000, 5000, 6000};
    static int[] stokObat = {15, 20, 27, 17, 25};
    static String[] namaObatMasuk = new String[10];
    static int[] jumlahObatMasuk = new int[10];
    static int indexMasuk = 0;
    static String[] obatDibeli;
    static int[] jumlahObatDibeli;
    static int index;
    static int totalKeseluruhan = 0;
    static double hasilDiskon;
    static String[] namaMember;
    static String[] alamatMember;
    static String[] nomorTeleponMember;
    static int jumlahMember;
    static boolean punyaKartuMember = false;
    
    static void tambahStokObat() {
        Scanner inputkan = new Scanner(System.in);
        boolean tambahStokLagi = true;
        
        while(tambahStokLagi){
        System.out.print("Masukkan Nama Obat: ");
        String namaObatnya = inputkan.nextLine();
        System.out.print("Masukkan Jumlah Tambahan: ");
        int tambahan = inputkan.nextInt();
        boolean obatAda = false;
        for(int i = 0; i < namaObat.length; i++) {
            if(namaObatnya.equalsIgnoreCase(namaObat[i])) {
                obatAda = true;
                stokObat[i] += tambahan;
                namaObatMasuk[indexMasuk] = namaObatnya;
                jumlahObatMasuk[indexMasuk] = tambahan;
                indexMasuk++;
                System.out.println("Penambahan Stok Obat Berhasil");
            }
            
        }
        if(obatAda == false) {
            System.out.println("Obat Tidak Ada");
        }
        
        System.out.print("Apakah Ada Penambahan Stok Lagi(y/n)? ");
        String jawaban = inputkan.next();
        inputkan.nextLine();
        if(jawaban.equalsIgnoreCase("n")) {
            tambahStokLagi = false;
            }
        }
    }
    
     static void tambahkanObatBaru(String nama, int harga, int stok) { 
            // Menambah Panjang Array
            String[] namaObatBaru = new String[namaObat.length + 1];
            int[] hargaObatBaru = new int[hargaObat.length + 1];
            int[] stokObatBaru = new int[stokObat.length + 1];
         
            // Menyalin ke Array baru
            for (int i = 0; i < namaObat.length; i++) {
               namaObatBaru[i] = namaObat[i];
               hargaObatBaru[i] = hargaObat[i];
               stokObatBaru[i] = stokObat[i];
            }
            
            // Menambahkan Obat Baru ke Array
            namaObatBaru[namaObat.length] = nama;
            hargaObatBaru[hargaObat.length] = harga;
            stokObatBaru[stokObat.length] = stok;

            // Array Baru
            namaObat = namaObatBaru;
            hargaObat = hargaObatBaru;
            stokObat = stokObatBaru;
         
            System.out.println("Obat Berhasil Ditambahkan");
            
       }
    
    static void tampilkanObat() {
        System.out.println("======== DAFTAR OBAT ========");
        System.out.println("Nama Obat\tHarga\t " + " Stok");
        for(int i = 0; i < namaObat.length; i++) {
            System.out.printf("%-10s \t%-10d %-5d\n", namaObat[i], hargaObat[i], stokObat[i]);
        }
    }
    
    static void beliObat(String nama, int jumlahYangDibeli) {
        if(obatDibeli == null) {
           obatDibeli = new String[namaObat.length];
           jumlahObatDibeli = new int[namaObat.length];
           index = 0;
        }
        
        boolean obatAda = false;
        for (int i = 0; i < namaObat.length; i++) {
            if (nama.equalsIgnoreCase(namaObat[i])) {
                obatAda = true;
                if (stokObat[i] >= jumlahYangDibeli) {
                    stokObat[i] -= jumlahYangDibeli;
                    obatDibeli[index] = nama;
                    jumlahObatDibeli[index] = jumlahYangDibeli;
                    index++;
                } else {
                    System.out.println("Stok Tidak Cukup!");
                    break;
                }

            } 
        }

        if (obatAda == false) { 
            System.out.println("Obat Tidak Ada");
        }
    }
    
    static String kodePembelian() {
        int panjangKode = 5;
        String hurufAngka = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder coba = new StringBuilder(panjangKode);
        Random random = new Random();
        
        for(int i = 0; i < panjangKode; i++) {
            int indexIsi = random.nextInt(hurufAngka.length());
            coba.append(hurufAngka.charAt(indexIsi));
        }
        return coba.toString();
    }

    static void tampilkanObatDibeli(String namaPembeli) {
        System.out.println("========= APOTEK FIRMA ==========");
        System.out.println("Kode Pembelian: " + kodePembelian());
        System.out.println("Atas Nama: " + namaPembeli);
        System.out.println("Daftar Pembelian: ");
        System.out.println("=================================");
        System.out.printf("%-10s \t%-10s %-5s\n", "Nama Obat", "Jumlah", "Harga");
        System.out.println("=================================");
        for (int i = 0; i < index; i++) {
            int hargaTiapObat = hitungTotalPerObat(i);
            System.out.printf("%-10s \t%-10d %-5d\n", obatDibeli[i], jumlahObatDibeli[i], hargaTiapObat);
            System.out.println("---------------------------------");
        }
        totalKeseluruhan = hitungTotal();
        if(punyaKartuMember) {
            hasilDiskon = 0.2 * totalKeseluruhan;
        } else {
            hasilDiskon = 0;
        }
        double totalAkhir = totalKeseluruhan - hasilDiskon;
        System.out.println("Total Keseluruhan\t   " + totalKeseluruhan);
        System.out.printf("Diskon\t\t\t   %.0f\n", hasilDiskon);
        System.out.printf("Total Akhir\t\t   %.0f\n", totalAkhir);
        System.out.println("=================================");
        System.out.println("Terima Kasih Atas Pembeliannya\n");
    }
    
    static int hitungTotalPerObat(int indexObat) {
        int hargaTiapObat = 0;
        for (int i = 0; i < namaObat.length; i++) {
            if (obatDibeli[indexObat].equalsIgnoreCase(namaObat[i])) {
                hargaTiapObat = hargaObat[i] * jumlahObatDibeli[indexObat];
                break;
            }
        }
        return hargaTiapObat;
    }
    
    static int hitungTotal() {
        int total = 0;
        for (int i = 0; i < index; i++) {
            total += hitungTotalPerObat(i);
        }
        return total;
    }
    
    static void daftarMember() {
        Scanner inputMember = new Scanner(System.in);
        namaMember = new String[3];
        alamatMember = new String[3];
        nomorTeleponMember = new String[3];
        jumlahMember = 0;
        
        System.out.print("Masukkan Nama: ");
        String nama = inputMember.next();
        inputMember.nextLine();
        System.out.print("Masukkan Alamat: ");
        String alamat = inputMember.next();
        inputMember.nextLine();
        System.out.print("Masukkan Nomor Telepon: ");
        String nomorTelp= inputMember.next();
        inputMember.nextLine();
        
        if(jumlahMember < namaMember.length) {
            namaMember[jumlahMember] = nama;
            alamatMember[jumlahMember] = alamat;
            nomorTeleponMember[jumlahMember] = nomorTelp;
            jumlahMember++;
            System.out.println("Member Berhasil Didaftarkan\n");
        } 
    }

    static void verifikasiMember(String nama, String noTelp) {
        boolean terdaftar = false;
        for(int i = 0; i < jumlahMember; i++) {
            if(nama.equalsIgnoreCase(namaMember[i]) && noTelp.equals(nomorTeleponMember[i])) {
                System.out.println("Member Telah Terdaftar!!!");
                System.out.println("======= Daftar Member =======");
                System.out.println("Nama: " + namaMember[i]);
                System.out.println("No Telepon: " + nomorTeleponMember[i]);
                System.out.println("Alamat: " + alamatMember[i]);
                System.out.println();
                terdaftar = true;
                break;
            } 
        }
        if(terdaftar == false) { 
            System.out.println("Member Tidak Terdaftar!");
            punyaKartuMember = false;
            System.out.println();
        }
    }
    
    static void tampilkanLaporanStok() {
        if(index == 0 && indexMasuk == 0) {
            System.out.println("Tidak Ada Stok Yang Keluar Ataupun Masuk!!!");
        } else {
            System.out.println("\n======= LAPORAN STOK OBAT =======");
            System.out.println("Nama Obat   Stok Keluar   Stok Masuk");
            for (int i = 0; i < index; i++) {
                boolean stokGabungan = false;
                for (int j = 0; j < indexMasuk; j++) {
                    if (obatDibeli[i].equalsIgnoreCase(namaObatMasuk[j])) {
                        System.out.println(obatDibeli[i] + "\t\t" + jumlahObatDibeli[i] + "\t\t" + jumlahObatMasuk[j]);
                        stokGabungan = true;
                        break;
                    }
                }
                if (!stokGabungan) {
                    System.out.println(obatDibeli[i] + "\t\t" + jumlahObatDibeli[i] + "\t\t-");
                }
            }

            for (int j = 0; j < indexMasuk; j++) {
                boolean obatBaruDitambah = true;
                for (int i = 0; i < index; i++) {
                    if (namaObatMasuk[j].equalsIgnoreCase(obatDibeli[i])) {
                        obatBaruDitambah = false;
                        break;
                    }
                }
                if (obatBaruDitambah) {
                    System.out.println(namaObatMasuk[j] + "\t\t-\t\t" + jumlahObatMasuk[j]);
                }
            }
        }
        System.out.println();
    }
      
    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int pilihan = 0;
        
        while(pilihan!=7) {
            System.out.println("===== APOTEK FIRMA =====");
            System.out.println("1. Tampilkan Daftar Obat");
            System.out.println("2. Tambah Stok Obat");
            System.out.println("3. Tambah Obat Baru");
            System.out.println("4. Beli Obat");
            System.out.println("5. Daftar Member");
            System.out.println("6. Laporan Stok Obat");
            System.out.println("7. Keluar");
            System.out.print("Masukkan Piihan: ");
            pilihan = input.nextInt();
        
            switch(pilihan) {
                case 1:
                    tampilkanObat();
                    break;
            
                case 2:
                    tambahStokObat();
                    break;
            
                case 3:
                    boolean tambahLagi = true;
                    while(tambahLagi) {
                        System.out.print("\nMasukkan Nama Obat: ");
                        Scanner inputObat = new Scanner(System.in);
                        String namaBaru = inputObat.nextLine();
                        System.out.print("Masukkan Harga: ");
                        int hargaBaru = input.nextInt();
                        System.out.print("Masukkan Stok: ");
                        int stokBaru = input.nextInt();
                        tambahkanObatBaru(namaBaru, hargaBaru, stokBaru);
                        System.out.print("Tambahkan obat lagi(y/n)? ");
                        Scanner inputJawaban = new Scanner (System.in);
                        String jawaban = inputJawaban.nextLine();
                        if(jawaban.equalsIgnoreCase("n")) {
                            tambahLagi = false;
                        }
                    }
                    break;
            
                case 4:
                    Scanner ada1 = new Scanner(System.in);
                    System.out.print("Apakah Ada Resep Dokter (y/n)? ");
                    String ada = ada1.nextLine();

                    boolean tambahPembelian = true;
                    System.out.print("Masukkan Nama Pasien/Pembeli: ");
                    String namaPembeli= input.next();
                    input.nextLine();
                    while (tambahPembelian) {
                        if (ada.equalsIgnoreCase("y")) {
                            System.out.print("Masukkan Obat Yang Tertera: ");
                            String obatTertera = input.next();
                            input.nextLine();
                            System.out.print("Masukkan Jumlah Yang Dibutuhkan: ");
                            int butuh = input.nextInt();
                            input.nextLine();
                            beliObat(obatTertera, butuh);
                        } else {
                            System.out.print("Masukkan Obat Yang Ingin Dibeli: ");
                            String obatBeli = input.next();
                            input.nextLine();
                            System.out.print("Masukkan Jumlah Yang Dibutuhkan: ");
                            int butuh = input.nextInt();
                            input.nextLine();
                            beliObat(obatBeli, butuh);
                        }

                        System.out.print("Apakah Ada Yang Ingin Dibeli Lagi (y/n)? ");
                        String tambahAtauTidak = input.nextLine();
                        if (tambahAtauTidak.equalsIgnoreCase("n")) {
                            tambahPembelian = false;
                            System.out.println("Pembelian Berhasil!");
                        }
                    }    
                    System.out.print("Apakah Memiliki Kartu Member (y/n)? ");
                    String kartuMember = input.nextLine();
                    if(kartuMember.equalsIgnoreCase("n")) {
                    System.out.print("Apakah Ingin Mendaftar (y/n)? ");
                    String daftar = input.nextLine();
                    System.out.println();
                        if(daftar.equalsIgnoreCase("y")) {
                            punyaKartuMember = true;
                            daftarMember();
                        } else {
                            punyaKartuMember = false;
                        }
                    } else {
                        punyaKartuMember = true;
                        System.out.println("\nTolong Verifikasi Terlebih Dahulu!!!");
                        System.out.print("Masukkan Nama Member: ");
                        String inputNama = input.next();
                        input.nextLine();
                        System.out.print("Masukkan Nomor Telepon:");
                        String inputNoTelp = input.next();
                        input.nextLine();
                        verifikasiMember(inputNama, inputNoTelp);
                    }
                    tampilkanObatDibeli(namaPembeli);
                    break;

                case 5:
                    punyaKartuMember = true;
                    daftarMember();
                    break;
                    
                case 6: 
                    tampilkanLaporanStok();
                    break;
                
                case 7:
                    System.out.println("Terima Kasih telah Menggunakan Aplikasi Apotek Firma\nJangan Lupa Berikan Feedback Kepada Kami^_^");
                    break;
                    
                default:
                    System.out.println("Masukkan Pilihan yang benar");
                }
        }
        
    }
}