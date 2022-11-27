package ua.com.javarush.quest.gribanov.questdelta.repository;

import java.util.Collection;
import java.util.stream.Stream;

public interface Repository<T> {
    Collection<T> getAll();

    Stream<T> find(T template);
    T getByID(long id);
    boolean add(T entity);
    void update(T entity);
    void remove(T entity);
}
