package game;

import piece.Units;
import piece.siegemachines.*;
import piece.troops.*;

public class Battlefield {
	
	//lunghezza e larghezza del campo
	private static final int X = 15;
	private static final int Y = 9;
    
	//campo di battaglia come array di unità (ovvero truppe, macchine d'assedio e generali)
	public Units[][] battlefield;

	private	Cannon[] cannons = null;
	private Engineer[] engineers = null;
	private Units[] specials = null;
	private Infantry[] infantry = null;
	private Armored[] armored = null;
	private Ballista[] ballistas = null;
	private Cavalry[] cavalry = null;
	private Archer[] archers = null;
	private Catapult[] catapults = null;

	public Battlefield() {
    	this.battlefield = new Units[Y][X];
	}

	public int getX() {
        	return X;
	}

	public int getY() {
        	return Y;
	}

	public Units[][] getBattlefield(){
		return this.battlefield;
	}
    
	//metodo che schiera le unità in base al generale scelto
	public void deploy(Player player) {

        //metto il generale nella posizione (1,4) se il giocatore è host, altrimenti nella posizione (13, 4)
		//la posizione del generale è la stessa per tutti gli eserciti
		if(player.isHost) {
			this.battlefield[4][1] = player.getGeneral();
            this.battlefield[4][1].setX(1);
            this.battlefield[4][1].setY(4);
		} else {
			this.battlefield[4][13] = player.getGeneral();
			this.battlefield[4][13].setX(13);
			this.battlefield[4][13].setY(4);
		}
		
		//in base al generale scelto, metto le altre unità nelle loro posizioni predefinite
		switch(player.getGeneral().getName()) {
			
			//test
			case "Ghandi":
				break;
				
			case "Sun Tzu":
				this.cannons = ((piece.Army)player.getGeneral().army).getCannons();	
				this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
				this.infantry = ((piece.Army)player.getGeneral().army).getInfantry();
				this.armored = ((piece.Army)player.getGeneral().army).getArmored();

				if(player.isHost) {

					
					this.battlefield[2][0] = this.cannons[0];
					this.battlefield[2][0].setX(0);
					this.battlefield[2][0].setY(2);

					this.battlefield[6][0] = this.cannons[1];
					this.battlefield[6][0].setX(0);
					this.battlefield[6][0].setY(6);

					this.battlefield[3][0] = this.engineers[0];
					this.battlefield[3][0].setX(0);
					this.battlefield[3][0].setY(3);

					this.battlefield[7][0] = this.engineers[1];
					this.battlefield[7][0].setX(0);
					this.battlefield[7][0].setY(7);

					this.battlefield[3][1] = this.specials[0];
					this.battlefield[3][1].setX(1);
					this.battlefield[3][1].setY(3);

					this.battlefield[5][1] = this.specials[1];
					this.battlefield[5][1].setX(1);
					this.battlefield[5][1].setY(5);

					this.battlefield[3][2] = this.infantry[0];
					this.battlefield[3][2].setX(2);
					this.battlefield[3][2].setY(3);

					this.battlefield[4][2] = this.infantry[1];
					this.battlefield[4][2].setX(2);
					this.battlefield[4][2].setY(4);

					this.battlefield[5][2] = this.infantry[2];
					this.battlefield[5][2].setX(2);
					this.battlefield[5][2].setY(5);

					this.battlefield[2][2] = this.armored[0];
					this.battlefield[2][2].setX(2);
					this.battlefield[2][2].setY(2);

					this.battlefield[6][2] = this.armored[1];
					this.battlefield[6][2].setX(2);
					this.battlefield[6][2].setY(6);
				}
				
				else {

					this.battlefield[2][14] = this.cannons[0];
					this.battlefield[2][14].setX(14);
					this.battlefield[2][14].setY(2);

					this.battlefield[6][14] = this.cannons[1];
					this.battlefield[6][14].setX(14);
					this.battlefield[6][14].setY(6);

					this.battlefield[3][14] = this.engineers[0];
					this.battlefield[3][14].setX(14);
					this.battlefield[3][14].setY(3);

					this.battlefield[7][14] = this.engineers[1];
					this.battlefield[7][14].setX(14);
					this.battlefield[7][14].setY(7);

					this.battlefield[3][13] = this.specials[0];
					this.battlefield[3][13].setX(13);
					this.battlefield[3][13].setY(3);

					this.battlefield[5][13] = this.specials[1];
					this.battlefield[5][13].setX(13);
					this.battlefield[5][13].setY(5);

					this.battlefield[3][12] = this.infantry[0];
					this.battlefield[3][12].setX(12);
					this.battlefield[3][12].setY(3);

					this.battlefield[4][12] = this.infantry[1];
					this.battlefield[4][12].setX(12);
					this.battlefield[4][12].setY(4);

					this.battlefield[5][12] = this.infantry[2];
					this.battlefield[5][12].setX(12);
					this.battlefield[5][12].setY(5);

					this.battlefield[2][12] = this.armored[0];
					this.battlefield[2][12].setX(12);
					this.battlefield[2][12].setY(2);

					this.battlefield[6][12] = this.armored[1];
					this.battlefield[6][12].setX(12);
					this.battlefield[6][12].setY(6);
				}
				
				break;

			case "Nobunaga Oda":
				this.ballistas = ((piece.Army)player.getGeneral().army).getBallistas();	
				this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
				this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
				this.infantry = ((piece.Army)player.getGeneral().army).getInfantry();
				this.archers = ((piece.Army)player.getGeneral().army).getArchers();
				this.armored = ((piece.Army)player.getGeneral().army).getArmored();

				if(player.isHost) {
			
					this.battlefield[4][0] = this.ballistas[0];
					this.battlefield[4][0].setX(0);
					this.battlefield[4][0].setY(4);

					this.battlefield[5][0] = this.engineers[0];
					this.battlefield[5][0].setX(0);
					this.battlefield[5][0].setY(5);

					this.battlefield[1][1] = this.cavalry[0];
					this.battlefield[1][1].setX(1);
					this.battlefield[1][1].setY(1);

					this.battlefield[7][1] = this.cavalry[1];
					this.battlefield[7][1].setX(1);
					this.battlefield[7][1].setY(7);

					this.battlefield[2][1] = this.archers[0];
					this.battlefield[2][1].setX(1);
					this.battlefield[2][1].setY(2);

					this.battlefield[3][1] = this.archers[1];
					this.battlefield[3][1].setX(1);
					this.battlefield[3][1].setY(3);

					this.battlefield[5][1] = this.archers[2];
					this.battlefield[5][1].setX(1);
					this.battlefield[5][1].setY(5);

					this.battlefield[6][1] = this.archers[3];
					this.battlefield[6][1].setX(1);
					this.battlefield[6][1].setY(6);

					this.battlefield[2][2] = this.infantry[0];
					this.battlefield[2][2].setX(2);
					this.battlefield[2][2].setY(2);

					this.battlefield[6][2] = this.infantry[1];
					this.battlefield[6][2].setX(2);
					this.battlefield[6][2].setY(6);

					this.battlefield[3][2] = this.specials[0];
					this.battlefield[3][2].setX(2);
					this.battlefield[3][2].setY(3);

					this.battlefield[5][2] = this.specials[1];
					this.battlefield[5][2].setX(2);
					this.battlefield[5][2].setY(5);

					this.battlefield[4][2] = this.armored[0];
					this.battlefield[4][2].setX(2);
					this.battlefield[4][2].setY(4);
				}
		
				else {
			
					this.battlefield[4][14] = this.ballistas[0];
					this.battlefield[4][14].setX(14);
					this.battlefield[4][14].setY(4);

					this.battlefield[5][14] = this.engineers[0];
					this.battlefield[5][14].setX(14);
					this.battlefield[5][14].setY(5);

					this.battlefield[1][13] = this.cavalry[0];
					this.battlefield[1][13].setX(13);
					this.battlefield[1][13].setY(1);

					this.battlefield[7][13] = this.cavalry[1];
					this.battlefield[7][13].setX(13);
					this.battlefield[7][13].setY(7);

					this.battlefield[2][13] = this.archers[0];
					this.battlefield[2][13].setX(13);
					this.battlefield[2][13].setY(2);

					this.battlefield[3][13] = this.archers[1];
					this.battlefield[3][13].setX(13);
					this.battlefield[3][13].setY(3);

					this.battlefield[5][13] = this.archers[2];
					this.battlefield[5][13].setX(13);
					this.battlefield[5][13].setY(5);

					this.battlefield[6][13] = this.archers[3];
					this.battlefield[6][13].setX(13);
					this.battlefield[6][13].setY(6);

					this.battlefield[2][12] = this.infantry[0];
					this.battlefield[2][12].setX(12);
					this.battlefield[2][12].setY(2);

					this.battlefield[6][12] = this.infantry[1];
					this.battlefield[6][12].setX(12);
					this.battlefield[6][12].setY(6);

					this.battlefield[3][12] = this.specials[0];
					this.battlefield[3][12].setX(12);
					this.battlefield[3][12].setY(3);

					this.battlefield[5][12] = this.specials[1];
					this.battlefield[5][12].setX(12);
					this.battlefield[5][12].setY(5);

					this.battlefield[4][12] = this.armored[0];
					this.battlefield[4][12].setX(12);
					this.battlefield[4][12].setY(4);
				}
				
				break;

			case "King Arthur":
				this.catapults = ((piece.Army)player.getGeneral().army).getCatapults();
				this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
				this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
				this.infantry = ((piece.Army)player.getGeneral().army).getInfantry();
				this.archers = ((piece.Army)player.getGeneral().army).getArchers();
				this.armored = ((piece.Army)player.getGeneral().army).getArmored();

				if(player.isHost) {
			
					this.battlefield[2][0] = this.catapults[0];
					this.battlefield[2][0].setX(0);
					this.battlefield[2][0].setY(2);

					this.battlefield[5][0] = this.catapults[1];
					this.battlefield[5][0].setX(0);
					this.battlefield[5][0].setY(5);

					this.battlefield[3][0] = this.engineers[0];
					this.battlefield[3][0].setX(0);
					this.battlefield[3][0].setY(3);

					this.battlefield[6][0] = this.engineers[1];
					this.battlefield[6][0].setX(0);
					this.battlefield[6][0].setY(6);

					this.battlefield[1][1] = this.cavalry[0];
					this.battlefield[1][1].setX(1);
					this.battlefield[1][1].setY(1);

					this.battlefield[7][1] = this.cavalry[1];
					this.battlefield[7][1].setX(1);
					this.battlefield[7][1].setY(7);

					this.battlefield[3][1] = this.archers[0];
					this.battlefield[3][1].setX(1);
					this.battlefield[3][1].setY(3);

					this.battlefield[5][1] = this.archers[1];
					this.battlefield[5][1].setX(1);
					this.battlefield[5][1].setY(5);

					this.battlefield[2][2] = this.infantry[0];
					this.battlefield[2][2].setX(2);
					this.battlefield[2][2].setY(2);

					this.battlefield[4][2] = this.infantry[1];
					this.battlefield[4][2].setX(2);
					this.battlefield[4][2].setY(4);

					this.battlefield[6][2] = this.infantry[2];
					this.battlefield[6][2].setX(2);
					this.battlefield[6][2].setY(6);

					this.battlefield[3][2] = this.specials[0];
					this.battlefield[3][2].setX(2);
					this.battlefield[3][2].setY(3);

					this.battlefield[5][2] = this.specials[1];
					this.battlefield[5][2].setX(2);
					this.battlefield[5][2].setY(5);
				}
		
				else {
			
					this.battlefield[2][14] = this.catapults[0];
					this.battlefield[2][14].setX(14);
					this.battlefield[2][14].setY(2);

					this.battlefield[5][14] = this.catapults[1];
					this.battlefield[5][14].setX(14);
					this.battlefield[5][14].setY(5);

					this.battlefield[3][14] = this.engineers[0];
					this.battlefield[3][14].setX(14);
					this.battlefield[3][14].setY(3);

					this.battlefield[6][14] = this.engineers[1];
					this.battlefield[6][14].setX(14);
					this.battlefield[6][14].setY(6);

					this.battlefield[1][13] = this.cavalry[0];
					this.battlefield[1][13].setX(13);
					this.battlefield[1][13].setY(1);

					this.battlefield[7][13] = this.cavalry[1];
					this.battlefield[7][13].setX(13);
					this.battlefield[7][13].setY(7);

					this.battlefield[3][13] = this.archers[0];
					this.battlefield[3][13].setX(13);
					this.battlefield[3][13].setY(3);

					this.battlefield[5][13] = this.archers[1];
					this.battlefield[5][13].setX(13);
					this.battlefield[5][13].setY(5);

					this.battlefield[2][12] = this.infantry[0];
					this.battlefield[2][12].setX(12);
					this.battlefield[2][12].setY(2);

					this.battlefield[4][12] = this.infantry[1];
					this.battlefield[4][12].setX(12);
					this.battlefield[4][12].setY(4);

					this.battlefield[6][12] = this.infantry[2];
					this.battlefield[6][12].setX(12);
					this.battlefield[6][12].setY(6);

					this.battlefield[3][12] = this.specials[0];
					this.battlefield[3][12].setX(12);
					this.battlefield[3][12].setY(3);

					this.battlefield[5][12] = this.specials[1];
					this.battlefield[5][12].setX(12);
					this.battlefield[5][12].setY(5);
				}
				
				break;

			case "Julius Caesar":
				this.catapults = ((piece.Army)player.getGeneral().army).getCatapults();
				this.ballistas = ((piece.Army)player.getGeneral().army).getBallistas();
				this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
				this.archers = ((piece.Army)player.getGeneral().army).getArchers
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
				this.armored = ((piece.Army)player.getGeneral().army).getArmored();

				if(player.isHost) {
			
					this.battlefield[2][0] = this.catapults[0];
					this.battlefield[2][0].setX(0);
					this.battlefield[2][0].setY(2);

					this.battlefield[5][0] = this.ballistas[0];
					this.battlefield[5][0].setX(0);
					this.battlefield[5][0].setY(5);

					this.battlefield[3][0] = this.engineers[0];
					this.battlefield[3][0].setX(0);
					this.battlefield[3][0].setY(3);

					this.battlefield[6][0] = this.engineers[1];
					this.battlefield[6][0].setX(0);
					this.battlefield[6][0].setY(6);

					this.battlefield[3][1] = this.archers[0];
					this.battlefield[3][1].setX(1);
					this.battlefield[3][1].setY(3);

					this.battlefield[5][1] = this.archers[1];
					this.battlefield[5][1].setX(1);
					this.battlefield[5][1].setY(5);

					this.battlefield[2][2] = this.specials[0];
					this.battlefield[2][2].setX(2);
					this.battlefield[2][2].setY(2);

					this.battlefield[6][2] = this.specials[1];
					this.battlefield[6][2].setX(2);
					this.battlefield[6][2].setY(6);

					this.battlefield[3][2] = this.armored[0];
					this.battlefield[3][2].setX(2);
					this.battlefield[3][2].setY(3);

					this.battlefield[4][2] = this.armored[1];
					this.battlefield[4][2].setX(2);
					this.battlefield[4][2].setY(4);

					this.battlefield[5][2] = this.armored[2];
					this.battlefield[5][2].setX(2);
					this.battlefield[5][2].setY(5);
				}
		
				else {

					this.battlefield[2][14] = this.catapults[0];
					this.battlefield[2][14].setX(14);
					this.battlefield[2][14].setY(2);

					this.battlefield[5][14] = this.ballistas[0];
					this.battlefield[5][14].setX(14);
					this.battlefield[5][14].setY(5);

					this.battlefield[3][14] = this.engineers[0];
					this.battlefield[3][14].setX(14);
					this.battlefield[3][14].setY(3);

					this.battlefield[6][14] = this.engineers[1];
					this.battlefield[6][14].setX(14);
					this.battlefield[6][14].setY(6);

					this.battlefield[3][13] = this.archers[0];
					this.battlefield[3][13].setX(13);
					this.battlefield[3][13].setY(3);

					this.battlefield[5][13] = this.archers[1];
					this.battlefield[5][13].setX(13);
					this.battlefield[5][13].setY(5);

					this.battlefield[2][12] = this.specials[0];
					this.battlefield[2][12].setX(12);
					this.battlefield[2][12].setY(2);

					this.battlefield[6][12] = this.specials[1];
					this.battlefield[6][12].setX(12);
					this.battlefield[6][12].setY(6);

					this.battlefield[3][12] = this.armored[0];
					this.battlefield[3][12].setX(12);
					this.battlefield[3][12].setY(3);

					this.battlefield[4][12] = this.armored[1];
					this.battlefield[4][12].setX(12);
					this.battlefield[4][12].setY(4);

					this.battlefield[5][12] = this.armored[2];
					this.battlefield[5][12].setX(12);
					this.battlefield[5][12].setY(5);
				}
				
				break;

			case "Leonida":
				this.catapults = ((piece.Army)player.getGeneral().army).getCatapults();
				this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
				this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
				this.archers = ((piece.Army)player.getGeneral().army).getArchers();
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();

				if(player.isHost) {

					this.battlefield[4][0] = this.catapults[0];
					this.battlefield[4][0].setX(0);
					this.battlefield[4][0].setY(4);

					this.battlefield[5][0] = this.engineers[0];
					this.battlefield[5][0].setX(0);
					this.battlefield[5][0].setY(5);

					this.battlefield[1][1] = this.cavalry[0];
					this.battlefield[1][1].setX(1);
					this.battlefield[1][1].setY(1);

					this.battlefield[7][1] = this.cavalry[1];
					this.battlefield[7][1].setX(1);
					this.battlefield[7][1].setY(7);

					this.battlefield[2][1] = this.archers[0];
					this.battlefield[2][1].setX(1);
					this.battlefield[2][1].setY(2);

					this.battlefield[3][1] = this.archers[1];
					this.battlefield[3][1].setX(1);
					this.battlefield[3][1].setY(3);

					this.battlefield[5][1] = this.archers[2];
					this.battlefield[5][1].setX(1);
					this.battlefield[5][1].setY(5);

					this.battlefield[6][1] = this.archers[3];
					this.battlefield[6][1].setX(1);
					this.battlefield[6][1].setY(6);

					this.battlefield[2][2] = this.specials[0];
					this.battlefield[2][2].setX(2);
					this.battlefield[2][2].setY(2);

					this.battlefield[3][2] = this.specials[1];
					this.battlefield[3][2].setX(2);
					this.battlefield[3][2].setY(3);

					this.battlefield[5][2] = this.specials[2];
					this.battlefield[5][2].setX(2);
					this.battlefield[5][2].setY(5);

					this.battlefield[6][2] = this.specials[3];
					this.battlefield[6][2].setX(2);
					this.battlefield[6][2].setY(6);
				}
		
				else {

					this.battlefield[4][14] = this.catapults[0];
					this.battlefield[4][14].setX(14);
					this.battlefield[4][14].setY(4);

					this.battlefield[5][14] = this.engineers[0];
					this.battlefield[5][14].setX(14);
					this.battlefield[5][14].setY(5);

					this.battlefield[1][13] = this.cavalry[0];
					this.battlefield[1][13].setX(13);
					this.battlefield[1][13].setY(1);

					this.battlefield[7][13] = this.cavalry[1];
					this.battlefield[7][13].setX(13);
					this.battlefield[7][13].setY(7);

					this.battlefield[2][13] = this.archers[0];
					this.battlefield[2][13].setX(13);
					this.battlefield[2][13].setY(2);

					this.battlefield[3][13] = this.archers[1];
					this.battlefield[3][13].setX(13);
					this.battlefield[3][13].setY(3);

					this.battlefield[5][13] = this.archers[2];
					this.battlefield[5][13].setX(13);
					this.battlefield[5][13].setY(5);

					this.battlefield[6][13] = this.archers[3];
					this.battlefield[6][13].setX(13);
					this.battlefield[6][13].setY(6);

					this.battlefield[2][12] = this.specials[0];
					this.battlefield[2][12].setX(12);
					this.battlefield[2][12].setY(2);

					this.battlefield[3][12] = this.specials[1];
					this.battlefield[3][12].setX(12);
					this.battlefield[3][12].setY(3);

					this.battlefield[5][12] = this.specials[2];
					this.battlefield[5][12].setX(12);
					this.battlefield[5][12].setY(5);

					this.battlefield[6][12] = this.specials[3];
					this.battlefield[6][12].setX(12);
					this.battlefield[6][12].setY(6);
				}
				
				break;

			case "Ragnar":
				this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
				this.archers = ((piece.Army)player.getGeneral().army).getArchers();
				this.armored = ((piece.Army)player.getGeneral().army).getArmored();

				if(player.isHost) {

					this.battlefield[0][1] = this.cavalry[0];
					this.battlefield[0][1].setX(1);
					this.battlefield[0][1].setY(0);

					this.battlefield[1][1] = this.cavalry[1];
					this.battlefield[1][1].setX(1);
					this.battlefield[1][1].setY(1);

					this.battlefield[7][1] = this.cavalry[2];
					this.battlefield[7][1].setX(1);
					this.battlefield[7][1].setY(7);

					this.battlefield[8][1] = this.cavalry[3];
					this.battlefield[8][1].setX(1);
					this.battlefield[8][1].setY(8);

					this.battlefield[2][1] = this.specials[0];
					this.battlefield[2][1].setX(1);
					this.battlefield[2][1].setY(2);

					this.battlefield[6][1] = this.specials[1];
					this.battlefield[6][1].setX(1);
					this.battlefield[6][1].setY(6);

					this.battlefield[3][2] = this.specials[2];
					this.battlefield[3][2].setX(2);
					this.battlefield[3][2].setY(3);

					this.battlefield[4][2] = this.specials[3];
					this.battlefield[4][2].setX(2);
					this.battlefield[4][2].setY(4);

					this.battlefield[5][2] = this.specials[4];
					this.battlefield[5][2].setX(2);
					this.battlefield[5][2].setY(5);

					this.battlefield[3][1] = this.archers[0];
					this.battlefield[3][1].setX(1);
					this.battlefield[3][1].setY(3);

					this.battlefield[5][1] = this.archers[1];
					this.battlefield[5][1].setX(1);
					this.battlefield[5][1].setY(5);

					this.battlefield[2][2] = this.armored[0];
					this.battlefield[2][2].setX(2);
					this.battlefield[2][2].setY(2);

					this.battlefield[6][2] = this.armored[1];
					this.battlefield[6][2].setX(2);
					this.battlefield[6][2].setY(6);
				}
		
				else {

					this.battlefield[0][13] = this.cavalry[0];
					the.battlefield[0][13].setX(13);
					this.battlefield[0][13].setY(0);

					this.battlefield[1][13] = this.cavalry[1];
					this.battlefield[1][13].setX(13);
					this.battlefield[1][13].setY(1);

					this.battlefield[7][13] = this.cavalry[2];
					this.battlefield[7][13].setX(13);
					this.battlefield[7][13].setY(7);

					this.battlefield[8][13] = this.cavalry[3];
					this.battlefield[8][13].setX(13);
					this.battlefield[8][13].setY(8);

					this.battlefield[2][13] = this.specials[0];
					this.battlefield[2][13].setX(13);
					this.battlefield[2][13].setY(2);

					this.battlefield[6][13] = this.specials[1];
					this.battlefield[6][13].setX(13);
					this.battlefield[6][13].setY(6);

					this.battlefield[3][13] = this.archers[0];
					this.battlefield[3][13].setX(13);
					this.battlefield[3][13].setY(3);

					this.battlefield[5][13] = this.archers[1];
					this.battlefield[5][13].setX(13);
					this.battlefield[5][13].setY(5);

					this.battlefield[2][12] = this.armored[0];
					this.battlefield[2][12].setX(12);
					this.battlefield[2][12].setY(2);

					this.battlefield[6][12] = this.armored[1];
					this.battlefield[6][12].setX(12);
					this.battlefield[6][12].setY(6);

					this.battlefield[3][12] = this.specials[2];
					this.battlefield[3][12].setX(12);
					this.battlefield[3][12].setY(3);

					this.battlefield[4][12] = this.specials[3];
					this.battlefield[4][12].setX(12);
					this.battlefield[4][12].setY(4);

					this.battlefield[5][12] = this.specials[4];
					this.battlefield[5][12].setX(12);
					this.battlefield[5][12].setY(5);
				}
				
				break;

			default:
				break;
        }                 
    }

	public Units getUnit(int x, int y){
		
		return this.battlefield[x][y];
	}

    @Override
	public String toString(){
		String s="";

		for (Units[] riga : getBattlefield()) {
			s+="[";
            for (Units elemento : riga) {
                if(elemento!=null){
					s+="X";
				}else{
					s+="_";
				}
				s+="|";
            }
			s = s.substring(0, s.length() - 1);
			s+="]\n";
		}
		return s;
	}
}
