package pt15.albumBasicJDBC;

import java.sql.*;
import java.util.Scanner;

public class TrackMenu {
    public void trackMainMenu(){
        System.out.println("Escull una opció :\n"
                + "1) Llista les cançons\n"
                + "2) Selecciona una cançó\n"
                + "3) Introdueix una cançó\n"
                + "4) Modifica una cançó\n"
                + "5) Elimina una cançó\n"
                + "0) Sortir\n"
        );
    }



    public static void main(String args[] ) throws SQLException {
        Track track = new Track();
        Scanner sc = new Scanner(System.in);
        TrackMenu main = new TrackMenu();
        main.trackMainMenu();
        int opcio = sc.nextInt();sc.nextLine();

        while (opcio!=0){
            switch(opcio){
                case 1: {
                    System.out.println(track.llegirTracks());
                    break;
                }
                case 2:{
                    System.out.println("Introdueix quina cançó vols veure (ID)");
                    int trackId = sc.nextInt();sc.nextLine();
                    Track trackTrobat = track.llegirTrack(trackId);
                    System.out.println(trackTrobat);
                    System.out.println();
                    break;
                }
                case 3:{
                    System.out.println("Introdueix TrackId nou (ID)");
                    int trackId = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el titol nou (Text)");
                    String titol = sc.nextLine();
                    System.out.println("Introdueix el AlbumId nou (Numero)");
                    int albumId = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el MediaType nou (Numero)");
                    int mediaType = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el genere nou (Numero)");
                    int genre = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el compositor nou (Numero)");
                    String compositor = sc.nextLine();
                    System.out.println("Introdueix els milisegons (Numero)");
                    int miliseconds = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix la quantitat de bytes (Numero)");
                    int bytes = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el preu (Numero decimal)");
                    double price = sc.nextDouble();
                    System.out.println(track.creaTrack(trackId, titol, albumId, track.llegirMediaType(mediaType), track.llegirGenre(genre), compositor, miliseconds, bytes, price));
                    break;
                }
                case 4:{
                    System.out.println("Introdueix quina cançó vols modificar (ID)");
                    int trackId = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el titol nou (Text)");
                    String titol = sc.nextLine();
                    System.out.println("Introdueix el AlbumId nou (Numero)");
                    int albumId = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el MediaType nou (Numero)");
                    int mediaType = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el genere nou (Numero)");
                    int genre = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el compositor nou (Text)");
                    String compositor = sc.nextLine();
                    System.out.println("Introdueix els milisegons (Numero)");
                    int miliseconds = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix la quantitat de bytes (Numero)");
                    int bytes = sc.nextInt();sc.nextLine();
                    System.out.println("Introdueix el preu (Numero decimal)");
                    double price = sc.nextDouble();
                    track.modificaTrack(trackId, titol, albumId, track.llegirMediaType(mediaType), track.llegirGenre(genre), compositor, miliseconds, bytes, price);
                    break;
                }
                case 5:{
                    System.out.println("Introdueix quina cançó vols eliminar (ID)");
                    int trackId = sc.nextInt();sc.nextLine();
                    track.eliminaTrack(trackId);
                    break;
                }
                case 0:{
                    break;
                }
                default:{
                    System.out.println("Introdueix un nombre vàlid del 0 al 6");
                    break;
                }
            }

            main.trackMainMenu();
            opcio = sc.nextInt();sc.nextLine();
        }

    }
}
