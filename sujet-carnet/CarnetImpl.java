import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;


public class CarnetImpl extends UnicastRemoteObject implements Carnet {

	private HashMap<String, SFiche> carnet = new HashMap<String, SFiche>();
	
	private String nom;
	
	private static Carnet carnet2;
	
	public CarnetImpl(String nom) throws RemoteException {
		this.nom = nom;
		
	}

	public void Ajouter(SFiche sf) throws RemoteException {
		carnet.put(sf.getNom(), sf);
	}


	public RFiche Consulter(String n, boolean forward) throws RemoteException {
		RFiche fiche;
		
		if(carnet.containsKey(n)) {
			return fiche = new RFicheImpl(n, carnet.get(n).getEmail());
		}
		else {
			if(!forward) {
				return carnet2.Consulter(n, true);
			}
		}
		// Pas tres bon.
		return fiche = new RFicheImpl(n, carnet.get(n).getEmail());
	}
	
	public static void main(String args[]) throws MalformedURLException, RemoteException, AlreadyBoundException, NotBoundException {
		Naming.bind("//localhost:4000/" + args[0], new CarnetImpl(args[0]));
		Naming.bind("//localhost:4000/" + args[1], new CarnetImpl(args[1]));
		carnet2 = (Carnet) Naming.lookup("//localhost:4000/" + (args[1]));
	}

}
