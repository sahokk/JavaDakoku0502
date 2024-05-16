package ui_control;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import db_control.DbControl;
import run.MainSystem;

public class MainFrame extends Frame implements ActionListener, ItemListener, WindowListener {
	DbControl dbControl;
	MainSystem mainSystem;
	Panel centerPanel;
	Panel northPanel;
	Panel northPanel2;
	Panel southPanel;

	Checkbox rCheckbox;
	Checkbox jcCheckbox;

	Button dakokuButton;
	Button settingButton;

	TextArea mainArea;
	boolean isFirstOpened;

	public MainFrame(MainSystem mainSystem) {
		this.mainSystem = mainSystem;
		centerPanel = new Panel(new BorderLayout());
		northPanel = new Panel(new BorderLayout());
		northPanel2 = new Panel(new GridLayout(2, 1));
		southPanel = new Panel();

		dakokuButton = new Button("打刻");
		settingButton = new Button("設定");

		rCheckbox = new Checkbox("R GROUP");
		jcCheckbox = new Checkbox("JC");

		northPanel2.add(jcCheckbox);
		northPanel2.add(rCheckbox);

		mainArea = new TextArea(18, 80);
		mainArea.setEditable(false);

		setLayout(new BorderLayout());

		northPanel.add(new Label("ログイン項目を選択： "), BorderLayout.NORTH);
		northPanel.add(northPanel2, BorderLayout.CENTER);
		northPanel.add(dakokuButton, BorderLayout.SOUTH);
		southPanel.add(settingButton);
		centerPanel.add(mainArea);

		dakokuButton.addActionListener(this);
		settingButton.addActionListener(this);
		rCheckbox.addItemListener(this);
		jcCheckbox.addItemListener(this);

		add(northPanel, BorderLayout.NORTH);
		add(southPanel, BorderLayout.SOUTH);
		add(centerPanel, BorderLayout.CENTER);
		addWindowListener(this);

		setTitle("打刻システム");

		this.isFirstOpened = mainSystem.isFirstOpened();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		if (isFirstOpened) {
			mainArea.setText("はじめまして！　初めにログイン情報を登録してください。");
			String str = mainSystem.pushSettingButton(this, isFirstOpened);
			mainArea.setText(str);
		}

	}

	@Override
	public void windowClosing(WindowEvent e) {
		mainSystem.finishDriver();
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
	public void itemStateChanged(ItemEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == dakokuButton) {
			mainArea.setText("dakoku");
			mainArea.setText(mainSystem.doDakoku(jcCheckbox.getState(), rCheckbox.getState()));
			jcCheckbox.setState(false);
			rCheckbox.setState(false);

		} else if (e.getSource() == settingButton) {
			mainArea.setText("setting");
			String str = mainSystem.pushSettingButton(this, isFirstOpened);
			mainArea.setText(str);

		}

	}
}
