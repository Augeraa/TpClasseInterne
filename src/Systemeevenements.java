import java.util.*;

// ============================================================
// PARTIE A - Interfaces fonctionnelles
// ============================================================

@FunctionalInterface
interface ClickListener {
    void onClick(int x, int y);
}

@FunctionalInterface
interface KeyListener {
    void onKeyPress(char touche);
}

@FunctionalInterface
interface HoverListener {
    void onHover(boolean entre);
}

// ============================================================
// Classe Bouton
// ============================================================

class Bouton {
    private String label;
    private List<ClickListener> clickListeners = new ArrayList<>();
    private List<KeyListener>  keyListeners   = new ArrayList<>();
    private List<HoverListener> hoverListeners = new ArrayList<>();

    public Bouton(String label) {
        this.label = label;
    }

    // --- Ajout de listeners ---
    public void addClickListener(ClickListener l)  { clickListeners.add(l); }
    public void addKeyListener(KeyListener l)       { keyListeners.add(l); }
    public void addHoverListener(HoverListener l)   { hoverListeners.add(l); }

    // --- Suppression de listeners (Question Bonus) ---
    public void removeClickListener(ClickListener l)  { clickListeners.remove(l); }
    public void removeKeyListener(KeyListener l)       { keyListeners.remove(l); }
    public void removeHoverListener(HoverListener l)   { hoverListeners.remove(l); }

    // --- Simulation d'événements ---
    public void simulerClic(int x, int y) {
        System.out.println("\n[" + label + "] Clic simulé en (" + x + ", " + y + ")");
        for (ClickListener l : clickListeners) l.onClick(x, y);
    }

    public void simulerTouche(char c) {
        System.out.println("\n[" + label + "] Touche simulée : '" + c + "'");
        for (KeyListener l : keyListeners) l.onKeyPress(c);
    }

    public void simulerSurvol(boolean entre) {
        System.out.println("\n[" + label + "] Survol simulé : " + (entre ? "entrée" : "sortie"));
        for (HoverListener l : hoverListeners) l.onHover(entre);
    }
}



public class Systemeevenements {

    
    // PARTIE A 
    
    public static void main(String[] args) {
        Bouton btn = new Bouton("Valider");

        // Compteur partagé (doit être effectivement final → tableau à 1 case)
        final int[] compteur = {0};

        // ClickListener 1 : affiche les coordonnées
        btn.addClickListener(new ClickListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("  [ClickListener 1] Clic aux coordonnées (" + x + ", " + y + ")");
            }
        });

        // ClickListener 2 : incrémente un compteur
        btn.addClickListener(new ClickListener() {
            @Override
            public void onClick(int x, int y) {
                compteur[0]++;
                System.out.println("  [ClickListener 2] Nombre total de clics : " + compteur[0]);
            }
        });

        // KeyListener : distingue voyelles et consonnes
        btn.addKeyListener(new KeyListener() {
            private final String VOYELLES = "aeiouAEIOU";

            @Override
            public void onKeyPress(char touche) {
                if (VOYELLES.indexOf(touche) >= 0) {
                    System.out.println("  [KeyListener] '" + touche + "' est une VOYELLE");
                } else {
                    System.out.println("  [KeyListener] '" + touche + "' est une CONSONNE");
                }
            }
        });

        // HoverListener : affiche une info-bulle fictive
        btn.addHoverListener(new HoverListener() {
            @Override
            public void onHover(boolean entre) {
                if (entre) {
                    System.out.println("  [HoverListener] *** Info-bulle : \"Cliquez pour valider votre saisie\" ***");
                } else {
                    System.out.println("  [HoverListener] Info-bulle masquée.");
                }
            }
        });

        // --- Simulation des interactions ---
        System.out.println("=== PARTIE A : Classes anonymes ===");
        btn.simulerSurvol(true);
        btn.simulerClic(150, 300);
        btn.simulerClic(160, 305);
        btn.simulerTouche('a');
        btn.simulerTouche('b');
        btn.simulerTouche('E');
        btn.simulerSurvol(false);

        System.out.println("\n");
        mainLambda(args);

        System.out.println("\n");
        bonusRemoveListener();
    }

    // --------------------------------------------------------
    // PARTIE B - main avec lambdas
    // --------------------------------------------------------
    public static void mainLambda(String[] args) {
        System.out.println("=== PARTIE B : Lambdas ===");

        Bouton btn = new Bouton("Valider");
        final int[] compteur = {0};
        final String VOYELLES = "aeiouAEIOU";

        // ClickListener 1 : lambda — affiche les coordonnées
        btn.addClickListener((x, y) ->
            System.out.println("  [ClickListener 1] Clic aux coordonnées (" + x + ", " + y + ")")
        );

        // ClickListener 2 : lambda — incrémente le compteur
        btn.addClickListener((x, y) -> {
            compteur[0]++;
            System.out.println("  [ClickListener 2] Nombre total de clics : " + compteur[0]);
        });

        // KeyListener : lambda — distingue voyelles et consonnes
        btn.addKeyListener(touche -> {
            if (VOYELLES.indexOf(touche) >= 0) {
                System.out.println("  [KeyListener] '" + touche + "' est une VOYELLE");
            } else {
                System.out.println("  [KeyListener] '" + touche + "' est une CONSONNE");
            }
        });

        // HoverListener : lambda — info-bulle fictive
        btn.addHoverListener(entre -> {
            if (entre) {
                System.out.println("  [HoverListener] *** Info-bulle : \"Cliquez pour valider votre saisie\" ***");
            } else {
                System.out.println("  [HoverListener] Info-bulle masquée.");
            }
        });

        // --- Simulation ---
        btn.simulerSurvol(true);
        btn.simulerClic(150, 300);
        btn.simulerClic(160, 305);
        btn.simulerTouche('a');
        btn.simulerTouche('b');
        btn.simulerSurvol(false);
    }

    // --------------------------------------------------------
    // QUESTION BONUS — removeListener et problème avec les lambdas
    // --------------------------------------------------------
    public static void bonusRemoveListener() {
        System.out.println("=== BONUS : removeListener ===");

        Bouton btn = new Bouton("Valider");

        // ---- Approche 1 : Lambda anonyme → IMPOSSIBLE à supprimer ----
        //
        // btn.addClickListener((x, y) -> System.out.println("lambda anonyme"));
        // btn.removeClickListener((x, y) -> System.out.println("lambda anonyme")); // ← NE FONCTIONNE PAS
        //
        // Chaque expression lambda crée une NOUVELLE instance d'objet.
        // List.remove() utilise equals(), qui compare les références.
        // Les deux lambdas ci-dessus sont deux objets distincts → remove() ne supprime rien.

        // ---- Approche 2 : Stocker la référence du lambda → fonctionne ----
        ClickListener listenerASupprimer = (x, y) ->
            System.out.println("  [Listener temporaire] Clic en (" + x + ", " + y + ")");

        btn.addClickListener(listenerASupprimer);
        System.out.println("Avant suppression :");
        btn.simulerClic(10, 20);

        btn.removeClickListener(listenerASupprimer); // fonctionne : même référence
        System.out.println("Après suppression :");
        btn.simulerClic(10, 20); // aucun listener actif → rien affiché

        // ---- Approche 3 (alternative) : classe anonyme → remove() fiable ----
        // Avec une classe anonyme stockée dans une variable, le comportement est identique.
        // L'avantage d'une VRAIE classe (non-anonyme, nommée) est qu'elle peut
        // surcharger equals()/hashCode() pour un remove() plus souple.

        System.out.println("\n[Explication] : voir commentaires dans le code source.");
    }
}

/*
 *
 * PARTIE C — Questions de réflexion
 * 
 *
 * Question 1
 * L'annotation @FunctionalInterface est-elle obligatoire ?
 *
 * NON, elle n'est pas obligatoire. Une interface qui possède exactement
 * une méthode abstraite est déjà une interface fonctionnelle aux yeux
 * du compilateur, qu'elle soit annotée ou non.
 *
 * Son rôle exact :
 *   1. Documentation : signale explicitement l'intention de l'auteur.
 *   2. Vérification à la compilation : si l'on ajoute accidentellement
 *      une deuxième méthode abstraite, le compilateur produit une erreur :
 *      "Unexpected @FunctionalInterface annotation;
 *       [interface] is not a functional interface".
 *      Sans l'annotation, aucune erreur n'est levée, mais les lambdas
 *      ne peuvent plus être assignées à cette interface.
 *
 *
 *
 * ── Question 2 ──────────────────────────────────────────────
 * Deux cas où la classe anonyme reste préférable à un lambda en Java 8+
 *
 * CAS 1 — L'interface n'est PAS fonctionnelle (plusieurs méthodes abstraites)
 *
 *   interface Forme {
 *       double aire();
 *       double perimetre();   // 2ème méthode → lambda impossible
 *   }
 *
 *   // Obligatoirement une classe anonyme :
 *   Forme cercle = new Forme() {
 *       @Override public double aire()      { return Math.PI * 5 * 5; }
 *       @Override public double perimetre() { return 2 * Math.PI * 5; }
 *   };
 *
 * CAS 2 — Besoin d'un état interne propre à l'instance
 *
 *   // Un lambda ne peut capturer que des variables effectivement finales
 *   // de la portée englobante. Il ne peut pas déclarer ses propres champs.
 *
 *   ClickListener compteurPropre = new ClickListener() {
 *       private int clics = 0;   // état propre, impossible en lambda
 *       @Override
 *       public void onClick(int x, int y) {
 *           clics++;
 *           System.out.println("Clics sur CE bouton : " + clics);
 *       }
 *   };
 *
 *   // En lambda, on contourne avec un tableau ou un AtomicInteger
 *   // dans la portée englobante, ce qui est moins encapsulé et moins lisible.
 *
 *

 */
