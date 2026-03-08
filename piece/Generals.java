package piece;

public abstract class Generals extends Troops{
	public Army army;   /* Esercito del generale */
	
	public Generals(String name, int hp, int atk, int range, int mov, int maxStamina, boolean player) {
		super(name, hp, atk, range, mov, maxStamina, player);
		createArmy();
	}
	
	/* Genera un esercito in base al generale ed alla fazione */
	public void createArmy() {
		this.army = new Army(getName(), isHost());
	}
}
