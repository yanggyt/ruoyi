package com.ruoyi.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

/**
 * jwt-token 加、解密工具类 Created by wubei on 16/12/11.
 */
public class JWTUtil {
    static final Key KEY = MacProvider.generateKey();

    public static final String AUTHORIZATION = "Authorization";

    public static String createJWT(String subject) {
        return JWTUtil.createJWT(subject, null);
    }

    public static String createJWT(String subject, Long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder =
                Jwts.builder().setIssuedAt(now).setSubject(subject).signWith(SignatureAlgorithm.HS256, AUTHORIZATION);
        if (ttlMillis != null) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    public static Claims parseJWT(String compactJws) {
        Jwts.parser().isSigned(compactJws);
        Claims claims = Jwts.parser().setSigningKey(AUTHORIZATION).parseClaimsJws(compactJws).getBody();
        return claims;
    }

    /*public static SysUserBean getSysUser(HttpServletRequest request, RedisTemplate redisTemplate,
                                         boolean flag) {
        String token = request.getHeader(AUTHORIZATION);

        // 取值attr里面予值
        if (StringUtils.isBlank(token)) {
            Object tToken = request.getAttribute(AUTHORIZATION);
            if (tToken != null) {
                token = tToken.toString();
            }
        }

        if (StringUtils.isBlank(token)) {
            if (flag) {
                throw new AuthorizationException();
            } else {
                return new SysUserBean();
            }
        }
        try {
            Claims claims = parseJWT(token);
            String hasKey = claims.getSubject();
            if (redisTemplate.hasKey(hasKey)) {
                String conpanyUserStr = (String)redisTemplate.opsForValue().get(hasKey);
                SysUserBean sysUseBean = JSONObject.parseObject(conpanyUserStr, SysUserBean.class);
                if (token.equals(sysUseBean.getToken())) {
                    return sysUseBean;
                } else {
                    throw new AuthorizationException();
                }
            } else {
                throw new AuthorizationException();
            }
        } catch (Exception e) {
            throw new AuthorizationException();
        }
    }*/
}
