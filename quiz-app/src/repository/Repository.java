package repository;

import domain.Question;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public List<Question> getAll() {
        ArrayList<Question> cakes = new ArrayList<>();
        try {
            openConnection();
            try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM  Questions;"); ResultSet rs = ps.executeQuery();) {
                while (rs.next()) {
                    Question d = new Question(rs.getInt("Qid"), rs.getString("Text"), rs.getString("Correct"), rs.getInt("Score"), rs.getString("UserA"));
                    cakes.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return cakes;
    }

    public void updateUserAnswer(int id ,String text) {
        try
        {
            openConnection();

            String updateString = "UPDATE Questions SET UserA = ? WHERE Qid=" + id;
            try (PreparedStatement ps = conn.prepareStatement(updateString)) {
                ps.setString(1, text);
                ps.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
