package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.CUser;
import model.FileProcessing;
import view.LoginFrame;
import view.MainFrame;

public class LoginController {
	private LoginFrame loginFrame = null;
	private CUser userLogin = null;

	public LoginController() {
		loginFrame = new LoginFrame("Login Restaurant Management");
		userLogin = (CUser) FileProcessing
				.ReadData(FileProcessing.FILENAME_USERLOGIN);
	}

	public void doShow() {
		loginFrame.setSize(450, 300);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginFrame.setLocationRelativeTo(null);
		loginFrame.setResizable(false);
		loginFrame.setVisible(true);
		addEventforAllControl();
		if (userLogin != null && userLogin.getRememberPassword() == true) {
			loginFrame.getTextFieldUserName().setText(
					userLogin.getUserName());
			loginFrame.getPasswordField().setText(userLogin.getPassword());
			loginFrame.getCheckBoxRememberPassword().setSelected(
					userLogin.getRememberPassword());
		}
	}

	public void addEventforAllControl() {
		loginFrame.getButtonLogin().addActionListener(new CMyButtonEvent());
		loginFrame.getTextFieldUserName().addActionListener(
				new CMyButtonEvent());
		loginFrame.getPasswordField().addActionListener(new CMyButtonEvent());
		loginFrame.getButtonShutdown()
				.addActionListener(new CMyButtonEvent());
	}

	private void doProcessLogin() {
		String strUserName = loginFrame.getTextFieldUserName().getText();
		String strPwd = String.copyValueOf(loginFrame.getPasswordField()
				.getPassword());
		boolean bRemember = loginFrame.getCheckBoxRememberPassword()
				.isSelected();
		if (userLogin == null) {
			userLogin = new CUser(strUserName, strPwd, bRemember);
		}
		if (userLogin != null
				&& userLogin.getUserName().equals(strUserName)
				&& userLogin.getPassword().equals(strPwd)) {
			loginFrame.dispose();
			MainFrame frame = new MainFrame();
			frame.setVisible(true);
			userLogin.setRememeberPassword(bRemember);
			FileProcessing.WriteData(userLogin, FileProcessing.FILENAME_USERLOGIN);
		} else {
			JOptionPane.showMessageDialog(null, "Login Failed");
			loginFrame.getTextFieldUserName().requestFocus();
		}
	}

	private class CMyButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			Object o = arg0.getSource();
			if (o.equals(loginFrame.getButtonShutdown())) {
				loginFrame.dispose();
			} else if (o.equals(loginFrame.getButtonLogin())
					|| o.equals(loginFrame.getTextFieldUserName())
					|| o.equals(loginFrame.getPasswordField())) {

				doProcessLogin();
			}
		}

	}
}
