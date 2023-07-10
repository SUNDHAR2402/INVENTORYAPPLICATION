package controller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import dao.LoginDAO;
import dao.ProdutDAO;
import model.Login;
import model.Product1;
public class Main
{
	public static void main(String[] args) throws NumberFormatException,IOException,ClassNotFoundException,SQLException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		Login login=new Login();
		LoginDAO logindao=new LoginDAO();
		int option,choice;
		Product1 product1=new Product1();
		ProdutDAO productdao=new ProdutDAO();
		do
		{
			System.out.println("1.Admin");
			System.out.println("2.Agent");
			System.out.println("3.exit");
			System.out.println("--------------------------------------------------");
			choice=Integer.parseInt(br.readLine());
			switch(choice)
			{
			case 1:
				System.out.println("------------------------------------------------------");
				System.out.println("Enter Username");
				String username=br.readLine();
				System.out.println("Enter Password");
				String password=br.readLine();
				login.setUSERNAME(username);
				login.setPASSWORD(password);
				boolean result=logindao.validate(login);
				if(result==true)
				{
					System.out.println("Login Successful");
					System.out.println("----------------------------------------------------------");
					do
					{
						System.out.println("1.Add Product");
						System.out.println("2.Display Inventory Details");
						System.out.println("3.Logout");
						System.out.println("---------------------------------------------------------");
						option=Integer.parseInt(br.readLine());
						switch(option)
						{
						case 1:
							System.out.println("Enter product Name");
							String productName=br.readLine();
							System.out.println("Enter product id");
							int productId=Integer.parseInt(br.readLine());
							System.out.println("Enter the min selling quantity");
							int minsell=Integer.parseInt(br.readLine());
							System.out.println("Enter the price of the product");
							int price=Integer.parseInt(br.readLine());
							System.out.println("Enter the Quantity");
							int quantity=Integer.parseInt(br.readLine());
							product1.setPRODUCTNAME(productName);
							product1.setPRODUCTID(productId);
							product1.setMINSELL(minsell);
							product1.setQUANTITY(quantity);
							product1.setPRICE(price);
							productdao.addProduct(product1);
							System.out.println("---------------------------------------------------------");
							break;
						case 2:
							productdao.display();
						}
					}
					while(option!=3);
				}
				else
				{
					System.out.println("Username & Password is not correct");
				}
				break;
			case 2:
				System.out.println("------------------------------------------------------");
				System.out.println("Enter Username");
				String agentusername=br.readLine();
				System.out.println("Enter Password");
				String agentpassword=br.readLine();
				login.setUSERNAME(agentusername);
				login.setPASSWORD(agentpassword);
				result=logindao.validate(login);
				if(result==true)
				{
					System.out.println("Login Successful");
					System.out.println("----------------------------------------------------------");
					do
					{
						System.out.println("1.Buy/Sell");
						System.out.println("2.Show history");
						System.out.println("3.Logout");
						System.out.println("---------------------------------------------------------");
						option=Integer.parseInt(br.readLine());
						switch(option)
						{
						case 1:
							System.out.println("Buy/Sell");
							String agent=br.readLine();
							if(agent.equalsIgnoreCase("buy"))
							{
								System.out.println("Enter product Name");
								String productName=br.readLine();
								System.out.println("Enter product id");
								int productId=Integer.parseInt(br.readLine());
								System.out.println("Enter the min selling quantity");
								int minsell=Integer.parseInt(br.readLine());
								System.out.println("Enter the price of the product");
								int price=Integer.parseInt(br.readLine());
								System.out.println("Enter the Quantity");
								int quantity=Integer.parseInt(br.readLine());
								product1.setPRODUCTNAME(productName);
								product1.setPRODUCTID(productId);
								product1.setMINSELL(minsell);
								product1.setQUANTITY(quantity);
								product1.setPRICE(price);
								productdao.addProduct(product1);
								System.out.println("---------------------------------------------------------");
							}
							else if(agent.equalsIgnoreCase("shell"))
							{
								System.out.println("---------------------------------------------------------");
								System.out.println("Enter product id");
								int productId=Integer.parseInt(br.readLine());
								System.out.println("Enter the Quantity");
								int quantity=Integer.parseInt(br.readLine());
								if(productdao.quantityAvailable(productId,quantity))
								{
									int total=productdao.totalCost(productId,quantity);
									System.out.println("---------------------------------------------------------");
									System.out.println("Total cost is "+total);
									System.out.println("---------------------------------------------------------");
									System.out.println("Confirm Booking(Yes/No");
									String booking=br.readLine();
									System.out.println("---------------------------------------------------------");
								}
							}
						case 2:
							productdao.display();
							break;
						case 3:
							break;
						}
					}
					while(option != 3);
				}
				else
				{
					System.out.println("Username & Password is not correct");
				}
				break;
			}
		}
		while(choice!=3);
	}
}