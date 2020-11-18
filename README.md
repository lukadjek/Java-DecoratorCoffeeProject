SPECIFICATION

Short description: This project represents the advanced use of the *Decorator Pattern. ‹Advanced› meaning it tracks down the decorators hierarchy (added possibility to take a peek at multiple decorator layers). 

*The Decorator Pattern attaches additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality.

### This is a ‹brief› overview of the project (a thorough explanation comes below):

- decorators(TOPPINGS) have the same supertype as the object they decorate(REFRESHMENT)
- an object(REFRESHMENT TYPE) can be decorated dynamically at runtime with one or more toppings (also, it’s size and price can be altered)
- both the refreshment type and the toppings are of the same type (REFRESHMENT)
- the main method for the refreshment type and toppings is the method ‹cost()› that is the one that gets delegated throughout multiple classes (which again all share the same supertype)
- both the refreshment type and the toppings are added to the same list which then gets transformed into a set(which does not allow duplicates by default) that parses the description to print the number of same toppings for one refreshment type(one/double/triple + topping name)
- for either a refreshment type or it’s toppings, a size must be set (small, medium, large)
- by default, each refreshment type as well as each topping have a default price set
- by using getters and setters, a price of each refreshment type as well as each topping can be called/altered
- some added functionalities include: adding a discount to toppings on a certain day, adding a discount to the refreshment type and it’s toppings on a certain day and only within business hours  
- every error/problem/confusion/exception is caught with a clear and precise information so that a user will know what’s happening at all times during testing/playing around with this software

## General explanation for each of the 10 classes from the project:
    
1. Class ‹Refreshment› : this is the main abstract base class. Main class meaning all refreshment types (ex. a coffee, a juice, a champagne, a vine etc.) will extend this class. Toppings (ice cream, foam etc.) will also extend this class. So in one sentence - all refreshment types and all topping types are of the same general type (Refreshment). This class has several methods which are: getDescription(), setPrice(), getPrice(), setSize(), getSize(), equals(), cost(), toString(). This class has several instances which are the following: description, size, price. This class extends ‹RefreshmentJob› class.

2. Class ‹RefreshmentJob› : this is the main and only job class. It implements and defines jobs for the Refreshment class. This class basically overrides all methods that are set in the job interfaces. Besides every method in this class there is a clear explanation what this method does/returns so it’s easier to navigate around all specific jobs. This class implements the following interfaces: RefreshmentJobPrint, RefreshmentJobDiscount.

3. Interface ‹RefreshmentJobPrint› : this interface only defines methods that are of importance for the print function. This class has several methods which are: getToppings(), getOccurencesCount(), createToppingsSet(). This class has 1 instance which is of the following type: list.

4. Interface ‹RefreshmentJobDiscount› : this interface only defines method that are of importance for the discount function. It has a static inner class that is used solely for the calculation purposes.
This class has several methods which are: addFridayToppingsDiscount(), addNotOnMondayFullDiscount(), checkDatePattern(), returnDayNumber(), returnDayWeekOrderNumber(), returnDayName(), . This class has several instances which are the following: formatter_date, formatter_day, formatter_day_number_in_week, formatter_hour. This class extends ‹RefreshmentJobPrint› interface because it uses that same list from that interface.

5. Class ‹IrishCoffee› : this class represents a refreshment type. Obviously it has a constructor that takes a size instance since every refreshment type must be set together with a default size. This class overrides the following methods: cost(), toString().

6. Class ‹ToppingDecorator› : this is the main abstract class for all toppings meaning all toppings must extend this particular class. This class has several methods which are: getDescription(), thisToppingCost(), toString(). This abstract class extends class ‹Refreshment› because ultimately, whenever a new topping extends ‹ToppingDecorator›, that new topping is at the end of type ‹Refreshment› since ‹ToppingDecorator› extends ‹Refreshment›.

7. Class ‹Foam› : this class is a topping type. It’s constructor takes in 2 types: a Refreshment type and a size enum. Because it takes a Refreshment type, that type can either be another topping or a one of the refreshment types if we want to close the object’s wrapping. It overrides all abstract methods set in the ‹ToppingDecorator› and ‹Refreshment› classes. This class has 1 instance which is of the following type: refreshment. This class extends <ToppingDecorator> class.

8. Class ‹IceCream› : this class is a topping type. It’s constructor takes in 2 types: a Refreshment type and a size enum. Because it takes a Refreshment type, that type can either be another topping or a one of the refreshment types if we want to close the object’s wrapping. It overrides all abstract methods set in the ‹ToppingDecorator› and ‹Refreshment› classes. This class has 1 instance which is of the following type: refreshment. This class extends <ToppingDecorator> class.

9. Enum ‹Size› : this enum defines 3 default size values for anything that is of Refreshment type (all refreshment types and all topping types). These values are: small, medium, large. Obviously it has a constructor that takes a size name. This enum has several methods which are: getName() , toString(). This class has 1 instance which is the following: name. 

10. Class ‹Test› : this class is used for testing multiple possible scenarios. 
> The tested cases are as follows: 
```
 -  *** 1. Determine only refreshment's cost without any toppings ***
 -  *** 2. Determine refreshment's cost with different toppings (only 1 topping per drink) *** 
 -  *** 3. Determine refreshment's cost with multiple toppings (multiple toppings per drink) *** 
 -  *** 4. Calculate default full drink's cost and then determine your own refreshment's and topping's price and finally calculate full drink's cost afterwards *** 
 -  *** 5. Another (more difficult to read) way of creating a refreshment without creating toppings' type objects *** 
 -  *** 6. Adding your provided discount to the toppings only on Friday *** 
 -  *** 7. Adding your provided discount to the refreshment and it's toppings on any given day except for Monday (Monday is day off) and only within business hours 11AM - 11PM *** 
```
### Deeper explanation for each of the 10 test cases from the project:

   1. This one is pretty self-explanatory. We just create a refreshment type without any toppings and call its default cost
   2. Here we are adding different toppings to the refreshment type and then calling cost() method on that refreshment type. Also we are listing number of occurrences for each of the toppings in that refreshment type
   3. Same as 2. but we the slight difference that we are here adding multiple same toppings to the refreshment type over and over again
   4. This one is the same as 1. or 2. but ultimately after determining the full refreshment cost (refreshment type + it’s toppings), we set our own refreshment type and/or topping’s price and then get the new full refreshment cost printed out
   5. This is the same as 2. or 3. but written in a touch more arduous manner
   6. This one is worth explaining deeper. Here we check the following:
	   - a) percentage value must be between 1 and 99
	   - b) date pattern must be a valid one (written in this manner: day/month/year. Example: 01/10/2021)
	   - c) there must be at least 1 topping added to the refreshment type (otherwise, a toppings discount will not be allowed)
	   - d) day name from the provided date pattern must be Friday (because this discount is valid only on Fridays)
	   - e) a discount calculation is taking place and for each added topping a new discount price is set
	   - f) printed data at the end: a brief overview for the full drink (for the refreshment type and each of the toppings) consisting of the name and size and 	        price; informing the user whether his whole input is valid and whether a discount will be granted or not and if so then why not; all toppings with their old prices together with their new prices (for each of the added toppings); total old toppings’ price; total new toppings’ price; how much was saved with    	the added discount; an invoice (consisting of a refreshment price + full toppings’ price)
	   - g) important note: should the user give a false input for any of the asked data, the error message must be to the point expressed so that a stellar user   		   experience is guaranteed
  7. Same as 6. , but the discount is valid on any given day except for Monday and it is valid only during business hours (11AM-11PM). Also the discount is in this case added on both the refreshment type and it’s toppings

