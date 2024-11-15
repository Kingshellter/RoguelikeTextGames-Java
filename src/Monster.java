public class Monster extends Character {
    private int expValue;

    public Monster(String name, int health, int damage, int expValue) {
        super(name, health, damage);
        this.expValue = expValue;
    }

    public int getExpValue() {
        return expValue;
    }

    @Override
    public void attack(Character target) {
        target.setHealth(target.getHealth() - damage);
        System.out.println(getName() + " attacks " + target.getName() + " for " + damage + " damage!");
    }

    @Override
    public void levelUp() {
    }
}

class BossMonster extends Monster {
    public BossMonster(String name, int health, int damage, int expValue) {
        super(name, health, damage, expValue);
    }

    @Override
    public void attack(Character target) {
        int bossDamage = damage + 5;
        target.setHealth(target.getHealth() - bossDamage);
        System.out.println(getName() + " (Boss) attacks " + target.getName() + " for " + bossDamage + " damage!");
    }
}
