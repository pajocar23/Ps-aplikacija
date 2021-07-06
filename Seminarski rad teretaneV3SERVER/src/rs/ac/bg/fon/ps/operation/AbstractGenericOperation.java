/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.operation;

import java.util.List;
import rs.ac.bg.fon.ps.repository.IRepository;
import rs.ac.bg.fon.ps.repository.db.IDbRepository;
import rs.ac.bg.fon.ps.repository.db.impl.RepositoryDBGeneric;

/**
 *
 * @author Mr OLOGIZ
 */
public abstract class AbstractGenericOperation<T> { //T-tip executa(povrt v)

    protected final IRepository repository;

    public AbstractGenericOperation() {
        this.repository = new RepositoryDBGeneric();
    }

    public void execute(Object param) throws Exception {
        try {
            startTransaction();
            preconditions(param);
            executeOperation(param);
            commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            rollbackTransaction();
            throw e;
        } finally {
            disconnect();
        }
    }

    private void startTransaction() throws Exception {
        ((IDbRepository) repository).connect();
    }

    //Operation-specific method
    protected abstract void preconditions(Object param) throws Exception;

    //Operation-specific method
    protected abstract void executeOperation(Object param) throws Exception;  

    private void commitTransaction() throws Exception {
        ((IDbRepository) repository).commit();
    }

    private void rollbackTransaction() throws Exception{
        ((IDbRepository) repository).rollback();
    }

    private void disconnect() throws Exception {
        ((IDbRepository) repository).disconnect();
    }

}
