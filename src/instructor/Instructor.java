package instructor;

import java.sql.*;

public class Instructor {

    // Method to establish a connection to the SQLite database
    private Connection connect() {
        String url = "jdbc:sqlite:your_database_name.db"; // Path to your SQLite database
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // CREATE: Insert a new instructor into the database
    public void createInstructor(String name, String email, String department, String phoneNumber, String hireDate) {
        String sql = "INSERT INTO Instructor(name, email, department, phone_number, hire_date) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, department);
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, hireDate);
            pstmt.executeUpdate();
            System.out.println("Instructor added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // READ: Retrieve all instructors from the database
    public void readInstructors() {
        String sql = "SELECT * FROM Instructor";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + "\t" +
                                   "Name: " + rs.getString("name") + "\t" +
                                   "Email: " + rs.getString("email") + "\t" +
                                   "Department: " + rs.getString("department") + "\t" +
                                   "Phone: " + rs.getString("phone_number") + "\t" +
                                   "Hire Date: " + rs.getString("hire_date"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // UPDATE: Update an instructor's details based on their ID
    public void updateInstructor(int id, String name, String email, String department, String phoneNumber, String hireDate) {
        String sql = "UPDATE Instructor SET name = ?, email = ?, department = ?, phone_number = ?, hire_date = ? WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, department);
            pstmt.setString(4, phoneNumber);
            pstmt.setString(5, hireDate);
            pstmt.setInt(6, id);
            pstmt.executeUpdate();
            System.out.println("Instructor updated successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // DELETE: Remove an instructor from the database based on their ID
    public void deleteInstructor(int id) {
        String sql = "DELETE FROM Instructor WHERE id = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Instructor deleted successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}


