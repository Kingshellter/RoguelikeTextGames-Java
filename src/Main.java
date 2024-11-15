import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Hero hero = new Hero("Adventurer");
        Weapon sword = new Weapon("Iron Sword", 5, 0);
        hero.equipWeapon(sword);

        System.out.println("Battle starts!");
        for (int stage = 1; stage <= 3; stage++) {
            System.out.println("\n--- Stage " + stage + " ---");
            playStage(hero, stage);
            if (!hero.isAlive()) {
                System.out.println(hero.getName() + " was defeated. Game Over!");
                System.out.println("FInal Score : " + hero.getScore());
                System.out.println("Final Score: " + hero.getScore());
                return;
            }
        }
        System.out.println("Congratulations! " + hero.getName() + " has completed all stages!");
        System.out.println("Final Level: " + hero.level);
        System.out.println("Final Score: " + hero.getScore());
    }

    private static void playStage(Hero hero, int stage) {
        Monster[] monsters = new Monster[3];
        BossMonster boss;

        switch (stage) {
            case 1:
                for (int i = 0; i < 3; i++) {
                    monsters[i] = new Monster("Slime " + (i + 1), 30, 5, 10);
                }
                boss = new BossMonster("Slime King", 80, 15, 50);
                break;
            case 2:
                for (int i = 0; i < 3; i++) {
                    monsters[i] = new Monster("Goblin " + (i + 1), 50, 10, 20);
                }
                boss = new BossMonster("Troll", 120, 20, 100);
                break;
            case 3:
                for (int i = 0; i < 3; i++) {
                    monsters[i] = new Monster("Mini Dragon " + (i + 1), 70, 15, 30);
                }
                boss = new BossMonster("Dragon", 200, 25, 200);
                break;
            default:
                throw new IllegalStateException("Unexpected stage: " + stage);
        }

        for (Monster monster : monsters) {
            System.out.println("\nA " + monster.getName() + " appears!");
            startBattle(hero, monster);
            if (!hero.isAlive()) return;
        }

        System.out.println("\nThe boss " + boss.getName() + " appears!");
        startBattle(hero, boss);
    }

    private static void startBattle(Hero hero, Monster monster) {
        Scanner scanner = new Scanner(System.in);

        while (monster.isAlive() && hero.isAlive()) {
            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Heal");
            System.out.println("4. Use Fireball (cd : " + hero.getFireballCooldown() + "turns)");
            System.out.println("5. Use Explosion (cd : " + hero.getExplosionCooldown() + "turns)");
            System.out.print("Enter choice (1-5): ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                hero.attack(monster);
            } else if (choice == 2) {
                hero.defend();
            } else if (choice == 3) {
                hero.heal();
            } else if (choice == 4) {
                hero.useSkill(monster, "fireball");
            } else if (choice == 5) {
                hero.useSkill(monster, "explosion");
            } else {
                System.out.println("Invalid choice, try again.");
                continue;
            }

            if (monster.isAlive()) {
                monster.attack(hero);
            }

            displayStatus(hero, monster);
            hero.resetDefense();
            hero.reduceSkillCooldowns(); // Reduce cooldowns each turn
        }

        if (hero.isAlive()) {
            System.out.println("Victory! " + hero.getName() + " defeated " + monster.getName());
            hero.addScore(monster.getExpValue());
        }
    }

    private static void displayStatus(Hero hero, Monster monster) {
        System.out.println(hero.getName() + " HP: " + hero.getHealth() + "/" + hero.maxHealth);
        System.out.println(monster.getName() + " HP: " + monster.getHealth() + "/" + monster.maxHealth);
    }
}
