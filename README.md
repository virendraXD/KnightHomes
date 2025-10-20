# KnightHomes 🏠

KnightHomes is a lightweight and customizable Minecraft plugin that allows players to set, teleport to, and manage personal homes with a clean and modern GUI with sound effects.

## ✨ Features
- Set, teleport to, and delete homes
- Customizable home limits via permissions
- GUI-based home management and sound effects on an event
- **Change icons and their colors directly in `config.yml` — no server restart needed!**  
  Just run `/home reload` to apply changes instantly.
- Simple configuration

## 📥 Installation
Download the latest .jar from Modrinth.

Download: 
https://modrinth.com/plugin/knighthomes


Place it into your server's plugins folder.

Restart the server.

Configure config.yml to your liking.

## 📜 Commands
| Command          | Description                      | Permission        |
|------------------|----------------------------------|-------------------|
| `/home`          | Opens the home management menu   | knighthomes.home  |
| `/home reload`   | Reloads the plugin configuration | home.reload       |

## ⚙️ Permissions
| Permissions                   | Description                               |
|-------------------------------|-------------------------------------------|
|`home.reload`                  |  Allows user to reload the Home plugin    |
|`knighthomes.home`             |  Allows use of /home command              |
|`knighthomes.home.limit.1`     |  Allows setting 1 home                    |
|`knighthomes.home.limit.2`     |  Allows setting 2 home                    |
|`knighthomes.home.limit.3`     |  Allows setting 3 home                    |
|`knighthomes.home.limit.4`     |  Allows setting 4 home                    |
|`knighthomes.home.limit.5`     |  Allows setting 5 home                    |
|`knighthomes.cooldown.bypass`  |  Allows bypass teleport cooldown          |

## ⚙️ Config (Allow changing Icons and their colors)
```yaml
# +----------------------------------------------------------------------------------------------+ #
# |                                           ICONS                                               | #
# +----------------------------------------------------------------------------------------------+ #

# Define Unicode icons for different actions. These are shown in messages.    
icons:
  player: "⭐"                      # Shown before player names
  homes_owned: "🏠"                 # Shown before personal home names/messages
  home_slot: "▣"                    # Used for home slots   
  empty_slot: "🫙"                  # Used for empty slots
  locked_slot: "🔒"                 # Used for locked slots
  delete_home: "❌"                 # Used for home deletion
  set_home: "➕"                    # Used when setting a home  
  set_home_confirmed: "✔"           # Used when home is set successfully
  teleport_cancelled: "✖"           # Used when teleportation is cancelled
  teleporting: "✈"                  # Used when teleporting
  combat_teleport_blocked: "⛔"     # Shown when teleport is blocked due to combat
  home_teleport_success: "🌀"       # Shown when teleported successfully
  home_deleted: "🗑"                 # Shown when home is deleted
  out_of_combat: "🛡"                # Shown when player out of combat
  in_combat: "⚔"                    # Shown when player enters combat

# +----------------------------------------------------------------------------------------------+ #
# |                                           COLORS                                              | #
# +----------------------------------------------------------------------------------------------+ #

# Colors use '&' codes and are applied to icons/messages.
colors:
  player: "&6"
  homes_owned: "&f"                  # Green
  home_slot: "&a"                    # Green
  empty_slot: "&7"                   # Gray
  locked_slot: "&4"                  # Dark Red
  delete_home: "&4"                  # Dark Red
  set_home: "&e"                     # Yellow
  set_home_confirmed: "&e"           # Yellow
  teleport_cancelled: "&4"           # Dark Red
  teleporting: "&f"                  # White
  combat_teleport_blocked: "&4"      # Dark Red
  home_teleport_success: "&2"        # Dark Green
  home_deleted: "&4"                 # Dark Red
  out_of_combat: "&2"                # Dark Green
  in_combat: "&4"                    # Dark Red
```

## Support & Issue
🐛 If you find a bug or have a suggestion, please open an issue here:
https://github.com/virendraXD/KnightHomes/issues

Discord: https://discord.gg/uySRT32aRD

## 📄 License
This project is licensed under the MIT License — see the LICENSE file for details.
Credit is required when using or modifying this code.

