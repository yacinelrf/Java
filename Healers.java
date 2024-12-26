public class Healers extends Mage{

    public Healers(String name,int hit_points,int level,int maxMagicPoints,int spirit){
        super(name, hit_points, level, maxMagicPoints, spirit);
    }
    public Healers(String name,int hit_points,int maxMagicPoints,int spirit){
        super(name, hit_points, maxMagicPoints, spirit);
    }
    boolean heal(Perso p){
        if (getMagicPoints() < 5) {
            System.out.println(getName() + " does not have enough magic points to heal!");
            return false;
        }
        if (!isAlive()) {
            System.out.println(getName() + " is dead and cannot heal!");
            return false;
        }
        if (!p.isAlive()) {
            System.out.println(p.getName() + " is already dead and cannot be healed!");
            return false;
        }
        magicPoints-=5;
        p.addHealth(getLevel());
        return true;
    }
    public boolean resurrect(Perso target) {
        if (getMagicPoints() < 50) {
            System.out.println(getName() + " does not have enough magic points to resurrect!");
            return false;
        }
        if (!isAlive()) {
            System.out.println(getName() + " is dead and cannot resurrect!");
            return false;
        }
        if (target.isAlive()) {
            System.out.println(target.getName() + " is already alive and cannot be resurrected!");
            return false;
        }

        // Resurrect the target
        target.addHealth(10); // Resurrection gives the target 10 health points
        magicPoints -= 50; // Deduct magic points for resurrection
        System.out.println(getName() + " resurrects " + target.getName() + "!");
        return true;
    }
    @Override
    public String toString() {
        return "Healer{" + super.toString() + "}";
    }
}
