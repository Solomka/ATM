package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class DisplayPanel extends JPanel {

	private JButton a1, a2, a3, a4;
	private JPanel as1, as2;
	private JTextArea prompt, options;
	private MouseListener mouseListener;
	private TitledBorder display;
	
	private ATMFrame atm;

	public int state = 0;

	public DisplayPanel(ATMFrame atm) {
		this.atm = atm;
		init();
		initBorder();
		initListeners();
		addMouseListeners();
		setLayout(new BorderLayout());
		add(prompt, BorderLayout.NORTH);
		add(as1, BorderLayout.WEST);
		add(as2, BorderLayout.EAST);
		add(options, BorderLayout.CENTER);
	}

	private void addMouseListeners() {
		a1.addMouseListener(mouseListener);
		a2.addMouseListener(mouseListener);
		a3.addMouseListener(mouseListener);
		a4.addMouseListener(mouseListener);
	}

	private void init() {
		a1 = new JButton(">");
		a2 = new JButton(">");
		a3 = new JButton("<");
		a4 = new JButton("<");
		prompt = new JTextArea();
		prompt.setEditable(false);
		options = new JTextArea();
		options.setEditable(false);

		a1.setContentAreaFilled(false);
		a2.setContentAreaFilled(false);
		a3.setContentAreaFilled(false);
		a4.setContentAreaFilled(false);

		as1 = new JPanel(new GridLayout(2, 1));
		as1.add(a1);
		as1.add(a2);

		as2 = new JPanel(new GridLayout(2, 1));
		as2.add(a3);
		as2.add(a4);

		setState(0);

	}

	private void initListeners() {
		mouseListener = new MouseListener() {
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
				case 2:
					if (e.getSource() == a1) {
						setState(6);
					}
					if (e.getSource() == a2) {
						setState(3);
						atm.kp.setState(4);
					}
					if (e.getSource() == a3) {
						if (ATMFrame.atm.getCreditAccount() != null) {
							try {
								String check = ATMFrame.service.getService()
										.printCheck(
												ATMFrame.atm.getAtmId(),
												ATMFrame.atm.getCreditAccount()
														.getAccountNumber());
								atm.cp.print(check);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} else if (ATMFrame.atm.getDebitAccount() != null) {
							try {
								String check = ATMFrame.service.getService()
										.printCheck(
												ATMFrame.atm.getAtmId(),
												ATMFrame.atm.getDebitAccount()
														.getAccountNumber());
								atm.cp.print(check);
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						} else {
							// exception
						}
					}
					if (e.getSource() == a4) {
						atm.cp.print("");
						atm.dp.setState(0);
						atm.kp.setState(0);
						atm.crp.setState(1);
						try {
							ATMFrame.service.getService().endWork(
									ATMFrame.atm.getAtmId());
						} catch (RemoteException | SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					break;
				case 6:
					int sum = 0;
					if (e.getSource() == a1)
						sum = 50;
					if (e.getSource() == a2)
						sum = 200;
					if (e.getSource() == a3)
						sum = 100;

					if (sum != 0 && ATMFrame.atm.getCreditAccount() != null) {
						try {
							int accnum = ATMFrame.atm.getCreditAccount()
									.getAccountNumber();
							ATMFrame.service.getService().withdrawMoney(accnum,
									sum);
							JOptionPane.showMessageDialog(null,
									"Successful operation");
							atm.cp
									.print(ATMFrame.service.getService()
											.printCheck(
													ATMFrame.atm.getAtmId(),
													accnum));
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setState(2);
						atm.kp.setState(0);
					}

					if (sum != 0 && ATMFrame.atm.getDebitAccount() != null) {
						try {
							int accnum = ATMFrame.atm.getDebitAccount()
									.getAccountNumber();
							ATMFrame.service.getService().withdrawMoney(accnum,
									sum);
							JOptionPane.showMessageDialog(null,
									"Successful operation");
							atm.cp
									.print(ATMFrame.service.getService()
											.printCheck(
													ATMFrame.atm.getAtmId(),
													accnum));
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						setState(2);
						atm.kp.setState(0);
					}

					if (e.getSource() == a4) {
						setState(4);
						atm.kp.setState(3);
					}

				}
			}

		};
	}

	private void initBorder() {
		display = BorderFactory.createTitledBorder("Display ");
		display.setTitleColor(Color.BLACK);
		display.setTitleJustification(TitledBorder.CENTER);
		display.setTitleFont(new Font("Courier", Font.PLAIN, 16));
		display.setBorder(BorderFactory.createLineBorder(Color.GRAY.brighter(),
				2));

		setBorder(display);

	}

	public void setState(int s) {
		switch (s) {
		case 0:
			prompt.setText("");
			setOptions("");
			state = s;
			a1.setEnabled(false);
			a2.setEnabled(false);
			a3.setEnabled(false);
			a4.setEnabled(false);
			break;
		case 1:
			state = s;
			a1.setEnabled(true);
			a2.setEnabled(true);
			a3.setEnabled(true);
			a4.setEnabled(true);
			prompt.setText("Enter pin: ");
			setOptions("\n\n\n\n                    ");
			break;
		case 2:
			state = s;
			a1.setEnabled(true);
			a2.setEnabled(true);
			a3.setEnabled(true);
			a4.setEnabled(true);
			prompt.setText("Select action: ");
			setOptions("\n\n withdraw               balance \n\n\n\n transfer                     finish ");
			break;
		case 3:
			state = s;
			a1.setEnabled(false);
			a2.setEnabled(false);
			a3.setEnabled(false);
			a4.setEnabled(false);
			prompt.setText("Enter card number: ");
			setOptions("\n\n\n\n                    ");
			break;
		case 4:
			prompt.setText("Enter sum: ");
			setOptions("\n\n\n\n                    ");
			a1.setEnabled(false);
			a2.setEnabled(false);
			a3.setEnabled(false);
			a4.setEnabled(false);
			break;
		case 5:
			prompt.setText("Enter sum: ");
			setOptions("\n\n\n\n                    ");
			a1.setEnabled(false);
			a2.setEnabled(false);
			a3.setEnabled(false);
			a4.setEnabled(false);
			break;
		case 6:
			state = s;
			atm.kp.setState(2);
			a1.setEnabled(true);
			a2.setEnabled(true);
			a3.setEnabled(true);
			a4.setEnabled(true);
			prompt.setText("Select sum: ");
			setOptions("\n\n  50                             100 \n\n\n\n  200                other sum ");
			break;
		default:

		}

	}

	public String getOptions() {
		return options.getText();
	}

	public void setOptions(String string) {
		options.setText(string);
		options.updateUI();
	}
}
