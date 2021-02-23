package br.com.bibliotech.controllers;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.domains.Loan;
import br.com.bibliotech.services.LoanService;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Path("loan")
public class LoanController extends UtilRest {
    @POST
    @Path("/create")
    @Consumes("application/*")
    public Response create(String loanJson) {
        try {
            Loan loan = new Gson().fromJson(loanJson, Loan.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            LoanService loanService = new LoanService(connection);
            boolean response = loanService.create(loan);

            String msg;
            
            if (response){
                msg = "Empréstimo cadastrado com sucesso!";
            }else {
                msg = "Erro ao cadastrar empréstimo.";
            }
            
            dbConnection.closeConnection();
            
            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar cadastrar o empréstimo! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/getBySearch")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@QueryParam("searchText") String searchText) {
        try {
            List<Loan> loanList = new ArrayList<Loan>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            LoanService loanService = new LoanService(connection);

            loanList = loanService.getBySearch(searchText);

            dbConnection.closeConnection();

            return this.buildResponse(loanList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os empréstimos! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/list")
    @Consumes("application/*")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listByUserId(@QueryParam("userId") String userId) {
        try {
            List<Loan> loanList = new ArrayList<Loan>();

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            LoanService loanService = new LoanService(connection);

            if (userId != null) {
                loanList = loanService.list(userId);
            } else {
                loanList = loanService.list();
            }

            dbConnection.closeConnection();

            return this.buildResponse(loanList);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar buscar os empréstimos! \n Erro: \n" + e.getMessage());
        }
    }

    @PUT
    @Path("/deleteAndDeliver")
    @Consumes("application/*")
    public Response deleteAndDeliver(String params){
        try{
            Loan loan = new Gson().fromJson(params, Loan.class);

            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            LoanService loanService = new LoanService(connection);

            boolean response = loanService.deleteAndDeliver(loan);

            String msg;

            if (response){
                msg = "Empréstimo resetado com sucesso!";
            }else {
                msg = "Erro ao resetar empréstimo.";
            }

            dbConnection.closeConnection();

            return this.buildResponse(msg);
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao tentar resetar o empréstimo! \n Erro: \n" + e.getMessage());
        }
    }
}
