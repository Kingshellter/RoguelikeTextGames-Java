public abstract class Character implements Combat {
    private String name;
    protected int health;
    protected int maxHealth;
    protected int damage;
    protected int level;

    public Character(String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.damage = damage;
        this.level = 1;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public abstract void levelUp(); 
}
