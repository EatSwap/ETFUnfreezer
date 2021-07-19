If some of your entities have been **frozen** due to my version of the EntityTrackerFixer plugin or because you have removed the ETF plugin, **this is your solution**.

This plugin unfreezes entities automatically when chunks are **unloaded**.

Additionally it has commands to **unfreeze entities near players** or in a certain **area**. It also has a command to freeze entities, just in case.

**This plugin is not compatible with versions lower than 1.17.**

================================

- Command **/etfu unfreeze**:

With **/etfu unfreeze area** you can unfreeze all the entities that are in an determined radius near to you.
> Example: **/etfu unfreeze area 30** will unfreeze all the entities that are in a radius of 30 blocks.

You can also use **/etfu unfreeze player**, which will unfreeze all entities near to a player that you specify.
> Example: **/etfu unfreeze player cascaseno** would unfreeze all entities that are near to me (in this case).

With **/etfu unfreeze player** or **/etfu unfreeze player all** you can do the same for all the online players.
> This will get all the near entities loaded by all the online players and unfreeze them.

- Command **/etfu freeze**

With this command you can freeze entities. This can be used for testing or for other purposes, but use with caution. Usage: **/etfu freeze area «area»**

Example: **/etfu freeze area 30**. This will **freeze** all the entities that are in a radius of 30 blocks near to you.

================================

**Appart from this** the plugin **AUTOMATICALLY** unfreezes entities in all worlds, so you don't have to worry.

When a player loads a chunk, the entities will still be frozen (because I can't use the ChunkLoadEvent because it has an error).

But when the chunks are unloaded, then the "ChunkUnloadEvent" will be called and automatically unfreeze all the entities in that chunks.
