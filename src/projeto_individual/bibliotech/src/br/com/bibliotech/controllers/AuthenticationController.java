package br.com.bibliotech.controllers;

import java.sql.Connection;

import javax.json.Json;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.Token;
import br.com.bibliotech.domains.User;
import br.com.bibliotech.dtos.UserPasswordDTO;
import br.com.bibliotech.services.AuthenticationService;
import com.google.gson.Gson;

@Path("/auth")
public class AuthenticationController extends UtilRest {
    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInformations(@HeaderParam("Authorization") String token) {
        try {
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            AuthenticationService authenticationService = new AuthenticationService(connection);
            User user = authenticationService.getUserInformations(token);

            dbConnection.closeConnection();

            return this.buildResponse(user);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao buscar dados do usuário! \n Erro: \n" + e.getMessage());
        }
    }

    @POST
    @Path("/login")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(String userParam) {
        try {
            User user = new Gson().fromJson(userParam, User.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            AuthenticationService authenticationService = new AuthenticationService(connection);
            Token token = authenticationService.login(user);

            if (token.getToken() == null) {
                throw new Exception();
            }
            dbConnection.closeConnection();

            return this.buildResponse(token);

        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao logar! \n Erro: \n" + e.getMessage());
        }
    }

    @DELETE
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout(@HeaderParam("Authorization") String token) {
        try {
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            AuthenticationService authenticationService = new AuthenticationService(connection);
            Boolean response = authenticationService.logout(token);

            String msg;

            if (response){
                msg = "Deslogado com sucesso!";
            }else {
                msg = "Erro ao deslogar.";
            }

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao deslogar! \n Erro: \n" + e.getMessage());
        }
    }

    @POST
    @Path("/recoverPassword")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response recoverPassword(String userParam) {
        try {
            User user = new Gson().fromJson(userParam, User.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            AuthenticationService authenticationService = new AuthenticationService(connection);
            String msg = authenticationService.recoverPassword(user);

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao logar! \n Erro: \n" + e.getMessage());
        }
    }

    @POST
    @Path("/resetPassword")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPassword(String userParam, @QueryParam("code") String tokenId) {
        try {
            UserPasswordDTO user = new Gson().fromJson(userParam, UserPasswordDTO.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            AuthenticationService authenticationService = new AuthenticationService(connection);
            String msg = authenticationService.resetPassword(user, tokenId);

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao alterar senha! \n Erro: \n" + e.getMessage());
        }
    }
}
