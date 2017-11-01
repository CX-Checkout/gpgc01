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
			return basket.getPrice();
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
			switch(input.charAt(i))
			{
				case 'A':
					basket.addA();
					break;
				case 'B':
					basket.addB();
					break;
				case 'C':
					basket.addC();
					break;
				case 'D':
					basket.addD();
					break;
				case 'E':
					basket.addE();
					break;
				case 'F':
					basket.addF();
					break;
				default:
					throw new  IllegalInputException("Input gresit!");
			}
		}
		
	}
}
