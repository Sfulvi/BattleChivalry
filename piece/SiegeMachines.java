package piece;

import game.Battlefield;
import game.MyException;
import piece.troops.Engineer;

public class SiegeMachines implements Units{
    
    public String name;
    public int atk;
    public boolean hasAttacked;
    public int rechargeTime;
    public boolean recharged;
    public int actionCost;
    public boolean faction;
    public int standby;
    public int x;
    public int y;

    
    public SiegeMachines (String name, int atk, int rechargeTime, boolean recharged, int actionCost, int standby, boolean faction, boolean hasAttacked) {
            
        setName(name);
        setAtk(atk);
        setRechargeTime(rechargeTime);
        setRecharged(recharged);
        setActionCost(actionCost);
        setStandby(standby);
        setFaction(faction);
        setHasAttacked(hasAttacked);
        setX(0);
        setY(0);

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

    public int getRechargeTime() {
        return this.rechargeTime;
    }

    public boolean getRecharged() {
        return this.recharged;
    }

    public int getActionCost() {
        return this.actionCost;
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

    private void setName(String name) {
        this.name = name;
    }

    private void setAtk(int atk) {
        this.atk = atk;
    }

    private void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }

    private void setRechargeTime(int rechargeTime) {
        this.rechargeTime = rechargeTime;
    }

    private void setRecharged(boolean recharged) {
        this.recharged = recharged;
    }

    private void setActionCost(int actionCost) {
        this.actionCost = actionCost;
    }

    private void setFaction(boolean faction) {
        this.faction = faction;
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

    public void recharge(Battlefield field) {

        if(hasEngineer(field)==true) {

            if(getHasAttacked()==true && getStandby()==0){

                setRecharged(false);
                setStandby(getRechargeTime());

            }else if(getHasAttacked()==true && getStandby()!=0){

                setStandby(getStandby()-1);

                if(getStandby()==0){

                    setRecharged(true);
                
                }

            }
            
            if(getRecharged()==true){

                setHasAttacked(false);

            }
        }

    }

    public void move(Battlefield position, int orientation) {    

    }

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

        if(field.battlefield[getY()][getX()+1] instanceof Engineer == true) { //l'ingegnere è sempre sotto
            return true;
        } else {
            return false;
        }

    }

    public boolean isHost() {
        return false;
    }

    public void attacked(int atk) {

    }

    public boolean isAlive() {
        return false;
    }

    public void recharge(){

    }
}
