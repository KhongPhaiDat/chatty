import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseConnectionTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testDatabaseConnection() {
        try {
            // A simple query that should always succeed
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            System.out.println("Connection Successful!");
        } catch (Exception e) {
            System.out.println("Connection Failed: " + e.getMessage());
        }
    }
}
