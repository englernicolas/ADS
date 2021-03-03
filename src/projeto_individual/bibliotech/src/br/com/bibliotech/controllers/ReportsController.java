package br.com.bibliotech.controllers;

import br.com.bibliotech.database.DBConnection;
import br.com.bibliotech.services.ReportsService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;

@Path("report")
public class ReportsController extends UtilRest {
    @GET
    @Path("/mostBorrowedBooks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mostBorrowedBooks(
            @QueryParam("initialDate") String initialDate, @QueryParam("endDate") String endDate)
    {
        try {
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            ReportsService reportsService = new ReportsService(connection);

            reportsService.mostBorrowedBooks(initialDate, endDate);

            dbConnection.closeConnection();

            return this.buildResponse("Relatório gerado!");
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao gerar relatório! \n Erro: \n" + e.getMessage());
        }
    }

    @GET
    @Path("/studentsThatMostLent")
    @Produces(MediaType.APPLICATION_JSON)
    public Response studentsThatMostLent(
            @QueryParam("initialDate") String initialDate, @QueryParam("endDate") String endDate)
    {
        try {
            DBConnection dbConnection = new DBConnection();
            Connection connection = dbConnection.openConnection();

            ReportsService reportsService = new ReportsService(connection);

            reportsService.studentsThatMostLent(initialDate, endDate);

            dbConnection.closeConnection();

            return this.buildResponse("Relatório gerado!");
        } catch (Exception e) {
            e.printStackTrace();
            return this.buildErrorResponse("Ocorreu um erro ao gerar relatório! \n Erro: \n" + e.getMessage());
        }
    }
}