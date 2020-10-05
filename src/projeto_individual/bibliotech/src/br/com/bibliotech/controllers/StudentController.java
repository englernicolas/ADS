package br.com.bibliotech.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.User;
import br.com.bibliotech.services.UserService;
import com.google.gson.Gson;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("student")
public class StudentController extends UtilRest {
    @POST
    @Path("/create")
    @Consumes("application/*")
    public Response create(String student) {
        try {
            User user = new Gson().fromJson(student, User.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);
            boolean response = userService.createStudent(user);

            String msg;
            
            if (response){
                msg = "Estudante cadastrado com sucesso!";
            }else {
                msg = "Erro ao cadastrar estudante.";
            }
            
            dbConnection.closeConnection();
            
            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar cadastrar o estudante! \n Erro: \n" + e.getMessage());
        }
    }

    @PUT
    @Path("/edit")
    @Consumes("application/*")
    public Response edit(String student){
        try{
            User user = new Gson().fromJson(student, User.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);

            boolean response = userService.editStudent(user);

            String msg;

            if (response){
                msg = "Estudante editado com sucesso!";
            }else {
                msg = "Erro ao editar estudante.";
            }

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar editar o estudante! \n Erro: \n" + e.getMessage());
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

            User student = userService.getStudentById(id);

            dbConnection.closeConnection();

            return this.buildResponse(student);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os estudantes! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/list")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<User> studentList = new ArrayList<User>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            UserService userService = new UserService(connection);

            studentList = userService.listStudents();

            dbConnection.closeConnection();

            return this.buildResponse(studentList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os estudantes! \n Erro: \n" + e.getMessage());
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

            boolean response = userService.deleteStudent(user);

            String msg;

            if (response){
                msg = "Estudante deletado com sucesso!";
            }else {
                msg = "Erro ao deletar estudante.";
            }

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar editar o estudante! \n Erro: \n" + e.getMessage());
        }
    }
}
