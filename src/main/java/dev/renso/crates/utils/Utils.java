package dev.renso.crates.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String translate(String msg){
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public static List<String> translate(List<String> msg){
        return msg.stream().map(Utils::translate).collect(Collectors.toList());
    }

    public static String getStringofLocation(Location loc){
        return loc.getBlockX() + "," + loc.getBlockY() + "," + loc.getBlockX() + "," + loc.getWorld().getName();
    }

    public static Location getLocationOfString(String loc){
        String[] split = loc.split(",");
        return new Location(Bukkit.getWorld(split[3]), Double.parseDouble(split[0]), Double.parseDouble(split[1]), Double.parseDouble(split[2]));

    }

    public static void decrementOrRemove(Player player){
        if(player.getItemInHand().getAmount() > 1){
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
        }else{
            player.getItemInHand().setType(Material.AIR);
        }
    }

}
