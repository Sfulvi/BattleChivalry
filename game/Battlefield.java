package game;

import piece.Army;
import piece.Generals;
import piece.Units;
import piece.siegemachines.Ballista;
import piece.siegemachines.Cannon;
import piece.siegemachines.Catapult;
import piece.troops.Archer;
import piece.troops.Armored;
import piece.troops.Cavalry;
import piece.troops.Engineer;
import piece.troops.Infantry;

public class Battlefield {
	
	//lunghezza e larghezza del campo
	private static final int X = 15;
	private static final int Y = 9;
    
	//campo di battaglia come array di unità (ovvero truppe, macchine d'assedio e generali)
	public Units[][] battlefield;

	private final Infantry[] infantry;
	private final Archer[] archers;
	private final Armored[] armored;
	private final Cavalry[] cavalry;
	private final Engineer[] engineers;
	private final Units[] specials;
	private final Cannon[] cannons;
	private final Ballista[] ballistas;
	private final Catapult[] catapults;

	private Generals g;
	private Army a;
	private boolean isHost;

	public Battlefield() {
    	this.battlefield = new Units[Y][X];

		infantry = a.getInfantry();
		archers = a.getArchers();
		armored = a.getArmored();
		cavalry = a.getCavalry();
		engineers = a.getEngineers();
		specials = a.getSpecials();
		cannons = a.getCannons();
		ballistas = a.getBallistas();
		catapults = a.getCatapults();

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
		if(isHost) {
			this.battlefield[4][1] = g;
                	g.setX(1);
                	g.setY(4);
        	}

        	else {
                	this.battlefield[4][13] = g;
                	g.setX(13);
                	g.setY(4);
		}
		
		//in base al generale scelto, metto le altre unità nelle loro posizioni predefinite
		switch(g.getName()) {
			
			//test
			case "Ghandi":
				break;
				
			case "Sun Tzu":
				
				if(isHost) {

		
					this.battlefield[2][0] = cannons[0];
					this.battlefield[6][0] = cannons[1];
					this.battlefield[3][0] = engineers[0];
					this.battlefield[7][0] = engineers[1];
					this.battlefield[3][1] = specials[0];
					this.battlefield[5][1] = specials[1];
					this.battlefield[3][2] = infantry[0];
					this.battlefield[4][2] = infantry[1];
					this.battlefield[5][2] = infantry[2];
					this.battlefield[2][2] = armored[0];
					this.battlefield[6][2] = armored[1];

					setCoordinates("Sun Tzu", true);
			
				}
				
				else {

					this.battlefield[2][14] = cannons[0];
					this.battlefield[6][14] = cannons[1];
					this.battlefield[3][14] = engineers[0];
					this.battlefield[7][14] = engineers[1];
					this.battlefield[3][13] = specials[0];
					this.battlefield[5][13] = specials[1];
					this.battlefield[3][12] = infantry[0];
					this.battlefield[4][12] = infantry[1];
					this.battlefield[5][12] = infantry[2];
					this.battlefield[2][12] = armored[0];
					this.battlefield[6][12] = armored[1];

					setCoordinates("Sun Tzu", false);
			
				}
				
				break;

			case "Nobunaga Oda":
		
				if(isHost) {
			
					this.battlefield[4][0] = ballistas[0];
					this.battlefield[5][0] = engineers[0];
					this.battlefield[1][1] = cavalry[0];
					this.battlefield[7][1] = cavalry[1];
					this.battlefield[2][1] = archers[0];
					this.battlefield[3][1] = archers[1];
					this.battlefield[5][1] = archers[2];
					this.battlefield[6][1] = archers[3];
					this.battlefield[2][2] = infantry[0];
					this.battlefield[6][2] = infantry[1];
					this.battlefield[3][2] = specials[0];
					this.battlefield[5][2] = specials[1];
					this.battlefield[4][2] = armored[0];

					setCoordinates("Nobunaga Oda", true);
				}
		
				else {
			
					this.battlefield[4][14] = ballistas[0];
					this.battlefield[5][14] = engineers[0];
					this.battlefield[1][13] = cavalry[0];
					this.battlefield[7][13] = cavalry[1];
					this.battlefield[2][13] = archers[0];
					this.battlefield[3][13] = archers[1];
					this.battlefield[5][13] = archers[2];
					this.battlefield[6][13] = archers[3];
					this.battlefield[2][12] = infantry[0];
					this.battlefield[6][12] = infantry[1];
					this.battlefield[3][12] = specials[0];
					this.battlefield[5][12] = specials[1];
					this.battlefield[4][12] = armored[0];

					setCoordinates("Nobunaga Oda", false);
				}
				
				break;

			case "King Arthur":
		
				if(isHost) {
			
					this.battlefield[2][0] = catapults[0];
					this.battlefield[5][0] = catapults[1];
					this.battlefield[3][0] = engineers[0];
					this.battlefield[6][0] = engineers[1];
					this.battlefield[1][1] = cavalry[0];
					this.battlefield[7][1] = cavalry[1];
					this.battlefield[3][1] = archers[0];
					this.battlefield[5][1] = archers[1];
					this.battlefield[2][2] = infantry[0];
					this.battlefield[4][2] = infantry[1];
					this.battlefield[6][2] = infantry[2];
					this.battlefield[3][2] = specials[0];
					this.battlefield[5][2] = specials[1];

					setCoordinates("King Arthur", true);
				}
		
				else {
			
					this.battlefield[2][14] = catapults[0];
					this.battlefield[5][14] = catapults[1];
					this.battlefield[3][14] = engineers[0];
					this.battlefield[6][14] = engineers[1];
					this.battlefield[1][13] = cavalry[0];
					this.battlefield[7][13] = cavalry[1];
					this.battlefield[3][13] = archers[0];
					this.battlefield[5][13] = archers[1];
					this.battlefield[2][12] = infantry[0];
					this.battlefield[4][12] = infantry[1];
					this.battlefield[6][12] = infantry[2];
					this.battlefield[3][12] = specials[0];
					this.battlefield[5][12] = specials[1];

					setCoordinates("King Arthur", false);
				}
				
				break;

			case "Julius Caesar":
		
				if(isHost) {
			
					this.battlefield[2][0] = catapults[0];
					this.battlefield[5][0] = ballistas[0];
					this.battlefield[3][0] = engineers[0];
					this.battlefield[6][0] = engineers[1];
					this.battlefield[3][1] = archers[0];
					this.battlefield[5][1] = archers[1];
					this.battlefield[2][2] = specials[0];
					this.battlefield[6][2] = specials[1];
					this.battlefield[3][2] = armored[0];
					this.battlefield[4][2] = armored[1];
					this.battlefield[5][2] = armored[2];

					setCoordinates("Julius Caesar", true);
				}
		
				else {

					this.battlefield[2][14] = catapults[0];
					this.battlefield[5][14] = ballistas[0];
					this.battlefield[3][14] = engineers[0];
					this.battlefield[6][14] = engineers[1];
					this.battlefield[3][13] = archers[0];
					this.battlefield[5][13] = archers[1];
					this.battlefield[2][12] = specials[0];
					this.battlefield[6][12] = specials[1];
					this.battlefield[3][12] = armored[0];
					this.battlefield[4][12] = armored[1];
					this.battlefield[5][12] = armored[2];

					setCoordinates("Julius Caesar", false);
				}
				
				break;

			case "Leonida":
		
				if(isHost) {

					this.battlefield[4][0] = catapults[0];
					this.battlefield[5][0] = engineers[0];
					this.battlefield[1][1] = cavalry[0];
					this.battlefield[7][1] = cavalry[1];
					this.battlefield[2][1] = archers[0];
					this.battlefield[3][1] = archers[1];
					this.battlefield[5][1] = archers[2];
					this.battlefield[6][1] = archers[3];
					this.battlefield[2][2] = specials[0];
					this.battlefield[3][2] = specials[1];
					this.battlefield[5][2] = specials[2];
					this.battlefield[6][2] = specials[3];

					setCoordinates("Leonida", true);
				}
		
				else {

					this.battlefield[4][14] = catapults[0];
					this.battlefield[5][14] = engineers[0];
					this.battlefield[1][13] = cavalry[0];
					this.battlefield[7][13] = cavalry[1];
					this.battlefield[2][13] = archers[0];
					this.battlefield[3][13] = archers[1];
					this.battlefield[5][13] = archers[2];
					this.battlefield[6][13] = archers[3];
					this.battlefield[2][12] = specials[0];
					this.battlefield[3][12] = specials[1];
					this.battlefield[5][12] = specials[2];
					this.battlefield[6][12] = specials[3];
					
					setCoordinates("Leonida", false);
				}
				
				break;

			case "Ragnar":
		
				if(isHost) {

					this.battlefield[0][1] = cavalry[0];
					this.battlefield[1][1] = cavalry[1];
					this.battlefield[7][1] = cavalry[2];
					this.battlefield[8][1] = cavalry[3];
					this.battlefield[2][1] = specials[0];
					this.battlefield[6][1] = specials[1];
					this.battlefield[3][1] = archers[0];
					this.battlefield[5][1] = archers[1];
					this.battlefield[2][2] = armored[0];
					this.battlefield[6][2] = armored[1];
					this.battlefield[3][2] = specials[2];
					this.battlefield[4][2] = specials[3];
					this.battlefield[5][2] = specials[4];

					setCoordinates("Ragnar", true);
				}
		
				else {

					this.battlefield[0][13] = cavalry[0];
					this.battlefield[1][13] = cavalry[1];
					this.battlefield[7][13] = cavalry[2];
					this.battlefield[8][13] = cavalry[3];
					this.battlefield[2][13] = specials[0];
					this.battlefield[6][13] = specials[1];
					this.battlefield[3][13] = archers[0];
					this.battlefield[5][13] = archers[1];
					this.battlefield[2][12] = armored[0];
					this.battlefield[6][12] = armored[1];
					this.battlefield[3][12] = specials[2];
					this.battlefield[4][12] = specials[3];
					this.battlefield[5][12] = specials[4];

					setCoordinates("Ragnar", false);
				}
				
				break;

			default:
				break;
        }                 
    }

	public void setCoordinates(String genName, boolean isHost) {

		switch(genName) {

			//test
			case "Ghandi":
				break;
				
			case "Sun Tzu":

				if(isHost) {

					
					cannons[0].setX(2);
					cannons[0].setY(0);
					
					cannons[1].setX(6);
					cannons[1].setY(0);

					engineers[0].setX(3);
					engineers[0].setY(0);

					engineers[1].setX(7);
					engineers[1].setY(0);

					specials[0].setX(3);
					specials[0].setY(1);

					specials[1].setX(5);
					specials[1].setY(1);

					infantry[0].setX(3);
					infantry[0].setY(2);

					infantry[1].setX(4);
					infantry[1].setY(2);

					infantry[2].setX(5);
					infantry[2].setY(2);

					armored[0].setX(2);
					armored[0].setY(2);

					armored[1].setX(6);
					armored[1].setY(2);
				}
				
				else {

					cannons[0].setX(2);
					cannons[0].setY(14);

					cannons[1].setX(6);
					cannons[1].setY(14);

					engineers[0].setX(3);
					engineers[0].setY(14);

					engineers[1].setX(7);
					engineers[1].setY(14);

					specials[0].setX(3);
					specials[0].setY(13);

					specials[1].setX(5);
					specials[1].setY(13);

					infantry[0].setX(3);
					infantry[0].setY(12);

					infantry[1].setX(4);
					infantry[1].setY(12);

					infantry[2].setX(5);
					infantry[2].setY(12);

					armored[0].setX(2);
					armored[0].setY(12);

					armored[1].setX(6);
					armored[1].setY(12);
				}

				break;

			case "Nobunaga Oda":
		
				if(isHost) {

					ballistas[0].setX(4);
					ballistas[0].setY(0);

					engineers[0].setX(5);
					engineers[0].setY(0);

					cavalry[0].setX(1);
					cavalry[0].setY(1);

					cavalry[1].setX(7);
					cavalry[1].setY(1);

					archers[0].setX(2);
					archers[0].setY(1);

					archers[1].setX(3);
					archers[1].setY(1);

					archers[2].setX(5);
					archers[2].setY(1);

					archers[3].setX(6);
					archers[3].setY(1);

					infantry[0].setX(2);
					infantry[0].setY(2);

					infantry[1].setX(6);
					infantry[1].setY(2);

					specials[0].setX(3);
					specials[0].setY(2);

					specials[1].setX(5);
					specials[1].setY(2);

					armored[0].setX(4);
					armored[0].setY(2);
				}

				else {

					ballistas[0].setX(4);
					ballistas[0].setY(14);

					engineers[0].setX(5);
					engineers[0].setY(14);

					cavalry[0].setX(1);
					cavalry[0].setY(13);

					cavalry[1].setX(7);
					cavalry[1].setY(13);

					archers[0].setX(2);
					archers[0].setY(13);

					archers[1].setX(3);
					archers[1].setY(13);

					archers[2].setX(5);
					archers[2].setY(13);

					archers[3].setX(6);
					archers[3].setY(13);

					infantry[0].setX(2);
					infantry[0].setY(12);

					infantry[1].setX(6);
					infantry[1].setY(12);

					specials[0].setX(3);
					specials[0].setY(12);

					specials[1].setX(5);
					specials[1].setY(12);

					armored[0].setX(4);
					armored[0].setY(12);
				}

				break;

			case "King Arthur":
		
				if(isHost) {

					catapults[0].setX(2);
					catapults[0].setY(0);

					catapults[1].setX(5);
					catapults[1].setY(0);

					engineers[0].setX(3);
					engineers[0].setY(0);

					engineers[1].setX(6);
					engineers[1].setY(0);

					cavalry[0].setX(1);
					cavalry[0].setY(1);

					cavalry[1].setX(7);
					cavalry[1].setY(1);

					archers[0].setX(3);
					archers[0].setY(1);

					archers[1].setX(5);
					archers[1].setY(1);

					infantry[0].setX(2);
					infantry[0].setY(2);

					infantry[1].setX(4);
					infantry[1].setY(2);

					infantry[2].setX(6);
					infantry[2].setY(2);

					specials[0].setX(3);
					specials[0].setY(2);

					specials[1].setX(5);
					specials[1].setY(2);
				}

				else {

					catapults[0].setX(2);
					catapults[0].setY(14);

					catapults[1].setX(5);
					catapults[1].setY(14);

					engineers[0].setX(3);
					engineers[0].setY(14);

					engineers[1].setX(6);
					engineers[1].setY(14);

					cavalry[0].setX(1);
					cavalry[0].setY(13);

					cavalry[1].setX(7);
					cavalry[1].setY(13);

					archers[0].setX(3);
					archers[0].setY(13);

					archers[1].setX(5);
					archers[1].setY(13);

					infantry[0].setX(2);
					infantry[0].setY(12);

					infantry[1].setX(4);
					infantry[1].setY(12);

					infantry[2].setX(6);
					infantry[2].setY(12);

					specials[0].setX(3);
					specials[0].setY(12);

					specials[1].setX(5);
					specials[1].setY(12);
				}

				break;

			case "Julius Caesar":

				if(isHost) {

					catapults[0].setX(2);
					catapults[0].setY(0);

					ballistas[0].setX(5);
					ballistas[0].setY(0);

					engineers[0].setX(3);
					engineers[0].setY(0);

					engineers[1].setX(6);
					engineers[1].setY(0);

					archers[0].setX(3);
					archers[0].setY(1);

					archers[1].setX(5);
					archers[1].setY(1);

					specials[0].setX(2);
					specials[0].setY(2);

					specials[1].setX(6);
					specials[1].setY(2);

					armored[0].setX(3);
					armored[0].setY(2);

					armored[1].setX(4);
					armored[1].setY(2);

					armored[2].setX(5);
					armored[2].setY(2);
				}

				else {

					catapults[0].setX(2);
					catapults[0].setY(14);

					ballistas[0].setX(5);
					ballistas[0].setY(14);

					engineers[0].setX(3);
					engineers[0].setY(14);

					engineers[1].setX(6);
					engineers[1].setY(14);

					archers[0].setX(3);
					archers[0].setY(13);

					archers[1].setX(5);
					archers[1].setY(13);

					specials[0].setX(2);
					specials[0].setY(12);

					specials[1].setX(6);
					specials[1].setY(12);

					armored[0].setX(3);
					armored[0].setY(12);

					armored[1].setX(4);
					armored[1].setY(12);

					armored[2].setX(5);
					armored[2].setY(12);
				}

				break;

			case "Leonida":

				if(isHost) {

					catapults[0].setX(4);
					catapults[0].setY(0);

					engineers[0].setX(5);
					engineers[0].setY(0);

					cavalry[0].setX(1);
					cavalry[0].setY(1);

					cavalry[1].setX(7);
					cavalry[1].setY(1);

					archers[0].setX(2);
					archers[0].setY(1);

					archers[1].setX(3);
					archers[1].setY(1);

					archers[2].setX(5);
					archers[2].setY(1);

					archers[3].setX(6);
					archers[3].setY(1);

					specials[0].setX(2);
					specials[0].setY(2);

					specials[1].setX(3);
					specials[1].setY(2);

					specials[2].setX(5);
					specials[2].setY(2);

					specials[3].setX(6);
					specials[3].setY(2);
				}

				else {

					catapults[0].setX(4);
					catapults[0].setY(14);

					engineers[0].setX(5);
					engineers[0].setY(14);

					cavalry[0].setX(1);
					cavalry[0].setY(13);

					cavalry[1].setX(7);
					cavalry[1].setY(13);

					archers[0].setX(2);
					archers[0].setY(13);

					archers[1].setX(3);
					archers[1].setY(13);

					archers[2].setX(5);
					archers[2].setY(13);

					archers[3].setX(6);
					archers[3].setY(13);

					specials[0].setX(2);
					specials[0].setY(12);

					specials[1].setX(3);
					specials[1].setY(12);

					specials[2].setX(5);
					specials[2].setY(12);

					specials[3].setX(6);
					specials[3].setY(12);
				}

				break;

			case "Ragnar":

				if(isHost) {

					cavalry[0].setX(0);
					cavalry[0].setY(1);

					cavalry[1].setX(1);
					cavalry[1].setY(1);

					cavalry[2].setX(7);
					cavalry[2].setY(1);

					cavalry[3].setX(8);
					cavalry[3].setY(1);

					specials[0].setX(2);
					specials[0].setY(1);

					specials[1].setX(6);
					specials[1].setY(1);

					archers[0].setX(3);
					archers[0].setY(1);

					archers[1].setX(5);
					archers[1].setY(1);

					armored[0].setX(2);
					armored[0].setY(2);

					armored[1].setX(6);
					armored[1].setY(2);

					specials[2].setX(3);
					specials[2].setY(2);

					specials[3].setX(4);
					specials[3].setY(2);

					specials[4].setX(5);
					specials[4].setY(2);
				}

				else {

					cavalry[0].setX(0);
					cavalry[0].setY(13);

					cavalry[1].setX(1);
					cavalry[1].setY(13);

					cavalry[2].setX(7);
					cavalry[2].setY(13);

					cavalry[3].setX(8);
					cavalry[3].setY(13);

					specials[0].setX(2);
					specials[0].setY(13);

					specials[1].setX(6);
					specials[1].setY(13);

					archers[0].setX(3);
					archers[0].setY(13);

					archers[1].setX(5);
					archers[1].setY(13);

					armored[0].setX(2);
					armored[0].setY(12);

					armored[1].setX(6);
					armored[1].setY(12);

					specials[2].setX(3);
					specials[2].setY(12);

					specials[3].setX(4);
					specials[3].setY(12);

					specials[4].setX(5);
					specials[4].setY(12);
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
