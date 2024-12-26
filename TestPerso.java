public class TestPerso {
    public static void main(String[] args) {
        // Create a new Perso object
        Perso perso1 = new Perso("John", 50, 2);
        Perso perso2 = new Perso("Jane", 30, 3);

        // Display initial states
        System.out.println("Initial States:");
        System.out.println(perso1);
        System.out.println(perso2);

        // Test addHealth
        perso1.addHealth(10); // Increase health by 10
        System.out.println("\nAfter adding health to John:");
        System.out.println(perso1);

        // Test takeDamage
        perso2.takeDamage(20); // Take 20 damage
        System.out.println("\nAfter Jane takes 20 damage:");
        System.out.println(perso2);

        // Test levelUp
        perso1.levelUp(); // Level up John
        System.out.println("\nAfter leveling up John:");
        System.out.println(perso1);

        // Test isAlive
        System.out.println("\nIs John alive?");
        System.out.println(perso1.isAlive());

        System.out.println("\nIs Jane alive?");
        System.out.println(perso2.isAlive());

        // Test rest (health + 2)
        perso1.rest();
        System.out.println("\nAfter John rests:");
        System.out.println(perso1);

        // Test Warrior
        Warrior warrior = new Warrior("Thor", 100, 5, 10, 20);
        Warrior warrior2 = new Warrior("Loki", 50, 3, 5, 15);

        // Display Warrior states
        System.out.println("\nInitial Warrior States:");
        System.out.println(warrior);
        System.out.println(warrior2);

        // Test Warrior attack
        warrior.attack(warrior2); // Thor attacks Loki
        System.out.println("\nAfter Thor attacks Loki:");
        System.out.println(warrior);
        System.out.println(warrior2);

        // Test Mage
        Mage mage = new Mage("Gandalf", 80, 4, 30, 6);
        Perso mageTarget = new Perso("Saruman", 60, 3);

        // Display Mage state
        System.out.println("\nInitial Mage State:");
        System.out.println(mage);
        System.out.println(mageTarget);

        // Test Mage electrocute
        mage.electrocute(mageTarget); // Gandalf electrocutes Saruman
        System.out.println("\nAfter Gandalf electrocutes Saruman:");
        System.out.println(mage);
        System.out.println(mageTarget);

        // Test Healer
        Healers healer = new Healers("Merlin", 100, 5, 30, 8);
        Perso target = new Perso("Arthur", 30, 3);

        // Display Healer state
        System.out.println("\nInitial Healer State:");
        System.out.println(healer);
        System.out.println(target);

        // Test Healer healing
        healer.heal(target); // Merlin heals Arthur
        System.out.println("\nAfter Merlin heals Arthur:");
        System.out.println(healer);
        System.out.println(target);

        // Test Healer resurrection
        target.takeDamage(30); // Arthur dies
        healer.resurrect(target); // Merlin resurrects Arthur
        System.out.println("\nAfter Merlin resurrects Arthur:");
        System.out.println(healer);
        System.out.println(target);
    }
}
