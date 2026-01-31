package game;

import piece.Troops;
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
			this.battlefield[4][1] = java.util.Optional.of(player.getGeneral()).map(g -> { g.setX(1); g.setY(4); return g; }).orElse(null);
		} else {
			this.battlefield[4][13] = java.util.Optional.of(player.getGeneral()).map(g -> { g.setX(13); g.setY(4); return g; }).orElse(null);
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

					this.battlefield[2][0] = java.util.Optional.of(this.cannons[0]).map(c -> { c.setX(0); c.setY(2); return c; }).orElse(null);
					this.battlefield[6][0] = java.util.Optional.of(this.cannons[1]).map(c -> { c.setX(0); c.setY(6); return c; }).orElse(null);

					this.battlefield[3][0] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(0); e.setY(3); return e; }).orElse(null);
					this.battlefield[7][0] = java.util.Optional.of(this.engineers[1]).map(e -> { e.setX(0); e.setY(7); return e; }).orElse(null);

					this.battlefield[3][1] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(1); s.setY(3); return s; }).orElse(null);
					this.battlefield[5][1] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(1); s.setY(5); return s; }).orElse(null);

					this.battlefield[3][2] = java.util.Optional.of(this.infantry[0]).map(i -> { i.setX(2); i.setY(3); return i; }).orElse(null);
					this.battlefield[4][2] = java.util.Optional.of(this.infantry[1]).map(i -> { i.setX(2); i.setY(4); return i; }).orElse(null);
					this.battlefield[5][2] = java.util.Optional.of(this.infantry[2]).map(i -> { i.setX(2); i.setY(5); return i; }).orElse(null);

					this.battlefield[2][2] = java.util.Optional.of(this.armored[0]).map(a -> { a.setX(2); a.setY(2); return a; }).orElse(null);
					this.battlefield[6][2] = java.util.Optional.of(this.armored[1]).map(a -> { a.setX(2); a.setY(6); return a; }).orElse(null);

				} else {

					this.battlefield[2][14] = java.util.Optional.of(this.cannons[0]).map(c -> { c.setX(14); c.setY(2); return c; }).orElse(null);
					this.battlefield[6][14] = java.util.Optional.of(this.cannons[1]).map(c -> { c.setX(14); c.setY(6); return c; }).orElse(null);

					this.battlefield[3][14] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(14); e.setY(3); return e; }).orElse(null);
					this.battlefield[7][14] = java.util.Optional.of(this.engineers[1]).map(e -> { e.setX(14); e.setY(7); return e; }).orElse(null);

					this.battlefield[3][13] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(13); s.setY(3); return s; }).orElse(null);
					this.battlefield[5][13] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(13); s.setY(5); return s; }).orElse(null);

					this.battlefield[3][12] = java.util.Optional.of(this.infantry[0]).map(i -> { i.setX(12); i.setY(3); return i; }).orElse(null);
					this.battlefield[4][12] = java.util.Optional.of(this.infantry[1]).map(i -> { i.setX(12); i.setY(4); return i; }).orElse(null);
					this.battlefield[5][12] = java.util.Optional.of(this.infantry[2]).map(i -> { i.setX(12); i.setY(5); return i; }).orElse(null);

					this.battlefield[2][12] = java.util.Optional.of(this.armored[0]).map(a -> { a.setX(12); a.setY(2); return a; }).orElse(null);
					this.battlefield[6][12] = java.util.Optional.of(this.armored[1]).map(a -> { a.setX(12); a.setY(6); return a; }).orElse(null);
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
			
					this.battlefield[4][0] = java.util.Optional.of(this.ballistas[0]).map(b -> { b.setX(0); b.setY(4); return b; }).orElse(null);

					this.battlefield[5][0] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(0); e.setY(5); return e; }).orElse(null);

					this.battlefield[1][1] = java.util.Optional.of(this.cavalry[0]).map(c -> { c.setX(1); c.setY(1); return c; }).orElse(null);
					this.battlefield[7][1] = java.util.Optional.of(this.cavalry[1]).map(c -> { c.setX(1); c.setY(7); return c; }).orElse(null);
					
					this.battlefield[2][1] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(1); a.setY(2); return a; }).orElse(null);
					this.battlefield[3][1] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(1); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][1] = java.util.Optional.of(this.archers[2]).map(a -> { a.setX(1); a.setY(5); return a; }).orElse(null);
					this.battlefield[6][1] = java.util.Optional.of(this.archers[3]).map(a -> { a.setX(1); a.setY(6); return a; }).orElse(null);

					this.battlefield[2][2] = java.util.Optional.of(this.infantry[0]).map(i -> { i.setX(2); i.setY(2); return i; }).orElse(null);
					this.battlefield[6][2] = java.util.Optional.of(this.infantry[1]).map(i -> { i.setX(2); i.setY(6); return i; }).orElse(null);

					this.battlefield[3][2] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(2); s.setY(3); return s; }).orElse(null);
					this.battlefield[5][2] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(2); s.setY(5); return s; }).orElse(null);

					this.battlefield[4][2] = java.util.Optional.of(this.armored[0]).map(a -> { a.setX(2); a.setY(4); return a; }).orElse(null);
				
				} else {
			
					this.battlefield[4][14] = java.util.Optional.of(this.ballistas[0]).map(b -> { b.setX(14); b.setY(4); return b; }).orElse(null);
					
					this.battlefield[5][14] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(14); e.setY(5); return e; }).orElse(null);

					this.battlefield[1][13] = java.util.Optional.of(this.cavalry[0]).map(c -> { c.setX(13); c.setY(1); return c; }).orElse(null);
					this.battlefield[7][13] = java.util.Optional.of(this.cavalry[1]).map(c -> { c.setX(13); c.setY(7); return c; }).orElse(null);

					this.battlefield[2][13] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(13); a.setY(2); return a; }).orElse(null);
					this.battlefield[3][13] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(13); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][13] = java.util.Optional.of(this.archers[2]).map(a -> { a.setX(13); a.setY(5); return a; }).orElse(null);
					this.battlefield[6][13] = java.util.Optional.of(this.archers[3]).map(a -> { a.setX(13); a.setY(6); return a; }).orElse(null);

					this.battlefield[2][12] = java.util.Optional.of(this.infantry[0]).map(i -> { i.setX(12); i.setY(2); return i; }).orElse(null);
					this.battlefield[6][12] = java.util.Optional.of(this.infantry[1]).map(i -> { i.setX(12); i.setY(6); return i; }).orElse(null);

					this.battlefield[3][12] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(12); s.setY(3); return s; }).orElse(null);
					this.battlefield[5][12] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(12); s.setY(5); return s; }).orElse(null);
					
					this.battlefield[4][12] = java.util.Optional.of(this.armored[0]).map(a -> { a.setX(12); a.setY(4); return a; }).orElse(null);
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
			
					this.battlefield[2][0] = java.util.Optional.of(this.catapults[0]).map(c -> { c.setX(0); c.setY(2); return c; }).orElse(null);
					this.battlefield[5][0] = java.util.Optional.of(this.catapults[1]).map(c -> { c.setX(0); c.setY(5); return c; }).orElse(null);

					this.battlefield[3][0] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(0); e.setY(3); return e; }).orElse(null);
					this.battlefield[6][0] = java.util.Optional.of(this.engineers[1]).map(e -> { e.setX(0); e.setY(6); return e; }).orElse(null);

					this.battlefield[1][1] = java.util.Optional.of(this.cavalry[0]).map(c -> { c.setX(1); c.setY(1); return c; }).orElse(null);
					this.battlefield[7][1] = java.util.Optional.of(this.cavalry[1]).map(c -> { c.setX(1); c.setY(7); return c; }).orElse(null);

					this.battlefield[3][1] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(1); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][1] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(1); a.setY(5); return a; }).orElse(null);

					this.battlefield[2][2] = java.util.Optional.of(this.infantry[0]).map(i -> { i.setX(2); i.setY(2); return i; }).orElse(null);
					this.battlefield[4][2] = java.util.Optional.of(this.infantry[1]).map(i -> { i.setX(2); i.setY(4); return i; }).orElse(null);
					this.battlefield[6][2] = java.util.Optional.of(this.infantry[2]).map(i -> { i.setX(2); i.setY(6); return i; }).orElse(null);

					this.battlefield[3][2] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(2); s.setY(3); return s; }).orElse(null);
					this.battlefield[5][2] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(2); s.setY(5); return s; }).orElse(null);
			
				} else {
			
					this.battlefield[2][14] = java.util.Optional.of(this.catapults[0]).map(c -> { c.setX(14); c.setY(2); return c; }).orElse(null);
					this.battlefield[5][14] = java.util.Optional.of(this.catapults[1]).map(c -> { c.setX(14); c.setY(5); return c; }).orElse(null);

					this.battlefield[3][14] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(14); e.setY(3); return e; }).orElse(null);
					this.battlefield[6][14] = java.util.Optional.of(this.engineers[1]).map(e -> { e.setX(14); e.setY(6); return e; }).orElse(null);

					this.battlefield[1][13] = java.util.Optional.of(this.cavalry[0]).map(c -> { c.setX(13); c.setY(1); return c; }).orElse(null);
					this.battlefield[7][13] = java.util.Optional.of(this.cavalry[1]).map(c -> { c.setX(13); c.setY(7); return c; }).orElse(null);

					this.battlefield[3][13] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(13); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][13] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(13); a.setY(5); return a; }).orElse(null);

					this.battlefield[2][12] = java.util.Optional.of(this.infantry[0]).map(i -> { i.setX(12); i.setY(2); return i; }).orElse(null);
					this.battlefield[4][12] = java.util.Optional.of(this.infantry[1]).map(i -> { i.setX(12); i.setY(4); return i; }).orElse(null);
					this.battlefield[6][12] = java.util.Optional.of(this.infantry[2]).map(i -> { i.setX(12); i.setY(6); return i; }).orElse(null);

					this.battlefield[3][12] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(12); s.setY(3); return s; }).orElse(null);
					this.battlefield[5][12] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(12); s.setY(5); return s; }).orElse(null);
				}
				break;

			case "Julius Caesar":
				this.catapults = ((piece.Army)player.getGeneral().army).getCatapults();
				this.ballistas = ((piece.Army)player.getGeneral().army).getBallistas();
				this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
				this.archers = ((piece.Army)player.getGeneral().army).getArchers();
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
				this.armored = ((piece.Army)player.getGeneral().army).getArmored();

				if(player.isHost) {
			
					this.battlefield[2][0] = java.util.Optional.of(this.catapults[0]).map(c -> { c.setX(0); c.setY(2); return c; }).orElse(null);

					this.battlefield[5][0] = java.util.Optional.of(this.ballistas[0]).map(b -> { b.setX(0); b.setY(5); return b; }).orElse(null);

					this.battlefield[3][0] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(0); e.setY(3); return e; }).orElse(null);
					this.battlefield[6][0] = java.util.Optional.of(this.engineers[1]).map(e -> { e.setX(0); e.setY(6); return e; }).orElse(null);
					
					this.battlefield[3][1] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(1); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][1] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(1); a.setY(5); return a; }).orElse(null);

					this.battlefield[2][2] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(2); s.setY(2); return s; }).orElse(null);
					this.battlefield[6][2] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(2); s.setY(6); return s; }).orElse(null);

					this.battlefield[3][2] = java.util.Optional.of(this.armored[0]).map(a -> { a.setX(2); a.setY(3); return a; }).orElse(null);
					this.battlefield[4][2] = java.util.Optional.of(this.armored[1]).map(a -> { a.setX(2); a.setY(4); return a; }).orElse(null);
					this.battlefield[5][2] = java.util.Optional.of(this.armored[2]).map(a -> { a.setX(2); a.setY(5); return a; }).orElse(null);
				
				} else {

					this.battlefield[2][14] = java.util.Optional.of(this.catapults[0]).map(c -> { c.setX(14); c.setY(2); return c; }).orElse(null);

					this.battlefield[5][14] = java.util.Optional.of(this.ballistas[0]).map(b -> { b.setX(14); b.setY(5); return b; }).orElse(null);
					
					this.battlefield[3][14] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(14); e.setY(3); return e; }).orElse(null);
					this.battlefield[6][14] = java.util.Optional.of(this.engineers[1]).map(e -> { e.setX(14); e.setY(6); return e; }).orElse(null);

					this.battlefield[3][13] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(13); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][13] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(13); a.setY(5); return a; }).orElse(null);

					this.battlefield[2][12] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(12); s.setY(2); return s; }).orElse(null);
					this.battlefield[6][12] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(12); s.setY(6); return s; }).orElse(null);
					
					this.battlefield[3][12] = java.util.Optional.of(this.armored[0]).map(a -> { a.setX(12); a.setY(3); return a; }).orElse(null);
					this.battlefield[4][12] = java.util.Optional.of(this.armored[1]).map(a -> { a.setX(12); a.setY(4); return a; }).orElse(null);
					this.battlefield[5][12] = java.util.Optional.of(this.armored[2]).map(a -> { a.setX(12); a.setY(5); return a; }).orElse(null);
				}
				
				break;

			case "Leonida":
				this.catapults = ((piece.Army)player.getGeneral().army).getCatapults();
				this.engineers = ((piece.Army)player.getGeneral().army).getEngineers();
				this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
				this.archers = ((piece.Army)player.getGeneral().army).getArchers();
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();

				if(player.isHost) {

					this.battlefield[4][0] = java.util.Optional.of(this.catapults[0]).map(c -> { c.setX(0); c.setY(4); return c; }).orElse(null);

					this.battlefield[5][0] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(0); e.setY(5); return e; }).orElse(null);

					this.battlefield[1][1] = java.util.Optional.of(this.cavalry[0]).map(c -> { c.setX(1); c.setY(1); return c; }).orElse(null);
					this.battlefield[7][1] = java.util.Optional.of(this.cavalry[1]).map(c -> { c.setX(1); c.setY(7); return c; }).orElse(null);

					this.battlefield[2][1] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(1); a.setY(2); return a; }).orElse(null);
					this.battlefield[3][1] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(1); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][1] = java.util.Optional.of(this.archers[2]).map(a -> { a.setX(1); a.setY(5); return a; }).orElse(null);
					this.battlefield[6][1] = java.util.Optional.of(this.archers[3]).map(a -> { a.setX(1); a.setY(6); return a; }).orElse(null);

					this.battlefield[2][2] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(2); s.setY(2); return s; }).orElse(null);
					this.battlefield[3][2] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(2); s.setY(3); return s; }).orElse(null);
					this.battlefield[5][2] = java.util.Optional.of((Troops)this.specials[2]).map(s -> { s.setX(2); s.setY(5); return s; }).orElse(null);
					this.battlefield[6][2] = java.util.Optional.of((Troops)this.specials[3]).map(s -> { s.setX(2); s.setY(6); return s; }).orElse(null);
				
				} else {

					this.battlefield[4][14] = java.util.Optional.of(this.catapults[0]).map(c -> { c.setX(14); c.setY(4); return c; }).orElse(null);

					this.battlefield[5][14] = java.util.Optional.of(this.engineers[0]).map(e -> { e.setX(14); e.setY(5); return e; }).orElse(null);

					this.battlefield[1][13] = java.util.Optional.of(this.cavalry[0]).map(c -> { c.setX(13); c.setY(1); return c; }).orElse(null);
					this.battlefield[7][13] = java.util.Optional.of(this.cavalry[1]).map(c -> { c.setX(13); c.setY(7); return c; }).orElse(null);

					this.battlefield[2][13] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(13); a.setY(2); return a; }).orElse(null);
					this.battlefield[3][13] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(13); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][13] = java.util.Optional.of(this.archers[2]).map(a -> { a.setX(13); a.setY(5); return a; }).orElse(null);
					this.battlefield[6][13] = java.util.Optional.of(this.archers[3]).map(a -> { a.setX(13); a.setY(6); return a; }).orElse(null);

					this.battlefield[2][12] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(12); s.setY(2); return s; }).orElse(null);
					this.battlefield[3][12] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(12); s.setY(3); return s; }).orElse(null);
					this.battlefield[5][12] = java.util.Optional.of((Troops)this.specials[2]).map(s -> { s.setX(12); s.setY(5); return s; }).orElse(null);
					this.battlefield[6][12] = java.util.Optional.of((Troops)this.specials[3]).map(s -> { s.setX(12); s.setY(6); return s; }).orElse(null);
				}
				break;

			case "Ragnar":
				this.cavalry = ((piece.Army)player.getGeneral().army).getCavalry();
				this.specials = ((piece.Army)player.getGeneral().army).getSpecials();
				this.archers = ((piece.Army)player.getGeneral().army).getArchers();
				this.armored = ((piece.Army)player.getGeneral().army).getArmored();

				if(player.isHost) {

					this.battlefield[0][1] = java.util.Optional.of(this.cavalry[0]).map(c -> { c.setX(1); c.setY(0); return c; }).orElse(null);
					this.battlefield[1][1] = java.util.Optional.of(this.cavalry[1]).map(c -> { c.setX(1); c.setY(1); return c; }).orElse(null);
					this.battlefield[7][1] = java.util.Optional.of(this.cavalry[2]).map(c -> { c.setX(1); c.setY(7); return c; }).orElse(null);
					this.battlefield[8][1] = java.util.Optional.of(this.cavalry[3]).map(c -> { c.setX(1); c.setY(8); return c; }).orElse(null);

					this.battlefield[2][1] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(1); s.setY(2); return s; }).orElse(null);
					this.battlefield[6][1] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(1); s.setY(6); return s; }).orElse(null);
					this.battlefield[3][2] = java.util.Optional.of((Troops)this.specials[2]).map(s -> { s.setX(2); s.setY(3); return s; }).orElse(null);
					this.battlefield[4][2] = java.util.Optional.of((Troops)this.specials[3]).map(s -> { s.setX(2); s.setY(4); return s; }).orElse(null);
					this.battlefield[5][2] = java.util.Optional.of((Troops)this.specials[4]).map(s -> { s.setX(2); s.setY(5); return s; }).orElse(null);

					this.battlefield[3][1] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(1); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][1] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(1); a.setY(5); return a; }).orElse(null);

					this.battlefield[2][2] = java.util.Optional.of(this.armored[0]).map(a -> { a.setX(2); a.setY(2); return a; }).orElse(null);
					this.battlefield[6][2] = java.util.Optional.of(this.armored[1]).map(a -> { a.setX(2); a.setY(6); return a; }).orElse(null);
				
				} else {

					this.battlefield[0][13] = java.util.Optional.of(this.cavalry[0]).map(c -> { c.setX(13); c.setY(0); return c; }).orElse(null);
					this.battlefield[1][13] = java.util.Optional.of(this.cavalry[1]).map(c -> { c.setX(13); c.setY(1); return c; }).orElse(null);
					this.battlefield[7][13] = java.util.Optional.of(this.cavalry[2]).map(c -> { c.setX(13); c.setY(7); return c; }).orElse(null);
					this.battlefield[8][13] = java.util.Optional.of(this.cavalry[3]).map(c -> { c.setX(13); c.setY(8); return c; }).orElse(null);

					this.battlefield[2][13] = java.util.Optional.of((Troops)this.specials[0]).map(s -> { s.setX(13); s.setY(2); return s; }).orElse(null);
					this.battlefield[6][13] = java.util.Optional.of((Troops)this.specials[1]).map(s -> { s.setX(13); s.setY(6); return s; }).orElse(null);
					this.battlefield[3][12] = java.util.Optional.of((Troops)this.specials[2]).map(s -> { s.setX(12); s.setY(3); return s; }).orElse(null);
					this.battlefield[4][12] = java.util.Optional.of((Troops)this.specials[3]).map(s -> { s.setX(12); s.setY(4); return s; }).orElse(null);
					this.battlefield[5][12] = java.util.Optional.of((Troops)this.specials[4]).map(s -> { s.setX(12); s.setY(5); return s; }).orElse(null);

					this.battlefield[3][13] = java.util.Optional.of(this.archers[0]).map(a -> { a.setX(13); a.setY(3); return a; }).orElse(null);
					this.battlefield[5][13] = java.util.Optional.of(this.archers[1]).map(a -> { a.setX(13); a.setY(5); return a; }).orElse(null);

					this.battlefield[2][12] = java.util.Optional.of(this.armored[0]).map(a -> { a.setX(12); a.setY(2); return a; }).orElse(null);
					this.battlefield[6][12] = java.util.Optional.of(this.armored[1]).map(a -> { a.setX(12); a.setY(6); return a; }).orElse(null);
				}
				
				break;

			default:
				break;
        }                 
    }

	public Units getUnit(int x, int y){
		
		return this.battlefield[y][x];
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
