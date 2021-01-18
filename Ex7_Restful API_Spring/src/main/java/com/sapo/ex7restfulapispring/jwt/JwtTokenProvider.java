package com.sapo.ex7restfulapispring.jwt;

import com.sapo.ex7restfulapispring.entities.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class JwtTokenProvider {

    Logger logger = Logger.getLogger(String.valueOf(JwtTokenProvider.class));

    // Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
    private final String JWT_SECRET = "loginform";

    //Thời gian có hiệu lực của chuỗi jwt
    private final long JWT_EXPIRATION = 604800000L;

    // Tạo ra jwt từ thông tin user
    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        // Tạo chuỗi json web token từ id của user.
        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    // Lấy thông tin user từ jwt
    public Integer getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return Integer.parseInt(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException ex) {
            logger.log(logger.getLevel(),"Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.log(logger.getLevel(),"Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.log(logger.getLevel(),"Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.log(logger.getLevel(),"JWT claims string is empty.");
        }
        return false;
    }
}
