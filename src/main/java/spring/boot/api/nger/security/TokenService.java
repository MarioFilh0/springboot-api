package spring.boot.api.nger.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.boot.api.nger.model.User;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    // NOVO: Adicionar informações do departamento ao token
                    .withClaim("role", user.getRole().name()) // Já deve estar aqui ou similar
                    .withClaim("userId", user.getId())
                    .withClaim("departmentId", user.getDep() != null ? user.getDep().getId() : null)
                    .withClaim("departmentName", user.getDep() != null ? user.getDep().getName() : null)
                    .sign(algorithm);
            return token;
        }
        catch(JWTCreationException e) {
            throw new RuntimeException("Error while generating token", e);
        }
    }

    public String validateToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch(JWTVerificationException e) {
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00")); // Ajuste para fuso horário de Cuiabá, se necessário
    }
}