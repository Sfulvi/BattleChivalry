package piece;

public abstract class Generals extends Troops{
	public Army army;
	
	public Generals(String name, int hp, int atk, int range, int mov, int maxStamina, boolean player) {
		super(name, hp, atk, range, mov, maxStamina, player);
		createArmy();
	}
	
	public void createArmy() {
		this.army = new Army(getName(), isHost());
	}
}
