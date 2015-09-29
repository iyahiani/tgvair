package com.avancial.app.model.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


/** 
 * permet de sauvegareder l'etat du Context d'une page 
 * @author ismael.yahiani
 *
 */ 

@Named("savesession")
@SessionScoped
public class SaveSession implements Serializable{

   /**
    * 
    */ 
   
   private static final long serialVersionUID = 1L;
   private UIComponent formulaire  ;
   private  UIViewRoot view ; 
   
   public void goTrain() {
      ExternalContext context = FacesContext.getCurrentInstance().getExternalContext(); 
      String num = context.getRequestParameterMap().get("ajoutTain:num") ;
      context.getInitParameterMap() ;   
      this.view =   FacesContext.getCurrentInstance().getViewRoot() ;
      this.formulaire = FacesContext.getCurrentInstance().getViewRoot().findComponent("ajoutTain:tout") ;   
      Map<String,String> map = context.getRequestParameterMap() ; 
      for (Entry<String, String> entry : map.entrySet()) {
         System.out.println(entry.getKey()+"-----"+entry.getValue());
      }
      
      try {
         context.redirect("pointArret.xhtml?faces-redirect=true");
      } catch (IOException e) {

         e.printStackTrace();
      }
   }

   public UIComponent getFormulaire() {
      return formulaire;
   }

   public void setFormulaire(UIComponent formulaire) {
      this.formulaire = formulaire;
   }

   public UIViewRoot getView() {
      return view;
   }

   public void setView(UIViewRoot view) {
      this.view = view;
   }
}
