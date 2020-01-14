/* ------------------------------------------------------- 
		Les packages Java qui doivent etre importes.
*/
import java.lang.*;
import java.net.MalformedURLException;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.rmi.*;
import javax.swing.*;



/* ------------------------------------------------------- 
		Implementation de l'application
*/

public class Saisie extends JApplet {
	private static final long serialVersionUID = 1;
	TextField nom, email;
	Choice carnets;
	Label message;
	private static Carnet RRcarnet1;
	private static Carnet RRcarnet2;

	public void init() {
		setSize(300,200);
		setLayout(new GridLayout(6,2));
		add(new Label("  Nom : "));
		nom = new TextField(30);
		add(nom);
		add(new Label("  Email : "));
		email = new TextField(30);
		add(email);
		add(new Label("  Carnet : "));
		carnets = new Choice();
		carnets.addItem("Carnet1");
		carnets.addItem("Carnet2");
		add(carnets);
		add(new Label(""));
		add(new Label(""));
		Button Abutton = new Button("Ajouter");
		Abutton.addActionListener(new AButtonAction());
		add(Abutton);
		Button Cbutton = new Button("Consulter");
		Cbutton.addActionListener(new CButtonAction());
		add(Cbutton);
		message = new Label();
		add(message);
	}

	// La reaction au bouton Consulter
	class CButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String n, c;
			n = nom.getText();
			c = carnets.getSelectedItem();
			message.setText("Consulter("+n+","+c+")        ");
			// DO IT
			try {

				if(carnets.equals("Carnet1")) {
					RFiche rf = RRcarnet1.Consulter(n, false);
					email.setText(rf.getEmail());
				}
				else if(carnets.equals("Carnet2")) {
					RFiche rf = RRcarnet2.Consulter(n, false);
					email.setText(rf.getEmail());
				}
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// La reaction au bouton Ajouter
	class AButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent ae) {
			String n, e, c;
			n = nom.getText();
			e = email.getText();
			c = carnets.getSelectedItem();
			message.setText("Ajouter("+n+","+e+","+c+")        ");
			SFiche sf = new SFicheImpl(n, e);
			try {
				if(carnets.equals("Carnet1")) {
					RRcarnet1.Ajouter(sf);
				}
				else if(carnets.equals("Carnet2")) {
					RRcarnet1.Ajouter(sf);
				}
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) throws MalformedURLException, RemoteException, NotBoundException {
		Saisie a = new Saisie();
	  	RRcarnet1 = (Carnet) Naming.lookup("//localhost:4000/C1");
	  	RRcarnet2 = (Carnet) Naming.lookup("//localhost:4000/C2");
		a.init();
		a.start();
		JFrame frame = new JFrame("Applet");
		frame.setSize(400,200);
	  	frame.getContentPane().add(a);
	  	frame.setVisible(true);
	}
	
}


