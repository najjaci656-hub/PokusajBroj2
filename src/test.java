import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;

public static void main(String[] args) {
    SQLServerDataSource dataSource = createDataSource();
    Scanner sc = new Scanner(System.in);
    int izbor = 0;

    while (izbor != 5) {
        System.out.println("\n--- IZBORNIK ---");
        System.out.println("1 – nova država");
        System.out.println("2 – izmjena postojeće države");
        System.out.println("3 – brisanje postojeće države");
        System.out.println("4 – prikaz svih država sortiranih po nazivu");
        System.out.println("5 – kraj");
        System.out.print("Odaberite opciju: ");

        try {
            izbor = Integer.parseInt(sc.nextLine()); // Sigurniji unos broja

            switch (izbor) {
                case 1 -> novaDrzava(dataSource, sc);
                case 2 -> izmjenaDrzave(dataSource, sc);
                case 3 -> brisanjeDrzave(dataSource, sc);
                case 4 -> prikazDrzava(dataSource);
                case 5 -> System.out.println("Izlaz iz aplikacije...");
                default -> System.out.println("Pogrešan odabir, pokušajte ponovno.");
            }
        } catch (Exception e) {
            System.out.println("Greška: " + e.getMessage());
        }
    }
}

private static void novaDrzava(SQLServerDataSource ds, Scanner sc) throws SQLException {
    System.out.print("Unesite naziv nove države: ");
    String naziv = sc.nextLine();

    String sql = "INSERT INTO Drzava (Naziv) VALUES (?)";
    try (Connection conn = ds.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, naziv);
        pstmt.executeUpdate();
        System.out.println("Država uspješno dodana!");
    }
}

private static void izmjenaDrzave(SQLServerDataSource ds, Scanner sc) throws SQLException {
    System.out.print("Unesi ID države koju želiš izmijeniti: ");
    int id = Integer.parseInt(sc.nextLine());

    if (id <= 3) {
        System.out.println("Greška: Dozvoljena je izmjena samo onih država čiji je ID veći od 3.");
        return;
    }

    System.out.print("Unesi novi naziv države: ");
    String noviNaziv = sc.nextLine();

    String sql = "UPDATE Drzava SET Naziv = ? WHERE IdDrzava = ?";
    try (Connection conn = ds.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, noviNaziv);
        pstmt.setInt(2, id);
        int redova = pstmt.executeUpdate();

        if (redova > 0) System.out.println("Država uspješno izmijenjena!");
        else System.out.println("Država s tim ID-om ne postoji.");
    }
}
private static void brisanjeDrzave(SQLServerDataSource ds, Scanner sc) throws SQLException {
    System.out.print("Unesi ID države koju želiž obrisati: ");
    int id = Integer.parseInt(sc.nextLine());

    // Provjera napomene: IdDrzava veći od 3
    if (id <= 3) {
        System.out.println("Greška: Dozvoljeno je brisanje samo onih država čiji je ID veći od 3.");
        return;
    }

    String sql = "DELETE FROM Drzava WHERE IdDrzava = ?";
    try (Connection conn = ds.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, id);
        int redova = pstmt.executeUpdate();

        if (redova > 0) System.out.println("Država uspješno obrisana!");
        else System.out.println("Država s tim ID-om ne postoji.");
    }
}

private static void prikazDrzava(SQLServerDataSource ds) throws SQLException {
    String sql = "SELECT IdDrzava, Naziv FROM Drzava ORDER BY Naziv ASC";

    try (Connection conn = ds.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        System.out.println("\n--- POPIS DRŽAVA (Sortirano po imenu) ---");
        while (rs.next()) {
            System.out.printf("ID: %d | Naziv: %s\n", rs.getInt("IdDrzava"), rs.getString("Naziv"));
        }
    }
}

private static SQLServerDataSource createDataSource() {
    SQLServerDataSource ds = new SQLServerDataSource();
    ds.setServerName("localhost");
    ds.setDatabaseName("AdventureWorksOBP");
    ds.setPortNumber(60968);
    ds.setUser("sa");
    ds.setPassword("SQL");
    ds.setTrustServerCertificate(true);
    return ds;
}
