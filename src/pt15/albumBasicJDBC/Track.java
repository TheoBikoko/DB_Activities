//3
package pt15.albumBasicJDBC;

import java.sql.*;
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

    public Track(int trackId, String titol, int albumId, MediaType mediaType, Genre genre, String composer) {
        this.trackId = trackId;
        this.titol = titol;
        this.albumId = albumId;
        this.mediaType = mediaType;
        this.genre = genre;
        this.composer = composer;
    }

    public Track() {
        super();
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
        return String.format(
                "Track %d: %s | Album: %d | Génere: %s | Tipus: %s%n | Compositor: %s" + "\n",
                trackId, titol, albumId, genre.getNom(),
                mediaType.getName(), composer
        );

    }

    public int creaTrack(int trackId, String titol, int albumId, MediaType mediaType, Genre genre, String composer, int milliseconds, int bytes, double unitPrice){
        int nouTrack = -1;
        try {
            String query = "INSERT INTO Track (TrackId, Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, trackId);
            ps.setString(2, titol);
            ps.setInt(3, albumId);
            ps.setInt(4, mediaType.getMediaTypeId());
            ps.setInt(5, genre.getGenreId());
            ps.setString(6, composer);
            ps.setInt(7, milliseconds);
            ps.setInt(8, bytes);
            ps.setDouble(9, unitPrice);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            nouTrack = rs.getInt(1);
            System.out.println("Track created succesfully");
            return nouTrack;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return nouTrack;
    }

    public Track llegirTrack(int trackId){
        Statement stmt;
        try {
            String query = "SELECT TrackId, Name, AlbumId, MediaTypeId, GenreId, Composer FROM Track WHERE TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, trackId);
            ResultSet rs = ps.executeQuery();

                rs.next();
                String titol = rs.getString("Name");
                int albumId = rs.getInt("AlbumId");
                MediaType mediaType = llegirMediaType(rs.getInt("MediaTypeId"));
                Genre genre = llegirGenre(rs.getInt("GenreId"));
                String compositor = rs.getString("Composer");

                rs.close();
                ps.close();

                System.out.println("Track read succesfully.");
                return new Track(trackId, titol, albumId, mediaType, genre, compositor);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public List<Track> llegirTracks(){
        Statement stmt;
        ArrayList<Track> tracks = new ArrayList<>();
        try {
            String query = "SELECT TrackId, Name, AlbumId, MediaTypeId, GenreId, Composer FROM Track";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                int trackId = rs.getInt("TrackId");
                String titol = rs.getString("Name");
                int albumId = rs.getInt("AlbumId");
                MediaType mediaType = llegirMediaType(rs.getInt("MediaTypeId"));
                Genre genre = llegirGenre(rs.getInt("GenreId"));
                String compositor = rs.getString("Composer");

                Track track = new Track(trackId, titol, albumId, mediaType, genre, compositor);

                tracks.add(track);
            }
            return tracks;
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    public void modificaTrack(int trackId, String titol, int albumId, MediaType mediaType, Genre genre, String composer, int milliseconds, int bytes, double unitPrice){
        try{
            con.setAutoCommit(false);
            String query = "UPDATE Track set Name = ?, AlbumId= ?, MediaTypeId = ?, GenreId = ?, Composer = ?, Milliseconds = ?, Bytes = ?,  UnitPrice = ? WHERE TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, titol);
            ps.setInt(2, albumId);
            ps.setInt(3, mediaType.getMediaTypeId());
            ps.setInt(4, genre.getGenreId());
            ps.setString(5, composer);
            ps.setInt(6, milliseconds);
            ps.setInt(7, bytes);
            ps.setDouble(8, unitPrice);
            ps.setInt(9, trackId);
            ps.executeUpdate();
            con.commit();
            ps.close();

            System.out.println("Track updated succesfully");
        }catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void eliminaTrack(int trackId){
        try {
            con.setAutoCommit(false);
            String query = "DELETE FROM Track WHERE TrackId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, trackId);
            ps.executeUpdate();
            con.commit();
            ps.close();
            System.out.println("Track deleted succesfully");

        } catch (Exception e){
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
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
