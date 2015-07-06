package algorithm;

/**
 * 기본 알고리즘 - 수열
 * 
 * @author yeongjun
 *
 */
public class Chapter01 {

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
	 * 등차수열
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
		int r = 3;
		int a = 2;
		int s = a;
		int n = 2;

		do {
			a = a * r;
			s = s + a;
			n = n + 1;
		} while (!(n > 100));

		System.out.println(s);
	}

	/**
	 * <pre>
	 * 피보나치 수열
	 * 다음 피보나치 수열에 대하여 100번째 항까지의 합을 구하는 알고리즘을 제시하라.
	 * 1, 1, 2, 3, 5, 8, 13, ...
	 * </pre>
	 */
	public void code04() {
		int a = 1;
		int b = 1;
		int sum = a + b;
		int n = 2;
		do {
			int c = a + b;
			sum = sum + c;
			a = b;
			b = c;
			n = n + 1;
		} while (!(n == 100));

		System.out.println(sum);
	}

	/**
	 * <pre>
	 * 누승 활용 수열
	 * 1부터 100까지의 누승의 합을 구하여 출력하는 알고리즘을 제시하라.
	 * S = 1! + 2! + 3! + 4! + 5! + ... + 100!
	 * </pre>
	 */
	public void code05() {
		int n = 1;
		int f = 1;
		int sum = 1;
		do {
			n = n + 1;
			f = f * n;
			sum = sum + f;
		} while (!(n == 100));

		System.out.println(sum);
	}

	/**
	 * <pre>
	 * 제곱의 합
	 * S = (100*1)^2 + (99*2)^2 + (98*3)^2 + ... + (2*99)^2 + (1*100)^2의 합을 구하여 출력하는 알고리즘을 제시하라.
	 * </pre>
	 */
	public void code06() {
		int sum = 0, a = 0, b = 0, c = 0;

		do {
			a = a + 1;
			b = 101 - a;
			c = a * b;
			c = c * c;
			sum = sum + c;
		} while (a < 100);

		System.out.println(sum);
	}

	/**
	 * <pre>
	 * '+,-' 교행 자연수 수열
	 * S = 1 - 2 + 3 - 4 + 5 + ... - 100의 값을 구하여 출력하는 알고리즘을 제시하라.
	 * </pre>
	 */
	public void code07() {
		int n = 0, sum = 0;

		do {
			n = n + 1;
			sum = sum + n;
			n = n + 1;
			sum = sum - n;
		} while (!(n == 100));

		System.out.println(sum);
	}

	/**
	 * <pre>
	 * '+,-' 교행 분수 수열
	 * S = 1/2*3 - 2/3*4 + 3/4*5 - 4/5*6 + 5/6*7 + ... + 49/50*51의 값을 구하여 출력하는 알고리즘을 제시하라.
	 * </pre>
	 */
	public void code08() {
		float k = 0;
		float s = 0;
		float sw = 0;

		do {
			k = k + 1;
			float l = k / ((k + 1) * (k + 2));
			if (sw == 0) {
				s = s + l;
				sw = 1;
			} else {
				s = s - l;
				sw = 0;
			}
		} while (!(k == 49));

		System.out.println(s);
	}
}
