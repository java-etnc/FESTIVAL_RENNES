package models.DAO;

import java.util.Collection;

/* Le contrat CRUD commun à toutes les entités. Ne change jamais. */
public interface Dao<T> {

    void create(T entity);

    T read(Long id);

    Collection<T> readAll();

    void update(T entity);

    void delete(Long id);

    void delete(T entity);

    boolean exist(Long id);

    Long count();
}
