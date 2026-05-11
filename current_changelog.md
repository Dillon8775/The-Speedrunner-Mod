# Version 1.12 (Fabric 26.1.2)

- `Updated to 26.1.2!`
- The mod now requires `Fabric Loader version 0.19.2 to run.`

## Worldgen Changes
- `Speedrunner blocks` now have a small chance to generate within patches of `raw speedrunner blocks` in the `Speedrunner's Wasteland biome.`

## Dragon's Aura Changes
- Lowered the default dragon's aura duration to `2 minutes` (previously 4).
- Lowered the increased duration of the dragon's aura to `6 minutes` (previously 8).

## Retired Speedrunner Changes
- Retired speedrunner trades are now `data-driven.`
- Tweaked `retired speedrunner trades.`
  - Retired speedrunners `now sell potions` instead of tipped arrows.
  - Retired speedrunners can `now sell emeralds for 3 speedrunner ingots.`
  - Retired speedrunners `no longer sell max level enchanted books.` `Levels are now random.`

## Doom Mode Changes
- Reverted back to the `default doom trees.`
- Reverted back to `default Goliath sounds` (low-pitched zombie sounds).
  - Dillon note: I sounded stupid making those sounds lol
- `Goliath` no longer needs to `see a player` in order `to target it.`

## Other Changes
- Removed the `"Better Villager Trades"` option.
- `Fireproof boats now float a little higher in lava.`
- New splash texts.
- Added `Discord link` for `Mod menu.`
- Updated compatibility for [Quality of Queso](https://modrinth.com/mod/quality-of-queso) and [Simple Keybinds](https://modrinth.com/mod/simple-keybinds).
- Removed `tutorial mode feature images.`

### Technical Additions
- New item tags: `retired_speedrunner_potions`, and `retired_speedrunner_level_#`
  - Retired speedrunner potions are potions that can be sold by retired speedrunner's (*not* including dragon's aura, as that is a separate trade)
  - Retired speedrunner level **#** are level tags that contain each trade that a retired speedrunner can offer at their profession level.
- Updated initialization success message.
- Renamed `mixin options,` according to Minecraft's official mappings.
- Removed `"RenderLayersMixin"`, because it has been removed, and is not needed anymore.

## Bugs Fixed
- Fixed (rare) critical crash with mixins.