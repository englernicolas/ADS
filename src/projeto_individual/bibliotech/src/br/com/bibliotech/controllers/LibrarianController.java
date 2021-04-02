package br.com.bibliotech.controllers;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.User;
import br.com.bibliotech.services.UserService;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("librarian")
public class LibrarianController extends UtilRest {
    @POST
    @Path("/create")
    @Consumes("application/*")
    public Response create(String librarian) {
        try {
            User user = new Gson().fromJson(librarian, User.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);
            boolean response = userService.createLibrarian(user);

            String msg;
            
            if (response){
                msg = "Bibliotecário(a) cadastrado com sucesso!";
            }else {
                msg = "Erro ao cadastrar bibliotecário.";
            }
            
            dbConnection.closeConnection();
            
            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar cadastrar o bibliotecário! \n Erro: \n" + e.getMessage());
        }
    }

    @PUT
    @Path("/edit")
    @Consumes("application/*")
    public Response edit(String librarian){
        try{
            User user = new Gson().fromJson(librarian, User.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);

            boolean response = userService.editLibrarian(user);

            String msg;

            if (response){
                msg = "Bibliotecário(a) editado com sucesso!";
            }else {
                msg = "Erro ao editar bibliotecário.";
            }

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar editar o bibliotecário! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/getById")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@QueryParam("id") int id) {
        try {
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);

            User librarian = userService.getLibrarianById(id);

            dbConnection.closeConnection();

            return this.buildResponse(librarian);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os bibliotecários! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/getBySearch")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@QueryParam("searchText") String searchText) {
        try {
            List<User> librarianList = new ArrayList<User>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);

            librarianList = userService.getLibrarianBySearch(searchText);

            dbConnection.closeConnection();

            return this.buildResponse(librarianList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os bibliotecários! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/list")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<User> librarianList = new ArrayList<User>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);

            librarianList = userService.listLibrarians();

            dbConnection.closeConnection();

            return this.buildResponse(librarianList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os bibliotecários! \n Erro: \n" + e.getMessage());
        }
    }

    @PUT
    @Path("/delete")
    @Consumes("application/*")
    public Response delete(String params){
        try{
            User user = new Gson().fromJson(params, User.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);

            boolean response = userService.deleteLibrarian(user);

            String msg;

            if (response){
                msg = "Bibliotecário(a) deletado com sucesso!";
            }else {
                msg = "Erro ao deletar bibliotecário.";
            }

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar editar o bibliotecário! \n Erro: \n" + e.getMessage());
        }
    }
}
