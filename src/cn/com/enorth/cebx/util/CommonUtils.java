package cn.com.enorth.cebx.util;

import javax.servlet.http.HttpServletRequest;
import javax.util.zz.StringUtil;

import cn.com.enorth.cebx.consts.SystemConst;

public class CommonUtils {
	public static String getIp(HttpServletRequest request) {
		String realip = request.getHeader("X-Real-IP");
		if (StringUtil.isEmpty(realip)) {
			realip = request.getHeader("X-Forwarded-For");
			if (StringUtil.isEmpty(realip)) {
				return request.getRemoteAddr();
			} else {
				return realip;
			}
		} else {
			return realip;
		}
	}
	
	private static boolean isValidFile(String filename, String[] extNames){
		if(StringUtil.isEmpty(filename)){
			return false;
		}
		if(extNames == null || extNames.length == 0){
			return false;
		}
		for(String ext : extNames){
			if(filename.toLowerCase().endsWith(ext.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 返回是否是可以上传的文件
	 * @param filename 文件名
	 * @return
	 */
	public static boolean isValidInfoFile(String filename) {
		return isValidFile(filename, StringUtil.splitStr(SystemConst.FILE_TYPE, " "));
	}
}
