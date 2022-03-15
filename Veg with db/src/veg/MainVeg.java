package veg;

import java.util.Scanner;
import Dao.OperationDao;
import Dao.OperationDaoImpl;

public class MainVeg {
	
public static void main(String args[]) {
		
		MainVeg mainVeg = new MainVeg();
		mainVeg.menu();
		
	}

	public void menu()
	{
		int ch;
		OperationDao op = new OperationDaoImpl();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1.Add");
		System.out.println("2.Stock");
		System.out.println("3.Bill");
		System.out.println("4.Update");
		System.out.println("Press any key to exit");
		System.out.println("Enter your choice");
		ch = sc.nextInt();
		
		switch(ch)
		{
		case 1:
			op.insert();
			break;
			
		case 2:
			op.stock(ch);
			break;
			
		case 3:
			op.billing();
			break;
		
		case 4:
			op.updateVeg();
			break;
			
		default:
			System.out.println("Terminated");
			System.exit(0);
		}
		
		sc.close();
		
	}

}
