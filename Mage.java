public class Mage extends Perso{
    public int magicPoints;
    public int maxMagicPoints;
    public int spirit;
    


public Mage(String name, int hp, int level, int maxMagicPoints, int spirit) { 
    super(name, hp,level);
    if (maxMagicPoints < 0) {
        throw new IllegalArgumentException("Maximum magic points must be greater than or equal to 0.");
    }
    if (spirit < 0) {
        throw new IllegalArgumentException("Spirit must be greater than or equal to 0.");
    }
    this.maxMagicPoints=maxMagicPoints;
    this.spirit=spirit;
} 
public Mage(String name, int hp, int maxMagicPoints, int spirit) { 
    this(name, hp, 1, maxMagicPoints, spirit);
}
public int getSpirit() { 
    return spirit;
}
public int getMagicPoints() { 
    return magicPoints;
}
public int getMaxMagicPoints() { 
    return maxMagicPoints;
} 
public boolean electrocute(Perso p) { 
    if (magicPoints < 10) {
        System.out.println(this.getName() + " doesn't have enough magic points to electrocute!");
        return false;
    }
    if (!this.isAlive()) {
        System.out.println(this.getName() + " is dead and cannot electrocute!");
        return false;
    }
    if (!p.isAlive()) {
        System.out.println(p.getName() + " is already dead!");
        return false;
    }

    // Deduct magic points and deal damage
    magicPoints -= 10;
    int damage = this.getLevel() * 2; // Damage is twice the level of the Mage
    p.takeDamage(damage);
    System.out.println(this.getName() + " electrocutes " + p.getName() + " for " + damage + " damage!");
    return true;
}
@Override
public String toString() {
    return super.toString() + ", magicPoints=" + magicPoints + "/" + maxMagicPoints + ", spirit=" + spirit;
}

@Override
 public void rest(){
        super.rest();
        if (isAlive()) {
            magicPoints += spirit;
            if (magicPoints > maxMagicPoints) {
                magicPoints = maxMagicPoints;
            }
        }
    }

}
