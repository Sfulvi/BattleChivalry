package game;

import piece.Units;
import piece.siegemachines.*;
import piece.troops.*;

public class Battlefield {
	
	//lunghezza e larghezza del campo
	private static final int X = 15;
	private static final int Y = 9;
    
	//campo di battaglia come array di unità (ovvero truppe, macchine d'assedio e generali)
	private final Units[][] battlefield;

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
			player.getGeneral().place(this.battlefield, 4, 1);
		} else {
			player.getGeneral().place(this.battlefield, 4, 13);
		}
		
		//in base al generale scelto, metto le altre unità nelle loro posizioni predefinite
		switch(player.getGeneral().getName()) {
			case "Ghandi" -> {
                }
				
			case "Sun Tzu" -> {
                            this.cannons = ((piece.Army)player.getGeneral().army).getCannons();
                            this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            this.infantry = ((piece.Army)player.getGeneral().army).getInfantry();
                            this.armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                this.cannons[0].place(this.battlefield, 2, 0);
                                this.cannons[1].place(this.battlefield, 6, 0);
                                
                                this.engineers[0].place(this.battlefield, 3, 0);
                                this.engineers[1].place(this.battlefield, 7, 0);
                                
                                this.specials[0].place(this.battlefield, 3, 1);
                                this.specials[1].place(this.battlefield, 5, 1);
                                
                                this.infantry[0].place(this.battlefield, 3, 2);
                                this.infantry[1].place(this.battlefield, 4, 2);
                                this.infantry[2].place(this.battlefield, 5, 2);
                                
                                this.armored[0].place(this.battlefield, 2, 2);
                                this.armored[1].place(this.battlefield, 6, 2);
                                
                            } else {
                                
                                this.cannons[0].place(this.battlefield, 2, 14);
                                this.cannons[1].place(this.battlefield, 6, 14);
                                
                                this.engineers[0].place(this.battlefield, 3, 14);
                                this.engineers[1].place(this.battlefield, 7, 14);
                                
                                this.specials[0].place(this.battlefield, 3, 13);
                                this.specials[1].place(this.battlefield, 5, 13);
                                
                                this.infantry[0].place(this.battlefield, 3, 12);
                                this.infantry[1].place(this.battlefield, 4, 12);
                                this.infantry[2].place(this.battlefield, 5, 12);
                                
                                this.armored[0].place(this.battlefield, 2, 12);
                                this.armored[1].place(this.battlefield, 6, 12);
                            }
                }

			case "Nobunaga Oda" -> {
                            this.ballistas = ((piece.Army)player.getGeneral().army).getBallistas();
                            this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
                            this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            this.infantry = ((piece.Army)player.getGeneral().army).getInfantry();
                            this.archers = ((piece.Army)player.getGeneral().army).getArchers();
                            this.armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                this.ballistas[0].place(this.battlefield, 4, 0);
                                
                                this.engineers[0].place(this.battlefield, 5, 0);
                                
                                this.cavalry[0].place(this.battlefield, 1, 1);
                                this.cavalry[1].place(this.battlefield, 7, 1);
                                
                                this.archers[0].place(this.battlefield, 2, 1);
                                this.archers[1].place(this.battlefield, 3, 1);
                                this.archers[2].place(this.battlefield, 5, 1);
                                this.archers[3].place(this.battlefield, 6, 1);
                                
                                this.infantry[0].place(this.battlefield, 2, 2);
                                this.infantry[1].place(this.battlefield, 6, 2);
                                
                                this.specials[0].place(this.battlefield, 3, 2);
                                this.specials[1].place(this.battlefield, 5, 2);
                                
                                this.armored[0].place(this.battlefield, 4, 2);
                                
                            } else {
                                
                                this.ballistas[0].place(this.battlefield, 4, 14);
                                
                                this.engineers[0].place(this.battlefield, 5, 14);
                                
                                this.cavalry[0].place(this.battlefield, 1, 13);
                                this.cavalry[1].place(this.battlefield, 7, 13);
                                
                                this.archers[0].place(this.battlefield, 2, 13);
                                this.archers[1].place(this.battlefield, 3, 13);
                                this.archers[2].place(this.battlefield, 5, 13);
                                this.archers[3].place(this.battlefield, 6, 13);
                                
                                this.infantry[0].place(this.battlefield, 2, 12);
                                this.infantry[1].place(this.battlefield, 6, 12);
                                
                                this.specials[0].place(this.battlefield, 3, 12);
                                this.specials[1].place(this.battlefield, 5, 12);
                                
                                this.armored[0].place(this.battlefield, 4, 12);
                            }
                }

			case "King Arthur" -> {
                            this.catapults = ((piece.Army)player.getGeneral().army).getCatapults();
                            this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
                            this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            this.infantry = ((piece.Army)player.getGeneral().army).getInfantry();
                            this.archers = ((piece.Army)player.getGeneral().army).getArchers();
                            this.armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                this.catapults[0].place(this.battlefield, 2, 0);
                                this.catapults[1].place(this.battlefield, 5, 0);
                                
                                this.engineers[0].place(this.battlefield, 3, 0);
                                this.engineers[1].place(this.battlefield, 6, 0);
                                
                                this.cavalry[0].place(this.battlefield, 1, 1);
                                this.cavalry[1].place(this.battlefield, 7, 1);
                                
                                this.archers[0].place(this.battlefield, 3, 1);
                                this.archers[1].place(this.battlefield, 5, 1);
                                
                                this.infantry[0].place(this.battlefield, 2, 2);
                                this.infantry[1].place(this.battlefield, 4, 2);
                                this.infantry[2].place(this.battlefield, 6, 2);
                                
                                this.specials[0].place(this.battlefield, 3, 2);
                                this.specials[1].place(this.battlefield, 5, 2);
                                
                            } else {
                                
                                this.catapults[0].place(this.battlefield, 2, 14);
                                this.catapults[1].place(this.battlefield, 5, 14);
                                
                                this.engineers[0].place(this.battlefield, 3, 14);
                                this.engineers[1].place(this.battlefield, 6, 14);
                                
                                this.cavalry[0].place(this.battlefield, 1, 13);
                                this.cavalry[1].place(this.battlefield, 7, 13);
                                
                                this.archers[0].place(this.battlefield, 3, 13);
                                this.archers[1].place(this.battlefield, 5, 13);
                                
                                this.infantry[0].place(this.battlefield, 2, 12);
                                this.infantry[1].place(this.battlefield, 4, 12);
                                this.infantry[2].place(this.battlefield, 6, 12);
                                
                                this.specials[0].place(this.battlefield, 3, 12);
                                this.specials[1].place(this.battlefield, 5, 12);
                            }
                }

			case "Julius Caesar" -> {
                            this.catapults = ((piece.Army)player.getGeneral().army).getCatapults();
                            this.ballistas = ((piece.Army)player.getGeneral().army).getBallistas();
                            this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            this.archers = ((piece.Army)player.getGeneral().army).getArchers();
                            this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            this.armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                this.catapults[0].place(this.battlefield, 2, 0);
                                
                                this.ballistas[0].place(this.battlefield, 5, 0);
                                
                                this.engineers[0].place(this.battlefield, 3, 0);
                                this.engineers[1].place(this.battlefield, 6, 0);
                                
                                this.archers[0].place(this.battlefield, 3, 1);
                                this.archers[1].place(this.battlefield, 5, 1);
                                
                                this.specials[0].place(this.battlefield, 2, 2);
                                this.specials[1].place(this.battlefield, 6, 2);
                                
                                this.armored[0].place(this.battlefield, 3, 2);
                                this.armored[1].place(this.battlefield, 4, 2);
                                this.armored[2].place(this.battlefield, 5, 2);
                                
                            } else {
                                
                                this.catapults[0].place(this.battlefield, 2, 14);
                                
                                this.ballistas[0].place(this.battlefield, 5, 14);
                                
                                this.engineers[0].place(this.battlefield, 3, 14);
                                this.engineers[1].place(this.battlefield, 6, 14);
                                
                                this.archers[0].place(this.battlefield, 3, 13);
                                this.archers[1].place(this.battlefield, 5, 13);
                                
                                this.specials[0].place(this.battlefield, 2, 12);
                                this.specials[1].place(this.battlefield, 6, 12);
                                
                                this.armored[0].place(this.battlefield, 3, 12);
                                this.armored[1].place(this.battlefield, 4, 12);
                                this.armored[2].place(this.battlefield, 5, 12);
                            }
                }

			case "Leonida" -> {
                            this.catapults = ((piece.Army)player.getGeneral().army).getCatapults();
                            this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
                            this.archers = ((piece.Army)player.getGeneral().army).getArchers();
                            this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            
                            if(player.isHost) {
                                
                                this.catapults[0].place(this.battlefield, 4, 0);
                                
                                this.engineers[0].place(this.battlefield, 5, 0);
                                
                                this.cavalry[0].place(this.battlefield, 1, 1);
                                this.cavalry[1].place(this.battlefield, 7, 1);
                                
                                this.archers[0].place(this.battlefield, 2, 1);
                                this.archers[1].place(this.battlefield, 3, 1);
                                this.archers[2].place(this.battlefield, 5, 1);
                                this.archers[3].place(this.battlefield, 6, 1);
                                
                                this.specials[0].place(this.battlefield, 2, 2);
                                this.specials[1].place(this.battlefield, 3, 2);
                                this.specials[2].place(this.battlefield, 5, 2);
                                this.specials[3].place(this.battlefield, 6, 2);
                                
                            } else {
                                
                                this.catapults[0].place(this.battlefield, 4, 14);
                                
                                this.engineers[0].place(this.battlefield, 5, 14);
                                
                                this.cavalry[0].place(this.battlefield, 1, 13);
                                this.cavalry[1].place(this.battlefield, 7, 13);
                                
                                this.archers[0].place(this.battlefield, 2, 13);
                                this.archers[1].place(this.battlefield, 3, 13);
                                this.archers[2].place(this.battlefield, 5, 13);
                                this.archers[3].place(this.battlefield, 6, 13);
                                
                                this.specials[0].place(this.battlefield, 2, 12);
                                this.specials[1].place(this.battlefield, 3, 12);
                                this.specials[2].place(this.battlefield, 5, 12);
                                this.specials[3].place(this.battlefield, 6, 12);
                            }
                }

			case "Ragnar" -> {
                            this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
                            this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            this.archers = ((piece.Army)player.getGeneral().army).getArchers();
                            this.armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                this.cavalry[0].place(this.battlefield, 1, 0);
                                this.cavalry[1].place(this.battlefield, 1, 1);
                                this.cavalry[2].place(this.battlefield, 1, 7);
                                this.cavalry[3].place(this.battlefield, 1, 8);
                                
                                this.specials[0].place(this.battlefield, 1, 2);
                                this.specials[1].place(this.battlefield, 6, 1);
                                this.specials[2].place(this.battlefield, 3, 2);
                                this.specials[3].place(this.battlefield, 4, 2);
                                this.specials[4].place(this.battlefield, 5, 2);
                                
                                this.archers[0].place(this.battlefield, 3, 1);
                                this.archers[1].place(this.battlefield, 5, 1);
                                
                                this.armored[0].place(this.battlefield, 2, 2);
                                this.armored[1].place(this.battlefield, 6, 2);
                                
                            } else {
                                
                                this.cavalry[0].place(this.battlefield, 13, 0);
                                this.cavalry[1].place(this.battlefield, 13, 1);
                                this.cavalry[2].place(this.battlefield, 13, 7);
                                this.cavalry[3].place(this.battlefield, 13, 8);
                                
                                this.specials[0].place(this.battlefield, 2, 13);
                                this.specials[1].place(this.battlefield, 6, 13);
                                this.specials[2].place(this.battlefield, 3, 12);
                                this.specials[3].place(this.battlefield, 4, 12);
                                this.specials[4].place(this.battlefield, 5, 12);
                                
                                this.archers[0].place(this.battlefield, 3, 13);
                                this.archers[1].place(this.battlefield, 5, 13);
                                
                                this.armored[0].place(this.battlefield, 2, 12);
                                this.armored[1].place(this.battlefield, 6, 12);
                            }
                }

			default -> {
                }
        }
    }

	public Units getUnit(int x, int y){
		
		return this.battlefield[y][x];
	}
	public void removeUnit(int x, int y){
		this.battlefield[y][x]=null;
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
