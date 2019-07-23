package conexao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.Connection;

public class Conect extends JFrame {
	JButton botao;
	JButton botaoListar;

	public Conect() {
		super("SYS. CAD. REPORTAGENS.");
		Container tela = getContentPane();
		setLayout(null);
		botao = new JButton("INICIAR CADASTRO");
		botaoListar = new JButton("Listar CODIGO");
		botao.setBounds(80, 10, 150, 20);
		botaoListar.setBounds(80, 50, 150, 20);
		botao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				try {
					String url = "jdbc:mysql://localhost:3306/coursejdbc?useSSL=false";
					String usuario = "developer";
					String senha = "159357D";
					Class.forName("com.mysql.jdbc.Driver");
					Connection con;
					con = DriverManager.getConnection(url, usuario, senha);
					JOptionPane.showMessageDialog(null, "Conexão com a base OK. \n\nInforme os dados na Proxima Tela.",	"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
					
					// Criando um Statement
					Statement stmt = con.createStatement();

					String cadNome = JOptionPane.showInputDialog("Informe o Nome");
					String cadTitulo = JOptionPane.showInputDialog("Informe o Titulo da Reportagem");
					String cadNoticia = JOptionPane.showInputDialog("Informe a Noticia");

					stmt.executeUpdate("INSERT INTO cliente (nome, titulo, noticia) VALUES  ('" + cadNome + "', '"+ cadTitulo + "','" + cadNoticia + "')");

					con.close();

				} catch (Exception event) {
					JOptionPane.showMessageDialog(null, "Conexão não estabelecida", "Mensagem do Programa",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		botaoListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String url = "jdbc:mysql://localhost:3306/coursejdbc?useSSL=false";
					String usuario = "developer";
					String senha = "159357D";
					Class.forName("com.mysql.jdbc.Driver");
					Connection con;
					con = DriverManager.getConnection(url, usuario, senha);
					JOptionPane.showMessageDialog(null, "Conexão com a base OK. \n\nClique OK para listar dados.",	"Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
					
					// Criando um Statement
					Statement stmt = con.createStatement();
					String comandoSQL = "SELECT id, nome, titulo, noticia  FROM cliente";
					ResultSet resp = stmt.executeQuery(comandoSQL);
					while (resp.next()) {
						System.out.println("CODIGO ID = " + resp.getString("id")+" | Nome = " + resp.getString("nome")+" | Titulo = " + resp.getString("titulo")+" | Noticia = " + resp.getString("noticia"));
					}

				} catch (Exception event) {
					JOptionPane.showMessageDialog(null, "Conexão não estabelecida", "Mensagem do Programa",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		tela.add(botao);
		tela.add(botaoListar);
		setSize(300, 150);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	public static void main(String args[]) {
		Conect app = new Conect();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
