import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class TelaContaCorrente extends JFrame{
    private JPanel CadastroPessoaFisica;
    private JLabel lblAgencia;
    private JLabel lblNcontaCorrente;
    private JTextField txtAgencia;
    private JLabel lblDeposito;
    private JTextField txtNContaCorrente;
    private JButton btnSalvar;
    private JTextField txtDeposito;
    final String URL = "jdbc:mysql://localhost:3306/alyabank";
    final String USER = "root";
    final String PASSWORD = "root";
    final String INSERIR = "INSERT INTO contaCorrente (numConta, agencia, saldo) VALUES(?,?,?)";

    public TelaContaCorrente() {
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
                    String numConta = txtNContaCorrente.getText();
                    String agencia = txtAgencia.getText();
                    String saldoStr = txtDeposito.getText();
                    try {
                        Double saldo = Double.parseDouble(saldoStr);
                        stmtInserir.setString(1, numConta);
                        stmtInserir.setString(2,agencia);
                        stmtInserir.setDouble(3,saldo);
                        stmtInserir.executeUpdate();
                        System.out.println("Dados Inseridos!");
                        JOptionPane.showMessageDialog(btnSalvar, "Dados inseridos!");
                        txtDeposito.setText("");
                        txtAgencia.setText("");
                        txtNContaCorrente.setText("");
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
        TelaContaCorrente telaContaCorrente = new TelaContaCorrente();
    }
}
