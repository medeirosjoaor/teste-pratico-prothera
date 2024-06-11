import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // 3.1
        List<Funcionario> funcionarios = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/employees.csv"));
            String line = "";

            while ((line = bufferedReader.readLine()) != null) {
                Funcionario funcionario = new Funcionario();
                String[] info = line.split(",");

                funcionario.setNome(info[0]);
                funcionario.setDataNascimento(LocalDate.of(
                        Integer.parseInt(info[1].substring(6, 10)),
                        Integer.parseInt(info[1].substring(3, 5)),
                        Integer.parseInt(info[1].substring(0, 2))
                ));
                funcionario.setSalario(new BigDecimal(info[2]));
                funcionario.setPosition(info[3]);

                funcionarios.add(funcionario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 3.2
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3
        System.out.println(funcionarios + "\n");

        // 3.4
        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.1))));

        //3.5
        HashMap<String, List<Funcionario>> funcoes = new HashMap<>();

        funcionarios.forEach(funcionario -> {
            String funcao = funcionario.getFuncao();

            if (!funcoes.containsKey(funcao)) {
                funcoes.put(funcao, new ArrayList<>());
            }

            funcoes.get(funcao).add(funcionario);
        });

        // 3.6
        System.out.println(funcoes + "\n");

        // 3.8
        List<Funcionario> aniversariantesOutubroOuDezembro = funcionarios.stream().filter(funcionario -> {
            String mes = funcionario.getDataNascimentoFormatada().substring(3, 5);

            return mes.equals("10") || mes.equals("12");
        }).collect(Collectors.toList());

        System.out.println(aniversariantesOutubroOuDezembro + "\n");

        // 3.9
        Funcionario maisVelho = funcionarios.stream().sorted((f1, f2) -> {
            int c = f1.getDataNascimentoFormatada().substring(6, 10).compareTo(f2.getDataNascimentoFormatada().substring(6, 10));

            if (c == 0) {
                c = f1.getDataNascimentoFormatada().substring(3, 5).compareTo(f2.getDataNascimentoFormatada().substring(3, 5));
            }

            if (c == 0) {
                c = f1.getDataNascimentoFormatada().substring(0, 2).compareTo(f2.getDataNascimentoFormatada().substring(0, 2));
            }

            return c;
        }).toList().get(0);

        System.out.println(maisVelho.getNome() + " - " + maisVelho.getIdade() + "\n");

        // 3.10
        List<Funcionario> funcionariosOrdenados = funcionarios.stream().sorted((f1, f2) -> {
            return f1.getNome().compareTo(f2.getNome());
        }).toList();

        System.out.println(funcionariosOrdenados + "\n");

        // 3.11
        BigDecimal total = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println(NumberFormat.getCurrencyInstance(Locale.of("pt", "BR")).format(total) + "\n");

        // 3.12
        HashMap<String, String> salariosMinimos = new HashMap<>();

        funcionarios.forEach(funcionario -> {
            salariosMinimos.put(funcionario.getNome(), String.format("%.2f", funcionario.getSalario().doubleValue() / 1212.0));
        });

        System.out.println(salariosMinimos);
    }
}
