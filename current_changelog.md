# Version 1.12.3 (Fabric 26.2)

## Speedrunner Harness
- A new harness item, which makes happy ghasts fly faster.
- Crafted with 3 speedrunner ingots, 2 glass blocks, and 1 block of light blue wool.
- This also comes with the golden speedrunner harness, which makes ghasts fly even faster. It can be crafted in a smithing table by combining a speedrunner harness with a golden speedrunner upgrade template.
- Added an advancement titled "Speedy Ghast" for obtaining one of these harnesses.

## Speedrunner Nautilus
- A new nautilus item, which makes nautiluses traverse faster in water.
- It also makes nautiluses dash faster.
- Found in various loot chests.
- This also comes with the golden speedrunner nautilus, which makes nautiluses travel even faster. It can be crafted in a smithing table by combining golden nautilus armor with a golden speedrunner upgrade template.
- Added an advancement titled "Speedy Slosh" for obtaining one of these nautiluses.

# Main Changes
- Updated to 26.2!
- Removed speedrunner signs and speedrunner hanging signs.
    - Due to the inconsistency with creating signs and how little speedrunner signs would be used in a mod like this, they have been removed.
- A wiki page is not coming anytime soon, so feature screens have been brought back.
- The "create new world" button is now positioned differently in accordance to 26.2's pause menu changes.
- Throwing a dragons fireball now grants the "You should add a feature..." advancement.
- The "Balanced Diet" advancement no longer requires any speedrunner mod item.
- Speedrunner spears now only drain little bits of hunger, compared to normal spears.
- Added a feature screen for the speedrunner spears.
- Re-added helpful tooltips to the speedrunner's workbench.
- You can no longer go above level 10 when combining enchantments.
- Increased the "dragon mass kill radius" and "dragon immunity" from 200 blocks (x, y and z) to 300 blocks.
- Increased ender eye breaking cooldown from 3 seconds to 4 seconds.
- Renamed "Fast World Creation" settings to "World Creation Settings".
- Renamed "Fast World Creation" to "Instant World Creation".
- You can now set the seed for each world you create.
- Slightly modified biome generation.
- Tweaked the speedrunner mod item group.
- Tweaked and optmized buffed loot tables, also fixed bug where most modified loot tables don't have a random sequence.
- A couple of new splash texts.
- Removed speedrunner mod debug huds, as they are pointless.
- Removed stripped doom logs.
- Removed deactivated OptiFine button from the Other Mods screen.
- Updated mod description.
- Other small changes, tweaks and optimizations.

## Speedrunner's Wasteland Changes
- Animals now spawn correctly.
- Both taiga and plains villages can generate in the biome.
- Sheep spawn with random colors, including cyan, blue, and light blue (25% chance for each).
- Cornflowers, blue orchids and wildflowers now generate throughout the biome.
- Sculk, dripstone caves, lush caves, sulfur caves, and fossils now generate throughout the biome.
- Sugar cane generates more commonly.
- Slimes and sniffers can now spawn in the biome.

## Loot Table and Recipe Changes
- The Eye of Annul is now crafted with a piece of ender matter and a eye of ender.
- Ender matter is now slightly more common in various loot chests.
- Ender matter can now generate in buried treasures.
- Enderman now have a 33% chance to drop ender matter (previously 25%).
  - With looting, a 36% with looting 1, 39% with looting 2, and a 42% with looting 3.

## Configuration Revamp
- Renamed "Main Options" to "General Options".
- New "WorldGen" options category.
- Moved options around to different categories.
- Renamed "External" button to "Links".
- Renamed "Allow Cheats" to "Allow Commands" in Instant World Creation options.
- You can no longer enable commands or change the difficulty when creating a hardcore world.

## Doom Mode Changes
- Doom blocks now only have a 12-16% chance to drop loot when breaking with a speedrunner tool (previously 22-32).
- Breaking doom blocks with silk touch (and a safe tool) no longer does anything.
- Thrown fireballs on doom mode now have a random fireball explosion power, ranging from 1-10, with a 10% chance of having an immense explosion power of 50.
  - Additionally, you can no longer modify the "fireball explosion power" on doom mode.
- The stronghold distance and spread is now locked to Minecraft's default setting on doom mode, meaning strongholds will be difficult to find.
  - With this change, the "Stronghold Distance" and "Stronghold Spread" options are now locked on doom mode and cannot be changed.
- Increased max health of warden to 500.

## Textures and Recipes
- Another new texture for the inventory preserver.
  - This is still not the official final texture for the inventory preserver; there are plans in the future for a unique, finalized texture.
- Speedrunner paddles are now crafted with 1 speedrunner stick and 2 speedrunner planks, instead of requiring speedrunner nuggets.
- New textures for the speedrunner doors, matching the new textures introduced in a previous version.
- Speedrunner leaves, dead speedrunner leaves and doom leaves no longer admit leaf particles.

## Block Names
- Renamed the "Speedrunner Door" to "Metal Speedrunner Door", and renamed the "Speedrunner Trapdoor" to "Metal Speedrunner Trapdoor".
- Renamed "Speedrunner Weighted Pressure Plate" to "Mediate Weighted Speedrunner Pressure Plate".
- Renamed "Wooden Speedrunner Door" to "Speedrunner Door".
- Renamed "Wooden Speedrunner Trapdoor" to "Speedrunner Trapdoor".
- Renamed "Wooden Speedrunner Pressure Plate" to "Speedrunner Pressure Plate".
- Renamed "Wooden Speedrunner Button" to "Speedrunner Button".
- Renamed "Dead Wooden Speedrunner Door" to "Dead Speedrunner Door".
- Renamed "Dead Wooden Speedrunner Trapdoor" to "Dead Wooden Speedrunner Trapdoor".
- Renamed "Dead Wooden Speedrunner Pressure Plate" to "Dead Speedrunner Pressure Plate".
- Renamed "Dead Wooden Speedrunner Button" to "Dead Speedrunner Button".

## Bugs Fixed
- Fixed inconsistent tooltips with fireballs and dragons fireballs when "Throwable Fireballs" option is off.
- Fixed bug where the difficulty button can become unlocked if entering the "restrictions..." or "edit game rules..." screen.

### Technical Changes
- Renamed "triggered_by_item_criterion" advancement predicate to "itemlike_trigger".
- Block hardness tags have been cleaned up to include all the new Minecraft blocks, using their tags. Additionally, speedrunner mod blocks are not affected by block hardness tags - their block hardness values are hardcoded and cannot be changed (unless you increase the block breaking speed multiplier).
- Most buffed loot tables don't have a random sequence.
- Incorrectly named translation for smithing upgrade for the golden smithing upgrade template.
- Unable to find normal nautilus armor in shipwrecks, ocean ruins and buried treasures.