package org.bamboo.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bamboo.utils.Result;
import org.bamboo.utils.ResultCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 匿名用户访问无权限资源时处理器
 */
@Component
public class AnonymousAuthenticationHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        //设置响应的编码格式
        response.setContentType("application/json;charset=utf-8");
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //将结果转换成JSON格式
        String result = JSON.toJSONString(Result.error().code(ResultCode.NO_LOGIN).message("匿名用户无权限访问！"), SerializerFeature.DisableCircularReferenceDetect);
        //将结果保存到输出中写出
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
    }
}
