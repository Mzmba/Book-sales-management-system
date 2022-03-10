package util;

/**
 * 判断字符串是否为空
 *
 * @author admin
 *
 */
public class StringNull {
	/**
	 * 判断字符串是否为空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否非空
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
}
