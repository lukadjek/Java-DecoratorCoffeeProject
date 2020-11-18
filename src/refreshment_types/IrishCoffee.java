package refreshment_types;

import refreshment.Refreshment;
import refreshment_sizes.Size;

/*
 * IrishCoffee class is a type of a Refreshment. It's a sort of 'main' refreshment and not a topping.
 */

public class IrishCoffee extends Refreshment {
	
	public IrishCoffee(Size size) {
		list.clear(); 								// empty list whenever a new refreshment type is used -> because it is not allowed to mix 2 refreshments types; only multiple toppings per 1 refreshment type are allowed
		description = "Irish Coffee";
		setSize(size);
		list.add(0, this);
		StringBuilder sb = new StringBuilder("You choose this refreshment: \n");
		sb.append("\t - name: " + getDescription() + ",\n");
		sb.append("\t - size: " + getSize() + ",\n");
		sb.append("\t - price: " + cost() + "$.");
		System.out.println(sb);
	}

	@Override
	public double cost() {		
		
		if ( getPrice() == 0 ) {

			if (getSize().getName().equals("Small"))
				price = 1;
			else if (getSize().getName().equals("Medium"))
				price = 2;
			else if (getSize().getName().equals("Large"))
				price = 3.299;
				
			setPrice(price);
			return getPrice();
		}
		else 
			return getPrice();
		
	}
	
	@Override
	public String toString() {
		return description;
	}

}
