package kot0328.minecraftBank;

import java.util.UUID;
import org.bukkit.OfflinePlayer;

public interface MinecraftBankAPI {
   double getPocketBalance(OfflinePlayer var1);

   double getPersonalBankBalance(UUID var1);

   int getCreditScoreOf(UUID var1);

   double getTreasuryBalance();

   String getActiveEconomyEvent();
}
