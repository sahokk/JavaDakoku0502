package ui_control;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import run.MainSystem;

public class LoginFrame extends Frame implements ActionListener, WindowListener {
	TextField userField;
	TextField passField;

	Button okButton;
	Button cancelButton;

	Label msgLabel;

	Panel northPanel;
	Panel centerPanel;
	Panel centerPanelNorth;
	Panel centerPanelSouth;
	Panel southPanel;

	MainSystem mainSystem;

	public LoginFrame() {
		this.mainSystem = new MainSystem();

		userField = new TextField("", 20);
		passField = new TextField("", 20);
		passField.setEchoChar('*');

		okButton = new Button("ログイン");
		cancelButton = new Button("キャンセル");

		msgLabel = new Label("                                                       ");

		northPanel = new Panel();
		centerPanel = new Panel(new BorderLayout());
		centerPanelNorth = new Panel();
		centerPanelSouth = new Panel();
		centerPanelSouth.add(msgLabel);
		southPanel = new Panel();

		centerPanel.add(centerPanelNorth, BorderLayout.NORTH);
		centerPanel.add(centerPanelSouth, BorderLayout.SOUTH);

		northPanel.add(new Label("SQLアカウントにログインしてください"));
		centerPanelNorth.add(new Label("ユーザー名: "));
		centerPanelNorth.add(userField);
		centerPanelNorth.add(new Label("パスワード: "));
		centerPanelNorth.add(passField);
		southPanel.add(cancelButton);
		southPanel.add(okButton);

		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		addWindowListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

		setTitle("Login");
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			System.exit(0);
		} else if (e.getSource() == okButton) {
			boolean connectable = mainSystem.isConnectableDB(userField.getText(), passField.getText());
			System.out.println(connectable);
			if (connectable) {
				setVisible(false);
				dispose();
				MainFrame mainFrame = new MainFrame(mainSystem);
				mainFrame.setBounds(300, 300, 500, 300);
				mainFrame.setVisible(true);
			} else {
				msgLabel.setText("ログイン情報が間違っています。");
				userField.setText(null);
				passField.setText(null);
			}
		}

	}
}
