package domain.repository;

import domain.entity.Request;

public interface RequestRepository {
    Request findById(long id);
    Request save(Request request);
    Request update(Request request);
    void delete(Request request);
}
