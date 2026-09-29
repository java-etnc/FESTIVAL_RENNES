package models.DAO;

import models.entities.AbstractEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/*
 * Le CRUD en mémoire, écrit une fois pour toutes. Le jour J : DAOXxxImpl extends MemoirDao<Xxx>.
 * "persist" est protected : tes requêtes spécifiques parcourent persist.values().
 */
public abstract class MemoirDao<T extends AbstractEntity> implements Dao<T> {

    protected Map<Long, T> persist = new HashMap<>();

    private Long sequence = 1L;

    private Long incrementationAutoSequence() {
        return sequence++;
    }

    @Override
    public void create(T entity) {
        entity.setId(incrementationAutoSequence());
        persist.put(entity.getId(), entity);
    }

    @Override
    public T read(Long id) {
        return persist.get(id);
    }

    @Override
    public Collection<T> readAll() {
        return Collections.unmodifiableCollection(persist.values());
    }

    @Override
    public void update(T entity) {
        persist.replace(entity.getId(), entity);
    }

    @Override
    public void delete(Long id) {
        persist.remove(id);
    }

    @Override
    public void delete(T entity) {
        persist.remove(entity.getId());
    }

    @Override
    public boolean exist(Long id) {
        return persist.containsKey(id);
    }

    @Override
    public Long count() {
        return Long.valueOf(persist.size());
    }
}
