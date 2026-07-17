package domain.entity;

import domain.enums.RequestStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public class Request {

    private Long id;
    private String number;
    private LocalDateTime createdAt;
    private Employee author;
    private Employee executor;
    private String description;
    private LocalDateTime deadline;
    private RequestStatus status;

    public static Request create(
            String number,
            Employee author,
            Employee executor,
            String description,
            LocalDateTime deadline
    ) {
        Request request = new Request();
        request.number = number;
        request.author = author;
        request.executor = executor;
        request.description = description;
        request.deadline = deadline;
        request.status = RequestStatus.NEW;
        request.createdAt = LocalDateTime.now();
        return request;
    }

    public void updateStatus(RequestStatus newStatus) {
        this.status = newStatus;
    }

    public void updateExecutor(Employee newExecutor) {
        this.executor = newExecutor;
    }
}
