/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi.dev.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import pi.dev.Entity.Categorie;
import pi.dev.Entity.Livraison;
import pi.dev.Entity.Produit;
import pi.dev.Entity.Promotion;
import pi.dev.Entity.Reclamation;
import pi.dev.Service.CategorieService;
import pi.dev.Service.PromotionService;
import pi.dev.Service.ReclamationServices;
import pi.dev.Service.ServiceLivraison;
import pi.dev.Service.produitSevice;

/**
 *
 * @author Ach
 */
public class Test {
 public static void main(String[] args) {
   
     /*
        Produit p41 = new Produit("sofsos", 14, "er5isa insaf", 10000);
        
        produitSevice ps = new produitSevice();
        Categorie c1 = new Categorie ( "Femme");
        Categorie c2 = new Categorie (4, "Homme");
        Categorie c3 = new Categorie ("Enfant");
        
        CategorieService c = new CategorieService();
        
        
     ReclamationServices pss= new  ReclamationServices();
     Calendar calendar = Calendar.getInstance();
     Date startDate = new Date(calendar.getTime().getTime());
     
       Reclamation r = new Reclamation(25, "ben khedhe", "Hamza",false,startDate);
       Reclamation r1 = new Reclamation(25, "ben khedhe", "Hamzaa",false,startDate);


        
       // ServiceLivraison ser = new ServiceLivraison();
        
        Livraison l1 = new Livraison("Achraf", "A", "B", 2000, 28546619, "Ach.Thamri@gmail.com");
        Livraison l2 = new Livraison("Achraff", "Aa", "Bb", 2001, 28546619, "Ach.Thamri@gmail.com");
        
        try {
                   pss.ajouterReclamation2(r);
                   pss.ajouterReclamation2(r1);
       pss.SupprimerReclamation();
       pss.ModificationReclamation(r);
//         
ps.insererProduit(p41);
         


//ser.ajouter1(l2);
        //   ser.ajouter(l1);
            
            c.ajouter1(c1);
            c.ajouter(c2);
            c.ajouter(c3);   
            c.supprimer("Femme");
            c.update("elee");
            */
            //eleeeeeeeee
            try{
            CategorieService c = new CategorieService();
            Categorie cc1=new Categorie( "categorieX");
            Categorie cc2=new Categorie( "categorieY");
            System.out.println("*************Ajouter Categories***********");
            c.ajouter1(cc1);
            c.ajouter1(cc2);
            System.out.println("*************Afficher Categories***********");
            System.out.println(c.readAll() +"\n");
            System.out.println("*************Mette a jour Categories***********");
            c.update("categorieX","categorieZ");
            System.out.println("*************Afficher Categories***********");
            System.out.println(c.readAll()+"\n");
            
           // c.supprimer("categorieZ");
            System.out.println("*************Supprimer Categories***********");
            System.out.println("*************Afficher Categories***********");
            System.out.println(c.readAll()+"\n");
            cc2=c.getCategorieByNom("categorieY");
            
            
            Produit p11=new Produit("produitX",10 , "produitXXX", 1);
            Produit p2=new Produit("produitY", 10, "produitYYY", 1);
//            p11.setCategorie(cc2);
  //          p2.setCategorie(cc2);
            produitSevice pService=new produitSevice();
           System.out.println("************* Ajouter produits***********");
            pService.insererProduit(p11);
            pService.insererProduit(p2);
            System.out.println("*************Afficher produits***********");
           // p11=pService.getProduit("produitX");
            //p2=pService.getProduit("produitY");
          //  p11.setCategorie(cc2);
            //p2.setCategorie(cc2);
            
            for(int i=0;i<pService.readAll().size();i++){
               Produit prd=pService.readAll().get(i);
                System.out.println(prd.toString());
            }
                 System.out.println("*************modifier produits***********");
                 p2.setNom("produitYYYC");
            pService.modifier(p2);
             for(int i=0;i<pService.readAll().size();i++){
               Produit prd=pService.readAll().get(i);
                System.out.println(prd.toString());
            }
              System.out.println("*************suuprimer produits***********");
           // pService.supprimer(p11.getNom());
             for(int i=0;i<pService.readAll().size();i++){
               Produit prd=pService.readAll().get(i);
                System.out.println(prd.toString());
            }
             System.out.println("*************recherche produit***********");
             pService.RechercheProduit(p2.getNom());
             //p11.setCategorie(cc2);
           // p2.setCategorie(cc2);
            
//            Promotion prom=new Promotion(0, "01/04/2020", "05/04/2020", 10);
           // prom.setProduit(p2);
            PromotionService pserv=new PromotionService();
            System.out.println("*************inserer promotion***********");
           // pserv.insererProduit(prom, p2.getIdP());
             for(int i=0;i<pserv.readAll().size();i++){
               Promotion prd=pserv.readAll().get(i);
                System.out.println(prd.toString());
            }
            //prom=pserv.RecherchePromotion(p2.getIdP());
            System.out.println("*************modifier promotion***********");
           // pserv.modifier(prom, prom.getId());
             for(int i=0;i<pserv.readAll().size();i++){
               Promotion prd=pserv.readAll().get(i);
                System.out.println(prd.toString());
            }
             System.out.println("*************rechercher promotion***********"); 
            pserv.RecherchePromotion(p2.getIdP());
             for(int i=0;i<pserv.readAll().size();i++){
               Promotion prd=pserv.readAll().get(i);
                System.out.println(prd.toString());
            }
                System.out.println("*************supprimer promotion***********");        
           // pserv.supprimer(prom.getId());
             for(int i=0;i<pserv.readAll().size();i++){
               Promotion prd=pserv.readAll().get(i);
                System.out.println(prd.toString());
            }
            
             //pService.supprimer(p2.getNom());
            //c.supprimer("categorieY");
            
            
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }      
}
