package vn.kien.core.data.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import vn.kien.core.common.utils.HibernateUtil;
import vn.kien.core.data.dao.GenericDao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID, T> {
    private Class<T> perssistenceClass;

    public AbstractDao() {
        this.perssistenceClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName(){
        return this.perssistenceClass.getSimpleName();
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        Transaction transaction = this.getSession().beginTransaction();

        try {
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = this.getSession().createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            throw e;
        } finally {
            this.getSession().close();
        }

        return list;
    }

    @Override
    public T update(T entity) {
        T result = null;
        Transaction transaction = this.getSession().beginTransaction();
        try {
            Object obj = this.getSession().merge(entity);
            result = (T) obj;
            transaction.commit();
        } catch (HibernateException e){
            transaction.rollback();
            throw e;
        }
        return result;
    }

    protected Session getSession() {
        return HibernateUtil.getSessionFactory().openSession();
    }


}
