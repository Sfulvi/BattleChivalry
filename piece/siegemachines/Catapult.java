package piece.siegemachines;

import game.Battlefield;
import game.MyException;
import piece.SiegeMachines;
import piece.Units;

public class Catapult extends SiegeMachines {

    public Catapult(String name, boolean faction) {
        super(name, 50, 4, faction);

    }

    @Override
    public void attack(int targetX, int targetY, Battlefield field) throws MyException {
		Units targetUnit = field.getUnit(targetX, targetY);

		if(targetX > 12 || targetX < 2)
            /* Errore: Non puoi colpire quella zona */
            throw new MyException("Non puoi colpire quella zona");
            
        if(targetUnit == null) 
            /* Errore: La zona é vuota */
            throw new MyException("La zona é vuota");

        if(targetUnit.isHost() == getFaction())
            /* Errore: "Il fuoco amico non sará tollerato" */
            throw new MyException("Il fuoco amico non sará tollerato");

        if(getHasAttacked()==true || hasEngineer(field)==false)
            /* Errore: La macchina da assedio sta ricaricando */
            throw new MyException("La macchina da assedio sta ricaricando");
			
			
		/* eseguo l'attacco*/
		int damage = getAtk();
		targetUnit.attacked(damage);
		if(targetUnit.isAlive() == false)
			field.removeUnit(targetX, targetY);

		//controllo vicini
		Units leftNeighbor = field.getUnit(targetX - 1, targetY);
		Units rightNeighbor = field.getUnit(targetX + 1, targetY);

		if (leftNeighbor != null) {
			leftNeighbor.attacked(damage / 2);
			if(leftNeighbor.isAlive() == false)
				field.removeUnit(targetX - 1, targetY);
		}
		if (rightNeighbor != null) {
			rightNeighbor.attacked(damage / 2);
			if(rightNeighbor.isAlive() == false)
				field.removeUnit(targetX + 1, targetY);
		}

		//controllo sopra e sotto
		if (targetY > 0) {
			Units topNeighbor = field.getUnit(targetX, targetY - 1);
			if (topNeighbor != null) {
				topNeighbor.attacked(damage / 2);
				if(topNeighbor.isAlive() == false)
					field.removeUnit(targetX, targetY - 1);
			}
		}

		if (targetY < 8) {
			Units bottomNeighbor = field.getUnit(targetX, targetY + 1);
			if (bottomNeighbor != null) {
				bottomNeighbor.attacked(damage / 2);
				if(bottomNeighbor.isAlive() == false)
					field.removeUnit(targetX, targetY + 1);
			}
		}

		setHasAttacked(true);		
	}

    @Override
	public boolean hasEngineer(Battlefield field) {
		return super.hasEngineer(field);
	}
    
}
