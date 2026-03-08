package game;

import piece.Generals;

public class Player {
    
    public static final int MAX_AP = 7; /* Azioni massime che si possono effettuare ogni turno */
    
    private String name;             /* Nome del giocatore */
    public final boolean isHost;     /* Player1 o Player2 */
    private Generals general;        /* Generale scelto */
    private int apDone;              /* Azioni effettuate nel turno corrente */
    
    //costruttore
    public Player(String name, boolean host, int apDone, Generals general) {
        setName(name);
        this.isHost = host;
        setApDone(apDone);
        setGeneral(general);
        
    }
    
    //getter e setter per i campi
    public static int getMaxAp() {
        return MAX_AP;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setGeneral(Generals g){
        this.general=g;
    }
    
    public int getApDone() {
        return this.apDone;
    }
    
    public void setApDone(int apDone) {
        this.apDone = apDone;
    }

    public Generals getGeneral() {
        return this.general;
    }

    @Override
    public String toString(){
        String s = "";
        
        s+="Name: "+getName()+"\nHost: "+this.isHost+"\nOperation done: "+getApDone()+"\n";

        return s;
    }
}
