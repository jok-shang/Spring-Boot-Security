package com.jwt.token.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
    /**
     * 获取系统版本信息
     * @param request
     * @return
     */
    public static HashMap<String,String> getRequestSystemInfo(HttpServletRequest request){
        HashMap<String, String> map = new HashMap<>();
        String userAgent = request.getHeader("User-Agent");
        if(userAgent.equals("")||userAgent.equals(null)){
            userAgent="";
        }
        if (userAgent.contains("Windows")) {

            return judgeBrowser(userAgent,"Windows");

        } else if (userAgent.contains("Mac OS X")) {
            if(userAgent.contains("iPhone")){
                return judgeBrowser(userAgent, "iPhone");
            }
            else if (userAgent.contains("iPad")) {
                return judgeBrowser(userAgent, "iPad");
            }else{
                return judgeBrowser(userAgent, "Mac");
            }
        }else if(userAgent.contains("Android")){
            return judgeBrowser(userAgent, "Android");
        }else if(userAgent.contains("Linux")){
            return judgeBrowser(userAgent, "Linux");
        }else if(userAgent.contains("FreeBSD")){
            return judgeBrowser(userAgent, "FreeBSD");
        }else if(userAgent.contains("Solaris")){
            return judgeBrowser(userAgent, "Solaris");
        }
        return judgeBrowser(userAgent, "其他");
    }

    private static HashMap<String, String> judgeBrowser(String userAgent, String platformType) {
        HashMap<String, String> map = new HashMap<>();
        if (userAgent.contains("Edge")) {
            map.put("Microsoft Edge", platformType);
            return map;
        } else if (userAgent.contains("QQBrowser")) {
            map.put("腾讯浏览器", platformType);
            return map;
        } else if (userAgent.contains("Chrome") && userAgent.contains("Safari")) {
            map.put("Chrome", platformType);
            return map;
        } else if (userAgent.contains("Firefox")) {
            map.put("Firefox", platformType);
            return map;
        } else if (userAgent.contains("360")) {//Internet Explorer 6
            map.put("360浏览器", platformType);
            return map;
        }else if (userAgent.contains("Opera")) {//Internet Explorer 6
            map.put("Opera", platformType);
            return map;
        } else if (userAgent.contains("Safari") && !userAgent.contains("Chrome")) {
            map.put("Safari", platformType);
            return map;
        } else if (userAgent.contains("MetaSr1.0")) {//Internet Explorer 6
            map.put("搜狗浏览器", platformType);
            return map;
        } else if (userAgent.contains("TencentTraveler")) {//Internet Explorer 6
            map.put("腾讯浏览器", platformType);
            return map;
        } else if (userAgent.contains("UC")) {//Internet Explorer 6
            map.put("UC浏览器", platformType);
            return map;
        } else {

            map.put("其他", platformType);
            return map;
        }
    }
}


