package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlHelper {
    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    public static void clearData() {
        val deleteOrderEntity = "DELETE FROM order_entity";
        val deletePaymentEntity = "DELETE FROM payment_entity";
        val deleteCreditRequestEntity = "DELETE FROM credit_request_entity";
        val runner = new QueryRunner();
        try (
                val conn = DriverManager.getConnection(url, user, password
                );
        ) {
            runner.update(conn, deleteOrderEntity);
            runner.update(conn, deletePaymentEntity);
            runner.update(conn, deleteCreditRequestEntity);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Long countRecords() {
        val countSQL = "SELECT COUNT(*) FROM order_entity;";
        val runner = new QueryRunner();
        Long count = null;
        try (
                val conn = DriverManager.getConnection(url, user, password
                );
        ) {
            count = runner.query(conn, countSQL, new ScalarHandler<>());
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public static String getStatus(String query) {
        val runner = new QueryRunner();
        String data = "";
        try (
                val conn = DriverManager.getConnection(url, user, password
                );
        ) {
            data = runner.query(conn, query, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static String getPaymentStatus() {
        val statusSQL = "SELECT status FROM payment_entity;";
        return getStatus(statusSQL);
    }

    public static String getCreditStatus() {
        val statusSQL = "SELECT status FROM credit_request_entity;";
        return getStatus(statusSQL);
    }
}