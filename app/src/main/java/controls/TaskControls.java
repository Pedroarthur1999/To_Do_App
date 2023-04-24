package controls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Tasks;
import utils.ConnectionFactory;

/**
 *
 * @author eu
 */
public class TaskControls {

    public void save(Tasks task) {

        String sql = "INSERT INTO tasks(id_project,name,description,completed,notes,created_at,updated_at,task_completed) VALUES (?,?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getId_project());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setDate(4, new java.sql.Date(task.getCompleted().getTime()));
            statement.setString(5, task.getNotes());
            statement.setDate(6, new java.sql.Date(task.getCreated_at().getTime()));
            statement.setDate(7, new java.sql.Date(task.getUpdated_at().getTime()));
            statement.setBoolean(8, task.getTask_completed());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possivel salvar os dados" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Tasks task) throws SQLException {

        String sql = "UPDATE tasks SET id_project = ? , name = ?, description = ?, completed = ?, notes = ?, created_at = ?, updated_at = ?, task_completed = ? WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getId_project());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setDate(4, new java.sql.Date(task.getCompleted().getTime()));
            statement.setString(5, task.getNotes());
            statement.setDate(6, new java.sql.Date(task.getCreated_at().getTime()));
            statement.setDate(7, new java.sql.Date(task.getUpdated_at().getTime()));
            statement.setBoolean(8, task.getTask_completed());
            statement.setInt(9, task.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao alterar os dados!" + ex.getMessage());

        } finally {

            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public void removeById(int taskId) throws SQLException {

        String sql = "DELETE FROM tasks WHERE id = ?";
        PreparedStatement statement = null;
        Connection connection = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, taskId);
            statement.execute();

        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar a tarefa!");
        } finally {

            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    public List<Tasks> getAll() throws SQLException {

        String sql = "SELECT * FROM tasks";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        List<Tasks> tasks = new ArrayList<>();

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            result = statement.executeQuery();

            while (result.next()) {

                Tasks task = new Tasks();

                task.setId(result.getInt("id"));
                task.setId_project(result.getInt("id_project"));
                task.setName(result.getString("name"));
                task.setDescription(result.getString("description"));
                task.setCompleted(result.getDate("completed"));
                task.setNotes(result.getString("notes"));
                task.setCreated_at(result.getDate("created_at"));
                task.setUpdated_at(result.getDate("updated_at"));
                task.setTask_completed(result.getBoolean("task_completed"));

                tasks.add(task);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar a tarefa!", e);
        } finally {
            ConnectionFactory.closeConnection(connection, statement, result);
        }
        return tasks;

    }

    public List<Tasks> getTaskById(int id_project) {

        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rst = null;
        String sql = "SELECT * FROM tasks WHERE id_project = ?";

        try {

            conn = ConnectionFactory.getConnection();
            stm = conn.prepareStatement(sql);

            stm.setInt(1, id_project);
            rst = stm.executeQuery();

            List<Tasks> tasks = new ArrayList<>();

            while (rst.next()) {

                Tasks task = new Tasks();

                task.setId(rst.getInt("id"));
                task.setId_project(rst.getInt("id_project"));
                task.setName(rst.getString("name"));
                task.setDescription(rst.getString("description"));
                task.setCompleted(rst.getDate("completed"));
                task.setNotes(rst.getString("notes"));
                task.setCreated_at(rst.getDate("created_at"));
                task.setUpdated_at(rst.getDate("updated_at"));
                task.setTask_completed(rst.getBoolean("task_completed"));

                tasks.add(task);
            }
            return tasks;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar a tarefa", e);
        } finally {
            ConnectionFactory.closeConnection(conn, stm, rst);
        }
    }
}
