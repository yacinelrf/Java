public class Warrior extends Perso{
    public int attackPoints;
    public int endurancePoints;

    public Warrior(String name, int hp, int level, int endurance, int attack) { 
        super(name,hp,level);
        if (endurance < 0) {
            throw new IllegalArgumentException("Endurance must be greater than or equal to 0.");
        }
        if (attack < 0) {
            throw new IllegalArgumentException("Attack must be greater than or equal to 0.");
        }
        attackPoints=attack;
        endurancePoints=endurance;
    } 
    public Warrior(String name, int hp, int endurance, int attack) { 
        super(name, hp);
        attackPoints=attack;
        endurancePoints=endurance;
    }
    public int getAttack() { 
        return attackPoints;
    }
    public int getEndurance() { 
        return endurancePoints;
    }
    public boolean attack(Perso p) { 
        if (endurancePoints <= 0) {
            System.out.println(this.getName() + " has no endurance left to attack!");
            return false;
        }
        if (!this.isAlive()) {
            System.out.println(this.getName() + " is dead and cannot attack!");
            return false;
        }
        if (!p.isAlive()) {
            System.out.println(p.getName() + " is already dead!");
            return false;
        }

        p.takeDamage(attackPoints); // Target loses health equal to attack points
        endurancePoints--;              // Warrior spends 1 endurance point
        System.out.println(this.getName() + " attacks " + p.getName() + " for " + attackPoints + " damage!");
        return true;
    }
    @Override
    public String toString() {
        return super.toString() + ", endurance=" + endurancePoints + ", attack=" + attackPoints;
    }

@Override
    public void rest(){
        super.rest();
        if(endurancePoints<10){
            endurancePoints++;
        }
    }

}
