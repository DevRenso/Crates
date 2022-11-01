package dev.renso.crates.manager;

import com.google.common.collect.Lists;
import lombok.Data;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

@Data
public class Crate {

    public static List<Crate> crateList = Lists.newArrayList();

    private Location loc;
    private String name, displayName, keyName;
    private List<ItemStack> inventory;
    private ItemStack keyItem, itemDisplay;
    private boolean enable, commands;
    private int prize;
    private List<String> commandsList;

    public Crate(){
        crateList.add(this);
    }

}

