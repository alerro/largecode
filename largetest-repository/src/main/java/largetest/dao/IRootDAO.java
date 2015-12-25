package largetest.dao;

import largetest.domain.Root;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;


public interface IRootDAO<Model extends Root> {
    Model load(long id);
    Root load(long id, String entity);
    Root load(long id, Class persistenceClass);
    Long	save(Model entity) throws DataAccessException;
    void	update(Model entity);
    void	merge(Model entity);
    void	remove(long id);
    void	remove(Model entity);
    List<Model> findAllList();
    Map<Long, Model> findAllMap();
    Model	get(long id);
    Root get(long id, String entity);
    Root get(long id, Class persistenceClass);
    long getTotalNumber();
    void persist(Model entity);

}
