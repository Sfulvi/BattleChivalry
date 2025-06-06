package piece;

public class Troops implements Units{
    private String name;
    private int hp;
    private int atk;
    private int range;
    private int mov;
    private int stamina;
    private int maxStamina;
    private boolean hasAttacked;
    private int x;
    private int y;
    private int AC;
    private boolean faction;
    
    public Troops (String name, int hp, int atk, int range, int mov, int stamina, int maxStamina, boolean faction) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.range = range;
        this.mov = mov;
        this.stamina = stamina;
        this.maxStamina = maxStamina;
        this.hasAttacked = false;
        this.x = 0;
        this.y = 0;
        this.AC = 1;
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
    
    public int getAC() {
        return this.AC;
    }
    
    public boolean getFaction() {
        return this.faction;
    }
    
    //metodi setter
    
    public void setName(String name){
        this.name = name;    
    }
    
    public void setHP(int hp) {
        this.hp = hp;
    }
    
    public void setAtk(int atk) {
        this.atk = atk;
    }
    
    public void setRange(int range) {
        this.range = range;
    }
    
    public void setMov(int mov) {
        this.mov = mov;
    }
    
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    
    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
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
    
    public void setAC(int AC) {
        this.AC = AC;
    }
    
    public void setFaction(boolean faction) {
        this.faction = faction;
    } 
       //funzione che definisce l'attacco
    public void attack(int targetX, int targetY, Battlefield field) throws MyException{
        Units targetUnit = field.getUnit(targetX, targetY);

        // verifica presenza di un'unità
        if (targetUnit == null) {
            // errore: cella vuota
            throw new MyException("cella vuota");
        }

        // verifica se il bersaglio è nel range
        if (!isInRange(targetX, targetY)) {
            // errore: cella fuori portata
            throw new MyException("bersaglio fuori portata");
        }

        //verifica se il bersaglio è un alleato
       /* if ( bersaglio alleato ) {
            //errore: bersaglio alleato
            return false;
        } */
        
        //l'attacco ha successo
        targetUnit.attacked(getAtk());
        
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
            field.battlefield[newX][newY] = this;           //valori nel campo
            field.battlefield[currentX][currentY] = null;   //svuoto la casella vecchia
            setStamina(getStamina() - 1);
        } else {
            // movimento non valido
            throw new MyException("movimento non valido");
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
        int range = getRange();
        return distance <= range;
    }

    private boolean isValidMove(int newX, int newY, Battlefield field) {
            if (newX < 0 || newX > 14 || newY < 0 || newY > 8) {
                //fuori dal campo
                return false;
                
            } else if (field.battlefield[newX][newY] != null) {
                //casella occupata
                return false;
    
            } else if (getStamina() < 1) {
                //stamina esaurita
                return false;
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
