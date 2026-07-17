package employeerequests.infrastructure.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void seed() {
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM departments", Integer.class);
        if (count != null && count > 0) {
            return;
        }

        System.out.println("Starting database seeding...");

        jdbcTemplate.update("INSERT INTO departments (name) VALUES ('IT Department')");
        Long deptId = jdbcTemplate.queryForObject("SELECT id FROM departments LIMIT 1", Long.class);

        List<Object[]> employees = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            employees.add(new Object[]{"Employee " + i, "Position " + i, deptId});
        }
        jdbcTemplate.batchUpdate("INSERT INTO employees (full_name, position, department_id) VALUES (?, ?, ?)", employees);
        System.out.println("1000 employees created.");

        String[] statuses = {"NEW", "IN_PROGRESS", "COMPLETED"};
        int batchSize = 10000;
        List<Object[]> requests = new ArrayList<>();

        for (int i = 1; i <= 1000000; i++) {
            String number = "REQ-" + String.format("%07d", i);
            Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now().minusDays((int) (Math.random() * 30)));
            long authorId = (long) (Math.random() * 1000) + 1;
            long executorId = (long) (Math.random() * 1000) + 1;
            String description = "Description for request " + i;

            int daysAdd = (int) (Math.random() * 20) - 10;
            Timestamp deadline = Timestamp.valueOf(LocalDateTime.now().plusDays(daysAdd));
            String status = statuses[(int) (Math.random() * statuses.length)];

            requests.add(new Object[]{number, createdAt, authorId, executorId, description, deadline, status});

            if (i % batchSize == 0) {
                jdbcTemplate.batchUpdate("INSERT INTO requests (number, created_at, author_id, executor_id, description, deadline, status) VALUES (?, ?, ?, ?, ?, ?, ?)", requests);
                requests.clear();
                System.out.println(i + " requests created...");
            }
        }
        System.out.println("Database seeding completed!");
    }
}