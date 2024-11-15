public class Skill {
    private String name;
    private int damage;
    private int cooldown;
    int remainingCooldown;

    public Skill(String name, int damage, int cooldown) {
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

    public boolean isAvailable() {
        return remainingCooldown == 0;
    }

    public void use() {
        if (isAvailable()) {
            remainingCooldown = cooldown;
        }
    }

    public void reduceCooldown() {
        if (remainingCooldown > 0) {
            remainingCooldown--;
        }
    }
}
