package assignment.four;

public class RawItem implements Item {

	private String name;
	private double price;
	private int quantity;
	private double tax;
	
	public RawItem(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	@Override
	public void computeTax() {
		this.tax = 0.125 * this.price * this.quantity;
	}
	
	public String toString() {
		double itemCost = this.price * this.quantity;
		double finalPrice = itemCost + this.tax;
		
		return "Name: " + this.name + "\nPrice: " + itemCost + "\nTax: " + this.tax + "\nFinal Price: " + finalPrice;      
	}

}
