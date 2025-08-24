import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/db";

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter course ID");
        int cid= Integer.parseInt(sc.nextLine());

        System.out.println("Enter Course name");
        String cname = sc.nextLine().trim();

        System.out.println(cname);

        try {
            Connection connection = DriverManager.getConnection(url,"root","Vignesh@123");
            if (connection!=null){
                System.out.println("connection established");
                PreparedStatement pst = connection.prepareStatement("insert into course values(?,?)");
                pst.setInt(1,cid);
                pst.setString(2,cname);

//                PreparedStatement pst = connection.prepareStatement("select * from course");
//                ResultSet rs = pst.executeQuery();

//                while(rs.next())
//                {
//                    int id = rs.getInt("id");
//                    String cname = rs.getString("course_name");
//
//                    System.out.println("Id is: "+id);
//                    System.out.println("Course name is: "+cname);
//
//                    System.out.println();
//                }

                int i = pst.executeUpdate(); //no of rows affected
                if(i>0){
                    System.out.println("record inserted");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}