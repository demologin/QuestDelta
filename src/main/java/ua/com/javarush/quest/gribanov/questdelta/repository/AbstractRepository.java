package ua.com.javarush.quest.gribanov.questdelta.repository;

import ua.com.javarush.quest.gribanov.questdelta.entity.AbstractEntity;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class AbstractRepository<T extends AbstractEntity> implements Repository<T>{
    public final static AtomicLong id = new AtomicLong(System.currentTimeMillis());
    protected final Map<Long, T> repository = new HashMap<>();

    @Override
    public Collection<T> getAll() {
        return repository.values();
    }

    protected <V> boolean isCoincide(T template, T current, Function<T, V> fieldGetter) {
        V currentFieldValue = fieldGetter.apply(current);
        V templateFieldValue = fieldGetter.apply(template);
        return Objects.isNull(templateFieldValue) || templateFieldValue.equals(currentFieldValue);
    }

    @Override
    public abstract Stream<T> find(T template);

    @Override
    public T getByID(long id) {
        return repository.get(id);
    }

    @Override
    public boolean add(T entity) {
        if (entity.getId() == null || entity.getId() < 1){
            entity.setId(id.incrementAndGet());
        }
        if (!repository.containsKey(entity.getId())){
            repository.put(entity.getId(), entity);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void update(T entity) {
        if (repository.containsKey(entity.getId())){
            repository.put(entity.getId(), entity);
        }
    }

    @Override
    public void remove(T entity) {
        repository.remove(entity.getId(), entity);
    }

}
