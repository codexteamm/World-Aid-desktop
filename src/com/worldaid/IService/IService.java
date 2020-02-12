/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worldaid.IService;

import com.worldaid.Entites.DonMateriel;
import com.worldaid.Entites.DonMateriel.type_materiel;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author ASUS
 * @param <T>
 */
public interface IService<T> {
    void ajouter(T t) throws SQLException;
    public void delete(T t) throws SQLException;
    public void update(T t) throws SQLException;
    public void count(T t) throws SQLException;
    List<T> readAll() throws SQLException;
}
