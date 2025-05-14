package pt15.albumBasicJDBC;

public class Genre {
    private int genreId;
    private String nom;

    public Genre(int genreId, String nom) {
        this.genreId = genreId;
        this.nom = nom;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "genreId=" + genreId +
                ", nom='" + nom + '\'' +
                '}';
    }
}
