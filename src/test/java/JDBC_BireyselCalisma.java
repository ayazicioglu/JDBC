import java.sql.*;

public class JDBC_BireyselCalisma {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "1234");
        Statement st=con.createStatement();

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
    }
}
