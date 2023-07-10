package dao;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import connectionManager.ConnectionManager;
import model.Product1;
public class ProdutDAO
{
	public void addProduct(Product1 product) throws ClassNotFoundException, SQLException
	{
		int id=product.getPRODUCTID();
		String name=product.getPRODUCTNAME();
		int minsell=product.getMINSELL();
		int price=product.getPRICE();
		int quantity=product.getQUANTITY();
		System.out.println(id+name);
		ConnectionManager conn=new ConnectionManager();
		Connection con=conn.establishConnection();
		String query="insert into "+"PRODUCT1(PRODUCTID,PRODUCTNAME,MINSELL,PRICE,QUANTITY)"+"values(?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(query);
		ps.setInt(1,id);
		ps.setString(2,name);
		ps.setInt(3,minsell);
		ps.setInt(4,price);
		ps.setInt(5,quantity);
		ps.executeUpdate();
		conn.closeConnection();
	}
	public void display() throws ClassNotFoundException, SQLException
	{
		ConnectionManager conn=new ConnectionManager();
		Connection con=conn.establishConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery("SELECT*FROM PRODUCT");
		while(rs.next())
		{
			System.out.println("---------------------------------------------------------");
			System.out.println(rs.getString("PRODUCTNAME")+"|"+rs.getInt("PRODUCTID")+"|"+rs.getInt("MINSELL")+"|"+rs.getInt("PRICE")+"|"+rs.getString("QUANTITY"));
			System.out.println("---------------------------------------------------------");
		}
		conn.closeConnection();
	}
	public boolean quantityAvailable(int productid,int quantity) throws ClassNotFoundException, SQLException
	{
		ConnectionManager conn=new ConnectionManager();
		Connection con=conn.establishConnection();
		PreparedStatement st=con.prepareStatement("select quantity,minsell from from product where productid=?");
		st.setInt(1,productid);
		ResultSet rs=st.executeQuery();
		if(rs.next())
		{
			if(quantity<=rs.getInt("quantity") && quantity<rs.getInt("minsell"))
			{
				conn.closeConnection();
				return false;
			}
			else
			{
				conn.closeConnection();
				return false;
			}
		}
		return false;
	}
	public int totalCost(int productid,int quantity) throws ClassNotFoundException, SQLException
	{
		ConnectionManager conn=new ConnectionManager();
		Connection con=conn.establishConnection();
		PreparedStatement st=con.prepareStatement("select quantity,minsell from from product where productid=?");
		st.setInt(1,productid);
		ResultSet rs=st.executeQuery();
		int total=0;
		if(rs.next())
		{
			total=quantity*rs.getInt("price");
		}
		conn.closeConnection();
		return total;
	}
}