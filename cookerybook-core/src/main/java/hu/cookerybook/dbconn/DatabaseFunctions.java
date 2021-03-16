package hu.cookerybook.dbconn;

import hu.cookerybook.dao.Labels;

import java.sql.*;

public class DatabaseFunctions {

    public static void setDataInDatabase(String query) {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = DriverManager.getConnection(Labels.CONNECTION_STRING, Labels.DB_USER, Labels.DB_PASSWORD);
            statement = connection.createStatement();
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
    }

    public static ResultSet getDataFromDatabase(String query) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(Labels.CONNECTION_STRING, Labels.DB_USER, Labels.DB_PASSWORD);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, statement);
        }
        return resultSet;
    }

    private static void closeConnection(Connection connection, Statement statement) {
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
