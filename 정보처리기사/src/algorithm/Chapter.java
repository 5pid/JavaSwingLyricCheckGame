package algorithm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

public abstract class Chapter {
	static Random r = new Random();

	public Chapter() {
		r.setSeed(System.currentTimeMillis());
	}
	
	/**
	 * <pre>
	 * 챕터내 알고리즘 종류
	 * </pre>
	 */
	public abstract void explain();

	/**
	 * <pre>
	 * 메서드 실행 부분
	 * </pre>
	 */
	public abstract void excuteAlgorithm();

	/**
	 * <pre>
	 * code로 시작하는 모든 메서드 반환하는 메서드
	 * 
	 * @param clz
	 * @return
	 * </pre>
	 */
	public Method[] getCodeMethods(Class clz) {
		ArrayList<Method> t = new ArrayList<Method>();

		for (Method m : clz.getDeclaredMethods()) {
			if (m.getName().substring(0, 4).equals("code")) {
				t.add(m);
			}
		}

		return (Method[]) t.toArray(new Method[t.size()]);
	}

	/**
	 * <pre>
	 * 난수 발생기
	 * 
	 * @param min
	 * @param max
	 * @return 범위내 난수 값
	 * </pre>
	 */
	public static int getRandomInteger(int min, int max) {
		return r.nextInt(max - min + 1) + min;
	}

}
