package com.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Entity
@Table(name = "PRODUCTS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "Nome obbligatorio")
    @Column(name = "NAME")
    private String name;

    @NotNull(message = "Descrizione obbligatoria")
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull(message = "Prezzo obbligatorio")
    @Column(name = "PRICE")
    private BigDecimal price;

    @NotNull(message = "Genere obbligatorio")
    @Enumerated(EnumType.STRING)
    @Column(name = "GENRE")
    private Genre genre;

    public enum Genre {
        TECNOLOGIA,
        ALIMENTARI,
        ABBIGLIAMENTO,
        ARREDAMENTO,
        INTRATTENIMENTO,
        BENESSERE
    }
}