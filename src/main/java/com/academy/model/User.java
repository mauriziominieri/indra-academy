package com.academy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Entity
@Table(name = "USERS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

/*
L'eccezione "org.hibernate.LazyInitializationException: failed to lazily initialize a collection, could not initialize proxy - no Session" si verifica quando si tenta di accedere a una collezione lazily loaded (cioè caricata in modo differito) e la sessione Hibernate è stata chiusa o non è più disponibile.
In altre parole, quando si elimina un'entità in una relazione many-to-many, Hibernate deve anche eliminare le corrispondenti righe nella tabella di giunzione. Tuttavia, se l'entità viene caricata in modo differito e la sessione Hibernate è stata chiusa o non è disponibile, non è possibile eliminare le righe corrispondenti nella tabella di giunzione.
Per evitare questo errore, è necessario caricare in modo esplicito le collezioni prima di chiudere la sessione Hibernate. Ci sono diverse soluzioni possibili, ad esempio:
Utilizzare il metodo fetch = FetchType.EAGER nella specifica @ManyToMany per caricare le collezioni in modo esplicito quando si accede all'entità.
 */
    @ManyToMany(fetch = FetchType.EAGER,
        cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE })
    @JoinTable(name = "orders",
        joinColumns = { @JoinColumn(name = "user_id") },
        inverseJoinColumns = { @JoinColumn(name = "product_id") })
    @JsonIgnore
    private List<Product> ordini;   // usare Set se non si vogliono duplicati (se ogni user può ordinare un solo prodotto)
}