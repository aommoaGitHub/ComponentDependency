import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryReader {

	public List<File> getFiles(File root) {
		List<File> files = new ArrayList<File>();
		// If the root is file, return file
		if (root.isFile()) {
			if (root.getName().contains(".java"))
				files.add(root);
			return files;
		}
		// If the root is the directory, get all Files in the directory
		if (root.isDirectory()) {
			for (File file : root.listFiles()) {
				files.addAll(getFiles(file));
			}
			return files;
		}
		return new ArrayList<File>();
	}
}
