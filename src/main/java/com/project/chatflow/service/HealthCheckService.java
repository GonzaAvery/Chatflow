package com.project.chatflow.service;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HealthCheckService {

	@Autowired
    private javax.sql.DataSource dataSource;

    public boolean checkConnection() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(2); // Chequea la conexión por 2 segundos
        } catch (SQLException e) {
            return false; // Si ocurre una excepción, la conexión no es válida
        }
    }
}
