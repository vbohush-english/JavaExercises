package net.bohush.exercises.chapter43;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Exercise19 {
    private Statement statement;
    private Connection connection;

    private String operation = "";
    private String id = "";
    private String lastName = "";
    private String firstName = "";
    private String mi = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String telephone = "";
    private String errorMessage = "";
        
    public Exercise19() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "scott", "tiger");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(net.bohush.exercises.chapter42.Exercise13.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    public void doOperation() {
        if(operation.equals("")) {
            try {
                ResultSet rset = statement.executeQuery("select * from Staff limit 1;");
                if(rset.next()) {
                    id = rset.getString(1);
                    lastName = rset.getString(2);
                    firstName = rset.getString(3);
                    mi = rset.getString(4);
                    address = rset.getString(5);
                    city = rset.getString(6);
                    state = rset.getString(7);
                    telephone = rset.getString(8);
                    errorMessage = "";  
                } else {
                    id = "";
                    resetData();
                    errorMessage = "Table is empty";
                }                
            } catch (SQLException ex) {
                id = "";
                resetData();
                errorMessage = "Table is empty";
            }
        } else if(operation.equals("View")) {
            if((id == null) || (id.equals(""))) {
                id = "";
                resetData();
                errorMessage = "Enter ID";   
            } else {
                try {
                    ResultSet rset = statement.executeQuery("select * from Staff where id = " + id + " limit 1;");
                    if(rset.next()) {
                        id = rset.getString(1);
                        lastName = rset.getString(2);
                        firstName = rset.getString(3);
                        mi = rset.getString(4);
                        address = rset.getString(5);
                        city = rset.getString(6);
                        state = rset.getString(7);
                        telephone = rset.getString(8);
                        errorMessage = "";            
                    } else {
                        resetData();
                        errorMessage = "ID " + id + " not found";   
                    }                
                } catch (SQLException ex) {
                    resetData();
                    errorMessage = "ID " + id + " not found";      
                }
            }
        } else if (operation.equals("Insert")) {
            if((id == null) || (id.equals(""))) {
                errorMessage = "Enter ID";  
            } else {
                String queryString = "insert into Staff (id, lastName, firstName, mi, address, city, state, telephone) values (?, ?, ?, ?, ?, ?, ?, ?);";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                    preparedStatement.setString(1, id);
                    preparedStatement.setString(2, lastName);
                    preparedStatement.setString(3, firstName);
                    preparedStatement.setString(4, mi);
                    preparedStatement.setString(5, address);
                    preparedStatement.setString(6, city);
                    preparedStatement.setString(7, state);
                    preparedStatement.setString(8, telephone);
                    preparedStatement.executeUpdate();
                    errorMessage = "";   
                } catch (SQLException e2) {
                    errorMessage = e2.getMessage();   
                }
            }
        } else if (operation.equals("Update")) {
            if((id == null) || (id.equals(""))) {
                resetData();
                errorMessage = "ID " + id + " not found";   
            } else {
                String queryString = "update Staff set lastName = ?, firstName = ?, mi = ?, address = ?, city = ?, state = ?, telephone = ? where id = ?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(queryString);
                    preparedStatement.setString(1, lastName);
                    preparedStatement.setString(2, firstName);
                    preparedStatement.setString(3, mi);
                    preparedStatement.setString(4, address);
                    preparedStatement.setString(5, city);
                    preparedStatement.setString(6, state);
                    preparedStatement.setString(7, telephone);
                    preparedStatement.setString(8, id);
                    preparedStatement.executeUpdate();
                    errorMessage = "";  
                } catch (SQLException e2) {
                    errorMessage = e2.getMessage();   
                }
            }
        } else if (operation.equals("Delete")) {
            if((id == null) || (id.equals(""))) {
                id = "";
                resetData();
                errorMessage = "Enter ID"; 
            } else {
                try {
                    statement.executeUpdate("delete from Staff where id = " + id + "");
                    try {
                        ResultSet rset = statement.executeQuery("select * from Staff limit 1;");
                        if (rset.next()) {
                            id = rset.getString(1);
                            lastName = rset.getString(2);
                            firstName = rset.getString(3);
                            mi = rset.getString(4);
                            address = rset.getString(5);
                            city = rset.getString(6);
                            state = rset.getString(7);
                            telephone = rset.getString(8);
                            errorMessage = "";  
                        } else {
                            id = "";
                            resetData();
                            errorMessage = "Table is empty";
                        }
                    } catch (SQLException ex) {
                        id = "";
                        resetData();
                        errorMessage = "Table is empty";        
                    }
                } catch (SQLException ex) {
                    id = "";
                    resetData();
                    errorMessage = ex.getMessage();   
                }
            }
        }
        operation = "";
    }
    
    private void resetData() {
        lastName = "";
        firstName = "";
        mi = "";
        address = "";
        city = "";
        state = "";
        telephone = "";
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMi() {
        return mi;
    }

    public String getState() {
        return state;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMi(String mi) {
        this.mi = mi;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    
}
