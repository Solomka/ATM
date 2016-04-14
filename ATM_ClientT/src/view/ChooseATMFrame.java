package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;

import service.ATMClient;
import vo.ATM;

public class ChooseATMFrame extends JFrame {

	private static final int width = 250;
	private static final int height = 150;

	private ATMClient service;

	private JLabel label;
	private JComboBox<String> combobox;
	private JButton ok;

	ChooseATMFrame() {
		setTitle("Choose ATM");
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		init();
		initListeners();
		getContentPane().setLayout(new GridLayout(3,1));
		getContentPane().add(label);
		getContentPane().add(combobox);
		
		JPanel flowNorth = new JPanel(); // defaults to centered FlowLayout
		flowNorth.add(ok);
		getContentPane().add(BorderLayout.CENTER, flowNorth);

		setVisible(true);
	}

	private void init() {
		List<ATM> atms = new ArrayList<ATM>();
		try {
			service = new ATMClient();
			atms = service.getService().getAllATMs();
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		label = new JLabel("Choose address of an ATM: ");
		combobox = new JComboBox<String>();
		ok=new JButton("Ok");
		
		Iterator<ATM> iter = atms.iterator();
		while (iter.hasNext())
			combobox.addItem(iter.next().getAddress());
	}

	private void initListeners() {
		ok.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					ATM atm = service.getService().getATM((String)combobox.getSelectedItem());
					new ATMFrame(atm, ChooseATMFrame.this);
					combobox.setEnabled(false);
					ok.setEnabled(false);
				} catch (RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

		});
	}
	
	public void enableComboBox(){
		combobox.setEnabled(true);
		ok.setEnabled(true);
	}

}
