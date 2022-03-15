package Dao;

import Bean.Bill;

public interface OperationDao {

	public void insert();
	public void updateVeg();
	public void updateName();
	public void updatePrice();
	public void stock(int num);
	public void billing();
	public Bill billDb(int i);
	public void updateStock(float quantity);
	
}
