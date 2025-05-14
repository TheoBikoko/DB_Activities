package pt15.albumBasicJDBC;

public class Artist {
    private int artistId;
    private String name;


    public Artist(int artistId, String name) {
        this.artistId = artistId;
        this.name = name;
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        artistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "ArtistId=" + artistId +
                ", name='" + name + '\'' +
                '}';
    }
}
