import java.util.*;

public class GestionnaireStock {

    private List<Produit> stock = new ArrayList<>();

    public void ajouterProduit(Produit p) {
        stock.add(p);
    }

    public List<Produit> filtrerEtTrier(String categorie, double prixMax, int quantiteMin) {

        
        interface Filtre {
            boolean accepter(Produit p);
        }

        
        class FiltreCompose implements Filtre {
            public boolean accepter(Produit p) {
                return p.getCategorie().equalsIgnoreCase(categorie) &&
                       p.getPrix() <= prixMax &&
                       p.getQuantite() >= quantiteMin;
            }
        }

        FiltreCompose filtre = new FiltreCompose();
        List<Produit> resultat = new ArrayList<>();

        for (Produit p : stock) {
            if (filtre.accepter(p)) {
                resultat.add(p);
            }
        }

        resultat.sort((p1, p2) -> Double.compare(p1.getPrix(), p2.getPrix()));
        return resultat;
    }
}