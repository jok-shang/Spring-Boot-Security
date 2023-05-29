package com.jwt.token.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.token.domin.ResponseResult;
import jdk.nashorn.internal.ir.debug.JSONWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class WebUtils
{

    public static void renderString(HttpServletResponse response, String s) throws IOException {

        Map<Object, Object> map = new HashMap<>();
        map.put("code",401);
        map.put("message",s);
        String jsonMap = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(401);
        response.getWriter().println(jsonMap);
    }


}