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

        Cannon[] cannons;
        Engineer[] engineers;
        Units[] specials;
        Infantry[] infantry;
        Armored[] armored;
        Ballista[] ballistas;
        Cavalry[] cavalry;
        Archer[] archers;
        Catapult[] catapults;

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
                            cannons = ((piece.Army)player.getGeneral().army).getCannons();
                            engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            infantry = ((piece.Army)player.getGeneral().army).getInfantry();
                            armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                cannons[0].place(this.battlefield, 2, 0);
                                cannons[1].place(this.battlefield, 6, 0);
                                
                                engineers[0].place(this.battlefield, 3, 0);
                                engineers[1].place(this.battlefield, 7, 0);
                                
                                specials[0].place(this.battlefield, 3, 1);
                                specials[1].place(this.battlefield, 5, 1);
                                
                                infantry[0].place(this.battlefield, 3, 2);
                                infantry[1].place(this.battlefield, 4, 2);
                                infantry[2].place(this.battlefield, 5, 2);
                                
                                armored[0].place(this.battlefield, 2, 2);
                                armored[1].place(this.battlefield, 6, 2);
                                
                            } else {
                                
                                cannons[0].place(this.battlefield, 2, 14);
                                cannons[1].place(this.battlefield, 6, 14);
                                
                                engineers[0].place(this.battlefield, 3, 14);
                                engineers[1].place(this.battlefield, 7, 14);
                                
                                specials[0].place(this.battlefield, 3, 13);
                                specials[1].place(this.battlefield, 5, 13);
                                
                                infantry[0].place(this.battlefield, 3, 12);
                                infantry[1].place(this.battlefield, 4, 12);
                                infantry[2].place(this.battlefield, 5, 12);
                                
                                armored[0].place(this.battlefield, 2, 12);
                                armored[1].place(this.battlefield, 6, 12);
                            }
                }

			case "Nobunaga Oda" -> {
                            ballistas = ((piece.Army)player.getGeneral().army).getBallistas();
                            engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
                            specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            infantry = ((piece.Army)player.getGeneral().army).getInfantry();
                            archers = ((piece.Army)player.getGeneral().army).getArchers();
                            armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                ballistas[0].place(this.battlefield, 4, 0);
                                
                                engineers[0].place(this.battlefield, 5, 0);
                                
                                cavalry[0].place(this.battlefield, 1, 1);
                                cavalry[1].place(this.battlefield, 7, 1);
                                
                                archers[0].place(this.battlefield, 2, 1);
                                archers[1].place(this.battlefield, 3, 1);
                                archers[2].place(this.battlefield, 5, 1);
                                archers[3].place(this.battlefield, 6, 1);
                                
                                infantry[0].place(this.battlefield, 2, 2);
                                infantry[1].place(this.battlefield, 6, 2);
                                
                                specials[0].place(this.battlefield, 3, 2);
                                specials[1].place(this.battlefield, 5, 2);
                                
                                armored[0].place(this.battlefield, 4, 2);
                                
                            } else {
                                
                                ballistas[0].place(this.battlefield, 4, 14);
                                
                                engineers[0].place(this.battlefield, 5, 14);
                                
                                cavalry[0].place(this.battlefield, 1, 13);
                                cavalry[1].place(this.battlefield, 7, 13);
                                
                                archers[0].place(this.battlefield, 2, 13);
                                archers[1].place(this.battlefield, 3, 13);
                                archers[2].place(this.battlefield, 5, 13);
                                archers[3].place(this.battlefield, 6, 13);
                                
                                infantry[0].place(this.battlefield, 2, 12);
                                infantry[1].place(this.battlefield, 6, 12);
                                
                                specials[0].place(this.battlefield, 3, 12);
                                specials[1].place(this.battlefield, 5, 12);
                                
                                armored[0].place(this.battlefield, 4, 12);
                            }
                }

			case "King Arthur" -> {
                            catapults = ((piece.Army)player.getGeneral().army).getCatapults();
                            engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
                            specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            infantry = ((piece.Army)player.getGeneral().army).getInfantry();
                            archers = ((piece.Army)player.getGeneral().army).getArchers();
                            
                            if(player.isHost) {
                                
                                catapults[0].place(this.battlefield, 2, 0);
                                catapults[1].place(this.battlefield, 5, 0);
                                
                                engineers[0].place(this.battlefield, 3, 0);
                                engineers[1].place(this.battlefield, 6, 0);
                                
                                cavalry[0].place(this.battlefield, 1, 1);
                                cavalry[1].place(this.battlefield, 7, 1);
                                
                                archers[0].place(this.battlefield, 3, 1);
                                archers[1].place(this.battlefield, 5, 1);
                                
                                infantry[0].place(this.battlefield, 2, 2);
                                infantry[1].place(this.battlefield, 4, 2);
                                infantry[2].place(this.battlefield, 6, 2);
                                
                                specials[0].place(this.battlefield, 3, 2);
                                specials[1].place(this.battlefield, 5, 2);
                                
                            } else {
                                
                                catapults[0].place(this.battlefield, 2, 14);
                                catapults[1].place(this.battlefield, 5, 14);
                                
                                engineers[0].place(this.battlefield, 3, 14);
                                engineers[1].place(this.battlefield, 6, 14);
                                
                                cavalry[0].place(this.battlefield, 1, 13);
                                cavalry[1].place(this.battlefield, 7, 13);
                                
                                archers[0].place(this.battlefield, 3, 13);
                                archers[1].place(this.battlefield, 5, 13);
                                
                                infantry[0].place(this.battlefield, 2, 12);
                                infantry[1].place(this.battlefield, 4, 12);
                                infantry[2].place(this.battlefield, 6, 12);
                                
                                specials[0].place(this.battlefield, 3, 12);
                                specials[1].place(this.battlefield, 5, 12);
                            }
                }

			case "Julius Caesar" -> {
                            catapults = ((piece.Army)player.getGeneral().army).getCatapults();
                            ballistas = ((piece.Army)player.getGeneral().army).getBallistas();
                            engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            archers = ((piece.Army)player.getGeneral().army).getArchers();
                            specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                catapults[0].place(this.battlefield, 2, 0);
                                
                                ballistas[0].place(this.battlefield, 5, 0);
                                
                                engineers[0].place(this.battlefield, 3, 0);
                                engineers[1].place(this.battlefield, 6, 0);
                                
                                archers[0].place(this.battlefield, 3, 1);
                                archers[1].place(this.battlefield, 5, 1);
                                
                                specials[0].place(this.battlefield, 2, 2);
                                specials[1].place(this.battlefield, 6, 2);
                                
                                armored[0].place(this.battlefield, 3, 2);
                                armored[1].place(this.battlefield, 4, 2);
                                armored[2].place(this.battlefield, 5, 2);
                                
                            } else {
                                
                                catapults[0].place(this.battlefield, 2, 14);
                                
                                ballistas[0].place(this.battlefield, 5, 14);
                                
                                engineers[0].place(this.battlefield, 3, 14);
                                engineers[1].place(this.battlefield, 6, 14);
                                
                                archers[0].place(this.battlefield, 3, 13);
                                archers[1].place(this.battlefield, 5, 13);
                                
                                specials[0].place(this.battlefield, 2, 12);
                                specials[1].place(this.battlefield, 6, 12);
                                
                                armored[0].place(this.battlefield, 3, 12);
                                armored[1].place(this.battlefield, 4, 12);
                                armored[2].place(this.battlefield, 5, 12);
                            }
                }

			case "Leonida" -> {
                            catapults = ((piece.Army)player.getGeneral().army).getCatapults();
                            engineers = ((piece.Army)player.getGeneral().army).getEngineers();
                            cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
                            archers = ((piece.Army)player.getGeneral().army).getArchers();
                            specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            
                            if(player.isHost) {
                                
                                catapults[0].place(this.battlefield, 4, 0);
                                
                                engineers[0].place(this.battlefield, 5, 0);
                                
                                cavalry[0].place(this.battlefield, 1, 1);
                                cavalry[1].place(this.battlefield, 7, 1);
                                
                                archers[0].place(this.battlefield, 2, 1);
                                archers[1].place(this.battlefield, 3, 1);
                                archers[2].place(this.battlefield, 5, 1);
                                archers[3].place(this.battlefield, 6, 1);
                                
                                specials[0].place(this.battlefield, 2, 2);
                                specials[1].place(this.battlefield, 3, 2);
                                specials[2].place(this.battlefield, 5, 2);
                                specials[3].place(this.battlefield, 6, 2);
                                
                            } else {
                                
                                catapults[0].place(this.battlefield, 4, 14);
                                
                                engineers[0].place(this.battlefield, 5, 14);
                                
                                cavalry[0].place(this.battlefield, 1, 13);
                                cavalry[1].place(this.battlefield, 7, 13);
                                
                                archers[0].place(this.battlefield, 2, 13);
                                archers[1].place(this.battlefield, 3, 13);
                                archers[2].place(this.battlefield, 5, 13);
                                archers[3].place(this.battlefield, 6, 13);
                                
                                specials[0].place(this.battlefield, 2, 12);
                                specials[1].place(this.battlefield, 3, 12);
                                specials[2].place(this.battlefield, 5, 12);
                                specials[3].place(this.battlefield, 6, 12);
                            }
                }

			case "Ragnar" -> {
                            cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
                            specials = ((piece.Army)player.getGeneral().army).getSpecials();
                            archers = ((piece.Army)player.getGeneral().army).getArchers();
                            armored = ((piece.Army)player.getGeneral().army).getArmored();
                            
                            if(player.isHost) {
                                
                                cavalry[0].place(this.battlefield, 0, 1);
                                cavalry[1].place(this.battlefield, 1, 1);
                                cavalry[2].place(this.battlefield, 7, 1);
                                cavalry[3].place(this.battlefield, 8, 1);
                                
                                specials[0].place(this.battlefield, 2, 1);
                                specials[1].place(this.battlefield, 6, 1);
                                specials[2].place(this.battlefield, 3, 2);
                                specials[3].place(this.battlefield, 4, 2);
                                specials[4].place(this.battlefield, 5, 2);
                                
                                archers[0].place(this.battlefield, 3, 1);
                                archers[1].place(this.battlefield, 5, 1);
                                
                                armored[0].place(this.battlefield, 2, 2);
                                armored[1].place(this.battlefield, 6, 2);
                                
                            } else {
                                
                                cavalry[0].place(this.battlefield, 0, 13);
                                cavalry[1].place(this.battlefield, 1, 13);
                                cavalry[2].place(this.battlefield, 7, 13);
                                cavalry[3].place(this.battlefield, 8, 13);
                                
                                specials[0].place(this.battlefield, 2, 13);
                                specials[1].place(this.battlefield, 6, 13);
                                specials[2].place(this.battlefield, 3, 12);
                                specials[3].place(this.battlefield, 4, 12);
                                specials[4].place(this.battlefield, 5, 12);
                                
                                archers[0].place(this.battlefield, 3, 13);
                                archers[1].place(this.battlefield, 5, 13);
                                
                                armored[0].place(this.battlefield, 2, 12);
                                armored[1].place(this.battlefield, 6, 12);
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
