import java.sql.*;

public class JDBC_BireyselCalisma {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st=con.createStatement();

        String dropQuery= "DROP TABLE books";

        if (!st.execute(dropQuery)){
            System.out.println("Books tablosu silindi");
        }

        String createTableBooks = "CREATE TABLE books" +
                "(id INT, " +
                "title VARCHAR(50), " +"author VARCHAR(50), "+ "price float, "+"qty int, "+"primary key (id))";

        if (!st.execute(createTableBooks)){
            System.out.println("Books tablosu olusturuldu");
        }

        String [] queries= {"insert into books values (1001, 'Java for dummies', 'Tan Ah Teck', 11.11, 11)",
                            "insert into books values (1002, 'More Java for dummies', 'Tan Ah Teck', 22.22, 22)",
                            "insert into books values (1003, 'More Java for more dummies', 'Mohammad Ali', 33.33, 33)",
                            "insert into books values (1004, 'A Cup of Java', 'Kumar', 44.44, 44)",
                            "insert into books values (1005, 'A Teaspoon of Java', 'Kevin Jones', 55.55, 55)"};

        int count=0;
        for (String each:queries
        ) {
            count+= st.executeUpdate(each);
        }
        System.out.println(count+" satir eklendi");

        String selectQuery="SELECT * FROM books";

        ResultSet booksTable=st.executeQuery(selectQuery);

        while (booksTable.next()){
            System.out.println(booksTable.getInt(1)+ " "+
                    booksTable.getString(2)+" "+
                    booksTable.getString(3)+" "+
                    booksTable.getFloat(4)+" "+
                    booksTable.getInt(5));
        }

        //kitaplara %10 indirim yapıp guncelleyelim
        String updateQuery= "UPDATE books SET price=price*0.9 ";

        int satir= st.executeUpdate(updateQuery);
        System.out.println(satir+" satir guncellendi");

        System.out.println("===================Indirimli Liste====================");
        ResultSet booksTablosu2=st.executeQuery(selectQuery);

        while (booksTablosu2.next()){
            System.out.println(booksTablosu2.getInt(1)+ " "+
                    booksTablosu2.getString(2)+" "+
                    booksTablosu2.getString(3)+" "+
                    booksTablosu2.getFloat(4)+" "+
                    booksTablosu2.getInt(5));
        }

        //icinde dummies kelimesi gecen kitapların qty değerini 10 artıralım
        String updateQuery2= "UPDATE books SET qty=qty+10 WHERE title LIKE '%dummies%' ";

        int satir2= st.executeUpdate(updateQuery2);
        System.out.println(satir2+" satir guncellendi");
        System.out.println("===================Stok Artmış Liste====================");
        ResultSet booksTablosu3=st.executeQuery(selectQuery);

        while (booksTablosu3.next()){
            System.out.println(booksTablosu3.getInt(1)+ " "+
                    booksTablosu3.getString(2)+" "+
                    booksTablosu3.getString(3)+" "+
                    booksTablosu3.getFloat(4)+" "+
                    booksTablosu3.getInt(5));
        }

        con.close();
        st.close();
        booksTable.close();
        booksTablosu2.close();
        booksTablosu3.close();
    }
}
