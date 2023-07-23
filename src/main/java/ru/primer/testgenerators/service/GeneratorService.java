package ru.primer.testgenerators.service;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import ru.primer.testgenerators.TestGenerators;
import ru.primer.testgenerators.model.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GeneratorService {

    private static List<Generator> generators = new ArrayList<>();

    public static void addGenerator(Generator generator) {
        generators.add(generator);
    }

    public static void loadGenerators() {
        FileConfiguration config = TestGenerators.getInstance().getConfig();

        if(config.getConfigurationSection("generators") != null) {
            for (String key : config.getConfigurationSection("generators").getKeys(false)) {
                ItemStack itemStack = config.getItemStack("generators." + key + ".itemstack");
                Location location = config.getLocation("generators." + key + ".location");
                int cooldown = config.getInt("generators." + key + ".cooldown");
                long lastSpawn = config.getLong("generators." + key + ".lastspawn");
                generators.add(new Generator(UUID.fromString(key), itemStack, location, cooldown, lastSpawn));
            }
        }
    }

    public static void saveGenerators() {
        FileConfiguration config = TestGenerators.getInstance().getConfig();

        generators.forEach(t -> {
            config.set("generators." + t.getUuid() + ".itemstack", t.getItemStack());
            config.set("generators." + t.getUuid() + ".location", t.getLocation());
            config.set("generators." + t.getUuid() + ".cooldown", t.getCooldown());
            config.set("generators." + t.getUuid() + ".lastspawn", t.getLastSpawn());
        });

        TestGenerators.getInstance().saveConfig();
    }

    public static void startGeneratorTimer() {
        Bukkit.getScheduler().runTaskTimer(TestGenerators.getInstance(), () -> generators.forEach(t -> {
            if(t.getLastSpawn() + (t.getCooldown() * 1000L) - System.currentTimeMillis() > 0) {
                return;
            }

            t.getLocation().getWorld().dropItemNaturally(t.getLocation(), t.getItemStack());
            t.setLastSpawn(System.currentTimeMillis());
        }), 0L, 1L);
    }
}
