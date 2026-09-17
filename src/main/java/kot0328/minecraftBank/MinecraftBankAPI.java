/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bukkit.OfflinePlayer
 */
package kot0328.minecraftBank;

import java.util.UUID;
import org.bukkit.OfflinePlayer;

public interface MinecraftBankAPI {
    public double getPocketBalance(OfflinePlayer var1);

    public double getPersonalBankBalance(UUID var1);

    public int getCreditScoreOf(UUID var1);

    public double getTreasuryBalance();

    public String getActiveEconomyEvent();
}

