import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class EvidencijaPolaznika {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Polaznik> listaPolaznik = new HashMap<>();

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
