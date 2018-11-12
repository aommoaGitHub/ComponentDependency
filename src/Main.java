import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) {
		File root = new File("./kafka-2.0 2.1-src");
		DirectoryReader dirReader = new DirectoryReader();
		List<File> files = dirReader.getFiles(root);
		Map<String, PackageInfo> packages = new HashMap<>();
		for (File file : files) {
			FileReader fileReader = new FileReader();
			fileReader.read(file);
			PackageInfo newPackageInfo = fileReader.getPackageInfo();
			if (newPackageInfo.packageName.equals("")) {
				String packageWithFile = file.toString().split("java/")[1].replaceAll("/", ".");
				String packageName = packageWithFile.substring(0, packageWithFile.lastIndexOf("."));
				newPackageInfo.packageName = packageName;
			}
			if (packages.containsKey(newPackageInfo.packageName)) {
				PackageInfo packageInfo = packages.get(newPackageInfo.packageName);
				packageInfo.ca += newPackageInfo.ca;
				packageInfo.na += newPackageInfo.na;
				packageInfo.nc += newPackageInfo.nc;
			} else {
				packages.put(newPackageInfo.packageName, newPackageInfo);
			}
			List<String> usedPackages = fileReader.getUsedPackages();
			for (String s : usedPackages) {
				if (packages.containsKey(s)) {
					PackageInfo packageInfo = packages.get(s);
					packageInfo.ce++;
				} else {
					PackageInfo p = new PackageInfo();
					p.packageName = s;
					p.ce = 1;
					packages.put(s, p);
				}
			}
		}
		List<String> removes = new ArrayList<>();
		for (String key : packages.keySet()) {
			PackageInfo p = packages.get(key);
			if (p.nc == 0 || p.packageName.contains("{")) {
				removes.add(key);
			}
		}
		for (String key : removes) {
			packages.remove(key);
		}
		System.out.println(files.size() + " java files");
		System.out.println(packages.size() + " packages");

		try {
			PrintWriter writer = new PrintWriter("packages.csv", "UTF-8");
			writer.println("packageName, instability, abstractness, normalizedDistance, distance");
			for (PackageInfo p : packages.values()) {
				writer.println(p.packageName + ", " + p.getInstability() + ", " + p.getAbstractness() + ", "
						+ p.getNormalizedDistance() + ", " + p.getDistance());
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("packages.csv is created at the project's root directory");
		System.out.println("Done");
	}
}
