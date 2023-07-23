package ru.primer.testgenerators;

import org.bukkit.plugin.java.JavaPlugin;
import ru.primer.testgenerators.command.GeneratorCommand;
import ru.primer.testgenerators.service.GeneratorService;

public final class TestGenerators extends JavaPlugin {

    private static TestGenerators instance;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        GeneratorService.loadGenerators();
        GeneratorService.startGeneratorTimer();

        getCommand("generator").setExecutor(new GeneratorCommand());
    }

    @Override
    public void onDisable() {
        GeneratorService.saveGenerators();
    }

    public static TestGenerators getInstance() {
        return instance;
    }
}
