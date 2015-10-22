package main;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 가사 불러와서 처리하기
 */
public class LyricChecker {
	ArrayList<String> lyric; // 단어별로 나눈 가사
	HashMap<String, String> filePath; // 가사파일 경로 저장변수
	HashMap<Integer, String> checkMap; // 정답 저장공간

	String content;
	
	public LyricChecker(String fileName) {
//		String content = null;
		FileListLoader fl = FileListLoader.INSTANCE;
		filePath = fl.getsLyricFileMap();

		// 먼저 파일 있는 지 확인
		try {

			List<String> lines = Files.readAllLines(Paths.get(filePath.get(fileName)), StandardCharsets.UTF_8);
			StringBuilder sb = new StringBuilder(1024);
			for (int i = 0; i < lines.size(); i++) {
				String line = lines.get(i);
				line = (i < lines.size() - 1) ? (line + "\n") : line;
				sb.append(line);
			}

			content = sb.toString();

		} catch (Exception e) {
			// 파일이 없거나 에러난 경우
			e.printStackTrace();
			content = "nofile";
		}

		// 가사를 리스트에 저장하기
		String token = "…|~| |\n|?|.|,|)|(";
		StringTokenizer stkn = new StringTokenizer(content, token);
		lyric = new ArrayList<String>();

		while (stkn.hasMoreElements()) {
			String s = (String) stkn.nextElement();
			lyric.add(s);
		}

	}

	/**
	 * 출력할 가사 가져오기
	 */
	public String getLyric() {
		StringBuilder sb = new StringBuilder(1024);
		sb.append("<pre>");
		for (String line : lyric) {
			if (line.contains("\n")) {
			}
			sb.append(line + " ");
		}
		sb.append("</pre>");
//		return sb.toString();
		return "<pre>" + content + "</pre>";
	}

	/**
	 * 가사 파일이 있는지 확인, 마지막 파일명으로 확인
	 */
	private boolean hasLyricFile(String path) {
		try {
			List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);
		} catch (Exception e) {
		}
		return true;
	}

}
