package br.com.bibliotech.domains;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Loan implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String loanDate;
    private String deliveryDate;
    private int studentId;
    private String deletedReason;
    private List<Integer> booksIds = new ArrayList<Integer>();

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getLoanDate() {
        return loanDate;
    }
    public void setLoanDate(String loanDate) {
        this.loanDate = loanDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getStudentId() {
        return studentId;
    }
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getDeletedReason() {
        return deletedReason;
    }
    public void setDeletedReason(String deletedReason) {
        this.deletedReason = deletedReason;
    }

    public List<Integer> getBooksIds() {
        return booksIds;
    }
    public void setBooksIds(List booksIds) {
        this.booksIds = booksIds;
    }
}