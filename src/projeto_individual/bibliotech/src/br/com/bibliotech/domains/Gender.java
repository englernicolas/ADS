package br.com.bibliotech.domains;

import java.io.Serializable;

public class Gender implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}