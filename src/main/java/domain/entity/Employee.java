package domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Employee {
    private Long id;
    private String fullName;
    private String position;
    private Department department;
}
