package mc.rellox.extractableenchantments.hook;

import net.Indyuce.mmoitems.ItemStats;
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem;
import net.Indyuce.mmoitems.api.item.mmoitem.MMOItem;
import net.Indyuce.mmoitems.stat.data.EnchantListData;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MMOItemsHook implements IHook {


    @Override
    public void enable() {

    }

    public void remove(ItemStack item, Enchantment enchantment) {
        MMOItem mmoItem;
        try {
            mmoItem = new LiveMMOItem(item);
        } catch (Exception ignored) {
            return;
        }

        if (!(mmoItem.getData(ItemStats.ENCHANTS) instanceof EnchantListData data)) {
            return;
        }

        data.addEnchant(enchantment, -1);
        mmoItem.setData(ItemStats.ENCHANTS, data);
        ItemStack after = mmoItem.newBuilder().build();
        if (after == null) return;

        ItemMeta applied = after.getItemMeta();
        if (applied == null) return;
        item.setItemMeta(applied);
    }

    @Override
    public String name() {
        return "MMOItems";
    }
}
