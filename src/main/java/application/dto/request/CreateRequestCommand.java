package application.dto.request;

import java.time.LocalDateTime;

public record CreateRequestCommand(

        Long authorId,

        Long executorId,

        String description,

        LocalDateTime deadline

) {}
