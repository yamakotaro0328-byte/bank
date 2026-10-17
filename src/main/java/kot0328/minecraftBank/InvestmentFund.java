package kot0328.minecraftBank;

import java.util.HashMap;
import java.util.UUID;

class InvestmentFund {
   UUID id;
   String name;
   UUID manager;
   HashMap<UUID, Double> contributions = new HashMap<>();
   double cashBalance = 0.0;
}
