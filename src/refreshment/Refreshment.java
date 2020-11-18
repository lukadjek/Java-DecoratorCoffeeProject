package refreshment;

import java.math.BigDecimal;
import java.math.RoundingMode;

import refreshment_jobs.RefreshmentJob;
import refreshment_sizes.Size;

/*
 * Refreshment is the abstract base class. All drinks and toppings are of the same type (of the Refreshment type).
 */

public abstract class Refreshment extends RefreshmentJob {

	protected String description = "Uknown Refreshment";
	protected Size size;
	protected double price;

	public String getDescription() {
		return description;
	}
	
	public double setPrice(double aPrice) {			// format the newly set price so it is always rounded to 2 decimal places (rounding mode: half up)
		BigDecimal bd = new BigDecimal(aPrice).setScale(2, RoundingMode.HALF_UP);
		double formattedPrice = bd.doubleValue();
		return price = formattedPrice;
	};	
	
	public double getPrice() {
		return price;
	};

	public Size setSize(Size size) {
		if ( size != null )
			return this.size = size;
		else
			throw new NullPointerException("A size for a refreshment must be set!");	// if a Size is set to null, throw an exception immediately
	};
	public Size getSize() {
		return size;
	};
	
	public abstract double cost();
	
	@Override
	public boolean equals(Object obj) {				// two refreshments are the same only if their description is the same (the size is of zero importance for refreshment equality)
		
		if ( obj == null )
			return false;
		
		if ( !(obj instanceof Refreshment ) )
			return false;
		
		if ( String.valueOf(obj).strip().length() == 0 )
			return false;
		
		Refreshment otherRefreshment = (Refreshment) obj;
		return otherRefreshment.description.equals(this.description);
	}
		
	public String toString() {
		return getDescription() + " (" + getSize() + ")";
	};

}
