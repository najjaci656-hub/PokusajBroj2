import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.SQLException;


public class Zadatak {
    public static void main(String[] args) throws SQLException {
        SQLServerDataSource ds = createDataSource();

        ArrayList<String> Drzava = new ArrayList<>(Arrays.asList("Hrvatska", "Poljska", "Srbija", "Crna Gora", "Makedonija", "Island", "Bugarska", "Bosna I Hercegovina", "Slovenija", "Mađarska"));

        try (Connection conn = ds.getConnection()) {
            System.out.println("Povezano s bazom");

            String SQL = "INSERT INTO Drzava (Naziv) VALUES (?)";
            int prviId = -1;

            PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS); {
                for (String naziv : Drzava) {
                    pstmt.setString(1, naziv);
                    pstmt.executeUpdate();
                }

                if (prviId == -1) {
                    ResultSet rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        prviId = rs.getInt(1);
                    }
                    rs.close();
                }
            }

            pstmt.close();
            System.out.println("10 država je uneseno. Prvi ID je: " + prviId);

            if (prviId != -1) {
                System.out.println("Brisanje država po ID-u: " + prviId);

                String brisanje = "{CALL BrisanjeDrzava(?)}";

                CallableStatement cstmt = conn.prepareCall(brisanje);
                cstmt.setInt(1, prviId);
                cstmt.execute();
                System.out.println("Uspiješno Obrisano!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SQLServerDataSource createDataSource() {
        SQLServerDataSource data = new SQLServerDataSource();
        data.setServerName("localhost");
        data.setDatabaseName("AdventureWorksOBP");
        data.setPortNumber(60968);
        data.setUser("sa");
        data.setPassword("SQL");
        data.setTrustServerCertificate(true);

        return data;

    }
}
