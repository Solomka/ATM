package view;



import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class CardreaderPanel extends JPanel {
	private JLabel cardNum;
	private JTextField cardNumField;
	private JButton insert;
	private TitledBorder cardReader;
	private boolean enabled;
	private MouseListener mouseListener;
	
	public int state = 1;
	private int cardnum;
	
	private ATMFrame atm;

	public CardreaderPanel(ATMFrame atm) {
		this.atm = atm;
		enabled = true;
		setLayout(new GridLayout(4, 1));
		initElements();
		initBorder();
		initListeners();
		organizeElements();

	}

	private void initElements() {
		cardNum = new JLabel("Type card number:");
		cardNumField = new JTextField();
		
		
		insert = new JButton("Enter");

		cardNum.setFont(new Font("Courier", Font.CENTER_BASELINE, 12));
		insert.setFont(new Font("Courier", Font.ROMAN_BASELINE, 12));
		insert.setBounds(20, 20, 100, 100);
		insert.setContentAreaFilled(false);		

	}

	private void initListeners()  {
		mouseListener = new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				//	cardNumField.setEditable(false);
				if (state==1){
					if (cardNumField.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"Please print your card number");
						return;
					}

					int cardNumber = Integer
							.parseInt(cardNumField.getText());
					try {
						if(ATMFrame.service.getService().exist(cardNumber)){
							atm.dp.setState(1);
							atm.kp.setState(1);
							cardnum = cardNumber;
							setState(0);
						}
						else{
							JOptionPane.showMessageDialog(null,
									"Non-existing account");
							setState(1);
						}
						
							
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		};
	}

	private void organizeElements() {
		add(cardNum);
		cardNumField.setFont(new Font("Consolas", Font.BOLD, 12));
		add(cardNumField);
		add(new JLabel(""));
		add(insert);
		insert.addMouseListener(mouseListener);	
	}

	private void initBorder() {
		cardReader = BorderFactory.createTitledBorder("Insert card");
		cardReader.setTitleColor(Color.BLACK);
		cardReader.setTitleJustification(TitledBorder.CENTER);
		cardReader.setTitleFont(new Font("Courier", Font.PLAIN, 16));
		cardReader.setBorder(BorderFactory
				.createLineBorder(Color.GRAY.brighter(), 2));

		setBorder(cardReader);
	}
	
	public  void setState(int s){
		if (s==1){
			//!!!!!!!!!!!!!!
			cardNumField.setEditable(true);
			insert.setEnabled(true);
			
			state = s;
			cardNumField.setText("");
			cardnum = -1;
		}else if (s==0){
			//!!!
			cardNumField.setEditable(false);
			insert.setEnabled(false);
			state = s;
		}else{
			//
		}
	}
	
	public int getCardnum(){
		return cardnum;
	}

}
