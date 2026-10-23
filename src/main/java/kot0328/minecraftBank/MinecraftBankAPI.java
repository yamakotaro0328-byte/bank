package kot0328.minecraftBank;

import java.util.UUID;
import org.bukkit.OfflinePlayer;

public interface MinecraftBankAPI {
   double getPocketBalance(OfflinePlayer player);

   double getPersonalBankBalance(UUID playerId);

   int getCreditScoreOf(UUID playerId);

   double getTreasuryBalance();

   String getActiveEconomyEvent();
}
