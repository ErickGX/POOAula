package br.senac.sp.livraria.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import br.senac.sp.livraria.dao.ClienteDAO;
import br.senac.sp.livraria.dao.ConnectionFactory;
import br.senac.sp.livraria.dao.InterfaceDAO;
import br.senac.sp.livraria.enumeration.Escolaridade;
import br.senac.sp.livraria.enumeration.EstadoCivil;
import br.senac.sp.livraria.model.Cliente;

public class ViewCliente extends JFrame implements ActionListener {
	JLabel lbId, lbCpf, lbNome, lbNascimento, lbTelefone, lbEmail, lbEndereco, lbEscolaridade, lbEstadoCivil;
	JTextField tfId, tfCpf, tfNome, tfTelefone, tfEmail;
	JFormattedTextField tfNascimento;
	MaskFormatter mskNascimento;
	Font fontePadrao;
	JTextArea taEndereco;
	JComboBox<Escolaridade> cbEscolaridade;
	JComboBox<EstadoCivil> cbEstadoCivil;
	JButton btSalvar;
	Cliente cliente;
	Connection conexao;
	InterfaceDAO<Cliente> daoCliente;
	
	public static void main(String[] args) {
		new ViewCliente();
	}
	
	public ViewCliente() {
		try {
			conexao = ConnectionFactory.getConexao();
			daoCliente = new ClienteDAO(conexao);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
			e.printStackTrace();
		}
		
		initComponents();
		actions();
	}
	
	// constroi a tela
	private void initComponents() {
		fontePadrao = new Font("Tahoma", Font.PLAIN, 20);
		
		// lbId
		lbId = new JLabel("Id: ");
		lbId.setBounds(20,20,50,30);
		lbId.setFont(fontePadrao);
		
		// tfId
		tfId = new JTextField();
		tfId.setBounds(50,20,40,30);
		// tfId.setEditable(false); // aparece o cursor, da p copiar o texto, mas nao editar
		tfId.setEnabled(false); // nao da pra copiar o texto nem editar
		tfId.setFont(fontePadrao);
		tfId.setHorizontalAlignment(SwingConstants.CENTER); // alinhamento do texto
		tfId.setText("id");
		
		///////////
		
		// lbNome
		lbNome = new JLabel("Nome: ");
		lbNome.setBounds(110,20,100,30);
		lbNome.setFont(fontePadrao);
		
		// tfNome
		tfNome = new JTextField();
		tfNome.setBounds(180,20,270,30);
		tfNome.setEnabled(true); 
		tfNome.setFont(fontePadrao);
		
		///////////
		
		// lbCpf
		lbCpf = new JLabel("CPF: ");
		lbCpf.setBounds(20,70,80,30);
		lbCpf.setFont(fontePadrao);
				
		// tfCpf
		tfCpf = new JTextField();
		tfCpf.setBounds(70,70,150,30);
		tfCpf.setEnabled(true); 
		tfCpf.setFont(fontePadrao);
		
		///////////
		
		// lbNascimento
		lbNascimento = new JLabel("Nascimento: ");
		lbNascimento.setBounds(230,70,250,30);
		lbNascimento.setFont(fontePadrao);
				
		// mskNascimento
		try {
			mskNascimento = new MaskFormatter("##/##/####");
			mskNascimento.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
		// tfNascimento
		tfNascimento = new JFormattedTextField(mskNascimento);
		tfNascimento.setBounds(350, 70, 100, 30);
		tfNascimento.setFont(fontePadrao);
		tfNascimento.setHorizontalAlignment(SwingConstants.CENTER);
		
		///////////
		
		// lbTelefone
		lbTelefone = new JLabel("Telefone: ");
		lbTelefone.setBounds(20,120,130,30);
		lbTelefone.setFont(fontePadrao);
				
		// tfTelefone
		tfTelefone = new JTextField();
		tfTelefone.setBounds(110,120,340,30);
		tfTelefone.setEnabled(true); 
		tfTelefone.setFont(fontePadrao);
		
		///////////
		
		// lbEmail
		lbEmail = new JLabel("E-mail: ");
		lbEmail.setBounds(20,170,100,30);
		lbEmail.setFont(fontePadrao);
				
		// tfEmail
		tfEmail = new JTextField();
		tfEmail.setBounds(90,170,360,30);
		tfEmail.setEnabled(true); 
		tfEmail.setFont(fontePadrao);
				
		///////////

		// lbEscolaridade
		lbEscolaridade = new JLabel("Escolaridade: ");
		lbEscolaridade.setBounds(20,220,200,30);
		lbEscolaridade.setFont(fontePadrao);
		
		// cbEscolaridade
		cbEscolaridade = new JComboBox<>(Escolaridade.values());
		cbEscolaridade.setBounds(150, 220, 150, 30);
		cbEscolaridade.setBackground(Color.white);
		cbEscolaridade.setSelectedIndex(-1);
		
		///////////

		// lbEstadoCivil
		lbEstadoCivil = new JLabel("Estado Civil: ");
		lbEstadoCivil.setBounds(20,270,200,30);
		lbEstadoCivil.setFont(fontePadrao);
		
		// cbEstadoCivil
		cbEstadoCivil = new JComboBox<>(EstadoCivil.values());
		cbEstadoCivil.setBounds(150, 270, 150, 30);
		cbEstadoCivil.setBackground(Color.white);
		cbEstadoCivil.setSelectedIndex(-1);
		
		///////////
		
		// lbEndereco
		lbEndereco = new JLabel("Endereço: ");
		lbEndereco.setBounds(20,320,100,30);
		lbEndereco.setFont(fontePadrao);
		
		// taEndereco
		taEndereco = new JTextArea();
		taEndereco.setBounds(20, 350, 430, 50);
		taEndereco.setFont(fontePadrao);
		taEndereco.setLineWrap(true);
		taEndereco.setWrapStyleWord(true);
		
		///////////
		
		// botão de salvar
		btSalvar = new JButton("Salvar");
		btSalvar.setBounds(20, 420, 100, 30);
		btSalvar.setFont(fontePadrao);
		btSalvar.setMnemonic('S');
		
		// adicionar componentes
		Container base = getContentPane();
		base.setLayout(null);
		base.add(lbId);
		base.add(tfId);
		base.add(lbNome);
		base.add(tfNome);
		base.add(lbCpf);
		base.add(tfCpf);
		base.add(lbNascimento);
		base.add(tfNascimento);
		base.add(lbTelefone);
		base.add(tfTelefone);
		base.add(lbEmail);
		base.add(tfEmail);
		base.add(lbEscolaridade);
		base.add(cbEscolaridade);
		base.add(lbEstadoCivil);
		base.add(cbEstadoCivil);
		base.add(lbEndereco);
		base.add(taEndereco);
		base.add(btSalvar);
		
		
		// parâmetros da janela
		setSize(500, 700);
		setLocationRelativeTo(null); // posiciona no meio da tela
		setVisible(true); // smp no fim
		setDefaultCloseOperation(EXIT_ON_CLOSE); // smp no fim, faz a execução parar ao clicar no X
	}
	
	
	
			
	
	// define comportamentos
	private void actions() {
		btSalvar.addActionListener(this);
		
		// permitir somente numeros na tfCpf
		tfCpf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar()) || tfCpf.getText().length() == 11) {
					e.consume();									
				}
			}
			
		});
		
		btSalvar.addActionListener(e -> {
			if (tfNome.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Informe o nome", "Erro", JOptionPane.WARNING_MESSAGE);
				tfNome.requestFocus();
			} else if (tfCpf.getText().isEmpty() || tfCpf.getText().length() != 11) {
				JOptionPane.showMessageDialog(this, "Informe o CPF", "Erro", JOptionPane.WARNING_MESSAGE);
				tfCpf.requestFocus();
			} else if (tfNascimento.getValue() == null) {
				JOptionPane.showMessageDialog(this, "Informe a data de nascimento", "Erro", JOptionPane.WARNING_MESSAGE);
				tfNascimento.requestFocus();
			} else if (cbEscolaridade.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Informe a escolaridade", "Erro", JOptionPane.WARNING_MESSAGE);
				cbEscolaridade.requestFocus();
			} else if (cbEstadoCivil.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(this, "Informe o estado civil", "Erro", JOptionPane.WARNING_MESSAGE);
				cbEstadoCivil.requestFocus();
			} else {
				cliente = new Cliente();
				cliente.setNome(tfNome.getText());
				
				Calendar dataNasc = Calendar.getInstance();
				SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
				try {
					dataNasc.setTime(fmt.parse(tfNascimento.getText()));
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(this, "Erro ao converter");
				}
				cliente.setNascimento(dataNasc);
				cliente.setTelefone(tfTelefone.getText());
				cliente.setEmail(tfEmail.getText());
				cliente.setEscolaridade((Escolaridade) cbEscolaridade.getSelectedItem());
				cliente.setEstadoCivil((EstadoCivil) cbEstadoCivil.getSelectedItem());
				cliente.setEndereco(taEndereco.getText());
				
				try {
					conexao = ConnectionFactory.getConexao();
					daoCliente.inserir(cliente);
					limpar();
				} catch (SQLException e2) {
					JOptionPane.showMessageDialog(this, e2.getMessage());
					e2.printStackTrace();
				}
			}
		});
		
	}
	
	private void limpar() {
		cliente = null;
		tfId.setText(null);
		tfNome.setText(null);;
		tfCpf.setText(null);
		tfNascimento.setText(null);;
		tfTelefone.setText(null);;
		tfEmail.setText(null);;
		cbEscolaridade.setSelectedIndex(-1);
		cbEstadoCivil.setSelectedIndex(-1);
		tfNome.requestFocus();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
