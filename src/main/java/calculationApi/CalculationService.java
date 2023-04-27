package calculationApi;
//This class will contain the business logic for performing the calculations and inserting the data into the database.

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CalculationService {
    private static final String INSERT_CALCULATION_SQL = "INSERT INTO calculations (number1, number2, operation) VALUES (?, ?, ?)";
    private static final String SELECT_LAST_CALCULATION_SQL = "SELECT result FROM calculations ORDER BY id DESC LIMIT 1";
    
    public CalculationResult performCalculation(Calculation calculation) throws SQLException, NamingException {
        int result = 0;
        
        switch (calculation.getOperation()) {
            case "+":
                result = calculation.getNumber1() + calculation.getNumber2();
                break;
            case "-":
                result = calculation.getNumber1() - calculation.getNumber2();
                break;
            case "*":
                result = calculation.getNumber1() * calculation.getNumber2();
                break;
            case "/":
                result = calculation.getNumber1() / calculation.getNumber2();
                break;
            default:
                throw new IllegalArgumentException("Invalid operation: " + calculation.getOperation());
        }
        
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_CALCULATION_SQL)) {
            statement.setInt(1, calculation.getNumber1());
            statement.setInt(2, calculation.getNumber2());
            statement.setString(3, calculation.getOperation());
            statement.executeUpdate();
        }
        
        CalculationResult calculationResult = new CalculationResult();
        calculationResult.setResult(result);
        return calculationResult;
    }
    
    public int getLastCalculationResult() throws SQLException, NamingException {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_LAST_CALCULATION_SQL);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("result");
            } else {
                throw new SQLException("No calculations found");
            }
        }
    }
    
    private Connection getConnection() throws SQLException, NamingException {
        InitialContext initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:/comp/env/jdbc/myDataSource");
        return dataSource.getConnection();
    }
}

