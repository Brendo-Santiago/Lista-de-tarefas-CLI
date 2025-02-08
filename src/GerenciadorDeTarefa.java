package src;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GerenciadorDeTarefa {
    private static final Path ARQUIVO_D = Path.of("./src/tarefas.json");
    private List<Tarefa> tarefas;

    public GerenciadorDeTarefa(){
        this.tarefas = carregarTarefa();
    }

    public List<Tarefa> carregarTarefa() {
        List<Tarefa> lista_salva = new ArrayList<>();
        if (!Files.exists(ARQUIVO_D)) {
            return new ArrayList<>();
        }
        try {
            String conteudoJson = Files.readString(ARQUIVO_D);
            String[] listaTarefa = conteudoJson.replace("[","").replace("]", "").split("},");
            for (String listaJson : listaTarefa) {
                if (!listaJson.endsWith("}")) {
                    listaJson = listaJson + "}";
                    lista_salva.add(Tarefa.deJson(listaJson));
                }else {
                    lista_salva.add(Tarefa.deJson(listaJson));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista_salva;
    }

    public void addTarefa(String descricao) {
        Tarefa nova_tarefa = new Tarefa(descricao);
        tarefas.add(nova_tarefa);
        System.out.printf("Tarefa criada com sucesso (ID:%d)\n", nova_tarefa.getId());
    }
    public void removerTarefa(String id) {
        Tarefa tarefa = acharTarefa(id).orElseThrow(()-> new IllegalArgumentException("A tarefa com ID " + id + "Não foi achado"));
        tarefas.remove(tarefa);
    }
    public void atualizarTarefa (String id, String descricao) {
        Tarefa tarefa = acharTarefa(id).orElseThrow(()-> new IllegalArgumentException("A tarefa com ID " + id + "Não foi achado"));
        tarefa.update(descricao);
    }

    public void salvadoTarefa() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int i = 0; i < tarefas.size(); i++) {
            sb.append(tarefas.get(i).paraJson());
            if (i < tarefas.size() - 1) {
                sb.append(",\n");
            }

        }
        sb.append("\n]");
        try (FileWriter escrever = new FileWriter(ARQUIVO_D.toFile())){
            escrever.write(sb.toString());
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Optional<Tarefa> acharTarefa(String id) {
        return tarefas.stream().filter((tarefa) -> tarefa.getId() == Integer.parseInt(id)).findFirst();
    }
}
