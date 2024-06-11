import java.time.LocalDate;
import java.time.Period;

// 1
public class Pessoa {
    private String nome;
    private LocalDate dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public String getDataNascimentoFormatada() {
        String dia = String.valueOf(this.dataNascimento.getDayOfMonth());
        String mes = String.valueOf(this.dataNascimento.getMonthValue());

        if (dia.length() == 1) {
            dia = "0".concat(dia);
        }

        if (mes.length() == 1) {
            mes = "0".concat(mes);
        }

        return "%s/%s/%d".formatted(
                dia,
                mes,
                this.dataNascimento.getYear()
        );
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getIdade() {
        return Period.between(this.getDataNascimento(), LocalDate.now()).getYears();
    }
}
