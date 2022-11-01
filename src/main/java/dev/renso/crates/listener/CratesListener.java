package dev.renso.crates.listener;

import dev.renso.crates.manager.Crate;
import dev.renso.crates.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class CratesListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!e.getAction().equals(Action.RIGHT_CLICK_AIR) || !e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        for(Crate crate : Crate.crateList){
            if(e.getPlayer().getItemInHand().isSimilar(crate.getKeyItem())){
                Player player = e.getPlayer();
                Utils.decrementOrRemove(player);
                for(int i =1;i<crate.getPrize();i++){
                    Random random = new Random();
                    int number = random.nextInt(crate.getInventory().size());
                    if(crate.getInventory().get(number).getType() != Material.AIR && crate.getInventory().get(number).getType() != Material.STAINED_GLASS_PANE) player.getInventory().addItem(crate.getInventory().get(number));
                }
            }

        }
    }

}
