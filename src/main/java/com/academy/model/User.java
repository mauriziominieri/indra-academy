package com.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
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

    @NotNull(message = "Nome obbligatorio")
    @Column(name = "NAME")
    private String name;

    @NotNull(message = "Cognome obbligatorio")
    @Column(name = "SURNAME")
    private String surname;

    @NotNull(message = "Email obbligatoria")
    @Column(name = "EMAIL")
    private String email;

    @NotNull(message = "Anni obbligatori")
    @Min(value = 1, message = "Anni minimi 1")
    @Max(value = 150, message = "Anni massimi 150")
    @Column(name = "YEARS")
    private Long years;

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
    private List<Product> ordini;   // usare Set se non si vogliono duplicati (se ogni user può ordinare un solo prodotto)
}