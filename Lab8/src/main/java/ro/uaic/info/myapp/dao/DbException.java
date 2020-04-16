package ro.uaic.info.myapp.dao;

public class DbException extends Exception {
    public DbException(String err) {
        super(err);
    }
    public DbException(String err, Exception ex) {
        super(err, ex);
    }
}
