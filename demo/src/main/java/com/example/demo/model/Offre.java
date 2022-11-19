package com.example.demo.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "offres")
public class Offre {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotBlank
    private String titre;

    @Column(nullable = true)
    private String description;

    @NotBlank
    private Date dateDebut;

    @NotBlank
    private Date dateFin;

    @NotBlank
    private String lieu;

    @NotBlank
    private int nbre_postes;

    @NotBlank
    private int remunerationBase;

    @NotBlank
    private String avantage;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employeur_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Employeur employeur;

    public Offre(String titre, String description, Date dateDebut, Date dateFin, String lieu, int nbre_postes, int remunerationBase, String avantage) {
        this.titre = titre;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.lieu = lieu;
        this.nbre_postes = nbre_postes;
        this.remunerationBase = remunerationBase;
        this.avantage = avantage;
    }

    public Offre(){}

}
