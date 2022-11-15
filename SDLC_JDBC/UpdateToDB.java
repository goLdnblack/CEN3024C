import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class UpdateToDB
{
	public static void main(String[] args)
	{
		UpdateDB();
	}

	public static void UpdateDB()
	{
		//String conUrl = "jdbc:sqlserver://localhost;encrypt=true;integratedSecurity=true;";
		//String conUrl = "jdbc:sqlserver://localhost\\CEN4333CDB;databaseName=word occurrences;integratedSecurity=true";
		String myUrl = "jdbc:mysql://localhost/word_occurrences";
		Connection con = null;
		//Statement stmt = null;
		ResultSet rs = null;

		try
		{
			//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(myUrl, "root", "admin");

			// SQL Statement
			String PRINT = "SELECT * FROM word";
			String SQL = "INSERT INTO word(word, frequency) " +
			"VALUES(?,?)";

			PreparedStatement stmt = con.prepareStatement(SQL);

			Map<String, Integer> m = TextAnalyzer.analyzeText("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm");

			int counter = 0;

			for (Map.Entry<String, Integer> set : m.entrySet())
			{
				stmt.setString(1, set.getKey());
				stmt.setInt(2, set.getValue());

				stmt.addBatch();
				counter++;

				if (counter == 20)
				{
					stmt.executeBatch();
					break;
				}
			}

			// Print result
			Statement stmt1 = con.createStatement();
			rs = stmt1.executeQuery(PRINT);

			while(rs.next())
			{
				System.out.println(rs.getString("word") + " " + rs.getString("frequency"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}