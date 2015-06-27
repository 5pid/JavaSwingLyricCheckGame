package algorithm;

import java.util.ArrayList;

import category01.Code;

public class Main {
	public static void main(String[] args) {
		menu();

		Code code = new Code();
		code.code01();
		code.code02();

	}

	static void menu() {
		System.out.println("2014년 정보처리기사 실기 - 이기적");

		ArrayList<String> category01 = new ArrayList<>();
		category01.add("기본 수열(1부터 100까지 자연수)");
		category01.add("등차 수열");
		category01.add("등비 수열");
		category01.add("피보나치 수열");
		category01.add("누승 활용 수열");
		category01.add("제곱의 합");
		category01.add("'+,-' 교행 자연수 수열");
		category01.add("'+,-' 교행 분수 수열");

		ArrayList<String> category02 = new ArrayList<>();
		category02.add("Count 알고리즘");
		category02.add("최댓값과 최솟값");
		category02.add("합계와 평균");
		category02.add("소수 판별");
		category02.add("소인수 분해");
		category02.add("배수와 공배수");
		category02.add("약수와 완전수");
		category02.add("최대공약수와 최소공배수");
		category02.add("근사값");
		category02.add("10진수와 2진수의 변환");
		category02.add("10진수와 16진수의 변환");
		category02.add("BCD 코드와 3초과 코드의 변환");
		category02.add("1의 보수와 2의 보수");
		category02.add("패리티 비트 검증");

		ArrayList<String> category03 = new ArrayList<>();
		category03.add("행 우선/열 우선 배열 채우기");
		category03.add("삼각형 모양으로 배열 채우기");
		category03.add("모래시계 모양으로 배열 채우기");
		category03.add("달팽이 모양으로 배열 채우기");
		category03.add("마름모 모양으로 배열 채우기");
		category03.add("'ㄹ'자 모양으로 배열 채우기");
		category03.add("마방진 배열 채우기");
		category03.add("배열 회전시키기");
		category03.add("행렬 곱셈 계산하기");

		ArrayList<String> category04 = new ArrayList<>();
		category04.add("석차 구하기");
		category04.add("선택 정렬");
		category04.add("버블 정렬");
		category04.add("삽입 정렬");
		category04.add("병합 정렬");
		category04.add("퀵 정렬");
		category04.add("이분 검색");
		category04.add("최소비용 그래프");

		ArrayList<String> category05 = new ArrayList<>();
		category05.add("통계 산출 알고리즘");
		category05.add("재고 관리 알고리즘");
		category05.add("급여 계산/지폐 매수 계산 알고리즘");
		category05.add("요일 계산 알고리즘");
		category05.add("은행이자 계산 알고리즘");

		String[] category = new String[5];
		System.out.println("기본 알고리즘 - 수열");
		System.out.println("기본 알고리즘 - 수학");
		System.out.println("응용 알고리즘 - 배열");
		System.out.println("응용 알고리즘 - 자료 구조");
		System.out.println("실무 응용 알고리즘");
	}
}
