package com.buybig.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private String url;
    private String username;
    private String password;

    DAOFactory(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }

        DAOFactory instance = new DAOFactory(
                "jdbc:mysql://localhost:3306/javaee", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection connexion = DriverManager.getConnection(url, username, password);
        connexion.setAutoCommit(false);
        return connexion; 
    }

   /* // R�cup�ration du Dao
    public IUserDAO getUtilisateurDao() {
        return new UserDAOImpl(this);
    }*/
}