package com.jwt.token.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 尚智江
 * @CreateDate 2023/5/28 21:19
 */
public class SystemUtil {

    /**
     * 获取来访者的浏览器版本
     * @param request
     * @return
     */
    public static String getRequestBrowserInfo(HttpServletRequest request){
        String browserVersion = null;
        String header = request.getHeader("user-agent");
        if(header == null || header.equals("")){
            return "";
        }
        if(header.indexOf("MSIE")>0){
            browserVersion = "IE";
        }else if(header.indexOf("Firefox")>0){
            browserVersion = "Firefox";
        }else if(header.indexOf("Chrome")>0){
            browserVersion = "Chrome";
        }else if(header.indexOf("Safari")>0){
            browserVersion = "Safari";
        }else if(header.indexOf("Camino")>0){
            browserVersion = "Camino";
        }else if(header.indexOf("Konqueror")>0){
            browserVersion = "Konqueror";
        }
        return browserVersion;
    }

}
