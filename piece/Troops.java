package piece;

import game.Battlefield;
import game.MyException;

public abstract class Troops implements Units{
    private final String name;
    private int hp;
    private final int atk;
    private final int range;
    private final int mov;
    private int stamina;
    private final int maxStamina;
    private boolean hasAttacked;
    private int x;
    private int y;
    private final boolean faction;
    
    public Troops (String name, int hp, int atk, int range, int mov, int maxStamina, boolean faction) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.range = range;
        this.mov = mov;
        this.maxStamina = maxStamina;
        this.stamina = maxStamina;
        this.hasAttacked = false;
        this.faction = faction;
    }
    
    public boolean isAlive() {
        return hp > 0;
    }
    
    public boolean isHost() {
		return faction;
	}
    //metodi getter
    
    public String getName() {
        return this.name;
    }
    
    public int getHP() {
        return this.hp;
    }
    
    public int getAtk() {
        return this.atk;
    }
    
    public int getRange() {
        return this.range;
    }
    
    public int getMov() {
        return this.mov;
    }
    
    public int getStamina() {
        return this.stamina;
    }
    
    public int getMaxStamina() {
        return this.maxStamina;
    }
    
    public boolean getHasAttacked() {
        return this.hasAttacked;
    }
    
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
    
    public boolean getFaction() {
        return this.faction;
    }
    
    //metodi setter
    
    public void setHP(int hp) {
        this.hp = hp;
    }
    
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    
    public void setHasAttacked(boolean hasAttacked) {
        this.hasAttacked = hasAttacked;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    //funzione che definisce l'attacco
    public void attack(int targetX, int targetY, Battlefield field) throws MyException{
        Units targetUnit = field.getUnit(targetX, targetY);

        // verifica se l'unità ha già attaccato
        if (getHasAttacked()) {
            // errore: unità ha già attaccato
            throw new MyException("questa unità ha già attaccato");
        }

        // verifica se il bersaglio è nel range
        if (!isInRange(targetX, targetY)) {
            // errore: Cella fuori portata
            throw new MyException("bersaglio fuori portata");
        }

        // verifica presenza di un'unità
        if (targetUnit == null) {
            // errore: Cella vuota
            throw new MyException("cella bersaglio vuota");
        }

        //verifica se il bersaglio è un alleato
        if (targetUnit.isHost() == this.isHost()) {
            // errore: Bersaglio alleato
            throw new MyException("bersaglio alleato");
        }

        if(field.battlefield[targetY][targetX] instanceof SiegeMachines) {
			// errore: Obiettivo non bersagliabile
			throw new MyException("obiettivo non bersagliabile");
		}
        //l'attacco ha successo
        targetUnit.attacked(getAtk());
        // rimuovo se il bersaglio è morto
        if(targetUnit.isAlive() == false){
            field.removeUnit(targetX, targetY);
        }
        setStamina(getStamina() - 1);
        setHasAttacked(true); //solo un attacco a turno
        
    }
    
    //funzione di movimento delle truppe
    public void move(Battlefield field, int direction) throws MyException{
        int currentX = getX();
        int currentY = getY();
        int movement = getMov();
        int newX = -1;
        int newY = -1;
    
        switch (direction) {
            case 1: // nw
                newX = currentX - movement;
                newY = currentY - movement;
                break;
            case 2: // north
                newX = currentX;
                newY = currentY - movement;
                break;
            case 3: // ne
                newX = currentX + movement;
                newY = currentY - movement;
                break;
            case 4: // east
                newX = currentX + movement;
                newY = currentY;
                break;
            case 5: // se
                newX = currentX + movement; 
                newY = currentY + movement;
                break;
            case 6: // south
                newX = currentX;
                newY = currentY + movement;
                break;
            case 7: // sw
                newX = currentX - movement;
                newY = currentY + movement;
                break;
            case 8: // west
                newX = currentX - movement;
                newY=currentY;
                break;
        }
    
        // verifica se la nuova posizione è valida
        if (isValidMove(newX, newY, field)) {
            setX(newX);                                     //valori nella truppa
            setY(newY);
            field.battlefield[newY][newX] = this;           //valori nel campo
            field.battlefield[currentY][currentX] = null;   //svuoto la casella vecchia
            setStamina(getStamina() - 1);
        }
    
    }
    
    public void attacked(int atk) {
		setHP(getHP()-atk);
	}
    
    //funzione che calcola la distanza tra due punti
    private int calculateDistance(int x1, int y1, int x2, int y2) {
        /* visto che il movimento diagonale vale come 1 casella,
        la distanza fra due punti è la differenza maggiore fra le X e le Y */
        int disX = Math.abs(x1-x2);
        int disY = Math.abs(y1-y2);
        int distance = Math.max(disX, disY);

        return distance;
    }

    //funzione che calcola se l'obiettivo selezionato è a portata dell'attaccante
    private boolean isInRange(int targetX, int targetY) {
        int distance = calculateDistance(getX(), getY(), targetX, targetY);
        return distance <= getRange();
    }

    private boolean isValidMove(int newX, int newY, Battlefield field) throws MyException {
            if (newX < 0 || newX > field.getX() - 1 || newY < 0 || newY > field.getY() - 1) {
                //fuori dal campo
                throw new MyException("fuori campi");
                
            } else if (field.battlefield[newY][newX] != null) {
                //casella occupata
                throw new MyException("cella occupata");
    
            } else if (getStamina() == 0) {
                //stamina esaurita
                throw new MyException("stamina esaurita");

            } else {
                //tutto ok
                return true;
            }
        }
        
    public void recharge(){
        setStamina(maxStamina);
    }

    public String toString(){
		String s="";

		s+="Name: "+getName()+"\nHp: "+getHP()+"\nAtk: "+getAtk()+"\nPosizione: ("+getX()+";"+getY()+")";
		
		return s;
	}
}
