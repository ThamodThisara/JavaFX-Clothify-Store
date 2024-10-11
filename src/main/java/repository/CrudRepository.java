package repository;

public interface CrudRepository <T> extends SuperDao{
    boolean addUser(T entity);
}
