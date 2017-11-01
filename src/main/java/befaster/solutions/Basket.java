package befaster.solutions;

import java.util.HashMap;

public class Basket {

	private int numOfA = 0;
	private int numOfB = 0;
	private int numOfC = 0;
	private int numOfD = 0;
	private int numOfE = 0;
	private int numOfF = 0;
	
	public Basket()
	{
	}
	
	public void addA()
	{
		numOfA++;	
	}
	
	public void addB()
	{
		numOfB++;	
	}
	
	public void addC()
	{
		numOfC++;	
	}
	
	public void addD()
	{
		numOfD++;	
	}
	
	public void addE()
	{
		numOfE++;	
	}
		
	public void addF()
	{
		numOfF++;	
	}

	public int getPrice()
	{
		int price = 0;
		
		price += getTotalPriceForA();
		price += getTotalPriceForE();
		price += getTotalPriceForB();
		price += getTotalPriceForC();
		price += getTotalPriceForD();
		price += getTotalPriceForF();
		
		return price;
	}
	
	private int getTotalPriceForA()
	{
		int priceForA = 0;
		
		priceForA += (numOfA / 5) * 200;
		
		int numOfAaux = numOfA % 5;
		
		priceForA += (numOfAaux / 3) * 130;
		priceForA += (numOfAaux % 3) * getPriceForA();
		
		return priceForA;
	}
	
	private int getTotalPriceForB()
	{
		if(numOfB > 0)
		{
			return ((numOfB / 2) * 45) + ((numOfB % 2) * getPriceForB());
		}
		return 0;
		
	}
	
	
	private int getTotalPriceForC()
	{
		return numOfC * getPriceForC();
	}
	
	private int getTotalPriceForD()
	{
		return numOfD * getPriceForD();
	}
	
	private int getTotalPriceForE()
	{
		int numOfDoubleE = numOfE / 2;
		numOfB -= numOfDoubleE;
		
		return numOfE * getPriceForE();
	}
	
	private int getTotalPriceForF()
	{
		int priceOfF = 0;
		
		if(numOfF >= 3)
		{
			int numOfDoubleF = numOfF / 2;
			
			
			if(numOfF % 2 == 0)
			{
				numOfF++;
			}
			
			numOfF -= numOfDoubleF;

		}
		priceOfF += numOfF * getPriceForF();
		return priceOfF;
	}
	
	
	public int getPriceForA()
	{
		return 50;
	}
	
	public int getPriceForB()
	{
		return 30;
	}
	
	public int getPriceForC()
	{
		return 20;
	}
	
	public int getPriceForD()
	{
		return 15;
	}
	
	public int getPriceForE()
	{
		return 40;
	}
	
	public int getPriceForF()
	{
		return 10;
	}
}
