package kot0328.minecraftBank;

import java.util.UUID;

class InstallmentPlan {
   UUID id;
   UUID owner;
   String description;
   double installmentAmount;
   int installmentsRemaining;
   long nextDueTime;
}
