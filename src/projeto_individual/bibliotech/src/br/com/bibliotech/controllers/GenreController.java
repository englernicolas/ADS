package br.com.bibliotech.controllers;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.Genre;
import br.com.bibliotech.services.GenreService;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("genre")
public class GenreController extends UtilRest {
    @POST
    @Path("/create")
    @Consumes("application/*")
    public Response create(String params) {
        try {
            Genre genre = new Gson().fromJson(params, Genre.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            GenreService genreService = new GenreService(connection);
            boolean response = genreService.createGenre(genre);

            String msg;
            
            if (response){
                msg = "Gênero cadastrada com sucesso!";
            }else {
                msg = "Erro ao cadastrar gênero.";
            }
            
            dbConnection.closeConnection();
            
            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar cadastrar o gênero! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/list")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<Genre> genreList = new ArrayList<Genre>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            GenreService genreService = new GenreService(connection);

            genreList = genreService.list();

            dbConnection.closeConnection();

            return this.buildResponse(genreList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os gêneros! \n Erro: \n" + e.getMessage());
        }
    }
}
