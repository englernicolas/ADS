package br.com.bibliotech.auth;
import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import br.com.bibliotech.domains.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTCode {
    Base64Code base64 = new Base64Code();
    private static String SECRET_KEY = "oeRaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5";

    public String encode(User user, int tempo) {
        Instant now = Instant.now();

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setId(Integer.toString(user.getId()))
                .setSubject(user.getFirstName() + " " + user.getLastName())
                .setHeaderParam("user", user)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(tempo, ChronoUnit.HOURS)))
                .signWith(signatureAlgorithm, signingKey);

        return builder.compact();
    }

    public User decode(String token) throws JsonProcessingException {
        JwsHeader<?> header = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
                .parseClaimsJws(token).getHeader();

        ObjectMapper mapper = new ObjectMapper();
        String headerJson = mapper.writeValueAsString(header.get("user"));

        User user = new Gson().fromJson(headerJson, User.class);

        return user;
    }

    public Boolean valid(String tokenBase64, Connection conexao, Boolean findHash) throws SQLException {
        if (tokenBase64 == null) {
            return false;
        }

        Base64Code base64 = new Base64Code();
        String token = base64.decode(tokenBase64);

        MD5Code md5Code = new MD5Code();
        String hashToken = md5Code.encode(token);

        try {
            // Este processo retorna erro caso a signature key do token for inv�lida
            Claims body = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

            Date expiresAt = body.getExpiration();
            Date now = new Date();

            if (expiresAt.before(now)) {
                return false;
            }
        }
        catch(Exception e) {
            e.printStackTrace();

            return false;
        }

        String buscaToken = "SELECT * FROM tokens WHERE code = ?";

        PreparedStatement p = conexao.prepareStatement(buscaToken);

        if (findHash) {
            p.setString(1, hashToken);
        }
        else {
            p.setString(1, token);
        }

        ResultSet rs = p.executeQuery();

        if (rs.next()) {
            return true;
        }
        else {
            return false;
        }
    }
}