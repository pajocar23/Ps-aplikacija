/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Mr OLOGIZ
 */
public class DataBaseConfig {

    private static DataBaseConfig instance;
    private String DBConfigFilePath ;
    private File DBConfigFile;
    BufferedReader br;
    Properties properties;

    private DataBaseConfig() {
        DBConfigFilePath = new File("src\\rs\\ac\\bg\\fon\\ps\\config\\DataBaseConfig.properties").getAbsolutePath();
        DBConfigFile = new File(DBConfigFilePath);
        properties = new Properties();

    }

    public static DataBaseConfig getInstance() {
        if (instance == null) {
            instance = new DataBaseConfig();
        }
        return instance;
    }

    public String getURL() throws Exception {
        properties.load(new FileInputStream(DBConfigFilePath));
        String url = properties.getProperty("url");
        return url;
    }

    public String getDataBaseUser() throws Exception {
        properties.load(new FileInputStream(DBConfigFilePath));
        String user = properties.getProperty("user");
        return user;
    }

    public String getDataBasePassword() throws Exception {
        properties.load(new FileInputStream(DBConfigFilePath));
        String password = properties.getProperty("password");
        return password;
    }
}
