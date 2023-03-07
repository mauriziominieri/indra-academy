package com.academy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by IntelliJ IDEA.
 *
 * @author: Maurizio Minieri
 * @email: mauminieri@gmail.com
 * @website: www.mauriziominieri.it
 */

@Entity
@Table(name = "ACADEMY_LOGS")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Log {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotNull(message = "Data Log obbligatoria")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LOG_DATE")
    private Date logDate;

    @NotNull(message = "Modulo obbligatorio")
    @Column(name = "MODULE")
    private String module;

    @NotNull(message = "Azione obbligatoria")
    @Column(name = "ACTION")
    private String action;

    @NotNull(message = "Descrizione obbligatoria")
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull(message = "Dettaglio obbligatorio")
    @Lob
    @Column(name = "DETAIL")
    private byte[] detail;
}
