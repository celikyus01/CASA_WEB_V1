package com.app.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {
    private static Connection connection;
    private static Statement statement;
    private static Statement statement1;
    private static ResultSet resultSet;

    public static void createConnection() {
        String dbUrl = ConfigurationReader.get("dbUrl");
        String dbUsername = ConfigurationReader.get("dbUsername");
        String dbPassword = ConfigurationReader.get("dbPassword");

        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void destroy() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param query
     * @return returns a single cell value. If the results in multiple rows and/or
     * columns of data, only first column of the first row will be returned.
     * The rest of the data will be ignored
     */
    public static Object getCellValue(String query) {
        return getQueryResultList(query).get(0).get(0);
    }

    /**
     * @param query
     * @return returns a list of Strings which represent a row of data. If the query
     * results in multiple rows and/or columns of data, only first row will
     * be returned. The rest of the data will be ignored
     */
    public static List<Object> getRowList(String query) {
        return getQueryResultList(query).get(0);
    }

    /**
     * @param query
     * @return returns a map which represent a row of data where key is the column
     * name. If the query results in multiple rows and/or columns of data,
     * only first row will be returned. The rest of the data will be ignored
     */
    public static Map<String, Object> getRowMap(String query) {
        return getQueryResultMap(query).get(0);
    }

    /**
     * @param query
     * @return returns query result in a list of lists where outer list represents
     * collection of rows and inner lists represent a single row
     */
    public static List<List<Object>> getQueryResultList(String query) {
        executeQuery(query);
        List<List<Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                List<Object> row = new ArrayList<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    row.add(resultSet.getObject(i));
                }
                rowList.add(row);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @param column
     * @return list of values of a single column from the result set
     */
    public static List<Object> getColumnData(String query, String column) {
        executeQuery(query);
        List<Object> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                rowList.add(resultSet.getObject(column));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return returns query result in a list of maps where the list represents
     * collection of rows and a map represents represent a single row with
     * key being the column name
     */
    public static List<Map<String, Object>> getQueryResultMap(String query) {
        executeQuery(query);
        List<Map<String, Object>> rowList = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            while (resultSet.next()) {
                Map<String, Object> colNameValueMap = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    colNameValueMap.put(rsmd.getColumnName(i), resultSet.getObject(i));
                }
                rowList.add(colNameValueMap);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rowList;
    }

    /**
     * @param query
     * @return List of columns returned in result set
     */
    public static List<String> getColumnNames(String query) {
        executeQuery(query);
        List<String> columns = new ArrayList<>();
        ResultSetMetaData rsmd;
        try {
            rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                columns.add(rsmd.getColumnName(i));
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return columns;
    }

    private static void executeQuery(String query) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public static int getRowCount() throws Exception {
        resultSet.last();
        int rowCount = resultSet.getRow();
        return rowCount;
    }

    // ----------  ----------  ---------- APPLICATION RELATED METHODS


    /**
     * @param organisationID
     * @param columnName:    Column name to be updated
     * @param newValue
     */
    public static void update_orgSecPol(int organisationID, String columnName, String newValue) {

        String query = "UPDATE ast_org_user_sec_policy\n" +
                "  SET " + columnName + " = '" + newValue + "'\n" +
                "  where org_id=" + organisationID + "\n";

        System.out.println(query);


        update(query);


    }


    /**
     * @param organisationName: should be exact or beginning part of the organisation name
     * @param columnName:       Column name to be updated
     * @param newValue
     */
    public static void update_orgSecPol(String organisationName, String columnName, String newValue) {

        String organisationID = getOrgIDByName(organisationName).toString();

        String query = "UPDATE ast_org_user_sec_policy\n" +
                "  SET " + columnName + " = '" + newValue + "'\n" +
                "  where org_id=" + organisationID + "\n";

        System.out.println(query);


        update(query);


    }

    public static void update_orgSecPol(String organisationName, String columnName, Integer newValue) {

        String organisationID = getOrgIDByName(organisationName).toString();

        String query = "UPDATE ast_org_user_sec_policy\n" +
                "  SET " + columnName + " = " + newValue + "\n" +
                "  where org_id=" + organisationID + "\n";

        System.out.println(query);


        update(query);


    }

    public static Object get_orgSecPol(int organisationID, String columnName) {
        String query = "SELECT " + columnName + "\n" +
                "FROM ast_org_user_sec_policy \n" +
                "WHERE org_id=" + organisationID + "";

        return getCellValue(query);

    }

    public static Object get_orgSecPol(String organisationName, String columnName) {

        Object orgIDByName = getOrgIDByName(organisationName);

        String query = "SELECT " + columnName + "\n" +
                "FROM ast_org_user_sec_policy \n" +
                "WHERE org_id=" + orgIDByName.toString() + "";

        return getCellValue(query);

    }

    /**
     * @param orgName: should be exact or beginning part of the organisation name
     * @return organisation ID in Object format. Should be converted to desired format.. i.e String, Integer
     */

    public static Object getOrgIDByName(String orgName) {

        String query = "select org_id from ac_organisation\n" +
                "where org_name like '" + orgName + "%'";

        return getCellValue(query);

    }


    public static void getDefaults(String orgID) {

        int PASSWORD_MIN_SIZE = 8;
        int ALPHABETIC_MIN_SIZE = 0;


    }

    public static void findDefaultValuesForPasswordCreate(ExcelUtil excelUtil) {





    }


    public static void update(String query) {



        createConnection();
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void update(String criteria, String newValue) {

        String query = "UPDATE ast_org_user_sec_policy\n" +
                "  SET "+criteria+" = '"+newValue+"'\n" +
                "  where org_id=621";


        createConnection();
        try {
            connection.createStatement().executeUpdate(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public static void main(String[] args) {


//        System.out.println('a');
//        Statement statement = connection.prepareStatement(String.valueOf(ResultSet.TYPE_SCROLL_INSENSITIVE), ResultSet.CONCUR_UPDATABLE);
//
//
//        statement.executeUpdate("UPDATE ast_org_user_sec_policy\n" +
//                "  SET PASSWORD_MIN_SIZE = 6\n" +
//                "  where org_id=621");
// ============= ============= ============= ============= ============= ============= =============


//
//        try {
//            Statement statement1 = connection.createStatement();
//            statement1.executeUpdate("UPDATE ast_org_user_sec_policy\n" +
//                    "  SET PASSWORD_MIN_SIZE = 11\n" +
//                    "  where org_id=621");
//        } catch (SQLException throwables) {
//            System.out.println("throw");
//            throwables.printStackTrace();
//        }

//        System.out.println('a');

// ============= ============= ============= ============= ============= ============= =============
//

//    update("UPDATE ast_org_user_sec_policy\n"+
//                   "  SET PASSWORD_MIN_SIZE = '8.0'\n"+
//                   "  where org_id=621");

    update("UPDATE ast_org_user_sec_policy\n" +
            "  SET SPECIAL_CHAR_MIN_SIZE = '1.0'\n" +
            "  where org_id=621");



        System.out.println('a');


}



}
