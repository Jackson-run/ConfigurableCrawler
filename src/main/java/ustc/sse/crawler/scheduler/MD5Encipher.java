package ustc.sse.crawler.scheduler;

import java.security.MessageDigest;

/**
 * MD5加密器
 *
 * @author wangrun
 * @version 0.1
 */
public class MD5Encipher {

    /**
     * MD5加密
     *
     * @param s
     * @return MD5加密字符串
     */

    public String MD5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            return toHex(bytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 16进制转换
     *
     * @param bytes
     * @return 16进制转换后的字符串
     */
    public String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }
}
