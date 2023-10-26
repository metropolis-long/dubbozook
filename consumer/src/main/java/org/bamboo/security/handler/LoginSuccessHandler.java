package org.bamboo.security.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import io.jsonwebtoken.Jwts;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bamboo.config.redis.RedisService;
import org.bamboo.pojo.User;
import org.bamboo.utils.JwtUtils;
import org.bamboo.utils.LoginResult;
import org.bamboo.utils.ResultCode;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 登录认证成功处理器类
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisService redisService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //设置响应的编码格式
        response.setContentType("application/json;charset=utf-8");
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        //获取当前登录用户信息
        User user = (User) authentication.getPrincipal();
        //生成token
        String token = jwtUtils.generateToken(user);
        //设置token的签名密钥及过期时间
        long expireTime = 0l;
//        Jwts.parser()
//                .setSigningKey(jwtUtils.getSecret())    //设置前面密钥
//                .parseClaimsJws(token.replace("jwt_", ""))
//                .getBody().getExpiration().getTime();   //设置过期时间
        //创建LoginResult登录结果对象
        LoginResult loginResult = new LoginResult(user.getId(), ResultCode.SUCCESS,token,expireTime);
        //将对象转换成JSON格式，并消除循环引用
        String result = JSON.toJSONString(loginResult, SerializerFeature.DisableCircularReferenceDetect);
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        //将token信息保存到redis中
        String tokenKey = "token_" + token;
        redisService.set(tokenKey,token,jwtUtils.getExpiration() / 1000);
    }
}
