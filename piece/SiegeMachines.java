package piece;

import game.Battlefield;
import game.MyException;
import piece.troops.Engineer;

public class SiegeMachines implements Units{
    
    private final String name;
    private final int atk;
    private boolean hasAttacked;
    private boolean recharged;
    private final int rechargeTime;
    private final boolean faction;
    private int standby;
    private int x;
    private int y;
    
    public SiegeMachines (String name, int atk, int rechargeTime, boolean faction) {
            
        this.name = name;
        this.atk = atk;
        this.rechargeTime = rechargeTime;
        this.standby = 0;
        this.faction = faction;
        this.hasAttacked = false;
        this.recharged = true;
    }

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

    private void setHasAttacked(boolean hasAttacked) {
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

    @Override
    public void recharge(){
        //metodo vuoto
    }
    
    public void recharge(Battlefield field) {

        
        /* Controlla se ha attaccato o é in standby */
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
        if(getRecharged()==true){

            setHasAttacked(false);

        }

    }

    @Override
    public void move(Battlefield position, int orientation) throws MyException {
        // Le macchine da assedio non si muovono
        throw new MyException("Questa unità non può muoversi");

    }

    @Override
    public void attack(int targetX, int targetY, Battlefield field) throws MyException{


        if(targetX > 12 && targetX < 2){

            /* Errore: Non puoi colpire quella zona */
            throw new MyException("Non puoi colpire quella zona");

        }else if(field.battlefield[targetY][targetX] == null) {

            /* Errore: La zona é vuota */
            throw new MyException("La zona é vuota");
                
        }else if(field.battlefield[targetY][targetX].isHost() == getFaction()) {

            /* Errore: "Il fuoco amico non sará tollerato" */
            throw new MyException("Il fuoco amico non sará tollerato");

        }else if(getHasAttacked()==true || hasEngineer(field)==false){

            /* Errore: La macchina da assedio sta ricaricando */
            throw new MyException("La macchina da assedio sta ricaricando");

        } else {

            field.battlefield[targetY][targetX].attacked(getAtk());

        }

    }

    public boolean hasEngineer(Battlefield field) {

        if(field.battlefield[getY()][getX()+1] instanceof Engineer == true) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isHost() {
        return false;
    }

    @Override
    public void attacked(int atk) {
        //le macchine da assedio non possono essere attaccate
    }

    @Override
    public boolean isAlive() {
        return false;
    }

}
