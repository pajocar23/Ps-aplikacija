/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.domain;

import java.io.Serializable;
import java.sql.ResultSet;

/**
 *
 * @author Mr OLOGIZ
 */
public interface GenericEntity extends Serializable{

    String getTableName();

    String getColumnNamesForInsert();

    String getInsertValues();

    String getJoinConditions();

    String getPrimaryKey();

    String getPrimaryKeyValue();

    String getUpdateValues();

    public GenericEntity setGetAllValues(ResultSet rs);    
}
