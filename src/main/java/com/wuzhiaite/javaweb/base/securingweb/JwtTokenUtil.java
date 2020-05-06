package com.wuzhiaite.javaweb.base.securingweb;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;

/**
 * @author lpf
 */
@Slf4j
public class JwtTokenUtil {
    // 寻找证书文件
    private static InputStream inputStream =
            Thread.currentThread().getContextClassLoader().getResourceAsStream("jwt.jks");
    private static PrivateKey privateKey = null;
    private static PublicKey publicKey = null;
    /**有效期24小时 */
    private static final long EXPIRE_TIME = 60 * 60 * 24000;

    private static final String BASE64SECRET = "abcdefgh";


    static { // 将证书文件里边的私钥公钥拿出来
        try {
            // java key store 固定常量
            KeyStore keyStore = KeyStore.getInstance("JKS");
            keyStore.load(inputStream, "123456".toCharArray());
            // jwt 为 命令生成整数文件时的别名
            privateKey = (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray());
            publicKey = keyStore.getCertificate("jwt").getPublicKey();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateToken(String subject) {
        //使用HmacSHA256签名算法生成一个HS256的签名秘钥Key
        Key signKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(BASE64SECRET),SignatureAlgorithm.HS256.getJcaName());

        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, signKey)
                .compact();
    }

    public static String parseToken(String token) {
        String subject = null;
        Key signKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(BASE64SECRET),SignatureAlgorithm.HS256.getJcaName());
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(signKey)
                    .parseClaimsJws(token).getBody();
            subject = claims.getSubject();
        } catch (Exception e) {
        }
        return subject;
    }


    public static void main(String[] args) throws InterruptedException {
        String root = generateToken("123");
        log.info(root);
        String s = parseToken(root);
        log.info(s);
    }

}

