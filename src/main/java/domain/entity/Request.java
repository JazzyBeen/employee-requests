package domain.entity;

import java.time.LocalDateTime;

public class Request {
    Long id;
    String number;
    LocalDateTime crawledAt;
    Employee author;
    Employee executor;
    String description;
    LocalDateTime deadline;
    RequestStatus status;
}
