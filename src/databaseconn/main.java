package databaseconn;

import java.sql.*;

public class main {

    public static void main(String args[]) {

        try {
            // Conectar BBDD
            Class.forName("org.sqlite.JDBC");
            String sqliteURL = "jdbc:sqlite:sofiafraire.db";
            Connection conexion = DriverManager.getConnection(sqliteURL);
            Statement statement = conexion.createStatement();
            System.out.println("Conexion establecida con BBDD");

            // Crear tabla
            statement.executeUpdate("CREATE TABLE 'empleados de Sofia' (id INTEGER PRIMARY KEY, nombre VARCHAR(50), telefono VARCHAR(20), salario DOUBLE, fechaContrato DATE)");

            // Inserccion 3 elementos
            String sqlInsercion = "INSERT INTO 'empleados de Sofia' (nombre, telefono, salario, fechaContrato)" + "VALUES (?,?,?,?)";
            PreparedStatement prep = conexion.prepareStatement(sqlInsercion);
            prep.setString(1, "matthew");
            prep.setString(2, "645693745");
            prep.setInt(3, 1);
            Date fecha = new Date(125, 5,20);
            prep.setDate(4,fecha);
            prep.executeUpdate();
            System.out.println("Fila insertada Matthew");

            prep.setString(1, "lucia");
            prep.setString(2, "645693740");
            prep.setInt(3, 1000);
            fecha = new Date(125, 6,22);
            prep.setDate(4,fecha);
            prep.executeUpdate();
            System.out.println("Fila insertada Lucia");

            prep.setString(1, "fran");
            prep.setString(2, "645693745");
            prep.setInt(3, 3);
            fecha = new Date(125, 4,13);
            prep.setDate(4,fecha);
            prep.executeUpdate();
            System.out.println("Fila insertada Fran");

            // Consulta
            ResultSet result = statement.executeQuery("SELECT * FROM 'empleados de Sofia'");
            int id, salario;
            String nombre;
            while(result.next()){
                id = result.getInt("id");
                nombre = result.getString("nombre");
                salario = result.getInt("salario");
                System.out.println("Trabajador " + id + " -- Nombre: " + nombre + " -- Salario: " + salario + "€");
            }

            // Update
            String sqlUpdate = "UPDATE 'empleados de Sofia' SET salario = ? WHERE nombre = ?";
            PreparedStatement prep2 = conexion.prepareStatement(sqlUpdate);

            // Close
            if (result != null) result.close();
            if (prep != null) prep.close();
            if (prep2 != null) prep2.close();
            if (statement != null) statement.close();
            if (conexion != null) conexion.close();
            System.out.println("Conexiones cerradas correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 
