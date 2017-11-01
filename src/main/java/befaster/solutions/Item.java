package befaster.solutions;

import befaster.solutions.Basket;

public abstract class Item {
	private Basket basket;
	private char itemSku;
	
	public Item(Basket basket, char itemSku)
	{
		this.basket = basket;
		this.itemSku = itemSku;
	}
	
	public int getPrice()
	{
		return -1;
	}
}
