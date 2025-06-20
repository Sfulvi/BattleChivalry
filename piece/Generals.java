package piece;

import game.Battlefield;
import game.MyException;

public class Generals implements Units{
	public int maxStamina;
	public String name;
	public int hp;
	public int atk;
	public int range;
	public int stamina;
	public boolean hasAttacked;
	public int x;
	public int y;
	public int actionCost;
	public final boolean playerOne;
	public Army army;
	
	public Generals(String name, int hp, int atk, int range, int stamina, int maxStamina, boolean player) {
		setName(name);
		setHp(hp);
		setAtk(atk);
		setRange(range);
		setStamina(stamina);
		setMaxStamina(maxStamina);
		setX(0);
		setY(0);
		this.playerOne = player;
		createArmy();
	}
	
	public void move(Battlefield field, int orientation) throws MyException{
		int x = getX(),
			y = getY();
		
		if(getStamina () == 0)
			// Exception: mosse finite
			throw new MyException("stamina esaurita");
		
		switch(orientation) {
		case 1:
			if((x-1 < 0) || (y-1 < 0)) {
				// Exception: Fuori campo
				throw new MyException("fuori campo");
			}else if(field.battlefield[y-1][x-1] == null) {
				setX(x-1);
				setY(y-1);
				field.battlefield[getY()][getX()] = this;
				field.battlefield[y][x] = null;
			}else {
				// Exception: cella occupata
				throw new MyException("cella occupata");
			}
			break;
		case 2:
			if(y-1 < 0) {
				// Exception: Fuori campo
				throw new MyException("fuori campo");
			}else if(field.battlefield[y-1][x] == null) {
				setY(y-1);
				field.battlefield[getY()][getX()] = this;
				field.battlefield[y][x] = null;
			}else {
				// Exception: cella occupata
				throw new MyException("cella occupata");
			}
			break;
		case 3:
			if((y-1 < 0) || (x+1 >= field.getX())) {
				// Exception: Fuori campo
				throw new MyException("fuori campo");
			}else if(field.battlefield[y-1][x+1] == null) {
				setX(x+1);
				setY(y-1);
				field.battlefield[getY()][getX()] = this;
				field.battlefield[y][x] = null;
			}else {
				// Exception: cella occupata
				throw new MyException("cella occupata");
			}
			break;
		case 4:
			if(x+1 >= field.getX()) {
				// Exception: Fuori campo
				throw new MyException("fuori campo");
			}else if(field.battlefield[y][x+1] == null) {
				setX(x+1);
				field.battlefield[getY()][getX()] = this;
				field.battlefield[y][x] = null;
			}else {
				// Exception: cella occupata
				throw new MyException("cella occupata");
			}
			break;
		case 5:
			if((x+1 >= field.getX()) || (y+1 >= field.getY())) {
				// Exception: Fuori campo
				throw new MyException("fuori campo");
			}else if(field.battlefield[y+1][x+1] == null) {
				setX(x+1);
				setY(y+1);
				field.battlefield[getY()][getX()] = this;
				field.battlefield[y][x] = null;
			}else {
				// Exception: cella occupata
				throw new MyException("cella occupata");
			}
			break;
		case 6:
			if(y+1 >= field.getY()) {
				// Exception: Fuori campo
				throw new MyException("fuori campo");
			}else if(field.battlefield[y+1][x] == null) {
				setY(y+1);
				field.battlefield[getY()][getX()] = this;
				field.battlefield[y][x] = null;
			}else {
				// Exception: cella occupata
				throw new MyException("cella occupata");
			}
			break;
		case 7:
			if((x-1 < 0) || (y+1 >= field.getY())) {
				// Exception: Fuori campo
				throw new MyException("fuori campo");
			}else if(field.battlefield[y+1][x-1] == null) {
				setX(x-1);
				setY(y+1);
				field.battlefield[getY()][getX()] = this;
				field.battlefield[y][x] = null;
			}else {
				// Exception: cella occupata
				throw new MyException("cella occupata");
			}
			break;
		case 8:
			if(x-1 < 0) {
				// Exception: Fuori campo
				throw new MyException("fuori campo");
			}else if(field.battlefield[y][x-1] == null) {
				setX(x-1);
				field.battlefield[getY()][getX()] = this;
				field.battlefield[y][x] = null;
			}else {
				// Exception: cella occupata
				throw new MyException("cella occupata");
			}
			break;
		}
		
		setStamina(getStamina()-1);
	}

	public void attack(int targetX, int targetY, Battlefield field)throws MyException{
		
		if(getStamina() == 0) {
			// Exception: mosse finite
			throw new MyException("stamina finita");
		}else if(field.battlefield[targetY][targetX] == null) {
			// Exception: Zona vuota
			throw new MyException("cella vuota");
		}else if(field.battlefield[targetY][targetX] instanceof SiegeMachines) {
			// Exception: Obiettivo non bersagliabile
			throw new MyException("obiettivo non bersagliabile");
		}else if(targetX > getX() + getRange() || 
				 targetX < getX() - getRange() ||
				 targetY > getY() + getRange() ||
				 targetY < getY() - getRange()){
			// Exception: Fuori Portata
			throw new MyException("fuori portata");
		}else if(field.battlefield[targetY][targetX].isHost() == this.isHost()) {
			// Exception: Niente Fuoco Amico
			throw new MyException("alleato selezionato");
		}else {
			field.battlefield[targetY][targetX].attacked(getAtk());
		}
		
		setStamina(getStamina()-1);
	}
	
	public void createArmy() {
		this.army = new Army(getName(), isHost());
	}

	public Army getArmy(){
		return this.army;
	}
	public void attacked(int atk) {
		setHp(getHp()-atk);
	}
	public boolean isAlive() {
		return (getHp() >= 0);
	}
	public void recharge() {
		setStamina(getMaxStamina());
	}
	public String getName() {
		return name;
	}
	private void setName(String name) {
		this.name = name;
	}
	public int getHp() {
		return hp;
	}
	private void setHp(int hp) {
		this.hp = hp;
	}
	public int getAtk() {
		return atk;
	}
	private void setAtk(int atk) {
		this.atk = atk;
	}
	public int getRange() {
		return range;
	}
	private void setRange(int range) {
		this.range = range;
	}
	public int getStamina() {
		return stamina;
	}
	private void setStamina(int sta) {
		this.stamina = sta;
	}
	public boolean isHasAttacked() {
		return hasAttacked;
	}
	public void setHasAttacked(boolean hasAttacked) {
		this.hasAttacked = hasAttacked;
	}
	public int getX() {
		return this.x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return this.y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getActionCost() {
		return actionCost;
	}
	/*private void setActionCost(int actionCost) {
		this.actionCost = actionCost;
	}*/
	public int getMaxStamina() {
		return maxStamina;
	}
	private void setMaxStamina(int maxStamina) {
		this.maxStamina = maxStamina;
	}
	public boolean isHost() {
		return playerOne;
	}
	public String toString(){
		String s="";

		s+="Name: "+getName()+"\nHp: "+getHp()+"\nAtk: "+getAtk()+"\nPosizione: ("+getX()+";"+getY()+")";
		
		return s;
	}
}
