package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class KeyboardPanel extends JPanel {

	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bok, bclear,
			bcancel;
	private ArrayList<JButton> bnumbers;
	private JPanel sub1, sub2;
	private TitledBorder keyboard;

	private MouseListener mouselistener;

	private String pin = "";
	private String sum = "";
	private String acc = "";

	public int state = 0;
	
	private ATMFrame atm;

	public KeyboardPanel(ATMFrame atm) {
		this.atm = atm;
		init();

		initBorder();

		initListeners();
		addMouseListeners();
		setLayout(new BorderLayout());
		add(sub1, BorderLayout.CENTER);
		add(sub2, BorderLayout.SOUTH);
	}

	private void init() {
		b1 = new JButton("1");
		b2 = new JButton("2");
		b3 = new JButton("3");
		b4 = new JButton("4");
		b5 = new JButton("5");
		b6 = new JButton("6");
		b7 = new JButton("7");
		b8 = new JButton("8");
		b9 = new JButton("9");
		b0 = new JButton("0");
		initButtonList();

		bok = new JButton("OK");
		bclear = new JButton("Clear");
		bcancel = new JButton("Cancel");

		sub1 = new JPanel(new GridLayout(4, 3));
		sub1.add(b1);
		sub1.add(b2);
		sub1.add(b3);
		sub1.add(b4);
		sub1.add(b5);
		sub1.add(b6);
		sub1.add(b7);
		sub1.add(b8);
		sub1.add(b9);
		sub1.add(bclear);
		sub1.add(b0);
		sub1.add(bcancel);

		sub2 = new JPanel(new GridLayout(1, 1));
		sub2.add(bok);
		setState(0);
	}

	private void initButtonList() {
		bnumbers = new ArrayList<JButton>();
		bnumbers.add(b1);
		bnumbers.add(b2);
		bnumbers.add(b3);
		bnumbers.add(b4);
		bnumbers.add(b5);
		bnumbers.add(b6);
		bnumbers.add(b7);
		bnumbers.add(b8);
		bnumbers.add(b9);
		bnumbers.add(b0);
	}

	private void initListeners() {
		mouselistener = new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				switch (state) {
				case 1: 
					if (bnumbers.contains(e.getSource())) {
						pin += ((JButton) e.getSource()).getText();
						atm.dp.setOptions(atm.dp.getOptions() + "*");

					} else if (e.getSource() == bok) {
						try {
							if (pin.equals("")) {
								return;
							} else {
								
								ATMFrame.atm = ATMFrame.service.getService()
										.startWork(777,atm.crp.getCardnum(),
												Integer.parseInt(pin));
								if(ATMFrame.atm == null){
									JOptionPane.showMessageDialog(null,
											"Wrong pin. Try again");
								}
							}
							if (ATMFrame.atm == null) {
								atm.dp
										.setOptions("\n\n\n\n                    ");

							} else {
								setState(0);
								atm.dp.setState(2);
							}
						} catch (NumberFormatException | RemoteException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						pin = "";

					} else if (e.getSource() == bclear) {
						pin = "";
						atm.dp.setOptions("\n\n\n\n                    ");
					}
					break;
				case 2:
					if (e.getSource() == bcancel) {
						setState(0);
						atm.dp.setState(2);
					}
					break;
				case 3:
					if (bnumbers.contains(e.getSource())) {
						sum += ((JButton) e.getSource()).getText();
						atm.dp.setOptions(atm.dp.getOptions()
								+ ((JButton) e.getSource()).getText());
					}
					if (e.getSource() == bok) {
						int s = Integer.parseInt(sum);
						if (ATMFrame.atm.getCreditAccount() != null) {
							try {
								int accnum = ATMFrame.atm.getCreditAccount()
										.getAccountNumber();
								if(ATMFrame.service.getService().withdrawMoney(
										accnum, s)){
								JOptionPane.showMessageDialog(null,
										"Successful operation");}
								else{
									JOptionPane.showMessageDialog(null,
											"Not enough money");
								}
								atm.cp.print(ATMFrame.service.getService()
										.printCheck(ATMFrame.atm.getAtmId(),
												accnum));
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}

						if (ATMFrame.atm.getDebitAccount() != null) {
							try {
								int accnum = ATMFrame.atm.getDebitAccount()
										.getAccountNumber();
								if(ATMFrame.service.getService().withdrawMoney(
										accnum, s)){
								JOptionPane.showMessageDialog(null,
										"Successful operation");
								}
								else{
									JOptionPane.showMessageDialog(null,
											"Not enough money");
								}
								atm.cp.print(ATMFrame.service.getService()
										.printCheck(ATMFrame.atm.getAtmId(),
												accnum));
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						setState(0);
						atm.dp.setState(2);
						sum = "";
					}
					if (e.getSource() == bcancel) {
						setState(0);
						atm.dp.setState(2);
						sum = "";
					}
					if (e.getSource() == bclear) {
						sum = "";
						atm.dp.setOptions("\n\n\n\n                    ");
					}
					break;
				case 4:
					if (bnumbers.contains(e.getSource())) {
						acc += ((JButton) e.getSource()).getText();
						atm.dp.setOptions(atm.dp.getOptions()
								+ ((JButton) e.getSource()).getText());
					}
					if (e.getSource() == bok) {
						setState(5);
						atm.dp.setState(5);
					}
					if (e.getSource() == bclear) {
						acc = "";
						atm.dp.setOptions("\n\n\n\n                    ");
					}
					if (e.getSource() == bcancel) {
						setState(0);
						atm.dp.setState(2);
						acc = "";
					}
					break;
				case 5:
					if (bnumbers.contains(e.getSource())) {
						sum += ((JButton) e.getSource()).getText();
						atm.dp.setOptions(atm.dp.getOptions()
								+ ((JButton) e.getSource()).getText());
					}
					if (e.getSource() == bok) {
						if (acc.equals("") || sum.equals("")){
							JOptionPane.showMessageDialog(null, "Wrong input");
							setState(0);
							atm.dp.setState(2);
							sum = "";
							acc = "";
							return;
						}
						int accountFrom = 0;
						if (ATMFrame.atm.getCreditAccount() != null)
							accountFrom = ATMFrame.atm.getCreditAccount().getAccountNumber();
						else
							accountFrom = ATMFrame.atm.getDebitAccount().getAccountNumber();
						try {
							boolean op = ATMFrame.service.getService().transferMoney(accountFrom, Integer.parseInt(acc), Integer.parseInt(sum));
							if (op){
								JOptionPane.showMessageDialog(null, "Successful operation");
								atm.cp.print(ATMFrame.service.getService().printCheck(ATMFrame.atm.getAtmId(), accountFrom));
							}else{
								JOptionPane.showMessageDialog(null, "Unsuccessful operation");
							}
						} catch (NumberFormatException | RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setState(0);
						atm.dp.setState(2);
						sum = "";
						acc = "";
					}
					if (e.getSource() == bclear) {
						sum = "";
						atm.dp.setOptions("\n\n\n\n                    ");
					}
					if (e.getSource() == bcancel) {
						setState(0);
						atm.dp.setState(2);
						acc = "";
						sum = "";
					}
					break;
				default:
					//
				}
			}
		};
	}

	private void addMouseListeners() {
		for (JButton b : bnumbers)
			b.addMouseListener(mouselistener);
		bok.addMouseListener(mouselistener);
		bclear.addMouseListener(mouselistener);
		bcancel.addMouseListener(mouselistener);
	}

	private void initBorder() {
		keyboard = BorderFactory.createTitledBorder("Keyboard ");
		keyboard.setTitleColor(Color.BLACK);
		keyboard.setTitleJustification(TitledBorder.CENTER);
		keyboard.setTitleFont(new Font("Courier", Font.PLAIN, 16));
		keyboard.setBorder(BorderFactory.createLineBorder(
				Color.GRAY.brighter(), 2));

		setBorder(keyboard);

	}

	public void setState(int s) {
		switch (s) {
		case 0:
			for (JButton b : bnumbers)
				b.setEnabled(false);
			bok.setEnabled(false);
			bcancel.setEnabled(false);
			bclear.setEnabled(false);
			state = s;
			break;
		case 1:
			for (JButton b : bnumbers)
				b.setEnabled(true);
			bok.setEnabled(true);
			bcancel.setEnabled(false);
			bclear.setEnabled(true);
			state = s;
			break;
		case 2:
			for (JButton b : bnumbers)
				b.setEnabled(false);
			bok.setEnabled(false);
			bcancel.setEnabled(true);
			bclear.setEnabled(false);
			state = s;
			break;
		default:
			for (JButton b : bnumbers)
				b.setEnabled(true);
			bok.setEnabled(true);
			bcancel.setEnabled(true);
			bclear.setEnabled(true);
			state = s;
			break;
		}
	}

}
