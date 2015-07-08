package algorithm;

/**
 * 기본 알고리즘 - 수학
 * 
 * @author yeongjun
 *
 */
public class Chapter02 {

	/**
	 * <pre>
	 * Count 알고리즘 영어 시험 성적이 80점 이상인 학생들의 수를 구하는 알고리즘을 제시하라.
	 * - 전체 학생의 수는 100명이다.
	 * - 영어 점수는 100점 만점을 기준으로 채점되었다. 
	 * - 영어 점수는 배열 변수 jumsu(100)에 이미 저장되어 있다고 가정한다.
	 * </pre>
	 */
	public void code01() {
		int jumsu[] = new int[100];
		int cnt = 0;
		int i = 1;

		do {
			if (jumsu[i] >= 80) {
				cnt = cnt + 1;
			}
			i = i + 1;

		} while (!(i > 100));

		System.out.println(cnt);
	}

	/**
	 * <pre>
	 * 최댁값과 최솟값
	 * 영어 시험 만점 학생들 중에서 가장 높은 수학 시험 점수를 가지고 있는 학생의 수학 점수를 찾아서 출력하는 알고리즘을 제시하라.
	 * - 시험을 본 학생은 모두 200명이다.
	 * - 영어 점수는 배열 변수 end(200), 수학 점수는 배열 변수 math(200)에 저장되어 있다.
	 * - 영어, 수학 모두 100점 만점이다.
	 * </pre>
	 */
	public void code02() {
		int eng[] = new int[200];
		int math[] = new int[200];
		int m = 0;
		int i = 1;
		do {
			if (eng[i] == 100) {
				if (math[i] > m) {
					m = math[i];
				}
			}
			i = i + 1;
		} while (!(i > 200));

		System.out.println(m);
	}

	/**
	 * <pre>
	 * 합계와 평균
	 * 다음과 같은 조건에서 휴대폰 고객 1명이 한 달 동안 사용하는 총 통화시간을 토대로 일일 평균 통화시간(초)을 구하는 알고리즘을 제시하다.
	 * - 한 달을 30일로 잡고, 매일(i)의 통화시간은 변수 T(i)에 저장된다.
	 * - 만일 일일 통화시간이 200초 이하이면 무료 서비스를 해주며 총 통화시간에서 제외하고 평균 통화시간을 산정하는 과정에서도 제외한다.
	 * </pre>
	 */
	public void code03() {
		int t[] = new int[30];
		int sum = 0;
		int n = 0;
		int i = 1;

		do {
			if (t[i] > 200) {
				sum = sum + t[i];
				n = n + 1;
			}
			i = i + 1;
		} while (!(i > 30));

		float avg = sum / n;
		System.out.println(avg);
	}

	/**
	 * <pre>
	 * 소수 판별
	 * 1부터 100사이에서 가장 큰 소수를 구하는 알고리즘을 제시하라.
	 * - 소수(Prime Number)란 1과 자기 자신 이외의 수로는 나누어 떨어지지 않는 1보다 큰 자연수를 가리킨다. 예를 들어 2, 3, 5, 7, ... 은 소수이다.
	 * - 만일 자연수 n이 소수라면, 2부터 n의 제곱근√n까지의 자연수들 중에서 n을 나누어 떨어지게  하는 수는 존재하지 않는다.
	 * - n의 제곱근(√n)은 시스템 함수 sqrt(n)을 호출하여 계산한다.
	 * </pre>
	 */
	public void code04() {
		int p = 2;
		int n = 3;

		do {
			int m = (int) Math.sqrt(n);
			int i = 2;
			int r = n % i;

			do {
				if (r == 0) {
					break;
				}
				i = i + 1;
				if (i > m) {
					p = n;
					break;
				} else {
					continue;
				}
			} while (true);
		} while (!(n > 100));

		System.out.println(p);
	}
}
