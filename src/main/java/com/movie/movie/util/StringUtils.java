package com.movie.movie.util;

/**
 * 문자열 관련 Util Class
 */
public class StringUtils {

	/**
	 * 문자열이 null 이거나 비어있는지 확인
	 * @param str 체크 문자열
	 * @return
	 */
	public static boolean isEmptyOrNull(String str) {
		return str == null || str.trim().isEmpty();
	}

	/**
	 * 문자열을 뒤집기
	 * @param str 문자열
	 * @return
	 */
	public static String reverse(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}

		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * 첫 글자 대문자로 변환
	 * @param str 문자열
	 * @return
	 */
	public static String capitalize(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}

		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}

	/**
	 * 공백이나 밑줄이 있는 문자열을 CamelCase 로 변환
	 * @param str 문자열
	 * @return
	 */
	public static String toCamelCase(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}

		String[] words = str.split("[ _]+");
		StringBuilder stringBuilder = new StringBuilder(words[0].toLowerCase());

		for (int i = 1; i < words.length; i++) {
			stringBuilder.append(capitalize(words[i]));
		}

		return stringBuilder.toString();
	}

	/**
	 * 특정 문자 등장 횟수 계산
	 * @param str	문자열
	 * @param target 등장하는지 확인 할 문자열
	 * @return
	 */
	public static int countOccurrences(String str, char target) {
		if (isEmptyOrNull(str)) {
			return 0;
		}

		int count = 0;
		for (char ch : str.toCharArray()) {
			if (ch == target) {
				count++;
			}
		}

		return count;
	}

	/**
	 * 문자열 모든 공백 제거
	 * @param str 문자열
	 * @return
	 */
	public static String removeWhitespace(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}

		return str.replaceAll("\\s", "");
	}

	/**
	 * 특정 접두사로 시작하는지 확인
	 * @param str 문자열
	 * @param prefix 확인할 접두어
	 * @return
	 */
	public static boolean startWithIgnoreCase(String str, String prefix) {
		if (isEmptyOrNull(str) || isEmptyOrNull(prefix)) {
			return false;
		}

		return str.toLowerCase().startsWith(prefix.toLowerCase());
	}

	/**
	 * 특정 접미사로 끝나는지 확인
	 * @param str 문자열
	 * @param suffix 확인할 접미사
	 * @return
	 */
	public static boolean endWithIgnoreCase(String str, String suffix) {
		if (isEmptyOrNull(str) || isEmptyOrNull(suffix)) {
			return false;
		}

		return str.toLowerCase().endsWith(suffix.toLowerCase());
	}

	/**
	 * 문자열을 지정한 길이만큼 자르기 (초과시 ... 추가)
	 * @param str 문자열
	 * @param maxLength 지정할 길이
	 * @return
	 */
	public static String truncate(String str, int maxLength) {
		if (isEmptyOrNull(str) || maxLength < 0 ) {
			return str;
		}

		return (str.length() > maxLength) ? str.substring(0, maxLength) + "..." : str;
	}

	/**
	 * 문자열에 모든 숫자 제거
	 * @param str 문자열
	 * @return
	 */
	public static String removeNumber(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}

		return str.replaceAll("\\d", "");
	}

	/**
	 * 문자열
	 * @param str
	 * @return
	 */
	public static String removeSpecialCharacters(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}

		return str.replaceAll("[^a-zA-Z0-9]", "_");
	}

	/**
	 * 문자열을 Snake-Case 로 변환
	 * @param str 문자열
	 * @return
	 */
	public static String toSnakeCase(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}

		return str.replaceAll("\\s+", "_").toLowerCase();
	}

	/**
	 * 문자열을 kebab-case 로 변경
	 * @param str
	 * @return
	 */
	public static String toKebabCase(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}

		return str.replaceAll("\\s+", "-").toLowerCase();
	}

	/**
	 * 문자열을 반복하여 새로운 문자열 생성
	 * @param str
	 * @param times
	 * @return
	 */
	public static String repeat(String str, int times) {
		if (isEmptyOrNull(str) || times <= 0 ) {
			return str;
		}

		StringBuilder stringBuilder = new StringBuilder();

		for(int i = 0; i < times; i++) {
			stringBuilder.append(str);
		}

//		return str.repeat(times);
		return stringBuilder.toString();
	}

	public static String replace(String str, String target, String replacement) {
		if (isEmptyOrNull(str) || isEmptyOrNull(target) || isEmptyOrNull(replacement)) {
			return str;
		}

		return str.replace(target, replacement);
	}

	/**
	 * 문자열을 특정 길이로 패딩 (앞쪽에 추가)
	 * @param str 문자열
	 * @param length 길이
	 * @param padChar 채울 문자열
	 * @return
	 */
	public static String padLeft(String str, int length, char padChar) {
		if (isEmptyOrNull(str)) {
			return null;
		}

		return String.format("%1$" + length + "s", str).replace(' ', padChar);
	}

	/**
	 * 문자열을 특정 길이로 패딩 (뒤쪽에 추가)
	 * @param str 문자열
	 * @param length 길이
	 * @param padChar 채울 문자열
	 * @return
	 */
	public static String padRight(String str, int length, char padChar) {
		if (isEmptyOrNull(str)) {
			return null;
		}

		return String.format("%-" + length + "s", str).replace(' ', padChar);
	}

	/**
	 * 문자열이 숫자로만 이루어졌는지 확인
	 * @param str 문자열
	 * @return
	 */
	public static boolean isNumberic(String str) {
		if (isEmptyOrNull(str)) {
			return false;
		}
		
		return str.matches("\\d+");
	}

	/**
	 * 문자열을 대문자로 반환
	 * @param str 문자열
	 * @return
	 */
	public static String toUpperCase(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}
		
		return str.toUpperCase();
	}

	/**
	 * 문자열을 소문자로 반환
	 * @param str 문자열
	 * @return
	 */
	public static String toLowerCase(String str) {
		if (isEmptyOrNull(str)) {
			return str;
		}
		
		return str.toLowerCase();
	}

	/**
	 * 문자열을 반으로 나누어 배열로 반환
	 * @param str 문자열
	 * @return
	 */
	public static String[] splitInHalf(String str) {
		if (isEmptyOrNull(str)) {
			return new String[]{"", ""};
		}
		
		int mid = str.length() / 2;
		return new String[] {str.substring(0, mid), str.substring(mid)};
	}

	/**
	 * 문자열을 index 로 나누어 배열로 반환
	 * @param str 문자열
	 * @param index 인덱스
	 * @return
	 */
	public static String[] splitInNumber(String str, int index) {
		if (isEmptyOrNull(str)) {
			return new String[] {"", ""};
		}
		
		return new String[] { str.substring(0, index), str.substring(index) } ;
	}
	
	
//	/**
//	 * 문자열을 좌우 반대로 정렬 (좌우 공백 유지)
//	 * @param str
//	 * @return
//	 */
//	public static String alignReverse(String str) {
//		if (isEmptyOrNull(str)) {
//			return str;
//		}
//
//		return new StringBuilder(str.stripLeading()).reverse().toString();
//	}
}
