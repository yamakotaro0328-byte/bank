package kot0328.minecraftBank;

import java.util.HashSet;
import java.util.UUID;

class GroupAccount {
   UUID id;
   String name;
   UUID owner;
   HashSet<UUID> members = new HashSet<>();
   double balance = 0.0;
}
