package befaster.solutions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CheckoutTest {

	@Test
	public void testEmptyCheckout()
	{
		assertEquals(Checkout.checkout(""), new Integer(0));
	}
	
	@Test
	public void testSimpleCheckout()
	{
		assertEquals(Checkout.checkout("ABCD"), new Integer(115));
	}
}