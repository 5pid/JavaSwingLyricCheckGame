package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 * @author Yeong-jun
 *
 */

/**
 * @author Yeong-jun
 *
 */
/**
 * @author Yeong-jun
 *
 */
public enum FileListLoader {
	INSTANCE;

	private HashMap<String, String> mp3PathMap; // 파일이름(노래_가수), 경로
	private HashMap<String, String> lyricPathMap;

	FileListLoader() {
		mp3PathMap = new HashMap<>();
		lyricPathMap = new HashMap<>();
		fileOpen();

		System.out.println("------------------------------------");
		System.out.println(mp3PathMap.size() + "개의 mp3 파일 로드 완료");
		System.out.println("------------------------------------");

	}

	/**
	 * 파일명들을 리스트패널에 넣어줄 수 있도록 변환하기
	 */
	public ListModel getListModel(int how) {
		DefaultListModel listModel;
		listModel = new DefaultListModel();

		List<String> list = new ArrayList<String>(mp3PathMap.keySet());

		// list = quicksort(list, how);
		if (how == 1) {
			Collections.sort(list);
		} else {
			Collections.sort(list, Collections.reverseOrder());
		}

		System.out.println();
		for (String s : list) {
			listModel.addElement(s);
		}

		return listModel;
	}

	public HashMap getMp3FileMap() {
		return mp3PathMap;
	}

	public HashMap getsLyricFileMap() {
		return lyricPathMap;
	}

	/**
	 * @param list
	 * @param how
	 *            - 1 오름차순, 2 - 내림차순
	 * @return
	 */
	private List<String> quicksort(List<String> input, int how) {

		if (input.size() <= 1) {
			return input;
		}

		int middle = (int) Math.ceil((double) input.size() / 2);
		String pivot = input.get(middle);

		List<String> less = new ArrayList<String>();
		List<String> greater = new ArrayList<String>();

		for (int i = 0; i < input.size(); i++) {
			if (input.get(i).compareTo(pivot) < 0) {
				if (i == middle) {
					continue;
				}
				less.add(input.get(i));
			} else {
				greater.add(input.get(i));
			}
		}

		return concatenate(quicksort(less, how), pivot, quicksort(greater, how));
	}

	/**
	 * 퀵정렬에 사용할 메서드
	 */
	private List<String> concatenate(List<String> less, String pivot, List<String> greater) {

		List<String> list = new ArrayList<String>();

		for (int i = 0; i < less.size(); i++) {
			list.add(less.get(i));
		}

		list.add(pivot);

		for (int i = 0; i < greater.size(); i++) {
			list.add(greater.get(i));
		}

		return list;
	}

	/**
	 * 디렉토리에 저장된 노래목록 읽어오기
	 * 
	 * <pre>
	 * 앞으로 해야할 일. mp3파일만 걸러서 출력하기
	 * </pre>
	 */
	public void fileOpen() {
		String path = System.getProperty("user.dir") + "/resources";
		String key = null;
		File dirFile = new File(path);
		File[] fileList = dirFile.listFiles();
		for (File tempFile : fileList) {
			if (tempFile.isFile()) {
				String tempPath = tempFile.getParent();
				String tempFileName = tempFile.getName();

				if (tempFileName.contains(".mp3")) {
					key = replaceLast(tempFileName, ".mp3", "");

					mp3PathMap.put(key, tempPath + "/" + tempFileName);
					lyricPathMap.put(key, tempPath + "/" + key + ".txt");
				}
				System.out.println(key + ", " + mp3PathMap.get(key));
			}
		}

	}

	private static String replaceLast(String string, String toReplace, String replacement) {
		int pos = string.lastIndexOf(toReplace);
		if (pos > -1) {
			return string.substring(0, pos) + replacement + string.substring(pos + toReplace.length(), string.length());
		} else {
			return string;
		}
	}

}
