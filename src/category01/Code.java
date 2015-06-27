package category01;

/**
 * 기본 알고리즘 - 수열
 * 
 * @author yeongjun
 *
 */
public class Code {

	/**
	 * <pre>
	 * 기본 수열(1부터 100까지 자연수)
	 * 1부터 100까지 자연수의 합을 구하는 알고리즘을 제시하라.
	 * </pre>
	 */
	public void code01() {
		int sum = 0;
		int n = 1; // 수열 항을 나타낼 변수

		do {
			sum += n;
			n++;
		} while (!(n > 100));

		System.out.println(sum);
	}

	/**
	 * <pre>
	 * 등차 수열
	 * 다음 등차 수열에 대하여 200번째 숫자까지의 합을 구하라.
	 * 2, 8, 14, 20, 26, 32, ...
	 * 
	 * <pre>
	 */
	public void code02() {
		int a = 2; // 등차 수열의 초항
		int d = 6; // 등차 수열의 공차
		int s = a; // 합을 보관할 변수
		int n = 2; // 항의 순서

		do {
			int an = a + (n - 1) * d;
			s += an;
			n++;
		} while (!(n > 200));

		System.out.println(s);
	}

	/**
	 * <pre>
	 * 등비수열
	 * 다음 등비 수열에 대하여 100번째 할까지의 합을 구하라.
	 * 2, 6, 18, 54, 162, 486, ...
	 * </pre>
	 */
	public void code03() {
		
	}

}
