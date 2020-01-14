
public class IndividuImpl implements Individu {

	private String nom;
	
	private int age;
	
	public IndividuImpl(String nom, int age) {
		this.nom = nom;
		this.age = age;
	}
	
	public int age() {
		return this.age;
	}
	
	public String nom {
		return this.nom;
	}
	
	public void feter_anniversaire () {
		this.age ++;
	}
	
}
