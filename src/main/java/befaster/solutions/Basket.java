package befaster.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Basket {

	/*private int numOfA = 0;
	private int numOfB = 0;
	private int numOfC = 0;
	private int numOfD = 0;
	private int numOfE = 0;
	private int numOfF = 0;*/

	private static final HashMap<Character, Integer> itemPrices;
	static {
		itemPrices = new HashMap<Character, Integer>();
		itemPrices.put('A', 50);
		itemPrices.put('B', 30);
		itemPrices.put('C', 20);
		itemPrices.put('D', 15);
		itemPrices.put('E', 40);
		itemPrices.put('F', 10);
		itemPrices.put('G', 20);
		itemPrices.put('H', 10);
		itemPrices.put('I', 35);
		itemPrices.put('J', 60);
		itemPrices.put('K', 80);
		itemPrices.put('L', 90);
		itemPrices.put('M', 15);
		itemPrices.put('N', 40);
		itemPrices.put('O', 10);
		itemPrices.put('P', 50);
		itemPrices.put('Q', 30);
		itemPrices.put('R', 50);
		itemPrices.put('S', 30);
		itemPrices.put('T', 20);
		itemPrices.put('U', 40);
		itemPrices.put('V', 50);
		itemPrices.put('W', 20);
		itemPrices.put('X', 90);
		itemPrices.put('Y', 10);
		itemPrices.put('Z', 50);
	}
	
	private HashMap<Character, Integer> items;
	
	private HashMap<Character, HashMap<Integer, Integer>> specialPriceOffers;
	private HashMap<Character, HashMap<Integer, Character>> specialItemOffers;
	
	
	
	public Basket()
	{
		items = new HashMap<Character, Integer>();
		initializePriceOffers();
		initializeItemOffers();
	}
	
	private void initializePriceOffers()
	{
		specialPriceOffers = new HashMap<Character, HashMap<Integer, Integer>>();
		
		HashMap<Integer, Integer> auxHashMap = new HashMap<Integer, Integer>();
		auxHashMap.put(3, 130);
		auxHashMap.put(5, 200);
		specialPriceOffers.put('A', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Integer>();
		auxHashMap.put(2, 45);
		specialPriceOffers.put('B', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Integer>();
		auxHashMap.put(5, 45);
		auxHashMap.put(10, 80);
		specialPriceOffers.put('H', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Integer>();
		auxHashMap.put(2, 150);
		specialPriceOffers.put('K', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Integer>();
		auxHashMap.put(5, 200);
		specialPriceOffers.put('P', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Integer>();
		auxHashMap.put(3, 80);
		specialPriceOffers.put('Q', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Integer>();
		auxHashMap.put(2, 90);
		auxHashMap.put(3, 130);
		specialPriceOffers.put('V', auxHashMap);
	}
	
	private void initializeItemOffers()
	{
		specialItemOffers = new HashMap<Character, HashMap<Integer, Character>>();
		
		HashMap<Integer, Character> auxHashMap = new HashMap<Integer, Character>();
		auxHashMap.put(2, 'B');
		specialItemOffers.put('E', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Character>();
		auxHashMap.put(2, 'F');
		specialItemOffers.put('F', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Character>();
		auxHashMap.put(3, 'M');
		specialItemOffers.put('N', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Character>();
		auxHashMap.put(3, 'Q');
		specialItemOffers.put('R', auxHashMap);
		
		auxHashMap = new HashMap<Integer, Character>();
		auxHashMap.put(3, 'U');
		specialItemOffers.put('U', auxHashMap);
	}
	
	public void addItem(char itemSku)
	{
		Integer oldValue = items.get(itemSku);
		if(oldValue == null)
		{
			items.put(itemSku, 1);
		}
		else
		{
			items.replace(itemSku, oldValue++);
		}
	}
	
	public int getTotalPrice()
	{
		int totalPrice = 0;
		Set<Character> itemSKUs = items.keySet();
		
		for(Character itemName : itemSKUs)
		{
			totalPrice += getTotalPriceForItem(itemName);
		}
		return totalPrice;
	}
	
	public int getTotalPriceForItem(char itemSKU)
	{
		int numberOfItems = items.get(itemSKU);
		int itemPrice = 0;
		
		if(items.get(itemSKU) > 0)
		{			
			if(specialItemOffers.containsKey(itemSKU) == true)
			{
				HashMap<Integer, Character> itemOffers = specialItemOffers.get(itemSKU);
				
				for(Entry<Integer, Character> entry : itemOffers.entrySet())
				{
					if(entry.getValue().equals(itemSKU) == true)
					{
						int offerNumber = entry.getKey();
						
						if(items.get(itemSKU) > (offerNumber + 1))
						{
							int numOfFreeItems = numberOfItems / offerNumber;
							
							if(numberOfItems % entry.getKey() == 0)
							{
								numberOfItems++;
							}
							
							numberOfItems -= numOfFreeItems;
							items.replace(itemSKU, numberOfItems);
						}
						
					}
					else
					{
						Integer numOfItems = items.get(entry.getValue());
						numOfItems -= (numberOfItems / entry.getKey());
						items.replace(entry.getValue(), numOfItems);
					}
				}
				
				itemPrice += items.get(itemSKU) * getPriceForItem(itemSKU);
			}
			else if(specialPriceOffers.containsKey(itemSKU) == true)
			{
				HashMap<Integer, Integer> offers = specialPriceOffers.get(itemSKU);
				Set<Integer> keySet = offers.keySet();
				
				List<Integer> keySetList = new ArrayList(keySet);
				Collections.sort(keySetList);
				
				ListIterator<Integer> iterator  = keySetList.listIterator(keySetList.size());
				while(iterator.hasPrevious())
				{
					Integer currentInt = iterator.previous();
					
					if(numberOfItems >= currentInt)
					{
						itemPrice += (numberOfItems / currentInt) * offers.get(currentInt);
						numberOfItems = numberOfItems % currentInt;
					}
				}
				
				itemPrice += numberOfItems * getPriceForItem(itemSKU);
			}
			else
				itemPrice = numberOfItems * getPriceForItem(itemSKU); 
		}
		return itemPrice;
	}
	
	
	private int getPriceForItem(char itemSKU)
	{
		return itemPrices.get(itemSKU);
	}
	
/*	public void addA()
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
	
	public void addG()
	{
		numOfF++;	
	}
	
	public void addH()
	{
		numOfF++;	
	}
	
	public void addI()
	{
		numOfF++;	
	}
	
	public void addJ()
	{
		numOfF++;	
	}
	
	public void addK()
	{
		numOfF++;	
	}
	
	public void addL()
	{
		numOfF++;	
	}
	
	public void addM()
	{
		numOfF++;	
	}
	
	public void addN()
	{
		numOfF++;	
	}
	
	public void addO()
	{
		numOfF++;	
	}
	
	public void addP()
	{
		numOfF++;	
	}
	
	public void addQ()
	{
		numOfF++;	
	}
	
	public void addR()
	{
		numOfF++;	
	}
	
	public void addS()
	{
		numOfF++;	
	}
	
	public void addT()
	{
		numOfF++;	
	}
	
	public void addU()
	{
		numOfF++;	
	}
	
	public void addV()
	{
		numOfF++;	
	}
	
	public void addW()
	{
		numOfF++;	
	}
	
	public void addX()
	{
		numOfF++;	
	}
	
	public void addY()
	{
		numOfF++;	
	}
	
	public void addZ()
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
	}*/
}
