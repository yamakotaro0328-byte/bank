package kot0328.minecraftBank;

import java.util.HashSet;
import java.util.UUID;

class Quest {
   UUID id;
   UUID posterId;
   String worldName;
   double x;
   double y;
   double z;
   double reward;
   QuestState state = QuestState.AVAILABLE;
   UUID acceptedBy;
   long cooldownUntil;
   int requiredTeamSize = 1;
   HashSet<UUID> teamMembers = new HashSet<>();
   HashSet<UUID> teamArrived = new HashSet<>();
}
