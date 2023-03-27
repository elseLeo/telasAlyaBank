import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TelaCadastroPessoaFisica extends JFrame {
    private JTextField txtNome;
    private JTextField txtCPF;
    private JTextField txtProfissao;
    private JTextField txtEndereco;
    private JTextField txtNCasa;
    private JTextField txtCidade;
    private JTextField txtUF;
    private JButton btnSalvar;
    private JLabel lblCidade;
    private JLabel lblEndereco;
    private JLabel lblProfissao;
    private JLabel lblCPF;
    private JLabel lblNome;
    private JLabel lblNCasa;
    private JLabel lblUF;
    private JPanel CadastroPessoaFisica;
    private JTextField txtRenda;
    private JLabel lblRenda;
    final String URL = "jdbc:mysql://localhost:3306/alyabank";
    final String USER = "root";
    final String PASSWORD = "root";
    final String INSERIR = "INSERT INTO conta (nome, cpf, profissao, rua, cidade, UF, nCasa, renda) VALUES(?,?,?,?,?,?,?,?)";

    public TelaCadastroPessoaFisica(){
        iniciarComponentes();
        conectar();

    }
    public void iniciarComponentes(){
        setTitle("Cadastro de Aluno");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(CadastroPessoaFisica);
        setVisible(true);
    }
    public void conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conectado!");

            final PreparedStatement stmtInserir;

            stmtInserir = con.prepareStatement(INSERIR);
            btnSalvar.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String nome = txtNome.getText();
                    String cpf = txtCPF.getText();
                    String profissao = txtProfissao.getText();
                    String rua = txtEndereco.getText();
                    String cidade = txtCidade.getText();
                    String UF =txtUF.getText();
                    String nCasa = txtNCasa.getText();
                    String rendaStr = txtRenda.getText();
                    try {
                        Double renda = Double.parseDouble(rendaStr);
                        stmtInserir.setString(1, nome);
                        stmtInserir.setString(2,cpf);
                        stmtInserir.setString(3,profissao);
                        stmtInserir.setString(4,rua);
                        stmtInserir.setString(5,cidade);
                        stmtInserir.setString(6,UF);
                        stmtInserir.setString(7,nCasa);
                        stmtInserir.setDouble(8,renda);
                        stmtInserir.executeUpdate();
                        System.out.println("Dados Inseridos!");
                        JOptionPane.showMessageDialog(btnSalvar, "Dados inseridos!");
                        txtNome.setText("");
                        txtCPF.setText("");
                        txtProfissao.setText("");
                        txtCidade.setText("");
                        txtCidade.setText("");
                        txtNCasa.setText("");
                        txtUF.setText("");
                        txtRenda.setText("");
                    }
                    catch (Exception ex){
                        System.out.println("Erro ao inserir os dados no banco!");
                    }
                }
            });
        }catch (Exception ex) {
            System.out.println("Erro ao conectar o banco de dados!");
        }


    }

    public static void main(String[] args) {
        TelaCadastroPessoaFisica telaCadastroPessoaFisica = new TelaCadastroPessoaFisica();
    }


}

