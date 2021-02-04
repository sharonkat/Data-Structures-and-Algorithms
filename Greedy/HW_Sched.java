import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;


	protected Assignment() {
	}

	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}



	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * Return -1 if a1 > a2
	 * Return 1 if a1 < a2
	 * Return 0 if a1 = a2 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		// TODO Implement this

		/* with this implementation, compare returns the hierarchy in deadlines */
		int deadlineDiff = a1.deadline - a2.deadline;
		int weightDiff = a1.weight - a2.weight;

		if (deadlineDiff == 0) {
			if (weightDiff > 0) {
				return -1; 						//a1
			} else if (weightDiff < 0) {
				return 1; 						//a2
			} else if (weightDiff == 0) {
				return 0;
			}
		} else if (deadlineDiff > 0) {			//a2 due first
			return 1;
		} else if (deadlineDiff < 0) {			//a1 due first
			return -1;
		}

		return 0;
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;

	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}


	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this

		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());			// this is an array-list
																	// They are returned in order of deadline with some weight consideration

		// If schedule[i] has a value -1, it indicates that the 
		// i'th time-slot in the schedule is empty
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i<lastDeadline; i++) {
			homeworkPlan[i] = -1;
		}
		
		
		// Add another for loop looping over the Assignments! 
		// Check if the deadline has been reached
		for (Assignment assign : Assignments) {
			for (int i=1; i<=assign.deadline; i++) {				// making sure we don't double book deadlines
				if (homeworkPlan[assign.deadline-i] == -1) {
					homeworkPlan[assign.deadline-i] = assign.number;
				}
			}
		}



		return homeworkPlan;
	}
}




