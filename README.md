# Totem Slot

Fabric mod for **Minecraft 26.3+** that adds a dedicated slot for Totems of Undying.

📦 Download: [Modrinth](https://modrinth.com/mod/totem-slot)

## How it works
- Open the **Relics** screen (default key: **C**) and put your totems in the **Totem** slot.
- **Shift-click** a totem in your inventory to send it straight to its slot.
- When you would die:
  1. A totem in your **hand** is used first (vanilla behavior).
  2. If you're not holding one, **one totem from the slot** is consumed and saves you.

## Features
- Holds as many totems as the item's maximum stack size (works with mods that let totems stack to 64)
- Works with any item that has death protection, including modded totems
- Totems in the slot drop on death by default. You can change that with `keepTotemsOnDeath` in `config/totemslot.json`
- Respects `keepInventory`

## Compatibility
Shares the Relics screen with [Beacons Reforged](https://github.com/Nithrom/beacons-reforged) and [Conduit Reforged](https://github.com/Nithrom/Conduit-Reforged).

## Requirements
- Fabric Loader + Fabric API
- Required on both client and server

## Building
This mod depends on [Relic Slots](https://github.com/Nithrom/Relic-Slots), which is bundled into the jar.
Clone it next to this repo, in a folder named `CereWare Slots`, then run:
Requires JDK 25.

## License
LGPL-3.0-only
