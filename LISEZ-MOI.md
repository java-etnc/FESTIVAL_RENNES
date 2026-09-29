# SOCLE JAVA BASE V2 — la seule fiche

## 1. Démarrage (2 min)

1. Copier le dossier, le renommer `<GRADE>_<NOM>_TEST_INT_JAVABASE_<code>`.
2. IntelliJ > Open, SDK 21, `ProgMain` > Run : le menu s'affiche.
3. Remplacer `AffichageConsole` et `LectureConsole` par ceux du formateur. **Garder `ViewUtils`.**

## 2. L'ordre d'attaque

| Quand | Quoi |
|---|---|
| **P0** — 10 min | Remplir `REGLES-DU-SUJET.md` : règles de la dernière page, phrases d'unicité, menu, **la règle qui lèvera l'exception** |
| **P1** | Enums (`models/references`) → entités → `Factory` → DAO → façade modèle |
| **P2** | `ConstantesView` (menu), `initData()`, une méthode privée par cas de menu |
| **P3** | Vue : une méthode publique par sous-menu, qui renvoie l'objet |
| **3 h 45** | Checklist de rendu en bas de `REGLES-DU-SUJET.md` |

## 3. Les modèles à recopier

### Entité

1. `extends AbstractEntity` (ou la mère le fait).
2. Attributs privés, collections créées à la déclaration : `private Set<Joueur> joueurs = new HashSet<>();`
3. Constructeur vide `protected`.
4. Getters/setters, **sauf** setter de collection. Getter : `return Collections.unmodifiableSet(joueurs);`
5. `ajouterJoueur()` / `supprimerJoueur()` qui renvoient `joueurs.add(j)` / `joueurs.remove(j)`.
6. `equals`/`hashCode` générés sur les champs de la phrase d'unicité (**pas l'id**).
7. Plafonds en `public static final int MAX_JOUEURS = 18;`
8. Affichage : `public String toString() { return getDescription(); }` (c'est ce qu'affiche `ViewUtils`).

Mère abstraite : `public abstract String getDescription();` dans la mère, `@Override` dans chaque fille,
une `createXxx()` par fille dans la `Factory`, jamais de `createMere()`.

### Enum avec libellé

```java
public enum Niveau {
    LIGUE_1("Ligue 1"), LIGUE_2("Ligue 2");

    private final String libelle;
    Niveau(String libelle) { this.libelle = libelle; }
    public String getLibelle() { return libelle; }
    @Override public String toString() { return libelle; }
}
```

### DAO : 2 fichiers par entité dans `models/DAO` + 1 champ dans la façade

```java
public interface IDAOClub extends Dao<Club> {
}

public class DAOClubImpl extends MemoirDao<Club> implements IDAOClub {
}
```

```java
// FacadeModel
private IDAOClub daoClub = new DAOClubImpl();     // une seule fois, ici

public boolean ajouterClub(Club club) {
    if (daoClub.readAll().contains(club)) {        // unicité = equals
        return false;
    }
    daoClub.create(club);                          // create() donne l'id
    return true;
}

public List<Club> getClubs() {
    return new ArrayList<>(daoClub.readAll());     // Collection -> List pour le menu
}

public List<Equipe> getEquipesDu(Club club) {
    return new ArrayList<>(club.getEquipes());     // Set -> List pour le menu
}
```

Requête spécifique (filtre) : déclarée dans `IDAOXxx`, écrite dans `DAOXxxImpl`.

```java
public List<ProduitBrut> readProduitsBruts() {
    List<ProduitBrut> resultat = new ArrayList<>();
    for (Produit produit : persist.values()) {
        if (produit instanceof ProduitBrut) {
            resultat.add((ProduitBrut) produit);
        }
    }
    return resultat;
}
```

Une requête qui mélange deux entités s'écrit dans la **façade**, pas dans un DAO.

### Exception : au moins une, levée et attrapée

```java
// FacadeModel (et la même signature avec throws dans IFacadeModel)
public void ajouterJoueurDansClub(Club club, Joueur joueur) throws ModelFacadeException {
    if (club.getJoueurs().size() >= Club.MAX_JOUEURS) {
        throw new ModelFacadeException("Ce club a deja " + Club.MAX_JOUEURS + " joueurs.");
    }
    club.ajouterJoueur(joueur);
}

// Presenter
try {
    facadeModel.ajouterJoueurDansClub(club, joueur);
    facadeView.afficherMessage("Joueur ajoute.");      // le succès DANS le try
} catch (ModelFacadeException e) {
    facadeView.afficherErreur(e.getMessage());
}

// initData() : les données de test sont censées être valides
catch (ModelFacadeException e) { throw new RuntimeException(e); }
```

### Vue : une ligne par sous-menu grâce à `ViewUtils`

| Besoin | Dans la vue |
|---|---|
| choisir dans un enum | `return ViewUtils.choixEnumMenu("Niveau :", Niveau.class);` |
| choisir un objet | `return ViewUtils.choixDansListe("Club :", clubs);` → `null` si liste vide |
| sélection multiple | `return ViewUtils.choixMultipleDansListe("Joueurs :", disponibles);` |
| oui / non | `ViewUtils.confirmer("Titulaire ?")` |
| afficher une liste | `facadeView.afficherListe("Joueurs :", joueurs);` (depuis le Presenter) |

Côté Presenter, navigation à deux niveaux :

```java
Club club = facadeView.choisirClub(facadeModel.getClubs());
if (club == null) {
    facadeView.afficherErreur("Aucun club enregistre.");
    return;
}
Equipe equipe = facadeView.choisirEquipe(facadeModel.getEquipesDu(club));   // les enfants DU parent choisi
if (equipe == null) {
    facadeView.afficherErreur("Ce club n'a aucune equipe.");
    return;
}
```

## 4. Les pièges qui ont déjà coûté cher

| Symptôme | Cause |
|---|---|
| `NullPointerException` | une collection ou un DAO jamais `new`, ou un `choisirXxx` qui a renvoyé `null` |
| `StackOverflowError` | un getter qui s'appelle lui-même au lieu de lire le champ |
| `UnsupportedOperationException` | `add` sur un `readAll()` ou un getter protégé |
| unicité qui ne marche pas | l'id coché dans `equals`, ou `equals` pas généré |
| cas 1 qui exécute aussi le cas 2 | `break` oublié dans le `switch` |
| prix à 0.0 partout | un setter oublié dans la `Factory` ou `initData()` |
| deux stockages différents | un `new DAOXxxImpl()` ailleurs que dans `FacadeModel` |
| 0 point sur les règles | `REGLES-DU-SUJET.md` pas rempli à la minute 10 |
