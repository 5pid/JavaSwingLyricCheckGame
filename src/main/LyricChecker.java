package main;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
	int[] num;
			
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
		String token = "…|~| |?|.|,|)|(|[|]|:|-|\n";
		// content = content.replace(System.getProperty("line.separator"), " ");
		StringTokenizer stkn = new StringTokenizer(content, token);
		lyric = new ArrayList<String>();

		while (stkn.hasMoreElements()) {
			String s = (String) stkn.nextElement();
			lyric.add(s.trim());
		}

		// 가사를 배열로 담았을때 4개 이하인것은 나중에 추가
		// 가사중에 4개 인덱스 선택
		Set<Integer> ts = new HashSet<>();
		while (ts.size() != 4) {
			int index = (int) (Math.random() * lyric.size());
			ts.add(index);
		}

		// 인덱스 리스트에 저장하고 정렬
		Iterator<Integer> ii = ts.iterator();
		int[] nums = new int[4];
		int i = 0;
		while (ii.hasNext()) {
			nums[i] = ii.next();
			i++;
		}

		Arrays.sort(nums);

		for (i = 0; i < 4; i++) {
			checkMap.put(nums[i], lyric.get(nums[i]));
		}

		num = nums; // 랜덤번호
		
		if (lyric.size() > 1) {
			/**
			 * 
			 * // 랜덤으로 4개 빼기 int size = 0; while (true) { int index = (int)
			 * (Math.random() * lyric.size()); String s = lyric.get(index); if
			 * (s.length() > 5) { checkList.add(s); checkMap.put(index, s);
			 * size++; if (size == 4) { break; } }
			 * 
			 * }
			 */

			// Collections.sort(checkList);
			// checkMap = (HashMap<Integer, String>) sortByValue(checkMap);

			// 빈칸 들어간 것 만들기
			StringBuilder sb = new StringBuilder(1024);
			int idx = 1;

			sb.append("<pre>");
			for (i = 0; i < lyric.size(); i++) {
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

	// public static List sortByValue(final Map map) {
	// List<Integer> list = new ArrayList();
	// list.addAll(map.keySet());
	//
	// Collections.sort(list, new Comparator() {
	//
	// public int compare(Object o1, Object o2) {
	// Object v1 = map.get(o1);
	// Object v2 = map.get(o2);
	//
	// return ((Comparable) v1).compareTo(v2);
	// }
	//
	// });
	// // Collections.reverse(list); // 주석시 오름차순
	// return list;
	// }

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
		
//				checkMap.put(nums[i], lyric.get(nums[i]));
				
				Object[][] result2 = new Object[][] {
				{ 1, lyric.get(num[0]), userInput.get(0), lyric.get(num[0]).equalsIgnoreCase(userInput.get(0)) },
				{ 2, lyric.get(num[1]), userInput.get(1), lyric.get(num[1]).equalsIgnoreCase(userInput.get(1)) },
				{ 3, lyric.get(num[2]), userInput.get(2), lyric.get(num[2]).equalsIgnoreCase(userInput.get(2)) },
				{ 3, lyric.get(num[3]), userInput.get(3), lyric.get(num[3]).equalsIgnoreCase(userInput.get(3)) } };

		return result2;
	}

}
