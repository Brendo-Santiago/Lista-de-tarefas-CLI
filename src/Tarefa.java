package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private static int ultimoId = 0;
    private int id;
    private String descricao;
    private LocalDateTime dataCricao;
    private LocalDateTime dataAtualizacao;

    private static final DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Tarefa(String descricao) {
        this.id = ++ultimoId;
        this.descricao = descricao;
        this.dataCricao = LocalDateTime.now();
        this.dataAtualizacao = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getDataCricao() {
        return dataCricao;
    }

    public void setDataCricao(LocalDateTime dataCricao) {
        this.dataCricao = dataCricao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }


    public void update(String descricao) {
        setDescricao(descricao);
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String paraJson(){
        return "{\"id\":\"" + id + "\", \"descricao\":\"" + descricao + "\", \"dataCricao\":\"" + dataCricao.format(formato) + "\", \"dataAtualizacao\":\"" + dataAtualizacao.format(formato) + "\"}";
    }

    public static Tarefa deJson(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] json1 = json.split(",");
        String id = json1[0].split(":")[1].strip();
        String descricao = json1[1].split(":")[1].strip();
        String dataCriacao = json1[2].split("[a-z]:")[1].strip();
        String dataAtualizacao = json1[3].split("[a-z]:")[1].strip();


        Tarefa tarefa = new Tarefa(descricao);
        tarefa.setId(Integer.parseInt(id));
        tarefa.setDataCricao(LocalDateTime.parse(dataCriacao, formato));
        tarefa.setDataAtualizacao(LocalDateTime.parse(dataAtualizacao, formato));
        if (Integer.parseInt(id) > ultimoId) {
            ultimoId = Integer.parseInt(id);
        }
        return tarefa;

    }
}
