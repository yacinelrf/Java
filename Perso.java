public class Perso {
    public String name;
    public int level;
    public int hit_points;

    public Perso(String name, int hp, int level) { 
        if(level<1){
            throw  new IllegalArgumentException("Level must be greater than or equal to 1.");

        }
        if(hp<0){
            throw new IllegalArgumentException("Hit points must be greater than or equal to 0.");
        }
        this.name= name ;
        this.level=level;
        hit_points=hp;

    }
    public Perso(String name, int hp) { 
         this(name,hp,1);
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return hit_points;
    }

    public int getLevel() {
        return level;
    }

    public boolean isAlive() {
        return !(hit_points==0);
    }

    public void levelUp() {
        level ++;
    }

    public void addHealth(int health) {
        hit_points+=health;
    }

    public void takeDamage(int damage) {
        hit_points-=damage;
    }

    @Override
    public String toString() {
        return "The Person: "+name+" has level: "+level+" health: "+hit_points;

    }

    public void rest() { 

        if(isAlive()){
            hit_points+=2;
        }
    }

}
