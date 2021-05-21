package by.vit.ban.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Datasource {
    private static final org.apache.logging.log4j.core.Logger logger = (Logger) LogManager.getLogger();
    private static Datasource instance;
    private Connection connection;


    private Datasource() {
        String password = "";
        try (Scanner passScanner = new Scanner(new File(Datasource.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "databaseProperties.txt"))) {
            if (passScanner.hasNext()) {
                password = passScanner.nextLine();
            }
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection("jdbc:h2:mem:clients", "user", password);
            initializeDataBase();
        } catch (ClassNotFoundException exception) {
            logger.error("Wrong database driver name", exception);
        } catch (SQLException exception) {
            logger.error("Connection error", exception);
        } catch (FileNotFoundException exception) {
            logger.error("Wrong file path", exception);
        }
    }

    public static Datasource getInstance() {
        if (instance == null) {
            instance = new Datasource();
        }
        return instance;
    }

    private void initializeDataBase() {
        StringBuilder initialSql = new StringBuilder();
        PreparedStatement preparedStatement;
        try (Scanner scanner = new Scanner(new File(Datasource.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "databaseInitialize.txt"))) {
            while (scanner.hasNext()) {
                initialSql.append(scanner.nextLine());
            }
            preparedStatement = getStatement(initialSql.toString());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            logger.error("SQL script are defected", exception);

        } catch (FileNotFoundException exception) {
            logger.error("Wrong file path", exception);
        }
    }

    public PreparedStatement getStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }
}