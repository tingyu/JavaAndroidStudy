package Analysis;

import Model.Student;

public abstract class StatisticAbstract {
	
	abstract void findlow(Student [] a);
	abstract void findhigh(Student [] a);
	abstract void findavg(Student [] a);
}
