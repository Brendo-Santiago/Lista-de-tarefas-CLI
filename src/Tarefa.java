package src;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarefa {
    private static int ultimoId = 0;
    private int id;
    private String descricao;
    Status status;
    private LocalDateTime dataCricao;
    private LocalDateTime dataAtualizacao;

    private static final DateTimeFormatter formato = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public Tarefa(String descricao) {
        this.id = ++ultimoId;
        this.descricao = descricao;
        this.status = Status.PENDENTE;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public void atualizarStatus (Status status){
        this.status = status;
        this.dataAtualizacao = LocalDateTime.now();
    }

    public String paraJson(){
        return "{\"id\":\"" + id + "\", \"descricao\":\"" + descricao + "\",\"status\":\"" + status + "\", \"dataCricao\":\"" + dataCricao.format(formato) + "\", \"dataAtualizacao\":\"" + dataAtualizacao.format(formato) + "\"}";
    }

    public static Tarefa deJson(String json) {
        json = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] json1 = json.split(",");
        String id = json1[0].split(":")[1].strip();
        String descricao = json1[1].split(":")[1].strip();
        String status = json1[2].split(":")[1].strip();
        String dataCriacao = json1[3].split("[a-z]:")[1].strip();
        String dataAtualizacao = json1[4].split("[a-z]:")[1].strip();


        Tarefa tarefa = new Tarefa(descricao);
        tarefa.setId(Integer.parseInt(id));
        tarefa.setStatus(Status.valueOf(status));
        tarefa.setDataCricao(LocalDateTime.parse(dataCriacao, formato));
        tarefa.setDataAtualizacao(LocalDateTime.parse(dataAtualizacao, formato));
        if (Integer.parseInt(id) > ultimoId) {
            ultimoId = Integer.parseInt(id);
        }
        return tarefa;

    }
}
