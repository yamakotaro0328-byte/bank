package kot0328.minecraftBank;

import java.util.UUID;
import org.bukkit.OfflinePlayer;

public interface MinecraftBankAPI {
    public double getPocketBalance(OfflinePlayer player);

    public double getPersonalBankBalance(UUID playerId);

    public int getCreditScoreOf(UUID playerId);

    public double getTreasuryBalance();

    public String getActiveEconomyEvent();
}

