import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

// 2
public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setPosition(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        return String.format("%s - %s - %s - %s",
                this.getNome(),
                this.getDataNascimentoFormatada(),
                NumberFormat.getCurrencyInstance(Locale.of("pt", "BR")).format(this.getSalario()),
                this.getFuncao()
        );
    }
}
