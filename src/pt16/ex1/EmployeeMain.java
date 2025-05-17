package pt16.ex1;
import pt15.albumDao.Connexio;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class EmployeeMain {
    public void employeeMenu(){
        System.out.println("""
                            Escull una de les opcions seguents:
                            1. Obtenir informacio de tots els empleats\s
                            2. Obtenir informacio sobre un empleat
                            3. Crear un nou empleat
                            4. Modificar les dades d'un empleat
                            5. Esborrar un empleat
                            0. Sortir
                           \s""");

    }

    public static void main(String[] args) throws SQLException {
        Connection c = Connexio.getConnection();
        EmployeeMain main = new EmployeeMain();
        EmployeeDaoImplementation employeeDao= new EmployeeDaoImplementation();
        Scanner sc = new Scanner(System.in);
        main.employeeMenu();
        int opcio = sc.nextInt(); sc.nextLine();
        while(opcio != 0){
            switch (opcio) {
                case 1: {
                    System.out.println(employeeDao.readItems());
                    break;
                }
                case 2: {
                    System.out.println("Introdueix quin empleat vols veure (ID)");
                    int employeeId = sc.nextInt();
                    sc.nextLine();
                    System.out.println(employeeDao.readItem(employeeId));
                    System.out.println();
                    break;
                }
                case 3: {
                    System.out.println("Introdueix el nou ID de l'empleat (Numero)");
                    int employeeId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introdueix el cognom nou (Text)");
                    String cognom = sc.nextLine();
                    System.out.println("Introdueix el nom nou (Text)");
                    String nom = sc.nextLine();
                    System.out.println("Introdueix el titol nou (Text)");
                    String titol = sc.nextLine();
                    System.out.println("Introdueix el ID de l'encarregat d'aquest nou empleat (Numero)");
                    int reportsToId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introdueix la data de naixement del nou empleat (Text, format yyyy-MM-dd)");
                    String dataNaixementInput = sc.nextLine();
                    LocalDate formatDataNaixement = LocalDate.parse(dataNaixementInput);
                    Date dataNaixement = Date.valueOf(formatDataNaixement);
                    System.out.println("Introdueix la data de contratacio del nou empleat (Text, format yyyy-MM-dd)");
                    String dataContratacioInput = sc.nextLine();
                    LocalDate formatDataContratacio = LocalDate.parse(dataContratacioInput);
                    Date dataContratacio = Date.valueOf(formatDataContratacio);
                    System.out.println("Introdueix l'adreca' del nou empleat (Text)");
                    String adress = sc.nextLine();
                    System.out.println("Introdueix la ciutat del nou empleat (Text)");
                    String ciutat = sc.nextLine();
                    System.out.println("Introdueix l'estat del nou empleat (Text)");
                    String estat = sc.nextLine();
                    System.out.println("Introdueix el pais del nou empleat (Text)");
                    String pais = sc.nextLine();
                    System.out.println("Introdueix el codi postal del nou empleat (Text)");
                    String codiPostal = sc.nextLine();
                    System.out.println("Introdueix el telefon del nou empleat (Text)");
                    String telefon = sc.nextLine();
                    System.out.println("Introdueix el fax del nou empleat (Text)");
                    String fax = sc.nextLine();
                    System.out.println("Introdueix l'email del nou empleat (Text)");
                    String email = sc.nextLine();
                    System.out.println(employeeDao.createItem(new Employee(employeeId, cognom, nom, titol, reportsToId, dataNaixement, dataContratacio, adress, ciutat, estat, pais, codiPostal, telefon, fax, email)));
                    break;
                }
                case 4: {
                    System.out.println("Introdueix quin empleat vols modificar (ID)");
                    int idEmpleat = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introdueix el nou cognom a utilitzar (Text)");
                    String cognom = sc.nextLine();
                    System.out.println("Introdueix el nou nom a utilitzar (Text)");
                    String nom = sc.nextLine();
                    System.out.println("Introdueix el nou titol a utilitzar (Text)");
                    String titol = sc.nextLine();
                    System.out.println("Introdueix el nou ID de l'encarregat a utilitzar (Numero)");
                    int reportsToId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introdueix la nova data de naixement a utilitzar (Text, format yyyy-MM-dd)");
                    String dataNaixementInput = sc.nextLine();
                    LocalDate formatDataNaixement = LocalDate.parse(dataNaixementInput);
                    Date dataNaixement = Date.valueOf(formatDataNaixement);
                    System.out.println("Introdueix la nova data de contratacio a utilitzar (Text, format yyyy-MM-dd)");
                    String dataContratacioInput = sc.nextLine();
                    LocalDate formatDataContratacio = LocalDate.parse(dataContratacioInput);
                    Date dataContratacio = Date.valueOf(formatDataContratacio);
                    System.out.println("Introdueix la nova adreca a utilitzar (Text)");
                    String adress = sc.nextLine();
                    System.out.println("Introdueix la nova ciutat (Text)");
                    String ciutat = sc.nextLine();
                    System.out.println("Introdueix el nou estat (Text)");
                    String estat = sc.nextLine();
                    System.out.println("Introdueix el nou pais (Text)");
                    String pais = sc.nextLine();
                    System.out.println("Introdueix el nou codi postal (Text)");
                    String codiPostal = sc.nextLine();
                    System.out.println("Introdueix el nou telefon a utilitzar (Text)");
                    String telefon = sc.nextLine();
                    System.out.println("Introdueix el nou fax a utilitzar (Text)");
                    String fax = sc.nextLine();
                    System.out.println("Introdueix el nou email a utilitzar (Text)");
                    String email = sc.nextLine();
                    employeeDao.updateItem(new Employee(idEmpleat, cognom, nom, titol, reportsToId, dataNaixement, dataContratacio, adress, ciutat, estat, pais, codiPostal, telefon, fax, email));
                    break;
                }
                case 5: {
                    System.out.println("Introdueix quina empleat vols eliminar (ID)");
                    int idEmpleat = sc.nextInt();
                    sc.nextLine();
                    employeeDao.deleteItem(employeeDao.readItem(idEmpleat));
                    break;
                }
                case 0: {
                    break;
                }
                default: {
                    System.out.println("Introdueix un nombre vàlid del 0 al 6");
                    break;
                }
            }
            main.employeeMenu();
            opcio = sc.nextInt();sc.nextLine();
        }
        c.close();
    }
}
