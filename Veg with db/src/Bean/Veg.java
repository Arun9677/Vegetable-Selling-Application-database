package Bean;

public class Veg {

	int Id;
	String Name;
	Float Quantity, Price;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Float getQuantity() {
		return Quantity;
	}

	public void setQuantity(Float quantity) {
		Quantity = quantity;
	}

	public Float getPrice() {
		return Price;
	}

	public void setPrice(Float price) {
		Price = price;
	}

	public Veg(int id, String name, Float quantity, Float price) {
		super();
		Id = id;
		Name = name;
		Quantity = quantity;
		Price = price;
	}
	
}
