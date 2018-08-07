package com.desjf.dsjr.utils;

import android.annotation.SuppressLint;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint("DefaultLocale")
public class StringUtil {



	/**
	 * 字符串是否空白
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		int strLen;
		if(str != null && (strLen = str.length()) != 0) {
			for(int i = 0; i < strLen; ++i) {
				if(!Character.isWhitespace(str.charAt(i))) {
					return false;
				}
			}

			return true;
		} else {
			return true;
		}
	}

	public static boolean isEmpty(String string){
		if (string != null && string.trim().length() > 0) {
			return false;
		}
		return true;
	}
	
	public static String toMD5String(String string){
		
		char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };

		byte[] btInput = string.getBytes();
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			
			mdInst.update(btInput);

			byte[] md = mdInst.digest();

			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = md5String[byte0 >>> 4 & 0xf];
				str[k++] = md5String[byte0 & 0xf];
			}
			return new String(str);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

	}
	public static String genNewMsgCountString(int newMsgCount) {
		if (newMsgCount != 0) {
			if (newMsgCount > 99) {
				return "99+";
			} else {
				return "" + newMsgCount;
			}
		} else {
			return "";
		}
	}
	
	/**
	 * 过滤字符串的前后空格以及所有的回车换行符
	 * @param src
	 * @return
	 */
	public static String trim(String src){
		src = src.trim(); //先过滤掉前后的空格
		
		Pattern pattern = Pattern.compile("(\r\n|\r|\n|\n\r)");
		//正则表达式的匹配一定要是这样，否则单个替换\r|\n的时候会错误
		Matcher matcher = pattern.matcher(src);
		String dest = matcher.replaceAll(" "); //将回车或者换行符替换为空字符串
		
		return dest;
	}
	
	public static boolean verifyPhoneNumber(String phoneNumber){
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

		Matcher m = p.matcher(phoneNumber);

		return m.matches();
	}
	
    /**
     * 字符串转换成十六进制值
     * @param bin String 我们看到的要转换成十六进制的字符串
     * @return 
     */
    public static String bin2hex(String bin) {
        char[] digital = "0123456789ABCDEF".toCharArray();
        StringBuffer sb = new StringBuffer("");
        byte[] bs = bin.getBytes();
        int bit;
        for (int i = 0; i < bs.length; i++) {
            bit = (bs[i] & 0x0f0) >> 4;
            sb.append(digital[bit]);
            bit = bs[i] & 0x0f;
            sb.append(digital[bit]);
        }
        return sb.toString();
    }

    /**
     * 十六进制转换字符串
     * @param hex String 十六进制
     * @return String 转换后的字符串
     */
    public static String hex2bin(String hex) {
        String digital = "0123456789ABCDEF";
        char[] hex2char = hex.toCharArray();
        byte[] bytes = new byte[hex.length() / 2];
        int temp;
        for (int i = 0; i < bytes.length; i++) {
            temp = digital.indexOf(hex2char[2 * i]) * 16;
            temp += digital.indexOf(hex2char[2 * i + 1]);
            bytes[i] = (byte) (temp & 0xff);
        }
        return new String(bytes);
    }

    /** 
     * java字节码转字符串 
     * @param b 
     * @return 
     */
    @SuppressLint("DefaultLocale")
	public static String byte2hex(byte[] b) { //一个字节的数组，

        // 转成16进制字符串

        String hs = "";
        String tmp = "";
        for (int n = 0; n < b.length; n++) {
            //整数转成十六进制表示

            tmp = (Integer.toHexString(b[n] & 0XFF));
            if (tmp.length() == 1) {
                hs = hs + "0" + tmp;
            } else {
                hs = hs + tmp;
            }
        }
        tmp = null;
        return hs.toUpperCase(); //转成大写

    }

    /**
     * 字符串转java字节码
     * @param b
     * @return
     */
    public static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0) {
            throw new IllegalArgumentException("长度不是偶数");
        }
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            // 两位一组，表示一个字节,把这样表示的16进制字符串，还原成一个进制字节

            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        b = null;
        return b2;
    }
    
    public static int genMinusUniqueId(){
		long l = System.currentTimeMillis();
		String s = String.valueOf(l);
		int i = -Integer.parseInt(s.substring(4));
		
		return i;
    }

	/**
	 * 在字符串特定位置插入字符串
	 * @param src 原字符串
	 * @param dec 插入字符串
	 * @param position 插入位置
     * @return
     */
	public static String insertStringInParticularPosition(String src, String dec, int position){
		StringBuffer stringBuffer = new StringBuffer(src);

		return stringBuffer.insert(position, dec).toString();

	}
}
