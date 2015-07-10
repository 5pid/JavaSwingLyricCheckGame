package algorithm;

import java.lang.reflect.Method;

/**
 * 기본 알고리즘 - 수학
 * 
 * @author yeongjun
 *
 */
public class Chapter02 extends Chapter {

	@Override
	public void explain() {

	}

	@Override
	public void excuteAlgorithm() {
		Chapter02 obj = new Chapter02();
		Method[] m = obj.getCodeMethods(obj.getClass());

		for (Method md : m) {
			try {
				System.out.println(md);

				md.setAccessible(true); // private 메서드 사용허용하기
				// md.invoke(obj, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		obj.code08();

	}

	/**
	 * Count 알고리즘
	 * 
	 * <pre>
	 * 영어 시험 성적이 80점 이상인 학생들의 수를 구하는 알고리즘을 제시하라.
	 * - 전체 학생의 수는 100명이다.
	 * - 영어 점수는 100점 만점을 기준으로 채점되었다. 
	 * - 영어 점수는 배열 변수 jumsu(100)에 이미 저장되어 있다고 가정한다.
	 * </pre>
	 */
	private void code01() {
		int jumsu[] = new int[100];

		for (int i = 0; i < 100; i++) {
			jumsu[i] = getRandomInteger(0, 100);
		}

		int cnt = 0;
		int i = 0;

		do {
			if (jumsu[i] >= 80) {
				cnt = cnt + 1;
			}
			i = i + 1;

		} while (!(i >= 100));

		System.out.println(cnt);
	}

	/**
	 * 최댓값과 최솟값
	 * 
	 * <pre>
	 * 영어 시험 만점 학생들 중에서 가장 높은 수학 시험 점수를 가지고 있는 학생의 수학 점수를 찾아서 출력하는 알고리즘을 제시하라.
	 * - 시험을 본 학생은 모두 200명이다.
	 * - 영어 점수는 배열 변수 end(200), 수학 점수는 배열 변수 math(200)에 저장되어 있다.
	 * - 영어, 수학 모두 100점 만점이다.
	 * </pre>
	 */
	private void code02() {
		int eng[] = new int[200];
		int math[] = new int[200];

		for (int i = 0; i < 200; i++) {
			eng[i] = getRandomInteger(0, 100);
			math[i] = getRandomInteger(0, 100);
		}

		int m = 0;
		int i = 1;
		do {
			if (eng[i] == 100) {
				if (math[i] > m) {
					m = math[i];
				}
			}
			i = i + 1;
		} while (!(i >= 200));

		System.out.println(m);
	}

	/**
	 * 합계와 평균
	 * 
	 * <pre>
	 * 다음과 같은 조건에서 휴대폰 고객 1명이 한 달 동안 사용하는 총 통화시간을 토대로 일일 평균 통화시간(초)을 구하는 알고리즘을 제시하다.
	 * - 한 달을 30일로 잡고, 매일(i)의 통화시간은 변수 T(i)에 저장된다.
	 * - 만일 일일 통화시간이 200초 이하이면 무료 서비스를 해주며 총 통화시간에서 제외하고 평균 통화시간을 산정하는 과정에서도 제외한다.
	 * </pre>
	 */
	private void code03() {
		int t[] = new int[30];

		for (int c = 0; c < 30; c++) {
			t[c] = getRandomInteger(0, 6000);
		}

		int sum = 0;
		int n = 0;
		int i = 1;

		do {
			if (t[i] > 200) {
				sum = sum + t[i];
				n = n + 1;
			}
			i = i + 1;
		} while (i > 30);

		float avg = sum / n;
		System.out.println(avg);
	}

	/**
	 * 소수 판별
	 * 
	 * <pre>
	 * 1부터 100사이에서 가장 큰 소수를 구하는 알고리즘을 제시하라.
	 * - 소수(Prime Number)란 1과 자기 자신 이외의 수로는 나누어 떨어지지 않는 1보다 큰 자연수를 가리킨다. 예를 들어 2, 3, 5, 7, ... 은 소수이다.
	 * - 만일 자연수 n이 소수라면, 2부터 n의 제곱근√n까지의 자연수들 중에서 n을 나누어 떨어지게  하는 수는 존재하지 않는다.
	 * - n의 제곱근(√n)은 시스템 함수 sqrt(n)을 호출하여 계산한다.
	 * </pre>
	 */
	private void code04() {
		int p = 2;
		int n = 3;

		do {
			int m = (int) Math.sqrt(n);
			int i = 2;
			int r = n % i;

			loop: do {
				if (r == 0) {
					break;
				}
				i = i + 1;
				if (i > m) {
					p = n;
					break loop;
				} else {
					r = n % i;
					continue;
				}
			} while (true);
			n++;
		} while (!(n > 100));

		System.out.println(p);
	}

	/**
	 * 소인수 분해
	 * 
	 * <pre>
	 * 자연수 n을 입력받아 소인수 분해하여 그 결과를 출력하는 알고리즘을 제시하라.
	 * - 입력받은 값 n은 1000 이하의 자연수라고 가정한다.
	 * - 입력받은 정수 n이 2보다 작으면 알고리즘을 종료한다.
	 * - 입력받은 정수 n이 소수이면 '소수'라고 출력한다.
	 * - 입력받은 정수 n이 소수가 아니면 소인수 분해한 결과를 출력한다.
	 * - 단계별로 소인수 분해ㅐ한 결과를 배열에 저장해 두었다가 나중에 한꺼번에 출력한다.
	 * - 132는 2 x 2 x 3 x 11 과 같이 소인수 분해된다.
	 * </pre>
	 */
	private void code05() {
		int[] a = new int[20];
		int n = getRandomInteger(0, 1000);
		System.out.println("n = " + n);

		if (n >= 2) {
			int t = 0;
			do {
				int p = 2;

				while (!(n % p == 0)) {
					p = p + 1;
				}
				t = t + 1;
				a[t] = p;
				n = n / p;

			} while (!(n == 1));

			if (t == 1) {
				System.out.println("소수");
			} else {
				for (int j = 1; j <= t - 1; j++) {
					System.out.print(a[j] + "*");
				}
				System.out.println(a[t]);
			}

		} else {

		}
	}

	/**
	 * 배수와 공배수
	 * 
	 * <pre>
	 * 배열 a에 21, 17, 4, 51, 24, 75, 40, 27, 48, 72가 a(1)부터 시작하여 순차적으로 입력되어
	 * 있다고 가정할 때, 3의 배수이면서 4의 배수인 수의 개수를 구하는 알고리즘을 제시하라
	 * (단, A를 B로 나눈 나머지를 구해주는 시스템 함수 mod(a,b)를 활용한다).
	 * </pre>
	 */
	private void code06() {
		int a[] = new int[] { 21, 17, 4, 51, 24, 75, 40, 27, 48, 72 };
		int cnt = 0;
		int i = 0;

		do {
			int n3 = a[i] % 3;
			int n4 = a[i] % 4;
			int n = n3 + n4;
			i = i + 1;

			if (n == 0) {
				cnt = cnt + 1;
			}
		} while (i < 10);

		System.out.println(cnt);
	}

	/**
	 * 약수와 완전수
	 *
	 * <pre>
	 * 4부터 500까지의 자연수 중에서 자기 자신을 뺀 약수들의 합이 자신과 같아지는 수(완전수)를 
	 * 찾아 출력하고 그 개수를 구하는 알고리즘을 제시하라.
	 * </pre>
	 */
	private void code07() {
		int tn = 0;
		for (int n = 4; n <= 500; n++) {
			int sum = 0;
			int k = n / 2;
			for (int j = 1; j <= k; j++) {
				int r = n % j;
				if (r == 0) {
					sum = sum + j;
				}
			}

			if (n == sum) {
				System.out.println(n);
				tn = tn + 1;
			}
		}
		System.out.println(tn);
	}

	/**
	 * 최대공약수와 최소공배수
	 * 
	 * <pre>
	 * 다음과 같은 유클리드 호제법에 의하여 두 정수 x, y의 최대공약수(gcd)를 구하는 알고리즘을 제시하라.
	 * 1. gcd(x,y) = gcd(x,y)			x < y 일 경우
	 * 2. gcd(x,y) = y					x >= y이면서 mod(x,y)=0일 경우
	 * 3. gcd(x,y) = gcd(y, mod(x,y))	그 외(즉, x >= y이면서 mod(x,y) != 0일 경우)
	 * </pre>
	 */
	private void code08() {
		int x = getRandomInteger(0, 100);
		int y = getRandomInteger(0, 100);
		System.out.println("x:" + x + " / y:" + y);

		if (!(x >= y)) {
			int temp = x;
			x = y;
			y = temp;
		}

		while (true) {
			int m = x % y;
			if (m == 0) {
				break;
			}
			x = y;
			y = m;
		}
		System.out.println(y);
	}

	/**
	 * 근사값
	 * 
	 * <pre>
	 * 배열 a(100)의 원소 100개는 절대값이 500 이하이다. 이 중에서 
	 * 정수 33에 가까운 근사값을 찾아 해당 원소의 첨자를 출력하는 알고리즘을 제시하라.
	 * </pre>
	 */
	private void code09() {
		int[] a = new int[100];
		int cha; // 어떤 배열 원소의 값과 33의 차이
		int minCha; // 100개의 원소 모두에 대한 cha의 값들 중 가장 작은 값
		int ans; // minCha 값을 갱신시키는 배열 원소의 첨자
		
		
	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private void code10() {

	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private void code11() {

	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private void code12() {

	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private void code13() {

	}

	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private void code14() {

	}

}
