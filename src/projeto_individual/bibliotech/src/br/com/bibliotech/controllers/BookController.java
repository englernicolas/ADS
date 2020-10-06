package br.com.bibliotech.controllers;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.Book;
import br.com.bibliotech.services.BookService;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("book")
public class BookController extends UtilRest {
    @POST
    @Path("/create")
    @Consumes("application/*")
    public Response create(String bookJson) {
        try {
            Book book = new Gson().fromJson(bookJson, Book.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            BookService bookService = new BookService(connection);
            boolean response = bookService.create(book);

            String msg;
            
            if (response){
                msg = "Livro cadastrado com sucesso!";
            }else {
                msg = "Erro ao cadastrar livro.";
            }
            
            dbConnection.closeConnection();
            
            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar cadastrar o livro! \n Erro: \n" + e.getMessage());
        }
    }

    @PUT
    @Path("/edit")
    @Consumes("application/*")
    public Response edit(String bookJson){
        try{
            Book book = new Gson().fromJson(bookJson, Book.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            BookService bookService = new BookService(connection);

            boolean response = bookService.edit(book);

            String msg;

            if (response){
                msg = "Livro editado com sucesso!";
            }else {
                msg = "Erro ao editar livro.";
            }

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar editar o livro! \n Erro: \n" + e.getMessage());
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

            BookService bookService = new BookService(connection);

            Book book = bookService.getById(id);

            dbConnection.closeConnection();

            return this.buildResponse(book);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os livros! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/getBySearch")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@QueryParam("searchText") String searchText) {
        try {
            List<Book> bookList = new ArrayList<Book>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            BookService bookService = new BookService(connection);

            bookList = bookService.getBySearch(searchText);

            dbConnection.closeConnection();

            return this.buildResponse(bookList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os livros! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/list")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<Book> bookList = new ArrayList<Book>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            BookService bookService = new BookService(connection);

            bookList = bookService.list();

            dbConnection.closeConnection();

            return this.buildResponse(bookList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os livros! \n Erro: \n" + e.getMessage());
        }
    }

    @PUT
    @Path("/delete")
    @Consumes("application/*")
    public Response delete(String params){
        try{
            Book book = new Gson().fromJson(params, Book.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            BookService bookService = new BookService(connection);

            boolean response = bookService.delete(book);

            String msg;

            if (response){
                msg = "Livro deletado com sucesso!";
            }else {
                msg = "Erro ao deletar livro.";
            }

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar editar o livro! \n Erro: \n" + e.getMessage());
        }
    }
}
