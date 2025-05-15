package pt16.ex1;

import java.util.List;

public interface DAOGeneric<T> {
    T createItem(T t);
    T readItem (int idItem);
    List<T> readItems();
    T updateItem(T t);
    T deleteItem(T t);

}
