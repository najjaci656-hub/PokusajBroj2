public class Polaznik {
    private String ime;
    private String prezime;
    private String email;


    public Polaznik(String Ime, String Prezime, String email) {
        this.ime = Ime;
        this.prezime = Prezime;
        this.email = email;
    }

    public String getIme() {
        return ime;
    }
    public void setIme(String Ime) {
        this.ime = ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public void setPrezime(String Prezime) {
        this.prezime = prezime;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Polaznik: " + ime + " " + prezime + " (" + email + ")";
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polaznik polaznik = (Polaznik) o;
        return java.util.Objects.equals(email, polaznik.email);
    }
        @Override
        public int hashCode() {
            return java.util.Objects.hash(email);
   }
}



