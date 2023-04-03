
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

        String sql = "INSERT INTO tasks(id_project"
                + "id"
                + "name"
                + "description"
                + "completed"
                + "notes"
                + "created_at"
                + "update_at" + ") VALUES (?,?,?,?,?,?,?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getId_project());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, (java.sql.Date) new Date(task.getCreated_at().getTime()));
            statement.setDate(7, (java.sql.Date) new Date(task.getUpdate_at().getTime()));
            statement.setInt(1, task.getId());

            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Não foi possivel salvar os dados" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update(Tasks task) throws SQLException {

        String sql = "UPDATE tasks SET "
                + "id_project = ?"
                + "name = ?"
                + "description = ?"
                + "completed = ?"
                + "notes = ?"
                + "created_at = ?"
                + "updated_at = ?"
                +"WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getId_project());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.getCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, (java.sql.Date) new Date(task.getCreated_at().getTime()));
            statement.setDate(7, (java.sql.Date) new Date(task.getUpdate_at().getTime()));
            statement.setInt(8, task.getId());
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

            ConnectionFactory.closeConnection(connection,statement);
        }

    }

    public List<Tasks> getAll(int id_project) throws SQLException {

        String sql = "SELECT * FROM tasks WHERE id_project = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet result = null;

        List<Tasks> tasks = new ArrayList<Tasks>();

        try {

            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id_project);
            result = statement.executeQuery();

            while (result.next()) {

                Tasks task = new Tasks();

                task.setId(result.getInt("id"));
                task.setId_project(result.getInt("id_project"));
                task.setName(result.getString("name"));
                task.setDescription(result.getString("description"));
                task.setCompleted(result.getBoolean("completed"));
                task.setNotes(result.getString("notes"));
                task.setCreated_at(result.getDate("created_at"));
                task.setUpdate_at(result.getDate("update_at"));

                tasks.add(task);

            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao selecionar a tarefa!",e);
        }
        finally{
            ConnectionFactory.closeConnection(connection, statement, result);
        }
                return tasks;
    }
}
