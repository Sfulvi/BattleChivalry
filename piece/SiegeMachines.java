package piece;

import game.Battlefield;
import game.MyException;
import piece.troops.Engineer;

public abstract class SiegeMachines implements Units{
    
    private final String name;           /* Nome unita */
    private final int atk;               /* Valore di attacco */
    private boolean hasAttacked;         /* Flag che mostra se questa unita' ha attaccato durante il turno */
    private boolean recharged;           /* Flag che mostra se l'unita' e' pronta ad attaccare */
    private final int rechargeTime;      /* Tempo di ricarica della macchina */
    private final boolean faction;       /* Flag che determina la fazione e quindi se si tratta di Player1 o Player2 */
    private int standby;                 /* Utilizzata per calcolare l'attesa di ricarica */
    private int x;                       /* Coordinata X */
    private int y;                       /* Coordinata Y */
    
    public SiegeMachines (String name, int atk, int rechargeTime, boolean faction) {
        this.name = name;
        this.atk = atk;
        this.rechargeTime = rechargeTime;
        this.standby = 0;
        this.faction = faction;
        this.hasAttacked = false;
        this.recharged = true;
    }

    /* Getter */
    public String getName() {
        return this.name;
    }
    public int getAtk() {
        return this.atk;
    }
    public boolean getHasAttacked() {
        return this.hasAttacked;
    }
    public boolean getRecharged() {
        return this.recharged;
    }
    public int getRechargeTime() {
        return this.rechargeTime;
    }
    public boolean getFaction() {
        return this.faction;
    }
    public int getStandby() {
        return this.standby;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }

    /* Setter */
    protected void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }
    private void setRecharged(boolean recharged) {
        this.recharged = recharged;
    }
    private void setStandby(int standby) {
        this.standby = standby;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    
    /* Funzione che ricarica la macchina */
    @Override
    public void recharge() {
        /* Controlla se ha attaccato o e' in standby */
        if(getHasAttacked()==true && getStandby()==0){ 
            setRecharged(false);
            setStandby(getRechargeTime());
        }else if(getHasAttacked()==true && getStandby()!=0){ 
            setStandby(getStandby()-1);
            if(getStandby()==0){
                setRecharged(true);
            }
        }
        /* Prepara la macchina per attaccare */
        if(getRecharged()){
            setHasAttacked(false);
        }
    }

    /* Funzione di Movimento */
    @Override
    public void move(Battlefield position, int orientation) throws MyException {
        // Le macchine da assedio non si muovono
        throw new MyException("Questa unità non può muoversi");

    }

    /* Funzione di attacco
     * Riceve in entrata le coordinate del bersaglio ed il campo di battaglia.
     * Se l'attacco non ha successo genera una eccezione */
    @Override
    public void attack(int targetX, int targetY, Battlefield field) throws MyException{
        Units targetUnit = field.getUnit(targetX, targetY);
        
        if(targetX > 12 || targetX < 2)
            /* Errore: Non puoi colpire quella zona */
            throw new MyException("Non puoi colpire quella zona");
            
        if(targetUnit == null) 
            /* Errore: La zona e' vuota */
            throw new MyException("La zona é vuota");

        if(targetUnit.isHost() == getFaction())
            /* Errore: "Il fuoco amico e' permesso" */
            throw new MyException("Il fuoco amico non e' permesso");

        if(getHasAttacked()==true || hasEngineer(field)==false)
            /* Errore: La macchina da assedio sta ricaricando */
            throw new MyException("La macchina da assedio sta ricaricando");
        
        
        setHasAttacked(true);
        targetUnit.attacked(getAtk());

        if(targetUnit.isAlive() == false)
            field.removeUnit(targetX, targetY);
        
    }

    /* Verifica che ci sia un ingegnere vicino */
    public boolean hasEngineer(Battlefield field) {
        return((field.getUnit(getX(), getY()+1) instanceof Engineer == true) || (field.getUnit(getX(), getY()-1) instanceof Engineer == true ));
    }

    /* Funzione che restituisce vero se l'unita' appartiene a Player1 */
    @Override
    public boolean isHost() {
        return this.faction;
    }

    /* Questa funzione fa subire i danni all'unita */
    @Override
    public void attacked(int atk) {
        //le macchine da assedio non possono essere attaccate
    }

    /* Funzione che restituisce vero se l'unita' e' ancora in vita */
    @Override
    public boolean isAlive() {
        return true;
    }

    /* Funzione di posizionamento macchina d'assedio nel campo di battaglia */
    @Override
    public void place(Units[][] battlefield, int y, int x){
        java.util.Optional.of(this)
            .map(u -> u)
            .ifPresent(s -> {
                s.setX(x);
                s.setY(y);
            });
        
        battlefield[y][x] = this;
    }
}
