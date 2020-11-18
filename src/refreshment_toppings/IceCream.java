package refreshment_toppings;

import refreshment.Refreshment;
import refreshment_sizes.Size;

/*
 * IceCream class is a topping basically so it extends the ToppingDecorator class.
 * Toppings are the same type as components they decorate either through inheritance or interface implentation.
 */

public class IceCream extends ToppingDecorator {

	private Refreshment refreshment;

	public IceCream(Refreshment r, Size size) {
		this.refreshment = r;
		this.size = size;
		description = "Ice Cream";
		list.add(this);
		StringBuilder sb = new StringBuilder("You added this topping to your: " + r + "\n");
		sb.append("\t - name: " + getDescription() + ",\n");
		sb.append("\t - size: " + getSize() + ",\n");
		sb.append("\t - price: " + thisToppingCost(size.getName()) + "$.");
		System.out.println(sb);			
	}

	@Override
	public double thisToppingCost(String toppingSize) {
				
		if ( getPrice() == 0 ) {
 
			if (toppingSize.equals("Small"))
				price = 0.3011113999;
			else if (toppingSize.equals("Medium"))
				price = 0.60;
			else if (toppingSize.equals("Large"))
				price = 0.80;
			
			setPrice(price);
			return getPrice();
		}
		else 
			return getPrice();
		
	}

	@Override
	public double cost() {		// first calls super.cost ( refreshment.cost() ) and than adds that double value to thisToppingCost

		double toppingCost = thisToppingCost(getSize().getName());

		double refreshmentCost = refreshment.cost() + toppingCost;
		
		setPrice(refreshmentCost);		// round refreshmentCost to a 2 decimal number (mode half.up)
				
		return getPrice();

	}
	
	@Override
	public String getDescription() {
		return description + " topping";
	}
	
	@Override
	public String toString() {
		return description;
	}

}
