package models.entities;

/*
 * Mère de toute entité rangée dans un DAO (s'il y a une mère abstraite, c'est
 * elle qui hérite). L'id est donné par le DAO : jamais de setId() à la main,
 * jamais l'id dans equals/hashCode.
 */
public abstract class AbstractEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
