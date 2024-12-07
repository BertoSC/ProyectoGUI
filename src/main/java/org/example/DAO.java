package org.example;
import java.util.List;

public interface DAO<T> {
    List<T> getAll();
    boolean add(T object);
    boolean delete(int id);
    boolean update(T object);
}
