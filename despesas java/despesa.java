import java.util.ArrayList;
import java.util.Date;
import java.util.List;

abstract class Transacao {
    private Date data;
    private String descricao;
    private double valor;

    public Transacao(Date data, String descricao, double valor) {
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public abstract String tipo();
}

class Receita extends Transacao {
    private Categoria categoria;

    public Receita(Date data, String descricao, double valor, Categoria categoria) {
        super(data, descricao, valor);
        this.categoria = categoria;
    }

    @Override
    public String tipo() {
        return "Receita";
    }

    public Categoria getCategoria() {
        return categoria;
    }
}

class Despesa extends Transacao {
    private Categoria categoria;

    public Despesa(Date data, String descricao, double valor, Categoria categoria) {
        super(data, descricao, valor);
        this.categoria = categoria;
    }

    @Override
    public String tipo() {
        return "Despesa";
    }

    public Categoria getCategoria() {
        return categoria;
    }
}

abstract class Categoria {
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}

class Conta extends Categoria {
    public Conta() {
        super("Conta");
    }
}

class Alimentacao extends Categoria {
    public Alimentacao() {
        super("Alimentação");
    }
}

class Lazer extends Categoria {
    public Lazer() {
        super("Lazer");
    }
}

class ControleFinanceiro {
    private List<Transacao> transacoes;

    public ControleFinanceiro() {
        this.transacoes = new ArrayList<>();
    }

    public void adicionarTransacao(Transacao transacao) {
        transacoes.add(transacao);
    }

    public void removerTransacao(Transacao transacao) {
        transacoes.remove(transacao);
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }
}

class Relatorio {
    public static void gerarRelatorio(List<Transacao> transacoes, String tipo) {
        double total = 0;
        System.out.println("Relatório de " + tipo + ":\n");
        for (Transacao transacao : transacoes) {
            if (transacao.tipo().equals(tipo)) {
                System.out.println("Data: " + transacao.getData() + " | Descrição: " + transacao.getDescricao() + " | Valor: " + transacao.getValor() + " | Categoria: " + transacao.getCategoria().getNome());
                total += transacao.getValor();
            }
        }
        System.out.println("\nTotal de " + tipo + ": " + total + "\n");
    }
}

public class Main {
    public static void main(String[] args) {
        ControleFinanceiro controle = new ControleFinanceiro();

        Categoria conta = new Conta();
        Categoria alimentacao = new Alimentacao();
        Categoria lazer = new Lazer();

        Transacao receita1 = new Receita(new Date(), "Salário", 3000, conta);
        Transacao receita2 = new Receita(new Date(), "Bônus", 500, conta);
        Transacao despesa1 = new Despesa(new Date(), "Restaurante", 200, alimentacao);
        Transacao despesa2 = new Despesa(new Date(), "Cinema", 50, lazer);

        controle.adicionarTransacao(receita1);
        controle.adicionarTransacao(receita2);
        controle.adicionarTransacao(despesa1);
        controle.adicionarTransacao(despesa2);

        List<Transacao> transacoes = controle.getTransacoes();

        Relatorio.gerarRelatorio(transacoes, "Receitas");
        Relatorio.gerarRelatorio(transacoes, "Despesas");
    }
}