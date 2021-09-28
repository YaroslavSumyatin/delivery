package com.delivery.database.dao;

import com.delivery.database.entities.Entity;
import com.delivery.exceptions.DBException;

import java.util.List;
import java.util.logging.Logger;

/*
 * Data Access Object interface
 */
public interface EntityDAO <E extends Entity> {

    E findById(int id) throws DBException;
    List<E> findAll() throws DBException;
    boolean insert(E entity) throws DBException;
    boolean update(E entity) throws DBException;
    boolean delete(E entity) throws DBException;

}
