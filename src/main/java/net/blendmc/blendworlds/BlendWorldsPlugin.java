package net.blendmc.blendworlds;

import co.aikar.commands.MessageType;
import net.blendmc.blendworlds.commands.BlendWorldsCommands;
import net.blendmc.blendworlds.generators.GeneratorManager;

import co.aikar.commands.PaperCommandManager;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class BlendWorldsPlugin extends JavaPlugin {

    private static BlendWorldsPlugin instance;
    private static PaperCommandManager commandManager;

    @Override
    public void onEnable() {
        instance = this;
        this.registerCommands();
        saveDefaultConfig();
    }

    private void registerCommands() {
        commandManager = new PaperCommandManager(this);
        commandManager.enableUnstableAPI("help");

        // Blendworlds command
        commandManager.registerCommand(new BlendWorldsCommands());

        // Mesage syntax
        commandManager.setFormat(MessageType.HELP, ChatColor.YELLOW, ChatColor.BOLD, ChatColor.GRAY);

        // Command completion placeholders
        commandManager.getCommandCompletions().registerAsyncCompletion("environments", command -> {
            return GeneratorManager.getInstance().getEnvironments();
        });
        commandManager.getCommandCompletions().registerAsyncCompletion("generators", command ->
            GeneratorManager.getInstance().getGenerators()
        );

    }

    public static BlendWorldsPlugin getInstance() {
        return instance;
    }

    public static PaperCommandManager getCommandManager() {
        return commandManager;
    }
}
