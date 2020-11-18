package refreshment_sizes;

/*
 * Refreshments and toppings can both be charged according to their sizes (small, medium, large).
 */

public enum Size {

	SMALL("Small"), 
	MEDIUM("Medium"), 
	LARGE("Large");

	private String name;

	private Size(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
