package assignment.one;

public class ImportedItem implements Item {
	
	private String name;
	private double price;
	private int quantity;
	private double tax;

	public ImportedItem(String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		computeTax();
	}

	@Override
	public void computeTax() {
		double itemCost = this.price * this.quantity;
		double finalPrice = (0.1 * itemCost) + itemCost;
		
		if (finalPrice <= 100) {
			this.tax = finalPrice + 5;
		} else if (finalPrice > 100 && finalPrice <= 200) {
			this.tax = finalPrice + 10;
		} else {
			this.tax = finalPrice + (0.05 * finalPrice);
		}
	}
	
	public String toString() {
		double itemCost = this.price * this.quantity;
		double finalPrice = itemCost + this.tax;
		
		return "Name: " + this.name + "\nPrice: " + itemCost + "\nTax: " + this.tax + "\nFinalPrice: " + finalPrice;      
	}

}
