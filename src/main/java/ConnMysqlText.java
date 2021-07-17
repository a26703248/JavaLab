import java.sql.*;

public class ConnMysqlText {
    public static void main(String[] args) {
        String sql = "select * from empolyee";
        try(Connection conn = getConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(sql)){
            while(rs.next()){
                System.out.println("ID:" + rs.getString("id") +
                                    "姓名" + rs.getString("name") +
                                    "薪資" + rs.getString("salary"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //設定檔
    private static Connection getConnection() throws SQLException {
        String serverName = "localhost";
        String database = "java";
        String url = "jdbc:mysql://" + serverName + "/" + database;
        // 帳號和密碼
        String user = "root";
        String password = "a0909007892";
        return DriverManager.getConnection(url, user, password);
    }
}
