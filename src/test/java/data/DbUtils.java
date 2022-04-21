package data;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    static String url = "jdbc:mysql://localhost:3306/app";
    static String user = "app";
    static String password = "pass";


    public static void cleanDB() {
        var runner = new QueryRunner();

        try (
                var conn = DriverManager.getConnection(
                        url, user, password
                );

        ) {
            runner.execute(conn, "DELETE FROM credit_request_entity;");
            runner.execute(conn, "DELETE FROM order_entity;");
            runner.execute(conn, "DELETE FROM payment_entity;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String findPaymentStatus() {
        return getData("SELECT status FROM payment_entity;");
    }

    public static String findCreditRequestStatus() {
        return getData("SELECT status FROM credit_request_entity;");
    }

    public static String findCountOrderEntity() {
        Long count = null;
        val codesSQL = " SELECT COUNT(*) FROM order_entity;";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            count = runner.query(conn, codesSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Long.toString(count);
    }

    public static String getData(String query) {
        String data = "";
        val runner = new QueryRunner();
        try (val conn = DriverManager.getConnection(url, user, password)) {
            data = runner.query(conn, query, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return data;
    }
}
