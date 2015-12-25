package largetest.dao.impl;


import largetest.dao.IRootDAO;
import largetest.domain.Root;
import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RootDAOImpl<Model extends Root> extends HibernateDaoSupport implements IRootDAO<Model> {
    private final static Logger logger = LoggerFactory.getLogger(RootDAOImpl.class);
    private String entityName;
    private Class<Model> persistentClass;



    public RootDAOImpl(String entityName, Class<Model> persistentClass) {
        this.entityName = entityName;
        this.persistentClass = persistentClass;
    }

    public RootDAOImpl(Class<Model> persistentClass) {
        this.persistentClass = persistentClass;
    }

    @Override
    public Model get(long id) {
        logger.debug("loading entity with id={}",id);
        return (Model) getSession().get(entityName, id);
    }

    @Override
    public Root get(long id, String entity) {
        logger.debug("loading entity {} with id={}",entity,id);
        return (Root) getSession().get(entity, id);
    }

    @Override
    public Root get(long id, Class persistenceClass) {
        logger.debug("loading entity {} with id={}",persistenceClass,id);
        return (Root) getSession().get(persistenceClass, id);
    }

    @Override
    public Model load(long id) {
        logger.debug("loading entity with id={}",id);
        return (Model) getSession().load(entityName, id);
    }

    @Override
    public Root load(long id, String entity) {
        logger.debug("loading entity {} with id={}",entity,id);
        return (Root) getSession().load(entity, id);
    }

    @Override
    public Root load(long id, Class persistenceClass) {
        logger.debug("loading entity {} with id={}",persistenceClass,id);
        return (Root) getSession().load(persistenceClass, id);
    }


    @Override
    public Long save(Model entity) throws DataAccessException {
        logger.debug("saving entity {} with id={}",entityName, entity.getId());
        return (Long) getSession().save(entity);
    }

    @Override
    public void remove(long id) {
        logger.debug("removing entity with id={} ", id);
        getSession().delete(this.get(id));
    }

    @Override
    public void remove(Model entity) {
        logger.debug("removing entity {} with id={}", entityName, entity.getId());
        getSession().delete(entity);
    }

    @Override
    public void update(Model entity) {
        logger.debug("updating entity {} with id={}", entityName, entity.getId());
        getSession().update(entity);
    }

    @Override
    public void merge(Model entity) {
        logger.debug("merging entity {} with id={}", entityName, entity.getId());
        getSession().merge(entity);
    }

    @Override
    public void persist(Model entity) {
        logger.debug("persist entity {} with id={}", entityName, entity.getId());
        getSession().persist(entity);
    }

    @Override
    public List<Model> findAllList() {
        logger.debug("loading all entities of {} ", entityName);
        StringBuilder stringBuilder = getStartOfSQL().append(" order by id asc ");
        return getSession().createQuery(stringBuilder.toString()).list();
    }

    @Override
    public Map<Long, Model> findAllMap() {
        logger.debug("loading all entities of {} as map", entityName);
        List<Model> list = findAllList();
        if (list != null) {
            Map<Long, Model> result = new HashMap<Long, Model>();
            for (Model elem : list) {
                result.put(elem.getId(), elem);
            }
            return result;
        }
        return null;
    }


    @Override
    public long getTotalNumber() {
        logger.debug("loading total number of {}", entityName);
        StringBuilder countString = getCountSQL();
        Query query = getSession().createQuery(countString.toString());
        return  (Long)query.uniqueResult();
    }


    protected StringBuilder getStartOfSQL() {
        return new StringBuilder(" select entity from ").append(entityName).append(" as entity ");
    }

    private StringBuilder getCountSQL() {
        return new StringBuilder(" select count(entity.id) from ").append(entityName).append(" as entity ");
    }

    protected void closeAfterRaw(Connection connection, PreparedStatement preparedStatement, ResultSet rs) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (rs != null) {
                rs.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Exception on closing connection", e);
        }
    }

}
