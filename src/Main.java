import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat FORMATO_NUMERO = NumberFormat.getInstance(new Locale("pt", "BR"));
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {

        // 3.1 - Inserir todos os funcionários, na mesma ordem da tabela
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        // 3.2 - Remover o funcionário "João" da lista
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários com todas as informações
        System.out.println("=== 3.3 - Funcionários ===");
        funcionarios.forEach(Main::imprimirFuncionario);

        // 3.4 - Aplicar aumento de 10% e atualizar a lista
        funcionarios.forEach(f -> {
            BigDecimal reajuste = f.getSalario().multiply(new BigDecimal("0.10"));
            f.setSalario(f.getSalario().add(reajuste).setScale(2, RoundingMode.HALF_UP));
        });

        System.out.println("\n=== 3.4 - Funcionários com salário reajustado (+10%) ===");
        funcionarios.forEach(Main::imprimirFuncionario);

        // 3.5 - Agrupar os funcionários por função em um Map<funcao, List<Funcionario>>
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\n=== 3.6 - Funcionários agrupados por função ===");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(Main::imprimirFuncionario);
        });

        // 3.8 - Imprimir funcionários que fazem aniversário nos meses 10 e 12
        System.out.println("\n=== 3.8 - Aniversariantes de outubro e dezembro ===");
        funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10
                        || f.getDataNascimento().getMonthValue() == 12)
                .forEach(Main::imprimirFuncionario);

        // 3.9 - Imprimir o funcionário com a maior idade (nome e idade)
        Funcionario maisVelho = funcionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElse(null);

        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.println("\n=== 3.9 - Funcionário com maior idade ===");
            System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade);
        }

        // 3.10 - Imprimir a lista de funcionários em ordem alfabética
        List<Funcionario> ordemAlfabetica = funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .collect(Collectors.toList());

        System.out.println("\n=== 3.10 - Funcionários em ordem alfabética ===");
        ordemAlfabetica.forEach(Main::imprimirFuncionario);

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\n=== 3.11 - Total dos salários ===");
        System.out.println("Total: " + FORMATO_NUMERO.format(totalSalarios));

        // 3.12 - Imprimir quantos salários mínimos cada funcionário ganha
        System.out.println("\n=== 3.12 - Salários mínimos por funcionário ===");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + FORMATO_NUMERO.format(qtdSalariosMinimos) + " salários mínimos");
        });
    }

    private static void imprimirFuncionario(Funcionario f) {
        System.out.println(
                "Nome: " + f.getNome() +
                " | Nascimento: " + f.getDataNascimento().format(FORMATO_DATA) +
                " | Salário: " + FORMATO_NUMERO.format(f.getSalario()) +
                " | Função: " + f.getFuncao()
        );
    }
}
