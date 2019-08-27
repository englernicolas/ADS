package oop.src.ContaBancaria.src.contabancaria;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movimentacao {
    private int tipo;
    private float valor;
    private String data;


    Movimentacao(int tipo, float valor){
        setTipo(tipo);
        setValor(valor);
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("kk:mm:ss  MMM dd, yyyy");
        this.data = sdf.format(data.getTime());
    }

    Movimentacao(float valor){
        setValor(valor);
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getData() {
        return data;
    }

    public int getTipo() {
        return tipo;
    }

    public float getValor() {
        return valor;
    }

}
