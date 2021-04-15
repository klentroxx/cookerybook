package hu.cookerybook.core.dbconn;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseFunctions {

    private String connectionURL;

    public DatabaseFunctions() {
        try {
            Properties properties = new Properties();
            properties.load(getClass().getResourceAsStream("/application.properties"));
            connectionURL = properties.getProperty("jdbc.jdbc-url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDataInDatabase(String query) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(connectionURL);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }

    public List<String[]> getDataFromDatabase(String query) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        List<String[]> resultList = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(connectionURL);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String[] tmp = new String[9];
                for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                    tmp[i - 1] = resultSet.getString(i);
                }
                resultList.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }

        return resultList;
    }

    private void closeConnection(Connection connection, Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
