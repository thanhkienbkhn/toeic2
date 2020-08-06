package vn.kien.core.data.daoimpl;

import org.hibernate.*;
import vn.kien.core.common.utils.HibernateUtil;
import vn.kien.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private Class<T> perssistenceClass;

    public AbstractDao() {
        this.perssistenceClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName() {
        return this.perssistenceClass.getSimpleName();
    }

    @Override
    public List<T> findAll() {
        List<T> list;
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return list;
    }

    @Override
    public T update(T entity) {
        T result = null;
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Object obj = session.merge(entity);
            result = (T) obj;
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public void save(final T entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public T findById(final ID id) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        T result = null;
        try {
            result = (T) session.get(perssistenceClass, id);
            if (result == null) {
                throw new ObjectNotFoundException("Not found entity with id: " + id + " in class " + perssistenceClass.getSimpleName(), null);
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }

        return result;
    }

    @Override
    public Object[] findByProperty(final String property, final Object value, final String sortExpression, final String sortDirection) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        Object[] res = new Object[2];
        try {
            StringBuilder sql = new StringBuilder("FROM ");
            sql.append(this.getPersistenceClassName());
            sql.append(" WHERE " + property + " = :value");
            if (sortExpression != null && sortDirection != null) {
                sql.append(" ORDER BY ").append(sortExpression).append(" " + sortDirection);
            }
            Query query = session.createQuery(sql.toString());
            query.setParameter("value", value);
            List<T> list = query.list();
            res[0] = list;
            res[1] = list.size();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return res;
    }

    @Override
    public Integer delete(final List<ID> list) {
        Integer count = 0;
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            for (ID item : list) {
                T entity = (T) session.get(this.perssistenceClass, item);
                session.delete(entity);
                count++;
            }
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return count;
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }

}
