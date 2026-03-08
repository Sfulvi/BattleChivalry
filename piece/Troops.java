package piece;

import game.Battlefield;
import game.MyException;

public abstract class Troops implements Units{
    private final String name;               /* Nome unita' */
    private int hp;                          /* Punti ferita */
    private final int atk;                   /* Valore di attacco */
    private final int range;                 /* Distanza attacco */
    private final int mov;                   /* Distanza di movimento dell'unita' */
    private int stamina;                     /* Numero di mosse rimanenti di questa unita' */
    private final int maxStamina;            /* Numero massimo di movimenti per turno di questa unita' */
    private boolean hasAttacked;             /* Flag che mostra se questa unita' ha attaccato durante il turno */
    private int x;                           /* Coordinata X */
    private int y;                           /* Coordinata Y */
    private final boolean faction;           /* Flag che determina la fazione e quindi se si tratta di Player1 o Player2 */
    
    /* Costruttore di Troops */
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
    
    /* Funzione che restituisce vero se l'unita' e' ancora in vita */
    @Override
    public boolean isAlive() {
        return hp > 0;
    }
    
    /* Funzione che restituisce vero se l'unita' appartiene a Player1 */
    @Override
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
    /* Riceve in entrata le coordinate del bersaglio ed il campo di battaglia.
     * Se l'attacco non ha successo genera una eccezione */
    @Override
    public void attack(int targetX, int targetY, Battlefield field) throws MyException{
        Units targetUnit = field.getUnit(targetX, targetY);

        // verifica se l'unita' ha stamina
        if(getStamina() == 0)
            // errore: Stamina esaurita
            throw new MyException("stamina esaurita");

        // verifica se l'unita' ha gia' attaccato
        if (getHasAttacked())
            // errore: unita' ha gia' attaccato
            throw new MyException("questa unita' ha già attaccato");

        // verifica se il bersaglio e' nel range
        if (!isInRange(targetX, targetY))
            // errore: Cella fuori portata
            throw new MyException("bersaglio fuori portata");

        // verifica presenza di un'unita'
        if (targetUnit == null)
            // errore: Cella vuota
            throw new MyException("cella bersaglio vuota");

        //verifica se il bersaglio e' un alleato
        if (targetUnit.isHost() == this.isHost())
            // errore: Bersaglio alleato
            throw new MyException("bersaglio alleato");

        if(field.getUnit(targetX, targetY) instanceof SiegeMachines)
			// errore: Obiettivo non bersagliabile
			throw new MyException("obiettivo non bersagliabile");
		
        //l'attacco ha successo
        targetUnit.attacked(getAtk());
        // rimuovo se il bersaglio e' morto
        if(targetUnit.isAlive() == false)
            field.removeUnit(targetX, targetY);

        setStamina(getStamina() - 1);
        setHasAttacked(true); //solo un attacco a turno
        
    }
    
    //funzione di movimento delle truppe
    /* Riceve in entrata il campo di battaglia e la direzione del movimento.
     * Se il movimento fallisce genera una eccezione */
    @Override
    public void move(Battlefield field, int direction) throws MyException{
        int currentX = getX();
        int currentY = getY();
        int movement = getMov();
        int newX = -1;
        int newY = -1;
    
        switch (direction) {
            case 1 -> {
                // nw
                newX = currentX - movement;
                newY = currentY - movement;
            }
            case 2 -> {
                // north
                newX = currentX;
                newY = currentY - movement;
            }
            case 3 -> {
                // ne
                newX = currentX + movement;
                newY = currentY - movement;
            }
            case 4 -> {
                // east
                newX = currentX + movement;
                newY = currentY;
            }
            case 5 -> {
                // se
                newX = currentX + movement;
                newY = currentY + movement;
            }
            case 6 -> {
                // south
                newX = currentX;
                newY = currentY + movement;
            }
            case 7 -> {
                // sw
                newX = currentX - movement;
                newY = currentY + movement;
            }
            case 8 -> {
                // west
                newX = currentX - movement;
                newY=currentY;
            }
        }
    
        // verifica se la nuova posizione e' valida
        if (isValidMove(newX, newY, field)) {
            // valori nella truppa
            setX(newX);                                     
            setY(newY);

            Units[][] battlefield = field.getBattlefield();
            // sposto l'unita' nella nuova posizione
            battlefield[newY][newX] = this;   
            // aggiorno la vecchia posizione a null
            battlefield[currentY][currentX] = null;

            setStamina(getStamina() - 1);
        }
    }
    
    /* Questa funzione fa subire i danni all'unita */
    @Override
    public void attacked(int atk) {
		setHP(getHP()-atk);
	}
    
    /* Funzione che calcola la distanza tra due punti */
    private int calculateDistance(int x1, int y1, int x2, int y2) {
        /* visto che il movimento diagonale vale come 1 casella,
        la distanza fra due punti e' la differenza maggiore fra le X e le Y */
        int disX = Math.abs(x1-x2);
        int disY = Math.abs(y1-y2);
        int distance = Math.max(disX, disY);

        return distance;
    }

    /* Funzione che calcola se l'obiettivo selezionato e' a portata dell'attaccante */
    private boolean isInRange(int targetX, int targetY) {
        int distance = calculateDistance(getX(), getY(), targetX, targetY);
        return distance <= getRange();
    }

    /* Funzione che conferma se l'azione di movimento e' accettabile */
    private boolean isValidMove(int newX, int newY, Battlefield field) throws MyException {
        if (newX < 0 || newX > field.getX() - 1 || newY < 0 || newY > field.getY() - 1) {
            //fuori dal campo
            throw new MyException("fuori campo di battaglia");
            
        } else if (field.getUnit(newX, newY) != null) {
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
     
    /* Funzione che ricarica la stamina */
    @Override
    public void recharge(){
        setStamina(maxStamina);
    }

    /* Usato per test */
    @Override
    public String toString(){
		String s="";

		s+="Name: "+getName()+"\nHp: "+getHP()+"\nAtk: "+getAtk()+"\nPosizione: ("+getX()+";"+getY()+")";
		
		return s;
	}
}
