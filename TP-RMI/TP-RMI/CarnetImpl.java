import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;
import java.

/** Implémentation basique d'un Carnet accessible à distance.
 *  Utilise une List(e) pour ranger les Individu(s).
 */
public class CarnetImpl extends UnicastRemoteObject implements Carnet {
    
    private List<Individu> contenu = new ArrayList<Individu>();


    public CarnetImpl() throws RemoteException {}

    /**** A COMPLETER ****/
    /** Insère l'Individu dans le carnet. */
    public void inserer (Individu x) throws RemoteException {
    	this.contenu.
    }

    /** Renvoie un Individu dont le nom correspond à <code>nom</code>,
     * ou lève l'exception <code>IndividuInexistant</code> s'il n'y en a pas. */
    public Individu chercher (String nom) throws RemoteException, IndividuInexistant;

    /** Renvoie le <code>n</code>-ième Individu du carnet,
     * ou lève <code>IndividuInexistant</code> s'il n'y en a pas. */
    public Individu get (int n) throws RemoteException, IndividuInexistant;

    /** Renvoie l'ensemble des Individu(s) contenus dans le carnet, sous
     * la forme d'un tableau. */
    public Individu[] getAll() throws RemoteException;

    /** Ajouter un callback pour être informé lors de la création d'un Individu. */
    public void addCallbackOnCreation(CallbackOnCreation cb) throws RemoteException;
    
    /** Enlever un callback. */
    public void removeCallbackOnCreation(CallbackOnCreation cb) throws RemoteException;

}
