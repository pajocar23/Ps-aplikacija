/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository;

import java.util.List;

/**
 *
 * @author Mr OLOGIZ
 */
public interface IRepository<T,K> {
    
    public void connect() throws Exception;
    public void disconnect() throws Exception;
    
    public void insert(T t) throws Exception;
    public void update(T t) throws Exception;
    public void delete(T t) throws Exception;
    public List<T> getAll() throws Exception;
    public List<T> getAll(T param) throws Exception;
    public T get(K k) throws Exception;
    public T get(K k,T t)throws Exception;
}
