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
	 * 
	 * </pre>
	 */
	public void code02() {
		
	}
}
