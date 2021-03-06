package com.feed_the_beast.ftbutilities.command.ranks;

import com.feed_the_beast.ftblib.FTBLib;
import com.feed_the_beast.ftblib.lib.command.CmdBase;
import com.feed_the_beast.ftbutilities.FTBUtilities;
import com.feed_the_beast.ftbutilities.ranks.Rank;
import com.feed_the_beast.ftbutilities.ranks.Ranks;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

/**
 * @author LatvianModder
 */
public class CmdAdd extends CmdBase
{
	public CmdAdd()
	{
		super("add", Level.OP);
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException
	{
		if (!Ranks.isActive())
		{
			throw FTBLib.error(sender, "feature_disabled_server");
		}

		checkArgs(sender, args, 1);

		if (!Ranks.isValidName(args[0]))
		{
			throw FTBUtilities.error(sender, "commands.ranks.add.id_invalid", args[0]);
		}
		else if (!Ranks.INSTANCE.getRank(args[0]).isNone())
		{
			throw FTBUtilities.error(sender, "commands.ranks.add.id_exists", args[0]);
		}

		Rank rank = new Rank(Ranks.INSTANCE, args[0]);

		if (args.length == 2)
		{
			String pid = args[1].toLowerCase();
			rank.parent = Ranks.INSTANCE.getRank(pid);

			if (rank.parent.isNone())
			{
				throw FTBUtilities.error(sender, "commands.ranks.not_found", pid);
			}
		}

		Ranks.INSTANCE.addRank(rank);
		sender.sendMessage(FTBUtilities.lang(sender, "commands.ranks.add.added", rank.getDisplayName()));
	}
}