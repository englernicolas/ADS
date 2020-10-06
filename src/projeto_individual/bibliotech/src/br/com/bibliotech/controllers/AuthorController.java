package br.com.bibliotech.controllers;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.Author;
import br.com.bibliotech.services.AuthorService;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("author")
public class AuthorController extends UtilRest {
    @POST
    @Path("/create")
    @Consumes("application/*")
    public Response create(String params) {
        try {
            Author author = new Gson().fromJson(params, Author.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            AuthorService authorService = new AuthorService(connection);
            boolean response = authorService.createAuthor(author);

            String msg;
            
            if (response){
                msg = "Autor cadastrada com sucesso!";
            }else {
                msg = "Erro ao cadastrar autor.";
            }
            
            dbConnection.closeConnection();
            
            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar cadastrar o autor! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/list")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<Author> authorList = new ArrayList<Author>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            AuthorService authorService = new AuthorService(connection);

            authorList = authorService.list();

            dbConnection.closeConnection();

            return this.buildResponse(authorList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os autores! \n Erro: \n" + e.getMessage());
        }
    }
}
