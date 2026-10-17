package kot0328.minecraftBank;

import java.util.UUID;
import org.bukkit.inventory.Inventory;

class TradeSession {
   UUID id;
   UUID playerA;
   UUID playerB;
   Inventory inventory;
   boolean confirmedA = false;
   boolean confirmedB = false;
   double moneyOfferedA = 0.0;
   double moneyOfferedB = 0.0;
   boolean finished = false;
}
