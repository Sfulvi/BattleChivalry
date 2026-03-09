package piece.siegemachines;

import game.Battlefield;
import game.MyException;
import piece.SiegeMachines;
import piece.Units;

public class Catapult extends SiegeMachines {

    public Catapult(String name, boolean faction) {
        super(name, 50, 4, faction);

    }

	/* La funzione d'attacco per la catapulta e' differente dalle altre macchine d'assedio.
	 * Attorno al punto di attacco verranno inflitti anche la meta' del danno diretto del colpo */
    @Override
    public void attack(int targetX, int targetY, Battlefield field) throws MyException {
		Units targetUnit = field.getUnit(targetX, targetY);

		if(targetX > 12 || targetX < 2)
            /* Errore: Non puoi colpire quella zona */
            throw new MyException("You can't hit that area");
            
        if(targetUnit == null) 
            /* Errore: La zona e' vuota */
            throw new MyException("Empty target");

        if(targetUnit.isHost() == getFaction())
            /* Errore: "Il fuoco amico non e' permesso" */
            throw new MyException("Friendly fire is not allowed");

        if(getHasAttacked()==true || hasEngineer(field)==false)
            /* Errore: La macchina da assedio sta ricaricando */
            throw new MyException("Already hit or does he need to reload");

		if (hasEngineer(field)==false) {
            /* Errore: Ingegnere non in posizione */
            throw new MyException("Engineer not in position");
        }
			
			
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
    
}
