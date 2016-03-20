package com.ewsd.cmr.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DateUtils {

	public static final String SHORT_SWISS_DATE_PATTERN = "dd.MM.yyyy";

	public static final String ISO_DATE_PATTERN = "yyyy-MM-dd";

	public static final String LONG_SWISS_DATE_PATTERN = "dd.MM.yyyy HH:mm:ss";

	private static final SimpleDateFormat SWISS_DATE_FORMAT = new SimpleDateFormat(SHORT_SWISS_DATE_PATTERN);

	private static final SimpleDateFormat LONG_SWISS_DATE_FORMAT = new SimpleDateFormat(LONG_SWISS_DATE_PATTERN);

	private static final SimpleDateFormat ISO_DATE_FORMAT = new SimpleDateFormat(ISO_DATE_PATTERN);

	private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

	public static final String DATETIME_MINUTE_PATTERN = "dd.MM.yyyy HH:mm";

	/**
	 * Checks if the date is valid with defined the pattern.
	 *
	 * @param strDate
	 *            the date to check valid or not
	 * @param pattern
	 *            the pattern of the date
	 * @return <code>true</code> if the date is valid, <code>false</code>
	 *         otherwise.
	 */
	public static boolean isValidDate(final String strDate, final String pattern) {
		if (StringUtils.isEmpty(strDate) || StringUtils.isEmpty(pattern)) {
			return false;
		}
		try {
			final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			dateFormat.setLenient(false);
			dateFormat.parse(strDate);
			return true;
		} catch (final ParseException e) {
			return false;
		}
	}

	/**
	 * Converts a string date as pattern dd.MM.yyyy value to {@link Date}
	 * instance.
	 *
	 * @param strDate
	 *            a string date as pattern dd.MM.yyyy
	 * @return a {@link Date} instance.
	 */
	public static Date toDate(final String strDate) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		try {
			return SWISS_DATE_FORMAT.parse(strDate);
		} catch (final ParseException e) {
			LOG.error("Cannot parse date as pattern " + SHORT_SWISS_DATE_PATTERN, e);
		}
		return null;
	}

	/**
	 * Converts a string date as pattern dd.MM.yyyy HH:mm:ss value to
	 * {@link Date} instance.
	 *
	 * @param strDate
	 *            a string date as pattern dd.MM.yyyy HH:mm:ss
	 * @return a {@link Date} instance.
	 */
	public static Date toLongDate(final String strDate) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		try {
			return LONG_SWISS_DATE_FORMAT.parse(strDate);
		} catch (final ParseException e) {
			LOG.error("Cannot parse date as pattern " + LONG_SWISS_DATE_FORMAT, e);
		}
		return null;
	}

	/**
	 * Converts the {@link Date} to string date as pattern <code>pattern</code>.
	 *
	 * @param date
	 *            the date to convert.
	 * @param pattern
	 *            the pattern to convert the date to.
	 * @return the string date.
	 */
	public static String toDateString(final Date date, final String pattern) {
		if (date == null) {
			return StringUtils.EMPTY;
		}
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * Converts a string date as pattern yyyy-MM-dd value to {@link Date}
	 * instance.
	 *
	 * @param strDate
	 *            a string date as pattern yyyy-MM-dd
	 * @return a {@link Date} instance.
	 */
	public static Date toIsoDate(final String strDate) {
		if (StringUtils.isBlank(strDate)) {
			return null;
		}
		try {
			return ISO_DATE_FORMAT.parse(strDate);
		} catch (final ParseException e) {
			LOG.error("Cannot parse date as pattern " + ISO_DATE_PATTERN, e);
		}
		return null;
	}

	/**
	 * Converts a {@link java.util.Date} to a {@link java.time.LocalDateTime}
	 * instance.
	 *
	 * @param date
	 *            a date
	 * @return a {@link java.time.LocalDateTime} date instance. For example,
	 *         2016-01-28T14:20:36
	 */
	public static LocalDateTime toLocalDateTime(final Date date) {
		if (date == null) {
			return null;
		}
		final String startDateStr = DateUtils.toDateString(date, DateUtils.LONG_SWISS_DATE_PATTERN);
		return LocalDateTime.parse(startDateStr, DateTimeFormatter.ofPattern(DateUtils.LONG_SWISS_DATE_PATTERN));
	}

	private DateUtils() {
		// prevent instantiation
	}

	/**
	 * Converts a <code>string</code> date to a date instance as pre-defined
	 * <code>pattern</code>.
	 * 
	 * @param strDate
	 *            the <code>string</code> date to convert.
	 * @param pattern
	 *            the pattern to convert.
	 * @return the {@link Date}
	 */
	public static Date toDateAsPattern(String strDate, String pattern) {
		if (StringUtils.isEmpty(strDate)) {
			return null;
		}
		try {
			return new SimpleDateFormat(pattern).parse(strDate);
		} catch (ParseException e) {
			LOG.error("cannot parse the date {} as pattern {}", strDate, pattern);
			return null;
		}
	}
}
