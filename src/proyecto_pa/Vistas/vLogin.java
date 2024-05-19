package proyecto_pa.Vistas;

import proyecto_pa.Controladores.UserController;
import proyecto_pa.Modelos.User;
import proyecto_pa.Vistas.vPrincipal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class vLogin extends JFrame {
	static private JTextField user_txt;
	static private JTextField pass_txt;
	static private JButton incia_btn;
	
	static UserController userController = new UserController();
	
	public vLogin() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {150, 722, 150};
		gridBagLayout.rowHeights = new int[] {50, 30, 50};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		getContentPane().setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 1;
		getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {30, 90, 0, 90, 30, 0};
		gbl_panel.rowHeights = new int[] {30, 0, 40, 240, 40, 30, 30};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Inicio de Sesion");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 2;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 2;
		gbc_panel_1.gridy = 3;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[] {30, 0, 0, 30, 0, 11, 22, 31, 45, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JLabel lblNewLabel_1 = new JLabel("Usuario");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		user_txt = new JTextField();
		GridBagConstraints gbc_user_txt = new GridBagConstraints();
		gbc_user_txt.insets = new Insets(0, 0, 5, 5);
		gbc_user_txt.fill = GridBagConstraints.HORIZONTAL;
		gbc_user_txt.gridx = 1;
		gbc_user_txt.gridy = 2;
		panel_1.add(user_txt, gbc_user_txt);
		user_txt.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contraseña");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GridBagConstraints gbc_lblNewLabel_1_1 = new GridBagConstraints();
		gbc_lblNewLabel_1_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1_1.gridx = 1;
		gbc_lblNewLabel_1_1.gridy = 4;
		panel_1.add(lblNewLabel_1_1, gbc_lblNewLabel_1_1);
		
		pass_txt = new JTextField();
		pass_txt.setColumns(10);
		GridBagConstraints gbc_pass_txt = new GridBagConstraints();
		gbc_pass_txt.insets = new Insets(0, 0, 5, 5);
		gbc_pass_txt.fill = GridBagConstraints.HORIZONTAL;
		gbc_pass_txt.gridx = 1;
		gbc_pass_txt.gridy = 5;
		panel_1.add(pass_txt, gbc_pass_txt);
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 7;
		panel_1.add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0};
		gbl_panel_2.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		incia_btn = new JButton("Iniciar Sesion");
		incia_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkAuth();
			}
		});
		GridBagConstraints gbc_incia_btn = new GridBagConstraints();
		gbc_incia_btn.anchor = GridBagConstraints.SOUTH;
		gbc_incia_btn.gridx = 1;
		gbc_incia_btn.gridy = 0;
		panel_2.add(incia_btn, gbc_incia_btn);
		
		
	    initialize();
	}
	
	private void initialize() {
		setBounds(100, 100, 900, 600);
		setVisible(true);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
	
	private void checkAuth() {
		String username = user_txt.getText();
		String password = pass_txt.getText();
		
		if(username.length() == 0 || password.length() == 0) {
	         JOptionPane.showMessageDialog(null, "Por favor in gresa correctamente los datos");
		}else {
			User isAuth = userController.validarUser(username, password);
			
			
			if (isAuth instanceof User) {
				setVisible(false);
				vPrincipal principal = new vPrincipal(isAuth);
			}
		}
	}
}
