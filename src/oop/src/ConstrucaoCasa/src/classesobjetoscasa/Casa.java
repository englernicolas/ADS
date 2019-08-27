package oop.src.ConstrucaoCasa.src.classesobjetoscasa;

import java.util.List;
public class Casa {
    private String descricao;
    private String cor;
    private List<Porta> listaDePortas;
    private List<Janela> listaDeJanelas;

    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCor() {
        return this.cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public List<Porta> getListaDePortas() {
        return this.listaDePortas;
    }
    public void setListaDePortas(List<Porta> listaDePortas) {
        this.listaDePortas = listaDePortas;
    }

    public List<Janela> getListaDeJanelas() {
        return this.listaDeJanelas;
    }
    public void setListaDeJanelas(List<Janela> listaDeJanelas) {
        this.listaDeJanelas = listaDeJanelas;
    }

    public void construirCasa(String descricao, String cor, List<Janela> listaDeJanelas, List<Porta> listaDePortas){
        setDescricao(descricao);
        setCor(cor);
        setListaDeJanelas(listaDeJanelas);
        setListaDePortas(listaDePortas);
    }

    public void pintarCasa(String cor){
        setCor(cor);
    }

    public void movimentarPorta(int index, int status) {
        Porta porta = listaDePortas.get(index);
        porta.setStatus(status);
        listaDePortas.set(index, porta);
    }

    public void movimentarJanela(int index, int status) {
        Janela janela = listaDeJanelas.get(index);
        janela.setStatus(status);
        listaDeJanelas.set(index, janela);
    }
}
