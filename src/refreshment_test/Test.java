package refreshment_test;

import refreshment.Refreshment;
import refreshment_sizes.Size;
import refreshment_toppings.Foam;
import refreshment_toppings.IceCream;
import refreshment_types.IrishCoffee;

/*
 * This class is used for testing purposes. All test cases are clearly marked with order numbers and a full explanation text besides.
 * In some places next to the code itself, I've added simple explanation about what that code actually does.
 * All tests are numerically ordered from the simplest to the toughest so that a reader can more easily comprehend the whole logic behind this project.
 */

public class Test {
	
	public static void main(String[] args) {
		
			// 1. Determine only refreshment's cost without any toppings
		System.out.println(" *** 1. Determine only refreshment's cost without any toppings *** ");
		
		Refreshment irishCoffee = new IrishCoffee(Size.SMALL);
		System.out.println(irishCoffee + "'s cost is: " + irishCoffee.cost() + "$.");
		
		
		
		System.out.println("--------------- --------------- --------------- ---------------");
		
			// 2. Determine refreshment's cost with different toppings (only 1 topping per drink)
		System.out.println(" *** 2. Determine refreshment's cost with different toppings (only 1 topping per drink) *** ");
		
		Refreshment simpleDrink = new IrishCoffee(Size.SMALL);
		simpleDrink = new Foam(simpleDrink, Size.MEDIUM);
		simpleDrink = new IceCream(simpleDrink, Size.SMALL);
		
		System.out.println(simpleDrink.getToppings() + " and it all costs as low as: " + simpleDrink.cost() + "$.");
		
		
		
		System.out.println("--------------- --------------- --------------- ---------------");
				
			// 3. Determine refreshment's cost with multiple toppings (multiple toppings per drink)
		System.out.println(" *** 3. Determine refreshment's cost with multiple toppings (multiple toppings per drink) *** ");
		
		Refreshment fashionDrink = new IrishCoffee(Size.SMALL);
		fashionDrink = new Foam(fashionDrink, Size.MEDIUM);
		fashionDrink = new IceCream(fashionDrink, Size.SMALL);
		fashionDrink = new Foam(fashionDrink, Size.SMALL);
		fashionDrink = new IceCream(fashionDrink, Size.LARGE);
		fashionDrink = new Foam(fashionDrink, Size.LARGE);
	
		System.out.println(fashionDrink.getToppings() + " and it all costs as low as: " + fashionDrink.cost() + "$.");

	
		
		System.out.println("--------------- --------------- --------------- ---------------");
	
			// 4. Calculate default full drink's cost and then determine your own refreshment's and topping's price and finally calculate full drink's cost afterwards
		System.out.println(" *** 4. Calculate default full drink's cost and then determine your own refreshment's and topping's price and finally calculate full drink's cost afterwards *** ");
	
		Refreshment aDrink = new IrishCoffee(Size.SMALL);
		Foam aFoam = new Foam(aDrink, Size.LARGE);
		System.out.println();
		System.out.println("\t - Default " + aDrink + "'s price is: " + aDrink.getPrice() + "$. Default " + aFoam + "'s price is: " + aFoam.getPrice());
		System.out.println("Default " + aDrink + "'s full cost is: " + aFoam.cost());		// here we are calling cost() on the outermost decorator because it also picks up the cost of all other decorators up to the main refreshment type

		aDrink.setPrice(.5);		// setting new price for the main refreshment
		aFoam.setPrice(.25599);		// setting new price for the topping
		System.out.println();
		System.out.println("\t - New " + aDrink + "'s price is: " + aDrink.getPrice() + "$. New " + aFoam + "'s price is: " + aFoam.getPrice());
		System.out.println();
		System.out.println("New " + aDrink + "'s full cost is: " + aFoam.cost());			// here we are calling cost() on the outermost decorator because it also picks up the cost of all other decorators up to the main refreshment type
		
	
	
		System.out.println("--------------- --------------- --------------- ---------------");

			// 5. Another (more difficult to read) way of creating a refreshment without creating toppings' type objects
		System.out.println(" *** 5. Another (more difficult to read) way of creating a refreshment without creating toppings' type objects *** ");

		Refreshment r = new Foam(new IceCream(new IrishCoffee(Size.LARGE), Size.SMALL), Size.MEDIUM);
		System.out.println(r.getToppings() + " and it all costs as low as: " + r.cost() + "$.");
	
	
	
		System.out.println("--------------- --------------- --------------- ---------------");

			// 6. Adding your provided discount to the toppings only on Friday 
		System.out.println(" *** 6. Adding your provided discount to the toppings only on Friday *** ");
	
		Refreshment aFridayDrink = new IrishCoffee(Size.MEDIUM);
		//aFridayDrink.addFridayToppingsDiscount("09/10/2020", 45); 			// example with a correct day (Friday) and a correct percentage value but on a refreshment without any added toppings
		aFridayDrink = new Foam(aFridayDrink, Size.LARGE);
		aFridayDrink = new IceCream(aFridayDrink, Size.LARGE);
		aFridayDrink = new Foam(aFridayDrink, Size.SMALL);
		aFridayDrink.addFridayToppingsDiscount("09/10/2020", 50);			// example with a correct day (Friday) and a correct percentage value
		//aFridayDrink.addFridayToppingsDiscount("10/10/2020", 30); 		// example with a false day (Saturday) and a correct percentage value
		//aFridayDrink.addFridayToppingsDiscount(null, 40);					// example with a value 'null' set as date pattern and a correct percentage value
		//aFridayDrink.addFridayToppingsDiscount("09/10/2020", 0); 			// example with a correct day (Friday) and a false percentage value
		//aFridayDrink.addFridayToppingsDiscount("34/55/90099", 123);		// example with a false date pattern and a false percentage value
		
		
	
		System.out.println("--------------- --------------- --------------- ---------------");
	
			// 7. Adding your provided discount to the refreshment and it's toppings on any given day except for Monday (Monday is day off) and only within business hours 11AM - 11PM 
		System.out.println(" *** 7. Adding your provided discount to the refreshment and it's toppings on any given day except for Monday (Monday is day off) and only within business hours 11AM - 11PM *** ");
		
		Refreshment aDrinkOrderedWithinRegularBusinessHours = new IrishCoffee(Size.SMALL);
		//aDrinkOrderedWithinRegularBusinessHours.addNotOnMondayFullDiscount("01/01/2020", "11:00AM", 44);		// example with a correct day (not Monday) and a correct time and a correct percentage value but on a refreshment without any added toppings
		aDrinkOrderedWithinRegularBusinessHours = new Foam(aDrinkOrderedWithinRegularBusinessHours, Size.SMALL);
		aDrinkOrderedWithinRegularBusinessHours = new IceCream(aDrinkOrderedWithinRegularBusinessHours, Size.SMALL);
		aDrinkOrderedWithinRegularBusinessHours.addNotOnMondayFullDiscount("03/11/2020", "11:00PM", 50);		// example with a correct day (not Monday) and a correct time and a correct percentage value
		//aDrinkOrderedWithinRegularBusinessHours.addNotOnMondayFullDiscount("09/11/2020", "10:00PM", 30); 		// example with a false day (Monday) and a correct time and a correct percentage value
		//aDrinkOrderedWithinRegularBusinessHours.addNotOnMondayFullDiscount("00/999/2", "01:00AM", 90); 		// example with a false date pattern and an incorrect time and a correct percentage value
		//aDrinkOrderedWithinRegularBusinessHours.addNotOnMondayFullDiscount("03/11/2020", "01:00AM", 80);		// example with a correct day (not Monday) and an incorrect time and a correct percentage value
		//aDrinkOrderedWithinRegularBusinessHours.addNotOnMondayFullDiscount("03/11/2020", "01:00AM", -50);		// example with a correct day (not Monday) and an incorrect time and an incorrect percentage value
		
	}

}
