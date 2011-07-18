package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CLoginFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JCheckBox chkRememberPassword;
	private JButton btnLogin,btnShutdown;
	public CLoginFrame(String strTitle)
	{
		super(strTitle);
		createUI();
	}
	private void createUI()
	{
		Container con=getContentPane();
		JPanel pnGeneral=new JPanel();
		pnGeneral.setLayout(new BorderLayout());
		con.add(pnGeneral);
		JPanel pnTop=new JPanel();
		JLabel lblTop=new JLabel(new ImageIcon("images/r-manager.png"));
		pnTop.add(lblTop);
		pnGeneral.add(pnTop,BorderLayout.NORTH);
		
		JLabel lblEast=new JLabel(new ImageIcon("images/admin.png"));
		JPanel pnEast=new JPanel();
		pnEast.add(lblEast);
		pnGeneral.add(pnEast,BorderLayout.EAST);
		
		JPanel pnGeneralCenter=new JPanel();
		pnGeneral.add(pnGeneralCenter,BorderLayout.CENTER);
		JPanel pnCenter=new JPanel();
		pnGeneralCenter.add(pnCenter);
		
		pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
		JLabel lblUserName=new JLabel("User Name:");
		JLabel lblUserNameIcon=new JLabel(new ImageIcon("images/pwd.gif"));
		txtUserName=new JTextField(15);
		JPanel pnUserName=new JPanel();
		pnUserName.add(lblUserName);
		pnUserName.add(lblUserNameIcon);
		pnUserName.add(txtUserName);
		
		JLabel lblPassword=new JLabel("Password:");
		JLabel lblPasswordIcon=new JLabel(new ImageIcon("images/user.gif"));
		txtPassword=new JPasswordField(15);
		JPanel pnPassword=new JPanel();
		pnPassword.add(lblPassword);
		pnPassword.add(lblPasswordIcon);
		pnPassword.add(txtPassword);
		
		chkRememberPassword=new JCheckBox("Remember password        ");
		
		JPanel pnRemember=new JPanel();
		pnRemember.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnRemember.add(chkRememberPassword);
		
		btnLogin=new JButton("Login", new ImageIcon("images/key.png"));
		btnShutdown=new JButton("Exit", new ImageIcon("images/shutdown.png"));
		JPanel pnButton=new JPanel();
		pnButton.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pnButton.add(btnLogin);
		pnButton.add(btnShutdown);
		
		pnCenter.add(pnUserName);
		pnCenter.add(pnPassword);
		pnCenter.add(pnRemember);
		pnCenter.add(pnButton);
		TitledBorder bor=new TitledBorder(BorderFactory.createLineBorder(Color.BLUE), "Login information");
		pnCenter.setBorder(bor);
		
		lblPassword.setPreferredSize(lblUserName.getPreferredSize());
		txtUserName.setBackground(Color.LIGHT_GRAY);
		txtPassword.setBackground(Color.LIGHT_GRAY);
		
		txtUserName.requestFocus();
	}
	public JButton getButtonLogin()
	{
		return btnLogin;
	}
	public JButton getButtonShutdown()
	{
		return btnShutdown;
	}
	public JTextField getTextFieldUserName()
	{
		return txtUserName;
	}
	public JPasswordField getPasswordField()
	{
		return txtPassword;
	}
	public JCheckBox getCheckBoxRememberPassword()
	{
		return chkRememberPassword;
	}
}