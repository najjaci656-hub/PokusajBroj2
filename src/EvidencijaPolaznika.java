import java.util.*;

public class EvidencijaPolaznika {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TreeMap<String, Polaznik> listaPolaznik = new TreeMap<>();

        boolean nastavi = true;
        while (nastavi) {

        System.out.println("Unesite ime: ");
        String ime = scanner.nextLine();

        System.out.println("Unesite prezime: ");
        String prezime = scanner.nextLine();

        System.out.println("Unesite email: ");
        String email = scanner.nextLine();

            if (listaPolaznik.containsKey(email)) {
                System.out.println("Error: Polaznik s tom e-mail adresom već postoji!");
            } else {
                Polaznik p = new Polaznik(ime, prezime, email);
                listaPolaznik.put(email, p);
                System.out.println("Polaznik je uspješno dodan!");
            }

        System.out.println("Želiš li dodati novog polaznika (DA/NE)");
        String odgovor = scanner.nextLine();
        if (odgovor.equals("Ne")) {
            nastavi = false;
        }
        else if (odgovor.equals("Da")) {
            nastavi = true;
            }
        }

        System.out.println("Polaznik je dodan!");

        ArrayList<Polaznik> listaZaMijesanje = new ArrayList<>(listaPolaznik.values());
        java.util.Collections.shuffle(listaZaMijesanje);
        System.out.println("\nPopis polaznika u nasumičnom redoslijedu:");
        for (Polaznik polaznik : listaZaMijesanje) {
            System.out.println(polaznik);
        }

        System.out.println("Popis polaznika: ");
        for(Polaznik pp: listaPolaznik.values()) {
            System.out.println(pp);
        }

        System.out.print("Unesi email korisnika: ");
        String korisnik = scanner.nextLine();

        for(Polaznik polaznik : listaPolaznik.values()) {
            if(polaznik.getEmail().equals(korisnik)) {
                System.out.println("Pronađen polaznik: " + polaznik);
            }
        }
        }
    }
