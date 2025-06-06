package piece;

public class Army {
	private String general;
	private Infantry[] infantry;
	private Archer[] archers;
	private Armored[] armored;
	private Cavalry[] cavalry;
	private Engineer[] engineers;
	private Units[] specials;
	private Cannon[] cannons;
	private Ballista[] ballistas;
	private Catapult[] catapults;
	
	public Army(String general, boolean faction) {
		setGeneral(general);
		deploy(general,faction);
	}

	private void deploy(String general, boolean faction){
		switch(general) {
			case "Ghandi":
				Infantry[] infantryG = null;
				Archer[] archersG = null;
				Armored[] armoredG = null;
				Cavalry[] cavalryG = null;
				Engineer[] engineersG = null;
				Units[] specialsG = null;
				Cannon[] cannonsG = null;
				Ballista[] ballistasG = null;
				Catapult[] catapultsG = null;

				setInfantry(infantryG);
				setArchers(archersG);
				setArmored(armoredG);
				setCavalry(cavalryG);
				setEngineers(engineersG);
				setSpecials(specialsG);
				setCannons(cannonsG);
				setBallistas(ballistasG);
				setCatapults(catapultsG);
				break;
			case "Sun Tzu":
				Infantry[] infantryS= {new Infantry("Infantry1", faction),
									  new Infantry("Infantry2", faction),
									  new Infantry("Infantry3", faction)};
				Archer[] archersS = null;
				Armored[] armoredS = {new Armored("Armored1", faction),
									 new Armored("Armored2", faction)};
				Cavalry[] cavalryS = null;
				Engineer[] engineersS = {new Engineer("Engineer1", faction),
										new Engineer("Engineer2", faction)};
				Units[] specialsS = {new Musketeer("Musketeer1", faction),
									new Musketeer("Musketeer2", faction)};
				Cannon[] cannonsS = {new Cannon("Cannon1", faction),
									new Cannon("Cannon2", faction)};
				Ballista[] ballistasS = null;
				Catapult[] catapultsS = null;

				setInfantry(infantryS);
				setArchers(archersS);
				setArmored(armoredS);
				setCavalry(cavalryS);
				setEngineers(engineersS);
				setSpecials(specialsS);
				setCannons(cannonsS);
				setBallistas(ballistasS);
				setCatapults(catapultsS);
				break;
			case "Nobunaga Oda":
				Infantry[] infantryN = {new Infantry("Infantry1", faction),
										new Infantry("Infantry2", faction)};
				Archer[] archersN = {new Archer("Archer1", faction),
									new Archer("Archer2", faction),
									new Archer("Archer3", faction),
									new Archer("Archer4", faction)};
				Armored[] armoredN = {new Armored("Armored1", faction)};
				Cavalry[] cavalryN = {new Cavalry("Cavalry1", faction),
									 new Cavalry("Cavalry2", faction)};
				Engineer[] engineersN = {new Engineer("Engineer1", faction)};
				Units[] specialsN = {new Samurai("Samurai1", faction),
									new Samurai("Samurai2", faction)};
				Cannon[] cannonsN = null;
				Ballista[] ballistasN = {new Ballista("Ballista1", faction)};
				Catapult[] catapultsN = null;

				setInfantry(infantryN);
				setArchers(archersN);
				setArmored(armoredN);
				setCavalry(cavalryN);
				setEngineers(engineersN);
				setSpecials(specialsN);
				setCannons(cannonsN);
				setBallistas(ballistasN);
				setCatapults(catapultsN);
				break;
			case "King Arthur":
				Infantry[] infantryK = {new Infantry("Infantry1", faction),
									   new Infantry("Infantry2", faction),
										 new Infantry("Infantry3", faction)};;
				Archer[] archersK = {new Archer("Archer1", faction),
									new Archer("Archer2", faction)};
				Armored[] armoredK = null;
				Cavalry[] cavalryK = {new Cavalry("Cavalry1", faction),
									  new Cavalry("Cavalry2", faction)};
				Engineer[] engineersK = {new Engineer("Engineer1", faction),
										new Engineer("Engineer2", faction)};
				Units[] specialsK = {new Knight("Knight1", faction),
									new Knight("Knight2", faction)};
				Cannon[] cannonsK = null;
				Ballista[] ballistasK = null;
				Catapult[] catapultsK = {new Catapult("Catapult1", faction),
										new Catapult("Catapult2", faction)};

				setInfantry(infantryK);
				setArchers(archersK);
				setArmored(armoredK);
				setCavalry(cavalryK);
				setEngineers(engineersK);
				setSpecials(specialsK);
				setCannons(cannonsK);
				setBallistas(ballistasK);
				setCatapults(catapultsK);
				break;
			case "Julius Ceasar":
				Infantry[] infantryJ = null;
				Archer[] archersJ = {new Archer("Archer1", faction),
									new Archer("Archer2", faction)};
				Armored[] armoredJ = {new Armored("Armored1", faction),
									 new Armored("Armored2", faction),
									 new Armored("Armored3", faction)};
				Cavalry[] cavalryJ = null;
				Engineer[] engineersJ = {new Engineer("Engineer1", faction),
										new Engineer("Engineer2", faction)};
				Units[] specialsJ = {new Gladiator("Gladiator1", faction),
									new Gladiator("Gladiator2", faction)};
				Cannon[] cannonsJ = null;
				Ballista[] ballistasJ = {new Ballista("Ballista1", faction)};
				Catapult[] catapultsJ = {new Catapult("Catapult1", faction)};
				
				setInfantry(infantryJ);
				setArchers(archersJ);
				setArmored(armoredJ);
				setCavalry(cavalryJ);
				setEngineers(engineersJ);
				setSpecials(specialsJ);
				setCannons(cannonsJ);
				setBallistas(ballistasJ);
				setCatapults(catapultsJ);
				break;
			case "Leonida":			
				Infantry[] infantryL = null;
				Archer[] archersL = {new Archer("Archer1", faction),
									new Archer("Archer2", faction),
									new Archer("Archer3", faction),
									new Archer("Archer4", faction)};
				Armored[] armoredL = null;
				Cavalry[] cavalryL = {new Cavalry("Cavalry1", faction),
									   new Cavalry("Cavalry2", faction)};
				Engineer[] engineersL = {new Engineer("Engineer1", faction)};
				Units[] specialsL = {new Spartan("Spartan1", faction),
									new Spartan("Spartan2", faction),
									new Spartan("Spartan3", faction),
									new Spartan("Spartan4", faction)};
				Cannon[] cannonsL = null;
				Ballista[] ballistasL = null;
				Catapult[] catapultsL = {new Catapult("Catapult1", faction)};

				setInfantry(infantryL);
				setArchers(archersL);
				setArmored(armoredL);
				setCavalry(cavalryL);
				setEngineers(engineersL);
				setSpecials(specialsL);
				setCannons(cannonsL);
				setBallistas(ballistasL);
				setCatapults(catapultsL);
				break;
			case "Ragnar":
				Infantry[] infantryR = null;
				Archer[] archersR = {new Archer("Archer1", faction),
									new Archer("Archer2", faction)};
				Armored[] armoredR = {new Armored("Armored1", faction),
									 new Armored("Armored2", faction)};
				Cavalry[] cavalryR = {new Cavalry("Cavalry1", faction),
									   new Cavalry("Cavalry2", faction),
									   new Cavalry("Cavalry3", faction),
									   new Cavalry("Cavalry4", faction)};
				Engineer[] engineersR = null;
				Units[] specialsR = {new Berserker("Berserker1", faction),
									new Berserker("Berserker2", faction),
									new Berserker("Berserker3", faction),
									new Berserker("Berserker4", faction),
									new Berserker("Berserker5", faction)};
				Cannon[] cannonsR = null;
				Ballista[] ballistasR = null;
				Catapult[] catapultsR = null;

				setInfantry(infantryR);
				setArchers(archersR);
				setArmored(armoredR);
				setCavalry(cavalryR);
				setEngineers(engineersR);
				setSpecials(specialsR);
				setCannons(cannonsR);
				setBallistas(ballistasR);
				setCatapults(catapultsR);
				break;
			}
	}
	private void setInfantry(Infantry[] i) {
		this.infantry = i;
	}
	public Infantry[] getInfantry() {
		return this.infantry;
	}
	private void setArchers(Archer[] a) {
		this.archers = a;
	}
	public Archer[] getArchers() {
		return this.archers;
	}
	private void setArmored(Armored[] a) {
		this.armored = a;
	}
	public Armored[] getArmored() {
		return this.armored;
	}
	private void setCavalry(Cavalry[] c) {
		this.cavalry = c;
	}
	public Cavalry[] getCavalry() {
		return this.cavalry;
	}
	private void setEngineers(Engineer[] e) {
		this.engineers = e;
	}
	public Engineer[] getEngineers() {
		return this.engineers;
	}
	private void setSpecials(Units[] s) {
		this.specials = s;
	}
	public Units[] getSpecials() {
		return this.specials;
	}
	private void setCannons(Cannon[] c) {
		this.cannons = c;
	}
	public Cannon[] getCannons() {
		return this.cannons;
	}
	private void setBallistas(Ballista[] b) {
		this.ballistas = b;
	}
	public Ballista[] getBallistas() {
		return this.ballistas;
	}
	private void setCatapults(Catapult[] c) {
		this.catapults = c;
	}
	public Catapult[] getCatapults() {
		return this.catapults;
	}
	
	public String getGeneral() {
		return general;
	}
	public void setGeneral(String general) {
		this.general = general;
	}
	
}
