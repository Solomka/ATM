package view;


import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import service.ATMClient;
import vo.ATM;

public class ATMFrame extends JFrame{
	
	private static final int width = 490;
	private static final int height = 400;
	
	public DisplayPanel dp;
	public KeyboardPanel kp;
	public CheckPanel cp;
	public CardreaderPanel crp;
	
	public static ATMClient service;
	
	private JPanel mainPanel;
	static ATM atm;
	private ChooseATMFrame caf;
	
	public ATMFrame(ATM atm, ChooseATMFrame caf) throws RemoteException, NotBoundException{
		this.atm = atm;
		this.caf = caf;
		setTitle("ATM");
		setSize(new Dimension(width, height));
		init();
		initListeners();
		getContentPane().add(mainPanel);
		setVisible(true);
	}

	private void init() throws RemoteException, NotBoundException {
		service = new ATMClient();
		dp = new DisplayPanel(this);
		kp = new KeyboardPanel(this);
		cp = new CheckPanel(this);
		crp = new CardreaderPanel(this);
		
		mainPanel = new JPanel(new GridLayout(2,2));
		
		mainPanel.add(dp);
		mainPanel.add(cp);
		mainPanel.add(kp);
		mainPanel.add(crp);
	}
	
	private void initListeners(){
		this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                caf.enableComboBox();
            }
        });
	}
	

}
