package customer.leave_management.Utilis;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JWT {
     private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() +EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }
     // Extract username
    public String extractUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    //Validate token
    public boolean validateToken(String token, String username) {
        String extracted = extractUsername(token);
        return extracted.equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Date exp = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return exp.before(new Date());
    }
}
