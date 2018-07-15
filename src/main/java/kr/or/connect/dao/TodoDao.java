package kr.or.connect.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import kr.or.connect.dto.TodoDto;

public class TodoDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/connectdb";
	private static String dbUser = "connectuser";
	private static String dbPasswd = "connect123!@#";
	
	private HashMap<String, String> nextTypes;
	
	private Connection getConnection() {
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver"); // driver loading.
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPasswd);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
	
	public TodoDao() {
		nextTypes = new HashMap<>();
		nextTypes.put("TODO", "DOING");
		nextTypes.put("DOING", "DONE");
		nextTypes.put("DONE", "DONE");
	}
	
	public List<TodoDto> getTodos() {
		String sql = "SELECT id, title, name, sequence, type, regdate FROM todo ORDER BY regdate DESC";
		
		List<TodoDto> ret = new ArrayList<>();
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					long id = rs.getLong("id");
					String title = rs.getString("title");
					String name = rs.getString("name");
					int sequence = rs.getInt("sequence");
					String type = rs.getString("type");
					String regDate = rs.getDate("regdate").toString();
					
					ret.add(new TodoDto(id, title, name, sequence, type, regDate));
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
	}	
	
	public int updateTodo(TodoDto newTodo) {
		int updateCount = 0;
		String sql = "UPDATE todo SET type = ? WHERE id = ?";
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, this.nextTypes.get(newTodo.getType()));
			ps.setLong(2, newTodo.getId());
			
			updateCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updateCount;
	}
	
	public int deleteTodo(long id) {
		int deleteCount = 0;
		String sql = "DELETE FROM todo WHERE id = ?";
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setLong(1, id);
			deleteCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleteCount;
	}
	
	public int addTodo(String title, String name, int sequence) {
		int insertCount = 0;
		String sql = "INSERT INTO todo (title, name, sequence) VALUES (?, ?, ?)";
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				) {
			ps.setString(1, title);
			ps.setString(2, name);
			ps.setInt(3, sequence);
			
			insertCount = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return insertCount;
	}
	
	public TodoDto getTodo(long id) {
		TodoDto ret = null;
		String sql = "SELECT id, title, name, sequence, type, regdate FROM todo WHERE id = ?";
		
		try (
				Connection conn = this.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql);
				) {
			
			ps.setLong(1, id);
			
			try (ResultSet rs = ps.executeQuery()) {
				String title = rs.getString("title");
				String name = rs.getString("name");
				int sequence = rs.getInt("sequence");
				String type = rs.getString("type");
				String regdate = rs.getDate("regdate").toString();
				
				ret = new TodoDto(id, title, name, sequence, type, regdate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}
}