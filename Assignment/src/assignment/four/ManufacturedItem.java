package assignment.four;

public class ManufacturedItem implements Item {
	
	private String name;
	private double price;
	private int quantity;
	private double tax;

	public ManufacturedItem(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	@Override
	public void computeTax() {
		double itemCost = this.quantity * this.price;
		this.tax = (0.125 * itemCost) + (0.02  *(itemCost + (0.125 * itemCost)));
	}
	
	public String toString() {
		double itemCost = this.price * this.quantity;
		double finalPrice = itemCost + this.tax;
		
		return "Name: " + this.name + "\nPrice: " + itemCost + "\nTax: " + this.tax + "\nFinal Price: " + finalPrice;      
	}

}
