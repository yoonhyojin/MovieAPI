package com.movie.movie.util;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

/**
 * 날짜 관련 Util Class
 */
public class DateUtils {

	private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * 현재 날짜 및 시간 가져오기
	 * @return
	 */
	public static String getCureentDateTime() {
		return LocalDateTime.now().format(DEFAULT_FORMATTER);
	}

	/**
	 * 특정 포맷으로 현재 날짜 및 시간 가져오기
	 * @param format 날짜 format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		return LocalDateTime.now().format(getDateTimeFormatter(format));
	}

	/**
	 * 문자열을 LocalDateTime 으로 변환
	 * @param dateTime 문자열 시간
	 * @param format 날짜 format
	 * @return
	 */
	public static LocalDateTime parseStringToLocalDateTime(String dateTime, String format) {
		return LocalDateTime.parse(dateTime, getDateTimeFormatter(format));
	}

	/**
	 * LocalDateTime 을 문자열로 변환
	 * @param localDateTime LocalDateTime
	 * @param format 날짜 format
	 * @return
	 */
	public static String formatLocalDateTime(LocalDateTime localDateTime, String format) {
		return localDateTime.format(getDateTimeFormatter(format));
	}

	/**
	 * Date -> LocalDateTime 으로 변환
	 * @param date Date
	 * @return
	 */
	public static LocalDateTime convertDateToLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	/**
	 * LocalDateTime -> Date 으로 변환
	 * @param localDateTime LocalDateTime
	 * @return
	 */
	public static Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 날짜 더하기
	 * @param localDateTime LocalDateTime
	 * @param days 날짜
	 * @return
	 */
	public static LocalDateTime addDays(LocalDateTime localDateTime, long days) {
		return localDateTime.plusDays(days);
	}

	/**
	 * 날짜 빼기
	 * @param localDateTime LocalDateTime
	 * @param days 날짜
	 * @return
	 */
	public static LocalDateTime subtractDays(LocalDateTime localDateTime, long days) {
		return localDateTime.minusDays(days);
	}

	/**
	 * 현재 년도 가져오기
	 * @return
	 */
	public static int getCurrentYear() {
		return LocalDateTime.now().getYear();
	}

	/**
	 * 현재 월 가져오기
	 * @return
	 */
	public static int getCurrentMonth() {
		return LocalDateTime.now().getMonthValue();
	}

	/**
	 * 현재 일 가져오기
	 * @return
	 */
	public static int getCurrentDays() {
		return LocalDateTime.now().getDayOfMonth();
	}

	/**
	 * 두 날짜 간의 차이 계산 (일  단위)
	 * @param startDate 시작일
	 * @param endDate 종료일
	 * @return
	 */
	public static long getDayBetween(LocalDate startDate, LocalDate endDate) {
		return Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
	}

	/**
	 * 현재 날짜 가져오기 (yyyy-MM-dd 형식)
	 * @return
	 */
	public static String getCurrentDate() {
		return LocalDate.now().toString();
	}

	/**
	 * 현재 시간을 가져오기 (HH:mm:ss 형식)
	 * @return
	 */
	public static String getCurrentTime() {
		return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
	}

	/**
	 * 주어진 날짜가 윤년인지 확인
	 * @param year 년도
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return Year.isLeap(year);
	}

	/**
	 * 특정 날짜가 주말인지 확인
	 * @param date LocalDate
	 * @return
	 */
	public static boolean isWeekend(LocalDate date) {
		DayOfWeek dayOfWeek = date.getDayOfWeek();
		return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
	}

	/**
	 * 특정 날자의 요일 가져오기
	 * @param localDate LocalDate
	 * @param locale Locale
	 * @return
	 */
	public static String getDayofWeek(LocalDate localDate, Locale locale) {
		return localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale);
	}

	/**
	 * 특정 날짜의 말일 가져오기
	 * @param year 년도
	 * @param month 월
	 * @return
	 */
	public static LocalDate getLastDayOfMonth(int year, int month) {
		return YearMonth.of(year, month).atEndOfMonth();
	}

	/**
	 * 특정 날자의 첫날 가져오기
	 * @param year 년도
	 * @param month 월
	 * @return
	 */
	public static LocalDate getFirstDayOfMonth(int year, int month) {
		return YearMonth.of(year, month).atDay(1);
	}


	// private

	/**
	 * 해당 pattern 의 DateTimeFormatter 를 리턴해준다.
	 * @param pattern 날짜 pattern
	 * @return
	 */
	private static DateTimeFormatter getDateTimeFormatter(String pattern) {
		return DateTimeFormatter.ofPattern(pattern);
	}

}
