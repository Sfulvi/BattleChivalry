package game;

import piece.Generals;

public class Player {
    
    private static final int MAX_AP = 0; //?
    
    private String name;
    private boolean host;
    private Generals general;
    private int apDone;
    
    //costruttore
    public Player(String name, boolean host, int apDone, Generals general) {
        setName(name);
        setHost(host);
        setApDone(apDone);
        setGeneral(general);
        
    }
    
    //getter e setter per i campi
    public int getMaxAp() {
        return MAX_AP;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    private void setGeneral(Generals g){
        this.general=g;
    }

    public boolean isHost() {
        return this.host;
    }
    
    public void setHost(boolean host) {
        this.host = host;
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

    public String toString(){
        String s = "";
        
        s+="Name: "+getName()+"\nHost: "+isHost()+"\nOperation done: "+getApDone()+"\n";

        return s;
    }
}
