package Model;

import java.io.Serializable;

/**
 * MenuItem class: Implements Serialzable and holds the private data for menu items.
 * Has getters and setters for the data as well as an overridden toString method.
 * @author dumag
 *
 */
public class MenuItem implements Serializable{
	private String name;
	private int type; // 0 Drinks, 1 Baked Goods
	private double price;
	private int quantity;
	private boolean stock;

	// Constructors 
	public MenuItem() {
		setName("N/A");
		setType(-1);
		setPrice(-1);
		setQuantity(-1);
		setStock(getQuantity());
	}
	public MenuItem(String name, int type, double price, int quantity, boolean stock) {
		setName(name);
		setType(type);
		setPrice(price);
		setQuantity(quantity);
		setStock(getQuantity());
	}
	
	// Accessor Methods
	// Getters
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	public boolean getStock() {
		return stock;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setType(int type) {
		if(type >= 0 || type <= 1) {
			this.type = type;
		} else {
			this.type = -1;
		}
	}
	public void setPrice(double price) {
		if(price > 0 ) {
			this.price = price;
		} else {
			this.price = -99.99;
		}
	}
	public void setQuantity(int quantity) {
		if(quantity > 0 ) {
			this.quantity = quantity;
		} else {
			this.quantity = -1;
		}
		setStock(quantity);
	}
	public void setStock(int quantity) {
		if(quantity > 0) {
			stock = true;
		} else {
			stock = false;
		}
	}
	
	// ToString Methods
	@Override
	public String toString() {
		return String.format("%s, %d, %.2f, %d, %b",name,type,price,quantity,stock);
	}

	public String toStringForTextFile() {
		return String.format("%s, %d, %.2f, %d, %b");
	}
}
