
public class Robot {
	private String nom;
	private boolean allume = false;
	private int energie;
	
	public Robot (String nom, int energie) {
		this.nom = nom;
		this.energie = energie;
	}
	
	public void allumer() {
		allume= true;
	}
	
	public void eteindre() {
		allume= false;
	}
	
	public void getBras() {
		Bras Bras = new Bras();
		return;
	}
	
	public class Bras{
	   public void saisir(String objet) {
	   
	   if(allume && energie > 20) {
		   System.out.println(" Robot allumé");
		   energie -=10;
	   }
 }
	   
	   public void deposer(String objet) {
		   
		   if(allume){
			   System.out.println("Robot allumé");
			   energie -=5;
	}
}
		   
	 
	   
}
	


	
	  public void afficherEtat() {
		   System.out.println("Nom du robot :" + nom);
		   System.out.println("Etat : " + allume);
		   System.out.println("Energie : " + energie);
		   
	   }
		   


}


