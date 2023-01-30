import java.sql.*;

public class JDBC01_Query01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1- Ilgili Driveri'ı yuklemeliyiz. MySQL kullandigimizi bildiriyoruz
        //Driver'i bulamama ihtimaline karşı bizden forName methodu için ClassNotFoundException'ı
        //method signature'a exception olarak fırlatmamızı öneriyor, bizde kabul ediyoruz

        Class.forName("com.mysql.cj.jdbc.Driver");

        //2- Baglantiyi olusturmak icin username ve password girmeliyiz
        //burda da bu username ve passwordun yanlis olma ihtimaline karsi sqlException firlamamizi öneriyor
        //bizde kabul edince getConnection altındaki kırmızı çizgi gider

        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");

       //3- Sql query'leri için bir Statement objesi olusturup, java SQL sorgularimiz icin bir alan acacagiz

        Statement st=con.createStatement();

        //4- SQL query'lerimizi yazıp, calistirabiliriz

       ResultSet veri= st.executeQuery( "SELECT * FROM personel");

       //5- Sonuclari Set'e kaydettigimiz icin, Iteration ile Set icindeki elemanlari while dongusu
        //ile yazdirarak gorebiliriz

        while (veri.next()){
            System.out.println(veri.getInt(1)+" "+veri.getString(2)+" "+veri.getString(3)
            +" "+veri.getInt(4)+" "+veri.getString(5));
        }

        //6-Olusturulan nesneleri kapatalim.

        con.close();
        st.close();
        veri.close();


        //calistirdigimizdaki cikti:
        /*
        123456789 Ali Seker Istanbul 2500 Honda
        234567890 Ayse Gul Istanbul 1500 Toyota
        345678901 Veli Yilmaz Ankara 3000 Honda
        456789012 Veli Yilmaz Izmir 1000 Ford
        567890123 Veli Yilmaz Ankara 7000 Hyundai
        456789012 Ayse Gul Ankara 1500 Ford
        123456710 Fatma Yasa Bursa 2500 Honda
         */

    }
}
