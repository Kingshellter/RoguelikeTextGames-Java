public class Hero extends Character {
    private int score;
    private Weapon weapon;
    private Skill fireball;
    private Skill explosion;
    private boolean isDefending;
    private int healAmount;
    private int exp;

    public Hero(String name) {
        super(name, 100, 10);
        this.score = 0;
        this.isDefending = false;
        this.healAmount = 30;
        this.exp = 100;
        this.level = 1;

        this.fireball = new Skill("Fireball", 15, 3);
        this.explosion = new Skill("Explosion", 25, 5); 
    }
    
    public int getFireballCooldown() {
        return fireball.isAvailable() ? 0 : fireball.remainingCooldown;
    }

    public int getExplosionCooldown() {
        return explosion.isAvailable() ? 0 : explosion.remainingCooldown;
    }

    public int getScore() {
        return score;
        }

    public void addScore(int expValue) {
        score += expValue;
        checkLevelUp();
     }

    private void checkLevelUp() {
        while (score >= exp) {
            levelUp();
        }
    }

    @Override
    public void levelUp() {
        level++;
        score -= exp;
        exp += level * 50; 
        maxHealth += 20;
        health = maxHealth; 
        damage += 5;
        healAmount += 5; 

        System.out.println(getName() + " reached level " + level + "!");
        System.out.println("Stats increased: Health = " + maxHealth + ", Damage = " + damage + ", Heal amount = " + healAmount);
    }

    public void equipWeapon(Weapon weapon) {
        this.weapon = weapon;
        System.out.println(getName() + " equipped " + weapon.getName());
    }

    @Override
    public void attack(Character target) {
        int totalDamage = damage + (weapon != null ? weapon.getDamage() : 0);
        target.setHealth(target.getHealth() - totalDamage);
        System.out.println(getName() + " attacks " + target.getName() + " for " + totalDamage + " damage!");
    }

    public void useSkill(Character target, String skillName) {
        Skill skill = null;
        if ("fireball".equalsIgnoreCase(skillName)) {
            skill = fireball;
        } else if ("explosion".equalsIgnoreCase(skillName)) {
            skill = explosion;
        }

        if (skill != null && skill.isAvailable()) {
            skill.use();
            target.setHealth(target.getHealth() - skill.getDamage());
            System.out.println(getName() + " used " + skill.getName() + " on " + target.getName() + " for " + skill.getDamage() + " damage!");
        } else {
            System.out.println(skill != null ? skill.getName() + " is on cooldown!" : "Invalid skill!");
        }
    }

    public void heal() {
        health = Math.min(health + healAmount, maxHealth);
        System.out.println(getName() + " healed for " + healAmount + " health!");
    }

    public void reduceSkillCooldowns() {
        fireball.reduceCooldown();
        explosion.reduceCooldown();
    }

    public void defend() {
        isDefending = true;
        System.out.println(getName() + " is defending!");
    }

    public void resetDefense() {
        isDefending = false;
    }

    @Override
    public void setHealth(int health) {
        if (isDefending) {
            health += (this.health - health) / 2;
        }
        super.setHealth(health);
    }
}
