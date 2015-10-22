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
	ArrayList<String> lyric_blank; // 단어별로 나눈 가사
	HashMap<String, String> filePath; // 가사파일 경로 저장변수
	HashMap<Integer, String> checkMap; // 가사파일 경로 저장변수
	ArrayList<String> checkList = null; // 정답 저장공간

	String content;
	String content_answer;
	String content_blank;

	public LyricChecker(String fileName) {
		// String content = null;
		FileListLoader fl = FileListLoader.INSTANCE;
		filePath = fl.getsLyricFileMap();
		checkList = new ArrayList<>();
		checkMap = new HashMap<>();

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
		// String token = "…|~| |\n|?|.|,|)|(";
		String token = "…|~| |?|.|,|)|(|:|-";
		StringTokenizer stkn = new StringTokenizer(content, token);
		lyric = new ArrayList<String>();

		while (stkn.hasMoreElements()) {
			String s = (String) stkn.nextElement();
			lyric.add(s);
		}

		if (lyric.size() > 1) {
			// 랜덤으로 4개 빼기
			int size = 0;
			while (true) {
				int index = (int) (Math.random() * lyric.size());
				String s = lyric.get(index);
				if (s.length() > 5) {
					checkList.add(s);
					checkMap.put(index, s);
					size++;
					if (size == 4) {
						break;
					}
				}

			}

			// 빈칸 들어간 것 만들기
			StringBuilder sb = new StringBuilder(1024);
			int idx = 1;

			sb.append("<pre>");
			for (int i = 0; i < lyric.size(); i++) {
				StringBuffer str = new StringBuffer(lyric.get(i));
				if (checkMap.get(i) != null) {
					System.out.println(str.toString());
					StringBuffer qstr = new StringBuffer();
					qstr.append("<b><i><font color=\"red\">");
					qstr.append(idx + ".");
					for (int c = 0; c < str.length(); c++) {
						qstr.append("_");
					}
					qstr.append("</font></i></b>");
					idx++;

					checkList.add(checkMap.get(i));
					str = qstr;
				} else {
				}
				sb.append(str + " ");
			}
			sb.append("</pre>");
			content_blank = sb.toString();
		} else {
			content_blank = content;
		}

	}

	/**
	 * 출력할 가사 가져오기
	 */
	public String getLyric_blank() {
		return content_blank;
		// 임시 스샷용
		// return "<pre>" + content + "</pre>";
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

	public Object[][] getResultDates(ArrayList<String> userInput) {
		Object[][] result = new Object[][] {
				{ 1, checkList.get(0), userInput.get(0), checkList.get(0).equalsIgnoreCase(userInput.get(0)) },
				{ 2, checkList.get(1), userInput.get(1), checkList.get(1).equalsIgnoreCase(userInput.get(1)) },
				{ 3, checkList.get(2), userInput.get(2), checkList.get(2).equalsIgnoreCase(userInput.get(2)) },
				{ 3, checkList.get(3), userInput.get(3), checkList.get(3).equalsIgnoreCase(userInput.get(3)) } };

		return result;
	}

}
