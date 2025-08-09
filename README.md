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
Download link: https://modrinth.com/plugin/knighthomes

Place it into your server's plugins folder.

Restart the server.

Configure config.yml to your liking.

## 📜 Commands
| Command          | Description                      | Permission        |
|------------------|----------------------------------|-------------------|
| `/home`          | Opens the home management menu   | knighthomes.home  |
| `/home reload`   | Reloads the plugin configuration | home.reload       |

🐛 Issues & Support
If you find a bug or have a suggestion, please open an issue here:
https://github.com/virendraXD/KnightHomes/issues

📄 License
This project is licensed under the MIT License — see the LICENSE file for details.
Credit is required when using or modifying this code.

## ⚙️ Permissions
```yaml
home.reload:
  description: Allows player to reload the Home plugin
  default: op

knighthomes.home:
  home.reload:
    description: Allows player to reload the Home plugin
    default: op

  knighthomes.home:
    description: Allows use of /home command
    default: true

  knighthomes.home.limit.1:
    description: Allows setting 1 home
    default: true

  knighthomes.home.limit.2:
    description: Allows setting 2 homes
    default: false

  knighthomes.home.limit.3:
    description: Allows setting 3 homes
    default: false

  knighthomes.home.limit.4:
    description: Allows setting 4 homes
    default: false

  knighthomes.home.limit.5:
    description: Allows setting 5 homes
    default: op

