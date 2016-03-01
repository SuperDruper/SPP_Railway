package service;

import java.util.List;

/**
 * Created by PC-Alyaksei on 29.02.2016.
 */
public interface IService <T, PK> {

    public T create() throws ServiceException;

    /** Создает новую запись, соответствующую объекту object */
    public T persist(T obj)  throws ServiceException;

    /** Возвращает объект соответствующий записи с первичным ключом key или null */
    public T getByPK(PK key) throws ServiceException;

    /** Сохраняет состояние объекта group в базе данных */
    public void update(T obj) throws ServiceException;

    /** Удаляет запись об объекте из базы данных */
    public void delete(T obj) throws ServiceException;

    /** Возвращает список объектов соответствующих всем записям в базе данных */
    public List<T> getAll() throws ServiceException;

}
