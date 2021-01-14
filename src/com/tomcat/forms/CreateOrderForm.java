package com.tomcat.forms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import com.tomcat.beans.Client;
import com.tomcat.beans.Order;

public class CreateOrderForm {

    private static final String CHAMP_CHOIX_CLIENT     = "choixNouveauClient";
    private static final String CHAMP_LISTE_CLIENTS    = "listeClients";

    private static final String CHAMP_MODE_LIVRAISON   = "modeLivraisonCommande";
    private static final String CHAMP_MODE_PAIEMENT    = "modePaiementCommande";
    private static final String CHAMP_STATUT_LIVRAISON = "statutLivraisonCommande";
    private static final String CHAMP_STATUT_PAIEMENT  = "statutPaiementCommande";
    private static final String CHAMP_MONTANT_COMMANDE = "montantCommande";

    private static final String FORMAT_DATE            = "dd/MM/yyyy HH:mm:ss";

    private static final String ANCIEN_CLIENT          = "ancienClient";
    private static final String SESSION_CLIENTS        = "listeClients";

    // contient champ du parametre,message d'erreur
    private Map<String, String> erreurs                = new HashMap<String, String>();
    private String              resultat;

    public Order createOrder( HttpServletRequest request ) {
        Client user;

        String choixNouveauClient = getValeurChamp( request, CHAMP_CHOIX_CLIENT );
        if ( ANCIEN_CLIENT.equals( choixNouveauClient ) ) {
            String nomAncienClient = getValeurChamp( request, CHAMP_LISTE_CLIENTS );
            HttpSession session = request.getSession();
            user = ( (Map<String, Client>) session.getAttribute( SESSION_CLIENTS ) ).get( nomAncienClient );
        } else {
            CreateClientForm form_user = new CreateClientForm();
            user = form_user.creerClient( request );
            erreurs = form_user.getErreurs();
        }

        Order order = new Order();

        String montant = getValeurChamp( request, CHAMP_MONTANT_COMMANDE );
        String modeLivraison = getValeurChamp( request, CHAMP_MODE_LIVRAISON );
        String modePaiement = getValeurChamp( request, CHAMP_MODE_PAIEMENT );
        String statutLivraison = getValeurChamp( request, CHAMP_STATUT_LIVRAISON );
        String statutPaiement = getValeurChamp( request, CHAMP_STATUT_PAIEMENT );

        double valeurMontant = -1;
        try {
            valeurMontant = validerMontant( montant );

        } catch ( Exception e ) {
            setErreurs( CHAMP_MONTANT_COMMANDE, e.getMessage() );
        }

        order.setMontant( valeurMontant );

        try {
            validerModeLivraison( modeLivraison );
        } catch ( Exception e ) {
            setErreurs( CHAMP_MODE_LIVRAISON, e.getMessage() );
        }
        order.setModeLivraison( modeLivraison );
        try {
            validerModePaiement( modePaiement );
        } catch ( Exception e ) {
            setErreurs( CHAMP_MODE_PAIEMENT, e.getMessage() );
        }
        order.setModePaiement( modePaiement );
        try {
            validerStatutLivraison( statutLivraison );
        } catch ( Exception e ) {
            setErreurs( CHAMP_STATUT_LIVRAISON, e.getMessage() );
        }
        order.setStatutLivraison( statutLivraison );
        try {
            validerStatutPaiement( statutPaiement );
        } catch ( Exception e ) {
            setErreurs( CHAMP_STATUT_PAIEMENT, e.getMessage() );
        }
        order.setStatutPaiement( statutPaiement );

        order.setDateOrder( new DateTime().toString( DateTimeFormat.forPattern( FORMAT_DATE ) ) );

        // set le client

        order.setClient( user );

        if ( erreurs.isEmpty() ) {
            resultat = "La commande a été créé avec succès";

        } else {
            resultat = "Une erreur est intervenu lors de la création de la commande";

        }
        return order;
    }

    private double validerMontant( String montant ) throws Exception {
        double temp;
        if ( montant != null ) {
            try {
                temp = Double.parseDouble( montant );
                if ( temp < 0 ) {
                    throw new Exception( "Le montant ne peut pas être négatif" );
                }
            } catch ( NumberFormatException e ) {
                temp = -1;
                throw new Exception( "Le montant doit être un nombre décimal" );
            }
        } else {
            temp = -1;
            throw new Exception( "Merci d'entrer un montant" );
        }
        return temp;

    }

    private void validerModeLivraison( String modeLivraison ) throws Exception {
        if ( modeLivraison != null ) {
            if ( modeLivraison.length() < 2 ) {
                throw new Exception( "Le mode de livraison doit être au minimum 2 caractères" );
            }
        } else {
            throw new Exception( "Merci d'entrer un mode de livraison" );
        }
    }

    private void validerModePaiement( String modePaiement ) throws Exception {
        if ( modePaiement != null ) {
            if ( modePaiement.length() < 2 ) {
                throw new Exception( "Le mode de paiement doit être au minimum 2 caractères" );
            }
        } else {
            throw new Exception( "Merci d'entrer un mode de paiement" );
        }
    }

    private void validerStatutLivraison( String statutLivraison ) throws Exception {
        if ( statutLivraison != null && statutLivraison.length() < 2 ) {
            throw new Exception( "Le statut de livraison doit être au minimum 2 caractères" );
        }
    }

    private void validerStatutPaiement( String statutPaiement ) throws Exception {
        if ( statutPaiement != null && statutPaiement.length() < 2 ) {
            throw new Exception( "Le statut de paiement doit être au minimum 2 caractères" );
        }
    }

    /**
     * @return the resultat
     */
    public String getResultat() {
        return resultat;
    }

    /**
     * @return the erreurs
     */
    public Map<String, String> getErreurs() {
        return erreurs;
    }

    /**
     * @param champ
     *            de parametre
     * @param message
     * 
     */
    private void setErreurs( String champ, String message ) {
        erreurs.put( champ, message );
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
