package org.ideaccum.libs.commons.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 日付操作を行う際の支援的な操作メソッドを提供します。<br>
 * <p>
 * システム開発時に利用する日付操作で利用頻度の高い操作を提供します。<br>
 * </p>
 * 
 * @author Kitagawa<br>
 * 
 *<!--
 * 更新日		更新者			更新内容
 * 2012/07/26	Kitagawa		新規作成
 * 2018/05/02	Kitagawa		再構築(SourceForge.jpからGitHubへの移行に併せて全面改訂)
 * 2019/03/18	Kitagawa		和暦定義列挙型(JapaneseYearType)を追加
 * 2019/03/18	Kitagawa		和暦定義関連のアクセッサを追加
 *-->
 */
public class DateUtil {

	/**
	 * コンストラクタ<br>
	 */
	private DateUtil() {
		super();
	}

	/**
	 * 日付情報を指定書式にフォーマットした文字列として提供します。<br>
	 * nullの日付情報が指定された場合は、空文字列として返却します。<br>
	 * @param date 日付情報
	 * @param pattern 日付文字列書式
	 * @return フォーマットされた日付文字列
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * 日付文字列を指定書式に沿ってパース下日付情報を提供します。<br>
	 * 空の文字列が指定された場合は、nullの日付情報として返却します。<br>
	 * @param date 日付文字列
	 * @param pattern 日付文字列書式
	 * @return 日付情報
	 */
	public static Date parse(String date, String pattern) {
		if (StringUtil.isEmpty(date)) {
			return null;
		}
		try {
			return new SimpleDateFormat(pattern).parse(date);
		} catch (Throwable e) {
			throw new IllegalArgumentException(pattern, e);
		}
	}

	/**
	 * 日付情報に指定年数を加算した日付情報を提供します。<br>
	 * @param date 日付情報
	 * @param year 加算年数
	 * @return 指定年数を加算した日付情報
	 */
	public static Date addYear(Date date, int year) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	/**
	 * 日付情報に指定月数を加算した日付情報を提供します。<br>
	 * @param date 日付情報
	 * @param month 加算月数
	 * @return 指定月数を加算した日付情報
	 */
	public static Date addMonth(Date date, int month) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	/**
	 * 日付情報に指定日数を加算した日付情報を提供します。<br>
	 * @param date 日付オブジェクト
	 * @param day 加算日数
	 * @return 指定日数を加算した日付情報
	 */
	public static Date addDay(Date date, int day) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, day);
		return calendar.getTime();
	}

	/**
	 * 日付情報に指定時間を加算した日付情報を提供します。<br>
	 * @param date 日付オブジェクト
	 * @param hour 加算時間
	 * @return 指定時間を加算した日付情報
	 */
	public static Date addHour(Date date, int hour) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

	/**
	 * 日付情報に指定分算した日付情報を提供します。<br>
	 * @param date 日付オブジェクト
	 * @param minute 加算分
	 * @return 指定分を加算した日付情報
	 */
	public static Date addMinute(Date date, int minute) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, minute);
		return calendar.getTime();
	}

	/**
	 * 日付情報に指定秒算した日付情報を提供します。<br>
	 * @param date 日付オブジェクト
	 * @param second 加算秒
	 * @return 指定秒を加算した日付情報
	 */
	public static Date addSecond(Date date, int second) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.SECOND, second);
		return calendar.getTime();
	}

	/**
	 * 日付情報に指定ミリ秒算した日付情報を提供します。<br>
	 * @param date 日付オブジェクト
	 * @param millisecond 加算ミリ秒
	 * @return 指定ミリ秒を加算した日付情報
	 */
	public static Date addMillisecond(Date date, int millisecond) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.MILLISECOND, millisecond);
		return calendar.getTime();
	}

	/**
	 * 一日前の日付情報を取得します。<br>
	 * @param date 日付情報
	 * @return 一日前の日付情報
	 */
	public static Date yesterday(Date date) {
		return addDay(date, -1);
	}

	/**
	 * 一日先の日付情報を取得します。<br>
	 * @param date 日付情報
	 * @return 一日先の日付情報
	 */
	public static Date tommorrow(Date date) {
		return addDay(date, +1);
	}

	/**
	 * 日付情報に対応する和暦情報を取得します。<br>
	 * @param date 日付情報
	 * @return 日付情報に対応する和暦情報
	 */
	public static JapaneseYearType getJapaneseYearType(Date date) {
		return date == null ? null : JapaneseYearType.match(date);
	}

	/**
	 * 日付情報に対応する和暦年を取得します。<br>
	 * @param date 日付情報
	 * @return 日付情報に対応する和暦年
	 */
	public static int getJapaneseYear(Date date) {
		if (date == null) {
			return 0;
		}
		JapaneseYearType yearType = JapaneseYearType.match(date);
		return getYear(date) - yearType.getStartYear() + 1;
	}

	/**
	 * 日付情報から年を取得します。<br>
	 * @param date 日付情報
	 * @return 年
	 */
	public static int getYear(Date date) {
		return date == null ? 0 : Integer.parseInt(new SimpleDateFormat("yyyy").format(date));
	}

	/**
	 * 日付情報から月を取得します。<br>
	 * @param date 日付情報
	 * @return 月
	 */
	public static int getMonth(Date date) {
		return date == null ? 0 : Integer.parseInt(format(date, "MM"));
	}

	/**
	 * 日付情報から日を取得します。<br>
	 * @param date 日付情報
	 * @return 日
	 */
	public static int getDay(Date date) {
		return date == null ? 0 : Integer.parseInt(format(date, "dd"));
	}

	/**
	 * 日付情報から時間(24時間表記)を取得します。<br>
	 * @param date 日付情報
	 * @return 時間(24時間表記)
	 */
	public static int getHour(Date date) {
		return date == null ? 0 : Integer.parseInt(format(date, "HH"));
	}

	/**
	 * 日付情報から時間(12時間表記)を取得します。<br>
	 * @param date 日付情報
	 * @return 時間(12時間表記)
	 */
	public static int getHour12(Date date) {
		return date == null ? 0 : Integer.parseInt(format(date, "hh"));
	}

	/**
	 * 日付情報から分を取得します。<br>
	 * @param date 日付情報
	 * @return 分
	 */
	public static int getMinute(Date date) {
		return date == null ? 0 : Integer.parseInt(format(date, "mm"));
	}

	/**
	 * 日付情報から秒を取得します。<br>
	 * @param date 日付情報
	 * @return 秒
	 */
	public static int getSecond(Date date) {
		return date == null ? 0 : Integer.parseInt(format(date, "ss"));
	}

	/**
	 * 日付情報からミリ秒を取得します。<br>
	 * @param date 日付情報
	 * @return ミリ秒
	 */
	public static int getMillis(Date date) {
		return date == null ? 0 : Integer.parseInt(format(date, "SSS"));
	}

	/**
	 * 日付情報からナノ秒を取得します。<br>
	 * このメソッドは日付情報が{@link java.sql.Timestamp}の場合にのみ有効な値を返却します。<br>
	 * @param date 日付情報
	 * @return ナノ秒
	 */
	public static int getNanos(Date date) {
		if (!(date instanceof Timestamp)) {
			return 0;
		}
		Timestamp timestamp = (Timestamp) date;
		return timestamp.getNanos();
	}

	/**
	 * 日付情報から"yyyy"書式でフォーマットされた年文字列を取得します。<br>
	 * @param date 日付情報
	 * @return "yyyy"書式でフォーマットされた年文字列
	 */
	public static String getYYYY(Date date) {
		return format(date, "yyyy");
	}

	/**
	 * 日付情報から"MM"書式でフォーマットされた月文字列を取得します。<br>
	 * @param date 日付情報
	 * @return "MM"書式でフォーマットされた月文字列
	 */
	public static String getMM(Date date) {
		return format(date, "MM");
	}

	/**
	 * 日付情報から"dd"書式でフォーマットされた日文字列を取得します。<br>
	 * @param date 日付情報
	 * @return "dd"書式でフォーマットされた日文字列
	 */
	public static String getDD(Date date) {
		return format(date, "dd");
	}

	/**
	 * 特定年月の月末日を取得します。<br>
	 * @param year 年
	 * @param month 月
	 * @return 月末日
	 */
	public static int getLastDay(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1);
		return calendar.getActualMaximum(Calendar.DATE);
	}

	/**
	 * 特定年月の月末日付情報を取得します。<br>
	 * @param year 年
	 * @param month 月
	 * @return 月末日付情報
	 */
	public static Date getLastDate(int year, int month) {
		try {
			return new SimpleDateFormat("yyyy/M/d").parse(year + "/" + month + "/" + getLastDay(year, month));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 特定年月日の日付情報を取得します。<br>
	 * @param year 年
	 * @param month 月
	 * @param day 日
	 * @return 日付情報
	 */
	public static Date getDate(int year, int month, int day) {
		try {
			return new SimpleDateFormat("yyyy/M/d").parse(year + "/" + month + "/" + day);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 日付情報から曜日情報を取得します。<br>
	 * @param date 日付情報
	 * @return 曜日情報
	 */
	public static DayOfWeek getDayOfWeek(Date date) {
		if (date == null) {
			return null;
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int w = calendar.get(Calendar.DAY_OF_WEEK);
		return DayOfWeek.getDayOfWeek(w);
	}

	/**
	 * 日付情報から該当年における週番号を取得します。<br>
	 * @param date 日付情報
	 * @return 年における週番号
	 */
	public static int getWeekOfYear(Date date) {
		if (date == null) {
			return 0;
		}
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 時間情報を持つ日付情報と同一日付の0時0分0秒000ミリ秒の日付情報を取得します。<br>
	 * @param date 日付情報
	 * @return 指定された日付情報と同一日付の0時0分0秒000ミリ秒の日付情報
	 */
	public static Date getMinOfDate(Date date) {
		if (date == null) {
			return new Date(Long.MIN_VALUE);
		} else {
			String yyyyMMdd = format(date, "yyyyMMdd");
			return parse("yyyyMMddHHmmssSSS", yyyyMMdd + "000000000");
		}
	}

	/**
	 * 時間情報を持つ日付情報と同一日付の23時59分59秒999ミリ秒の日付情報を取得します。<br>
	 * @param date 日付情報
	 * @return 指定された日付情報と同一日付の23時59分59秒999ミリ秒の日付情報
	 */
	public static Date getMaxOfDate(Date date) {
		if (date == null) {
			return new Date(Long.MIN_VALUE);
		} else {
			String yyyyMMdd = format(date, "yyyyMMdd");
			return parse("yyyyMMddHHmmssSSS", yyyyMMdd + "235959999");
		}
	}

	/**
	 * 日付情報が月初日か判定します。<br>
	 * @param date 日付情報
	 * @return 月初日の場合にtrueを返却
	 */
	public static boolean isStartOfMonth(Date date) {
		if (date == null) {
			return false;
		}
		return getDay(date) == 1;
	}

	/**
	 * 日付情報が月末日か判定します。<br>
	 * @param date 日付情報
	 * @return 月末日の場合にtrueを返却
	 */
	public static boolean isEndOfMonth(Date date) {
		if (date == null) {
			return false;
		}
		return getDay(date) == getLastDay(getYear(date), getMonth(date));
	}

	/**
	 * 年がうるう年であるか判定します。<br>
	 * @param year 判定対象年
	 * @return うるう年であるである場合にtrueを返却
	 */
	public static boolean isLeapYear(int year) {
		return getLastDay(year, 2) == 29;
	}

	/**
	 * 日付情報の年がうるう年であるか判定します。<br>
	 * @param date 日付情報
	 * @return うるう年であるである場合にtrueを返却
	 */
	public static boolean isLeapYear(Date date) {
		if (date == null) {
			return false;
		}
		return isLeapYear(getYear(date));
	}

	/**
	 * 日付期間同士が重なり合うか判定します。<br>
	 * @param date1Start 比較元期間開始
	 * @param date1End 比較元期間終了
	 * @param date2Start 比較先期間開始
	 * @param date2End 比較先期間終了
	 * @return 2つの期間が重なりあう場合にtrueを返却
	 */
	public static boolean isOverlapPeriod(Date date1Start, Date date1End, Date date2Start, Date date2End) {
		long date1StartTime = date1Start == null ? Long.MIN_VALUE : date1Start.getTime();
		long date1EndTime = date1End == null ? Long.MAX_VALUE : date1End.getTime();
		long date2StartTime = date2Start == null ? Long.MIN_VALUE : date2Start.getTime();
		long date2EndTime = date2End == null ? Long.MAX_VALUE : date2End.getTime();
		return date1StartTime <= date2EndTime && date2StartTime <= date1EndTime;
	}

	/**
	 * 日付文字列が正しい日付日付であるか判定します。<br>
	 * ここでの判定は{@link #parse(String, String)}によるパースを前提としているため、空文字列でも妥当な文字列として判定します。<br>
	 * 但し、2000/2/31のようにカレンダーオブジェクトでは、2000/3/2に補完されるような日付データについては不正な文字列として結果を返却します。<br>
	 * @param date 日付文字列
	 * @param pattern 日付書式
	 * @return 正しい日付文字列である場合にtrueを返却
	 */
	public static boolean isValidDateString(String date, String pattern) {
		if (StringUtil.isEmpty(date)) {
			return true;
		}
		try {
			String revdate = new SimpleDateFormat(pattern).format(new SimpleDateFormat(pattern).parse(date));
			return date.equals(revdate);
		} catch (Throwable e) {
			return false;
		}
	}

	/**
	 * 曜日情報を列挙型にて提供します。<br>
	 * <p>
	 * このクラスは単純に曜日情報を列挙型で比較するためにのみ設置されました。<br>
	 * 内部的な曜日値は{@link java.util.Calendar}が提供する曜日定数で管理されます。<br>
	 * </p>
	 * 
	 * @author Kitagawa<br>
	 * 
	 *<!--
	 * 更新日		更新者			更新内容
	 * 2018/03/15	Kitagawa		新規作成
	 *-->
	 */
	public static enum DayOfWeek {

		/** 日曜 */
		SUNDAY(Calendar.SUNDAY), //

		/** 月曜 */
		MONDAY(Calendar.MONDAY), //

		/** 火曜 */
		TUESDAY(Calendar.TUESDAY), //

		/** 水曜 */
		WEDNESDAY(Calendar.WEDNESDAY), //

		/** 木曜 */
		THURSDAY(Calendar.THURSDAY), //

		/** 金曜 */
		FRIDAY(Calendar.FRIDAY), //

		/** 土曜 */
		SATURDAY(Calendar.SATURDAY), //

		;

		/** 曜日値 */
		private int value;

		/**
		 * コンストラクタ<br>
		 * @param value 曜日値
		 */
		private DayOfWeek(int value) {
			this.value = value;
		}

		/**
		 * {@link java.util.Calendar}の曜日番号(日曜1～土曜7)を取得します。<br>
		 * @return {@link java.util.Calendar}の曜日番号
		 */
		public int getValue() {
			return value;
		}

		/**
		 * 利用者が期待する曜日開始値を基準とした曜日番号(日曜n～土曜n+6)を取得します。<br>
		 * @param start 開始値
		 * @return 曜日番号
		 */
		public int getValue(int start) {
			return value - 1 + start;
		}

		/**
		 * {@link java.util.Calendar}の曜日値をもとに曜日情報を取得します。<br>
		 * @param value {@link java.util.Calendar}の曜日値
		 * @return 曜日情報
		 */
		private static DayOfWeek getDayOfWeek(int value) {
			for (DayOfWeek e : values()) {
				if (e.value == value) {
					return e;
				}
			}
			return null;
		}
	}

	/**
	 * 日本の和暦情報を列挙型で提供します。<br>
	 * <p>
	 * 和暦情報は該当和暦の開始日付情報のみを管理します。<br>
	 * 和暦に対応する年の算出などの業務的な処理は利用者側に委譲するものとし、ここでは提供しません。<br>
	 * </p>
	 * 
	 * @author Kitagawa<br>
	 * 
	 *<!--
	 * 更新日		更新者			更新内容
	 * 2019/03/18	Kitagawa		新規作成
	 *-->
	 */
	public enum JapaneseYearType {

		/** 大化(たいか) */
		TAIKA(645, 7, 17, "大化", "Taika"), //

		/** 白雉(はくち) */
		HAKUCHI(650, 3, 22, "白雉", "Hakuchi"), //

		/** 朱鳥(しゅちょう) */
		SHUCHO(686, 8, 14, "朱鳥", "Shucho"), //

		/** 大宝(たいほう) */
		TAIHO(701, 5, 3, "大宝", "Taiho"), //

		/** 慶雲(けいうん) */
		KEIUN(704, 6, 16, "慶雲", "Keiun"), //

		/** 和銅(わどう) */
		WADO(708, 2, 7, "和銅", "Wado"), //

		/** 霊亀(れいき) */
		REIKI(715, 10, 3, "霊亀", "Reiki"), //

		/** 養老(ようろう) */
		YORO(717, 12, 24, "養老", "Yoro"), //

		/** 神亀(じんき) */
		JINKI(724, 3, 3, "神亀", "Jinki"), //

		/** 天平(てんぴょう) */
		TEMPYO(729, 9, 2, "天平", "Tempyo"), //

		/** 天平感宝(てんぴょうかんぽう) */
		TEMPYOKAMPO(749, 5, 4, "天平感宝", "Tempyokampo"), //

		/** 天平勝宝(てんぴょうしょうほう) */
		TEMPYOSHOHO(749, 8, 19, "天平勝宝", "Tempyoshoho"), //

		/** 天平宝字(てんぴょうほうじ) */
		TEMPYOHOJI(757, 9, 6, "天平宝字", "Tempyohoji"), //

		/** 天平神護(てんぴょうじんご) */
		TEMPYOJINGO(765, 2, 1, "天平神護", "Tempyojingo"), //

		/** 神護景雲(じんごけいうん) */
		JINGOKEIUN(767, 9, 13, "神護景雲", "Jingokeiun"), //

		/** 宝亀(ほうき) */
		HOKI(770, 10, 23, "宝亀", "Hoki"), //

		/** 天応(てんおう) */
		TENO(781, 1, 30, "天応", "Teno"), //

		/** 延暦(えんりゃく) */
		ENRYAKU(782, 9, 30, "延暦", "Enryaku"), //

		/** 大同(だいどう) */
		DAIDO(806, 6, 8, "大同", "Daido"), //

		/** 弘仁(こうにん) */
		KONIN(810, 10, 20, "弘仁", "Konin"), //

		/** 天長(てんちょう) */
		TENCHO(824, 2, 8, "天長", "Tencho"), //

		/** 承和(じょうわ) */
		JOWA_834(834, 2, 14, "承和", "Jowa"), //

		/** 嘉祥(かしょう) */
		KASHO_848(848, 7, 16, "嘉祥", "Kasho"), //

		/** 仁寿(にんじゅ) */
		NINJU(851, 6, 1, "仁寿", "Ninju"), //

		/** 斉衡(さいこう) */
		SAIKO(854, 12, 23, "斉衡", "Saiko"), //

		/** 天安(てんあん) */
		TENAN(857, 3, 20, "天安", "Tenan"), //

		/** 貞観(じょうがん) */
		JOGAN(859, 5, 20, "貞観", "Jogan"), //

		/** 元慶(がんぎょう) */
		GANGYO(877, 6, 1, "元慶", "Gangyo"), //

		/** 仁和(にんな) */
		NINNA(885, 3, 11, "仁和", "Ninna"), //

		/** 寛平(かんぴょう) */
		KAMPYO(889, 5, 30, "寛平", "Kampyo"), //

		/** 昌泰(しょうたい) */
		SHOTAI(898, 5, 20, "昌泰", "Shotai"), //

		/** 延喜(えんぎ) */
		ENGI(901, 8, 31, "延喜", "Engi"), //

		/** 延長(えんちょう) */
		ENCHO(923, 5, 29, "延長", "Encho"), //

		/** 承平(じょうへい) */
		JOHEI(931, 5, 16, "承平", "Johei"), //

		/** 天慶(てんぎょう) */
		TENGYO(938, 6, 22, "天慶", "Tengyo"), //

		/** 天暦(てんりゃく) */
		TENRYAKU(947, 5, 15, "天暦", "Tenryaku"), //

		/** 天徳(てんとく) */
		TENTOKU(957, 11, 21, "天徳", "Tentoku"), //

		/** 応和(おうわ) */
		OWA(961, 3, 5, "応和", "Owa"), //

		/** 康保(こうほう) */
		KOHO(964, 8, 19, "康保", "Koho"), //

		/** 安和(あんな) */
		ANNA(968, 9, 8, "安和", "Anna"), //

		/** 天禄(てんろく) */
		TENROKU(970, 5, 3, "天禄", "Tenroku"), //

		/** 天延(てんえん) */
		TENEN(974, 1, 16, "天延", "Tenen"), //

		/** 貞元(じょうげん) */
		JOGEN_976(976, 8, 11, "貞元", "Jogen"), //

		/** 天元(てんげん) */
		TENGEN(978, 12, 31, "天元", "Tengen"), //

		/** 永観(えいかん) */
		EIKAN(983, 5, 29, "永観", "Eikan"), //

		/** 寛和(かんな) */
		KANNA(985, 5, 19, "寛和", "Kanna"), //

		/** 永延(えいえん) */
		EIEN(987, 5, 5, "永延", "Eien"), //

		/** 永祚(えいそ) */
		EISO(989, 9, 10, "永祚", "Eiso"), //

		/** 正暦(しょうりゃく) */
		SHORYAKU(990, 11, 26, "正暦", "Shoryaku"), //

		/** 長徳(ちょうとく) */
		CHOTOKU(995, 3, 25, "長徳", "Chotoku"), //

		/** 長保(ちょうほう) */
		CHOHO(999, 2, 1, "長保", "Choho"), //

		/** 寛弘(かんこう) */
		KANKO(1004, 8, 8, "寛弘", "Kanko"), //

		/** 長和(ちょうわ) */
		CHOWA(1013, 2, 8, "長和", "Chowa"), //

		/** 寛仁(かんにん) */
		KANNIN(1017, 5, 21, "寛仁", "Kannin"), //

		/** 治安(じあん) */
		JIAN(1021, 3, 17, "治安", "Jian"), //

		/** 万寿(まんじゅ) */
		MANJU(1024, 8, 19, "万寿", "Manju"), //

		/** 長元(ちょうげん) */
		CHOGEN(1028, 8, 18, "長元", "Chogen"), //

		/** 長暦(ちょうりゃく) */
		CHORYAKU(1037, 5, 9, "長暦", "Choryaku"), //

		/** 長久(ちょうきゅう) */
		CHOKYU(1040, 12, 16, "長久", "Chokyu"), //

		/** 寛徳(かんとく) */
		KANTOKU(1044, 12, 16, "寛徳", "Kantoku"), //

		/** 永承(えいしょう) */
		EISHO_1046(1046, 5, 22, "永承", "Eisho"), //

		/** 天喜(てんき) */
		TENKI(1053, 2, 2, "天喜", "Tenki"), //

		/** 康平(こうへい) */
		KOHEI(1058, 9, 19, "康平", "Kohei"), //

		/** 治暦(じりゃく) */
		JIRYAKU(1065, 9, 4, "治暦", "Jiryaku"), //

		/** 延久(えんきゅう) */
		ENKYU(1069, 5, 6, "延久", "Enkyu"), //

		/** 承保(じょうほう) */
		JOHO(1074, 9, 16, "承保", "Joho"), //

		/** 承暦(じょうりゃく) */
		JORYAKU(1077, 12, 5, "承暦", "Joryaku"), //

		/** 永保(えいほう) */
		EIHO(1081, 3, 22, "永保", "Eiho"), //

		/** 応徳(おうとく) */
		OTOKU(1084, 3, 15, "応徳", "Otoku"), //

		/** 寛治(かんじ) */
		KANJI(1087, 5, 11, "寛治", "Kanji"), //

		/** 嘉保(かほう) */
		KAHO(1095, 1, 23, "嘉保", "Kaho"), //

		/** 永長(えいちょう) */
		EICHO(1097, 1, 3, "永長", "Eicho"), //

		/** 承徳(じょうとく) */
		JOTOKU(1097, 12, 27, "承徳", "Jotoku"), //

		/** 康和(こうわ) */
		KOWA_1099(1099, 9, 15, "康和", "Kowa"), //

		/** 長治(ちょうじ) */
		CHOJI(1104, 3, 8, "長治", "Choji"), //

		/** 嘉承(かしょう) */
		KASHO(1106, 5, 13, "嘉承", "Kasho"), //

		/** 天仁(てんにん) */
		TENNIN(1108, 9, 9, "天仁", "Tennin"), //

		/** 天永(てんえい) */
		TENEI(1110, 7, 31, "天永", "Tenei"), //

		/** 永久(えいきゅう) */
		EIKYU(1113, 8, 25, "永久", "Eikyu"), //

		/** 元永(げんえい) */
		GENEI(1118, 4, 25, "元永", "Genei"), //

		/** 保安(ほうあん) */
		HOAN(1120, 5, 9, "保安", "Hoan"), //

		/** 天治(てんじ) */
		TENJI(1124, 5, 18, "天治", "Tenji"), //

		/** 大治(だいじ) */
		DAIJI(1126, 2, 15, "大治", "Daiji"), //

		/** 天承(てんしょう) */
		TENSHO_1131(1131, 2, 28, "天承", "Tensho"), //

		/** 長承(ちょうしょう) */
		CHOSHO(1132, 9, 21, "長承", "Chosho"), //

		/** 保延(ほうえん) */
		HOEN(1135, 6, 10, "保延", "Hoen"), //

		/** 永治(えいじ) */
		EIJI(1141, 8, 13, "永治", "Eiji"), //

		/** 康治(こうじ) */
		KOJI_1142(1142, 5, 25, "康治", "Koji"), //

		/** 天養(てんよう) */
		TENYO(1144, 3, 28, "天養", "Tenyo"), //

		/** 久安(きゅうあん) */
		KYUAN(1145, 8, 12, "久安", "Kyuan"), //

		/** 仁平(にんぺい) */
		NIMPEI(1151, 2, 14, "仁平", "Nimpei"), //

		/** 久寿(きゅうじゅ) */
		KYUJU(1154, 12, 4, "久寿", "Kyuju"), //

		/** 保元(ほうげん) */
		HOGEN(1156, 5, 18, "保元", "Hogen"), //

		/** 平治(へいじ) */
		HEIJI(1159, 5, 9, "平治", "Heiji"), //

		/** 永暦(えいりゃく) */
		EIRYAKU(1160, 2, 18, "永暦", "Eiryaku"), //

		/** 応保(おうほう) */
		OHO(1161, 9, 24, "応保", "Oho"), //

		/** 長寛(ちょうかん) */
		CHOKAN(1163, 5, 4, "長寛", "Chokan"), //

		/** 永万(えいまん) */
		EIMAN(1165, 7, 14, "永万", "Eiman"), //

		/** 仁安(にんあん) */
		NINAN(1166, 9, 23, "仁安", "Ninan"), //

		/** 嘉応(かおう) */
		KAO(1169, 5, 6, "嘉応", "Kao"), //

		/** 承安(しょうあん) */
		SHOAN_1171(1171, 5, 27, "承安", "Shoan"), //

		/** 安元(あんげん) */
		ANGEN(1175, 8, 16, "安元", "Angen"), //

		/** 治承(じしょう) */
		JISHO(1177, 8, 29, "治承", "Jisho"), //

		/** 養和(ようわ) */
		YOWA(1181, 8, 25, "養和", "Yowa"), //

		/** 寿永(じゅえい) */
		JUEI(1182, 6, 29, "寿永", "Juei"), //

		/** 元暦(げんりゃく) */
		GENRYAKU(1184, 5, 27, "元暦", "Genryaku"), //

		/** 文治(ぶんじ) */
		BUNJI(1185, 9, 9, "文治", "Bunji"), //

		/** 建久(けんきゅう) */
		KENKYU(1190, 5, 16, "建久", "Kenkyu"), //

		/** 正治(しょうじ) */
		SHOJI(1199, 5, 23, "正治", "Shoji"), //

		/** 建仁(けんにん) */
		KENNIN(1201, 3, 19, "建仁", "Kennin"), //

		/** 元久(げんきゅう) */
		GENKYU(1204, 3, 23, "元久", "Genkyu"), //

		/** 建永(けんえい) */
		KENEI(1206, 6, 5, "建永", "Kenei"), //

		/** 承元(じょうげん) */
		JOGEN(1207, 11, 16, "承元", "Jogen"), //

		/** 建暦(けんりゃく) */
		KENRYAKU(1211, 4, 23, "建暦", "Kenryaku"), //

		/** 建保(けんぽう) */
		KEMPO(1214, 1, 18, "建保", "Kempo"), //

		/** 承久(じょうきゅう) */
		JOKYU(1219, 5, 27, "承久", "Jokyu"), //

		/** 貞応(じょうおう) */
		JOO_1222(1222, 5, 25, "貞応", "Joo"), //

		/** 元仁(げんにん) */
		GENNIN(1224, 12, 31, "元仁", "Gennin"), //

		/** 嘉禄(かろく) */
		KAROKU(1225, 5, 28, "嘉禄", "Karoku"), //

		/** 安貞(あんてい) */
		ANTEI(1228, 1, 18, "安貞", "Antei"), //

		/** 寛喜(かんき) */
		KANKI(1229, 3, 31, "寛喜", "Kanki"), //

		/** 貞永(じょうえい) */
		JOEI(1232, 4, 23, "貞永", "Joei"), //

		/** 天福(てんぷく) */
		TEMPUKU(1233, 5, 25, "天福", "Tempuku"), //

		/** 文暦(ぶんりゃく) */
		BUNRYAKU(1234, 11, 27, "文暦", "Bunryaku"), //

		/** 嘉禎(かてい) */
		KATEI(1235, 11, 1, "嘉禎", "Katei"), //

		/** 暦仁(りゃくにん) */
		RYAKUNIN(1238, 12, 30, "暦仁", "Ryakunin"), //

		/** 延応(えんおう) */
		ENO(1239, 3, 13, "延応", "Eno"), //

		/** 仁治(にんじ) */
		NINJI(1240, 8, 5, "仁治", "Ninji"), //

		/** 寛元(かんげん) */
		KANGEN(1243, 3, 18, "寛元", "Kangen"), //

		/** 宝治(ほうじ) */
		HOJI(1247, 4, 5, "宝治", "Hoji"), //

		/** 建長(けんちょう) */
		KENCHO(1249, 5, 2, "建長", "Kencho"), //

		/** 康元(こうげん) */
		KOGEN(1256, 10, 24, "康元", "Kogen"), //

		/** 正嘉(しょうか) */
		SHOKA(1257, 3, 31, "正嘉", "Shoka"), //

		/** 正元(しょうげん) */
		SHOGEN(1259, 4, 20, "正元", "Shogen"), //

		/** 文応(ぶんおう) */
		BUNO(1260, 5, 24, "文応", "Buno"), //

		/** 弘長(こうちょう) */
		KOCHO(1261, 3, 22, "弘長", "Kocho"), //

		/** 文永(ぶんえい) */
		BUNEI(1264, 3, 27, "文永", "Bunei"), //

		/** 建治(けんじ) */
		KENJI(1275, 5, 22, "建治", "Kenji"), //

		/** 弘安(こうあん) */
		KOAN_1278(1278, 3, 23, "弘安", "Koan"), //

		/** 正応(しょうおう) */
		SHOO(1288, 5, 29, "正応", "Shoo"), //

		/** 永仁(えいにん) */
		EININ(1293, 9, 6, "永仁", "Einin"), //

		/** 正安(しょうあん) */
		SHOAN(1299, 5, 25, "正安", "Shoan"), //

		/** 乾元(けんげん) */
		KENGEN(1302, 12, 10, "乾元", "Kengen"), //

		/** 嘉元(かげん) */
		KAGEN(1303, 9, 16, "嘉元", "Kagen"), //

		/** 徳治(とくじ) */
		TOKUJI(1307, 1, 18, "徳治", "Tokuji"), //

		/** 延慶(えんきょう) */
		ENKYO_1308(1308, 11, 22, "延慶", "Enkyo"), //

		/** 応長(おうちょう) */
		OCHO(1311, 5, 17, "応長", "Ocho"), //

		/** 正和(しょうわ) */
		SHOWA_1312(1312, 4, 27, "正和", "Showa"), //

		/** 文保(ぶんぽう) */
		BUMPO(1317, 3, 16, "文保", "Bumpo"), //

		/** 元応(げんおう) */
		GENO(1319, 5, 18, "元応", "Geno"), //

		/** 元亨(げんこう) */
		GENKO_1321(1321, 3, 22, "元亨", "Genko"), //

		/** 正中(しょうちゅう) */
		SHOCHU(1324, 12, 25, "正中", "Shochu"), //

		/** 嘉暦(かりゃく) */
		KARYAKU(1326, 5, 28, "嘉暦", "Karyaku"), //

		/** 元徳(げんとく) */
		GENTOKU(1329, 9, 22, "元徳", "Gentoku"), //

		/** 元弘(げんこう) */
		GENKO(1331, 9, 11, "元弘", "Genko"), //

		/** 正慶(しょうきょう) */
		SHOKYO(1332, 5, 23, "正慶", "Shokyo"), //

		/** 建武(けんむ) */
		KEMMU(1334, 3, 5, "建武", "Kemmu"), //

		/** 延元(えんげん) */
		ENGEN(1336, 4, 11, "延元", "Engen"), //

		/** 興国(こうこく) */
		KOKOKU(1340, 5, 25, "興国", "Kokoku"), //

		/** 正平(しょうへい) */
		SHOHEI(1347, 1, 20, "正平", "Shohei"), //

		/** 建徳(けんとく) */
		KENTOKU(1370, 8, 16, "建徳", "Kentoku"), //

		/** 文中(ぶんちゅう) */
		BUNCHU(1372, 5, 1, "文中", "Bunchu"), //

		/** 天授(てんじゅ) */
		TENJU(1375, 6, 26, "天授", "Tenju"), //

		/** 弘和(こうわ) */
		KOWA(1381, 3, 6, "弘和", "Kowa"), //

		/** 元中(げんちゅう) */
		GENCHU(1384, 5, 18, "元中", "Genchu"), //

		/** 暦応(りゃくおう) */
		RYAKUO(1338, 10, 11, "暦応", "Ryakuo"), //

		/** 康永(こうえい) */
		KOEI(1342, 6, 1, "康永", "Koei"), //

		/** 貞和(じょうわ) */
		JOWA(1345, 11, 15, "貞和", "Jowa"), //

		/** 観応(かんのう) */
		KANNO(1350, 4, 4, "観応", "Kanno"), //

		/** 文和(ぶんな) */
		BUNNA(1352, 11, 4, "文和", "Bunna"), //

		/** 延文(えんぶん) */
		EMBUN(1356, 4, 29, "延文", "Embun"), //

		/** 康安(こうあん) */
		KOAN(1361, 5, 4, "康安", "Koan"), //

		/** 貞治(じょうじ) */
		JOJI(1362, 10, 11, "貞治", "Joji"), //

		/** 応安(おうあん) */
		OAN(1368, 3, 7, "応安", "Oan"), //

		/** 永和(えいわ) */
		EIWA(1375, 3, 29, "永和", "Eiwa"), //

		/** 康暦(こうりゃく) */
		KORYAKU(1379, 4, 9, "康暦", "Koryaku"), //

		/** 永徳(えいとく) */
		EITOKU(1381, 3, 20, "永徳", "Eitoku"), //

		/** 至徳(しとく) */
		SHITOKU(1384, 3, 19, "至徳", "Shitoku"), //

		/** 嘉慶(かきょう) */
		KAKYO(1387, 10, 5, "嘉慶", "Kakyo"), //

		/** 康応(こうおう) */
		KOO(1389, 3, 7, "康応", "Koo"), //

		/** 明徳(めいとく) */
		MEITOKU(1390, 4, 12, "明徳", "Meitoku"), //

		/** 応永(おうえい) */
		OEI(1394, 8, 2, "応永", "Oei"), //

		/** 正長(しょうちょう) */
		SHOCHO(1428, 6, 10, "正長", "Shocho"), //

		/** 永享(えいきょう) */
		EIKYO(1429, 10, 3, "永享", "Eikyo"), //

		/** 嘉吉(かきつ) */
		KAKITSU(1441, 3, 10, "嘉吉", "Kakitsu"), //

		/** 文安(ぶんあん) */
		BUNAN(1444, 2, 23, "文安", "Bunan"), //

		/** 宝徳(ほうとく) */
		HOTOKU(1449, 8, 16, "宝徳", "Hotoku"), //

		/** 享徳(きょうとく) */
		KYOTOKU(1452, 8, 10, "享徳", "Kyotoku"), //

		/** 康正(こうしょう) */
		KOSHO(1455, 9, 6, "康正", "Kosho"), //

		/** 長禄(ちょうろく) */
		CHOROKU(1457, 10, 16, "長禄", "Choroku"), //

		/** 寛正(かんしょう) */
		KANSHO(1461, 2, 1, "寛正", "Kansho"), //

		/** 文正(ぶんしょう) */
		BUNSHO(1466, 3, 14, "文正", "Bunsho"), //

		/** 応仁(おうにん) */
		ONIN(1467, 4, 9, "応仁", "Onin"), //

		/** 文明(ぶんめい) */
		BUMMEI(1469, 6, 8, "文明", "Bummei"), //

		/** 長享(ちょうきょう) */
		CHOKYO(1487, 8, 9, "長享", "Chokyo"), //

		/** 延徳(えんとく) */
		ENTOKU(1489, 9, 16, "延徳", "Entoku"), //

		/** 明応(めいおう) */
		MEIO(1492, 8, 12, "明応", "Meio"), //

		/** 文亀(ぶんき) */
		BUNKI(1501, 3, 18, "文亀", "Bunki"), //

		/** 永正(えいしょう) */
		EISHO(1504, 3, 16, "永正", "Eisho"), //

		/** 大永(だいえい) */
		DAIEI(1521, 9, 23, "大永", "Daiei"), //

		/** 享禄(きょうろく) */
		KYOROKU(1528, 9, 3, "享禄", "Kyoroku"), //

		/** 天文(てんぶん) */
		TEMBUN(1532, 8, 29, "天文", "Tembun"), //

		/** 弘治(こうじ) */
		KOJI(1555, 11, 7, "弘治", "Koji"), //

		/** 永禄(えいろく) */
		EIROKU(1558, 3, 18, "永禄", "Eiroku"), //

		/** 元亀(げんき) */
		GENKI(1570, 5, 27, "元亀", "Genki"), //

		/** 天正(てんしょう) */
		TENSHO(1573, 8, 25, "天正", "Tensho"), //

		/** 文禄(ぶんろく) */
		BUNROKU(1593, 1, 10, "文禄", "Bunroku"), //

		/** 慶長(けいちょう) */
		KEICHO(1596, 12, 16, "慶長", "Keicho"), //

		/** 元和(げんな) */
		GENNA(1615, 9, 5, "元和", "Genna"), //

		/** 寛永(かんえい) */
		KANEI(1624, 4, 17, "寛永", "Kanei"), //

		/** 正保(しょうほう) */
		SHOHO(1645, 1, 13, "正保", "Shoho"), //

		/** 慶安(けいあん) */
		KEIAN(1648, 4, 7, "慶安", "Keian"), //

		/** 承応(じょうおう) */
		JOO(1652, 10, 20, "承応", "Joo"), //

		/** 明暦(めいれき) */
		MEIREKI(1655, 5, 18, "明暦", "Meireki"), //

		/** 万治(まんじ) */
		MANJI(1658, 8, 21, "万治", "Manji"), //

		/** 寛文(かんぶん) */
		KAMBUN(1661, 5, 23, "寛文", "Kambun"), //

		/** 延宝(えんぽう) */
		EMPO(1673, 10, 30, "延宝", "Empo"), //

		/** 天和(てんな) */
		TENNA(1681, 11, 9, "天和", "Tenna"), //

		/** 貞享(じょうきょう) */
		JOKYO(1684, 4, 5, "貞享", "Jokyo"), //

		/** 元禄(げんろく) */
		GENROKU(1688, 10, 23, "元禄", "Genroku"), //

		/** 宝永(ほうえい) */
		HOEI(1704, 4, 16, "宝永", "Hoei"), //

		/** 正徳(しょうとく) */
		SHOTOKU(1711, 6, 11, "正徳", "Shotoku"), //

		/** 享保(きょうほう) */
		KYOHO(1716, 8, 9, "享保", "Kyoho"), //

		/** 元文(げんぶん) */
		GEMBUN(1736, 6, 7, "元文", "Gembun"), //

		/** 寛保(かんぽう) */
		KAMPO(1741, 4, 12, "寛保", "Kampo"), //

		/** 延享(えんきょう) */
		ENKYO(1744, 4, 3, "延享", "Enkyo"), //

		/** 寛延(かんえん) */
		KANEN(1748, 8, 5, "寛延", "Kanen"), //

		/** 宝暦(ほうれき) */
		HOREKI(1751, 12, 14, "宝暦", "Horeki"), //

		/** 明和(めいわ) */
		MEIWA(1764, 6, 30, "明和", "Meiwa"), //

		/** 安永(あんえい) */
		ANEI(1772, 12, 10, "安永", "Anei"), //

		/** 天明(てんめい) */
		TEMMEI(1781, 4, 25, "天明", "Temmei"), //

		/** 寛政(かんせい) */
		KANSEI(1789, 2, 19, "寛政", "Kansei"), //

		/** 享和(きょうわ) */
		KYOWA(1801, 3, 19, "享和", "Kyowa"), //

		/** 文化(ぶんか) */
		BUNKA(1804, 3, 22, "文化", "Bunka"), //

		/** 文政(ぶんせい) */
		BUNSEI(1818, 5, 26, "文政", "Bunsei"), //

		/** 天保(てんぽう) */
		TEMPO(1831, 1, 23, "天保", "Tempo"), //

		/** 弘化(こうか) */
		KOKA(1845, 1, 9, "弘化", "Koka"), //

		/** 嘉永(かえい) */
		KAEI(1848, 4, 1, "嘉永", "Kaei"), //

		/** 安政(あんせい) */
		ANSEI(1855, 1, 15, "安政", "Ansei"), //

		/** 万延(まんえん) */
		MANEN(1860, 4, 8, "万延", "Manen"), //

		/** 文久(ぶんきゅう) */
		BUNKYU(1861, 3, 29, "文久", "Bunkyu"), //

		/** 元治(げんじ) */
		GENJI(1864, 3, 27, "元治", "Genji"), //

		/** 慶応(けいおう) */
		KEIO(1865, 5, 1, "慶応", "Keio"), //

		/** 明治 */
		MEIJI(1868, 1, 25, "明治", "Meiji"), //

		/** 大正 */
		TAISHO(1912, 7, 30, "大正", "Taisho"), //

		/** 昭和 */
		SHOWA(1926, 12, 25, "昭和", "Showa"), //

		/** 平成 */
		HEISEI(1989, 1, 8, "平成", "Heisei"), //

		;

		/** 始期(年) */
		private int startYear;

		/** 始期(月) */
		private int startMonth;

		/** 始期(日) */
		private int startDay;

		/** 名称 */
		private String name;

		/** ローマ字表記 */
		private String roman;

		/** 始期(1970/1/1 00:00:00 GMTを基準にした経過時間) */
		private long startTime;

		/**
		 * コンストラクタ<br>
		 * @param startYear 始期(年)
		 * @param startMonth 始期(月)
		 * @param startDay 始期(日)
		 * @param name 名称
		 * @param roman ローマ字表記
		 */
		private JapaneseYearType(int startYear, int startMonth, int startDay, String name, String roman) {
			this.startYear = startYear;
			this.startMonth = startMonth;
			this.startDay = startDay;
			this.name = name;
			this.roman = roman;
			this.startTime = parse(startYear + "/" + startMonth + "/" + startDay, "y/M/d").getTime();
		}

		/**
		 * 始期(年)を取得します。<br>
		 * @return 始期(年)
		 */
		public int getStartYear() {
			return startYear;
		}

		/**
		 * 始期(月)を取得します。<br>
		 * @return 始期(月)
		 */
		public int getStartMonth() {
			return startMonth;
		}

		/**
		 * 始期(日)を取得します。<br>
		 * @return 始期(日)
		 */
		public int getStartDay() {
			return startDay;
		}

		/**
		 * 名称を取得します。<br>
		 * @return 名称
		 */
		public String getName() {
			return name;
		}

		/**
		 * ローマ字表記を取得します。<br>
		 * @return ローマ字表記
		 */
		public String getRoman() {
			return roman;
		}

		/**
		 * 始期(1970/1/1 00:00:00 GMTを基準にした経過時間)を取得します。<br>
		 * @return 始期(1970/1/1 00:00:00 GMTを基準にした経過時間)
		 */
		public long getStartTime() {
			return startTime;
		}

		/**
		 * 経過時刻(1970/1/1基準)が合致する和暦情報を提供します。<br>
		 * @param time 経過時刻(1970/1/1基準)
		 * @return 合致する和暦情報
		 */
		public static JapaneseYearType match(long time) {
			List<JapaneseYearType> values = CollectionUtil.arrayList(values());
			Collections.sort(values, new Comparator<JapaneseYearType>() {
				@Override
				public int compare(JapaneseYearType o1, JapaneseYearType o2) {
					return o1.startTime > o2.startTime ? -1 : o1.startTime == o2.startTime ? 0 : 1;
				}
			});
			for (JapaneseYearType e : values) {
				if (e.startTime <= time) {
					return e;
				}
			}
			return null;
		}

		/**
		 * 日付が合致する和暦情報を提供します。<br>
		 * @param date 日付情報
		 * @return 合致する和暦情報
		 */
		public static JapaneseYearType match(Date date) {
			if (date == null) {
				return null;
			}
			return match(date.getTime());
		}
	}
}
