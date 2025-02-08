package src;

public class Main {
    public static void main(String[] args) {
        GerenciadorDeTarefa gerenciadorTarefa = new GerenciadorDeTarefa();
        if (args.length < 2) {
            System.out.println("Uso: java Main.java <comando> [argumento]");
        }
        String comando = args[0];

        switch (comando) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Uso: java Main.java add <descrição>");
                    return;
                }
                gerenciadorTarefa.addTarefa(args[1]);
            break;
            case "remove":
                if (args.length < 2) {
                    System.out.println("Uso: java Main.java remove <ID>");
                }

                gerenciadorTarefa.removerTarefa(args[1]);
            break;
            case "update":
                if (args.length < 3) {
                    System.out.println("Uso: java Main.java udpate <id> <descrição");
                }
                gerenciadorTarefa.atualizarTarefa(args[1], args[2]);
                System.out.printf("Tarefa %s foi atualizada com sucesso\n", args[1]);
                break;
            default:
                System.out.println("Comando Invalido");
        }
        gerenciadorTarefa.salvadoTarefa();
    }
}