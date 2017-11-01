package befaster.solutions;


public class Checkout {
	
	public static Integer checkout(String input)
	{
		if(input.isEmpty() == true)
		{
			return 0;
		}
		
		Basket basket = new Basket();
		
		try
		{
			parseInputString(input, basket);
			return basket.getTotalPrice();
		}
		catch(IllegalInputException e)
		{
			return -1;
		}
		
	}
	
	private static void parseInputString(String input, Basket basket) throws IllegalInputException
	{
		for(int i = 0; i < input.length(); i++)
		{
			if(input.charAt(i) < 65 || input.charAt(i) > 90)
				throw new  IllegalInputException("Input gresit!");
			else
				basket.addItem(input.charAt(i));
		}
		
	}
}
