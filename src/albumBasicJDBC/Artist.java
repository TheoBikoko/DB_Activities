package albumBasicJDBC;

public class Artist {
    private int ArtistId;
    private String name;
    private int age;
    private int debutYear;

    public Artist(int artistId, String name, int age, int debutYear) {
        ArtistId = artistId;
        this.name = name;
        this.age = age;
        this.debutYear = debutYear;
    }

    public int getArtistId() {
        return ArtistId;
    }

    public void setArtistId(int artistId) {
        ArtistId = artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDebutYear() {
        return debutYear;
    }

    public void setDebutYear(int debutYear) {
        this.debutYear = debutYear;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "ArtistId=" + ArtistId +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", debutYear=" + debutYear +
                '}';
    }
}
