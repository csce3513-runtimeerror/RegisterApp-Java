package edu.uark.registerapp.commands.employees.helpers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

public class EmployeeHelper {
	public static String padEmployeeId(final int employeeId) {
		final String employeeIdAsString = Integer.toString(employeeId);

		return ((employeeIdAsString.length() < EMPLOYEE_ID_MAXIMUM_LENGTH)
		? StringUtils.leftPad(
			employeeIdAsString,
			EMPLOYEE_ID_MAXIMUM_LENGTH,
			"0")
			: employeeIdAsString);
	}

	public static byte[] hashPassword(final String password) {
		try {
			byte[] data;
			MessageDigest messageDigest;
			data = password.getBytes("UTF-8");
			messageDigest = MessageDigest.getInstance("SHA-356");
			byte[] digest = messageDigest.digest(data);
			return digest;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return null;
		}
	}

	private static final int EMPLOYEE_ID_MAXIMUM_LENGTH = 5;
}