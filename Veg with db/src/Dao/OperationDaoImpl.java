package Dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Scanner;

import Bean.Bill;
import Bean.Veg;
import dbStat.ShortStatement;
import veg.MainVeg;

public class OperationDaoImpl implements OperationDao
{

	int check, Id, val;
	String str, Name;
	Float Quantity, Price;
	
	MainVeg mainVeg = new MainVeg();

	Scanner sc = new Scanner(System.in);
	
	public void insert()
	{
		check = 0;
		System.out.println("Enter vegetable");
		Name = sc.next();
		System.out.println("Enter quantity");
		Quantity = sc.nextFloat();
		System.out.println("Enter price");
		Price = sc.nextFloat();
		
		try
		{
			Statement st = ShortStatement.smallStatement();
			check = st.executeUpdate("INSERT INTO `veg` (`Name`, `Quantity`, `Price`) VALUES ('"+Name+"', '"+Quantity+"', '"+Price+"') ");
			System.out.println();
			if(check != 0)
			{
				System.out.println("Vegetable added successfully");
			}else
			{
				System.out.println("Vegetable not added");
			}
			
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		System.out.println("Press c to Main");
		System.out.println("Press a to Add");
		System.out.println("Press any key to Exit");
		str = sc.next();
		System.out.println();
		
		if(str.equals("c"))
		{
			mainVeg.menu();
		}else if(str.equals("a"))
		{
			insert();
		}
		else {
			System.exit(0);
		}	
		
	}
	
	public void updateVeg()
	{
		stock(0);
		System.out.println("Enter id that you want to update");
		Id = sc.nextInt();
		try
		{
			Statement st = ShortStatement.smallStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM `veg` WHERE `id` = '"+Id+"' ");
			if(rs.next())
			{
				System.out.println("1.Name");
				System.out.println("2.Quantity");
				System.out.println("3.Add Quantity");
				System.out.println("4.Price");
				System.out.println("Press any key to exit");
				System.out.println("Enter your choice");
				val = sc.nextInt();
				switch(val)
				{
				case 1:
					updateName();
					mainVeg.menu();
					break;
					
				case 2:
					System.out.println("Enter quantity");
					Quantity = sc.nextFloat();
					updateStock(Quantity);
					mainVeg.menu();
					break;
					
				case 3:
					System.out.println("Enter quantity to add");
					Quantity = sc.nextFloat();
					Quantity += rs.getFloat(3);
					updateStock(Quantity);
					mainVeg.menu();
					break;
					
				case 4:
					updatePrice();
					mainVeg.menu();
					break;
				
				default:
					System.out.println("Terminated");
					System.exit(0);
				}
			}
		
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void updateName()
	{
		System.out.println("Enter new name");
		Name = sc.next();
		try
		{
			check = 0;
			Statement st = ShortStatement.smallStatement();
			check = st.executeUpdate("UPDATE `veg` SET `Name` = '"+Name+"' WHERE `Id` = '"+Id+"' ");
			if(check != 0)
			{
				System.out.println("Name updated successfully");
				System.out.println();
			}else {
				System.out.println("Something went wrong");
				System.out.println("Press a to try again");
				System.out.println("Press any key to exit");
				str = sc.next();
				if(str.equals("a"))
				{
					updateVeg();
				}else {
					System.exit(0);
				}
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void updatePrice()
	{
		System.out.println("Enter new price");
		Price = sc.nextFloat();
		check = 0;
		
		try {
			Statement st = ShortStatement.smallStatement();
			check = st.executeUpdate("UPDATE `veg` SET `Price` = '"+Price+"' WHERE `Id` = '"+Id+"' ");
			
			if(check != 0)
			{
				System.out.println("Price updated successfully");
				System.out.println();
			}else {
				System.out.println("Something went wrong");
				System.out.println("Press a to try again");
				System.out.println("Press any key to exit");
				str = sc.next();
				if(str.equals("a"))
				{
					updateVeg();
				}else {
					System.exit(0);
				}
			}
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	public void stock(int num)
	{
		try
		{
			Veg veg;
			LinkedList<Veg> vegs = new LinkedList<Veg>();
			
			Statement st = ShortStatement.smallStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM veg");
			
			while(rs.next())
			{
				veg = new Veg(rs.getInt(1), rs.getString(2), rs.getFloat(3), rs.getFloat(4));
				vegs.add(veg);
			}
			
			System.out.println();
			System.out.println("---------------------------------------------------------");
			System.out.println("|\t\t\tAR VEG SHOP\t\t\t|");
			System.out.println("---------------------------------------------------------");
			System.out.println("| Id\t|Name\t\t| Quantity\t| Price\t\t|");
			System.out.println("---------------------------------------------------------");
			for(Veg v: vegs)
			{
				System.out.println("| "+v.getId()+"\t|"+v.getName()+"\t\t| "+v.getQuantity()+"\t.kg\t| Rs. "+v.getPrice()+"\t|");
			}
			System.out.println("---------------------------------------------------------");
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		if(num == 2)
		{
			System.out.println();
			System.out.println("Press m to Main");
			System.out.println("Press any key to exit");
			String e = sc.next();
			System.out.println();
			if(e.equals("m"))
			{
				mainVeg.menu();				
			}else {
				System.out.println("Terminated");
				System.exit(0);
			}
		
		}
		
	}
	
	public void billing()
	{
		float amount = 0;
		int i = 1;
		String next;
		System.out.println();
		stock(0);
		
		Bill bill;
		LinkedList<Bill> bills = new LinkedList<Bill>();
		
		do {
			bill = billDb(i++);
			bills.add(bill);
			System.out.println("Press n to add next item");
			System.out.println("Press any key to bill");
			next = sc.next();
		}while(next.equals("n"));
		
		System.out.println();
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("|\t\t\t\tAR VEG SHOP\t\t\t\t|");
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("| S.No\t| Name\t\t| Quantity\t| Unit Price\t| Total Price\t|");
		System.out.println("-------------------------------------------------------------------------");
		
		for(Bill b: bills)
		{
			System.out.println("| "+b.getId()+"\t| "+b.getName()+"\t| "+b.getQuantity()+"\t.kg\t| Rs. "+b.getUnitPrice()+"\t| Rs. "+String.format("%.2f", b.getTotalPrice())+"\t|");
			amount = amount + b.getTotalPrice();
		}
		System.out.println("-------------------------------------------------------------------------");
		System.out.println("\t\t\t\t\t|\t Total\t= Rs. "+String.format("%.2f", amount)+"\t|");
		System.out.println("\t\t\t\t\t---------------------------------");
		
		System.out.println();
		System.out.println("Press c to Main");
		System.out.println("Press b to bill");
		System.out.println("Press any key to Exit");
		str = sc.next();
		System.out.println();

		if(str.equals("c"))
		{
			mainVeg.menu();
		}else if(str.equals("b"))
		{
			billing();
		}
		else {
			System.exit(0);
		}
		
	}
	
	public Bill billDb(int i)
	{
		System.out.println("Enter id");
		Id = sc.nextInt();
		System.out.println("Enter quantity");
		Quantity = sc.nextFloat();
		
		Bill bill;
		
		try
		{
			Statement st = ShortStatement.smallStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM veg WHERE `Id` = '"+Id+"' ");
			if(rs.next())
			{
				if(rs.getFloat(3)>Quantity)
				{
					bill = new Bill(i, rs.getString(2), Quantity, rs.getFloat(4), (rs.getFloat(4) * Quantity));
					updateStock(rs.getFloat(3) - Quantity);
					return bill;
					
				}else
				{
					System.out.println("Not enough stock");
					billDb(i);
				}
			}
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
		
		return null;
		
	}
	
	public void updateStock(float quantity)
	{
		try
		{
			Statement st = ShortStatement.smallStatement();
			st.executeUpdate("UPDATE `veg` SET `Quantity` = '"+quantity+"' WHERE `Id`='"+Id+"' ");
			
		}catch(Exception e)
		{
			System.out.println(e);
		}
	}	
	
}
