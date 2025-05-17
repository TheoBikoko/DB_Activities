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
            rs.close();
            ps.close();
            return newEmployee;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return newEmployee;
    }

    @Override
    public Employee readItem(int employeeId) {
        try{
            String query = "SELECT * FROM Employee WHERE EmployeeId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, employeeId);
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
            rs.close();
            ps.close();

            return new Employee(employeeId, newLastName, newFirstName, newTitle, newReportsTo, newBirthDate, newHireDate, newAddress, newCity, newState, newCountry, newPostalCode, newPhone, newFax, newEmail);
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Employee> readItems() {
        try{
            List<Employee> employees= new ArrayList<>();
            String query = "SELECT * FROM Employee";
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
            rs.close();
            ps.close();
            return employees;
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateItem(Employee employee) {
        try{
            con.setAutoCommit(false);
            String query = "UPDATE Employee SET LastName = ?, FirstName = ?, Title = ?, ReportsTo = ?, BirthDate = ?, HireDate = ?, Address = ?, City = ?, State = ?, Country = ?, PostalCode = ?, Phone = ?, Fax = ?, Email = ? WHERE EmployeeId = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, employee.getLastName());
            ps.setString(2, employee.getFirstName());
            ps.setString(3, employee.getTitle());
            ps.setInt(4, employee.getReportsTo());
            ps.setDate(5, employee.getBirthDate());
            ps.setDate(6, employee.getHireDate());
            ps.setString(7, employee.getAddress());
            ps.setString(8, employee.getCity());
            ps.setString(9, employee.getState());
            ps.setString(10, employee.getCountry());
            ps.setString(11, employee.getPostalCode());
            ps.setString(12, employee.getPhone());
            ps.setString(13, employee.getFax());
            ps.setString(14, employee.getEmail());
            ps.setInt(15, employee.getEmployeeId());
            ps.executeUpdate();
            con.commit();
            ps.close();
            System.out.println("Employee with ID " + employee.getEmployeeId() + " modified succesfully");
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteItem(Employee employee) {
        try{
        con.setAutoCommit(false);
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
