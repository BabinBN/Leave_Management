package customer.leave_management;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
public class DBconnection implements CommandLineRunner {

    private final DataSource dataSource;

    public DBconnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) {
        try (Connection conn = dataSource.getConnection()) {
            if (conn.isValid(5)) {
                System.out.println("✅ Database connection is valid");
            } else {
                System.out.println("❌ Database connection is NOT valid");
            }
        } catch (Exception e) {
            System.err.println("❌ Failed to connect: " + e.getMessage());
        }
    }
}
