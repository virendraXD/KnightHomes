package com.knightgost.knighthomes;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Player;

public class MessageUtils {

    public static void sendColoredMessage(JavaPlugin plugin, Player player, String key, String message) {
        player.sendMessage(getColoredIconMessage(plugin, key, message));
    }

    public static Component getColoredIconMessage(JavaPlugin plugin, String key, String message) {
        FileConfiguration config = plugin.getConfig();

        String icon = config.getString("icons." + key, "");
        String colorCode = config.getString("colors." + key, "&f");

        NamedTextColor iconColor = parseColorCode(colorCode);

        return Component.text()
            .append(Component.text(icon + " ", iconColor).decoration(TextDecoration.ITALIC, false)) // Icon with configured color (no italic)
            .append(colorAmpersands(message)).decoration(TextDecoration.ITALIC, false)               // Message with & color codes
            .build();
    }

    // Parses legacy '&' codes to NamedTextColor
    private static NamedTextColor parseColorCode(String code) {
        if (code != null && code.length() == 2 && code.charAt(0) == '&') {
            return switch (Character.toLowerCase(code.charAt(1))) {
                case '0' -> NamedTextColor.BLACK;
                case '1' -> NamedTextColor.DARK_BLUE;
                case '2' -> NamedTextColor.DARK_GREEN;
                case '3' -> NamedTextColor.DARK_AQUA;
                case '4' -> NamedTextColor.DARK_RED;
                case '5' -> NamedTextColor.DARK_PURPLE;
                case '6' -> NamedTextColor.GOLD;
                case '7' -> NamedTextColor.GRAY;
                case '8' -> NamedTextColor.DARK_GRAY;
                case '9' -> NamedTextColor.BLUE;
                case 'a' -> NamedTextColor.GREEN;
                case 'b' -> NamedTextColor.AQUA;
                case 'c' -> NamedTextColor.RED;
                case 'd' -> NamedTextColor.LIGHT_PURPLE;
                case 'e' -> NamedTextColor.YELLOW;
                case 'f' -> NamedTextColor.WHITE;
                default -> NamedTextColor.WHITE;
            };
        }
        return NamedTextColor.WHITE;
    }

    // Converts &-style codes into colored components
    private static Component colorAmpersands(String message) {
        Component component = Component.empty();
        char[] chars = message.toCharArray();

        NamedTextColor currentColor = NamedTextColor.WHITE;
        StringBuilder buffer = new StringBuilder();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '&' && i + 1 < chars.length) {
                // Flush existing buffered text with current color
                if (buffer.length() > 0) {
                    component = component.append(Component.text(buffer.toString(), currentColor));
                    buffer.setLength(0);
                }

                char colorChar = Character.toLowerCase(chars[i + 1]);
                currentColor = parseColorCode("&" + colorChar);
                i++; // Skip the next character
            } else {
                buffer.append(chars[i]);
            }
        }

        // Append the remaining text
        if (buffer.length() > 0) {
            component = component.append(Component.text(buffer.toString(), currentColor));
        }

        return component;
    }
}
