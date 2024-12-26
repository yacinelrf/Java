public class Barbarian extends Warrior {
    public Barbarian(String name, int hp, int level, int endurance, int attack) {
        super(name, hp, level, endurance, attack);
    }

    public Barbarian(String name, int hp, int endurance, int attack) {
        super(name, hp, endurance, attack);
    }

    public boolean meHit(Perso target) {
        if (getEndurance() < 3) {
            System.out.println(getName() + " doesn't have enough endurance to use meHit!");
            return false;
        }
        if (!isAlive()) {
            System.out.println(getName() + " is dead and cannot use meHit!");
            return false;
        }
        if (!target.isAlive()) {
            System.out.println(target.getName() + " is already dead!");
            return false;
        }

        // Barbarian attack logic
        target.takeDamage(getAttack() * 2);
        System.out.println(getName() + " strikes " + target.getName() + " with meHit for " + (getAttack() * 2) + " damage!");
        return true;
    }

    @Override
    public String toString() {
        return "Barbarian{" + super.toString() + "}";
    }
}
