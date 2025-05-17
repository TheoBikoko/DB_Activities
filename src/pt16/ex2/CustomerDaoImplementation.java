package pt16.ex2;

import pt16.ex1.DAOGeneric;
import pt16.ex1.Employee;
import pt16.ex1.EmployeeDaoImplementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static pt15.albumDao.Connexio.con;

public class CustomerDaoImplementation implements DAOGeneric<Customer> {
    @Override
    public int createItem(Customer customer) {
        int newCustomer = -1;
        try {
            EmployeeDaoImplementation edao = new EmployeeDaoImplementation();
            String query = "INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, customer.getCustomerId());
            ps.setString(2, customer.getFirstName());
            ps.setString(3, customer.getLastName());
            ps.setString(4, customer.getCompany());
            ps.setString(5, customer.getAddress());
            ps.setString(6, customer.getCity());
            ps.setString(7, customer.getState());
            ps.setString(8, customer.getCountry());
            ps.setString(9, customer.getPostalCode());
            ps.setString(10, customer.getPhone());
            ps.setString(11, customer.getFax());
            ps.setString(12, customer.getEmail());
            ps.setObject(13, edao.readItem(customer.getSupportRepresentative().getEmployeeId()));

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            newCustomer = rs.getInt(1);
            rs.close();
            ps.close();
            return newCustomer;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return newCustomer;
    }

    @Override
    public Customer readItem(int idItem) {
        try{
            EmployeeDaoImplementation edao = new EmployeeDaoImplementation();
            String query = "SELECT * FROM Customer WHERE CustomerId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idItem);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String newFirstName = rs.getString(2);
            String newLastName = rs.getString(3);
            String newCompany = rs.getString(4);
            String newAddress = rs.getString(5);
            String newCity = rs.getString(6);
            String newState = rs.getString(7);
            String newCountry = rs.getString(8);
            String newPostalCode = rs.getString(9);
            String newPhone = rs.getString(10);
            String newFax = rs.getString(11);
            String newEmail = rs.getString(12);
            Employee newSupportRepresentative = edao.readItem(rs.getInt(13));
            rs.close();
            ps.close();
            return new Customer(idItem, newLastName, newFirstName, newCompany, newAddress, newCity, newState, newCountry, newPostalCode, newPhone, newFax, newEmail, newSupportRepresentative);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Customer> readItems() {
        try{
            EmployeeDaoImplementation edao = new EmployeeDaoImplementation();
            List<Customer> customers= new ArrayList<>();
            String query = "SELECT * FROM Customer";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int newCustomerId = rs.getInt(1);
                String newFirstName = rs.getString(2);
                String newLastName = rs.getString(3);
                String newCompany = rs.getString(4);
                String newAddress = rs.getString(5);
                String newCity = rs.getString(6);
                String newState = rs.getString(7);
                String newCountry = rs.getString(8);
                String newPostalCode = rs.getString(9);
                String newPhone = rs.getString(10);
                String newFax = rs.getString(11);
                String newEmail = rs.getString(12);
                Employee newSupportRepresentative = edao.readItem(rs.getInt(13));
                Customer customer = new Customer(newCustomerId, newLastName, newFirstName, newCompany, newAddress, newCity, newState, newCountry, newPostalCode, newPhone, newFax, newEmail, newSupportRepresentative);
                customers.add(customer);
            }
            rs.close();
            ps.close();
            return customers;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateItem(Customer customer) {
        try{
            EmployeeDaoImplementation edao = new EmployeeDaoImplementation();
            con.setAutoCommit(false);
            String query = "UPDATE Customer SET FirstName = ?, LastName = ?, Company = ?, Address = ?, City = ?, State = ?, Country = ?, PostalCode = ?, Phone = ?, Fax = ?, Email = ?, SupportRepId = ? WHERE CustomerId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, customer.getFirstName());
            ps.setString(2, customer.getLastName());
            ps.setString(3, customer.getCompany());
            ps.setString(4, customer.getAddress());
            ps.setString(5, customer.getCity());
            ps.setString(6, customer.getState());
            ps.setString(7, customer.getCountry());
            ps.setString(8, customer.getPostalCode());
            ps.setString(9, customer.getPhone());
            ps.setString(10, customer.getFax());
            ps.setString(11, customer.getEmail());
            ps.setObject(12, edao.readItem(customer.getSupportRepresentative().getEmployeeId()));
            ps.setInt(13, customer.getCustomerId());
            ps.executeUpdate();
            con.commit();
            ps.close();
            System.out.println("Customer with ID " + customer.getCustomerId() + " modified succesfully");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteItem(Customer customer) {
        try{
            con.setAutoCommit(false);
            String query = "DELETE FROM Customer WHERE CustomerId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, customer.getCustomerId());
            ps.executeUpdate();
            con.commit();
            ps.close();
            System.out.println("Customer with ID " + customer.getCustomerId() + " deleted succesfully");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
