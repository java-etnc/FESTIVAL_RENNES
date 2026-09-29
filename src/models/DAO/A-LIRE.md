# Les DAO — ce qui est déjà fait, ce que tu écris le jour J

## Déjà fait, tu n'y touches pas

| Fichier | Rôle |
|---|---|
| `entities/AbstractEntity` | la mère qui porte l'`id` |
| `DAO/Dao<T>` | le contrat CRUD : `create`, `read`, `readAll`, `update`, `delete`, `exist`, `count` |
| `DAO/MemoirDao<T>` | le CRUD en mémoire : il range, il numérote, il rend |

## Ce que tu écris : 2 fichiers par entité + 1 ligne dans la façade

Exemple avec `Club`. Recopie-le en changeant le nom de l'entité.

### 0. L'entité hérite de `AbstractEntity`

```java
public class Club extends AbstractEntity { ... }
```

Sans ça, `MemoirDao<Club>` ne compile pas (`T extends AbstractEntity`).

### 1. `IDAOClub` : l'interface du DAO spécifique

```java
package models.DAO;

import models.entities.Club;

public interface IDAOClub extends Dao<Club> {
    // les requêtes PROPRES au club, s'il y en a (sinon l'interface reste vide)
}
```

### 2. `DAOClubImpl` : l'implémentation

```java
package models.DAO;

import models.entities.Club;

public class DAOClubImpl extends MemoirDao<Club> implements IDAOClub {
    // le CRUD est hérité de MemoirDao : rien d'autre à écrire
}
```

**`extends` d'abord, `implements` ensuite.** C'est l'ordre imposé par Java.

### 3. La façade crée le DAO et s'en sert pour stocker

```java
public class FacadeModel implements IFacadeModel {

    private IDAOClub daoClub = new DAOClubImpl();

    public boolean ajouterClub(Club club) {
        if (daoClub.readAll().contains(club)) {   // unicité = equals de Club
            return false;
        }
        daoClub.create(club);
        return true;
    }

    public List<Club> getClubs() {
        return new ArrayList<>(daoClub.readAll());   // Collection -> List pour le menu
    }
}
```

Le champ est déclaré avec le type de **l'interface** (`IDAOClub`), le `new` utilise
**l'implémentation** (`DAOClubImpl`). C'est le même geste que
`IFacadeModel facadeModel = new FacadeModel()`.

---

## Une requête spécifique (filtrer, chercher)

Elle se déclare dans `IDAOXxx` et s'écrit dans `DAOXxxImpl`, en parcourant
`persist.values()` (hérité, `protected`).

```java
// IDAOJoueur
List<Joueur> readByPoste(Poste poste);

// DAOJoueurImpl
@Override
public List<Joueur> readByPoste(Poste poste) {
    List<Joueur> resultat = new ArrayList<>();
    for (Joueur joueur : persist.values()) {
        if (joueur.getPoste() == poste) {
            resultat.add(joueur);
        }
    }
    return resultat;
}
```

C'est exactement le filtre `getListeProduitsBruts()` que tu n'avais pas su faire seul
le 14/09 : une liste vide, un `for`, un `if`, un `add`. Pour filtrer sur une
**classe fille** : `if (produit instanceof ProduitBrut) { resultat.add((ProduitBrut) produit); }`.

---

## LA règle : un seul DAO par entité, créé dans la façade

Chaque `new DAOClubImpl()` crée un **nouveau stockage vide**. Deux `new` = deux
listes de clubs différentes, et ce que tu ranges dans l'une n'est pas dans l'autre.

- Les DAO sont créés **une seule fois**, en champ de `FacadeModel`, avec `new`.
- Pas de `new DAOXxxImpl()` ailleurs, pas de `getInstance()`.
- Une requête qui mélange deux entités (« les musiques d'un auteur ») s'écrit
  **dans la façade**, qui a les deux DAO sous la main, et pas dans un DAO qui
  irait chercher l'autre DAO.

C'est le bug de `youtube_musique` : `FacadeModel` fait `new DAOMusiqueImpl()` et
`DAOAuteurImpl` passe par `DAOMusiqueImpl.getInstance()`. Ce sont deux stockages
différents. En plus, ce `getInstance()` renvoie `null` au premier appel
(`instance` n'est rempli que dans le `else`).

---

## Pièges

1. Entité qui n'hérite pas de `AbstractEntity` → `MemoirDao<Club>` ne compile pas.
2. `id` coché dans `equals`/`hashCode` → deux clubs identiques ont deux id
   différents, donc ne sont jamais égaux : l'unicité ne marche plus.
3. `setId()` appelé à la main : c'est `create()` qui numérote.
4. `readAll()` est protégé : `daoClub.readAll().add(...)` lève
   `UnsupportedOperationException`. On ajoute par `create()`.
5. `readAll()` rend une `Collection`, pas une `List` : pas de `.get(i)`. Pour un
   menu, `new ArrayList<>(daoClub.readAll())`.
6. L'ordre de `readAll()` n'est pas garanti. Si l'affichage doit être dans un
   ordre précis, trie la `List` dans la façade.
