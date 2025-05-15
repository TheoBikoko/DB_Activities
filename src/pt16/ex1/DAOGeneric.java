package pt16.ex1;

import java.util.List;

public interface DAOGeneric<T> {
    int createItem(T t);
    T readItem (int idItem);
    List<T> readItems();
    void updateItem(T t);
    void deleteItem(T t);

}
