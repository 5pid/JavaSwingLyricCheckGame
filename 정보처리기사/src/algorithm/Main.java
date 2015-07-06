package algorithm;

import java.util.Scanner;

import category01.Code;

/**
 * @author yeongjun
 *
 */
public class Main {
	private String[][] category;

	public Main() {
		init();
	}

	/**
	 * 카테고리 텍스트 배열 초기화
	 */
	private void init() {
		category = new String[5][];
		category[0] = new String[] { "기본 수열(1부터 100까지 자연수)", "등차 수열", "등비 수열",
				"피보나치 수열", "누승 활용 수열", "제곱의 합", "'+,-' 교행 자연수 수열",
				"'+,-' 교행 분수 수열" };

		category[1] = new String[] { "Count 알고리즘", "최댓값과 최솟값", "합계와 평균",
				"소수 판별", "소인수 분해", "배수와 공배수", "약수와 완전수", "최대공약수와 최소공배수", "근사값",
				"10진수와 2진수의 변환", "10진수와 16진수의 변환", "BCD 코드와 3초과 코드의 변환",
				"1의 보수와 2의 보수", "패리티 비트 검증" };

		category[2] = new String[] { "행 우선/열 우선 배열 채우기", "삼각형 모양으로 배열 채우기",
				"모래시계 모양으로 배열 채우기", "달팽이 모양으로 배열 채우기", "마름모 모양으로 배열 채우기",
				"'ㄹ'자 모양으로 배열 채우기", "마방진 배열 채우기", "배열 회전시키기", "행렬 곱셈 계산하기" };

		category[3] = new String[] { "석차 구하기", "선택 정렬", "버블 정렬", "삽입 정렬",
				"병합 정렬", "퀵 정렬", "이분 검색", "최소비용 그래프" };

		category[4] = new String[] { "통계 산출 알고리즘", "재고 관리 알고리즘",
				"급여 계산/지폐 매수 계산 알고리즘", "요일 계산 알고리즘", "은행이자 계산 알고리즘" };
	}

	/**
	 * 단원 선택 
	 */
	void menuSelector() {
		Scanner input = new Scanner(System.in);
		int select = -1;

		System.out.println("==================================");
		System.out.println("2014년 정보처리기사 실기 - 이기적");
		System.out.println("==================================");
		System.out.println("1. 기본 알고리즘 - 수열");
		System.out.println("2. 기본 알고리즘 - 수학");
		System.out.println("3. 응용 알고리즘 - 배열");
		System.out.println("4. 응용 알고리즘 - 자료 구조");
		System.out.println("5. 실무 응용 알고리즘");
		System.out.println("==================================");

		menu: while (true) {
			System.out.print("선택: ");
			select = input.nextInt();

			switch (select) {
			case 1:
				// 기본 알고리즘 - 수열
				for (int i = 1; i < category[0].length; i++) {
					System.out.println(i + ") " + category[0][i]);
				}

				break;
			case 2:
				// 기본 알고리즘 - 수학
				for (int i = 1; i < category[1].length; i++) {
					System.out.println(i + ") " + category[1][i]);
				}

				break;
			case 3:
				// 응용 알고리즘 - 배열
				for (int i = 1; i < category[2].length; i++) {
					System.out.println(i + ") " + category[2][i]);
				}

				break;
			case 4:
				// 응용 알고리즘 - 자료 구조
				for (int i = 1; i < category[3].length; i++) {
					System.out.println(i + ") " + category[3][i]);
				}

				break;
			case 5:
				// 실무 응용 알고리즘
				for (int i = 1; i < category[4].length; i++) {
					System.out.println(i + ") " + category[4][i]);
				}

				break;
			case -1:
				System.out.println("종료.");
				break menu;
			default:
				System.out.println("다시 입력하시오.");
				break;
			}
		}
	}

	public static void main(String[] args) {
		Main m = new Main();

//		m.menuSelector();

		Code code = new Code();
		code.code01();
		code.code02();
		code.code03();
		code.code04();
	}
}
