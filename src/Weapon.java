public class Weapon {
    private String name;
    private int damage;
    private int cooldown;
    private int remainingCooldown;

    public Weapon(String name, int damage, int cooldown) {
        this.name = name;
        this.damage = damage;
        this.cooldown = cooldown;
        this.remainingCooldown = 0;
    }

    public String getName() {
        return name;
    }

    public int getDamage() {
        return damage;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getRemainingCooldown() {
        return remainingCooldown;
    }

    public void reduceCooldown() {
        if (remainingCooldown > 0) {
            remainingCooldown--;
        }
    }

    public void resetCooldown() {
        remainingCooldown = cooldown;
    }
}
