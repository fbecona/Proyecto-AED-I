package OnceIdeales;

import java.io.IOException;
import javax.swing.DefaultListModel;
import OnceIdeales.EstructurasDeDatos.Lista;
import OnceIdeales.EstructurasDeDatos.Nodo;

/**
 * Esta clase representa el interfaz gráfico del programa.
 * 
 * @author federico
 */
public class Programa extends javax.swing.JFrame {
    
    private Football football;
    
    private String text = "";
    
    private final DefaultListModel clear = new DefaultListModel();

    public Football getFootball() {
        return football;
    }

    public void setFootball(Football football) {
        this.football = football;
    }

    /**
     * Creates new form Programa
     */
    public Programa() {
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        title = new javax.swing.JLabel();
        panel = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        buttonBestXXI = new javax.swing.JButton();
        buttonMostMatches = new javax.swing.JButton();
        buttonEffective = new javax.swing.JButton();
        buttonPlayerLeagues = new javax.swing.JButton();
        buttonTeamGoals = new javax.swing.JButton();
        author = new javax.swing.JLabel();
        options = new javax.swing.JLabel();
        inputText = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(1, 1, 1));

        title.setText("Mundo del fútbol");

        list.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        panel.setViewportView(list);

        buttonBestXXI.setText("Once Ideales");
        buttonBestXXI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBestXXIActionPerformed(evt);
            }
        });

        buttonMostMatches.setText("Jugadores con más partidos");
        buttonMostMatches.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMostMatchesActionPerformed(evt);
            }
        });

        buttonEffective.setText("Jugadores Efectivos");
        buttonEffective.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEffectiveActionPerformed(evt);
            }
        });

        buttonPlayerLeagues.setText("Ligas de un Jugador");
        buttonPlayerLeagues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPlayerLeaguesActionPerformed(evt);
            }
        });

        buttonTeamGoals.setText("Goles de un Equipo");
        buttonTeamGoals.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTeamGoalsActionPerformed(evt);
            }
        });

        author.setText("Federico Becoña ");

        options.setText("Opciones:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonBestXXI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEffective, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPlayerLeagues, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonTeamGoals, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonMostMatches, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(options, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputText))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(author))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(264, 264, 264))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(58, Short.MAX_VALUE)
                        .addComponent(options)
                        .addGap(18, 18, 18)
                        .addComponent(buttonBestXXI)
                        .addGap(18, 18, 18)
                        .addComponent(buttonMostMatches)
                        .addGap(18, 18, 18)
                        .addComponent(buttonEffective)
                        .addGap(18, 18, 18)
                        .addComponent(buttonPlayerLeagues)
                        .addGap(18, 18, 18)
                        .addComponent(buttonTeamGoals)
                        .addGap(24, 24, 24)
                        .addComponent(inputText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(author)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Responde a la primera pregunta.
     * @param evt 
     */
    private void buttonMostMatchesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMostMatchesActionPerformed
        this.text = inputText.getText();
        this.list.setModel(this.clear);
        DefaultListModel playersWithMostMatches = new DefaultListModel();
        playersWithMostMatches.addElement(this.football.getPlayerWithMostMatches(text));
        this.list.setModel(playersWithMostMatches);
    }//GEN-LAST:event_buttonMostMatchesActionPerformed

   /**
    * Da el 11 ideal.
    * @param evt 
    */
    private void buttonBestXXIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBestXXIActionPerformed
        this.list.setModel(this.clear);
        DefaultListModel players = new DefaultListModel();
        Lista<Player> bestPlayers = this.football.getBestXI();
        Nodo<Player> aux = bestPlayers.getPrimero();
        while(aux!=null){
           String playerInfo = "-" + aux.getEtiqueta()+", "+aux.getDato().getName() +
                    " ----- " + " | goles hechos:  " + aux.getDato().getGoals() +
                                " | goles en contra:  " + aux.getDato().getGoalsAgainst() +
                                " | partidos jugados:  " + aux.getDato().getMatchesPlayed() +
                                " | posiciones: " + aux.getDato().getPositions().inOrden()+
                                " | faltas:  " + aux.getDato().getFouls() +
                                " | tarjetas:  " + aux.getDato().getCards();
           players.addElement(playerInfo);
           aux = aux.getSiguiente();
        }
        this.list.setModel(players);
    }//GEN-LAST:event_buttonBestXXIActionPerformed

    /**
     * Responde a la segunda pregunta.
     * @param evt 
     */
    private void buttonEffectiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEffectiveActionPerformed
        this.text = inputText.getText();
        this.list.setModel(this.clear);
        DefaultListModel effectivePlayers = new DefaultListModel();
        effectivePlayers.addElement(this.football.getEffectivePlayer(text));
        this.list.setModel(effectivePlayers);        
    }//GEN-LAST:event_buttonEffectiveActionPerformed

    /**
     * Responde a la tercera pregunta.
     * @param evt 
     */
    private void buttonPlayerLeaguesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPlayerLeaguesActionPerformed
        this.text = inputText.getText();
        this.list.setModel(this.clear);
        DefaultListModel playersLeagues = new DefaultListModel();
        playersLeagues.addElement(this.football.getAllLeaguesOfAPlayers(text));
        this.list.setModel(playersLeagues);
    }//GEN-LAST:event_buttonPlayerLeaguesActionPerformed

    /**
     * Responde a la cuarta pregunta.
     * @param evt 
     */
    private void buttonTeamGoalsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTeamGoalsActionPerformed
        this.text = inputText.getText();
        this.list.setModel(this.clear);
        DefaultListModel teamWithGoals = new DefaultListModel();
        teamWithGoals.addElement(this.football.getTeamGoals(text));
        this.list.setModel(teamWithGoals);
    }//GEN-LAST:event_buttonTeamGoalsActionPerformed

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String args[]) throws IOException {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Programa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        System.out.println("BIENVENIDO");
        System.out.println("Por favor, espere mientras se procesa la información...");
        
        String[] leaguesIds = new String[]{"I1","F1","D1","SP1"};
        String[] seasonYears = new String[]{"2012","2013","2014","2015","2016"};
        
        DataLoader dataLoader = new DataLoader(
            leaguesIds,
            seasonYears,
            "Datos/matches.csv",
            "Datos/match-standings.csv",
            "Datos/teams.csv",
            "Datos/players.csv",
            "Datos/events.csv"
         );
        
        Football football = new Football(dataLoader.getStructure(),leaguesIds);
        
        java.awt.EventQueue.invokeLater(() -> {
            Programa programa = new Programa();
            DefaultListModel initial = new DefaultListModel();
            initial.addElement("Cargue una temporada o el nombre de un jugador"
                    + " y seleccione una opción");
            programa.list.setModel(initial);
            programa.setFootball(football);
            programa.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel author;
    private javax.swing.JButton buttonBestXXI;
    private javax.swing.JButton buttonEffective;
    private javax.swing.JButton buttonMostMatches;
    private javax.swing.JButton buttonPlayerLeagues;
    private javax.swing.JButton buttonTeamGoals;
    private javax.swing.JTextField inputText;
    private javax.swing.JList<String> list;
    private javax.swing.JLabel options;
    private javax.swing.JScrollPane panel;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}