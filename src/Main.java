import java.io.File;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		DirectoryReader dirReader = new DirectoryReader();
		File root = new File("./kafka-2.0 2.1-src");
		FileReader fileReader = new FileReader();
		List<File> files = dirReader.getFiles(root);
		for (File file : files) {
			// if (files.indexOf(file) == 0) {
			// String content = fileReader.read();
			// System.out.println("\n" + file.isFile() + "\n");
			// }
			System.out.println(file);
		}
	}
}
