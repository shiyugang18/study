package com.syg.utils;

import com.syg.exception.BusinessException;
import io.jsonwebtoken.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.util.Date;

/**
 * tokan管理   后期改成 threadLocal
 */
public class TokenUtil {

    public static final String DefaultSchool = "default";

    public static final String HEADER_NAME = "Authorization";
    private static final ThreadLocal<Claims> claimsThreadLocal = new NamedThreadLocal("Request token");
    private static String key = "lszToken";


    /**
     * 获取Request
     */
    public static HttpServletRequest getRequest() {
        return getServletRequestAttributes().getRequest();
    }

    public static void notToken(String userId, Integer role) {
        String token = createToken(userId, userId, role.toString(), "notoken", new Date());
        claimsThreadLocal.set(getTokenClaims(token));
    }

    public static void remove() {
        claimsThreadLocal.remove();
    }

    /**
     * 获取Response
     */
    public static HttpServletResponse getResponse() {
        return getServletRequestAttributes().getResponse();
    }

    public static Key getSecretKey() {

        byte[] encodedKey = Base64Util.decryptBase64(key).getBytes();
        // 使用AES对称加密
        Key key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    public static ServletRequestAttributes getServletRequestAttributes() {
        ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return attrs;
    }

    public static String getCurrentSchoolId() {
        if (isSystem()) {
            return DefaultSchool;
        }
        Claims tokenClaims = getTokenClaims();

        if (tokenClaims == null) {
            throw BusinessException.tokenException();
        }
        return tokenClaims.getSubject();
    }

    public static String getCurrentRole() {
        Claims tokenClaims = getTokenClaims();

        if (tokenClaims == null) {
            throw BusinessException.tokenException();
        }
        return tokenClaims.getIssuer();
    }

    public static String getCurrentUserId() {
        Claims tokenClaims = getTokenClaims();

        if (tokenClaims == null) {
            throw BusinessException.tokenException();
        }
        return tokenClaims.getId();
    }

    public static boolean isSystem() {
        return "0".equals(getCurrentRole());
    }

    public static boolean isSchool() {
        return "1".equals(getCurrentRole());
    }

    public static boolean isTeacher() {
        return "2".equals(getCurrentRole());
    }

    public static boolean isStudent() {
        return "3".equals(getCurrentRole());
    }

    public static String getCurrentUserName() {
        Claims tokenClaims = getTokenClaims();
        if (tokenClaims == null) {
            return null;
        }
        return tokenClaims.getAudience();
    }

    public static Date getLoginTime() {
        Claims tokenClaims = getTokenClaims();
        if (tokenClaims == null) {
            throw BusinessException.tokenException();
        }
        return tokenClaims.getIssuedAt();
    }

    public static Claims getTokenClaims() {
        Claims claims = claimsThreadLocal.get();
        if (claims != null) {
            return claims;
        }

        HttpServletRequest request = getRequest();
        Claims tokenClaims = (Claims) request.getAttribute(HEADER_NAME);
        if (tokenClaims == null) {
            String auth = request.getHeader(HEADER_NAME);
            tokenClaims = getTokenClaims(auth);
            if (tokenClaims != null) {
                request.setAttribute(HEADER_NAME, tokenClaims);
            }
        }
        return tokenClaims;
    }

    private static Claims getTokenClaims(String auth) {
        if (StringUtils.isEmpty(auth)) {
            throw BusinessException.authException();
        }

        try {
            Key key = getSecretKey();
            Jws<Claims> jws = Jwts.parser().setSigningKey(key).parseClaimsJws(auth);
            Claims claims = jws.getBody();
            return claims;
        } catch (Exception e) {
            throw BusinessException.authException();
        }
    }

    public static String createToken(String userId, String name, String role, String schoolId, Date date) {
        JwtBuilder builder = Jwts.builder()
                .setId(userId)
                .setIssuer(role)
                .setAudience(name)
                .setSubject(schoolId)
                .setIssuedAt(date)
                .signWith(SignatureAlgorithm.HS256, getSecretKey());

        // 生成JWT
        String token = builder.compact();
        return token;
    }

    /**
     * 获取IP地址
     */
    public static String getIpAddress() {
        HttpServletRequest request = getRequest();
        String remoteAddr = request.getHeader("X-REAL-IP");
        if (StringUtils.isNotBlank(remoteAddr)) {
            return remoteAddr;
        }
        remoteAddr = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isNotBlank(remoteAddr)) {
            return remoteAddr;
        }
        return request.getRemoteAddr();
    }

    public static String getHeader(String headerName) {
        HttpServletRequest request = getRequest();
        return request.getHeader(headerName);
    }
}
