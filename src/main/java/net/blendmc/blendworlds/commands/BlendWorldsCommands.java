package net.blendmc.blendworlds.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import co.aikar.commands.annotation.HelpCommand;
import co.aikar.commands.annotation.Optional;
import co.aikar.commands.annotation.Subcommand;

import org.bukkit.command.CommandSender;

@CommandAlias("blendworlds|blendworld|blws|blw")
@Description("Blendworlds main command.")
public class BlendWorldsCommands extends BaseCommand {

    // /blendworlds create world_name <enviroment> <type> <seed>
    @Subcommand("create")
    @CommandCompletion("@nothing @environments @generators @nothing")
    @Description("Creates a world with the given parameters.")
    public void onWorldCreate(CommandSender sender, String world, @Default("test") @Optional String environment, @Optional @Default("test") String type, @Default("110000") @Optional long seed) {
        System.out.println(sender);
    }

    @Default
    @HelpCommand
    public static void onHelp(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }


}