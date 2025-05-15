package pt16.ex1;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static pt15.albumDao.Connexio.con;

public class EmployeeDaoImplementation implements DAOGeneric<Employee>{
    @Override
    public int createItem(Employee employee) {
        int newEmployee = -1;
        try {
            String query = "INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, employee.getEmployeeId());
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getFirstName());
            ps.setString(4, employee.getTitle());
            ps.setInt(5, employee.getReportsTo());
            ps.setDate(6, employee.getBirthDate());
            ps.setDate(7, employee.getHireDate());
            ps.setString(8, employee.getAddress());
            ps.setString(9, employee.getCity());
            ps.setString(10, employee.getState());
            ps.setString(11, employee.getCountry());
            ps.setString(12, employee.getPostalCode());
            ps.setString(13, employee.getPhone());
            ps.setString(14, employee.getFax());
            ps.setString(15, employee.getEmail());


            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.next();
            newEmployee = rs.getInt(1);
            return newEmployee;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return newEmployee;
    }

    @Override
    public Employee readItem(int idItem) {
        Employee employee = null;
        try{
            String query = "SELECT * FROM Employee WHERE EmployeeId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String newLastName = rs.getString(2);
            String newFirstName = rs.getString(3);
            String newTitle = rs.getString(4);
            int newReportsTo = rs.getInt(5);
            Date newBirthDate = rs.getDate(6);
            Date newHireDate = rs.getDate(7);
            String newAddress = rs.getString(8);
            String newCity = rs.getString(9);
            String newState = rs.getString(10);
            String newCountry = rs.getString(11);
            String newPostalCode = rs.getString(12);
            String newPhone = rs.getString(13);
            String newFax = rs.getString(14);
            String newEmail = rs.getString(15);
            employee = new Employee(idItem, newLastName, newFirstName, newTitle, newReportsTo, newBirthDate, newHireDate, newAddress, newCity, newState, newCountry, newPostalCode, newPhone, newFax, newEmail);
            return employee;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return employee;
    }

    @Override
    public List<Employee> readItems() {
        List<Employee> employees= new ArrayList<>();
        try{
            String query = "SELECT * FROM Employee WHERE EmployeeId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int newEmployeeId = rs.getInt(1);
                String newLastName = rs.getString(2);
                String newFirstName = rs.getString(3);
                String newTitle = rs.getString(4);
                int newReportsTo = rs.getInt(5);
                Date newBirthDate = rs.getDate(6);
                Date newHireDate = rs.getDate(7);
                String newAddress = rs.getString(8);
                String newCity = rs.getString(9);
                String newState = rs.getString(10);
                String newCountry = rs.getString(11);
                String newPostalCode = rs.getString(12);
                String newPhone = rs.getString(13);
                String newFax = rs.getString(14);
                String newEmail = rs.getString(15);
                Employee employee = new Employee(newEmployeeId, newLastName, newFirstName, newTitle, newReportsTo, newBirthDate, newHireDate, newAddress, newCity, newState, newCountry, newPostalCode, newPhone, newFax, newEmail);
                employees.add(employee);
            }
            return employees;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return employees;
    }

    @Override
    public void updateItem(Employee employee) {
        try{
            String query = "UPDATE Employee SET WHERE EmployeeId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(2, employee.getLastName());
            ps.setString(3, employee.getFirstName());
            ps.setString(4, employee.getTitle());
            ps.setInt(5, employee.getReportsTo());
            ps.setDate(6, employee.getBirthDate());
            ps.setDate(7, employee.getHireDate());
            ps.setString(8, employee.getAddress());
            ps.setString(9, employee.getCity());
            ps.setString(10, employee.getState());
            ps.setString(11, employee.getCountry());
            ps.setString(12, employee.getPostalCode());
            ps.setString(13, employee.getPhone());
            ps.setString(14, employee.getFax());
            ps.setString(15, employee.getEmail());
            ps.executeUpdate();
            con.commit();;
            ps.close();
            System.out.println("Employee with ID " + employee.getEmployeeId() + " modified succesfully");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteItem(Employee employee) {
        try{
        String query = "DELETE FROM Employee WHERE EmployeeId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, employee.getEmployeeId());
        ps.executeUpdate();
        con.commit();
        ps.close();
            System.out.println("Employee with ID " + employee.getEmployeeId() + " deleted succesfully");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }
}
