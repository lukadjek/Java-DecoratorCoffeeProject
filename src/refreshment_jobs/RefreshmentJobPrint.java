package refreshment_jobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import refreshment.Refreshment;

/*
 * This interface defines RefreshmentJobPrint methods.
 */

public interface RefreshmentJobPrint {

	final ArrayList<Refreshment> list = new ArrayList<Refreshment>();

	public Set<String> getToppings();
	
	public String getOccurencesCount(int occurrences);
	
	public void createToppingsSet(int occurrences, Set<String> set, List<Refreshment> list, int i);

}
