
public class Produit {
	
	private String nom;
	private String categorie;
	private double prix;
	private int quantite;
	
	
	public Produit(String nom, String categorie, double prix, int quantite) {
		this.nom = nom;
		this.categorie = categorie;
		this.prix = prix;
		this.quantite = quantite;
	}
	
	
	
	public String toString() {
        return "Produit{" +
                "nom='" + nom + '\'' +
                ", categorie='" + categorie + '\'' +
                ", prix=" + prix +
                "€, quantite=" + quantite +
                '}';
	
	
	

}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getCategorie() {
		return categorie;
	}



	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}



	public double getPrix() {
		return prix;
	}



	public void setPrix(double prix) {
		this.prix = prix;
	}



	public int getQuantite() {
		return quantite;
	}



	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
}
