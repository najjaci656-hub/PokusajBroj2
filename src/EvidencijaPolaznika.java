import java.util.ArrayList;
import java.util.Scanner;

public class EvidencijaPolaznika {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Polaznik> listaPolaznik = new ArrayList<>();

        System.out.println("Unesite ime: ");
        String ime = scanner.nextLine();

        System.out.println("Unesite prezime: ");
        String prezime = scanner.nextLine();

        System.out.println("Unesite email: ");
        String email = scanner.nextLine();

        Polaznik p = new Polaznik(ime, prezime, email);
        listaPolaznik.add(p);

        System.out.println("Polaznik je dodan!");

        System.out.println("Popis polaznika: ");
        for(Polaznik pp: listaPolaznik) {
            System.out.println(pp);
        }

        System.out.print("Unesi email korisnika: ");
        String korisnik = scanner.nextLine();

        for(Polaznik polaznik : listaPolaznik) {
            if(polaznik.getEmail().equals(korisnik)) {
                System.out.println("Pronađen polaznik: " + polaznik);
            }
        }
    }
}
