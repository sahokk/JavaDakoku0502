package ui_control;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class SettingDialog extends Dialog implements ActionListener, WindowListener {
	boolean canceled;

	TextField jcLoginIdField;
	TextField jcLoginPassField;

	TextField rLoginIdField;
	TextField rLoginPassField;
	Button okButton;
	Button cancelButton;

	Panel northPanel;
	Panel jcPanel;
	Panel jcPanel2;
	Panel centerPanel;
	Panel rPanel;
	Panel rPanel2;
	Panel southPanel;

	public SettingDialog(Frame owner) {
		super(owner, "Setting", true);
		canceled = true;

		jcLoginIdField = new TextField("", 50);
		jcLoginPassField = new TextField("", 50);
		jcLoginPassField.setEchoChar('*');
		rLoginIdField = new TextField("", 50);
		rLoginPassField = new TextField("", 50);
		rLoginPassField.setEchoChar('*');

		okButton = new Button("登録");
		cancelButton = new Button("キャンセル");

		northPanel = new Panel(new BorderLayout());
		jcPanel = new Panel();
		jcPanel2 = new Panel();
		northPanel.add(jcPanel, BorderLayout.NORTH);
		northPanel.add(jcPanel2, BorderLayout.CENTER);

		centerPanel = new Panel(new BorderLayout());
		rPanel = new Panel();
		rPanel2 = new Panel();
		centerPanel.add(rPanel, BorderLayout.NORTH);
		centerPanel.add(rPanel2, BorderLayout.CENTER);

		southPanel = new Panel();

		jcPanel.add(new Label("JC ID"));
		jcPanel.add(jcLoginIdField);
		jcPanel2.add(new Label("JC Pass"));
		jcPanel2.add(jcLoginPassField);

		rPanel.add(new Label("R ID"));
		rPanel.add(rLoginIdField);
		rPanel2.add(new Label("R Pass"));
		rPanel2.add(rLoginPassField);

		southPanel.add(cancelButton);
		southPanel.add(okButton);

		setLayout(new BorderLayout());
		add(northPanel, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		addWindowListener(this);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);

	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void windowClosing(WindowEvent e) {
		setVisible(false);
		canceled = true;
		dispose();
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
			canceled = true;
		} else if (e.getSource() == okButton) {
			canceled = false;
		}
		setVisible(false);
		dispose();

	}

	public String getJcLoginIdField() {
		return jcLoginIdField.getText();
	}

	public String getJcLoginPassField() {
		return jcLoginPassField.getText();
	}

	public String getRLoginIdField() {
		return rLoginIdField.getText();
	}

	public String getRLoginPassField() {
		return rLoginPassField.getText();
	}

	public boolean isCanceled() {
		return canceled;
	}

}
