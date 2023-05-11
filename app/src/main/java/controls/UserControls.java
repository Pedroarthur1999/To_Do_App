/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.User;
import utils.ConnectionFactory;

public class UserControls {

    public void save(User usuarios) {

        String sql = "INSERT INTO usuarios (login, senha, esta_logado) VALUES (?,?,?)";

        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);

            statement.setString(1, usuarios.getLogin());
            statement.setString(2, usuarios.getSenha());
            statement.setBoolean(3, usuarios.getEstaLogado());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar os dados!" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }

    public void update(User usuarios) {

        String sql = "UPDATE usuarios SET login = ?, senha = ?, esta_logado = ? WHERE id = ? ";
        Connection conn = null;
        PreparedStatement statement = null;

        try {

            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, usuarios.getLogin());
            statement.setString(2, usuarios.getSenha());
            statement.setBoolean(3, usuarios.getEstaLogado());
            statement.setInt(4, usuarios.getId());
            statement.execute();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar os dados!" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }
    }

    public void removeById(int id) {

        String sql = "DELETE FROM usuarios WHERE id = ?";
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar os dados!" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, statement);
        }

    }

    public List<User> getAll() {

        String sql = "SELECT * FROM usuarios";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet result = null;
        List<User> users = new ArrayList<>();

        try {
            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            result = statement.executeQuery();

            while (result.next()) {

                User usr = new User();
                usr.setId(result.getInt("id"));
                usr.setLogin(result.getString("login"));
                usr.setSenha(result.getString("senha"));
                usr.setEstaLogado(result.getBoolean("esta_logado"));
                users.add(usr);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar a tarefa!", e);
        } finally {
            ConnectionFactory.closeConnection(conn, statement, result);
        }
        return users;
    }

    public User findByEmail(String login) {

        ResultSet rst = null;
        Connection conn = null;
        PreparedStatement statement = null;
        String sql = "SELECT * FROM usuarios WHERE login = ?";
        User user = null;
        try {

            conn = ConnectionFactory.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, login);
            rst = statement.executeQuery();

            if (rst.next()) {

                user = new User();

                user.setId(rst.getInt("id"));
                user.setLogin(rst.getString("login"));
                user.setSenha(rst.getString("senha"));
                user.setEstaLogado(rst.getBoolean("esta_logado"));
            }
            user.setEstaLogado(true);

        } catch (Exception e) {
            System.out.println("Erro ao buscar usuário por e-mail: " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(conn, statement, rst);
        }
        return user;
    }
}
