package pt16.ex2;
import pt15.albumDao.Connexio;

import pt16.ex1.Employee;
import pt16.ex1.EmployeeDaoImplementation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class CustomerMain {
    public void customerMenu(){
        System.out.println("""
                        Escull una de les opcions seguents:
                        1. Obtenir informacio de tots els clients\s
                        2. Obtenir informacio sobre un client
                        3. Crear un nou client
                        4. Modificar les dades d'un client
                        5. Esborrar un client
                        0. Sortir
                       \s""");
    }

    public static void main(String[] args) throws SQLException {
        Connection c = Connexio.getConnection();
        CustomerMain main = new CustomerMain();
        Scanner sc = new Scanner(System.in);
        CustomerDaoImplementation customerDao = new CustomerDaoImplementation();
        EmployeeDaoImplementation employeeDao = new EmployeeDaoImplementation();
        main.customerMenu();
        int opcio = sc.nextInt(); sc.nextLine();
            while(opcio != 0){
                switch (opcio){
                    case 1: {
                        System.out.println(customerDao.readItems());
                        break;
                    }
                    case 2:{
                        System.out.println("Introdueix quin empleat vols veure (ID)");
                        int customerId = sc.nextInt();sc.nextLine();
                        System.out.println(customerDao.readItem(customerId));
                        System.out.println();
                        break;
                    }
                    case 3:{
                        System.out.println("Introdueix el nou ID del client (Numero)");
                        int employeeId = sc.nextInt();sc.nextLine();
                        System.out.println("Introdueix el nom nou (Text)");
                        String nom = sc.nextLine();
                        System.out.println("Introdueix el cognom nou (Text)");
                        String cognom = sc.nextLine();
                        System.out.println("Introdueix la nova companyia (Text)");
                        String companyia = sc.nextLine();
                        System.out.println("Introdueix la nova adreca (Text)");
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
                        System.out.println("Introdueix l'ID del nou empleat representatiu per aquest client (Numero)");
                        int idEmpleatRepresentatiu = sc.nextInt();sc.nextLine();
                        Employee empleatRepresentatiu = employeeDao.readItem(idEmpleatRepresentatiu);
                        System.out.println(customerDao.createItem(new Customer(employeeId, nom, cognom, companyia, adress, ciutat, estat, pais, codiPostal, telefon, fax, email, empleatRepresentatiu)));
                        break;
                    }
                    case 4:{
                        System.out.println("Introdueix quin client vols modificar (ID)");
                        int idClient = sc.nextInt();sc.nextLine();
                        System.out.println("Introdueix el nou nom a utilitzar (Text)");
                        String nom = sc.nextLine();
                        System.out.println("Introdueix el nou cognom a utilitzar (Text)");
                        String cognom = sc.nextLine();
                        System.out.println("Introdueix la nova companyia (Text)");
                        String companyia = sc.nextLine();
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
                        System.out.println("Introdueix l'ID del nou empleat representatiu (Numero)");
                        int idEmpleatRepresentatiu = sc.nextInt();sc.nextLine();
                        Employee empleatRepresentatiu = employeeDao.readItem(idEmpleatRepresentatiu);
                        customerDao.updateItem(new Customer(idClient, nom, cognom, companyia, adress, ciutat, estat, pais, codiPostal, telefon, fax, email, empleatRepresentatiu));
                        break;
                    }
                    case 5:{
                        System.out.println("Introdueix quin client vols eliminar (ID)");
                        int idClient = sc.nextInt();sc.nextLine();
                        customerDao.deleteItem(customerDao.readItem(idClient));
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
             main.customerMenu();
             opcio = sc.nextInt(); sc.nextLine();
            }
        c.close();
    }
}




