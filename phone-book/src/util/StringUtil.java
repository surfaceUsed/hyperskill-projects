package util;

public class StringUtil {

    private StringUtil() {}

    public static String getContactNameInfo(String contactInfo) {
        return contactInfo.replaceAll("\\d+", "").trim();
    }

    public static String getContactNumberInfo(String contactInfo) {
        return contactInfo.replaceAll("[A-Za-z]+", "").trim();
    }
}
