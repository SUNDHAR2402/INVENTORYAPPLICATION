package connectivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class ConnectionManager {

	public static void main(String[] args) throws SQLException {
		Connection con=null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/TEST","root","22122");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT*FROM USER");
			while(rs.next())
			{
				System.out.println(rs.getInt("ID")+"   |   "+rs.getString("USERNAME")+"   |   "+rs.getString("ADDRESS"));
			}
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		if(con !=null)
		{
			System.out.println("Connection Established");
		}
		else
		{
			System.out.println("Connection Not Established");
		}
		
	}

}
