# Version 1.12.6 (Fabric 26.2)

## Changes
- Piglin teleported (or awakened) by the piglin awakener can no longer be teleported again.
- The piglin awakener's cooldown is now calculated based on the number of piglin teleported.
    - For example, if 3 piglin are teleported, then the piglin awakener will have a cooldown of 30 seconds.
    - The cooldown will cap at 1 minute.
- Backend optimizations for mixins.

## Bugs Fixed
- Throwing a fireball gets rid of offhand stack completely.