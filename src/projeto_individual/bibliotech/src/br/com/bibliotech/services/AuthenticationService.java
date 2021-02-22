package br.com.bibliotech.services;

import br.com.bibliotech.auth.Base64Code;
import br.com.bibliotech.auth.JWTCode;
import br.com.bibliotech.auth.MD5Code;
import br.com.bibliotech.domains.Token;
import br.com.bibliotech.domains.User;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthenticationService {
    private Connection connection;

    public AuthenticationService(Connection connection) {
        this.connection = connection;
    }

    Base64Code base64 = new Base64Code();
    MD5Code md5Code = new MD5Code();
    JWTCode jwtCode = new JWTCode();

    public User getUserInformations(String tokenBase64) throws JsonProcessingException {
        String token = base64.decode(tokenBase64);
        User user = jwtCode.decode(token);
        try {
            if (jwtCode.valid(tokenBase64, this.connection, true)) {

                String query = "SELECT u.id, u.email, u.first_name, u.last_name, u.user_type_id " +
                        "FROM user u " +
                        "INNER JOIN user_type t ON u.user_type_id = t.id WHERE u.id = ?";

                PreparedStatement p = this.connection.prepareStatement(query);
                p.setInt(1, user.getId());

                ResultSet rs = p.executeQuery();
                if (rs.next()) {
                    String email = rs.getString("email");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    int userTypeId = rs.getInt("user_type_id");

                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setUserTypeId(userTypeId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    public Token login(User userParams) {
        Token token = new Token();
        String query = "SELECT u.id, u.active, u.email, u.first_name, u.last_name, u.active, u.user_type_id " +
                "FROM user u INNER JOIN user_type t ON u.user_type_id = t.id " +
                "WHERE email = ? AND password = ?";
        String deleteToken = "DELETE FROM tokens WHERE user_id = ?";
        String saveToken = "INSERT INTO tokens (code, user_id) VALUES (?, ?)";

        try {
            String password = base64.decode(userParams.getPassword());

            userParams.setPassword(md5Code.encode(password));

            PreparedStatement p = this.connection.prepareStatement(query);
            p.setString(1, userParams.getEmail());
            p.setString(2, userParams.getPassword());

            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                int userTypeId = rs.getInt("user_type_id");
                int active = rs.getInt("active");

                if (active == 1) {
                    User user = new User();
                    user.setId(id);
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setUserTypeId(userTypeId);

                    String code = jwtCode.encode(user, 24);
                    token.setCode(base64.encode(code));

                    p = this.connection.prepareStatement(deleteToken);
                    p.setInt(1, user.getId());
                    p.execute();

                    p = this.connection.prepareStatement(saveToken);
                    p.setString(1, md5Code.encode(code));
                    p.setInt(2, user.getId());
                    p.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public boolean logout(String tokenBase64) {
        String deleteToken = "DELETE FROM tokens WHERE code = ?";

        try {
            String token = base64.decode(tokenBase64);

            PreparedStatement p = this.connection.prepareStatement(deleteToken);
            p.setString(1, md5Code.encode(token));
            p.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
