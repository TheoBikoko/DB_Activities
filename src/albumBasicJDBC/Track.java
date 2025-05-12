//3
package albumBasicJDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Track {

    private int trackId;
    private String titol;
    private int albumId;
    private MediaType mediaType;
    private Genre genre;
    private String composer;
    private int milliseconds;
    private int bytes;
    private double unitPrice;
    private static Connection con = Connexio.getConnection();

    public Track(int trackId, String titol, int albumId, MediaType mediaType, Genre genre, String composer, int milliseconds, int bytes, double unitPrice) {
        this.trackId = trackId;
        this.titol = titol;
        this.albumId = albumId;
        this.mediaType = mediaType;
        this.genre = genre;
        this.composer = composer;
        this.milliseconds = milliseconds;
        this.bytes = bytes;
        this.unitPrice = unitPrice;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaTypeId(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public int getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(int milliseconds) {
        this.milliseconds = milliseconds;
    }

    public int getBytes() {
        return bytes;
    }

    public void setBytes(int bytes) {
        this.bytes = bytes;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Track{" +
                "trackId=" + trackId +
                ", titol='" + titol + '\'' +
                ", albumId=" + albumId +
                ", mediaTypeId=" + mediaType +
                ", genreId=" + genre +
                ", composer='" + composer + '\'' +
                ", milliseconds=" + milliseconds +
                ", bytes=" + bytes +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public Track llegirTrack(int trackId){
        Statement stmt;
        try {
            String query = "SELECT * FROM Track WHERE TrackId = ?";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                String titol = rs.getString("Name");
                int albumId = rs.getInt("AlbumId");
                MediaType mediaType = llegirMediaType(rs.getInt("MediaTypeId"));

                return new Track(trackId, titol, albumId);
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public List<Track> llegirTracks(){
        Statement stmt;
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            String query = "SELECT * FROM Track";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                int trackId = rs.getInt("TrackId");
                tracks.add(llegirTrack(trackId));
            }
            return tracks;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public MediaType llegirMediaType(int mediaTypeId) {
        try {
            String query = "SELECT * FROM MediaType WHERE MediaTypeId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, mediaTypeId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new MediaType(rs.getInt("MediaTypeId"),rs.getString("Name"));
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public Genre llegirGenre(int genreId){
        try {
            String query = "SELECT * FROM Genre WHERE GenreId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, genreId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Genre(rs.getInt("GenreId"), rs.getString("Name"));
            }
        } catch (Exception e){
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }
}
