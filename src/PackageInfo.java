import static java.lang.Math.abs;

public class PackageInfo {
	String packageName;
	int ca; // the number of classes inside of component
	int ce; // the number of classes outside of component

	public double getInstability() {
		return (double) ce / (double) (ca + ce);
	}

	int na; // the number of abstract classes
	int nc; // the number of all classes

	public double getAbstractness() {
		return (double) na / (double) nc;
	}

	public double getNormalizedDistance() {
		return Math.abs(getAbstractness() + getInstability() - 1);
	}

	public double getDistance() {
		return getNormalizedDistance() / Math.sqrt(2);
	}
}
