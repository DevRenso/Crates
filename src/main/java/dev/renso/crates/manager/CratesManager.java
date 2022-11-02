package dev.renso.crates.manager;

import com.google.common.collect.Lists;
import dev.renso.crates.CratesPlugin;
import dev.renso.crates.utils.ConfigFile;
import dev.renso.crates.utils.Utils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class CratesManager {

    private ConfigFile cratesFile = CratesPlugin.getInstance().getCratesFile();

    public CratesManager(){
        init();
    }

    private void init(){
        CratesPlugin.getInstance().getCratesFile().getConfigurationSection("CRATES").getKeys(false).forEach(m -> {

            ConfigurationSection cratesSection = CratesPlugin.getInstance().getCratesFile().getSection("CRATES");

            Location loc = Utils.getLocationOfString(cratesSection.getString(m+".CRATE-LOCATION"));

            int prize = cratesSection.getInt(m+".PRIZE");

            String name = cratesSection.getString(m+".DISPLAY-NAME");
            String keyName = cratesSection.getString(m+".KEY.DISPLAY-NAME");
            List<ItemStack> itemStacks = (List<ItemStack>)cratesSection.get(m+".INVENTORY-CONTENTS");

            ItemStack keyItem = (ItemStack) cratesSection.get(m+".KEY.ITEMSTACK");
            ItemStack itemDisplay = (ItemStack) cratesSection.get(m+".ITEM-DISPLAY");

            boolean enable = cratesSection.getBoolean(m+".ENABLE");
            boolean commands = cratesSection.getBoolean(m+".COMMANDS.ENABLE");

            List<String> commandList = cratesSection.getStringList(m+".COMMANDS.LIST");

            Crate crate = new Crate();

            crate.setInventory(itemStacks);
            crate.setLoc(loc);
            crate.setPrize(prize);
            crate.setName(m);
            crate.setDisplayName(name);
            crate.setKeyName(keyName);
            crate.setKeyItem(keyItem);
            crate.setItemDisplay(itemDisplay);
            crate.setEnable(enable);
            crate.setCommands(commands);
            crate.setCommandsList(commandList);

        });
    }

    public void crateCreate(String crateName, Location loc){
        int cratePrize = 1;
        String keyName = Utils.translate("&7[ &6"+crateName+" &7] &fKey"), crateDName = Utils.translate("&7[ &6"+crateName+" &7] &fCrate");
        ItemStack keyItem = new ItemStack(Material.TRIPWIRE_HOOK), displayItem = new ItemStack(Material.SUGAR);
        boolean crateCommands = false, enable = true;
        ItemStack defaultLoot = new ItemStack(Material.APPLE);

        ItemMeta meta = defaultLoot.getItemMeta();
        meta.setDisplayName(Utils.translate("&fDefault loot"));
        meta.setLore(Utils.translate(Lists.newArrayList("&fThis is a default loot")));
        defaultLoot.setItemMeta(meta);

        ItemStack[] itemStacks = new ItemStack[]{defaultLoot};

        Crate crate = new Crate();

        crate.setName(crateName);

        setCommandList(crate);
        setCommands(crate, crateCommands);
        setPrize(crate, cratePrize);
        setLocation(crate, loc);
        setName(crate, crateDName);
        setKeyName(crate, keyName);
        setItems(crate, itemStacks);
        setEnable(crate, enable);
        setDisplayItem(crate, displayItem);
        setKeyItem(crate, keyItem);
    }

    public Crate getCrate(String name){
        return Crate.crateList.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void setLocation(Crate crate, Location loc){
        String stringLoc = Utils.getStringofLocation(loc);
        cratesFile.set("CRATES."+crate.getName()+".LOCATION", stringLoc);
        cratesFile.save();
        crate.setLoc(loc);
    }

    public void setName(Crate crate, String name){
        cratesFile.set("CRATES."+crate.getName()+".DISPLAY-NAME", name);
        cratesFile.save();
        crate.setDisplayName(name);
    }

    public void setPrize(Crate crate, int prize){
        cratesFile.set("CRATES."+crate.getName()+".PRIZE", prize);
        cratesFile.save();
        crate.setPrize(prize);
    }

    public void setEnable(Crate crate, boolean enable){
        cratesFile.set("CRATES."+crate.getName()+".ENABLE", enable);
        cratesFile.save();
        crate.setEnable(enable);
    }

    public void setCommands(Crate crate, boolean commands){
        cratesFile.set("CRATES."+crate.getName()+".COMMANDS.ENABLE", commands);
        cratesFile.save();
        crate.setCommands(commands);
    }

    public void setCommandList(Crate crate){
        List<String> commands = Lists.newArrayList();
        cratesFile.set("CRATES."+crate.getName()+".COMMANDS.LIST", commands);
        cratesFile.save();
        crate.setCommandsList(commands);
    }

    public void setItems(Crate crate, ItemStack[] itemStacks){
        cratesFile.set("CRATES."+crate.getName()+".INVENTORY-CONTENTS", itemStacks);
        cratesFile.save();
        List<ItemStack> items = (List<ItemStack>)cratesFile.get("CRATES."+crate.getName()+".INVENTORY-CONTENTS");
        crate.setInventory(items);
    }

    public void setKeyName(Crate crate, String name){
        cratesFile.set("CRATES."+crate.getName()+".KEY.DISPLAY-NAME", name);
        cratesFile.save();
        crate.setDisplayName(name);
    }

    public void setDisplayItem(Crate crate, ItemStack displayItem){
        cratesFile.set("CRATES."+crate.getName()+".DISPLAY-ITEM", displayItem);
        cratesFile.save();
        crate.setItemDisplay(displayItem);
    }

    public void setKeyItem(Crate crate, ItemStack itemStack){
        cratesFile.set("CRATES."+crate.getName()+".KEY.ITEMSTACK", itemStack);
        cratesFile.save();
        crate.setKeyItem(itemStack);
    }

}
