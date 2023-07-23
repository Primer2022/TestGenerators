package ru.primer.testgenerators.command;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import ru.primer.testgenerators.model.Generator;
import ru.primer.testgenerators.service.GeneratorService;

import java.util.UUID;

public class GeneratorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if(args.length < 6) {
            return true;
        }

        if(!args[0].equalsIgnoreCase("create")) {
            return true;
        }

        int x, y, z, cooldown;
        Material material;

        try {
            x = Integer.parseInt(args[1]);
            y = Integer.parseInt(args[2]);
            z = Integer.parseInt(args[3]);
            material = Material.valueOf(args[4].toUpperCase());
            cooldown = Integer.parseInt(args[5]);
        } catch (Exception e) {
            return true;
        }

        GeneratorService.addGenerator(new Generator(UUID.randomUUID(), new ItemStack(material),
                new Location(player.getWorld(), x, y, z), cooldown));
        player.sendMessage("Вы создали генератор");
        return true;
    }
}
