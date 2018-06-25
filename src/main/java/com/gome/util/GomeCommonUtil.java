package com.gome.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.alibaba.dubbo.common.utils.StringUtils;

/**
 * <dt>公用工具类</dt>
 * @author yyzhang
 * @since 2018年2月2日18:28:02
 */
public class GomeCommonUtil {

	/**
	 * 参数空值校验
	 * 
	 * @param name
	 * @param value
	 */
	public static void checkValue(String name, String value) {
		if (StringUtils.isBlank(value)) {
			throw new IllegalArgumentException("参数 【" + name + "】 不能为空！");
		}
	}

	/**
	 * 对象空值校验
	 * 
	 * @param obj
	 */
	public static void checkObject(Object obj) {
		if (obj == null) {
			throw new IllegalArgumentException("参数不能为空！");
		}
	}

	/**
	 * 检验是否包含中文字符
	 * 
	 * @param c
	 * @return
	 */
	public static final boolean isChinese(char[] c) {
		boolean flag = false;
		for (char d : c) {
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(d);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
					|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
					|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 将File转为字节
	 * 
	 * @param file
	 * @return
	 */
	public static byte[] toFileByte(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}
}
