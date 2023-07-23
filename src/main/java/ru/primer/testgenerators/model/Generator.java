package ru.primer.testgenerators.model;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Generator {

    private final UUID uuid;
    private final ItemStack itemStack;
    private final Location location;
    private final int cooldown;
    private long lastSpawn = 0;

    public Generator(UUID uuid, ItemStack itemStack, Location location, int cooldown) {
        this.uuid = uuid;
        this.itemStack = itemStack;
        this.location = location;
        this.cooldown = cooldown;
    }

    public Generator(UUID uuid, ItemStack itemStack, Location location, int cooldown, long lastSpawn) {
        this.uuid = uuid;
        this.itemStack = itemStack;
        this.location = location;
        this.cooldown = cooldown;
        this.lastSpawn = lastSpawn;
    }

    public UUID getUuid() {
        return uuid;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public Location getLocation() {
        return location;
    }

    public int getCooldown() {
        return cooldown;
    }

    public long getLastSpawn() {
        return lastSpawn;
    }

    public void setLastSpawn(long lastSpawn) {
        this.lastSpawn = lastSpawn;
    }
}
