package refreshment_toppings;

import refreshment.Refreshment;

/*
 * We are here using inheritance to get type matching, not to get the behavior of the component. We get the behavior
 * of the component in the Foam and IceCream classes.
 * Also we are making sure that all classes that implement this class must override these abstract methods.
 */

public abstract class ToppingDecorator extends Refreshment {

	public abstract String getDescription();
	
	public abstract double thisToppingCost(String toppingSize);
	
	@Override
	public String toString() {
		return getDescription() + " (" + getSize() + ")";
	}

}
