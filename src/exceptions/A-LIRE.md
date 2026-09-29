# Les exceptions — lever, déclarer, attraper

Le formateur veut voir **au moins une exception levée et attrapée**, pour vérifier
que tu as compris le chemin. Choisis une règle métier de l'énoncé et fais-la passer
par une exception au lieu d'un simple `if` dans le présenteur.

`exceptions/models/ModelFacadeException` est déjà écrite. Tu n'as que 3 gestes à faire.

## Le chemin complet, sur un exemple

Règle de l'énoncé : « un club a au maximum 18 joueurs ».

### 1. La façade modèle **lève** l'exception quand la règle est violée

```java
@Override
public void ajouterJoueurDansClub(Club club, Joueur joueur) throws ModelFacadeException {
    if (club.getJoueurs().size() >= Club.MAX_JOUEURS) {
        throw new ModelFacadeException("Ce club a deja " + Club.MAX_JOUEURS + " joueurs.");
    }
    club.ajouterJoueur(joueur);
}
```

`throw` (sans s) : je lance l'exception maintenant. La méthode s'arrête là, la
ligne `ajouterJoueur` n'est jamais exécutée.

### 2. La signature la **déclare**, dans l'interface ET dans la classe

```java
// IFacadeModel
void ajouterJoueurDansClub(Club club, Joueur joueur) throws ModelFacadeException;
```

`throws` (avec s) : « cette méthode peut lancer ça ». Oublié dans l'interface →
erreur de compilation dans `FacadeModel` (« overridden method does not throw »).

### 3. Le présenteur **attrape** et fait afficher par la vue

```java
private void ajouterJoueurDansClub() {
    Club club = facadeView.choisirClub(facadeModel.getClubs());
    Joueur joueur = facadeView.saisirJoueur();
    try {
        facadeModel.ajouterJoueurDansClub(club, joueur);
        facadeView.afficherMessage("Joueur ajoute.");
    } catch (ModelFacadeException e) {
        facadeView.afficherErreur(e.getMessage());
    }
}
```

Le message de succès est **dans le `try`**, après l'appel : si l'exception part,
on saute directement au `catch` et le succès ne s'affiche pas.

## Les 3 mots à ne pas confondre

| Mot | Où | Veut dire |
|---|---|---|
| `throw new XxxException("...")` | dans le corps de la méthode | je lance l'exception maintenant |
| `throws XxxException` | dans la signature | cette méthode peut la lancer |
| `try { } catch (XxxException e) { }` | chez l'appelant (le présenteur) | je l'attrape et je la traite |

## Les autres bons candidats pour une exception

- **Liste vide avant un choix** : `getClubsDisponibles()` lève « Aucun club
  enregistré » au lieu de renvoyer une liste vide (fait dans `youtube_musique`).
- **Doublon** : `ajouterClub()` lève « Ce club existe déjà » au lieu de renvoyer
  `false`.
- **Plafond d'un conteneur** : 4 comptoirs max par catégorie, 100 produits max
  par comptoir.

Une ou deux exceptions bien faites valent mieux que des exceptions partout. Le
reste des règles peut rester en `if` + `afficherErreur` + `return`.

## Dans `initData()`

`initData()` appelle des méthodes qui déclarent `throws` : le compilateur t'oblige
à les entourer. Les données de test sont censées être valides, donc :

```java
try {
    facadeModel.ajouterJoueurDansClub(psg, mbappe);
} catch (ModelFacadeException e) {
    throw new RuntimeException(e);   // si ça part ici, c'est ton jeu de données qui est faux
}
```

## Pièges

1. `catch (Exception e)` : attrape tout, y compris tes vrais bugs (un
   `NullPointerException` s'affichera comme un message métier). Attrape **ton**
   exception.
2. `catch` vide `{ }` : l'erreur disparaît et l'utilisateur ne voit rien. Toujours
   `facadeView.afficherErreur(e.getMessage())`.
3. `System.out.println` dans le `catch` : l'affichage passe par la vue.
4. `throw` dans la vue ou le `try/catch` dans la façade : on lève en bas (modèle), on
   attrape en haut (présenteur).
