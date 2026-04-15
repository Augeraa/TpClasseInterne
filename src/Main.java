import java.util.*;

public class Main {
    public static void main(String[] args) {

		/*public static void main(String[] args) {
	    Robot robot1 = new Robot("Axel", 30);
	
	    robot1.allumer();
	
	    robot1.afficherEtat();*/

        GestionnaireStock gs = new GestionnaireStock();

        // Ajout des produits
        Produit pc  = new Produit("Acer",    "Electronique", 1200, 5);
        Produit pc2 = new Produit("Acer2",   "Electronique", 1100, 5);
        Produit pc3 = new Produit("Dell",    "Electronique",  900, 3);
        Produit sac = new Produit("Sac",     "Maroquinerie",   50, 10); // ❌ mauvaise catégorie
        Produit pc4 = new Produit("HP",      "Electronique", 1500, 5);  // ❌ prix trop élevé
        Produit pc5 = new Produit("Lenovo",  "Electronique",  800, 1);  // ❌ quantité trop faible

        gs.ajouterProduit(pc);
        gs.ajouterProduit(pc2);
        gs.ajouterProduit(pc3);
        gs.ajouterProduit(sac);
        gs.ajouterProduit(pc4);
        gs.ajouterProduit(pc5);

       
        List<Produit> resultat = gs.filtrerEtTrier("Electronique", 1200, 3);

        System.out.println("=== Produits filtrés et triés par prix ===");
        for (Produit p : resultat) {
            System.out.println(p);
        }
    }
}


