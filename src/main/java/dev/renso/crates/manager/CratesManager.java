package dev.renso.crates.manager;

import dev.renso.crates.CratesPlugin;
import dev.renso.crates.utils.ConfigFile;
import dev.renso.crates.utils.Utils;
import org.bukkit.Location;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

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

            ItemStack keyItem = (ItemStack) cratesSection.get(m+".KEY.ITEMSTACK");
            ItemStack itemDisplay = (ItemStack) cratesSection.get(m+".ITEM-DISPLAY");

            boolean enable = cratesSection.getBoolean(m+".ENABLE");
            boolean commands = cratesSection.getBoolean(m+".COMMANDS.ENABLE");

            List<String> commandList = cratesSection.getStringList(m+".COMMANDS.LIST");

            Crate crate = new Crate();

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

    public Crate getCrate(String name){
        return Crate.crateList.stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void setLocation(Crate crate, Location loc){
        String stringLoc = Utils.getStringofLocation(loc);
        cratesFile.set("CRATES."+crate.getDisplayName()+".LOCATION", stringLoc);
        crate.setLoc(loc);
    }

    public void setName(Crate crate, String name){
        cratesFile.set("CRATES."+crate.getDisplayName()+".DISPLAY-NAME", name);
        crate.setDisplayName(name);
    }

    public void setEnable(Crate crate, boolean enable){
        cratesFile.set("CRATES."+crate.getDisplayName()+".ENABLE", enable);
        crate.setEnable(enable);
    }

    public void setItems(Crate crate, ItemStack[] itemStacks){

    }

    public void setKeyName(Crate crate, String name){
        cratesFile.set("CRATES."+crate.getDisplayName()+".KEY.DISPLAY-NAME", name);
        crate.setDisplayName(name);
    }

    public void setDisplayItem(Crate crate, ItemStack displayItem){
        cratesFile.set("CRATES."+crate.getDisplayName()+".DISPLAY-ITEM", displayItem);
        crate.setItemDisplay(displayItem);
    }

    public void setKeyItem(Crate crate, ItemStack itemStack){
        cratesFile.set("CRATES."+crate.getDisplayName()+".KEY.ITEMSTACK", itemStack);
        crate.setKeyItem(itemStack);
    }

}
