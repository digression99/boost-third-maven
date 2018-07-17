package kr.or.connect.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.connect.dto.TodoDto;

public class TodoDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbPasswd = "connect123!@#";

	private Connection conn;

	private TodoDao() {
		conn = getConnection();
	}

	// singleton pattern method.
	public static TodoDao getInstance() {
		return LazyHolder.INSTANCE;
	}

	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			throw new Error(e.getMessage());
		}
	}

	// singleton pattern helper class.
	private static class LazyHolder {
		private static final TodoDao INSTANCE = new TodoDao();
	}

	private Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver"); // driver loading.
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}

		return conn;
	}

	public List<TodoDto> selectTodos() {
		String sql = "SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY regdate DESC";

		List<TodoDto> todos = new ArrayList<>();

		try (
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				long id = rs.getLong("id");
				String title = rs.getString("title");
				String name = rs.getString("name");
				int sequence = rs.getInt("sequence");
				String type = rs.getString("type");
				Date regDate = rs.getDate("regdate");

				todos.add(new TodoDto(id, title, name, sequence, type, regDate));
			}
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}
		return todos;
	}

	public TodoDto selectTodo(long id) {
		TodoDto todoDto = null;
		String sql = "SELECT id, title, name, sequence, type, regdate FROM todo WHERE id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {

			ps.setLong(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String title = rs.getString("title");
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String type = rs.getString("type");
					Date regdate = rs.getDate("regdate");

					todoDto = new TodoDto(id, title, name, sequence, type, regdate);
				}
			} catch (Exception e) {
				throw new Error(e.getMessage());
			}
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}

		return todoDto;
	}

	public List<TodoDto> selectTodosByType(String type) {
		String sql = "SELECT id, title, name, sequence, type, regdate " +
			"FROM todo WHERE type = ?";
		List<TodoDto> todosList = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, type);

			try (ResultSet resultSet = ps.executeQuery()) {

				while (resultSet.next()) {

					long id = resultSet.getLong("id");
					String title = resultSet.getString("title");
					String name = resultSet.getString("name");
					int sequence = resultSet.getInt("sequence");
					Date regDate = resultSet.getDate("regdate");

					todosList.add(new TodoDto(id, title, name, sequence, type, regDate));
				}
			} catch (SQLException e) {
				throw new Error(e.getMessage());
			}
		} catch (SQLException e) {
			throw new Error(e.getMessage());
		}
		return todosList;
	}

	public int updateTodo(TodoDto newTodo) {
		int updateCount = 0;
		String sql = "UPDATE todo SET type = ? WHERE id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, newTodo.getType());
			ps.setLong(2, newTodo.getId());

			updateCount = ps.executeUpdate();
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}

		return updateCount;
	}

	public int deleteTodo(long id) {
		int deleteCount = 0;
		String sql = "DELETE FROM todo WHERE id = ?";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			deleteCount = ps.executeUpdate();
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}

		return deleteCount;
	}

	public int insertTodo(String title, String name, int sequence) {
		int insertCount = 0;
		String sql = "INSERT INTO todo (title, name, sequence) VALUES (?, ?, ?)";

		try (PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, title);
			ps.setString(2, name);
			ps.setInt(3, sequence);

			insertCount = ps.executeUpdate();
		} catch (Exception e) {
			throw new Error(e.getMessage());
		}

		return insertCount;
	}
}