package infrastructure.service;

import domain.service.RequestNumberGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SequentialRequestNumberGenerator implements RequestNumberGenerator {
    @Override
    public String generate() {
        return "REQ-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
