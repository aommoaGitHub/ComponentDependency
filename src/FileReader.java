import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	private PackageInfo packageInfo;
	private List<String> usedPackages;

	public FileReader() {
		this.packageInfo = new PackageInfo();
		this.usedPackages = new ArrayList<>();
	}

	String allLine = "";

	public void read(File file) {
		try {
			BufferedReader reader = new BufferedReader(new java.io.FileReader(file));
			String line = reader.readLine();
			while (line != null) {
				if (line.contains("package "))
					this.packageInfo.packageName = line.split(" ")[1].replace(";", "");
				if (line.contains("class "))
					this.packageInfo.nc++;
				if (line.contains("abstract class "))
					this.packageInfo.na++;
				if (line.contains("import ")) {
					this.packageInfo.ca++;
					String[] splitImport = line.split(" ");
					String usedClass = splitImport[splitImport.length - 1].replace(";", "");
					String usedPackage = usedClass.contains(".") ? usedClass.substring(0, usedClass.lastIndexOf("."))
							: usedClass;
					this.usedPackages.add(usedPackage);
				}
				allLine += line;
				line = reader.readLine();
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public PackageInfo getPackageInfo() {
		return this.packageInfo;
	}

	public List<String> getUsedPackages() {
		return this.usedPackages;
	}
}
