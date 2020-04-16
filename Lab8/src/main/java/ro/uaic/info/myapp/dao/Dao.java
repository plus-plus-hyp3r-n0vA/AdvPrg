package ro.uaic.info.myapp.dao;

public interface Dao<T> {
    public  Integer create(T t) throws DbException;
    public  T findById(int id) throws DbException;
}
