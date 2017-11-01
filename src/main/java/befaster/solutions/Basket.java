package befaster.solutions;

public class Basket {

	private int numOfA = 0;
	private int numOfB = 0;
	private int numOfC = 0;
	private int numOfD = 0;
	
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
	
	public int getPrice()
	{
		int price = 0;
		
		price += ((numOfA / 3) * 130) + ((numOfA % 3) * 50);
		price += ((numOfB / 2) * 45) + ((numOfB % 2) * 30);
		price += numOfC * 20;
		price += numOfD * 15;
		
		return price;
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
	
}
