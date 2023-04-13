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
import java.util.Date;
import java.util.List;
import models.Projects;
import utils.ConnectionFactory;

/**
 *
 * @author eu
 */
public class ProjectsControls {

    public void save(Projects project) {

        String sql = "INSERT INTO projects (name,description,createDate,updateDate) VALUES(?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreateDate().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdateDate().getTime()));            
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar os dados!" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Projects project) throws SQLException {

        String sql = "UPDATE projects SET name = ?,description = ? ,createDate = ? ,updatedate = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new java.sql.Date(project.getCreateDate().getTime()));
            statement.setDate(4, new java.sql.Date(project.getUpdateDate().getTime()));
            statement.setInt(5, project.getId());

            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar os dados!" + ex.getMessage());

        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(int projectId) throws SQLException {

        String sql = "DELETE FROM projects WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, projectId);
            statement.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa!");
        } finally {

            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Projects> getAll() throws SQLException {

        String sql = "SELECT * FROM projects";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        List<Projects> project = new ArrayList<>();

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();

            while (result.next()) {

                Projects p = new Projects();

                p.setId(result.getInt("id"));
                p.setName(result.getString("name"));
                p.setDescription(result.getString("description"));
                p.setCreateDate(result.getDate("createDate"));
                p.setUpdateDate(result.getDate("updateDate"));
                project.add(p);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar a tarefa!", e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, result);
        }
        return project;
    }

}
