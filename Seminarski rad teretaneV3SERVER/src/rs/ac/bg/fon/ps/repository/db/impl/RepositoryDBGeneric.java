/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.repository.db.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.ps.domain.GenericEntity;
import rs.ac.bg.fon.ps.domain.Grupa;
import rs.ac.bg.fon.ps.domain.Trener;
import rs.ac.bg.fon.ps.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.ps.repository.db.IDbRepository;

/**
 *
 * @author Mr OLOGIZ
 */
public class RepositoryDBGeneric implements IDbRepository<GenericEntity, Long> {

    @Override
    public void insert(GenericEntity entity) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb0 = new StringBuilder();
            sb0.append("ALTER TABLE ")
                    .append(entity.getTableName())
                    .append(" AUTO_INCREMENT=1");
            Statement statement0 = connection.createStatement();
            String query0 = sb0.toString();
            statement0.executeUpdate(query0);
            statement0.close();
            System.out.println(query0);
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ")
                    .append(entity.getTableName())
                    .append(" (").append(entity.getColumnNamesForInsert()).append(")")
                    .append(" VALUES (")
                    .append(entity.getInsertValues())
                    .append(")");
            String query = sb.toString();
            System.out.println(query);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();
            statement.close();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<GenericEntity> getAll(GenericEntity param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            List<GenericEntity> lista = new ArrayList<>();
            sb.append("SELECT ")
                    .append("*")
                    .append(" FROM ")
                    .append(param.getTableName()).append(" ")
                    .append(param.getJoinConditions());
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                param = param.setGetAllValues(rs);

                lista.add(param);
            }
            rs.close();
            statement.close();
            return lista;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void update(GenericEntity t) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("UPDATE ")
                    .append(t.getTableName())
                    .append(" SET ")
                    .append(t.getUpdateValues())
                    .append(" WHERE ")
                    .append(t.getPrimaryKey()).append("=")
                    .append(" ").append(t.getPrimaryKeyValue());
            String query = sb.toString();
            System.out.println(query);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void delete(GenericEntity t) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            sb.append("DELETE FROM ")
                    .append(t.getTableName())
                    .append(" WHERE ")
                    .append(t.getPrimaryKey()).append("=")
                    .append(t.getPrimaryKeyValue());
            String query = sb.toString();
            System.out.println(query);

            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();

        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public List<GenericEntity> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericEntity get(Long k) throws Exception {
      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GenericEntity get(Long k, GenericEntity param) throws Exception {
        try {
            Connection connection = DbConnectionFactory.getInstance().getConnection();
            StringBuilder sb = new StringBuilder();
            //GenericEntity ge=null
            sb.append("SELECT ")
                    .append("*")
                    .append(" FROM ")
                    .append(param.getTableName()).append(" ")
                    .append(param.getJoinConditions())
                    .append(" WHERE ")
                    .append(param.getPrimaryKey()).append("=")
                    .append(k);
            String query = sb.toString();
            System.out.println(query);
            Statement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                param = param.setGetAllValues(rs);               
            }
            rs.close();
            statement.close();
            return param;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
