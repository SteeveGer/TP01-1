/**
 * Module 633.1-Programmation / TP Série P10
 * 
 * Fenêtre principale de l'application
 *
 * @author Clément Savary
 *
*/
import java.util.*;
import java.util.ArrayList;
public class FrmMain extends java.awt.Frame {
  
  private static final String PERSONNES = "Personnes.txt";
  private static final String CADEAUX = "Cadeaux.txt";
  private static final String cPour = "Cadeaux pour ";
  private static final String sTotale = "Somme totale: ";
  private ArrayList lstC;
  private ArrayList lstP;
  
  /* Constructeur */
  public FrmMain() {
    initComponents();
    chargerListes();
  } // Constructeur
  
  private void chargerListes(){
    String[] tabPersonnes = chargerListe(PERSONNES);
    String[] tabCadeaux = chargerListe(CADEAUX);
    lstP = decouperString(tabPersonnes);
    afficherTab(lstP);
    lstC = decouperStringCadeaux(tabCadeaux);
    afficherCadeaux(lstC);
    afficherLesCadeauxCourant(lstP, lstC);
    int somme = calculerSomme(lstP, lstC);
    afficherSomme(somme);
  }
  
  private String[] chargerListe(String str){
    String[] tabStr = outils.FileStr.read(str);
    return tabStr;
  }
  
  private ArrayList decouperString(String[] tabPersonnes){      
      int x = 0;
      ArrayList lstPersonne = new ArrayList();
      for (int i = 0 ; i < tabPersonnes.length ; i++){
        StringTokenizer sT = new StringTokenizer(tabPersonnes[i], ";");
        String nom;
        ArrayList lstCadeau = new ArrayList();
        nom = sT.nextToken();
        while (sT.hasMoreTokens()){            
            lstCadeau.add(new Cadeau(Integer.parseInt(sT.nextToken())));
        }
        lstPersonne.add(i, new Personne(nom, lstCadeau));
    }
      return lstPersonne;
  }
  
  private void afficherTab(ArrayList lstPersonne){
    for (int i = 0 ; i < lstPersonne.size() ; i++){
        lstPersonnes.add(((Personne)lstPersonne.get(i)).getNom());
    }
    lstPersonnes.select(0);
    lblCadeauxPour.setText(cPour + ((Personne)lstPersonne.get(0)).getNom());
    
  }
  
  private ArrayList decouperStringCadeaux(String[] tabCadeaux){
    ArrayList lstCadeau = new ArrayList();
    for (int i = 0 ; i < tabCadeaux.length ; i++){
        StringTokenizer sT = new StringTokenizer(tabCadeaux[i], ";");
        int id = Integer.parseInt(sT.nextToken());
        String nom = sT.nextToken();
        int prix = Integer.parseInt(sT.nextToken());
        lstCadeau.add(i, new Cadeau(id, nom, prix));
    }
    return lstCadeau;
  }
  
  private void afficherCadeaux(ArrayList lstCadeau){
      for (int i = 0 ; i < lstCadeau.size() ; i++){
          lstCadeaux.add(((Cadeau)lstCadeau.get(i)).toString());
      }
  }
  
  private void afficherLesCadeauxCourant(ArrayList lstPersonne, ArrayList lstCadeau){
      lstCadeauxCourante.clear();
      String nom = lstPersonnes.getSelectedItem();
      Personne p1 = new Personne(nom, null);
      int ind = lstPersonne.indexOf(p1);
      Personne p = ((Personne)lstPersonne.get(ind));
      ArrayList listeCadeauPersonne = p.getIdsCadeaux();
      for (int i = 0 ; i < listeCadeauPersonne.size() ; i++){
          Cadeau c = (Cadeau)listeCadeauPersonne.get(i);
          int index = lstCadeau.indexOf(c);
          if (index != -1){
              lstCadeauxCourante.add(((Cadeau)lstCadeau.get(index)).toString());
          }
      }
  }
  
  private int calculerSomme(ArrayList lstPersonne, ArrayList lstCadeau){
      int somme = 0;
      String nom = lstPersonnes.getSelectedItem();
      Personne p1 = new Personne(nom, null);
      int ind = lstPersonne.indexOf(p1);
      Personne p = ((Personne)lstPersonne.get(ind));
      ArrayList listeCadeauPersonne = p.getIdsCadeaux();
      for (int i = 0 ; i < listeCadeauPersonne.size() ; i++){
          Cadeau c = (Cadeau)listeCadeauPersonne.get(i);
          int index = lstCadeau.indexOf(c);
          if (index != -1){
              somme += ((Cadeau)lstCadeau.get(index)).getPrix();
          }
      }
      return somme;
  }
  
  private void afficherSomme(int somme){
      lblSomme.setText(sTotale + somme + ".-");
  }
  
  private ArrayList personneCadeaux(String nomP){
      ArrayList tabCadeauPersonne = null;
      Personne p = new Personne(nomP, null);
      int ind = lstP.indexOf(p);
      if (ind != -1){
        tabCadeauPersonne = ((Personne)lstP.get(ind)).getIdsCadeaux();
      }
      return tabCadeauPersonne;
  }
  
  private void enregistrePersonne(){
      String[] tabStr = new String[lstP.size()];
      for (int i = 0 ; i < lstP.size() ; i++){
          String nom = ((Personne)lstP.get(i)).getNom();
          ArrayList aRemplacer = ((Personne)lstP.get(i)).getIdsCadeaux();
          StringBuffer buffer = infoDansTableau(nom, aRemplacer);
          String str = buffer.toString();
          tabStr[i] = str;
      }
      outils.FileStr.write(PERSONNES, tabStr);
  }
  
  private StringBuffer infoDansTableau(String nom, ArrayList lst){
      StringBuffer buffer = new StringBuffer();
      for (int i = 0 ; i < lst.size() ; i++){
          int idC = ((Cadeau)lst.get(i)).getId();
          buffer.append(";").append(idC);
      }
      return buffer.insert(0, nom);
  }
  
  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
   * content of this method is always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        java.awt.Label label1 = new java.awt.Label();
        lstPersonnes = new java.awt.List();
        lblCadeauxPour = new java.awt.Label();
        lstCadeauxCourante = new java.awt.List();
        lblSomme = new java.awt.Label();
        java.awt.Label label2 = new java.awt.Label();
        lstCadeaux = new java.awt.List();
        btnAjouter = new java.awt.Button();

        setTitle("Cadeaux de Noël");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        label1.setText("Personnes");

        lstPersonnes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lstPersonnesItemStateChanged(evt);
            }
        });

        lblCadeauxPour.setText("Cadeaux pour XYZ");

        lblSomme.setText("Somme totale: 99.-");

        label2.setText("Cadeaux possibles");

        lstCadeaux.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                lstCadeauxItemStateChanged(evt);
            }
        });

        btnAjouter.setEnabled(false);
        btnAjouter.setLabel("Ajouter");
        btnAjouter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAjouterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSomme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstCadeauxCourante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lstPersonnes, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCadeauxPour, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAjouter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstCadeaux, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lstPersonnes, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCadeauxPour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lstCadeaux, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lstCadeauxCourante, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAjouter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblSomme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  /* Fin de l'application */
  private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    dispose();
    enregistrePersonne();
  }//GEN-LAST:event_formWindowClosing

  private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    System.exit(0);
  }//GEN-LAST:event_formWindowClosed

    private void lstCadeauxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lstCadeauxItemStateChanged
        boolean ok = true;
        int selection = lstCadeaux.getSelectedIndex();
        selection++;
        Cadeau c = new Cadeau(selection);
        String nomPersonne = lstPersonnes.getSelectedItem();
        ArrayList tabPersonneCadeau = personneCadeaux(nomPersonne);
        if (tabPersonneCadeau != null){
            for (int i = 0 ; i < tabPersonneCadeau.size() ; i++){
                Cadeau c1 = ((Cadeau)tabPersonneCadeau.get(i));
                if (c1.equals(c)){
                    ok = false;
                }
            }
        }
        btnAjouter.setEnabled(ok);
    }//GEN-LAST:event_lstCadeauxItemStateChanged

    private void lstPersonnesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_lstPersonnesItemStateChanged
        lstCadeauxItemStateChanged(evt);
        lblCadeauxPour.setText(cPour + lstPersonnes.getSelectedItem());
        afficherLesCadeauxCourant(lstP, lstC);
        afficherSomme(calculerSomme(lstP, lstC));
    }//GEN-LAST:event_lstPersonnesItemStateChanged

    private void btnAjouterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAjouterActionPerformed
        int personne = lstPersonnes.getSelectedIndex();
        System.out.println(personne);
        int selection = lstCadeaux.getSelectedIndex();
        selection++;
        Cadeau c = new Cadeau(selection);
        ArrayList persCadeau = ((Personne)lstP.get(personne)).getIdsCadeaux();
        boolean ok = false;
        for (int i = 0 ; i < persCadeau.size() ; i++){
            Cadeau c1 = ((Cadeau)persCadeau.get(i));
            if (c.equals(c1)){
                ok = true;
            }
        }
        persCadeau.add(c);
        afficherLesCadeauxCourant(lstP, lstC);
        int somme = calculerSomme(lstP, lstC);
        afficherSomme(somme);
    }//GEN-LAST:event_btnAjouterActionPerformed

  /* Méthode principale */
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new FrmMain().setVisible(true);
      }
    });
  }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button btnAjouter;
    private java.awt.Label lblCadeauxPour;
    private java.awt.Label lblSomme;
    private java.awt.List lstCadeaux;
    private java.awt.List lstCadeauxCourante;
    private java.awt.List lstPersonnes;
    // End of variables declaration//GEN-END:variables

} // FrmMain
