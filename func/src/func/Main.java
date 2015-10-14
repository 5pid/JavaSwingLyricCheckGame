package func;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) {

		String content = null;

		try {
			content = new String(Files.readAllBytes(Paths
					.get("D:/git/proj_sunmi/func/src/func/song.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringTokenizer stkn = new StringTokenizer(content, " ");
		ArrayList<String> lyric = new ArrayList<String>();

		while (stkn.hasMoreElements()) {
			String s = (String) stkn.nextElement();
			lyric.add(s);
		}

		for (String s : lyric) {
			System.out.print(s + " ");
		}

	}
}
