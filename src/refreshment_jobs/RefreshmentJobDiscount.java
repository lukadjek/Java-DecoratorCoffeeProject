package refreshment_jobs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import refreshment.Refreshment;

/*
 * This interface defines RefreshmentJobDiscount methods and has an inner static class DiscountCalculator used for calculation purposes.
 */

public interface RefreshmentJobDiscount extends RefreshmentJobPrint {

	// inherits also the list from RefreshmentJobPrint interface and it uses that list to do the discount stuff

	public void addFridayToppingsDiscount(String date, int percentage);
	
	public void addNotOnMondayFullDiscount(String date, String hour, int percentage);

	public String checkDatePattern(String date);
	
	int returnDayNumber(String date);
	
	public String returnDayWeekOrderNumber(int dayNumberInt);

	public String returnDayName(String date);
	
	final DateFormat formatter_date = new SimpleDateFormat("dd/MM/yyyy");
	final DateFormat formatter_day = new SimpleDateFormat("EEEE");
	final DateFormat formatter_day_number_in_week = new SimpleDateFormat("u");
	final SimpleDateFormat formatter_hour = new SimpleDateFormat("hh:mmaa");

	static class DiscountCalculator {				// a static inner class used for determing new discount prices
		
		private Refreshment topping = null;
		private double oldTotalPrice = 0;
		private double newTotalPrice = 0;
		private final String refreshmentType = list.get(0).getDescription();
		
		public double calculateFullDiscount(int percentage) {
						
			for (int i = 0; i < list.size(); i++) {			// iterates from index 0 (to include a refreshment type and it's toppings)
				
				topping = list.get(i);
				double oldPriceOfThisRefreshment = list.get(i).getPrice();
				oldTotalPrice = oldTotalPrice + oldPriceOfThisRefreshment;

				double newPriceOfThisRefreshment = (oldPriceOfThisRefreshment * percentage) / 100;
				System.out.println("\t  " + (i+1) + ". " + list.get(i).getDescription() + " (" + list.get(i).getSize()
						+ ") -> old price: " + oldPriceOfThisRefreshment + "$" + " ; " + " new price: "
						+ newPriceOfThisRefreshment + "$.");
				topping.setPrice(newPriceOfThisRefreshment);

				newTotalPrice = newTotalPrice + topping.getPrice();
				
			}
						
			printFullDiscountDetails(percentage);			// print details when a full discount is applied (refreshment type and toppings)
			
			return newTotalPrice;
			
		}
		

		public double calculateToppingsDiscount(int percentage) {
			
			
			for (int i = 1; i < list.size(); i++) {			// iterates from index 1 (to include only toppings)
				topping = list.get(i);
				double oldPriceOfThisTopping = list.get(i).getPrice();
				oldTotalPrice = oldTotalPrice + topping.getPrice();

				double newPriceOfThisTopping = (oldPriceOfThisTopping * percentage) / 100;
				System.out.println("\t  " + i + ". " + list.get(i).getDescription() + " (" + list.get(i).getSize()
						+ ") -> old price: " + oldPriceOfThisTopping + "$" + " ; " + " new price: "
						+ newPriceOfThisTopping + "$.");
				topping.setPrice(newPriceOfThisTopping);

				newTotalPrice = newTotalPrice + topping.getPrice();

			}
			
			printToppingsDiscountDetails(percentage);			// print details when only toppings discount is applied
			
			return newTotalPrice;
			
		}
		
		 private void printFullDiscountDetails(int percentage) { // list the benefit for the customer; fully inform him/her about the full discount

			System.out.println();

			System.out.println("\tTotal old full (toppings' & refreshment's) price was: " + oldTotalPrice + "$.");
			System.out.println("\tTotal new full (toppings' & refreshment's) price is: " + newTotalPrice + "$.");
			double bucksSaved = oldTotalPrice - newTotalPrice;
			System.out.println("\n\tTo conclude, with this discount of " + percentage + "%" + ", you saved in total "
					+ bucksSaved + "$.");
			System.out.println("\n\t*YOUR TOTAL INVOICE AMOUNTS TO*");
			System.out.println("\t" + refreshmentType + "'s price: " + list.get(0).getPrice() + "$");
			System.out.println("\tToppings' price: " + ( newTotalPrice - list.get(0).getPrice() ) + "$");			
			System.out.println("\tAmount to be payed preferably with a smile: " + topping.cost() + "$");

		}
		
		 private void printToppingsDiscountDetails(int percentage) { // list the benefit for the customer; fully inform him/her about the toppings discount

			System.out.println();

			System.out.println("\tTotal old toppings' price was: " + oldTotalPrice + "$.");
			System.out.println("\tTotal new toppings' price is: " + newTotalPrice + "$.");
			double bucksSaved = oldTotalPrice - newTotalPrice;
			System.out.println("\n\tTo conclude, with this discount of " + percentage + "%" + ", you saved in total "
					+ bucksSaved + "$.");
			System.out.println("\n\t*YOUR TOTAL INVOICE AMOUNTS TO*");
			System.out.println("\t" + refreshmentType + "'s price: " + list.get(0).getPrice() + "$");
			System.out.println("\tToppings' price: " + newTotalPrice + "$");
			System.out.println("\tAmount to be payed preferably with a smile: " + topping.cost() + "$");

		}


	}

}
