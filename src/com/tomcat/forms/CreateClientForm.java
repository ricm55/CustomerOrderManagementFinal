package com.tomcat.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tomcat.beans.Client;

public class CreateClientForm {

    // Parametres qui contiennent l'information du client
    public static final String  CHAMP_NOM     = "nomClient";
    public static final String  CHAMP_PRENOM  = "prenomClient";
    public static final String  CHAMP_ADRESSE = "adresseClient";
    public static final String  CHAMP_TEL     = "telephoneClient";
    public static final String  CHAMP_EMAIL   = "emailClient";

    // contient champ du parametre,message d'erreur
    private Map<String, String> erreurs       = new HashMap<String, String>();
    private String              resultat;

    // Obtenir la liste des erreurs de l'utilisateur
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    // Creer le client avec les informations fournies
    public Client creerClient( HttpServletRequest request ) {
        Client user = new Client();

        String nom = getValeurChamp( request, CHAMP_NOM );
        String prenom = getValeurChamp( request, CHAMP_PRENOM );
        String adresse = getValeurChamp( request, CHAMP_ADRESSE );
        String telephone = getValeurChamp( request, CHAMP_TEL );
        String email = getValeurChamp( request, CHAMP_EMAIL );

        try {
            validerNom( nom );
        } catch ( Exception e ) {
            setErreur( CHAMP_NOM, e.getMessage() );
        }
        user.setNom( nom );

        try {
            validerPrenom( prenom );
        } catch ( Exception e ) {
            setErreur( CHAMP_PRENOM, e.getMessage() );
        }
        user.setPrenom( prenom );

        try {
            validerAdresse( adresse );
        } catch ( Exception e ) {
            setErreur( CHAMP_ADRESSE, e.getMessage() );
        }
        user.setAdresseLivraison( adresse );

        try {
            validerTelephone( telephone );
        } catch ( Exception e ) {
            setErreur( CHAMP_TEL, e.getMessage() );
        }
        user.setNumTelephone( telephone );

        try {
            validerEmail( email );
        } catch ( Exception e ) {
            setErreur( CHAMP_EMAIL, e.getMessage() );
        }
        user.setCourriel( email );

        if ( erreurs.isEmpty() ) {
            setResultat( "Le client a bien été créé" );
        } else {
            setResultat( "Échec de la création du client" );
        }
        return user;
    }

    // Valider le nom de l'utilisateur
    private void validerNom( String nom ) throws Exception {
        if ( nom != null ) {
            if ( nom.length() < 2 ) {
                throw new Exception( "Le nom d'utilisateur doit contenir au moins 2 caractères." );
            }
        } else {
            throw new Exception( "Merci d'entrer un nom d'utilisateur" );
        }
    }

    // Valider le nom de l'utilisateur
    private void validerPrenom( String prenom ) throws Exception {
        if ( prenom != null && prenom.length() < 2 ) {
            throw new Exception( "Le prenom d'utilisateur doit avoir au moins 2 caractère" );
        }
    }

    // Valider l'adresse de l'utilisateur
    private void validerAdresse( String adresse ) throws Exception {
        if ( adresse != null ) {
            if ( adresse.length() < 10 ) {
                throw new Exception( "L'adresse doit contenir au moins 10 caractères" );
            }
        } else {
            throw new Exception( "Merci d'entrer une adresse de livraison" );
        }
    }

    // Valider le telephone de l'utilisateur
    private void validerTelephone( String telephone ) throws Exception {

        if ( telephone != null ) {
            // Verifier si le numero de telephone est composé de nombres
            if ( !telephone.matches( "^\\d+$" ) ) {
                throw new Exception( "Le numéro de téléphone doit uniquement contenir des chiffres" );
            }
            if ( !( telephone.length() > 3 ) ) {
                throw new Exception( "Le numéro de téléphone doit avoir au moins 4 caractères" );
            }
        } else {
            throw new Exception( "Merci d'entrer un numéro de téléphone" );
        }

    }

    private void validerEmail( String email ) throws Exception {

        if ( email != null && !( email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" ) ) ) {
            throw new Exception( "Le email n'est pas valide" );
        }
    }

    // Mettre les erreurs dans la liste d'erreurs
    private void setErreur( String champ, String message ) {
        erreurs.put( champ, message );
    }

    /**
     * @return the resultat
     */
    public String getResultat() {
        return resultat;
    }

    /**
     * @param resultat
     *            the resultat to set
     */
    public void setResultat( String resultat ) {
        this.resultat = resultat;
    }

    // Verifier si la chaine est vide
    private static String getValeurChamp( HttpServletRequest request, String nomChamp ) {
        String valeur = request.getParameter( nomChamp );
        if ( valeur == null || valeur.trim().length() == 0 ) {
            return null;
        } else {
            return valeur;
        }
    }

}
