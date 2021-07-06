/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

/**
 *
 * @author Mr OLOGIZ
 */
public class ServerConfig {

    private static ServerConfig instance;
    private static String serverConfigFilePath;
    private File serverConfigFile;
    Properties properties;

    private ServerConfig() {
        serverConfigFilePath = new File("src\\rs\\ac\\bg\\fon\\ps\\config\\ServerConfig.properties").getAbsolutePath();
        serverConfigFile = new File(serverConfigFilePath);
        properties = new Properties();

    }

    public static ServerConfig getInstance() {
        if (instance == null) {
            instance = new ServerConfig();
        }
        return instance;
    }

    public String getServerPort() throws FileNotFoundException, IOException {
        properties.load(new FileInputStream(serverConfigFilePath));
        String port = properties.getProperty("port");
        return port;
    }

}
