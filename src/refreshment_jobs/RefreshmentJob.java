package refreshment_jobs;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import refreshment.Refreshment;

/*
 * This is the main and only job class. This class implements specific jobs for the Refreshment class.
 */

public class RefreshmentJob implements RefreshmentJobPrint, RefreshmentJobDiscount {

	// RefreshmentJobPrint methods start here

	@Override
	public Set<String> getToppings() {

		Set<String> set = new HashSet<String>();
		int i = 1; // iterate from 1. index (they represent toppings), because 0 index is reserved for the refreshment type
		
		while (i != list.size()) {

			int occurrences = 0;

			createToppingsSet(occurrences, set, list, i);

			i++;
		}

		String refreshmentType = list.get(0).getDescription();

		System.out.print("To your mouthwatering " + refreshmentType + ", you added these toppings: ");
		return set;
	}

	@Override
	public void createToppingsSet(int occurrences, Set<String> set, List<Refreshment> list, int i) { // a method to return one, double or triple condiment

		occurrences = Collections.frequency(list, list.get(i));	// Collections.frequency works according to the overriden equals() method

		String getCount = getOccurencesCount(occurrences);

		if (occurrences == 1)
			set.add(getCount + list.get(i));

		if (occurrences == 2)
			set.add(getCount + list.get(i));

		if (occurrences == 3)
			set.add(getCount + list.get(i));

	}

	@Override
	public String getOccurencesCount(int occurrences) {
		switch (occurrences) {
		case 1:
			return "One ";
		case 2:
			return "Double ";
		case 3:
			return "Triple ";
		default:
			return "/";
		}
	}

	// RefreshmentJobPrint methods end here

	// RefreshmentJobDiscount methods start here

	@Override
	public void addFridayToppingsDiscount(String date, int percentage) { // adds a discount only to the toppings (to
																			// each of the toppings) and only on Fridays

		System.out.println(
				"\n\t ...checking if there is a discount to be made to your toppings (checking the provided date pattern, day from the provided date pattern and percentage)... ");

		StringBuilder sb = new StringBuilder();

		if (percentage <= 0 || percentage >= 100) // check percentage value
			sb.append("\n\tPlease make sure the given percentage is in range (1%-99%).\n");

		if (checkDatePattern(date) == null) // check date pattern
			sb.append("\n\tPlease make sure the date is set correctly.\n");

		String refreshmentType = list.get(0).getDescription();

		if (list.size() == 1) { // first element in the list is always the refreshment type and toppings come
								// after; toppings do not exist
			sb.append("\n\tYou did not add any toppings to your " + refreshmentType
					+ " so no discount can be taken into consideration!");
		}

		if (sb.length() != 0) { // check if there are erros and abort the process; if there are no errors simply
								// continue
			System.out.println(sb);
			return;
		}

		else { // toppings exist and a discount can be approved

			String dayName = returnDayName(date);
			String dayOrder = returnDayWeekOrderNumber(returnDayNumber(date));

			// check if the given number is 5; 5 = Friday
			if (5 != returnDayNumber(date)) {
				System.out.println("\n\tSorry, the given day is " + dayName
						+ " and that means no discount for you. \n\t" + "Discount is valid only on Fridays :(");
				return;
			}

			// inform the customer he's/she's lucky
			System.out.println("\n\tLucky you! The given day is " + dayName + " which is the " + dayOrder
					+ " day of the week. " + "\n\tThere is a discount of " + percentage
					+ "% to be granted for your " + refreshmentType + "'s toppings.");

			// determine new toppings' prices
			System.out.println("\n\tDetails about toppings:");

			// delegate to another class
			new DiscountCalculator().calculateToppingsDiscount(percentage);

		}
	}

	@Override
	public String checkDatePattern(String date) { // checking only if the date pattern is valid

		// I played I bit here with DateTimeFormatter and DateTimeFormatter classes. I could have 
		// easilly used the formatter_date final field from the class 'RefreshmentJobDiscount'
		
		String dayNumber = "dd";
		String month = "MM";
		String year = "yyyy";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dayNumber + "/" + month + "/" + year);

		String dateString = null;
		try {
			LocalDate dateTime = LocalDate.parse(date, formatter);
			dateString = formatter.format(dateTime);

			System.out.println("\t --- given date pattern of '" + dateString + "' is valid");

		} catch (DateTimeParseException | NullPointerException e) {
			System.out.println("\n\t" + e + " \n\t-> date format must be provided in this pattern 'dd/MM/yyyy'");
		}

		return dateString;

	}

	@Override
	public int returnDayNumber(String date) { // if this method returns 0, then 0 means no day is found; anyway it won't ever
												// return 0 but a value between 1-7

		Integer dayNumberInt = 0;

		try {
			Date result = formatter_date.parse(date);

			String dayNumberString = formatter_day_number_in_week.format(result);

			dayNumberInt = Integer.valueOf(dayNumberString);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dayNumberInt;

	}

	@Override
	public String returnDayName(String date) {

		String dayName = null;
		try {
			Date real_date = formatter_date.parse(date);
			dayName = formatter_day.format(real_date);

			System.out.println("\t --- day name is:  " + dayName);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dayName;
	}

	@Override
	public String returnDayWeekOrderNumber(int dayNumberInt) {

		String dayOrder;

		String[] arrayOfStrings = new String[] { "st", "nd", "rd", "th", "uknown day" };

		switch (dayNumberInt) {
		case 1: // Monday
			dayOrder = dayNumberInt + arrayOfStrings[0];
			break;
		case 2: // Tuesday
			dayOrder = dayNumberInt + arrayOfStrings[1];
			break;
		case 3: // Wednesday
			dayOrder = dayNumberInt + arrayOfStrings[2];
			break;
		case 4: // Thursday
			dayOrder = dayNumberInt + arrayOfStrings[3];
			break;
		case 5: // Friday
			dayOrder = dayNumberInt + arrayOfStrings[3];
			break;
		case 6: // Saturday
			dayOrder = dayNumberInt + arrayOfStrings[3];
			break;
		case 7: // Sunday
			dayOrder = dayNumberInt + arrayOfStrings[3];
			break;
		default: // will not ever be exectuted because dayNumberInt is generated directly from
					// the date and can return values in range 1-7
			dayOrder = dayNumberInt + arrayOfStrings[4];
			break;
		}

		return dayOrder;

	}

	@Override
	public void addNotOnMondayFullDiscount(String date, String hour,
			int percentage) {

		System.out.println(
				"\n\t 	 ...checking if there is a discount to be made to your refreshment and it's toppings (checking the provided date pattern, day from the provided date pattern, percentage and hours)... ");

		StringBuilder sb = new StringBuilder();

		if (percentage <= 0 || percentage >= 100) // check percentage value
			sb.append("\n\tPlease make sure the given percentage is in range (1%-99%).\n");

		if (checkDatePattern(date) == null) // check date pattern
			sb.append("\n\tPlease make sure the date is set correctly.\n");

		String refreshmentType = list.get(0).getDescription();

		if (list.size() == 1) { // first element in the list is always the refreshment type and toppings come
								// after; toppings do not exist
			sb.append("\n\tYou did not add any toppings to your " + refreshmentType
					+ " so no discount can be taken into consideration!");
		}

		if (sb.length() != 0) { // check if there are erros and abort the process; if there are no errors simply
								// continue
			System.out.println(sb);
			return;
		}

		else { // toppings exist and a discount can be approved

			String dayName = returnDayName(date);
			String dayOrder = returnDayWeekOrderNumber(returnDayNumber(date));
			boolean timeValue = returnHour(hour);

			// check if the given number is 1; 1 = Monday
			if (1 == returnDayNumber(date)) {
				System.out
						.println("\n\tSorry, the given day is " + dayName + " and that means no discount for you. \n\t"
								+ "Discount is valid on all days except on Mondays (and during business hours of course) :(");
				return;
			}

			// check if the time value is written correctly
			if (timeValue == false) {
				System.out.println("\n\tSorry, we are currently not open and that means no discount for you. \n\t"
						+ "Discount is valid only whilst the Café is open (11AM - 11PM) and on all days except on Mondays :(");
				return;
			}

			// inform the customer he's/she's lucky
			System.out.println("\n\tLucky you! The given day is " + dayName + " which is the " + dayOrder
					+ " day of the week and we are currently open (business hours: 11AM - 11PM). "
					+ "\n\tThere is a discount of " + percentage + "% to be granted for both of your "
					+ refreshmentType + " and your toppings.");

			// determine new toppings' prices
			System.out.println("\n\tDetails about your refreshment and it's toppings:");

			// delegate to another class
			new DiscountCalculator().calculateFullDiscount(percentage);

		}

	}

	public boolean returnHour(String hour) {

	//	SimpleDateFormat sdf = new SimpleDateFormat("hh:mmaa");

		try {
			Date d = formatter_hour.parse(hour);

			if (d.after(formatter_hour.parse("10:59AM")) && d.before(formatter_hour.parse("11:01PM"))) {
				System.out.println("\t --- given time is: " + hour);
				return true;
			}


		} catch (ParseException e) {
			System.out.println("\n\tPlease check the provided hour pattern. " + e.getMessage());
			System.out.println("\tProvided date must be written in this manner (example): 09:14AM");
		}

		System.out.println("\t --- given time is: " + hour);
		return false;

	}

	// RefreshmentJobDiscount methods end here

}
