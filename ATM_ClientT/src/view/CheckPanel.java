package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class CheckPanel extends JPanel {

	private JLabel label;
	private JTextArea check;
	private TitledBorder balance;	
	
	private ATMFrame atm;

	public CheckPanel(ATMFrame atm) {
		init();
		initBorder();
		
		setLayout(new BorderLayout());
		add(label, BorderLayout.NORTH);
		add(check, BorderLayout.CENTER);
	}

	public void init() {
		label = new JLabel("Print check: ");
		check = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(check);
		check.setEditable(false);
	}
	
	private void initBorder(){
		balance = BorderFactory.createTitledBorder("Balance ");
		balance.setTitleColor(Color.BLACK);
		balance.setTitleJustification(TitledBorder.CENTER);
		balance.setTitleFont(new Font("Courier", Font.PLAIN, 16));
		balance.setBorder(BorderFactory.createLineBorder(Color.GRAY.brighter(), 2));

		setBorder(balance);
		
	}
	
	public void print(String string){
		check.setText(string);
		check.updateUI();
	}


}
