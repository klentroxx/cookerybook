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
            this.connectionURL = properties.getProperty("jdbc.jdbc-url");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDataInDatabase(String query, List<PreparedStatementParameter> parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = DriverManager.getConnection(this.connectionURL);
            preparedStatement = connection.prepareStatement(query);
            for (PreparedStatementParameter parameter : parameters) {
                switch (parameter.getParameterType()) {
                    case "string":
                        preparedStatement.setString(parameter.getParameterIndex(), parameter.getStringData());
                        break;
                    case "int":
                        preparedStatement.setInt(parameter.getParameterIndex(), parameter.getIntegerData());
                        break;
                    case "float":
                        preparedStatement.setFloat(parameter.getParameterIndex(), parameter.getFloatData());
                        break;
                }
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection, preparedStatement);
        }
    }

    public List<String[]> getDataFromDatabaseStat(String query) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        List<String[]> resultList = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(this.connectionURL);
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

    public List<String[]> getDataFromDatabasePrepStat(String query, List<PreparedStatementParameter> parameters) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet;
        List<String[]> resultList = new ArrayList<>();

        try {
            connection = DriverManager.getConnection(this.connectionURL);
            preparedStatement = connection.prepareStatement(query);
            for (PreparedStatementParameter parameter : parameters) {
                switch (parameter.getParameterType()) {
                    case "string":
                        preparedStatement.setString(parameter.getParameterIndex(), parameter.getStringData());
                        break;
                    case "int":
                        preparedStatement.setInt(parameter.getParameterIndex(), parameter.getIntegerData());
                        break;
                    case "float":
                        preparedStatement.setFloat(parameter.getParameterIndex(), parameter.getFloatData());
                        break;
                }
            }
            resultSet = preparedStatement.executeQuery();
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
            closeConnection(connection, preparedStatement);
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
