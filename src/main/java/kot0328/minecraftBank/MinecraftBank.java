/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  me.clip.placeholderapi.expansion.PlaceholderExpansion
 *  net.kyori.adventure.text.Component
 *  net.kyori.adventure.text.minimessage.MiniMessage
 *  net.kyori.adventure.title.Title
 *  net.milkbowl.vault.economy.Economy
 *  org.bukkit.Bukkit
 *  org.bukkit.ChatColor
 *  org.bukkit.Location
 *  org.bukkit.Material
 *  org.bukkit.NamespacedKey
 *  org.bukkit.OfflinePlayer
 *  org.bukkit.Registry
 *  org.bukkit.Sound
 *  org.bukkit.World
 *  org.bukkit.block.Block
 *  org.bukkit.block.BlockState
 *  org.bukkit.block.TileState
 *  org.bukkit.command.Command
 *  org.bukkit.command.CommandExecutor
 *  org.bukkit.command.CommandSender
 *  org.bukkit.command.TabCompleter
 *  org.bukkit.configuration.ConfigurationSection
 *  org.bukkit.configuration.file.FileConfiguration
 *  org.bukkit.configuration.file.YamlConfiguration
 *  org.bukkit.enchantments.Enchantment
 *  org.bukkit.entity.Entity
 *  org.bukkit.entity.HumanEntity
 *  org.bukkit.entity.Player
 *  org.bukkit.entity.WanderingTrader
 *  org.bukkit.event.EventHandler
 *  org.bukkit.event.EventPriority
 *  org.bukkit.event.Listener
 *  org.bukkit.event.block.Action
 *  org.bukkit.event.block.BlockBreakEvent
 *  org.bukkit.event.entity.PlayerDeathEvent
 *  org.bukkit.event.inventory.ClickType
 *  org.bukkit.event.inventory.InventoryClickEvent
 *  org.bukkit.event.inventory.InventoryCloseEvent
 *  org.bukkit.event.inventory.InventoryDragEvent
 *  org.bukkit.event.player.AsyncPlayerChatEvent
 *  org.bukkit.event.player.PlayerInteractEntityEvent
 *  org.bukkit.event.player.PlayerInteractEvent
 *  org.bukkit.event.player.PlayerJoinEvent
 *  org.bukkit.event.player.PlayerMoveEvent
 *  org.bukkit.inventory.EquipmentSlot
 *  org.bukkit.inventory.Inventory
 *  org.bukkit.inventory.ItemFlag
 *  org.bukkit.inventory.ItemStack
 *  org.bukkit.inventory.meta.EnchantmentStorageMeta
 *  org.bukkit.inventory.meta.ItemMeta
 *  org.bukkit.persistence.PersistentDataType
 *  org.bukkit.plugin.Plugin
 *  org.bukkit.plugin.RegisteredServiceProvider
 *  org.bukkit.plugin.ServicePriority
 *  org.bukkit.plugin.java.JavaPlugin
 *  org.bukkit.scoreboard.Criteria
 *  org.bukkit.scoreboard.DisplaySlot
 *  org.bukkit.scoreboard.Objective
 *  org.bukkit.scoreboard.Scoreboard
 *  org.bukkit.scoreboard.ScoreboardManager
 *  org.bukkit.util.io.BukkitObjectInputStream
 *  org.bukkit.util.io.BukkitObjectOutputStream
 */
package kot0328.minecraftBank;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.invoke.CallSite;
import java.lang.invoke.LambdaMetafactory;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kot0328.minecraftBank.MinecraftBankAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.title.Title;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.Registry;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.TileState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WanderingTrader;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

public final class MinecraftBank
extends JavaPlugin
implements CommandExecutor,
TabCompleter,
Listener,
MinecraftBankAPI {
    private static final String PERM_USE = "bank.use";
    private static final String PERM_ADMIN = "bank.admin";
    private static Economy econ = null;
    private NamespacedKey lenderKey;
    private NamespacedKey loanSlotKey;
    private NamespacedKey auctionIdKey;
    private NamespacedKey hubItemKey;
    private NamespacedKey adminTargetKey;
    private NamespacedKey questIdKey;
    private NamespacedKey worldStockKey;
    private NamespacedKey resourceMaterialKey;
    private NamespacedKey resourceActionKey;
    private NamespacedKey worldStockActionKey;
    private NamespacedKey merchantDealKey;
    private NamespacedKey merchantNpcMarkerKey;
    private NamespacedKey treasureChestMarkerKey;
    private NamespacedKey tradeTargetKey;
    private NamespacedKey groupAccountKey;
    private NamespacedKey groupTargetKey;
    private NamespacedKey investmentFundKey;
    private final HashMap<UUID, UUID> adminViewingTarget = new HashMap();
    private final HashSet<UUID> hubItemIssued = new HashSet();
    private final HashMap<UUID, Long> hubItemReissueCooldown = new HashMap();
    private long cfgHubItemReissueCooldownMs = 300000L;
    private final HashMap<UUID, Double> personalBank = new HashMap();
    private final HashSet<UUID> bankers = new HashSet();
    private final HashMap<UUID, Double> bankCapital = new HashMap();
    private final HashMap<UUID, String> publishedLoans = new HashMap();
    private final HashMap<UUID, String> activeDebts = new HashMap();
    private final HashMap<UUID, Double> fixedDeposit = new HashMap();
    private final HashMap<UUID, Long> fixedDepositUnlockTime = new HashMap();
    private final HashMap<UUID, Double> govDebt = new HashMap();
    private final HashMap<UUID, Integer> creditScore = new HashMap();
    private final HashMap<UUID, Double> tempLoanAmount = new HashMap();
    private final HashMap<UUID, Double> tempInterestRate = new HashMap();
    private final HashMap<UUID, Integer> planDesignSlot = new HashMap();
    private final LinkedHashMap<UUID, LinkedList<String>> transactionLogs = new LinkedHashMap();
    private static final int LOG_MAX = 30;
    private final HashMap<UUID, String> awaitingChatInput = new HashMap();
    private final HashMap<UUID, Long> govDebtDueTime = new HashMap();
    private final HashMap<UUID, Double> fixedDeposit2 = new HashMap();
    private final HashMap<UUID, Long> fixedDepositUnlockTime2 = new HashMap();
    private final HashMap<UUID, Double> fixedDeposit3 = new HashMap();
    private final HashMap<UUID, Long> fixedDepositUnlockTime3 = new HashMap();
    private final HashMap<UUID, String> publishedLoans2 = new HashMap();
    private final HashMap<UUID, String> publishedLoans3 = new HashMap();
    private final HashSet<UUID> newsBroadcastOff = new HashSet();
    private double cfgBankEstablishCost = 10000.0;
    private double cfgDepositStep = 1000.0;
    private double cfgFixedDepositAmount = 10000.0;
    private double cfgFixedDepositRate = 0.2;
    private long cfgFixedDepositDurationMs = 60000L;
    private double cfgGovLoanAmount = 10000.0;
    private long cfgGovLoanDurationMs = 1200000L;
    private double cfgGovLoanPenaltyCapMultiplier = 2.0;
    private boolean cfgBroadcastNewsDefault = true;
    private int cfgAutosaveIntervalMinutes = 5;
    private int cfgBackupIntervalMinutes = 180;
    private int cfgBackupRetentionCount = 14;
    private int cfgSelfCheckReportIntervalMinutes = 360;
    private List<String> lastSelfCheckIssues = new ArrayList<String>();
    private long lastSelfCheckAt = 0L;
    private final LinkedHashMap<UUID, Quest> quests = new LinkedHashMap();
    private final HashMap<UUID, UUID> playerActiveQuest = new HashMap();
    private double cfgQuestRadius = 15.0;
    private long cfgQuestCooldownMs = 3600000L;
    private int cfgQuestMaxPerPlayer = 3;
    private int cfgQuestTeamMaxSize = 6;
    private static final UUID SYSTEM_QUEST_POSTER_ID = new UUID(0L, 0L);
    private int cfgQuestSystemMinAvailable = 3;
    private int cfgQuestSystemGenerateCount = 1;
    private double cfgQuestSystemRewardMin = 500.0;
    private double cfgQuestSystemRewardMax = 3000.0;
    private double cfgQuestSystemSpawnRadius = 500.0;
    private int cfgQuestSystemCheckIntervalMinutes = 10;
    private boolean cfgWebDashboardEnabled = false;
    private int cfgWebDashboardPort = 8765;
    private long cfgWebDashboardExportIntervalMinutes = 5L;
    private String cfgWebDashboardPublicUrl = "";
    private final HashMap<UUID, String> dashboardTokens = new HashMap();
    private final HashMap<UUID, String> webPasswordHash = new HashMap();
    private final HashMap<UUID, String> webAdminPasswordHash = new HashMap();
    private HttpServer webDashboardServer;
    private final HashMap<UUID, ItemStack> collateralItem = new HashMap();
    private final HashMap<UUID, Double> collateralLoanAmount = new HashMap();
    private final HashMap<UUID, Long> collateralDueTime = new HashMap();
    private final HashMap<UUID, UUID> collateralLender = new HashMap();
    private double cfgCollateralLtv = 0.5;
    private double cfgCollateralInterest = 15.0;
    private long cfgCollateralDurationMs = 600000L;
    private final HashMap<UUID, UUID> auctionSeller = new HashMap();
    private final HashMap<UUID, ItemStack> auctionItem = new HashMap();
    private final HashMap<UUID, Double> auctionBid = new HashMap();
    private final HashMap<UUID, UUID> auctionBidder = new HashMap();
    private final HashMap<UUID, Long> auctionEndTime = new HashMap();
    private final HashMap<UUID, List<ItemStack>> auctionPendingItems = new HashMap();
    private final HashMap<UUID, ItemStack> auctionListingDraft = new HashMap();
    private final HashMap<UUID, Double> auctionBuyoutPrice = new HashMap();
    private final HashMap<UUID, Double> auctionListingStartPrice = new HashMap();
    private final HashMap<UUID, List<String>> auctionOfflineNotices = new HashMap();
    private final HashMap<UUID, Double> auctionTotalSoldAmount = new HashMap();
    private final HashMap<UUID, List<String>> loanOfflineNotices = new HashMap();
    private final HashMap<UUID, Integer> auctionSortMode = new HashMap();
    private final HashMap<UUID, String> auctionSearchQuery = new HashMap();
    private final HashSet<UUID> auctionMyListingsOnlyFilter = new HashSet();
    private final HashMap<UUID, Integer> auctionPage = new HashMap();
    private final HashMap<UUID, Integer> configEditorPage = new HashMap();
    private NamespacedKey configKeyTag;
    private double cfgAuctionMinIncrement = 100.0;
    private double cfgAuctionFeeRate = 0.05;
    private long cfgAuctionDurationMs = 1200000L;
    private int cfgAuctionMaxListingsPerPlayer = 9;
    private final HashMap<UUID, Long> insuranceExpiry = new HashMap();
    private final HashMap<UUID, Long> insuranceLastClaim = new HashMap();
    private double cfgInsurancePremium = 3000.0;
    private double cfgInsurancePayout = 5000.0;
    private long cfgInsuranceDurationMs = 1800000L;
    private long cfgInsuranceClaimCooldownMs = 300000L;
    private final HashMap<UUID, HashSet<String>> unlockedAchievements = new HashMap();
    private static final String[] ACHIEVEMENT_IDS = new String[]{"first_loan_repaid", "first_gov_loan_repaid", "millionaire", "first_fixed_deposit", "credit_master"};
    private String cfgDiscordBotToken = "";
    private String cfgDiscordChannelId = "";
    private double treasury = 0.0;
    private String activeEconomyEvent = "\u901a\u5e38";
    private long activeEconomyEventUntil = 0L;
    private final Random economyRandom = new Random();
    private double cfgRandomEventChance = 0.15;
    private int cfgRandomEventIntervalMinutes = 30;
    private boolean cfgCitizenDividendEnabled = true;
    private double cfgCitizenDividendAmount = 500.0;
    private double cfgCitizenDividendMinTreasury = 100000.0;
    private int cfgCitizenDividendIntervalMinutes = 60;
    private boolean cfgWelfareEnabled = true;
    private double cfgWelfareThreshold = 1000.0;
    private double cfgWelfareAmount = 800.0;
    private int cfgWelfareIntervalMinutes = 30;
    private int cfgWelfareMaxPerDay = 4;
    private final HashMap<UUID, Integer> welfareCountToday = new HashMap();
    private final HashMap<UUID, Long> welfareCountResetAt = new HashMap();
    private final HashSet<UUID> tutorialSeen = new HashSet();
    private final HashMap<UUID, String> pendingConfirmation = new HashMap();
    private final HashMap<UUID, Long> pendingConfirmationTime = new HashMap();
    private static final long CONFIRMATION_TIMEOUT_MS = 30000L;
    private final HashMap<UUID, Long> lastGuiClickTime = new HashMap();
    private static final long GUI_CLICK_DEBOUNCE_MS = 250L;
    private final HashMap<UUID, HashMap<String, Integer>> playerWorldStocks = new HashMap();
    private final HashMap<UUID, HashMap<String, Double>> playerWorldStockAvgCost = new HashMap();
    private final HashMap<String, WorldStockQuote> worldStockQuoteCache = new HashMap();
    private double cfgWorldStockCacheSeconds = 60.0;
    private double cfgWorldStockFeeRate = 0.005;
    private double cfgWorldStockDividendAnnualRate = 0.02;
    private int cfgWorldStockDividendIntervalHours = 24;
    private final HashMap<String, Double> fxRateCache = new HashMap();
    private final HashMap<String, Long> fxRateCacheTime = new HashMap();
    private List<String> cfgFxCurrencies = new ArrayList<String>(List.of("USD", "GBP", "EUR", "HKD", "AUD", "CAD"));
    private int cfgFxRefreshMinutes = 10;
    private final HashMap<String, Double> cfgFxFallbackRates = new HashMap();
    private double cfgFxFallbackRateDefault = 150.0;
    private final HashMap<UUID, Integer> worldStockTradesToday = new HashMap();
    private final HashMap<UUID, Double> worldStockAmountToday = new HashMap();
    private final HashMap<UUID, Double> worldStockProfitToday = new HashMap();
    private final HashMap<UUID, Long> worldStockDailyResetAt = new HashMap();
    private final HashMap<UUID, HashMap<String, Long>> worldStockLastTradeTime = new HashMap();
    private int cfgWorldStockMaxTradesPerDay = 20;
    private double cfgWorldStockMaxAmountPerDay = 500000.0;
    private double cfgWorldStockMaxProfitPerDay = 100000.0;
    private int cfgWorldStockTradeCooldownSeconds = 30;
    private int cfgWorldStockMaxBulkQty = 1000;
    private String cfgCurrencyUnit = "\u5186";
    private boolean cfgCustomMoneyEnabled = false;
    private String cfgCustomMoneySymbol = "V";
    private double cfgCustomMoneyRate = 10.0;
    private static final Map<String, String> COUNTRY_CURRENCY_MAP = new HashMap<String, String>();
    private final HashMap<UUID, List<WorldStockAlert>> worldStockAlerts = new HashMap();
    private static final LinkedHashMap<Material, Double> RESOURCE_BASE_PRICES;
    private static final Map<String, List<Material>> RESOURCE_CATEGORIES;
    private final HashMap<Material, Double> resourcePrices = new HashMap();
    private final HashMap<UUID, String> resourceShopViewCategory = new HashMap();
    private final HashMap<UUID, HashMap<Material, Double>> resourcePersonalBuyMultiplier = new HashMap();
    private double cfgResourcePersonalImpactRate = 0.02;
    private double cfgResourcePersonalCeilingPercent = 3.0;
    private double cfgResourcePriceImpactRate = 0.004;
    private double cfgResourcePriceFloorPercent = 0.2;
    private double cfgResourcePriceCeilingPercent = 3.0;
    private double cfgResourcePriceReversionRate = 0.05;
    private int cfgResourcePriceDriftIntervalMinutes = 10;
    private static final LinkedHashMap<Material, Double> MERCHANT_ITEM_POOL;
    private final List<MerchantDeal> merchantDeals = new ArrayList<MerchantDeal>();
    private long merchantRefreshAt = 0L;
    private int cfgMerchantRefreshHours = 24;
    private int cfgMerchantDealCount = 4;
    private double cfgMerchantMinDiscountPercent = 20.0;
    private double cfgMerchantMaxDiscountPercent = 50.0;
    private int cfgMerchantMinStock = 3;
    private int cfgMerchantMaxStock = 10;
    private double cfgMerchantSpawnRadius = 300.0;
    private int cfgVipSilverScore = 500;
    private int cfgVipGoldScore = 650;
    private int cfgVipPlatinumScore = 750;
    private double cfgVipFeeDiscountSilver = 0.1;
    private double cfgVipFeeDiscountGold = 0.25;
    private double cfgVipFeeDiscountPlatinum = 0.5;
    private boolean cfgVipStipendEnabled = true;
    private double cfgVipStipendSilver = 1000.0;
    private double cfgVipStipendGold = 2500.0;
    private double cfgVipStipendPlatinum = 5000.0;
    private final HashMap<UUID, Long> vipStipendClaimedAt = new HashMap();
    private double cfgVipTradeLimitMultSilver = 1.5;
    private double cfgVipTradeLimitMultGold = 2.0;
    private double cfgVipTradeLimitMultPlatinum = 3.0;
    private static final LinkedHashMap<Material, Double> VIP_SHOP_POOL;
    private final List<VipDeal> vipShopDeals = new ArrayList<VipDeal>();
    private long vipShopRefreshAt = 0L;
    private boolean cfgVipShopEnabled = true;
    private int cfgVipShopRefreshHours = 24;
    private int cfgVipShopDealCount = 4;
    private double cfgVipShopMinDiscountPercent = 25.0;
    private double cfgVipShopMaxDiscountPercent = 55.0;
    private int cfgVipShopMinStock = 2;
    private int cfgVipShopMaxStock = 6;
    private NamespacedKey vipDealKey;
    private final HashMap<UUID, List<InstallmentPlan>> installmentPlans = new HashMap();
    private boolean cfgInstallmentEnabled = true;
    private int cfgInstallmentMinCreditScore = 400;
    private int cfgInstallmentCount = 3;
    private int cfgInstallmentIntervalHours = 24;
    private double cfgInstallmentFeeRate = 0.08;
    private double cfgInstallmentCreditLimitMultiplier = 50.0;
    private int cfgInstallmentMissedPaymentPenalty = 15;
    private UUID merchantEntityId;
    private String merchantWorldName;
    private double merchantX;
    private double merchantY;
    private double merchantZ;
    private int cfgTreasureIntervalHours = 8;
    private double cfgTreasureRewardMin = 3000.0;
    private double cfgTreasureRewardMax = 15000.0;
    private double cfgTreasureSpawnRadius = 500.0;
    private boolean treasureActive = false;
    private String treasureWorldName;
    private int treasureX;
    private int treasureY;
    private int treasureZ;
    private double treasureReward = 0.0;
    private long treasureNextSpawnAt = 0L;
    private final HashMap<UUID, ItemStack> collateralSelection = new HashMap();
    private double weeklyTradeVolume = 0.0;
    private long cfgReportIntervalDays = 7L;
    private final HashMap<UUID, Integer> donationScoreToday = new HashMap();
    private final HashMap<UUID, Long> donationScoreResetAt = new HashMap();
    private int cfgDonationScoreCapPerDay = 10;
    private final HashMap<UUID, UUID> loanGuarantor = new HashMap();
    private final HashMap<UUID, UUID> guarantorProposals = new HashMap();
    private final HashMap<UUID, Long> guarantorProposalTime = new HashMap();
    private long cfgGuarantorProposalTimeoutMs = 300000L;
    private final HashMap<UUID, TradeSession> activeTradeSessions = new HashMap();
    private final HashMap<UUID, UUID> tradeRequests = new HashMap();
    private final HashMap<UUID, Long> tradeRequestTime = new HashMap();
    private long cfgTradeRequestTimeoutMs = 60000L;
    private final HashMap<UUID, GroupAccount> groupAccounts = new HashMap();
    private final HashMap<UUID, HashSet<UUID>> playerGroupAccounts = new HashMap();
    private final HashMap<UUID, UUID> groupAccountViewing = new HashMap();
    private double cfgGroupAccountCreateCost = 1000.0;
    private int cfgGroupAccountMaxMembers = 10;
    private final HashMap<UUID, InvestmentFund> investmentFunds = new HashMap();
    private final HashMap<UUID, HashSet<UUID>> playerInvestmentFunds = new HashMap();
    private final HashMap<UUID, UUID> fundViewing = new HashMap();
    private double cfgFundCreateCost = 2000.0;
    private int cfgFundMaxContributors = 10;
    private final HashMap<UUID, Integer> lotteryTickets = new HashMap();
    private double lotteryPool = 0.0;
    private long lotteryDrawAt = 0L;
    private String lastLotteryWinnerName = "-";
    private double lastLotteryWinnerAmount = 0.0;
    private double cfgLotteryTicketPrice = 500.0;
    private int cfgLotteryDrawIntervalHours = 24;
    private double cfgLotteryPayoutRate = 0.8;
    private boolean cfgLotteryJackpotBoostEnabled = true;
    private double cfgLotteryJackpotBoostThreshold = 10000.0;
    private double cfgLotteryJackpotBoostAmount = 5000.0;
    private final HashMap<UUID, Inventory> storageInventories = new HashMap();
    private final HashMap<UUID, Long> storageRentDueTime = new HashMap();
    private final HashMap<UUID, Map<Integer, ItemStack>> pendingStorageContents = new HashMap();
    private int cfgStorageSize = 27;
    private double cfgStorageRentAmount = 2000.0;
    private int cfgStorageRentIntervalHours = 72;
    private final Component tMain = this.mm("<bold><gold>\u3010\u7dcf\u5408\u91d1\u878d\u30bb\u30f3\u30bf\u30fc\u30c8\u30c3\u30d7\u3011</gold></bold>");
    private final Component tPersonal = this.mm("<bold><aqua>\u3010\u500b\u4eba\u53e3\u5ea7\u30fb\u9810\u91d1\u7a93\u53e3\u3011</aqua></bold>");
    private final Component tMarket = this.mm("<bold><green>\u3010\u878d\u8cc7\u5e02\u5834\uff08\u30ed\u30fc\u30f3\u30de\u30fc\u30b1\u30c3\u30c8\uff09\u3011</green></bold>");
    private final Component tRepay = this.mm("<bold><yellow>\u3010\u7dcf\u5408\u30fb\u501f\u91d1\u8fd4\u6e08\u7a93\u53e3\u3011</yellow></bold>");
    private final Component tEstablish = this.mm("<bold><dark_purple>\u3010\u9280\u884c\u8a2d\u7acb\u30aa\u30d5\u30a3\u30b9\u3011</dark_purple></bold>");
    private final Component tBanker = this.mm("<bold><red>\u3010\u982d\u53d6\u30b3\u30f3\u30c8\u30ed\u30fc\u30eb\u30d1\u30cd\u30eb\u3011</red></bold>");
    private final Component tPlan = this.mm("<bold><dark_red>\u3010\u878d\u8cc7\u30d7\u30e9\u30f3\u8a2d\u8a08\u5ba4\u3011</dark_red></bold>");
    private final Component tGovLoan = this.mm("<bold><aqua>\u3010\u56fd\u55b6\u516c\u5eab\u30fb\u30b5\u30fc\u30d0\u30fc\u30ed\u30fc\u30f3\u3011</aqua></bold>");
    private final Component tFixedDepo = this.mm("<bold><gold>\u3010\u9ad8\u91d1\u5229\u30fb\u5b9a\u671f\u9810\u91d1\u7a93\u53e3\u3011</gold></bold>");
    private final Component tCredit = this.mm("<bold><green>\u3010\u500b\u4eba\u4fe1\u7528\u60c5\u5831\u30bb\u30f3\u30bf\u30fc\u3011</green></bold>");
    private final Component tQuestBoard = this.mm("<bold><dark_aqua>\u3010\u4f9d\u983c\u30dc\u30fc\u30c9\u30fb\u63a2\u7d22\u4f9d\u983c\u3011</dark_aqua></bold>");
    private final Component tWorldStock = this.mm("<bold><blue>\u3010\u4e16\u754c\u682a\u5f0f\u5e02\u5834\u3011</blue></bold>");
    private final Component tWorldStockDetail = this.mm("<bold><blue>\u3010\u4e16\u754c\u682a\u5f0f\u5e02\u5834\u30fb\u9298\u67c4\u8a73\u7d30\u3011</blue></bold>");
    private final Component tWorldStockLeaderboard = this.mm("<bold><blue>\u3010\u4e16\u754c\u682a\u5f0f\u5e02\u5834\u30fb\u4fdd\u6709\u682a\u30e9\u30f3\u30ad\u30f3\u30b0\u3011</blue></bold>");
    private final Component tResourceShop = this.mm("<bold><green>\u3010\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u3011</green></bold>");
    private final Component tResourceShopList = this.mm("<bold><green>\u3010\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u30fb\u54c1\u76ee\u4e00\u89a7\u3011</green></bold>");
    private final Component tResourceShopDetail = this.mm("<bold><green>\u3010\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u30fb\u58f2\u8cb7\u3011</green></bold>");
    private final Component tTravelingMerchant = this.mm("<bold><gold>\u3010\u5de1\u56de\u5546\u4eba\u3011</gold></bold>");
    private final Component tLeaderboard = this.mm("<bold><yellow>\u3010\u8cc7\u7523\u30e9\u30f3\u30ad\u30f3\u30b0\u3011</yellow></bold>");
    private final Component tCollateral = this.mm("<bold><dark_purple>\u3010\u8cea\u5c4b\u30fb\u62c5\u4fdd\u4ed8\u304d\u878d\u8cc7\u3011</dark_purple></bold>");
    private final Component tInsurance = this.mm("<bold><blue>\u3010\u751f\u547d\u4fdd\u967a\u7a93\u53e3\u3011</blue></bold>");
    private final Component tAchievement = this.mm("<bold><gold>\u3010\u5b9f\u7e3e\u30fb\u79f0\u53f7\u30b3\u30ec\u30af\u30b7\u30e7\u30f3\u3011</gold></bold>");
    private final Component tAuction = this.mm("<bold><green>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u30cf\u30a6\u30b9\u3011</green></bold>");
    private final Component tHub = this.mm("<bold><white>\u3010\u7d4c\u6e08\u7dcf\u5408\u30e1\u30cb\u30e5\u30fc\u3011</white></bold>");
    private final Component tBankHub = this.mm("<bold><aqua>\u3010\u9280\u884c\u7a93\u53e3\u3011</aqua></bold>");
    private final Component tMarketHub = this.mm("<bold><green>\u3010\u30de\u30fc\u30b1\u30c3\u30c8\u3011</green></bold>");
    private final Component tTodoHub = this.mm("<bold><yellow>\u3010\u3084\u308b\u3053\u3068\u3011</yellow></bold>");
    private final Component tMyPage = this.mm("<bold><light_purple>\u3010\u30de\u30a4\u30da\u30fc\u30b8\u3011</light_purple></bold>");
    private final Component tInstallmentList = this.mm("<bold><light_purple>\u3010\u5206\u5272\u6255\u3044\u306e\u72b6\u6cc1\u3011</light_purple></bold>");
    private final Component tTutorial = this.mm("<bold><gold>\u3010\u7d4c\u6e08\u521d\u5fc3\u8005\u30ac\u30a4\u30c9\u3011</gold></bold>");
    private final Component tAuctionSelect = this.mm("<bold><green>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u51fa\u54c1\u30a2\u30a4\u30c6\u30e0\u9078\u629e\u3011</green></bold>");
    private final Component tAuctionCancelConfirm = this.mm("<bold><red>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u51fa\u54c1\u53d6\u6d88\u306e\u78ba\u8a8d\u3011</red></bold>");
    private final Component tAuctionRanking = this.mm("<bold><green>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u30fb\u843d\u672d\u58f2\u4e0a\u30e9\u30f3\u30ad\u30f3\u30b0\u3011</green></bold>");
    private final Component tCollateralSelect = this.mm("<bold><dark_purple>\u3010\u62c5\u4fdd\u30a2\u30a4\u30c6\u30e0\u9078\u629e\u3011</dark_purple></bold>");
    private final Component tAdminMain = this.mm("<bold><red>\u3010\u7ba1\u7406\u8005\u30d1\u30cd\u30eb\u3011</red></bold>");
    private final Component tAdminPlayerList = this.mm("<bold><red>\u3010\u7ba1\u7406\u8005\uff1a\u30d7\u30ec\u30a4\u30e4\u30fc\u4e00\u89a7\u3011</red></bold>");
    private final Component tAdminPlayerDetail = this.mm("<bold><red>\u3010\u7ba1\u7406\u8005\uff1a\u30d7\u30ec\u30a4\u30e4\u30fc\u8a73\u7d30\u3011</red></bold>");
    private final Component tAdminServer = this.mm("<bold><red>\u3010\u7ba1\u7406\u8005\uff1a\u30b5\u30fc\u30d0\u30fc\u7ba1\u7406\u3011</red></bold>");
    private final Component tConfigEditor = this.mm("<bold><red>\u3010\u7ba1\u7406\u8005\uff1aconfig\u8a2d\u5b9a\u3011</red></bold>");
    private final Component tLottery = this.mm("<bold><gold>\u3010\u5b9a\u671f\u62bd\u9078\u30fb\u5b9d\u304f\u3058\u3011</gold></bold>");
    private final Component tStorageRent = this.mm("<bold><gold>\u3010\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u30fb\u5951\u7d04\u7a93\u53e3\u3011</gold></bold>");
    private final Component tStorageLocker = this.mm("<bold><gray>\u3010\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u3011</gray></bold>");
    private final Component tTradeSelect = this.mm("<bold><green>\u3010\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u30fb\u76f8\u624b\u9078\u629e\u3011</green></bold>");
    private final Component tTrade = this.mm("<bold><gold>\u3010\u5b89\u5168\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u3011</gold></bold>");
    private final Component tGroupList = this.mm("<bold><gold>\u3010\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u3011</gold></bold>");
    private final Component tGroupAccount = this.mm("<bold><gold>\u3010\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u30fb\u8a73\u7d30\u3011</gold></bold>");
    private final Component tGroupMembers = this.mm("<bold><gold>\u3010\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u30fb\u30e1\u30f3\u30d0\u30fc\u7ba1\u7406\u3011</gold></bold>");
    private final Component tFundList = this.mm("<bold><dark_green>\u3010\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u3011</dark_green></bold>");
    private final Component tFundInfo = this.mm("<bold><dark_green>\u3010\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u30fb\u60c5\u5831\u3011</dark_green></bold>");
    private final Component tFundStock = this.mm("<bold><dark_green>\u3010\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u30fb\u904b\u7528\u3011</dark_green></bold>");
    private final Component tFundStockDetail = this.mm("<bold><dark_green>\u3010\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u30fb\u9298\u67c4\u8a73\u7d30\u3011</dark_green></bold>");
    private final Component tVipLounge = this.mm("<bold><light_purple>\u3010\ud83d\udc8e VIP\u30e9\u30a6\u30f3\u30b8\u3011</light_purple></bold>");
    private PluginDataStore db;
    private FileConfiguration messages;
    private File messagesFile;
    private static final Set<String> MANAGED_DATA_PREFIXES;
    private volatile String lastDashboardJson = "{}";
    private static final int DASHBOARD_HISTORY_LIMIT = 60;
    private final ArrayDeque<Long> dashboardHistoryTimestamps = new ArrayDeque();
    private final ArrayDeque<Long> dashboardHistoryTreasury = new ArrayDeque();
    private static final String MY_DASHBOARD_HTML = "<!doctype html>\n<html lang=\"ja\">\n<head>\n<meta charset=\"UTF-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n<title>\u500b\u4eba\u30da\u30fc\u30b8 | MinecraftBank</title>\n<style>\n  :root { --bg:#0b0f19; --panel:#141b2c; --panel-border:#232c42; --text:#e7ecf7; --text-dim:#8a93ab; --accent:#5b8cff; --gold:#f4c542; --emerald:#34d399; --red:#f87171; }\n  * { box-sizing: border-box; }\n  body { margin:0; background:var(--bg); color:var(--text); font-family: 'Segoe UI', system-ui, sans-serif; }\n  .wrap { max-width: 720px; margin: 0 auto; padding: 20px 16px 40px; }\n  h1 { font-size: 20px; margin: 0 0 4px; }\n  .sub { color: var(--text-dim); font-size: 13px; margin-bottom: 18px; }\n  .grid { display:grid; grid-template-columns: repeat(auto-fit, minmax(140px,1fr)); gap:10px; margin-bottom:18px; }\n  .card { background:var(--panel); border:1px solid var(--panel-border); border-radius:12px; padding:14px; }\n  .card .label { color:var(--text-dim); font-size:12px; margin-bottom:6px; }\n  .card .value { font-size:20px; font-weight:700; }\n  .gold .value { color: var(--gold); }\n  .emerald .value { color: var(--emerald); }\n  .panel { background:var(--panel); border:1px solid var(--panel-border); border-radius:12px; padding:16px; margin-bottom:16px; }\n  .panel h2 { font-size:14px; margin:0 0 10px; color:var(--text-dim); font-weight:600; }\n  table { width:100%; border-collapse:collapse; font-size:13px; }\n  th, td { text-align:left; padding:6px 4px; border-bottom:1px solid var(--panel-border); }\n  th { color:var(--text-dim); font-weight:600; }\n  .log-line { font-size:12px; padding:5px 0; border-bottom:1px solid var(--panel-border); color: var(--text); }\n  .empty { color:var(--text-dim); font-size:13px; padding:10px 0; }\n  .err { color: var(--red); }\n  .btn { background:var(--accent); color:#fff; border:none; border-radius:8px; padding:8px 14px; font-size:13px; cursor:pointer; font-weight:600; }\n  .btn:hover { opacity:.9; }\n  .btn:disabled { opacity:.4; cursor:default; }\n  .btn.gold { background: var(--gold); color:#1a1300; }\n  .input { background:#0f1524; border:1px solid var(--panel-border); color:var(--text); border-radius:7px; padding:7px 10px; font-size:13px; width:100px; }\n  .row { display:flex; align-items:center; gap:8px; flex-wrap:wrap; margin-top:8px; }\n  .status-msg { font-size:12px; margin-top:8px; min-height:16px; }\n  .status-msg.ok { color: var(--emerald); }\n  .status-msg.err { color: var(--red); }\n  .auction-card { border-bottom:1px solid var(--panel-border); padding:10px 0; font-size:13px; }\n  .auction-card:last-child { border-bottom:none; }\n  .auction-card .title { font-weight:600; margin-bottom:4px; }\n  .auction-card .meta { color:var(--text-dim); font-size:12px; margin-bottom:6px; }\n</style>\n</head>\n<body>\n  <div class=\"wrap\">\n    <h1>\ud83d\udc64 \u500b\u4eba\u30da\u30fc\u30b8</h1>\n    <div class=\"sub\" id=\"playerName\">\u8aad\u8fbc\u4e2d...</div>\n    <div class=\"sub\" id=\"onlineStatus\"></div>\n\n    <div class=\"panel\" id=\"passwordPanel\" hidden>\n      <h2>\u30aa\u30d5\u30e9\u30a4\u30f3\u64cd\u4f5c\u7528\u30d1\u30b9\u30ef\u30fc\u30c9</h2>\n      <div class=\"empty\">\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u3044\u306a\u3044\u6642\u306b\u3053\u306e\u30da\u30fc\u30b8\u304b\u3089\u64cd\u4f5c\u3059\u308b\u306b\u306f\u3001\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044(\u30b2\u30fc\u30e0\u5185\u306e /meco webpage password \u3067\u8a2d\u5b9a\u3067\u304d\u307e\u3059)\u3002\u30ed\u30b0\u30a4\u30f3\u4e2d\u306f\u4e0d\u8981\u3067\u3059\u3002</div>\n      <div class=\"row\"><input class=\"input\" id=\"offlinePassword\" type=\"password\" placeholder=\"\u30d1\u30b9\u30ef\u30fc\u30c9\" style=\"width:160px;\"></div>\n    </div>\n\n    <div class=\"grid\">\n      <div class=\"card gold\"><div class=\"label\">\u6240\u6301\u91d1</div><div class=\"value\" id=\"pocket\">-</div></div>\n      <div class=\"card gold\"><div class=\"label\">\u9280\u884c\u6b8b\u9ad8</div><div class=\"value\" id=\"bank\">-</div></div>\n      <div class=\"card emerald\"><div class=\"label\">\u4fe1\u7528\u30b9\u30b3\u30a2</div><div class=\"value\" id=\"credit\">-</div></div>\n      <div class=\"card\"><div class=\"label\">\u5b9f\u7e3e\u89e3\u9664\u6570</div><div class=\"value\" id=\"achievements\">-</div></div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"row\" style=\"justify-content:space-between; margin-top:0;\">\n        <h2 style=\"margin:0;\">\u53d7\u53d6\u7bb1</h2>\n        <button class=\"btn\" id=\"collectBtn\">\ud83d\udce6 \u53d7\u53d6\u7bb1\u3092\u78ba\u8a8d\u3059\u308b</button>\n      </div>\n      <div class=\"status-msg\" id=\"collectMsg\"></div>\n      <div class=\"empty\">\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u843d\u672d\u54c1\u306a\u3069\u304c\u5c4a\u3044\u3066\u3044\u307e\u3059\u3002\u30ed\u30b0\u30a4\u30f3\u4e2d\u306b\u30a4\u30f3\u30d9\u30f3\u30c8\u30ea\u306e\u7a7a\u304d\u304c\u7121\u304f\u3066\u53d7\u3051\u53d6\u308c\u306a\u304b\u3063\u305f\u5206\u3082\u3053\u3053\u3067\u518d\u53d6\u5f97\u3067\u304d\u307e\u3059\u3002</div>\n    </div>\n\n    <div class=\"panel\">\n      <h2>\u4fdd\u6709\u4e2d\u306e\u4e16\u754c\u682a / \u8cfc\u5165</h2>\n      <div id=\"stocksArea\"><div class=\"empty\">\u8aad\u8fbc\u4e2d...</div></div>\n      <div class=\"row\">\n        <input class=\"input\" id=\"stockSymbol\" type=\"text\" placeholder=\"\u9298\u67c4(\u4f8b: AAPL)\" style=\"width:140px;\">\n        <input class=\"input\" id=\"stockQty\" type=\"number\" min=\"1\" value=\"1\" placeholder=\"\u682a\u6570\">\n        <button class=\"btn\" id=\"stockBuyBtn\">\u8cfc\u5165\u3059\u308b</button>\n      </div>\n      <div class=\"status-msg\" id=\"stockMsg\"></div>\n    </div>\n\n    <div class=\"panel\">\n      <h2>\u30aa\u30fc\u30af\u30b7\u30e7\u30f3</h2>\n      <div id=\"auctionArea\"><div class=\"empty\">\u8aad\u8fbc\u4e2d...</div></div>\n      <div class=\"status-msg\" id=\"auctionMsg\"></div>\n    </div>\n\n    <div class=\"panel\">\n      <h2>\u30ec\u30f3\u30bf\u30eb\u5009\u5eab</h2>\n      <div id=\"storageArea\"><div class=\"empty\">\u8aad\u8fbc\u4e2d...</div></div>\n      <div class=\"status-msg\" id=\"storageMsg\"></div>\n    </div>\n\n    <div class=\"panel\">\n      <h2>\u76f4\u8fd1\u306e\u53d6\u5f15\u5c65\u6b74</h2>\n      <div id=\"logsArea\"><div class=\"empty\">\u8aad\u8fbc\u4e2d...</div></div>\n    </div>\n  </div>\n\n  <script>\n    function fmtYen(n) { return Math.round(n).toLocaleString('ja-JP') + '\u5186'; }\n    const params = new URLSearchParams(location.search);\n    const token = params.get('token') || '';\n\n    async function load() {\n      try {\n        const res = await fetch('/me.json?token=' + encodeURIComponent(token), { cache: 'no-store' });\n        const data = await res.json();\n        if (data.error) {\n          document.getElementById('playerName').innerHTML = '<span class=\"err\">\u30ea\u30f3\u30af\u304c\u7121\u52b9\u3067\u3059\u3002\u30b2\u30fc\u30e0\u5185\u3067 /meco webpage \u3092\u5b9f\u884c\u3057\u76f4\u3057\u3066\u304f\u3060\u3055\u3044\u3002</span>';\n          return;\n        }\n        document.getElementById('playerName').textContent = data.name + ' \u3055\u3093\u306e\u500b\u4eba\u30da\u30fc\u30b8';\n        document.getElementById('onlineStatus').textContent = data.online ? '\ud83d\udfe2 \u30ed\u30b0\u30a4\u30f3\u4e2d' : '\u26aa \u30aa\u30d5\u30e9\u30a4\u30f3';\n        // \u2605\u4fee\u6b63: \u30d1\u30b9\u30ef\u30fc\u30c9\u672a\u8a2d\u5b9a\u3067\u3082\u5165\u529b\u6b04\u81ea\u4f53\u306f\u5fc5\u305a\u8868\u793a\u3059\u308b(\u7ba1\u7406\u8005\u304c\u767a\u884c\u3057\u305f\u30d1\u30b9\u30ef\u30fc\u30c9\u3067\n        // \u64cd\u4f5c\u3067\u304d\u308b\u53ef\u80fd\u6027\u304c\u3042\u308b\u305f\u3081\u3001\u300c\u672a\u8a2d\u5b9a\u300d\u3092\u7406\u7531\u306b\u5165\u529b\u6b04\u3054\u3068\u6d88\u3057\u3066\u306f\u3044\u3051\u306a\u3044)\u3002\n        document.getElementById('passwordPanel').hidden = data.online;\n        document.getElementById('pocket').textContent = fmtYen(data.pocket || 0);\n        document.getElementById('bank').textContent = fmtYen(data.bank || 0);\n        document.getElementById('credit').textContent = data.credit_score;\n        document.getElementById('achievements').textContent = data.achievement_count;\n\n        const stocks = data.world_stocks || [];\n        const stocksArea = document.getElementById('stocksArea');\n        stocksArea.innerHTML = stocks.length === 0 ? '<div class=\"empty\">\u4fdd\u6709\u4e2d\u306e\u682a\u306f\u3042\u308a\u307e\u305b\u3093\u3002</div>' :\n          '<table><thead><tr><th>\u9298\u67c4</th><th>\u682a\u6570</th><th>\u5e73\u5747\u53d6\u5f97\u5358\u4fa1</th><th>\u8a55\u4fa1\u984d</th></tr></thead><tbody>' +\n          stocks.map(s => '<tr><td>' + s.symbol + '</td><td>' + s.qty + '</td><td>' + fmtYen(s.avg_cost) + '</td><td>' + fmtYen(s.value) + '</td></tr>').join('') +\n          '</tbody></table>';\n\n        const logs = data.logs || [];\n        const logsArea = document.getElementById('logsArea');\n        logsArea.innerHTML = logs.length === 0 ? '<div class=\"empty\">\u53d6\u5f15\u5c65\u6b74\u306f\u3042\u308a\u307e\u305b\u3093\u3002</div>' :\n          logs.map(l => '<div class=\"log-line\">' + l.replace(/</g, '&lt;') + '</div>').join('');\n\n        renderAuctions(data.auctions || []);\n        renderStorage(data.storage || {});\n      } catch (e) {\n        document.getElementById('playerName').innerHTML = '<span class=\"err\">\u8aad\u8fbc\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002</span>';\n      }\n    }\n\n    function fmtRemain(ms) {\n      if (ms <= 0) return '\u307e\u3082\u306a\u304f\u7d42\u4e86';\n      const s = Math.floor(ms / 1000);\n      return Math.floor(s / 60) + '\u5206' + (s % 60) + '\u79d2';\n    }\n\n    function renderAuctions(auctions) {\n      const area = document.getElementById('auctionArea');\n      if (auctions.length === 0) { area.innerHTML = '<div class=\"empty\">\u51fa\u54c1\u4e2d\u306e\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u306f\u3042\u308a\u307e\u305b\u3093\u3002</div>'; return; }\n      area.innerHTML = auctions.map(a => {\n        const disabled = a.is_own ? 'disabled' : '';\n        const buyoutBtn = a.buyout !== null ? '<button class=\"btn gold\" ' + disabled + ' onclick=\"bidAuction(\\'' + a.id + '\\', ' + a.buyout + ')\">\u5373\u6c7a (' + fmtYen(a.buyout) + ')</button>' : '';\n        return '<div class=\"auction-card\">' +\n          '<div class=\"title\">' + a.item + (a.is_own ? '(\u81ea\u5206\u306e\u51fa\u54c1)' : '') + '</div>' +\n          '<div class=\"meta\">\u51fa\u54c1\u8005: ' + a.seller + ' / \u73fe\u5728\u4fa1\u683c: ' + fmtYen(a.bid) + ' / \u5165\u672d\u8005: ' + (a.has_bidder ? '\u3042\u308a' : '\u306a\u3057') + ' / \u6b8b\u308a: ' + fmtRemain(a.remain_ms) + '</div>' +\n          '<div class=\"row\" style=\"margin-top:0;\">' +\n            '<input class=\"input\" id=\"bidamt-' + a.id + '\" type=\"number\" placeholder=\"\u5165\u672d\u984d\" ' + disabled + '>' +\n            '<button class=\"btn\" ' + disabled + ' onclick=\"bidAuctionFromInput(\\'' + a.id + '\\')\">\u5165\u672d\u3059\u308b</button>' +\n            buyoutBtn +\n          '</div></div>';\n      }).join('');\n    }\n\n    function renderStorage(storage) {\n      const area = document.getElementById('storageArea');\n      if (!storage.rented) {\n        area.innerHTML = '<div class=\"empty\">\u672a\u5951\u7d04\u3067\u3059\u3002\u5951\u7d04\u3059\u308b\u3068\u5009\u5eab\u304c\u4f7f\u3048\u308b\u3088\u3046\u306b\u306a\u308a\u307e\u3059(\u5bb6\u8cc3 ' + fmtYen(storage.rent_amount) + ' / \u5b9a\u671f\u652f\u6255\u3044)\u3002</div>' +\n          '<div class=\"row\"><button class=\"btn\" onclick=\"payStorageRent()\">\u5951\u7d04\u3059\u308b</button></div>';\n      } else {\n        area.innerHTML = '<div class=\"empty\">\u5951\u7d04\u4e2d\u3002\u6b21\u56de\u5bb6\u8cc3\u652f\u6255\u3044\u671f\u9650: ' + new Date(storage.due_at).toLocaleString('ja-JP') + '</div>' +\n          '<div class=\"row\"><button class=\"btn\" onclick=\"payStorageRent()\">\u5bb6\u8cc3\u3092\u4eca\u6255\u3063\u3066\u5ef6\u9577\u3059\u308b (' + fmtYen(storage.rent_amount) + ')</button></div>';\n      }\n    }\n\n    async function postAction(path, params) {\n      const password = (document.getElementById('offlinePassword') || {}).value || '';\n      const body = new URLSearchParams(Object.assign({ token, password }, params));\n      const res = await fetch(path, { method: 'POST', headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, body });\n      if (!res.ok) {\n        const err = await res.json().catch(() => ({}));\n        return { ok: false, message: err.message || ('HTTP\u30a8\u30e9\u30fc ' + res.status) };\n      }\n      return await res.json();\n    }\n\n    function showStatus(id, ok, text) {\n      const el = document.getElementById(id);\n      el.className = 'status-msg ' + (ok ? 'ok' : 'err');\n      el.textContent = text;\n    }\n\n    document.getElementById('collectBtn').addEventListener('click', async () => {\n      try {\n        const r = await postAction('/me/collect', {});\n        showStatus('collectMsg', r.ok, r.ok ? '\u53d7\u53d6\u7bb1\u3092\u78ba\u8a8d\u3057\u307e\u3057\u305f\u3002' : (r.message || '\u5931\u6557\u3057\u307e\u3057\u305f\u3002'));\n        load();\n      } catch (e) { showStatus('collectMsg', false, '\u30b2\u30fc\u30e0\u5185\u306b\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u3044\u308b\u6642\u3060\u3051\u64cd\u4f5c\u3067\u304d\u307e\u3059\u3002'); }\n    });\n\n    document.getElementById('stockBuyBtn').addEventListener('click', async () => {\n      const symbol = document.getElementById('stockSymbol').value.trim();\n      const qty = document.getElementById('stockQty').value;\n      if (!symbol || !qty) { showStatus('stockMsg', false, '\u9298\u67c4\u3068\u682a\u6570\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002'); return; }\n      try {\n        const r = await postAction('/me/worldstock/buy', { symbol, qty });\n        showStatus('stockMsg', r.ok, r.ok ? '\u8cfc\u5165\u51e6\u7406\u3092\u9001\u4fe1\u3057\u307e\u3057\u305f(\u7d50\u679c\u306f\u30b2\u30fc\u30e0\u5185\u30c1\u30e3\u30c3\u30c8\u3067\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044)\u3002' : (r.message || '\u5931\u6557\u3057\u307e\u3057\u305f\u3002'));\n        load();\n      } catch (e) { showStatus('stockMsg', false, '\u30b2\u30fc\u30e0\u5185\u306b\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u3044\u308b\u6642\u3060\u3051\u64cd\u4f5c\u3067\u304d\u307e\u3059\u3002'); }\n    });\n\n    async function payStorageRent() {\n      try {\n        const r = await postAction('/me/storage/pay-rent', {});\n        showStatus('storageMsg', r.ok, r.ok ? '\u652f\u6255\u3044\u3092\u9001\u4fe1\u3057\u307e\u3057\u305f\u3002' : (r.message || '\u6b8b\u9ad8\u4e0d\u8db3\u3001\u307e\u305f\u306f\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u3044\u307e\u305b\u3093\u3002'));\n        load();\n      } catch (e) { showStatus('storageMsg', false, '\u30b2\u30fc\u30e0\u5185\u306b\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u3044\u308b\u6642\u3060\u3051\u64cd\u4f5c\u3067\u304d\u307e\u3059\u3002'); }\n    }\n\n    async function bidAuction(id, amount) {\n      try {\n        const r = await postAction('/me/auction/bid', { auction_id: id, amount });\n        showStatus('auctionMsg', r.ok, r.ok ? '\u5165\u672d\u3092\u9001\u4fe1\u3057\u307e\u3057\u305f(\u7d50\u679c\u306f\u30b2\u30fc\u30e0\u5185\u30c1\u30e3\u30c3\u30c8\u3067\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044)\u3002' : (r.message || '\u5931\u6557\u3057\u307e\u3057\u305f\u3002'));\n        load();\n      } catch (e) { showStatus('auctionMsg', false, '\u30b2\u30fc\u30e0\u5185\u306b\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u3044\u308b\u6642\u3060\u3051\u64cd\u4f5c\u3067\u304d\u307e\u3059\u3002'); }\n    }\n\n    function bidAuctionFromInput(id) {\n      const val = document.getElementById('bidamt-' + id).value;\n      if (!val) { showStatus('auctionMsg', false, '\u5165\u672d\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002'); return; }\n      bidAuction(id, val);\n    }\n\n    load();\n  </script>\n</body>\n</html>\n";
    private static final String DASHBOARD_HTML = "<!doctype html>\n<html lang=\"ja\">\n<head>\n<meta charset=\"UTF-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n<title>\u7d4c\u6e08\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9 | MinecraftBank</title>\n<style>\n  :root {\n    --bg: #0b0f19;\n    --panel: #141b2c;\n    --panel-alt: #101625;\n    --panel-border: #232c42;\n    --text: #eaeefb;\n    --text-dim: #8b93a7;\n    --gold: #f4c542;\n    --emerald: #4ade9b;\n    --red: #ff6b6b;\n    --accent: #5b8cff;\n    --purple: #b18cff;\n    --cyan: #43d9e0;\n\n    /* \u2605\u8ffd\u52a0: \u30c7\u30fc\u30bf\u53ef\u8996\u5316\u7528\u306e\u691c\u8a3c\u6e08\u307f\u30d1\u30ec\u30c3\u30c8(dataviz skill\u306ecolor-formula.md\u306b\u6e96\u62e0)\n       \u30ab\u30c6\u30b4\u30ea\u30ab\u30eb8\u8272\u306f\u56fa\u5b9a\u9806\u3067\u8b58\u5225(\u7cfb\u5217/\u30ab\u30c6\u30b4\u30ea)\u5c02\u7528\u3001\u30b9\u30c6\u30fc\u30bf\u30b94\u8272\u306f\u72b6\u614b\u5c02\u7528\u3001\n       \u30b7\u30fc\u30b1\u30f3\u30b7\u30e3\u30eb/\u30c0\u30a4\u30d0\u30fc\u30b8\u30f3\u30b0\u306f\u5927\u5c0f\u30fb\u6975\u6027\u306e\u8868\u73fe\u5c02\u7528\u3002\u8272\u306e\u5f79\u5272\u3092\u6df7\u540c\u3057\u306a\u3044\u3053\u3068\u3002 */\n    --cat-1: #3987e5; /* blue */\n    --cat-2: #d95926; /* orange */\n    --cat-3: #199e70; /* aqua */\n    --cat-4: #c98500; /* yellow */\n    --cat-5: #d55181; /* magenta */\n    --cat-6: #008300; /* green */\n    --cat-7: #9085e9; /* violet */\n    --cat-8: #e66767; /* red */\n    --status-good: #0ca30c;\n    --status-warning: #fab219;\n    --status-serious: #ec835a;\n    --status-critical: #d03b3b;\n    --seq-100: #cde2fb;\n    --seq-400: #3987e5;\n    --seq-700: #0d366b;\n    --div-neg: #3987e5;  /* \u57fa\u6e96\u5024\u3088\u308a\u5b89\u3044(\u30de\u30a4\u30ca\u30b9\u5074\u306e\u6975) */\n    --div-pos: #e66767;  /* \u57fa\u6e96\u5024\u3088\u308a\u9ad8\u3044(\u30d7\u30e9\u30b9\u5074\u306e\u6975) */\n    --div-mid: #383835;  /* \u4e2d\u7acb(\u57fa\u6e96\u5024\u3068\u540c\u6c34\u6e96) */\n  }\n  * { box-sizing: border-box; }\n  ::-webkit-scrollbar { width: 10px; height: 10px; }\n  ::-webkit-scrollbar-thumb { background: #2a3350; border-radius: 6px; }\n  ::-webkit-scrollbar-track { background: transparent; }\n  body {\n    margin: 0;\n    font-family: 'Segoe UI', 'Hiragino Kaku Gothic ProN', 'Yu Gothic', sans-serif;\n    background:\n      radial-gradient(circle at 15% -10%, #24304f66 0%, transparent 45%),\n      radial-gradient(circle at 100% 0%, #1a2c2266 0%, transparent 40%),\n      var(--bg);\n    color: var(--text);\n    min-height: 100vh;\n    padding: 32px 20px 60px;\n  }\n  .wrap { max-width: 1180px; margin: 0 auto; }\n  @keyframes fadeInUp {\n    from { opacity: 0; transform: translateY(10px); }\n    to { opacity: 1; transform: translateY(0); }\n  }\n  header {\n    display: flex;\n    align-items: flex-start;\n    justify-content: space-between;\n    flex-wrap: wrap;\n    gap: 12px;\n    margin-bottom: 24px;\n    animation: fadeInUp .4s ease both;\n  }\n  header h1 {\n    font-size: 25px;\n    margin: 0 0 6px;\n    letter-spacing: 0.3px;\n  }\n  header h1 span { color: var(--gold); }\n  .sub-row { display: flex; align-items: center; gap: 10px; flex-wrap: wrap; }\n  .event-badge {\n    display: inline-flex;\n    align-items: center;\n    gap: 6px;\n    background: #1c2440;\n    border: 1px solid var(--panel-border);\n    color: var(--cyan);\n    font-size: 12px;\n    font-weight: 600;\n    padding: 5px 12px;\n    border-radius: 999px;\n  }\n  .event-badge::before { content: '\u25cf'; font-size: 8px; color: var(--cyan); }\n  #updated {\n    font-size: 12px;\n    color: var(--text-dim);\n    text-align: right;\n  }\n  #updated b { color: var(--emerald); }\n  #countdown { color: var(--text-dim); font-variant-numeric: tabular-nums; }\n  .grid {\n    display: grid;\n    grid-template-columns: repeat(auto-fit, minmax(170px, 1fr));\n    gap: 14px;\n    margin-bottom: 18px;\n  }\n  .stat-card {\n    background: linear-gradient(160deg, var(--panel), var(--panel-alt));\n    border: 1px solid var(--panel-border);\n    border-radius: 14px;\n    padding: 16px 18px;\n    animation: fadeInUp .45s ease both;\n    transition: transform .15s ease, border-color .15s ease;\n  }\n  .stat-card:hover { transform: translateY(-2px); border-color: #34406a; }\n  .stat-card .label {\n    font-size: 11.5px;\n    color: var(--text-dim);\n    margin-bottom: 6px;\n    white-space: nowrap;\n  }\n  .stat-card .value {\n    font-size: 23px;\n    font-weight: 700;\n    font-variant-numeric: tabular-nums;\n  }\n  .stat-card.gold .value { color: var(--gold); }\n  .stat-card.emerald .value { color: var(--emerald); }\n  .stat-card.accent .value { color: var(--accent); }\n  .stat-card.red .value { color: var(--red); }\n  .stat-card.purple .value { color: var(--purple); }\n  .stat-card.cyan .value { color: var(--cyan); }\n  .stat-card.status-good .value { color: var(--status-good); }\n  .stat-card.status-warning .value { color: var(--status-warning); }\n  .stat-card.status-critical .value { color: var(--status-critical); }\n\n  /* \u2605\u8ffd\u52a0: \u300c\u30de\u30fc\u30b1\u30c3\u30c8\u6982\u6cc1\u300d\u300c\u3084\u308b\u3053\u3068\u6982\u6cc1\u300d\u5411\u3051\u306e\u65b0\u898f\u30b3\u30f3\u30dd\u30fc\u30cd\u30f3\u30c8 */\n  .subpanel {\n    background: var(--panel-alt);\n    border: 1px solid var(--panel-border);\n    border-radius: 12px;\n    padding: 14px 16px;\n  }\n  .subpanel h3 {\n    font-size: 12.5px;\n    margin: 0 0 12px;\n    color: var(--text-dim);\n    text-transform: uppercase;\n    letter-spacing: .5px;\n  }\n  .rank-list { display: flex; flex-direction: column; gap: 9px; }\n  .rank-list-item { display: flex; align-items: center; gap: 8px; font-size: 13px; }\n  .cat-dot { width: 10px; height: 10px; border-radius: 50%; flex: none; }\n  .rank-list-name { font-weight: 600; font-variant-numeric: tabular-nums; }\n  .rank-list-meta { margin-left: auto; color: var(--text-dim); font-size: 11.5px; text-align: right; white-space: nowrap; }\n  .badge-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(148px, 1fr)); gap: 8px; }\n  .badge {\n    display: flex;\n    align-items: center;\n    gap: 7px;\n    background: var(--panel);\n    border: 1px solid var(--panel-border);\n    border-radius: 8px;\n    padding: 7px 10px;\n    font-size: 12px;\n  }\n  .badge .dot { width: 9px; height: 9px; border-radius: 50%; flex: none; }\n  .badge-name { flex: 1; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; color: var(--text); }\n  .badge-trend { font-variant-numeric: tabular-nums; color: var(--text-dim); font-weight: 600; white-space: nowrap; }\n  .mini-legend { display: flex; gap: 14px; flex-wrap: wrap; font-size: 11.5px; color: var(--text-dim); margin-bottom: 10px; }\n  .mini-legend .dot { display: inline-block; width: 8px; height: 8px; border-radius: 50%; margin-right: 5px; }\n  .merchant-refresh { font-size: 13px; color: var(--text-dim); margin-bottom: 10px; }\n  .merchant-refresh b { color: var(--text); font-variant-numeric: tabular-nums; }\n  .deal-grid { display: flex; flex-direction: column; gap: 10px; }\n  .deal-card { background: var(--panel); border: 1px solid var(--panel-border); border-radius: 10px; padding: 10px 12px; }\n  .deal-card-head { display: flex; align-items: center; justify-content: space-between; gap: 8px; margin-bottom: 6px; }\n  .deal-name { font-weight: 600; font-size: 13px; }\n  .deal-discount { color: var(--emerald); font-weight: 700; font-size: 13px; font-variant-numeric: tabular-nums; white-space: nowrap; }\n  .meter-track { height: 7px; border-radius: 4px; background: var(--seq-100); overflow: hidden; }\n  .meter-fill { height: 100%; background: var(--seq-400); border-radius: 4px; transition: width .4s ease; }\n  .deal-stock { font-size: 11.5px; color: var(--text-dim); margin-top: 6px; }\n  .hero-tile { text-align: center; padding: 4px 0 16px; }\n  .hero-tile .hero-value {\n    font-size: 40px;\n    font-weight: 700;\n    color: var(--gold);\n    line-height: 1.15;\n    /* \u2605\u91cd\u8981: \u5927\u304d\u306a\u5358\u72ec\u306e\u6570\u5024\u306f\u6bd4\u4f8b\u6570\u5b57\u3092\u4f7f\u3046(\u7b49\u5e45\u306etabular-nums\u306f\u5927\u304d\u3044\u6587\u5b57\u3060\u3068\u9593\u5ef6\u3073\u3057\u3066\u898b\u3048\u308b) */\n  }\n  .hero-tile .hero-label {\n    font-size: 12px;\n    color: var(--text-dim);\n    margin-top: 6px;\n    text-transform: uppercase;\n    letter-spacing: .5px;\n  }\n  .mini-stats { display: flex; flex-direction: column; gap: 7px; font-size: 13px; color: var(--text-dim); }\n  .mini-stats b { color: var(--text); font-variant-numeric: tabular-nums; font-weight: 600; }\n  .panel {\n    background: var(--panel);\n    border: 1px solid var(--panel-border);\n    border-radius: 14px;\n    padding: 20px 22px;\n    margin-bottom: 16px;\n    animation: fadeInUp .5s ease both;\n  }\n  .panel-head {\n    display: flex;\n    align-items: center;\n    justify-content: space-between;\n    flex-wrap: wrap;\n    gap: 10px;\n    margin-bottom: 14px;\n  }\n  .panel h2 {\n    font-size: 14px;\n    margin: 0;\n    color: var(--text-dim);\n    text-transform: uppercase;\n    letter-spacing: 1px;\n  }\n  .legend { display: flex; gap: 14px; flex-wrap: wrap; font-size: 12px; color: var(--text-dim); }\n  .legend .dot { display: inline-block; width: 8px; height: 8px; border-radius: 50%; margin-right: 5px; }\n  .chart-box { position: relative; width: 100%; height: 220px; }\n  .chart-box canvas { width: 100%; height: 100%; display: block; }\n  .alert-box { border-radius: 10px; padding: 12px 16px; font-size: 13px; line-height: 1.7; }\n  .alert-box.ok { background: rgba(16,185,129,.12); border: 1px solid rgba(16,185,129,.4); color: var(--emerald); }\n  .alert-box.warn { background: rgba(239,68,68,.12); border: 1px solid rgba(239,68,68,.4); color: #f87171; }\n  .alert-box ul { margin: 6px 0 0 18px; padding: 0; }\n  .alert-box .alert-time { color: var(--text-dim); font-size: 11px; margin-top: 6px; }\n  .bars-box { position: relative; width: 100%; height: 160px; }\n  .panels-2col {\n    display: grid;\n    grid-template-columns: 1fr 1fr;\n    gap: 16px;\n  }\n  @media (max-width: 820px) {\n    .panels-2col { grid-template-columns: 1fr; }\n  }\n  .toolbar {\n    display: flex;\n    align-items: center;\n    gap: 10px;\n    flex-wrap: wrap;\n  }\n  .search-input {\n    background: var(--panel-alt);\n    border: 1px solid var(--panel-border);\n    color: var(--text);\n    font-size: 13px;\n    padding: 7px 12px;\n    border-radius: 8px;\n    outline: none;\n    min-width: 160px;\n  }\n  .search-input:focus { border-color: var(--accent); }\n  table { width: 100%; border-collapse: collapse; font-size: 13px; }\n  thead th {\n    position: sticky;\n    top: 0;\n    background: var(--panel);\n    text-align: left;\n    color: var(--text-dim);\n    font-weight: 600;\n    font-size: 11.5px;\n    text-transform: uppercase;\n    letter-spacing: .5px;\n    padding: 8px 10px;\n    border-bottom: 1px solid var(--panel-border);\n    cursor: pointer;\n    user-select: none;\n    white-space: nowrap;\n  }\n  thead th:hover { color: var(--text); }\n  thead th .arrow { opacity: .5; margin-left: 3px; font-size: 10px; }\n  tbody td {\n    padding: 8px 10px;\n    border-bottom: 1px solid #1c2438;\n    vertical-align: middle;\n  }\n  tbody tr { transition: background .12s ease; }\n  tbody tr:hover { background: #1a2136; }\n  tbody tr:nth-child(even) { background: #121a2b55; }\n  tbody tr:nth-child(even):hover { background: #1a2136; }\n  .rank-cell { width: 34px; font-weight: 700; color: var(--text-dim); }\n  .medal { font-size: 14px; }\n  .name-cell { font-weight: 600; max-width: 140px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }\n  .bar-cell { min-width: 90px; }\n  .bar-track { height: 8px; background: #1c2338; border-radius: 5px; overflow: hidden; }\n  .bar-fill { height: 100%; background: linear-gradient(90deg, var(--accent), var(--emerald)); border-radius: 5px; transition: width .4s ease; }\n  .amount-cell { text-align: right; font-variant-numeric: tabular-nums; color: var(--gold); font-weight: 600; white-space: nowrap; }\n  .score-cell { text-align: right; font-variant-numeric: tabular-nums; color: var(--cyan); white-space: nowrap; }\n  .pagination {\n    display: flex;\n    align-items: center;\n    justify-content: flex-end;\n    gap: 10px;\n    margin-top: 12px;\n    font-size: 12px;\n    color: var(--text-dim);\n  }\n  .pagination button {\n    background: var(--panel-alt);\n    border: 1px solid var(--panel-border);\n    color: var(--text);\n    padding: 5px 12px;\n    border-radius: 7px;\n    cursor: pointer;\n    font-size: 12px;\n  }\n  .pagination button:disabled { opacity: .35; cursor: default; }\n  .pagination button:not(:disabled):hover { border-color: var(--accent); }\n  .empty { color: var(--text-dim); font-size: 13px; padding: 14px 0; text-align: center; }\n  footer {\n    text-align: center;\n    color: var(--text-dim);\n    font-size: 12px;\n    margin-top: 8px;\n  }\n</style>\n</head>\n<body>\n  <div class=\"wrap\">\n    <header>\n      <div>\n        <h1>\ud83d\udcb0 <span>MinecraftBank</span> \u7d4c\u6e08\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9</h1>\n        <div class=\"sub-row\">\n          <div class=\"event-badge\" id=\"eventBadge\">\u901a\u5e38</div>\n        </div>\n      </div>\n      <div id=\"updated\">\u6700\u7d42\u66f4\u65b0: <b id=\"updatedAt\">-</b><br><span id=\"countdown\"></span></div>\n    </header>\n\n    <div class=\"grid\" id=\"statGrid\">\n      <div class=\"stat-card gold\">\n        <div class=\"label\">\u56fd\u5eab\u6b8b\u9ad8</div>\n        <div class=\"value\" id=\"treasury\">-</div>\n      </div>\n      <div class=\"stat-card red\">\n        <div class=\"label\">\u653f\u5e9c\u50b5\u52d9\u5408\u8a08</div>\n        <div class=\"value\" id=\"totalGovDebt\">-</div>\n      </div>\n      <div class=\"stat-card gold\">\n        <div class=\"label\">\u4eca\u9031\u306e\u53d6\u5f15\u91cf</div>\n        <div class=\"value\" id=\"weeklyTrade\">-</div>\n      </div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>\u7570\u5e38\u691c\u77e5</h2>\n      </div>\n      <div class=\"alert-box ok\" id=\"selfcheckBox\">\u78ba\u8a8d\u4e2d...</div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>\u56fd\u5eab\u6b8b\u9ad8\u306e\u63a8\u79fb</h2>\n      </div>\n      <div class=\"chart-box\"><canvas id=\"treasuryChart\"></canvas></div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>\u8cc7\u7523\u30e9\u30f3\u30ad\u30f3\u30b0</h2>\n        <div class=\"toolbar\">\n          <input class=\"search-input\" id=\"lbSearch\" type=\"text\" placeholder=\"\u30d7\u30ec\u30a4\u30e4\u30fc\u540d\u3067\u691c\u7d22...\">\n        </div>\n      </div>\n      <div style=\"overflow-x:auto\">\n        <table>\n          <thead>\n            <tr>\n              <th data-key=\"rank\">#<span class=\"arrow\"></span></th>\n              <th data-key=\"name\">\u30d7\u30ec\u30a4\u30e4\u30fc<span class=\"arrow\"></span></th>\n              <th data-key=\"credit_score\">\u4fe1\u7528\u30b9\u30b3\u30a2<span class=\"arrow\"></span></th>\n              <th data-key=\"total_assets\">\u7dcf\u8cc7\u7523<span class=\"arrow\"></span></th>\n            </tr>\n          </thead>\n          <tbody id=\"lbBody\"></tbody>\n        </table>\n      </div>\n      <div id=\"lbEmpty\" class=\"empty\" style=\"display:none\">\u30c7\u30fc\u30bf\u304c\u3042\u308a\u307e\u305b\u3093</div>\n      <div class=\"pagination\">\n        <span id=\"lbPageInfo\"></span>\n        <button id=\"lbPrev\">\u2190 \u524d\u3078</button>\n        <button id=\"lbNext\">\u6b21\u3078 \u2192</button>\n      </div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>\ud83d\uded2 \u30de\u30fc\u30b1\u30c3\u30c8\u6982\u6cc1</h2>\n      </div>\n\n      <div class=\"grid\" id=\"marketStatGrid\"></div>\n\n      <div class=\"panels-2col\" style=\"margin-top:16px\">\n        <div class=\"subpanel\">\n          <h3>\ud83d\udcc8 \u4e16\u754c\u682a\u5f0f\u5e02\u5834 - \u4fdd\u6709\u682a\u6570\u30c8\u30c3\u30d75</h3>\n          <div class=\"rank-list\" id=\"worldStockTopList\"></div>\n        </div>\n        <div class=\"subpanel\">\n          <h3>\ud83e\uddf3 \u5de1\u56de\u5546\u4eba</h3>\n          <div id=\"merchantStatus\"></div>\n        </div>\n      </div>\n\n      <div class=\"subpanel\" style=\"margin-top:16px\">\n        <h3>\u26cf \u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7 - \u57fa\u6e96\u4fa1\u683c\u3068\u306e\u4e56\u96e2</h3>\n        <div class=\"mini-legend\">\n          <span><span class=\"dot\" style=\"background:var(--div-neg)\"></span>\u57fa\u6e96\u3088\u308a\u5b89\u3044</span>\n          <span><span class=\"dot\" style=\"background:var(--div-mid)\"></span>\u6a2a\u3070\u3044</span>\n          <span><span class=\"dot\" style=\"background:var(--div-pos)\"></span>\u57fa\u6e96\u3088\u308a\u9ad8\u3044</span>\n        </div>\n        <div class=\"badge-grid\" id=\"resourceBadgeGrid\"></div>\n      </div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>\ud83c\udfaf \u3084\u308b\u3053\u3068\u6982\u6cc1</h2>\n      </div>\n\n      <div class=\"grid\" id=\"questStatGrid\"></div>\n\n      <div class=\"panels-2col\" style=\"margin-top:16px\">\n        <div class=\"subpanel\">\n          <h3>\ud83c\udf9f \u5b9d\u304f\u3058</h3>\n          <div class=\"hero-tile\">\n            <div class=\"hero-value\" id=\"lotteryPool\">-</div>\n            <div class=\"hero-label\">\u6b21\u56de\u62bd\u9078\u306e\u8cde\u91d1\u30d7\u30fc\u30eb</div>\n          </div>\n          <div class=\"mini-stats\">\n            <div>\u6b21\u56de\u62bd\u9078\u307e\u3067: <b id=\"lotteryCountdown\">-</b></div>\n            <div>\u8ca9\u58f2\u30c1\u30b1\u30c3\u30c8\u6570: <b id=\"lotteryTickets\">-</b></div>\n            <div>\u524d\u56de\u306e\u5f53\u305b\u3093: <b id=\"lotteryLastWinner\">-</b></div>\n          </div>\n        </div>\n        <div class=\"subpanel\">\n          <h3>\ud83d\udce6 \u5009\u5eab\u30ec\u30f3\u30bf\u30eb / \ud83d\udcb3 \u5206\u5272\u6255\u3044 / \u26cf \u57cb\u8535\u91d1</h3>\n          <div class=\"grid\" id=\"miscStatGrid\"></div>\n        </div>\n      </div>\n    </div>\n\n    <footer>10\u79d2\u3054\u3068\u306b\u81ea\u52d5\u66f4\u65b0\u3055\u308c\u307e\u3059</footer>\n  </div>\n\n  <script>\n    const PAGE_SIZE = 10;\n    let lastData = null;\n    const state = {\n      lb: { sortKey: 'rank', sortDir: 1, page: 1, search: '' }\n    };\n\n    function fmtYen(n) { return Math.round(n).toLocaleString('ja-JP') + '\u5186'; }\n    function fmtNum(n) { return Math.round(n).toLocaleString('ja-JP'); }\n    function medal(rank) {\n      if (rank === 1) return '\ud83e\udd47';\n      if (rank === 2) return '\ud83e\udd48';\n      if (rank === 3) return '\ud83e\udd49';\n      return rank;\n    }\n\n    // \u2605\u8ffd\u52a0: \u30ab\u30c6\u30b4\u30ea\u30ab\u30eb\u30d1\u30ec\u30c3\u30c8(\u56fa\u5b9a8\u8272\u30fb\u56fa\u5b9a\u9806\u3002\u7cfb\u5217/\u30ab\u30c6\u30b4\u30ea\u306e\u8b58\u5225\u5c02\u7528\u3002\u30b9\u30c6\u30fc\u30bf\u30b9\u8272\u3068\u306f\u6df7\u540c\u3057\u306a\u3044\u3053\u3068)\n    const CAT_COLORS = ['var(--cat-1)', 'var(--cat-2)', 'var(--cat-3)', 'var(--cat-4)', 'var(--cat-5)', 'var(--cat-6)', 'var(--cat-7)', 'var(--cat-8)'];\n\n    // \u2605\u8ffd\u52a0: \u30c0\u30a4\u30d0\u30fc\u30b8\u30f3\u30b0\u914d\u8272(\u9752\u21d4\u30b0\u30ec\u30fc\u21d4\u8d64)\u3002\u57fa\u6e96\u5024\u304b\u3089\u306e\u4e56\u96e2%\u3092\u00b130%\u3067\u30af\u30e9\u30f3\u30d7\u3057\u3066\u7dda\u5f62\u88dc\u9593\u3059\u308b\u3002\n    function divergingColor(pct) {\n      const clamped = Math.max(-30, Math.min(30, pct || 0));\n      const t = clamped / 30; // -1..1\n      const neg = [0x39, 0x87, 0xe5];  // --cat-1 / --div-neg (blue)\n      const mid = [0x38, 0x38, 0x35];  // --div-mid (neutral gray)\n      const pos = [0xe6, 0x67, 0x67];  // --cat-8 / --div-pos (red)\n      const from = t < 0 ? neg : pos;\n      const amt = Math.abs(t);\n      const rgb = mid.map((m, i) => Math.round(m + (from[i] - m) * amt));\n      return `rgb(${rgb[0]},${rgb[1]},${rgb[2]})`;\n    }\n\n    // \u2605\u8ffd\u52a0: \u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7/\u5de1\u56de\u5546\u4eba\u306e\u7d20\u6750\u540d \u65e5\u672c\u8a9e\u8868\u793a(\u672a\u767b\u9332\u306e\u7d20\u6750\u306f\u6574\u5f62\u3057\u3066\u8868\u793a)\n    const MATERIAL_JA = {\n      COAL: '\u77f3\u70ad', IRON_INGOT: '\u9244\u30a4\u30f3\u30b4\u30c3\u30c8', GOLD_INGOT: '\u91d1\u30a4\u30f3\u30b4\u30c3\u30c8', COPPER_INGOT: '\u9285\u30a4\u30f3\u30b4\u30c3\u30c8',\n      REDSTONE: '\u30ec\u30c3\u30c9\u30b9\u30c8\u30fc\u30f3', LAPIS_LAZULI: '\u30e9\u30d4\u30b9\u30e9\u30ba\u30ea', DIAMOND: '\u30c0\u30a4\u30e4\u30e2\u30f3\u30c9', EMERALD: '\u30a8\u30e1\u30e9\u30eb\u30c9',\n      NETHERITE_SCRAP: '\u30cd\u30b6\u30e9\u30a4\u30c8\u306e\u6b20\u7247', WHEAT: '\u5c0f\u9ea6', CARROT: '\u30cb\u30f3\u30b8\u30f3', POTATO: '\u30b8\u30e3\u30ac\u30a4\u30e2',\n      BEETROOT: '\u30d3\u30fc\u30c8\u30eb\u30fc\u30c8', MELON_SLICE: '\u30b9\u30a4\u30ab', PUMPKIN: '\u30ab\u30dc\u30c1\u30e3', SUGAR_CANE: '\u30b5\u30c8\u30a6\u30ad\u30d3',\n      NETHER_WART: '\u30cd\u30b6\u30fc\u30a6\u30a9\u30fc\u30c8', COCOA_BEANS: '\u30ab\u30ab\u30aa\u8c46', ROTTEN_FLESH: '\u8150\u3063\u305f\u8089', BONE: '\u9aa8',\n      STRING: '\u7cf8', GUNPOWDER: '\u706b\u85ac', SPIDER_EYE: '\u30af\u30e2\u306e\u76ee', SLIME_BALL: '\u30b9\u30e9\u30a4\u30e0\u30dc\u30fc\u30eb',\n      ENDER_PEARL: '\u30a8\u30f3\u30c0\u30fc\u30d1\u30fc\u30eb', BLAZE_ROD: '\u30d6\u30ec\u30a4\u30ba\u30ed\u30c3\u30c9', GHAST_TEAR: '\u30ac\u30b9\u30c8\u306e\u6d99',\n      OAK_LOG: '\u30aa\u30fc\u30af\u306e\u539f\u6728', SPRUCE_LOG: '\u30c8\u30a6\u30d2\u306e\u539f\u6728', BIRCH_LOG: '\u30b7\u30e9\u30ab\u30d0\u306e\u539f\u6728', JUNGLE_LOG: '\u30b8\u30e3\u30f3\u30b0\u30eb\u306e\u539f\u6728',\n      ACACIA_LOG: '\u30a2\u30ab\u30b7\u30a2\u306e\u539f\u6728', DARK_OAK_LOG: '\u30c0\u30fc\u30af\u30aa\u30fc\u30af\u306e\u539f\u6728', MANGROVE_LOG: '\u30de\u30f3\u30b0\u30ed\u30fc\u30d6\u306e\u539f\u6728', CHERRY_LOG: '\u30b5\u30af\u30e9\u306e\u539f\u6728',\n      ELYTRA: '\u30a8\u30ea\u30c8\u30e9', TOTEM_OF_UNDYING: '\u4e0d\u6b7b\u306e\u30c8\u30fc\u30c6\u30e0', NETHERITE_INGOT: '\u30cd\u30b6\u30e9\u30a4\u30c8\u30a4\u30f3\u30b4\u30c3\u30c8',\n      NETHERITE_BLOCK: '\u30cd\u30b6\u30e9\u30a4\u30c8\u30d6\u30ed\u30c3\u30af', ENCHANTED_GOLDEN_APPLE: '\u30a8\u30f3\u30c1\u30e3\u30f3\u30c8\u3055\u308c\u305f\u91d1\u306e\u30ea\u30f3\u30b4',\n      SADDLE: '\u30b5\u30c9\u30eb', NAME_TAG: '\u540d\u672d', SHULKER_BOX: '\u30b7\u30e5\u30eb\u30ab\u30fc\u30dc\u30c3\u30af\u30b9', TRIDENT: '\u30c8\u30e9\u30a4\u30c7\u30f3\u30c8',\n      NETHER_STAR: '\u30cd\u30b6\u30fc\u30b9\u30bf\u30fc', DRAGON_EGG: '\u30c9\u30e9\u30b4\u30f3\u306e\u5375', BEACON: '\u30d3\u30fc\u30b3\u30f3', DIAMOND_BLOCK: '\u30c0\u30a4\u30e4\u30e2\u30f3\u30c9\u30d6\u30ed\u30c3\u30af',\n      EMERALD_BLOCK: '\u30a8\u30e1\u30e9\u30eb\u30c9\u30d6\u30ed\u30c3\u30af', MUSIC_DISC_PIGSTEP: '\u30ec\u30b3\u30fc\u30c9(Pigstep)', MUSIC_DISC_OTHERSIDE: '\u30ec\u30b3\u30fc\u30c9(Otherside)',\n      HEART_OF_THE_SEA: '\u6d77\u306e\u5fc3', CONDUIT: '\u30b3\u30f3\u30b8\u30c3\u30c8', END_CRYSTAL: '\u30a8\u30f3\u30c9\u30af\u30ea\u30b9\u30bf\u30eb', GOLDEN_CARROT: '\u91d1\u306e\u30cb\u30f3\u30b8\u30f3'\n    };\n    function materialLabel(name) {\n      if (MATERIAL_JA[name]) return MATERIAL_JA[name];\n      return String(name).toLowerCase().split('_').map(w => w.charAt(0).toUpperCase() + w.slice(1)).join(' ');\n    }\n\n    // \u2605\u8ffd\u52a0: \u6b8b\u308a\u30df\u30ea\u79d2\u3092\u300c\u25ef\u65e5\u25ef\u6642\u9593\u300d\u300c\u25ef\u6642\u9593\u25ef\u5206\u300d\u300c\u25ef\u5206\u300d\u5f62\u5f0f\u306b\u6574\u5f62\n    function fmtDuration(ms) {\n      if (!ms || ms <= 0) return '\u307e\u3082\u306a\u304f';\n      const totalMin = Math.floor(ms / 60000);\n      const d = Math.floor(totalMin / 1440);\n      const h = Math.floor((totalMin % 1440) / 60);\n      const m = totalMin % 60;\n      if (d > 0) return d + '\u65e5' + h + '\u6642\u9593';\n      if (h > 0) return h + '\u6642\u9593' + m + '\u5206';\n      return m + '\u5206';\n    }\n\n    function sortRows(rows, key, dir) {\n      return [...rows].sort((a, b) => {\n        const av = a[key], bv = b[key];\n        if (typeof av === 'string') return av.localeCompare(bv, 'ja') * dir;\n        return ((av ?? 0) - (bv ?? 0)) * dir;\n      });\n    }\n\n    function bindSortableHeaders(tableSelector, sortState, renderFn) {\n      document.querySelectorAll(tableSelector + ' thead th[data-key]').forEach(th => {\n        th.addEventListener('click', () => {\n          const key = th.getAttribute('data-key');\n          if (sortState.sortKey === key) {\n            sortState.sortDir *= -1;\n          } else {\n            sortState.sortKey = key;\n            sortState.sortDir = 1;\n          }\n          sortState.page = 1;\n          renderFn();\n        });\n      });\n    }\n\n    function updateHeaderArrows(tableSelector, sortState) {\n      document.querySelectorAll(tableSelector + ' thead th[data-key]').forEach(th => {\n        const arrow = th.querySelector('.arrow');\n        if (th.getAttribute('data-key') === sortState.sortKey) {\n          arrow.textContent = sortState.sortDir === 1 ? '\u25b2' : '\u25bc';\n        } else {\n          arrow.textContent = '';\n        }\n      });\n    }\n\n    function drawLineChart(canvas, series, labels) {\n      if (!canvas) return;\n      const dpr = window.devicePixelRatio || 1;\n      const rect = canvas.parentElement.getBoundingClientRect();\n      const w = Math.max(rect.width, 100), h = Math.max(rect.height, 100);\n      canvas.width = w * dpr; canvas.height = h * dpr;\n      const ctx = canvas.getContext('2d');\n      ctx.scale(dpr, dpr);\n      ctx.clearRect(0, 0, w, h);\n\n      const padL = 54, padR = 12, padT = 12, padB = 24;\n      const plotW = w - padL - padR, plotH = h - padT - padB;\n\n      const allVals = series.flatMap(s => s.data);\n      if (allVals.length === 0) {\n        ctx.fillStyle = '#8b93a7';\n        ctx.font = '12px sans-serif';\n        ctx.fillText('\u30c7\u30fc\u30bf\u304c\u84c4\u7a4d\u3055\u308c\u308b\u3068\u3053\u3053\u306b\u8868\u793a\u3055\u308c\u307e\u3059', padL, h / 2);\n        return;\n      }\n      let min = Math.min(...allVals), max = Math.max(...allVals);\n      if (min === max) { min -= 1; max += 1; }\n      const pad = (max - min) * 0.1;\n      min -= pad; max += pad;\n\n      ctx.strokeStyle = '#1f2740';\n      ctx.lineWidth = 1;\n      ctx.font = '10.5px sans-serif';\n      ctx.fillStyle = '#8b93a7';\n      const gridLines = 4;\n      for (let i = 0; i <= gridLines; i++) {\n        const y = padT + (plotH / gridLines) * i;\n        ctx.beginPath();\n        ctx.moveTo(padL, y);\n        ctx.lineTo(w - padR, y);\n        ctx.stroke();\n        const val = max - ((max - min) / gridLines) * i;\n        ctx.fillText(Math.round(val).toLocaleString('ja-JP'), 2, y + 3);\n      }\n\n      const n = Math.max(...series.map(s => s.data.length), 2);\n      series.forEach(s => {\n        if (s.data.length < 2) return;\n        ctx.beginPath();\n        s.data.forEach((v, i) => {\n          const x = padL + (i / (n - 1)) * plotW;\n          const y = padT + plotH - ((v - min) / (max - min)) * plotH;\n          if (i === 0) ctx.moveTo(x, y); else ctx.lineTo(x, y);\n        });\n        ctx.strokeStyle = s.color;\n        ctx.lineWidth = 2;\n        ctx.lineJoin = 'round';\n        ctx.stroke();\n      });\n\n      if (labels && labels.length >= 2) {\n        ctx.fillStyle = '#8b93a7';\n        const first = new Date(labels[0]).toLocaleTimeString('ja-JP', { hour: '2-digit', minute: '2-digit' });\n        const last = new Date(labels[labels.length - 1]).toLocaleTimeString('ja-JP', { hour: '2-digit', minute: '2-digit' });\n        ctx.fillText(first, padL, h - 6);\n        ctx.fillText(last, w - padR - 34, h - 6);\n      }\n    }\n\n    function renderLeaderboard() {\n      if (!lastData) return;\n      let rows = lastData.leaderboard || [];\n      if (state.lb.search) {\n        const q = state.lb.search.toLowerCase();\n        rows = rows.filter(r => r.name.toLowerCase().includes(q));\n      }\n      rows = sortRows(rows, state.lb.sortKey, state.lb.sortDir);\n      const totalPages = Math.max(1, Math.ceil(rows.length / PAGE_SIZE));\n      state.lb.page = Math.min(state.lb.page, totalPages);\n      const pageRows = rows.slice((state.lb.page - 1) * PAGE_SIZE, state.lb.page * PAGE_SIZE);\n      const maxAsset = Math.max(1, ...rows.map(r => r.total_assets));\n\n      const body = document.getElementById('lbBody');\n      document.getElementById('lbEmpty').style.display = rows.length === 0 ? 'block' : 'none';\n      body.innerHTML = pageRows.map(p => `\n        <tr>\n          <td class=\"rank-cell\"><span class=\"medal\">${medal(p.rank)}</span></td>\n          <td class=\"name-cell\" title=\"${p.name}\">${p.name}</td>\n          <td class=\"score-cell\">${p.credit_score}</td>\n          <td class=\"amount-cell\">\n            <div style=\"display:flex;align-items:center;gap:8px;justify-content:flex-end\">\n              <div class=\"bar-track bar-cell\"><div class=\"bar-fill\" style=\"width:${Math.max(4, p.total_assets / maxAsset * 100)}%\"></div></div>\n              <span>${fmtYen(p.total_assets)}</span>\n            </div>\n          </td>\n        </tr>\n      `).join('');\n\n      document.getElementById('lbPageInfo').textContent = rows.length === 0 ? '' : `${state.lb.page} / ${totalPages} \u30da\u30fc\u30b8 (\u5168${rows.length}\u4ef6)`;\n      document.getElementById('lbPrev').disabled = state.lb.page <= 1;\n      document.getElementById('lbNext').disabled = state.lb.page >= totalPages;\n      updateHeaderArrows('#lbBody', state.lb);\n      updateHeaderArrows('table:has(#lbBody)', state.lb);\n    }\n\n    function renderCharts() {\n      if (!lastData) return;\n      const hist = lastData.history || { labels: [], treasury: [] };\n      drawLineChart(document.getElementById('treasuryChart'),\n        [{ label: '\u56fd\u5eab\u6b8b\u9ad8', color: '#f4c542', data: hist.treasury || [] }], hist.labels);\n    }\n\n    // \u2605\u8ffd\u52a0: \u300c\ud83d\uded2 \u30de\u30fc\u30b1\u30c3\u30c8\u6982\u6cc1\u300d\u30bb\u30af\u30b7\u30e7\u30f3\u306e\u63cf\u753b(\u4e16\u754c\u682a\u5f0f\u5e02\u5834/\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7/\u5de1\u56de\u5546\u4eba/\u30aa\u30fc\u30af\u30b7\u30e7\u30f3)\n    function renderMarket() {\n      if (!lastData) return;\n      const ws = lastData.world_stock || { active_investors: 0, distinct_symbols: 0, total_portfolio_value: 0, top_symbols: [] };\n      const merchant = lastData.merchant || { refresh_at: 0, deals: [] };\n      const auction = lastData.auction || { active_listings: 0 };\n      const resources = lastData.resource_shop || [];\n\n      document.getElementById('marketStatGrid').innerHTML = `\n        <div class=\"stat-card accent\"><div class=\"label\">\u682a\u5f0f\u6295\u8cc7\u5bb6\u6570</div><div class=\"value\">${fmtNum(ws.active_investors)}\u4eba</div></div>\n        <div class=\"stat-card cyan\"><div class=\"label\">\u4fdd\u6709\u9298\u67c4\u6570(\u7a2e\u985e)</div><div class=\"value\">${fmtNum(ws.distinct_symbols)}</div></div>\n        <div class=\"stat-card gold\"><div class=\"label\">\u682a\u5f0f\u8a55\u4fa1\u984d\u5408\u8a08</div><div class=\"value\">${fmtYen(ws.total_portfolio_value)}</div></div>\n        <div class=\"stat-card purple\"><div class=\"label\">\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u51fa\u54c1\u4e2d</div><div class=\"value\">${fmtNum(auction.active_listings)}\u4ef6</div></div>\n      `;\n\n      const topList = document.getElementById('worldStockTopList');\n      const topSymbols = ws.top_symbols || [];\n      topList.innerHTML = topSymbols.length === 0 ? '<div class=\"empty\">\u4fdd\u6709\u30c7\u30fc\u30bf\u304c\u3042\u308a\u307e\u305b\u3093</div>' :\n        topSymbols.map((s, i) => `\n          <div class=\"rank-list-item\">\n            <span class=\"cat-dot\" style=\"background:${CAT_COLORS[i % CAT_COLORS.length]}\"></span>\n            <span class=\"rank-list-name\">${s.symbol}</span>\n            <span class=\"rank-list-meta\">${fmtNum(s.holders)}\u4eba\u4fdd\u6709 \u30fb ${fmtNum(s.total_shares)}\u682a</span>\n          </div>\n        `).join('');\n\n      const remainMs = Math.max(0, (merchant.refresh_at || 0) - Date.now());\n      const deals = merchant.deals || [];\n      const dealsHtml = deals.length === 0 ? '<div class=\"empty\">\u73fe\u5728\u306e\u54c1\u63c3\u3048\u306f\u3042\u308a\u307e\u305b\u3093</div>' :\n        deals.map(d => {\n          const ratio = d.stock_total > 0 ? Math.max(0, Math.min(1, d.stock_remaining / d.stock_total)) : 0;\n          return `\n            <div class=\"deal-card\">\n              <div class=\"deal-card-head\">\n                <span class=\"deal-name\">${materialLabel(d.material)}</span>\n                <span class=\"deal-discount\">-${Math.round(d.discount_percent)}%</span>\n              </div>\n              <div class=\"meter-track\"><div class=\"meter-fill\" style=\"width:${(ratio * 100).toFixed(0)}%\"></div></div>\n              <div class=\"deal-stock\">\u6b8b\u308a ${fmtNum(d.stock_remaining)} / ${fmtNum(d.stock_total)}</div>\n            </div>\n          `;\n        }).join('');\n      document.getElementById('merchantStatus').innerHTML = `\n        <div class=\"merchant-refresh\">\u6b21\u56de\u5165\u8377\u307e\u3067: <b>${fmtDuration(remainMs)}</b></div>\n        <div class=\"deal-grid\">${dealsHtml}</div>\n      `;\n\n      document.getElementById('resourceBadgeGrid').innerHTML = resources.map(r => {\n        const color = divergingColor(r.trend_percent);\n        const sign = r.trend_percent > 0 ? '+' : '';\n        return `\n          <div class=\"badge\" title=\"\u57fa\u6e96\u4fa1\u683c ${fmtNum(r.base_price)}\u5186/\u500b\">\n            <span class=\"dot\" style=\"background:${color}\"></span>\n            <span class=\"badge-name\">${materialLabel(r.material)}</span>\n            <span class=\"badge-trend\">${sign}${r.trend_percent.toFixed(1)}%</span>\n          </div>\n        `;\n      }).join('');\n    }\n\n    // \u2605\u8ffd\u52a0: \u300c\ud83c\udfaf \u3084\u308b\u3053\u3068\u6982\u6cc1\u300d\u30bb\u30af\u30b7\u30e7\u30f3\u306e\u63cf\u753b(\u4f9d\u983c\u30dc\u30fc\u30c9/\u5b9d\u304f\u3058/\u5009\u5eab\u30ec\u30f3\u30bf\u30eb/\u5206\u5272\u6255\u3044/\u57cb\u8535\u91d1)\n    function renderQuestsPanel() {\n      if (!lastData) return;\n      const q = lastData.quests || { available: 0, in_progress: 0, cooldown: 0, system_posted: 0, player_posted: 0 };\n      document.getElementById('questStatGrid').innerHTML = `\n        <div class=\"stat-card status-good\"><div class=\"label\">\u52df\u96c6\u4e2d</div><div class=\"value\">${fmtNum(q.available)}\u4ef6</div></div>\n        <div class=\"stat-card accent\"><div class=\"label\">\u53d7\u6ce8\u4e2d</div><div class=\"value\">${fmtNum(q.in_progress)}\u4ef6</div></div>\n        <div class=\"stat-card status-warning\"><div class=\"label\">\u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d</div><div class=\"value\">${fmtNum(q.cooldown)}\u4ef6</div></div>\n        <div class=\"stat-card\"><div class=\"label\">\u904b\u55b6\u6295\u7a3f</div><div class=\"value\">${fmtNum(q.system_posted)}\u4ef6</div></div>\n        <div class=\"stat-card\"><div class=\"label\">\u30d7\u30ec\u30a4\u30e4\u30fc\u6295\u7a3f</div><div class=\"value\">${fmtNum(q.player_posted)}\u4ef6</div></div>\n      `;\n\n      const lottery = lastData.lottery || { pool: 0, draw_at: 0, total_tickets_sold: 0, last_winner_name: '-', last_winner_amount: 0 };\n      document.getElementById('lotteryPool').textContent = fmtYen(lottery.pool);\n      document.getElementById('lotteryCountdown').textContent = fmtDuration((lottery.draw_at || 0) - Date.now());\n      document.getElementById('lotteryTickets').textContent = fmtNum(lottery.total_tickets_sold) + '\u679a';\n      document.getElementById('lotteryLastWinner').textContent = lottery.last_winner_name +\n        (lottery.last_winner_amount > 0 ? ` (${fmtYen(lottery.last_winner_amount)})` : '');\n\n      const treasure = lastData.treasure || { active: false, reward: 0 };\n      const storage = lastData.storage || { active_renters: 0 };\n      const installments = lastData.installments || { active_plans: 0, total_outstanding: 0 };\n      document.getElementById('miscStatGrid').innerHTML = `\n        <div class=\"stat-card ${treasure.active ? 'status-good' : ''}\">\n          <div class=\"label\">\u57cb\u8535\u91d1\u30a4\u30d9\u30f3\u30c8</div>\n          <div class=\"value\">${treasure.active ? ('\u767a\u751f\u4e2d (' + fmtYen(treasure.reward) + ')') : '\u5f85\u6a5f\u4e2d'}</div>\n        </div>\n        <div class=\"stat-card\"><div class=\"label\">\u5009\u5eab\u30ec\u30f3\u30bf\u30eb\u5951\u7d04\u6570</div><div class=\"value\">${fmtNum(storage.active_renters)}\u4ef6</div></div>\n        <div class=\"stat-card\"><div class=\"label\">\u5206\u5272\u6255\u3044\u30d7\u30e9\u30f3\u6570</div><div class=\"value\">${fmtNum(installments.active_plans)}\u4ef6</div></div>\n        <div class=\"stat-card gold\"><div class=\"label\">\u5206\u5272\u6255\u3044\u672a\u6255\u3044\u5408\u8a08</div><div class=\"value\">${fmtYen(installments.total_outstanding)}</div></div>\n      `;\n    }\n\n    function renderSelfCheck() {\n      const sc = (lastData && lastData.selfcheck) || { checked_at: 0, issues: [] };\n      const box = document.getElementById('selfcheckBox');\n      const issues = sc.issues || [];\n      const timeStr = sc.checked_at ? new Date(sc.checked_at).toLocaleString('ja-JP') : '\u672a\u5b9f\u884c';\n      if (issues.length === 0) {\n        box.className = 'alert-box ok';\n        box.innerHTML = '\u2705 \u7570\u5e38\u306f\u691c\u51fa\u3055\u308c\u3066\u3044\u307e\u305b\u3093\u3002<div class=\"alert-time\">\u6700\u7d42\u30c1\u30a7\u30c3\u30af: ' + timeStr + '</div>';\n      } else {\n        box.className = 'alert-box warn';\n        const items = issues.map(i => '<li>' + String(i).replace(/</g, '&lt;') + '</li>').join('');\n        box.innerHTML = '\u26a0\ufe0f ' + issues.length + ' \u4ef6\u306e\u4e0d\u6574\u5408\u3092\u691c\u51fa\u3057\u3001\u81ea\u52d5\u4fee\u5fa9\u3057\u307e\u3057\u305f\u3002<ul>' + items + '</ul><div class=\"alert-time\">\u6700\u7d42\u30c1\u30a7\u30c3\u30af: ' + timeStr + '</div>';\n      }\n    }\n\n    function renderAll() {\n      renderLeaderboard();\n      renderCharts();\n      renderMarket();\n      renderQuestsPanel();\n      renderSelfCheck();\n    }\n\n    let nextRefreshAt = Date.now() + 10000;\n    async function refresh() {\n      try {\n        const res = await fetch('/dashboard.json', { cache: 'no-store' });\n        const data = await res.json();\n        lastData = data;\n        document.getElementById('treasury').textContent = fmtYen(data.treasury || 0);\n        document.getElementById('totalGovDebt').textContent = fmtYen(data.total_gov_debt || 0);\n        const weekly = data.weekly || {};\n        document.getElementById('weeklyTrade').textContent = fmtYen(weekly.trade_volume || 0);\n        document.getElementById('eventBadge').textContent = data.active_event || '\u901a\u5e38';\n        document.getElementById('updatedAt').textContent = new Date(data.generated_at || Date.now()).toLocaleString('ja-JP');\n        renderAll();\n        nextRefreshAt = Date.now() + 10000;\n      } catch (e) {\n        document.getElementById('updatedAt').textContent = '\u53d6\u5f97\u30a8\u30e9\u30fc';\n      }\n    }\n\n    document.getElementById('lbSearch').addEventListener('input', e => { state.lb.search = e.target.value; state.lb.page = 1; renderLeaderboard(); });\n    document.getElementById('lbPrev').addEventListener('click', () => { state.lb.page--; renderLeaderboard(); });\n    document.getElementById('lbNext').addEventListener('click', () => { state.lb.page++; renderLeaderboard(); });\n    bindSortableHeaders('table:has(#lbBody)', state.lb, renderLeaderboard);\n\n    window.addEventListener('resize', renderCharts);\n    setInterval(() => {\n      const secLeft = Math.max(0, Math.round((nextRefreshAt - Date.now()) / 1000));\n      document.getElementById('countdown').textContent = secLeft + '\u79d2\u5f8c\u306b\u66f4\u65b0';\n    }, 1000);\n\n    refresh();\n    setInterval(refresh, 10000);\n  </script>\n</body>\n</html>\n";
    private static final LinkedHashMap<String, String> DEFAULT_MESSAGES;

    private String resolveCurrencyByCountry(String country) {
        if (country == null || country.isBlank()) {
            return null;
        }
        return COUNTRY_CURRENCY_MAP.get(country.trim().toLowerCase());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private String detectServerCountry() {
        HttpURLConnection conn = null;
        try {
            String body;
            URL url = new URL("http://ip-api.com/json/?fields=status,country");
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(4000);
            conn.setReadTimeout(4000);
            if (conn.getResponseCode() != 200) {
                String string = null;
                return string;
            }
            try (InputStream in = conn.getInputStream();){
                body = new String(in.readAllBytes(), StandardCharsets.UTF_8);
            }
            if (!body.contains("\"status\":\"success\"")) {
                in = null;
                return in;
            }
            Matcher m = Pattern.compile("\"country\"\\s*:\\s*\"([^\"]+)\"").matcher(body);
            String string = m.find() ? m.group(1) : null;
            return string;
        }
        catch (Exception ex) {
            this.getLogger().warning("[Economy] \u30b5\u30fc\u30d0\u30fc\u306e\u6240\u5728\u5730\u81ea\u52d5\u5224\u5b9a\u306b\u5931\u6557\u3057\u307e\u3057\u305f(currency-unit\u3092\u4f7f\u7528\u3057\u307e\u3059): " + ex.getMessage());
            String string = null;
            return string;
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    private void parseCustomMoney(String raw) {
        if (raw == null || raw.isBlank()) {
            this.cfgCustomMoneyEnabled = false;
            return;
        }
        Matcher m = Pattern.compile("^\\s*1\\s*([^\\d=]+?)\\s*=\\s*([0-9]+(?:\\.[0-9]+)?)\\s*$").matcher(raw);
        if (m.matches()) {
            this.cfgCustomMoneySymbol = m.group(1);
            this.cfgCustomMoneyRate = Double.parseDouble(m.group(2));
            this.cfgCustomMoneyEnabled = true;
        } else {
            this.cfgCustomMoneyEnabled = false;
        }
    }

    private String fmtCur(double amount) {
        if (this.cfgCustomMoneyEnabled) {
            double converted = amount / this.cfgCustomMoneyRate;
            String num = Math.abs(converted - (double)Math.round(converted)) < 0.005 ? String.valueOf(Math.round(converted)) : String.format("%.2f", converted);
            return num + this.cfgCustomMoneySymbol;
        }
        return (long)amount + this.cfgCurrencyUnit;
    }

    private String fmtCurPrecise(double amount) {
        if (this.cfgCustomMoneyEnabled) {
            return String.format("%.2f", amount / this.cfgCustomMoneyRate) + this.cfgCustomMoneySymbol;
        }
        return String.format("%.2f", amount) + this.cfgCurrencyUnit;
    }

    private void unlockAchievement(UUID u, String id, String displayName) {
        HashSet set = this.unlockedAchievements.computeIfAbsent(u, k -> new HashSet());
        if (set.contains(id)) {
            return;
        }
        set.add(id);
        Player online = Bukkit.getPlayer((UUID)u);
        if (online != null && online.isOnline()) {
            online.showTitle(Title.title((Component)this.mm("<gold><bold>\u5b9f\u7e3e\u89e3\u653e\uff01</bold></gold>"), (Component)this.mm("<yellow>" + displayName + "</yellow>")));
            this.msgKey(online, "achievement.unlocked", "title", displayName);
            online.playSound(online.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.2f);
        }
        this.addLog(u, "\u5b9f\u7e3e\u89e3\u653e: " + displayName);
        this.sendDiscordWebhook("\ud83c\udfc6 **" + (online != null ? online.getName() : Bukkit.getOfflinePlayer((UUID)u).getName()) + "** \u304c\u5b9f\u7e3e\u300c**" + displayName + "**\u300d\u3092\u89e3\u653e\u3057\u307e\u3057\u305f\uff01");
    }

    public void onEnable() {
        if (!this.setupEconomy()) {
            this.getLogger().severe("Vault\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3002\u30d7\u30e9\u30b0\u30a4\u30f3\u3092\u505c\u6b62\u3057\u307e\u3059\u3002");
            this.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        this.lenderKey = new NamespacedKey((Plugin)this, "lender_uuid");
        this.loanSlotKey = new NamespacedKey((Plugin)this, "loan_slot");
        this.auctionIdKey = new NamespacedKey((Plugin)this, "auction_id");
        this.hubItemKey = new NamespacedKey((Plugin)this, "economy_hub_item");
        this.adminTargetKey = new NamespacedKey((Plugin)this, "admin_target");
        this.questIdKey = new NamespacedKey((Plugin)this, "quest_id");
        this.worldStockKey = new NamespacedKey((Plugin)this, "world_stock_symbol");
        this.resourceMaterialKey = new NamespacedKey((Plugin)this, "resource_material");
        this.resourceActionKey = new NamespacedKey((Plugin)this, "resource_action");
        this.worldStockActionKey = new NamespacedKey((Plugin)this, "world_stock_action");
        this.merchantDealKey = new NamespacedKey((Plugin)this, "merchant_deal_id");
        this.merchantNpcMarkerKey = new NamespacedKey((Plugin)this, "merchant_npc_marker");
        this.treasureChestMarkerKey = new NamespacedKey((Plugin)this, "treasure_chest_marker");
        this.tradeTargetKey = new NamespacedKey((Plugin)this, "trade_target");
        this.groupAccountKey = new NamespacedKey((Plugin)this, "group_account_id");
        this.groupTargetKey = new NamespacedKey((Plugin)this, "group_target_uuid");
        this.investmentFundKey = new NamespacedKey((Plugin)this, "investment_fund_id");
        this.vipDealKey = new NamespacedKey((Plugin)this, "vip_deal_id");
        this.configKeyTag = new NamespacedKey((Plugin)this, "config_key");
        this.saveDefaultConfig();
        this.loadConfigValues();
        this.loadMessages();
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }
        File dbFile = new File(this.getDataFolder(), "data.db");
        boolean dbFileExistedBeforeStartup = dbFile.exists();
        try {
            this.db = new PluginDataStore(dbFile);
        }
        catch (SQLException ex) {
            this.getLogger().severe("SQLite\u30c7\u30fc\u30bf\u30d9\u30fc\u30b9(data.db)\u306e\u521d\u671f\u5316\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002\u30d7\u30e9\u30b0\u30a4\u30f3\u3092\u505c\u6b62\u3057\u307e\u3059: " + ex.getMessage());
            this.getServer().getPluginManager().disablePlugin((Plugin)this);
            return;
        }
        if (!dbFileExistedBeforeStartup && this.hasLegacyManagedDataInConfig()) {
            this.migrateLegacyYamlDataToSqlite();
        } else {
            this.loadData();
        }
        this.checkTreasureSpawn();
        List<String> startupIssues = this.runSelfCheck(true);
        this.lastSelfCheckIssues = startupIssues;
        this.lastSelfCheckAt = System.currentTimeMillis();
        if (!startupIssues.isEmpty()) {
            this.getLogger().warning("[\u81ea\u52d5\u4fee\u5fa9] \u8d77\u52d5\u6642\u306e\u30c7\u30fc\u30bf\u81ea\u5df1\u8a3a\u65ad\u3067 " + startupIssues.size() + " \u4ef6\u306e\u4e0d\u6574\u5408\u3092\u691c\u51fa\u3057\u3001\u81ea\u52d5\u4fee\u5fa9\u3057\u307e\u3057\u305f:");
            for (String issue : startupIssues) {
                this.getLogger().warning("[\u81ea\u52d5\u4fee\u5fa9] " + issue);
            }
            this.saveData();
        }
        if (this.getCommand("meco") != null) {
            this.getCommand("meco").setExecutor((CommandExecutor)this);
            this.getCommand("meco").setTabCompleter((TabCompleter)this);
        }
        this.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
        long autosaveTicks = (long)Math.max(1, this.cfgAutosaveIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, () -> {
            this.saveData();
            this.getLogger().info("[\u81ea\u52d5\u4fdd\u5b58] \u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u81ea\u52d5\u4fdd\u5b58\u3057\u307e\u3057\u305f\u3002");
        }, autosaveTicks, autosaveTicks);
        long backupTicks = (long)Math.max(1, this.cfgBackupIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::backupDatabase, backupTicks, backupTicks);
        long selfCheckTicks = (long)Math.max(1, this.cfgSelfCheckReportIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::runPeriodicSelfCheckReport, selfCheckTicks, selfCheckTicks);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::checkGovLoanDeadlines, 1200L, 1200L);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::checkCollateralDeadlines, 1200L, 1200L);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::refreshQuestBoard, 1200L, 1200L);
        long questSystemTicks = (long)Math.max(1, this.cfgQuestSystemCheckIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::generateSystemQuestsIfNeeded, questSystemTicks, questSystemTicks);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::checkAuctionEnd, 1200L, 1200L);
        long eventTicks = (long)Math.max(1, this.cfgRandomEventIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::tryRandomEconomyEvent, eventTicks, eventTicks);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::updateAllScoreboards, 20L, 40L);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::checkWorldStockAlerts, 2400L, 2400L);
        long dividendTicks = (long)Math.max(1, this.cfgWorldStockDividendIntervalHours) * 60L * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::payWorldStockDividends, dividendTicks, dividendTicks);
        this.refreshFxRates();
        long fxTicks = (long)Math.max(1, this.cfgFxRefreshMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::refreshFxRates, fxTicks, fxTicks);
        long resourceDriftTicks = (long)Math.max(1, this.cfgResourcePriceDriftIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::driftResourcePrices, resourceDriftTicks, resourceDriftTicks);
        long reportTicks = Math.max(1L, this.cfgReportIntervalDays) * 24L * 60L * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::sendPeriodicEconomyReport, reportTicks, reportTicks);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::collectDueInstallments, 36000L, 36000L);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::checkStorageRent, 36000L, 36000L);
        this.ensureMerchantDealsCurrent();
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::ensureMerchantDealsCurrent, 12000L, 12000L);
        this.ensureVipShopCurrent();
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::ensureVipShopCurrent, 12000L, 12000L);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::checkTreasureSpawn, 12000L, 12000L);
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::runLotteryDrawIfDue, 12000L, 12000L);
        long citizenDividendTicks = (long)Math.max(1, this.cfgCitizenDividendIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::payCitizenDividend, citizenDividendTicks, citizenDividendTicks);
        long welfareTicks = (long)Math.max(1, this.cfgWelfareIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::payWelfare, welfareTicks, welfareTicks);
        this.startWebDashboard();
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            try {
                new BankPlaceholderExpansion(this).register();
                this.getLogger().info("[PlaceholderAPI] \u30d7\u30ec\u30fc\u30b9\u30db\u30eb\u30c0\u30fc\u3092\u767b\u9332\u3057\u307e\u3057\u305f\u3002");
            }
            catch (Throwable ex) {
                this.getLogger().warning("[PlaceholderAPI] \u767b\u9332\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + ex.getMessage());
            }
        }
        this.getServer().getServicesManager().register(MinecraftBankAPI.class, (Object)this, (Plugin)this, ServicePriority.Normal);
        this.getLogger().info("[\u516c\u958bAPI] MinecraftBankAPI \u3092ServicesManager\u306b\u767b\u9332\u3057\u307e\u3057\u305f\u3002");
        this.getLogger().info("\u3010\u8d85\u7d76\u5f37\u5316\u7248\u3011BtoB\u5378\u58f2\u5e02\u5834\u62e1\u5145\u30fb\u5168\u6a5f\u80fd\u5b8c\u5168\u7a3c\u50cd\u30b7\u30b9\u30c6\u30e0\u304c\u8d77\u52d5\u3057\u307e\u3057\u305f\uff01");
    }

    private void loadConfigValues() {
        FileConfiguration c = this.getConfig();
        c.addDefault("economy.bank-establish-cost", (Object)this.cfgBankEstablishCost);
        c.addDefault("economy.deposit-step", (Object)this.cfgDepositStep);
        c.addDefault("economy.fixed-deposit-amount", (Object)this.cfgFixedDepositAmount);
        c.addDefault("economy.fixed-deposit-rate", (Object)this.cfgFixedDepositRate);
        c.addDefault("economy.fixed-deposit-duration-seconds", (Object)(this.cfgFixedDepositDurationMs / 1000L));
        c.addDefault("economy.gov-loan-amount", (Object)this.cfgGovLoanAmount);
        c.addDefault("economy.gov-loan-duration-minutes", (Object)(this.cfgGovLoanDurationMs / 60000L));
        c.addDefault("economy.gov-loan-penalty-cap-multiplier", (Object)this.cfgGovLoanPenaltyCapMultiplier);
        c.addDefault("economy.quest-radius", (Object)this.cfgQuestRadius);
        c.addDefault("economy.quest-cooldown-minutes", (Object)(this.cfgQuestCooldownMs / 60000L));
        c.addDefault("economy.quest-max-per-player", (Object)this.cfgQuestMaxPerPlayer);
        c.addDefault("economy.quest-team-max-size", (Object)this.cfgQuestTeamMaxSize);
        c.addDefault("economy.quest-system-min-available", (Object)this.cfgQuestSystemMinAvailable);
        c.addDefault("economy.quest-system-generate-count", (Object)this.cfgQuestSystemGenerateCount);
        c.addDefault("economy.quest-system-reward-min", (Object)this.cfgQuestSystemRewardMin);
        c.addDefault("economy.quest-system-reward-max", (Object)this.cfgQuestSystemRewardMax);
        c.addDefault("economy.quest-system-spawn-radius", (Object)this.cfgQuestSystemSpawnRadius);
        c.addDefault("economy.quest-system-check-interval-minutes", (Object)this.cfgQuestSystemCheckIntervalMinutes);
        c.addDefault("economy.world-stock-cache-seconds", (Object)this.cfgWorldStockCacheSeconds);
        c.addDefault("economy.world-stock-fee-rate", (Object)this.cfgWorldStockFeeRate);
        c.addDefault("economy.world-stock-dividend-annual-rate", (Object)this.cfgWorldStockDividendAnnualRate);
        c.addDefault("economy.world-stock-dividend-interval-hours", (Object)this.cfgWorldStockDividendIntervalHours);
        c.addDefault("economy.world-stock-max-trades-per-day", (Object)this.cfgWorldStockMaxTradesPerDay);
        c.addDefault("economy.world-stock-max-amount-per-day", (Object)this.cfgWorldStockMaxAmountPerDay);
        c.addDefault("economy.world-stock-max-profit-per-day", (Object)this.cfgWorldStockMaxProfitPerDay);
        c.addDefault("economy.world-stock-trade-cooldown-seconds", (Object)this.cfgWorldStockTradeCooldownSeconds);
        c.addDefault("economy.world-stock-max-bulk-qty", (Object)this.cfgWorldStockMaxBulkQty);
        c.addDefault("economy.resource-price-impact-rate", (Object)this.cfgResourcePriceImpactRate);
        c.addDefault("economy.resource-price-floor-percent", (Object)this.cfgResourcePriceFloorPercent);
        c.addDefault("economy.resource-price-ceiling-percent", (Object)this.cfgResourcePriceCeilingPercent);
        c.addDefault("economy.resource-personal-impact-rate", (Object)this.cfgResourcePersonalImpactRate);
        c.addDefault("economy.resource-personal-ceiling-percent", (Object)this.cfgResourcePersonalCeilingPercent);
        c.addDefault("economy.resource-price-reversion-rate", (Object)this.cfgResourcePriceReversionRate);
        c.addDefault("economy.resource-price-drift-interval-minutes", (Object)this.cfgResourcePriceDriftIntervalMinutes);
        c.addDefault("economy.merchant-refresh-hours", (Object)this.cfgMerchantRefreshHours);
        c.addDefault("economy.merchant-deal-count", (Object)this.cfgMerchantDealCount);
        c.addDefault("economy.merchant-min-discount-percent", (Object)this.cfgMerchantMinDiscountPercent);
        c.addDefault("economy.merchant-max-discount-percent", (Object)this.cfgMerchantMaxDiscountPercent);
        c.addDefault("economy.merchant-min-stock", (Object)this.cfgMerchantMinStock);
        c.addDefault("economy.merchant-max-stock", (Object)this.cfgMerchantMaxStock);
        c.addDefault("economy.merchant-spawn-radius", (Object)this.cfgMerchantSpawnRadius);
        c.addDefault("economy.vip-silver-score", (Object)this.cfgVipSilverScore);
        c.addDefault("economy.vip-gold-score", (Object)this.cfgVipGoldScore);
        c.addDefault("economy.vip-platinum-score", (Object)this.cfgVipPlatinumScore);
        c.addDefault("economy.vip-fee-discount-silver", (Object)this.cfgVipFeeDiscountSilver);
        c.addDefault("economy.vip-fee-discount-gold", (Object)this.cfgVipFeeDiscountGold);
        c.addDefault("economy.vip-fee-discount-platinum", (Object)this.cfgVipFeeDiscountPlatinum);
        c.addDefault("economy.vip-stipend-enabled", (Object)this.cfgVipStipendEnabled);
        c.addDefault("economy.vip-stipend-silver", (Object)this.cfgVipStipendSilver);
        c.addDefault("economy.vip-stipend-gold", (Object)this.cfgVipStipendGold);
        c.addDefault("economy.vip-stipend-platinum", (Object)this.cfgVipStipendPlatinum);
        c.addDefault("economy.vip-trade-limit-mult-silver", (Object)this.cfgVipTradeLimitMultSilver);
        c.addDefault("economy.vip-trade-limit-mult-gold", (Object)this.cfgVipTradeLimitMultGold);
        c.addDefault("economy.vip-trade-limit-mult-platinum", (Object)this.cfgVipTradeLimitMultPlatinum);
        c.addDefault("economy.vip-shop-enabled", (Object)this.cfgVipShopEnabled);
        c.addDefault("economy.vip-shop-refresh-hours", (Object)this.cfgVipShopRefreshHours);
        c.addDefault("economy.vip-shop-deal-count", (Object)this.cfgVipShopDealCount);
        c.addDefault("economy.vip-shop-min-discount-percent", (Object)this.cfgVipShopMinDiscountPercent);
        c.addDefault("economy.vip-shop-max-discount-percent", (Object)this.cfgVipShopMaxDiscountPercent);
        c.addDefault("economy.vip-shop-min-stock", (Object)this.cfgVipShopMinStock);
        c.addDefault("economy.vip-shop-max-stock", (Object)this.cfgVipShopMaxStock);
        c.addDefault("economy.treasure-interval-hours", (Object)this.cfgTreasureIntervalHours);
        c.addDefault("economy.treasure-reward-min", (Object)this.cfgTreasureRewardMin);
        c.addDefault("economy.treasure-reward-max", (Object)this.cfgTreasureRewardMax);
        c.addDefault("economy.treasure-spawn-radius", (Object)this.cfgTreasureSpawnRadius);
        c.addDefault("economy.installment-enabled", (Object)this.cfgInstallmentEnabled);
        c.addDefault("economy.installment-min-credit-score", (Object)this.cfgInstallmentMinCreditScore);
        c.addDefault("economy.installment-count", (Object)this.cfgInstallmentCount);
        c.addDefault("economy.installment-interval-hours", (Object)this.cfgInstallmentIntervalHours);
        c.addDefault("economy.installment-fee-rate", (Object)this.cfgInstallmentFeeRate);
        c.addDefault("economy.installment-credit-limit-multiplier", (Object)this.cfgInstallmentCreditLimitMultiplier);
        c.addDefault("economy.installment-missed-payment-penalty", (Object)this.cfgInstallmentMissedPaymentPenalty);
        c.addDefault("system.broadcast-news-default", (Object)this.cfgBroadcastNewsDefault);
        c.addDefault("system.autosave-interval-minutes", (Object)this.cfgAutosaveIntervalMinutes);
        c.addDefault("system.backup-interval-minutes", (Object)this.cfgBackupIntervalMinutes);
        c.addDefault("system.backup-retention-count", (Object)this.cfgBackupRetentionCount);
        c.addDefault("system.selfcheck-report-interval-minutes", (Object)this.cfgSelfCheckReportIntervalMinutes);
        c.addDefault("webdashboard.enabled", (Object)this.cfgWebDashboardEnabled);
        c.addDefault("webdashboard.port", (Object)this.cfgWebDashboardPort);
        c.addDefault("webdashboard.export-interval-minutes", (Object)this.cfgWebDashboardExportIntervalMinutes);
        c.addDefault("webdashboard.public-url", (Object)this.cfgWebDashboardPublicUrl);
        c.addDefault("economy.auction-min-increment", (Object)this.cfgAuctionMinIncrement);
        c.addDefault("economy.auction-fee-rate", (Object)this.cfgAuctionFeeRate);
        c.addDefault("economy.auction-duration-minutes", (Object)(this.cfgAuctionDurationMs / 60000L));
        c.addDefault("economy.auction-max-listings-per-player", (Object)this.cfgAuctionMaxListingsPerPlayer);
        c.addDefault("economy.collateral-ltv", (Object)this.cfgCollateralLtv);
        c.addDefault("economy.collateral-interest", (Object)this.cfgCollateralInterest);
        c.addDefault("economy.collateral-duration-minutes", (Object)(this.cfgCollateralDurationMs / 60000L));
        c.addDefault("economy.insurance-premium", (Object)this.cfgInsurancePremium);
        c.addDefault("economy.insurance-payout", (Object)this.cfgInsurancePayout);
        c.addDefault("economy.insurance-duration-minutes", (Object)(this.cfgInsuranceDurationMs / 60000L));
        c.addDefault("economy.insurance-claim-cooldown-minutes", (Object)(this.cfgInsuranceClaimCooldownMs / 60000L));
        c.addDefault("economy.report-interval-days", (Object)this.cfgReportIntervalDays);
        c.addDefault("economy.donation-score-cap-per-day", (Object)this.cfgDonationScoreCapPerDay);
        c.addDefault("system.discord-bot-token", (Object)this.cfgDiscordBotToken);
        c.addDefault("system.discord-channel-id", (Object)this.cfgDiscordChannelId);
        c.addDefault("economy.random-event-chance", (Object)this.cfgRandomEventChance);
        c.addDefault("economy.random-event-interval-minutes", (Object)this.cfgRandomEventIntervalMinutes);
        c.addDefault("economy.lottery-ticket-price", (Object)this.cfgLotteryTicketPrice);
        c.addDefault("economy.lottery-draw-interval-hours", (Object)this.cfgLotteryDrawIntervalHours);
        c.addDefault("economy.lottery-payout-rate", (Object)this.cfgLotteryPayoutRate);
        c.addDefault("economy.citizen-dividend-enabled", (Object)this.cfgCitizenDividendEnabled);
        c.addDefault("economy.citizen-dividend-amount", (Object)this.cfgCitizenDividendAmount);
        c.addDefault("economy.citizen-dividend-min-treasury", (Object)this.cfgCitizenDividendMinTreasury);
        c.addDefault("economy.citizen-dividend-interval-minutes", (Object)this.cfgCitizenDividendIntervalMinutes);
        c.addDefault("economy.lottery-jackpot-boost-enabled", (Object)this.cfgLotteryJackpotBoostEnabled);
        c.addDefault("economy.lottery-jackpot-boost-threshold", (Object)this.cfgLotteryJackpotBoostThreshold);
        c.addDefault("economy.lottery-jackpot-boost-amount", (Object)this.cfgLotteryJackpotBoostAmount);
        c.addDefault("economy.welfare-enabled", (Object)this.cfgWelfareEnabled);
        c.addDefault("economy.welfare-threshold", (Object)this.cfgWelfareThreshold);
        c.addDefault("economy.welfare-amount", (Object)this.cfgWelfareAmount);
        c.addDefault("economy.welfare-interval-minutes", (Object)this.cfgWelfareIntervalMinutes);
        c.addDefault("economy.welfare-max-per-day", (Object)this.cfgWelfareMaxPerDay);
        c.addDefault("economy.storage-size", (Object)this.cfgStorageSize);
        c.addDefault("economy.storage-rent-amount", (Object)this.cfgStorageRentAmount);
        c.addDefault("economy.storage-rent-interval-hours", (Object)this.cfgStorageRentIntervalHours);
        c.addDefault("economy.group-account-create-cost", (Object)this.cfgGroupAccountCreateCost);
        c.addDefault("economy.group-account-max-members", (Object)this.cfgGroupAccountMaxMembers);
        c.addDefault("economy.fund-create-cost", (Object)this.cfgFundCreateCost);
        c.addDefault("economy.fund-max-contributors", (Object)this.cfgFundMaxContributors);
        c.addDefault("economy.fx-currencies", this.cfgFxCurrencies);
        c.addDefault("economy.fx-refresh-minutes", (Object)this.cfgFxRefreshMinutes);
        c.addDefault("economy.currency-country", (Object)"");
        c.addDefault("economy.currency-unit", (Object)this.cfgCurrencyUnit);
        c.addDefault("economy.custom-money", (Object)"");
        c.addDefault("economy.fx-fallback-rate.USD", (Object)150.0);
        c.addDefault("economy.fx-fallback-rate.GBP", (Object)190.0);
        c.addDefault("economy.fx-fallback-rate.EUR", (Object)165.0);
        c.addDefault("economy.fx-fallback-rate.HKD", (Object)19.0);
        c.addDefault("economy.fx-fallback-rate.AUD", (Object)100.0);
        c.addDefault("economy.fx-fallback-rate.CAD", (Object)110.0);
        c.addDefault("economy.fx-fallback-rate-default", (Object)this.cfgFxFallbackRateDefault);
        c.options().copyDefaults(true);
        c.setComments("economy.currency-country", List.of("Leave this blank to auto-detect the currency from the server's own location (IP-based, checked once on startup).", "Or type an English country name yourself to force it (e.g. \"Japan\", \"United States\", \"Germany\").", "If auto-detection fails and this is blank, 'currency-unit' below is used instead."));
        c.setComments("economy.currency-unit", List.of("Manual override for the currency symbol/suffix shown after every amount (e.g. \"$\", \"USD\", \"\u5186\").", "Only used when 'currency-country' above is blank or not recognised."));
        c.setComments("economy.custom-money", List.of("Optional custom currency. Just fill this in to enable it - leave blank to disable.", "Format: 1<symbol>=<amount>  (amount is in the base currency set above)", "Example: \"1V=10\" means 1V is worth 10 of the base currency, and all balances/prices will display in V."));
        this.saveConfig();
        this.cfgBankEstablishCost = c.getDouble("economy.bank-establish-cost", this.cfgBankEstablishCost);
        this.cfgDepositStep = c.getDouble("economy.deposit-step", this.cfgDepositStep);
        this.cfgFixedDepositAmount = c.getDouble("economy.fixed-deposit-amount", this.cfgFixedDepositAmount);
        this.cfgFixedDepositRate = c.getDouble("economy.fixed-deposit-rate", this.cfgFixedDepositRate);
        this.cfgFixedDepositDurationMs = c.getLong("economy.fixed-deposit-duration-seconds", this.cfgFixedDepositDurationMs / 1000L) * 1000L;
        this.cfgGovLoanAmount = c.getDouble("economy.gov-loan-amount", this.cfgGovLoanAmount);
        this.cfgGovLoanDurationMs = c.getLong("economy.gov-loan-duration-minutes", this.cfgGovLoanDurationMs / 60000L) * 60000L;
        this.cfgGovLoanPenaltyCapMultiplier = c.getDouble("economy.gov-loan-penalty-cap-multiplier", this.cfgGovLoanPenaltyCapMultiplier);
        this.cfgQuestRadius = c.getDouble("economy.quest-radius", this.cfgQuestRadius);
        this.cfgQuestCooldownMs = c.getLong("economy.quest-cooldown-minutes", this.cfgQuestCooldownMs / 60000L) * 60000L;
        this.cfgQuestMaxPerPlayer = c.getInt("economy.quest-max-per-player", this.cfgQuestMaxPerPlayer);
        this.cfgQuestTeamMaxSize = c.getInt("economy.quest-team-max-size", this.cfgQuestTeamMaxSize);
        this.cfgQuestSystemMinAvailable = c.getInt("economy.quest-system-min-available", this.cfgQuestSystemMinAvailable);
        this.cfgQuestSystemGenerateCount = c.getInt("economy.quest-system-generate-count", this.cfgQuestSystemGenerateCount);
        this.cfgQuestSystemRewardMin = c.getDouble("economy.quest-system-reward-min", this.cfgQuestSystemRewardMin);
        this.cfgQuestSystemRewardMax = c.getDouble("economy.quest-system-reward-max", this.cfgQuestSystemRewardMax);
        this.cfgQuestSystemSpawnRadius = c.getDouble("economy.quest-system-spawn-radius", this.cfgQuestSystemSpawnRadius);
        this.cfgQuestSystemCheckIntervalMinutes = c.getInt("economy.quest-system-check-interval-minutes", this.cfgQuestSystemCheckIntervalMinutes);
        this.cfgWorldStockCacheSeconds = c.getDouble("economy.world-stock-cache-seconds", this.cfgWorldStockCacheSeconds);
        this.cfgWorldStockFeeRate = c.getDouble("economy.world-stock-fee-rate", this.cfgWorldStockFeeRate);
        this.cfgWorldStockDividendAnnualRate = c.getDouble("economy.world-stock-dividend-annual-rate", this.cfgWorldStockDividendAnnualRate);
        this.cfgWorldStockDividendIntervalHours = c.getInt("economy.world-stock-dividend-interval-hours", this.cfgWorldStockDividendIntervalHours);
        this.cfgWorldStockMaxTradesPerDay = c.getInt("economy.world-stock-max-trades-per-day", this.cfgWorldStockMaxTradesPerDay);
        this.cfgWorldStockMaxAmountPerDay = c.getDouble("economy.world-stock-max-amount-per-day", this.cfgWorldStockMaxAmountPerDay);
        this.cfgWorldStockMaxProfitPerDay = c.getDouble("economy.world-stock-max-profit-per-day", this.cfgWorldStockMaxProfitPerDay);
        this.cfgWorldStockTradeCooldownSeconds = c.getInt("economy.world-stock-trade-cooldown-seconds", this.cfgWorldStockTradeCooldownSeconds);
        this.cfgWorldStockMaxBulkQty = c.getInt("economy.world-stock-max-bulk-qty", this.cfgWorldStockMaxBulkQty);
        String cfgCountryInput = c.getString("economy.currency-country", "");
        String effectiveCountry = cfgCountryInput.isBlank() ? this.detectServerCountry() : cfgCountryInput;
        String resolvedCountryCurrency = this.resolveCurrencyByCountry(effectiveCountry);
        String string = this.cfgCurrencyUnit = resolvedCountryCurrency != null ? resolvedCountryCurrency : c.getString("economy.currency-unit", this.cfgCurrencyUnit);
        if (cfgCountryInput.isBlank() && resolvedCountryCurrency != null) {
            this.getLogger().info("[Economy] \u30b5\u30fc\u30d0\u30fc\u306e\u6240\u5728\u5730\u3092\u81ea\u52d5\u5224\u5b9a\u3057\u307e\u3057\u305f: " + effectiveCountry + " \u2192 \u901a\u8ca8\u8868\u8a18: " + resolvedCountryCurrency);
        }
        this.parseCustomMoney(c.getString("economy.custom-money", ""));
        List fxList = c.getStringList("economy.fx-currencies");
        this.cfgFxCurrencies = fxList.isEmpty() ? new ArrayList<String>(List.of("USD", "GBP", "EUR", "HKD", "AUD", "CAD")) : new ArrayList(fxList);
        this.cfgFxRefreshMinutes = c.getInt("economy.fx-refresh-minutes", this.cfgFxRefreshMinutes);
        this.cfgFxFallbackRateDefault = c.getDouble("economy.fx-fallback-rate-default", this.cfgFxFallbackRateDefault);
        this.cfgFxFallbackRates.clear();
        ConfigurationSection fxSec = c.getConfigurationSection("economy.fx-fallback-rate");
        if (fxSec != null) {
            for (String cur : fxSec.getKeys(false)) {
                this.cfgFxFallbackRates.put(cur.toUpperCase(), fxSec.getDouble(cur));
            }
        }
        if (this.cfgFxFallbackRates.isEmpty()) {
            this.cfgFxFallbackRates.put("USD", 150.0);
            this.cfgFxFallbackRates.put("GBP", 190.0);
            this.cfgFxFallbackRates.put("EUR", 165.0);
            this.cfgFxFallbackRates.put("HKD", 19.0);
            this.cfgFxFallbackRates.put("AUD", 100.0);
            this.cfgFxFallbackRates.put("CAD", 110.0);
        }
        this.cfgResourcePriceImpactRate = c.getDouble("economy.resource-price-impact-rate", this.cfgResourcePriceImpactRate);
        this.cfgResourcePriceFloorPercent = c.getDouble("economy.resource-price-floor-percent", this.cfgResourcePriceFloorPercent);
        this.cfgResourcePriceCeilingPercent = c.getDouble("economy.resource-price-ceiling-percent", this.cfgResourcePriceCeilingPercent);
        this.cfgResourcePersonalImpactRate = c.getDouble("economy.resource-personal-impact-rate", this.cfgResourcePersonalImpactRate);
        this.cfgResourcePersonalCeilingPercent = c.getDouble("economy.resource-personal-ceiling-percent", this.cfgResourcePersonalCeilingPercent);
        this.cfgResourcePriceReversionRate = c.getDouble("economy.resource-price-reversion-rate", this.cfgResourcePriceReversionRate);
        this.cfgResourcePriceDriftIntervalMinutes = c.getInt("economy.resource-price-drift-interval-minutes", this.cfgResourcePriceDriftIntervalMinutes);
        this.cfgMerchantRefreshHours = c.getInt("economy.merchant-refresh-hours", this.cfgMerchantRefreshHours);
        this.cfgMerchantDealCount = c.getInt("economy.merchant-deal-count", this.cfgMerchantDealCount);
        this.cfgMerchantMinDiscountPercent = c.getDouble("economy.merchant-min-discount-percent", this.cfgMerchantMinDiscountPercent);
        this.cfgMerchantMaxDiscountPercent = c.getDouble("economy.merchant-max-discount-percent", this.cfgMerchantMaxDiscountPercent);
        this.cfgMerchantMinStock = c.getInt("economy.merchant-min-stock", this.cfgMerchantMinStock);
        this.cfgMerchantMaxStock = c.getInt("economy.merchant-max-stock", this.cfgMerchantMaxStock);
        this.cfgMerchantSpawnRadius = c.getDouble("economy.merchant-spawn-radius", this.cfgMerchantSpawnRadius);
        this.cfgVipSilverScore = c.getInt("economy.vip-silver-score", this.cfgVipSilverScore);
        this.cfgVipGoldScore = c.getInt("economy.vip-gold-score", this.cfgVipGoldScore);
        this.cfgVipPlatinumScore = c.getInt("economy.vip-platinum-score", this.cfgVipPlatinumScore);
        this.cfgVipFeeDiscountSilver = c.getDouble("economy.vip-fee-discount-silver", this.cfgVipFeeDiscountSilver);
        this.cfgVipFeeDiscountGold = c.getDouble("economy.vip-fee-discount-gold", this.cfgVipFeeDiscountGold);
        this.cfgVipFeeDiscountPlatinum = c.getDouble("economy.vip-fee-discount-platinum", this.cfgVipFeeDiscountPlatinum);
        this.cfgVipStipendEnabled = c.getBoolean("economy.vip-stipend-enabled", this.cfgVipStipendEnabled);
        this.cfgVipStipendSilver = c.getDouble("economy.vip-stipend-silver", this.cfgVipStipendSilver);
        this.cfgVipStipendGold = c.getDouble("economy.vip-stipend-gold", this.cfgVipStipendGold);
        this.cfgVipStipendPlatinum = c.getDouble("economy.vip-stipend-platinum", this.cfgVipStipendPlatinum);
        this.cfgVipTradeLimitMultSilver = c.getDouble("economy.vip-trade-limit-mult-silver", this.cfgVipTradeLimitMultSilver);
        this.cfgVipTradeLimitMultGold = c.getDouble("economy.vip-trade-limit-mult-gold", this.cfgVipTradeLimitMultGold);
        this.cfgVipTradeLimitMultPlatinum = c.getDouble("economy.vip-trade-limit-mult-platinum", this.cfgVipTradeLimitMultPlatinum);
        this.cfgVipShopEnabled = c.getBoolean("economy.vip-shop-enabled", this.cfgVipShopEnabled);
        this.cfgVipShopRefreshHours = c.getInt("economy.vip-shop-refresh-hours", this.cfgVipShopRefreshHours);
        this.cfgVipShopDealCount = c.getInt("economy.vip-shop-deal-count", this.cfgVipShopDealCount);
        this.cfgVipShopMinDiscountPercent = c.getDouble("economy.vip-shop-min-discount-percent", this.cfgVipShopMinDiscountPercent);
        this.cfgVipShopMaxDiscountPercent = c.getDouble("economy.vip-shop-max-discount-percent", this.cfgVipShopMaxDiscountPercent);
        this.cfgVipShopMinStock = c.getInt("economy.vip-shop-min-stock", this.cfgVipShopMinStock);
        this.cfgVipShopMaxStock = c.getInt("economy.vip-shop-max-stock", this.cfgVipShopMaxStock);
        this.cfgTreasureIntervalHours = c.getInt("economy.treasure-interval-hours", this.cfgTreasureIntervalHours);
        this.cfgTreasureRewardMin = c.getDouble("economy.treasure-reward-min", this.cfgTreasureRewardMin);
        this.cfgTreasureRewardMax = c.getDouble("economy.treasure-reward-max", this.cfgTreasureRewardMax);
        this.cfgTreasureSpawnRadius = c.getDouble("economy.treasure-spawn-radius", this.cfgTreasureSpawnRadius);
        this.cfgInstallmentEnabled = c.getBoolean("economy.installment-enabled", this.cfgInstallmentEnabled);
        this.cfgInstallmentMinCreditScore = c.getInt("economy.installment-min-credit-score", this.cfgInstallmentMinCreditScore);
        this.cfgInstallmentCount = c.getInt("economy.installment-count", this.cfgInstallmentCount);
        this.cfgInstallmentIntervalHours = c.getInt("economy.installment-interval-hours", this.cfgInstallmentIntervalHours);
        this.cfgInstallmentFeeRate = c.getDouble("economy.installment-fee-rate", this.cfgInstallmentFeeRate);
        this.cfgInstallmentCreditLimitMultiplier = c.getDouble("economy.installment-credit-limit-multiplier", this.cfgInstallmentCreditLimitMultiplier);
        this.cfgInstallmentMissedPaymentPenalty = c.getInt("economy.installment-missed-payment-penalty", this.cfgInstallmentMissedPaymentPenalty);
        this.cfgBroadcastNewsDefault = c.getBoolean("system.broadcast-news-default", this.cfgBroadcastNewsDefault);
        this.cfgAutosaveIntervalMinutes = c.getInt("system.autosave-interval-minutes", this.cfgAutosaveIntervalMinutes);
        this.cfgBackupIntervalMinutes = c.getInt("system.backup-interval-minutes", this.cfgBackupIntervalMinutes);
        this.cfgBackupRetentionCount = c.getInt("system.backup-retention-count", this.cfgBackupRetentionCount);
        this.cfgSelfCheckReportIntervalMinutes = c.getInt("system.selfcheck-report-interval-minutes", this.cfgSelfCheckReportIntervalMinutes);
        this.cfgWebDashboardEnabled = c.getBoolean("webdashboard.enabled", this.cfgWebDashboardEnabled);
        this.cfgWebDashboardPort = c.getInt("webdashboard.port", this.cfgWebDashboardPort);
        this.cfgWebDashboardExportIntervalMinutes = c.getLong("webdashboard.export-interval-minutes", this.cfgWebDashboardExportIntervalMinutes);
        this.cfgWebDashboardPublicUrl = c.getString("webdashboard.public-url", this.cfgWebDashboardPublicUrl);
        this.cfgAuctionMinIncrement = c.getDouble("economy.auction-min-increment", this.cfgAuctionMinIncrement);
        this.cfgAuctionFeeRate = c.getDouble("economy.auction-fee-rate", this.cfgAuctionFeeRate);
        this.cfgAuctionDurationMs = c.getLong("economy.auction-duration-minutes", this.cfgAuctionDurationMs / 60000L) * 60000L;
        this.cfgAuctionMaxListingsPerPlayer = c.getInt("economy.auction-max-listings-per-player", this.cfgAuctionMaxListingsPerPlayer);
        this.cfgCollateralLtv = c.getDouble("economy.collateral-ltv", this.cfgCollateralLtv);
        this.cfgCollateralInterest = c.getDouble("economy.collateral-interest", this.cfgCollateralInterest);
        this.cfgCollateralDurationMs = c.getLong("economy.collateral-duration-minutes", this.cfgCollateralDurationMs / 60000L) * 60000L;
        this.cfgInsurancePremium = c.getDouble("economy.insurance-premium", this.cfgInsurancePremium);
        this.cfgInsurancePayout = c.getDouble("economy.insurance-payout", this.cfgInsurancePayout);
        this.cfgInsuranceDurationMs = c.getLong("economy.insurance-duration-minutes", this.cfgInsuranceDurationMs / 60000L) * 60000L;
        this.cfgInsuranceClaimCooldownMs = c.getLong("economy.insurance-claim-cooldown-minutes", this.cfgInsuranceClaimCooldownMs / 60000L) * 60000L;
        this.cfgReportIntervalDays = c.getLong("economy.report-interval-days", this.cfgReportIntervalDays);
        this.cfgDonationScoreCapPerDay = c.getInt("economy.donation-score-cap-per-day", this.cfgDonationScoreCapPerDay);
        this.cfgDiscordBotToken = c.getString("system.discord-bot-token", this.cfgDiscordBotToken);
        this.cfgDiscordChannelId = c.getString("system.discord-channel-id", this.cfgDiscordChannelId);
        this.cfgRandomEventChance = c.getDouble("economy.random-event-chance", this.cfgRandomEventChance);
        this.cfgRandomEventIntervalMinutes = c.getInt("economy.random-event-interval-minutes", this.cfgRandomEventIntervalMinutes);
        this.cfgLotteryTicketPrice = c.getDouble("economy.lottery-ticket-price", this.cfgLotteryTicketPrice);
        this.cfgLotteryDrawIntervalHours = c.getInt("economy.lottery-draw-interval-hours", this.cfgLotteryDrawIntervalHours);
        this.cfgLotteryPayoutRate = c.getDouble("economy.lottery-payout-rate", this.cfgLotteryPayoutRate);
        this.cfgCitizenDividendEnabled = c.getBoolean("economy.citizen-dividend-enabled", this.cfgCitizenDividendEnabled);
        this.cfgCitizenDividendAmount = c.getDouble("economy.citizen-dividend-amount", this.cfgCitizenDividendAmount);
        this.cfgCitizenDividendMinTreasury = c.getDouble("economy.citizen-dividend-min-treasury", this.cfgCitizenDividendMinTreasury);
        this.cfgCitizenDividendIntervalMinutes = c.getInt("economy.citizen-dividend-interval-minutes", this.cfgCitizenDividendIntervalMinutes);
        this.cfgLotteryJackpotBoostEnabled = c.getBoolean("economy.lottery-jackpot-boost-enabled", this.cfgLotteryJackpotBoostEnabled);
        this.cfgLotteryJackpotBoostThreshold = c.getDouble("economy.lottery-jackpot-boost-threshold", this.cfgLotteryJackpotBoostThreshold);
        this.cfgLotteryJackpotBoostAmount = c.getDouble("economy.lottery-jackpot-boost-amount", this.cfgLotteryJackpotBoostAmount);
        this.cfgWelfareEnabled = c.getBoolean("economy.welfare-enabled", this.cfgWelfareEnabled);
        this.cfgWelfareThreshold = c.getDouble("economy.welfare-threshold", this.cfgWelfareThreshold);
        this.cfgWelfareAmount = c.getDouble("economy.welfare-amount", this.cfgWelfareAmount);
        this.cfgWelfareIntervalMinutes = c.getInt("economy.welfare-interval-minutes", this.cfgWelfareIntervalMinutes);
        this.cfgWelfareMaxPerDay = c.getInt("economy.welfare-max-per-day", this.cfgWelfareMaxPerDay);
        this.cfgStorageSize = c.getInt("economy.storage-size", this.cfgStorageSize);
        this.cfgStorageRentAmount = c.getDouble("economy.storage-rent-amount", this.cfgStorageRentAmount);
        this.cfgStorageRentIntervalHours = c.getInt("economy.storage-rent-interval-hours", this.cfgStorageRentIntervalHours);
        this.cfgGroupAccountCreateCost = c.getDouble("economy.group-account-create-cost", this.cfgGroupAccountCreateCost);
        this.cfgGroupAccountMaxMembers = c.getInt("economy.group-account-max-members", this.cfgGroupAccountMaxMembers);
        this.cfgFundCreateCost = c.getDouble("economy.fund-create-cost", this.cfgFundCreateCost);
        this.cfgFundMaxContributors = c.getInt("economy.fund-max-contributors", this.cfgFundMaxContributors);
    }

    private void checkGovLoanDeadlines() {
        long now = System.currentTimeMillis();
        for (Map.Entry<UUID, Long> entry : new HashMap<UUID, Long>(this.govDebtDueTime).entrySet()) {
            UUID u = entry.getKey();
            long due = entry.getValue();
            if (!this.govDebt.containsKey(u) || this.govDebt.getOrDefault(u, 0.0) <= 0.0) {
                this.govDebtDueTime.remove(u);
                continue;
            }
            if (now <= due) continue;
            double debt = this.govDebt.getOrDefault(u, 0.0);
            double ceiling = this.getGovLoanCap(this.getScore(u)) * this.cfgGovLoanPenaltyCapMultiplier;
            double penalty = Math.max(0.0, Math.min(debt * 0.05, ceiling - debt));
            this.govDebt.put(u, debt + penalty);
            this.govDebtDueTime.put(u, now + this.cfgGovLoanDurationMs);
            this.addScore(u, -15);
            Player online = Bukkit.getPlayer((UUID)u);
            if (penalty > 0.0) {
                if (online != null && online.isOnline()) {
                    this.msgKey(online, "loan.gov-overdue-penalty", "amount", String.valueOf((long)penalty));
                }
                this.addLog(u, "\u56fd\u55b6\u30ed\u30fc\u30f3\u5ef6\u6ede\u91d1 +" + this.fmtCur(penalty));
                this.sendDiscordWebhook("\u26a0\ufe0f **" + Bukkit.getOfflinePlayer((UUID)u).getName() + "** \u306e\u56fd\u55b6\u30ed\u30fc\u30f3\u304c\u5ef6\u6ede\u3057\u3001" + (long)penalty + "\u5186 \u306e\u5ef6\u6ede\u91d1\u304c\u52a0\u7b97\u3055\u308c\u307e\u3057\u305f\u3002");
                continue;
            }
            if (online == null || !online.isOnline()) continue;
            this.msgKey(online, "loan.gov-overdue-ceiling", "amount", String.valueOf((long)ceiling));
        }
    }

    private void collectDueInstallments() {
        long now = System.currentTimeMillis();
        for (UUID owner : new ArrayList<UUID>(this.installmentPlans.keySet())) {
            List<InstallmentPlan> plans = this.installmentPlans.get(owner);
            if (plans == null) continue;
            OfflinePlayer offlineOwner = Bukkit.getOfflinePlayer((UUID)owner);
            Player online = Bukkit.getPlayer((UUID)owner);
            Iterator<InstallmentPlan> it = plans.iterator();
            while (it.hasNext()) {
                InstallmentPlan plan = it.next();
                if (plan.nextDueTime > now) continue;
                if (econ.getBalance(offlineOwner) >= plan.installmentAmount) {
                    econ.withdrawPlayer(offlineOwner, plan.installmentAmount);
                    --plan.installmentsRemaining;
                    this.addLog(owner, "\u5206\u5272\u6255\u3044: " + plan.description + " -" + this.fmtCurPrecise(plan.installmentAmount));
                    if (plan.installmentsRemaining <= 0) {
                        it.remove();
                        if (online == null || !online.isOnline()) continue;
                        this.msgKey(online, "installment.completed", "item", plan.description);
                        continue;
                    }
                    plan.nextDueTime = now + (long)this.cfgInstallmentIntervalHours * 3600000L;
                    if (online == null || !online.isOnline()) continue;
                    this.msgKey(online, "installment.paid", "item", plan.description, "amount", String.format("%.2f", plan.installmentAmount), "remaining", String.valueOf(plan.installmentsRemaining));
                    continue;
                }
                this.addScore(owner, -this.cfgInstallmentMissedPaymentPenalty);
                plan.nextDueTime = now + (long)this.cfgInstallmentIntervalHours * 3600000L;
                if (online == null || !online.isOnline()) continue;
                this.msgKey(online, "installment.missed", "item", plan.description, "amount", String.format("%.2f", plan.installmentAmount));
            }
            if (!plans.isEmpty()) continue;
            this.installmentPlans.remove(owner);
        }
    }

    private void checkCollateralDeadlines() {
        long now = System.currentTimeMillis();
        for (Map.Entry<UUID, Long> entry : new HashMap<UUID, Long>(this.collateralDueTime).entrySet()) {
            String itemName;
            UUID u = entry.getKey();
            long due = entry.getValue();
            if (!this.collateralItem.containsKey(u)) {
                this.collateralDueTime.remove(u);
                continue;
            }
            if (now <= due) continue;
            ItemStack forfeited = this.collateralItem.remove(u);
            this.collateralLoanAmount.remove(u);
            this.collateralDueTime.remove(u);
            this.collateralLender.remove(u);
            this.addScore(u, -30);
            Player online = Bukkit.getPlayer((UUID)u);
            String string = itemName = forfeited != null ? forfeited.getType().name() : "\u30a2\u30a4\u30c6\u30e0";
            if (online != null && online.isOnline()) {
                this.msgKey(online, "collateral.seized", "item", itemName);
            }
            this.addLog(u, "\u62c5\u4fdd\u6ca1\u53ce: " + itemName);
            this.sendDiscordWebhook("\u26a0\ufe0f **" + Bukkit.getOfflinePlayer((UUID)u).getName() + "** \u306e\u62c5\u4fdd " + itemName + " \u304c\u8fd4\u6e08\u671f\u9650\u8d85\u904e\u306b\u3088\u308a\u6ca1\u53ce\u3055\u308c\u307e\u3057\u305f\u3002");
        }
    }

    private void checkAuctionEnd() {
        long now = System.currentTimeMillis();
        for (Map.Entry<UUID, Long> entry : new HashMap<UUID, Long>(this.auctionEndTime).entrySet()) {
            UUID auctionId = entry.getKey();
            long end = entry.getValue();
            if (now < end) continue;
            UUID seller = this.auctionSeller.get(auctionId);
            ItemStack item = this.auctionItem.get(auctionId);
            if (seller == null || item == null) {
                this.clearAuctionEntry(auctionId);
                continue;
            }
            UUID winner = this.auctionBidder.get(auctionId);
            if (winner == null) {
                this.addPendingItem(seller, item);
                Player sellerOnline = Bukkit.getPlayer((UUID)seller);
                if (sellerOnline != null && sellerOnline.isOnline()) {
                    this.msgKey(sellerOnline, "auction.no-bid-returned", "item", item.getType().name());
                }
                this.addLog(seller, "\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u6d41\u672d: " + item.getType().name());
                this.clearAuctionEntry(auctionId);
                continue;
            }
            double finalBid = this.auctionBid.getOrDefault(auctionId, 0.0);
            this.finalizeAuctionSale(auctionId, seller, winner, item, finalBid);
        }
    }

    private void placeAuctionBid(OfflinePlayer p, UUID auctionId, double amount) {
        double minNext;
        Player pl;
        UUID u = p.getUniqueId();
        Player online = p instanceof Player && (pl = (Player)p).isOnline() ? pl : null;
        double pocket = econ.getBalance(p);
        UUID seller = this.auctionSeller.get(auctionId);
        ItemStack auctionedItem = this.auctionItem.get(auctionId);
        if (seller == null || auctionedItem == null) {
            if (online != null) {
                this.msgKey(online, "auction.listing-ended", new String[0]);
            }
            return;
        }
        if (seller.equals(u)) {
            if (online != null) {
                this.msgKey(online, "auction.cannot-bid-own", new String[0]);
            }
            return;
        }
        double currentBid = this.auctionBid.getOrDefault(auctionId, 0.0);
        UUID prevBidder = this.auctionBidder.get(auctionId);
        double d = minNext = prevBidder != null ? currentBid + this.cfgAuctionMinIncrement : currentBid;
        if (amount < minNext) {
            if (online != null) {
                this.msgKey(online, "auction.bid-too-low", "amount", String.valueOf((long)minNext));
            }
            return;
        }
        if (pocket < amount) {
            if (online != null) {
                this.msgKey(online, "common.insufficient-funds", "amount", String.valueOf((long)pocket));
            }
            return;
        }
        Double buyout = this.auctionBuyoutPrice.get(auctionId);
        if (buyout != null && amount >= buyout) {
            econ.withdrawPlayer(p, buyout.doubleValue());
            if (prevBidder != null) {
                econ.depositPlayer(Bukkit.getOfflinePlayer((UUID)prevBidder), currentBid);
                this.notifyAuctionOutbid(prevBidder, auctionedItem, currentBid);
            }
            if (online != null) {
                this.msgKey(online, "auction.buyout-purchased", new String[0]);
            }
            this.finalizeAuctionSale(auctionId, seller, u, auctionedItem, buyout);
            return;
        }
        econ.withdrawPlayer(p, amount);
        if (prevBidder != null) {
            econ.depositPlayer(Bukkit.getOfflinePlayer((UUID)prevBidder), currentBid);
            this.notifyAuctionOutbid(prevBidder, auctionedItem, currentBid);
        }
        this.auctionBid.put(auctionId, amount);
        this.auctionBidder.put(auctionId, u);
        this.addLog(u, "\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u5165\u672d: " + auctionedItem.getType().name() + " -" + this.fmtCur(amount) + (online == null ? "(Web/\u30aa\u30d5\u30e9\u30a4\u30f3)" : ""));
        if (online != null) {
            this.msgKey(online, "auction.bid-placed", "item", auctionedItem.getType().name(), "amount", String.valueOf((long)amount));
            online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            this.sendToast(online, "\u5165\u672d\u6210\u529f", auctionedItem.getType().name() + " \u306b " + (long)amount + "\u5186 \u3067\u5165\u672d");
        }
        this.sendDiscordWebhook("\ud83d\udcb7 **" + p.getName() + "** \u304c **" + Bukkit.getOfflinePlayer((UUID)seller).getName() + "** \u306e\u51fa\u54c1(" + auctionedItem.getType().name() + ")\u306b " + (long)amount + "\u5186 \u3067\u5165\u672d\u3057\u307e\u3057\u305f\u3002");
    }

    private void notifyAuctionOutbid(UUID prevBidder, ItemStack auctionedItem, double refundAmount) {
        Player prevOnline = Bukkit.getPlayer((UUID)prevBidder);
        if (prevOnline != null && prevOnline.isOnline()) {
            this.msgKey(prevOnline, "auction.outbid-refund", "item", auctionedItem.getType().name(), "amount", String.valueOf((long)refundAmount));
            this.sendToast(prevOnline, "\u5165\u672d\u66f4\u65b0", auctionedItem.getType().name() + " \u304c\u4ed6\u306e\u4eba\u306b\u5165\u672d\u3055\u308c\u307e\u3057\u305f");
            prevOnline.playSound(prevOnline.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 0.7f);
        } else {
            this.queueAuctionOfflineNotice(prevBidder, auctionedItem.getType().name(), refundAmount);
        }
    }

    private void finalizeAuctionSale(UUID auctionId, UUID seller, UUID winner, ItemStack item, double finalBid) {
        Player winnerOnline;
        double fee = finalBid * this.cfgAuctionFeeRate * this.vipFeeMultiplier(seller);
        double proceeds = finalBid - fee;
        this.weeklyTradeVolume += finalBid;
        this.auctionTotalSoldAmount.merge(seller, finalBid, Double::sum);
        this.addPendingItem(winner, item);
        econ.depositPlayer(Bukkit.getOfflinePlayer((UUID)seller), proceeds);
        Player sellerOnline = Bukkit.getPlayer((UUID)seller);
        if (sellerOnline != null && sellerOnline.isOnline()) {
            this.msgKey(sellerOnline, "auction.sold", "item", item.getType().name(), "amount", String.valueOf((long)finalBid), "fee", String.valueOf((long)fee), "net", String.valueOf((long)proceeds));
            this.sendToast(sellerOnline, "\u843d\u672d\u6210\u7acb", item.getType().name() + " / +" + this.fmtCur(proceeds));
        }
        if ((winnerOnline = Bukkit.getPlayer((UUID)winner)) != null && winnerOnline.isOnline()) {
            this.msgKey(winnerOnline, "auction.won", "item", item.getType().name(), "amount", String.valueOf((long)finalBid));
            this.sendToast(winnerOnline, "\u843d\u672d", item.getType().name() + " \u304c\u53d7\u53d6\u7bb1\u306b\u5c4a\u304d\u307e\u3057\u305f\uff01");
        }
        this.addLog(seller, "\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u843d\u672d\u6210\u7acb: " + item.getType().name() + " +" + this.fmtCur(proceeds));
        this.addLog(winner, "\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u843d\u672d: " + item.getType().name() + " -" + this.fmtCur(finalBid));
        this.sendDiscordWebhook("\ud83d\udd28 **" + Bukkit.getOfflinePlayer((UUID)winner).getName() + "** \u304c **" + Bukkit.getOfflinePlayer((UUID)seller).getName() + "** \u306e\u51fa\u54c1(" + item.getType().name() + ")\u3092 " + (long)finalBid + "\u5186 \u3067\u843d\u672d\u3057\u307e\u3057\u305f\u3002");
        this.broadcastNews("<gold><bold>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u3011</bold></gold> <white>" + Bukkit.getOfflinePlayer((UUID)seller).getName() + "</white>\u306e<white>" + item.getType().name() + "</white>\u304c<yellow>" + Bukkit.getOfflinePlayer((UUID)winner).getName() + "</yellow>\u306b\u3088\u308a<gold>" + (long)finalBid + "\u5186</gold>\u3060\uff01");
        this.clearAuctionEntry(auctionId);
    }

    private void clearAuctionEntry(UUID auctionId) {
        this.auctionSeller.remove(auctionId);
        this.auctionItem.remove(auctionId);
        this.auctionBid.remove(auctionId);
        this.auctionBidder.remove(auctionId);
        this.auctionEndTime.remove(auctionId);
        this.auctionBuyoutPrice.remove(auctionId);
    }

    private void addPendingItem(UUID u, ItemStack item) {
        List list = this.auctionPendingItems.computeIfAbsent(u, k -> new ArrayList());
        list.add(item);
    }

    private void deliverPendingAuctionItems(Player p) {
        UUID u = p.getUniqueId();
        List<ItemStack> items = this.auctionPendingItems.get(u);
        if (items != null && !items.isEmpty()) {
            ArrayList remaining = new ArrayList();
            int delivered = 0;
            for (ItemStack item : items) {
                HashMap overflow = p.getInventory().addItem(new ItemStack[]{item});
                if (overflow.isEmpty()) {
                    ++delivered;
                    continue;
                }
                remaining.addAll(overflow.values());
            }
            if (delivered > 0) {
                this.msgKey(p, "auction.items-received", "count", String.valueOf(delivered));
            }
            if (remaining.isEmpty()) {
                this.auctionPendingItems.remove(u);
            } else {
                this.auctionPendingItems.put(u, remaining);
                this.msgKey(p, "auction.items-inventory-full", "count", String.valueOf(remaining.size()));
            }
        }
    }

    private void queueAuctionOfflineNotice(UUID u, String itemTypeName, double amount) {
        List list = this.auctionOfflineNotices.computeIfAbsent(u, k -> new ArrayList());
        list.add(itemTypeName + ";" + (long)amount);
    }

    private void deliverAuctionOfflineNotices(Player p) {
        UUID u = p.getUniqueId();
        List<String> notices = this.auctionOfflineNotices.remove(u);
        if (notices == null || notices.isEmpty()) {
            return;
        }
        for (String notice : notices) {
            int sep = notice.indexOf(59);
            if (sep < 0) continue;
            String itemTypeName = notice.substring(0, sep);
            String amountStr = notice.substring(sep + 1);
            this.msgKey(p, "auction.outbid-refund", "item", itemTypeName, "amount", amountStr);
        }
    }

    private void queueLoanOfflineNotice(UUID lenderId, String borrowerName, double amount) {
        List list = this.loanOfflineNotices.computeIfAbsent(lenderId, k -> new ArrayList());
        list.add(borrowerName + ";" + (long)amount);
    }

    private void deliverLoanOfflineNotices(Player p) {
        UUID u = p.getUniqueId();
        List<String> notices = this.loanOfflineNotices.remove(u);
        if (notices == null || notices.isEmpty()) {
            return;
        }
        for (String notice : notices) {
            int sep = notice.indexOf(59);
            if (sep < 0) continue;
            String borrowerName = notice.substring(0, sep);
            String amountStr = notice.substring(sep + 1);
            this.msgKey(p, "loan.player-approved-notice", "player", borrowerName, "amount", amountStr);
        }
    }

    private double evaluateItemValue(ItemStack item) {
        if (item == null || item.getType() == Material.AIR) {
            return 0.0;
        }
        double total = (switch (item.getType()) {
            case Material.NETHERITE_INGOT -> 8000.0;
            case Material.NETHERITE_BLOCK -> 72000.0;
            case Material.DIAMOND -> 1000.0;
            case Material.DIAMOND_BLOCK -> 9000.0;
            case Material.EMERALD -> 500.0;
            case Material.EMERALD_BLOCK -> 4500.0;
            case Material.GOLD_INGOT -> 200.0;
            case Material.GOLD_BLOCK -> 1800.0;
            case Material.IRON_INGOT -> 50.0;
            case Material.TOTEM_OF_UNDYING -> 20000.0;
            case Material.ENCHANTED_GOLDEN_APPLE -> 15000.0;
            case Material.NETHER_STAR -> 25000.0;
            case Material.ELYTRA -> 15000.0;
            case Material.ENCHANTED_BOOK -> 0.0;
            default -> 0.0;
        }) * (double)item.getAmount();
        ItemMeta itemMeta = item.getItemMeta();
        if (itemMeta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta esm = (EnchantmentStorageMeta)itemMeta;
            for (Map.Entry entry : esm.getStoredEnchants().entrySet()) {
                total += (double)((Integer)entry.getValue()).intValue() * 800.0;
            }
        }
        if (item.getItemMeta() != null && item.getItemMeta().hasEnchants()) {
            for (Map.Entry ench : item.getItemMeta().getEnchants().entrySet()) {
                total += (double)((Integer)ench.getValue()).intValue() * 300.0;
            }
        }
        return total;
    }

    private void sendPeriodicEconomyReport() {
        StringBuilder report = new StringBuilder();
        report.append("<gold><bold>========== \ud83d\udcca \u7d4c\u6e08\u30ec\u30dd\u30fc\u30c8 (\u76f4\u8fd1").append(this.cfgReportIntervalDays).append("\u65e5\u9593) ==========</bold></gold>\n");
        report.append("<gray>\u7dcf\u53d6\u5f15\u984d(\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u30fb\u878d\u8cc7\u306e\u5408\u8a08):</gray> <green>").append((long)this.weeklyTradeVolume).append("\u5186</green>\n");
        report.append("<gray>\u56fd\u5eab\u6b8b\u9ad8:</gray> <gold>").append((long)this.treasury).append("\u5186</gold>");
        String text = report.toString();
        for (String line : text.split("\n")) {
            this.broadcastNews(line);
        }
        this.sendDiscordWebhook("\ud83d\udcca **\u9031\u6b21\u7d4c\u6e08\u30ec\u30dd\u30fc\u30c8**\n\u7dcf\u53d6\u5f15\u984d: " + (long)this.weeklyTradeVolume + "\u5186\n\u56fd\u5eab\u6b8b\u9ad8: " + this.fmtCur(this.treasury));
        this.weeklyTradeVolume = 0.0;
        this.saveData();
    }

    private boolean economyEventActive(String id) {
        return this.activeEconomyEvent.equals(id) && System.currentTimeMillis() < this.activeEconomyEventUntil;
    }

    private double economyMultiplier() {
        if (this.economyEventActive("\u9ec4\u91d1\u30e9\u30c3\u30b7\u30e5")) {
            return 1.1;
        }
        if (this.economyEventActive("\u51ac\u306e\u6642\u4ee3")) {
            return 0.9;
        }
        return 1.0;
    }

    private double effectiveWorldStockFeeRate() {
        return this.economyEventActive("\u624b\u6570\u6599\u9ad8\u9a30") ? this.cfgWorldStockFeeRate * 2.0 : this.cfgWorldStockFeeRate;
    }

    private double effectiveWorldStockFeeRate(UUID u) {
        return this.effectiveWorldStockFeeRate() * this.vipFeeMultiplier(u);
    }

    private void fireEconomyEvent(String type) {
        long duration = 600000L;
        if (type.equals("random")) {
            type = switch (this.economyRandom.nextInt(4)) {
                case 0 -> "\u9ec4\u91d1\u30e9\u30c3\u30b7\u30e5";
                case 1 -> "\u624b\u6570\u6599\u9ad8\u9a30";
                case 2 -> "\u30dc\u30fc\u30ca\u30b9\u652f\u7d66\u30c7\u30fc";
                default -> "\u51ac\u306e\u6642\u4ee3";
            };
        }
        this.activeEconomyEvent = type;
        this.activeEconomyEventUntil = System.currentTimeMillis() + duration;
        String text = switch (type) {
            case "\u9ec4\u91d1\u30e9\u30c3\u30b7\u30e5" -> this.getMsg("event.boom", new String[0]);
            case "\u624b\u6570\u6599\u9ad8\u9a30" -> this.getMsg("event.tax", new String[0]);
            case "\u30dc\u30fc\u30ca\u30b9\u652f\u7d66\u30c7\u30fc" -> {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    double bonus = 5000.0;
                    econ.depositPlayer((OfflinePlayer)online, bonus);
                    this.addLog(online.getUniqueId(), "\u7d4c\u6e08\u30a4\u30d9\u30f3\u30c8\u30dc\u30fc\u30ca\u30b9 +" + this.fmtCur(bonus));
                    this.sendToast(online, "\u30dc\u30fc\u30ca\u30b9\u652f\u7d66", "+5,000\u5186 \u304c\u652f\u7d66\u3055\u308c\u307e\u3057\u305f\uff01");
                }
                yield this.getMsg("event.bonus", new String[0]);
            }
            default -> this.getMsg("event.recession", new String[0]);
        };
        this.broadcastNews(text);
        this.sendDiscordWebhook("\ud83d\udce2 " + MiniMessage.miniMessage().stripTags(text));
        this.saveData();
    }

    private void tryRandomEconomyEvent() {
        if (System.currentTimeMillis() < this.activeEconomyEventUntil) {
            return;
        }
        if (this.economyRandom.nextDouble() <= this.cfgRandomEventChance) {
            this.fireEconomyEvent("random");
        }
    }

    private void updateAllScoreboards() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            this.updateScoreboard(p);
        }
    }

    private void updateScoreboard(Player p) {
        ScoreboardManager sm = Bukkit.getScoreboardManager();
        if (sm == null) {
            return;
        }
        Scoreboard board = sm.getNewScoreboard();
        Objective obj = board.registerNewObjective("keizai", Criteria.DUMMY, this.mm("<gold><bold>\u7d4c\u6e08\u30b9\u30c6\u30fc\u30bf\u30b9</bold></gold>"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        UUID u = p.getUniqueId();
        String[] lines = new String[]{"\u00a77\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500", "\u00a7e\u6240\u6301\u91d1 \u00a7f" + this.fmtCur(econ.getBalance((OfflinePlayer)p)), "\u00a7b\u53e3\u5ea7 \u00a7f" + this.fmtCur(this.personalBank.getOrDefault(u, 0.0)), "\u00a7d\u4fe1\u7528 \u00a7f" + this.getScore(u), "\u00a76\u56fd\u5eab \u00a7f" + this.fmtCur(this.treasury), "\u00a77\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500\u2500"};
        int score = lines.length;
        for (String line : lines) {
            obj.getScore(line).setScore(score--);
        }
        p.setScoreboard(board);
    }

    private void sendToast(Player p, String title, String message) {
        if (p == null || !p.isOnline()) {
            return;
        }
        p.sendActionBar(this.mm("<gold><bold>" + title + "</bold></gold> <gray>\u00bb</gray> <white>" + message + "</white>"));
        p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.8f, 1.15f);
    }

    private void openTutorialGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tTutorial);
        gui.setItem(4, this.createItem(Material.BOOK, "<gold><bold>\u7d4c\u6e08\u306e\u306f\u3058\u3081\u304b\u305f</bold></gold>", "<gray>\u307e\u305a\u306f\u500b\u4eba\u53e3\u5ea7\u3078\u9810\u91d1\u3057\u3066\u307f\u307e\u3057\u3087\u3046\u3002</gray>"));
        gui.setItem(10, this.createItem(Material.CHEST, "<green>1. \u500b\u4eba\u53e3\u5ea7</green>", "<gray>\u304a\u91d1\u3092\u5b89\u5168\u306b\u9810\u3051\u5165\u308c\u30fb\u5f15\u304d\u51fa\u305b\u307e\u3059\u3002</gray>"));
        gui.setItem(14, this.createItem(Material.ITEM_FRAME, "<yellow>2. \u30aa\u30fc\u30af\u30b7\u30e7\u30f3</yellow>", "<gray>\u30a4\u30f3\u30d9\u30f3\u30c8\u30ea\u304b\u3089\u597d\u304d\u306a\u30a2\u30a4\u30c6\u30e0\u3092\u51fa\u54c1\u3067\u304d\u307e\u3059\u3002</gray>"));
        gui.setItem(22, this.createItem(Material.LIME_DYE, "<green><bold>\u30c1\u30e5\u30fc\u30c8\u30ea\u30a2\u30eb\u5b8c\u4e86</bold></green>", "<gray>\u6b21\u56de\u304b\u3089\u81ea\u52d5\u8868\u793a\u3057\u307e\u305b\u3093\u3002</gray>"));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u9589\u3058\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void backupDatabase() {
        if (this.db == null) {
            return;
        }
        this.saveData();
        try {
            File srcFile = new File(this.getDataFolder(), "data.db");
            if (!srcFile.exists()) {
                return;
            }
            File backupsDir = new File(this.getDataFolder(), "backups");
            if (!backupsDir.exists()) {
                backupsDir.mkdirs();
            }
            String stamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
            File destFile = new File(backupsDir, "data-" + stamp + ".db");
            Files.copy(srcFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            this.getLogger().info("[\u81ea\u52d5\u30d0\u30c3\u30af\u30a2\u30c3\u30d7] " + destFile.getName() + " \u3092\u4f5c\u6210\u3057\u307e\u3057\u305f\u3002");
            this.pruneOldBackups(backupsDir);
        }
        catch (IOException e) {
            this.getLogger().warning("[\u81ea\u52d5\u30d0\u30c3\u30af\u30a2\u30c3\u30d7] \u30d0\u30c3\u30af\u30a2\u30c3\u30d7\u306e\u4f5c\u6210\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + e.getMessage());
        }
    }

    private void pruneOldBackups(File backupsDir) {
        File[] files = backupsDir.listFiles((dir, name) -> name.startsWith("data-") && name.endsWith(".db"));
        if (files == null || files.length <= this.cfgBackupRetentionCount) {
            return;
        }
        Arrays.sort(files, Comparator.comparingLong(File::lastModified));
        int toDelete = files.length - this.cfgBackupRetentionCount;
        for (int i = 0; i < toDelete; ++i) {
            if (files[i].delete()) continue;
            this.getLogger().warning("[\u81ea\u52d5\u30d0\u30c3\u30af\u30a2\u30c3\u30d7] \u53e4\u3044\u30d0\u30c3\u30af\u30a2\u30c3\u30d7\u306e\u524a\u9664\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + files[i].getName());
        }
    }

    private void runPeriodicSelfCheckReport() {
        List<String> issues = this.runSelfCheck(true);
        this.lastSelfCheckIssues = issues;
        this.lastSelfCheckAt = System.currentTimeMillis();
        if (issues.isEmpty()) {
            return;
        }
        this.saveData();
        this.getLogger().warning("[\u5b9a\u671f\u81ea\u5df1\u8a3a\u65ad] " + issues.size() + " \u4ef6\u306e\u4e0d\u6574\u5408\u3092\u691c\u51fa\u3057\u3001\u81ea\u52d5\u4fee\u5fa9\u3057\u307e\u3057\u305f:");
        for (String issue : issues) {
            this.getLogger().warning("[\u5b9a\u671f\u81ea\u5df1\u8a3a\u65ad] " + issue);
        }
        StringBuilder sb = new StringBuilder("\u26a0\ufe0f **\u5b9a\u671f\u81ea\u5df1\u8a3a\u65ad**: " + issues.size() + " \u4ef6\u306e\u4e0d\u6574\u5408\u3092\u691c\u51fa\u3057\u3001\u81ea\u52d5\u4fee\u5fa9\u3057\u307e\u3057\u305f\u3002\n");
        int shown = 0;
        for (String issue : issues) {
            if (shown >= 10) {
                sb.append("...\u4ed6 ").append(issues.size() - shown).append(" \u4ef6");
                break;
            }
            sb.append("- ").append(issue).append("\n");
            ++shown;
        }
        this.sendDiscordWebhook(sb.toString());
    }

    public void onDisable() {
        this.saveData();
        if (this.webDashboardServer != null) {
            this.webDashboardServer.stop(0);
            this.getLogger().info("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] HTTP\u30b5\u30fc\u30d0\u30fc\u3092\u505c\u6b62\u3057\u307e\u3057\u305f\u3002");
        }
        this.getServer().getServicesManager().unregister(MinecraftBankAPI.class, (Object)this);
        if (this.db != null) {
            this.db.close();
        }
    }

    private boolean setupEconomy() {
        if (this.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider rsp = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = (Economy)rsp.getProvider();
        return econ != null;
    }

    private boolean hasLegacyManagedDataInConfig() {
        FileConfiguration c = this.getConfig();
        for (String prefix : MANAGED_DATA_PREFIXES) {
            if (!c.isSet(prefix)) continue;
            return true;
        }
        return false;
    }

    private void migrateLegacyYamlDataToSqlite() {
        this.legacyImportLoadDataFromYaml();
        this.saveData();
        FileConfiguration c = this.getConfig();
        for (String prefix : MANAGED_DATA_PREFIXES) {
            c.set(prefix, null);
        }
        this.saveConfig();
        this.getLogger().info("[SQLite\u79fb\u884c] config.yml\u306e\u30d7\u30ec\u30a4\u30e4\u30fc/\u7d4c\u6e08\u30c7\u30fc\u30bf(" + MANAGED_DATA_PREFIXES.size() + "\u500b\u306e\u30d7\u30ec\u30d5\u30a3\u30c3\u30af\u30b9)\u3092data.db(SQLite)\u3078\u79fb\u884c\u3057\u307e\u3057\u305f\u3002\u4eca\u5f8c\u306fdata.db\u304c\u6b63\u3068\u306a\u308a\u3001config.yml\u306b\u306f\u8a2d\u5b9a\u9805\u76ee\u306e\u307f\u304c\u6b8b\u308a\u307e\u3059\u3002");
    }

    private void legacyImportLoadDataFromYaml() {
        Object deal2;
        String matName;
        Object base;
        UUID owner;
        Object list;
        List raw;
        Object obj;
        FileConfiguration c = this.getConfig();
        if (c.getConfigurationSection("personal") != null) {
            for (String key : c.getConfigurationSection("personal").getKeys(false)) {
                this.personalBank.put(UUID.fromString(key), c.getDouble("personal." + key));
            }
        }
        for (String s : c.getStringList("bankers")) {
            this.bankers.add(UUID.fromString(s));
        }
        if (c.getConfigurationSection("capital") != null) {
            for (String key : c.getConfigurationSection("capital").getKeys(false)) {
                this.bankCapital.put(UUID.fromString(key), c.getDouble("capital." + key));
            }
        }
        if (c.getConfigurationSection("market") != null) {
            for (String key : c.getConfigurationSection("market").getKeys(false)) {
                this.publishedLoans.put(UUID.fromString(key), c.getString("market." + key));
            }
        }
        if (c.getConfigurationSection("debts") != null) {
            for (String key : c.getConfigurationSection("debts").getKeys(false)) {
                this.activeDebts.put(UUID.fromString(key), c.getString("debts." + key));
            }
        }
        if (c.getConfigurationSection("fixed_deposit") != null) {
            for (String key : c.getConfigurationSection("fixed_deposit").getKeys(false)) {
                this.fixedDeposit.put(UUID.fromString(key), c.getDouble("fixed_deposit." + key));
            }
        }
        if (c.getConfigurationSection("fixed_time") != null) {
            for (String key : c.getConfigurationSection("fixed_time").getKeys(false)) {
                this.fixedDepositUnlockTime.put(UUID.fromString(key), c.getLong("fixed_time." + key));
            }
        }
        if (c.getConfigurationSection("gov_debt") != null) {
            for (String key : c.getConfigurationSection("gov_debt").getKeys(false)) {
                this.govDebt.put(UUID.fromString(key), c.getDouble("gov_debt." + key));
            }
        }
        if (c.getConfigurationSection("credit_score") != null) {
            for (String key : c.getConfigurationSection("credit_score").getKeys(false)) {
                this.creditScore.put(UUID.fromString(key), c.getInt("credit_score." + key));
            }
        }
        if (c.getConfigurationSection("gov_debt_due") != null) {
            for (String key : c.getConfigurationSection("gov_debt_due").getKeys(false)) {
                this.govDebtDueTime.put(UUID.fromString(key), c.getLong("gov_debt_due." + key));
            }
        }
        if (c.getConfigurationSection("auction_seller") != null) {
            for (String key : c.getConfigurationSection("auction_seller").getKeys(false)) {
                this.auctionSeller.put(UUID.fromString(key), UUID.fromString(c.getString("auction_seller." + key)));
            }
        }
        if (c.getConfigurationSection("auction_item") != null) {
            for (String key : c.getConfigurationSection("auction_item").getKeys(false)) {
                obj = c.get("auction_item." + key);
                if (!(obj instanceof ItemStack)) continue;
                this.auctionItem.put(UUID.fromString(key), (ItemStack)obj);
            }
        }
        if (c.getConfigurationSection("auction_bid") != null) {
            for (String key : c.getConfigurationSection("auction_bid").getKeys(false)) {
                this.auctionBid.put(UUID.fromString(key), c.getDouble("auction_bid." + key));
            }
        }
        if (c.getConfigurationSection("auction_bidder") != null) {
            for (String key : c.getConfigurationSection("auction_bidder").getKeys(false)) {
                this.auctionBidder.put(UUID.fromString(key), UUID.fromString(c.getString("auction_bidder." + key)));
            }
        }
        if (c.getConfigurationSection("auction_end") != null) {
            for (String key : c.getConfigurationSection("auction_end").getKeys(false)) {
                this.auctionEndTime.put(UUID.fromString(key), c.getLong("auction_end." + key));
            }
        }
        if (c.getConfigurationSection("auction_buyout") != null) {
            for (String key : c.getConfigurationSection("auction_buyout").getKeys(false)) {
                this.auctionBuyoutPrice.put(UUID.fromString(key), c.getDouble("auction_buyout." + key));
            }
        }
        if (c.getConfigurationSection("auction_pending") != null) {
            for (String key : c.getConfigurationSection("auction_pending").getKeys(false)) {
                raw = c.getList("auction_pending." + key);
                if (raw == null) continue;
                list = new ArrayList<ItemStack>();
                for (Object o : raw) {
                    if (!(o instanceof ItemStack)) continue;
                    list.add((ItemStack)o);
                }
                this.auctionPendingItems.put(UUID.fromString(key), (List<ItemStack>)list);
            }
        }
        if (c.getConfigurationSection("auction_draft") != null) {
            for (String key : c.getConfigurationSection("auction_draft").getKeys(false)) {
                obj = c.get("auction_draft." + key);
                if (!(obj instanceof ItemStack)) continue;
                this.auctionListingDraft.put(UUID.fromString(key), (ItemStack)obj);
            }
        }
        if (c.getConfigurationSection("auction_offline_notices") != null) {
            for (String key : c.getConfigurationSection("auction_offline_notices").getKeys(false)) {
                raw = c.getStringList("auction_offline_notices." + key);
                if (raw.isEmpty()) continue;
                this.auctionOfflineNotices.put(UUID.fromString(key), new ArrayList(raw));
            }
        }
        if (c.getConfigurationSection("auction_total_sold") != null) {
            for (String key : c.getConfigurationSection("auction_total_sold").getKeys(false)) {
                this.auctionTotalSoldAmount.put(UUID.fromString(key), c.getDouble("auction_total_sold." + key));
            }
        }
        if (c.getConfigurationSection("loan_offline_notices") != null) {
            for (String key : c.getConfigurationSection("loan_offline_notices").getKeys(false)) {
                raw = c.getStringList("loan_offline_notices." + key);
                if (raw.isEmpty()) continue;
                this.loanOfflineNotices.put(UUID.fromString(key), new ArrayList(raw));
            }
        }
        if (c.getConfigurationSection("collateral_item") != null) {
            for (String key : c.getConfigurationSection("collateral_item").getKeys(false)) {
                obj = c.get("collateral_item." + key);
                if (!(obj instanceof ItemStack)) continue;
                this.collateralItem.put(UUID.fromString(key), (ItemStack)obj);
            }
        }
        if (c.getConfigurationSection("collateral_loan") != null) {
            for (String key : c.getConfigurationSection("collateral_loan").getKeys(false)) {
                this.collateralLoanAmount.put(UUID.fromString(key), c.getDouble("collateral_loan." + key));
            }
        }
        if (c.getConfigurationSection("collateral_due") != null) {
            for (String key : c.getConfigurationSection("collateral_due").getKeys(false)) {
                this.collateralDueTime.put(UUID.fromString(key), c.getLong("collateral_due." + key));
            }
        }
        if (c.getConfigurationSection("collateral_lender") != null) {
            for (String key : c.getConfigurationSection("collateral_lender").getKeys(false)) {
                this.collateralLender.put(UUID.fromString(key), UUID.fromString(c.getString("collateral_lender." + key)));
            }
        }
        if (c.getConfigurationSection("insurance_expiry") != null) {
            for (String key : c.getConfigurationSection("insurance_expiry").getKeys(false)) {
                this.insuranceExpiry.put(UUID.fromString(key), c.getLong("insurance_expiry." + key));
            }
        }
        if (c.getConfigurationSection("insurance_last_claim") != null) {
            for (String key : c.getConfigurationSection("insurance_last_claim").getKeys(false)) {
                this.insuranceLastClaim.put(UUID.fromString(key), c.getLong("insurance_last_claim." + key));
            }
        }
        if (c.getConfigurationSection("vip_stipend_claimed") != null) {
            for (String key : c.getConfigurationSection("vip_stipend_claimed").getKeys(false)) {
                this.vipStipendClaimedAt.put(UUID.fromString(key), c.getLong("vip_stipend_claimed." + key));
            }
        }
        if (c.getConfigurationSection("achievements") != null) {
            for (String key : c.getConfigurationSection("achievements").getKeys(false)) {
                HashSet set = new HashSet(c.getStringList("achievements." + key));
                this.unlockedAchievements.put(UUID.fromString(key), set);
            }
        }
        if (c.getConfigurationSection("fixed_deposit2") != null) {
            for (String key : c.getConfigurationSection("fixed_deposit2").getKeys(false)) {
                this.fixedDeposit2.put(UUID.fromString(key), c.getDouble("fixed_deposit2." + key));
            }
        }
        if (c.getConfigurationSection("fixed_time2") != null) {
            for (String key : c.getConfigurationSection("fixed_time2").getKeys(false)) {
                this.fixedDepositUnlockTime2.put(UUID.fromString(key), c.getLong("fixed_time2." + key));
            }
        }
        if (c.getConfigurationSection("fixed_deposit3") != null) {
            for (String key : c.getConfigurationSection("fixed_deposit3").getKeys(false)) {
                this.fixedDeposit3.put(UUID.fromString(key), c.getDouble("fixed_deposit3." + key));
            }
        }
        if (c.getConfigurationSection("fixed_time3") != null) {
            for (String key : c.getConfigurationSection("fixed_time3").getKeys(false)) {
                this.fixedDepositUnlockTime3.put(UUID.fromString(key), c.getLong("fixed_time3." + key));
            }
        }
        if (c.getConfigurationSection("market2") != null) {
            for (String key : c.getConfigurationSection("market2").getKeys(false)) {
                this.publishedLoans2.put(UUID.fromString(key), c.getString("market2." + key));
            }
        }
        if (c.getConfigurationSection("market3") != null) {
            for (String key : c.getConfigurationSection("market3").getKeys(false)) {
                this.publishedLoans3.put(UUID.fromString(key), c.getString("market3." + key));
            }
        }
        if (c.contains("news_off")) {
            for (String s : c.getStringList("news_off")) {
                this.newsBroadcastOff.add(UUID.fromString(s));
            }
        }
        if (c.contains("hub_item_issued")) {
            for (String s : c.getStringList("hub_item_issued")) {
                this.hubItemIssued.add(UUID.fromString(s));
            }
        }
        if (c.getConfigurationSection("tx_logs") != null) {
            for (String key : c.getConfigurationSection("tx_logs").getKeys(false)) {
                LinkedList logs = new LinkedList(c.getStringList("tx_logs." + key));
                this.transactionLogs.put(UUID.fromString(key), logs);
            }
        }
        this.treasury = c.getDouble("treasury", 0.0);
        this.weeklyTradeVolume = c.getDouble("weekly_trade_volume", 0.0);
        if (c.getConfigurationSection("donation_score_today") != null) {
            for (String key : c.getConfigurationSection("donation_score_today").getKeys(false)) {
                this.donationScoreToday.put(UUID.fromString(key), c.getInt("donation_score_today." + key));
            }
        }
        if (c.getConfigurationSection("donation_score_reset_at") != null) {
            for (String key : c.getConfigurationSection("donation_score_reset_at").getKeys(false)) {
                this.donationScoreResetAt.put(UUID.fromString(key), c.getLong("donation_score_reset_at." + key));
            }
        }
        if (c.getConfigurationSection("welfare_count_today") != null) {
            for (String key : c.getConfigurationSection("welfare_count_today").getKeys(false)) {
                this.welfareCountToday.put(UUID.fromString(key), c.getInt("welfare_count_today." + key));
            }
        }
        if (c.getConfigurationSection("welfare_count_reset_at") != null) {
            for (String key : c.getConfigurationSection("welfare_count_reset_at").getKeys(false)) {
                this.welfareCountResetAt.put(UUID.fromString(key), c.getLong("welfare_count_reset_at." + key));
            }
        }
        if (c.getConfigurationSection("loan_guarantor") != null) {
            for (String key : c.getConfigurationSection("loan_guarantor").getKeys(false)) {
                this.loanGuarantor.put(UUID.fromString(key), UUID.fromString(c.getString("loan_guarantor." + key)));
            }
        }
        if (c.getConfigurationSection("guarantor_proposals") != null) {
            for (String key : c.getConfigurationSection("guarantor_proposals").getKeys(false)) {
                this.guarantorProposals.put(UUID.fromString(key), UUID.fromString(c.getString("guarantor_proposals." + key)));
            }
        }
        if (c.getConfigurationSection("guarantor_proposal_time") != null) {
            for (String key : c.getConfigurationSection("guarantor_proposal_time").getKeys(false)) {
                this.guarantorProposalTime.put(UUID.fromString(key), c.getLong("guarantor_proposal_time." + key));
            }
        }
        this.activeEconomyEvent = c.getString("economy-event.name", "\u901a\u5e38");
        this.activeEconomyEventUntil = c.getLong("economy-event.until", 0L);
        this.tutorialSeen.addAll(c.getStringList("tutorial-seen").stream().map(UUID::fromString).toList());
        if (c.getConfigurationSection("world_stocks") != null) {
            for (String uuidKey : c.getConfigurationSection("world_stocks").getKeys(false)) {
                owner = UUID.fromString(uuidKey);
                HashMap<String, Integer> shares = new HashMap<String, Integer>();
                Iterator avgCosts = new HashMap();
                if (c.isList("world_stocks." + uuidKey)) {
                    for (String line : c.getStringList("world_stocks." + uuidKey)) {
                        String[] parts = line.split(";", 3);
                        if (parts.length < 3) continue;
                        try {
                            qty = Integer.parseInt(parts[1].trim());
                            if (qty <= 0) continue;
                            shares.put(parts[0], qty);
                            ((HashMap)((Object)avgCosts)).put(parts[0], Double.parseDouble(parts[2].trim()));
                        }
                        catch (NumberFormatException qty) {}
                    }
                } else if (c.getConfigurationSection("world_stocks." + uuidKey) != null) {
                    for (String symbol : c.getConfigurationSection("world_stocks." + uuidKey).getKeys(false)) {
                        String base2 = "world_stocks." + uuidKey + "." + symbol + ".";
                        qty = c.getInt(base2 + "shares", 0);
                        if (qty <= 0) continue;
                        shares.put(symbol, qty);
                        ((HashMap)((Object)avgCosts)).put(symbol, c.getDouble(base2 + "avg_cost", 0.0));
                    }
                }
                if (shares.isEmpty()) continue;
                this.playerWorldStocks.put(owner, shares);
                this.playerWorldStockAvgCost.put(owner, (HashMap<String, Double>)((Object)avgCosts));
            }
        }
        if (c.getConfigurationSection("world_stock_daily") != null) {
            for (String key : c.getConfigurationSection("world_stock_daily").getKeys(false)) {
                owner = UUID.fromString(key);
                String base3 = "world_stock_daily." + key + ".";
                this.worldStockTradesToday.put(owner, c.getInt(base3 + "trades", 0));
                this.worldStockAmountToday.put(owner, c.getDouble(base3 + "amount", 0.0));
                this.worldStockProfitToday.put(owner, c.getDouble(base3 + "profit", 0.0));
                this.worldStockDailyResetAt.put(owner, c.getLong(base3 + "reset_at", 0L));
            }
        }
        if (c.getConfigurationSection("world_stock_last_trade") != null) {
            for (String uuidKey : c.getConfigurationSection("world_stock_last_trade").getKeys(false)) {
                owner = UUID.fromString(uuidKey);
                HashMap<String, Long> lastTrade = new HashMap<String, Long>();
                if (c.isList("world_stock_last_trade." + uuidKey)) {
                    for (String line : c.getStringList("world_stock_last_trade." + uuidKey)) {
                        int sep = line.lastIndexOf(59);
                        if (sep <= 0) continue;
                        try {
                            lastTrade.put(line.substring(0, sep), Long.parseLong(line.substring(sep + 1).trim()));
                        }
                        catch (NumberFormatException base2) {}
                    }
                } else if (c.getConfigurationSection("world_stock_last_trade." + uuidKey) != null) {
                    for (String symbol : c.getConfigurationSection("world_stock_last_trade." + uuidKey).getKeys(false)) {
                        lastTrade.put(symbol, c.getLong("world_stock_last_trade." + uuidKey + "." + symbol));
                    }
                }
                if (lastTrade.isEmpty()) continue;
                this.worldStockLastTradeTime.put(owner, lastTrade);
            }
        }
        if (c.getConfigurationSection("world_stock_alerts") != null) {
            for (String uuidKey : c.getConfigurationSection("world_stock_alerts").getKeys(false)) {
                owner = UUID.fromString(uuidKey);
                list = new ArrayList();
                if (c.getConfigurationSection("world_stock_alerts." + uuidKey) != null) {
                    for (String alertIdKey : c.getConfigurationSection("world_stock_alerts." + uuidKey).getKeys(false)) {
                        String base4 = "world_stock_alerts." + uuidKey + "." + alertIdKey + ".";
                        String symbol = c.getString(base4 + "symbol");
                        if (symbol == null) continue;
                        WorldStockAlert alert = new WorldStockAlert();
                        alert.id = UUID.fromString(alertIdKey);
                        alert.symbol = symbol;
                        alert.baselinePrice = c.getDouble(base4 + "baseline", 0.0);
                        alert.thresholdPercent = c.getDouble(base4 + "threshold", 0.0);
                        list.add(alert);
                    }
                }
                if (list.isEmpty()) continue;
                this.worldStockAlerts.put(owner, (List<WorldStockAlert>)list);
            }
        }
        if (c.getConfigurationSection("resource_prices") != null) {
            for (String key : c.getConfigurationSection("resource_prices").getKeys(false)) {
                try {
                    this.resourcePrices.put(Material.valueOf((String)key), c.getDouble("resource_prices." + key));
                }
                catch (IllegalArgumentException owner2) {}
            }
        }
        if (c.getConfigurationSection("resource_personal_buy") != null) {
            for (String key : c.getConfigurationSection("resource_personal_buy").getKeys(false)) {
                UUID pu = UUID.fromString(key);
                for (String entry : c.getStringList("resource_personal_buy." + key)) {
                    int sep = entry.indexOf(59);
                    if (sep < 0) continue;
                    try {
                        Material mat = Material.valueOf((String)entry.substring(0, sep));
                        double mult = Double.parseDouble(entry.substring(sep + 1));
                        this.resourcePersonalBuyMultiplier.computeIfAbsent(pu, k -> new HashMap()).put(mat, mult);
                    }
                    catch (IllegalArgumentException mat) {}
                }
            }
        }
        this.merchantRefreshAt = c.getLong("traveling_merchant.refresh_at", 0L);
        if (c.getConfigurationSection("traveling_merchant.deals") != null) {
            for (String key : c.getConfigurationSection("traveling_merchant.deals").getKeys(false)) {
                base = "traveling_merchant.deals." + key + ".";
                matName = c.getString((String)base + "material");
                if (matName == null) continue;
                try {
                    deal2 = new MerchantDeal();
                    ((MerchantDeal)deal2).id = UUID.fromString(key);
                    ((MerchantDeal)deal2).material = Material.valueOf((String)matName);
                    ((MerchantDeal)deal2).normalPrice = c.getDouble((String)base + "normal_price", 0.0);
                    ((MerchantDeal)deal2).discountPercent = c.getDouble((String)base + "discount_percent", 0.0);
                    ((MerchantDeal)deal2).stockTotal = c.getInt((String)base + "stock_total", 0);
                    ((MerchantDeal)deal2).stockRemaining = c.getInt((String)base + "stock_remaining", 0);
                    this.merchantDeals.add((MerchantDeal)deal2);
                }
                catch (IllegalArgumentException deal2) {}
            }
        }
        this.vipShopRefreshAt = c.getLong("vip_shop.refresh_at", 0L);
        if (c.getConfigurationSection("vip_shop.deals") != null) {
            for (String key : c.getConfigurationSection("vip_shop.deals").getKeys(false)) {
                base = "vip_shop.deals." + key + ".";
                matName = c.getString((String)base + "material");
                if (matName == null) continue;
                try {
                    deal2 = new VipDeal();
                    ((VipDeal)deal2).id = UUID.fromString(key);
                    ((VipDeal)deal2).material = Material.valueOf((String)matName);
                    ((VipDeal)deal2).normalPrice = c.getDouble((String)base + "normal_price", 0.0);
                    ((VipDeal)deal2).discountPercent = c.getDouble((String)base + "discount_percent", 0.0);
                    ((VipDeal)deal2).stockTotal = c.getInt((String)base + "stock_total", 0);
                    ((VipDeal)deal2).stockRemaining = c.getInt((String)base + "stock_remaining", 0);
                    this.vipShopDeals.add((VipDeal)deal2);
                }
                catch (IllegalArgumentException deal3) {}
            }
        }
        if (c.contains("traveling_merchant.location.world")) {
            this.merchantWorldName = c.getString("traveling_merchant.location.world");
            this.merchantX = c.getDouble("traveling_merchant.location.x", 0.0);
            this.merchantY = c.getDouble("traveling_merchant.location.y", 0.0);
            this.merchantZ = c.getDouble("traveling_merchant.location.z", 0.0);
        }
        this.treasureActive = c.getBoolean("treasure.active", false);
        this.treasureWorldName = c.getString("treasure.world", null);
        this.treasureX = c.getInt("treasure.x", 0);
        this.treasureY = c.getInt("treasure.y", 0);
        this.treasureZ = c.getInt("treasure.z", 0);
        this.treasureReward = c.getDouble("treasure.reward", 0.0);
        this.treasureNextSpawnAt = c.getLong("treasure.next-spawn-at", 0L);
        if (c.getConfigurationSection("quests") != null) {
            for (String key : c.getConfigurationSection("quests").getKeys(false)) {
                base = "quests." + key + ".";
                Quest q = new Quest();
                q.id = UUID.fromString(key);
                q.posterId = UUID.fromString(c.getString((String)base + "poster"));
                q.worldName = c.getString((String)base + "world");
                q.x = c.getDouble((String)base + "x");
                q.y = c.getDouble((String)base + "y");
                q.z = c.getDouble((String)base + "z");
                q.reward = c.getDouble((String)base + "reward");
                q.state = QuestState.valueOf(c.getString((String)base + "state", "AVAILABLE"));
                String acceptedByStr = c.getString((String)base + "acceptedBy");
                q.acceptedBy = acceptedByStr != null ? UUID.fromString(acceptedByStr) : null;
                q.cooldownUntil = c.getLong((String)base + "cooldownUntil");
                q.requiredTeamSize = c.getInt((String)base + "requiredTeamSize", 1);
                q.teamMembers = new HashSet<UUID>(c.getStringList((String)base + "teamMembers").stream().map(UUID::fromString).toList());
                q.teamArrived = new HashSet<UUID>(c.getStringList((String)base + "teamArrived").stream().map(UUID::fromString).toList());
                this.quests.put(q.id, q);
                if (q.state == QuestState.IN_PROGRESS && q.acceptedBy != null) {
                    this.playerActiveQuest.put(q.acceptedBy, q.id);
                }
                if (q.requiredTeamSize <= 1) continue;
                for (UUID member : q.teamMembers) {
                    this.playerActiveQuest.put(member, q.id);
                }
            }
        }
        if (c.getConfigurationSection("installment_plans") != null) {
            for (String ownerKey : c.getConfigurationSection("installment_plans").getKeys(false)) {
                try {
                    owner = UUID.fromString(ownerKey);
                }
                catch (IllegalArgumentException ex) {
                    continue;
                }
                if (c.getConfigurationSection("installment_plans." + ownerKey) == null) continue;
                for (String planKey : c.getConfigurationSection("installment_plans." + ownerKey).getKeys(false)) {
                    String base5 = "installment_plans." + ownerKey + "." + planKey + ".";
                    try {
                        InstallmentPlan plan = new InstallmentPlan();
                        plan.id = UUID.fromString(planKey);
                        plan.owner = owner;
                        plan.description = c.getString(base5 + "description", "");
                        plan.installmentAmount = c.getDouble(base5 + "installment_amount", 0.0);
                        plan.installmentsRemaining = c.getInt(base5 + "installments_remaining", 0);
                        plan.nextDueTime = c.getLong(base5 + "next_due_time", 0L);
                        if (plan.installmentsRemaining <= 0) continue;
                        this.installmentPlans.computeIfAbsent(owner, k -> new ArrayList()).add(plan);
                    }
                    catch (IllegalArgumentException plan) {}
                }
            }
        }
        this.lotteryPool = c.getDouble("lottery.pool", 0.0);
        this.lotteryDrawAt = c.getLong("lottery.draw_at", 0L);
        this.lastLotteryWinnerName = c.getString("lottery.last_winner_name", "-");
        this.lastLotteryWinnerAmount = c.getDouble("lottery.last_winner_amount", 0.0);
        if (c.getConfigurationSection("lottery.tickets") != null) {
            for (String key : c.getConfigurationSection("lottery.tickets").getKeys(false)) {
                try {
                    this.lotteryTickets.put(UUID.fromString(key), c.getInt("lottery.tickets." + key));
                }
                catch (IllegalArgumentException owner3) {}
            }
        }
        if (c.getConfigurationSection("storage_rent") != null) {
            for (String key : c.getConfigurationSection("storage_rent").getKeys(false)) {
                try {
                    UUID u = UUID.fromString(key);
                    this.storageRentDueTime.put(u, c.getLong("storage_rent." + key));
                    if (c.getConfigurationSection("storage_items." + key) == null) continue;
                    HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();
                    for (String slotKey : c.getConfigurationSection("storage_items." + key).getKeys(false)) {
                        Object obj2 = c.get("storage_items." + key + "." + slotKey);
                        if (!(obj2 instanceof ItemStack)) continue;
                        try {
                            slots.put(Integer.parseInt(slotKey), (ItemStack)obj2);
                        }
                        catch (NumberFormatException mult) {}
                    }
                    if (slots.isEmpty()) continue;
                    this.pendingStorageContents.put(u, slots);
                }
                catch (IllegalArgumentException u) {}
            }
        }
        if (c.getConfigurationSection("group_accounts") != null) {
            for (String key : c.getConfigurationSection("group_accounts").getKeys(false)) {
                try {
                    base = "group_accounts." + key + ".";
                    GroupAccount acc = new GroupAccount();
                    acc.id = UUID.fromString(key);
                    acc.name = c.getString((String)base + "name", "");
                    acc.owner = UUID.fromString(c.getString((String)base + "owner"));
                    acc.balance = c.getDouble((String)base + "balance", 0.0);
                    acc.members = new HashSet<UUID>(c.getStringList((String)base + "members").stream().map(UUID::fromString).toList());
                    this.groupAccounts.put(acc.id, acc);
                }
                catch (IllegalArgumentException base6) {}
            }
        }
        this.playerGroupAccounts.clear();
        for (GroupAccount acc : this.groupAccounts.values()) {
            this.playerGroupAccounts.computeIfAbsent(acc.owner, k -> new HashSet()).add(acc.id);
            for (UUID m : acc.members) {
                this.playerGroupAccounts.computeIfAbsent(m, k -> new HashSet()).add(acc.id);
            }
        }
        if (c.getConfigurationSection("investment_funds") != null) {
            for (String key : c.getConfigurationSection("investment_funds").getKeys(false)) {
                try {
                    base = "investment_funds." + key + ".";
                    InvestmentFund fund = new InvestmentFund();
                    fund.id = UUID.fromString(key);
                    fund.name = c.getString((String)base + "name", "");
                    fund.manager = UUID.fromString(c.getString((String)base + "manager"));
                    fund.cashBalance = c.getDouble((String)base + "cash_balance", 0.0);
                    if (c.getConfigurationSection((String)base + "contributions") != null) {
                        for (String contribKey : c.getConfigurationSection((String)base + "contributions").getKeys(false)) {
                            try {
                                UUID contributor = UUID.fromString(contribKey);
                                double amount = c.getDouble((String)base + "contributions." + contribKey, 0.0);
                                fund.contributions.put(contributor, amount);
                            }
                            catch (IllegalArgumentException illegalArgumentException) {}
                        }
                    }
                    this.investmentFunds.put(fund.id, fund);
                }
                catch (IllegalArgumentException illegalArgumentException) {}
            }
        }
        this.playerInvestmentFunds.clear();
        for (InvestmentFund fund : this.investmentFunds.values()) {
            this.playerInvestmentFunds.computeIfAbsent(fund.manager, k -> new HashSet()).add(fund.id);
            for (UUID contributor : fund.contributions.keySet()) {
                this.playerInvestmentFunds.computeIfAbsent(contributor, k -> new HashSet()).add(fund.id);
            }
        }
    }

    private void loadData() {
        Object deal2;
        String matName;
        String base;
        Object base2;
        UUID owner;
        List<String> raw;
        ItemStack item;
        for (String key : this.db.getKeys("personal")) {
            this.personalBank.put(UUID.fromString(key), this.db.getDouble("personal", key, 0.0));
        }
        for (String s : this.db.getStringList("bankers", "_")) {
            this.bankers.add(UUID.fromString(s));
        }
        for (String key : this.db.getKeys("capital")) {
            this.bankCapital.put(UUID.fromString(key), this.db.getDouble("capital", key, 0.0));
        }
        for (String key : this.db.getKeys("market")) {
            this.publishedLoans.put(UUID.fromString(key), this.db.getString("market", key, null));
        }
        for (String key : this.db.getKeys("debts")) {
            this.activeDebts.put(UUID.fromString(key), this.db.getString("debts", key, null));
        }
        for (String key : this.db.getKeys("fixed_deposit")) {
            this.fixedDeposit.put(UUID.fromString(key), this.db.getDouble("fixed_deposit", key, 0.0));
        }
        for (String key : this.db.getKeys("fixed_time")) {
            this.fixedDepositUnlockTime.put(UUID.fromString(key), this.db.getLong("fixed_time", key, 0L));
        }
        for (String key : this.db.getKeys("gov_debt")) {
            this.govDebt.put(UUID.fromString(key), this.db.getDouble("gov_debt", key, 0.0));
        }
        for (String key : this.db.getKeys("credit_score")) {
            this.creditScore.put(UUID.fromString(key), this.db.getInt("credit_score", key, 0));
        }
        for (String key : this.db.getKeys("gov_debt_due")) {
            this.govDebtDueTime.put(UUID.fromString(key), this.db.getLong("gov_debt_due", key, 0L));
        }
        for (String key : this.db.getKeys("auction_seller")) {
            this.auctionSeller.put(UUID.fromString(key), UUID.fromString(this.db.getString("auction_seller", key, null)));
        }
        for (String key : this.db.getKeys("auction_item")) {
            item = this.db.getItemStack("auction_item", key);
            if (item == null) continue;
            this.auctionItem.put(UUID.fromString(key), item);
        }
        for (String key : this.db.getKeys("auction_bid")) {
            this.auctionBid.put(UUID.fromString(key), this.db.getDouble("auction_bid", key, 0.0));
        }
        for (String key : this.db.getKeys("auction_bidder")) {
            this.auctionBidder.put(UUID.fromString(key), UUID.fromString(this.db.getString("auction_bidder", key, null)));
        }
        for (String key : this.db.getKeys("auction_end")) {
            this.auctionEndTime.put(UUID.fromString(key), this.db.getLong("auction_end", key, 0L));
        }
        for (String key : this.db.getKeys("auction_buyout")) {
            this.auctionBuyoutPrice.put(UUID.fromString(key), this.db.getDouble("auction_buyout", key, 0.0));
        }
        for (String key : this.db.getKeys("auction_pending")) {
            this.auctionPendingItems.put(UUID.fromString(key), this.db.getItemStackList("auction_pending", key));
        }
        for (String key : this.db.getKeys("auction_draft")) {
            item = this.db.getItemStack("auction_draft", key);
            if (item == null) continue;
            this.auctionListingDraft.put(UUID.fromString(key), item);
        }
        for (String key : this.db.getKeys("auction_offline_notices")) {
            raw = this.db.getStringList("auction_offline_notices", key);
            if (raw.isEmpty()) continue;
            this.auctionOfflineNotices.put(UUID.fromString(key), new ArrayList<String>(raw));
        }
        for (String key : this.db.getKeys("auction_total_sold")) {
            this.auctionTotalSoldAmount.put(UUID.fromString(key), this.db.getDouble("auction_total_sold", key, 0.0));
        }
        for (String key : this.db.getKeys("loan_offline_notices")) {
            raw = this.db.getStringList("loan_offline_notices", key);
            if (raw.isEmpty()) continue;
            this.loanOfflineNotices.put(UUID.fromString(key), new ArrayList<String>(raw));
        }
        for (String key : this.db.getKeys("collateral_item")) {
            item = this.db.getItemStack("collateral_item", key);
            if (item == null) continue;
            this.collateralItem.put(UUID.fromString(key), item);
        }
        for (String key : this.db.getKeys("collateral_loan")) {
            this.collateralLoanAmount.put(UUID.fromString(key), this.db.getDouble("collateral_loan", key, 0.0));
        }
        for (String key : this.db.getKeys("collateral_due")) {
            this.collateralDueTime.put(UUID.fromString(key), this.db.getLong("collateral_due", key, 0L));
        }
        for (String key : this.db.getKeys("collateral_lender")) {
            this.collateralLender.put(UUID.fromString(key), UUID.fromString(this.db.getString("collateral_lender", key, null)));
        }
        for (String key : this.db.getKeys("insurance_expiry")) {
            this.insuranceExpiry.put(UUID.fromString(key), this.db.getLong("insurance_expiry", key, 0L));
        }
        for (String key : this.db.getKeys("insurance_last_claim")) {
            this.insuranceLastClaim.put(UUID.fromString(key), this.db.getLong("insurance_last_claim", key, 0L));
        }
        for (String key : this.db.getKeys("vip_stipend_claimed")) {
            this.vipStipendClaimedAt.put(UUID.fromString(key), this.db.getLong("vip_stipend_claimed", key, 0L));
        }
        for (String key : this.db.getKeys("achievements")) {
            HashSet<String> set = new HashSet<String>(this.db.getStringList("achievements", key));
            this.unlockedAchievements.put(UUID.fromString(key), set);
        }
        for (String key : this.db.getKeys("fixed_deposit2")) {
            this.fixedDeposit2.put(UUID.fromString(key), this.db.getDouble("fixed_deposit2", key, 0.0));
        }
        for (String key : this.db.getKeys("fixed_time2")) {
            this.fixedDepositUnlockTime2.put(UUID.fromString(key), this.db.getLong("fixed_time2", key, 0L));
        }
        for (String key : this.db.getKeys("fixed_deposit3")) {
            this.fixedDeposit3.put(UUID.fromString(key), this.db.getDouble("fixed_deposit3", key, 0.0));
        }
        for (String key : this.db.getKeys("fixed_time3")) {
            this.fixedDepositUnlockTime3.put(UUID.fromString(key), this.db.getLong("fixed_time3", key, 0L));
        }
        for (String key : this.db.getKeys("market2")) {
            this.publishedLoans2.put(UUID.fromString(key), this.db.getString("market2", key, null));
        }
        for (String key : this.db.getKeys("market3")) {
            this.publishedLoans3.put(UUID.fromString(key), this.db.getString("market3", key, null));
        }
        for (String s : this.db.getStringList("news_off", "_")) {
            this.newsBroadcastOff.add(UUID.fromString(s));
        }
        for (String s : this.db.getStringList("hub_item_issued", "_")) {
            this.hubItemIssued.add(UUID.fromString(s));
        }
        for (String key : this.db.getKeys("tx_logs")) {
            LinkedList<String> logs = new LinkedList<String>(this.db.getStringList("tx_logs", key));
            this.transactionLogs.put(UUID.fromString(key), logs);
        }
        this.treasury = this.db.getDouble("treasury", "_", 0.0);
        this.weeklyTradeVolume = this.db.getDouble("weekly_trade_volume", "_", 0.0);
        for (String key : this.db.getKeys("donation_score_today")) {
            this.donationScoreToday.put(UUID.fromString(key), this.db.getInt("donation_score_today", key, 0));
        }
        for (String key : this.db.getKeys("donation_score_reset_at")) {
            this.donationScoreResetAt.put(UUID.fromString(key), this.db.getLong("donation_score_reset_at", key, 0L));
        }
        for (String key : this.db.getKeys("welfare_count_today")) {
            this.welfareCountToday.put(UUID.fromString(key), this.db.getInt("welfare_count_today", key, 0));
        }
        for (String key : this.db.getKeys("welfare_count_reset_at")) {
            this.welfareCountResetAt.put(UUID.fromString(key), this.db.getLong("welfare_count_reset_at", key, 0L));
        }
        for (String key : this.db.getKeys("loan_guarantor")) {
            this.loanGuarantor.put(UUID.fromString(key), UUID.fromString(this.db.getString("loan_guarantor", key, null)));
        }
        for (String key : this.db.getKeys("guarantor_proposals")) {
            this.guarantorProposals.put(UUID.fromString(key), UUID.fromString(this.db.getString("guarantor_proposals", key, null)));
        }
        for (String key : this.db.getKeys("guarantor_proposal_time")) {
            this.guarantorProposalTime.put(UUID.fromString(key), this.db.getLong("guarantor_proposal_time", key, 0L));
        }
        this.activeEconomyEvent = this.db.getString("economy-event", "name", "\u901a\u5e38");
        this.activeEconomyEventUntil = this.db.getLong("economy-event", "until", 0L);
        this.tutorialSeen.addAll(this.db.getStringList("tutorial-seen", "_").stream().map(UUID::fromString).toList());
        for (String uuidKey : this.db.getKeys("world_stocks")) {
            owner = UUID.fromString(uuidKey);
            HashMap<String, Integer> shares = new HashMap<String, Integer>();
            Iterator<String> avgCosts = new HashMap();
            for (String line : this.db.getStringList("world_stocks", uuidKey)) {
                String[] parts = line.split(";", 3);
                if (parts.length < 3) continue;
                try {
                    int qty = Integer.parseInt(parts[1].trim());
                    if (qty <= 0) continue;
                    shares.put(parts[0], qty);
                    ((HashMap)((Object)avgCosts)).put(parts[0], Double.parseDouble(parts[2].trim()));
                }
                catch (NumberFormatException qty) {}
            }
            if (shares.isEmpty()) continue;
            this.playerWorldStocks.put(owner, shares);
            this.playerWorldStockAvgCost.put(owner, (HashMap<String, Double>)((Object)avgCosts));
        }
        for (String key : this.db.getKeys("world_stock_daily")) {
            owner = UUID.fromString(key);
            base2 = key + ".";
            this.worldStockTradesToday.put(owner, this.db.getInt("world_stock_daily", (String)base2 + "trades", 0));
            this.worldStockAmountToday.put(owner, this.db.getDouble("world_stock_daily", (String)base2 + "amount", 0.0));
            this.worldStockProfitToday.put(owner, this.db.getDouble("world_stock_daily", (String)base2 + "profit", 0.0));
            this.worldStockDailyResetAt.put(owner, this.db.getLong("world_stock_daily", (String)base2 + "reset_at", 0L));
        }
        for (String uuidKey : this.db.getKeys("world_stock_last_trade")) {
            owner = UUID.fromString(uuidKey);
            HashMap<String, Long> lastTrade = new HashMap<String, Long>();
            for (String line : this.db.getStringList("world_stock_last_trade", uuidKey)) {
                int sep = line.lastIndexOf(59);
                if (sep <= 0) continue;
                try {
                    lastTrade.put(line.substring(0, sep), Long.parseLong(line.substring(sep + 1).trim()));
                }
                catch (NumberFormatException parts) {}
            }
            if (lastTrade.isEmpty()) continue;
            this.worldStockLastTradeTime.put(owner, lastTrade);
        }
        for (String uuidKey : this.db.getKeys("world_stock_alerts")) {
            owner = UUID.fromString(uuidKey);
            ArrayList list = new ArrayList();
            for (String alertIdKey : this.db.getKeys("world_stock_alerts", uuidKey)) {
                String base3 = uuidKey + "." + alertIdKey + ".";
                String symbol = this.db.getString("world_stock_alerts", base3 + "symbol", null);
                if (symbol == null) continue;
                WorldStockAlert alert = new WorldStockAlert();
                alert.id = UUID.fromString(alertIdKey);
                alert.symbol = symbol;
                alert.baselinePrice = this.db.getDouble("world_stock_alerts", base3 + "baseline", 0.0);
                alert.thresholdPercent = this.db.getDouble("world_stock_alerts", base3 + "threshold", 0.0);
                list.add(alert);
            }
            if (list.isEmpty()) continue;
            this.worldStockAlerts.put(owner, list);
        }
        for (String key : this.db.getKeys("resource_prices")) {
            try {
                this.resourcePrices.put(Material.valueOf((String)key), this.db.getDouble("resource_prices", key, 0.0));
            }
            catch (IllegalArgumentException owner2) {}
        }
        for (String key : this.db.getKeys("resource_personal_buy")) {
            UUID pu = UUID.fromString(key);
            for (String entry : this.db.getStringList("resource_personal_buy", key)) {
                int sep = entry.indexOf(59);
                if (sep < 0) continue;
                try {
                    Material mat = Material.valueOf((String)entry.substring(0, sep));
                    double mult = Double.parseDouble(entry.substring(sep + 1));
                    this.resourcePersonalBuyMultiplier.computeIfAbsent(pu, k -> new HashMap()).put(mat, mult);
                }
                catch (IllegalArgumentException mat) {}
            }
        }
        this.merchantRefreshAt = this.db.getLong("traveling_merchant", "refresh_at", 0L);
        for (String key : this.db.getKeys("traveling_merchant", "deals")) {
            base = "deals." + key + ".";
            matName = this.db.getString("traveling_merchant", base + "material", null);
            if (matName == null) continue;
            try {
                deal2 = new MerchantDeal();
                ((MerchantDeal)deal2).id = UUID.fromString(key);
                ((MerchantDeal)deal2).material = Material.valueOf((String)matName);
                ((MerchantDeal)deal2).normalPrice = this.db.getDouble("traveling_merchant", base + "normal_price", 0.0);
                ((MerchantDeal)deal2).discountPercent = this.db.getDouble("traveling_merchant", base + "discount_percent", 0.0);
                ((MerchantDeal)deal2).stockTotal = this.db.getInt("traveling_merchant", base + "stock_total", 0);
                ((MerchantDeal)deal2).stockRemaining = this.db.getInt("traveling_merchant", base + "stock_remaining", 0);
                this.merchantDeals.add((MerchantDeal)deal2);
            }
            catch (IllegalArgumentException deal2) {}
        }
        this.vipShopRefreshAt = this.db.getLong("vip_shop", "refresh_at", 0L);
        for (String key : this.db.getKeys("vip_shop", "deals")) {
            base = "deals." + key + ".";
            matName = this.db.getString("vip_shop", base + "material", null);
            if (matName == null) continue;
            try {
                deal2 = new VipDeal();
                ((VipDeal)deal2).id = UUID.fromString(key);
                ((VipDeal)deal2).material = Material.valueOf((String)matName);
                ((VipDeal)deal2).normalPrice = this.db.getDouble("vip_shop", base + "normal_price", 0.0);
                ((VipDeal)deal2).discountPercent = this.db.getDouble("vip_shop", base + "discount_percent", 0.0);
                ((VipDeal)deal2).stockTotal = this.db.getInt("vip_shop", base + "stock_total", 0);
                ((VipDeal)deal2).stockRemaining = this.db.getInt("vip_shop", base + "stock_remaining", 0);
                this.vipShopDeals.add((VipDeal)deal2);
            }
            catch (IllegalArgumentException deal3) {}
        }
        String merchantLocWorld = this.db.getString("traveling_merchant", "location.world", null);
        if (merchantLocWorld != null) {
            this.merchantWorldName = merchantLocWorld;
            this.merchantX = this.db.getDouble("traveling_merchant", "location.x", 0.0);
            this.merchantY = this.db.getDouble("traveling_merchant", "location.y", 0.0);
            this.merchantZ = this.db.getDouble("traveling_merchant", "location.z", 0.0);
        }
        this.treasureActive = this.db.getBoolean("treasure", "active", false);
        this.treasureWorldName = this.db.getString("treasure", "world", null);
        this.treasureX = this.db.getInt("treasure", "x", 0);
        this.treasureY = this.db.getInt("treasure", "y", 0);
        this.treasureZ = this.db.getInt("treasure", "z", 0);
        this.treasureReward = this.db.getDouble("treasure", "reward", 0.0);
        this.treasureNextSpawnAt = this.db.getLong("treasure", "next-spawn-at", 0L);
        for (String key : this.db.getKeys("quests")) {
            base2 = key + ".";
            Quest q = new Quest();
            q.id = UUID.fromString(key);
            q.posterId = UUID.fromString(this.db.getString("quests", (String)base2 + "poster", null));
            q.worldName = this.db.getString("quests", (String)base2 + "world", null);
            q.x = this.db.getDouble("quests", (String)base2 + "x", 0.0);
            q.y = this.db.getDouble("quests", (String)base2 + "y", 0.0);
            q.z = this.db.getDouble("quests", (String)base2 + "z", 0.0);
            q.reward = this.db.getDouble("quests", (String)base2 + "reward", 0.0);
            q.state = QuestState.valueOf(this.db.getString("quests", (String)base2 + "state", "AVAILABLE"));
            String acceptedByStr = this.db.getString("quests", (String)base2 + "acceptedBy", null);
            q.acceptedBy = acceptedByStr != null ? UUID.fromString(acceptedByStr) : null;
            q.cooldownUntil = this.db.getLong("quests", (String)base2 + "cooldownUntil", 0L);
            q.requiredTeamSize = this.db.getInt("quests", (String)base2 + "requiredTeamSize", 1);
            q.teamMembers = new HashSet<UUID>(this.db.getStringList("quests", (String)base2 + "teamMembers").stream().map(UUID::fromString).toList());
            q.teamArrived = new HashSet<UUID>(this.db.getStringList("quests", (String)base2 + "teamArrived").stream().map(UUID::fromString).toList());
            this.quests.put(q.id, q);
            if (q.state == QuestState.IN_PROGRESS && q.acceptedBy != null) {
                this.playerActiveQuest.put(q.acceptedBy, q.id);
            }
            if (q.requiredTeamSize <= 1) continue;
            for (UUID member : q.teamMembers) {
                this.playerActiveQuest.put(member, q.id);
            }
        }
        for (String ownerKey : this.db.getKeys("installment_plans")) {
            UUID owner3;
            try {
                owner3 = UUID.fromString(ownerKey);
            }
            catch (IllegalArgumentException ex) {
                continue;
            }
            for (String planKey : this.db.getKeys("installment_plans", ownerKey)) {
                String base4 = ownerKey + "." + planKey + ".";
                try {
                    InstallmentPlan plan = new InstallmentPlan();
                    plan.id = UUID.fromString(planKey);
                    plan.owner = owner3;
                    plan.description = this.db.getString("installment_plans", base4 + "description", "");
                    plan.installmentAmount = this.db.getDouble("installment_plans", base4 + "installment_amount", 0.0);
                    plan.installmentsRemaining = this.db.getInt("installment_plans", base4 + "installments_remaining", 0);
                    plan.nextDueTime = this.db.getLong("installment_plans", base4 + "next_due_time", 0L);
                    if (plan.installmentsRemaining <= 0) continue;
                    this.installmentPlans.computeIfAbsent(owner3, k -> new ArrayList()).add(plan);
                }
                catch (IllegalArgumentException plan) {}
            }
        }
        this.lotteryPool = this.db.getDouble("lottery", "pool", 0.0);
        this.lotteryDrawAt = this.db.getLong("lottery", "draw_at", 0L);
        this.lastLotteryWinnerName = this.db.getString("lottery", "last_winner_name", "-");
        this.lastLotteryWinnerAmount = this.db.getDouble("lottery", "last_winner_amount", 0.0);
        for (String key : this.db.getKeys("lottery", "tickets")) {
            try {
                this.lotteryTickets.put(UUID.fromString(key), this.db.getInt("lottery", "tickets." + key, 0));
            }
            catch (IllegalArgumentException owner4) {}
        }
        for (String key : this.db.getKeys("storage_rent")) {
            try {
                UUID u = UUID.fromString(key);
                this.storageRentDueTime.put(u, this.db.getLong("storage_rent", key, 0L));
                HashMap<Integer, ItemStack> slots = new HashMap<Integer, ItemStack>();
                for (String slotKey : this.db.getKeys("storage_items", key)) {
                    ItemStack item2 = this.db.getItemStack("storage_items", key + "." + slotKey);
                    if (item2 == null) continue;
                    try {
                        slots.put(Integer.parseInt(slotKey), item2);
                    }
                    catch (NumberFormatException alert) {}
                }
                if (slots.isEmpty()) continue;
                this.pendingStorageContents.put(u, slots);
            }
            catch (IllegalArgumentException u) {}
        }
        for (String key : this.db.getKeys("group_accounts")) {
            try {
                base2 = key + ".";
                GroupAccount acc = new GroupAccount();
                acc.id = UUID.fromString(key);
                acc.name = this.db.getString("group_accounts", (String)base2 + "name", "");
                acc.owner = UUID.fromString(this.db.getString("group_accounts", (String)base2 + "owner", null));
                acc.balance = this.db.getDouble("group_accounts", (String)base2 + "balance", 0.0);
                acc.members = new HashSet<UUID>(this.db.getStringList("group_accounts", (String)base2 + "members").stream().map(UUID::fromString).toList());
                this.groupAccounts.put(acc.id, acc);
            }
            catch (IllegalArgumentException base5) {}
        }
        this.playerGroupAccounts.clear();
        for (GroupAccount acc : this.groupAccounts.values()) {
            this.playerGroupAccounts.computeIfAbsent(acc.owner, k -> new HashSet()).add(acc.id);
            for (UUID m : acc.members) {
                this.playerGroupAccounts.computeIfAbsent(m, k -> new HashSet()).add(acc.id);
            }
        }
        for (String key : this.db.getKeys("investment_funds")) {
            try {
                base2 = key + ".";
                InvestmentFund fund = new InvestmentFund();
                fund.id = UUID.fromString(key);
                fund.name = this.db.getString("investment_funds", (String)base2 + "name", "");
                fund.manager = UUID.fromString(this.db.getString("investment_funds", (String)base2 + "manager", null));
                fund.cashBalance = this.db.getDouble("investment_funds", (String)base2 + "cash_balance", 0.0);
                for (String contribKey : this.db.getKeys("investment_funds", key + ".contributions")) {
                    try {
                        UUID contributor = UUID.fromString(contribKey);
                        double amount = this.db.getDouble("investment_funds", (String)base2 + "contributions." + contribKey, 0.0);
                        fund.contributions.put(contributor, amount);
                    }
                    catch (IllegalArgumentException illegalArgumentException) {}
                }
                this.investmentFunds.put(fund.id, fund);
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
        this.playerInvestmentFunds.clear();
        for (InvestmentFund fund : this.investmentFunds.values()) {
            this.playerInvestmentFunds.computeIfAbsent(fund.manager, k -> new HashSet()).add(fund.id);
            for (UUID contributor : fund.contributions.keySet()) {
                this.playerInvestmentFunds.computeIfAbsent(contributor, k -> new HashSet()).add(fund.id);
            }
        }
        for (String key2 : this.db.getKeys("dashboard_tokens")) {
            try {
                this.dashboardTokens.put(UUID.fromString(key2), this.db.getString("dashboard_tokens", key2, null));
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
        for (String key3 : this.db.getKeys("web_password")) {
            try {
                this.webPasswordHash.put(UUID.fromString(key3), this.db.getString("web_password", key3, null));
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
        for (String key4 : this.db.getKeys("web_admin_password")) {
            try {
                this.webAdminPasswordHash.put(UUID.fromString(key4), this.db.getString("web_admin_password", key4, null));
            }
            catch (IllegalArgumentException illegalArgumentException) {}
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void saveData() {
        if (this.db == null) {
            return;
        }
        this.db.beginTransaction();
        try {
            Object base;
            for (Map.Entry<UUID, Inventory> entry : this.storageInventories.entrySet()) {
                this.persistPlayerStorage(entry.getKey(), entry.getValue());
            }
            this.db.removeSection("storage_rent");
            for (Map.Entry<UUID, Object> entry : this.storageRentDueTime.entrySet()) {
                this.db.setLong("storage_rent", entry.getKey().toString(), (Long)entry.getValue());
            }
            this.db.removeSection("personal");
            for (UUID uUID : this.personalBank.keySet()) {
                this.db.setDouble("personal", uUID.toString(), this.personalBank.get(uUID));
            }
            this.db.setStringList("bankers", "_", this.bankers.stream().map(UUID::toString).toList());
            this.db.removeSection("capital");
            for (UUID uUID : this.bankCapital.keySet()) {
                this.db.setDouble("capital", uUID.toString(), this.bankCapital.get(uUID));
            }
            this.db.removeSection("market");
            for (UUID uUID : this.publishedLoans.keySet()) {
                this.db.setString("market", uUID.toString(), this.publishedLoans.get(uUID));
            }
            this.db.removeSection("debts");
            for (UUID uUID : this.activeDebts.keySet()) {
                this.db.setString("debts", uUID.toString(), this.activeDebts.get(uUID));
            }
            this.db.removeSection("fixed_deposit");
            for (UUID uUID : this.fixedDeposit.keySet()) {
                this.db.setDouble("fixed_deposit", uUID.toString(), this.fixedDeposit.get(uUID));
            }
            this.db.removeSection("fixed_time");
            for (UUID uUID : this.fixedDepositUnlockTime.keySet()) {
                this.db.setLong("fixed_time", uUID.toString(), this.fixedDepositUnlockTime.get(uUID));
            }
            this.db.removeSection("gov_debt");
            for (UUID uUID : this.govDebt.keySet()) {
                this.db.setDouble("gov_debt", uUID.toString(), this.govDebt.get(uUID));
            }
            this.db.removeSection("credit_score");
            for (UUID uUID : this.creditScore.keySet()) {
                this.db.setInt("credit_score", uUID.toString(), this.creditScore.get(uUID));
            }
            this.db.removeSection("gov_debt_due");
            for (UUID uUID : this.govDebtDueTime.keySet()) {
                this.db.setLong("gov_debt_due", uUID.toString(), this.govDebtDueTime.get(uUID));
            }
            this.db.removeSection("auction_seller");
            for (UUID uUID : this.auctionSeller.keySet()) {
                this.db.setString("auction_seller", uUID.toString(), this.auctionSeller.get(uUID).toString());
            }
            this.db.removeSection("auction_item");
            for (UUID uUID : this.auctionItem.keySet()) {
                this.db.setItemStack("auction_item", uUID.toString(), this.auctionItem.get(uUID));
            }
            this.db.removeSection("auction_bid");
            for (UUID uUID : this.auctionBid.keySet()) {
                this.db.setDouble("auction_bid", uUID.toString(), this.auctionBid.get(uUID));
            }
            this.db.removeSection("auction_bidder");
            for (UUID uUID : this.auctionBidder.keySet()) {
                this.db.setString("auction_bidder", uUID.toString(), this.auctionBidder.get(uUID).toString());
            }
            this.db.removeSection("auction_end");
            for (UUID uUID : this.auctionEndTime.keySet()) {
                this.db.setLong("auction_end", uUID.toString(), this.auctionEndTime.get(uUID));
            }
            this.db.removeSection("auction_buyout");
            for (UUID uUID : this.auctionBuyoutPrice.keySet()) {
                this.db.setDouble("auction_buyout", uUID.toString(), this.auctionBuyoutPrice.get(uUID));
            }
            this.db.removeSection("auction_pending");
            for (UUID uUID : this.auctionPendingItems.keySet()) {
                this.db.setItemStackList("auction_pending", uUID.toString(), this.auctionPendingItems.get(uUID));
            }
            this.db.removeSection("auction_draft");
            for (UUID uUID : this.auctionListingDraft.keySet()) {
                this.db.setItemStack("auction_draft", uUID.toString(), this.auctionListingDraft.get(uUID));
            }
            this.db.removeSection("auction_offline_notices");
            for (UUID uUID : this.auctionOfflineNotices.keySet()) {
                this.db.setStringList("auction_offline_notices", uUID.toString(), this.auctionOfflineNotices.get(uUID));
            }
            this.db.removeSection("auction_total_sold");
            for (UUID uUID : this.auctionTotalSoldAmount.keySet()) {
                this.db.setDouble("auction_total_sold", uUID.toString(), this.auctionTotalSoldAmount.get(uUID));
            }
            this.db.removeSection("loan_offline_notices");
            for (UUID uUID : this.loanOfflineNotices.keySet()) {
                this.db.setStringList("loan_offline_notices", uUID.toString(), this.loanOfflineNotices.get(uUID));
            }
            this.db.removeSection("collateral_item");
            for (UUID uUID : this.collateralItem.keySet()) {
                this.db.setItemStack("collateral_item", uUID.toString(), this.collateralItem.get(uUID));
            }
            this.db.removeSection("collateral_loan");
            for (UUID uUID : this.collateralLoanAmount.keySet()) {
                this.db.setDouble("collateral_loan", uUID.toString(), this.collateralLoanAmount.get(uUID));
            }
            this.db.removeSection("collateral_due");
            for (UUID uUID : this.collateralDueTime.keySet()) {
                this.db.setLong("collateral_due", uUID.toString(), this.collateralDueTime.get(uUID));
            }
            this.db.removeSection("collateral_lender");
            for (UUID uUID : this.collateralLender.keySet()) {
                this.db.setString("collateral_lender", uUID.toString(), this.collateralLender.get(uUID).toString());
            }
            this.db.removeSection("insurance_expiry");
            for (UUID uUID : this.insuranceExpiry.keySet()) {
                this.db.setLong("insurance_expiry", uUID.toString(), this.insuranceExpiry.get(uUID));
            }
            this.db.removeSection("insurance_last_claim");
            for (UUID uUID : this.insuranceLastClaim.keySet()) {
                this.db.setLong("insurance_last_claim", uUID.toString(), this.insuranceLastClaim.get(uUID));
            }
            this.db.removeSection("vip_stipend_claimed");
            for (UUID uUID : this.vipStipendClaimedAt.keySet()) {
                this.db.setLong("vip_stipend_claimed", uUID.toString(), this.vipStipendClaimedAt.get(uUID));
            }
            this.db.removeSection("achievements");
            for (UUID uUID : this.unlockedAchievements.keySet()) {
                this.db.setStringList("achievements", uUID.toString(), new ArrayList<String>((Collection)this.unlockedAchievements.get(uUID)));
            }
            this.db.removeSection("fixed_deposit2");
            for (UUID uUID : this.fixedDeposit2.keySet()) {
                this.db.setDouble("fixed_deposit2", uUID.toString(), this.fixedDeposit2.get(uUID));
            }
            this.db.removeSection("fixed_time2");
            for (UUID uUID : this.fixedDepositUnlockTime2.keySet()) {
                this.db.setLong("fixed_time2", uUID.toString(), this.fixedDepositUnlockTime2.get(uUID));
            }
            this.db.removeSection("fixed_deposit3");
            for (UUID uUID : this.fixedDeposit3.keySet()) {
                this.db.setDouble("fixed_deposit3", uUID.toString(), this.fixedDeposit3.get(uUID));
            }
            this.db.removeSection("fixed_time3");
            for (UUID uUID : this.fixedDepositUnlockTime3.keySet()) {
                this.db.setLong("fixed_time3", uUID.toString(), this.fixedDepositUnlockTime3.get(uUID));
            }
            this.db.removeSection("market2");
            for (UUID uUID : this.publishedLoans2.keySet()) {
                this.db.setString("market2", uUID.toString(), this.publishedLoans2.get(uUID));
            }
            this.db.removeSection("market3");
            for (UUID uUID : this.publishedLoans3.keySet()) {
                this.db.setString("market3", uUID.toString(), this.publishedLoans3.get(uUID));
            }
            this.db.setStringList("news_off", "_", this.newsBroadcastOff.stream().map(UUID::toString).toList());
            this.db.setStringList("hub_item_issued", "_", this.hubItemIssued.stream().map(UUID::toString).toList());
            this.db.removeSection("tx_logs");
            for (UUID uUID : this.transactionLogs.keySet()) {
                this.db.setStringList("tx_logs", uUID.toString(), new ArrayList<String>((Collection)this.transactionLogs.get(uUID)));
            }
            this.db.setDouble("treasury", "_", this.treasury);
            this.db.setDouble("weekly_trade_volume", "_", this.weeklyTradeVolume);
            this.db.removeSection("donation_score_today");
            for (UUID uUID : this.donationScoreToday.keySet()) {
                this.db.setInt("donation_score_today", uUID.toString(), this.donationScoreToday.get(uUID));
            }
            this.db.removeSection("donation_score_reset_at");
            for (UUID uUID : this.donationScoreResetAt.keySet()) {
                this.db.setLong("donation_score_reset_at", uUID.toString(), this.donationScoreResetAt.get(uUID));
            }
            this.db.removeSection("welfare_count_today");
            for (UUID uUID : this.welfareCountToday.keySet()) {
                this.db.setInt("welfare_count_today", uUID.toString(), this.welfareCountToday.get(uUID));
            }
            this.db.removeSection("welfare_count_reset_at");
            for (UUID uUID : this.welfareCountResetAt.keySet()) {
                this.db.setLong("welfare_count_reset_at", uUID.toString(), this.welfareCountResetAt.get(uUID));
            }
            this.db.removeSection("loan_guarantor");
            for (UUID uUID : this.loanGuarantor.keySet()) {
                this.db.setString("loan_guarantor", uUID.toString(), this.loanGuarantor.get(uUID).toString());
            }
            this.db.removeSection("guarantor_proposals");
            for (UUID uUID : this.guarantorProposals.keySet()) {
                this.db.setString("guarantor_proposals", uUID.toString(), this.guarantorProposals.get(uUID).toString());
            }
            this.db.removeSection("guarantor_proposal_time");
            for (UUID uUID : this.guarantorProposalTime.keySet()) {
                this.db.setLong("guarantor_proposal_time", uUID.toString(), this.guarantorProposalTime.get(uUID));
            }
            this.db.setString("economy-event", "name", this.activeEconomyEvent);
            this.db.setLong("economy-event", "until", this.activeEconomyEventUntil);
            this.db.setStringList("tutorial-seen", "_", this.tutorialSeen.stream().map(UUID::toString).toList());
            this.db.removeSection("quests");
            for (Quest quest : this.quests.values()) {
                String base2 = String.valueOf(quest.id) + ".";
                this.db.setString("quests", base2 + "poster", quest.posterId.toString());
                this.db.setString("quests", base2 + "world", quest.worldName);
                this.db.setDouble("quests", base2 + "x", quest.x);
                this.db.setDouble("quests", base2 + "y", quest.y);
                this.db.setDouble("quests", base2 + "z", quest.z);
                this.db.setDouble("quests", base2 + "reward", quest.reward);
                this.db.setString("quests", base2 + "state", quest.state.name());
                this.db.setString("quests", base2 + "acceptedBy", quest.acceptedBy != null ? quest.acceptedBy.toString() : null);
                this.db.setLong("quests", base2 + "cooldownUntil", quest.cooldownUntil);
                this.db.setInt("quests", base2 + "requiredTeamSize", quest.requiredTeamSize);
                this.db.setStringList("quests", base2 + "teamMembers", quest.teamMembers.stream().map(UUID::toString).toList());
                this.db.setStringList("quests", base2 + "teamArrived", quest.teamArrived.stream().map(UUID::toString).toList());
            }
            this.db.removeSection("world_stocks");
            for (Map.Entry entry : this.playerWorldStocks.entrySet()) {
                UUID owner = (UUID)entry.getKey();
                HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(owner, new HashMap());
                ArrayList<String> arrayList = new ArrayList<String>();
                for (Map.Entry holding : ((HashMap)entry.getValue()).entrySet()) {
                    if ((Integer)holding.getValue() <= 0) continue;
                    arrayList.add((String)holding.getKey() + ";" + String.valueOf(holding.getValue()) + ";" + String.valueOf(avgCosts.getOrDefault(holding.getKey(), 0.0)));
                }
                if (arrayList.isEmpty()) continue;
                this.db.setStringList("world_stocks", owner.toString(), arrayList);
            }
            this.db.removeSection("world_stock_daily");
            for (UUID uUID : this.worldStockTradesToday.keySet()) {
                base = String.valueOf(uUID) + ".";
                this.db.setInt("world_stock_daily", (String)base + "trades", this.worldStockTradesToday.get(uUID));
                this.db.setDouble("world_stock_daily", (String)base + "amount", this.worldStockAmountToday.getOrDefault(uUID, 0.0));
                this.db.setDouble("world_stock_daily", (String)base + "profit", this.worldStockProfitToday.getOrDefault(uUID, 0.0));
                this.db.setLong("world_stock_daily", (String)base + "reset_at", this.worldStockDailyResetAt.getOrDefault(uUID, 0L));
            }
            this.db.removeSection("world_stock_last_trade");
            for (Map.Entry entry : this.worldStockLastTradeTime.entrySet()) {
                ArrayList lines = new ArrayList();
                for (Map.Entry entry2 : ((HashMap)entry.getValue()).entrySet()) {
                    lines.add((String)entry2.getKey() + ";" + String.valueOf(entry2.getValue()));
                }
                if (lines.isEmpty()) continue;
                this.db.setStringList("world_stock_last_trade", ((UUID)entry.getKey()).toString(), lines);
            }
            this.db.removeSection("world_stock_alerts");
            for (Map.Entry entry : this.worldStockAlerts.entrySet()) {
                for (Object alert : (List)entry.getValue()) {
                    String string = String.valueOf(entry.getKey()) + "." + String.valueOf(((WorldStockAlert)alert).id) + ".";
                    this.db.setString("world_stock_alerts", string + "symbol", ((WorldStockAlert)alert).symbol);
                    this.db.setDouble("world_stock_alerts", string + "baseline", ((WorldStockAlert)alert).baselinePrice);
                    this.db.setDouble("world_stock_alerts", string + "threshold", ((WorldStockAlert)alert).thresholdPercent);
                }
            }
            this.db.removeSection("resource_prices");
            for (Map.Entry entry : this.resourcePrices.entrySet()) {
                this.db.setDouble("resource_prices", ((Material)entry.getKey()).name(), (Double)entry.getValue());
            }
            this.db.removeSection("resource_personal_buy");
            for (Map.Entry entry : this.resourcePersonalBuyMultiplier.entrySet()) {
                ArrayList<String> list = new ArrayList<String>();
                for (Map.Entry entry3 : ((HashMap)entry.getValue()).entrySet()) {
                    list.add(((Material)entry3.getKey()).name() + ";" + String.valueOf(entry3.getValue()));
                }
                if (list.isEmpty()) continue;
                this.db.setStringList("resource_personal_buy", ((UUID)entry.getKey()).toString(), list);
            }
            this.db.setLong("traveling_merchant", "refresh_at", this.merchantRefreshAt);
            this.db.removeByPathPrefix("traveling_merchant", "deals");
            for (MerchantDeal merchantDeal : this.merchantDeals) {
                base = "deals." + String.valueOf(merchantDeal.id) + ".";
                this.db.setString("traveling_merchant", (String)base + "material", merchantDeal.material.name());
                this.db.setDouble("traveling_merchant", (String)base + "normal_price", merchantDeal.normalPrice);
                this.db.setDouble("traveling_merchant", (String)base + "discount_percent", merchantDeal.discountPercent);
                this.db.setInt("traveling_merchant", (String)base + "stock_total", merchantDeal.stockTotal);
                this.db.setInt("traveling_merchant", (String)base + "stock_remaining", merchantDeal.stockRemaining);
            }
            this.db.setLong("vip_shop", "refresh_at", this.vipShopRefreshAt);
            this.db.removeByPathPrefix("vip_shop", "deals");
            for (VipDeal vipDeal : this.vipShopDeals) {
                base = "deals." + String.valueOf(vipDeal.id) + ".";
                this.db.setString("vip_shop", (String)base + "material", vipDeal.material.name());
                this.db.setDouble("vip_shop", (String)base + "normal_price", vipDeal.normalPrice);
                this.db.setDouble("vip_shop", (String)base + "discount_percent", vipDeal.discountPercent);
                this.db.setInt("vip_shop", (String)base + "stock_total", vipDeal.stockTotal);
                this.db.setInt("vip_shop", (String)base + "stock_remaining", vipDeal.stockRemaining);
            }
            if (this.merchantWorldName != null) {
                this.db.setString("traveling_merchant", "location.world", this.merchantWorldName);
                this.db.setDouble("traveling_merchant", "location.x", this.merchantX);
                this.db.setDouble("traveling_merchant", "location.y", this.merchantY);
                this.db.setDouble("traveling_merchant", "location.z", this.merchantZ);
            }
            this.db.setBoolean("treasure", "active", this.treasureActive);
            this.db.setString("treasure", "world", this.treasureWorldName);
            this.db.setInt("treasure", "x", this.treasureX);
            this.db.setInt("treasure", "y", this.treasureY);
            this.db.setInt("treasure", "z", this.treasureZ);
            this.db.setDouble("treasure", "reward", this.treasureReward);
            this.db.setLong("treasure", "next-spawn-at", this.treasureNextSpawnAt);
            this.db.removeSection("installment_plans");
            for (Map.Entry entry : this.installmentPlans.entrySet()) {
                for (InstallmentPlan plan : (List)entry.getValue()) {
                    String string = String.valueOf(entry.getKey()) + "." + String.valueOf(plan.id) + ".";
                    this.db.setString("installment_plans", string + "description", plan.description);
                    this.db.setDouble("installment_plans", string + "installment_amount", plan.installmentAmount);
                    this.db.setInt("installment_plans", string + "installments_remaining", plan.installmentsRemaining);
                    this.db.setLong("installment_plans", string + "next_due_time", plan.nextDueTime);
                }
            }
            this.db.setDouble("lottery", "pool", this.lotteryPool);
            this.db.setLong("lottery", "draw_at", this.lotteryDrawAt);
            this.db.setString("lottery", "last_winner_name", this.lastLotteryWinnerName);
            this.db.setDouble("lottery", "last_winner_amount", this.lastLotteryWinnerAmount);
            this.db.removeByPathPrefix("lottery", "tickets");
            for (Map.Entry entry : this.lotteryTickets.entrySet()) {
                this.db.setInt("lottery", "tickets." + String.valueOf(entry.getKey()), (Integer)entry.getValue());
            }
            this.db.removeSection("group_accounts");
            for (GroupAccount groupAccount : this.groupAccounts.values()) {
                base = String.valueOf(groupAccount.id) + ".";
                this.db.setString("group_accounts", (String)base + "name", groupAccount.name);
                this.db.setString("group_accounts", (String)base + "owner", groupAccount.owner.toString());
                this.db.setDouble("group_accounts", (String)base + "balance", groupAccount.balance);
                this.db.setStringList("group_accounts", (String)base + "members", groupAccount.members.stream().map(UUID::toString).toList());
            }
            this.db.removeSection("investment_funds");
            for (InvestmentFund investmentFund : this.investmentFunds.values()) {
                base = String.valueOf(investmentFund.id) + ".";
                this.db.setString("investment_funds", (String)base + "name", investmentFund.name);
                this.db.setString("investment_funds", (String)base + "manager", investmentFund.manager.toString());
                this.db.setDouble("investment_funds", (String)base + "cash_balance", investmentFund.cashBalance);
                for (Map.Entry<UUID, Double> entry : investmentFund.contributions.entrySet()) {
                    this.db.setDouble("investment_funds", (String)base + "contributions." + String.valueOf(entry.getKey()), entry.getValue());
                }
            }
            this.db.removeSection("dashboard_tokens");
            for (Map.Entry entry : this.dashboardTokens.entrySet()) {
                this.db.setString("dashboard_tokens", ((UUID)entry.getKey()).toString(), (String)entry.getValue());
            }
            this.db.removeSection("web_password");
            for (Map.Entry entry : this.webPasswordHash.entrySet()) {
                this.db.setString("web_password", ((UUID)entry.getKey()).toString(), (String)entry.getValue());
            }
            this.db.removeSection("web_admin_password");
            for (Map.Entry entry : this.webAdminPasswordHash.entrySet()) {
                this.db.setString("web_admin_password", ((UUID)entry.getKey()).toString(), (String)entry.getValue());
            }
        }
        finally {
            this.db.endTransaction();
        }
    }

    private int getScore(UUID u) {
        return this.creditScore.getOrDefault(u, 300);
    }

    private void addScore(UUID u, int amount) {
        int s = Math.max(0, Math.min(800, this.getScore(u) + amount));
        this.creditScore.put(u, s);
        if (s >= 700) {
            this.unlockAchievement(u, "credit_master", "\u4fe1\u7528\u306e\u9451");
        }
    }

    private boolean isDailyCounterResetDue(UUID u, HashMap<UUID, Long> resetAtMap, long now) {
        long resetAt = resetAtMap.getOrDefault(u, 0L);
        if (now >= resetAt) {
            resetAtMap.put(u, now + 86400000L);
            return true;
        }
        return false;
    }

    private void grantDonationCreditScore(UUID u, double donationAmount) {
        int usedToday;
        int remaining;
        long now = System.currentTimeMillis();
        if (this.isDailyCounterResetDue(u, this.donationScoreResetAt, now)) {
            this.donationScoreToday.put(u, 0);
        }
        if ((remaining = Math.max(0, this.cfgDonationScoreCapPerDay - (usedToday = this.donationScoreToday.getOrDefault(u, 0).intValue()))) <= 0) {
            return;
        }
        int grant = (int)Math.min((double)remaining, Math.max(1.0, donationAmount / 1000.0));
        this.addScore(u, grant);
        this.donationScoreToday.put(u, usedToday + grant);
    }

    private void ensureWorldStockDailyReset(UUID u) {
        long now = System.currentTimeMillis();
        if (this.isDailyCounterResetDue(u, this.worldStockDailyResetAt, now)) {
            this.worldStockTradesToday.put(u, 0);
            this.worldStockAmountToday.put(u, 0.0);
            this.worldStockProfitToday.put(u, 0.0);
        }
    }

    private boolean enforceWorldStockDailyLimits(Player p, UUID u, String symbol, double tradeAmount) {
        double maxAmountPerDay;
        int maxTradesPerDay;
        this.ensureWorldStockDailyReset(u);
        long now = System.currentTimeMillis();
        UUID vipRef = this.investmentFunds.containsKey(u) ? this.investmentFunds.get((Object)u).manager : u;
        double vipMult = this.vipTradeLimitMultiplier(vipRef);
        HashMap myLastTrade = this.worldStockLastTradeTime.computeIfAbsent(u, k -> new HashMap());
        long lastTrade = myLastTrade.getOrDefault(symbol, 0L);
        long cooldownMs = (long)Math.max(0, this.cfgWorldStockTradeCooldownSeconds) * 1000L;
        if (now - lastTrade < cooldownMs) {
            if (p != null) {
                long remainSeconds = (cooldownMs - (now - lastTrade) + 999L) / 1000L;
                this.msgKey(p, "worldstock.cooldown", "seconds", String.valueOf(remainSeconds));
                this.errorSound(p);
            }
            return true;
        }
        int tradesToday = this.worldStockTradesToday.getOrDefault(u, 0);
        if (tradesToday >= (maxTradesPerDay = (int)Math.round((double)this.cfgWorldStockMaxTradesPerDay * vipMult))) {
            if (p != null) {
                this.msgKey(p, "worldstock.daily-count-limit", "count", String.valueOf(maxTradesPerDay));
                this.errorSound(p);
            }
            return true;
        }
        double amountToday = this.worldStockAmountToday.getOrDefault(u, 0.0);
        if (amountToday + tradeAmount > (maxAmountPerDay = this.cfgWorldStockMaxAmountPerDay * vipMult)) {
            if (p != null) {
                this.msgKey(p, "worldstock.daily-amount-limit", "amount", String.format("%.2f", maxAmountPerDay));
                this.errorSound(p);
            }
            return true;
        }
        return false;
    }

    private double effectiveWorldStockMaxProfitPerDay(UUID trackedUuid) {
        UUID vipRef = this.investmentFunds.containsKey(trackedUuid) ? this.investmentFunds.get((Object)trackedUuid).manager : trackedUuid;
        return this.cfgWorldStockMaxProfitPerDay * this.vipTradeLimitMultiplier(vipRef);
    }

    private void recordWorldStockTrade(UUID u, String symbol, double tradeAmount, double profitIfPositive) {
        long now = System.currentTimeMillis();
        this.worldStockTradesToday.put(u, this.worldStockTradesToday.getOrDefault(u, 0) + 1);
        this.worldStockAmountToday.put(u, this.worldStockAmountToday.getOrDefault(u, 0.0) + tradeAmount);
        this.worldStockLastTradeTime.computeIfAbsent(u, k -> new HashMap()).put(symbol, now);
        if (profitIfPositive > 0.0) {
            this.worldStockProfitToday.put(u, this.worldStockProfitToday.getOrDefault(u, 0.0) + profitIfPositive);
        }
    }

    private void refreshFxRates() {
        for (String raw : this.cfgFxCurrencies) {
            String code;
            if (raw == null || (code = raw.trim().toUpperCase()).isEmpty() || code.equals("JPY")) continue;
            this.fetchWorldStockQuote(code + "JPY=X", q -> {
                if (q != null && q.price > 0.0) {
                    this.fxRateCache.put(code, q.price);
                    this.fxRateCacheTime.put(code, System.currentTimeMillis());
                }
            });
        }
    }

    private double fxRateToJpy(String currency) {
        if (currency == null || currency.equalsIgnoreCase("JPY")) {
            return 1.0;
        }
        String code = currency.toUpperCase();
        Double live = this.fxRateCache.get(code);
        Long at = this.fxRateCacheTime.get(code);
        if (live != null && live > 0.0 && at != null && System.currentTimeMillis() - at < 3600000L) {
            return live;
        }
        return this.cfgFxFallbackRates.getOrDefault(code, this.cfgFxFallbackRateDefault);
    }

    private double worldStockYenPrice(WorldStockQuote q) {
        return q.price * this.fxRateToJpy(q.currency);
    }

    private String fmtYenAmount(double v) {
        if (this.cfgCustomMoneyEnabled) {
            return String.format("%,.2f", v / this.cfgCustomMoneyRate) + this.cfgCustomMoneySymbol;
        }
        return String.format("%,.0f", v) + this.cfgCurrencyUnit;
    }

    private String worldStockNativePrice(WorldStockQuote q) {
        if (q.currency == null || q.currency.equalsIgnoreCase("JPY")) {
            return String.format("%,.2f", q.price) + "\u5186";
        }
        return String.format("%,.2f", q.price) + " " + q.currency;
    }

    private String worldStockNativeAmountLabel(WorldStockQuote q, int qty) {
        return this.worldStockNativePrice(q) + "/\u682a";
    }

    private double worldStockPositionValue(WorldStockQuote q, double avgCostFallback, int qty) {
        double unitValue = q != null ? this.worldStockYenPrice(q) : avgCostFallback;
        return unitValue * (double)qty;
    }

    private String worldStockPnlLine(double pnl) {
        return pnl >= 0.0 ? "<green>\u8a55\u4fa1\u640d\u76ca: +" + (long)pnl + "\u5186</green>" : "<red>\u8a55\u4fa1\u640d\u76ca: " + (long)pnl + "\u5186</red>";
    }

    private String worldStockPriceLine(String prefix, WorldStockQuote q) {
        double yen = this.worldStockYenPrice(q);
        if (q.currency == null || q.currency.equalsIgnoreCase("JPY")) {
            return prefix + "<white>" + String.format("%,.2f", q.price) + "\u5186</white>";
        }
        return prefix + "<white>" + String.format("%,.2f", q.price) + " " + q.currency + "</white> <gray>(\u2248 " + this.fmtYenAmount(yen) + ")</gray>";
    }

    private void executeWorldStockBuy(OfflinePlayer actor, String symbol, int qty, InvestmentFund fund) {
        boolean affordable;
        Player pl;
        UUID actorU = actor.getUniqueId();
        Player online = actor instanceof Player && (pl = (Player)actor).isOnline() ? pl : null;
        WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
        if (q == null) {
            if (online != null) {
                this.errorSound(online);
            }
            return;
        }
        if (qty > this.cfgWorldStockMaxBulkQty) {
            if (online != null) {
                this.msgKey(online, "worldstock.bulk-qty-too-large", "max", String.valueOf(this.cfgWorldStockMaxBulkQty));
                this.errorSound(online);
            }
            return;
        }
        UUID trackedUuid = fund != null ? fund.id : actorU;
        UUID feeContextUuid = fund != null ? fund.manager : actorU;
        double feeRate = this.effectiveWorldStockFeeRate(feeContextUuid);
        double yenPrice = this.worldStockYenPrice(q);
        double perShareCost = yenPrice * (1.0 + feeRate);
        double totalCost = perShareCost * (double)qty;
        double totalFee = yenPrice * feeRate * (double)qty;
        boolean bl = fund != null ? fund.cashBalance >= totalCost : (affordable = econ.getBalance(actor) >= totalCost);
        if (!affordable) {
            if (online != null) {
                this.msgKey(online, fund != null ? "fund.trade-funds-insufficient" : "worldstock.funds-insufficient", new String[0]);
                this.errorSound(online);
            }
            return;
        }
        if (this.enforceWorldStockDailyLimits(online, trackedUuid, symbol, totalCost)) {
            return;
        }
        HashMap holdings = this.playerWorldStocks.computeIfAbsent(trackedUuid, k -> new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.computeIfAbsent(trackedUuid, k -> new HashMap());
        int oldQty = holdings.getOrDefault(symbol, 0);
        double oldAvg = avgCosts.getOrDefault(symbol, 0.0);
        if (fund != null) {
            fund.cashBalance -= totalCost;
        } else {
            econ.withdrawPlayer(actor, totalCost);
        }
        this.treasury += totalFee;
        double newAvg = (oldAvg * (double)oldQty + perShareCost * (double)qty) / (double)(oldQty + qty);
        holdings.put(symbol, oldQty + qty);
        avgCosts.put(symbol, newAvg);
        this.recordWorldStockTrade(trackedUuid, symbol, totalCost, 0.0);
        if (online != null) {
            this.msgKey(online, "worldstock.bought", "symbol", symbol, "qty", String.valueOf(qty), "amount", String.format("%.2f", totalCost), "fee", String.format("%.2f", totalFee), "native", this.worldStockNativeAmountLabel(q, qty));
        }
        if (fund != null) {
            this.addLog(actorU, "\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d: " + symbol + " \u3092" + qty + "\u682a\u8cfc\u5165 -" + (long)totalCost + "\u5186(\u624b\u6570\u6599\u8fbc\u307f\u3001\u30d5\u30a1\u30f3\u30c9\u8cc7\u91d1)");
            this.sendDiscordWebhook("\ud83d\udcca **" + actor.getName() + "** \u304c\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u3067\u73fe\u5b9f\u682a **" + symbol + "** \u3092" + qty + "\u682a\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\u4fa1\u683c: " + String.format("%.2f", totalCost) + "\u5186(\u624b\u6570\u6599\u8fbc\u307f)");
        } else {
            this.addLog(actorU, "\u4e16\u754c\u682a\u5f0f\u5e02\u5834: " + symbol + " \u3092" + qty + "\u682a\u8cfc\u5165 -" + (long)totalCost + "\u5186(\u624b\u6570\u6599\u8fbc\u307f)" + (online == null ? "(Web/\u30aa\u30d5\u30e9\u30a4\u30f3)" : ""));
            this.sendDiscordWebhook("\ud83d\udcc8 **" + actor.getName() + "** \u304c\u73fe\u5b9f\u682a **" + symbol + "** \u3092" + qty + "\u682a\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\u4fa1\u683c: " + String.format("%.2f", totalCost) + "\u5186(\u624b\u6570\u6599\u8fbc\u307f)");
        }
        if (online != null) {
            online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            if (fund != null) {
                this.openFundStockDetailGUI(online, fund.id, symbol);
            } else {
                this.openWorldStockDetailGUI(online, symbol);
            }
        }
    }

    private void executeWorldStockSell(Player actor, String symbol, int qty, InvestmentFund fund) {
        UUID actorU = actor.getUniqueId();
        WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
        if (q == null) {
            this.errorSound(actor);
            return;
        }
        if (qty > this.cfgWorldStockMaxBulkQty) {
            this.msgKey(actor, "worldstock.bulk-qty-too-large", "max", String.valueOf(this.cfgWorldStockMaxBulkQty));
            this.errorSound(actor);
            return;
        }
        UUID trackedUuid = fund != null ? fund.id : actorU;
        UUID feeContextUuid = fund != null ? fund.manager : actorU;
        HashMap holdings = this.playerWorldStocks.computeIfAbsent(trackedUuid, k -> new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.computeIfAbsent(trackedUuid, k -> new HashMap());
        int oldQty = holdings.getOrDefault(symbol, 0);
        if (oldQty <= 0) {
            this.msgKey(actor, "worldstock.not-owned", new String[0]);
            this.errorSound(actor);
            return;
        }
        if (oldQty < qty) {
            this.msgKey(actor, "worldstock.sell-not-enough-shares", "owned", String.valueOf(oldQty), "requested", String.valueOf(qty));
            this.errorSound(actor);
            return;
        }
        double feeRate = this.effectiveWorldStockFeeRate(feeContextUuid);
        double yenPrice = this.worldStockYenPrice(q);
        double perShareProceeds = yenPrice * (1.0 - feeRate);
        double totalProceeds = perShareProceeds * (double)qty;
        double totalFee = yenPrice * feeRate * (double)qty;
        double oldAvg = avgCosts.getOrDefault(symbol, 0.0);
        double bulkProfit = (perShareProceeds - oldAvg) * (double)qty;
        if (this.enforceWorldStockDailyLimits(actor, trackedUuid, symbol, totalProceeds)) {
            return;
        }
        if (bulkProfit > 0.0 && this.worldStockProfitToday.getOrDefault(trackedUuid, 0.0) + bulkProfit > this.effectiveWorldStockMaxProfitPerDay(trackedUuid)) {
            this.msgKey(actor, "worldstock.daily-profit-limit", "amount", String.format("%.2f", this.effectiveWorldStockMaxProfitPerDay(trackedUuid)));
            this.errorSound(actor);
            return;
        }
        if (fund != null) {
            fund.cashBalance += totalProceeds;
        } else {
            econ.depositPlayer((OfflinePlayer)actor, totalProceeds);
        }
        this.treasury += totalFee;
        int remaining = oldQty - qty;
        if (remaining <= 0) {
            holdings.remove(symbol);
            avgCosts.remove(symbol);
        } else {
            holdings.put(symbol, remaining);
        }
        this.recordWorldStockTrade(trackedUuid, symbol, totalProceeds, bulkProfit);
        this.msgKey(actor, "worldstock.sold", "symbol", symbol, "qty", String.valueOf(qty), "amount", String.format("%.2f", totalProceeds), "pnl", (bulkProfit >= 0.0 ? "+" : "") + String.format("%.2f", bulkProfit), "fee", String.format("%.2f", totalFee), "native", this.worldStockNativeAmountLabel(q, qty));
        if (fund != null) {
            this.addLog(actorU, "\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d: " + symbol + " \u3092" + qty + "\u682a\u58f2\u5374 +" + (long)totalProceeds + "\u5186(\u624b\u6570\u6599\u8fbc\u307f\u3001\u30d5\u30a1\u30f3\u30c9\u8cc7\u91d1)");
            this.sendDiscordWebhook("\ud83d\udcca **" + actor.getName() + "** \u304c\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u3067\u73fe\u5b9f\u682a **" + symbol + "** \u3092" + qty + "\u682a\u58f2\u5374\u3057\u307e\u3057\u305f\u3002\u58f2\u5374\u984d: " + String.format("%.2f", totalProceeds) + "\u5186(\u624b\u6570\u6599\u8fbc\u307f)");
        } else {
            this.addLog(actorU, "\u4e16\u754c\u682a\u5f0f\u5e02\u5834: " + symbol + " \u3092" + qty + "\u682a\u58f2\u5374 +" + (long)totalProceeds + "\u5186(\u624b\u6570\u6599\u8fbc\u307f)");
            this.sendDiscordWebhook("\ud83d\udcc9 **" + actor.getName() + "** \u304c\u73fe\u5b9f\u682a **" + symbol + "** \u3092" + qty + "\u682a\u58f2\u5374\u3057\u307e\u3057\u305f\u3002\u58f2\u5374\u984d: " + String.format("%.2f", totalProceeds) + "\u5186(\u624b\u6570\u6599\u8fbc\u307f)");
        }
        actor.playSound(actor.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
        if (fund != null) {
            this.openFundStockDetailGUI(actor, fund.id, symbol);
        } else {
            this.openWorldStockDetailGUI(actor, symbol);
        }
    }

    private double getResourcePrice(Material mat) {
        return this.resourcePrices.getOrDefault(mat, RESOURCE_BASE_PRICES.getOrDefault(mat, 0.0));
    }

    private double getResourceEffectivePrice(Material mat) {
        return this.getResourcePrice(mat) * this.economyMultiplier();
    }

    private void applyResourceTradeImpact(Material mat, int qty, boolean isBuy) {
        double base = RESOURCE_BASE_PRICES.getOrDefault(mat, 0.0);
        if (base <= 0.0) {
            return;
        }
        double price = this.getResourcePrice(mat);
        double factor = isBuy ? 1.0 + this.cfgResourcePriceImpactRate : 1.0 - this.cfgResourcePriceImpactRate;
        price *= Math.pow(factor, qty);
        double floor = base * this.cfgResourcePriceFloorPercent;
        double ceiling = base * this.cfgResourcePriceCeilingPercent;
        price = Math.max(floor, Math.min(ceiling, price));
        this.resourcePrices.put(mat, price);
    }

    private void driftResourcePrices() {
        for (Map.Entry<Material, Double> entry : RESOURCE_BASE_PRICES.entrySet()) {
            Material mat = entry.getKey();
            double base = entry.getValue();
            double price = this.getResourcePrice(mat);
            price += (base - price) * this.cfgResourcePriceReversionRate;
            this.resourcePrices.put(mat, price);
        }
        this.decayResourcePersonalBuyMultipliers();
    }

    private double getResourcePersonalMultiplier(UUID u, Material mat) {
        HashMap<Material, Double> map = this.resourcePersonalBuyMultiplier.get(u);
        if (map == null) {
            return 1.0;
        }
        return map.getOrDefault(mat, 1.0);
    }

    private void applyResourcePersonalImpact(UUID u, Material mat, int qty) {
        HashMap map = this.resourcePersonalBuyMultiplier.computeIfAbsent(u, k -> new HashMap());
        double mult = map.getOrDefault(mat, 1.0) * Math.pow(1.0 + this.cfgResourcePersonalImpactRate, qty);
        map.put(mat, Math.min(mult, this.cfgResourcePersonalCeilingPercent));
    }

    private void decayResourcePersonalBuyMultipliers() {
        Iterator<Map.Entry<UUID, HashMap<Material, Double>>> it = this.resourcePersonalBuyMultiplier.entrySet().iterator();
        while (it.hasNext()) {
            HashMap<Material, Double> map = it.next().getValue();
            Iterator<Map.Entry<Material, Double>> it2 = map.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry<Material, Double> e = it2.next();
                double mult = e.getValue() + (1.0 - e.getValue()) * this.cfgResourcePriceReversionRate;
                if (mult <= 1.01) {
                    it2.remove();
                    continue;
                }
                e.setValue(mult);
            }
            if (!map.isEmpty()) continue;
            it.remove();
        }
    }

    private int countMaterialInInventory(Player p, Material mat) {
        int total = 0;
        for (ItemStack item : p.getInventory().getStorageContents()) {
            if (item == null || item.getType() != mat) continue;
            total += item.getAmount();
        }
        return total;
    }

    private void removeMaterialFromInventory(Player p, Material mat, int amount) {
        ItemStack[] contents = p.getInventory().getStorageContents();
        for (int i = 0; i < contents.length && amount > 0; ++i) {
            ItemStack item = contents[i];
            if (item == null || item.getType() != mat) continue;
            int take = Math.min(amount, item.getAmount());
            item.setAmount(item.getAmount() - take);
            amount -= take;
            if (item.getAmount() <= 0) {
                p.getInventory().setItem(i, null);
                continue;
            }
            p.getInventory().setItem(i, item);
        }
    }

    private void executeResourceSell(Player p, Material mat, int qty) {
        UUID u = p.getUniqueId();
        int have = this.countMaterialInInventory(p, mat);
        if (have < qty) {
            this.msgKey(p, "resourceshop.not-enough-items", "material", this.resourceDisplayName(mat), "have", String.valueOf(have), "need", String.valueOf(qty));
            this.errorSound(p);
            return;
        }
        double unitPrice = this.getResourceEffectivePrice(mat);
        double total = unitPrice * (double)qty;
        this.removeMaterialFromInventory(p, mat, qty);
        econ.depositPlayer((OfflinePlayer)p, total);
        this.applyResourceTradeImpact(mat, qty, false);
        this.addLog(u, "\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7: " + this.resourceDisplayName(mat) + " \u3092" + qty + "\u500b\u58f2\u5374 +" + this.fmtCur(total));
        if (qty >= 16) {
            this.sendDiscordWebhook("\ud83d\udce6 **" + p.getName() + "** \u304c\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u3067 **" + this.resourceDisplayName(mat) + "** \u3092" + qty + "\u500b\u58f2\u5374\u3057\u307e\u3057\u305f\u3002\uff08+" + (long)total + "\u5186\uff09");
        }
        this.msgKey(p, "resourceshop.sold", "material", this.resourceDisplayName(mat), "qty", String.valueOf(qty), "amount", String.format("%.2f", total));
        this.clickSound(p);
        this.openResourceShopDetailGUI(p, mat);
    }

    private void executeResourceBuy(Player p, Material mat, int qty) {
        UUID u = p.getUniqueId();
        double unitPrice = this.getResourceEffectivePrice(mat) * this.getResourcePersonalMultiplier(u, mat);
        double total = unitPrice * (double)qty;
        if (econ.getBalance((OfflinePlayer)p) < total) {
            this.msgKey(p, "resourceshop.funds-insufficient", new String[0]);
            this.errorSound(p);
            return;
        }
        econ.withdrawPlayer((OfflinePlayer)p, total);
        HashMap leftover = p.getInventory().addItem(new ItemStack[]{new ItemStack(mat, qty)});
        for (ItemStack over : leftover.values()) {
            p.getWorld().dropItem(p.getLocation(), over);
        }
        this.applyResourceTradeImpact(mat, qty, true);
        this.applyResourcePersonalImpact(u, mat, qty);
        this.addLog(u, "\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7: " + this.resourceDisplayName(mat) + " \u3092" + qty + "\u500b\u8cfc\u5165 -" + this.fmtCur(total));
        if (qty >= 16) {
            this.sendDiscordWebhook("\ud83d\udce6 **" + p.getName() + "** \u304c\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u3067 **" + this.resourceDisplayName(mat) + "** \u3092" + qty + "\u500b\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08-" + (long)total + "\u5186\uff09");
        }
        this.msgKey(p, "resourceshop.bought", "material", this.resourceDisplayName(mat), "qty", String.valueOf(qty), "amount", String.format("%.2f", total));
        this.clickSound(p);
        this.openResourceShopDetailGUI(p, mat);
    }

    private void buyLotteryTickets(Player p, int qty) {
        UUID u = p.getUniqueId();
        double totalCost = this.cfgLotteryTicketPrice * (double)qty;
        if (econ.getBalance((OfflinePlayer)p) < totalCost) {
            this.msgKey(p, "lottery.funds-insufficient", new String[0]);
            this.errorSound(p);
            return;
        }
        econ.withdrawPlayer((OfflinePlayer)p, totalCost);
        double toPool = totalCost * this.cfgLotteryPayoutRate;
        double toTreasury = totalCost - toPool;
        this.lotteryPool += toPool;
        this.treasury += toTreasury;
        this.lotteryTickets.merge(u, qty, Integer::sum);
        this.addLog(u, "\u5b9d\u304f\u3058 " + qty + "\u679a\u8cfc\u5165 -" + this.fmtCur(totalCost));
        if (qty >= 16) {
            this.sendDiscordWebhook("\ud83c\udf9f\ufe0f **" + p.getName() + "** \u304c\u5b9d\u304f\u3058\u3092" + qty + "\u679a\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08-" + (long)totalCost + "\u5186\uff09");
        }
        this.msgKey(p, "lottery.ticket-bought", "count", String.valueOf(qty), "amount", String.valueOf((long)totalCost));
        this.clickSound(p);
        this.openLotteryGUI(p);
    }

    private void runLotteryDrawIfDue() {
        long now = System.currentTimeMillis();
        if (now < this.lotteryDrawAt) {
            return;
        }
        if (this.lotteryTickets.isEmpty() && this.lotteryPool <= 0.0) {
            this.lotteryDrawAt = now + (long)this.cfgLotteryDrawIntervalHours * 3600000L;
            return;
        }
        if (this.lotteryTickets.isEmpty()) {
            this.lotteryDrawAt = now + (long)this.cfgLotteryDrawIntervalHours * 3600000L;
            return;
        }
        boolean jackpotBoosted = false;
        if (this.cfgLotteryJackpotBoostEnabled && this.lotteryPool < this.cfgLotteryJackpotBoostThreshold && this.treasury >= this.cfgLotteryJackpotBoostAmount) {
            this.treasury -= this.cfgLotteryJackpotBoostAmount;
            this.lotteryPool += this.cfgLotteryJackpotBoostAmount;
            jackpotBoosted = true;
        }
        int totalTickets = this.lotteryTickets.values().stream().mapToInt(Integer::intValue).sum();
        int roll = (int)(Math.random() * (double)totalTickets);
        UUID winnerUuid = null;
        int cumulative = 0;
        for (Map.Entry<UUID, Integer> entry : this.lotteryTickets.entrySet()) {
            if (roll >= (cumulative += entry.getValue().intValue())) continue;
            winnerUuid = entry.getKey();
            break;
        }
        if (winnerUuid == null) {
            winnerUuid = this.lotteryTickets.keySet().iterator().next();
        }
        double payout = this.lotteryPool;
        econ.depositPlayer(Bukkit.getOfflinePlayer(winnerUuid), payout);
        this.addLog(winnerUuid, "\u5b9d\u304f\u3058\u5f53\u9078 +" + this.fmtCur(payout));
        String winnerName = Bukkit.getOfflinePlayer((UUID)winnerUuid).getName();
        this.lastLotteryWinnerName = winnerName != null ? winnerName : "unknown";
        this.lastLotteryWinnerAmount = payout;
        this.broadcastNews("<gold><bold>\u3010\u5b9d\u304f\u3058\u62bd\u9078\u3011</bold> <yellow>" + this.lastLotteryWinnerName + "</yellow> \u3055\u3093\u304c\u8cde\u91d1 " + (long)payout + "\u5186 \u3092\u7372\u5f97\u3057\u307e\u3057\u305f\uff01</gold>");
        if (jackpotBoosted) {
            this.broadcastNews("<gold>\uff08\u4eca\u56de\u306e\u8cde\u91d1\u306b\u306f\u56fd\u5eab\u304b\u3089\u306e\u7279\u5225\u4e0a\u4e57\u305b " + (long)this.cfgLotteryJackpotBoostAmount + "\u5186 \u304c\u542b\u307e\u308c\u3066\u3044\u307e\u3059\uff09</gold>");
        }
        this.sendDiscordWebhook("\ud83c\udf89 **" + this.lastLotteryWinnerName + "** \u304c\u5b9d\u304f\u3058\u306b\u5f53\u305b\u3093\u3057\u307e\u3057\u305f\uff01 \u8cde\u91d1: " + this.fmtCur(payout) + (String)(jackpotBoosted ? "\uff08\u3046\u3061\u56fd\u5eab\u4e0a\u4e57\u305b " + (long)this.cfgLotteryJackpotBoostAmount + "\u5186\uff09" : ""));
        this.lotteryTickets.clear();
        this.lotteryPool = 0.0;
        this.lotteryDrawAt = now + (long)this.cfgLotteryDrawIntervalHours * 3600000L;
    }

    private void payCitizenDividend() {
        if (!this.cfgCitizenDividendEnabled) {
            return;
        }
        if (this.treasury < this.cfgCitizenDividendMinTreasury) {
            return;
        }
        double amount = this.cfgCitizenDividendAmount;
        if (amount <= 0.0) {
            return;
        }
        int paid = 0;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (this.treasury < amount) break;
            this.treasury -= amount;
            econ.depositPlayer((OfflinePlayer)p, amount);
            this.addLog(p.getUniqueId(), "\u5e02\u6c11\u914d\u5f53 +" + this.fmtCur(amount));
            this.msgKey(p, "treasury.citizen-dividend", "amount", String.valueOf((long)amount));
            ++paid;
        }
        if (paid > 0) {
            this.broadcastNews("<gold><bold>\u3010\u5e02\u6c11\u914d\u5f53\u3011</bold> \u56fd\u5eab\u304b\u3089 " + paid + "\u4eba\u306e\u30aa\u30f3\u30e9\u30a4\u30f3\u30d7\u30ec\u30a4\u30e4\u30fc\u3078 1\u4eba\u3042\u305f\u308a " + (long)amount + "\u5186 \u3092\u914d\u5f53\u3057\u307e\u3057\u305f\u3002</gold>");
        }
    }

    private void payWelfare() {
        if (!this.cfgWelfareEnabled) {
            return;
        }
        double amount = this.cfgWelfareAmount;
        if (amount <= 0.0) {
            return;
        }
        long now = System.currentTimeMillis();
        for (Player p : Bukkit.getOnlinePlayers()) {
            int usedToday;
            if (this.treasury < amount) break;
            UUID u = p.getUniqueId();
            double totalAssets = econ.getBalance((OfflinePlayer)p) + this.personalBank.getOrDefault(u, 0.0) + this.fixedDeposit.getOrDefault(u, 0.0) + this.fixedDeposit2.getOrDefault(u, 0.0) + this.fixedDeposit3.getOrDefault(u, 0.0);
            if (totalAssets >= this.cfgWelfareThreshold) continue;
            if (this.isDailyCounterResetDue(u, this.welfareCountResetAt, now)) {
                this.welfareCountToday.put(u, 0);
            }
            if ((usedToday = this.welfareCountToday.getOrDefault(u, 0).intValue()) >= this.cfgWelfareMaxPerDay) continue;
            this.treasury -= amount;
            econ.depositPlayer((OfflinePlayer)p, amount);
            this.welfareCountToday.put(u, usedToday + 1);
            this.addLog(u, "\u751f\u6d3b\u652f\u63f4\u91d1 +" + this.fmtCur(amount));
            this.msgKey(p, "treasury.welfare", "amount", String.valueOf((long)amount));
        }
    }

    private void checkWorldStockAlerts() {
        HashMap<String, List> alertsBySymbol = new HashMap<String, List>();
        for (Map.Entry<UUID, List<WorldStockAlert>> entry : new HashMap<UUID, List<WorldStockAlert>>(this.worldStockAlerts).entrySet()) {
            UUID owner = entry.getKey();
            List<WorldStockAlert> alerts = entry.getValue();
            if (alerts == null || alerts.isEmpty()) continue;
            for (WorldStockAlert alert : new ArrayList<WorldStockAlert>(alerts)) {
                if (alert.baselinePrice <= 0.0) continue;
                alertsBySymbol.computeIfAbsent(alert.symbol, k -> new ArrayList()).add(Map.entry(owner, alert));
            }
        }
        for (Map.Entry<UUID, List<WorldStockAlert>> entry : alertsBySymbol.entrySet()) {
            String symbol = (String)((Object)entry.getKey());
            List<WorldStockAlert> pending = entry.getValue();
            this.fetchWorldStockQuote(symbol, quote -> {
                if (quote == null) {
                    return;
                }
                for (Map.Entry pair : pending) {
                    Player online;
                    UUID owner = (UUID)pair.getKey();
                    WorldStockAlert alert = (WorldStockAlert)pair.getValue();
                    double moveRatio = Math.abs((quote.price - alert.baselinePrice) / alert.baselinePrice);
                    if (moveRatio < alert.thresholdPercent / 100.0) continue;
                    List<WorldStockAlert> list = this.worldStockAlerts.get(owner);
                    if (list != null) {
                        list.remove(alert);
                        if (list.isEmpty()) {
                            this.worldStockAlerts.remove(owner);
                        }
                    }
                    if ((online = Bukkit.getPlayer((UUID)owner)) == null || !online.isOnline()) continue;
                    this.msgKey(online, "worldstock.alert-triggered", "symbol", alert.symbol, "percent", String.format("%.1f", alert.thresholdPercent), "price", this.worldStockNativePrice((WorldStockQuote)quote) + (String)(quote.currency != null && !quote.currency.equalsIgnoreCase("JPY") ? " (\u2248 " + this.fmtYenAmount(this.worldStockYenPrice((WorldStockQuote)quote)) + ")" : ""));
                    online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                }
            });
        }
    }

    private void sendPlayerInfoToSender(CommandSender sender, String playerName) {
        LinkedList<String> logs;
        OfflinePlayer target = Bukkit.getOfflinePlayer((String)playerName);
        if (target.getName() == null || !target.hasPlayedBefore() && !target.isOnline()) {
            sender.sendMessage("\u30d7\u30ec\u30a4\u30e4\u30fc\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093: " + playerName);
            return;
        }
        UUID targetId = target.getUniqueId();
        double pocket = econ.getBalance(target);
        double bank = this.personalBank.getOrDefault(targetId, 0.0);
        int score = this.getScore(targetId);
        double gDebt = this.govDebt.getOrDefault(targetId, 0.0);
        int achievements = this.unlockedAchievements.getOrDefault(targetId, new HashSet()).size();
        sender.sendMessage("=== " + target.getName() + " \u306e\u7d4c\u6e08\u60c5\u5831 ===");
        sender.sendMessage("\u6240\u6301\u91d1: " + this.fmtCur(pocket));
        sender.sendMessage("\u9810\u91d1: " + this.fmtCur(bank));
        sender.sendMessage("\u4fe1\u7528\u30b9\u30b3\u30a2: " + score);
        sender.sendMessage("\u653f\u5e9c\u50b5\u52d9: " + this.fmtCur(gDebt));
        sender.sendMessage("\u5b9f\u7e3e\u89e3\u9664\u6570: " + achievements);
        HashMap holdings = this.playerWorldStocks.getOrDefault(targetId, new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(targetId, new HashMap());
        boolean anyStock = false;
        for (Map.Entry h : holdings.entrySet()) {
            if ((Integer)h.getValue() <= 0) continue;
            anyStock = true;
            String symbol = (String)h.getKey();
            WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
            double value = this.worldStockPositionValue(q, avgCosts.getOrDefault(symbol, 0.0), (Integer)h.getValue());
            sender.sendMessage("  \u682a " + symbol + ": " + String.valueOf(h.getValue()) + "\u682a (\u8a55\u4fa1\u984d " + (long)value + "\u5186)");
        }
        if (!anyStock) {
            sender.sendMessage("\u4fdd\u6709\u682a: \u306a\u3057");
        }
        if ((logs = this.transactionLogs.get(targetId)) != null && !logs.isEmpty()) {
            sender.sendMessage("\u76f4\u8fd1\u306e\u53d6\u5f15\u5c65\u6b74:");
            int shown = 0;
            for (String line : logs) {
                if (shown++ >= 10) break;
                sender.sendMessage("  " + line);
            }
        }
    }

    private void sendPlayerWebpageLinkToConsole(CommandSender sender, String playerName) {
        OfflinePlayer target = Bukkit.getOfflinePlayer((String)playerName);
        if (target.getName() == null) {
            sender.sendMessage("\u30d7\u30ec\u30a4\u30e4\u30fc\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093: " + playerName);
            return;
        }
        if (!this.cfgWebDashboardEnabled) {
            sender.sendMessage("Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9\u304c\u7121\u52b9\u3067\u3059\u3002config.yml\u306ewebdashboard.enabled\u3092true\u306b\u3057\u3066\u304f\u3060\u3055\u3044\u3002");
            return;
        }
        UUID targetId = target.getUniqueId();
        String token = this.dashboardTokens.computeIfAbsent(targetId, k -> UUID.randomUUID().toString().replace("-", ""));
        String base = this.cfgWebDashboardPublicUrl != null && !this.cfgWebDashboardPublicUrl.isBlank() ? this.cfgWebDashboardPublicUrl.replaceAll("/$", "") : "http://<\u30b5\u30fc\u30d0\u30fc\u306eIP>:" + this.cfgWebDashboardPort;
        String url = base + "/me.html?token=" + token;
        String newPassword = this.generateRandomPassword(10);
        this.webAdminPasswordHash.put(targetId, this.hashWebPassword(newPassword));
        sender.sendMessage("=== " + target.getName() + " \u306eWeb\u500b\u4eba\u30da\u30fc\u30b8 ===");
        sender.sendMessage("URL: " + url);
        sender.sendMessage("\u88cf\u30d1\u30b9\u30ef\u30fc\u30c9(\u3053\u306e\u30b3\u30f3\u30bd\u30fc\u30eb\u30ed\u30b0\u306b\u3057\u304b\u8868\u793a\u3055\u308c\u307e\u305b\u3093\u3002\u672c\u4eba\u306b\u306f\u77e5\u3089\u3055\u308c\u307e\u305b\u3093): " + newPassword);
        sender.sendMessage("\u203b \u672c\u4eba\u304c\u30b2\u30fc\u30e0\u5185\u3067\u8a2d\u5b9a\u3057\u305f\u30d1\u30b9\u30ef\u30fc\u30c9\u306b\u306f\u5f71\u97ff\u3057\u307e\u305b\u3093(\u3053\u306e\u88cf\u30d1\u30b9\u30ef\u30fc\u30c9\u306f\u5225\u67a0\u3067\u3059)\u3002");
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target;
        if (!(sender instanceof Player)) {
            if (args.length >= 2 && args[0].equalsIgnoreCase("info")) {
                this.sendPlayerInfoToSender(sender, args[1]);
            } else if (args.length >= 2 && args[0].equalsIgnoreCase("webpage")) {
                this.sendPlayerWebpageLinkToConsole(sender, args[1]);
            } else {
                sender.sendMessage("\u3053\u306e\u30b3\u30de\u30f3\u30c9\u306f\u30b2\u30fc\u30e0\u5185\u30d7\u30ec\u30a4\u30e4\u30fc\u5c02\u7528\u3067\u3059\u3002\u30b3\u30f3\u30bd\u30fc\u30eb\u304b\u3089\u306f\u4ee5\u4e0b\u306e\u307f\u5b9f\u884c\u3067\u304d\u307e\u3059: /meco info <\u30d7\u30ec\u30a4\u30e4\u30fc\u540d> / /meco webpage <\u30d7\u30ec\u30a4\u30e4\u30fc\u540d>");
            }
            return true;
        }
        Player p = (Player)sender;
        if (!p.hasPermission(PERM_USE)) {
            this.msgKey(p, "welcome.no-permission", new String[0]);
            return true;
        }
        if (args.length == 0) {
            this.openHubGUI(p);
            p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1.0f, 1.0f);
            return true;
        }
        UUID u = p.getUniqueId();
        String sub = args[0].toLowerCase();
        if (sub.equals("item")) {
            long remain = this.hubItemReissueCooldown.getOrDefault(u, 0L) - System.currentTimeMillis();
            if (remain > 0L) {
                this.msgKey(p, "item.reissue-cooldown", "seconds", String.valueOf(remain / 1000L + 1L));
                this.errorSound(p);
                return true;
            }
            HashMap leftover = p.getInventory().addItem(new ItemStack[]{this.createHubItem()});
            for (ItemStack over : leftover.values()) {
                p.getWorld().dropItemNaturally(p.getLocation(), over);
            }
            this.hubItemReissueCooldown.put(u, System.currentTimeMillis() + this.cfgHubItemReissueCooldownMs);
            this.msgKey(p, "item.reissued", new String[0]);
            p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
            return true;
        }
        switch (sub) {
            case "bank": {
                this.openBankHubGUI(p);
                this.clickSound(p);
                return true;
            }
            case "market": {
                this.openMarketHubGUI(p);
                this.clickSound(p);
                return true;
            }
            case "todo": {
                this.openTodoHubGUI(p);
                this.clickSound(p);
                return true;
            }
            case "mypage": {
                this.openMyPageGUI(p);
                this.clickSound(p);
                return true;
            }
            case "auction": {
                this.openAuctionGUI(p);
                this.clickSound(p);
                return true;
            }
            case "resource": {
                this.openResourceShopGUI(p);
                this.clickSound(p);
                return true;
            }
            case "worldstock": {
                this.openWorldStockGUI(p);
                this.clickSound(p);
                return true;
            }
            case "merchant": {
                this.openTravelingMerchantGUI(p);
                this.clickSound(p);
                return true;
            }
            case "vip": {
                this.openVipLoungeGUI(p);
                this.clickSound(p);
                return true;
            }
            case "quest": {
                this.openQuestBoardGUI(p);
                this.clickSound(p);
                return true;
            }
            case "lottery": {
                this.openLotteryGUI(p);
                this.clickSound(p);
                return true;
            }
            case "storage": {
                this.openStorageRentGUI(p);
                this.clickSound(p);
                return true;
            }
            case "loan": {
                this.openMarketGUI(p);
                this.clickSound(p);
                return true;
            }
            case "govloan": {
                this.openGovLoanGUI(p);
                this.clickSound(p);
                return true;
            }
            case "insurance": {
                this.openInsuranceGUI(p);
                this.clickSound(p);
                return true;
            }
            case "collateral": {
                this.openCollateralGUI(p);
                this.clickSound(p);
                return true;
            }
            case "credit": {
                this.openCreditGUI(p);
                this.clickSound(p);
                return true;
            }
            case "deposit": {
                this.openFixedDepoGUI(p);
                this.clickSound(p);
                return true;
            }
            case "personal": {
                this.openPersonalGUI(p);
                this.clickSound(p);
                return true;
            }
            case "installment": {
                this.openInstallmentListGUI(p);
                this.clickSound(p);
                return true;
            }
            case "leaderboard": {
                this.openLeaderboardGUI(p);
                this.clickSound(p);
                return true;
            }
            case "achievement": {
                this.openAchievementGUI(p);
                this.clickSound(p);
                return true;
            }
            case "repay": {
                this.openRepayGUI(p);
                this.clickSound(p);
                return true;
            }
            case "collect": {
                this.deliverPendingAuctionItems(p);
                this.deliverAuctionOfflineNotices(p);
                this.deliverLoanOfflineNotices(p);
                this.clickSound(p);
                return true;
            }
            case "webpage": {
                if (!this.cfgWebDashboardEnabled) {
                    this.msgKey(p, "webpage.disabled", new String[0]);
                    this.errorSound(p);
                    return true;
                }
                if (args.length >= 2 && args[1].equalsIgnoreCase("password")) {
                    if (args.length >= 3 && args[2].equalsIgnoreCase("off")) {
                        this.webPasswordHash.remove(u);
                        this.msgKey(p, "webpage.password-cleared", new String[0]);
                        return true;
                    }
                    this.awaitingChatInput.put(u, "webpage_password");
                    this.msgKey(p, "webpage.password-prompt", new String[0]);
                    p.closeInventory();
                    return true;
                }
                String token = this.dashboardTokens.computeIfAbsent(u, k -> UUID.randomUUID().toString().replace("-", ""));
                String base = this.cfgWebDashboardPublicUrl != null && !this.cfgWebDashboardPublicUrl.isBlank() ? this.cfgWebDashboardPublicUrl.replaceAll("/$", "") : "http://<\u30b5\u30fc\u30d0\u30fc\u306eIP>:" + this.cfgWebDashboardPort;
                String url = base + "/me.html?token=" + token;
                this.msgKey(p, "webpage.link", "url", url);
                this.msgKey(p, this.webPasswordHash.containsKey(u) ? "webpage.password-status-set" : "webpage.password-status-unset", new String[0]);
                this.clickSound(p);
                return true;
            }
        }
        if (sub.equals("help")) {
            this.msgKey(p, "help.header", new String[0]);
            this.msgKey(p, "help.menu", new String[0]);
            this.msgKey(p, "help.shortcuts", new String[0]);
            this.msgKey(p, "help.webpage", new String[0]);
            this.msgKey(p, "help.collect", new String[0]);
            this.msgKey(p, "help.item", new String[0]);
            this.msgKey(p, "help.log", new String[0]);
            this.msgKey(p, "help.news", new String[0]);
            this.msgKey(p, "help.treasury", new String[0]);
            this.msgKey(p, "help.tutorial", new String[0]);
            this.msgKey(p, "help.donate-treasury", new String[0]);
            this.msgKey(p, "help.donate-player", new String[0]);
            this.msgKey(p, "help.trade", new String[0]);
            this.msgKey(p, "help.group", new String[0]);
            this.msgKey(p, "help.fund", new String[0]);
            if (p.hasPermission(PERM_ADMIN)) {
                this.msgKey(p, "help.admin-header", new String[0]);
                this.msgKey(p, "help.admin-give", new String[0]);
                this.msgKey(p, "help.admin-take", new String[0]);
                this.msgKey(p, "help.admin-setcredit", new String[0]);
                this.msgKey(p, "help.admin-reset", new String[0]);
                this.msgKey(p, "help.admin-discord", new String[0]);
                this.msgKey(p, "help.admin-selfcheck", new String[0]);
                this.msgKey(p, "help.admin-reload", new String[0]);
                this.msgKey(p, "help.admin-save", new String[0]);
                this.msgKey(p, "help.admin-backup", new String[0]);
                this.msgKey(p, "help.admin-config", new String[0]);
            }
            return true;
        }
        if (sub.equals("admin")) {
            if (!p.hasPermission(PERM_ADMIN)) {
                this.msgKey(p, "admin.no-permission", new String[0]);
                this.errorSound(p);
                return true;
            }
            if (args.length < 2) {
                this.msgKey(p, "admin.usage", new String[0]);
                return true;
            }
            String adminSub = args[1].toLowerCase();
            if (adminSub.equals("reload")) {
                this.reloadConfig();
                this.loadConfigValues();
                this.loadMessages();
                this.msgKey(p, "admin.reloaded", new String[0]);
                return true;
            }
            if (adminSub.equals("save")) {
                this.saveData();
                this.msgKey(p, "admin.saved", new String[0]);
                return true;
            }
            if (adminSub.equals("backup")) {
                this.backupDatabase();
                this.msgKey(p, "admin.backed-up", new String[0]);
                return true;
            }
            if (adminSub.equals("config")) {
                this.openConfigEditorGUI(p, 0);
                return true;
            }
            if (adminSub.equals("gui")) {
                this.openAdminMainGUI(p);
                return true;
            }
            if (args.length < 3) {
                this.msgKey(p, "admin.need-playername", new String[0]);
                return true;
            }
            OfflinePlayer target2 = Bukkit.getOfflinePlayer((String)args[2]);
            UUID tUuid = target2.getUniqueId();
            if (adminSub.equals("give")) {
                if (args.length < 4) {
                    this.msgKey(p, "admin.usage-give", new String[0]);
                    return true;
                }
                try {
                    double amount = Double.parseDouble(args[3]);
                    econ.depositPlayer(target2, amount);
                    this.msgKey(p, "admin.give-success", "player", target2.getName(), "amount", String.valueOf((long)amount));
                    this.addLog(tUuid, "[\u7ba1\u7406\u8005\u64cd\u4f5c] " + p.getName() + " \u304b\u3089 +" + this.fmtCur(amount));
                    this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + target2.getName() + "** \u306b " + (long)amount + "\u5186 \u3092\u4ed8\u4e0e\u3057\u307e\u3057\u305f\u3002");
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-amount-number", new String[0]);
                }
                return true;
            }
            if (adminSub.equals("take")) {
                if (args.length < 4) {
                    this.msgKey(p, "admin.usage-take", new String[0]);
                    return true;
                }
                try {
                    double amount = Double.parseDouble(args[3]);
                    econ.withdrawPlayer(target2, amount);
                    this.msgKey(p, "admin.take-success", "player", target2.getName(), "amount", String.valueOf((long)amount));
                    this.addLog(tUuid, "[\u7ba1\u7406\u8005\u64cd\u4f5c] " + p.getName() + " \u306b\u3088\u308a -" + this.fmtCur(amount));
                    this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + target2.getName() + "** \u304b\u3089 " + (long)amount + "\u5186 \u3092\u6ca1\u53ce\u3057\u307e\u3057\u305f\u3002");
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-amount-number", new String[0]);
                }
                return true;
            }
            if (adminSub.equals("setcredit")) {
                if (args.length < 4) {
                    this.msgKey(p, "admin.usage-setcredit", new String[0]);
                    return true;
                }
                try {
                    int score = Math.max(0, Math.min(800, Integer.parseInt(args[3])));
                    this.creditScore.put(tUuid, score);
                    this.msgKey(p, "admin.setcredit-success", "player", target2.getName(), "score", String.valueOf(score));
                    this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + target2.getName() + "** \u306e\u4fe1\u7528\u30b9\u30b3\u30a2\u3092 " + score + " \u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002");
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "admin.invalid-score-number", new String[0]);
                }
                return true;
            }
            if (adminSub.equals("setgovdebt")) {
                if (args.length < 4) {
                    this.msgKey(p, "admin.usage-setgovdebt", new String[0]);
                    return true;
                }
                try {
                    double amount = Math.max(0.0, Double.parseDouble(args[3]));
                    if (amount <= 0.0) {
                        this.govDebt.remove(tUuid);
                        this.govDebtDueTime.remove(tUuid);
                    } else {
                        this.govDebt.put(tUuid, amount);
                        this.govDebtDueTime.put(tUuid, System.currentTimeMillis() + this.cfgGovLoanDurationMs);
                    }
                    this.msgKey(p, "admin.setgovdebt-success", "player", target2.getName(), "amount", String.valueOf((long)amount));
                    this.addLog(tUuid, "[\u7ba1\u7406\u8005\u64cd\u4f5c] " + p.getName() + " \u304c\u56fd\u55b6\u30ed\u30fc\u30f3\u6b8b\u50b5\u3092 " + (long)amount + "\u5186 \u306b\u88dc\u6b63");
                    this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + target2.getName() + "** \u306e\u56fd\u55b6\u30ed\u30fc\u30f3\u6b8b\u50b5\u3092 " + (long)amount + "\u5186 \u306b\u88dc\u6b63\u3057\u307e\u3057\u305f\u3002");
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-amount-number", new String[0]);
                }
                return true;
            }
            if (adminSub.equals("reset")) {
                this.resetPlayerEconomyData(tUuid);
                this.msgKey(p, "admin.reset-success", "player", target2.getName());
                this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + target2.getName() + "** \u306e\u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u30ea\u30bb\u30c3\u30c8\u3057\u307e\u3057\u305f\u3002");
                this.msgKey(p, "admin.reset-note", new String[0]);
                return true;
            }
            if (adminSub.equals("event")) {
                if (args.length < 3) {
                    this.msgKey(p, "admin.usage-event", new String[0]);
                    return true;
                }
                String event = switch (args[2].toLowerCase()) {
                    case "boom" -> "\u9ec4\u91d1\u30e9\u30c3\u30b7\u30e5";
                    case "tax" -> "\u624b\u6570\u6599\u9ad8\u9a30";
                    case "bonus" -> "\u30dc\u30fc\u30ca\u30b9\u652f\u7d66\u30c7\u30fc";
                    case "recession" -> "\u51ac\u306e\u6642\u4ee3";
                    default -> "random";
                };
                this.fireEconomyEvent(event);
                return true;
            }
            if (adminSub.equals("discord")) {
                if (args.length >= 3 && args[2].equalsIgnoreCase("test")) {
                    if (this.cfgDiscordBotToken == null || this.cfgDiscordBotToken.isBlank() || this.cfgDiscordChannelId == null || this.cfgDiscordChannelId.isBlank()) {
                        this.msgKey(p, "admin.discord-not-configured", new String[0]);
                        this.msgKey(p, "admin.discord-config-hint", new String[0]);
                        return true;
                    }
                    this.msgKey(p, "admin.discord-test-sending", new String[0]);
                    this.sendDiscordWebhook("\ud83d\udd14 **" + p.getName() + "** \u304c `/meco admin discord test` \u3092\u5b9f\u884c\u3057\u307e\u3057\u305f\u3002\u3053\u308c\u304c\u5c4a\u3044\u3066\u3044\u308c\u3070\u758e\u901aOK\u3067\u3059\u3002");
                } else {
                    this.msgKey(p, "admin.usage-discord-test", new String[0]);
                }
                return true;
            }
            if (adminSub.equals("selfcheck")) {
                boolean autoFix = args.length >= 3 && args[2].equalsIgnoreCase("fix");
                List<String> issues = this.runSelfCheck(autoFix);
                if (issues.isEmpty()) {
                    this.msgKey(p, "admin.selfcheck-ok", new String[0]);
                } else {
                    this.msgKey(p, "admin.selfcheck-issues", "count", String.valueOf(issues.size()), "note", autoFix ? "\uff08\u81ea\u52d5\u4fee\u5fa9\u3057\u307e\u3057\u305f\uff09" : "\uff08/meco admin selfcheck fix \u3067\u81ea\u52d5\u4fee\u5fa9\u3067\u304d\u307e\u3059\uff09");
                    int shown = 0;
                    for (String issue : issues) {
                        if (shown++ >= 30) {
                            this.msgKey(p, "admin.selfcheck-more", "count", String.valueOf(issues.size() - 30));
                            break;
                        }
                        this.msgKey(p, "admin.selfcheck-issue-line", "issue", issue);
                    }
                    for (String issue : issues) {
                        this.getLogger().warning("[\u81ea\u5df1\u8a3a\u65ad] " + issue);
                    }
                    if (autoFix) {
                        this.saveData();
                    }
                }
                return true;
            }
            this.msgKey(p, "admin.usage-full", new String[0]);
            return true;
        }
        if (sub.equals("log")) {
            LinkedList<String> logs = this.transactionLogs.get(u);
            if (logs == null || logs.isEmpty()) {
                this.msgKey(p, "log.empty", new String[0]);
                return true;
            }
            this.msgKey(p, "log.header", new String[0]);
            int shown = 0;
            for (String log : logs) {
                if (shown++ >= 15) break;
                this.msgKey(p, "log.entry", "entry", log);
            }
            return true;
        }
        if (sub.equals("news")) {
            if (this.newsBroadcastOff.contains(u)) {
                this.newsBroadcastOff.remove(u);
                this.msgKey(p, "news.enabled", new String[0]);
            } else {
                this.newsBroadcastOff.add(u);
                this.msgKey(p, "news.disabled", new String[0]);
            }
            return true;
        }
        if (sub.equals("tutorial")) {
            this.openTutorialGUI(p);
            return true;
        }
        if (sub.equals("treasury")) {
            if (args.length == 1) {
                this.msgKey(p, "treasury.balance", "amount", String.valueOf((long)this.treasury));
                return true;
            }
            if (!p.hasPermission(PERM_ADMIN)) {
                this.msgKey(p, "treasury.admin-required", new String[0]);
                return true;
            }
            if (args.length >= 3 && args[1].equalsIgnoreCase("bonus")) {
                try {
                    double amount = Double.parseDouble(args[2]);
                    if (amount <= 0.0 || this.treasury < amount) {
                        this.msgKey(p, "treasury.insufficient", new String[0]);
                        return true;
                    }
                    ArrayList online = new ArrayList(Bukkit.getOnlinePlayers());
                    if (online.isEmpty()) {
                        return true;
                    }
                    double each = amount / (double)online.size();
                    this.treasury -= amount;
                    for (Player target3 : online) {
                        econ.depositPlayer((OfflinePlayer)target3, each);
                    }
                    this.broadcastNews("<gold><bold>\u3010\u56fd\u5eab\u652f\u51fa\u3011</bold> \u56fd\u5eab\u304b\u3089\u7dcf\u984d " + (long)amount + "\u5186\u3092\u5168\u30aa\u30f3\u30e9\u30a4\u30f3\u30d7\u30ec\u30a4\u30e4\u30fc\u3078\u914d\u5e03\u3057\u307e\u3057\u305f\u3002</gold>");
                    return true;
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-amount", new String[0]);
                    return true;
                }
            }
            if (args.length >= 3 && args[1].equalsIgnoreCase("set")) {
                try {
                    double amount = Double.parseDouble(args[2]);
                    if (amount < 0.0) {
                        this.msgKey(p, "common.amount-must-be-positive", new String[0]);
                        return true;
                    }
                    this.treasury = amount;
                    this.msgKey(p, "treasury.set", "amount", String.valueOf((long)this.treasury));
                    this.sendDiscordWebhook("\ud83c\udfdb\ufe0f \u56fd\u5eab\u6b8b\u9ad8\u304c\u7ba1\u7406\u8005\u306b\u3088\u308a " + (long)this.treasury + "\u5186 \u306b\u8a2d\u5b9a\u3055\u308c\u307e\u3057\u305f\u3002");
                    return true;
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-amount", new String[0]);
                    return true;
                }
            }
            this.msgKey(p, "treasury.usage", new String[0]);
            return true;
        }
        if (sub.equals("donate")) {
            double amount;
            if (args.length < 2) {
                this.msgKey(p, "donate.usage-treasury", new String[0]);
                this.msgKey(p, "donate.usage-player", new String[0]);
                return true;
            }
            if (args[1].equalsIgnoreCase("treasury")) {
                double amount2;
                if (args.length < 3) {
                    this.msgKey(p, "donate.usage-treasury-amount", new String[0]);
                    return true;
                }
                try {
                    amount2 = Double.parseDouble(args[2]);
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-amount", new String[0]);
                    return true;
                }
                if (amount2 <= 0.0) {
                    this.msgKey(p, "common.amount-must-be-positive", new String[0]);
                    return true;
                }
                double pocket = econ.getBalance((OfflinePlayer)p);
                if (pocket < amount2) {
                    this.msgKey(p, "common.insufficient-funds-simple", new String[0]);
                    return true;
                }
                econ.withdrawPlayer((OfflinePlayer)p, amount2);
                this.treasury += amount2;
                this.grantDonationCreditScore(u, amount2);
                this.msgKey(p, "donate.treasury-thanks", "amount", String.valueOf((long)amount2));
                this.addLog(u, "\u56fd\u5eab\u3078\u5bc4\u4ed8: -" + this.fmtCur(amount2));
                this.sendDiscordWebhook("\ud83c\udf81 **" + p.getName() + "** \u304c\u56fd\u5eab\u3078 " + (long)amount2 + "\u5186 \u3092\u5bc4\u4ed8\u3057\u307e\u3057\u305f\u3002");
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                return true;
            }
            target = Bukkit.getPlayer((String)args[1]);
            if (target == null) {
                this.msgKey(p, "common.target-offline", new String[0]);
                return true;
            }
            if (target.getUniqueId().equals(u)) {
                this.msgKey(p, "donate.cannot-self", new String[0]);
                return true;
            }
            if (args.length < 3) {
                this.msgKey(p, "donate.usage-player-amount", new String[0]);
                return true;
            }
            try {
                amount = Double.parseDouble(args[2]);
            }
            catch (NumberFormatException ex) {
                this.msgKey(p, "common.invalid-amount", new String[0]);
                return true;
            }
            if (amount <= 0.0) {
                this.msgKey(p, "common.amount-must-be-positive", new String[0]);
                return true;
            }
            double pocket = econ.getBalance((OfflinePlayer)p);
            if (pocket < amount) {
                this.msgKey(p, "common.insufficient-funds-simple", new String[0]);
                return true;
            }
            econ.withdrawPlayer((OfflinePlayer)p, amount);
            econ.depositPlayer((OfflinePlayer)target, amount);
            this.grantDonationCreditScore(u, amount);
            this.msgKey(p, "donate.player-success", "player", target.getName(), "amount", String.valueOf((long)amount));
            this.msgKey(target, "donate.player-received", "player", p.getName(), "amount", String.valueOf((long)amount));
            this.addLog(u, "\u5bc4\u4ed8: " + target.getName() + " \u3078 -" + this.fmtCur(amount));
            this.sendDiscordWebhook("\ud83c\udf81 **" + p.getName() + "** \u304c **" + target.getName() + "** \u3078 " + (long)amount + "\u5186 \u3092\u5bc4\u4ed8\u3057\u307e\u3057\u305f\u3002");
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            return true;
        }
        if (sub.equals("guarantor")) {
            UUID borrower;
            if (args.length < 2) {
                this.msgKey(p, "guarantor.usage-request", new String[0]);
                this.msgKey(p, "guarantor.usage-accept", new String[0]);
                this.msgKey(p, "guarantor.usage-decline", new String[0]);
                return true;
            }
            if (args[1].equalsIgnoreCase("accept")) {
                borrower = this.guarantorProposals.get(u);
                long proposedAt = this.guarantorProposalTime.getOrDefault(u, 0L);
                if (borrower == null || System.currentTimeMillis() - proposedAt > this.cfgGuarantorProposalTimeoutMs) {
                    this.guarantorProposals.remove(u);
                    this.msgKey(p, "guarantor.no-proposal-expired", new String[0]);
                    return true;
                }
                if (!this.activeDebts.containsKey(borrower)) {
                    this.msgKey(p, "guarantor.borrower-no-debt", new String[0]);
                    this.guarantorProposals.remove(u);
                    return true;
                }
                this.loanGuarantor.put(borrower, u);
                this.guarantorProposals.remove(u);
                this.guarantorProposalTime.remove(u);
                this.msgKey(p, "guarantor.became", "player", Bukkit.getOfflinePlayer((UUID)borrower).getName());
                Player borrowerOnline = Bukkit.getPlayer((UUID)borrower);
                if (borrowerOnline != null) {
                    this.msgKey(borrowerOnline, "guarantor.became-notice", "player", p.getName());
                }
                this.addLog(u, Bukkit.getOfflinePlayer((UUID)borrower).getName() + " \u306e\u4fdd\u8a3c\u4eba\u306b\u306a\u3063\u305f");
                this.sendDiscordWebhook("\ud83e\udd1d **" + p.getName() + "** \u304c **" + Bukkit.getOfflinePlayer((UUID)borrower).getName() + "** \u306e\u878d\u8cc7\u4fdd\u8a3c\u4eba\u306b\u306a\u308a\u307e\u3057\u305f\u3002");
                return true;
            }
            if (args[1].equalsIgnoreCase("decline")) {
                borrower = this.guarantorProposals.remove(u);
                this.guarantorProposalTime.remove(u);
                if (borrower != null) {
                    this.msgKey(p, "guarantor.declined-self", new String[0]);
                    Player borrowerOnline = Bukkit.getPlayer((UUID)borrower);
                    if (borrowerOnline != null) {
                        this.msgKey(borrowerOnline, "guarantor.declined-notice", "player", p.getName());
                    }
                } else {
                    this.msgKey(p, "guarantor.no-proposal", new String[0]);
                }
                return true;
            }
            if (args[1].equalsIgnoreCase("request")) {
                if (!this.activeDebts.containsKey(u)) {
                    this.msgKey(p, "guarantor.no-debt", new String[0]);
                    return true;
                }
                if (this.loanGuarantor.containsKey(u)) {
                    this.msgKey(p, "guarantor.already-set", new String[0]);
                    return true;
                }
                if (args.length < 3) {
                    this.msgKey(p, "guarantor.usage-request-arg", new String[0]);
                    return true;
                }
                target = Bukkit.getPlayer((String)args[2]);
                if (target == null) {
                    this.msgKey(p, "common.target-offline", new String[0]);
                    return true;
                }
                if (target.getUniqueId().equals(u)) {
                    this.msgKey(p, "guarantor.cannot-self", new String[0]);
                    return true;
                }
                this.guarantorProposals.put(target.getUniqueId(), u);
                this.guarantorProposalTime.put(target.getUniqueId(), System.currentTimeMillis());
                this.msgKey(p, "guarantor.requested", "player", target.getName());
                this.msgKey(target, "guarantor.request-received", "player", p.getName());
                this.msgKey(target, "guarantor.request-instructions", new String[0]);
                this.msgKey(target, "guarantor.request-warning", new String[0]);
                return true;
            }
            this.msgKey(p, "guarantor.usage", new String[0]);
            return true;
        }
        if (sub.equals("trade")) {
            UUID requester;
            if (args.length < 2) {
                this.msgKey(p, "trade.usage", new String[0]);
                return true;
            }
            if (args[1].equalsIgnoreCase("accept")) {
                requester = this.tradeRequests.get(u);
                long requestedAt = this.tradeRequestTime.getOrDefault(u, 0L);
                if (requester == null || System.currentTimeMillis() - requestedAt > this.cfgTradeRequestTimeoutMs) {
                    this.tradeRequests.remove(u);
                    this.tradeRequestTime.remove(u);
                    this.msgKey(p, "trade.no-request-expired", new String[0]);
                    return true;
                }
                Player requesterOnline = Bukkit.getPlayer((UUID)requester);
                this.tradeRequests.remove(u);
                this.tradeRequestTime.remove(u);
                if (requesterOnline == null || !requesterOnline.isOnline()) {
                    this.msgKey(p, "common.target-offline", new String[0]);
                    return true;
                }
                if (this.activeTradeSessions.containsKey(u) || this.activeTradeSessions.containsKey(requester)) {
                    this.msgKey(p, "trade.already-in-progress", new String[0]);
                    return true;
                }
                this.startTradeSession(requesterOnline, p);
                return true;
            }
            if (args[1].equalsIgnoreCase("decline")) {
                requester = this.tradeRequests.remove(u);
                this.tradeRequestTime.remove(u);
                if (requester != null) {
                    this.msgKey(p, "trade.declined-self", new String[0]);
                    Player requesterOnline = Bukkit.getPlayer((UUID)requester);
                    if (requesterOnline != null) {
                        this.msgKey(requesterOnline, "trade.declined-notice", "player", p.getName());
                    }
                } else {
                    this.msgKey(p, "trade.no-request", new String[0]);
                }
                return true;
            }
            target = Bukkit.getPlayer((String)args[1]);
            if (target == null) {
                this.msgKey(p, "common.target-offline", new String[0]);
                return true;
            }
            this.sendTradeRequest(p, target);
            return true;
        }
        if (sub.equals("group")) {
            if (args.length < 2) {
                this.msgKey(p, "help.group", new String[0]);
                return true;
            }
            String gsub = args[1].toLowerCase();
            if (gsub.equals("create")) {
                if (args.length < 3) {
                    this.msgKey(p, "help.group", new String[0]);
                    return true;
                }
                String name = String.join((CharSequence)" ", Arrays.copyOfRange(args, 2, args.length)).trim();
                this.createGroupAccount(p, name);
                return true;
            }
            if (gsub.equals("invite")) {
                if (args.length < 4) {
                    this.msgKey(p, "help.group", new String[0]);
                    return true;
                }
                GroupAccount acc = this.findOwnedGroupAccount(u, args[2]);
                if (acc == null) {
                    this.msgKey(p, this.findAnyRoleGroupAccount(u, args[2]) != null ? "group.not-owner" : "group.not-found", new String[0]);
                    return true;
                }
                Player target4 = Bukkit.getPlayer((String)args[3]);
                if (target4 == null) {
                    this.msgKey(p, "common.target-offline", new String[0]);
                    return true;
                }
                UUID tu = target4.getUniqueId();
                if (acc.owner.equals(tu) || acc.members.contains(tu)) {
                    this.msgKey(p, "group.already-member", new String[0]);
                    return true;
                }
                if (1 + acc.members.size() >= this.cfgGroupAccountMaxMembers) {
                    this.msgKey(p, "group.full", new String[0]);
                    return true;
                }
                acc.members.add(tu);
                this.playerGroupAccounts.computeIfAbsent(tu, k -> new HashSet()).add(acc.id);
                this.msgKey(p, "group.invited", "player", target4.getName());
                this.msgKey(target4, "group.invite-received", "group", acc.name, "player", p.getName());
                this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u306b " + target4.getName() + " \u3092\u62db\u5f85");
                this.sendDiscordWebhook("\ud83d\udc5b **" + p.getName() + "** \u304c\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u306b **" + target4.getName() + "** \u3092\u62db\u5f85\u3057\u307e\u3057\u305f\u3002");
                return true;
            }
            if (gsub.equals("kick")) {
                if (args.length < 4) {
                    this.msgKey(p, "help.group", new String[0]);
                    return true;
                }
                GroupAccount acc = this.findOwnedGroupAccount(u, args[2]);
                if (acc == null) {
                    this.msgKey(p, this.findAnyRoleGroupAccount(u, args[2]) != null ? "group.not-owner" : "group.not-found", new String[0]);
                    return true;
                }
                OfflinePlayer target5 = Bukkit.getOfflinePlayer((String)args[3]);
                UUID tu = target5.getUniqueId();
                if (!acc.members.remove(tu)) {
                    this.msgKey(p, "group.not-found", new String[0]);
                    return true;
                }
                HashSet<UUID> pset = this.playerGroupAccounts.get(tu);
                if (pset != null) {
                    pset.remove(acc.id);
                    if (pset.isEmpty()) {
                        this.playerGroupAccounts.remove(tu);
                    }
                }
                String targetName = target5.getName() != null ? target5.getName() : tu.toString();
                this.msgKey(p, "group.kicked", "player", targetName);
                Player targetOnline = Bukkit.getPlayer((UUID)tu);
                if (targetOnline != null) {
                    this.msgKey(targetOnline, "group.kicked-notice", "group", acc.name);
                }
                this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u304b\u3089 " + targetName + " \u3092\u8ffd\u653e");
                return true;
            }
            if (gsub.equals("leave")) {
                if (args.length < 3) {
                    this.msgKey(p, "help.group", new String[0]);
                    return true;
                }
                if (this.findOwnedGroupAccount(u, args[2]) != null) {
                    this.msgKey(p, "group.owner-cannot-leave", new String[0]);
                    return true;
                }
                GroupAccount acc = this.findMemberGroupAccount(u, args[2]);
                if (acc == null) {
                    this.msgKey(p, "group.not-found", new String[0]);
                    return true;
                }
                acc.members.remove(u);
                HashSet<UUID> pset = this.playerGroupAccounts.get(u);
                if (pset != null) {
                    pset.remove(acc.id);
                    if (pset.isEmpty()) {
                        this.playerGroupAccounts.remove(u);
                    }
                }
                this.msgKey(p, "group.left", "group", acc.name);
                Player ownerOnline = Bukkit.getPlayer((UUID)acc.owner);
                if (ownerOnline != null) {
                    this.msgKey(ownerOnline, "group.kicked-notice", "group", acc.name);
                }
                this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u304b\u3089\u8131\u9000");
                return true;
            }
            if (gsub.equals("disband")) {
                if (args.length < 3) {
                    this.msgKey(p, "help.group", new String[0]);
                    return true;
                }
                GroupAccount acc = this.findOwnedGroupAccount(u, args[2]);
                if (acc == null) {
                    this.msgKey(p, this.findAnyRoleGroupAccount(u, args[2]) != null ? "group.not-owner" : "group.not-found", new String[0]);
                    return true;
                }
                this.disbandGroupAccount(p, acc);
                return true;
            }
            this.msgKey(p, "help.group", new String[0]);
            return true;
        }
        if (sub.equals("fund")) {
            if (args.length < 2) {
                this.msgKey(p, "help.fund", new String[0]);
                return true;
            }
            String fsub = args[1].toLowerCase();
            if (fsub.equals("create")) {
                if (args.length < 3) {
                    this.msgKey(p, "help.fund", new String[0]);
                    return true;
                }
                String name = String.join((CharSequence)" ", Arrays.copyOfRange(args, 2, args.length)).trim();
                this.createInvestmentFund(p, name);
                return true;
            }
            if (fsub.equals("invite")) {
                if (args.length < 4) {
                    this.msgKey(p, "help.fund", new String[0]);
                    return true;
                }
                InvestmentFund fund = this.findManagedFund(u, args[2]);
                if (fund == null) {
                    this.msgKey(p, this.findMemberFund(u, args[2]) != null ? "fund.not-manager" : "fund.not-found", new String[0]);
                    return true;
                }
                Player target6 = Bukkit.getPlayer((String)args[3]);
                if (target6 == null) {
                    this.msgKey(p, "common.target-offline", new String[0]);
                    return true;
                }
                UUID tu = target6.getUniqueId();
                if (this.isFundMember(fund, tu)) {
                    this.msgKey(p, "fund.already-contributor", new String[0]);
                    return true;
                }
                if (1 + fund.contributions.size() >= this.cfgFundMaxContributors) {
                    this.msgKey(p, "fund.full", new String[0]);
                    return true;
                }
                fund.contributions.put(tu, 0.0);
                this.playerInvestmentFunds.computeIfAbsent(tu, k -> new HashSet()).add(fund.id);
                this.msgKey(p, "fund.invited", "player", target6.getName());
                this.msgKey(target6, "fund.invite-received", "fund", fund.name, "player", p.getName());
                this.addLog(u, "\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u306b " + target6.getName() + " \u3092\u62db\u5f85");
                this.sendDiscordWebhook("\ud83d\udcca **" + p.getName() + "** \u304c\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u306b **" + target6.getName() + "** \u3092\u62db\u5f85\u3057\u307e\u3057\u305f\u3002");
                return true;
            }
            if (fsub.equals("contribute")) {
                double amount;
                if (args.length < 4) {
                    this.msgKey(p, "help.fund", new String[0]);
                    return true;
                }
                InvestmentFund fund = this.findMemberFund(u, args[2]);
                if (fund == null) {
                    this.msgKey(p, "fund.not-invited", new String[0]);
                    return true;
                }
                try {
                    amount = Double.parseDouble(args[3]);
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-number", new String[0]);
                    return true;
                }
                if (amount <= 0.0) {
                    this.msgKey(p, "common.amount-must-be-positive", new String[0]);
                    return true;
                }
                double pocket = econ.getBalance((OfflinePlayer)p);
                if (pocket < amount) {
                    this.msgKey(p, "fund.contribute-funds-insufficient", "amount", String.valueOf((long)pocket));
                    return true;
                }
                econ.withdrawPlayer((OfflinePlayer)p, amount);
                fund.cashBalance += amount;
                fund.contributions.merge(u, amount, Double::sum);
                this.msgKey(p, "fund.contributed", "amount", String.valueOf((long)amount), "fund", fund.name);
                this.addLog(u, "\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u3078\u51fa\u8cc7 -" + this.fmtCur(amount));
                this.sendDiscordWebhook("\ud83d\udcca **" + p.getName() + "** \u304c\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u3078 " + (long)amount + "\u5186 \u51fa\u8cc7\u3057\u307e\u3057\u305f\u3002");
                return true;
            }
            if (fsub.equals("redeem")) {
                if (args.length < 3) {
                    this.msgKey(p, "help.fund", new String[0]);
                    return true;
                }
                InvestmentFund fund = this.findMemberFund(u, args[2]);
                if (fund == null) {
                    this.msgKey(p, "fund.not-found", new String[0]);
                    return true;
                }
                this.redeemFundStake(p, fund);
                return true;
            }
            if (fsub.equals("disband")) {
                if (args.length < 3) {
                    this.msgKey(p, "help.fund", new String[0]);
                    return true;
                }
                InvestmentFund fund = this.findManagedFund(u, args[2]);
                if (fund == null) {
                    this.msgKey(p, this.findMemberFund(u, args[2]) != null ? "fund.not-manager" : "fund.not-found", new String[0]);
                    return true;
                }
                if (this.fundHasHoldings(fund)) {
                    this.msgKey(p, "fund.disband-has-holdings", new String[0]);
                    return true;
                }
                this.disbandInvestmentFund(p, fund);
                return true;
            }
            this.msgKey(p, "help.fund", new String[0]);
            return true;
        }
        this.openHubGUI(p);
        return true;
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        String cur;
        Player senderPlayer;
        ArrayList<String> result = new ArrayList<String>();
        if (args.length == 1) {
            ArrayList<String> subs = new ArrayList<String>(Arrays.asList("item", "log", "news", "treasury", "tutorial", "donate", "guarantor", "trade", "group", "fund", "help", "bank", "market", "todo", "mypage", "auction", "resource", "worldstock", "merchant", "vip", "quest", "lottery", "storage", "loan", "govloan", "insurance", "collateral", "credit", "deposit", "personal", "installment", "leaderboard", "achievement", "repay", "webpage", "collect"));
            if (sender.hasPermission(PERM_ADMIN)) {
                subs.add("admin");
            }
            String cur2 = args[0].toLowerCase();
            for (String s : subs) {
                if (!s.startsWith(cur2)) continue;
                result.add(s);
            }
            return result;
        }
        if (args.length == 2) {
            String sub = args[0].toLowerCase();
            if (sub.equals("admin")) {
                List<String> adminSubs = Arrays.asList("give", "take", "setcredit", "setgovdebt", "reset", "reload", "save", "backup", "config", "event", "discord", "selfcheck", "gui");
                String cur3 = args[1].toLowerCase();
                for (String s : adminSubs) {
                    if (!s.startsWith(cur3)) continue;
                    result.add(s);
                }
                return result;
            }
            if (sub.equals("trade")) {
                String cur4 = args[1].toLowerCase();
                for (String s : Arrays.asList("accept", "decline")) {
                    if (!s.startsWith(cur4)) continue;
                    result.add(s);
                }
                for (Player online : Bukkit.getOnlinePlayers()) {
                    if (online.equals((Object)sender) || !online.getName().toLowerCase().startsWith(cur4)) continue;
                    result.add(online.getName());
                }
                return result;
            }
            if (sub.equals("group")) {
                String cur5 = args[1].toLowerCase();
                for (String s : Arrays.asList("create", "invite", "kick", "leave", "disband")) {
                    if (!s.startsWith(cur5)) continue;
                    result.add(s);
                }
                return result;
            }
            if (sub.equals("fund")) {
                String cur6 = args[1].toLowerCase();
                for (String s : Arrays.asList("create", "invite", "contribute", "redeem", "disband")) {
                    if (!s.startsWith(cur6)) continue;
                    result.add(s);
                }
                return result;
            }
            if (sub.equals("treasury") && sender.hasPermission(PERM_ADMIN)) {
                String cur7 = args[1].toLowerCase();
                for (String s : Arrays.asList("bonus", "set")) {
                    if (!s.startsWith(cur7)) continue;
                    result.add(s);
                }
                return result;
            }
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("group") && (args[1].equalsIgnoreCase("invite") || args[1].equalsIgnoreCase("kick") || args[1].equalsIgnoreCase("leave") || args[1].equalsIgnoreCase("disband")) && sender instanceof Player) {
            senderPlayer = (Player)sender;
            String cur8 = args[2].toLowerCase();
            for (UUID id : this.playerGroupAccounts.getOrDefault(senderPlayer.getUniqueId(), new HashSet())) {
                GroupAccount acc = this.groupAccounts.get(id);
                if (acc == null || !acc.name.toLowerCase().startsWith(cur8)) continue;
                result.add(acc.name);
            }
            return result;
        }
        if (args.length == 4 && args[0].equalsIgnoreCase("group") && (args[1].equalsIgnoreCase("invite") || args[1].equalsIgnoreCase("kick"))) {
            cur = args[3].toLowerCase();
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.getName().toLowerCase().startsWith(cur)) continue;
                result.add(online.getName());
            }
            return result;
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("fund") && (args[1].equalsIgnoreCase("invite") || args[1].equalsIgnoreCase("contribute") || args[1].equalsIgnoreCase("redeem") || args[1].equalsIgnoreCase("disband")) && sender instanceof Player) {
            senderPlayer = (Player)sender;
            String cur9 = args[2].toLowerCase();
            for (UUID id : this.playerInvestmentFunds.getOrDefault(senderPlayer.getUniqueId(), new HashSet())) {
                InvestmentFund fund = this.investmentFunds.get(id);
                if (fund == null || !fund.name.toLowerCase().startsWith(cur9)) continue;
                result.add(fund.name);
            }
            return result;
        }
        if (args.length == 4 && args[0].equalsIgnoreCase("fund") && args[1].equalsIgnoreCase("invite")) {
            cur = args[3].toLowerCase();
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.getName().toLowerCase().startsWith(cur)) continue;
                result.add(online.getName());
            }
            return result;
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("event")) {
            cur = args[2].toLowerCase();
            for (String event : Arrays.asList("random", "boom", "tax", "bonus", "recession")) {
                if (!event.startsWith(cur)) continue;
                result.add(event);
            }
            return result;
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("admin") && !args[1].equalsIgnoreCase("reload") && !args[1].equalsIgnoreCase("save") && !args[1].equalsIgnoreCase("gui")) {
            cur = args[2].toLowerCase();
            for (Player online : Bukkit.getOnlinePlayers()) {
                if (!online.getName().toLowerCase().startsWith(cur)) continue;
                result.add(online.getName());
            }
            return result;
        }
        return result;
    }

    private void fillGlass(Inventory inv) {
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = glass.getItemMeta();
        if (meta != null) {
            meta.displayName((Component)Component.empty());
            glass.setItemMeta(meta);
        }
        for (int i = 0; i < inv.getSize(); ++i) {
            if (inv.getItem(i) != null) continue;
            inv.setItem(i, glass);
        }
    }

    private String formatHoursMinutes(long millis, boolean japaneseStyle) {
        long h = millis / 3600000L;
        long m = millis / 60000L % 60L;
        return japaneseStyle ? h + "\u6642\u9593" + m + "\u5206" : h + "h " + m + "m";
    }

    private long questCooldownRemainMinutes(long cooldownUntil, long now) {
        return Math.max(0L, cooldownUntil - now) / 60000L + 1L;
    }

    private String fixedDepositStatusText(double amount, long diffMs) {
        if (amount == 0.0) {
            return "<gray>\u9810\u91d1\u306a\u3057</gray>";
        }
        if (diffMs > 0L) {
            return "<red>\u6b8b\u308a " + diffMs / 1000L + "\u79d2</red>";
        }
        return "<green>\u6e80\u671f\uff01\u5f15\u51fa\u53ef\u80fd</green>";
    }

    private ItemStack createHubItem() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(this.mm("<white><bold>\ud83d\udcd6 \u7d4c\u6e08\u624b\u5e33</bold></white>"));
            meta.lore(List.of(this.mm("<gray>\u53f3\u30af\u30ea\u30c3\u30af\u3067\u7d4c\u6e08\u7dcf\u5408\u30e1\u30cb\u30e5\u30fc\u3092\u958b\u304d\u307e\u3059</gray>"), this.mm("<dark_gray>\u306a\u304f\u3057\u305f\u5834\u5408\u306f /meco item \u3067\u518d\u767a\u884c\u3067\u304d\u307e\u3059</dark_gray>")));
            Enchantment unbreaking = (Enchantment)Registry.ENCHANTMENT.get(NamespacedKey.minecraft((String)"unbreaking"));
            if (unbreaking != null) {
                meta.addEnchant(unbreaking, 1, true);
                meta.addItemFlags(new ItemFlag[]{ItemFlag.HIDE_ENCHANTS});
            }
            meta.setItemModel(new NamespacedKey("minecraftbank", "economy_notebook"));
            meta.getPersistentDataContainer().set(this.hubItemKey, PersistentDataType.BYTE, (Object)1);
            item.setItemMeta(meta);
        }
        return item;
    }

    private void openHubGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tHub);
        gui.setItem(11, this.createItem(Material.GOLD_INGOT, "<aqua><bold>\ud83c\udfe6 \u9280\u884c</bold></aqua>", "<gray>\u500b\u4eba\u53e3\u5ea7\u30fb\u878d\u8cc7\u30fb\u56fd\u55b6\u516c\u5eab\u30fb\u5b9a\u671f\u9810\u91d1\u30fb\u8cea\u5c4b\u30fb\u4fdd\u967a</gray>"));
        gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>\ud83d\uded2 \u30de\u30fc\u30b1\u30c3\u30c8</bold></green>", "<gray>\u4e16\u754c\u682a\u5f0f\u5e02\u5834\u30fb\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u30fb\u5de1\u56de\u5546\u4eba\u30fb\u30aa\u30fc\u30af\u30b7\u30e7\u30f3</gray>"));
        gui.setItem(15, this.createItem(Material.TARGET, "<yellow><bold>\ud83c\udfaf \u3084\u308b\u3053\u3068</bold></yellow>", "<gray>\u4f9d\u983c\u30dc\u30fc\u30c9\u30fb\u5b9d\u304f\u3058\u30fb\u30ec\u30f3\u30bf\u30eb\u5009\u5eab</gray>"));
        gui.setItem(20, this.createItem(Material.PLAYER_HEAD, "<light_purple><bold>\ud83d\udcb3 \u30de\u30a4\u30da\u30fc\u30b8</bold></light_purple>", "<gray>\u4fe1\u7528\u60c5\u5831\u30fb\u5b9f\u7e3e\u30fb\u30e9\u30f3\u30ad\u30f3\u30b0\u30fb\u53d6\u5f15\u5c65\u6b74</gray>"));
        gui.setItem(22, this.createItem(Material.CRAFTING_TABLE, "<gray><bold>\ud83d\uddc2 \u30af\u30e9\u30b7\u30c3\u30af\u30e1\u30cb\u30e5\u30fc</bold></gray>", "<dark_gray>\u65e7\u30d0\u30fc\u30b8\u30e7\u30f3\u306e\u4e00\u89a7\u8868\u793a\u5f62\u5f0f</dark_gray>"));
        if (p.hasPermission(PERM_ADMIN)) {
            gui.setItem(31, this.createItem(Material.COMMAND_BLOCK, "<red><bold>\ud83d\udee0 \u7ba1\u7406\u8005\u30d1\u30cd\u30eb</bold></red>", "<gray>\u30d7\u30ec\u30a4\u30e4\u30fc\u30fb\u30b5\u30fc\u30d0\u30fc\u306e\u7ba1\u7406\u64cd\u4f5c</gray>"));
        }
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openMarketHubGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tMarketHub);
        gui.setItem(12, this.createItem(Material.NETHER_STAR, "<blue><bold>\ud83d\udcc8 \u4e16\u754c\u682a\u5f0f\u5e02\u5834</bold></blue>", "<gray>\u73fe\u5b9f\u306e\u682a\u3092\u691c\u7d22\u3057\u3066\u58f2\u8cb7</gray>"));
        gui.setItem(14, this.createItem(Material.HOPPER, "<green><bold>\ud83c\udf3e \u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7</bold></green>", "<gray>\u9271\u77f3\u30fb\u8fb2\u4f5c\u7269\u30fb\u30c9\u30ed\u30c3\u30d7\u54c1\u3092\u58f2\u8cb7</gray>"));
        gui.setItem(20, this.createItem(Material.LEATHER_HORSE_ARMOR, "<gold><bold>\ud83e\uddf3 \u5de1\u56de\u5546\u4eba</bold></gold>", "<gray>\u73fe\u5728\u5730\u3092\u78ba\u8a8d\u3059\u308b(\u8cfc\u5165\u306f\u73fe\u5730\u306eNPC\u304b\u3089)</gray>"));
        gui.setItem(22, this.createItem(Material.ITEM_FRAME, "<green><bold>\ud83d\udd28 \u30aa\u30fc\u30af\u30b7\u30e7\u30f3</bold></green>", "<gray>\u30a2\u30a4\u30c6\u30e0\u306e\u51fa\u54c1\u30fb\u5165\u672d</gray>"));
        gui.setItem(16, this.createItem(Material.DIAMOND, "<light_purple><bold>\ud83d\udc8e VIP\u30e9\u30a6\u30f3\u30b8</bold></light_purple>", "<gray>\u4fe1\u7528\u30b9\u30b3\u30a2\u306b\u5fdc\u3058\u305f\u4f1a\u54e1\u7279\u5178(\u624b\u6570\u6599\u5272\u5f15\u30fb\u9650\u5b9a\u30b7\u30e7\u30c3\u30d7\u30fb\u65e5\u6b21\u624b\u5f53\u30fb\u53d6\u5f15\u67a0\u62e1\u5927)</gray>"));
        gui.setItem(31, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openTodoHubGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tTodoHub);
        gui.setItem(12, this.createItem(Material.OAK_SIGN, "<dark_aqua><bold>\ud83d\udccb \u4f9d\u983c\u30dc\u30fc\u30c9</bold></dark_aqua>", "<gray>\u63a2\u7d22\u4f9d\u983c\u306e\u6295\u7a3f\u30fb\u53d7\u6ce8</gray>"));
        gui.setItem(14, this.createItem(Material.FIREWORK_STAR, "<gold><bold>\ud83c\udf9f \u5b9d\u304f\u3058</bold></gold>", "<gray>\u30c1\u30b1\u30c3\u30c8\u3092\u8cfc\u5165\u3057\u3066\u5b9a\u671f\u62bd\u9078\u306b\u53c2\u52a0</gray>"));
        gui.setItem(16, this.createItem(Material.BARREL, "<gold><bold>\ud83c\udfec \u30ec\u30f3\u30bf\u30eb\u5009\u5eab</bold></gold>", "<gray>\u5bb6\u8cc3\u3092\u6255\u3063\u3066\u5c02\u7528\u306e\u53ce\u7d0d\u5009\u5eab\u3092\u501f\u308a\u3089\u308c\u307e\u3059</gray>"));
        gui.setItem(22, this.createItem(Material.PLAYER_HEAD, "<light_purple><bold>\ud83e\udd1d \u30a2\u30a4\u30c6\u30e0\u4ea4\u63db</bold></light_purple>", "<gray>\u4ed6\u30d7\u30ec\u30a4\u30e4\u30fc\u3068\u5b89\u5168\u306b\u30a2\u30a4\u30c6\u30e0\u30fb\u304a\u91d1\u3092\u4ea4\u63db</gray>"));
        gui.setItem(20, this.createItem(Material.ENDER_CHEST, "<gold><bold>\ud83d\udc5b \u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1</bold></gold>", "<gray>\u53cb\u4eba\u30fb\u5c0f\u3055\u306a\u30b0\u30eb\u30fc\u30d7\u3067\u5171\u6709\u3059\u308b\u5171\u540c\u53e3\u5ea7</gray>"));
        gui.setItem(24, this.createItem(Material.DIAMOND, "<dark_green><bold>\ud83d\udcca \u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9</bold></dark_green>", "<gray>\u8907\u6570\u4eba\u3067\u51fa\u8cc7\u3057\u3001\u30de\u30cd\u30fc\u30b8\u30e3\u30fc\u304c\u4e16\u754c\u682a\u5f0f\u5e02\u5834\u3067\u307e\u3068\u3081\u3066\u904b\u7528</gray>"));
        gui.setItem(31, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private GroupAccount findOwnedGroupAccount(UUID owner, String name) {
        for (GroupAccount acc : this.groupAccounts.values()) {
            if (!acc.owner.equals(owner) || !acc.name.equalsIgnoreCase(name)) continue;
            return acc;
        }
        return null;
    }

    private GroupAccount findMemberGroupAccount(UUID member, String name) {
        for (GroupAccount acc : this.groupAccounts.values()) {
            if (!acc.members.contains(member) || !acc.name.equalsIgnoreCase(name)) continue;
            return acc;
        }
        return null;
    }

    private GroupAccount findAnyRoleGroupAccount(UUID u, String name) {
        for (GroupAccount acc : this.groupAccounts.values()) {
            if (!this.isGroupAccountMember(acc, u) || !acc.name.equalsIgnoreCase(name)) continue;
            return acc;
        }
        return null;
    }

    private boolean isGroupAccountMember(GroupAccount acc, UUID u) {
        return acc.owner.equals(u) || acc.members.contains(u);
    }

    private boolean createGroupAccount(Player p, String name) {
        UUID u = p.getUniqueId();
        if (name == null || name.isBlank()) {
            this.msgKey(p, "common.cancelled", new String[0]);
            return false;
        }
        name = name.trim();
        double cost = this.cfgGroupAccountCreateCost;
        double pocket = econ.getBalance((OfflinePlayer)p);
        if (pocket < cost) {
            this.msgKey(p, "group.create-funds-insufficient", "amount", String.valueOf((long)cost));
            this.errorSound(p);
            return false;
        }
        econ.withdrawPlayer((OfflinePlayer)p, cost);
        this.treasury += cost;
        GroupAccount acc = new GroupAccount();
        acc.id = UUID.randomUUID();
        acc.name = name;
        acc.owner = u;
        acc.balance = 0.0;
        this.groupAccounts.put(acc.id, acc);
        this.playerGroupAccounts.computeIfAbsent(u, k -> new HashSet()).add(acc.id);
        this.msgKey(p, "group.created", "name", acc.name, "id", acc.id.toString());
        this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u3092\u4f5c\u6210 (-" + (long)cost + "\u5186)");
        this.sendDiscordWebhook("\ud83d\udc5b **" + p.getName() + "** \u304c\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u3092\u4f5c\u6210\u3057\u307e\u3057\u305f\u3002");
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        return true;
    }

    private void disbandGroupAccount(Player p, GroupAccount acc) {
        UUID u = p.getUniqueId();
        HashSet<UUID> allMembers = new HashSet<UUID>(acc.members);
        allMembers.add(acc.owner);
        double share = allMembers.isEmpty() ? 0.0 : acc.balance / (double)allMembers.size();
        for (UUID m : allMembers) {
            Player online;
            HashSet<UUID> pset;
            if (share > 0.0) {
                econ.depositPlayer(Bukkit.getOfflinePlayer((UUID)m), share);
            }
            if ((pset = this.playerGroupAccounts.get(m)) != null) {
                pset.remove(acc.id);
                if (pset.isEmpty()) {
                    this.playerGroupAccounts.remove(m);
                }
            }
            if ((online = Bukkit.getPlayer((UUID)m)) == null || online.getUniqueId().equals(u)) continue;
            this.msgKey(online, "group.disbanded", "amount", String.valueOf((long)share));
        }
        this.groupAccounts.remove(acc.id);
        this.msgKey(p, "group.disbanded", "amount", String.valueOf((long)share));
        this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u3092\u89e3\u6563 (\u6b8b\u9ad8 " + (long)acc.balance + "\u5186 \u3092 " + allMembers.size() + "\u4eba\u3067\u5747\u7b49\u5272\u308a)");
        this.sendDiscordWebhook("\ud83d\udc5b **" + p.getName() + "** \u304c\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u3092\u89e3\u6563\u3057\u307e\u3057\u305f\u3002\uff08\u6b8b\u9ad8" + (long)acc.balance + "\u5186\u3092" + allMembers.size() + "\u4eba\u3067\u5747\u7b49\u5272\u308a\uff09");
    }

    private void openGroupListGUI(Player p) {
        UUID u = p.getUniqueId();
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tGroupList);
        HashSet mine = this.playerGroupAccounts.getOrDefault(u, new HashSet());
        int slot = 0;
        for (UUID id : mine) {
            GroupAccount acc = this.groupAccounts.get(id);
            if (acc == null) continue;
            if (slot >= 45) break;
            boolean isOwner = acc.owner.equals(u);
            ItemStack icon = this.createItem(Material.CHEST, "<gold><bold>" + acc.name + "</bold></gold>", "<gray>\u6b8b\u9ad8:</gray> <white>" + (long)acc.balance + "\u5186</white>", "<gray>\u30e1\u30f3\u30d0\u30fc\u6570:</gray> <white>" + (1 + acc.members.size()) + "\u4eba</white> <dark_gray>(\u4e0a\u9650" + this.cfgGroupAccountMaxMembers + "\u4eba)</dark_gray>", isOwner ? "<yellow>\u3042\u306a\u305f\u306f\u30aa\u30fc\u30ca\u30fc\u3067\u3059</yellow>" : "<aqua>\u3042\u306a\u305f\u306f\u30e1\u30f3\u30d0\u30fc\u3067\u3059</aqua>", "<dark_gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u958b\u304f</dark_gray>");
            ItemMeta im = icon.getItemMeta();
            if (im != null) {
                im.getPersistentDataContainer().set(this.groupAccountKey, PersistentDataType.STRING, (Object)acc.id.toString());
                icon.setItemMeta(im);
            }
            gui.setItem(slot++, icon);
        }
        if (slot == 0) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<red><bold>\u6240\u5c5e\u3057\u3066\u3044\u308b\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u304c\u3042\u308a\u307e\u305b\u3093</bold></red>", "<gray>/meco group create <\u540d\u524d> \u3067\u65b0\u3057\u304f\u4f5c\u6210\u3067\u304d\u307e\u3059</gray>"));
        }
        gui.setItem(45, this.createItem(Material.WRITABLE_BOOK, "<green><bold>\u270f \u65b0\u3057\u304f\u4f5c\u308b</bold></green>", "<gray>\u30c1\u30e3\u30c3\u30c8\u306b\u540d\u524d\u3092\u5165\u529b\u3057\u3066\u4f5c\u6210\u3057\u307e\u3059</gray>", "<dark_gray>\u4f5c\u6210\u8cbb\u7528: " + (long)this.cfgGroupAccountCreateCost + "\u5186</dark_gray>"));
        gui.setItem(49, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openGroupAccountGUI(Player p, UUID accountId) {
        GroupAccount acc = this.groupAccounts.get(accountId);
        if (acc == null) {
            this.openGroupListGUI(p);
            return;
        }
        UUID u = p.getUniqueId();
        if (!this.isGroupAccountMember(acc, u)) {
            this.openGroupListGUI(p);
            return;
        }
        this.groupAccountViewing.put(u, acc.id);
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tGroupAccount);
        StringBuilder memberList = new StringBuilder();
        memberList.append(Bukkit.getOfflinePlayer((UUID)acc.owner).getName()).append("(\u30aa\u30fc\u30ca\u30fc)");
        for (UUID m : acc.members) {
            String name = Bukkit.getOfflinePlayer((UUID)m).getName();
            memberList.append(", ").append(name != null ? name : m.toString());
        }
        gui.setItem(4, this.createItem(Material.CHEST, "<gold><bold>" + acc.name + "</bold></gold>", "<gray>\u6b8b\u9ad8:</gray> <white>" + (long)acc.balance + "\u5186</white>", "<gray>\u30e1\u30f3\u30d0\u30fc:</gray> <white>" + String.valueOf(memberList) + "</white>", acc.owner.equals(u) ? "<yellow>\u3042\u306a\u305f\u306f\u30aa\u30fc\u30ca\u30fc\u3067\u3059</yellow>" : "<aqua>\u3042\u306a\u305f\u306f\u30e1\u30f3\u30d0\u30fc\u3067\u3059</aqua>"));
        gui.setItem(11, this.createItem(Material.EMERALD, "<green><bold>\u5165\u91d1\u3059\u308b</bold></green>", "<gray>\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b\u3057\u3066\u5165\u91d1\u3057\u307e\u3059</gray>"));
        gui.setItem(15, this.createItem(Material.GOLD_INGOT, "<gold><bold>\u5f15\u304d\u51fa\u3059</bold></gold>", "<gray>\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b\u3057\u3066\u5f15\u304d\u51fa\u3057\u307e\u3059</gray>", "<dark_gray>\u30e1\u30f3\u30d0\u30fc\u306a\u3089\u8ab0\u3067\u3082\u5f15\u304d\u51fa\u305b\u307e\u3059(\u4fe1\u983c\u5236)</dark_gray>"));
        if (acc.owner.equals(u)) {
            gui.setItem(13, this.createItem(Material.PLAYER_HEAD, "<aqua><bold>\ud83d\udc65 \u30e1\u30f3\u30d0\u30fc\u7ba1\u7406</bold></aqua>", "<gray>\u30e1\u30f3\u30d0\u30fc\u306e\u62db\u5f85\u30fb\u8ffd\u653e(\u30aa\u30fc\u30ca\u30fc\u5c02\u7528)</gray>"));
            gui.setItem(22, this.createItem(Material.BARRIER, "<dark_red><bold>\u89e3\u6563\u3059\u308b</bold></dark_red>", "<gray>\u6b8b\u9ad8\u306f\u30e1\u30f3\u30d0\u30fc\u5168\u54e1\u306b\u5747\u7b49\u5272\u308a\u3055\u308c\u307e\u3059</gray>", "<red>\u3082\u3046\u4e00\u5ea6\u30af\u30ea\u30c3\u30af\u3059\u308b\u3068\u78ba\u5b9a\u3057\u307e\u3059(30\u79d2\u4ee5\u5185)</red>"));
        }
        gui.setItem(31, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openGroupMembersGUI(Player p, UUID accountId) {
        GroupAccount acc = this.groupAccounts.get(accountId);
        if (acc == null || !acc.owner.equals(p.getUniqueId())) {
            this.openGroupListGUI(p);
            return;
        }
        this.groupAccountViewing.put(p.getUniqueId(), acc.id);
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tGroupMembers);
        gui.setItem(4, this.createItem(Material.BOOK, "<gold><bold>" + acc.name + " \u306e\u30e1\u30f3\u30d0\u30fc\u7ba1\u7406</bold></gold>", "<gray>\u4e0a\u6bb5: \u73fe\u5728\u306e\u30e1\u30f3\u30d0\u30fc(\u30af\u30ea\u30c3\u30af\u3067\u8ffd\u653e)</gray>", "<gray>\u4e0b\u6bb5: \u62db\u5f85\u3067\u304d\u308b\u30aa\u30f3\u30e9\u30a4\u30f3\u30d7\u30ec\u30a4\u30e4\u30fc(\u30af\u30ea\u30c3\u30af\u3067\u62db\u5f85)</gray>", "<dark_gray>\u30aa\u30fc\u30ca\u30fc\u81ea\u8eab\u306f\u8ffd\u653e\u3067\u304d\u307e\u305b\u3093\u3002</dark_gray>"));
        int slot = 9;
        for (UUID m : acc.members) {
            if (slot >= 18) break;
            String name = Bukkit.getOfflinePlayer((UUID)m).getName();
            ItemStack head = this.createItem(Material.PLAYER_HEAD, "<red><bold>" + (name != null ? name : m.toString()) + "</bold></red>", "<gray>\u73fe\u5728\u306e\u30e1\u30f3\u30d0\u30fc</gray>", "<yellow>\u30af\u30ea\u30c3\u30af\u3067\u8ffd\u653e</yellow>");
            ItemMeta hm = head.getItemMeta();
            if (hm != null) {
                hm.getPersistentDataContainer().set(this.groupTargetKey, PersistentDataType.STRING, (Object)m.toString());
                head.setItemMeta(hm);
            }
            gui.setItem(slot++, head);
        }
        int inviteSlot = 27;
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (inviteSlot >= 45) break;
            UUID ou = online.getUniqueId();
            if (this.isGroupAccountMember(acc, ou)) continue;
            ItemStack head = this.createItem(Material.PLAYER_HEAD, "<green><bold>" + online.getName() + "</bold></green>", "<gray>\u30aa\u30f3\u30e9\u30a4\u30f3</gray>", "<yellow>\u30af\u30ea\u30c3\u30af\u3067\u62db\u5f85</yellow>");
            ItemMeta hm = head.getItemMeta();
            if (hm != null) {
                hm.getPersistentDataContainer().set(this.groupTargetKey, PersistentDataType.STRING, (Object)ou.toString());
                head.setItemMeta(hm);
            }
            gui.setItem(inviteSlot++, head);
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private InvestmentFund findManagedFund(UUID manager, String name) {
        for (InvestmentFund f : this.investmentFunds.values()) {
            if (!f.manager.equals(manager) || !f.name.equalsIgnoreCase(name)) continue;
            return f;
        }
        return null;
    }

    private InvestmentFund findMemberFund(UUID u, String name) {
        for (InvestmentFund f : this.investmentFunds.values()) {
            if (!this.isFundMember(f, u) || !f.name.equalsIgnoreCase(name)) continue;
            return f;
        }
        return null;
    }

    private boolean isFundMember(InvestmentFund f, UUID u) {
        return f.manager.equals(u) || f.contributions.containsKey(u);
    }

    private double getFundNav(InvestmentFund fund) {
        double nav = fund.cashBalance;
        HashMap holdings = this.playerWorldStocks.getOrDefault(fund.id, new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(fund.id, new HashMap());
        for (Map.Entry holding : holdings.entrySet()) {
            int qty = (Integer)holding.getValue();
            if (qty <= 0) continue;
            String symbol = (String)holding.getKey();
            WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
            nav += this.worldStockPositionValue(q, avgCosts.getOrDefault(symbol, 0.0), qty);
        }
        return nav;
    }

    private double totalFundContributions(InvestmentFund fund) {
        double total = 0.0;
        for (double v : fund.contributions.values()) {
            total += v;
        }
        return total;
    }

    private boolean fundHasHoldings(InvestmentFund fund) {
        HashMap<String, Integer> holdings = this.playerWorldStocks.get(fund.id);
        if (holdings == null) {
            return false;
        }
        for (int qty : holdings.values()) {
            if (qty <= 0) continue;
            return true;
        }
        return false;
    }

    private boolean createInvestmentFund(Player p, String name) {
        UUID u = p.getUniqueId();
        if (name == null || name.isBlank()) {
            this.msgKey(p, "common.cancelled", new String[0]);
            return false;
        }
        name = name.trim();
        double cost = this.cfgFundCreateCost;
        double pocket = econ.getBalance((OfflinePlayer)p);
        if (pocket < cost) {
            this.msgKey(p, "fund.create-funds-insufficient", "amount", String.valueOf((long)cost));
            this.errorSound(p);
            return false;
        }
        econ.withdrawPlayer((OfflinePlayer)p, cost);
        this.treasury += cost;
        InvestmentFund fund = new InvestmentFund();
        fund.id = UUID.randomUUID();
        fund.name = name;
        fund.manager = u;
        fund.cashBalance = 0.0;
        this.investmentFunds.put(fund.id, fund);
        this.playerInvestmentFunds.computeIfAbsent(u, k -> new HashSet()).add(fund.id);
        this.msgKey(p, "fund.created", "name", fund.name, "id", fund.id.toString());
        this.addLog(u, "\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u3092\u4f5c\u6210 (-" + (long)cost + "\u5186)");
        this.sendDiscordWebhook("\ud83d\udcca **" + p.getName() + "** \u304c\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u3092\u4f5c\u6210\u3057\u307e\u3057\u305f\u3002");
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        return true;
    }

    private void redeemFundStake(Player p, InvestmentFund fund) {
        HashSet<UUID> pset;
        double myShare;
        UUID u = p.getUniqueId();
        if (!fund.contributions.containsKey(u)) {
            this.msgKey(p, "fund.not-invited", new String[0]);
            return;
        }
        double myContrib = fund.contributions.get(u);
        double totalContrib = this.totalFundContributions(fund);
        double nav = this.getFundNav(fund);
        double d = myShare = totalContrib > 0.0 ? myContrib / totalContrib * nav : 0.0;
        if (fund.cashBalance < myShare) {
            this.msgKey(p, "fund.redeem-insufficient-cash", new String[0]);
            this.errorSound(p);
            return;
        }
        fund.cashBalance -= myShare;
        fund.contributions.remove(u);
        if (!fund.manager.equals(u) && (pset = this.playerInvestmentFunds.get(u)) != null) {
            pset.remove(fund.id);
            if (pset.isEmpty()) {
                this.playerInvestmentFunds.remove(u);
            }
        }
        if (myShare > 0.0) {
            econ.depositPlayer((OfflinePlayer)p, myShare);
        }
        this.msgKey(p, "fund.redeemed", "amount", String.valueOf((long)myShare));
        this.addLog(u, "\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u304b\u3089\u89e3\u7d04 +" + this.fmtCur(myShare));
        this.sendDiscordWebhook("\ud83d\udcca **" + p.getName() + "** \u304c\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u304b\u3089\u89e3\u7d04\u3057\u307e\u3057\u305f\u3002(+" + (long)myShare + "\u5186)");
        p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
    }

    private void disbandInvestmentFund(Player p, InvestmentFund fund) {
        UUID u = p.getUniqueId();
        double totalContrib = this.totalFundContributions(fund);
        double totalCash = fund.cashBalance;
        double issuerShare = 0.0;
        for (Map.Entry<UUID, Double> entry : new HashMap<UUID, Double>(fund.contributions).entrySet()) {
            Player online;
            HashSet<UUID> pset;
            double share;
            UUID contributor = entry.getKey();
            double d = share = totalContrib > 0.0 ? entry.getValue() / totalContrib * totalCash : 0.0;
            if (share > 0.0) {
                econ.depositPlayer(Bukkit.getOfflinePlayer((UUID)contributor), share);
            }
            if (contributor.equals(u)) {
                issuerShare = share;
            }
            if ((pset = this.playerInvestmentFunds.get(contributor)) != null) {
                pset.remove(fund.id);
                if (pset.isEmpty()) {
                    this.playerInvestmentFunds.remove(contributor);
                }
            }
            if ((online = Bukkit.getPlayer((UUID)contributor)) == null || contributor.equals(u)) continue;
            this.msgKey(online, "fund.disbanded", "amount", String.valueOf((long)share));
        }
        HashSet<UUID> mgrSet = this.playerInvestmentFunds.get(fund.manager);
        if (mgrSet != null) {
            mgrSet.remove(fund.id);
            if (mgrSet.isEmpty()) {
                this.playerInvestmentFunds.remove(fund.manager);
            }
        }
        this.investmentFunds.remove(fund.id);
        this.playerWorldStocks.remove(fund.id);
        this.playerWorldStockAvgCost.remove(fund.id);
        this.worldStockTradesToday.remove(fund.id);
        this.worldStockAmountToday.remove(fund.id);
        this.worldStockProfitToday.remove(fund.id);
        this.worldStockDailyResetAt.remove(fund.id);
        this.worldStockLastTradeTime.remove(fund.id);
        this.msgKey(p, "fund.disbanded", "amount", String.valueOf((long)issuerShare));
        this.addLog(u, "\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u3092\u89e3\u6563(\u73fe\u91d1" + (long)totalCash + "\u5186\u3092\u51fa\u8cc7\u6bd4\u7387\u3067\u5206\u914d)");
        this.sendDiscordWebhook("\ud83d\udcca **" + p.getName() + "** \u304c\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u3092\u89e3\u6563\u3057\u307e\u3057\u305f\u3002(\u73fe\u91d1" + (long)totalCash + "\u5186\u3092\u51fa\u8cc7\u6bd4\u7387\u3067\u5206\u914d)");
    }

    private void openFundListGUI(Player p) {
        UUID u = p.getUniqueId();
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tFundList);
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<dark_green><bold>\u3010\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u3011</bold></dark_green>", "<gray>\u8907\u6570\u4eba\u3067\u73fe\u91d1\u3092\u51fa\u8cc7\u3057\u3001\u30de\u30cd\u30fc\u30b8\u30e3\u30fc\u304c\u4e16\u754c\u682a\u5f0f\u5e02\u5834\u3067\u307e\u3068\u3081\u3066\u904b\u7528\u3057\u307e\u3059\u3002</gray>", "<gray>\u6301\u3061\u5206(\u53d6\u308a\u5206)\u306f\u51fa\u8cc7\u984d\u306e\u6bd4\u7387\u3067\u6c7a\u307e\u308a\u307e\u3059\u3002</gray>", "<dark_gray>\u4f5c\u6210: /meco fund create <\u540d\u524d></dark_gray>", "<dark_gray>\u62db\u5f85: /meco fund invite <\u30d5\u30a1\u30f3\u30c9\u540d> <\u30d7\u30ec\u30a4\u30e4\u30fc></dark_gray>", "<dark_gray>\u51fa\u8cc7: /meco fund contribute <\u30d5\u30a1\u30f3\u30c9\u540d> <\u91d1\u984d></dark_gray>"));
        HashSet mine = this.playerInvestmentFunds.getOrDefault(u, new HashSet());
        int slot = 9;
        for (UUID id : mine) {
            InvestmentFund fund = this.investmentFunds.get(id);
            if (fund == null) continue;
            if (slot >= 45) break;
            boolean isManager = fund.manager.equals(u);
            double nav = this.getFundNav(fund);
            double totalContrib = this.totalFundContributions(fund);
            double myContrib = fund.contributions.getOrDefault(u, 0.0);
            double myStakeValue = totalContrib > 0.0 ? myContrib / totalContrib * nav : 0.0;
            String managerName = Bukkit.getOfflinePlayer((UUID)fund.manager).getName();
            if (managerName == null) {
                managerName = "\u4e0d\u660e";
            }
            ArrayList<Object> lore = new ArrayList<Object>();
            lore.add("<gray>\u30de\u30cd\u30fc\u30b8\u30e3\u30fc:</gray> <aqua>" + managerName + "</aqua>");
            lore.add("<gray>\u30d5\u30a1\u30f3\u30c9\u8a55\u4fa1\u984d(NAV):</gray> <gold>" + (long)nav + "\u5186</gold>");
            lore.add("<gray>\u73fe\u91d1\u6b8b\u9ad8:</gray> <white>" + (long)fund.cashBalance + "\u5186</white>");
            if (isManager) {
                lore.add("<yellow>\u3042\u306a\u305f\u306f\u30de\u30cd\u30fc\u30b8\u30e3\u30fc\u3067\u3059</yellow>");
                lore.add("<dark_gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u904b\u7528\u753b\u9762\u3092\u958b\u304f</dark_gray>");
            } else {
                lore.add("<gray>\u3042\u306a\u305f\u306e\u51fa\u8cc7\u984d:</gray> <white>" + (long)myContrib + "\u5186</white>");
                lore.add("<gray>\u3042\u306a\u305f\u306e\u6301\u5206\u8a55\u4fa1\u984d:</gray> <gold>" + (long)myStakeValue + "\u5186</gold>");
                lore.add("<dark_gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u60c5\u5831\u753b\u9762\u3092\u958b\u304f</dark_gray>");
            }
            ItemStack icon = this.createItem(isManager ? Material.EMERALD_BLOCK : Material.EMERALD, "<dark_green><bold>" + fund.name + "</bold></dark_green>", lore.toArray(new String[0]));
            ItemMeta im = icon.getItemMeta();
            if (im != null) {
                im.getPersistentDataContainer().set(this.investmentFundKey, PersistentDataType.STRING, (Object)fund.id.toString());
                icon.setItemMeta(im);
            }
            gui.setItem(slot++, icon);
        }
        if (slot == 9) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<red><bold>\u6240\u5c5e\u3057\u3066\u3044\u308b\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u304c\u3042\u308a\u307e\u305b\u3093</bold></red>", "<gray>/meco fund create <\u540d\u524d> \u3067\u65b0\u3057\u304f\u4f5c\u6210\u3067\u304d\u307e\u3059</gray>"));
        }
        gui.setItem(49, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openFundInfoGUI(Player p, UUID fundId) {
        InvestmentFund fund = this.investmentFunds.get(fundId);
        UUID u = p.getUniqueId();
        if (fund == null || !this.isFundMember(fund, u) || fund.manager.equals(u)) {
            this.openFundListGUI(p);
            return;
        }
        this.fundViewing.put(u, fund.id);
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tFundInfo);
        double nav = this.getFundNav(fund);
        double totalContrib = this.totalFundContributions(fund);
        double myContrib = fund.contributions.getOrDefault(u, 0.0);
        double myStakeValue = totalContrib > 0.0 ? myContrib / totalContrib * nav : 0.0;
        String managerName = Bukkit.getOfflinePlayer((UUID)fund.manager).getName();
        if (managerName == null) {
            managerName = "\u4e0d\u660e";
        }
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<dark_green><bold>" + fund.name + "</bold></dark_green>", "<gray>\u30de\u30cd\u30fc\u30b8\u30e3\u30fc:</gray> <aqua>" + managerName + "</aqua>", "<gray>\u30d5\u30a1\u30f3\u30c9\u8a55\u4fa1\u984d(NAV):</gray> <gold>" + (long)nav + "\u5186</gold>", "<gray>\u73fe\u91d1\u6b8b\u9ad8:</gray> <white>" + (long)fund.cashBalance + "\u5186</white>", "<gray>\u3042\u306a\u305f\u306e\u51fa\u8cc7\u984d:</gray> <white>" + (long)myContrib + "\u5186</white>", "<gray>\u3042\u306a\u305f\u306e\u6301\u5206\u8a55\u4fa1\u984d:</gray> <gold>" + (long)myStakeValue + "\u5186</gold>"));
        HashMap holdings = this.playerWorldStocks.getOrDefault(fund.id, new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(fund.id, new HashMap());
        int slot = 9;
        for (Map.Entry h : holdings.entrySet()) {
            if ((Integer)h.getValue() <= 0 || slot >= 17) continue;
            String symbol = (String)h.getKey();
            WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
            double avgCost = avgCosts.getOrDefault(symbol, 0.0);
            ArrayList<Object> hl = new ArrayList<Object>();
            hl.add("<gray>\u4fdd\u6709:</gray> <white>" + String.valueOf(h.getValue()) + "\u682a</white>");
            hl.add("<gray>\u5e73\u5747\u53d6\u5f97\u5358\u4fa1:</gray> <white>" + String.format("%.2f", avgCost) + "\u5186</white>");
            if (q != null) {
                hl.add(this.worldStockPriceLine("<gray>\u73fe\u5728\u5024:</gray> ", q));
            }
            gui.setItem(slot++, this.createItem(Material.PAPER, "<aqua><bold>" + symbol + "</bold></aqua>", hl.toArray(new String[0])));
        }
        gui.setItem(31, this.createItem(Material.GOLD_INGOT, "<gold><bold>\u89e3\u7d04\u3059\u308b(\u6301\u5206\u3092\u5168\u984d\u73fe\u91d1\u5316)</bold></gold>", "<gray>\u3042\u306a\u305f\u306e\u6301\u5206\u3092\u3059\u3079\u3066\u73fe\u91d1\u5316\u3057\u3066\u30d5\u30a1\u30f3\u30c9\u304b\u3089\u629c\u3051\u307e\u3059</gray>", "<dark_gray>\u30d5\u30a1\u30f3\u30c9\u306e\u73fe\u91d1\u6b8b\u9ad8\u304c\u8db3\u308a\u306a\u3044\u5834\u5408\u306f\u5931\u6557\u3057\u307e\u3059(\u30de\u30cd\u30fc\u30b8\u30e3\u30fc\u306b\u73fe\u7269\u58f2\u5374\u3092\u4f9d\u983c\u3057\u3066\u304f\u3060\u3055\u3044)</dark_gray>", "<red>\u3082\u3046\u4e00\u5ea6\u30af\u30ea\u30c3\u30af\u3059\u308b\u3068\u78ba\u5b9a\u3057\u307e\u3059(30\u79d2\u4ee5\u5185)</red>"));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openFundStockGUI(Player p, UUID fundId) {
        InvestmentFund fund = this.investmentFunds.get(fundId);
        UUID u = p.getUniqueId();
        if (fund == null || !fund.manager.equals(u)) {
            this.openFundListGUI(p);
            return;
        }
        this.fundViewing.put(u, fund.id);
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tFundStock);
        double nav = this.getFundNav(fund);
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<dark_green><bold>\u3010" + fund.name + "\u30fb\u904b\u7528\u3011</bold></dark_green>", "<gray>\u30d5\u30a1\u30f3\u30c9\u8a55\u4fa1\u984d(NAV):</gray> <gold>" + (long)nav + "\u5186</gold>", "<gray>\u73fe\u91d1\u6b8b\u9ad8:</gray> <white>" + (long)fund.cashBalance + "\u5186</white>", "<gray>\u51fa\u8cc7\u8005\u6570:</gray> <white>" + fund.contributions.size() + "\u4eba</white>", "<gray>\u5b9f\u5728\u3059\u308b\u9298\u67c4\u306e\u30c6\u30a3\u30c3\u30ab\u30fc\u30b7\u30f3\u30dc\u30eb\u3092\u691c\u7d22\u3057\u3066\u3001\u30d5\u30a1\u30f3\u30c9\u306e\u8cc7\u91d1\u3067\u58f2\u8cb7\u3067\u304d\u307e\u3059\u3002</gray>", "<dark_gray>\u53d6\u5f15\u56de\u6570/\u53d6\u5f15\u91d1\u984d\u306e1\u65e5\u3042\u305f\u308a\u4e0a\u9650\u306f\u3001\u3042\u306a\u305f\u500b\u4eba\u306e\u4e0a\u9650\u3068\u306f\u5225\u306b\u3053\u306e\u30d5\u30a1\u30f3\u30c9\u5c02\u7528\u3067\u30ab\u30a6\u30f3\u30c8\u3055\u308c\u307e\u3059\u3002</dark_gray>"));
        gui.setItem(49, this.createItem(Material.COMPASS, "<gold><bold>\ud83d\udd0d \u9298\u67c4\u3092\u691c\u7d22\u3057\u3066\u58f2\u8cb7</bold></gold>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c6\u30a3\u30c3\u30ab\u30fc\u30b7\u30f3\u30dc\u30eb\u3092\u30c1\u30e3\u30c3\u30c8\u5165\u529b</gray>"));
        HashMap holdings = this.playerWorldStocks.getOrDefault(fund.id, new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(fund.id, new HashMap());
        int slot = 9;
        for (Map.Entry entry : holdings.entrySet()) {
            String symbol = (String)entry.getKey();
            int qty = (Integer)entry.getValue();
            if (qty <= 0 || slot >= 45) continue;
            double avgCost = avgCosts.getOrDefault(symbol, 0.0);
            WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
            ArrayList<Object> lore = new ArrayList<Object>();
            lore.add("<gray>\u4fdd\u6709:</gray> <white>" + qty + "\u682a</white>");
            lore.add("<gray>\u5e73\u5747\u53d6\u5f97\u5358\u4fa1:</gray> <white>" + String.format("%.2f", avgCost) + "\u5186</white>");
            if (q != null) {
                double value = this.worldStockYenPrice(q) * (double)qty;
                double pnl = value - avgCost * (double)qty;
                lore.add(this.worldStockPriceLine("<gray>\u73fe\u5728\u5024:</gray> ", q));
                lore.add("<gray>\u8a55\u4fa1\u984d:</gray> <gold>" + (long)value + "\u5186</gold>");
                lore.add(this.worldStockPnlLine(pnl));
            } else {
                lore.add("<gray>\u73fe\u5728\u5024: \u53d6\u5f97\u4e2d... \u30af\u30ea\u30c3\u30af\u3057\u3066\u66f4\u65b0</gray>");
            }
            lore.add("<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u58f2\u8cb7\u753b\u9762\u3078</yellow>");
            ItemStack item = this.createItem(Material.PAPER, "<aqua><bold>" + symbol + "</bold></aqua>", lore.toArray(new String[0]));
            this.setWorldStockTag(item, symbol);
            gui.setItem(slot++, item);
            this.fetchWorldStockQuote(symbol, quote -> {});
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openFundStockDetailGUI(Player p, UUID fundId, String symbol) {
        InvestmentFund fund = this.investmentFunds.get(fundId);
        UUID u = p.getUniqueId();
        if (fund == null || !fund.manager.equals(u)) {
            this.openFundListGUI(p);
            return;
        }
        this.fundViewing.put(u, fund.id);
        this.openWorldStockDetailGUICommon(p, symbol, fund);
    }

    private void openWorldStockDetailGUICommon(Player p, String symbol, InvestmentFund fund) {
        String suffix;
        UUID actorU = p.getUniqueId();
        UUID trackedUuid = fund != null ? fund.id : actorU;
        UUID feeContextUuid = fund != null ? fund.manager : actorU;
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)(fund != null ? this.tFundStockDetail : this.tWorldStockDetail));
        WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
        int qty = this.playerWorldStocks.getOrDefault(trackedUuid, new HashMap()).getOrDefault(symbol, 0);
        double avgCost = this.playerWorldStockAvgCost.getOrDefault(trackedUuid, new HashMap()).getOrDefault(symbol, 0.0);
        ArrayList<Object> infoLore = new ArrayList<Object>();
        if (q != null) {
            infoLore.add(this.worldStockPriceLine("<gray>\u73fe\u5728\u5024: </gray>", q));
            if (q.currency != null && !q.currency.equalsIgnoreCase("JPY")) {
                infoLore.add("<dark_gray>\u70ba\u66ff: 1 " + q.currency + " \u2248 " + String.format("%,.2f", this.fxRateToJpy(q.currency)) + "\u5186</dark_gray>");
            }
            infoLore.add("<gray>\u53d6\u5f15\u6240: </gray><white>" + q.exchangeName + "</white>");
        } else {
            infoLore.add("<red>\u73fe\u5728\u5024\u3092\u53d6\u5f97\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f</red>");
            infoLore.add("<gray>\u5b58\u5728\u3057\u306a\u3044\u30c6\u30a3\u30c3\u30ab\u30fc\u304b\u3001\u901a\u4fe1\u30a8\u30e9\u30fc\u306e\u53ef\u80fd\u6027\u304c\u3042\u308a\u307e\u3059</gray>");
        }
        if (fund != null) {
            infoLore.add("<gray>\u30d5\u30a1\u30f3\u30c9\u73fe\u91d1\u6b8b\u9ad8: </gray><white>" + (long)fund.cashBalance + "\u5186</white>");
        }
        infoLore.add("<gray>\u4fdd\u6709: </gray><white>" + qty + "\u682a</white>");
        if (qty > 0) {
            infoLore.add("<gray>\u5e73\u5747\u53d6\u5f97\u5358\u4fa1: </gray><white>" + String.format("%.2f", avgCost) + "\u5186</white>");
        }
        if (this.getVipTier(feeContextUuid) >= 1) {
            String vipLabel = fund != null ? "\u30de\u30cd\u30fc\u30b8\u30e3\u30fc\u306eVIP\u624b\u6570\u6599\u5272\u5f15 " : "VIP\u624b\u6570\u6599\u5272\u5f15: ";
            infoLore.add("<light_purple>\ud83d\udc8e " + vipLabel + String.format("%.0f", (1.0 - this.vipFeeMultiplier(feeContextUuid)) * 100.0) + "%OFF \u9069\u7528\u4e2d</light_purple>");
        }
        ItemStack infoItem = this.createItem(Material.NETHER_STAR, "<aqua><bold>" + symbol + "</bold></aqua>", infoLore.toArray(new String[0]));
        this.setWorldStockTag(infoItem, symbol);
        gui.setItem(4, infoItem);
        String string = suffix = fund != null ? "(\u30d5\u30a1\u30f3\u30c9\u8cc7\u91d1)" : "";
        if (q != null) {
            double feeRate = this.effectiveWorldStockFeeRate(feeContextUuid);
            double yenPrice = this.worldStockYenPrice(q);
            double perBuy = yenPrice * (1.0 + feeRate);
            double perSell = yenPrice * (1.0 - feeRate);
            int[] tiers = new int[]{1, 10, 50};
            int[] buySlots = new int[]{9, 10, 11};
            int[] sellSlots = new int[]{15, 16, 17};
            for (int i = 0; i < tiers.length; ++i) {
                int tq = tiers[i];
                ItemStack buyItem = this.createItem(Material.PAPER, "<green><bold>" + tq + "\u682a\u8cfc\u5165" + suffix + "</bold></green>", "<gray>\u652f\u6255\u984d: </gray><gold>" + String.format("%.2f", perBuy * (double)tq) + "\u5186</gold> <dark_gray>(\u624b\u6570\u6599\u8fbc\u307f)</dark_gray>", "<dark_gray>(\u73fe\u5728\u5024 " + String.format("%,.0f", yenPrice) + "\u5186 \u00d7 " + tq + "\u682a + \u624b\u6570\u6599 " + String.format("%.1f", feeRate * 100.0) + "%)</dark_gray>");
                this.setWorldStockTag(buyItem, symbol);
                this.setWorldStockActionTag(buyItem, "buy:" + tq);
                gui.setItem(buySlots[i], buyItem);
            }
            ItemStack buyQtyItem = this.createItem(Material.WRITABLE_BOOK, "<green><bold>\ud83d\udcd6 \u6570\u91cf\u3092\u6307\u5b9a\u3057\u3066\u8cfc\u5165" + suffix + "</bold></green>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u682a\u6570\u3092\u5165\u529b</gray>");
            this.setWorldStockTag(buyQtyItem, symbol);
            this.setWorldStockActionTag(buyQtyItem, "buyqty");
            gui.setItem(12, buyQtyItem);
            if (qty > 0) {
                for (int i = 0; i < tiers.length; ++i) {
                    int tq = tiers[i];
                    ItemStack sellItem = this.createItem(Material.REDSTONE, "<red><bold>" + tq + "\u682a\u58f2\u5374" + suffix + "</bold></red>", "<gray>\u53d7\u53d6\u984d: </gray><gold>" + String.format("%.2f", perSell * (double)tq) + "\u5186</gold> <dark_gray>(\u624b\u6570\u6599\u5dee\u5f15\u5f8c)</dark_gray>", "<dark_gray>(\u73fe\u5728\u5024 " + String.format("%,.0f", yenPrice) + "\u5186 \u00d7 " + tq + "\u682a - \u624b\u6570\u6599 " + String.format("%.1f", feeRate * 100.0) + "%)</dark_gray>");
                    this.setWorldStockTag(sellItem, symbol);
                    this.setWorldStockActionTag(sellItem, "sell:" + tq);
                    gui.setItem(sellSlots[i], sellItem);
                }
                ItemStack sellQtyItem = this.createItem(Material.WRITABLE_BOOK, "<red><bold>\ud83d\udcd6 \u6570\u91cf\u3092\u6307\u5b9a\u3057\u3066\u58f2\u5374" + suffix + "</bold></red>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u682a\u6570\u3092\u5165\u529b</gray>");
                this.setWorldStockTag(sellQtyItem, symbol);
                this.setWorldStockActionTag(sellQtyItem, "sellqty");
                gui.setItem(14, sellQtyItem);
            } else {
                ItemStack noSell = this.createItem(Material.GRAY_DYE, "<dark_gray>\u58f2\u5374</dark_gray>", "<dark_gray>\u4fdd\u6709\u3057\u3066\u3044\u307e\u305b\u3093</dark_gray>");
                this.setWorldStockTag(noSell, symbol);
                gui.setItem(16, noSell);
            }
        }
        ItemStack refreshItem = this.createItem(Material.CLOCK, "<yellow><bold>\ud83d\udd04 \u4fa1\u683c\u3092\u518d\u53d6\u5f97</bold></yellow>", "<gray>\u30ad\u30e3\u30c3\u30b7\u30e5\u3092\u7121\u8996\u3057\u3066\u6700\u65b0\u5024\u3092\u53d6\u5f97</gray>");
        this.setWorldStockTag(refreshItem, symbol);
        gui.setItem(22, refreshItem);
        if (fund == null) {
            List myAlerts = this.worldStockAlerts.getOrDefault(actorU, new ArrayList());
            WorldStockAlert existingAlert = myAlerts.stream().filter(a -> a.symbol.equals(symbol)).findFirst().orElse(null);
            ItemStack bellItem = existingAlert != null ? this.createItem(Material.BELL, "<yellow><bold>\ud83d\udd14 \u5024\u52d5\u304d\u30a2\u30e9\u30fc\u30c8\u8a2d\u5b9a\u4e2d</bold></yellow>", "<gray>\u57fa\u6e96\u5024: </gray><white>" + String.format("%.2f", existingAlert.baselinePrice) + "</white>", "<gray>\u95be\u5024: </gray><white>\u00b1" + String.format("%.1f", existingAlert.thresholdPercent) + "%</white>", "<red>\u30af\u30ea\u30c3\u30af\u3057\u3066\u89e3\u9664</red>") : this.createItem(Material.BELL, "<yellow><bold>\ud83d\udd14 \u5024\u52d5\u304d\u30a2\u30e9\u30fc\u30c8\u3092\u8a2d\u5b9a</bold></yellow>", "<gray>\u57fa\u6e96\u5024\u304b\u3089\u6307\u5b9a%\u4ee5\u4e0a\u5909\u52d5\u3057\u305f\u3089\u30c1\u30e3\u30c3\u30c8\u3067\u901a\u77e5\u3057\u307e\u3059</gray>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u95be\u5024(%)\u3092\u5165\u529b</gray>");
            this.setWorldStockTag(bellItem, symbol);
            gui.setItem(13, bellItem);
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void handleWorldStockDetailClick(Player p, UUID u, Material mat, ItemStack item, InvestmentFund fund) {
        if (mat == Material.IRON_DOOR) {
            if (fund != null) {
                this.openFundStockGUI(p, fund.id);
            } else {
                this.openWorldStockGUI(p);
            }
            this.clickSound(p);
            return;
        }
        if (!item.hasItemMeta()) {
            return;
        }
        String symbol = (String)item.getItemMeta().getPersistentDataContainer().get(this.worldStockKey, PersistentDataType.STRING);
        if (symbol == null) {
            return;
        }
        if (mat == Material.CLOCK) {
            this.worldStockQuoteCache.remove(symbol);
            if (fund != null) {
                this.fetchWorldStockQuote(symbol, quote -> this.openFundStockDetailGUI(p, fund.id, symbol));
            } else {
                this.fetchWorldStockQuote(symbol, quote -> this.openWorldStockDetailGUI(p, symbol));
            }
            return;
        }
        if (fund == null && mat == Material.BELL) {
            List myAlerts = this.worldStockAlerts.computeIfAbsent(u, k -> new ArrayList());
            WorldStockAlert existingAlert = myAlerts.stream().filter(a -> a.symbol.equals(symbol)).findFirst().orElse(null);
            if (existingAlert != null) {
                myAlerts.remove(existingAlert);
                this.msgKey(p, "worldstock.alert-cancelled", "symbol", symbol);
                this.clickSound(p);
                this.openWorldStockDetailGUI(p, symbol);
                return;
            }
            this.awaitingChatInput.put(u, "world_stock_alert:" + symbol);
            p.closeInventory();
            this.msgKey(p, "worldstock.alert-prompt", "symbol", symbol);
            return;
        }
        WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
        if (q == null) {
            return;
        }
        String action = (String)item.getItemMeta().getPersistentDataContainer().get(this.worldStockActionKey, PersistentDataType.STRING);
        if (action == null) {
            return;
        }
        String buyQtyKey = fund != null ? "fund_stock_buy_qty:" + String.valueOf(fund.id) + ";" + symbol : "world_stock_buy_qty:" + symbol;
        String sellQtyKey = fund != null ? "fund_stock_sell_qty:" + String.valueOf(fund.id) + ";" + symbol : "world_stock_sell_qty:" + symbol;
        switch (action) {
            case "buy:1": {
                this.executeWorldStockBuy((OfflinePlayer)p, symbol, 1, fund);
                break;
            }
            case "buy:10": {
                this.executeWorldStockBuy((OfflinePlayer)p, symbol, 10, fund);
                break;
            }
            case "buy:50": {
                this.executeWorldStockBuy((OfflinePlayer)p, symbol, 50, fund);
                break;
            }
            case "sell:1": {
                this.executeWorldStockSell(p, symbol, 1, fund);
                break;
            }
            case "sell:10": {
                this.executeWorldStockSell(p, symbol, 10, fund);
                break;
            }
            case "sell:50": {
                this.executeWorldStockSell(p, symbol, 50, fund);
                break;
            }
            case "buyqty": {
                this.awaitingChatInput.put(u, buyQtyKey);
                p.closeInventory();
                this.msgKey(p, "worldstock.bulk-buy-prompt", "symbol", symbol);
                break;
            }
            case "sellqty": {
                this.awaitingChatInput.put(u, sellQtyKey);
                p.closeInventory();
                this.msgKey(p, "worldstock.bulk-sell-prompt", "symbol", symbol);
                break;
            }
        }
    }

    private void openTradeSelectGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tTradeSelect);
        int slot = 0;
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.getUniqueId().equals(p.getUniqueId())) continue;
            if (slot >= 45) break;
            ItemStack head = this.createItem(Material.PLAYER_HEAD, "<yellow><bold>" + online.getName() + "</bold></yellow>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u4ea4\u63db\u3092\u7533\u3057\u8fbc\u3080</gray>");
            ItemMeta hm = head.getItemMeta();
            if (hm != null) {
                hm.getPersistentDataContainer().set(this.tradeTargetKey, PersistentDataType.STRING, (Object)online.getUniqueId().toString());
                head.setItemMeta(hm);
            }
            gui.setItem(slot++, head);
        }
        if (slot == 0) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<red><bold>\u4ed6\u306b\u30aa\u30f3\u30e9\u30a4\u30f3\u306e\u30d7\u30ec\u30a4\u30e4\u30fc\u304c\u3044\u307e\u305b\u3093</bold></red>", new String[0]));
        }
        gui.setItem(49, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void sendTradeRequest(Player requester, Player target) {
        UUID tu;
        UUID ru = requester.getUniqueId();
        if (ru.equals(tu = target.getUniqueId())) {
            this.msgKey(requester, "trade.cannot-self", new String[0]);
            return;
        }
        if (this.activeTradeSessions.containsKey(ru) || this.activeTradeSessions.containsKey(tu)) {
            this.msgKey(requester, "trade.already-in-progress", new String[0]);
            return;
        }
        if (this.tradeRequests.containsKey(ru) || this.tradeRequests.containsKey(tu) || this.tradeRequests.containsValue(ru) || this.tradeRequests.containsValue(tu)) {
            this.msgKey(requester, "trade.already-in-progress", new String[0]);
            return;
        }
        this.tradeRequests.put(tu, ru);
        this.tradeRequestTime.put(tu, System.currentTimeMillis());
        this.msgKey(requester, "trade.request-sent", "player", target.getName());
        this.msgKey(target, "trade.request-received", "player", requester.getName());
        this.msgKey(target, "trade.request-instructions", new String[0]);
    }

    private void startTradeSession(Player a, Player b) {
        TradeSession session = new TradeSession();
        session.id = UUID.randomUUID();
        session.playerA = a.getUniqueId();
        session.playerB = b.getUniqueId();
        session.inventory = Bukkit.createInventory(null, (int)54, (Component)this.tTrade);
        this.fillTradeFrame(session.inventory);
        this.activeTradeSessions.put(session.playerA, session);
        this.activeTradeSessions.put(session.playerB, session);
        this.refreshTradeGui(session);
        a.openInventory(session.inventory);
        b.openInventory(session.inventory);
        a.playSound(a.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0f, 1.0f);
        b.playSound(b.getLocation(), Sound.BLOCK_CHEST_OPEN, 1.0f, 1.0f);
    }

    private void fillTradeFrame(Inventory inv) {
        int[] frameSlots;
        ItemStack glass = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = glass.getItemMeta();
        if (meta != null) {
            meta.displayName((Component)Component.empty());
            glass.setItemMeta(meta);
        }
        for (int slot : frameSlots = new int[]{18, 19, 21, 23, 25, 26, 46, 47, 48, 50, 51, 52}) {
            if (inv.getItem(slot) != null) continue;
            inv.setItem(slot, glass);
        }
    }

    private void refreshTradeGui(TradeSession session) {
        Inventory inv = session.inventory;
        OfflinePlayer a = Bukkit.getOfflinePlayer((UUID)session.playerA);
        OfflinePlayer b = Bukkit.getOfflinePlayer((UUID)session.playerB);
        String nameA = a.getName() != null ? a.getName() : session.playerA.toString();
        String nameB = b.getName() != null ? b.getName() : session.playerB.toString();
        inv.setItem(20, this.createItem(Material.GOLD_NUGGET, "<gold><bold>\ud83d\udcb0 " + nameA + " \u306e\u63d0\u793a\u91d1\u984d</bold></gold>", "<gray>\u73fe\u5728:</gray> <yellow>" + (long)session.moneyOfferedA + "\u5186</yellow>", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b</yellow>"));
        inv.setItem(24, this.createItem(Material.GOLD_NUGGET, "<gold><bold>\ud83d\udcb0 " + nameB + " \u306e\u63d0\u793a\u91d1\u984d</bold></gold>", "<gray>\u73fe\u5728:</gray> <yellow>" + (long)session.moneyOfferedB + "\u5186</yellow>", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b</yellow>"));
        inv.setItem(22, this.createItem(Material.PAPER, "<white><bold>\ud83d\udcc4 \u53d6\u5f15\u30b9\u30c6\u30fc\u30bf\u30b9</bold></white>", "<gray>" + nameA + ":</gray> " + (session.confirmedA ? "<green>\u78ba\u8a8d\u6e08\u307f</green>" : "<red>\u672a\u78ba\u8a8d</red>"), "<gray>" + nameB + ":</gray> " + (session.confirmedB ? "<green>\u78ba\u8a8d\u6e08\u307f</green>" : "<red>\u672a\u78ba\u8a8d</red>"), "<dark_gray>\u53cc\u65b9\u304c\u78ba\u8a8d\u3059\u308b\u3068\u53d6\u5f15\u304c\u6210\u7acb\u3057\u307e\u3059</dark_gray>", "<dark_gray>\u63d0\u793a\u5185\u5bb9\u3092\u5909\u66f4\u3059\u308b\u3068\u78ba\u8a8d\u306f\u89e3\u9664\u3055\u308c\u307e\u3059</dark_gray>"));
        inv.setItem(45, this.createItem(session.confirmedA ? Material.LIME_CONCRETE : Material.LIME_DYE, (String)(session.confirmedA ? "<green><bold>\u2714 " + nameA + ": \u78ba\u8a8d\u6e08\u307f</bold></green>" : "<yellow><bold>\u30af\u30ea\u30c3\u30af\u3057\u3066\u78ba\u8a8d\u3059\u308b</bold></yellow>"), "<gray>(" + nameA + "\u7528)</gray>"));
        inv.setItem(53, this.createItem(session.confirmedB ? Material.LIME_CONCRETE : Material.LIME_DYE, (String)(session.confirmedB ? "<green><bold>\u2714 " + nameB + ": \u78ba\u8a8d\u6e08\u307f</bold></green>" : "<yellow><bold>\u30af\u30ea\u30c3\u30af\u3057\u3066\u78ba\u8a8d\u3059\u308b</bold></yellow>"), "<gray>(" + nameB + "\u7528)</gray>"));
        inv.setItem(49, this.createItem(Material.BARRIER, "<red><bold>\u2716 \u53d6\u5f15\u3092\u30ad\u30e3\u30f3\u30bb\u30eb</bold></red>", "<gray>\u3069\u3061\u3089\u304b\u304c\u30af\u30ea\u30c3\u30af\u3059\u308b\u3068\u53d6\u5f15\u306f\u4e2d\u6b62\u3055\u308c\u307e\u3059</gray>"));
    }

    private void handleTradeClick(InventoryClickEvent e) {
        boolean ownRegion;
        boolean shiftIntoTop;
        HumanEntity humanEntity = e.getWhoClicked();
        if (!(humanEntity instanceof Player)) {
            return;
        }
        Player p = (Player)humanEntity;
        TradeSession session = this.activeTradeSessions.get(p.getUniqueId());
        if (session == null) {
            e.setCancelled(true);
            return;
        }
        if (e.getClick() == ClickType.DOUBLE_CLICK) {
            e.setCancelled(true);
            return;
        }
        boolean isA = p.getUniqueId().equals(session.playerA);
        Inventory clickedInv = e.getClickedInventory();
        boolean clickedIsTop = clickedInv != null && clickedInv.equals((Object)session.inventory);
        ItemStack currentItem = e.getCurrentItem();
        boolean bl = shiftIntoTop = !clickedIsTop && e.isShiftClick() && currentItem != null && currentItem.getType() != Material.AIR;
        if (!clickedIsTop && !shiftIntoTop) {
            return;
        }
        if (shiftIntoTop) {
            e.setCancelled(true);
            int start = isA ? 0 : 27;
            int end = isA ? 17 : 44;
            int remaining = currentItem.getAmount();
            for (int i = start; i <= end && remaining > 0; ++i) {
                ItemStack slotItem = session.inventory.getItem(i);
                if (slotItem == null || slotItem.getType() == Material.AIR) {
                    ItemStack toPlace = currentItem.clone();
                    toPlace.setAmount(Math.min(remaining, currentItem.getMaxStackSize()));
                    session.inventory.setItem(i, toPlace);
                    remaining -= toPlace.getAmount();
                    continue;
                }
                if (!slotItem.isSimilar(currentItem) || slotItem.getAmount() >= slotItem.getMaxStackSize()) continue;
                int space = slotItem.getMaxStackSize() - slotItem.getAmount();
                int move = Math.min(space, remaining);
                slotItem.setAmount(slotItem.getAmount() + move);
                remaining -= move;
            }
            int moved = currentItem.getAmount() - remaining;
            if (moved > 0) {
                if (remaining <= 0) {
                    clickedInv.setItem(e.getSlot(), null);
                } else {
                    ItemStack left = currentItem.clone();
                    left.setAmount(remaining);
                    clickedInv.setItem(e.getSlot(), left);
                }
                p.updateInventory();
                this.resetTradeConfirmations(session);
                this.clickSound(p);
            } else {
                this.errorSound(p);
            }
            return;
        }
        int rawSlot = e.getRawSlot();
        if (rawSlot == 20 || rawSlot == 22 || rawSlot == 24 || rawSlot == 45 || rawSlot == 49 || rawSlot == 53) {
            e.setCancelled(true);
            switch (rawSlot) {
                case 49: {
                    this.cancelTradeSession(session, "cancelled");
                    break;
                }
                case 20: {
                    if (isA) {
                        this.awaitingChatInput.put(p.getUniqueId(), "trade_money");
                        this.msgKey(p, "trade.money-prompt", new String[0]);
                        break;
                    }
                    this.errorSound(p);
                    break;
                }
                case 24: {
                    if (!isA) {
                        this.awaitingChatInput.put(p.getUniqueId(), "trade_money");
                        this.msgKey(p, "trade.money-prompt", new String[0]);
                        break;
                    }
                    this.errorSound(p);
                    break;
                }
                case 45: {
                    if (isA) {
                        session.confirmedA = true;
                        this.clickSound(p);
                        this.refreshTradeGui(session);
                        if (!session.confirmedA || !session.confirmedB) break;
                        this.executeTradeSession(session);
                        break;
                    }
                    this.errorSound(p);
                    break;
                }
                case 53: {
                    if (!isA) {
                        session.confirmedB = true;
                        this.clickSound(p);
                        this.refreshTradeGui(session);
                        if (!session.confirmedA || !session.confirmedB) break;
                        this.executeTradeSession(session);
                        break;
                    }
                    this.errorSound(p);
                    break;
                }
                default: {
                    this.errorSound(p);
                }
            }
            return;
        }
        boolean bl2 = isA ? rawSlot >= 0 && rawSlot <= 17 : (ownRegion = rawSlot >= 27 && rawSlot <= 44);
        if (ownRegion) {
            this.resetTradeConfirmations(session);
            return;
        }
        e.setCancelled(true);
    }

    private void resetTradeConfirmations(TradeSession session) {
        if (session.confirmedA || session.confirmedB) {
            session.confirmedA = false;
            session.confirmedB = false;
            this.refreshTradeGui(session);
        }
    }

    private void cancelTradeSession(TradeSession session, String reason) {
        if (session.finished) {
            return;
        }
        session.finished = true;
        Player a = Bukkit.getPlayer((UUID)session.playerA);
        Player b = Bukkit.getPlayer((UUID)session.playerB);
        this.returnTradeItems(session.inventory, 0, 17, session.playerA, a);
        this.returnTradeItems(session.inventory, 27, 44, session.playerB, b);
        this.activeTradeSessions.remove(session.playerA);
        this.activeTradeSessions.remove(session.playerB);
        if (a != null && a.isOnline()) {
            this.msgKey(a, "trade.cancelled", new String[0]);
            this.addLog(session.playerA, "\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u30ad\u30e3\u30f3\u30bb\u30eb(" + reason + ")");
            if (a.getOpenInventory().getTopInventory().equals((Object)session.inventory)) {
                a.closeInventory();
            }
        }
        if (b != null && b.isOnline()) {
            this.msgKey(b, "trade.cancelled", new String[0]);
            this.addLog(session.playerB, "\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u30ad\u30e3\u30f3\u30bb\u30eb(" + reason + ")");
            if (b.getOpenInventory().getTopInventory().equals((Object)session.inventory)) {
                b.closeInventory();
            }
        }
    }

    private void returnTradeItems(Inventory inv, int startSlot, int endSlot, UUID ownerId, Player ownerOnline) {
        for (int i = startSlot; i <= endSlot; ++i) {
            ItemStack item = inv.getItem(i);
            if (item == null || item.getType() == Material.AIR) continue;
            if (ownerOnline != null && ownerOnline.isOnline()) {
                HashMap overflow = ownerOnline.getInventory().addItem(new ItemStack[]{item});
                for (ItemStack over : overflow.values()) {
                    ownerOnline.getWorld().dropItemNaturally(ownerOnline.getLocation(), over);
                }
            } else {
                this.addPendingItem(ownerId, item);
            }
            inv.setItem(i, null);
        }
    }

    private void executeTradeSession(TradeSession session) {
        String nameB;
        if (session.finished) {
            return;
        }
        session.finished = true;
        OfflinePlayer offA = Bukkit.getOfflinePlayer((UUID)session.playerA);
        OfflinePlayer offB = Bukkit.getOfflinePlayer((UUID)session.playerB);
        if (econ.getBalance(offA) < session.moneyOfferedA || econ.getBalance(offB) < session.moneyOfferedB) {
            session.finished = false;
            this.cancelTradeSession(session, "insufficient-funds");
            return;
        }
        List<ItemStack> itemsFromA = this.collectTradeItems(session.inventory, 0, 17);
        List<ItemStack> itemsFromB = this.collectTradeItems(session.inventory, 27, 44);
        if (session.moneyOfferedA > 0.0) {
            econ.withdrawPlayer(offA, session.moneyOfferedA);
            econ.depositPlayer(offB, session.moneyOfferedA);
        }
        if (session.moneyOfferedB > 0.0) {
            econ.withdrawPlayer(offB, session.moneyOfferedB);
            econ.depositPlayer(offA, session.moneyOfferedB);
        }
        Player a = Bukkit.getPlayer((UUID)session.playerA);
        Player b = Bukkit.getPlayer((UUID)session.playerB);
        this.giveTradeItems(itemsFromA, session.playerB, b);
        this.giveTradeItems(itemsFromB, session.playerA, a);
        session.inventory.clear();
        this.activeTradeSessions.remove(session.playerA);
        this.activeTradeSessions.remove(session.playerB);
        String nameA = offA.getName() != null ? offA.getName() : session.playerA.toString();
        String string = nameB = offB.getName() != null ? offB.getName() : session.playerB.toString();
        if (a != null) {
            this.msgKey(a, "trade.completed", new String[0]);
            a.playSound(a.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            if (a.getOpenInventory().getTopInventory().equals((Object)session.inventory)) {
                a.closeInventory();
            }
        }
        if (b != null) {
            this.msgKey(b, "trade.completed", new String[0]);
            b.playSound(b.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            if (b.getOpenInventory().getTopInventory().equals((Object)session.inventory)) {
                b.closeInventory();
            }
        }
        this.addLog(session.playerA, "\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u6210\u7acb: " + nameB + " \u3068\u306e\u9593\u3067 \u30a2\u30a4\u30c6\u30e0" + itemsFromA.size() + "\u70b9" + (String)(session.moneyOfferedA > 0.0 ? "+" + this.fmtCur(session.moneyOfferedA) : "") + " \u3092\u6e21\u3057\u3001\u30a2\u30a4\u30c6\u30e0" + itemsFromB.size() + "\u70b9" + (String)(session.moneyOfferedB > 0.0 ? "+" + this.fmtCur(session.moneyOfferedB) : "") + " \u3092\u53d7\u3051\u53d6\u3063\u305f");
        this.addLog(session.playerB, "\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u6210\u7acb: " + nameA + " \u3068\u306e\u9593\u3067 \u30a2\u30a4\u30c6\u30e0" + itemsFromB.size() + "\u70b9" + (String)(session.moneyOfferedB > 0.0 ? "+" + this.fmtCur(session.moneyOfferedB) : "") + " \u3092\u6e21\u3057\u3001\u30a2\u30a4\u30c6\u30e0" + itemsFromA.size() + "\u70b9" + (String)(session.moneyOfferedA > 0.0 ? "+" + this.fmtCur(session.moneyOfferedA) : "") + " \u3092\u53d7\u3051\u53d6\u3063\u305f");
        this.sendDiscordWebhook("\ud83e\udd1d **" + nameA + "** \u3068 **" + nameB + "** \u306e\u9593\u3067\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u304c\u6210\u7acb\u3057\u307e\u3057\u305f\u3002(" + nameA + ": \u30a2\u30a4\u30c6\u30e0" + itemsFromA.size() + "\u70b9" + (String)(session.moneyOfferedA > 0.0 ? "+" + this.fmtCur(session.moneyOfferedA) : "") + " \u21d4 " + nameB + ": \u30a2\u30a4\u30c6\u30e0" + itemsFromB.size() + "\u70b9" + (String)(session.moneyOfferedB > 0.0 ? "+" + this.fmtCur(session.moneyOfferedB) : "") + ")");
    }

    private List<ItemStack> collectTradeItems(Inventory inv, int startSlot, int endSlot) {
        ArrayList<ItemStack> list = new ArrayList<ItemStack>();
        for (int i = startSlot; i <= endSlot; ++i) {
            ItemStack item = inv.getItem(i);
            if (item == null || item.getType() == Material.AIR) continue;
            list.add(item.clone());
            inv.setItem(i, null);
        }
        return list;
    }

    private void giveTradeItems(List<ItemStack> items, UUID receiverId, Player receiverOnline) {
        if (items.isEmpty()) {
            return;
        }
        if (receiverOnline != null && receiverOnline.isOnline()) {
            for (ItemStack item : items) {
                HashMap overflow = receiverOnline.getInventory().addItem(new ItemStack[]{item});
                for (ItemStack over : overflow.values()) {
                    receiverOnline.getWorld().dropItemNaturally(receiverOnline.getLocation(), over);
                }
            }
        } else {
            for (ItemStack item : items) {
                this.addPendingItem(receiverId, item);
            }
        }
    }

    private void openBankHubGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tBankHub);
        gui.setItem(10, this.createItem(Material.CHEST, "<aqua><bold>\u500b\u4eba\u53e3\u5ea7\u30fb\u9810\u91d1</bold></aqua>", new String[0]));
        gui.setItem(12, this.createItem(Material.EMERALD, "<green><bold>\u878d\u8cc7\u5e02\u5834\uff08\u501f\u308a\u308b\uff09</bold></green>", new String[0]));
        gui.setItem(14, this.createItem(Material.GOLD_INGOT, "<yellow><bold>\u501f\u91d1\u8fd4\u6e08\u7a93\u53e3\uff08\u8fd4\u3059\uff09</bold></yellow>", new String[0]));
        if (this.bankers.contains(p.getUniqueId())) {
            gui.setItem(16, this.createItem(Material.NETHER_STAR, "<red><bold>\u982d\u53d6\u30b3\u30f3\u30c8\u30ed\u30fc\u30eb\u30d1\u30cd\u30eb</bold></red>", new String[0]));
        } else {
            gui.setItem(16, this.createItem(Material.ENDER_EYE, "<dark_purple><bold>\u9280\u884c\u8a2d\u7acb\u30aa\u30d5\u30a3\u30b9</bold></dark_purple>", new String[0]));
        }
        gui.setItem(19, this.createItem(Material.DIAMOND, "<aqua><bold>\u56fd\u55b6\u516c\u5eab (\u30b5\u30fc\u30d0\u30fc\u30ed\u30fc\u30f3)</bold></aqua>", new String[0]));
        gui.setItem(21, this.createItem(Material.GOLD_BLOCK, "<gold><bold>\u9ad8\u91d1\u5229\u30fb\u5b9a\u671f\u9810\u91d1</bold></gold>", new String[0]));
        gui.setItem(23, this.createItem(Material.ANVIL, "<dark_purple><bold>\ud83c\udffa \u8cea\u5c4b\u30fb\u62c5\u4fdd\u4ed8\u304d\u878d\u8cc7</bold></dark_purple>", "<gray>\u30ec\u30a2\u30a2\u30a4\u30c6\u30e0\u3092\u62c5\u4fdd\u306b\u304a\u91d1\u3092\u501f\u308a\u3089\u308c\u307e\u3059</gray>"));
        gui.setItem(25, this.createItem(Material.GOLDEN_APPLE, "<blue><bold>\ud83d\udee1 \u751f\u547d\u4fdd\u967a\u7a93\u53e3</bold></blue>", "<gray>\u6b7b\u4ea1\u6642\u306e\u30ed\u30b9\u306b\u5099\u3048\u3066\u52a0\u5165\u3067\u304d\u307e\u3059</gray>"));
        gui.setItem(31, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openMyPageGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tMyPage);
        UUID u = p.getUniqueId();
        gui.setItem(10, this.createItem(Material.EMERALD_BLOCK, "<green><bold>\u500b\u4eba\u4fe1\u7528\u60c5\u5831\u30bb\u30f3\u30bf\u30fc</bold></green>", new String[0]));
        gui.setItem(12, this.createItem(Material.FIREWORK_ROCKET, "<gold><bold>\ud83c\udf96 \u5b9f\u7e3e\u30fb\u79f0\u53f7\u30b3\u30ec\u30af\u30b7\u30e7\u30f3</bold></gold>", new String[0]));
        gui.setItem(14, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>\ud83c\udfc6 \u8cc7\u7523\u30e9\u30f3\u30ad\u30f3\u30b0</bold></yellow>", new String[0]));
        gui.setItem(16, this.createItem(Material.WRITTEN_BOOK, "<aqua><bold>\ud83d\udcdc \u53d6\u5f15\u5c65\u6b74\u3092\u898b\u308b</bold></aqua>", new String[0]));
        boolean newsOff = this.newsBroadcastOff.contains(u);
        if (newsOff) {
            gui.setItem(19, this.createItem(Material.NOTE_BLOCK, "<gray><bold>\ud83d\udd15 \u7d4c\u6e08\u30cb\u30e5\u30fc\u30b9\u653e\u9001: OFF</bold></gray>", "<gray>\u30af\u30ea\u30c3\u30af\u3067ON\u306b\u5207\u308a\u66ff\u3048</gray>"));
        } else {
            gui.setItem(19, this.createItem(Material.BELL, "<green><bold>\ud83d\udd14 \u7d4c\u6e08\u30cb\u30e5\u30fc\u30b9\u653e\u9001: ON</bold></green>", "<gray>\u30af\u30ea\u30c3\u30af\u3067OFF\u306b\u5207\u308a\u66ff\u3048</gray>"));
        }
        gui.setItem(21, this.createItem(Material.GOLD_BLOCK, "<gold><bold>\ud83c\udfdb \u56fd\u5eab\u6b8b\u9ad8\u3092\u898b\u308b</bold></gold>", "<gray>\u30b5\u30fc\u30d0\u30fc\u5168\u4f53\u306e\u7a0e\u53ce\u30d7\u30fc\u30eb\u3092\u78ba\u8a8d\u3057\u307e\u3059</gray>"));
        gui.setItem(23, this.createItem(Material.KNOWLEDGE_BOOK, "<light_purple><bold>\ud83d\udcd8 \u521d\u5fc3\u8005\u30ac\u30a4\u30c9\u3092\u958b\u304f</bold></light_purple>", new String[0]));
        long remain = this.hubItemReissueCooldown.getOrDefault(u, 0L) - System.currentTimeMillis();
        if (remain > 0L) {
            gui.setItem(25, this.createItem(Material.BARRIER, "<dark_red><bold>\ud83d\udcd6 \u7d4c\u6e08\u624b\u5e33\u306e\u518d\u767a\u884c</bold></dark_red>", "<gray>\u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d: \u6b8b\u308a" + (remain / 1000L + 1L) + "\u79d2</gray>"));
        } else {
            gui.setItem(25, this.createItem(Material.BOOK, "<white><bold>\ud83d\udcd6 \u7d4c\u6e08\u624b\u5e33\u306e\u518d\u767a\u884c</bold></white>", "<gray>\u306a\u304f\u3057\u305f\u5834\u5408\u3001\u624b\u6301\u3061\u306b\u518d\u767a\u884c\u3057\u307e\u3059</gray>"));
        }
        gui.setItem(27, this.createItem(Material.SUNFLOWER, "<gold><bold>\ud83c\udf81 \u56fd\u5eab\u3078\u5bc4\u4ed8\u3059\u308b</bold></gold>", "<gray>\u56fd\u5eab\u3078\u4efb\u610f\u306e\u91d1\u984d\u3092\u5bc4\u4ed8\u3067\u304d\u307e\u3059</gray>"));
        List<InstallmentPlan> myPlans = this.installmentPlans.get(u);
        int planCount = myPlans == null ? 0 : myPlans.size();
        gui.setItem(29, this.createItem(Material.CLOCK, "<light_purple><bold>\ud83d\udcc5 \u5206\u5272\u6255\u3044\u306e\u72b6\u6cc1</bold></light_purple>", "<gray>\u73fe\u5728\u306e\u5206\u5272\u6255\u3044\u30d7\u30e9\u30f3\u6570:</gray> <yellow>" + planCount + "\u4ef6</yellow>"));
        gui.setItem(31, this.createItem(Material.ENDER_CHEST, "<aqua><bold>\ud83d\udce6 \u53d7\u53d6\u7bb1\u3092\u78ba\u8a8d</bold></aqua>", "<gray>\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u843d\u672d\u54c1\u306a\u3069\u3092\u518d\u53d7\u53d6\u3057\u307e\u3059</gray>"));
        boolean pwSet = this.webPasswordHash.containsKey(u);
        gui.setItem(33, this.createItem(Material.MAP, "<light_purple><bold>\ud83c\udf10 Web\u500b\u4eba\u30da\u30fc\u30b8\u306e\u30ea\u30f3\u30af</bold></light_purple>", "<gray>\u30af\u30ea\u30c3\u30af\u3067\u30ea\u30f3\u30af\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u8868\u793a</gray>"));
        gui.setItem(32, this.createItem(Material.TRIPWIRE_HOOK, "<light_purple><bold>\ud83d\udd11 \u30aa\u30d5\u30e9\u30a4\u30f3\u64cd\u4f5c\u7528\u30d1\u30b9\u30ef\u30fc\u30c9</bold></light_purple>", pwSet ? "<gray>\u73fe\u5728: <green>\u8a2d\u5b9a\u6e08\u307f</green></gray>" : "<gray>\u73fe\u5728: <red>\u672a\u8a2d\u5b9a</red></gray>", "<gray>\u30af\u30ea\u30c3\u30af\u3067\u30c1\u30e3\u30c3\u30c8\u5165\u529b\u306b\u5207\u308a\u66ff\u3048</gray>"));
        gui.setItem(35, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openInstallmentListGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tInstallmentList);
        UUID u = p.getUniqueId();
        List<InstallmentPlan> plans = this.installmentPlans.get(u);
        long now = System.currentTimeMillis();
        if (plans == null || plans.isEmpty()) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<gray><bold>\u5206\u5272\u6255\u3044\u306e\u30d7\u30e9\u30f3\u306f\u3042\u308a\u307e\u305b\u3093</bold></gray>", new String[0]));
        } else {
            int slot = 0;
            for (InstallmentPlan plan : plans) {
                if (slot >= 45) break;
                long remainMs = Math.max(0L, plan.nextDueTime - now);
                long remainH = remainMs / 3600000L;
                long remainM = remainMs / 60000L % 60L;
                String dueStr = new SimpleDateFormat("MM/dd HH:mm").format(new Date(plan.nextDueTime));
                gui.setItem(slot++, this.createItem(Material.PAPER, "<light_purple><bold>" + plan.description + "</bold></light_purple>", "<gray>1\u56de\u3042\u305f\u308a\u306e\u652f\u6255\u984d:</gray> <gold>" + String.format("%.2f", plan.installmentAmount) + "\u5186</gold>", "<gray>\u6b8b\u308a\u56de\u6570:</gray> <yellow>" + plan.installmentsRemaining + "\u56de</yellow>", "<gray>\u6b21\u56de\u671f\u65e5:</gray> <white>" + dueStr + "</white> <dark_gray>(\u3042\u3068 " + remainH + "h" + remainM + "m)</dark_gray>"));
            }
        }
        gui.setItem(49, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openMainGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tMain);
        gui.setItem(10, this.createItem(Material.CHEST, "<aqua><bold>\u500b\u4eba\u53e3\u5ea7\u30fb\u9810\u91d1</bold></aqua>", new String[0]));
        gui.setItem(12, this.createItem(Material.EMERALD, "<green><bold>\u878d\u8cc7\u5e02\u5834\uff08\u501f\u308a\u308b\uff09</bold></green>", new String[0]));
        gui.setItem(14, this.createItem(Material.GOLD_INGOT, "<yellow><bold>\u501f\u91d1\u8fd4\u6e08\u7a93\u53e3\uff08\u8fd4\u3059\uff09</bold></yellow>", new String[0]));
        if (this.bankers.contains(p.getUniqueId())) {
            gui.setItem(16, this.createItem(Material.NETHER_STAR, "<red><bold>\u982d\u53d6\u30b3\u30f3\u30c8\u30ed\u30fc\u30eb\u30d1\u30cd\u30eb</bold></red>", new String[0]));
        } else {
            gui.setItem(16, this.createItem(Material.ENDER_EYE, "<dark_purple><bold>\u9280\u884c\u8a2d\u7acb\u30aa\u30d5\u30a3\u30b9</bold></dark_purple>", new String[0]));
        }
        gui.setItem(19, this.createItem(Material.DIAMOND, "<aqua><bold>\u56fd\u55b6\u516c\u5eab (\u30b5\u30fc\u30d0\u30fc\u30ed\u30fc\u30f3)</bold></aqua>", new String[0]));
        gui.setItem(21, this.createItem(Material.GOLD_BLOCK, "<gold><bold>\u9ad8\u91d1\u5229\u30fb\u5b9a\u671f\u9810\u91d1</bold></gold>", new String[0]));
        gui.setItem(23, this.createItem(Material.EMERALD_BLOCK, "<green><bold>\u500b\u4eba\u4fe1\u7528\u60c5\u5831\u30bb\u30f3\u30bf\u30fc</bold></green>", new String[0]));
        gui.setItem(31, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>\ud83c\udfc6 \u8cc7\u7523\u30e9\u30f3\u30ad\u30f3\u30b0</bold></yellow>", "<gray>\u30b5\u30fc\u30d0\u30fc\u5185\u306e\u8cc7\u7523\u5bb6\u30c8\u30c3\u30d710\u3092\u8868\u793a</gray>"));
        gui.setItem(27, this.createItem(Material.ANVIL, "<dark_purple><bold>\ud83c\udffa \u8cea\u5c4b\u30fb\u62c5\u4fdd\u4ed8\u304d\u878d\u8cc7</bold></dark_purple>", "<gray>\u30ec\u30a2\u30a2\u30a4\u30c6\u30e0\u3092\u62c5\u4fdd\u306b\u304a\u91d1\u3092\u501f\u308a\u3089\u308c\u307e\u3059</gray>"));
        gui.setItem(29, this.createItem(Material.GOLDEN_APPLE, "<blue><bold>\ud83d\udee1 \u751f\u547d\u4fdd\u967a\u7a93\u53e3</bold></blue>", "<gray>\u6b7b\u4ea1\u6642\u306e\u30ed\u30b9\u306b\u5099\u3048\u3066\u52a0\u5165\u3067\u304d\u307e\u3059</gray>"));
        gui.setItem(33, this.createItem(Material.FIREWORK_ROCKET, "<gold><bold>\ud83c\udf96 \u5b9f\u7e3e\u30fb\u79f0\u53f7\u30b3\u30ec\u30af\u30b7\u30e7\u30f3</bold></gold>", "<gray>\u89e3\u653e\u3057\u305f\u5b9f\u7e3e\u3092\u78ba\u8a8d\u3067\u304d\u307e\u3059</gray>"));
        gui.setItem(28, this.createItem(Material.ITEM_FRAME, "<green><bold>\ud83d\udd28 \u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u30cf\u30a6\u30b9</bold></green>", "<gray>\u30a2\u30a4\u30c6\u30e0\u3092\u51fa\u54c1\u30fb\u5165\u672d\u3067\u304d\u307e\u3059</gray>"));
        gui.setItem(25, this.createItem(Material.COMPASS, "<blue><bold>\ud83d\udcc8 \u4e16\u754c\u682a\u5f0f\u5e02\u5834</bold></blue>", "<gray>\u73fe\u5b9f\u306e\u682a\u3092\u691c\u7d22\u3057\u3066\u58f2\u8cb7</gray>"));
        gui.setItem(17, this.createItem(Material.HOPPER, "<green><bold>\ud83c\udf3e \u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7</bold></green>", "<gray>\u9271\u77f3\u30fb\u8fb2\u4f5c\u7269\u30fb\u30c9\u30ed\u30c3\u30d7\u54c1\u3092\u58f2\u8cb7</gray>"));
        gui.setItem(15, this.createItem(Material.LEATHER_HORSE_ARMOR, "<gold><bold>\ud83e\uddf3 \u5de1\u56de\u5546\u4eba</bold></gold>", "<gray>\u73fe\u5728\u5730\u3092\u78ba\u8a8d\u3059\u308b(\u8cfc\u5165\u306f\u73fe\u5730\u306eNPC\u304b\u3089)</gray>"));
        gui.setItem(32, this.createItem(Material.FIREWORK_STAR, "<gold><bold>\ud83c\udf9f \u5b9d\u304f\u3058</bold></gold>", "<gray>\u30c1\u30b1\u30c3\u30c8\u3092\u8cfc\u5165\u3057\u3066\u5b9a\u671f\u62bd\u9078\u306b\u53c2\u52a0</gray>"));
        gui.setItem(26, this.createItem(Material.BARREL, "<gold><bold>\ud83c\udfec \u30ec\u30f3\u30bf\u30eb\u5009\u5eab</bold></gold>", "<gray>\u5bb6\u8cc3\u3092\u6255\u3063\u3066\u5c02\u7528\u306e\u53ce\u7d0d\u5009\u5eab\u3092\u501f\u308a\u3089\u308c\u307e\u3059</gray>"));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openLeaderboardGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tLeaderboard);
        HashSet<UUID> targets = new HashSet<UUID>();
        targets.addAll(this.personalBank.keySet());
        for (Object online : Bukkit.getOnlinePlayers()) {
            targets.add(online.getUniqueId());
        }
        ArrayList<Map.Entry<UUID, Double>> ranking = new ArrayList<Map.Entry<UUID, Double>>();
        for (UUID target : targets) {
            OfflinePlayer op = Bukkit.getOfflinePlayer((UUID)target);
            double d = 0.0;
            if (op.isOnline() && op.getPlayer() != null) {
                d = econ.getBalance((OfflinePlayer)op.getPlayer());
            } else if (econ.hasAccount(op)) {
                d = econ.getBalance(op);
            }
            double bankAmt = this.personalBank.getOrDefault(target, 0.0);
            double fixedAmt = this.fixedDeposit.getOrDefault(target, 0.0) + this.fixedDeposit2.getOrDefault(target, 0.0) + this.fixedDeposit3.getOrDefault(target, 0.0);
            double total = d + bankAmt + fixedAmt;
            ranking.add(Map.entry(target, total));
        }
        ranking.sort((a, b) -> Double.compare((Double)b.getValue(), (Double)a.getValue()));
        int slot = 0;
        int rank = 1;
        for (Map.Entry entry : ranking) {
            if (slot >= 45 || rank > 45) break;
            String name = Bukkit.getOfflinePlayer((UUID)((UUID)entry.getKey())).getName();
            if (name == null) {
                name = "unknown";
            }
            Material mat = rank == 1 ? Material.GOLD_BLOCK : (rank == 2 ? Material.IRON_BLOCK : (rank == 3 ? Material.COPPER_BLOCK : Material.PAPER));
            gui.setItem(slot++, this.createItem(mat, "<gold><bold>#" + rank + " " + name + "</bold></gold>", "<gray>\u7dcf\u8cc7\u7523:</gray> <green>" + (long)((Double)entry.getValue()).doubleValue() + "\u5186</green>"));
            ++rank;
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openCollateralSelectGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tCollateralSelect);
        ItemStack[] contents = p.getInventory().getStorageContents();
        for (int i = 0; i < 45 && i < contents.length; ++i) {
            ItemStack item = contents[i];
            if (item == null || item.getType() == Material.AIR) continue;
            gui.setItem(i, item.clone());
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAuctionSelectGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tAuctionSelect);
        ItemStack[] contents = p.getInventory().getStorageContents();
        for (int i = 0; i < 45 && i < contents.length; ++i) {
            ItemStack item = contents[i];
            if (item == null || item.getType() == Material.AIR) continue;
            gui.setItem(i, item.clone());
        }
        gui.setItem(49, this.createItem(Material.NETHER_STAR, "<green><bold>\u51fa\u54c1\u3059\u308b\u30a2\u30a4\u30c6\u30e0\u3092\u9078\u3093\u3067\u304f\u3060\u3055\u3044</bold></green>", "<gray>\u4e0b\u306b\u8868\u793a\u3055\u308c\u3066\u3044\u308b\u306e\u306f\u3042\u306a\u305f\u306e\u6301\u3061\u7269\u3067\u3059\u3002</gray>", "<gray>\u51fa\u54c1\u3057\u305f\u3044\u30a2\u30a4\u30c6\u30e0\u3092\u30af\u30ea\u30c3\u30af\u3059\u308b\u3068\u3001</gray>", "<gray>\u305d\u306e\u30a2\u30a4\u30c6\u30e0\u304c\u624b\u5143\u304b\u3089\u53d6\u308a\u51fa\u3055\u308c\u3001\u958b\u59cb\u4fa1\u683c\u306e\u5165\u529b\u306b\u9032\u307f\u307e\u3059\u3002</gray>", "<dark_gray>(\u3053\u306e\u753b\u9762\u81ea\u4f53\u306b\u306f\u4fa1\u683c\u5165\u529b\u6b04\u306f\u3042\u308a\u307e\u305b\u3093\u3002\u6b21\u306f\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u307e\u3059)</dark_gray>"));
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openCollateralGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tCollateral);
        UUID u = p.getUniqueId();
        if (this.collateralItem.containsKey(u)) {
            ItemStack item = this.collateralItem.get(u);
            double loan = this.collateralLoanAmount.getOrDefault(u, 0.0);
            double totalRepay = loan * (1.0 + this.cfgCollateralInterest / 100.0);
            long due = this.collateralDueTime.getOrDefault(u, 0L);
            long remain = due - System.currentTimeMillis();
            String timeStr = remain > 0L ? "<green>\u6b8b\u308a " + remain / 60000L + "\u5206</green>" : "<red>\u671f\u9650\u5207\u308c\uff08\u307e\u3082\u306a\u304f\u6ca1\u53ce\uff09</red>";
            ItemStack display = item.clone();
            ItemMeta dm = display.getItemMeta();
            if (dm != null) {
                dm.displayName(this.mm("<light_purple><bold>\u3010\u9810\u3051\u3066\u3044\u308b\u62c5\u4fdd\u3011</bold></light_purple>"));
                dm.lore(List.of(this.mm("<gray>\u501f\u5165\u984d:</gray> <aqua>" + (long)loan + "\u5186</aqua>"), this.mm("<gray>\u8fd4\u6e08\u7dcf\u984d:</gray> <gold>" + (long)totalRepay + "\u5186</gold>"), this.mm("<gray>\u671f\u9650:</gray> " + timeStr)));
                display.setItemMeta(dm);
            }
            gui.setItem(11, display);
            gui.setItem(15, this.createItem(Material.GOLD_BLOCK, "<gold><bold>\u8fd4\u6e08\u3057\u3066\u62c5\u4fdd\u3092\u53d6\u308a\u623b\u3059</bold></gold>", "<gray>\u8fd4\u6e08\u7dcf\u984d:</gray> <gold>" + (long)totalRepay + "\u5186</gold>"));
        } else {
            ItemStack selected = this.collateralSelection.get(u);
            gui.setItem(11, selected == null ? this.createItem(Material.NAME_TAG, "<gray>\u62c5\u4fdd\u30a2\u30a4\u30c6\u30e0\u672a\u9078\u629e</gray>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30a4\u30f3\u30d9\u30f3\u30c8\u30ea\u304b\u3089\u9078\u629e</gray>") : selected.clone());
            if (selected != null) {
                double value = this.evaluateItemValue(selected);
                double loan = value * this.cfgCollateralLtv;
                gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>\u9078\u629e\u3057\u305f\u30a2\u30a4\u30c6\u30e0\u3092\u62c5\u4fdd\u306b\u501f\u308a\u308b</bold></green>", "<gray>\u30a2\u30a4\u30c6\u30e0:</gray> <white>" + selected.getType().name() + " \u00d7" + selected.getAmount() + "</white>", "<gray>\u9451\u5b9a\u8a55\u4fa1\u984d:</gray> <gold>" + (long)value + "\u5186</gold>", "<gray>\u501f\u5165\u53ef\u80fd\u984d(LTV" + (int)(this.cfgCollateralLtv * 100.0) + "%):</gray> <aqua>" + (long)loan + "\u5186</aqua>", "<gray>\u91d1\u5229:</gray> <red>" + (int)this.cfgCollateralInterest + "%</red>", "<gray>\u8fd4\u6e08\u671f\u9650:</gray> <yellow>" + this.cfgCollateralDurationMs / 60000L + "\u5206</yellow>"));
            } else {
                gui.setItem(13, this.createItem(Material.ANVIL, "<yellow>\u62c5\u4fdd\u3092\u9078\u629e</yellow>", "<gray>\u30a4\u30f3\u30d9\u30f3\u30c8\u30ea\u304b\u3089\u62c5\u4fdd\u306b\u3059\u308b\u30a2\u30a4\u30c6\u30e0\u3092\u9078\u3073\u307e\u3059\u3002</gray>"));
            }
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAuctionGUI(Player p) {
        long myListings;
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tAuction);
        UUID u = p.getUniqueId();
        int slot = 0;
        int sortMode = this.auctionSortMode.getOrDefault(u, 0);
        String query = this.auctionSearchQuery.get(u);
        boolean myOnly = this.auctionMyListingsOnlyFilter.contains(u);
        ArrayList<UUID> ids = new ArrayList<UUID>(this.auctionEndTime.keySet());
        ids.removeIf(id -> {
            ItemStack it = this.auctionItem.get(id);
            UUID seller = this.auctionSeller.get(id);
            if (it == null || seller == null) {
                return true;
            }
            if (myOnly && !seller.equals(u)) {
                return true;
            }
            if (query != null && !query.isEmpty()) {
                boolean matches;
                String q = query.toLowerCase();
                String matName = it.getType().name().toLowerCase();
                ItemMeta im = it.getItemMeta();
                String display = im != null && im.hasDisplayName() ? ChatColor.stripColor((String)im.getDisplayName()).toLowerCase() : null;
                boolean bl = matches = matName.contains(q) || display != null && display.contains(q);
                if (!matches) {
                    return true;
                }
            }
            return false;
        });
        switch (sortMode) {
            case 1: {
                ids.sort(Comparator.comparingDouble(id -> this.auctionBid.getOrDefault(id, 0.0)));
                break;
            }
            case 2: {
                ids.sort(Comparator.comparingDouble(id -> this.auctionBid.getOrDefault(id, 0.0)).reversed());
                break;
            }
            default: {
                ids.sort(Comparator.comparingLong(id -> this.auctionEndTime.getOrDefault(id, Long.MAX_VALUE)));
            }
        }
        int totalPages = Math.max(1, (int)Math.ceil((double)ids.size() / 45.0));
        int page = Math.min(this.auctionPage.getOrDefault(u, 0), totalPages - 1);
        this.auctionPage.put(u, page);
        int fromIndex = Math.min(page * 45, ids.size());
        int toIndex = Math.min(fromIndex + 45, ids.size());
        List pageIds = ids.subList(fromIndex, toIndex);
        for (UUID id2 : pageIds) {
            if (slot >= 45) break;
            ItemStack orig = this.auctionItem.get(id2);
            UUID seller = this.auctionSeller.get(id2);
            if (orig == null || seller == null) continue;
            double bid = this.auctionBid.getOrDefault(id2, 0.0);
            UUID bidder = this.auctionBidder.get(id2);
            Double buyout = this.auctionBuyoutPrice.get(id2);
            long remain = this.auctionEndTime.getOrDefault(id2, 0L) - System.currentTimeMillis();
            String remainStr = remain > 0L ? remain / 60000L + "\u5206" + remain / 1000L % 60L + "\u79d2" : "\u307e\u3082\u306a\u304f\u7d42\u4e86";
            boolean endingSoon = remain > 0L && remain <= 60000L;
            String remainColor = endingSoon ? "red" : "yellow";
            ItemStack display = orig.clone();
            ItemMeta dm = display.getItemMeta();
            if (dm != null) {
                ArrayList<Component> lore = new ArrayList<Component>();
                lore.add(this.mm("<gray>\u51fa\u54c1\u8005:</gray> <white>" + Bukkit.getOfflinePlayer((UUID)seller).getName() + "</white>"));
                lore.add(this.mm("<gray>\u73fe\u5728\u4fa1\u683c:</gray> <gold>" + (long)bid + "\u5186</gold>"));
                if (buyout != null) {
                    lore.add(this.mm("<gray>\u5373\u6c7a\u4fa1\u683c:</gray> <light_purple>" + (long)buyout.doubleValue() + "\u5186</light_purple>"));
                }
                lore.add(this.mm("<gray>\u5165\u672d\u8005:</gray> " + (String)(bidder != null ? "<aqua>" + Bukkit.getOfflinePlayer((UUID)bidder).getName() + "</aqua>" : "<gray>\u307e\u3060\u3044\u307e\u305b\u3093</gray>")));
                lore.add(this.mm("<gray>\u6b8b\u308a\u6642\u9593:</gray> <" + remainColor + ">" + remainStr + (endingSoon ? " \u26a0\u7d42\u4e86\u9593\u8fd1" : "") + "</" + remainColor + ">"));
                if (!seller.equals(u)) {
                    lore.add(this.mm("<green>\u30af\u30ea\u30c3\u30af\u3057\u3066\u5165\u672d</green>"));
                    if (buyout != null) {
                        lore.add(this.mm("<light_purple>\u5373\u6c7a\u4fa1\u683c\u4ee5\u4e0a\u3092\u5165\u529b\u3059\u308b\u3068\u5373\u5ea7\u306b\u8cfc\u5165\u3067\u304d\u307e\u3059</light_purple>"));
                    }
                } else {
                    lore.add(this.mm("<dark_gray>(\u81ea\u5206\u306e\u51fa\u54c1)</dark_gray>"));
                }
                dm.lore(lore);
                dm.getPersistentDataContainer().set(this.auctionIdKey, PersistentDataType.STRING, (Object)id2.toString());
                display.setItemMeta(dm);
            }
            gui.setItem(slot++, display);
        }
        String sortModeName = switch (sortMode) {
            case 1 -> "\u4fa1\u683c\u304c\u5b89\u3044\u9806";
            case 2 -> "\u4fa1\u683c\u304c\u9ad8\u3044\u9806";
            default -> "\u7d42\u4e86\u304c\u8fd1\u3044\u9806";
        };
        gui.setItem(45, this.createItem(Material.COMPASS, "<aqua><bold>\u4e26\u3073\u66ff\u3048</bold></aqua>", "<gray>\u73fe\u5728:</gray> <yellow>" + sortModeName + "</yellow>", "<gray>\u30af\u30ea\u30c3\u30af\u3067\u5207\u308a\u66ff\u3048</gray>"));
        if (query != null && !query.isEmpty()) {
            gui.setItem(46, this.createItem(Material.NAME_TAG, "<aqua><bold>\u691c\u7d22</bold></aqua>", "<gray>\u73fe\u5728\u306e\u691c\u7d22\u8a9e:</gray> <white>" + query + "</white>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u691c\u7d22\u8a9e\u3092\u518d\u5165\u529b</gray>"));
        } else {
            gui.setItem(46, this.createItem(Material.NAME_TAG, "<aqua><bold>\u691c\u7d22</bold></aqua>", "<gray>\u30a2\u30a4\u30c6\u30e0\u540d\u3067\u7d5e\u308a\u8fbc\u307f\u307e\u3059</gray>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b</gray>"));
        }
        if (myOnly) {
            gui.setItem(47, this.createItem(Material.WRITTEN_BOOK, "<green><bold>\u81ea\u5206\u306e\u51fa\u54c1\u306e\u307f\u8868\u793a: ON</bold></green>", "<gray>\u30af\u30ea\u30c3\u30af\u3067\u3059\u3079\u3066\u8868\u793a\u306b\u623b\u3057\u307e\u3059</gray>"));
        } else {
            gui.setItem(47, this.createItem(Material.BOOK, "<gray><bold>\u81ea\u5206\u306e\u51fa\u54c1\u306e\u307f\u8868\u793a: OFF</bold></gray>", "<gray>\u30af\u30ea\u30c3\u30af\u3067\u81ea\u5206\u306e\u51fa\u54c1\u3060\u3051\u8868\u793a\u3057\u307e\u3059</gray>"));
        }
        gui.setItem(48, this.createItem(Material.PLAYER_HEAD, "<gold><bold>\u843d\u672d\u58f2\u4e0a\u30e9\u30f3\u30ad\u30f3\u30b0</bold></gold>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u8868\u793a</gray>"));
        if (query != null && !query.isEmpty()) {
            gui.setItem(50, this.createItem(Material.BARRIER, "<red>\u691c\u7d22\u3092\u30af\u30ea\u30a2</red>", "<gray>\u73fe\u5728\u306e\u691c\u7d22\u8a9e:</gray> <white>" + query + "</white>"));
        }
        if (totalPages > 1) {
            if (page > 0) {
                gui.setItem(51, this.createItem(Material.ARROW, "<aqua><bold>\u25c0 \u524d\u306e\u30da\u30fc\u30b8</bold></aqua>", "<gray>" + page + " / " + totalPages + "\u30da\u30fc\u30b8</gray>"));
            }
            if (page < totalPages - 1) {
                gui.setItem(52, this.createItem(Material.ARROW, "<aqua><bold>\u6b21\u306e\u30da\u30fc\u30b8 \u25b6</bold></aqua>", "<gray>" + (page + 2) + " / " + totalPages + "\u30da\u30fc\u30b8</gray>"));
            }
        }
        if ((myListings = this.auctionSeller.values().stream().filter(s -> s.equals(u)).count()) >= (long)this.cfgAuctionMaxListingsPerPlayer) {
            gui.setItem(49, this.createItem(Material.BARRIER, "<red>\u540c\u6642\u51fa\u54c1\u6570\u306e\u4e0a\u9650\u3067\u3059</red>", "<gray>\u4e0a\u9650: " + this.cfgAuctionMaxListingsPerPlayer + "\u4ef6</gray>"));
        } else {
            gui.setItem(49, this.createItem(Material.EMERALD, "<green><bold>\u30a4\u30f3\u30d9\u30f3\u30c8\u30ea\u304b\u3089\u51fa\u54c1\u3059\u308b</bold></green>", "<gray>\u51fa\u54c1\u671f\u9593:</gray> <yellow>" + this.cfgAuctionDurationMs / 60000L + "\u5206</yellow>", "<gray>\u843d\u672d\u6642\u306e\u624b\u6570\u6599:</gray> <red>" + (int)(this.cfgAuctionFeeRate * 100.0) + "%</red>", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u51fa\u54c1\u30a2\u30a4\u30c6\u30e0\u3092\u9078\u629e</yellow>"));
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAuctionCancelConfirmGUI(Player p, UUID auctionId) {
        ItemStack orig = this.auctionItem.get(auctionId);
        UUID seller = this.auctionSeller.get(auctionId);
        if (orig == null || seller == null || !seller.equals(p.getUniqueId()) || this.auctionBidder.containsKey(auctionId)) {
            this.openAuctionGUI(p);
            return;
        }
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tAuctionCancelConfirm);
        double bid = this.auctionBid.getOrDefault(auctionId, 0.0);
        long remain = this.auctionEndTime.getOrDefault(auctionId, 0L) - System.currentTimeMillis();
        String remainStr = remain > 0L ? remain / 60000L + "\u5206" + remain / 1000L % 60L + "\u79d2" : "\u307e\u3082\u306a\u304f\u7d42\u4e86";
        ItemStack display = orig.clone();
        ItemMeta dm = display.getItemMeta();
        if (dm != null) {
            ArrayList<Component> lore = new ArrayList<Component>();
            lore.add(this.mm("<gray>\u958b\u59cb/\u73fe\u5728\u4fa1\u683c:</gray> <gold>" + (long)bid + "\u5186</gold>"));
            lore.add(this.mm("<gray>\u5165\u672d\u8005:</gray> <gray>\u307e\u3060\u3044\u307e\u305b\u3093</gray>"));
            lore.add(this.mm("<gray>\u6b8b\u308a\u6642\u9593:</gray> <yellow>" + remainStr + "</yellow>"));
            dm.lore(lore);
            display.setItemMeta(dm);
        }
        gui.setItem(13, display);
        ItemStack confirmBtn = this.createItem(Material.EMERALD, "<green><bold>\u306f\u3044\u3001\u53d6\u308a\u4e0b\u3052\u308b</bold></green>", "<gray>\u51fa\u54c1\u3092\u53d6\u308a\u4e0b\u3052\u3066\u30a2\u30a4\u30c6\u30e0\u3092\u53d7\u53d6\u7bb1\u306b\u8fd4\u5374\u3057\u307e\u3059\u3002</gray>");
        ItemMeta cm = confirmBtn.getItemMeta();
        if (cm != null) {
            cm.getPersistentDataContainer().set(this.auctionIdKey, PersistentDataType.STRING, (Object)auctionId.toString());
            confirmBtn.setItemMeta(cm);
        }
        gui.setItem(11, confirmBtn);
        gui.setItem(15, this.createItem(Material.BARRIER, "<red><bold>\u623b\u308b</bold></red>", "<gray>\u51fa\u54c1\u3092\u53d6\u308a\u4e0b\u3052\u305a\u306b\u4e00\u89a7\u3078\u623b\u308a\u307e\u3059\u3002</gray>"));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAuctionRankingGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tAuctionRanking);
        ArrayList<Map.Entry<UUID, Double>> ranking = new ArrayList<Map.Entry<UUID, Double>>(this.auctionTotalSoldAmount.entrySet());
        ranking.sort((a, b) -> Double.compare((Double)b.getValue(), (Double)a.getValue()));
        int slot = 0;
        int rank = 1;
        for (Map.Entry entry : ranking) {
            String string;
            String openTag;
            Object medal;
            if (slot >= 10 || rank > 10) break;
            String name = Bukkit.getOfflinePlayer((UUID)((UUID)entry.getKey())).getName();
            if (name == null) {
                name = "unknown";
            }
            switch (rank) {
                case 1: {
                    Object object = "\ud83e\udd47";
                    break;
                }
                case 2: {
                    Object object = "\ud83e\udd48";
                    break;
                }
                case 3: {
                    Object object = "\ud83e\udd49";
                    break;
                }
                default: {
                    Object object = medal = "#" + rank;
                }
            }
            if (rank == 3) {
                v1 = "<color:#cd7f32>";
            } else {
                switch (rank) {
                    case 1: {
                        v1 = "<gold>";
                        break;
                    }
                    case 2: {
                        v1 = "<white>";
                        break;
                    }
                    default: {
                        v1 = openTag = "<gray>";
                    }
                }
            }
            if (rank == 3) {
                string = "</color>";
            } else {
                switch (rank) {
                    case 1: {
                        string = "</gold>";
                        break;
                    }
                    case 2: {
                        string = "</white>";
                        break;
                    }
                    default: {
                        string = "</gray>";
                    }
                }
            }
            String closeTag = string;
            gui.setItem(slot++, this.createItem(Material.PLAYER_HEAD, openTag + "<bold>" + (String)medal + " " + name + "</bold>" + closeTag, "<gray>\u7d2f\u8a08\u843d\u672d\u58f2\u4e0a:</gray> <green>" + (long)((Double)entry.getValue()).doubleValue() + "\u5186</green>"));
            ++rank;
        }
        if (ranking.isEmpty()) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<gray>\u307e\u3060\u843d\u672d\u5b9f\u7e3e\u304c\u3042\u308a\u307e\u305b\u3093</gray>", new String[0]));
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openInsuranceGUI(Player p) {
        boolean active;
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tInsurance);
        UUID u = p.getUniqueId();
        long expiry = this.insuranceExpiry.getOrDefault(u, 0L);
        boolean bl = active = expiry > System.currentTimeMillis();
        if (active) {
            long remain = expiry - System.currentTimeMillis();
            gui.setItem(13, this.createItem(Material.SHIELD, "<blue><bold>\u4fdd\u967a\u52a0\u5165\u4e2d</bold></blue>", "<gray>\u6709\u52b9\u671f\u9650:</gray> <green>\u6b8b\u308a " + remain / 60000L + "\u5206</green>", "<gray>\u6b7b\u4ea1\u6642\u306e\u88dc\u586b\u984d:</gray> <gold>" + (long)this.cfgInsurancePayout + "\u5186</gold>", "<gray>\u203b\u6b7b\u4ea1\u3059\u308b\u30681\u56de\u6d88\u8cbb\u3055\u308c\u307e\u3059</gray>"));
        } else {
            gui.setItem(13, this.createItem(Material.GOLDEN_APPLE, "<green><bold>\u4fdd\u967a\u306b\u52a0\u5165\u3059\u308b</bold></green>", "<gray>\u4fdd\u967a\u6599:</gray> <red>" + (long)this.cfgInsurancePremium + "\u5186</red>", "<gray>\u6709\u52b9\u671f\u9593:</gray> <yellow>" + this.cfgInsuranceDurationMs / 60000L + "\u5206</yellow>", "<gray>\u6b7b\u4ea1\u6642\u306e\u88dc\u586b\u984d:</gray> <gold>" + (long)this.cfgInsurancePayout + "\u5186</gold>"));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAchievementGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tAchievement);
        UUID u = p.getUniqueId();
        HashSet unlocked = this.unlockedAchievements.getOrDefault(u, new HashSet());
        String[][] defs = new String[][]{{"first_loan_repaid", "\u521d\u3081\u3066\u306e\u5b8c\u6e08", "\u30d7\u30ec\u30a4\u30e4\u30fc\u9593\u878d\u8cc7\u3092\u5b8c\u6e08\u3057\u305f"}, {"first_gov_loan_repaid", "\u516c\u5eab\u306e\u5e38\u9023", "\u56fd\u55b6\u30ed\u30fc\u30f3\u3092\u5b8c\u6e08\u3057\u305f"}, {"millionaire", "\u8cc7\u7523\u5bb6\u306e\u8a3c", "\u7dcf\u8cc7\u7523\u304c100\u4e07\u5186\u3092\u8d85\u3048\u305f"}, {"first_fixed_deposit", "\u5805\u5b9f\u306a\u6295\u8cc7\u5bb6", "\u5b9a\u671f\u9810\u91d1\u306e\u6e80\u671f\u3092\u8fce\u3048\u305f"}, {"credit_master", "\u4fe1\u7528\u306e\u9451", "\u4fe1\u7528\u30b9\u30b3\u30a2\u304c700\u306b\u5230\u9054\u3057\u305f"}};
        int slot = 0;
        for (String[] def : defs) {
            boolean has = unlocked.contains(def[0]);
            Material mat = has ? Material.NETHER_STAR : Material.GRAY_DYE;
            String nameColor = has ? "<gold><bold>\ud83c\udf96 " + def[1] + "</bold></gold>" : "<dark_gray>\ud83d\udd12 ???</dark_gray>";
            String desc = has ? "<gray>" + def[2] + "</gray>" : "<dark_gray>\u672a\u89e3\u653e</dark_gray>";
            gui.setItem(slot++, this.createItem(mat, nameColor, desc));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openPersonalGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tPersonal);
        UUID u = p.getUniqueId();
        double pocket = econ.getBalance((OfflinePlayer)p);
        double bank = this.personalBank.getOrDefault(u, 0.0);
        gui.setItem(4, this.createItem(Material.BOOK, "<yellow>\u3010\u73fe\u5728\u306e\u8ca1\u52d9\u72b6\u6cc1\u3011</yellow>", "<gray>\u624b\u6301\u3061: </gray><green>" + (long)pocket + "\u5186</green>", "<gray>\u9810\u91d1\u6b8b\u9ad8: </gray><aqua>" + (long)bank + "\u5186</aqua>"));
        gui.setItem(11, this.createItem(Material.LIME_DYE, "<green>+" + (long)this.cfgDepositStep + "\u5186 \u9810\u91d1</green>", new String[0]));
        gui.setItem(12, this.createItem(Material.LIME_GLAZED_TERRACOTTA, "<green><bold>\u5168\u984d\u9810\u91d1</bold></green>", new String[0]));
        gui.setItem(14, this.createItem(Material.RED_DYE, "<red>-" + (long)this.cfgDepositStep + "\u5186 \u5f15\u51fa</red>", new String[0]));
        gui.setItem(15, this.createItem(Material.RED_GLAZED_TERRACOTTA, "<red><bold>\u5168\u984d\u5f15\u51fa</bold></red>", new String[0]));
        gui.setItem(20, this.createItem(Material.WRITABLE_BOOK, "<gold><bold>\u91d1\u984d\u3092\u6307\u5b9a\u3057\u3066\u9810\u91d1</bold></gold>", "<gray>\u30af\u30ea\u30c3\u30af\u5f8c\u3001\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b</gray>"));
        gui.setItem(21, this.createItem(Material.PAPER, "<gold><bold>\u91d1\u984d\u3092\u6307\u5b9a\u3057\u3066\u5f15\u51fa</bold></gold>", "<gray>\u30af\u30ea\u30c3\u30af\u5f8c\u3001\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b</gray>"));
        gui.setItem(6, this.createItem(Material.WRITTEN_BOOK, "<aqua><bold>\ud83d\udcdc \u53d6\u5f15\u5c65\u6b74\u3092\u898b\u308b</bold></aqua>", "<gray>\u76f4\u8fd1\u306e\u5165\u51fa\u91d1\u30fb\u878d\u8cc7\u30fb\u6295\u8cc7\u5c65\u6b74\u3092\u8868\u793a</gray>"));
        boolean newsOff = this.newsBroadcastOff.contains(u);
        if (newsOff) {
            gui.setItem(7, this.createItem(Material.NOTE_BLOCK, "<gray><bold>\ud83d\udd15 \u7d4c\u6e08\u30cb\u30e5\u30fc\u30b9\u653e\u9001: OFF</bold></gray>", "<gray>\u30af\u30ea\u30c3\u30af\u3067ON\u306b\u5207\u308a\u66ff\u3048</gray>"));
        } else {
            gui.setItem(7, this.createItem(Material.BELL, "<green><bold>\ud83d\udd14 \u7d4c\u6e08\u30cb\u30e5\u30fc\u30b9\u653e\u9001: ON</bold></green>", "<gray>\u30af\u30ea\u30c3\u30af\u3067OFF\u306b\u5207\u308a\u66ff\u3048</gray>"));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openMarketGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tMarket);
        int slot = 0;
        for (Map.Entry<UUID, String> entry : this.publishedLoans.entrySet()) {
            if (slot >= 45) break;
            gui.setItem(slot++, this.buildLoanMarketItem(entry.getKey(), entry.getValue(), 1));
        }
        for (Map.Entry<UUID, String> entry : this.publishedLoans2.entrySet()) {
            if (slot >= 45) break;
            gui.setItem(slot++, this.buildLoanMarketItem(entry.getKey(), entry.getValue(), 2));
        }
        for (Map.Entry<UUID, String> entry : this.publishedLoans3.entrySet()) {
            if (slot >= 45) break;
            gui.setItem(slot++, this.buildLoanMarketItem(entry.getKey(), entry.getValue(), 3));
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private ItemStack buildLoanMarketItem(UUID lenderId, String planData, int slotNum) {
        String[] data = planData.split(":");
        double amount = Double.parseDouble(data[0]);
        double interest = Double.parseDouble(data[1]);
        ItemStack item = this.createItem(Material.PAPER, "<green><bold>\u878d\u8cc7\u30d7\u30e9\u30f3: " + Bukkit.getOfflinePlayer((UUID)lenderId).getName() + "\u9280\u884c (" + slotNum + "\u67a0\u76ee)</bold></green>", "<gray>\u878d\u8cc7\u984d:</gray> <aqua>" + (long)amount + "\u5186</aqua>", "<gray>\u5229\u606f:</gray> <red>" + (long)interest + "%</red>", "<gray>\u8fd4\u6e08\u7dcf\u984d:</gray> <gold>" + (long)(amount * (1.0 + interest / 100.0)) + "\u5186</gold>", "", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u5951\u7d04\u6210\u7acb</yellow>");
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(this.lenderKey, PersistentDataType.STRING, (Object)lenderId.toString());
            meta.getPersistentDataContainer().set(this.loanSlotKey, PersistentDataType.INTEGER, (Object)slotNum);
            item.setItemMeta(meta);
        }
        return item;
    }

    private void openRepayGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tRepay);
        UUID u = p.getUniqueId();
        if (!this.activeDebts.containsKey(u)) {
            gui.setItem(2, this.createItem(Material.SUNFLOWER, "<green>\u30d7\u30ec\u30a4\u30e4\u30fc\u9593\u306e\u501f\u91d1\u306a\u3057</green>", new String[0]));
        } else {
            String[] data = this.activeDebts.get(u).split(":");
            double pDebt = Double.parseDouble(data[1]);
            gui.setItem(2, this.createItem(Material.ANVIL, "<red>\u3010\u5bfe\u30d7\u30ec\u30a4\u30e4\u30fc\u50b5\u52d9\u3011</red>", "<gray>\u50b5\u6a29\u8005:</gray> " + Bukkit.getOfflinePlayer((UUID)UUID.fromString(data[0])).getName(), "<gray>\u6b8b\u308a:</gray> <red>" + (long)pDebt + "\u5186</red>"));
            gui.setItem(10, this.createItem(Material.GOLD_NUGGET, "<yellow>1,000\u5186 \u8fd4\u6e08</yellow>", new String[0]));
            gui.setItem(11, this.createItem(Material.GOLD_BLOCK, "<gold><bold>\u5168\u984d\u8fd4\u6e08</bold></gold>", new String[0]));
            UUID guarantorId = this.loanGuarantor.get(u);
            if (guarantorId != null) {
                gui.setItem(13, this.createItem(Material.SHIELD, "<aqua><bold>\u4fdd\u8a3c\u4eba\u8a2d\u5b9a\u6e08\u307f</bold></aqua>", "<gray>\u4fdd\u8a3c\u4eba:</gray> " + Bukkit.getOfflinePlayer((UUID)guarantorId).getName()));
            } else {
                gui.setItem(13, this.createItem(Material.IRON_HORSE_ARMOR, "<gray><bold>\u4fdd\u8a3c\u4eba\u306a\u3057</bold></gray>", "<gray>/meco guarantor request <\u30d7\u30ec\u30a4\u30e4\u30fc></gray>", "<gray>\u3067\u4fdd\u8a3c\u4eba\u3092\u4f9d\u983c\u3067\u304d\u307e\u3059</gray>"));
            }
        }
        double gDebt = this.govDebt.getOrDefault(u, 0.0);
        if (gDebt <= 0.0) {
            gui.setItem(6, this.createItem(Material.SUNFLOWER, "<green>\u56fd\u55b6\u516c\u5eab\u304b\u3089\u306e\u501f\u91d1\u306a\u3057</green>", new String[0]));
        } else {
            gui.setItem(6, this.createItem(Material.IRON_BARS, "<dark_red>\u3010\u56fd\u55b6\u516c\u5eab\u50b5\u52d9\u3011</dark_red>", "<gray>\u6b8b\u308a:</gray> <red>" + (long)gDebt + "\u5186</red>"));
            gui.setItem(15, this.createItem(Material.GOLD_NUGGET, "<yellow>1,000\u5186 \u8fd4\u6e08</yellow>", new String[0]));
            gui.setItem(16, this.createItem(Material.GOLD_BLOCK, "<gold><bold>\u5168\u984d\u8fd4\u6e08</bold></gold>", new String[0]));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openEstablishGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tEstablish);
        gui.setItem(13, this.createItem(Material.DIAMOND, "<light_purple><bold>\u3010\u9280\u884c\u3092\u8a2d\u7acb\u3059\u308b\u3011</bold></light_purple>", "<gray>\u8a2d\u7acb\u8cbb\u7528:</gray> <red>" + (long)this.cfgBankEstablishCost + "\u5186</red>"));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openBankerGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tBanker);
        UUID u = p.getUniqueId();
        double capital = this.bankCapital.getOrDefault(u, 0.0);
        gui.setItem(4, this.createItem(Material.BEACON, "<red>\u3010\u9280\u884c\u30b9\u30c6\u30fc\u30bf\u30b9\u3011</red>", "<gray>\u8cc7\u672c\u91d1\u30d7\u30fc\u30eb:</gray> <gold>" + (long)capital + "\u5186</gold>"));
        gui.setItem(10, this.createItem(Material.DIAMOND, "<aqua>\u8cc7\u672c\u91d1\u3078 +10,000\u5186</aqua>", new String[0]));
        gui.setItem(11, this.createItem(Material.COAL, "<gray>\u8cc7\u672c\u91d1\u304b\u3089 -10,000\u5186</gray>", new String[0]));
        if (this.publishedLoans.containsKey(u)) {
            gui.setItem(15, this.createItem(Material.BARRIER, "<red>\u30d7\u30e9\u30f3(1\u67a0\u76ee)\u3092\u53d6\u308a\u4e0b\u3052\u308b</red>", new String[0]));
        } else {
            gui.setItem(15, this.createItem(Material.WRITABLE_BOOK, "<gold><bold>\u878d\u8cc7\u30d7\u30e9\u30f3(1\u67a0\u76ee)\u3092\u8a2d\u8a08</bold></gold>", new String[0]));
        }
        if (this.publishedLoans2.containsKey(u)) {
            gui.setItem(16, this.createItem(Material.BARRIER, "<red>\u30d7\u30e9\u30f3(2\u67a0\u76ee)\u3092\u53d6\u308a\u4e0b\u3052\u308b</red>", new String[0]));
        } else {
            gui.setItem(16, this.createItem(Material.BOOK, "<aqua><bold>\u878d\u8cc7\u30d7\u30e9\u30f3(2\u67a0\u76ee)\u3092\u8a2d\u8a08</bold></aqua>", new String[0]));
        }
        if (this.publishedLoans3.containsKey(u)) {
            gui.setItem(17, this.createItem(Material.BARRIER, "<red>\u30d7\u30e9\u30f3(3\u67a0\u76ee)\u3092\u53d6\u308a\u4e0b\u3052\u308b</red>", new String[0]));
        } else {
            gui.setItem(17, this.createItem(Material.KNOWLEDGE_BOOK, "<light_purple><bold>\u878d\u8cc7\u30d7\u30e9\u30f3(3\u67a0\u76ee)\u3092\u8a2d\u8a08</bold></light_purple>", new String[0]));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openPlanGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tPlan);
        UUID u = p.getUniqueId();
        double amount = this.tempLoanAmount.getOrDefault(u, 1000.0);
        double interest = this.tempInterestRate.getOrDefault(u, 10.0);
        int slot = this.planDesignSlot.getOrDefault(u, 1);
        gui.setItem(4, this.createItem(Material.OAK_SIGN, "<gold>\u3010\u8a2d\u8a08\u4e2d: " + slot + "\u67a0\u76ee\u3011</gold>", "<gray>\u878d\u8cc7\u984d:</gray> <aqua>" + (long)amount + "\u5186</aqua>", "<gray>\u5229\u606f:</gray> <red>" + (long)interest + "%</red>"));
        gui.setItem(10, this.createItem(Material.SLIME_BALL, "<green>+1,000\u5186</green>", new String[0]));
        gui.setItem(11, this.createItem(Material.SLIME_BLOCK, "<green>+10,000\u5186</green>", new String[0]));
        gui.setItem(12, this.createItem(Material.REDSTONE, "<red>\u984d\u30ea\u30bb\u30c3\u30c8</red>", new String[0]));
        gui.setItem(14, this.createItem(Material.SUGAR, "<yellow>\u5229\u606f +5%</yellow>", new String[0]));
        gui.setItem(15, this.createItem(Material.GLOWSTONE_DUST, "<yellow>\u5229\u606f +10%</yellow>", new String[0]));
        gui.setItem(16, this.createItem(Material.REDSTONE, "<red>\u5229\u606f\u30ea\u30bb\u30c3\u30c8</red>", new String[0]));
        gui.setItem(18, this.createItem(Material.WRITABLE_BOOK, "<gold><bold>\u878d\u8cc7\u984d\u3092\u76f4\u63a5\u5165\u529b</bold></gold>", "<gray>\u30af\u30ea\u30c3\u30af\u5f8c\u3001\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b</gray>"));
        gui.setItem(19, this.createItem(Material.PAPER, "<gold><bold>\u5229\u606f(%)\u3092\u76f4\u63a5\u5165\u529b</bold></gold>", "<gray>\u30af\u30ea\u30c3\u30af\u5f8c\u3001\u30c1\u30e3\u30c3\u30c8\u306b\u6570\u5024\u3092\u5165\u529b</gray>"));
        gui.setItem(22, this.createItem(Material.NETHER_STAR, "<gold><bold>\u5e02\u5834\u306b\u516c\u958b\u3059\u308b</bold></gold>", new String[0]));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openGovLoanGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tGovLoan);
        UUID u = p.getUniqueId();
        int score = this.getScore(u);
        int interest = score >= 700 ? 5 : (score >= 500 ? 15 : 25);
        double cap = this.getGovLoanCap(score);
        double currentDebt = this.govDebt.getOrDefault(u, 0.0);
        ArrayList<CallSite> lores = new ArrayList<CallSite>();
        lores.add((CallSite)((Object)("<gray>\u9069\u7528\u91d1\u5229:</gray> <red>" + interest + "%</red>")));
        lores.add((CallSite)((Object)("<gray>\u501f\u5165\u4e0a\u9650(\u4fe1\u7528\u30b9\u30b3\u30a2\u9023\u52d5):</gray> <gold>" + (long)cap + "\u5186</gold>")));
        lores.add((CallSite)((Object)("<gray>\u73fe\u5728\u306e\u50b5\u52d9\u6b8b\u9ad8:</gray> <yellow>" + (long)currentDebt + "\u5186</yellow>")));
        if (this.govDebtDueTime.containsKey(u) && currentDebt > 0.0) {
            long remain = this.govDebtDueTime.get(u) - System.currentTimeMillis();
            lores.add((CallSite)((Object)("<gray>\u8fd4\u6e08\u671f\u9650:</gray> " + (String)(remain > 0L ? "<green>\u6b8b\u308a " + remain / 60000L + "\u5206</green>" : "<red>\u671f\u9650\u8d85\u904e\uff08\u5ef6\u6ede\u91d1\u52a0\u7b97\u4e2d\uff09</red>"))));
        }
        gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>+" + (long)this.cfgGovLoanAmount + "\u5186 \u56fd\u55b6\u30ed\u30fc\u30f3\u3092\u7d44\u3080</bold></green>", lores.toArray(new String[0])));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private double getGovLoanCap(int score) {
        if (score >= 700) {
            return 100000.0;
        }
        if (score >= 500) {
            return 50000.0;
        }
        return 20000.0;
    }

    private int getVipTier(UUID u) {
        int score = this.getScore(u);
        if (score >= this.cfgVipPlatinumScore) {
            return 3;
        }
        if (score >= this.cfgVipGoldScore) {
            return 2;
        }
        if (score >= this.cfgVipSilverScore) {
            return 1;
        }
        return 0;
    }

    private String vipTierName(int tier) {
        return switch (tier) {
            case 3 -> "\u30d7\u30e9\u30c1\u30ca";
            case 2 -> "\u30b4\u30fc\u30eb\u30c9";
            case 1 -> "\u30b7\u30eb\u30d0\u30fc";
            default -> "\u901a\u5e38";
        };
    }

    private double vipFeeMultiplier(UUID u) {
        return switch (this.getVipTier(u)) {
            case 3 -> 1.0 - this.cfgVipFeeDiscountPlatinum;
            case 2 -> 1.0 - this.cfgVipFeeDiscountGold;
            case 1 -> 1.0 - this.cfgVipFeeDiscountSilver;
            default -> 1.0;
        };
    }

    private double vipTradeLimitMultiplier(UUID u) {
        return switch (this.getVipTier(u)) {
            case 3 -> this.cfgVipTradeLimitMultPlatinum;
            case 2 -> this.cfgVipTradeLimitMultGold;
            case 1 -> this.cfgVipTradeLimitMultSilver;
            default -> 1.0;
        };
    }

    private double vipStipendAmount(int tier) {
        return switch (tier) {
            case 3 -> this.cfgVipStipendPlatinum;
            case 2 -> this.cfgVipStipendGold;
            case 1 -> this.cfgVipStipendSilver;
            default -> 0.0;
        };
    }

    private double getInstallmentCreditLimit(int score) {
        if (score < this.cfgInstallmentMinCreditScore) {
            return 0.0;
        }
        return (double)score * this.cfgInstallmentCreditLimitMultiplier;
    }

    private double getInstallmentOutstanding(UUID u) {
        List<InstallmentPlan> plans = this.installmentPlans.get(u);
        if (plans == null || plans.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (InstallmentPlan plan : plans) {
            total += plan.installmentAmount * (double)plan.installmentsRemaining;
        }
        return total;
    }

    private void openFixedDepoGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tFixedDepo);
        UUID u = p.getUniqueId();
        double amount = this.fixedDeposit.getOrDefault(u, 0.0);
        long diff = this.fixedDepositUnlockTime.getOrDefault(u, 0L) - System.currentTimeMillis();
        String timeStr = this.fixedDepositStatusText(amount, diff);
        gui.setItem(4, this.createItem(Material.CLOCK, "<gold>\u3010\u5b9a\u671f\u9810\u91d1 1\u67a0\u76ee\u3011</gold>", "<gray>\u73fe\u5728\u306e\u9810\u91d1\u984d:</gray> <yellow>" + (long)amount + "\u5186</yellow>", "<gray>\u72b6\u614b:</gray> " + timeStr));
        gui.setItem(11, this.createItem(Material.GOLD_INGOT, "<yellow>\u624b\u6301\u3061\u304b\u3089 " + (long)this.cfgFixedDepositAmount + "\u5186 \u9810\u3051\u308b</yellow>", new String[0]));
        gui.setItem(15, this.createItem(Material.GOLD_BLOCK, "<gold><bold>\u6e80\u671f\u5f15\u304d\u51fa\u3057 (+" + (int)(this.cfgFixedDepositRate * 100.0) + "%)</bold></gold>", new String[0]));
        double amount2 = this.fixedDeposit2.getOrDefault(u, 0.0);
        long diff2 = this.fixedDepositUnlockTime2.getOrDefault(u, 0L) - System.currentTimeMillis();
        String timeStr2 = this.fixedDepositStatusText(amount2, diff2);
        gui.setItem(3, this.createItem(Material.CLOCK, "<aqua>\u3010\u5b9a\u671f\u9810\u91d1 2\u67a0\u76ee\u3011</aqua>", "<gray>\u73fe\u5728\u306e\u9810\u91d1\u984d:</gray> <yellow>" + (long)amount2 + "\u5186</yellow>", "<gray>\u72b6\u614b:</gray> " + timeStr2));
        gui.setItem(12, this.createItem(Material.IRON_INGOT, "<yellow>2\u67a0\u76ee\u3078 " + (long)this.cfgFixedDepositAmount + "\u5186 \u9810\u3051\u308b</yellow>", new String[0]));
        gui.setItem(16, this.createItem(Material.IRON_BLOCK, "<aqua><bold>2\u67a0\u76ee \u6e80\u671f\u5f15\u304d\u51fa\u3057</bold></aqua>", new String[0]));
        double amount3 = this.fixedDeposit3.getOrDefault(u, 0.0);
        long diff3 = this.fixedDepositUnlockTime3.getOrDefault(u, 0L) - System.currentTimeMillis();
        String timeStr3 = this.fixedDepositStatusText(amount3, diff3);
        gui.setItem(5, this.createItem(Material.CLOCK, "<light_purple>\u3010\u5b9a\u671f\u9810\u91d1 3\u67a0\u76ee\u3011</light_purple>", "<gray>\u73fe\u5728\u306e\u9810\u91d1\u984d:</gray> <yellow>" + (long)amount3 + "\u5186</yellow>", "<gray>\u72b6\u614b:</gray> " + timeStr3));
        gui.setItem(13, this.createItem(Material.EMERALD, "<yellow>3\u67a0\u76ee\u3078 " + (long)this.cfgFixedDepositAmount + "\u5186 \u9810\u3051\u308b</yellow>", new String[0]));
        gui.setItem(17, this.createItem(Material.EMERALD_BLOCK, "<light_purple><bold>3\u67a0\u76ee \u6e80\u671f\u5f15\u304d\u51fa\u3057</bold></light_purple>", new String[0]));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openCreditGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tCredit);
        int score = this.getScore(p.getUniqueId());
        gui.setItem(13, this.createItem(Material.NAME_TAG, "<green><bold>\u3010\u4fe1\u7528\u60c5\u5831\u3011</bold></green>", "<gray>\u30b9\u30b3\u30a2:</gray> <aqua>" + score + " / 800</aqua>"));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openWorldStockGUI(Player p) {
        UUID u = p.getUniqueId();
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tWorldStock);
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<blue><bold>\u3010\u73fe\u5b9f\u4e16\u754c\u306e\u682a\u5f0f\u5e02\u5834\u3011</bold></blue>", "<gray>\u5b9f\u5728\u3059\u308b\u9298\u67c4\u306e\u30c6\u30a3\u30c3\u30ab\u30fc\u30b7\u30f3\u30dc\u30eb\u3092\u691c\u7d22\u3057\u3066\u58f2\u8cb7\u3067\u304d\u307e\u3059\u3002</gray>", "<gray>\u4fa1\u683c\u306f\u73fe\u5b9f\u306e\u682a\u4fa1\u3092\u3001\u30e9\u30a4\u30d6\u306e\u70ba\u66ff\u30ec\u30fc\u30c8\u3067\u5186\u63db\u7b97\u3057\u3066\u6271\u3044\u307e\u3059(1\u682a\u5358\u4f4d)\u3002</gray>", "<gray>\u4f8b: AAPL(Apple) TSLA(Tesla) 7203.T(\u30c8\u30e8\u30bf) ^GSPC(S&P500)</gray>", "<dark_gray>\u540c\u4e00\u9298\u67c4\u306e\u4fa1\u683c\u306f\u7d04" + (long)this.cfgWorldStockCacheSeconds + "\u79d2\u30ad\u30e3\u30c3\u30b7\u30e5\u3055\u308c\u307e\u3059(\u53d6\u5f15\u6240\u306b\u3088\u3063\u3066\u306f\u6570\u5206\u9045\u5ef6\u3042\u308a)\u3002</dark_gray>", "<gray>\u4fdd\u6709\u4e2d\u306e\u9298\u67c4\u306b\u306f\u5e74\u7387" + this.cfgWorldStockDividendAnnualRate * 100.0 + "%\u76f8\u5f53\u306e\u914d\u5f53\u91d1\u304c\u5b9a\u671f\u7684\u306b\u652f\u6255\u308f\u308c\u307e\u3059\u3002</gray>"));
        gui.setItem(49, this.createItem(Material.COMPASS, "<gold><bold>\ud83d\udd0d \u9298\u67c4\u3092\u691c\u7d22\u3057\u3066\u58f2\u8cb7</bold></gold>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c6\u30a3\u30c3\u30ab\u30fc\u30b7\u30f3\u30dc\u30eb\u3092\u30c1\u30e3\u30c3\u30c8\u5165\u529b</gray>"));
        gui.setItem(45, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>\ud83d\udcca \u4fdd\u6709\u682a\u30e9\u30f3\u30ad\u30f3\u30b0</bold></yellow>", "<gray>\u30b5\u30fc\u30d0\u30fc\u5185\u306e\u4fdd\u6709\u682a\u8a55\u4fa1\u984d\u30e9\u30f3\u30ad\u30f3\u30b0\u3092\u8868\u793a</gray>"));
        HashMap holdings = this.playerWorldStocks.getOrDefault(u, new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(u, new HashMap());
        int slot = 9;
        for (Map.Entry entry : holdings.entrySet()) {
            String symbol = (String)entry.getKey();
            int qty = (Integer)entry.getValue();
            if (qty <= 0 || slot >= 45) continue;
            double avgCost = avgCosts.getOrDefault(symbol, 0.0);
            WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
            ArrayList<Object> lore = new ArrayList<Object>();
            lore.add("<gray>\u4fdd\u6709: </gray><white>" + qty + "\u682a</white>");
            lore.add("<gray>\u5e73\u5747\u53d6\u5f97\u5358\u4fa1: </gray><white>" + String.format("%.2f", avgCost) + "\u5186</white>");
            if (q != null) {
                double value = this.worldStockYenPrice(q) * (double)qty;
                double pnl = value - avgCost * (double)qty;
                lore.add(this.worldStockPriceLine("<gray>\u73fe\u5728\u5024: </gray>", q));
                lore.add("<gray>\u8a55\u4fa1\u984d: </gray><gold>" + (long)value + "\u5186</gold>");
                lore.add(this.worldStockPnlLine(pnl));
            } else {
                lore.add("<gray>\u73fe\u5728\u5024: \u53d6\u5f97\u4e2d... \u30af\u30ea\u30c3\u30af\u3057\u3066\u66f4\u65b0</gray>");
            }
            lore.add("<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u58f2\u8cb7\u753b\u9762\u3078</yellow>");
            ItemStack item = this.createItem(Material.PAPER, "<aqua><bold>" + symbol + "</bold></aqua>", lore.toArray(new String[0]));
            this.setWorldStockTag(item, symbol);
            gui.setItem(slot++, item);
            this.fetchWorldStockQuote(symbol, quote -> {});
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openWorldStockDetailGUI(Player p, String symbol) {
        this.openWorldStockDetailGUICommon(p, symbol, null);
    }

    private void payWorldStockDividends() {
        double perRunRate = this.cfgWorldStockDividendAnnualRate * ((double)this.cfgWorldStockDividendIntervalHours / 8760.0);
        if (perRunRate <= 0.0) {
            return;
        }
        for (Map.Entry<UUID, HashMap<String, Integer>> entry : this.playerWorldStocks.entrySet()) {
            UUID owner = entry.getKey();
            HashMap<String, Integer> holdings = entry.getValue();
            if (holdings.isEmpty()) continue;
            HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(owner, new HashMap());
            double totalDividend = 0.0;
            for (Map.Entry<String, Integer> holding : holdings.entrySet()) {
                int qty = holding.getValue();
                if (qty <= 0) continue;
                WorldStockQuote q = this.worldStockQuoteCache.get(holding.getKey());
                double positionValue = this.worldStockPositionValue(q, avgCosts.getOrDefault(holding.getKey(), 0.0), qty);
                totalDividend += positionValue * perRunRate;
            }
            if (!(totalDividend > 0.0)) continue;
            InvestmentFund fund = this.investmentFunds.get(owner);
            if (fund != null) {
                fund.cashBalance += totalDividend;
                this.addLog(fund.manager, "\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c" + fund.name + "\u300d\u306e\u4e16\u754c\u682a\u914d\u5f53\u91d1 +" + (long)totalDividend + "\u5186(\u30d5\u30a1\u30f3\u30c9\u73fe\u91d1\u3078\u8a08\u4e0a)");
                continue;
            }
            econ.depositPlayer(Bukkit.getOfflinePlayer((UUID)owner), totalDividend);
            this.addLog(owner, "\u4e16\u754c\u682a\u306e\u914d\u5f53\u91d1 +" + this.fmtCur(totalDividend));
            Player online = Bukkit.getPlayer((UUID)owner);
            if (online == null) continue;
            this.msgKey(online, "worldstock.dividend-paid", "amount", String.format("%.2f", totalDividend));
        }
    }

    private void openWorldStockLeaderboardGUI(Player p) {
        int symbols;
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tWorldStockLeaderboard);
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<blue><bold>\u3010\u4fdd\u6709\u682a\u8a55\u4fa1\u984d\u30e9\u30f3\u30ad\u30f3\u30b0\u3011</bold></blue>", "<gray>\u4fdd\u6709\u4e2d\u306e\u9298\u67c4\u306e\u8a55\u4fa1\u984d\u5408\u8a08\u3067\u9806\u4f4d\u4ed8\u3051\u3057\u3066\u3044\u307e\u3059\u3002</gray>", "<dark_gray>\u4fa1\u683c\u30ad\u30e3\u30c3\u30b7\u30e5\u304c\u7121\u3044\u9298\u67c4\u306f\u5e73\u5747\u53d6\u5f97\u5358\u4fa1\u3067\u6982\u7b97\u3057\u307e\u3059\u3002</dark_gray>"));
        ArrayList<Map.Entry<UUID, Double>> ranking = new ArrayList<Map.Entry<UUID, Double>>();
        HashMap<UUID, Integer> symbolCounts = new HashMap<UUID, Integer>();
        for (Map.Entry<UUID, HashMap<String, Integer>> entry : this.playerWorldStocks.entrySet()) {
            UUID owner = entry.getKey();
            if (this.investmentFunds.containsKey(owner)) continue;
            HashMap hashMap = this.playerWorldStockAvgCost.getOrDefault(owner, new HashMap());
            double total = 0.0;
            symbols = 0;
            for (Map.Entry<String, Integer> holding : entry.getValue().entrySet()) {
                int qty = holding.getValue();
                if (qty <= 0) continue;
                ++symbols;
                WorldStockQuote q = this.worldStockQuoteCache.get(holding.getKey());
                total += this.worldStockPositionValue(q, hashMap.getOrDefault(holding.getKey(), 0.0), qty);
            }
            if (symbols <= 0) continue;
            ranking.add(Map.entry(owner, total));
            symbolCounts.put(owner, symbols);
        }
        ranking.sort((a, b) -> Double.compare((Double)b.getValue(), (Double)a.getValue()));
        int slot = 9;
        int rank = 1;
        for (Map.Entry entry : ranking) {
            if (slot >= 45 || rank > 28) break;
            UUID owner = (UUID)entry.getKey();
            String name = Bukkit.getOfflinePlayer((UUID)owner).getName();
            if (name == null) {
                name = "unknown";
            }
            symbols = symbolCounts.getOrDefault(owner, 0);
            gui.setItem(slot++, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>#" + rank + " " + name + "</bold></yellow>", "<gray>\u8a55\u4fa1\u984d\u5408\u8a08: </gray><gold>" + (long)((Double)entry.getValue()).doubleValue() + "\u5186</gold>", "<gray>\u4fdd\u6709\u9298\u67c4\u6570: </gray><white>" + symbols + "</white>"));
            ++rank;
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private String resourceDisplayName(Material mat) {
        return switch (mat) {
            case Material.COAL -> "\u77f3\u70ad";
            case Material.IRON_INGOT -> "\u9244\u30a4\u30f3\u30b4\u30c3\u30c8";
            case Material.GOLD_INGOT -> "\u91d1\u30a4\u30f3\u30b4\u30c3\u30c8";
            case Material.COPPER_INGOT -> "\u9285\u30a4\u30f3\u30b4\u30c3\u30c8";
            case Material.REDSTONE -> "\u30ec\u30c3\u30c9\u30b9\u30c8\u30fc\u30f3";
            case Material.LAPIS_LAZULI -> "\u30e9\u30d4\u30b9\u30e9\u30ba\u30ea";
            case Material.DIAMOND -> "\u30c0\u30a4\u30e4\u30e2\u30f3\u30c9";
            case Material.EMERALD -> "\u30a8\u30e1\u30e9\u30eb\u30c9";
            case Material.NETHERITE_SCRAP -> "\u30cd\u30b6\u30e9\u30a4\u30c8\u306e\u6b20\u7247";
            case Material.WHEAT -> "\u5c0f\u9ea6";
            case Material.CARROT -> "\u30cb\u30f3\u30b8\u30f3";
            case Material.POTATO -> "\u30b8\u30e3\u30ac\u30a4\u30e2";
            case Material.BEETROOT -> "\u30d3\u30fc\u30c8\u30eb\u30fc\u30c8";
            case Material.MELON_SLICE -> "\u30b9\u30a4\u30ab";
            case Material.PUMPKIN -> "\u30ab\u30dc\u30c1\u30e3";
            case Material.SUGAR_CANE -> "\u30b5\u30c8\u30a6\u30ad\u30d3";
            case Material.NETHER_WART -> "\u30cd\u30b6\u30fc\u30a6\u30a9\u30fc\u30c8";
            case Material.COCOA_BEANS -> "\u30ab\u30ab\u30aa\u8c46";
            case Material.ROTTEN_FLESH -> "\u8150\u3063\u305f\u8089";
            case Material.BONE -> "\u9aa8";
            case Material.STRING -> "\u7cf8";
            case Material.GUNPOWDER -> "\u706b\u85ac";
            case Material.SPIDER_EYE -> "\u30af\u30e2\u306e\u76ee";
            case Material.SLIME_BALL -> "\u30b9\u30e9\u30a4\u30e0\u30dc\u30fc\u30eb";
            case Material.ENDER_PEARL -> "\u30a8\u30f3\u30c0\u30fc\u30d1\u30fc\u30eb";
            case Material.BLAZE_ROD -> "\u30d6\u30ec\u30a4\u30ba\u30ed\u30c3\u30c9";
            case Material.GHAST_TEAR -> "\u30ac\u30b9\u30c8\u306e\u6d99";
            case Material.OAK_LOG -> "\u30aa\u30fc\u30af\u306e\u539f\u6728";
            case Material.SPRUCE_LOG -> "\u30c8\u30a6\u30d2\u306e\u539f\u6728";
            case Material.BIRCH_LOG -> "\u30b7\u30e9\u30ab\u30d0\u306e\u539f\u6728";
            case Material.JUNGLE_LOG -> "\u30b8\u30e3\u30f3\u30b0\u30eb\u306e\u539f\u6728";
            case Material.ACACIA_LOG -> "\u30a2\u30ab\u30b7\u30a2\u306e\u539f\u6728";
            case Material.DARK_OAK_LOG -> "\u30c0\u30fc\u30af\u30aa\u30fc\u30af\u306e\u539f\u6728";
            case Material.MANGROVE_LOG -> "\u30de\u30f3\u30b0\u30ed\u30fc\u30d6\u306e\u539f\u6728";
            case Material.CHERRY_LOG -> "\u30b5\u30af\u30e9\u306e\u539f\u6728";
            default -> mat.name();
        };
    }

    private String resourceTrendIndicator(Material mat) {
        double base = RESOURCE_BASE_PRICES.getOrDefault(mat, 0.0);
        if (base <= 0.0) {
            return "";
        }
        double price = this.getResourcePrice(mat);
        double ratio = price / base;
        if (ratio > 1.001) {
            return "<green>\u25b2</green>";
        }
        if (ratio < 0.999) {
            return "<red>\u25bc</red>";
        }
        return "<gray>=</gray>";
    }

    private void setResourceMaterialTag(ItemStack item, Material mat) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        meta.getPersistentDataContainer().set(this.resourceMaterialKey, PersistentDataType.STRING, (Object)mat.name());
        item.setItemMeta(meta);
    }

    private void setResourceActionTag(ItemStack item, String action) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        meta.getPersistentDataContainer().set(this.resourceActionKey, PersistentDataType.STRING, (Object)action);
        item.setItemMeta(meta);
    }

    private void openResourceShopGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tResourceShop);
        gui.setItem(4, this.createItem(Material.EMERALD, "<green><bold>\u3010\u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u3011</bold></green>", "<gray>\u9271\u77f3\u30fb\u8fb2\u4f5c\u7269\u30fb\u30e2\u30d6\u30c9\u30ed\u30c3\u30d7\u30fb\u6728\u6750\u3092\u30b5\u30fc\u30d0\u30fc\u306b\u5373\u6642\u58f2\u8cb7\u3067\u304d\u307e\u3059\u3002</gray>", "<gray>\u4fa1\u683c\u306f\u53d6\u5f15\u91cf\u306b\u5fdc\u3058\u3066\u4e0a\u4e0b\u3057\u3001\u6642\u9593\u7d4c\u904e\u3067\u57fa\u6e96\u4fa1\u683c\u3078\u7de9\u3084\u304b\u306b\u623b\u308a\u307e\u3059\u3002</gray>", "<dark_gray>\u58f2\u308a\u3059\u304e\u308b\u3068\u4fa1\u683c\u304c\u4e0b\u304c\u308b\u305f\u3081\u3001\u58f2\u5374\u76ca\u306f\u81ea\u7136\u3068\u982d\u6253\u3061\u306b\u306a\u308a\u307e\u3059\u3002</dark_gray>"));
        gui.setItem(10, this.createItem(Material.IRON_PICKAXE, "<white><bold>\u26cf \u9271\u77f3</bold></white>", "<gray>\u77f3\u70ad\u30fb\u9244\u30fb\u91d1\u30fb\u30c0\u30a4\u30e4\u30e2\u30f3\u30c9\u306a\u3069</gray>"));
        gui.setItem(12, this.createItem(Material.WHEAT, "<yellow><bold>\ud83c\udf3e \u8fb2\u4f5c\u7269</bold></yellow>", "<gray>\u5c0f\u9ea6\u30fb\u30cb\u30f3\u30b8\u30f3\u30fb\u30b8\u30e3\u30ac\u30a4\u30e2\u306a\u3069</gray>"));
        gui.setItem(14, this.createItem(Material.BONE, "<white><bold>\ud83e\uddb4 \u30e2\u30d6\u30c9\u30ed\u30c3\u30d7</bold></white>", "<gray>\u9aa8\u30fb\u7cf8\u30fb\u30a8\u30f3\u30c0\u30fc\u30d1\u30fc\u30eb\u306a\u3069</gray>"));
        gui.setItem(16, this.createItem(Material.OAK_LOG, "<gold><bold>\ud83e\udeb5 \u6728\u6750</bold></gold>", "<gray>\u5404\u7a2e\u539f\u6728</gray>"));
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openResourceShopListGUI(Player p, String category) {
        UUID u = p.getUniqueId();
        this.resourceShopViewCategory.put(u, category);
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tResourceShopList);
        String categoryName = switch (category) {
            case "ore" -> "\u9271\u77f3";
            case "crop" -> "\u8fb2\u4f5c\u7269";
            case "drop" -> "\u30e2\u30d6\u30c9\u30ed\u30c3\u30d7";
            case "wood" -> "\u6728\u6750";
            default -> category;
        };
        gui.setItem(4, this.createItem(Material.PAPER, "<green><bold>\u3010" + categoryName + "\u3011</bold></green>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u5404\u54c1\u76ee\u306e\u58f2\u8cb7\u753b\u9762\u3092\u958b\u304d\u307e\u3059\u3002</gray>"));
        List mats = RESOURCE_CATEGORIES.getOrDefault(category, List.of());
        int slot = 9;
        for (Material mat : mats) {
            if (slot >= 18) break;
            double base = RESOURCE_BASE_PRICES.getOrDefault(mat, 0.0);
            double price = this.getResourceEffectivePrice(mat);
            ItemStack item = this.createItem(mat, "<aqua><bold>" + this.resourceDisplayName(mat) + "</bold></aqua>", "<gray>\u73fe\u5728\u4fa1\u683c: </gray><gold>" + String.format("%.2f", price) + "\u5186/\u500b</gold> " + this.resourceTrendIndicator(mat), "<gray>\u57fa\u6e96\u4fa1\u683c: </gray><white>" + String.format("%.2f", base) + "\u5186/\u500b</white>", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u58f2\u8cb7\u753b\u9762\u3078</yellow>");
            this.setResourceMaterialTag(item, mat);
            gui.setItem(slot++, item);
        }
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openResourceShopDetailGUI(Player p, Material mat) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tResourceShopDetail);
        double base = RESOURCE_BASE_PRICES.getOrDefault(mat, 0.0);
        double price = this.getResourceEffectivePrice(mat);
        double personalMult = this.getResourcePersonalMultiplier(p.getUniqueId(), mat);
        double buyPrice = price * personalMult;
        int holding = this.countMaterialInInventory(p, mat);
        ArrayList<CallSite> infoLore = new ArrayList<CallSite>(List.of("<gray>\u73fe\u5728\u4fa1\u683c: </gray><gold>" + String.format("%.2f", price) + "\u5186/\u500b</gold> " + this.resourceTrendIndicator(mat), "<gray>\u57fa\u6e96\u4fa1\u683c: </gray><white>" + String.format("%.2f", base) + "\u5186/\u500b</white>", "<gray>\u6240\u6301\u6570: </gray><white>" + holding + "\u500b</white>"));
        if (personalMult > 1.01) {
            infoLore.add((CallSite)((Object)("<red>\u3042\u306a\u305f\u306f\u9023\u7d9a\u8cfc\u5165\u4e2d\u306e\u305f\u3081\u3001\u8cb7\u5024\u304c\u5272\u5897(\u00d7" + String.format("%.2f", personalMult) + ")\u306b\u306a\u3063\u3066\u3044\u307e\u3059</red>")));
        }
        ItemStack infoItem = this.createItem(mat, "<aqua><bold>" + this.resourceDisplayName(mat) + "</bold></aqua>", infoLore.toArray(new String[0]));
        gui.setItem(4, infoItem);
        int[] qtys = new int[]{1, 16, 64};
        int[] sellSlots = new int[]{10, 11, 12};
        int[] buySlots = new int[]{14, 15, 16};
        for (int i = 0; i < qtys.length; ++i) {
            int qty = qtys[i];
            double sellTotal = price * (double)qty;
            ItemStack sellItem = this.createItem(mat, "<red><bold>\u58f2\u308b x" + qty + "</bold></red>", "<gray>\u53d7\u53d6\u984d: </gray><gold>" + String.format("%.2f", sellTotal) + "\u5186</gold>", "<dark_gray>(\u5358\u4fa1 " + String.format("%.2f", price) + "\u5186 \u00d7 " + qty + "\u500b)</dark_gray>");
            this.setResourceMaterialTag(sellItem, mat);
            this.setResourceActionTag(sellItem, "sell" + qty);
            gui.setItem(sellSlots[i], sellItem);
            double buyTotal = buyPrice * (double)qty;
            ItemStack buyItem = this.createItem(mat, "<green><bold>\u8cb7\u3046 x" + qty + "</bold></green>", "<gray>\u652f\u6255\u984d: </gray><gold>" + String.format("%.2f", buyTotal) + "\u5186</gold>", "<dark_gray>(\u5358\u4fa1 " + String.format("%.2f", buyPrice) + "\u5186 \u00d7 " + qty + "\u500b)</dark_gray>");
            this.setResourceMaterialTag(buyItem, mat);
            this.setResourceActionTag(buyItem, "buy" + qty);
            gui.setItem(buySlots[i], buyItem);
        }
        ItemStack sellQtyItem = this.createItem(Material.WRITABLE_BOOK, "<red><bold>\ud83d\udcd6 \u6570\u91cf\u6307\u5b9a\u3067\u58f2\u308b</bold></red>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u500b\u6570\u3092\u5165\u529b</gray>");
        this.setResourceMaterialTag(sellQtyItem, mat);
        this.setResourceActionTag(sellQtyItem, "sellqty");
        gui.setItem(20, sellQtyItem);
        ItemStack buyQtyItem = this.createItem(Material.WRITABLE_BOOK, "<green><bold>\ud83d\udcd6 \u6570\u91cf\u6307\u5b9a\u3067\u8cb7\u3046</bold></green>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u500b\u6570\u3092\u5165\u529b</gray>");
        this.setResourceMaterialTag(buyQtyItem, mat);
        this.setResourceActionTag(buyQtyItem, "buyqty");
        gui.setItem(24, buyQtyItem);
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openLotteryGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tLottery);
        UUID u = p.getUniqueId();
        int totalTickets = this.lotteryTickets.values().stream().mapToInt(Integer::intValue).sum();
        int myTickets = this.lotteryTickets.getOrDefault(u, 0);
        double winChance = totalTickets == 0 ? 0.0 : (double)myTickets * 100.0 / (double)totalTickets;
        long remainMs = Math.max(0L, this.lotteryDrawAt - System.currentTimeMillis());
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<gold><bold>\u3010\u5b9a\u671f\u62bd\u9078\u30fb\u5b9d\u304f\u3058\u3011</bold></gold>", "<gray>\u8cde\u91d1\u30d7\u30fc\u30eb:</gray> <gold>" + (long)this.lotteryPool + "\u5186</gold>", "<gray>\u30c1\u30b1\u30c3\u30c8\u4fa1\u683c:</gray> <white>" + (long)this.cfgLotteryTicketPrice + "\u5186/\u679a</white>", "<gray>\u3042\u306a\u305f\u306e\u8cfc\u5165\u679a\u6570:</gray> <yellow>" + myTickets + "\u679a</yellow>", "<gray>\u73fe\u5728\u306e\u5f53\u305b\u3093\u78ba\u7387:</gray> <aqua>" + String.format("%.2f", winChance) + "%</aqua>", "<gray>\u6b21\u56de\u62bd\u9078\u307e\u3067:</gray> <white>" + this.formatHoursMinutes(remainMs, false) + "</white>", "<dark_gray>\u524d\u56de\u306e\u5f53\u305b\u3093\u8005:</dark_gray> <light_purple>" + this.lastLotteryWinnerName + "</light_purple>" + (String)(this.lastLotteryWinnerAmount > 0.0 ? " <dark_gray>(" + (long)this.lastLotteryWinnerAmount + "\u5186)</dark_gray>" : ""), "<dark_gray>\u8cde\u91d1\u30d7\u30fc\u30eb\u306f\u30c1\u30b1\u30c3\u30c8\u58f2\u4e0a\u306e\u307f\u304c\u539f\u8cc7\u3067\u3059(\u56fd\u5eab\u304b\u3089\u306e\u88dc\u586b\u306f\u3042\u308a\u307e\u305b\u3093)\u3002</dark_gray>"));
        double price = this.cfgLotteryTicketPrice;
        gui.setItem(11, this.createItem(Material.PAPER, "<green><bold>\ud83c\udf9f 1\u679a\u8cfc\u5165</bold></green>", "<gray>\u652f\u6255\u984d:</gray> <gold>" + (long)(price * 1.0) + "\u5186</gold>"));
        gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>\ud83c\udf9f 5\u679a\u8cfc\u5165</bold></green>", "<gray>\u652f\u6255\u984d:</gray> <gold>" + (long)(price * 5.0) + "\u5186</gold>"));
        gui.setItem(15, this.createItem(Material.DIAMOND, "<green><bold>\ud83c\udf9f 10\u679a\u8cfc\u5165</bold></green>", "<gray>\u652f\u6255\u984d:</gray> <gold>" + (long)(price * 10.0) + "\u5186</gold>"));
        gui.setItem(20, this.createItem(Material.WRITABLE_BOOK, "<aqua><bold>\ud83d\udcd6 \u6570\u91cf\u3092\u6307\u5b9a\u3057\u3066\u8cfc\u5165</bold></aqua>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u679a\u6570\u3092\u5165\u529b</gray>"));
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openStorageRentGUI(Player p) {
        UUID u = p.getUniqueId();
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tStorageRent);
        int clampedSize = Math.max(9, this.cfgStorageSize / 9 * 9);
        if (!this.storageRentDueTime.containsKey(u)) {
            gui.setItem(4, this.createItem(Material.BOOK, "<gold><bold>\u3010\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u3011</bold></gold>", "<gray>\u5bb6\u8cc3:</gray> <gold>" + (long)this.cfgStorageRentAmount + "\u5186</gold> / " + this.cfgStorageRentIntervalHours + "\u6642\u9593\u3054\u3068", "<gray>\u53ce\u7d0d\u30b9\u30ed\u30c3\u30c8\u6570:</gray> <white>" + clampedSize + "</white>", "<gray>\u500b\u4eba\u53e3\u5ea7\u30fb\u30a8\u30f3\u30c0\u30fc\u30c1\u30a7\u30b9\u30c8\u3068\u306f\u5225\u306e\u5c02\u7528\u53ce\u7d0d\u304c\u4f7f\u3048\u307e\u3059\u3002</gray>", "<red>\u26a0 \u5bb6\u8cc3\u306e\u652f\u6255\u3044\u30921\u56de\u3067\u3082\u6ede\u7d0d\u3059\u308b\u3068\u5951\u7d04\u306f\u5931\u52b9\u3057\u3001</red>", "<red>\u26a0 \u5009\u5eab\u306e\u4e2d\u8eab\u306f\u5168\u3066\u6ca1\u53ce\u3055\u308c\u307e\u3059\u306e\u3067\u3054\u6ce8\u610f\u304f\u3060\u3055\u3044\u3002</red>"));
            gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>\u501f\u308a\u308b</bold></green>", "<gray>\u4eca\u3059\u3050\u521d\u56de\u5bb6\u8cc3 " + (long)this.cfgStorageRentAmount + "\u5186 \u3092\u652f\u6255\u3063\u3066\u5951\u7d04\u3057\u307e\u3059\u3002</gray>"));
        } else {
            long due = this.storageRentDueTime.get(u);
            long remainMs = Math.max(0L, due - System.currentTimeMillis());
            gui.setItem(4, this.createItem(Material.BOOK, "<gold><bold>\u3010\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u3011\u5951\u7d04\u4e2d</bold></gold>", "<gray>\u5bb6\u8cc3:</gray> <gold>" + (long)this.cfgStorageRentAmount + "\u5186</gold> / " + this.cfgStorageRentIntervalHours + "\u6642\u9593\u3054\u3068", "<gray>\u6b21\u56de\u5bb6\u8cc3\u306e\u652f\u6255\u3044\u307e\u3067:</gray> <white>" + this.formatHoursMinutes(remainMs, false) + "</white>", "<red>\u26a0 \u652f\u6255\u3044\u306b\u5931\u6557\u3059\u308b\u3068\u5009\u5eab\u306e\u4e2d\u8eab\u306f\u5168\u3066\u6ca1\u53ce\u3055\u308c\u307e\u3059\u3002</red>"));
            gui.setItem(11, this.createItem(Material.CHEST, "<aqua><bold>\u5009\u5eab\u3092\u958b\u304f</bold></aqua>", "<gray>\u5c02\u7528\u306e\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u3092\u958b\u304d\u307e\u3059\u3002</gray>"));
            gui.setItem(15, this.createItem(Material.DIAMOND, "<light_purple><bold>\u65e9\u3081\u306b\u66f4\u65b0\u3059\u308b(\u6b21\u56de\u671f\u65e5\u3092\u5ef6\u9577)</bold></light_purple>", "<gray>" + (long)this.cfgStorageRentAmount + "\u5186\u3092\u652f\u6255\u3044\u3001\u6b21\u56de\u671f\u65e5\u3092\u305d\u3053\u304b\u3089" + this.cfgStorageRentIntervalHours + "\u6642\u9593\u5ef6\u9577\u3057\u307e\u3059\u3002</gray>"));
        }
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openPlayerStorage(Player p) {
        UUID u = p.getUniqueId();
        if (!this.storageRentDueTime.containsKey(u)) {
            this.msgKey(p, "storage.not-renting", new String[0]);
            this.errorSound(p);
            return;
        }
        Inventory inv = this.storageInventories.get(u);
        if (inv == null) {
            int clampedSize = Math.max(9, this.cfgStorageSize / 9 * 9);
            inv = Bukkit.createInventory(null, (int)clampedSize, (Component)this.tStorageLocker);
            Map<Integer, ItemStack> pending = this.pendingStorageContents.remove(u);
            if (pending != null) {
                for (Map.Entry<Integer, ItemStack> entry : pending.entrySet()) {
                    if (entry.getKey() < 0 || entry.getKey() >= clampedSize) continue;
                    inv.setItem(entry.getKey().intValue(), entry.getValue());
                }
            }
            this.storageInventories.put(u, inv);
        }
        p.openInventory(inv);
    }

    private void persistPlayerStorage(UUID u, Inventory inv) {
        this.db.removeByPathPrefix("storage_items", u.toString());
        for (int i = 0; i < inv.getSize(); ++i) {
            ItemStack item = inv.getItem(i);
            if (item == null || item.getType() == Material.AIR) continue;
            this.db.setItemStack("storage_items", String.valueOf(u) + "." + i, item);
        }
    }

    private boolean payOrStartStorageRent(OfflinePlayer p) {
        Player pl;
        Player online;
        UUID u = p.getUniqueId();
        Player player = online = p instanceof Player && (pl = (Player)p).isOnline() ? pl : null;
        if (econ.getBalance(p) < this.cfgStorageRentAmount) {
            if (online != null) {
                this.msgKey(online, "storage.funds-insufficient", new String[0]);
            }
            return false;
        }
        econ.withdrawPlayer(p, this.cfgStorageRentAmount);
        if (!this.storageRentDueTime.containsKey(u)) {
            this.storageRentDueTime.put(u, System.currentTimeMillis() + (long)this.cfgStorageRentIntervalHours * 3600000L);
            if (online != null) {
                this.msgKey(online, "storage.rented", "amount", String.valueOf((long)this.cfgStorageRentAmount), "hours", String.valueOf(this.cfgStorageRentIntervalHours));
            }
            this.addLog(u, "\u30ec\u30f3\u30bf\u30eb\u5009\u5eab: \u5951\u7d04\u958b\u59cb -" + this.fmtCur(this.cfgStorageRentAmount));
        } else {
            long current = this.storageRentDueTime.get(u);
            this.storageRentDueTime.put(u, current + (long)this.cfgStorageRentIntervalHours * 3600000L);
            if (online != null) {
                this.msgKey(online, "storage.rent-paid", "amount", String.valueOf((long)this.cfgStorageRentAmount));
            }
            this.addLog(u, "\u30ec\u30f3\u30bf\u30eb\u5009\u5eab: \u65e9\u671f\u66f4\u65b0 -" + this.fmtCur(this.cfgStorageRentAmount));
        }
        return true;
    }

    private void checkStorageRent() {
        long now = System.currentTimeMillis();
        for (Map.Entry<UUID, Long> entry : new HashMap<UUID, Long>(this.storageRentDueTime).entrySet()) {
            UUID u = entry.getKey();
            long due = entry.getValue();
            if (now < due) continue;
            OfflinePlayer offline = Bukkit.getOfflinePlayer((UUID)u);
            Player online = Bukkit.getPlayer((UUID)u);
            if (econ.getBalance(offline) >= this.cfgStorageRentAmount) {
                econ.withdrawPlayer(offline, this.cfgStorageRentAmount);
                this.storageRentDueTime.put(u, now + (long)this.cfgStorageRentIntervalHours * 3600000L);
                this.addLog(u, "\u30ec\u30f3\u30bf\u30eb\u5009\u5eab: \u5bb6\u8cc3\u81ea\u52d5\u5f15\u843d -" + this.fmtCur(this.cfgStorageRentAmount));
                if (online == null || !online.isOnline()) continue;
                this.msgKey(online, "storage.rent-paid", "amount", String.valueOf((long)this.cfgStorageRentAmount));
                continue;
            }
            this.storageRentDueTime.remove(u);
            this.db.removeByPathPrefix("storage_items", u.toString());
            this.pendingStorageContents.remove(u);
            Inventory cached = this.storageInventories.get(u);
            if (cached != null) {
                cached.clear();
            }
            this.addLog(u, "\u30ec\u30f3\u30bf\u30eb\u5009\u5eab: \u5bb6\u8cc3\u6ede\u7d0d\u306b\u3088\u308a\u5951\u7d04\u5931\u52b9\u30fb\u4e2d\u8eab\u6ca1\u53ce");
            if (online == null || !online.isOnline()) continue;
            this.msgKey(online, "storage.forfeited", new String[0]);
        }
    }

    private String merchantDisplayName(Material mat) {
        return switch (mat) {
            case Material.ELYTRA -> "\u30a8\u30ea\u30c8\u30e9";
            case Material.TOTEM_OF_UNDYING -> "\u4e0d\u6b7b\u306e\u30c8\u30fc\u30c6\u30e0";
            case Material.NETHERITE_INGOT -> "\u30cd\u30b6\u30e9\u30a4\u30c8\u30a4\u30f3\u30b4\u30c3\u30c8";
            case Material.NETHERITE_BLOCK -> "\u30cd\u30b6\u30e9\u30a4\u30c8\u30d6\u30ed\u30c3\u30af";
            case Material.ENCHANTED_GOLDEN_APPLE -> "\u30a8\u30f3\u30c1\u30e3\u30f3\u30c8\u3055\u308c\u305f\u91d1\u306e\u30ea\u30f3\u30b4";
            case Material.SADDLE -> "\u30b5\u30c9\u30eb";
            case Material.NAME_TAG -> "\u540d\u672d";
            case Material.SHULKER_BOX -> "\u30b7\u30e5\u30eb\u30ab\u30fc\u30dc\u30c3\u30af\u30b9";
            case Material.TRIDENT -> "\u30c8\u30e9\u30a4\u30c7\u30f3\u30c8";
            case Material.NETHER_STAR -> "\u30cd\u30b6\u30fc\u30b9\u30bf\u30fc";
            case Material.DRAGON_EGG -> "\u30c9\u30e9\u30b4\u30f3\u306e\u5375";
            case Material.BEACON -> "\u30d3\u30fc\u30b3\u30f3";
            case Material.DIAMOND_BLOCK -> "\u30c0\u30a4\u30e4\u30e2\u30f3\u30c9\u30d6\u30ed\u30c3\u30af";
            case Material.EMERALD_BLOCK -> "\u30a8\u30e1\u30e9\u30eb\u30c9\u30d6\u30ed\u30c3\u30af";
            case Material.MUSIC_DISC_PIGSTEP -> "\u30ec\u30b3\u30fc\u30c9(Pigstep)";
            case Material.MUSIC_DISC_OTHERSIDE -> "\u30ec\u30b3\u30fc\u30c9(Otherside)";
            case Material.HEART_OF_THE_SEA -> "\u6d77\u306e\u5fc3";
            case Material.CONDUIT -> "\u30b3\u30f3\u30b8\u30c3\u30c8";
            case Material.END_CRYSTAL -> "\u30a8\u30f3\u30c9\u30af\u30ea\u30b9\u30bf\u30eb";
            case Material.GOLDEN_CARROT -> "\u91d1\u306e\u30cb\u30f3\u30b8\u30f3";
            default -> mat.name();
        };
    }

    private void setMerchantDealTag(ItemStack item, UUID dealId) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        meta.getPersistentDataContainer().set(this.merchantDealKey, PersistentDataType.STRING, (Object)dealId.toString());
        item.setItemMeta(meta);
    }

    private void setVipDealTag(ItemStack item, UUID dealId) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        meta.getPersistentDataContainer().set(this.vipDealKey, PersistentDataType.STRING, (Object)dealId.toString());
        item.setItemMeta(meta);
    }

    private void ensureVipShopCurrent() {
        if (!this.cfgVipShopEnabled) {
            return;
        }
        if (this.vipShopDeals.isEmpty() || System.currentTimeMillis() >= this.vipShopRefreshAt) {
            this.rerollVipShop();
        }
    }

    private <T> void rerollDealsInto(Map<Material, Double> pool, List<T> target, int dealCountCfg, double minDiscountPercentCfg, double maxDiscountPercentCfg, int minStockCfg, int maxStockCfg, DealFactory<T> factory) {
        target.clear();
        ArrayList<Material> shuffled = new ArrayList<Material>(pool.keySet());
        Collections.shuffle(shuffled);
        int count = Math.min(Math.max(1, dealCountCfg), shuffled.size());
        Random rnd = new Random();
        for (int i = 0; i < count; ++i) {
            Material mat = (Material)shuffled.get(i);
            double normalPrice = pool.get(mat);
            double minD = Math.min(minDiscountPercentCfg, maxDiscountPercentCfg);
            double maxD = Math.max(minDiscountPercentCfg, maxDiscountPercentCfg);
            double discountPercent = minD + rnd.nextDouble() * (maxD - minD);
            int minS = Math.min(minStockCfg, maxStockCfg);
            int maxS = Math.max(minStockCfg, maxStockCfg);
            int stock = minS + (maxS > minS ? rnd.nextInt(maxS - minS + 1) : 0);
            target.add(factory.create(UUID.randomUUID(), mat, normalPrice, discountPercent, stock));
        }
    }

    private void rerollVipShop() {
        this.rerollDealsInto(VIP_SHOP_POOL, this.vipShopDeals, this.cfgVipShopDealCount, this.cfgVipShopMinDiscountPercent, this.cfgVipShopMaxDiscountPercent, this.cfgVipShopMinStock, this.cfgVipShopMaxStock, (id, mat, normalPrice, discountPercent, stock) -> {
            VipDeal deal = new VipDeal();
            deal.id = id;
            deal.material = mat;
            deal.normalPrice = normalPrice;
            deal.discountPercent = discountPercent;
            deal.stockTotal = stock;
            deal.stockRemaining = stock;
            return deal;
        });
        this.vipShopRefreshAt = System.currentTimeMillis() + (long)Math.max(1, this.cfgVipShopRefreshHours) * 60L * 60L * 1000L;
    }

    private void ensureMerchantDealsCurrent() {
        if (this.merchantDeals.isEmpty() || System.currentTimeMillis() >= this.merchantRefreshAt) {
            this.rerollMerchantDeals();
        }
        this.ensureMerchantEntityPresent();
    }

    private void rerollMerchantDeals() {
        this.rerollDealsInto(MERCHANT_ITEM_POOL, this.merchantDeals, this.cfgMerchantDealCount, this.cfgMerchantMinDiscountPercent, this.cfgMerchantMaxDiscountPercent, this.cfgMerchantMinStock, this.cfgMerchantMaxStock, (id, mat, normalPrice, discountPercent, stock) -> {
            MerchantDeal deal = new MerchantDeal();
            deal.id = id;
            deal.material = mat;
            deal.normalPrice = normalPrice;
            deal.discountPercent = discountPercent;
            deal.stockTotal = stock;
            deal.stockRemaining = stock;
            return deal;
        });
        this.merchantRefreshAt = System.currentTimeMillis() + (long)Math.max(1, this.cfgMerchantRefreshHours) * 60L * 60L * 1000L;
        this.relocateMerchant();
    }

    private void spawnMerchantEntityAt(Location loc) {
        World world = loc.getWorld();
        if (world == null) {
            return;
        }
        WanderingTrader trader = (WanderingTrader)world.spawn(loc, WanderingTrader.class, entity -> {
            entity.setAI(false);
            entity.setInvulnerable(true);
            entity.setPersistent(true);
            entity.setRemoveWhenFarAway(false);
            entity.setSilent(true);
            entity.customName(this.mm("<gold><bold>\u65c5\u306e\u884c\u5546\u4eba</bold></gold>"));
            entity.setCustomNameVisible(true);
            entity.setRecipes(new ArrayList());
            entity.getPersistentDataContainer().set(this.merchantNpcMarkerKey, PersistentDataType.BYTE, (Object)1);
        });
        this.merchantEntityId = trader.getUniqueId();
    }

    private void relocateMerchant() {
        World world = (World)Bukkit.getWorlds().get(0);
        Location spawn = world.getSpawnLocation();
        double angle = Math.random() * Math.PI * 2.0;
        double dist = Math.random() * this.cfgMerchantSpawnRadius;
        double x = spawn.getX() + Math.cos(angle) * dist;
        double z = spawn.getZ() + Math.sin(angle) * dist;
        double y = world.getHighestBlockYAt((int)x, (int)z) + 1;
        this.merchantWorldName = world.getName();
        this.merchantX = x;
        this.merchantY = y;
        this.merchantZ = z;
        if (this.merchantEntityId != null) {
            Entity old = Bukkit.getEntity((UUID)this.merchantEntityId);
            if (old != null) {
                old.remove();
            }
            this.merchantEntityId = null;
        }
        this.spawnMerchantEntityAt(new Location(world, x, y, z));
    }

    private void ensureMerchantEntityPresent() {
        if (this.merchantEntityId != null && Bukkit.getEntity((UUID)this.merchantEntityId) != null) {
            return;
        }
        if (this.merchantWorldName == null || this.merchantWorldName.isBlank()) {
            return;
        }
        World world = Bukkit.getWorld((String)this.merchantWorldName);
        if (world == null) {
            return;
        }
        this.spawnMerchantEntityAt(new Location(world, this.merchantX, this.merchantY, this.merchantZ));
    }

    private void checkTreasureSpawn() {
        if (this.treasureActive) {
            BlockState state;
            Block block;
            World world = Bukkit.getWorld((String)this.treasureWorldName);
            boolean stillThere = false;
            if (world != null && (block = world.getBlockAt(this.treasureX, this.treasureY, this.treasureZ)).getType() == Material.CHEST && (state = block.getState()) instanceof TileState) {
                TileState tile = (TileState)state;
                Byte marker = (Byte)tile.getPersistentDataContainer().get(this.treasureChestMarkerKey, PersistentDataType.BYTE);
                boolean bl = stillThere = marker != null;
            }
            if (!stillThere) {
                this.treasureActive = false;
                this.treasureNextSpawnAt = System.currentTimeMillis() + (long)this.cfgTreasureIntervalHours * 3600000L;
            }
            return;
        }
        if (System.currentTimeMillis() < this.treasureNextSpawnAt) {
            return;
        }
        World world = (World)Bukkit.getWorlds().get(0);
        Location spawn = world.getSpawnLocation();
        double angle = Math.random() * Math.PI * 2.0;
        double dist = Math.random() * this.cfgTreasureSpawnRadius;
        double x = spawn.getX() + Math.cos(angle) * dist;
        double z = spawn.getZ() + Math.sin(angle) * dist;
        double y = world.getHighestBlockYAt((int)x, (int)z) + 1;
        Block block = world.getBlockAt((int)x, (int)y, (int)z);
        block.setType(Material.CHEST);
        BlockState state = block.getState();
        if (state instanceof TileState) {
            TileState tile = (TileState)state;
            tile.getPersistentDataContainer().set(this.treasureChestMarkerKey, PersistentDataType.BYTE, (Object)1);
            state.update(true);
        }
        this.treasureReward = this.cfgTreasureRewardMin + Math.random() * (this.cfgTreasureRewardMax - this.cfgTreasureRewardMin);
        this.treasureWorldName = world.getName();
        this.treasureX = block.getX();
        this.treasureY = block.getY();
        this.treasureZ = block.getZ();
        this.treasureActive = true;
        this.broadcastNews("<gold><bold>\u3010\u57cb\u8535\u91d1\u3011</bold> " + this.treasureWorldName + " (" + this.treasureX + ", " + this.treasureY + ", " + this.treasureZ + ") \u4ed8\u8fd1\u306b\u57cb\u8535\u91d1\u306e\u5165\u3063\u305f\u30c1\u30a7\u30b9\u30c8\u304c\u51fa\u73fe\u3057\u307e\u3057\u305f\uff01\u6700\u521d\u306b\u767a\u898b\u3057\u305f\u8005\u304c\u7dcf\u53d6\u308a\u3067\u3059\u3002</gold>");
    }

    private void openTravelingMerchantGUI(Player p) {
        this.ensureMerchantDealsCurrent();
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tTravelingMerchant);
        long remainMs = Math.max(0L, this.merchantRefreshAt - System.currentTimeMillis());
        gui.setItem(4, this.createItem(Material.EMERALD, "<green><bold>\u3010\u5de1\u56de\u5546\u4eba\u3011</bold></green>", "<gray>\u65e5\u66ff\u308f\u308a\u3067\u6570\u91cf\u9650\u5b9a\u30fb\u5272\u5f15\u4fa1\u683c\u306e\u54c1\u3092\u8ca9\u58f2\u3057\u3066\u3044\u307e\u3059\u3002</gray>", "<gray>\u6b21\u56de\u306e\u66f4\u65b0\u307e\u3067: </gray><gold>" + this.formatHoursMinutes(remainMs, false) + "</gold>"));
        int slot = 9;
        for (MerchantDeal deal : this.merchantDeals) {
            if (slot >= 18) break;
            if (deal.stockRemaining <= 0) {
                gui.setItem(slot, this.createItem(Material.GRAY_DYE, "<gray><bold>" + this.merchantDisplayName(deal.material) + "</bold></gray>", "<dark_gray>\u58f2\u308a\u5207\u308c</dark_gray>"));
            } else {
                double discounted = deal.normalPrice * (1.0 - deal.discountPercent / 100.0);
                ItemStack item = this.createItem(deal.material, "<aqua><bold>" + this.merchantDisplayName(deal.material) + "</bold></aqua>", "<gray>\u901a\u5e38\u4fa1\u683c: </gray><strikethrough><white>" + (long)deal.normalPrice + "\u5186</white></strikethrough>", "<gray>\u5272\u5f15\u4fa1\u683c: </gray><gold><bold>" + (long)discounted + "\u5186</bold></gold>", "<green>\u5272\u5f15\u7387: " + String.format("%.0f", deal.discountPercent) + "%OFF</green>", "<gray>\u5728\u5eab: </gray><white>" + deal.stockRemaining + " / " + deal.stockTotal + "</white>", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u8cfc\u5165</yellow>");
                this.setMerchantDealTag(item, deal.id);
                gui.setItem(slot, item);
            }
            ++slot;
        }
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void executeMerchantPurchase(Player p, MerchantDeal deal) {
        UUID u = p.getUniqueId();
        if (deal.stockRemaining <= 0) {
            this.errorSound(p);
            return;
        }
        double price = deal.normalPrice * (1.0 - deal.discountPercent / 100.0);
        if (econ.getBalance((OfflinePlayer)p) < price) {
            InstallmentPlan plan = this.tryStartMerchantInstallment(p, deal, price);
            if (plan == null) {
                this.msgKey(p, "merchant.funds-insufficient", new String[0]);
                this.errorSound(p);
                return;
            }
            HashMap leftoverPlan = p.getInventory().addItem(new ItemStack[]{new ItemStack(deal.material, 1)});
            for (ItemStack over : leftoverPlan.values()) {
                p.getWorld().dropItem(p.getLocation(), over);
            }
            --deal.stockRemaining;
            this.addLog(u, "\u5de1\u56de\u5546\u4eba: " + this.merchantDisplayName(deal.material) + " \u3092\u5206\u5272\u6255\u3044\u3067\u8cfc\u5165 (\u521d\u56de" + (long)plan.installmentAmount + "\u5186)");
            this.sendDiscordWebhook("\ud83e\uddf3 **" + p.getName() + "** \u304c\u5de1\u56de\u5546\u4eba\u304b\u3089 **" + this.merchantDisplayName(deal.material) + "** \u3092\u5206\u5272\u6255\u3044\u3067\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08\u521d\u56de -" + (long)plan.installmentAmount + "\u5186\uff09");
            this.msgKey(p, "merchant.installment-purchased", "item", this.merchantDisplayName(deal.material), "first", String.format("%.2f", plan.installmentAmount), "remaining", String.valueOf(plan.installmentsRemaining), "each", String.format("%.2f", plan.installmentAmount), "count", String.valueOf(plan.installmentsRemaining + 1));
            this.clickSound(p);
            this.openTravelingMerchantGUI(p);
            return;
        }
        econ.withdrawPlayer((OfflinePlayer)p, price);
        HashMap leftover = p.getInventory().addItem(new ItemStack[]{new ItemStack(deal.material, 1)});
        for (ItemStack over : leftover.values()) {
            p.getWorld().dropItem(p.getLocation(), over);
        }
        --deal.stockRemaining;
        this.addLog(u, "\u5de1\u56de\u5546\u4eba: " + this.merchantDisplayName(deal.material) + " \u3092\u8cfc\u5165 -" + this.fmtCur(price));
        this.sendDiscordWebhook("\ud83e\uddf3 **" + p.getName() + "** \u304c\u5de1\u56de\u5546\u4eba\u304b\u3089 **" + this.merchantDisplayName(deal.material) + "** \u3092\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08-" + (long)price + "\u5186\uff09");
        this.msgKey(p, "merchant.bought", "item", this.merchantDisplayName(deal.material), "amount", String.valueOf((long)price));
        this.clickSound(p);
        this.openTravelingMerchantGUI(p);
    }

    private InstallmentPlan tryStartMerchantInstallment(Player p, MerchantDeal deal, double price) {
        double limit;
        if (!this.cfgInstallmentEnabled) {
            return null;
        }
        UUID u = p.getUniqueId();
        int score = this.getScore(u);
        if (score < this.cfgInstallmentMinCreditScore) {
            return null;
        }
        int effectiveCount = Math.max(1, this.cfgInstallmentCount);
        double installmentFeeRate = this.cfgInstallmentFeeRate * this.vipFeeMultiplier(u);
        double totalToRepay = price * (1.0 + installmentFeeRate);
        double perInstallment = totalToRepay / (double)effectiveCount;
        double outstanding = this.getInstallmentOutstanding(u);
        if (outstanding + totalToRepay > (limit = this.getInstallmentCreditLimit(score))) {
            return null;
        }
        if (econ.getBalance((OfflinePlayer)p) < perInstallment) {
            return null;
        }
        econ.withdrawPlayer((OfflinePlayer)p, perInstallment);
        InstallmentPlan plan = new InstallmentPlan();
        plan.id = UUID.randomUUID();
        plan.owner = u;
        plan.description = this.merchantDisplayName(deal.material);
        plan.installmentAmount = perInstallment;
        plan.installmentsRemaining = effectiveCount - 1;
        plan.nextDueTime = System.currentTimeMillis() + (long)this.cfgInstallmentIntervalHours * 3600000L;
        if (plan.installmentsRemaining > 0) {
            this.installmentPlans.computeIfAbsent(u, k -> new ArrayList()).add(plan);
        }
        return plan;
    }

    private void openVipLoungeGUI(Player p) {
        UUID u = p.getUniqueId();
        int tier = this.getVipTier(u);
        int score = this.getScore(u);
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tVipLounge);
        ArrayList<Object> lore = new ArrayList<Object>();
        lore.add("<gray>\u73fe\u5728\u306e\u30c6\u30a3\u30a2: </gray><white>" + this.vipTierName(tier) + "</white>");
        lore.add("<gray>\u4fe1\u7528\u30b9\u30b3\u30a2: </gray><white>" + score + " / 800</white>");
        if (tier >= 3) {
            lore.add("<green>\u6700\u9ad8\u30c6\u30a3\u30a2\u3067\u3059</green>");
        } else {
            int need = tier == 0 ? this.cfgVipSilverScore : (tier == 1 ? this.cfgVipGoldScore : this.cfgVipPlatinumScore);
            lore.add("<gray>\u6b21\u306e\u30c6\u30a3\u30a2(" + this.vipTierName(tier + 1) + ")\u307e\u3067: </gray><gold>\u3042\u3068 " + Math.max(0, need - score) + "</gold> <dark_gray>(\u5fc5\u8981\u30b9\u30b3\u30a2 " + need + ")</dark_gray>");
        }
        lore.add("");
        if (tier >= 1) {
            lore.add("<light_purple>\u3010\u9069\u7528\u4e2d\u306e\u7279\u5178\u3011</light_purple>");
            lore.add("<gray>\u30fb\u624b\u6570\u6599\u5272\u5f15: </gray><white>" + String.format("%.0f", (1.0 - this.vipFeeMultiplier(u)) * 100.0) + "%OFF</white> <dark_gray>(\u4e16\u754c\u682a/\u30d5\u30a1\u30f3\u30c9/\u30aa\u30fc\u30af\u30b7\u30e7\u30f3)</dark_gray>");
            lore.add("<gray>\u30fb\u65e5\u6b21VIP\u624b\u5f53: </gray><white>" + (long)this.vipStipendAmount(tier) + "\u5186 / 24\u6642\u9593</white>");
            lore.add("<gray>\u30fb\u4e16\u754c\u682a\u306e1\u65e5\u53d6\u5f15\u67a0: </gray><white>x" + String.format("%.1f", this.vipTradeLimitMultiplier(u)) + "</white>");
            lore.add("<gray>\u30fbVIP\u9650\u5b9a\u30b7\u30e7\u30c3\u30d7\u306e\u5229\u7528</gray>");
        } else {
            lore.add("<yellow>\u4fe1\u7528\u30b9\u30b3\u30a2\u304c " + this.cfgVipSilverScore + " \u4ee5\u4e0a\u3067\u30b7\u30eb\u30d0\u30fc\u4f1a\u54e1\u306b\u306a\u308a\u3001\u7279\u5178\u304c\u89e3\u653e\u3055\u308c\u307e\u3059\u3002</yellow>");
            lore.add("<red><bold>\u73fe\u5728\u306f VIP\u5bfe\u8c61\u5916 \u3067\u3059\u3002</bold></red>");
        }
        gui.setItem(4, this.createItem(Material.DIAMOND, "<light_purple><bold>\ud83d\udc8e VIP\u4f1a\u54e1\u30b9\u30c6\u30fc\u30bf\u30b9</bold></light_purple>", lore.toArray(new String[0])));
        if (tier == 0) {
            gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
            this.fillGlass(gui);
            p.openInventory(gui);
            return;
        }
        if (this.cfgVipStipendEnabled) {
            long now = System.currentTimeMillis();
            long last = this.vipStipendClaimedAt.getOrDefault(u, 0L);
            long cooldownMs = 86400000L;
            double amount = this.vipStipendAmount(tier);
            if (now - last >= cooldownMs) {
                gui.setItem(2, this.createItem(Material.GOLD_INGOT, "<gold><bold>\u65e5\u6b21VIP\u624b\u5f53\u3092\u53d7\u3051\u53d6\u308b</bold></gold>", "<gray>\u53d7\u53d6\u984d: </gray><gold>" + (long)amount + "\u5186</gold>", "<dark_gray>\u56fd\u5eab\u8ca1\u6e90\u300224\u6642\u9593\u3054\u3068\u306b1\u56de\u53d7\u3051\u53d6\u308c\u307e\u3059\u3002</dark_gray>", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u53d7\u3051\u53d6\u308b</yellow>"));
            } else {
                long remain = cooldownMs - (now - last);
                gui.setItem(2, this.createItem(Material.CLOCK, "<gray><bold>\u65e5\u6b21VIP\u624b\u5f53(\u53d7\u53d6\u6e08\u307f)</bold></gray>", "<gray>\u6b21\u56de\u53d7\u53d6\u307e\u3067: </gray><white>" + this.formatHoursMinutes(remain, true) + "</white>"));
            }
        }
        this.ensureVipShopCurrent();
        long remainMs = Math.max(0L, this.vipShopRefreshAt - System.currentTimeMillis());
        gui.setItem(6, this.createItem(Material.NETHER_STAR, "<light_purple><bold>\u3010VIP\u9650\u5b9a\u30b7\u30e7\u30c3\u30d7\u3011</bold></light_purple>", "<gray>VIP\u4f1a\u54e1\u3060\u3051\u304c\u8cb7\u3048\u308b\u5272\u5f15\u30e9\u30a4\u30f3\u30ca\u30c3\u30d7\u3002\u6570\u91cf\u9650\u5b9a\u3067\u3059\u3002</gray>", "<gray>\u6b21\u56de\u66f4\u65b0\u307e\u3067: </gray><gold>" + this.formatHoursMinutes(remainMs, false) + "</gold>"));
        int slot = 9;
        for (VipDeal deal : this.vipShopDeals) {
            if (slot >= 18) break;
            if (deal.stockRemaining <= 0) {
                gui.setItem(slot, this.createItem(Material.GRAY_DYE, "<gray><bold>" + this.merchantDisplayName(deal.material) + "</bold></gray>", "<dark_gray>\u58f2\u308a\u5207\u308c</dark_gray>"));
            } else {
                double discounted = deal.normalPrice * (1.0 - deal.discountPercent / 100.0);
                ItemStack card = this.createItem(deal.material, "<light_purple><bold>" + this.merchantDisplayName(deal.material) + "</bold></light_purple>", "<gray>\u901a\u5e38\u4fa1\u683c: </gray><strikethrough><white>" + (long)deal.normalPrice + "\u5186</white></strikethrough>", "<gray>VIP\u5272\u5f15\u4fa1\u683c: </gray><gold><bold>" + (long)discounted + "\u5186</bold></gold>", "<green>\u5272\u5f15\u7387: " + String.format("%.0f", deal.discountPercent) + "%OFF</green>", "<gray>\u5728\u5eab: </gray><white>" + deal.stockRemaining + " / " + deal.stockTotal + "</white>", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u8cfc\u5165</yellow>");
                this.setVipDealTag(card, deal.id);
                gui.setItem(slot, card);
            }
            ++slot;
        }
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void claimVipStipend(Player p) {
        long cooldownMs;
        long last;
        UUID u = p.getUniqueId();
        int tier = this.getVipTier(u);
        if (!this.cfgVipStipendEnabled) {
            this.msgKey(p, "vip.stipend-disabled", new String[0]);
            this.errorSound(p);
            return;
        }
        if (tier < 1) {
            this.msgKey(p, "vip.not-eligible", new String[0]);
            this.errorSound(p);
            return;
        }
        double amount = this.vipStipendAmount(tier);
        if (amount <= 0.0) {
            this.msgKey(p, "vip.not-eligible", new String[0]);
            this.errorSound(p);
            return;
        }
        long now = System.currentTimeMillis();
        if (now - (last = this.vipStipendClaimedAt.getOrDefault(u, 0L).longValue()) < (cooldownMs = 86400000L)) {
            long remain = cooldownMs - (now - last);
            long h = remain / 3600000L;
            this.msgKey(p, "vip.stipend-cooldown", "time", this.formatHoursMinutes(remain, true), "hours", String.valueOf(h));
            this.errorSound(p);
            return;
        }
        if (this.treasury < amount) {
            this.msgKey(p, "vip.stipend-treasury-empty", new String[0]);
            this.errorSound(p);
            return;
        }
        this.treasury -= amount;
        econ.depositPlayer((OfflinePlayer)p, amount);
        this.vipStipendClaimedAt.put(u, now);
        this.addLog(u, "VIP\u624b\u5f53(" + this.vipTierName(tier) + ") +" + this.fmtCur(amount));
        this.sendDiscordWebhook("\ud83d\udc8e **" + p.getName() + "** \u304cVIP\u624b\u5f53(" + this.vipTierName(tier) + ")\u3068\u3057\u3066 " + (long)amount + "\u5186 \u3092\u53d7\u3051\u53d6\u308a\u307e\u3057\u305f\u3002");
        this.msgKey(p, "vip.stipend-claimed", "amount", String.valueOf((long)amount), "tier", this.vipTierName(tier));
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }

    private void executeVipShopPurchase(Player p, VipDeal deal) {
        UUID u = p.getUniqueId();
        if (this.getVipTier(u) < 1) {
            this.msgKey(p, "vip.not-eligible", new String[0]);
            this.errorSound(p);
            return;
        }
        if (deal.stockRemaining <= 0) {
            this.errorSound(p);
            return;
        }
        double price = deal.normalPrice * (1.0 - deal.discountPercent / 100.0);
        if (econ.getBalance((OfflinePlayer)p) < price) {
            this.msgKey(p, "vip.shop-funds-insufficient", new String[0]);
            this.errorSound(p);
            return;
        }
        econ.withdrawPlayer((OfflinePlayer)p, price);
        HashMap leftover = p.getInventory().addItem(new ItemStack[]{new ItemStack(deal.material, 1)});
        for (ItemStack over : leftover.values()) {
            p.getWorld().dropItem(p.getLocation(), over);
        }
        --deal.stockRemaining;
        this.addLog(u, "VIP\u9650\u5b9a\u30b7\u30e7\u30c3\u30d7: " + this.merchantDisplayName(deal.material) + " \u3092\u8cfc\u5165 -" + this.fmtCur(price));
        this.sendDiscordWebhook("\ud83d\udc8e **" + p.getName() + "** \u304cVIP\u9650\u5b9a\u30b7\u30e7\u30c3\u30d7\u304b\u3089 **" + this.merchantDisplayName(deal.material) + "** \u3092\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08-" + (long)price + "\u5186\uff09");
        this.msgKey(p, "vip.shop-bought", "item", this.merchantDisplayName(deal.material), "amount", String.valueOf((long)price));
        this.clickSound(p);
        this.openVipLoungeGUI(p);
    }

    private void openQuestBoardGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tQuestBoard);
        UUID u = p.getUniqueId();
        long now = System.currentTimeMillis();
        int slot = 0;
        for (Quest q2 : this.quests.values()) {
            Object name;
            Material mat;
            String posterName;
            if (slot >= 44) break;
            boolean isPoster = q2.posterId.equals(u);
            boolean isAccepter = u.equals(q2.acceptedBy);
            if (q2.posterId.equals(SYSTEM_QUEST_POSTER_ID)) {
                posterName = "\u904b\u55b6";
            } else {
                posterName = Bukkit.getOfflinePlayer((UUID)q2.posterId).getName();
                if (posterName == null) {
                    posterName = "\u4e0d\u660e";
                }
            }
            Object distStr = "<gray>\u4e0d\u660e(\u5225\u30ef\u30fc\u30eb\u30c9)</gray>";
            World w = Bukkit.getWorld((String)q2.worldName);
            if (w != null && w.equals((Object)p.getWorld())) {
                double dist = p.getLocation().distance(new Location(w, q2.x, q2.y, q2.z));
                distStr = "<yellow>" + (long)dist + "\u30d6\u30ed\u30c3\u30af</yellow>";
            }
            boolean isSystemQuest = q2.posterId.equals(SYSTEM_QUEST_POSTER_ID);
            ArrayList<Object> lores = new ArrayList<Object>();
            lores.add("<gray>\u4f9d\u983c\u4e3b: </gray>" + (isSystemQuest ? "<gold>" : "<aqua>") + posterName + (isSystemQuest ? "</gold>" : "</aqua>"));
            if (isSystemQuest) {
                lores.add("<gold>\u2605 \u904b\u55b6\u304b\u3089\u306e\u4f9d\u983c</gold>");
            }
            lores.add("<gray>\u5ea7\u6a19: </gray><white>" + q2.worldName + " (" + (long)q2.x + ", " + (long)q2.y + ", " + (long)q2.z + ")</white>");
            lores.add("<gray>\u73fe\u5728\u5730\u304b\u3089\u306e\u8ddd\u96e2: </gray>" + (String)distStr);
            if (q2.requiredTeamSize > 1) {
                lores.add("<gray>\u4e00\u4eba\u3042\u305f\u308a\u5831\u916c: </gray><gold>" + (long)q2.reward + "\u5186</gold>");
                lores.add("<gray>\u5408\u8a08\u30b3\u30b9\u30c8: </gray><gold>" + (long)(q2.reward * (double)q2.requiredTeamSize) + "\u5186</gold>");
                lores.add("");
                boolean isMember = q2.teamMembers.contains(u);
                if (isPoster) {
                    if (q2.state == QuestState.AVAILABLE) {
                        lores.add("<gray>\u52df\u96c6\u72b6\u6cc1: </gray><white>" + q2.teamMembers.size() + " / " + q2.requiredTeamSize + "\u4eba</white>");
                        if (q2.teamMembers.isEmpty()) {
                            mat = Material.MAP;
                            name = "<green><bold>\ud83e\udd1d \u3042\u306a\u305f\u306e\u30c1\u30fc\u30e0\u4f9d\u983c(\u672a\u53c2\u52a0) - \u30af\u30ea\u30c3\u30af\u3067\u53d6\u308a\u4e0b\u3052</bold></green>";
                            lores.add("<red>\u25b6 \u30af\u30ea\u30c3\u30af\u3067\u53d6\u308a\u4e0b\u3052\u3066\u5831\u916c\u3092\u8fd4\u91d1</red>");
                        } else {
                            mat = Material.MAP;
                            name = "<yellow><bold>\ud83e\udd1d \u3042\u306a\u305f\u306e\u30c1\u30fc\u30e0\u4f9d\u983c(\u52df\u96c6\u4e2d)</bold></yellow>";
                            lores.add("<gray>\u53c2\u52a0\u8005\u304c\u3044\u308b\u305f\u3081\u53d6\u308a\u4e0b\u3052\u3089\u308c\u307e\u305b\u3093</gray>");
                        }
                    } else if (q2.state == QuestState.IN_PROGRESS) {
                        mat = Material.RECOVERY_COMPASS;
                        name = "<yellow><bold>\ud83e\udd1d \u3042\u306a\u305f\u306e\u30c1\u30fc\u30e0\u4f9d\u983c(\u6311\u6226\u4e2d)</bold></yellow>";
                        lores.add("<gray>\u5230\u9054\u72b6\u6cc1: </gray><white>" + q2.teamArrived.size() + " / " + q2.teamMembers.size() + "\u4eba</white>");
                    } else {
                        long remain = this.questCooldownRemainMinutes(q2.cooldownUntil, now);
                        mat = Material.CLOCK;
                        name = "<gray><bold>\ud83e\udd1d \u3042\u306a\u305f\u306e\u30c1\u30fc\u30e0\u4f9d\u983c(\u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d)</bold></gray>";
                        lores.add("<gray>\u518d\u51fa\u54c1\u307e\u3067: </gray><white>" + remain + "\u5206</white>");
                    }
                } else if (q2.state == QuestState.AVAILABLE) {
                    lores.add("<gray>\u52df\u96c6\u72b6\u6cc1: </gray><white>" + q2.teamMembers.size() + " / " + q2.requiredTeamSize + "\u4eba</white>");
                    mat = Material.MAP;
                    if (isMember) {
                        name = "<gold><bold>\ud83e\udd1d \u30c1\u30fc\u30e0\u4f9d\u983c(\u53c2\u52a0\u4e2d\u30fb\u52df\u96c6\u4e2d) - \u30af\u30ea\u30c3\u30af\u3067\u53c2\u52a0\u53d6\u6d88</bold></gold>";
                        lores.add("<red>\u25b6 \u30af\u30ea\u30c3\u30af\u3067\u53c2\u52a0\u3092\u53d6\u308a\u6d88\u3059</red>");
                    } else {
                        name = "<green><bold>\ud83e\udd1d \u30c1\u30fc\u30e0\u4f9d\u983c(\u52df\u96c6\u4e2d) - \u30af\u30ea\u30c3\u30af\u3067\u53c2\u52a0</bold></green>";
                        lores.add("<yellow>\u25b6 \u30af\u30ea\u30c3\u30af\u3067\u53c2\u52a0\u3059\u308b</yellow>");
                    }
                } else if (q2.state == QuestState.IN_PROGRESS && isMember) {
                    mat = Material.RECOVERY_COMPASS;
                    name = "<gold><bold>\ud83e\udd1d \u30c1\u30fc\u30e0\u4f9d\u983c(\u6311\u6226\u4e2d) - \u30af\u30ea\u30c3\u30af\u3067\u653e\u68c4</bold></gold>";
                    lores.add("<gray>\u5230\u9054\u72b6\u6cc1: </gray><white>" + q2.teamArrived.size() + " / " + q2.teamMembers.size() + "\u4eba</white>");
                    lores.add("<red>\u25b6 \u30af\u30ea\u30c3\u30af\u3067\u653e\u68c4\u3059\u308b(\u30c1\u30fc\u30e0\u5168\u4f53\u304c\u52df\u96c6\u4e2d\u306b\u623b\u308a\u307e\u3059)</red>");
                } else if (q2.state == QuestState.IN_PROGRESS) {
                    mat = Material.GRAY_DYE;
                    name = "<dark_gray>\ud83e\udd1d \u4ed6\u30d7\u30ec\u30a4\u30e4\u30fc\u306e\u30c1\u30fc\u30e0\u304c\u6311\u6226\u4e2d</dark_gray>";
                    lores.add("<gray>\u5230\u9054\u72b6\u6cc1: </gray><white>" + q2.teamArrived.size() + " / " + q2.teamMembers.size() + "\u4eba</white>");
                } else {
                    long remain = this.questCooldownRemainMinutes(q2.cooldownUntil, now);
                    mat = Material.CLOCK;
                    name = "<dark_gray>\ud83e\udd1d \u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d(\u6b8b\u308a" + remain + "\u5206)</dark_gray>";
                }
            } else {
                lores.add("<gray>\u5831\u916c: </gray><gold>" + (long)q2.reward + "\u5186</gold>");
                lores.add("");
                if (isPoster) {
                    if (q2.state == QuestState.AVAILABLE) {
                        mat = Material.PAPER;
                        name = "<green><bold>\u3042\u306a\u305f\u306e\u4f9d\u983c(\u672a\u53d7\u6ce8) - \u30af\u30ea\u30c3\u30af\u3067\u53d6\u308a\u4e0b\u3052</bold></green>";
                        lores.add("<red>\u25b6 \u30af\u30ea\u30c3\u30af\u3067\u53d6\u308a\u4e0b\u3052\u3066\u5831\u916c\u3092\u8fd4\u91d1</red>");
                    } else if (q2.state == QuestState.IN_PROGRESS) {
                        String accepterName = Bukkit.getOfflinePlayer((UUID)q2.acceptedBy).getName();
                        mat = Material.COMPASS;
                        name = "<yellow><bold>\u3042\u306a\u305f\u306e\u4f9d\u983c(\u53d7\u6ce8\u4e2d)</bold></yellow>";
                        lores.add("<gray>\u53d7\u6ce8\u8005: </gray><aqua>" + (accepterName != null ? accepterName : "\u4e0d\u660e") + "</aqua>");
                    } else {
                        long remain = this.questCooldownRemainMinutes(q2.cooldownUntil, now);
                        mat = Material.CLOCK;
                        name = "<gray><bold>\u3042\u306a\u305f\u306e\u4f9d\u983c(\u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d)</bold></gray>";
                        lores.add("<gray>\u518d\u51fa\u54c1\u307e\u3067: </gray><white>" + remain + "\u5206</white>");
                    }
                } else if (q2.state == QuestState.AVAILABLE) {
                    mat = Material.PAPER;
                    name = "<green><bold>\u63a2\u7d22\u4f9d\u983c - \u30af\u30ea\u30c3\u30af\u3067\u53d7\u6ce8</bold></green>";
                    lores.add("<yellow>\u25b6 \u30af\u30ea\u30c3\u30af\u3067\u53d7\u6ce8\u3059\u308b</yellow>");
                } else if (isAccepter) {
                    mat = Material.COMPASS;
                    name = "<gold><bold>\u53d7\u6ce8\u4e2d\u306e\u4f9d\u983c - \u30af\u30ea\u30c3\u30af\u3067\u653e\u68c4</bold></gold>";
                    lores.add("<red>\u25b6 \u30af\u30ea\u30c3\u30af\u3067\u653e\u68c4\u3059\u308b</red>");
                } else if (q2.state == QuestState.IN_PROGRESS) {
                    mat = Material.GRAY_DYE;
                    name = "<dark_gray>\u4ed6\u30d7\u30ec\u30a4\u30e4\u30fc\u304c\u53d7\u6ce8\u4e2d</dark_gray>";
                } else {
                    long remain = this.questCooldownRemainMinutes(q2.cooldownUntil, now);
                    mat = Material.CLOCK;
                    name = "<dark_gray>\u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d(\u6b8b\u308a" + remain + "\u5206)</dark_gray>";
                }
            }
            ItemStack item = this.createItem(mat, (String)name, lores.toArray(new String[0]));
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.getPersistentDataContainer().set(this.questIdKey, PersistentDataType.STRING, (Object)q2.id.toString());
                item.setItemMeta(meta);
            }
            gui.setItem(slot++, item);
        }
        long postedByMe = this.quests.values().stream().filter(q -> q.posterId.equals(u)).count();
        gui.setItem(49, this.createItem(Material.EMERALD, "<green><bold>\u2795 \u65b0\u3057\u3044\u4f9d\u983c\u3092\u51fa\u3059</bold></green>", "<gray>\u3042\u306a\u305f\u306e\u73fe\u5728\u5730\u3092\u76ee\u7684\u5730\u3068\u3057\u3066\u3001\u5831\u916c\u3092\u61f8\u3051\u3066\u4f9d\u983c\u3092\u63b2\u793a\u3057\u307e\u3059</gray>", "<gray>\u63b2\u793a\u4e2d\u306e\u4f9d\u983c\u6570: </gray><white>" + postedByMe + " / " + this.cfgQuestMaxPerPlayer + "</white>"));
        gui.setItem(48, this.createItem(Material.EMERALD_BLOCK, "<green><bold>\ud83e\udd1d \u30c1\u30fc\u30e0\u4f9d\u983c\u3092\u63b2\u793a</bold></green>", "<gray>\u3042\u306a\u305f\u306e\u73fe\u5728\u5730\u3092\u76ee\u7684\u5730\u3068\u3057\u3066\u3001\u8907\u6570\u4eba\u3067\u6311\u6226\u3059\u308b\u30c1\u30fc\u30e0\u4f9d\u983c\u3092\u63b2\u793a\u3057\u307e\u3059</gray>", "<gray>\u5168\u54e1\u304c\u540c\u6642\u306b\u76ee\u7684\u5730\u3078\u5230\u9054\u3059\u308b\u3068\u3001\u4e00\u4eba\u3042\u305f\u308a\u306e\u5831\u916c\u304c\u5168\u54e1\u306b\u652f\u6255\u308f\u308c\u307e\u3059</gray>", "<gray>\u63b2\u793a\u4e2d\u306e\u4f9d\u983c\u6570: </gray><white>" + postedByMe + " / " + this.cfgQuestMaxPerPlayer + "</white>"));
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAdminMainGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tAdminMain);
        gui.setItem(11, this.createItem(Material.PLAYER_HEAD, "<red><bold>\ud83d\udc64 \u30d7\u30ec\u30a4\u30e4\u30fc\u7ba1\u7406</bold></red>", "<gray>\u8cc7\u7523\u306e\u4ed8\u4e0e/\u6ca1\u53ce\u30fb\u4fe1\u7528\u30b9\u30b3\u30a2\u5909\u66f4\u30fb\u30c7\u30fc\u30bf\u30ea\u30bb\u30c3\u30c8</gray>"));
        gui.setItem(15, this.createItem(Material.COMMAND_BLOCK, "<red><bold>\u2699 \u30b5\u30fc\u30d0\u30fc\u7ba1\u7406</bold></red>", "<gray>\u7d4c\u6e08\u30a4\u30d9\u30f3\u30c8\u767a\u751f\u30fb\u81ea\u5df1\u8a3a\u65ad\u30fbreload/save</gray>"));
        gui.setItem(22, this.createItem(Material.BARRIER, "<gray>\u9589\u3058\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAdminPlayerListGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tAdminPlayerList);
        int slot = 0;
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (slot >= 45) break;
            UUID tid = online.getUniqueId();
            double pocket = econ.getBalance((OfflinePlayer)online);
            double bank = this.personalBank.getOrDefault(tid, 0.0);
            int score = this.getScore(tid);
            ItemStack head = this.createItem(Material.PLAYER_HEAD, "<yellow><bold>" + online.getName() + "</bold></yellow>", "<gray>\u6240\u6301\u91d1:</gray> <green>" + (long)pocket + "\u5186</green>", "<gray>\u9810\u91d1:</gray> <aqua>" + (long)bank + "\u5186</aqua>", "<gray>\u4fe1\u7528\u30b9\u30b3\u30a2:</gray> <light_purple>" + score + "</light_purple>", "<yellow>\u30af\u30ea\u30c3\u30af\u3067\u8a73\u7d30\u3092\u958b\u304f</yellow>");
            ItemMeta hm = head.getItemMeta();
            if (hm != null) {
                hm.getPersistentDataContainer().set(this.adminTargetKey, PersistentDataType.STRING, (Object)tid.toString());
                head.setItemMeta(hm);
            }
            gui.setItem(slot++, head);
        }
        gui.setItem(49, this.createItem(Material.NAME_TAG, "<white><bold>\ud83d\udd0d \u540d\u524d\u3067\u691c\u7d22\uff08\u30aa\u30d5\u30e9\u30a4\u30f3\u3082\u53ef\uff09</bold></white>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u30d7\u30ec\u30a4\u30e4\u30fc\u540d\u3092\u5165\u529b</gray>"));
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAdminPlayerDetailGUI(Player p, UUID targetId) {
        this.adminViewingTarget.put(p.getUniqueId(), targetId);
        OfflinePlayer target = Bukkit.getOfflinePlayer((UUID)targetId);
        String name = target.getName() != null ? target.getName() : targetId.toString();
        double pocket = 0.0;
        if (target.isOnline() && target.getPlayer() != null) {
            pocket = econ.getBalance((OfflinePlayer)target.getPlayer());
        } else if (econ.hasAccount(target)) {
            pocket = econ.getBalance(target);
        }
        double bank = this.personalBank.getOrDefault(targetId, 0.0);
        int score = this.getScore(targetId);
        double gDebt = this.govDebt.getOrDefault(targetId, 0.0);
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tAdminPlayerDetail);
        gui.setItem(4, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>" + name + "</bold></yellow>", "<gray>\u6240\u6301\u91d1:</gray> <green>" + (long)pocket + "\u5186</green>", "<gray>\u9810\u91d1:</gray> <aqua>" + (long)bank + "\u5186</aqua>", "<gray>\u4fe1\u7528\u30b9\u30b3\u30a2:</gray> <light_purple>" + score + "</light_purple>", "<gray>\u653f\u5e9c\u50b5\u52d9:</gray> <red>" + (long)gDebt + "\u5186</red>"));
        gui.setItem(10, this.createItem(Material.GOLD_INGOT, "<green><bold>\ud83d\udcb0 \u304a\u91d1\u3092\u4ed8\u4e0e\u3059\u308b</bold></green>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b</gray>"));
        gui.setItem(12, this.createItem(Material.REDSTONE, "<red><bold>\ud83d\udcb8 \u304a\u91d1\u3092\u6ca1\u53ce\u3059\u308b</bold></red>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u91d1\u984d\u3092\u5165\u529b</gray>"));
        gui.setItem(14, this.createItem(Material.EMERALD, "<light_purple><bold>\ud83d\udcca \u4fe1\u7528\u30b9\u30b3\u30a2\u3092\u8a2d\u5b9a</bold></light_purple>", "<gray>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b0\u301c800\u306e\u5024\u3092\u5165\u529b</gray>"));
        gui.setItem(16, this.createItem(Material.BARRIER, "<dark_red><bold>\ud83d\uddd1 \u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u30ea\u30bb\u30c3\u30c8</bold></dark_red>", "<gray>\u500b\u4eba\u306e\u53e3\u5ea7\u30fb\u878d\u8cc7\u30fb\u5b9f\u7e3e\u306a\u3069\u3092\u5168\u524a\u9664\u3057\u307e\u3059</gray>", "<red><bold>\u3082\u3046\u4e00\u5ea6\u30af\u30ea\u30c3\u30af\u3067\u78ba\u5b9a\uff0815\u79d2\u4ee5\u5185\uff09</bold></red>"));
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAdminServerGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tAdminServer);
        gui.setItem(10, this.createItem(Material.GOLD_BLOCK, "<gold><bold>\ud83c\udfb2 \u30e9\u30f3\u30c0\u30e0\u7d4c\u6e08\u30a4\u30d9\u30f3\u30c8\u767a\u751f</bold></gold>", new String[0]));
        gui.setItem(11, this.createItem(Material.EMERALD_BLOCK, "<green><bold>\ud83d\udcc8 \u9ec4\u91d1\u30e9\u30c3\u30b7\u30e5\u3092\u767a\u751f</bold></green>", new String[0]));
        gui.setItem(12, this.createItem(Material.NETHERITE_INGOT, "<yellow><bold>\ud83d\udcb0 \u624b\u6570\u6599\u9ad8\u9a30\u3092\u767a\u751f</bold></yellow>", new String[0]));
        gui.setItem(13, this.createItem(Material.SUNFLOWER, "<aqua><bold>\ud83c\udf81 \u30dc\u30fc\u30ca\u30b9\u652f\u7d66\u30c7\u30fc\u3092\u767a\u751f</bold></aqua>", new String[0]));
        gui.setItem(14, this.createItem(Material.REDSTONE_BLOCK, "<red><bold>\ud83d\udcc9 \u51ac\u306e\u6642\u4ee3\u3092\u767a\u751f</bold></red>", new String[0]));
        gui.setItem(16, this.createItem(Material.KNOWLEDGE_BOOK, "<light_purple><bold>\ud83d\udd0d \u81ea\u5df1\u8a3a\u65ad\u3092\u5b9f\u884c</bold></light_purple>", "<gray>\u30c7\u30fc\u30bf\u4e0d\u6574\u5408\u3092\u691c\u51fa\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u306b\u8868\u793a</gray>"));
        gui.setItem(19, this.createItem(Material.HOPPER, "<white><bold>\ud83d\udd04 config/messages\u3092\u518d\u8aad\u8fbc</bold></white>", new String[0]));
        gui.setItem(20, this.createItem(Material.CHEST, "<white><bold>\ud83d\udcbe \u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u4fdd\u5b58</bold></white>", new String[0]));
        gui.setItem(21, this.createItem(Material.BOOK, "<white><bold>\u2699 config\u8a2d\u5b9a\u3092\u7de8\u96c6</bold></white>", "<gray>config.yml\u306e\u5168\u8a2d\u5b9a\u3092GUI\u304b\u3089\u76f4\u63a5\u5909\u66f4\u3067\u304d\u307e\u3059</gray>"));
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private List<String> getAllSettingKeys() {
        FileConfiguration c = this.getConfig();
        ArrayList<String> keys = new ArrayList<String>();
        for (String key : c.getKeys(true)) {
            if (c.isConfigurationSection(key)) continue;
            keys.add(key);
        }
        Collections.sort(keys);
        return keys;
    }

    private void openConfigEditorGUI(Player p, int requestedPage) {
        UUID u = p.getUniqueId();
        List<String> keys = this.getAllSettingKeys();
        int totalPages = Math.max(1, (int)Math.ceil((double)keys.size() / 45.0));
        int page = Math.max(0, Math.min(requestedPage, totalPages - 1));
        this.configEditorPage.put(u, page);
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tConfigEditor);
        FileConfiguration c = this.getConfig();
        int from = page * 45;
        int to = Math.min(from + 45, keys.size());
        int slot = 0;
        for (int i = from; i < to; ++i) {
            String key = keys.get(i);
            Object value = c.get(key);
            String typeName = value == null ? "null" : value.getClass().getSimpleName();
            ItemStack item = this.createItem(Material.PAPER, "<aqua>" + key + "</aqua>", "<gray>\u73fe\u5728\u5024:</gray> <white>" + String.valueOf(value) + "</white>", "<dark_gray>\u578b: " + typeName + "</dark_gray>", "<yellow>\u30af\u30ea\u30c3\u30af\u3057\u3066\u30c1\u30e3\u30c3\u30c8\u3067\u65b0\u3057\u3044\u5024\u3092\u5165\u529b</yellow>");
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.getPersistentDataContainer().set(this.configKeyTag, PersistentDataType.STRING, (Object)key);
                item.setItemMeta(meta);
            }
            gui.setItem(slot++, item);
        }
        if (page > 0) {
            gui.setItem(51, this.createItem(Material.ARROW, "<aqua><bold>\u25c0 \u524d\u306e\u30da\u30fc\u30b8</bold></aqua>", "<gray>" + page + " / " + totalPages + "\u30da\u30fc\u30b8</gray>"));
        }
        if (page < totalPages - 1) {
            gui.setItem(52, this.createItem(Material.ARROW, "<aqua><bold>\u6b21\u306e\u30da\u30fc\u30b8 \u25b6</bold></aqua>", "<gray>" + (page + 2) + " / " + totalPages + "\u30da\u30fc\u30b8</gray>"));
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>\u623b\u308b</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    /*
     * Unable to fully structure code
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        block550: {
            block551: {
                block552: {
                    var3_2 = e.getWhoClicked();
                    if (!(var3_2 instanceof Player)) {
                        return;
                    }
                    p = (Player)var3_2;
                    t = e.getView().title();
                    if (t.equals((Object)this.tTrade)) {
                        this.handleTradeClick(e);
                        return;
                    }
                    if (!(t.equals((Object)this.tMain) || t.equals((Object)this.tPersonal) || t.equals((Object)this.tMarket) || t.equals((Object)this.tRepay) || t.equals((Object)this.tEstablish) || t.equals((Object)this.tBanker) || t.equals((Object)this.tPlan) || t.equals((Object)this.tGovLoan) || t.equals((Object)this.tFixedDepo) || t.equals((Object)this.tCredit) || t.equals((Object)this.tLeaderboard) || t.equals((Object)this.tCollateral) || t.equals((Object)this.tInsurance) || t.equals((Object)this.tAchievement) || t.equals((Object)this.tAuction) || t.equals((Object)this.tAuctionCancelConfirm) || t.equals((Object)this.tAuctionRanking) || t.equals((Object)this.tHub) || t.equals((Object)this.tBankHub) || t.equals((Object)this.tMarketHub) || t.equals((Object)this.tTodoHub) || t.equals((Object)this.tMyPage) || t.equals((Object)this.tTutorial) || t.equals((Object)this.tAuctionSelect) || t.equals((Object)this.tCollateralSelect) || t.equals((Object)this.tAdminMain) || t.equals((Object)this.tAdminPlayerList) || t.equals((Object)this.tAdminPlayerDetail) || t.equals((Object)this.tAdminServer) || t.equals((Object)this.tConfigEditor) || t.equals((Object)this.tQuestBoard) || t.equals((Object)this.tWorldStock) || t.equals((Object)this.tWorldStockDetail) || t.equals((Object)this.tWorldStockLeaderboard) || t.equals((Object)this.tResourceShop) || t.equals((Object)this.tResourceShopList) || t.equals((Object)this.tResourceShopDetail) || t.equals((Object)this.tTravelingMerchant) || t.equals((Object)this.tInstallmentList) || t.equals((Object)this.tLottery) || t.equals((Object)this.tStorageRent) || t.equals((Object)this.tTradeSelect) || t.equals((Object)this.tGroupList) || t.equals((Object)this.tGroupAccount) || t.equals((Object)this.tFundList) || t.equals((Object)this.tFundInfo) || t.equals((Object)this.tFundStock) || t.equals((Object)this.tFundStockDetail) || t.equals((Object)this.tGroupMembers) || t.equals((Object)this.tVipLounge))) {
                        return;
                    }
                    e.setCancelled(true);
                    item = e.getCurrentItem();
                    if (item == null || item.getType() == Material.BLACK_STAINED_GLASS_PANE || item.getType() == Material.AIR) {
                        return;
                    }
                    u = p.getUniqueId();
                    nowClick = System.currentTimeMillis();
                    if (nowClick - (lastClick = this.lastGuiClickTime.getOrDefault(u, 0L).longValue()) < 250L) {
                        return;
                    }
                    this.lastGuiClickTime.put(u, nowClick);
                    mat = item.getType();
                    pocket = MinecraftBank.econ.getBalance((OfflinePlayer)p);
                    if (t.equals((Object)this.tMain)) {
                        if (mat == Material.CHEST) {
                            this.openPersonalGUI(p);
                        } else if (mat == Material.EMERALD) {
                            this.openMarketGUI(p);
                        } else if (mat == Material.GOLD_INGOT) {
                            this.openRepayGUI(p);
                        } else if (mat == Material.ENDER_EYE) {
                            this.openEstablishGUI(p);
                        } else if (mat == Material.NETHER_STAR) {
                            this.openBankerGUI(p);
                        } else if (mat == Material.DIAMOND) {
                            this.openGovLoanGUI(p);
                        } else if (mat == Material.GOLD_BLOCK) {
                            this.openFixedDepoGUI(p);
                        } else if (mat == Material.EMERALD_BLOCK) {
                            this.openCreditGUI(p);
                        } else if (mat == Material.PLAYER_HEAD) {
                            this.openLeaderboardGUI(p);
                        } else if (mat == Material.ANVIL) {
                            this.openCollateralGUI(p);
                        } else if (mat == Material.GOLDEN_APPLE) {
                            this.openInsuranceGUI(p);
                        } else if (mat == Material.FIREWORK_ROCKET) {
                            this.openAchievementGUI(p);
                        } else if (mat == Material.ITEM_FRAME) {
                            this.openAuctionGUI(p);
                        } else if (mat == Material.COMPASS) {
                            this.openWorldStockGUI(p);
                        } else if (mat == Material.HOPPER) {
                            this.openResourceShopGUI(p);
                        } else if (mat == Material.LEATHER_HORSE_ARMOR) {
                            this.ensureMerchantDealsCurrent();
                            this.msgKey(p, "merchant.location-hint", new String[]{"world", this.merchantWorldName, "x", String.valueOf((int)this.merchantX), "y", String.valueOf((int)this.merchantY), "z", String.valueOf((int)this.merchantZ)});
                        } else if (mat == Material.FIREWORK_STAR) {
                            this.openLotteryGUI(p);
                        } else if (mat == Material.BARREL) {
                            this.openStorageRentGUI(p);
                        }
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tWorldStock)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.COMPASS) {
                            this.awaitingChatInput.put(u, "world_stock_search");
                            p.closeInventory();
                            this.msgKey(p, "worldstock.search-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.PLAYER_HEAD) {
                            this.openWorldStockLeaderboardGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (!item.hasItemMeta()) {
                            return;
                        }
                        symbol = (String)item.getItemMeta().getPersistentDataContainer().get(this.worldStockKey, PersistentDataType.STRING);
                        if (symbol == null) {
                            return;
                        }
                        this.openWorldStockDetailGUI(p, symbol);
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tWorldStockLeaderboard)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openWorldStockGUI(p);
                            this.clickSound(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tWorldStockDetail)) {
                        this.handleWorldStockDetailClick(p, u, mat, item, null);
                        return;
                    }
                    if (t.equals((Object)this.tResourceShop)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        switch (1.$SwitchMap$org$bukkit$Material[mat.ordinal()]) {
                            case 58: {
                                v0 = "ore";
                                break;
                            }
                            case 20: {
                                v0 = "crop";
                                break;
                            }
                            case 30: {
                                v0 = "drop";
                                break;
                            }
                            case 38: {
                                v0 = "wood";
                                break;
                            }
                            default: {
                                v0 = category = null;
                            }
                        }
                        if (category == null) {
                            return;
                        }
                        this.openResourceShopListGUI(p, category);
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tResourceShopList)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openResourceShopGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (!item.hasItemMeta()) {
                            return;
                        }
                        matName = (String)item.getItemMeta().getPersistentDataContainer().get(this.resourceMaterialKey, PersistentDataType.STRING);
                        if (matName == null) {
                            return;
                        }
                        try {
                            listedMat = Material.valueOf((String)matName);
                        }
                        catch (IllegalArgumentException ex) {
                            return;
                        }
                        this.openResourceShopDetailGUI(p, listedMat);
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tResourceShopDetail)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openResourceShopListGUI(p, this.resourceShopViewCategory.getOrDefault(u, "ore"));
                            this.clickSound(p);
                            return;
                        }
                        if (!item.hasItemMeta()) {
                            return;
                        }
                        matName = (String)item.getItemMeta().getPersistentDataContainer().get(this.resourceMaterialKey, PersistentDataType.STRING);
                        action = (String)item.getItemMeta().getPersistentDataContainer().get(this.resourceActionKey, PersistentDataType.STRING);
                        if (matName == null || action == null) {
                            return;
                        }
                        try {
                            rmat = Material.valueOf((String)matName);
                        }
                        catch (IllegalArgumentException ex) {
                            return;
                        }
                        ex = action;
                        var17_120 = -1;
                        switch (ex.hashCode()) {
                            case 109320991: {
                                if (!ex.equals("sell1")) break;
                                var17_120 = 0;
                                break;
                            }
                            case -906016521: {
                                if (!ex.equals("sell16")) break;
                                var17_120 = 1;
                                break;
                            }
                            case -906016368: {
                                if (!ex.equals("sell64")) break;
                                var17_120 = 2;
                                break;
                            }
                            case 3035755: {
                                if (!ex.equals("buy1")) break;
                                var17_120 = 3;
                                break;
                            }
                            case 94108459: {
                                if (!ex.equals("buy16")) break;
                                var17_120 = 4;
                                break;
                            }
                            case 94108612: {
                                if (!ex.equals("buy64")) break;
                                var17_120 = 5;
                                break;
                            }
                            case 1978322468: {
                                if (!ex.equals("sellqty")) break;
                                var17_120 = 6;
                                break;
                            }
                            case -1377541520: {
                                if (!ex.equals("buyqty")) break;
                                var17_120 = 7;
                            }
                        }
                        switch (var17_120) {
                            case 0: {
                                this.executeResourceSell(p, rmat, 1);
                                break;
                            }
                            case 1: {
                                this.executeResourceSell(p, rmat, 16);
                                break;
                            }
                            case 2: {
                                this.executeResourceSell(p, rmat, 64);
                                break;
                            }
                            case 3: {
                                this.executeResourceBuy(p, rmat, 1);
                                break;
                            }
                            case 4: {
                                this.executeResourceBuy(p, rmat, 16);
                                break;
                            }
                            case 5: {
                                this.executeResourceBuy(p, rmat, 64);
                                break;
                            }
                            case 6: {
                                this.awaitingChatInput.put(u, "resource_sell_qty:" + rmat.name());
                                p.closeInventory();
                                this.msgKey(p, "resourceshop.sell-qty-prompt", new String[]{"material", this.resourceDisplayName(rmat)});
                                break;
                            }
                            case 7: {
                                this.awaitingChatInput.put(u, "resource_buy_qty:" + rmat.name());
                                p.closeInventory();
                                this.msgKey(p, "resourceshop.buy-qty-prompt", new String[]{"material", this.resourceDisplayName(rmat)});
                                break;
                            }
                        }
                        return;
                    }
                    if (t.equals((Object)this.tLottery)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.PAPER) {
                            this.buyLotteryTickets(p, 1);
                            return;
                        }
                        if (mat == Material.EMERALD) {
                            this.buyLotteryTickets(p, 5);
                            return;
                        }
                        if (mat == Material.DIAMOND) {
                            this.buyLotteryTickets(p, 10);
                            return;
                        }
                        if (mat == Material.WRITABLE_BOOK) {
                            this.awaitingChatInput.put(u, "lottery_buy_qty");
                            p.closeInventory();
                            this.msgKey(p, "lottery.buy-qty-prompt", new String[0]);
                            return;
                        }
                        return;
                    }
                    if (t.equals((Object)this.tStorageRent)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (!this.storageRentDueTime.containsKey(u)) {
                            if (mat == Material.EMERALD) {
                                if (this.payOrStartStorageRent((OfflinePlayer)p)) {
                                    this.clickSound(p);
                                    this.openStorageRentGUI(p);
                                } else {
                                    this.errorSound(p);
                                }
                            }
                            return;
                        }
                        if (mat == Material.CHEST) {
                            this.openPlayerStorage(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.DIAMOND) {
                            if (this.payOrStartStorageRent((OfflinePlayer)p)) {
                                this.clickSound(p);
                                this.openStorageRentGUI(p);
                            } else {
                                this.errorSound(p);
                            }
                        }
                        return;
                    }
                    if (t.equals((Object)this.tTravelingMerchant)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (!item.hasItemMeta()) {
                            return;
                        }
                        dealIdStr = (String)item.getItemMeta().getPersistentDataContainer().get(this.merchantDealKey, PersistentDataType.STRING);
                        if (dealIdStr == null) {
                            return;
                        }
                        try {
                            dealId = UUID.fromString(dealIdStr);
                        }
                        catch (IllegalArgumentException ex) {
                            return;
                        }
                        deal = this.merchantDeals.stream().filter((Predicate<MerchantDeal>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$onInventoryClick$0(java.util.UUID kot0328.minecraftBank.MinecraftBank$MerchantDeal ), (Lkot0328/minecraftBank/MinecraftBank$MerchantDeal;)Z)((UUID)dealId)).findFirst().orElse(null);
                        if (deal == null) {
                            return;
                        }
                        this.executeMerchantPurchase(p, deal);
                        return;
                    }
                    if (t.equals((Object)this.tVipLounge)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openMarketHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.GOLD_INGOT) {
                            this.claimVipStipend(p);
                            this.openVipLoungeGUI(p);
                            return;
                        }
                        if (!item.hasItemMeta()) {
                            return;
                        }
                        vipDealIdStr = (String)item.getItemMeta().getPersistentDataContainer().get(this.vipDealKey, PersistentDataType.STRING);
                        if (vipDealIdStr == null) {
                            return;
                        }
                        try {
                            vipDealId = UUID.fromString(vipDealIdStr);
                        }
                        catch (IllegalArgumentException ex) {
                            return;
                        }
                        vipDeal = this.vipShopDeals.stream().filter((Predicate<VipDeal>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$onInventoryClick$1(java.util.UUID kot0328.minecraftBank.MinecraftBank$VipDeal ), (Lkot0328/minecraftBank/MinecraftBank$VipDeal;)Z)((UUID)vipDealId)).findFirst().orElse(null);
                        if (vipDeal == null) {
                            return;
                        }
                        this.executeVipShopPurchase(p, vipDeal);
                        return;
                    }
                    if (t.equals((Object)this.tQuestBoard)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.EMERALD) {
                            postedByMe = this.quests.values().stream().filter((Predicate<Quest>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$onInventoryClick$2(java.util.UUID kot0328.minecraftBank.MinecraftBank$Quest ), (Lkot0328/minecraftBank/MinecraftBank$Quest;)Z)((UUID)u)).count();
                            if (postedByMe >= (long)this.cfgQuestMaxPerPlayer) {
                                this.msgKey(p, "quest.max-reached", new String[]{"count", String.valueOf(this.cfgQuestMaxPerPlayer)});
                                this.errorSound(p);
                                return;
                            }
                            this.awaitingChatInput.put(u, "quest_post");
                            p.closeInventory();
                            this.msgKey(p, "quest.post-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.EMERALD_BLOCK) {
                            postedByMe = this.quests.values().stream().filter((Predicate<Quest>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$onInventoryClick$3(java.util.UUID kot0328.minecraftBank.MinecraftBank$Quest ), (Lkot0328/minecraftBank/MinecraftBank$Quest;)Z)((UUID)u)).count();
                            if (postedByMe >= (long)this.cfgQuestMaxPerPlayer) {
                                this.msgKey(p, "quest.max-reached", new String[]{"count", String.valueOf(this.cfgQuestMaxPerPlayer)});
                                this.errorSound(p);
                                return;
                            }
                            this.awaitingChatInput.put(u, "team_quest_post_reward");
                            p.closeInventory();
                            this.msgKey(p, "quest.team-post-prompt-reward", new String[0]);
                            return;
                        }
                        if (!item.hasItemMeta()) {
                            return;
                        }
                        qIdStr = (String)item.getItemMeta().getPersistentDataContainer().get(this.questIdKey, PersistentDataType.STRING);
                        if (qIdStr == null) {
                            return;
                        }
                        q = this.quests.get(UUID.fromString(qIdStr));
                        if (q == null) {
                            this.openQuestBoardGUI(p);
                            return;
                        }
                        isPoster = q.posterId.equals(u);
                        if (isPoster) {
                            if (q.requiredTeamSize > 1) {
                                if (q.state == QuestState.AVAILABLE) {
                                    if (!q.teamMembers.isEmpty()) {
                                        this.msgKey(p, "quest.team-withdraw-blocked", new String[0]);
                                    } else {
                                        refund = q.reward * (double)q.requiredTeamSize;
                                        MinecraftBank.econ.depositPlayer((OfflinePlayer)p, refund);
                                        this.quests.remove(q.id);
                                        this.msgKey(p, "quest.withdrawn", new String[]{"amount", String.valueOf((long)refund)});
                                        this.clickSound(p);
                                        this.openQuestBoardGUI(p);
                                    }
                                } else {
                                    this.msgKey(p, "quest.cannot-withdraw", new String[0]);
                                }
                            } else if (q.state == QuestState.AVAILABLE) {
                                MinecraftBank.econ.depositPlayer((OfflinePlayer)p, q.reward);
                                this.quests.remove(q.id);
                                this.msgKey(p, "quest.withdrawn", new String[]{"amount", String.valueOf((long)q.reward)});
                                this.clickSound(p);
                                this.openQuestBoardGUI(p);
                            } else {
                                this.msgKey(p, "quest.cannot-withdraw", new String[0]);
                            }
                            return;
                        }
                        if (q.requiredTeamSize > 1) {
                            if (q.state == QuestState.AVAILABLE) {
                                if (q.teamMembers.contains(u)) {
                                    q.teamMembers.remove(u);
                                    this.playerActiveQuest.remove(u);
                                    this.msgKey(p, "quest.team-left-recruiting", new String[0]);
                                    this.clickSound(p);
                                    this.openQuestBoardGUI(p);
                                } else if (q.teamMembers.size() < q.requiredTeamSize) {
                                    q.teamMembers.add(u);
                                    this.playerActiveQuest.put(u, q.id);
                                    this.msgKey(p, "quest.team-joined", new String[]{"world", q.worldName, "x", String.valueOf((long)q.x), "y", String.valueOf((long)q.y), "z", String.valueOf((long)q.z), "joined", String.valueOf(q.teamMembers.size()), "required", String.valueOf(q.requiredTeamSize)});
                                    posterOnline = Bukkit.getPlayer((UUID)q.posterId);
                                    if (posterOnline != null) {
                                        this.msgKey(posterOnline, "quest.team-join-notice", new String[]{"player", p.getName(), "joined", String.valueOf(q.teamMembers.size()), "required", String.valueOf(q.requiredTeamSize)});
                                    }
                                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                                    if (q.teamMembers.size() == q.requiredTeamSize) {
                                        q.state = QuestState.IN_PROGRESS;
                                        for (UUID member : q.teamMembers) {
                                            memberOnline = Bukkit.getPlayer((UUID)member);
                                            if (memberOnline == null) continue;
                                            this.msgKey(memberOnline, "quest.team-staffed", new String[0]);
                                        }
                                    }
                                    this.openQuestBoardGUI(p);
                                } else {
                                    this.msgKey(p, "quest.not-acceptable", new String[0]);
                                }
                            } else if (q.state == QuestState.IN_PROGRESS && q.teamMembers.contains(u)) {
                                q.teamMembers.remove(u);
                                this.playerActiveQuest.remove(u);
                                q.teamArrived.clear();
                                q.state = QuestState.AVAILABLE;
                                for (UUID member : q.teamMembers) {
                                    memberOnline = Bukkit.getPlayer((UUID)member);
                                    if (memberOnline == null) continue;
                                    this.msgKey(memberOnline, "quest.team-abandoned", new String[0]);
                                }
                                this.msgKey(p, "quest.abandoned", new String[0]);
                                this.clickSound(p);
                                this.openQuestBoardGUI(p);
                            } else {
                                this.msgKey(p, "quest.not-acceptable", new String[0]);
                            }
                            return;
                        }
                        if (q.state == QuestState.AVAILABLE) {
                            q.state = QuestState.IN_PROGRESS;
                            q.acceptedBy = u;
                            this.playerActiveQuest.put(u, q.id);
                            this.msgKey(p, "quest.accepted", new String[]{"world", q.worldName, "x", String.valueOf((long)q.x), "y", String.valueOf((long)q.y), "z", String.valueOf((long)q.z)});
                            posterOnline = Bukkit.getPlayer((UUID)q.posterId);
                            if (posterOnline != null) {
                                this.msgKey(posterOnline, "quest.accepted-notice", new String[]{"player", p.getName()});
                            }
                            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                            this.openQuestBoardGUI(p);
                        } else if (u.equals(q.acceptedBy)) {
                            q.state = QuestState.AVAILABLE;
                            q.acceptedBy = null;
                            this.playerActiveQuest.remove(u);
                            this.msgKey(p, "quest.abandoned", new String[0]);
                            this.clickSound(p);
                            this.openQuestBoardGUI(p);
                        } else {
                            this.msgKey(p, "quest.not-acceptable", new String[0]);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tAdminMain)) {
                        if (!p.hasPermission("bank.admin")) {
                            p.closeInventory();
                            return;
                        }
                        if (mat == Material.PLAYER_HEAD) {
                            this.openAdminPlayerListGUI(p);
                        } else if (mat == Material.COMMAND_BLOCK) {
                            this.openAdminServerGUI(p);
                        } else if (mat == Material.BARRIER) {
                            p.closeInventory();
                            return;
                        }
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tAdminPlayerList)) {
                        if (!p.hasPermission("bank.admin")) {
                            p.closeInventory();
                            return;
                        }
                        if (mat == Material.IRON_DOOR) {
                            this.openAdminMainGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.NAME_TAG) {
                            this.awaitingChatInput.put(u, "admin_search_player");
                            p.closeInventory();
                            this.msgKey(p, "admin.search-player-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.PLAYER_HEAD && item.hasItemMeta() && (idStr = (String)item.getItemMeta().getPersistentDataContainer().get(this.adminTargetKey, PersistentDataType.STRING)) != null) {
                            this.openAdminPlayerDetailGUI(p, UUID.fromString(idStr));
                            this.clickSound(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tAdminPlayerDetail)) {
                        if (!p.hasPermission("bank.admin")) {
                            p.closeInventory();
                            return;
                        }
                        adminTargetId = this.adminViewingTarget.get(u);
                        if (adminTargetId == null) {
                            this.openAdminMainGUI(p);
                            return;
                        }
                        if (mat == Material.IRON_DOOR) {
                            this.openAdminPlayerListGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.GOLD_INGOT) {
                            this.awaitingChatInput.put(u, "admin_give:" + String.valueOf(adminTargetId));
                            p.closeInventory();
                            this.msgKey(p, "admin.give-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.REDSTONE) {
                            this.awaitingChatInput.put(u, "admin_take:" + String.valueOf(adminTargetId));
                            p.closeInventory();
                            this.msgKey(p, "admin.take-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.EMERALD) {
                            this.awaitingChatInput.put(u, "admin_setcredit:" + String.valueOf(adminTargetId));
                            p.closeInventory();
                            this.msgKey(p, "admin.setcredit-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.BARRIER) {
                            confirmKey = "admin_reset:" + String.valueOf(adminTargetId);
                            confirmedAt = this.pendingConfirmationTime.getOrDefault(u, 0L);
                            v1 = expired = System.currentTimeMillis() - confirmedAt > 15000L;
                            if (!confirmKey.equals(this.pendingConfirmation.get(u)) || expired) {
                                this.pendingConfirmation.put(u, confirmKey);
                                this.pendingConfirmationTime.put(u, System.currentTimeMillis());
                                this.msgKey(p, "admin.reset-confirm", new String[0]);
                                this.errorSound(p);
                                return;
                            }
                            this.pendingConfirmation.remove(u);
                            this.pendingConfirmationTime.remove(u);
                            targetName = Bukkit.getOfflinePlayer((UUID)adminTargetId).getName();
                            if (targetName == null) {
                                targetName = adminTargetId.toString();
                            }
                            this.resetPlayerEconomyData(adminTargetId);
                            this.msgKey(p, "admin.reset-success", new String[]{"player", targetName});
                            this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + targetName + "** \u306e\u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u30ea\u30bb\u30c3\u30c8\u3057\u307e\u3057\u305f\u3002(GUI)");
                            this.openAdminPlayerDetailGUI(p, adminTargetId);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tAdminServer)) {
                        if (!p.hasPermission("bank.admin")) {
                            p.closeInventory();
                            return;
                        }
                        if (mat == Material.IRON_DOOR) {
                            this.openAdminMainGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.GOLD_BLOCK) {
                            this.fireEconomyEvent("random");
                            this.msgKey(p, "event.random-triggered", new String[0]);
                        } else if (mat == Material.EMERALD_BLOCK) {
                            this.fireEconomyEvent("\u9ec4\u91d1\u30e9\u30c3\u30b7\u30e5");
                            this.msgKey(p, "event.boom-triggered", new String[0]);
                        } else if (mat == Material.NETHERITE_INGOT) {
                            this.fireEconomyEvent("\u624b\u6570\u6599\u9ad8\u9a30");
                            this.msgKey(p, "event.tax-triggered", new String[0]);
                        } else if (mat == Material.SUNFLOWER) {
                            this.fireEconomyEvent("\u30dc\u30fc\u30ca\u30b9\u652f\u7d66\u30c7\u30fc");
                            this.msgKey(p, "event.bonus-triggered", new String[0]);
                        } else if (mat == Material.REDSTONE_BLOCK) {
                            this.fireEconomyEvent("\u51ac\u306e\u6642\u4ee3");
                            this.msgKey(p, "event.recession-triggered", new String[0]);
                        } else {
                            if (mat == Material.KNOWLEDGE_BOOK) {
                                issues = this.runSelfCheck(false);
                                p.closeInventory();
                                if (issues.isEmpty()) {
                                    this.msgKey(p, "admin.selfcheck-ok", new String[0]);
                                } else {
                                    this.msgKey(p, "admin.selfcheck-issues", new String[]{"count", String.valueOf(issues.size()), "note", "\uff08/meco admin selfcheck fix \u3067\u81ea\u52d5\u4fee\u5fa9\u3067\u304d\u307e\u3059\uff09"});
                                    shown = 0;
                                    for (String issue : issues) {
                                        if (shown++ >= 30) {
                                            this.msgKey(p, "admin.selfcheck-more", new String[]{"count", String.valueOf(issues.size() - 30)});
                                            break;
                                        }
                                        this.msgKey(p, "admin.selfcheck-issue-line", new String[]{"issue", issue});
                                    }
                                }
                                return;
                            }
                            if (mat == Material.HOPPER) {
                                this.reloadConfig();
                                this.loadConfigValues();
                                this.loadMessages();
                                this.msgKey(p, "admin.reloaded-alt", new String[0]);
                            } else if (mat == Material.CHEST) {
                                this.saveData();
                                this.msgKey(p, "admin.saved", new String[0]);
                            } else if (mat == Material.BOOK) {
                                this.openConfigEditorGUI(p, 0);
                                return;
                            }
                        }
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tConfigEditor)) {
                        if (!p.hasPermission("bank.admin")) {
                            p.closeInventory();
                            return;
                        }
                        rawSlot = e.getRawSlot();
                        if (mat == Material.IRON_DOOR) {
                            this.openAdminServerGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (rawSlot == 51 && mat == Material.ARROW) {
                            this.openConfigEditorGUI(p, this.configEditorPage.getOrDefault(u, 0) - 1);
                            this.clickSound(p);
                            return;
                        }
                        if (rawSlot == 52 && mat == Material.ARROW) {
                            this.openConfigEditorGUI(p, this.configEditorPage.getOrDefault(u, 0) + 1);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.PAPER) {
                            meta = item.getItemMeta();
                            v2 = key = meta != null ? (String)meta.getPersistentDataContainer().get(this.configKeyTag, PersistentDataType.STRING) : null;
                            if (key != null) {
                                this.awaitingChatInput.put(u, "config_edit:" + key);
                                this.msgKey(p, "config.edit-prompt", new String[]{"key", key});
                                p.closeInventory();
                            }
                        }
                        return;
                    }
                    if (t.equals((Object)this.tLeaderboard)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openMyPageGUI(p);
                            this.clickSound(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tCollateral)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openBankHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.ANVIL && !this.collateralItem.containsKey(u)) {
                            this.openCollateralSelectGUI(p);
                            return;
                        }
                        if (mat == Material.EMERALD && !this.collateralItem.containsKey(u)) {
                            selected = this.collateralSelection.remove(u);
                            value = this.evaluateItemValue(selected);
                            if (value <= 0.0) {
                                this.msgKey(p, "collateral.select-item", new String[0]);
                                this.errorSound(p);
                            } else {
                                loan = value * this.cfgCollateralLtv;
                                stored = selected.clone();
                                this.collateralItem.put(u, stored);
                                this.collateralLoanAmount.put(u, loan);
                                this.collateralDueTime.put(u, System.currentTimeMillis() + this.cfgCollateralDurationMs);
                                MinecraftBank.econ.depositPlayer((OfflinePlayer)p, loan);
                                this.msgKey(p, "collateral.borrowed", new String[]{"item", stored.getType().name(), "amount", String.valueOf((long)loan), "minutes", String.valueOf(this.cfgCollateralDurationMs / 60000L)});
                                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                                this.addLog(u, "\u62c5\u4fdd\u878d\u8cc7 +" + (long)loan + "\u5186 (" + stored.getType().name() + ")");
                                this.sendDiscordWebhook("\ud83c\udffa **" + p.getName() + "** \u304c " + stored.getType().name() + " \u3092\u62c5\u4fdd\u306b " + (long)loan + "\u5186 \u3092\u501f\u308a\u307e\u3057\u305f\u3002");
                            }
                        } else if (mat == Material.GOLD_BLOCK && this.collateralItem.containsKey(u)) {
                            loan = this.collateralLoanAmount.getOrDefault(u, 0.0);
                            totalRepay = loan * (1.0 + this.cfgCollateralInterest / 100.0);
                            if (pocket >= totalRepay) {
                                MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, totalRepay);
                                returned = this.collateralItem.remove(u);
                                this.collateralLoanAmount.remove(u);
                                this.collateralDueTime.remove(u);
                                this.collateralLender.remove(u);
                                if (returned != null) {
                                    leftover = p.getInventory().addItem(new ItemStack[]{returned});
                                    for (ItemStack over : leftover.values()) {
                                        p.getWorld().dropItem(p.getLocation(), over);
                                    }
                                }
                                this.msgKey(p, "collateral.repaid", new String[0]);
                                this.addScore(u, 10);
                                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                                this.addLog(u, "\u62c5\u4fdd\u878d\u8cc7\u5b8c\u6e08 -" + this.fmtCur(totalRepay));
                                this.sendDiscordWebhook("\ud83d\udd13 **" + p.getName() + "** \u304c\u62c5\u4fdd\u878d\u8cc7\u3092\u5b8c\u6e08\u3057\u307e\u3057\u305f\u3002\u8fd4\u6e08\u984d: " + this.fmtCur(totalRepay));
                            } else {
                                this.msgKey(p, "collateral.repay-insufficient", new String[]{"amount", String.valueOf((long)totalRepay)});
                                this.errorSound(p);
                            }
                        }
                        this.openCollateralGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tHub)) {
                        if (mat == Material.GOLD_INGOT) {
                            this.openBankHubGUI(p);
                        } else if (mat == Material.EMERALD) {
                            this.openMarketHubGUI(p);
                        } else if (mat == Material.TARGET) {
                            this.openTodoHubGUI(p);
                        } else if (mat == Material.PLAYER_HEAD) {
                            this.openMyPageGUI(p);
                        } else if (mat == Material.CRAFTING_TABLE) {
                            this.openMainGUI(p);
                        } else if (mat == Material.COMMAND_BLOCK && p.hasPermission("bank.admin")) {
                            this.openAdminMainGUI(p);
                        } else {
                            return;
                        }
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tMarketHub)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.NETHER_STAR) {
                            this.openWorldStockGUI(p);
                        } else if (mat == Material.HOPPER) {
                            this.openResourceShopGUI(p);
                        } else if (mat == Material.LEATHER_HORSE_ARMOR) {
                            this.ensureMerchantDealsCurrent();
                            this.msgKey(p, "merchant.location-hint", new String[]{"world", this.merchantWorldName, "x", String.valueOf((int)this.merchantX), "y", String.valueOf((int)this.merchantY), "z", String.valueOf((int)this.merchantZ)});
                        } else if (mat == Material.ITEM_FRAME) {
                            this.openAuctionGUI(p);
                        } else if (mat == Material.DIAMOND) {
                            this.openVipLoungeGUI(p);
                        } else {
                            return;
                        }
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tTodoHub)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.OAK_SIGN) {
                            this.openQuestBoardGUI(p);
                        } else if (mat == Material.FIREWORK_STAR) {
                            this.openLotteryGUI(p);
                        } else if (mat == Material.BARREL) {
                            this.openStorageRentGUI(p);
                        } else if (mat == Material.PLAYER_HEAD) {
                            this.openTradeSelectGUI(p);
                        } else if (mat == Material.ENDER_CHEST) {
                            this.openGroupListGUI(p);
                        } else if (mat == Material.DIAMOND) {
                            this.openFundListGUI(p);
                        } else {
                            return;
                        }
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tTradeSelect)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openTodoHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.PLAYER_HEAD && item.hasItemMeta() && (idStr = (String)item.getItemMeta().getPersistentDataContainer().get(this.tradeTargetKey, PersistentDataType.STRING)) != null) {
                            target = Bukkit.getPlayer((UUID)UUID.fromString(idStr));
                            if (target == null) {
                                this.msgKey(p, "common.target-offline", new String[0]);
                                this.errorSound(p);
                            } else {
                                p.closeInventory();
                                this.sendTradeRequest(p, target);
                                this.clickSound(p);
                            }
                        }
                        return;
                    }
                    if (t.equals((Object)this.tGroupList)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openTodoHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.WRITABLE_BOOK) {
                            this.awaitingChatInput.put(u, "group_create_name");
                            p.closeInventory();
                            this.msgKey(p, "group.create-name-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.CHEST && item.hasItemMeta() && (idStr = (String)item.getItemMeta().getPersistentDataContainer().get(this.groupAccountKey, PersistentDataType.STRING)) != null) {
                            this.openGroupAccountGUI(p, UUID.fromString(idStr));
                            this.clickSound(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tGroupAccount)) {
                        accountId = this.groupAccountViewing.get(u);
                        v3 = acc = accountId != null ? this.groupAccounts.get(accountId) : null;
                        if (acc == null || !this.isGroupAccountMember(acc, u)) {
                            this.openGroupListGUI(p);
                            return;
                        }
                        if (mat == Material.IRON_DOOR) {
                            this.openGroupListGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.EMERALD) {
                            this.awaitingChatInput.put(u, "group_deposit:" + String.valueOf(acc.id));
                            p.closeInventory();
                            this.msgKey(p, "group.deposit-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.GOLD_INGOT) {
                            this.awaitingChatInput.put(u, "group_withdraw:" + String.valueOf(acc.id));
                            p.closeInventory();
                            this.msgKey(p, "group.withdraw-prompt", new String[0]);
                            return;
                        }
                        if (mat == Material.PLAYER_HEAD && acc.owner.equals(u)) {
                            this.openGroupMembersGUI(p, acc.id);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.BARRIER && acc.owner.equals(u)) {
                            confirmKey = "group_disband:" + String.valueOf(acc.id);
                            confirmedAt = this.pendingConfirmationTime.getOrDefault(u, 0L);
                            v4 = expired = System.currentTimeMillis() - confirmedAt > 30000L;
                            if (!confirmKey.equals(this.pendingConfirmation.get(u)) || expired) {
                                this.pendingConfirmation.put(u, confirmKey);
                                this.pendingConfirmationTime.put(u, System.currentTimeMillis());
                                this.msgKey(p, "group.disband-confirm", new String[0]);
                                this.errorSound(p);
                                return;
                            }
                            this.pendingConfirmation.remove(u);
                            this.pendingConfirmationTime.remove(u);
                            this.groupAccountViewing.remove(u);
                            this.disbandGroupAccount(p, acc);
                            this.openGroupListGUI(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tGroupMembers)) {
                        accountId = this.groupAccountViewing.get(u);
                        v5 = acc = accountId != null ? this.groupAccounts.get(accountId) : null;
                        if (acc == null || !acc.owner.equals(u)) {
                            this.openGroupListGUI(p);
                            return;
                        }
                        if (mat == Material.IRON_DOOR) {
                            this.openGroupAccountGUI(p, acc.id);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.PLAYER_HEAD && item.hasItemMeta()) {
                            targetStr = (String)item.getItemMeta().getPersistentDataContainer().get(this.groupTargetKey, PersistentDataType.STRING);
                            if (targetStr == null) {
                                return;
                            }
                            tu = UUID.fromString(targetStr);
                            if (acc.members.contains(tu)) {
                                acc.members.remove(tu);
                                pset = this.playerGroupAccounts.get(tu);
                                if (pset != null) {
                                    pset.remove(acc.id);
                                    if (pset.isEmpty()) {
                                        this.playerGroupAccounts.remove(tu);
                                    }
                                }
                                tn = Bukkit.getOfflinePlayer((UUID)tu).getName();
                                this.msgKey(p, "group.kicked", new String[]{"player", tn != null ? tn : tu.toString()});
                                to = Bukkit.getPlayer((UUID)tu);
                                if (to != null) {
                                    this.msgKey(to, "group.kicked-notice", new String[]{"group", acc.name});
                                }
                                this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u304b\u3089 " + (tn != null ? tn : tu.toString()) + " \u3092\u8ffd\u653e");
                            } else {
                                if (1 + acc.members.size() >= this.cfgGroupAccountMaxMembers) {
                                    this.msgKey(p, "group.full", new String[0]);
                                    this.errorSound(p);
                                    return;
                                }
                                to = Bukkit.getPlayer((UUID)tu);
                                if (to == null) {
                                    this.msgKey(p, "common.target-offline", new String[0]);
                                    return;
                                }
                                acc.members.add(tu);
                                this.playerGroupAccounts.computeIfAbsent(tu, (Function<UUID, HashSet>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$onInventoryClick$4(java.util.UUID ), (Ljava/util/UUID;)Ljava/util/HashSet;)()).add(acc.id);
                                this.msgKey(p, "group.invited", new String[]{"player", to.getName()});
                                this.msgKey(to, "group.invite-received", new String[]{"group", acc.name, "player", p.getName()});
                                this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u306b " + to.getName() + " \u3092\u62db\u5f85");
                            }
                            this.clickSound(p);
                            this.openGroupMembersGUI(p, acc.id);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tFundList)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openTodoHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if ((mat == Material.EMERALD_BLOCK || mat == Material.EMERALD) && item.hasItemMeta()) {
                            idStr = (String)item.getItemMeta().getPersistentDataContainer().get(this.investmentFundKey, PersistentDataType.STRING);
                            if (idStr == null) {
                                return;
                            }
                            fund = this.investmentFunds.get(UUID.fromString(idStr));
                            if (fund == null) {
                                return;
                            }
                            if (fund.manager.equals(u)) {
                                this.openFundStockGUI(p, fund.id);
                            } else {
                                this.openFundInfoGUI(p, fund.id);
                            }
                            this.clickSound(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tFundInfo)) {
                        fundId = this.fundViewing.get(u);
                        v6 = fund = fundId != null ? this.investmentFunds.get(fundId) : null;
                        if (fund == null || !this.isFundMember(fund, u) || fund.manager.equals(u)) {
                            this.openFundListGUI(p);
                            return;
                        }
                        if (mat == Material.IRON_DOOR) {
                            this.fundViewing.remove(u);
                            this.openFundListGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.GOLD_INGOT) {
                            confirmKey = "fund_redeem:" + String.valueOf(fund.id);
                            confirmedAt = this.pendingConfirmationTime.getOrDefault(u, 0L);
                            v7 = expired = System.currentTimeMillis() - confirmedAt > 30000L;
                            if (!confirmKey.equals(this.pendingConfirmation.get(u)) || expired) {
                                this.pendingConfirmation.put(u, confirmKey);
                                this.pendingConfirmationTime.put(u, System.currentTimeMillis());
                                this.msgKey(p, "fund.redeem-confirm", new String[0]);
                                this.errorSound(p);
                                return;
                            }
                            this.pendingConfirmation.remove(u);
                            this.pendingConfirmationTime.remove(u);
                            this.fundViewing.remove(u);
                            this.redeemFundStake(p, fund);
                            this.openFundListGUI(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tFundStock)) {
                        fundId = this.fundViewing.get(u);
                        v8 = fund = fundId != null ? this.investmentFunds.get(fundId) : null;
                        if (fund == null || !fund.manager.equals(u)) {
                            return;
                        }
                        if (mat == Material.IRON_DOOR) {
                            this.fundViewing.remove(u);
                            this.openFundListGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.COMPASS) {
                            this.awaitingChatInput.put(u, "fund_stock_search:" + String.valueOf(fund.id));
                            p.closeInventory();
                            this.msgKey(p, "worldstock.search-prompt", new String[0]);
                            return;
                        }
                        if (!item.hasItemMeta()) {
                            return;
                        }
                        symbol = (String)item.getItemMeta().getPersistentDataContainer().get(this.worldStockKey, PersistentDataType.STRING);
                        if (symbol == null) {
                            return;
                        }
                        this.openFundStockDetailGUI(p, fund.id, symbol);
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tFundStockDetail)) {
                        fundId = this.fundViewing.get(u);
                        v9 = fund = fundId != null ? this.investmentFunds.get(fundId) : null;
                        if (fund == null || !fund.manager.equals(u)) {
                            return;
                        }
                        this.handleWorldStockDetailClick(p, u, mat, item, fund);
                        return;
                    }
                    if (t.equals((Object)this.tBankHub)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.CHEST) {
                            this.openPersonalGUI(p);
                        } else if (mat == Material.EMERALD) {
                            this.openMarketGUI(p);
                        } else if (mat == Material.GOLD_INGOT) {
                            this.openRepayGUI(p);
                        } else if (mat == Material.NETHER_STAR) {
                            this.openBankerGUI(p);
                        } else if (mat == Material.ENDER_EYE) {
                            this.openEstablishGUI(p);
                        } else if (mat == Material.DIAMOND) {
                            this.openGovLoanGUI(p);
                        } else if (mat == Material.GOLD_BLOCK) {
                            this.openFixedDepoGUI(p);
                        } else if (mat == Material.ANVIL) {
                            this.openCollateralGUI(p);
                        } else if (mat == Material.GOLDEN_APPLE) {
                            this.openInsuranceGUI(p);
                        } else {
                            return;
                        }
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tMyPage)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openHubGUI(p);
                            this.clickSound(p);
                            return;
                        }
                        if (mat == Material.EMERALD_BLOCK) {
                            this.openCreditGUI(p);
                        } else if (mat == Material.FIREWORK_ROCKET) {
                            this.openAchievementGUI(p);
                        } else if (mat == Material.PLAYER_HEAD) {
                            this.openLeaderboardGUI(p);
                        } else if (mat == Material.WRITTEN_BOOK) {
                            logs = this.transactionLogs.get(u);
                            if (logs == null || logs.isEmpty()) {
                                this.msgKey(p, "log.empty", new String[0]);
                            } else {
                                this.msgKey(p, "log.header", new String[0]);
                                shown = 0;
                                for (String log : logs) {
                                    if (shown++ >= 15) break;
                                    this.msgKey(p, "log.entry", new String[]{"entry", log});
                                }
                            }
                            this.openMyPageGUI(p);
                        } else if (mat == Material.BELL || mat == Material.NOTE_BLOCK) {
                            if (this.newsBroadcastOff.contains(u)) {
                                this.newsBroadcastOff.remove(u);
                                this.msgKey(p, "news.enabled", new String[0]);
                            } else {
                                this.newsBroadcastOff.add(u);
                                this.msgKey(p, "news.disabled", new String[0]);
                            }
                            this.openMyPageGUI(p);
                        } else if (mat == Material.GOLD_BLOCK) {
                            this.msgKey(p, "treasury.balance-gui", new String[]{"amount", String.valueOf((long)this.treasury)});
                            this.msgKey(p, "treasury.balance-note", new String[0]);
                            this.openMyPageGUI(p);
                        } else {
                            if (mat == Material.KNOWLEDGE_BOOK) {
                                this.openTutorialGUI(p);
                                return;
                            }
                            if (mat == Material.BOOK) {
                                remain = this.hubItemReissueCooldown.getOrDefault(u, 0L) - System.currentTimeMillis();
                                if (remain > 0L) {
                                    this.msgKey(p, "item.reissue-cooldown", new String[]{"seconds", String.valueOf(remain / 1000L + 1L)});
                                    this.errorSound(p);
                                } else {
                                    leftover = p.getInventory().addItem(new ItemStack[]{this.createHubItem()});
                                    for (ItemStack over : leftover.values()) {
                                        p.getWorld().dropItemNaturally(p.getLocation(), over);
                                    }
                                    this.hubItemReissueCooldown.put(u, System.currentTimeMillis() + this.cfgHubItemReissueCooldownMs);
                                    this.msgKey(p, "item.reissued", new String[0]);
                                    p.playSound(p.getLocation(), Sound.ENTITY_ITEM_PICKUP, 1.0f, 1.0f);
                                }
                                this.openMyPageGUI(p);
                            } else {
                                if (mat == Material.GLOWSTONE_DUST || mat == Material.SUNFLOWER) {
                                    this.awaitingChatInput.put(u, "treasury_donate");
                                    this.msgKey(p, "donate.treasury-prompt", new String[0]);
                                    p.closeInventory();
                                    return;
                                }
                                if (mat == Material.CLOCK) {
                                    this.openInstallmentListGUI(p);
                                    this.clickSound(p);
                                    return;
                                }
                                if (mat == Material.ENDER_CHEST) {
                                    this.deliverPendingAuctionItems(p);
                                    this.deliverAuctionOfflineNotices(p);
                                    this.deliverLoanOfflineNotices(p);
                                    this.openMyPageGUI(p);
                                } else if (mat == Material.MAP) {
                                    if (!this.cfgWebDashboardEnabled) {
                                        this.msgKey(p, "webpage.disabled", new String[0]);
                                        this.errorSound(p);
                                    } else {
                                        token = this.dashboardTokens.computeIfAbsent(u, (Function<UUID, String>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Ljava/lang/Object;, lambda$onInventoryClick$5(java.util.UUID ), (Ljava/util/UUID;)Ljava/lang/String;)());
                                        base = this.cfgWebDashboardPublicUrl != null && this.cfgWebDashboardPublicUrl.isBlank() == false ? this.cfgWebDashboardPublicUrl.replaceAll("/$", "") : "http://<\u30b5\u30fc\u30d0\u30fc\u306eIP>:" + this.cfgWebDashboardPort;
                                        this.msgKey(p, "webpage.link", new String[]{"url", base + "/me.html?token=" + token});
                                    }
                                } else {
                                    if (mat == Material.TRIPWIRE_HOOK) {
                                        this.awaitingChatInput.put(u, "webpage_password");
                                        this.msgKey(p, "webpage.password-prompt", new String[0]);
                                        p.closeInventory();
                                        return;
                                    }
                                    return;
                                }
                            }
                        }
                        this.clickSound(p);
                        return;
                    }
                    if (t.equals((Object)this.tInstallmentList)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openMyPageGUI(p);
                            this.clickSound(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tTutorial)) {
                        if (mat == Material.LIME_DYE || mat == Material.IRON_DOOR) {
                            this.tutorialSeen.add(u);
                            p.closeInventory();
                            if (mat == Material.LIME_DYE) {
                                this.msgKey(p, "tutorial.completed", new String[0]);
                            }
                            this.clickSound(p);
                        }
                        return;
                    }
                    if (t.equals((Object)this.tAuctionSelect)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openAuctionGUI(p);
                            return;
                        }
                        if (e.getRawSlot() < 45) {
                            selected = item.clone();
                            this.auctionListingDraft.put(u, selected);
                            p.getInventory().setItem(e.getRawSlot(), null);
                            this.awaitingChatInput.put(u, "auction_list_price");
                            this.msgKey(p, "auction.list-price-prompt", new String[0]);
                            p.closeInventory();
                        }
                        return;
                    }
                    if (t.equals((Object)this.tCollateralSelect)) {
                        if (mat == Material.IRON_DOOR) {
                            this.openCollateralGUI(p);
                            return;
                        }
                        if (e.getRawSlot() < 45) {
                            selected = item.clone();
                            value = this.evaluateItemValue(selected);
                            if (value <= 0.0) {
                                this.msgKey(p, "collateral.item-not-valid", new String[0]);
                                return;
                            }
                            this.collateralSelection.put(u, selected);
                            p.getInventory().setItem(e.getRawSlot(), null);
                            this.openCollateralGUI(p);
                        }
                        return;
                    }
                    if (!t.equals((Object)this.tAuction)) break block550;
                    rawSlot = e.getRawSlot();
                    if (mat == Material.IRON_DOOR) {
                        this.openHubGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (rawSlot == 45) {
                        mode = (this.auctionSortMode.getOrDefault(u, 0) + 1) % 3;
                        this.auctionSortMode.put(u, mode);
                        this.openAuctionGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (rawSlot == 46) {
                        this.awaitingChatInput.put(u, "auction_search");
                        this.msgKey(p, "auction.search-prompt", new String[0]);
                        p.closeInventory();
                        return;
                    }
                    if (rawSlot == 47) {
                        if (!this.auctionMyListingsOnlyFilter.add(u)) {
                            this.auctionMyListingsOnlyFilter.remove(u);
                        }
                        this.openAuctionGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (rawSlot == 48) {
                        this.openAuctionRankingGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (rawSlot == 50 && this.auctionSearchQuery.containsKey(u)) {
                        this.auctionSearchQuery.remove(u);
                        this.openAuctionGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (rawSlot == 51 && this.auctionPage.getOrDefault(u, 0) > 0) {
                        this.auctionPage.put(u, this.auctionPage.get(u) - 1);
                        this.openAuctionGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (rawSlot == 52 && mat == Material.ARROW) {
                        this.auctionPage.merge(u, 1, (BiFunction<Integer, Integer, Integer>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;, sum(int int ), (Ljava/lang/Integer;Ljava/lang/Integer;)Ljava/lang/Integer;)());
                        this.openAuctionGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (mat == Material.EMERALD) {
                        myListings = this.auctionSeller.values().stream().filter((Predicate<UUID>)LambdaMetafactory.metafactory(null, null, null, (Ljava/lang/Object;)Z, lambda$onInventoryClick$6(java.util.UUID java.util.UUID ), (Ljava/util/UUID;)Z)((UUID)u)).count();
                        if (myListings < (long)this.cfgAuctionMaxListingsPerPlayer) {
                            this.openAuctionSelectGUI(p);
                            return;
                        }
                        this.msgKey(p, "auction.listing-limit", new String[]{"count", String.valueOf(this.cfgAuctionMaxListingsPerPlayer)});
                        this.errorSound(p);
                        this.openAuctionGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    clickedMeta = item.getItemMeta();
                    if (clickedMeta == null || (idStr = (String)clickedMeta.getPersistentDataContainer().get(this.auctionIdKey, PersistentDataType.STRING)) == null) break block551;
                    auctionId = UUID.fromString(idStr);
                    seller = this.auctionSeller.get(auctionId);
                    if (seller != null) break block552;
                    this.msgKey(p, "auction.listing-ended", new String[0]);
                    this.errorSound(p);
                    break block551;
                }
                if (!seller.equals(u)) ** GOTO lbl1231
                if (this.auctionBidder.containsKey(auctionId)) {
                    this.msgKey(p, "auction.cancel-has-bid", new String[0]);
                    this.errorSound(p);
                } else {
                    this.openAuctionCancelConfirmGUI(p, auctionId);
                    this.clickSound(p);
                    return;
lbl1231:
                    // 1 sources

                    this.awaitingChatInput.put(u, "auction_bid:" + idStr);
                    currentBid = this.auctionBid.getOrDefault(auctionId, 0.0);
                    hasBidder = this.auctionBidder.containsKey(auctionId);
                    minNext = hasBidder != false ? currentBid + this.cfgAuctionMinIncrement : currentBid;
                    this.msgKey(p, "auction.bid-prompt", new String[]{"amount", String.valueOf((long)minNext)});
                    p.closeInventory();
                    return;
                }
            }
            this.openAuctionGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tAuctionCancelConfirm)) {
            if (mat == Material.IRON_DOOR || mat == Material.BARRIER) {
                this.openAuctionGUI(p);
                this.clickSound(p);
                return;
            }
            if (mat == Material.EMERALD) {
                confirmMeta = item.getItemMeta();
                v10 = idStr = confirmMeta != null ? (String)confirmMeta.getPersistentDataContainer().get(this.auctionIdKey, PersistentDataType.STRING) : null;
                if (idStr == null) {
                    this.openAuctionGUI(p);
                    return;
                }
                auctionId = UUID.fromString(idStr);
                seller = this.auctionSeller.get(auctionId);
                auctionedItem = this.auctionItem.get(auctionId);
                if (seller == null || auctionedItem == null) {
                    this.msgKey(p, "auction.listing-ended", new String[0]);
                    this.errorSound(p);
                } else if (!seller.equals(u) || this.auctionBidder.containsKey(auctionId)) {
                    this.msgKey(p, "auction.cancel-has-bid", new String[0]);
                    this.errorSound(p);
                } else {
                    this.addPendingItem(seller, auctionedItem);
                    this.clearAuctionEntry(auctionId);
                    this.addLog(seller, "\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u51fa\u54c1\u3092\u81ea\u4e3b\u53d6\u6d88: " + auctionedItem.getType().name());
                    this.msgKey(p, "auction.cancel-success", new String[]{"item", auctionedItem.getType().name()});
                    this.clickSound(p);
                }
                this.openAuctionGUI(p);
                return;
            }
            return;
        }
        if (t.equals((Object)this.tAuctionRanking)) {
            if (mat == Material.IRON_DOOR) {
                this.openAuctionGUI(p);
                this.clickSound(p);
            }
            return;
        }
        if (t.equals((Object)this.tInsurance)) {
            if (mat == Material.IRON_DOOR) {
                this.openBankHubGUI(p);
                this.clickSound(p);
                return;
            }
            if (mat == Material.GOLDEN_APPLE) {
                expiry = this.insuranceExpiry.getOrDefault(u, 0L);
                if (expiry > System.currentTimeMillis()) {
                    this.msgKey(p, "insurance.already-active", new String[0]);
                    this.errorSound(p);
                } else if (pocket >= this.cfgInsurancePremium) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, this.cfgInsurancePremium);
                    this.insuranceExpiry.put(u, System.currentTimeMillis() + this.cfgInsuranceDurationMs);
                    this.msgKey(p, "insurance.joined", new String[]{"minutes", String.valueOf(this.cfgInsuranceDurationMs / 60000L)});
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "\u4fdd\u967a\u52a0\u5165 -" + this.fmtCur(this.cfgInsurancePremium));
                    this.sendDiscordWebhook("\ud83d\udee1\ufe0f **" + p.getName() + "** \u304c\u751f\u547d\u4fdd\u967a\u306b\u52a0\u5165\u3057\u307e\u3057\u305f\u3002\u4fdd\u967a\u6599: " + this.fmtCur(this.cfgInsurancePremium));
                } else {
                    this.msgKey(p, "insurance.premium-insufficient", new String[]{"amount", String.valueOf((long)this.cfgInsurancePremium)});
                    this.errorSound(p);
                }
            }
            this.openInsuranceGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tAchievement)) {
            if (mat == Material.IRON_DOOR) {
                this.openMyPageGUI(p);
                this.clickSound(p);
            }
            return;
        }
        if (t.equals((Object)this.tPersonal)) {
            bank = this.personalBank.getOrDefault(u, 0.0);
            if (mat == Material.LIME_DYE && pocket >= this.cfgDepositStep) {
                MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, this.cfgDepositStep);
                this.personalBank.put(u, bank + this.cfgDepositStep);
                this.msgKey(p, "personal.deposit-step", new String[]{"amount", String.valueOf((long)this.cfgDepositStep)});
                this.sendDiscordWebhook("\ud83d\udcb0 **" + p.getName() + "** \u304c\u500b\u4eba\u53e3\u5ea7\u3078 " + (long)this.cfgDepositStep + "\u5186 \u9810\u91d1\u3057\u307e\u3057\u305f\u3002");
            } else if (mat == Material.LIME_GLAZED_TERRACOTTA && pocket > 0.0) {
                MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, pocket);
                this.personalBank.put(u, bank + pocket);
                this.msgKey(p, "personal.deposit-full", new String[0]);
                this.sendDiscordWebhook("\ud83d\udcb0 **" + p.getName() + "** \u304c\u500b\u4eba\u53e3\u5ea7\u3078\u5168\u984d\u9810\u91d1\u3057\u307e\u3057\u305f\u3002\u91d1\u984d: " + this.fmtCur(pocket));
            } else if (mat == Material.RED_DYE && bank >= this.cfgDepositStep) {
                this.personalBank.put(u, bank - this.cfgDepositStep);
                MinecraftBank.econ.depositPlayer((OfflinePlayer)p, this.cfgDepositStep);
                this.msgKey(p, "personal.withdraw-step", new String[]{"amount", String.valueOf((long)this.cfgDepositStep)});
                this.sendDiscordWebhook("\ud83d\udcb8 **" + p.getName() + "** \u304c\u500b\u4eba\u53e3\u5ea7\u304b\u3089 " + (long)this.cfgDepositStep + "\u5186 \u5f15\u304d\u51fa\u3057\u307e\u3057\u305f\u3002");
            } else if (mat == Material.RED_GLAZED_TERRACOTTA && bank > 0.0) {
                confirmedAt = this.pendingConfirmationTime.getOrDefault(u, 0L);
                v11 = expired = System.currentTimeMillis() - confirmedAt > 30000L;
                if (!"full_withdraw".equals(this.pendingConfirmation.remove(u)) || expired) {
                    this.pendingConfirmation.put(u, "full_withdraw");
                    this.pendingConfirmationTime.put(u, System.currentTimeMillis());
                    this.msgKey(p, "confirm.withdraw-prompt", new String[0]);
                    return;
                }
                this.pendingConfirmationTime.remove(u);
                this.personalBank.put(u, 0.0);
                MinecraftBank.econ.depositPlayer((OfflinePlayer)p, bank);
                this.msgKey(p, "personal.withdraw-full", new String[0]);
                this.sendDiscordWebhook("\ud83d\udcb8 **" + p.getName() + "** \u304c\u500b\u4eba\u53e3\u5ea7\u304b\u3089\u5168\u984d " + (long)bank + "\u5186 \u5f15\u304d\u51fa\u3057\u307e\u3057\u305f\u3002");
            } else {
                if (mat == Material.WRITABLE_BOOK) {
                    this.awaitingChatInput.put(u, "personal_deposit");
                    this.msgKey(p, "personal.deposit-prompt", new String[0]);
                    p.closeInventory();
                    return;
                }
                if (mat == Material.PAPER) {
                    this.awaitingChatInput.put(u, "personal_withdraw");
                    this.msgKey(p, "personal.withdraw-prompt", new String[0]);
                    p.closeInventory();
                    return;
                }
                if (mat == Material.WRITTEN_BOOK) {
                    logs = this.transactionLogs.get(u);
                    if (logs == null || logs.isEmpty()) {
                        this.msgKey(p, "log.empty", new String[0]);
                    } else {
                        this.msgKey(p, "log.header", new String[0]);
                        shown = 0;
                        for (String log : logs) {
                            if (shown++ >= 15) break;
                            this.msgKey(p, "log.entry", new String[]{"entry", log});
                        }
                    }
                    this.clickSound(p);
                    this.openPersonalGUI(p);
                    return;
                }
                if (mat == Material.BELL || mat == Material.NOTE_BLOCK) {
                    if (this.newsBroadcastOff.contains(u)) {
                        this.newsBroadcastOff.remove(u);
                        this.msgKey(p, "news.enabled", new String[0]);
                    } else {
                        this.newsBroadcastOff.add(u);
                        this.msgKey(p, "news.disabled", new String[0]);
                    }
                    this.clickSound(p);
                    this.openPersonalGUI(p);
                    return;
                }
                if (mat == Material.IRON_DOOR) {
                    this.openBankHubGUI(p);
                    return;
                }
            }
            this.openPersonalGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tMarket)) {
            if (mat == Material.IRON_DOOR) {
                this.openBankHubGUI(p);
                return;
            }
            meta = item.getItemMeta();
            if (meta != null && (lStr = (String)meta.getPersistentDataContainer().get(this.lenderKey, PersistentDataType.STRING)) != null) {
                lenderId = UUID.fromString(lStr);
                if (lenderId.equals(u)) {
                    this.msgKey(p, "loan.cannot-borrow-self-bank", new String[0]);
                    this.errorSound(p);
                    return;
                }
                if (this.activeDebts.containsKey(u)) {
                    this.msgKey(p, "loan.already-has-debt", new String[0]);
                    this.errorSound(p);
                    return;
                }
                slotNumBoxed = (Integer)meta.getPersistentDataContainer().get(this.loanSlotKey, PersistentDataType.INTEGER);
                v12 = slotNum = slotNumBoxed != null ? slotNumBoxed : 1;
                targetMap = slotNum == 2 ? this.publishedLoans2 : (slotNum == 3 ? this.publishedLoans3 : this.publishedLoans);
                planData = targetMap.get(lenderId);
                if (planData == null) {
                    this.msgKey(p, "loan.plan-not-exist", new String[0]);
                    this.errorSound(p);
                    this.openMarketGUI(p);
                    return;
                }
                data = planData.split(":");
                amount = Double.parseDouble(data[0]);
                interest = Double.parseDouble(data[1]);
                capital = this.bankCapital.getOrDefault(lenderId, 0.0);
                if (capital < amount) {
                    this.msgKey(p, "loan.bank-capital-insufficient", new String[0]);
                    this.errorSound(p);
                    return;
                }
                this.bankCapital.put(lenderId, capital - amount);
                MinecraftBank.econ.depositPlayer((OfflinePlayer)p, amount);
                totalRepay = amount * (1.0 + interest / 100.0);
                this.activeDebts.put(u, lenderId.toString() + ":" + totalRepay);
                targetMap.remove(lenderId);
                this.weeklyTradeVolume += amount;
                this.msgKey(p, "loan.player-approved", new String[]{"amount", String.valueOf((long)amount)});
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                this.addLog(u, "\u878d\u8cc7\u5951\u7d04\u6210\u7acb +" + (long)amount + "\u5186 (\u8fd4\u6e08\u7dcf\u984d" + (long)totalRepay + "\u5186)");
                this.sendDiscordWebhook("\ud83d\udcb0 **" + p.getName() + "** \u304c **" + Bukkit.getOfflinePlayer((UUID)lenderId).getName() + "** \u306e\u878d\u8cc7\u30d7\u30e9\u30f3\u304b\u3089 " + (long)amount + "\u5186 \u3092\u501f\u308a\u307e\u3057\u305f\u3002\u8fd4\u6e08\u7dcf\u984d: " + this.fmtCur(totalRepay));
                this.addLog(lenderId, p.getName() + " \u3078\u878d\u8cc7 -" + this.fmtCur(amount));
                lender = Bukkit.getPlayer((UUID)lenderId);
                if (lender != null && lender.isOnline()) {
                    this.msgKey(lender, "loan.player-approved-notice", new String[]{"player", p.getName(), "amount", String.valueOf((long)amount)});
                    lender.playSound(lender.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                } else {
                    this.queueLoanOfflineNotice(lenderId, p.getName(), amount);
                }
                this.openBankHubGUI(p);
                return;
            }
            this.openMarketGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tRepay)) {
            if (mat == Material.IRON_DOOR) {
                this.openBankHubGUI(p);
                return;
            }
            if (e.getRawSlot() == 10 && this.activeDebts.containsKey(u)) {
                data = this.activeDebts.get(u).split(":");
                lenderId = UUID.fromString(data[0]);
                debt = Double.parseDouble(data[1]);
                pay = Math.min(1000.0, debt);
                if (pocket >= pay) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, pay);
                    debt -= pay;
                    this.bankCapital.put(lenderId, this.bankCapital.getOrDefault(lenderId, 0.0) + pay);
                    if (debt <= 0.0) {
                        this.activeDebts.remove(u);
                        this.loanGuarantor.remove(u);
                        this.msgKey(p, "loan.player-repaid-full-plain", new String[0]);
                        this.addScore(u, 30);
                        this.unlockAchievement(u, "first_loan_repaid", "\u521d\u3081\u3066\u306e\u5b8c\u6e08");
                        this.sendDiscordWebhook("\ud83d\udcb3 **" + p.getName() + "** \u304c\u30d7\u30ec\u30a4\u30e4\u30fc\u9593\u878d\u8cc7\u3092\u5b8c\u6e08\u3057\u307e\u3057\u305f\u3002");
                    } else {
                        this.activeDebts.put(u, lenderId.toString() + ":" + debt);
                        this.msgKey(p, "loan.repay-partial", new String[]{"amount", String.valueOf((long)pay)});
                        this.addScore(u, 5);
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                } else {
                    this.msgKey(p, "common.insufficient-funds-simple", new String[0]);
                    this.errorSound(p);
                }
            } else if (e.getRawSlot() == 11 && this.activeDebts.containsKey(u)) {
                data = this.activeDebts.get(u).split(":");
                lenderId = UUID.fromString(data[0]);
                debt = Double.parseDouble(data[1]);
                if (pocket >= debt) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, debt);
                    this.activeDebts.remove(u);
                    this.loanGuarantor.remove(u);
                    this.bankCapital.put(lenderId, this.bankCapital.getOrDefault(lenderId, 0.0) + debt);
                    this.msgKey(p, "loan.repaid-full", new String[0]);
                    this.addScore(u, 50);
                    this.unlockAchievement(u, "first_loan_repaid", "\u521d\u3081\u3066\u306e\u5b8c\u6e08");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                } else {
                    this.msgKey(p, "loan.repay-full-insufficient", new String[0]);
                    this.errorSound(p);
                }
            }
            gDebt = this.govDebt.getOrDefault(u, 0.0);
            if (e.getRawSlot() == 15 && gDebt > 0.0) {
                pay = Math.min(1000.0, gDebt);
                if (pocket >= pay) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, pay);
                    gDebt -= pay;
                    if (gDebt <= 0.0) {
                        this.govDebt.remove(u);
                        this.govDebtDueTime.remove(u);
                        this.msgKey(p, "loan.gov-repaid-full-plain", new String[0]);
                        this.addScore(u, 40);
                        this.addLog(u, "\u56fd\u55b6\u30ed\u30fc\u30f3\u5b8c\u6e08");
                        this.unlockAchievement(u, "first_gov_loan_repaid", "\u516c\u5eab\u306e\u5e38\u9023");
                        this.sendDiscordWebhook("\ud83c\udfdb\ufe0f **" + p.getName() + "** \u304c\u56fd\u55b6\u516c\u5eab\u30ed\u30fc\u30f3\u3092\u5b8c\u6e08\u3057\u307e\u3057\u305f\u3002");
                    } else {
                        this.govDebt.put(u, gDebt);
                        this.msgKey(p, "loan.repay-partial", new String[]{"amount", String.valueOf((long)pay)});
                        this.addScore(u, 10);
                    }
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                } else {
                    this.msgKey(p, "common.insufficient-funds-simple", new String[0]);
                    this.errorSound(p);
                }
            } else if (e.getRawSlot() == 16 && gDebt > 0.0) {
                if (pocket >= gDebt) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, gDebt);
                    this.govDebt.remove(u);
                    this.govDebtDueTime.remove(u);
                    this.msgKey(p, "loan.gov-repaid-full-bold", new String[0]);
                    this.addScore(u, 60);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "\u56fd\u55b6\u30ed\u30fc\u30f3\u5168\u984d\u5b8c\u6e08");
                    this.unlockAchievement(u, "first_gov_loan_repaid", "\u516c\u5eab\u306e\u5e38\u9023");
                } else {
                    this.msgKey(p, "loan.repay-full-insufficient", new String[0]);
                    this.errorSound(p);
                }
            }
            this.openRepayGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tEstablish)) {
            if (mat == Material.IRON_DOOR) {
                this.openBankHubGUI(p);
                return;
            }
            if (mat == Material.DIAMOND) {
                if (this.bankers.contains(u)) {
                    this.msgKey(p, "bank.already-established", new String[0]);
                    this.errorSound(p);
                    return;
                }
                if (pocket >= this.cfgBankEstablishCost) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, this.cfgBankEstablishCost);
                    this.bankers.add(u);
                    this.bankCapital.put(u, 0.0);
                    this.msgKey(p, "bank.established", new String[0]);
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "\u9280\u884c\u3092\u8a2d\u7acb (-" + (long)this.cfgBankEstablishCost + "\u5186)");
                    this.sendDiscordWebhook("\ud83c\udfe6 **" + p.getName() + "** \u304c\u9280\u884c\u3092\u8a2d\u7acb\u3057\u307e\u3057\u305f\u3002\u8cbb\u7528: " + this.fmtCur(this.cfgBankEstablishCost));
                    this.openBankHubGUI(p);
                    return;
                }
                this.msgKey(p, "bank.establish-cost-insufficient", new String[]{"amount", String.valueOf((long)this.cfgBankEstablishCost)});
                this.errorSound(p);
            }
            this.openEstablishGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tBanker)) {
            if (mat == Material.IRON_DOOR) {
                this.openBankHubGUI(p);
                return;
            }
            capital = this.bankCapital.getOrDefault(u, 0.0);
            if (mat == Material.DIAMOND) {
                if (pocket >= 10000.0) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, 10000.0);
                    this.bankCapital.put(u, capital + 10000.0);
                    this.msgKey(p, "bank.capital-added", new String[0]);
                    this.sendDiscordWebhook("\ud83c\udfe6 **" + p.getName() + "** \u304c\u9280\u884c\u306e\u8cc7\u672c\u91d1\u3078 10,000\u5186 \u8ffd\u52a0\u3057\u307e\u3057\u305f\u3002");
                } else {
                    this.msgKey(p, "common.insufficient-funds-simple", new String[0]);
                    this.errorSound(p);
                }
            } else if (mat == Material.COAL) {
                if (capital >= 10000.0) {
                    this.bankCapital.put(u, capital - 10000.0);
                    MinecraftBank.econ.depositPlayer((OfflinePlayer)p, 10000.0);
                    this.msgKey(p, "bank.capital-withdrawn", new String[0]);
                    this.sendDiscordWebhook("\ud83c\udfe6 **" + p.getName() + "** \u304c\u9280\u884c\u306e\u8cc7\u672c\u91d1\u304b\u3089 10,000\u5186 \u5f15\u304d\u51fa\u3057\u307e\u3057\u305f\u3002");
                } else {
                    this.msgKey(p, "bank.capital-insufficient-pool", new String[0]);
                    this.errorSound(p);
                }
            } else if (mat == Material.BARRIER) {
                rawSlot = e.getRawSlot();
                if (rawSlot == 15 && this.publishedLoans.containsKey(u)) {
                    this.publishedLoans.remove(u);
                    this.msgKey(p, "bank.plan-withdrawn", new String[]{"slot", "1"});
                } else if (rawSlot == 16 && this.publishedLoans2.containsKey(u)) {
                    this.publishedLoans2.remove(u);
                    this.msgKey(p, "bank.plan-withdrawn", new String[]{"slot", "2"});
                } else if (rawSlot == 17 && this.publishedLoans3.containsKey(u)) {
                    this.publishedLoans3.remove(u);
                    this.msgKey(p, "bank.plan-withdrawn", new String[]{"slot", "3"});
                } else {
                    this.msgKey(p, "bank.no-published-plan", new String[0]);
                    this.errorSound(p);
                }
            } else {
                if (mat == Material.WRITABLE_BOOK) {
                    this.planDesignSlot.put(u, 1);
                    this.tempLoanAmount.remove(u);
                    this.tempInterestRate.remove(u);
                    this.openPlanGUI(p);
                    this.clickSound(p);
                    return;
                }
                if (mat == Material.BOOK) {
                    this.planDesignSlot.put(u, 2);
                    this.tempLoanAmount.remove(u);
                    this.tempInterestRate.remove(u);
                    this.openPlanGUI(p);
                    this.clickSound(p);
                    return;
                }
                if (mat == Material.KNOWLEDGE_BOOK) {
                    this.planDesignSlot.put(u, 3);
                    this.tempLoanAmount.remove(u);
                    this.tempInterestRate.remove(u);
                    this.openPlanGUI(p);
                    this.clickSound(p);
                    return;
                }
            }
            this.openBankerGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tPlan)) {
            if (mat == Material.IRON_DOOR) {
                this.openBankerGUI(p);
                return;
            }
            amount = this.tempLoanAmount.getOrDefault(u, 1000.0);
            interest = this.tempInterestRate.getOrDefault(u, 10.0);
            if (mat == Material.SLIME_BALL) {
                this.tempLoanAmount.put(u, amount + 1000.0);
            } else if (mat == Material.SLIME_BLOCK) {
                this.tempLoanAmount.put(u, amount + 10000.0);
            } else if (mat == Material.SUGAR) {
                this.tempInterestRate.put(u, interest + 5.0);
            } else if (mat == Material.GLOWSTONE_DUST) {
                this.tempInterestRate.put(u, interest + 10.0);
            } else if (mat == Material.REDSTONE) {
                if (e.getRawSlot() == 12) {
                    this.tempLoanAmount.put(u, 1000.0);
                } else if (e.getRawSlot() == 16) {
                    this.tempInterestRate.put(u, 10.0);
                }
            } else {
                if (mat == Material.WRITABLE_BOOK && e.getRawSlot() == 18) {
                    this.awaitingChatInput.put(u, "plan_amount");
                    this.msgKey(p, "bank.plan-amount-prompt", new String[0]);
                    p.closeInventory();
                    return;
                }
                if (mat == Material.PAPER && e.getRawSlot() == 19) {
                    this.awaitingChatInput.put(u, "plan_interest");
                    this.msgKey(p, "bank.plan-interest-prompt", new String[0]);
                    p.closeInventory();
                    return;
                }
                if (mat == Material.NETHER_STAR) {
                    slot = this.planDesignSlot.getOrDefault(u, 1);
                    if (slot == 2) {
                        this.publishedLoans2.put(u, amount + ":" + interest);
                    } else if (slot == 3) {
                        this.publishedLoans3.put(u, amount + ":" + interest);
                    } else {
                        this.publishedLoans.put(u, amount + ":" + interest);
                    }
                    this.msgKey(p, "bank.plan-published", new String[]{"slot", String.valueOf(slot)});
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "\u878d\u8cc7\u30d7\u30e9\u30f3(" + slot + "\u67a0\u76ee)\u516c\u958b: " + (long)amount + "\u5186/" + (long)interest + "%");
                    this.sendDiscordWebhook("\ud83d\udccb **" + p.getName() + "** \u304c\u878d\u8cc7\u30d7\u30e9\u30f3(" + slot + "\u67a0\u76ee)\u3092\u516c\u958b\u3057\u307e\u3057\u305f\u3002\u878d\u8cc7\u984d: " + (long)amount + "\u5186 / \u5229\u606f: " + (long)interest + "%");
                    this.openBankerGUI(p);
                    return;
                }
            }
            this.openPlanGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tGovLoan)) {
            if (mat == Material.IRON_DOOR) {
                this.openBankHubGUI(p);
                return;
            }
            if (mat == Material.EMERALD) {
                score = this.getScore(u);
                interest = score >= 700 ? 5 : (score >= 500 ? 15 : 25);
                currentGovDebt = this.govDebt.getOrDefault(u, 0.0);
                if (currentGovDebt + (addDebt = this.cfgGovLoanAmount * (1.0 + (double)interest / 100.0)) > (cap = this.getGovLoanCap(score))) {
                    this.msgKey(p, "loan.gov-cap-exceeded", new String[]{"amount", String.valueOf((long)cap)});
                    this.errorSound(p);
                    this.openGovLoanGUI(p);
                    return;
                }
                MinecraftBank.econ.depositPlayer((OfflinePlayer)p, this.cfgGovLoanAmount);
                this.govDebt.put(u, currentGovDebt + addDebt);
                this.govDebtDueTime.put(u, System.currentTimeMillis() + this.cfgGovLoanDurationMs);
                this.weeklyTradeVolume += this.cfgGovLoanAmount;
                this.msgKey(p, "loan.gov-approved", new String[]{"amount", String.valueOf((long)this.cfgGovLoanAmount), "rate", String.valueOf(interest), "total", String.valueOf((long)addDebt), "minutes", String.valueOf(this.cfgGovLoanDurationMs / 60000L)});
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                this.addLog(u, "\u56fd\u55b6\u30ed\u30fc\u30f3\u501f\u5165 +" + (long)this.cfgGovLoanAmount + "\u5186 (\u8fd4\u6e08\u7dcf\u984d" + (long)addDebt + "\u5186)");
                this.sendDiscordWebhook("\ud83c\udfdb\ufe0f **" + p.getName() + "** \u304c\u56fd\u55b6\u516c\u5eab\u304b\u3089 " + (long)this.cfgGovLoanAmount + "\u5186 \u3092\u501f\u308a\u307e\u3057\u305f\u3002\u8fd4\u6e08\u7dcf\u984d: " + this.fmtCur(addDebt));
                this.openBankHubGUI(p);
                return;
            }
            this.openGovLoanGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tFixedDepo)) {
            if (mat == Material.IRON_DOOR) {
                this.openBankHubGUI(p);
                return;
            }
            amount = this.fixedDeposit.getOrDefault(u, 0.0);
            unlockTime = this.fixedDepositUnlockTime.getOrDefault(u, 0L);
            if (mat == Material.GOLD_INGOT) {
                if (amount > 0.0) {
                    this.msgKey(p, "deposit.already-exists", new String[0]);
                    this.errorSound(p);
                } else if (pocket >= this.cfgFixedDepositAmount) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, this.cfgFixedDepositAmount);
                    this.fixedDeposit.put(u, this.cfgFixedDepositAmount);
                    this.fixedDepositUnlockTime.put(u, System.currentTimeMillis() + this.cfgFixedDepositDurationMs);
                    this.msgKey(p, "deposit.slot1-created", new String[]{"amount", String.valueOf((long)this.cfgFixedDepositAmount), "seconds", String.valueOf(this.cfgFixedDepositDurationMs / 1000L), "rate", String.valueOf((int)(this.cfgFixedDepositRate * 100.0))});
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "\u5b9a\u671f\u9810\u91d1(1\u67a0) -" + this.fmtCur(this.cfgFixedDepositAmount));
                    this.sendDiscordWebhook("\ud83d\udcb5 **" + p.getName() + "** \u304c\u5b9a\u671f\u9810\u91d11\u67a0\u76ee\u306b " + (long)this.cfgFixedDepositAmount + "\u5186 \u3092\u9810\u3051\u307e\u3057\u305f\u3002");
                } else {
                    this.msgKey(p, "deposit.funds-insufficient", new String[]{"amount", String.valueOf((long)this.cfgFixedDepositAmount)});
                    this.errorSound(p);
                }
            } else if (mat == Material.GOLD_BLOCK) {
                if (amount <= 0.0) {
                    this.msgKey(p, "deposit.slot1-none", new String[0]);
                    this.errorSound(p);
                } else if (System.currentTimeMillis() < unlockTime) {
                    this.msgKey(p, "deposit.slot1-not-matured", new String[0]);
                    this.errorSound(p);
                } else {
                    reward = amount * (1.0 + this.cfgFixedDepositRate);
                    MinecraftBank.econ.depositPlayer((OfflinePlayer)p, reward);
                    this.fixedDeposit.put(u, 0.0);
                    this.fixedDepositUnlockTime.put(u, 0L);
                    this.msgKey(p, "fixed-deposit.matured", new String[]{"amount", String.valueOf((long)reward)});
                    this.addScore(u, 15);
                    p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
                    this.addLog(u, "\u5b9a\u671f\u9810\u91d1(1\u67a0)\u6e80\u671f +" + this.fmtCur(reward));
                    this.sendDiscordWebhook("\ud83c\udf89 **" + p.getName() + "** \u306e\u5b9a\u671f\u9810\u91d11\u67a0\u76ee\u304c\u6e80\u671f\u306b\u306a\u308a\u307e\u3057\u305f\u3002\u53d7\u53d6\u984d: " + this.fmtCur(reward));
                    this.unlockAchievement(u, "first_fixed_deposit", "\u5805\u5b9f\u306a\u6295\u8cc7\u5bb6");
                }
            } else if (mat == Material.IRON_INGOT) {
                a2 = this.fixedDeposit2.getOrDefault(u, 0.0);
                if (a2 > 0.0) {
                    this.msgKey(p, "deposit.slot2-exists", new String[0]);
                    this.errorSound(p);
                } else if (pocket >= this.cfgFixedDepositAmount) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, this.cfgFixedDepositAmount);
                    this.fixedDeposit2.put(u, this.cfgFixedDepositAmount);
                    this.fixedDepositUnlockTime2.put(u, System.currentTimeMillis() + this.cfgFixedDepositDurationMs);
                    this.msgKey(p, "deposit.slot2-created", new String[]{"amount", String.valueOf((long)this.cfgFixedDepositAmount)});
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "\u5b9a\u671f\u9810\u91d1(2\u67a0) -" + this.fmtCur(this.cfgFixedDepositAmount));
                    this.sendDiscordWebhook("\ud83d\udcb5 **" + p.getName() + "** \u304c\u5b9a\u671f\u9810\u91d12\u67a0\u76ee\u306b " + (long)this.cfgFixedDepositAmount + "\u5186 \u3092\u9810\u3051\u307e\u3057\u305f\u3002");
                } else {
                    this.msgKey(p, "deposit.funds-insufficient", new String[]{"amount", String.valueOf((long)this.cfgFixedDepositAmount)});
                    this.errorSound(p);
                }
            } else if (mat == Material.IRON_BLOCK) {
                a2 = this.fixedDeposit2.getOrDefault(u, 0.0);
                t2 = this.fixedDepositUnlockTime2.getOrDefault(u, 0L);
                if (a2 <= 0.0) {
                    this.msgKey(p, "deposit.slot2-none", new String[0]);
                    this.errorSound(p);
                } else if (System.currentTimeMillis() < t2) {
                    this.msgKey(p, "deposit.slot2-not-matured", new String[0]);
                    this.errorSound(p);
                } else {
                    reward = a2 * (1.0 + this.cfgFixedDepositRate);
                    MinecraftBank.econ.depositPlayer((OfflinePlayer)p, reward);
                    this.fixedDeposit2.put(u, 0.0);
                    this.fixedDepositUnlockTime2.put(u, 0L);
                    this.msgKey(p, "fixed-deposit.matured-2", new String[]{"amount", String.valueOf((long)reward)});
                    this.addScore(u, 15);
                    p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
                    this.addLog(u, "\u5b9a\u671f\u9810\u91d1(2\u67a0)\u6e80\u671f +" + this.fmtCur(reward));
                    this.sendDiscordWebhook("\ud83c\udf89 **" + p.getName() + "** \u306e\u5b9a\u671f\u9810\u91d12\u67a0\u76ee\u304c\u6e80\u671f\u306b\u306a\u308a\u307e\u3057\u305f\u3002\u53d7\u53d6\u984d: " + this.fmtCur(reward));
                    this.unlockAchievement(u, "first_fixed_deposit", "\u5805\u5b9f\u306a\u6295\u8cc7\u5bb6");
                }
            } else if (mat == Material.EMERALD) {
                a3 = this.fixedDeposit3.getOrDefault(u, 0.0);
                if (a3 > 0.0) {
                    this.msgKey(p, "deposit.slot3-exists", new String[0]);
                    this.errorSound(p);
                } else if (pocket >= this.cfgFixedDepositAmount) {
                    MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, this.cfgFixedDepositAmount);
                    this.fixedDeposit3.put(u, this.cfgFixedDepositAmount);
                    this.fixedDepositUnlockTime3.put(u, System.currentTimeMillis() + this.cfgFixedDepositDurationMs);
                    this.msgKey(p, "deposit.slot3-created", new String[]{"amount", String.valueOf((long)this.cfgFixedDepositAmount)});
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "\u5b9a\u671f\u9810\u91d1(3\u67a0) -" + this.fmtCur(this.cfgFixedDepositAmount));
                    this.sendDiscordWebhook("\ud83d\udcb5 **" + p.getName() + "** \u304c\u5b9a\u671f\u9810\u91d13\u67a0\u76ee\u306b " + (long)this.cfgFixedDepositAmount + "\u5186 \u3092\u9810\u3051\u307e\u3057\u305f\u3002");
                } else {
                    this.msgKey(p, "deposit.funds-insufficient", new String[]{"amount", String.valueOf((long)this.cfgFixedDepositAmount)});
                    this.errorSound(p);
                }
            } else if (mat == Material.EMERALD_BLOCK) {
                a3 = this.fixedDeposit3.getOrDefault(u, 0.0);
                t3 = this.fixedDepositUnlockTime3.getOrDefault(u, 0L);
                if (a3 <= 0.0) {
                    this.msgKey(p, "deposit.slot3-none", new String[0]);
                    this.errorSound(p);
                } else if (System.currentTimeMillis() < t3) {
                    this.msgKey(p, "deposit.slot3-not-matured", new String[0]);
                    this.errorSound(p);
                } else {
                    reward = a3 * (1.0 + this.cfgFixedDepositRate);
                    MinecraftBank.econ.depositPlayer((OfflinePlayer)p, reward);
                    this.fixedDeposit3.put(u, 0.0);
                    this.fixedDepositUnlockTime3.put(u, 0L);
                    this.msgKey(p, "fixed-deposit.matured-3", new String[]{"amount", String.valueOf((long)reward)});
                    this.addScore(u, 15);
                    p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
                    this.addLog(u, "\u5b9a\u671f\u9810\u91d1(3\u67a0)\u6e80\u671f +" + this.fmtCur(reward));
                    this.sendDiscordWebhook("\ud83c\udf89 **" + p.getName() + "** \u306e\u5b9a\u671f\u9810\u91d13\u67a0\u76ee\u304c\u6e80\u671f\u306b\u306a\u308a\u307e\u3057\u305f\u3002\u53d7\u53d6\u984d: " + this.fmtCur(reward));
                    this.unlockAchievement(u, "first_fixed_deposit", "\u5805\u5b9f\u306a\u6295\u8cc7\u5bb6");
                }
            }
            this.openFixedDepoGUI(p);
            this.clickSound(p);
            return;
        }
        if (t.equals((Object)this.tCredit)) {
            if (mat == Material.IRON_DOOR) {
                this.openMyPageGUI(p);
                return;
            }
            this.openCreditGUI(p);
            this.clickSound(p);
            return;
        }
    }

    @EventHandler
    public void onInventoryDrag(InventoryDragEvent e) {
        Component t = e.getView().title();
        if (t.equals((Object)this.tTrade)) {
            HumanEntity humanEntity = e.getWhoClicked();
            if (!(humanEntity instanceof Player)) {
                e.setCancelled(true);
                return;
            }
            Player p = (Player)humanEntity;
            TradeSession session = this.activeTradeSessions.get(p.getUniqueId());
            if (session == null) {
                e.setCancelled(true);
                return;
            }
            boolean isA = p.getUniqueId().equals(session.playerA);
            int topSize = e.getView().getTopInventory().getSize();
            boolean touchesTop = false;
            Iterator iterator = e.getRawSlots().iterator();
            while (iterator.hasNext()) {
                int rawSlot = (Integer)iterator.next();
                if (rawSlot >= topSize) continue;
                touchesTop = true;
                boolean ownRegion = isA ? rawSlot >= 0 && rawSlot <= 17 : rawSlot >= 27 && rawSlot <= 44;
                if (ownRegion) continue;
                e.setCancelled(true);
                return;
            }
            if (touchesTop) {
                this.resetTradeConfirmations(session);
            }
            return;
        }
        if (!(t.equals((Object)this.tMain) || t.equals((Object)this.tPersonal) || t.equals((Object)this.tMarket) || t.equals((Object)this.tRepay) || t.equals((Object)this.tEstablish) || t.equals((Object)this.tBanker) || t.equals((Object)this.tPlan) || t.equals((Object)this.tGovLoan) || t.equals((Object)this.tFixedDepo) || t.equals((Object)this.tCredit) || t.equals((Object)this.tLeaderboard) || t.equals((Object)this.tCollateral) || t.equals((Object)this.tInsurance) || t.equals((Object)this.tAchievement) || t.equals((Object)this.tAuction) || t.equals((Object)this.tAuctionCancelConfirm) || t.equals((Object)this.tAuctionRanking) || t.equals((Object)this.tHub) || t.equals((Object)this.tBankHub) || t.equals((Object)this.tMarketHub) || t.equals((Object)this.tTodoHub) || t.equals((Object)this.tMyPage) || t.equals((Object)this.tTutorial) || t.equals((Object)this.tAuctionSelect) || t.equals((Object)this.tCollateralSelect) || t.equals((Object)this.tAdminMain) || t.equals((Object)this.tAdminPlayerList) || t.equals((Object)this.tAdminPlayerDetail) || t.equals((Object)this.tAdminServer) || t.equals((Object)this.tConfigEditor) || t.equals((Object)this.tQuestBoard) || t.equals((Object)this.tWorldStock) || t.equals((Object)this.tWorldStockDetail) || t.equals((Object)this.tWorldStockLeaderboard) || t.equals((Object)this.tResourceShop) || t.equals((Object)this.tResourceShopList) || t.equals((Object)this.tResourceShopDetail) || t.equals((Object)this.tTravelingMerchant) || t.equals((Object)this.tInstallmentList) || t.equals((Object)this.tLottery) || t.equals((Object)this.tStorageRent) || t.equals((Object)this.tTradeSelect) || t.equals((Object)this.tGroupList) || t.equals((Object)this.tGroupAccount) || t.equals((Object)this.tFundList) || t.equals((Object)this.tFundInfo) || t.equals((Object)this.tFundStock) || t.equals((Object)this.tFundStockDetail) || t.equals((Object)this.tGroupMembers) || t.equals((Object)this.tVipLounge))) {
            return;
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent e) {
        UUID u;
        ItemStack selected;
        HumanEntity humanEntity = e.getPlayer();
        if (!(humanEntity instanceof Player)) {
            return;
        }
        Player p = (Player)humanEntity;
        Component t = e.getView().title();
        if (t.equals((Object)this.tCollateralSelect)) {
            return;
        }
        if (t.equals((Object)this.tCollateral) && (selected = this.collateralSelection.remove(u = p.getUniqueId())) != null && !this.collateralItem.containsKey(u)) {
            HashMap overflow = p.getInventory().addItem(new ItemStack[]{selected});
            for (ItemStack extra : overflow.values()) {
                p.getWorld().dropItemNaturally(p.getLocation(), extra);
            }
        }
    }

    @EventHandler
    public void onStorageLockerClose(InventoryCloseEvent e) {
        HumanEntity humanEntity = e.getPlayer();
        if (!(humanEntity instanceof Player)) {
            return;
        }
        Player p = (Player)humanEntity;
        if (!e.getView().title().equals((Object)this.tStorageLocker)) {
            return;
        }
        this.persistPlayerStorage(p.getUniqueId(), e.getInventory());
    }

    @EventHandler
    public void onTradeInventoryClose(InventoryCloseEvent e) {
        HumanEntity humanEntity = e.getPlayer();
        if (!(humanEntity instanceof Player)) {
            return;
        }
        Player p = (Player)humanEntity;
        if (!e.getView().title().equals((Object)this.tTrade)) {
            return;
        }
        TradeSession session = this.activeTradeSessions.get(p.getUniqueId());
        if (session == null || session.finished) {
            return;
        }
        this.cancelTradeSession(session, "closed");
    }

    @EventHandler
    public void onHubItemInteract(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Action action = e.getAction();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        ItemStack item = e.getItem();
        if (item == null || item.getType() != Material.BOOK) {
            return;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        Byte tag = (Byte)meta.getPersistentDataContainer().get(this.hubItemKey, PersistentDataType.BYTE);
        if (tag == null || tag != 1) {
            return;
        }
        e.setCancelled(true);
        Player p = e.getPlayer();
        this.openHubGUI(p);
        p.playSound(p.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_CHIME, 1.0f, 1.0f);
    }

    @EventHandler
    public void onPlayerInteractMerchantEntity(PlayerInteractEntityEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        Entity clicked = e.getRightClicked();
        Byte marker = (Byte)clicked.getPersistentDataContainer().get(this.merchantNpcMarkerKey, PersistentDataType.BYTE);
        if (marker == null) {
            return;
        }
        e.setCancelled(true);
        Player p = e.getPlayer();
        this.openTravelingMerchantGUI(p);
    }

    private boolean isActiveTreasureChest(Block block) {
        if (!this.treasureActive || block == null) {
            return false;
        }
        if (block.getType() != Material.CHEST) {
            return false;
        }
        if (!block.getWorld().getName().equals(this.treasureWorldName)) {
            return false;
        }
        if (block.getX() != this.treasureX || block.getY() != this.treasureY || block.getZ() != this.treasureZ) {
            return false;
        }
        BlockState state = block.getState();
        if (!(state instanceof TileState)) {
            return false;
        }
        TileState tile = (TileState)state;
        return tile.getPersistentDataContainer().get(this.treasureChestMarkerKey, PersistentDataType.BYTE) != null;
    }

    @EventHandler
    public void onPlayerInteractTreasureChest(PlayerInteractEvent e) {
        if (e.getHand() != EquipmentSlot.HAND) {
            return;
        }
        if (e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        Block block = e.getClickedBlock();
        if (!this.isActiveTreasureChest(block)) {
            return;
        }
        e.setCancelled(true);
        this.treasureActive = false;
        this.treasureNextSpawnAt = System.currentTimeMillis() + (long)this.cfgTreasureIntervalHours * 3600000L;
        double reward = this.treasureReward;
        block.setType(Material.AIR);
        Player p = e.getPlayer();
        econ.depositPlayer((OfflinePlayer)p, reward);
        this.msgKey(p, "treasure.found", "amount", String.valueOf((long)reward));
        this.addLog(p.getUniqueId(), "\u57cb\u8535\u91d1\u3092\u767a\u898b +" + this.fmtCur(reward));
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        this.sendDiscordWebhook("\ud83d\udc8e **" + p.getName() + "** \u304c\u57cb\u8535\u91d1\u30c1\u30a7\u30b9\u30c8\u3092\u767a\u898b\u3057\u3001" + (long)reward + "\u5186 \u3092\u7372\u5f97\u3057\u307e\u3057\u305f\u3002");
        this.broadcastNews("<gold><bold>\u3010\u57cb\u8535\u91d1\u3011</bold> <yellow>" + p.getName() + "</yellow> \u304c\u57cb\u8535\u91d1 " + (long)reward + "\u5186 \u3092\u767a\u898b\u3057\u307e\u3057\u305f\uff01</gold>");
    }

    @EventHandler
    public void onBlockBreakTreasureChest(BlockBreakEvent e) {
        if (this.isActiveTreasureChest(e.getBlock())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerMoveForQuest(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        UUID u = p.getUniqueId();
        UUID qId = this.playerActiveQuest.get(u);
        if (qId == null) {
            return;
        }
        if (e.getFrom().getBlockX() == e.getTo().getBlockX() && e.getFrom().getBlockY() == e.getTo().getBlockY() && e.getFrom().getBlockZ() == e.getTo().getBlockZ()) {
            return;
        }
        Quest q = this.quests.get(qId);
        if (q != null && q.requiredTeamSize > 1) {
            if (q.state != QuestState.IN_PROGRESS || !q.teamMembers.contains(u)) {
                if (q.state != QuestState.AVAILABLE || !q.teamMembers.contains(u)) {
                    this.playerActiveQuest.remove(u);
                }
                return;
            }
            World tw = Bukkit.getWorld((String)q.worldName);
            if (tw == null || !tw.equals((Object)p.getWorld())) {
                return;
            }
            Location location = new Location(tw, q.x, q.y, q.z);
            if (p.getLocation().distance(location) > this.cfgQuestRadius) {
                return;
            }
            if (q.teamArrived.contains(u)) {
                return;
            }
            q.teamArrived.add(u);
            if (q.teamArrived.size() < q.teamMembers.size()) {
                this.msgKey(p, "quest.team-arrived-waiting", "arrived", String.valueOf(q.teamArrived.size()), "required", String.valueOf(q.teamMembers.size()));
                return;
            }
            for (UUID member : q.teamMembers) {
                OfflinePlayer memberOffline = Bukkit.getOfflinePlayer((UUID)member);
                econ.depositPlayer(memberOffline, q.reward);
                this.weeklyTradeVolume += q.reward;
                this.addLog(member, "\u30c1\u30fc\u30e0\u4f9d\u983c\u9054\u6210 +" + this.fmtCur(q.reward));
                Player memberOnline = Bukkit.getPlayer((UUID)member);
                if (memberOnline != null) {
                    this.msgKey(memberOnline, "quest.team-completed", "amount", String.valueOf((long)q.reward));
                    this.sendToast(memberOnline, "\u30c1\u30fc\u30e0\u4f9d\u983c\u9054\u6210", "+" + this.fmtCur(q.reward));
                    memberOnline.playSound(memberOnline.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
                }
                this.playerActiveQuest.remove(member);
            }
            Player posterOnline2 = Bukkit.getPlayer((UUID)q.posterId);
            if (posterOnline2 != null) {
                this.msgKey(posterOnline2, "quest.completed-notice", "player", p.getName());
            }
            this.sendDiscordWebhook("\ud83d\udccb **" + p.getName() + "** \u305f\u3061\u306e\u30c1\u30fc\u30e0\u304c\u63a2\u7d22\u4f9d\u983c\u3092\u9054\u6210\u3057\u307e\u3057\u305f\u3002\u4e00\u4eba\u3042\u305f\u308a\u5831\u916c: " + this.fmtCur(q.reward));
            q.state = QuestState.COOLDOWN;
            q.cooldownUntil = System.currentTimeMillis() + this.cfgQuestCooldownMs;
            q.teamMembers.clear();
            q.teamArrived.clear();
            return;
        }
        if (q == null || q.state != QuestState.IN_PROGRESS || !u.equals(q.acceptedBy)) {
            this.playerActiveQuest.remove(u);
            return;
        }
        World w = Bukkit.getWorld((String)q.worldName);
        if (w == null || !w.equals((Object)p.getWorld())) {
            return;
        }
        Location location = new Location(w, q.x, q.y, q.z);
        if (p.getLocation().distance(location) > this.cfgQuestRadius) {
            return;
        }
        econ.depositPlayer((OfflinePlayer)p, q.reward);
        this.weeklyTradeVolume += q.reward;
        this.msgKey(p, "quest.completed", "amount", String.valueOf((long)q.reward));
        this.sendToast(p, "\u4f9d\u983c\u9054\u6210", "+" + this.fmtCur(q.reward));
        p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
        this.addLog(u, "\u4f9d\u983c\u9054\u6210 +" + this.fmtCur(q.reward));
        Player posterOnline = Bukkit.getPlayer((UUID)q.posterId);
        if (posterOnline != null) {
            this.msgKey(posterOnline, "quest.completed-notice", "player", p.getName());
        }
        this.sendDiscordWebhook("\ud83d\udccb **" + p.getName() + "** \u304c\u63a2\u7d22\u4f9d\u983c\u3092\u9054\u6210\u3057\u307e\u3057\u305f\u3002\u5831\u916c: " + this.fmtCur(q.reward));
        this.playerActiveQuest.remove(u);
        q.state = QuestState.COOLDOWN;
        q.acceptedBy = null;
        q.cooldownUntil = System.currentTimeMillis() + this.cfgQuestCooldownMs;
    }

    private void refreshQuestBoard() {
        long now = System.currentTimeMillis();
        Iterator<Quest> it = this.quests.values().iterator();
        while (it.hasNext()) {
            Player posterOnline;
            Quest q = it.next();
            if (q.state != QuestState.COOLDOWN || now < q.cooldownUntil) continue;
            double totalReward = q.reward * (double)q.requiredTeamSize;
            if (q.posterId.equals(SYSTEM_QUEST_POSTER_ID)) {
                if (this.treasury >= totalReward) {
                    this.treasury -= totalReward;
                    q.state = QuestState.AVAILABLE;
                    continue;
                }
                it.remove();
                continue;
            }
            OfflinePlayer poster = Bukkit.getOfflinePlayer((UUID)q.posterId);
            if (econ.getBalance(poster) >= totalReward) {
                econ.withdrawPlayer(poster, totalReward);
                q.state = QuestState.AVAILABLE;
                posterOnline = Bukkit.getPlayer((UUID)q.posterId);
                if (posterOnline == null) continue;
                this.msgKey(posterOnline, "quest.relisted", "amount", String.valueOf((long)totalReward));
                continue;
            }
            posterOnline = Bukkit.getPlayer((UUID)q.posterId);
            if (posterOnline != null) {
                this.msgKey(posterOnline, "quest.removed-insufficient-funds", new String[0]);
            }
            it.remove();
        }
    }

    private void generateSystemQuestsIfNeeded() {
        double reward;
        long availableCount = this.quests.values().stream().filter(q -> q.state == QuestState.AVAILABLE).count();
        if (availableCount >= (long)this.cfgQuestSystemMinAvailable) {
            return;
        }
        World world = (World)Bukkit.getWorlds().get(0);
        Location spawn = world.getSpawnLocation();
        int generated = 0;
        for (int i = 0; i < this.cfgQuestSystemGenerateCount && !(this.treasury < (reward = this.cfgQuestSystemRewardMin + Math.random() * (this.cfgQuestSystemRewardMax - this.cfgQuestSystemRewardMin))); ++i) {
            this.treasury -= reward;
            double angle = Math.random() * Math.PI * 2.0;
            double dist = Math.random() * this.cfgQuestSystemSpawnRadius;
            double x = spawn.getX() + Math.cos(angle) * dist;
            double z = spawn.getZ() + Math.sin(angle) * dist;
            double y = world.getHighestBlockYAt((int)x, (int)z) + 1;
            Quest q2 = new Quest();
            q2.id = UUID.randomUUID();
            q2.posterId = SYSTEM_QUEST_POSTER_ID;
            q2.worldName = world.getName();
            q2.x = x;
            q2.y = y;
            q2.z = z;
            q2.reward = reward;
            q2.state = QuestState.AVAILABLE;
            this.quests.put(q2.id, q2);
            ++generated;
        }
        if (generated > 0) {
            this.broadcastNews("<gray>\u3010\u904b\u55b6\u3011\u65b0\u3057\u3044\u63a2\u7d22\u4f9d\u983c\u3092\u4f9d\u983c\u30dc\u30fc\u30c9\u306b\u63b2\u793a\u3057\u307e\u3057\u305f\u3002</gray>");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        double bankTotal;
        Player p = e.getPlayer();
        UUID u = p.getUniqueId();
        double pocket = econ.getBalance((OfflinePlayer)p);
        Bukkit.getScheduler().runTaskLater((Plugin)this, () -> this.updateScoreboard(p), 20L);
        if (!this.tutorialSeen.contains(u)) {
            Bukkit.getScheduler().runTaskLater((Plugin)this, () -> this.openTutorialGUI(p), 40L);
        }
        if (!this.hubItemIssued.contains(u)) {
            this.hubItemIssued.add(u);
            HashMap leftover = p.getInventory().addItem(new ItemStack[]{this.createHubItem()});
            for (ItemStack over : leftover.values()) {
                p.getWorld().dropItemNaturally(p.getLocation(), over);
            }
            this.msgKey(p, "welcome.first-join", new String[0]);
        }
        if (pocket + (bankTotal = this.personalBank.getOrDefault(u, 0.0) + this.fixedDeposit.getOrDefault(u, 0.0) + this.fixedDeposit2.getOrDefault(u, 0.0) + this.fixedDeposit3.getOrDefault(u, 0.0)) >= 1000000.0) {
            this.unlockAchievement(u, "millionaire", "\u8cc7\u7523\u5bb6\u306e\u8a3c");
        }
        this.deliverPendingAuctionItems(p);
        this.deliverAuctionOfflineNotices(p);
        this.deliverLoanOfflineNotices(p);
        ItemStack draft = this.auctionListingDraft.remove(u);
        if (draft != null) {
            HashMap leftover = p.getInventory().addItem(new ItemStack[]{draft});
            for (ItemStack over : leftover.values()) {
                p.getWorld().dropItemNaturally(p.getLocation(), over);
            }
            this.msgKey(p, "auction.listing-cancelled-returned", new String[0]);
        }
        if (this.activeDebts.containsKey(u) && pocket > 0.0) {
            String[] data = this.activeDebts.get(u).split(":");
            UUID lenderId = UUID.fromString(data[0]);
            double debt = Double.parseDouble(data[1]);
            double seize = Math.min(debt, pocket);
            econ.withdrawPlayer((OfflinePlayer)p, seize);
            this.bankCapital.put(lenderId, this.bankCapital.getOrDefault(lenderId, 0.0) + seize);
            debt -= seize;
            if (debt <= 0.0) {
                this.activeDebts.remove(u);
                this.loanGuarantor.remove(u);
                this.msgKey(p, "loan.login-seized-full", new String[0]);
                this.unlockAchievement(u, "first_loan_repaid", "\u521d\u3081\u3066\u306e\u5b8c\u6e08");
            } else {
                OfflinePlayer guarantorOff;
                double guarantorPocket;
                double guarantorSeize;
                this.msgKey(p, "loan.login-seized-partial", "amount", String.valueOf((long)seize));
                this.addScore(u, -20);
                UUID guarantorId = this.loanGuarantor.get(u);
                if (guarantorId != null && (guarantorSeize = Math.min(debt, guarantorPocket = econ.getBalance(guarantorOff = Bukkit.getOfflinePlayer((UUID)guarantorId)))) > 0.0) {
                    econ.withdrawPlayer(guarantorOff, guarantorSeize);
                    this.bankCapital.put(lenderId, this.bankCapital.getOrDefault(lenderId, 0.0) + guarantorSeize);
                    debt -= guarantorSeize;
                    this.addScore(guarantorId, -30);
                    this.addLog(guarantorId, "\u4fdd\u8a3c\u50b5\u52d9\u306e\u5c65\u884c: -" + (long)guarantorSeize + "\u5186 (" + p.getName() + "\u306e\u501f\u91d1\u5206)");
                    Player guarantorOnline = Bukkit.getPlayer((UUID)guarantorId);
                    if (guarantorOnline != null) {
                        this.msgKey(guarantorOnline, "loan.guarantor-seized-notice", "player", p.getName(), "amount", String.valueOf((long)guarantorSeize));
                    }
                    this.sendDiscordWebhook("\u26a0\ufe0f **\u4fdd\u8a3c\u50b5\u52d9\u57f7\u884c**: " + Bukkit.getOfflinePlayer((UUID)guarantorId).getName() + " \u304c " + p.getName() + " \u306e\u501f\u91d1 " + (long)guarantorSeize + "\u5186 \u3092\u4ee3\u308f\u308a\u306b\u652f\u6255\u3044\u307e\u3057\u305f\u3002");
                }
                if (debt <= 0.0) {
                    this.activeDebts.remove(u);
                    this.loanGuarantor.remove(u);
                    this.msgKey(p, "loan.guarantor-repaid-full", new String[0]);
                } else {
                    this.activeDebts.put(u, lenderId.toString() + ":" + debt);
                }
            }
        }
    }

    @EventHandler
    public void onInsurancePayout(PlayerDeathEvent e) {
        Player p = e.getEntity();
        UUID u = p.getUniqueId();
        long expiry = this.insuranceExpiry.getOrDefault(u, 0L);
        if (expiry <= System.currentTimeMillis()) {
            return;
        }
        long lastClaim = this.insuranceLastClaim.getOrDefault(u, 0L);
        if (System.currentTimeMillis() - lastClaim < this.cfgInsuranceClaimCooldownMs) {
            this.msgKey(p, "insurance.claim-cooldown", new String[0]);
            return;
        }
        this.insuranceLastClaim.put(u, System.currentTimeMillis());
        econ.depositPlayer((OfflinePlayer)p, this.cfgInsurancePayout);
        this.msgKey(p, "insurance.payout", "amount", String.valueOf((long)this.cfgInsurancePayout));
        this.sendToast(p, "\u4fdd\u967a\u91d1\u652f\u6255\u3044", "+" + (long)this.cfgInsurancePayout + "\u5186 \u304c\u632f\u308a\u8fbc\u307e\u308c\u307e\u3057\u305f\uff01");
        this.addLog(u, "\u4fdd\u967a\u91d1\u53d7\u7d66 +" + this.fmtCur(this.cfgInsurancePayout));
        this.sendDiscordWebhook("\ud83d\udcb0 **" + p.getName() + "** \u306b\u751f\u547d\u4fdd\u967a\u91d1 " + (long)this.cfgInsurancePayout + "\u5186 \u304c\u652f\u6255\u308f\u308c\u307e\u3057\u305f\u3002");
    }

    private void startWebDashboard() {
        if (!this.cfgWebDashboardEnabled) {
            return;
        }
        long ticks = Math.max(1L, this.cfgWebDashboardExportIntervalMinutes) * 60L * 20L;
        Bukkit.getScheduler().runTaskTimer((Plugin)this, this::exportDashboardJson, 100L, ticks);
        try {
            this.webDashboardServer = HttpServer.create(new InetSocketAddress(this.cfgWebDashboardPort), 0);
            this.webDashboardServer.createContext("/dashboard.json", this::handleDashboardRequest);
            this.webDashboardServer.createContext("/", this::handleDashboardPage);
            this.webDashboardServer.createContext("/me.json", this::handleMyDashboardRequest);
            this.webDashboardServer.createContext("/me.html", this::handleMyDashboardPage);
            this.webDashboardServer.createContext("/me/auction/bid", this::handleMyActionRequest);
            this.webDashboardServer.createContext("/me/worldstock/buy", this::handleMyActionRequest);
            this.webDashboardServer.createContext("/me/storage/pay-rent", this::handleMyActionRequest);
            this.webDashboardServer.createContext("/me/collect", this::handleMyActionRequest);
            this.webDashboardServer.setExecutor(null);
            this.webDashboardServer.start();
            this.getLogger().info("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] http://<\u30b5\u30fc\u30d0\u30fcIP>:" + this.cfgWebDashboardPort + "/ \u3067\u914d\u4fe1\u3092\u958b\u59cb\u3057\u307e\u3057\u305f\u3002(JSON API\u306f /dashboard.json)");
        }
        catch (Exception ex) {
            this.getLogger().warning("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] HTTP\u30b5\u30fc\u30d0\u30fc\u306e\u8d77\u52d5\u306b\u5931\u6557\u3057\u307e\u3057\u305f(\u30dd\u30fc\u30c8 " + this.cfgWebDashboardPort + " \u304c\u4f7f\u7528\u4e2d\u306e\u53ef\u80fd\u6027\u304c\u3042\u308a\u307e\u3059): " + ex.getMessage());
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleDashboardRequest(HttpExchange exchange) {
        try {
            byte[] body = this.lastDashboardJson.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream os = exchange.getResponseBody();){
                os.write(body);
            }
        }
        catch (Exception ex) {
            this.getLogger().warning("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] \u30ea\u30af\u30a8\u30b9\u30c8\u51e6\u7406\u4e2d\u306b\u30a8\u30e9\u30fc: " + ex.getMessage());
        }
        finally {
            exchange.close();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleDashboardPage(HttpExchange exchange) {
        try {
            byte[] body = DASHBOARD_HTML.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream os = exchange.getResponseBody();){
                os.write(body);
            }
        }
        catch (Exception ex) {
            this.getLogger().warning("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] \u30da\u30fc\u30b8\u914d\u4fe1\u4e2d\u306b\u30a8\u30e9\u30fc: " + ex.getMessage());
        }
        finally {
            exchange.close();
        }
    }

    private String hashWebPassword(String password) {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean verifyWebPassword(String stored, String password) {
        if (stored == null || password == null) {
            return false;
        }
        int sep = stored.indexOf(58);
        if (sep < 0) {
            return false;
        }
        try {
            byte[] salt = Base64.getDecoder().decode(stored.substring(0, sep));
            byte[] expected = Base64.getDecoder().decode(stored.substring(sep + 1));
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] actual = md.digest(password.getBytes(StandardCharsets.UTF_8));
            return MessageDigest.isEqual(expected, actual);
        }
        catch (Exception e) {
            return false;
        }
    }

    private <T> T runOnMainThreadSync(Callable<T> task) throws Exception {
        CompletableFuture future = new CompletableFuture();
        Bukkit.getScheduler().runTask((Plugin)this, () -> {
            try {
                future.complete(task.call());
            }
            catch (Throwable t) {
                future.completeExceptionally(t);
            }
        });
        return future.get(5L, TimeUnit.SECONDS);
    }

    private String getFormParam(String bodyCache, String name) {
        if (bodyCache == null) {
            return null;
        }
        for (String pair : bodyCache.split("&")) {
            String key;
            int eq = pair.indexOf(61);
            String string = key = eq >= 0 ? pair.substring(0, eq) : pair;
            if (!key.equals(name)) continue;
            String value = eq >= 0 ? pair.substring(eq + 1) : "";
            return URLDecoder.decode(value, StandardCharsets.UTF_8);
        }
        return null;
    }

    private String readRequestBody(HttpExchange exchange) throws IOException {
        try (InputStream is = exchange.getRequestBody();){
            String string = new String(is.readAllBytes(), StandardCharsets.UTF_8);
            return string;
        }
    }

    private void sendJsonResponse(HttpExchange exchange, int status, String json) throws IOException {
        byte[] body = json.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
        exchange.sendResponseHeaders(status, body.length);
        try (OutputStream os = exchange.getResponseBody();){
            os.write(body);
        }
    }

    private UUID resolveDashboardOwner(String token) {
        if (token == null) {
            return null;
        }
        for (Map.Entry<UUID, String> entry : this.dashboardTokens.entrySet()) {
            if (!entry.getValue().equals(token)) continue;
            return entry.getKey();
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleMyActionRequest(HttpExchange exchange) {
        try {
            boolean isOnline;
            if (!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                this.sendJsonResponse(exchange, 405, "{\"ok\":false,\"message\":\"POST only\"}");
                return;
            }
            String body = this.readRequestBody(exchange);
            String token = this.getFormParam(body, "token");
            UUID owner = this.resolveDashboardOwner(token);
            if (owner == null) {
                this.sendJsonResponse(exchange, 404, "{\"ok\":false,\"message\":\"invalid token\"}");
                return;
            }
            String path = exchange.getRequestURI().getPath();
            Player online = Bukkit.getPlayer((UUID)owner);
            boolean bl = isOnline = online != null && online.isOnline();
            if (path.equals("/me/collect")) {
                if (!isOnline) {
                    this.sendJsonResponse(exchange, 409, "{\"ok\":false,\"message\":\"\u53d7\u53d6\u7bb1\u306e\u53d7\u53d6\u306f\u30ed\u30b0\u30a4\u30f3\u4e2d\u306e\u307f\u64cd\u4f5c\u3067\u304d\u307e\u3059\"}");
                    return;
                }
            } else if (!isOnline) {
                boolean authorized;
                String password = this.getFormParam(body, "password");
                boolean bl2 = authorized = this.verifyWebPassword(this.webPasswordHash.get(owner), password) || this.verifyWebPassword(this.webAdminPasswordHash.get(owner), password);
                if (!authorized) {
                    this.sendJsonResponse(exchange, 403, "{\"ok\":false,\"message\":\"\u30d1\u30b9\u30ef\u30fc\u30c9\u304c\u9055\u3044\u307e\u3059(\u672a\u8a2d\u5b9a\u306e\u5834\u5408\u306f\u30b2\u30fc\u30e0\u5185\u3067 /meco webpage password \u3092\u8a2d\u5b9a\u3057\u3066\u304f\u3060\u3055\u3044)\"}");
                    return;
                }
            }
            Player actor = isOnline ? online : Bukkit.getOfflinePlayer((UUID)owner);
            String resultJson = this.runOnMainThreadSync(() -> this.lambda$handleMyActionRequest$0(path, (OfflinePlayer)actor, body));
            this.sendJsonResponse(exchange, 200, resultJson);
        }
        catch (Exception ex) {
            this.getLogger().warning("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] \u500b\u4eba\u30da\u30fc\u30b8\u64cd\u4f5c\u30ea\u30af\u30a8\u30b9\u30c8\u306e\u51e6\u7406\u4e2d\u306b\u30a8\u30e9\u30fc: " + ex.getMessage());
            try {
                this.sendJsonResponse(exchange, 500, "{\"ok\":false,\"message\":\"internal error\"}");
            }
            catch (IOException iOException) {
                // empty catch block
            }
        }
        finally {
            exchange.close();
        }
    }

    private String handleMyActionOnMainThread(String path, OfflinePlayer p, String body) {
        try {
            switch (path) {
                case "/me/auction/bid": {
                    UUID auctionId = UUID.fromString(this.getFormParam(body, "auction_id"));
                    double amount = Double.parseDouble(this.getFormParam(body, "amount"));
                    this.placeAuctionBid(p, auctionId, amount);
                    return "{\"ok\":true}";
                }
                case "/me/worldstock/buy": {
                    String symbol = this.getFormParam(body, "symbol");
                    int qty = Integer.parseInt(this.getFormParam(body, "qty"));
                    if (symbol == null || qty <= 0) {
                        return "{\"ok\":false,\"message\":\"invalid parameters\"}";
                    }
                    this.executeWorldStockBuy(p, symbol.toUpperCase(), qty, null);
                    return "{\"ok\":true}";
                }
                case "/me/storage/pay-rent": {
                    boolean ok = this.payOrStartStorageRent(p);
                    return "{\"ok\":" + ok + "}";
                }
                case "/me/collect": {
                    if (p instanceof Player) {
                        Player online = (Player)p;
                        this.deliverPendingAuctionItems(online);
                        this.deliverAuctionOfflineNotices(online);
                        this.deliverLoanOfflineNotices(online);
                    }
                    return "{\"ok\":true}";
                }
            }
            return "{\"ok\":false,\"message\":\"unknown action\"}";
        }
        catch (Exception ex) {
            return "{\"ok\":false,\"message\":\"" + this.jsonEscape(ex.getMessage() != null ? ex.getMessage() : "error") + "\"}";
        }
    }

    private String getQueryParam(HttpExchange exchange, String name) {
        String query = exchange.getRequestURI().getRawQuery();
        if (query == null) {
            return null;
        }
        for (String pair : query.split("&")) {
            String key;
            int eq = pair.indexOf(61);
            String string = key = eq >= 0 ? pair.substring(0, eq) : pair;
            if (!key.equals(name)) continue;
            String value = eq >= 0 ? pair.substring(eq + 1) : "";
            return URLDecoder.decode(value, StandardCharsets.UTF_8);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleMyDashboardRequest(HttpExchange exchange) {
        try {
            int status;
            byte[] body;
            String token = this.getQueryParam(exchange, "token");
            UUID owner = null;
            if (token != null) {
                for (Map.Entry<UUID, String> entry : this.dashboardTokens.entrySet()) {
                    if (!entry.getValue().equals(token)) continue;
                    owner = entry.getKey();
                    break;
                }
            }
            if (owner == null) {
                body = "{\"error\":\"invalid token\"}".getBytes(StandardCharsets.UTF_8);
                status = 404;
            } else {
                body = this.buildMyDashboardJson(owner).getBytes(StandardCharsets.UTF_8);
                status = 200;
            }
            exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
            exchange.sendResponseHeaders(status, body.length);
            try (OutputStream os = exchange.getResponseBody();){
                os.write(body);
            }
        }
        catch (Exception ex) {
            this.getLogger().warning("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] \u500b\u4eba\u30da\u30fc\u30b8\u7528\u30ea\u30af\u30a8\u30b9\u30c8\u51e6\u7406\u4e2d\u306b\u30a8\u30e9\u30fc: " + ex.getMessage());
        }
        finally {
            exchange.close();
        }
    }

    private String buildMyDashboardJson(UUID owner) {
        OfflinePlayer op = Bukkit.getOfflinePlayer((UUID)owner);
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"name\":\"").append(this.jsonEscape(op.getName() != null ? op.getName() : "?")).append("\",");
        Player onlineOwner = Bukkit.getPlayer((UUID)owner);
        json.append("\"online\":").append(onlineOwner != null && onlineOwner.isOnline()).append(",");
        json.append("\"password_set\":").append(this.webPasswordHash.containsKey(owner)).append(",");
        json.append("\"pocket\":").append((long)econ.getBalance(op)).append(",");
        json.append("\"bank\":").append((long)this.personalBank.getOrDefault(owner, 0.0).doubleValue()).append(",");
        json.append("\"credit_score\":").append(this.getScore(owner)).append(",");
        json.append("\"achievement_count\":").append(this.unlockedAchievements.getOrDefault(owner, new HashSet()).size()).append(",");
        json.append("\"world_stocks\":[");
        HashMap holdings = this.playerWorldStocks.getOrDefault(owner, new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(owner, new HashMap());
        boolean firstStock = true;
        for (Map.Entry holding : holdings.entrySet()) {
            if ((Integer)holding.getValue() <= 0) continue;
            if (!firstStock) {
                json.append(",");
            }
            firstStock = false;
            String symbol = (String)holding.getKey();
            double avgCost = avgCosts.getOrDefault(symbol, 0.0);
            WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
            double value = this.worldStockPositionValue(q, avgCost, (Integer)holding.getValue());
            json.append("{\"symbol\":\"").append(this.jsonEscape(symbol)).append("\"").append(",\"qty\":").append(holding.getValue()).append(",\"avg_cost\":").append((long)avgCost).append(",\"value\":").append((long)value).append("}");
        }
        json.append("],");
        json.append("\"logs\":[");
        LinkedList logs = this.transactionLogs.getOrDefault(owner, new LinkedList());
        boolean firstLog = true;
        for (String line : logs) {
            if (!firstLog) {
                json.append(",");
            }
            firstLog = false;
            json.append("\"").append(this.jsonEscape(line)).append("\"");
        }
        json.append("],");
        json.append("\"auctions\":[");
        boolean firstAuction = true;
        int shownAuctions = 0;
        for (UUID id : this.auctionSeller.keySet()) {
            if (shownAuctions >= 60) break;
            ItemStack auctionedItem = this.auctionItem.get(id);
            UUID seller = this.auctionSeller.get(id);
            if (auctionedItem == null || seller == null) continue;
            ++shownAuctions;
            if (!firstAuction) {
                json.append(",");
            }
            firstAuction = false;
            double bid = this.auctionBid.getOrDefault(id, 0.0);
            UUID bidder = this.auctionBidder.get(id);
            Double buyout = this.auctionBuyoutPrice.get(id);
            long remainMs = this.auctionEndTime.getOrDefault(id, 0L) - System.currentTimeMillis();
            json.append("{\"id\":\"").append(id).append("\"").append(",\"item\":\"").append(this.jsonEscape(auctionedItem.getType().name())).append("\"").append(",\"seller\":\"").append(this.jsonEscape(Bukkit.getOfflinePlayer((UUID)seller).getName())).append("\"").append(",\"is_own\":").append(seller.equals(owner)).append(",\"bid\":").append((long)bid).append(",\"has_bidder\":").append(bidder != null).append(",\"buyout\":").append(buyout != null ? String.valueOf((long)buyout.doubleValue()) : "null").append(",\"remain_ms\":").append(Math.max(0L, remainMs)).append("}");
        }
        json.append("],");
        Long dueTime = this.storageRentDueTime.get(owner);
        json.append("\"storage\":{\"rented\":").append(dueTime != null).append(",\"due_at\":").append(dueTime != null ? dueTime : 0L).append(",\"rent_amount\":").append((long)this.cfgStorageRentAmount).append("}");
        json.append("}");
        return json.toString();
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private void handleMyDashboardPage(HttpExchange exchange) {
        try {
            byte[] body = MY_DASHBOARD_HTML.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, body.length);
            try (OutputStream os = exchange.getResponseBody();){
                os.write(body);
            }
        }
        catch (Exception ex) {
            this.getLogger().warning("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] \u500b\u4eba\u30da\u30fc\u30b8\u914d\u4fe1\u4e2d\u306b\u30a8\u30e9\u30fc: " + ex.getMessage());
        }
        finally {
            exchange.close();
        }
    }

    private String jsonEscape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
    }

    private String joinLongs(Collection<Long> values) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (long v : values) {
            if (!first) {
                sb.append(",");
            }
            sb.append(v);
            first = false;
        }
        return sb.toString();
    }

    private void exportDashboardJson() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        long now = System.currentTimeMillis();
        json.append("\"generated_at\":").append(now).append(",");
        json.append("\"treasury\":").append((long)this.treasury).append(",");
        HashSet<UUID> targets = new HashSet<UUID>(this.personalBank.keySet());
        for (Object online : Bukkit.getOnlinePlayers()) {
            targets.add(online.getUniqueId());
        }
        ArrayList<Map.Entry<UUID, Double>> ranking = new ArrayList<Map.Entry<UUID, Double>>();
        for (UUID uUID : targets) {
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer((UUID)uUID);
            double pocketAmt = 0.0;
            if (offlinePlayer.isOnline() && offlinePlayer.getPlayer() != null) {
                pocketAmt = econ.getBalance((OfflinePlayer)offlinePlayer.getPlayer());
            } else if (econ.hasAccount(offlinePlayer)) {
                pocketAmt = econ.getBalance(offlinePlayer);
            }
            double bankAmt = this.personalBank.getOrDefault(uUID, 0.0);
            double fixedAmt = this.fixedDeposit.getOrDefault(uUID, 0.0) + this.fixedDeposit2.getOrDefault(uUID, 0.0) + this.fixedDeposit3.getOrDefault(uUID, 0.0);
            ranking.add(Map.entry(uUID, pocketAmt + bankAmt + fixedAmt));
        }
        ranking.sort((a, b) -> Double.compare((Double)b.getValue(), (Double)a.getValue()));
        json.append("\"leaderboard\":[");
        int rank = 0;
        for (Map.Entry entry : ranking) {
            if (rank >= 20) break;
            String name = Bukkit.getOfflinePlayer((UUID)((UUID)entry.getKey())).getName();
            if (name == null) continue;
            if (rank > 0) {
                json.append(",");
            }
            int score = this.getScore((UUID)entry.getKey());
            json.append("{\"rank\":").append(rank + 1).append(",\"name\":\"").append(this.jsonEscape(name)).append("\"").append(",\"total_assets\":").append((long)((Double)entry.getValue()).doubleValue()).append("").append(",\"credit_score\":").append(score).append("}");
            ++rank;
        }
        json.append("],");
        double d = 0.0;
        for (double d2 : this.govDebt.values()) {
            d += d2;
        }
        json.append("\"active_event\":\"").append(this.jsonEscape(this.activeEconomyEvent)).append("\",");
        json.append("\"weekly\":{").append("\"trade_volume\":").append((long)this.weeklyTradeVolume).append("},");
        json.append("\"total_gov_debt\":").append((long)d).append(",");
        this.dashboardHistoryTimestamps.addLast(now);
        this.dashboardHistoryTreasury.addLast((long)this.treasury);
        while (this.dashboardHistoryTimestamps.size() > 60) {
            this.dashboardHistoryTimestamps.removeFirst();
        }
        while (this.dashboardHistoryTreasury.size() > 60) {
            this.dashboardHistoryTreasury.removeFirst();
        }
        json.append("\"history\":{").append("\"labels\":[").append(this.joinLongs(this.dashboardHistoryTimestamps)).append("],").append("\"treasury\":[").append(this.joinLongs(this.dashboardHistoryTreasury)).append("]").append("}");
        json.append(",");
        HashSet<String> distinctSymbols = new HashSet<String>();
        int activeInvestors = 0;
        double totalPortfolioValue = 0.0;
        HashMap<String, Integer> symbolHolders = new HashMap<String, Integer>();
        HashMap<String, Integer> symbolTotalShares = new HashMap<String, Integer>();
        for (Map.Entry<UUID, HashMap<String, Integer>> entry : this.playerWorldStocks.entrySet()) {
            UUID owner = entry.getKey();
            if (this.investmentFunds.containsKey(owner)) continue;
            HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(owner, new HashMap());
            boolean hasPositiveHolding = false;
            for (Map.Entry<String, Integer> holding : entry.getValue().entrySet()) {
                int qty = holding.getValue();
                if (qty <= 0) continue;
                hasPositiveHolding = true;
                String symbol = holding.getKey();
                distinctSymbols.add(symbol);
                symbolHolders.merge(symbol, 1, Integer::sum);
                symbolTotalShares.merge(symbol, qty, Integer::sum);
                WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
                totalPortfolioValue += this.worldStockPositionValue(q, avgCosts.getOrDefault(symbol, 0.0), qty);
            }
            if (!hasPositiveHolding) continue;
            ++activeInvestors;
        }
        ArrayList topSymbols = new ArrayList(symbolTotalShares.keySet());
        topSymbols.sort((a, b) -> Integer.compare(symbolTotalShares.getOrDefault(b, 0), symbolTotalShares.getOrDefault(a, 0)));
        json.append("\"world_stock\":{").append("\"active_investors\":").append(activeInvestors).append(",").append("\"distinct_symbols\":").append(distinctSymbols.size()).append(",").append("\"total_portfolio_value\":").append((long)totalPortfolioValue).append(",").append("\"top_symbols\":[");
        int shownSymbols = 0;
        for (String symbol : topSymbols) {
            if (shownSymbols >= 5) break;
            if (shownSymbols > 0) {
                json.append(",");
            }
            json.append("{\"symbol\":\"").append(this.jsonEscape(symbol)).append("\"").append(",\"holders\":").append(symbolHolders.getOrDefault(symbol, 0)).append(",\"total_shares\":").append(symbolTotalShares.getOrDefault(symbol, 0)).append("}");
            ++shownSymbols;
        }
        json.append("]}");
        json.append(",");
        json.append("\"resource_shop\":[");
        boolean first = true;
        for (Map.Entry<Material, Double> entry : RESOURCE_BASE_PRICES.entrySet()) {
            Material mat = entry.getKey();
            double base = entry.getValue();
            double current = this.getResourcePrice(mat);
            double trendPercent = base > 0.0 ? (current - base) / base * 100.0 : 0.0;
            trendPercent = (double)Math.round(trendPercent * 100.0) / 100.0;
            if (!first) {
                json.append(",");
            }
            json.append("{\"material\":\"").append(this.jsonEscape(mat.name())).append("\"").append(",\"price\":").append((long)current).append(",\"base_price\":").append((long)base).append(",\"trend_percent\":").append(trendPercent).append("}");
            first = false;
        }
        json.append("],");
        json.append("\"merchant\":{").append("\"refresh_at\":").append(this.merchantRefreshAt).append(",").append("\"deals\":[");
        first = true;
        for (MerchantDeal deal : this.merchantDeals) {
            if (!first) {
                json.append(",");
            }
            json.append("{\"material\":\"").append(this.jsonEscape(deal.material.name())).append("\"").append(",\"discount_percent\":").append(deal.discountPercent).append(",\"stock_remaining\":").append(deal.stockRemaining).append(",\"stock_total\":").append(deal.stockTotal).append("}");
            first = false;
        }
        json.append("]},");
        int qAvailable = 0;
        int qInProgress = 0;
        int qCooldown = 0;
        int qSystemPosted = 0;
        int qPlayerPosted = 0;
        for (Quest q : this.quests.values()) {
            switch (q.state.ordinal()) {
                case 0: {
                    ++qAvailable;
                    break;
                }
                case 1: {
                    ++qInProgress;
                    break;
                }
                case 2: {
                    ++qCooldown;
                }
            }
            if (q.posterId.equals(SYSTEM_QUEST_POSTER_ID)) {
                ++qSystemPosted;
                continue;
            }
            ++qPlayerPosted;
        }
        json.append("\"quests\":{").append("\"available\":").append(qAvailable).append(",").append("\"in_progress\":").append(qInProgress).append(",").append("\"cooldown\":").append(qCooldown).append(",").append("\"system_posted\":").append(qSystemPosted).append(",").append("\"player_posted\":").append(qPlayerPosted).append("},");
        int activePlans = 0;
        double totalOutstanding = 0.0;
        for (List<InstallmentPlan> plans : this.installmentPlans.values()) {
            for (InstallmentPlan plan : plans) {
                ++activePlans;
                totalOutstanding += plan.installmentAmount * (double)plan.installmentsRemaining;
            }
        }
        json.append("\"installments\":{").append("\"active_plans\":").append(activePlans).append(",").append("\"total_outstanding\":").append((long)totalOutstanding).append("},");
        json.append("\"treasure\":{").append("\"active\":").append(this.treasureActive).append(",").append("\"reward\":").append(this.treasureActive ? (long)this.treasureReward : 0L).append("},");
        int totalTicketsSold = 0;
        for (int t : this.lotteryTickets.values()) {
            totalTicketsSold += t;
        }
        json.append("\"lottery\":{").append("\"pool\":").append((long)this.lotteryPool).append(",").append("\"draw_at\":").append(this.lotteryDrawAt).append(",").append("\"total_tickets_sold\":").append(totalTicketsSold).append(",").append("\"last_winner_name\":\"").append(this.jsonEscape(this.lastLotteryWinnerName)).append("\"").append(",\"last_winner_amount\":").append((long)this.lastLotteryWinnerAmount).append("},");
        json.append("\"storage\":{\"active_renters\":").append(this.storageRentDueTime.size()).append("},");
        json.append("\"auction\":{\"active_listings\":").append(this.auctionSeller.size()).append("},");
        json.append("\"selfcheck\":{").append("\"checked_at\":").append(this.lastSelfCheckAt).append(",").append("\"issues\":[");
        for (int i = 0; i < this.lastSelfCheckIssues.size(); ++i) {
            if (i > 0) {
                json.append(",");
            }
            json.append("\"").append(this.jsonEscape(this.lastSelfCheckIssues.get(i))).append("\"");
        }
        json.append("]}");
        json.append("}");
        this.lastDashboardJson = json.toString();
        try {
            File dir = new File(this.getDataFolder(), "webexport");
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, "dashboard.json");
            try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8);){
                writer.write(this.lastDashboardJson);
            }
        }
        catch (Exception ex) {
            this.getLogger().warning("[Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9] JSON\u30d5\u30a1\u30a4\u30eb\u306e\u66f8\u304d\u51fa\u3057\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + ex.getMessage());
        }
    }

    private List<String> runSelfCheck(boolean autoFix) {
        ArrayList<String> issues = new ArrayList<String>();
        HashSet<UUID> collateralKeys = new HashSet<UUID>();
        collateralKeys.addAll(this.collateralItem.keySet());
        collateralKeys.addAll(this.collateralLoanAmount.keySet());
        collateralKeys.addAll(this.collateralDueTime.keySet());
        collateralKeys.addAll(this.collateralLender.keySet());
        for (UUID key : collateralKeys) {
            boolean complete = this.collateralItem.containsKey(key) && this.collateralLoanAmount.containsKey(key) && this.collateralDueTime.containsKey(key) && this.collateralLender.containsKey(key);
            if (complete) continue;
            issues.add("\u62c5\u4fdd\u878d\u8cc7\u30c7\u30fc\u30bf\u4e0d\u6574\u5408: " + String.valueOf(key) + " \u306e\u30c7\u30fc\u30bf\u304c\u4e00\u90e8\u6b20\u843d\u3057\u3066\u3044\u307e\u3059");
        }
        for (UUID auctionId : new HashSet<UUID>(this.auctionSeller.keySet())) {
            if (this.auctionItem.containsKey(auctionId) && this.auctionEndTime.containsKey(auctionId)) continue;
            issues.add("\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u30c7\u30fc\u30bf\u4e0d\u6574\u5408: " + String.valueOf(auctionId) + " \u306e\u30a2\u30a4\u30c6\u30e0\u307e\u305f\u306f\u7d42\u4e86\u6642\u523b\u304c\u6b20\u843d\u3057\u3066\u3044\u307e\u3059");
            if (!autoFix) continue;
            this.auctionSeller.remove(auctionId);
            this.auctionBid.remove(auctionId);
            this.auctionBidder.remove(auctionId);
        }
        for (UUID k : this.personalBank.keySet()) {
            if (!(this.personalBank.get(k) < 0.0)) continue;
            issues.add("personalBank: " + String.valueOf(k) + " \u306e\u6b8b\u9ad8\u304c\u8ca0\u306e\u5024\u3067\u3059(" + String.valueOf(this.personalBank.get(k)) + "\u5186)");
        }
        return issues;
    }

    private void resetPlayerEconomyData(UUID u) {
        this.personalBank.remove(u);
        this.bankers.remove(u);
        this.bankCapital.remove(u);
        this.publishedLoans.remove(u);
        this.publishedLoans2.remove(u);
        this.publishedLoans3.remove(u);
        this.activeDebts.remove(u);
        this.fixedDeposit.remove(u);
        this.fixedDepositUnlockTime.remove(u);
        this.fixedDeposit2.remove(u);
        this.fixedDepositUnlockTime2.remove(u);
        this.fixedDeposit3.remove(u);
        this.fixedDepositUnlockTime3.remove(u);
        this.govDebt.remove(u);
        this.govDebtDueTime.remove(u);
        this.creditScore.remove(u);
        this.transactionLogs.remove(u);
        this.pendingConfirmation.remove(u);
        this.pendingConfirmationTime.remove(u);
        this.donationScoreToday.remove(u);
        this.donationScoreResetAt.remove(u);
        this.welfareCountToday.remove(u);
        this.welfareCountResetAt.remove(u);
        this.auctionPendingItems.remove(u);
        this.auctionListingDraft.remove(u);
        this.auctionListingStartPrice.remove(u);
        this.collateralItem.remove(u);
        this.collateralLoanAmount.remove(u);
        this.collateralDueTime.remove(u);
        this.collateralLender.remove(u);
        this.insuranceExpiry.remove(u);
        this.insuranceLastClaim.remove(u);
        this.vipStipendClaimedAt.remove(u);
        this.unlockedAchievements.remove(u);
        this.playerWorldStocks.remove(u);
        this.playerWorldStockAvgCost.remove(u);
        this.worldStockTradesToday.remove(u);
        this.worldStockAmountToday.remove(u);
        this.worldStockProfitToday.remove(u);
        this.worldStockDailyResetAt.remove(u);
        this.worldStockLastTradeTime.remove(u);
        this.worldStockAlerts.remove(u);
    }

    @EventHandler(priority=EventPriority.LOWEST, ignoreCancelled=true)
    public void onPlayerChatInput(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        UUID u = p.getUniqueId();
        if (!this.awaitingChatInput.containsKey(u)) {
            return;
        }
        e.setCancelled(true);
        String type = this.awaitingChatInput.remove(u);
        String raw = e.getMessage().trim();
        Bukkit.getScheduler().runTask((Plugin)this, () -> {
            double amount;
            if (raw.equals("\u30ad\u30e3\u30f3\u30bb\u30eb") || raw.equalsIgnoreCase("cancel")) {
                if (type.equals("auction_list_price") || type.equals("auction_buyout_price")) {
                    ItemStack draft = this.auctionListingDraft.remove(u);
                    this.auctionListingStartPrice.remove(u);
                    if (draft != null) {
                        HashMap leftover = p.getInventory().addItem(new ItemStack[]{draft});
                        for (ItemStack over : leftover.values()) {
                            p.getWorld().dropItem(p.getLocation(), over);
                        }
                        this.msgKey(p, "input.cancelled-auction-item-returned", new String[0]);
                        return;
                    }
                }
                this.msgKey(p, "input.cancelled", new String[0]);
                return;
            }
            if (type.equals("webpage_password")) {
                if (raw.length() < 4) {
                    this.msgKey(p, "webpage.password-too-short", new String[0]);
                    this.errorSound(p);
                    return;
                }
                this.webPasswordHash.put(u, this.hashWebPassword(raw));
                this.msgKey(p, "webpage.password-set", new String[0]);
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                return;
            }
            if (type.startsWith("config_edit:")) {
                Object parsed;
                if (!p.hasPermission(PERM_ADMIN)) {
                    this.msgKey(p, "admin.no-permission", new String[0]);
                    return;
                }
                String key = type.substring("config_edit:".length());
                FileConfiguration cfg = this.getConfig();
                Object current = cfg.get(key);
                try {
                    parsed = current instanceof Boolean ? Boolean.valueOf(Boolean.parseBoolean(raw)) : (current instanceof Integer ? Integer.valueOf(Integer.parseInt(raw)) : (current instanceof Long ? Long.valueOf(Long.parseLong(raw)) : (current instanceof Double || current instanceof Float ? Double.valueOf(Double.parseDouble(raw)) : raw)));
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "config.invalid-value", "value", raw);
                    this.errorSound(p);
                    this.openConfigEditorGUI(p, this.configEditorPage.getOrDefault(u, 0));
                    return;
                }
                cfg.set(key, parsed);
                this.saveConfig();
                this.reloadConfig();
                this.loadConfigValues();
                this.loadMessages();
                this.msgKey(p, "config.updated", "key", key, "value", String.valueOf(parsed));
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                this.openConfigEditorGUI(p, this.configEditorPage.getOrDefault(u, 0));
                return;
            }
            if (type.equals("admin_search_player")) {
                if (!p.hasPermission(PERM_ADMIN)) {
                    this.msgKey(p, "admin.no-permission", new String[0]);
                    return;
                }
                OfflinePlayer target = Bukkit.getOfflinePlayer((String)raw);
                if (target.getName() == null || !target.hasPlayedBefore() && !target.isOnline()) {
                    this.msgKey(p, "admin.player-not-found", "name", raw);
                    return;
                }
                this.openAdminPlayerDetailGUI(p, target.getUniqueId());
                return;
            }
            if (type.equals("world_stock_search")) {
                String symbol = raw.trim().toUpperCase();
                if (symbol.isEmpty() || !symbol.matches("[A-Z0-9.\\-^=]{1,15}")) {
                    this.msgKey(p, "worldstock.invalid-symbol", new String[0]);
                    return;
                }
                this.msgKey(p, "worldstock.searching", "symbol", symbol);
                this.fetchWorldStockQuote(symbol, quote -> {
                    if (quote == null) {
                        this.msgKey(p, "worldstock.not-found", "symbol", symbol);
                        this.errorSound(p);
                        return;
                    }
                    this.openWorldStockDetailGUI(p, quote.symbol);
                });
                return;
            }
            if (type.startsWith("fund_stock_search:")) {
                UUID fundId = UUID.fromString(type.substring("fund_stock_search:".length()));
                InvestmentFund fund = this.investmentFunds.get(fundId);
                if (fund == null || !fund.manager.equals(u)) {
                    this.msgKey(p, "fund.not-manager", new String[0]);
                    return;
                }
                String symbol = raw.trim().toUpperCase();
                if (symbol.isEmpty() || !symbol.matches("[A-Z0-9.\\-^=]{1,15}")) {
                    this.msgKey(p, "worldstock.invalid-symbol", new String[0]);
                    return;
                }
                this.msgKey(p, "worldstock.searching", "symbol", symbol);
                this.fetchWorldStockQuote(symbol, quote -> {
                    if (quote == null) {
                        this.msgKey(p, "worldstock.not-found", "symbol", symbol);
                        this.errorSound(p);
                        return;
                    }
                    this.openFundStockDetailGUI(p, fund.id, quote.symbol);
                });
                return;
            }
            if (type.equals("group_create_name")) {
                this.createGroupAccount(p, raw);
                return;
            }
            if (type.equals("auction_search")) {
                String q2 = raw.trim();
                if (q2.equalsIgnoreCase("\u30af\u30ea\u30a2") || q2.equalsIgnoreCase("clear")) {
                    this.auctionSearchQuery.remove(u);
                    this.msgKey(p, "auction.search-cleared", new String[0]);
                } else {
                    this.auctionSearchQuery.put(u, q2);
                    this.msgKey(p, "auction.search-set", "query", q2);
                }
                this.openAuctionGUI(p);
                return;
            }
            try {
                amount = Double.parseDouble(raw);
            }
            catch (NumberFormatException ex) {
                this.msgKey(p, "common.invalid-number", new String[0]);
                return;
            }
            if (amount < 0.0 || amount == 0.0 && !type.equals("auction_buyout_price") && !type.startsWith("admin_setcredit:") && !type.equals("trade_money")) {
                this.msgKey(p, "common.amount-must-be-positive", new String[0]);
                return;
            }
            double pocket = econ.getBalance((OfflinePlayer)p);
            double bank = this.personalBank.getOrDefault(u, 0.0);
            if (type.equals("quest_post")) {
                long postedByMe = this.quests.values().stream().filter(q -> q.posterId.equals(u)).count();
                if (postedByMe >= (long)this.cfgQuestMaxPerPlayer) {
                    this.msgKey(p, "quest.max-reached", "count", String.valueOf(this.cfgQuestMaxPerPlayer));
                    return;
                }
                if (pocket < amount) {
                    this.msgKey(p, "quest.reward-insufficient", new String[0]);
                    return;
                }
                econ.withdrawPlayer((OfflinePlayer)p, amount);
                Quest q3 = new Quest();
                q3.id = UUID.randomUUID();
                q3.posterId = u;
                Location loc = p.getLocation();
                q3.worldName = loc.getWorld().getName();
                q3.x = loc.getX();
                q3.y = loc.getY();
                q3.z = loc.getZ();
                q3.reward = amount;
                q3.state = QuestState.AVAILABLE;
                this.quests.put(q3.id, q3);
                this.msgKey(p, "quest.posted", "amount", String.valueOf((long)amount));
                this.addLog(u, "\u4f9d\u983c\u30dc\u30fc\u30c9\u306b\u63b2\u793a -" + (long)amount + "\u5186(\u30a8\u30b9\u30af\u30ed\u30fc)");
                this.sendDiscordWebhook("\ud83d\udccb **" + p.getName() + "** \u304c\u63a2\u7d22\u4f9d\u983c\u3092\u63b2\u793a\u3057\u307e\u3057\u305f\u3002\u5831\u916c: " + this.fmtCur(amount));
                return;
            }
            if (type.equals("team_quest_post_reward")) {
                this.awaitingChatInput.put(u, "team_quest_post_size:" + amount);
                this.msgKey(p, "quest.team-post-prompt-size", "max", String.valueOf(this.cfgQuestTeamMaxSize));
                return;
            }
            if (type.startsWith("team_quest_post_size:")) {
                double teamReward;
                try {
                    teamReward = Double.parseDouble(type.substring("team_quest_post_size:".length()));
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-number", new String[0]);
                    return;
                }
                if (amount != Math.floor(amount)) {
                    this.msgKey(p, "common.invalid-number", new String[0]);
                    return;
                }
                int teamSize = (int)amount;
                if (teamSize <= 1) {
                    this.msgKey(p, "quest.team-size-too-small", new String[0]);
                    return;
                }
                if (teamSize > this.cfgQuestTeamMaxSize) {
                    this.msgKey(p, "quest.team-size-too-large", "max", String.valueOf(this.cfgQuestTeamMaxSize));
                    return;
                }
                long postedByMe = this.quests.values().stream().filter(q -> q.posterId.equals(u)).count();
                if (postedByMe >= (long)this.cfgQuestMaxPerPlayer) {
                    this.msgKey(p, "quest.max-reached", "count", String.valueOf(this.cfgQuestMaxPerPlayer));
                    return;
                }
                double totalEscrow = teamReward * (double)teamSize;
                if (pocket < totalEscrow) {
                    this.msgKey(p, "quest.reward-insufficient", new String[0]);
                    return;
                }
                econ.withdrawPlayer((OfflinePlayer)p, totalEscrow);
                Quest q4 = new Quest();
                q4.id = UUID.randomUUID();
                q4.posterId = u;
                Location loc = p.getLocation();
                q4.worldName = loc.getWorld().getName();
                q4.x = loc.getX();
                q4.y = loc.getY();
                q4.z = loc.getZ();
                q4.reward = teamReward;
                q4.requiredTeamSize = teamSize;
                q4.state = QuestState.AVAILABLE;
                this.quests.put(q4.id, q4);
                this.msgKey(p, "quest.posted", "amount", String.valueOf((long)totalEscrow));
                this.addLog(u, "\u30c1\u30fc\u30e0\u4f9d\u983c\u3092\u30dc\u30fc\u30c9\u306b\u63b2\u793a -" + (long)totalEscrow + "\u5186(\u30a8\u30b9\u30af\u30ed\u30fc\u3001" + teamSize + "\u4eba\u00d7" + (long)teamReward + "\u5186)");
                this.sendDiscordWebhook("\ud83d\udccb **" + p.getName() + "** \u304c\u30c1\u30fc\u30e0\u63a2\u7d22\u4f9d\u983c(" + teamSize + "\u4eba)\u3092\u63b2\u793a\u3057\u307e\u3057\u305f\u3002\u4e00\u4eba\u3042\u305f\u308a\u5831\u916c: " + this.fmtCur(teamReward));
                return;
            }
            if (type.startsWith("world_stock_alert:")) {
                String symbol = type.substring("world_stock_alert:".length());
                double thresholdPercent = amount;
                WorldStockQuote cachedQ = this.worldStockQuoteCache.get(symbol);
                if (cachedQ != null) {
                    WorldStockAlert alert = new WorldStockAlert();
                    alert.id = UUID.randomUUID();
                    alert.symbol = symbol;
                    alert.baselinePrice = cachedQ.price;
                    alert.thresholdPercent = thresholdPercent;
                    this.worldStockAlerts.computeIfAbsent(u, k -> new ArrayList()).add(alert);
                    this.msgKey(p, "worldstock.alert-set", "symbol", symbol, "percent", String.format("%.1f", thresholdPercent));
                } else {
                    this.msgKey(p, "worldstock.searching", "symbol", symbol);
                    this.fetchWorldStockQuote(symbol, quote -> {
                        if (quote == null) {
                            this.msgKey(p, "worldstock.not-found", "symbol", symbol);
                            this.errorSound(p);
                            return;
                        }
                        WorldStockAlert alert = new WorldStockAlert();
                        alert.id = UUID.randomUUID();
                        alert.symbol = quote.symbol;
                        alert.baselinePrice = quote.price;
                        alert.thresholdPercent = thresholdPercent;
                        this.worldStockAlerts.computeIfAbsent(u, k -> new ArrayList()).add(alert);
                        this.msgKey(p, "worldstock.alert-set", "symbol", quote.symbol, "percent", String.format("%.1f", thresholdPercent));
                    });
                }
                return;
            }
            if (type.startsWith("resource_sell_qty:") || type.startsWith("resource_buy_qty:")) {
                Material rmat;
                boolean isSell = type.startsWith("resource_sell_qty:");
                String matName = type.substring((isSell ? "resource_sell_qty:" : "resource_buy_qty:").length());
                if (amount != Math.floor(amount)) {
                    this.msgKey(p, "common.invalid-number", new String[0]);
                    return;
                }
                try {
                    rmat = Material.valueOf((String)matName);
                }
                catch (IllegalArgumentException ex) {
                    return;
                }
                int qty = (int)amount;
                if (isSell) {
                    this.executeResourceSell(p, rmat, qty);
                } else {
                    this.executeResourceBuy(p, rmat, qty);
                }
                return;
            }
            if (type.startsWith("world_stock_buy_qty:") || type.startsWith("world_stock_sell_qty:")) {
                boolean isSell = type.startsWith("world_stock_sell_qty:");
                String symbol = type.substring((isSell ? "world_stock_sell_qty:" : "world_stock_buy_qty:").length());
                if (amount != Math.floor(amount)) {
                    this.msgKey(p, "common.invalid-number", new String[0]);
                    return;
                }
                int qty = (int)amount;
                if (qty < 1) {
                    this.msgKey(p, "common.amount-must-be-positive", new String[0]);
                    return;
                }
                if (qty > this.cfgWorldStockMaxBulkQty) {
                    this.msgKey(p, "worldstock.bulk-qty-too-large", "max", String.valueOf(this.cfgWorldStockMaxBulkQty));
                    return;
                }
                if (isSell) {
                    this.executeWorldStockSell(p, symbol, qty, null);
                } else {
                    this.executeWorldStockBuy((OfflinePlayer)p, symbol, qty, null);
                }
                return;
            }
            if (type.startsWith("fund_stock_buy_qty:") || type.startsWith("fund_stock_sell_qty:")) {
                UUID fundId;
                boolean isSell = type.startsWith("fund_stock_sell_qty:");
                String rest = type.substring((isSell ? "fund_stock_sell_qty:" : "fund_stock_buy_qty:").length());
                int sep = rest.indexOf(59);
                if (sep < 0) {
                    return;
                }
                try {
                    fundId = UUID.fromString(rest.substring(0, sep));
                }
                catch (IllegalArgumentException ex) {
                    return;
                }
                String symbol = rest.substring(sep + 1);
                InvestmentFund fund = this.investmentFunds.get(fundId);
                if (fund == null || !fund.manager.equals(u)) {
                    this.msgKey(p, "fund.not-manager", new String[0]);
                    return;
                }
                if (amount != Math.floor(amount)) {
                    this.msgKey(p, "common.invalid-number", new String[0]);
                    return;
                }
                int qty = (int)amount;
                if (qty < 1) {
                    this.msgKey(p, "common.amount-must-be-positive", new String[0]);
                    return;
                }
                if (qty > this.cfgWorldStockMaxBulkQty) {
                    this.msgKey(p, "worldstock.bulk-qty-too-large", "max", String.valueOf(this.cfgWorldStockMaxBulkQty));
                    return;
                }
                if (isSell) {
                    this.executeWorldStockSell(p, symbol, qty, fund);
                } else {
                    this.executeWorldStockBuy((OfflinePlayer)p, symbol, qty, fund);
                }
                return;
            }
            if (type.equals("lottery_buy_qty")) {
                if (amount != Math.floor(amount)) {
                    this.msgKey(p, "common.invalid-number", new String[0]);
                    return;
                }
                this.buyLotteryTickets(p, (int)amount);
                return;
            }
            if (type.startsWith("admin_give:")) {
                if (!p.hasPermission(PERM_ADMIN)) {
                    this.msgKey(p, "admin.no-permission", new String[0]);
                    return;
                }
                UUID targetId = UUID.fromString(type.substring("admin_give:".length()));
                OfflinePlayer target = Bukkit.getOfflinePlayer((UUID)targetId);
                econ.depositPlayer(target, amount);
                String targetName = target.getName() != null ? target.getName() : targetId.toString();
                this.msgKey(p, "admin.give-success", "player", targetName, "amount", String.valueOf((long)amount));
                this.addLog(targetId, "[\u7ba1\u7406\u8005\u64cd\u4f5c] " + p.getName() + " \u304b\u3089 +" + this.fmtCur(amount));
                this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + targetName + "** \u306b " + (long)amount + "\u5186 \u3092\u4ed8\u4e0e\u3057\u307e\u3057\u305f\u3002(GUI)");
                this.openAdminPlayerDetailGUI(p, targetId);
                return;
            }
            if (type.startsWith("admin_take:")) {
                if (!p.hasPermission(PERM_ADMIN)) {
                    this.msgKey(p, "admin.no-permission", new String[0]);
                    return;
                }
                UUID targetId = UUID.fromString(type.substring("admin_take:".length()));
                OfflinePlayer target = Bukkit.getOfflinePlayer((UUID)targetId);
                econ.withdrawPlayer(target, amount);
                String targetName = target.getName() != null ? target.getName() : targetId.toString();
                this.msgKey(p, "admin.take-success", "player", targetName, "amount", String.valueOf((long)amount));
                this.addLog(targetId, "[\u7ba1\u7406\u8005\u64cd\u4f5c] " + p.getName() + " \u306b\u3088\u308a -" + this.fmtCur(amount));
                this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + targetName + "** \u304b\u3089 " + (long)amount + "\u5186 \u3092\u6ca1\u53ce\u3057\u307e\u3057\u305f\u3002(GUI)");
                this.openAdminPlayerDetailGUI(p, targetId);
                return;
            }
            if (type.startsWith("group_deposit:")) {
                UUID accountId = UUID.fromString(type.substring("group_deposit:".length()));
                GroupAccount acc = this.groupAccounts.get(accountId);
                if (acc == null || !this.isGroupAccountMember(acc, u)) {
                    this.msgKey(p, "group.not-found", new String[0]);
                    return;
                }
                if (pocket < amount) {
                    this.msgKey(p, "common.insufficient-funds", "amount", String.valueOf((long)pocket));
                    return;
                }
                econ.withdrawPlayer((OfflinePlayer)p, amount);
                acc.balance += amount;
                this.msgKey(p, "group.deposited", "amount", String.valueOf((long)amount));
                this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u3078\u5165\u91d1 -" + this.fmtCur(amount));
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                return;
            }
            if (type.startsWith("group_withdraw:")) {
                UUID accountId = UUID.fromString(type.substring("group_withdraw:".length()));
                GroupAccount acc = this.groupAccounts.get(accountId);
                if (acc == null || !this.isGroupAccountMember(acc, u)) {
                    this.msgKey(p, "group.not-found", new String[0]);
                    return;
                }
                if (acc.balance < amount) {
                    this.msgKey(p, "group.withdraw-insufficient", "amount", String.valueOf((long)acc.balance));
                    return;
                }
                acc.balance -= amount;
                econ.depositPlayer((OfflinePlayer)p, amount);
                this.msgKey(p, "group.withdrawn", "amount", String.valueOf((long)amount));
                this.addLog(u, "\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c" + acc.name + "\u300d\u304b\u3089\u5f15\u51fa +" + this.fmtCur(amount));
                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                return;
            }
            if (type.startsWith("admin_setcredit:")) {
                if (!p.hasPermission(PERM_ADMIN)) {
                    this.msgKey(p, "admin.no-permission", new String[0]);
                    return;
                }
                UUID targetId = UUID.fromString(type.substring("admin_setcredit:".length()));
                int score = Math.max(0, Math.min(800, (int)amount));
                this.creditScore.put(targetId, score);
                String targetName = Bukkit.getOfflinePlayer((UUID)targetId).getName();
                if (targetName == null) {
                    targetName = targetId.toString();
                }
                this.msgKey(p, "admin.setcredit-success", "player", targetName, "score", String.valueOf(score));
                this.sendDiscordWebhook("\ud83d\udee0\ufe0f \u7ba1\u7406\u8005 **" + p.getName() + "** \u304c **" + targetName + "** \u306e\u4fe1\u7528\u30b9\u30b3\u30a2\u3092 " + score + " \u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002(GUI)");
                this.openAdminPlayerDetailGUI(p, targetId);
                return;
            }
            if (type.startsWith("auction_bid:")) {
                UUID auctionId = UUID.fromString(type.substring("auction_bid:".length()));
                this.placeAuctionBid((OfflinePlayer)p, auctionId, amount);
                return;
            }
            switch (type) {
                case "personal_deposit": {
                    if (pocket < amount) {
                        this.msgKey(p, "common.insufficient-funds", "amount", String.valueOf((long)pocket));
                        return;
                    }
                    econ.withdrawPlayer((OfflinePlayer)p, amount);
                    this.personalBank.put(u, bank + amount);
                    this.msgKey(p, "personal.deposit-custom", "amount", String.valueOf((long)amount));
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    this.addLog(u, "\u500b\u4eba\u53e3\u5ea7 \u6307\u5b9a\u9810\u91d1 -" + this.fmtCur(amount));
                    this.sendDiscordWebhook("\ud83d\udcb0 **" + p.getName() + "** \u304c\u500b\u4eba\u53e3\u5ea7\u3078 " + (long)amount + "\u5186 \u9810\u91d1\u3057\u307e\u3057\u305f\u3002");
                    break;
                }
                case "personal_withdraw": {
                    if (bank < amount) {
                        this.msgKey(p, "personal.withdraw-insufficient", "amount", String.valueOf((long)bank));
                        return;
                    }
                    this.personalBank.put(u, bank - amount);
                    econ.depositPlayer((OfflinePlayer)p, amount);
                    this.msgKey(p, "personal.withdraw-custom", "amount", String.valueOf((long)amount));
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                    this.addLog(u, "\u500b\u4eba\u53e3\u5ea7 \u6307\u5b9a\u5f15\u51fa +" + this.fmtCur(amount));
                    this.sendDiscordWebhook("\ud83d\udcb8 **" + p.getName() + "** \u304c\u500b\u4eba\u53e3\u5ea7\u304b\u3089 " + (long)amount + "\u5186 \u5f15\u304d\u51fa\u3057\u307e\u3057\u305f\u3002");
                    break;
                }
                case "plan_amount": {
                    this.tempLoanAmount.put(u, amount);
                    this.msgKey(p, "bank.plan-amount-set", "amount", String.valueOf((long)amount));
                    this.openPlanGUI(p);
                    break;
                }
                case "plan_interest": {
                    this.tempInterestRate.put(u, amount);
                    this.msgKey(p, "bank.plan-interest-set", "amount", String.valueOf((long)amount));
                    this.openPlanGUI(p);
                    break;
                }
                case "treasury_donate": {
                    if (pocket < amount) {
                        this.msgKey(p, "common.insufficient-funds", "amount", String.valueOf((long)pocket));
                        return;
                    }
                    econ.withdrawPlayer((OfflinePlayer)p, amount);
                    this.treasury += amount;
                    this.grantDonationCreditScore(u, amount);
                    this.msgKey(p, "donate.treasury-thanks", "amount", String.valueOf((long)amount));
                    this.addLog(u, "\u56fd\u5eab\u3078\u5bc4\u4ed8: -" + this.fmtCur(amount));
                    this.sendDiscordWebhook("\ud83c\udf81 **" + p.getName() + "** \u304c\u56fd\u5eab\u3078 " + (long)amount + "\u5186 \u3092\u5bc4\u4ed8\u3057\u307e\u3057\u305f\u3002");
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    break;
                }
                case "auction_list_price": {
                    if (!this.auctionListingDraft.containsKey(u)) {
                        this.msgKey(p, "auction.draft-not-found", new String[0]);
                        return;
                    }
                    this.auctionListingStartPrice.put(u, amount);
                    this.awaitingChatInput.put(u, "auction_buyout_price");
                    this.msgKey(p, "auction.buyout-prompt", new String[0]);
                    break;
                }
                case "auction_buyout_price": {
                    ItemStack draft = this.auctionListingDraft.remove(u);
                    Double startPrice = this.auctionListingStartPrice.remove(u);
                    if (draft == null || startPrice == null) {
                        this.msgKey(p, "auction.listing-info-not-found", new String[0]);
                        return;
                    }
                    double buyout = amount;
                    if (buyout > 0.0 && buyout <= startPrice) {
                        this.msgKey(p, "auction.buyout-too-low", "amount", String.valueOf((long)startPrice.doubleValue()));
                        HashMap leftover = p.getInventory().addItem(new ItemStack[]{draft});
                        for (ItemStack over : leftover.values()) {
                            p.getWorld().dropItem(p.getLocation(), over);
                        }
                        return;
                    }
                    UUID auctionId = UUID.randomUUID();
                    this.auctionSeller.put(auctionId, u);
                    this.auctionItem.put(auctionId, draft);
                    this.auctionBid.put(auctionId, startPrice);
                    this.auctionEndTime.put(auctionId, System.currentTimeMillis() + this.cfgAuctionDurationMs);
                    if (buyout > 0.0) {
                        this.auctionBuyoutPrice.put(auctionId, buyout);
                    }
                    String buyoutMsg = buyout > 0.0 ? " / \u5373\u6c7a\u4fa1\u683c: " + this.fmtCur(buyout) : "";
                    this.msgKey(p, "auction.listed", "item", draft.getType().name(), "amount", String.valueOf((long)startPrice.doubleValue()), "buyoutMsg", buyoutMsg, "minutes", String.valueOf(this.cfgAuctionDurationMs / 60000L));
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u51fa\u54c1: " + draft.getType().name() + " (\u958b\u59cb\u4fa1\u683c" + this.fmtCur(startPrice) + buyoutMsg + ")");
                    this.sendDiscordWebhook("\ud83c\udff7\ufe0f **" + p.getName() + "** \u304c **" + draft.getType().name() + "** \u3092\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u306b\u51fa\u54c1\u3057\u307e\u3057\u305f\u3002\u958b\u59cb\u4fa1\u683c: " + this.fmtCur(startPrice) + buyoutMsg);
                    this.broadcastNews("<gold><bold>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u3011</bold></gold> <yellow>" + p.getName() + "</yellow> \u304c <white>" + draft.getType().name() + "</white> \u3092\u51fa\u54c1\u3057\u305f\uff01");
                    break;
                }
                case "trade_money": {
                    TradeSession session = this.activeTradeSessions.get(u);
                    if (session == null) {
                        return;
                    }
                    if (pocket < amount) {
                        this.msgKey(p, "trade.money-insufficient", new String[0]);
                        return;
                    }
                    boolean isA = u.equals(session.playerA);
                    if (isA) {
                        session.moneyOfferedA = amount;
                    } else {
                        session.moneyOfferedB = amount;
                    }
                    session.confirmedA = false;
                    session.confirmedB = false;
                    this.refreshTradeGui(session);
                    this.msgKey(p, "trade.money-set", "amount", String.valueOf((long)amount));
                    this.clickSound(p);
                    break;
                }
            }
        });
    }

    private void addLog(UUID u, String content) {
        LinkedList logs = this.transactionLogs.computeIfAbsent(u, k -> new LinkedList());
        String time = new SimpleDateFormat("MM/dd HH:mm").format(new Date());
        logs.addFirst("[" + time + "] " + content);
        while (logs.size() > 30) {
            logs.removeLast();
        }
    }

    private void broadcastNews(String text) {
        if (!this.cfgBroadcastNewsDefault) {
            return;
        }
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (this.newsBroadcastOff.contains(online.getUniqueId())) continue;
            online.sendMessage(this.mm(text));
        }
        this.getLogger().info(MiniMessage.miniMessage().stripTags(text));
    }

    private void sendDiscordWebhook(String plainMessage) {
        if (this.cfgDiscordBotToken == null || this.cfgDiscordBotToken.isBlank() || this.cfgDiscordChannelId == null || this.cfgDiscordChannelId.isBlank()) {
            return;
        }
        String token = this.cfgDiscordBotToken;
        String channelId = this.cfgDiscordChannelId;
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)this, () -> {
            block26: {
                HttpURLConnection conn = null;
                try {
                    Object body;
                    String escaped = plainMessage.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n");
                    String json = "{\"content\":\"" + escaped + "\"}";
                    URL url = new URL("https://discord.com/api/v10/channels/" + channelId + "/messages");
                    conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Authorization", "Bot " + token);
                    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    try (OutputStream os = conn.getOutputStream();){
                        os.write(json.getBytes(StandardCharsets.UTF_8));
                    }
                    int code = conn.getResponseCode();
                    if (code < 300) break block26;
                    try (InputStream errStream = conn.getErrorStream();){
                        body = errStream != null ? new String(errStream.readAllBytes(), StandardCharsets.UTF_8) : "(\u8a73\u7d30\u306a\u3057)";
                    }
                    catch (Exception readEx) {
                        body = "(\u8a73\u7d30\u53d6\u5f97\u5931\u6557: " + readEx.getMessage() + ")";
                    }
                    this.getLogger().warning("[Discord Bot] \u9001\u4fe1\u306b\u5931\u6557\u3057\u307e\u3057\u305f\u3002HTTP\u30b9\u30c6\u30fc\u30bf\u30b9: " + code + " / \u5185\u5bb9: " + (String)body);
                    if (code == 401) {
                        this.getLogger().warning("[Discord Bot] 401 = \u30c8\u30fc\u30af\u30f3\u304c\u7121\u52b9\u3067\u3059\u3002config.yml\u306esystem.discord-bot-token\u3092\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\u3002");
                    } else if (code == 403) {
                        this.getLogger().warning("[Discord Bot] 403 = Bot\u306b\u3053\u306e\u30c1\u30e3\u30f3\u30cd\u30eb\u3078\u306e\u9001\u4fe1\u6a29\u9650\u304c\u3042\u308a\u307e\u305b\u3093\u3002\u30b5\u30fc\u30d0\u30fc\u3078\u306e\u62db\u5f85\u30fb\u6a29\u9650\u8a2d\u5b9a\u3092\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\u3002");
                    } else if (code == 404) {
                        this.getLogger().warning("[Discord Bot] 404 = \u30c1\u30e3\u30f3\u30cd\u30ebID\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3002system.discord-channel-id\u3092\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\u3002");
                    }
                }
                catch (Exception ex) {
                    this.getLogger().warning("[Discord Bot] \u9001\u4fe1\u4e2d\u306b\u30a8\u30e9\u30fc\u304c\u767a\u751f\u3057\u307e\u3057\u305f: " + ex.getMessage());
                }
                finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        });
    }

    private void fetchWorldStockQuote(String rawSymbol, Consumer<WorldStockQuote> callback) {
        String symbol = rawSymbol.trim().toUpperCase();
        WorldStockQuote cached = this.worldStockQuoteCache.get(symbol);
        long now = System.currentTimeMillis();
        if (cached != null && now - cached.fetchedAt < (long)(this.cfgWorldStockCacheSeconds * 1000.0)) {
            callback.accept(cached);
            return;
        }
        Bukkit.getScheduler().runTaskAsynchronously((Plugin)this, () -> {
            WorldStockQuote result = null;
            HttpURLConnection conn = null;
            try {
                Double price;
                String body;
                String encoded = URLEncoder.encode(symbol, StandardCharsets.UTF_8).replace("+", "%20");
                URL url = new URL("https://query1.finance.yahoo.com/v8/finance/chart/" + encoded + "?interval=1d&range=1d");
                conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) MinecraftBankPlugin");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                int code = conn.getResponseCode();
                InputStream stream = code >= 200 && code < 300 ? conn.getInputStream() : conn.getErrorStream();
                String string = body = stream != null ? new String(stream.readAllBytes(), StandardCharsets.UTF_8) : "";
                if (code >= 200 && code < 300 && (price = this.extractJsonNumber(body, "regularMarketPrice")) != null) {
                    String currency = this.extractJsonString(body, "currency");
                    String exch = this.extractJsonString(body, "fullExchangeName");
                    String realSymbol = this.extractJsonString(body, "symbol");
                    result = new WorldStockQuote(realSymbol != null ? realSymbol : symbol, exch != null ? exch : "-", price, currency != null ? currency : "USD", now);
                }
            }
            catch (Exception ex) {
                this.getLogger().warning("[\u4e16\u754c\u682a\u5f0f\u5e02\u5834] " + symbol + " \u306e\u682a\u4fa1\u53d6\u5f97\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + ex.getMessage());
            }
            finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
            WorldStockQuote finalResult = result;
            Bukkit.getScheduler().runTask((Plugin)this, () -> {
                if (finalResult != null) {
                    this.worldStockQuoteCache.put(symbol, finalResult);
                }
                callback.accept(finalResult);
            });
        });
    }

    private Double extractJsonNumber(String json, String key) {
        Matcher m = Pattern.compile("\"" + key + "\"\\s*:\\s*(-?[0-9]+(?:\\.[0-9]+)?)").matcher(json);
        if (m.find()) {
            try {
                return Double.parseDouble(m.group(1));
            }
            catch (NumberFormatException ex) {
                return null;
            }
        }
        return null;
    }

    private String extractJsonString(String json, String key) {
        Matcher m = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]*)\"").matcher(json);
        return m.find() ? m.group(1) : null;
    }

    private void setWorldStockTag(ItemStack item, String symbol) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        meta.getPersistentDataContainer().set(this.worldStockKey, PersistentDataType.STRING, (Object)symbol);
        item.setItemMeta(meta);
    }

    private void setWorldStockActionTag(ItemStack item, String action) {
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return;
        }
        meta.getPersistentDataContainer().set(this.worldStockActionKey, PersistentDataType.STRING, (Object)action);
        item.setItemMeta(meta);
    }

    private Component mm(String text) {
        return MiniMessage.miniMessage().deserialize((Object)text);
    }

    private void msg(Player p, String text) {
        p.sendMessage(this.mm(text));
    }

    private void loadMessages() {
        File file = new File(this.getDataFolder(), "messages.yml");
        this.messages = YamlConfiguration.loadConfiguration((File)file);
        boolean changed = false;
        for (Map.Entry<String, String> entry : DEFAULT_MESSAGES.entrySet()) {
            if (this.messages.contains(entry.getKey())) continue;
            this.messages.set(entry.getKey(), (Object)entry.getValue());
            changed = true;
        }
        if (changed) {
            try {
                this.messages.save(file);
            }
            catch (IOException ex) {
                this.getLogger().warning("[messages.yml] \u4fdd\u5b58\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + ex.getMessage());
            }
        }
    }

    private String getMsg(String key, String ... kv) {
        String template;
        String string = template = this.messages != null ? this.messages.getString(key) : null;
        if (template == null) {
            template = DEFAULT_MESSAGES.getOrDefault(key, "");
        }
        int i = 0;
        while (i + 1 < kv.length) {
            template = template.replace("{" + kv[i] + "}", kv[i + 1]);
            i += 2;
        }
        return template;
    }

    private void msgKey(Player p, String key, String ... kv) {
        String text = this.getMsg(key, kv);
        if (!text.isEmpty()) {
            p.sendMessage(this.mm(text));
        }
    }

    private void clickSound(Player p) {
        p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 0.5f, 1.0f);
    }

    private void errorSound(Player p) {
        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 1.0f, 1.0f);
    }

    private ItemStack createItem(Material mat, String name, String ... lores) {
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(this.mm(name));
            if (lores.length > 0) {
                meta.lore(List.of(lores).stream().map(this::mm).toList());
            }
            item.setItemMeta(meta);
        }
        return item;
    }

    @Override
    public double getPocketBalance(OfflinePlayer player) {
        return econ.getBalance(player);
    }

    @Override
    public double getPersonalBankBalance(UUID playerId) {
        return this.personalBank.getOrDefault(playerId, 0.0);
    }

    @Override
    public int getCreditScoreOf(UUID playerId) {
        return this.getScore(playerId);
    }

    @Override
    public double getTreasuryBalance() {
        return this.treasury;
    }

    @Override
    public String getActiveEconomyEvent() {
        return this.activeEconomyEvent;
    }

    private /* synthetic */ String lambda$handleMyActionRequest$0(String path, OfflinePlayer actor, String body) throws Exception {
        return this.handleMyActionOnMainThread(path, actor, body);
    }

    private static /* synthetic */ boolean lambda$onInventoryClick$6(UUID u, UUID s) {
        return s.equals(u);
    }

    private static /* synthetic */ String lambda$onInventoryClick$5(UUID k) {
        return UUID.randomUUID().toString().replace("-", "");
    }

    private static /* synthetic */ HashSet lambda$onInventoryClick$4(UUID k) {
        return new HashSet();
    }

    private static /* synthetic */ boolean lambda$onInventoryClick$3(UUID u, Quest q) {
        return q.posterId.equals(u);
    }

    private static /* synthetic */ boolean lambda$onInventoryClick$2(UUID u, Quest q) {
        return q.posterId.equals(u);
    }

    private static /* synthetic */ boolean lambda$onInventoryClick$1(UUID vipDealId, VipDeal d) {
        return d.id.equals(vipDealId);
    }

    private static /* synthetic */ boolean lambda$onInventoryClick$0(UUID dealId, MerchantDeal d) {
        return d.id.equals(dealId);
    }

    static {
        COUNTRY_CURRENCY_MAP.put("japan", "\u5186");
        COUNTRY_CURRENCY_MAP.put("united states", "USD");
        COUNTRY_CURRENCY_MAP.put("usa", "USD");
        COUNTRY_CURRENCY_MAP.put("united kingdom", "GBP");
        COUNTRY_CURRENCY_MAP.put("uk", "GBP");
        COUNTRY_CURRENCY_MAP.put("germany", "EUR");
        COUNTRY_CURRENCY_MAP.put("france", "EUR");
        COUNTRY_CURRENCY_MAP.put("italy", "EUR");
        COUNTRY_CURRENCY_MAP.put("spain", "EUR");
        COUNTRY_CURRENCY_MAP.put("canada", "CAD");
        COUNTRY_CURRENCY_MAP.put("australia", "AUD");
        COUNTRY_CURRENCY_MAP.put("hong kong", "HKD");
        COUNTRY_CURRENCY_MAP.put("china", "CNY");
        COUNTRY_CURRENCY_MAP.put("south korea", "KRW");
        COUNTRY_CURRENCY_MAP.put("korea", "KRW");
        COUNTRY_CURRENCY_MAP.put("india", "INR");
        COUNTRY_CURRENCY_MAP.put("brazil", "BRL");
        COUNTRY_CURRENCY_MAP.put("switzerland", "CHF");
        COUNTRY_CURRENCY_MAP.put("singapore", "SGD");
        COUNTRY_CURRENCY_MAP.put("new zealand", "NZD");
        COUNTRY_CURRENCY_MAP.put("mexico", "MXN");
        COUNTRY_CURRENCY_MAP.put("taiwan", "TWD");
        COUNTRY_CURRENCY_MAP.put("thailand", "THB");
        COUNTRY_CURRENCY_MAP.put("russia", "RUB");
        RESOURCE_BASE_PRICES = new LinkedHashMap();
        RESOURCE_CATEGORIES = new LinkedHashMap<String, List<Material>>();
        RESOURCE_BASE_PRICES.put(Material.COAL, 30.0);
        RESOURCE_BASE_PRICES.put(Material.IRON_INGOT, 95.0);
        RESOURCE_BASE_PRICES.put(Material.GOLD_INGOT, 150.0);
        RESOURCE_BASE_PRICES.put(Material.COPPER_INGOT, 40.0);
        RESOURCE_BASE_PRICES.put(Material.REDSTONE, 20.0);
        RESOURCE_BASE_PRICES.put(Material.LAPIS_LAZULI, 25.0);
        RESOURCE_BASE_PRICES.put(Material.DIAMOND, 950.0);
        RESOURCE_BASE_PRICES.put(Material.EMERALD, 550.0);
        RESOURCE_BASE_PRICES.put(Material.NETHERITE_SCRAP, 8000.0);
        RESOURCE_BASE_PRICES.put(Material.WHEAT, 12.0);
        RESOURCE_BASE_PRICES.put(Material.CARROT, 8.0);
        RESOURCE_BASE_PRICES.put(Material.POTATO, 8.0);
        RESOURCE_BASE_PRICES.put(Material.BEETROOT, 8.0);
        RESOURCE_BASE_PRICES.put(Material.MELON_SLICE, 8.0);
        RESOURCE_BASE_PRICES.put(Material.PUMPKIN, 15.0);
        RESOURCE_BASE_PRICES.put(Material.SUGAR_CANE, 12.0);
        RESOURCE_BASE_PRICES.put(Material.NETHER_WART, 20.0);
        RESOURCE_BASE_PRICES.put(Material.COCOA_BEANS, 15.0);
        RESOURCE_BASE_PRICES.put(Material.ROTTEN_FLESH, 4.0);
        RESOURCE_BASE_PRICES.put(Material.BONE, 8.0);
        RESOURCE_BASE_PRICES.put(Material.STRING, 8.0);
        RESOURCE_BASE_PRICES.put(Material.GUNPOWDER, 23.0);
        RESOURCE_BASE_PRICES.put(Material.SPIDER_EYE, 12.0);
        RESOURCE_BASE_PRICES.put(Material.SLIME_BALL, 20.0);
        RESOURCE_BASE_PRICES.put(Material.ENDER_PEARL, 150.0);
        RESOURCE_BASE_PRICES.put(Material.BLAZE_ROD, 115.0);
        RESOURCE_BASE_PRICES.put(Material.GHAST_TEAR, 225.0);
        RESOURCE_BASE_PRICES.put(Material.OAK_LOG, 8.0);
        RESOURCE_BASE_PRICES.put(Material.SPRUCE_LOG, 8.0);
        RESOURCE_BASE_PRICES.put(Material.BIRCH_LOG, 8.0);
        RESOURCE_BASE_PRICES.put(Material.JUNGLE_LOG, 8.0);
        RESOURCE_BASE_PRICES.put(Material.ACACIA_LOG, 8.0);
        RESOURCE_BASE_PRICES.put(Material.DARK_OAK_LOG, 8.0);
        RESOURCE_BASE_PRICES.put(Material.MANGROVE_LOG, 8.0);
        RESOURCE_BASE_PRICES.put(Material.CHERRY_LOG, 8.0);
        RESOURCE_CATEGORIES.put("ore", List.of(Material.COAL, Material.IRON_INGOT, Material.GOLD_INGOT, Material.COPPER_INGOT, Material.REDSTONE, Material.LAPIS_LAZULI, Material.DIAMOND, Material.EMERALD, Material.NETHERITE_SCRAP));
        RESOURCE_CATEGORIES.put("crop", List.of(Material.WHEAT, Material.CARROT, Material.POTATO, Material.BEETROOT, Material.MELON_SLICE, Material.PUMPKIN, Material.SUGAR_CANE, Material.NETHER_WART, Material.COCOA_BEANS));
        RESOURCE_CATEGORIES.put("drop", List.of(Material.ROTTEN_FLESH, Material.BONE, Material.STRING, Material.GUNPOWDER, Material.SPIDER_EYE, Material.SLIME_BALL, Material.ENDER_PEARL, Material.BLAZE_ROD, Material.GHAST_TEAR));
        RESOURCE_CATEGORIES.put("wood", List.of(Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.MANGROVE_LOG, Material.CHERRY_LOG));
        MERCHANT_ITEM_POOL = new LinkedHashMap();
        MERCHANT_ITEM_POOL.put(Material.ELYTRA, 8000.0);
        MERCHANT_ITEM_POOL.put(Material.TOTEM_OF_UNDYING, 6000.0);
        MERCHANT_ITEM_POOL.put(Material.NETHERITE_INGOT, 4000.0);
        MERCHANT_ITEM_POOL.put(Material.NETHERITE_BLOCK, 30000.0);
        MERCHANT_ITEM_POOL.put(Material.ENCHANTED_GOLDEN_APPLE, 5000.0);
        MERCHANT_ITEM_POOL.put(Material.SADDLE, 800.0);
        MERCHANT_ITEM_POOL.put(Material.NAME_TAG, 600.0);
        MERCHANT_ITEM_POOL.put(Material.SHULKER_BOX, 3000.0);
        MERCHANT_ITEM_POOL.put(Material.TRIDENT, 3500.0);
        MERCHANT_ITEM_POOL.put(Material.NETHER_STAR, 4500.0);
        MERCHANT_ITEM_POOL.put(Material.DRAGON_EGG, 50000.0);
        MERCHANT_ITEM_POOL.put(Material.BEACON, 8000.0);
        MERCHANT_ITEM_POOL.put(Material.DIAMOND_BLOCK, 2200.0);
        MERCHANT_ITEM_POOL.put(Material.EMERALD_BLOCK, 1300.0);
        MERCHANT_ITEM_POOL.put(Material.MUSIC_DISC_PIGSTEP, 1500.0);
        MERCHANT_ITEM_POOL.put(Material.MUSIC_DISC_OTHERSIDE, 1500.0);
        MERCHANT_ITEM_POOL.put(Material.HEART_OF_THE_SEA, 2500.0);
        MERCHANT_ITEM_POOL.put(Material.CONDUIT, 4000.0);
        MERCHANT_ITEM_POOL.put(Material.END_CRYSTAL, 2000.0);
        MERCHANT_ITEM_POOL.put(Material.GOLDEN_CARROT, 200.0);
        VIP_SHOP_POOL = new LinkedHashMap();
        VIP_SHOP_POOL.put(Material.ELYTRA, 8000.0);
        VIP_SHOP_POOL.put(Material.TOTEM_OF_UNDYING, 6000.0);
        VIP_SHOP_POOL.put(Material.NETHERITE_INGOT, 4000.0);
        VIP_SHOP_POOL.put(Material.NETHERITE_BLOCK, 30000.0);
        VIP_SHOP_POOL.put(Material.ENCHANTED_GOLDEN_APPLE, 5000.0);
        VIP_SHOP_POOL.put(Material.NETHERITE_SCRAP, 2000.0);
        VIP_SHOP_POOL.put(Material.SHULKER_BOX, 3000.0);
        VIP_SHOP_POOL.put(Material.TRIDENT, 3500.0);
        VIP_SHOP_POOL.put(Material.NETHER_STAR, 4500.0);
        VIP_SHOP_POOL.put(Material.BEACON, 8000.0);
        VIP_SHOP_POOL.put(Material.DIAMOND_BLOCK, 2200.0);
        VIP_SHOP_POOL.put(Material.EMERALD_BLOCK, 1300.0);
        VIP_SHOP_POOL.put(Material.HEART_OF_THE_SEA, 2500.0);
        VIP_SHOP_POOL.put(Material.CONDUIT, 4000.0);
        VIP_SHOP_POOL.put(Material.GOLDEN_APPLE, 800.0);
        MANAGED_DATA_PREFIXES = Set.of("storage_items", "storage_rent", "personal", "bankers", "capital", "market", "market2", "market3", "debts", "fixed_deposit", "fixed_time", "fixed_deposit2", "fixed_time2", "fixed_deposit3", "fixed_time3", "gov_debt", "gov_debt_due", "credit_score", "auction_seller", "auction_item", "auction_bid", "auction_bidder", "auction_end", "auction_buyout", "auction_pending", "auction_draft", "auction_offline_notices", "auction_total_sold", "loan_offline_notices", "collateral_item", "collateral_loan", "collateral_due", "collateral_lender", "insurance_expiry", "insurance_last_claim", "vip_stipend_claimed", "achievements", "news_off", "hub_item_issued", "tx_logs", "treasury", "weekly_trade_volume", "donation_score_today", "donation_score_reset_at", "welfare_count_today", "welfare_count_reset_at", "loan_guarantor", "guarantor_proposals", "guarantor_proposal_time", "economy-event", "tutorial-seen", "quests", "world_stocks", "world_stock_daily", "world_stock_last_trade", "world_stock_alerts", "resource_prices", "resource_personal_buy", "traveling_merchant", "vip_shop", "treasure", "installment_plans", "lottery", "group_accounts", "investment_funds", "dashboard_tokens", "web_password", "web_admin_password");
        DEFAULT_MESSAGES = new LinkedHashMap();
        DEFAULT_MESSAGES.put("welcome.first-join", "<gold><bold>\u3010\u7d4c\u6e08\u624b\u5e33\u3011</bold> \u3042\u306a\u305f\u306b\u7d4c\u6e08\u624b\u5e33\u304c\u914d\u5e03\u3055\u308c\u307e\u3057\u305f\u3002\u53f3\u30af\u30ea\u30c3\u30af\u3067\u7d4c\u6e08\u7dcf\u5408\u30e1\u30cb\u30e5\u30fc\u3092\u958b\u3051\u307e\u3059\u3002</gold>");
        DEFAULT_MESSAGES.put("welcome.no-permission", "<red>\u3053\u306e\u30b3\u30de\u30f3\u30c9\u3092\u4f7f\u7528\u3059\u308b\u6a29\u9650\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("common.insufficient-funds", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002\uff08\u6240\u6301\u91d1: {amount}\u5186\uff09</red>");
        DEFAULT_MESSAGES.put("common.invalid-number", "<red>\u6570\u5024\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u4f8b: 5000\uff09</red>");
        DEFAULT_MESSAGES.put("common.cancelled", "<gray>\u5165\u529b\u3092\u30ad\u30e3\u30f3\u30bb\u30eb\u3057\u307e\u3057\u305f\u3002</gray>");
        DEFAULT_MESSAGES.put("insurance.payout", "<blue><bold>\u3010\u4fdd\u967a\u91d1\u652f\u6255\u3044\u3011 \u52a0\u5165\u3057\u3066\u3044\u305f\u751f\u547d\u4fdd\u967a\u304b\u3089 {amount}\u5186 \u304c\u632f\u308a\u8fbc\u307e\u308c\u307e\u3057\u305f\u3002</bold></blue>");
        DEFAULT_MESSAGES.put("auction.won", "<green><bold>\u3010\u843d\u672d\u3011 {item} \u3092 {amount}\u5186 \u3067\u843d\u672d\u3057\u307e\u3057\u305f\u3002\uff08\u53d7\u53d6\u7bb1\u3092\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\uff09</bold></green>");
        DEFAULT_MESSAGES.put("auction.sold", "<green><bold>\u3010\u843d\u672d\u6210\u7acb\u3011</bold> {item} \u304c {amount}\u5186 \u3067\u843d\u672d\u3055\u308c\u307e\u3057\u305f\u3002\uff08\u624b\u6570\u6599 {fee}\u5186 \u5dee\u5f15\u5f8c {net}\u5186 \u3092\u5165\u91d1\uff09</green>");
        DEFAULT_MESSAGES.put("auction.outbid-refund", "<yellow>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u3011 {item} \u3078\u306e\u3042\u306a\u305f\u306e\u5165\u672d\u306f\u4e0a\u56de\u3089\u308c\u307e\u3057\u305f\u3002\u5165\u672d\u91d1 {amount}\u5186 \u3092\u8fd4\u91d1\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("loan.player-approved", "<green><bold>\u878d\u8cc7\u5951\u7d04\u304c\u6210\u7acb\u3057\u307e\u3057\u305f\uff01 {amount}\u5186 \u3092\u53d7\u9818\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("loan.gov-approved", "<green><bold>\u56fd\u55b6\u516c\u5eab\u304b\u3089 {amount}\u5186 \u878d\u8cc7\u3092\u53d7\u3051\u307e\u3057\u305f\uff01 (\u9069\u7528\u91d1\u5229: {rate}% / \u7dcf\u8fd4\u6e08\u984d: {total}\u5186 / \u671f\u9650: {minutes}\u5206)</bold></green>");
        DEFAULT_MESSAGES.put("loan.repaid-full", "<green><bold>\u30d7\u30ec\u30a4\u30e4\u30fc\u9593\u306e\u501f\u91d1\u3092\u5168\u984d\u5b8c\u6e08\u3057\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("achievement.unlocked", "<gold><bold>\ud83c\udf96 \u5b9f\u7e3e\u300c{title}\u300d\u3092\u89e3\u653e\u3057\u307e\u3057\u305f\uff01</bold></gold>");
        DEFAULT_MESSAGES.put("event.boom", "<green><bold>\u3010\u7d4c\u6e08\u30a4\u30d9\u30f3\u30c8\u3011\u9ec4\u91d1\u30e9\u30c3\u30b7\u30e5\uff01</bold> \u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u306e\u53d6\u5f15\u4fa1\u683c\u304c\u4e00\u6642\u7684\u306b10%\u4e0a\u6607\u3057\u307e\u3059\u3002</green>");
        DEFAULT_MESSAGES.put("event.tax", "<red><bold>\u3010\u7d4c\u6e08\u30a4\u30d9\u30f3\u30c8\u3011\u624b\u6570\u6599\u9ad8\u9a30\uff01</bold> \u4e16\u754c\u682a\u5f0f\u5e02\u5834\u306e\u58f2\u8cb7\u624b\u6570\u6599\u304c\u4e00\u6642\u7684\u306b2\u500d\u306b\u306a\u308a\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("event.bonus", "<gold><bold>\u3010\u7d4c\u6e08\u30a4\u30d9\u30f3\u30c8\u3011\u30dc\u30fc\u30ca\u30b9\u652f\u7d66\u30c7\u30fc\uff01</bold> \u5168\u30d7\u30ec\u30a4\u30e4\u30fc\u306b5,000\u5186\u3092\u652f\u7d66\u3057\u307e\u3057\u305f\u3002</gold>");
        DEFAULT_MESSAGES.put("event.recession", "<dark_red><bold>\u3010\u7d4c\u6e08\u30a4\u30d9\u30f3\u30c8\u3011\u51ac\u306e\u6642\u4ee3\uff01</bold> \u8cc7\u6e90\u76f8\u5834\u30b7\u30e7\u30c3\u30d7\u306e\u53d6\u5f15\u4fa1\u683c\u304c\u4e00\u6642\u7684\u306b10%\u4e0b\u843d\u3057\u307e\u3059\u3002</dark_red>");
        DEFAULT_MESSAGES.put("confirm.dissolve-prompt", "<red><bold>\u4f1a\u793e\u3092\u672c\u5f53\u306b\u89e3\u6563\u3057\u307e\u3059\u304b\uff1f</bold></red>");
        DEFAULT_MESSAGES.put("confirm.withdraw-prompt", "<red><bold>\u9810\u91d1\u5168\u984d\u3092\u5f15\u304d\u51fa\u3057\u307e\u3059\u304b\uff1f</bold></red> <yellow>30\u79d2\u4ee5\u5185\u306b\u3082\u3046\u4e00\u5ea6\u30af\u30ea\u30c3\u30af\u3067\u78ba\u5b9a\u3057\u307e\u3059\u3002</yellow>");
        DEFAULT_MESSAGES.put("confirm.expired", "<red>\u78ba\u8a8d\u306e\u6709\u52b9\u671f\u9650\u304c\u5207\u308c\u307e\u3057\u305f\u3002\u3082\u3046\u4e00\u5ea6\u5b9f\u884c\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("fixed-deposit.matured", "<green><bold>\u5b9a\u671f\u9810\u91d1\u304c\u6e80\u671f\u3092\u8fce\u3048\u307e\u3057\u305f\uff01 \u5831\u916c {amount}\u5186 \u3092\u53d7\u9818\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("fixed-deposit.matured-2", "<aqua><bold>2\u67a0\u76ee\u306e\u5b9a\u671f\u9810\u91d1\u304c\u6e80\u671f\u3092\u8fce\u3048\u307e\u3057\u305f\uff01 \u5831\u916c {amount}\u5186 \u3092\u53d7\u9818\u3057\u307e\u3057\u305f\u3002</bold></aqua>");
        DEFAULT_MESSAGES.put("fixed-deposit.matured-3", "<light_purple><bold>3\u67a0\u76ee\u306e\u5b9a\u671f\u9810\u91d1\u304c\u6e80\u671f\u3092\u8fce\u3048\u307e\u3057\u305f\uff01 \u5831\u916c {amount}\u5186 \u3092\u53d7\u9818\u3057\u307e\u3057\u305f\u3002</bold></light_purple>");
        DEFAULT_MESSAGES.put("collateral.seized", "<dark_red><bold>\u3010\u62c5\u4fdd\u6ca1\u53ce\u3011</bold> \u62c5\u4fdd\u4ed8\u304d\u878d\u8cc7\u306e\u8fd4\u6e08\u671f\u9650\u3092\u904e\u304e\u305f\u305f\u3081\u3001\u62c5\u4fdd( {item} )\u306f\u8cea\u5c4b\u306b\u6ca1\u53ce\u3055\u308c\u307e\u3057\u305f\u3002</dark_red>");
        DEFAULT_MESSAGES.put("loan.gov-overdue-penalty", "<dark_red><bold>\u3010\u5ef6\u6ede\u3011</bold> \u56fd\u55b6\u516c\u5eab\u30ed\u30fc\u30f3\u306e\u8fd4\u6e08\u671f\u9650\u3092\u904e\u304e\u305f\u305f\u3081\u3001\u5ef6\u6ede\u91d1 {amount}\u5186 \u304c\u52a0\u7b97\u3055\u308c\u307e\u3057\u305f\u3002</dark_red>");
        DEFAULT_MESSAGES.put("loan.gov-overdue-ceiling", "<dark_red><bold>\u3010\u5ef6\u6ede\u3011</bold> \u56fd\u55b6\u516c\u5eab\u30ed\u30fc\u30f3\u304c\u5ef6\u6ede\u4e0a\u9650\uff08{amount}\u5186\uff09\u306b\u9054\u3057\u3066\u3044\u307e\u3059\u3002\u81f3\u6025\u8fd4\u6e08\u3057\u3066\u304f\u3060\u3055\u3044\u3002</dark_red>");
        DEFAULT_MESSAGES.put("auction.no-bid-returned", "<gray>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u7d42\u4e86\u3011 {item} \u306e\u51fa\u54c1\u306f\u5165\u672d\u8005\u304c\u304a\u3089\u305a\u3001\u624b\u5143\u306b\u623b\u308a\u307e\u3059\u3002\uff08\u53d7\u53d6\u7bb1\u3092\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\uff09</gray>");
        DEFAULT_MESSAGES.put("auction.items-received", "<gold><bold>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u30cf\u30a6\u30b9\u3011</bold> \u843d\u672d\u54c1\u30fb\u8fd4\u5374\u54c1 {count}\u4ef6 \u3092\u30a4\u30f3\u30d9\u30f3\u30c8\u30ea\u306b\u53d7\u3051\u53d6\u308a\u307e\u3057\u305f\u3002</gold>");
        DEFAULT_MESSAGES.put("item.reissue-cooldown", "<red>\u518d\u767a\u884c\u306e\u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d\u3067\u3059\u3002\u3042\u3068 {seconds}\u79d2 \u304a\u5f85\u3061\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("item.reissued", "<green><bold>\u7d4c\u6e08\u624b\u5e33\u3092\u518d\u767a\u884c\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("help.header", "<gold><bold>=== MinecraftBank \u30b3\u30de\u30f3\u30c9\u4e00\u89a7 ===</bold></gold>");
        DEFAULT_MESSAGES.put("help.menu", "<yellow>/meco</yellow> <gray>- \u7d4c\u6e08\u7dcf\u5408\u30e1\u30cb\u30e5\u30fc\u3092\u958b\u304f\uff08\ud83d\udcd6\u7d4c\u6e08\u624b\u5e33\u306e\u53f3\u30af\u30ea\u30c3\u30af\u3067\u3082\u958b\u3051\u307e\u3059\uff09</gray>");
        DEFAULT_MESSAGES.put("help.shortcuts", "<yellow>/meco <bank|market|auction|resource|worldstock|merchant|vip|todo|quest|lottery|storage|loan|govloan|insurance|collateral|credit|deposit|personal|installment|mypage|leaderboard|achievement|repay></yellow> <gray>- \u5404\u753b\u9762\u306b\u76f4\u63a5\u30a2\u30af\u30bb\u30b9</gray>");
        DEFAULT_MESSAGES.put("help.webpage", "<yellow>/meco webpage</yellow> <gray>- \u81ea\u5206\u5c02\u7528\u306eWeb\u500b\u4eba\u30da\u30fc\u30b8\u306e\u30ea\u30f3\u30af\u3092\u8868\u793a</gray>");
        DEFAULT_MESSAGES.put("help.collect", "<yellow>/meco collect</yellow> <gray>- \u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u843d\u672d\u54c1\u306a\u3069\u306e\u53d7\u53d6\u7bb1\u3092\u305d\u306e\u5834\u3067\u518d\u53d7\u53d6</gray>");
        DEFAULT_MESSAGES.put("help.item", "<yellow>/meco item</yellow> <gray>- \u7d4c\u6e08\u624b\u5e33\u3092\u306a\u304f\u3057\u305f\u5834\u5408\u306b\u518d\u767a\u884c\u3059\u308b</gray>");
        DEFAULT_MESSAGES.put("help.log", "<yellow>/meco log</yellow> <gray>- \u76f4\u8fd1\u306e\u53d6\u5f15\u5c65\u6b74\u3092\u8868\u793a</gray>");
        DEFAULT_MESSAGES.put("help.news", "<yellow>/meco news</yellow> <gray>- \u7d4c\u6e08\u30cb\u30e5\u30fc\u30b9\u653e\u9001(\u81ea\u5206\u5b9b)\u306eON/OFF\u5207\u66ff</gray>");
        DEFAULT_MESSAGES.put("help.treasury", "<yellow>/meco treasury</yellow> <gray>- \u56fd\u5eab\u6b8b\u9ad8\u3092\u78ba\u8a8d</gray>");
        DEFAULT_MESSAGES.put("help.tutorial", "<yellow>/meco tutorial</yellow> <gray>- \u521d\u5fc3\u8005\u30ac\u30a4\u30c9\u3092\u958b\u304d\u76f4\u3059</gray>");
        DEFAULT_MESSAGES.put("help.donate-treasury", "<yellow>/meco donate treasury <\u91d1\u984d></yellow> <gray>- \u56fd\u5eab\u3078\u5bc4\u4ed8(\u4fe1\u7528\u30b9\u30b3\u30a2\u304c\u5c11\u3057\u4e0a\u304c\u308a\u307e\u3059)</gray>");
        DEFAULT_MESSAGES.put("help.donate-player", "<yellow>/meco donate <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u91d1\u984d></yellow> <gray>- \u4ed6\u30d7\u30ec\u30a4\u30e4\u30fc\u3078\u5bc4\u4ed8</gray>");
        DEFAULT_MESSAGES.put("help.admin-header", "<red><bold>--- \u7ba1\u7406\u8005\u30b3\u30de\u30f3\u30c9 ---</bold></red>");
        DEFAULT_MESSAGES.put("help.admin-give", "<red>/meco admin give <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u91d1\u984d></red> <gray>- \u6240\u6301\u91d1\u3092\u4ed8\u4e0e</gray>");
        DEFAULT_MESSAGES.put("help.admin-take", "<red>/meco admin take <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u91d1\u984d></red> <gray>- \u6240\u6301\u91d1\u3092\u6ca1\u53ce</gray>");
        DEFAULT_MESSAGES.put("help.admin-setcredit", "<red>/meco admin setcredit <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u30b9\u30b3\u30a2></red> <gray>- \u4fe1\u7528\u30b9\u30b3\u30a2\u3092\u8a2d\u5b9a</gray>");
        DEFAULT_MESSAGES.put("help.admin-reset", "<red>/meco admin reset <\u30d7\u30ec\u30a4\u30e4\u30fc></red> <gray>- \u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u5168\u30ea\u30bb\u30c3\u30c8</gray>");
        DEFAULT_MESSAGES.put("help.admin-discord", "<red>/meco admin discord test</red> <gray>- Discord Bot\u9023\u643a\u306e\u758e\u901a\u30c6\u30b9\u30c8\u9001\u4fe1</gray>");
        DEFAULT_MESSAGES.put("help.admin-selfcheck", "<red>/meco admin selfcheck [fix]</red> <gray>- \u30c7\u30fc\u30bf\u4e0d\u6574\u5408\u3092\u691c\u51fa(fix\u3067\u81ea\u52d5\u4fee\u5fa9)</gray>");
        DEFAULT_MESSAGES.put("help.admin-reload", "<red>/meco admin reload</red> <gray>- config.yml\u3092\u518d\u8aad\u8fbc</gray>");
        DEFAULT_MESSAGES.put("help.admin-save", "<red>/meco admin save</red> <gray>- \u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u5373\u6642\u4fdd\u5b58</gray>");
        DEFAULT_MESSAGES.put("help.admin-backup", "<red>/meco admin backup</red> <gray>- data.db\u3092\u4eca\u3059\u3050\u30d0\u30c3\u30af\u30a2\u30c3\u30d7</gray>");
        DEFAULT_MESSAGES.put("help.admin-config", "<red>/meco admin config</red> <gray>- config\u8a2d\u5b9a\u3092GUI\u3067\u7de8\u96c6</gray>");
        DEFAULT_MESSAGES.put("admin.no-permission", "<red>\u7ba1\u7406\u8005\u6a29\u9650\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("admin.usage", "<yellow>\u4f7f\u7528\u6cd5: /meco admin <give|take|setcredit|reset|reload|save|backup|config|gui> ...</yellow>");
        DEFAULT_MESSAGES.put("admin.reloaded", "<green><bold>config.yml / messages.yml \u3092\u518d\u8aad\u8fbc\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("admin.reloaded-alt", "<green><bold>config.yml / messages.yml\u3092\u518d\u8aad\u8fbc\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("admin.saved", "<green><bold>\u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u4fdd\u5b58\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("admin.backed-up", "<green><bold>data.db\u306e\u30d0\u30c3\u30af\u30a2\u30c3\u30d7\u3092\u4f5c\u6210\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("config.edit-prompt", "<yellow>{key} \u306e\u65b0\u3057\u3044\u5024\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002(\u30ad\u30e3\u30f3\u30bb\u30eb \u3067\u4e2d\u6b62)</yellow>");
        DEFAULT_MESSAGES.put("config.invalid-value", "<red>\u300c{value}\u300d\u306f\u3053\u306e\u8a2d\u5b9a\u306e\u578b\u3068\u3057\u3066\u89e3\u91c8\u3067\u304d\u307e\u305b\u3093\u3067\u3057\u305f\u3002\u6570\u5024\u8a2d\u5b9a\u306b\u306f\u6570\u5b57\u306e\u307f\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("config.updated", "<green><bold>{key} \u3092 {value} \u306b\u5909\u66f4\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("webpage.disabled", "<red>Web\u30c0\u30c3\u30b7\u30e5\u30dc\u30fc\u30c9\u304c\u7121\u52b9\u306b\u306a\u3063\u3066\u3044\u307e\u3059\u3002config.yml\u306ewebdashboard.enabled\u3092true\u306b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("webpage.link", "<green>\u3042\u306a\u305f\u306e\u500b\u4eba\u30da\u30fc\u30b8:</green> <click:open_url:'{url}'><underlined><aqua>{url}</aqua></underlined></click>");
        DEFAULT_MESSAGES.put("webpage.password-prompt", "<yellow>\u30aa\u30d5\u30e9\u30a4\u30f3\u64cd\u4f5c\u7528\u306e\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044(4\u6587\u5b57\u4ee5\u4e0a)\u3002\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3067\u4e2d\u6b62\u3002</yellow>");
        DEFAULT_MESSAGES.put("webpage.password-too-short", "<red>\u30d1\u30b9\u30ef\u30fc\u30c9\u306f4\u6587\u5b57\u4ee5\u4e0a\u306b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("webpage.password-set", "<green><bold>\u30aa\u30d5\u30e9\u30a4\u30f3\u64cd\u4f5c\u7528\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002\u3053\u308c\u3067\u30ed\u30b0\u30a4\u30f3\u3057\u3066\u3044\u306a\u3044\u6642\u3082Web\u30da\u30fc\u30b8\u304b\u3089\u64cd\u4f5c\u3067\u304d\u307e\u3059\u3002</bold></green>");
        DEFAULT_MESSAGES.put("webpage.password-cleared", "<green>\u30aa\u30d5\u30e9\u30a4\u30f3\u64cd\u4f5c\u7528\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u89e3\u9664\u3057\u307e\u3057\u305f\u3002\u4ee5\u5f8c\u306f\u30ed\u30b0\u30a4\u30f3\u4e2d\u306e\u307fWeb\u30da\u30fc\u30b8\u304b\u3089\u64cd\u4f5c\u3067\u304d\u307e\u3059\u3002</green>");
        DEFAULT_MESSAGES.put("webpage.password-status-set", "<gray>(\u30aa\u30d5\u30e9\u30a4\u30f3\u64cd\u4f5c\u7528\u30d1\u30b9\u30ef\u30fc\u30c9\u8a2d\u5b9a\u6e08\u307f\u3002\u5909\u66f4/\u89e3\u9664\u306f /meco webpage password )</gray>");
        DEFAULT_MESSAGES.put("webpage.password-status-unset", "<gray>(\u30ed\u30b0\u30a4\u30f3\u4e2d\u306e\u307fWeb\u304b\u3089\u64cd\u4f5c\u3067\u304d\u307e\u3059\u3002\u30aa\u30d5\u30e9\u30a4\u30f3\u3067\u3082\u64cd\u4f5c\u3057\u305f\u3044\u5834\u5408\u306f /meco webpage password \u3067\u30d1\u30b9\u30ef\u30fc\u30c9\u3092\u8a2d\u5b9a\u3057\u3066\u304f\u3060\u3055\u3044)</gray>");
        DEFAULT_MESSAGES.put("admin.need-playername", "<yellow>\u30d7\u30ec\u30a4\u30e4\u30fc\u540d\u3092\u6307\u5b9a\u3057\u3066\u304f\u3060\u3055\u3044\u3002</yellow>");
        DEFAULT_MESSAGES.put("admin.usage-give", "<yellow>\u4f7f\u7528\u6cd5: /meco admin give <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u91d1\u984d></yellow>");
        DEFAULT_MESSAGES.put("admin.give-success", "<green>{player} \u306b {amount}\u5186 \u3092\u4ed8\u4e0e\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("common.invalid-amount-number", "<red>\u91d1\u984d\u306f\u6570\u5024\u3067\u6307\u5b9a\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("admin.usage-take", "<yellow>\u4f7f\u7528\u6cd5: /meco admin take <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u91d1\u984d></yellow>");
        DEFAULT_MESSAGES.put("admin.take-success", "<green>{player} \u304b\u3089 {amount}\u5186 \u3092\u6ca1\u53ce\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("admin.usage-setcredit", "<yellow>\u4f7f\u7528\u6cd5: /meco admin setcredit <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u30b9\u30b3\u30a2></yellow>");
        DEFAULT_MESSAGES.put("admin.setcredit-success", "<green>{player} \u306e\u4fe1\u7528\u30b9\u30b3\u30a2\u3092 {score} \u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("admin.invalid-score-number", "<red>\u30b9\u30b3\u30a2\u306f\u6570\u5024\u3067\u6307\u5b9a\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("admin.usage-setgovdebt", "<yellow>\u4f7f\u7528\u6cd5: /meco admin setgovdebt <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u91d1\u984d>\uff080\u3067\u5b8c\u6e08\u6271\u3044\uff09</yellow>");
        DEFAULT_MESSAGES.put("admin.setgovdebt-success", "<green>{player} \u306e\u56fd\u55b6\u30ed\u30fc\u30f3\u6b8b\u50b5\u3092 {amount}\u5186 \u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("admin.reset-success", "<green><bold>{player} \u306e\u7d4c\u6e08\u30c7\u30fc\u30bf\u3092\u30ea\u30bb\u30c3\u30c8\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("admin.reset-note", "<gray>(\u4f1a\u793e\u30fb\u9023\u5408\u30c7\u30fc\u30bf\u306f\u5f71\u97ff\u304c\u5927\u304d\u3044\u305f\u3081\u5225\u9014 /meco admin reset \u306f\u500b\u4eba\u30c7\u30fc\u30bf\u306e\u307f\u5bfe\u8c61\u3067\u3059)</gray>");
        DEFAULT_MESSAGES.put("admin.usage-event", "<yellow>/meco admin event <random|boom|tax|bonus|recession></yellow>");
        DEFAULT_MESSAGES.put("admin.discord-not-configured", "<red>Bot\u30c8\u30fc\u30af\u30f3\u307e\u305f\u306f\u30c1\u30e3\u30f3\u30cd\u30ebid\u304cconfig.yml\u306b\u8a2d\u5b9a\u3055\u308c\u3066\u3044\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("admin.discord-config-hint", "<gray>system.discord-bot-token / system.discord-channel-id \u3092\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\u3002</gray>");
        DEFAULT_MESSAGES.put("admin.discord-test-sending", "<gray>Discord\u3078\u30c6\u30b9\u30c8\u9001\u4fe1\u4e2d...\u30b5\u30fc\u30d0\u30fc\u30b3\u30f3\u30bd\u30fc\u30eb\u306b\u7d50\u679c\u30ed\u30b0\u304c\u51fa\u307e\u3059\u3002</gray>");
        DEFAULT_MESSAGES.put("admin.usage-discord-test", "<yellow>\u4f7f\u7528\u6cd5: /meco admin discord test</yellow>");
        DEFAULT_MESSAGES.put("admin.selfcheck-ok", "<green><bold>\u3010\u81ea\u5df1\u8a3a\u65ad\u3011</bold> \u30c7\u30fc\u30bf\u4e0d\u6574\u5408\u306f\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3067\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("admin.selfcheck-issues", "<yellow><bold>\u3010\u81ea\u5df1\u8a3a\u65ad\u3011{count}\u4ef6\u306e\u4e0d\u6574\u5408\u3092\u691c\u51fa\u3057\u307e\u3057\u305f{note}</bold></yellow>");
        DEFAULT_MESSAGES.put("admin.selfcheck-more", "<gray>...\u4ed6 {count}\u4ef6\uff08\u30b3\u30f3\u30bd\u30fc\u30eb\u30ed\u30b0\u3092\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\uff09</gray>");
        DEFAULT_MESSAGES.put("admin.selfcheck-issue-line", "<gray>- {issue}</gray>");
        DEFAULT_MESSAGES.put("admin.usage-full", "<yellow>\u4f7f\u7528\u6cd5: /meco admin <give|take|setcredit|setgovdebt|reset|reload|save|event|discord|selfcheck|gui> ...</yellow>");
        DEFAULT_MESSAGES.put("log.empty", "<gray>\u53d6\u5f15\u5c65\u6b74\u306f\u307e\u3060\u3042\u308a\u307e\u305b\u3093\u3002</gray>");
        DEFAULT_MESSAGES.put("log.header", "<gold><bold>=== \u76f4\u8fd1\u306e\u53d6\u5f15\u5c65\u6b74 ===</bold></gold>");
        DEFAULT_MESSAGES.put("log.entry", "<gray>{entry}</gray>");
        DEFAULT_MESSAGES.put("news.enabled", "<green>\u7d4c\u6e08\u30cb\u30e5\u30fc\u30b9\u653e\u9001\u3092\u3010ON\u3011\u306b\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("news.disabled", "<yellow>\u7d4c\u6e08\u30cb\u30e5\u30fc\u30b9\u653e\u9001\u3092\u3010OFF\u3011\u306b\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("treasury.balance", "<gold>\u3010\u56fd\u5eab\u3011 <white>{amount}\u5186</white></gold>");
        DEFAULT_MESSAGES.put("treasury.admin-required", "<red>\u7ba1\u7406\u8005\u6a29\u9650\u304c\u5fc5\u8981\u3067\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("treasury.insufficient", "<red>\u56fd\u5eab\u6b8b\u9ad8\u304c\u4e0d\u8db3\u3057\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("common.invalid-amount", "<red>\u91d1\u984d\u304c\u4e0d\u6b63\u3067\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("treasury.usage", "<yellow>/meco treasury</yellow> / <yellow>/meco treasury bonus <\u91d1\u984d></yellow> / <yellow>/meco treasury set <\u91d1\u984d></yellow> / <yellow>/meco treasury donate <\u91d1\u984d></yellow>");
        DEFAULT_MESSAGES.put("treasury.set", "<gold>\u56fd\u5eab\u6b8b\u9ad8\u3092 <white>{amount}\u5186</white> \u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002</gold>");
        DEFAULT_MESSAGES.put("treasury.citizen-dividend", "<gold><bold>\u3010\u5e02\u6c11\u914d\u5f53\u3011</bold> \u56fd\u5eab\u304b\u3089 <white>{amount}\u5186</white> \u304c\u914d\u5f53\u3055\u308c\u307e\u3057\u305f\u3002</gold>");
        DEFAULT_MESSAGES.put("treasury.welfare", "<green><bold>\u3010\u751f\u6d3b\u652f\u63f4\u3011</bold> \u56fd\u5eab\u304b\u3089\u751f\u6d3b\u652f\u63f4\u91d1 <white>{amount}\u5186</white> \u304c\u7d66\u4ed8\u3055\u308c\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("donate.usage-treasury", "<yellow>/meco donate treasury <\u91d1\u984d></yellow> <gray>- \u56fd\u5eab\u3078\u5bc4\u4ed8(\u4fe1\u7528\u30b9\u30b3\u30a2\u304c\u5c11\u3057\u4e0a\u304c\u308a\u307e\u3059)</gray>");
        DEFAULT_MESSAGES.put("donate.usage-player", "<yellow>/meco donate <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u91d1\u984d></yellow> <gray>- \u4ed6\u30d7\u30ec\u30a4\u30e4\u30fc\u3078\u5bc4\u4ed8</gray>");
        DEFAULT_MESSAGES.put("donate.usage-treasury-amount", "<yellow>/meco donate treasury <\u91d1\u984d></yellow>");
        DEFAULT_MESSAGES.put("common.amount-must-be-positive", "<red>0\u5186\u3088\u308a\u5927\u304d\u3044\u91d1\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("common.insufficient-funds-simple", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("donate.treasury-thanks", "<green><bold>\u56fd\u5eab\u3078 {amount}\u5186 \u3092\u5bc4\u4ed8\u3057\u307e\u3057\u305f\u3002\u3042\u308a\u304c\u3068\u3046\u3054\u3056\u3044\u307e\u3059\uff01</bold></green>");
        DEFAULT_MESSAGES.put("common.target-offline", "<red>\u5bfe\u8c61\u30d7\u30ec\u30a4\u30e4\u30fc\u304c\u30aa\u30f3\u30e9\u30a4\u30f3\u3067\u306f\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("donate.cannot-self", "<red>\u81ea\u5206\u81ea\u8eab\u306b\u306f\u5bc4\u4ed8\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("donate.usage-player-amount", "<yellow>/meco donate <\u30d7\u30ec\u30a4\u30e4\u30fc> <\u91d1\u984d></yellow>");
        DEFAULT_MESSAGES.put("donate.player-success", "<green><bold>{player} \u306b {amount}\u5186 \u3092\u5bc4\u4ed8\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("donate.player-received", "<gold><bold>\u3010\u5bc4\u4ed8\u3011</bold> {player} \u304b\u3089 {amount}\u5186 \u306e\u5bc4\u4ed8\u3092\u53d7\u3051\u53d6\u308a\u307e\u3057\u305f\u3002</gold>");
        DEFAULT_MESSAGES.put("guarantor.usage-request", "<yellow>/meco guarantor request <\u30d7\u30ec\u30a4\u30e4\u30fc></yellow> <gray>- \u4fdd\u8a3c\u4eba\u3092\u4f9d\u983c\u3059\u308b</gray>");
        DEFAULT_MESSAGES.put("guarantor.usage-accept", "<yellow>/meco guarantor accept</yellow> <gray>- \u4f9d\u983c\u3092\u627f\u8afe\u3059\u308b</gray>");
        DEFAULT_MESSAGES.put("guarantor.usage-decline", "<yellow>/meco guarantor decline</yellow> <gray>- \u4f9d\u983c\u3092\u62d2\u5426\u3059\u308b</gray>");
        DEFAULT_MESSAGES.put("guarantor.no-proposal-expired", "<red>\u6709\u52b9\u306a\u4fdd\u8a3c\u4eba\u4f9d\u983c\u304c\u3042\u308a\u307e\u305b\u3093\u3002\uff08\u671f\u9650\u5207\u308c\u306e\u53ef\u80fd\u6027\u304c\u3042\u308a\u307e\u3059\uff09</red>");
        DEFAULT_MESSAGES.put("guarantor.borrower-no-debt", "<red>\u4f9d\u983c\u8005\u306b\u306f\u3082\u3046\u5bfe\u8c61\u306e\u501f\u91d1\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("guarantor.became", "<green><bold>{player} \u306e\u4fdd\u8a3c\u4eba\u306b\u306a\u308a\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("guarantor.became-notice", "<green>{player} \u304c\u3042\u306a\u305f\u306e\u4fdd\u8a3c\u4eba\u306b\u306a\u308a\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("guarantor.declined-self", "<yellow>\u4fdd\u8a3c\u4eba\u4f9d\u983c\u3092\u62d2\u5426\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("guarantor.declined-notice", "<red>{player} \u306f\u4fdd\u8a3c\u4eba\u4f9d\u983c\u3092\u62d2\u5426\u3057\u307e\u3057\u305f\u3002</red>");
        DEFAULT_MESSAGES.put("guarantor.no-proposal", "<red>\u6709\u52b9\u306a\u4fdd\u8a3c\u4eba\u4f9d\u983c\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("guarantor.no-debt", "<red>\u3042\u306a\u305f\u306b\u306f\u73fe\u5728\u3001\u5bfe\u8c61\u3068\u306a\u308b\u501f\u91d1\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("guarantor.already-set", "<red>\u3059\u3067\u306b\u4fdd\u8a3c\u4eba\u304c\u8a2d\u5b9a\u3055\u308c\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("guarantor.usage-request-arg", "<yellow>/meco guarantor request <\u30d7\u30ec\u30a4\u30e4\u30fc></yellow>");
        DEFAULT_MESSAGES.put("guarantor.cannot-self", "<red>\u81ea\u5206\u81ea\u8eab\u3092\u4fdd\u8a3c\u4eba\u306b\u306f\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("guarantor.requested", "<green><bold>{player} \u306b\u4fdd\u8a3c\u4eba\u3092\u4f9d\u983c\u3057\u307e\u3057\u305f\u3002\uff085\u5206\u4ee5\u5185\u306b\u8fd4\u7b54\u304c\u5fc5\u8981\uff09</bold></green>");
        DEFAULT_MESSAGES.put("guarantor.request-received", "<gold><bold>\u3010\u4fdd\u8a3c\u4eba\u4f9d\u983c\u3011</bold> {player} \u304c\u3042\u306a\u305f\u306b\u878d\u8cc7\u306e\u4fdd\u8a3c\u4eba\u3092\u4f9d\u983c\u3057\u3066\u3044\u307e\u3059\u3002</gold>");
        DEFAULT_MESSAGES.put("guarantor.request-instructions", "<yellow>\u627f\u8afe: /meco guarantor accept \uff0f \u62d2\u5426: /meco guarantor decline</yellow>");
        DEFAULT_MESSAGES.put("guarantor.request-warning", "<gray>\u203b\u4fdd\u8a3c\u4eba\u306b\u306a\u308b\u3068\u3001\u4f9d\u983c\u8005\u304c\u8fd4\u6e08\u4e0d\u80fd\u306b\u306a\u3063\u305f\u969b\u306b\u4fe1\u7528\u30b9\u30b3\u30a2\u3078\u5f71\u97ff\u3057\u307e\u3059\u3002</gray>");
        DEFAULT_MESSAGES.put("guarantor.usage", "<yellow>\u4f7f\u7528\u6cd5: /meco guarantor <request|accept|decline></yellow>");
        DEFAULT_MESSAGES.put("trade.usage", "<yellow>\u4f7f\u7528\u6cd5: /meco trade <\u30d7\u30ec\u30a4\u30e4\u30fc|accept|decline></yellow>");
        DEFAULT_MESSAGES.put("trade.request-sent", "<green><bold>{player} \u306b\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u3092\u7533\u3057\u8fbc\u307f\u307e\u3057\u305f\u3002\uff0860\u79d2\u4ee5\u5185\u306b\u8fd4\u7b54\u304c\u5fc5\u8981\uff09</bold></green>");
        DEFAULT_MESSAGES.put("trade.request-received", "<gold><bold>\u3010\u4ea4\u63db\u4f9d\u983c\u3011</bold> {player} \u304c\u3042\u306a\u305f\u306b\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u3092\u7533\u3057\u8fbc\u3093\u3067\u3044\u307e\u3059\u3002</gold>");
        DEFAULT_MESSAGES.put("trade.request-instructions", "<yellow>\u627f\u8afe: /meco trade accept \uff0f \u62d2\u5426: /meco trade decline</yellow>");
        DEFAULT_MESSAGES.put("trade.no-request", "<red>\u6709\u52b9\u306a\u4ea4\u63db\u4f9d\u983c\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("trade.no-request-expired", "<red>\u6709\u52b9\u306a\u4ea4\u63db\u4f9d\u983c\u304c\u3042\u308a\u307e\u305b\u3093\u3002\uff08\u671f\u9650\u5207\u308c\u306e\u53ef\u80fd\u6027\u304c\u3042\u308a\u307e\u3059\uff09</red>");
        DEFAULT_MESSAGES.put("trade.declined-self", "<yellow>\u4ea4\u63db\u4f9d\u983c\u3092\u62d2\u5426\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("trade.declined-notice", "<red>{player} \u306f\u4ea4\u63db\u4f9d\u983c\u3092\u62d2\u5426\u3057\u307e\u3057\u305f\u3002</red>");
        DEFAULT_MESSAGES.put("trade.cannot-self", "<red>\u81ea\u5206\u81ea\u8eab\u3068\u306f\u4ea4\u63db\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("trade.already-in-progress", "<red>\u65e2\u306b\u4ea4\u63db\u304c\u9032\u884c\u4e2d\u304b\u3001\u4fdd\u7559\u4e2d\u306e\u4f9d\u983c\u304c\u3042\u308a\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("trade.money-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u63d0\u793a\u3059\u308b\u91d1\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff080\u3067\u30ad\u30e3\u30f3\u30bb\u30eb\u53ef\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("trade.money-set", "<green>\u63d0\u793a\u91d1\u984d\u3092 {amount}\u5186 \u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("trade.money-insufficient", "<red>\u6240\u6301\u91d1\u304c\u8db3\u308a\u306a\u3044\u305f\u3081\u3001\u305d\u306e\u91d1\u984d\u306f\u63d0\u793a\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("trade.cancelled", "<red><bold>\u53d6\u5f15\u306f\u30ad\u30e3\u30f3\u30bb\u30eb\u3055\u308c\u307e\u3057\u305f\u3002\u63d0\u793a\u3057\u3066\u3044\u305f\u30a2\u30a4\u30c6\u30e0\u306f\u8fd4\u5374\u3055\u308c\u307e\u3057\u305f\u3002</bold></red>");
        DEFAULT_MESSAGES.put("trade.completed", "<green><bold>\u3010\u6210\u7acb\u3011\u30a2\u30a4\u30c6\u30e0\u4ea4\u63db\u304c\u5b8c\u4e86\u3057\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("help.trade", "<yellow>/meco trade <\u30d7\u30ec\u30a4\u30e4\u30fc></yellow> <gray>- \u5b89\u5168\u306b\u30a2\u30a4\u30c6\u30e0\u30fb\u304a\u91d1\u3092\u4ea4\u63db\u3059\u308b</gray>");
        DEFAULT_MESSAGES.put("quest.max-reached", "<red>\u63b2\u793a\u3067\u304d\u308b\u4f9d\u983c\u6570\u306e\u4e0a\u9650({count}\u4ef6)\u306b\u9054\u3057\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("quest.post-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u4f9d\u983c\u306e\u5831\u916c\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u3042\u306a\u305f\u306e\u4eca\u306e\u5834\u6240\u304c\u76ee\u7684\u5730\u306b\u306a\u308a\u307e\u3059\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("quest.withdrawn", "<yellow>\u4f9d\u983c\u3092\u53d6\u308a\u4e0b\u3052\u3001\u5831\u916c {amount}\u5186 \u3092\u8fd4\u91d1\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("quest.cannot-withdraw", "<gray>\u53d7\u6ce8\u4e2d\u307e\u305f\u306f\u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d\u306e\u4f9d\u983c\u306f\u53d6\u308a\u4e0b\u3052\u3089\u308c\u307e\u305b\u3093\u3002</gray>");
        DEFAULT_MESSAGES.put("quest.accepted", "<green><bold>\u4f9d\u983c\u3092\u53d7\u6ce8\u3057\u307e\u3057\u305f\uff01\u76ee\u7684\u5730({world} {x}, {y}, {z})\u307e\u3067\u79fb\u52d5\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></green>");
        DEFAULT_MESSAGES.put("quest.accepted-notice", "<aqua>{player} \u304c\u3042\u306a\u305f\u306e\u4f9d\u983c\u3092\u53d7\u6ce8\u3057\u307e\u3057\u305f\u3002</aqua>");
        DEFAULT_MESSAGES.put("quest.abandoned", "<yellow>\u4f9d\u983c\u3092\u653e\u68c4\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("quest.not-acceptable", "<gray>\u3053\u306e\u4f9d\u983c\u306f\u73fe\u5728\u53d7\u6ce8\u3067\u304d\u307e\u305b\u3093\u3002</gray>");
        DEFAULT_MESSAGES.put("quest.team-post-prompt-reward", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u30c1\u30fc\u30e0\u4f9d\u983c\u306e\u300c\u4e00\u4eba\u3042\u305f\u308a\u300d\u306e\u5831\u916c\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u3042\u306a\u305f\u306e\u4eca\u306e\u5834\u6240\u304c\u76ee\u7684\u5730\u306b\u306a\u308a\u307e\u3059\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("quest.team-post-prompt-size", "<gold><bold>\u6b21\u306b\u3001\u5fc5\u8981\u306a\u4eba\u6570\uff082\u301c{max}\u4eba\uff09\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("quest.team-size-too-small", "<red>\u30c1\u30fc\u30e0\u4f9d\u983c\u306e\u4eba\u6570\u306f2\u4eba\u4ee5\u4e0a\u3092\u6307\u5b9a\u3057\u3066\u304f\u3060\u3055\u3044\u30021\u4eba\u306e\u5834\u5408\u306f\u901a\u5e38\u306e\u4f9d\u983c\u63b2\u793a\u3092\u3054\u5229\u7528\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("quest.team-size-too-large", "<red>\u30c1\u30fc\u30e0\u4f9d\u983c\u306e\u4eba\u6570\u306f\u6700\u5927{max}\u4eba\u307e\u3067\u3067\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("quest.team-joined", "<green><bold>\u30c1\u30fc\u30e0\u4f9d\u983c\u306b\u53c2\u52a0\u3057\u307e\u3057\u305f\uff01\u76ee\u7684\u5730({world} {x}, {y}, {z})\u307e\u3067\u79fb\u52d5\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08{joined}/{required}\u4eba\uff09</bold></green>");
        DEFAULT_MESSAGES.put("quest.team-join-notice", "<aqua>{player} \u304c\u3042\u306a\u305f\u306e\u30c1\u30fc\u30e0\u4f9d\u983c\u306b\u53c2\u52a0\u3057\u307e\u3057\u305f\u3002\uff08{joined}/{required}\u4eba\uff09</aqua>");
        DEFAULT_MESSAGES.put("quest.team-staffed", "<green><bold>\u30c1\u30fc\u30e0\u306e\u4eba\u6570\u304c\u63c3\u3044\u307e\u3057\u305f\uff01\u5168\u54e1\u3067\u76ee\u7684\u5730\u307e\u3067\u79fb\u52d5\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></green>");
        DEFAULT_MESSAGES.put("quest.team-left-recruiting", "<yellow>\u30c1\u30fc\u30e0\u4f9d\u983c\u306e\u53c2\u52a0\u3092\u53d6\u308a\u6d88\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("quest.team-abandoned", "<red>\u30c1\u30fc\u30e0\u30e1\u30f3\u30d0\u30fc\u304c\u96e2\u8131\u3057\u305f\u305f\u3081\u3001\u4f9d\u983c\u306f\u52df\u96c6\u4e2d\u306b\u623b\u308a\u307e\u3057\u305f\u3002</red>");
        DEFAULT_MESSAGES.put("quest.team-withdraw-blocked", "<gray>\u53c2\u52a0\u8005\u304c\u3044\u308b\u30c1\u30fc\u30e0\u4f9d\u983c\u306f\u53d6\u308a\u4e0b\u3052\u3089\u308c\u307e\u305b\u3093\u3002\u5168\u54e1\u304c\u96e2\u8131\u3059\u308b\u306e\u3092\u5f85\u3063\u3066\u304f\u3060\u3055\u3044\u3002</gray>");
        DEFAULT_MESSAGES.put("quest.team-arrived-waiting", "<yellow>\u76ee\u7684\u5730\u306b\u5230\u9054\u3057\u307e\u3057\u305f\u3002\u4ed6\u306e\u30e1\u30f3\u30d0\u30fc\u306e\u5230\u7740\u3092\u5f85\u3063\u3066\u3044\u307e\u3059\u3002\uff08{arrived}/{required}\u4eba\uff09</yellow>");
        DEFAULT_MESSAGES.put("quest.team-completed", "<green><bold>\u3010\u30c1\u30fc\u30e0\u4f9d\u983c\u9054\u6210\u3011 \u63a2\u7d22\u4f9d\u983c\u3092\u9054\u6210\u3057\u3001\u5831\u916c {amount}\u5186 \u3092\u53d7\u3051\u53d6\u308a\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("admin.search-player-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u691c\u7d22\u3057\u305f\u3044\u30d7\u30ec\u30a4\u30e4\u30fc\u540d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("admin.give-prompt", "<gold><bold>\u4ed8\u4e0e\u3059\u308b\u91d1\u984d\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("admin.take-prompt", "<gold><bold>\u6ca1\u53ce\u3059\u308b\u91d1\u984d\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("admin.setcredit-prompt", "<gold><bold>\u8a2d\u5b9a\u3059\u308b\u4fe1\u7528\u30b9\u30b3\u30a2(0\u301c800)\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("admin.reset-confirm", "<red><bold>\u672c\u5f53\u306b\u30ea\u30bb\u30c3\u30c8\u3059\u308b\u5834\u5408\u306f\u3082\u3046\u4e00\u5ea6\u30af\u30ea\u30c3\u30af\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff0815\u79d2\u4ee5\u5185\uff09</bold></red>");
        DEFAULT_MESSAGES.put("event.random-triggered", "<green>\u30e9\u30f3\u30c0\u30e0\u7d4c\u6e08\u30a4\u30d9\u30f3\u30c8\u3092\u767a\u751f\u3055\u305b\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("event.boom-triggered", "<green>\u9ec4\u91d1\u30e9\u30c3\u30b7\u30e5\u3092\u767a\u751f\u3055\u305b\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("event.tax-triggered", "<green>\u624b\u6570\u6599\u9ad8\u9a30\u3092\u767a\u751f\u3055\u305b\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("event.bonus-triggered", "<green>\u30dc\u30fc\u30ca\u30b9\u652f\u7d66\u30c7\u30fc\u3092\u767a\u751f\u3055\u305b\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("event.recession-triggered", "<green>\u51ac\u306e\u6642\u4ee3\u3092\u767a\u751f\u3055\u305b\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("collateral.select-item", "<red>\u62c5\u4fdd\u30a2\u30a4\u30c6\u30e0\u3092\u9078\u629e\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("collateral.borrowed", "<green><bold>{item} \u3092\u62c5\u4fdd\u306b {amount}\u5186 \u3092\u501f\u308a\u5165\u308c\u307e\u3057\u305f\u3002\u8fd4\u6e08\u671f\u9650\u306f {minutes}\u5206\u5f8c\u3067\u3059\u3002</bold></green>");
        DEFAULT_MESSAGES.put("collateral.repaid", "<green><bold>\u62c5\u4fdd\u878d\u8cc7\u3092\u5b8c\u6e08\u3057\u3001\u30a2\u30a4\u30c6\u30e0\u3092\u53d6\u308a\u623b\u3057\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("collateral.repay-insufficient", "<red>\u8fd4\u6e08\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002\uff08\u5fc5\u8981\u984d: {amount}\u5186\uff09</red>");
        DEFAULT_MESSAGES.put("treasury.balance-gui", "<gold><bold>\ud83c\udfdb \u56fd\u5eab\u6b8b\u9ad8: {amount}\u5186</bold></gold>");
        DEFAULT_MESSAGES.put("treasury.balance-note", "<gray>\u6cd5\u4eba\u7a0e\u30fb\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u624b\u6570\u6599\u306a\u3069\u304c\u7a4d\u307f\u7acb\u3066\u3089\u308c\u3066\u3044\u307e\u3059\u3002</gray>");
        DEFAULT_MESSAGES.put("donate.treasury-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u56fd\u5eab\u3078\u5bc4\u4ed8\u3059\u308b\u91d1\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u30ad\u30e3\u30f3\u30bb\u30eb\u3059\u308b\u5834\u5408\u306f\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3068\u5165\u529b\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("tutorial.completed", "<green>\u30c1\u30e5\u30fc\u30c8\u30ea\u30a2\u30eb\u3092\u5b8c\u4e86\u3057\u307e\u3057\u305f\uff01</green>");
        DEFAULT_MESSAGES.put("auction.list-price-prompt", "<gold>\u958b\u59cb\u4fa1\u683c\u3092\u30c1\u30e3\u30c3\u30c8\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3067\u8fd4\u5374\u3057\u307e\u3059\u3002</gold>");
        DEFAULT_MESSAGES.put("collateral.item-not-valid", "<red>\u305d\u306e\u30a2\u30a4\u30c6\u30e0\u306f\u62c5\u4fdd\u3068\u3057\u3066\u8a55\u4fa1\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("auction.listing-limit", "<red>\u540c\u6642\u51fa\u54c1\u6570\u306e\u4e0a\u9650\uff08{count}\u4ef6\uff09\u306b\u9054\u3057\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("auction.listing-ended", "<red>\u3053\u306e\u51fa\u54c1\u306f\u3059\u3067\u306b\u7d42\u4e86\u3057\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("auction.cannot-bid-own", "<red>\u81ea\u5206\u306e\u51fa\u54c1\u306b\u306f\u5165\u672d\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("auction.items-inventory-full", "<yellow>\u30a4\u30f3\u30d9\u30f3\u30c8\u30ea\u304c\u6e80\u676f\u306e\u305f\u3081 {count} \u500b\u3092\u53d7\u3051\u53d6\u308c\u307e\u305b\u3093\u3067\u3057\u305f\u3002\u7a7a\u304d\u3092\u4f5c\u3063\u3066\u304b\u3089 /meco collect \u3067\u3082\u3046\u4e00\u5ea6\u304a\u8a66\u3057\u304f\u3060\u3055\u3044\u3002</yellow>");
        DEFAULT_MESSAGES.put("auction.cancel-has-bid", "<red>\u3059\u3067\u306b\u5165\u672d\u304c\u5165\u3063\u3066\u3044\u308b\u305f\u3081\u3001\u3053\u306e\u51fa\u54c1\u306f\u53d6\u308a\u4e0b\u3052\u3089\u308c\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("auction.cancel-success", "<green><bold>{item} \u306e\u51fa\u54c1\u3092\u53d6\u308a\u4e0b\u3052\u307e\u3057\u305f\u3002\uff08\u53d7\u53d6\u7bb1\u3092\u78ba\u8a8d\u3057\u3066\u304f\u3060\u3055\u3044\uff09</bold></green>");
        DEFAULT_MESSAGES.put("auction.search-prompt", "<gold>\u691c\u7d22\u3057\u305f\u3044\u30a2\u30a4\u30c6\u30e0\u540d\uff08\u306e\u4e00\u90e8\uff09\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\u300c\u30af\u30ea\u30a2\u300d\u3067\u691c\u7d22\u3092\u89e3\u9664\u3001\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3067\u9589\u3058\u307e\u3059\u3002</gold>");
        DEFAULT_MESSAGES.put("auction.search-set", "<green>\u691c\u7d22\u8a9e\u3092\u300c{query}\u300d\u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("auction.search-cleared", "<gray>\u691c\u7d22\u6761\u4ef6\u3092\u30af\u30ea\u30a2\u3057\u307e\u3057\u305f\u3002</gray>");
        DEFAULT_MESSAGES.put("auction.bid-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u672d\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u6700\u4f4e {amount}\u5186 / \u30ad\u30e3\u30f3\u30bb\u30eb\u3059\u308b\u5834\u5408\u306f\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3068\u5165\u529b\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("insurance.already-active", "<red>\u3059\u3067\u306b\u4fdd\u967a\u306b\u52a0\u5165\u4e2d\u3067\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("insurance.joined", "<green><bold>\u751f\u547d\u4fdd\u967a\u306b\u52a0\u5165\u3057\u307e\u3057\u305f\uff01\u6709\u52b9\u671f\u9593: {minutes}\u5206</bold></green>");
        DEFAULT_MESSAGES.put("insurance.premium-insufficient", "<red>\u4fdd\u967a\u6599\uff08{amount}\u5186\uff09\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("personal.deposit-step", "<green>{amount}\u5186 \u9810\u91d1\u3002</green>");
        DEFAULT_MESSAGES.put("personal.deposit-full", "<green>\u5168\u984d\u9810\u91d1\u3002</green>");
        DEFAULT_MESSAGES.put("personal.withdraw-step", "<red>{amount}\u5186 \u5f15\u51fa\u3002</red>");
        DEFAULT_MESSAGES.put("personal.withdraw-full", "<red>\u5168\u984d\u5f15\u51fa\u3002</red>");
        DEFAULT_MESSAGES.put("personal.deposit-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u9810\u91d1\u3057\u305f\u3044\u91d1\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u30ad\u30e3\u30f3\u30bb\u30eb\u3059\u308b\u5834\u5408\u306f\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3068\u5165\u529b\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("personal.withdraw-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u5f15\u304d\u51fa\u3057\u305f\u3044\u91d1\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u30ad\u30e3\u30f3\u30bb\u30eb\u3059\u308b\u5834\u5408\u306f\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3068\u5165\u529b\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("loan.cannot-borrow-self-bank", "<red>\u81ea\u5206\u81ea\u8eab\u306e\u9280\u884c\u304b\u3089\u501f\u308a\u308b\u3053\u3068\u306f\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("loan.already-has-debt", "<red>\u65e2\u306b\u4ed6\u306e\u30d7\u30ec\u30a4\u30e4\u30fc\u304b\u3089\u306e\u501f\u91d1\u304c\u3042\u308a\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("loan.plan-not-exist", "<red>\u3053\u306e\u30d7\u30e9\u30f3\u306f\u3059\u3067\u306b\u5b58\u5728\u3057\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("loan.bank-capital-insufficient", "<red>\u3053\u306e\u9280\u884c\u306e\u8cc7\u672c\u91d1\u30d7\u30fc\u30eb\u304c\u4e0d\u8db3\u3057\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("loan.player-approved-notice", "<green><bold>\u3010\u878d\u8cc7\u6210\u7acb\u3011 {player} \u304c\u3042\u306a\u305f\u306e\u30d7\u30e9\u30f3\u304b\u3089 {amount}\u5186 \u3092\u501f\u308a\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("loan.player-repaid-full-plain", "<green>\u30d7\u30ec\u30a4\u30e4\u30fc\u9593\u306e\u501f\u91d1\u3092\u5b8c\u6e08\u3057\u307e\u3057\u305f\uff01</green>");
        DEFAULT_MESSAGES.put("loan.repay-partial", "<yellow>{amount}\u5186 \u3092\u8fd4\u6e08\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("loan.repay-full-insufficient", "<red>\u5168\u984d\u8fd4\u6e08\u3059\u308b\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("loan.gov-repaid-full-plain", "<green>\u56fd\u55b6\u516c\u5eab\u306e\u30ed\u30fc\u30f3\u3092\u5b8c\u6e08\u3057\u307e\u3057\u305f\uff01</green>");
        DEFAULT_MESSAGES.put("loan.gov-repaid-full-bold", "<green><bold>\u56fd\u55b6\u516c\u5eab\u306e\u30ed\u30fc\u30f3\u3092\u5168\u984d\u5b8c\u6e08\u3057\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("bank.already-established", "<red>\u3059\u3067\u306b\u9280\u884c\u3092\u8a2d\u7acb\u3057\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("bank.established", "<green><bold>\u9280\u884c\u3092\u8a2d\u7acb\u3057\u307e\u3057\u305f\uff01\u982d\u53d6\u30d1\u30cd\u30eb\u304c\u958b\u653e\u3055\u308c\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("bank.establish-cost-insufficient", "<red>\u8a2d\u7acb\u8cbb\u7528\uff08{amount}\u5186\uff09\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("bank.capital-added", "<green>\u8cc7\u672c\u91d1\u30d7\u30fc\u30eb\u306b +10,000\u5186 \u8ffd\u52a0\u3002</green>");
        DEFAULT_MESSAGES.put("bank.capital-withdrawn", "<red>\u8cc7\u672c\u91d1\u30d7\u30fc\u30eb\u304b\u3089 -10,000\u5186 \u5f15\u304d\u51fa\u3057\u3002</red>");
        DEFAULT_MESSAGES.put("bank.capital-insufficient-pool", "<red>\u8cc7\u672c\u91d1\u30d7\u30fc\u30eb\u306b\u305d\u308c\u3060\u3051\u306e\u8cc7\u91d1\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("bank.plan-withdrawn", "<yellow>\u30d7\u30e9\u30f3({slot}\u67a0\u76ee)\u3092\u53d6\u308a\u4e0b\u3052\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("bank.no-published-plan", "<red>\u516c\u958b\u4e2d\u306e\u30d7\u30e9\u30f3\u306f\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("bank.plan-amount-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u878d\u8cc7\u984d\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u30ad\u30e3\u30f3\u30bb\u30eb\u3059\u308b\u5834\u5408\u306f\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3068\u5165\u529b\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("bank.plan-interest-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u5229\u606f(%)\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u30ad\u30e3\u30f3\u30bb\u30eb\u3059\u308b\u5834\u5408\u306f\u300c\u30ad\u30e3\u30f3\u30bb\u30eb\u300d\u3068\u5165\u529b\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("bank.plan-published", "<green><bold>\u878d\u8cc7\u30d7\u30e9\u30f3({slot}\u67a0\u76ee)\u3092\u5e02\u5834\u306b\u516c\u958b\u3057\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("loan.gov-cap-exceeded", "<red>\u501f\u5165\u4e0a\u9650\uff08{amount}\u5186\uff09\u3092\u8d85\u3048\u308b\u305f\u3081\u3001\u3053\u308c\u4ee5\u4e0a\u501f\u308a\u3089\u308c\u307e\u305b\u3093\u3002\u4fe1\u7528\u30b9\u30b3\u30a2\u3092\u4e0a\u3052\u308b\u304b\u8fd4\u6e08\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.already-exists", "<red>\u3059\u3067\u306b\u5b9a\u671f\u9810\u91d1\u304c\u5b58\u5728\u3057\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot1-created", "<green>{amount}\u5186 \u3092\u5b9a\u671f\u9810\u91d1\u306b\u9810\u3051\u307e\u3057\u305f\uff08\u6e80\u671f: {seconds}\u79d2\u5f8c / \u5229\u7387: +{rate}%\uff09</green>");
        DEFAULT_MESSAGES.put("deposit.funds-insufficient", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c {amount}\u5186 \u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot1-none", "<red>\u5b9a\u671f\u9810\u91d1\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot1-not-matured", "<red>\u307e\u3060\u6e80\u671f\u3092\u8fce\u3048\u3066\u3044\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot2-exists", "<red>\u3059\u3067\u306b2\u67a0\u76ee\u306b\u5b9a\u671f\u9810\u91d1\u304c\u5b58\u5728\u3057\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot2-created", "<aqua>{amount}\u5186 \u30922\u67a0\u76ee\u306e\u5b9a\u671f\u9810\u91d1\u306b\u9810\u3051\u307e\u3057\u305f\u3002</aqua>");
        DEFAULT_MESSAGES.put("deposit.slot2-none", "<red>2\u67a0\u76ee\u306b\u5b9a\u671f\u9810\u91d1\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot2-not-matured", "<red>2\u67a0\u76ee\u306f\u307e\u3060\u6e80\u671f\u3092\u8fce\u3048\u3066\u3044\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot3-exists", "<red>\u3059\u3067\u306b3\u67a0\u76ee\u306b\u5b9a\u671f\u9810\u91d1\u304c\u5b58\u5728\u3057\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot3-created", "<light_purple>{amount}\u5186 \u30923\u67a0\u76ee\u306e\u5b9a\u671f\u9810\u91d1\u306b\u9810\u3051\u307e\u3057\u305f\u3002</light_purple>");
        DEFAULT_MESSAGES.put("deposit.slot3-none", "<red>3\u67a0\u76ee\u306b\u5b9a\u671f\u9810\u91d1\u304c\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("deposit.slot3-not-matured", "<red>3\u67a0\u76ee\u306f\u307e\u3060\u6e80\u671f\u3092\u8fce\u3048\u3066\u3044\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("quest.completed", "<green><bold>\u3010\u4f9d\u983c\u9054\u6210\u3011 \u63a2\u7d22\u4f9d\u983c\u3092\u9054\u6210\u3057\u3001\u5831\u916c {amount}\u5186 \u3092\u53d7\u3051\u53d6\u308a\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("quest.completed-notice", "<aqua>{player} \u304c\u3042\u306a\u305f\u306e\u4f9d\u983c\u3092\u9054\u6210\u3057\u307e\u3057\u305f\u3002</aqua>");
        DEFAULT_MESSAGES.put("quest.relisted", "<aqua>\u3042\u306a\u305f\u306e\u4f9d\u983c\u304c\u518d\u51fa\u54c1\u3055\u308c\u307e\u3057\u305f\u3002\uff08\u5831\u916c {amount}\u5186 \u3092\u518d\u5fb4\u53ce\uff09</aqua>");
        DEFAULT_MESSAGES.put("quest.removed-insufficient-funds", "<red>\u8cc7\u91d1\u4e0d\u8db3\u306e\u305f\u3081\u3001\u4f9d\u983c\u30dc\u30fc\u30c9\u304b\u3089\u4f9d\u983c\u3092\u53d6\u308a\u4e0b\u3052\u307e\u3057\u305f\u3002</red>");
        DEFAULT_MESSAGES.put("auction.listing-cancelled-returned", "<gray>\u3010\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u30cf\u30a6\u30b9\u3011 \u51fa\u54c1\u624b\u7d9a\u304d\u4e2d\u3060\u3063\u305f\u30a2\u30a4\u30c6\u30e0\u3092\u8fd4\u5374\u3057\u307e\u3057\u305f\u3002</gray>");
        DEFAULT_MESSAGES.put("loan.login-seized-full", "<green><bold>[\u5b8c\u6e08] \u30ed\u30b0\u30a4\u30f3\u6642\u306b\u501f\u91d1\u304c\u3059\u3079\u3066\u5fb4\u53ce\u3055\u308c\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("loan.login-seized-partial", "<red><bold>[\u57f7\u884c]</bold> \u501f\u91d1 <yellow>{amount}\u5186</yellow> \u5f37\u5236\u5fb4\u53ce\u3002</red>");
        DEFAULT_MESSAGES.put("loan.guarantor-seized-notice", "<dark_red><bold>\u3010\u4fdd\u8a3c\u50b5\u52d9\u3011</bold> {player} \u306e\u501f\u91d1\u8fd4\u6e08\u4e0d\u80fd\u306b\u3064\u304d\u3001\u4fdd\u8a3c\u4eba\u3068\u3057\u3066 {amount}\u5186 \u304c\u5fb4\u53ce\u3055\u308c\u3001\u4fe1\u7528\u30b9\u30b3\u30a2\u304c\u4f4e\u4e0b\u3057\u307e\u3057\u305f\u3002</dark_red>");
        DEFAULT_MESSAGES.put("loan.guarantor-repaid-full", "<green><bold>[\u5b8c\u6e08] \u4fdd\u8a3c\u4eba\u306b\u3088\u308a\u501f\u91d1\u304c\u5b8c\u6e08\u3055\u308c\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("insurance.claim-cooldown", "<gray>[\u4fdd\u967a] \u30af\u30fc\u30eb\u30c0\u30a6\u30f3\u4e2d\u306e\u305f\u3081\u3001\u4eca\u56de\u306e\u6b7b\u4ea1\u3067\u306f\u4fdd\u967a\u91d1\u306f\u652f\u6255\u308f\u308c\u307e\u305b\u3093\u3067\u3057\u305f\u3002</gray>");
        DEFAULT_MESSAGES.put("input.cancelled-auction-item-returned", "<gray>\u51fa\u54c1\u3092\u30ad\u30e3\u30f3\u30bb\u30eb\u3057\u3001\u30a2\u30a4\u30c6\u30e0\u3092\u8fd4\u5374\u3057\u307e\u3057\u305f\u3002</gray>");
        DEFAULT_MESSAGES.put("input.cancelled", "<gray>\u5165\u529b\u3092\u30ad\u30e3\u30f3\u30bb\u30eb\u3057\u307e\u3057\u305f\u3002</gray>");
        DEFAULT_MESSAGES.put("admin.player-not-found", "<red>\u30d7\u30ec\u30a4\u30e4\u30fc\u300c{name}\u300d\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3067\u3057\u305f\u3002</red>");
        DEFAULT_MESSAGES.put("quest.reward-insufficient", "<red>\u6240\u6301\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("quest.posted", "<green><bold>\u4f9d\u983c\u3092\u63b2\u793a\u3057\u307e\u3057\u305f\uff01\u5831\u916c {amount}\u5186 \u3092\u30a8\u30b9\u30af\u30ed\u30fc\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("worldstock.search-prompt", "<gold><bold>\u30c1\u30e3\u30c3\u30c8\u306b\u30c6\u30a3\u30c3\u30ab\u30fc\u30b7\u30f3\u30dc\u30eb\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u4f8b: AAPL, TSLA, 7203.T\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.invalid-symbol", "<red>\u30c6\u30a3\u30c3\u30ab\u30fc\u30b7\u30f3\u30dc\u30eb\u306e\u5f62\u5f0f\u304c\u6b63\u3057\u304f\u3042\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.searching", "<gray>{symbol} \u306e\u682a\u4fa1\u3092\u53d6\u5f97\u4e2d...</gray>");
        DEFAULT_MESSAGES.put("worldstock.not-found", "<red>{symbol} \u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3067\u3057\u305f\u3002\u30c6\u30a3\u30c3\u30ab\u30fc\u30b7\u30f3\u30dc\u30eb\u3092\u78ba\u8a8d\u3059\u308b\u304b\u3001\u3057\u3070\u3089\u304f\u7d4c\u3063\u3066\u304b\u3089\u518d\u5ea6\u304a\u8a66\u3057\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.funds-insufficient", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.not-owned", "<red>\u3053\u306e\u9298\u67c4\u3092\u4fdd\u6709\u3057\u3066\u3044\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.bought", "<green><bold>{symbol} \u3092 {qty}\u682a \u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08{amount}\u5186 / \u5358\u4fa1 {native} / \u624b\u6570\u6599 {fee}\u5186\uff09</bold></green>");
        DEFAULT_MESSAGES.put("worldstock.sold", "<green><bold>{symbol} \u3092 {qty}\u682a \u58f2\u5374\u3057\u307e\u3057\u305f\u3002\uff08{amount}\u5186 / \u5358\u4fa1 {native} / \u640d\u76ca: {pnl}\u5186 / \u624b\u6570\u6599 {fee}\u5186\uff09</bold></green>");
        DEFAULT_MESSAGES.put("worldstock.sell-not-enough-shares", "<red>\u58f2\u5374\u3057\u305f\u3044\u682a\u6570\u304c\u4fdd\u6709\u6570\u3092\u8d85\u3048\u3066\u3044\u307e\u3059\u3002\uff08\u4fdd\u6709: {owned}\u682a / \u6307\u5b9a: {requested}\u682a\uff09\u90e8\u5206\u7d04\u5b9a\u306f\u3057\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.bulk-qty-too-large", "<red>\u4e00\u5ea6\u306b\u53d6\u5f15\u3067\u304d\u308b\u682a\u6570\u306e\u4e0a\u9650\uff08{max}\u682a\uff09\u3092\u8d85\u3048\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.bulk-buy-prompt", "<gold><bold>{symbol} \u3092\u8cfc\u5165\u3059\u308b\u682a\u6570\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u4f8b: 25\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.bulk-sell-prompt", "<gold><bold>{symbol} \u3092\u58f2\u5374\u3059\u308b\u682a\u6570\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u4f8b: 25\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.cooldown", "<red>\u3053\u306e\u9298\u67c4\u306f\u53d6\u5f15\u5f8c {seconds}\u79d2\u7d4c\u904e\u3059\u308b\u307e\u3067\u518d\u5ea6\u53d6\u5f15\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.daily-count-limit", "<red>\u672c\u65e5\u306e\u4e16\u754c\u682a\u53d6\u5f15\u56de\u6570\u306e\u4e0a\u9650\uff08{count}\u56de\uff09\u306b\u9054\u3057\u307e\u3057\u305f\u3002\u307e\u305f\u660e\u65e5\u304a\u8a66\u3057\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.daily-amount-limit", "<red>\u672c\u65e5\u306e\u4e16\u754c\u682a\u53d6\u5f15\u91d1\u984d\u306e\u4e0a\u9650\uff08{amount}\u5186\uff09\u306b\u9054\u3059\u308b\u305f\u3081\u3001\u3053\u306e\u53d6\u5f15\u306f\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.daily-profit-limit", "<red>\u672c\u65e5\u306e\u4e16\u754c\u682a\u5b9f\u73fe\u5229\u76ca\u306e\u4e0a\u9650\uff08{amount}\u5186\uff09\u306b\u9054\u3057\u305f\u305f\u3081\u3001\u3053\u308c\u4ee5\u4e0a\u306e\u5229\u76ca\u78ba\u5b9a\u58f2\u308a\u306f\u3067\u304d\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("worldstock.alert-prompt", "<gold><bold>{symbol} \u306e\u5024\u52d5\u304d\u30a2\u30e9\u30fc\u30c8\u95be\u5024(%)\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u4f8b: 5 \u3067 \u00b15% \u5909\u52d5\u6642\u306b\u901a\u77e5\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.alert-set", "<green><bold>{symbol} \u306b\u5024\u52d5\u304d\u30a2\u30e9\u30fc\u30c8\u3092\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002\uff08\u73fe\u5728\u5024\u304b\u3089 \u00b1{percent}% \u5909\u52d5\u3067\u901a\u77e5\uff09</bold></green>");
        DEFAULT_MESSAGES.put("worldstock.alert-cancelled", "<gray>{symbol} \u306e\u5024\u52d5\u304d\u30a2\u30e9\u30fc\u30c8\u3092\u89e3\u9664\u3057\u307e\u3057\u305f\u3002</gray>");
        DEFAULT_MESSAGES.put("worldstock.alert-triggered", "<gold><bold>\ud83d\udd14 {symbol} \u304c\u57fa\u6e96\u5024\u304b\u3089{percent}%\u4ee5\u4e0a\u5909\u52d5\u3057\u307e\u3057\u305f\uff01\uff08\u73fe\u5728\u5024: {price}\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.dividend-paid", "<green>\u4e16\u754c\u682a\u306e\u914d\u5f53\u91d1\u3068\u3057\u3066 {amount}\u5186 \u3092\u53d7\u3051\u53d6\u308a\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("resourceshop.not-enough-items", "<red>{material}\u304c\u8db3\u308a\u307e\u305b\u3093\u3002\uff08\u6240\u6301: {have}\u500b / \u5fc5\u8981: {need}\u500b\uff09</red>");
        DEFAULT_MESSAGES.put("resourceshop.funds-insufficient", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("resourceshop.sold", "<green><bold>{material}\u3092{qty}\u500b\u58f2\u5374\u3057\u307e\u3057\u305f\u3002\uff08{amount}\u5186\uff09</bold></green>");
        DEFAULT_MESSAGES.put("resourceshop.bought", "<green><bold>{material}\u3092{qty}\u500b\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08{amount}\u5186\uff09</bold></green>");
        DEFAULT_MESSAGES.put("resourceshop.sell-qty-prompt", "<gold><bold>{material}\u3092\u58f2\u308b\u500b\u6570\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u4f8b: 40\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("resourceshop.buy-qty-prompt", "<gold><bold>{material}\u3092\u8cb7\u3046\u500b\u6570\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u4f8b: 40\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("lottery.funds-insufficient", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("lottery.ticket-bought", "<green><bold>\u5b9d\u304f\u3058\u30c1\u30b1\u30c3\u30c8\u3092{count}\u679a\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08{amount}\u5186\uff09</bold></green>");
        DEFAULT_MESSAGES.put("lottery.buy-qty-prompt", "<gold><bold>\u8cfc\u5165\u3059\u308b\u30c1\u30b1\u30c3\u30c8\u306e\u679a\u6570\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\uff08\u4f8b: 3\uff09</bold></gold>");
        DEFAULT_MESSAGES.put("merchant.funds-insufficient", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("merchant.bought", "<green><bold>{item} \u3092\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08{amount}\u5186\uff09</bold></green>");
        DEFAULT_MESSAGES.put("merchant.location-hint", "<gold><bold>\u5de1\u56de\u5546\u4eba\u306f\u73fe\u5728 {world} ({x}, {y}, {z}) \u4ed8\u8fd1\u306b\u3044\u307e\u3059\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("treasure.found", "<gold><bold>\u3010\u57cb\u8535\u91d1\u767a\u898b\u3011 \u57cb\u8535\u91d1\u30c1\u30a7\u30b9\u30c8\u3092\u767a\u898b\u3057\u3001{amount}\u5186 \u3092\u624b\u306b\u5165\u308c\u307e\u3057\u305f\uff01</bold></gold>");
        DEFAULT_MESSAGES.put("merchant.installment-purchased", "<green><bold>{item} \u3092\u5206\u5272\u6255\u3044\u3067\u8cfc\u5165\u3057\u307e\u3057\u305f\uff01\u521d\u56de {first}\u5186\u3092\u652f\u6255\u3044\u307e\u3057\u305f\u3002\uff08\u6b8b\u308a{count}\u56de\u4e2d {remaining}\u56de\u3001\u5404{each}\u5186\uff09</bold></green>");
        DEFAULT_MESSAGES.put("installment.paid", "<green>\u5206\u5272\u6255\u3044: {item} \u306e {amount}\u5186 \u3092\u5f15\u304d\u843d\u3068\u3057\u307e\u3057\u305f\u3002\uff08\u6b8b\u308a{remaining}\u56de\uff09</green>");
        DEFAULT_MESSAGES.put("installment.completed", "<green><bold>\u5206\u5272\u6255\u3044: {item} \u306e\u652f\u6255\u3044\u304c\u5b8c\u4e86\u3057\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("installment.missed", "<red>\u5206\u5272\u6255\u3044: {item} \u306e {amount}\u5186 \u304c\u5f15\u304d\u843d\u3068\u305b\u307e\u305b\u3093\u3067\u3057\u305f\u3002\u4fe1\u7528\u30b9\u30b3\u30a2\u304c\u6e1b\u5c11\u3057\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("auction.bid-too-low", "<red>\u5165\u672d\u984d\u304c\u4f4e\u3059\u304e\u307e\u3059\u3002\uff08\u6700\u4f4e {amount}\u5186\uff09</red>");
        DEFAULT_MESSAGES.put("auction.buyout-purchased", "<green><bold>\u5373\u6c7a\u4fa1\u683c\u3067\u8cfc\u5165\u3057\u307e\u3057\u305f\uff01</bold></green>");
        DEFAULT_MESSAGES.put("auction.bid-placed", "<green><bold>{item} \u306b {amount}\u5186 \u3067\u5165\u672d\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("personal.deposit-custom", "<green><bold>{amount}\u5186 \u3092\u9810\u91d1\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("personal.withdraw-insufficient", "<red>\u9810\u91d1\u6b8b\u9ad8\u304c\u8db3\u308a\u307e\u305b\u3093\u3002\uff08\u6b8b\u9ad8: {amount}\u5186\uff09</red>");
        DEFAULT_MESSAGES.put("personal.withdraw-custom", "<green><bold>{amount}\u5186 \u3092\u5f15\u304d\u51fa\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("bank.plan-amount-set", "<green>\u878d\u8cc7\u984d\u3092 {amount}\u5186 \u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("bank.plan-interest-set", "<green>\u5229\u606f\u3092 {amount}% \u306b\u8a2d\u5b9a\u3057\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("auction.draft-not-found", "<red>\u51fa\u54c1\u3059\u308b\u30a2\u30a4\u30c6\u30e0\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3067\u3057\u305f\u3002\u3082\u3046\u4e00\u5ea6\u304a\u8a66\u3057\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("auction.buyout-prompt", "<gold><bold>\u5373\u6c7a\u4fa1\u683c(Buy Now)\u3092\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002\u8a2d\u5b9a\u3057\u306a\u3044\u5834\u5408\u306f 0 \u3068\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("auction.listing-info-not-found", "<red>\u51fa\u54c1\u60c5\u5831\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3067\u3057\u305f\u3002\u3082\u3046\u4e00\u5ea6\u304a\u8a66\u3057\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("auction.buyout-too-low", "<red>\u5373\u6c7a\u4fa1\u683c\u306f\u958b\u59cb\u4fa1\u683c\uff08{amount}\u5186\uff09\u3088\u308a\u9ad8\u304f\u8a2d\u5b9a\u3057\u3066\u304f\u3060\u3055\u3044\u3002\u51fa\u54c1\u3092\u30ad\u30e3\u30f3\u30bb\u30eb\u3057\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("auction.listed", "<green><bold>{item} \u3092\u30aa\u30fc\u30af\u30b7\u30e7\u30f3\u306b\u51fa\u54c1\u3057\u307e\u3057\u305f\uff01\uff08\u958b\u59cb\u4fa1\u683c: {amount}\u5186{buyoutMsg} / \u671f\u9593: {minutes}\u5206\uff09</bold></green>");
        DEFAULT_MESSAGES.put("storage.funds-insufficient", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("storage.rented", "<green><bold>\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u3092\u5951\u7d04\u3057\u307e\u3057\u305f\uff01\uff08\u5bb6\u8cc3 {amount}\u5186 / {hours}\u6642\u9593\u3054\u3068\uff09</bold></green> <red>\u5bb6\u8cc3\u306e\u652f\u6255\u3044\u306b1\u56de\u3067\u3082\u5931\u6557\u3059\u308b\u3068\u3001\u5951\u7d04\u306f\u5931\u52b9\u3057\u5009\u5eab\u306e\u4e2d\u8eab\u306f\u5168\u3066\u6ca1\u53ce\u3055\u308c\u307e\u3059\u306e\u3067\u3054\u6ce8\u610f\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("storage.rent-paid", "<green>\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u306e\u5bb6\u8cc3 {amount}\u5186 \u3092\u652f\u6255\u3044\u307e\u3057\u305f\u3002</green>");
        DEFAULT_MESSAGES.put("storage.forfeited", "<red><bold>\u3010\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u3011\u5bb6\u8cc3\u306e\u652f\u6255\u3044\u306b\u5931\u6557\u3057\u305f\u305f\u3081\u5951\u7d04\u304c\u5931\u52b9\u3057\u3001\u5009\u5eab\u306e\u4e2d\u8eab\u306f\u5168\u3066\u6ca1\u53ce\u3055\u308c\u307e\u3057\u305f\u3002</bold></red>");
        DEFAULT_MESSAGES.put("storage.not-renting", "<red>\u30ec\u30f3\u30bf\u30eb\u5009\u5eab\u3092\u5951\u7d04\u3057\u3066\u3044\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("group.created", "<green><bold>\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u300c{name}\u300d\u3092\u4f5c\u6210\u3057\u307e\u3057\u305f\uff01\uff08ID: {id}\uff09</bold></green>");
        DEFAULT_MESSAGES.put("group.create-funds-insufficient", "<red>\u4f5c\u6210\u8cbb\u7528\u304c\u4e0d\u8db3\u3057\u3066\u3044\u307e\u3059\u3002\uff08\u5fc5\u8981\u984d: {amount}\u5186\uff09</red>");
        DEFAULT_MESSAGES.put("group.create-name-prompt", "<gold><bold>\u4f5c\u6210\u3059\u308b\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u306e\u540d\u524d\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("group.not-found", "<red>\u6307\u5b9a\u3057\u305f\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("group.not-owner", "<red>\u3053\u306e\u64cd\u4f5c\u306f\u30aa\u30fc\u30ca\u30fc\u306e\u307f\u5b9f\u884c\u3067\u304d\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("group.already-member", "<red>\u305d\u306e\u30d7\u30ec\u30a4\u30e4\u30fc\u306f\u65e2\u306b\u30e1\u30f3\u30d0\u30fc\u3067\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("group.full", "<red>\u30e1\u30f3\u30d0\u30fc\u6570\u304c\u4e0a\u9650\u306b\u9054\u3057\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("group.invited", "<green><bold>{player} \u3092\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u306b\u62db\u5f85\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("group.invite-received", "<gold><bold>\u3010\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u3011</bold> {player} \u304c\u3042\u306a\u305f\u3092\u300c{group}\u300d\u306b\u62db\u5f85\u3057\u307e\u3057\u305f\u3002</gold>");
        DEFAULT_MESSAGES.put("group.kicked", "<yellow>{player} \u3092\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u304b\u3089\u8ffd\u653e\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("group.kicked-notice", "<yellow>\u3010\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u3011</yellow> \u300c{group}\u300d\u306e\u30e1\u30f3\u30d0\u30fc\u69cb\u6210\u304c\u5909\u66f4\u3055\u308c\u307e\u3057\u305f\u3002");
        DEFAULT_MESSAGES.put("group.owner-cannot-leave", "<red>\u30aa\u30fc\u30ca\u30fc\u306f\u8131\u9000\u3067\u304d\u307e\u305b\u3093\u3002\u89e3\u6563\u3059\u308b\u5834\u5408\u306f /meco group disband <\u540d\u524d> \u3092\u4f7f\u7528\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("group.left", "<yellow>\u300c{group}\u300d\u304b\u3089\u8131\u9000\u3057\u307e\u3057\u305f\u3002</yellow>");
        DEFAULT_MESSAGES.put("group.disbanded", "<gold><bold>\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u304c\u89e3\u6563\u3055\u308c\u307e\u3057\u305f\u3002\u6b8b\u9ad8\u304b\u3089 {amount}\u5186 \u304c\u632f\u308a\u8fbc\u307e\u308c\u307e\u3057\u305f\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("group.disband-confirm", "<red><bold>\u672c\u5f53\u306b\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u3092\u89e3\u6563\u3057\u307e\u3059\u304b\uff1f</bold></red> <yellow>\u6b8b\u9ad8\u306f\u30e1\u30f3\u30d0\u30fc\u5168\u54e1\u306b\u5747\u7b49\u5272\u308a\u3055\u308c\u307e\u3059\u300230\u79d2\u4ee5\u5185\u306b\u3082\u3046\u4e00\u5ea6\u30af\u30ea\u30c3\u30af\u3067\u78ba\u5b9a\u3057\u307e\u3059\u3002</yellow>");
        DEFAULT_MESSAGES.put("group.deposit-prompt", "<gold><bold>\u5165\u91d1\u3059\u308b\u91d1\u984d\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("group.deposited", "<green><bold>\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u3078 {amount}\u5186 \u3092\u5165\u91d1\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("group.withdraw-prompt", "<gold><bold>\u5f15\u304d\u51fa\u3059\u91d1\u984d\u3092\u30c1\u30e3\u30c3\u30c8\u306b\u5165\u529b\u3057\u3066\u304f\u3060\u3055\u3044\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("group.withdraw-insufficient", "<red>\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u306e\u6b8b\u9ad8\u304c\u8db3\u308a\u307e\u305b\u3093\u3002\uff08\u6b8b\u9ad8: {amount}\u5186\uff09</red>");
        DEFAULT_MESSAGES.put("group.withdrawn", "<green><bold>\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u304b\u3089 {amount}\u5186 \u3092\u5f15\u304d\u51fa\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("help.group", "<yellow>/meco group <create|invite|kick|leave|disband></yellow> <gray>- \u8907\u6570\u4eba\u3067\u5171\u6709\u3059\u308b\u30b0\u30eb\u30fc\u30d7\u8caf\u91d1\u7bb1\u3092\u7ba1\u7406\u3059\u308b</gray>");
        DEFAULT_MESSAGES.put("fund.created", "<green><bold>\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c{name}\u300d\u3092\u4f5c\u6210\u3057\u307e\u3057\u305f\uff01\uff08ID: {id}\uff09</bold></green>");
        DEFAULT_MESSAGES.put("fund.create-funds-insufficient", "<red>\u4f5c\u6210\u8cbb\u7528\u304c\u4e0d\u8db3\u3057\u3066\u3044\u307e\u3059\u3002\uff08\u5fc5\u8981\u984d: {amount}\u5186\uff09</red>");
        DEFAULT_MESSAGES.put("fund.not-found", "<red>\u6307\u5b9a\u3057\u305f\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u304c\u898b\u3064\u304b\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("fund.not-manager", "<red>\u3053\u306e\u64cd\u4f5c\u306f\u30d5\u30a1\u30f3\u30c9\u306e\u30de\u30cd\u30fc\u30b8\u30e3\u30fc\u306e\u307f\u5b9f\u884c\u3067\u304d\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("fund.already-contributor", "<red>\u305d\u306e\u30d7\u30ec\u30a4\u30e4\u30fc\u306f\u65e2\u306b\u51fa\u8cc7\u8005\u3067\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("fund.full", "<red>\u51fa\u8cc7\u8005\u6570\u304c\u4e0a\u9650\u306b\u9054\u3057\u3066\u3044\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("fund.invited", "<green><bold>{player} \u3092\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u306b\u62db\u5f85\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("fund.invite-received", "<gold><bold>\u3010\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u3011</bold> {player} \u304c\u3042\u306a\u305f\u3092\u300c{fund}\u300d\u306b\u62db\u5f85\u3057\u307e\u3057\u305f\u3002/meco fund contribute {fund} <\u91d1\u984d> \u3067\u51fa\u8cc7\u3067\u304d\u307e\u3059\u3002</gold>");
        DEFAULT_MESSAGES.put("fund.not-invited", "<red>\u305d\u306e\u30d5\u30a1\u30f3\u30c9\u306e\u51fa\u8cc7\u8005(\u307e\u305f\u306f\u30de\u30cd\u30fc\u30b8\u30e3\u30fc)\u3067\u306f\u3042\u308a\u307e\u305b\u3093\u3002\u5148\u306b\u62db\u5f85\u3057\u3066\u3082\u3089\u3046\u5fc5\u8981\u304c\u3042\u308a\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("fund.contributed", "<green><bold>\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u300c{fund}\u300d\u3078 {amount}\u5186 \u51fa\u8cc7\u3057\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("fund.contribute-funds-insufficient", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002\uff08\u6240\u6301\u91d1: {amount}\u5186\uff09</red>");
        DEFAULT_MESSAGES.put("fund.redeemed", "<green><bold>\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u304b\u3089\u89e3\u7d04\u3057\u3001\u6301\u5206 {amount}\u5186 \u3092\u53d7\u3051\u53d6\u308a\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("fund.redeem-confirm", "<red><bold>\u672c\u5f53\u306b\u6301\u5206\u3092\u5168\u984d\u89e3\u7d04\u3057\u307e\u3059\u304b\uff1f</bold></red> <yellow>\u3082\u3046\u4e00\u5ea6\u30af\u30ea\u30c3\u30af\u3059\u308b\u3068\u78ba\u5b9a\u3057\u307e\u3059(30\u79d2\u4ee5\u5185)\u3002</yellow>");
        DEFAULT_MESSAGES.put("fund.redeem-insufficient-cash", "<red>\u30d5\u30a1\u30f3\u30c9\u306e\u73fe\u91d1\u6b8b\u9ad8\u304c\u4e0d\u8db3\u3057\u3066\u3044\u308b\u305f\u3081\u89e3\u7d04\u3067\u304d\u307e\u305b\u3093\u3002\u30de\u30cd\u30fc\u30b8\u30e3\u30fc\u306b\u4fdd\u6709\u9298\u67c4\u306e\u58f2\u5374\u3092\u4f9d\u983c\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("fund.disband-has-holdings", "<red>\u4fdd\u6709\u9298\u67c4\u304c\u6b8b\u3063\u3066\u3044\u308b\u305f\u3081\u89e3\u6563\u3067\u304d\u307e\u305b\u3093\u3002\u5148\u306b\u904b\u7528\u753b\u9762\u3067\u3059\u3079\u3066\u58f2\u5374\u3057\u3066\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("fund.disbanded", "<gold><bold>\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u304c\u89e3\u6563\u3055\u308c\u307e\u3057\u305f\u3002\u6301\u5206\u306b\u5fdc\u3058\u3066 {amount}\u5186 \u304c\u632f\u308a\u8fbc\u307e\u308c\u307e\u3057\u305f\u3002</bold></gold>");
        DEFAULT_MESSAGES.put("fund.trade-funds-insufficient", "<red>\u30d5\u30a1\u30f3\u30c9\u306e\u73fe\u91d1\u6b8b\u9ad8\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("help.fund", "<yellow>/meco fund <create|invite|contribute|redeem|disband></yellow> <gray>- \u8907\u6570\u4eba\u3067\u51fa\u8cc7\u3057\u5408\u3046\u5171\u540c\u6295\u8cc7\u30d5\u30a1\u30f3\u30c9\u3092\u7ba1\u7406\u3059\u308b</gray>");
        DEFAULT_MESSAGES.put("vip.not-eligible", "<red>VIP\u9650\u5b9a\u3067\u3059\u3002\u4fe1\u7528\u30b9\u30b3\u30a2\u3092\u4e0a\u3052\u3066\u30b7\u30eb\u30d0\u30fc\u4f1a\u54e1\u4ee5\u4e0a\u306b\u306a\u308b\u3068\u5229\u7528\u3067\u304d\u307e\u3059\u3002</red>");
        DEFAULT_MESSAGES.put("vip.shop-bought", "<green><bold>VIP\u9650\u5b9a\u30b7\u30e7\u30c3\u30d7\u3067 {item} \u3092\u8cfc\u5165\u3057\u307e\u3057\u305f\u3002\uff08{amount}\u5186\uff09</bold></green>");
        DEFAULT_MESSAGES.put("vip.shop-funds-insufficient", "<red>\u624b\u6301\u3061\u8cc7\u91d1\u304c\u8db3\u308a\u307e\u305b\u3093\u3002</red>");
        DEFAULT_MESSAGES.put("vip.stipend-claimed", "<green><bold>\u65e5\u6b21VIP\u624b\u5f53\uff08{tier}\uff09\u3068\u3057\u3066 {amount}\u5186 \u3092\u53d7\u3051\u53d6\u308a\u307e\u3057\u305f\u3002</bold></green>");
        DEFAULT_MESSAGES.put("vip.stipend-cooldown", "<red>\u65e5\u6b21VIP\u624b\u5f53\u306f\u53d7\u53d6\u6e08\u307f\u3067\u3059\u3002\u6b21\u56de\u53d7\u53d6\u307e\u3067\u3042\u3068 {time}\u3002</red>");
        DEFAULT_MESSAGES.put("vip.stipend-treasury-empty", "<red>\u56fd\u5eab\u6b8b\u9ad8\u304c\u4e0d\u8db3\u3057\u3066\u3044\u308b\u305f\u3081\u3001\u73fe\u5728\u306f\u65e5\u6b21VIP\u624b\u5f53\u3092\u652f\u7d66\u3067\u304d\u307e\u305b\u3093\u3002\u3057\u3070\u3089\u304f\u7d4c\u3063\u3066\u304b\u3089\u304a\u8a66\u3057\u304f\u3060\u3055\u3044\u3002</red>");
        DEFAULT_MESSAGES.put("vip.stipend-disabled", "<red>\u65e5\u6b21VIP\u624b\u5f53\u306f\u73fe\u5728\u7121\u52b9\u5316\u3055\u308c\u3066\u3044\u307e\u3059\u3002</red>");
    }

    private static final class PluginDataStore {
        private final Connection conn;

        PluginDataStore(File dbFile) throws SQLException {
            try {
                Class.forName("org.sqlite.JDBC");
            }
            catch (ClassNotFoundException classNotFoundException) {
                // empty catch block
            }
            this.conn = DriverManager.getConnection("jdbc:sqlite:" + dbFile.getAbsolutePath());
            try (Statement st = this.conn.createStatement();){
                st.execute("CREATE TABLE IF NOT EXISTS plugin_data (section TEXT NOT NULL, path TEXT NOT NULL, value TEXT, PRIMARY KEY (section, path))");
                st.execute("CREATE INDEX IF NOT EXISTS idx_plugin_data_section ON plugin_data(section)");
            }
        }

        void close() {
            try {
                this.conn.close();
            }
            catch (SQLException sQLException) {
                // empty catch block
            }
        }

        void beginTransaction() {
            try {
                this.conn.setAutoCommit(false);
            }
            catch (SQLException e) {
                throw new RuntimeException("SQLite\u30c8\u30e9\u30f3\u30b6\u30af\u30b7\u30e7\u30f3\u958b\u59cb\u306b\u5931\u6557\u3057\u307e\u3057\u305f", e);
            }
        }

        void endTransaction() {
            try {
                this.conn.commit();
            }
            catch (SQLException e) {
                try {
                    this.conn.rollback();
                }
                catch (SQLException sQLException) {
                    // empty catch block
                }
                throw new RuntimeException("SQLite\u3078\u306e\u30b3\u30df\u30c3\u30c8\u306b\u5931\u6557\u3057\u307e\u3057\u305f", e);
            }
            finally {
                try {
                    this.conn.setAutoCommit(true);
                }
                catch (SQLException sQLException) {}
            }
        }

        void setString(String section, String path, String value) {
            if (value == null) {
                this.remove(section, path);
                return;
            }
            try (PreparedStatement ps = this.conn.prepareStatement("INSERT OR REPLACE INTO plugin_data(section, path, value) VALUES (?, ?, ?)");){
                ps.setString(1, section);
                ps.setString(2, path);
                ps.setString(3, value);
                ps.executeUpdate();
            }
            catch (SQLException e) {
                throw new RuntimeException("SQLite\u66f8\u8fbc\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + path, e);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        String getString(String section, String path, String def) {
            try (PreparedStatement ps = this.conn.prepareStatement("SELECT value FROM plugin_data WHERE section=? AND path=?");){
                ps.setString(1, section);
                ps.setString(2, path);
                try (ResultSet rs = ps.executeQuery();){
                    if (!rs.next()) return def;
                    String v = rs.getString(1);
                    String string = v != null ? v : def;
                    return string;
                }
            }
            catch (SQLException e) {
                throw new RuntimeException("SQLite\u8aad\u8fbc\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + path, e);
            }
        }

        void setDouble(String section, String path, double value) {
            this.setString(section, path, Double.toString(value));
        }

        double getDouble(String section, String path, double def) {
            String v = this.getString(section, path, null);
            if (v == null) {
                return def;
            }
            try {
                return Double.parseDouble(v);
            }
            catch (NumberFormatException e) {
                return def;
            }
        }

        void setLong(String section, String path, long value) {
            this.setString(section, path, Long.toString(value));
        }

        long getLong(String section, String path, long def) {
            String v = this.getString(section, path, null);
            if (v == null) {
                return def;
            }
            try {
                return Long.parseLong(v);
            }
            catch (NumberFormatException e) {
                return def;
            }
        }

        void setInt(String section, String path, int value) {
            this.setString(section, path, Integer.toString(value));
        }

        int getInt(String section, String path, int def) {
            String v = this.getString(section, path, null);
            if (v == null) {
                return def;
            }
            try {
                return Integer.parseInt(v);
            }
            catch (NumberFormatException e) {
                return def;
            }
        }

        void setBoolean(String section, String path, boolean value) {
            this.setString(section, path, Boolean.toString(value));
        }

        boolean getBoolean(String section, String path, boolean def) {
            String v = this.getString(section, path, null);
            if (v == null) {
                return def;
            }
            return Boolean.parseBoolean(v);
        }

        void setStringList(String section, String path, List<String> values) {
            if (values == null || values.isEmpty()) {
                this.remove(section, path);
                return;
            }
            this.setString(section, path, String.join((CharSequence)"\n", values));
        }

        List<String> getStringList(String section, String path) {
            String v = this.getString(section, path, null);
            if (v == null || v.isEmpty()) {
                return new ArrayList<String>();
            }
            return new ArrayList<String>(Arrays.asList(v.split("\n", -1)));
        }

        void setItemStack(String section, String path, ItemStack item) {
            if (item == null) {
                this.remove(section, path);
                return;
            }
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try (BukkitObjectOutputStream oos = new BukkitObjectOutputStream((OutputStream)baos);){
                    oos.writeObject((Object)item);
                }
                this.setString(section, path, Base64.getEncoder().encodeToString(baos.toByteArray()));
            }
            catch (IOException e) {
                throw new RuntimeException("ItemStack\u306e\u30b7\u30ea\u30a2\u30e9\u30a4\u30ba\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + path, e);
            }
        }

        ItemStack getItemStack(String section, String path) {
            ItemStack itemStack;
            String v = this.getString(section, path, null);
            if (v == null) {
                return null;
            }
            byte[] bytes = Base64.getDecoder().decode(v);
            BukkitObjectInputStream ois = new BukkitObjectInputStream((InputStream)new ByteArrayInputStream(bytes));
            try {
                Object obj = ois.readObject();
                itemStack = obj instanceof ItemStack ? (ItemStack)obj : null;
            }
            catch (Throwable throwable) {
                try {
                    try {
                        ois.close();
                    }
                    catch (Throwable throwable2) {
                        throwable.addSuppressed(throwable2);
                    }
                    throw throwable;
                }
                catch (Exception e) {
                    throw new RuntimeException("ItemStack\u306e\u30c7\u30b7\u30ea\u30a2\u30e9\u30a4\u30ba\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + path, e);
                }
            }
            ois.close();
            return itemStack;
        }

        void setItemStackList(String section, String path, List<ItemStack> items) {
            if (items == null || items.isEmpty()) {
                this.remove(section, path);
                return;
            }
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try (BukkitObjectOutputStream oos = new BukkitObjectOutputStream((OutputStream)baos);){
                    oos.writeObject(new ArrayList<ItemStack>(items));
                }
                this.setString(section, path, Base64.getEncoder().encodeToString(baos.toByteArray()));
            }
            catch (IOException e) {
                throw new RuntimeException("ItemStack\u30ea\u30b9\u30c8\u306e\u30b7\u30ea\u30a2\u30e9\u30a4\u30ba\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + path, e);
            }
        }

        /*
         * Enabled aggressive block sorting
         * Enabled unnecessary exception pruning
         * Enabled aggressive exception aggregation
         */
        List<ItemStack> getItemStackList(String section, String path) {
            String v = this.getString(section, path, null);
            if (v == null) {
                return new ArrayList<ItemStack>();
            }
            try {
                byte[] bytes = Base64.getDecoder().decode(v);
                try (BukkitObjectInputStream ois = new BukkitObjectInputStream((InputStream)new ByteArrayInputStream(bytes));){
                    Object obj = ois.readObject();
                    if (obj instanceof List) {
                        ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>((List)obj);
                        return arrayList;
                    }
                    ArrayList<ItemStack> arrayList = new ArrayList<ItemStack>();
                    return arrayList;
                }
            }
            catch (Exception e) {
                throw new RuntimeException("ItemStack\u30ea\u30b9\u30c8\u306e\u30c7\u30b7\u30ea\u30a2\u30e9\u30a4\u30ba\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + path, e);
            }
        }

        Set<String> getKeys(String section) {
            return this.getKeys(section, null);
        }

        Set<String> getKeys(String section, String pathPrefix) {
            LinkedHashSet<String> result = new LinkedHashSet<String>();
            boolean root = pathPrefix == null || pathPrefix.isEmpty();
            try (PreparedStatement ps = this.conn.prepareStatement("SELECT path FROM plugin_data WHERE section=?");){
                ps.setString(1, section);
                try (ResultSet rs = ps.executeQuery();){
                    while (rs.next()) {
                        String path = rs.getString(1);
                        if (root) {
                            int dot = path.indexOf(46);
                            result.add(dot >= 0 ? path.substring(0, dot) : path);
                            continue;
                        }
                        String needle = pathPrefix + ".";
                        if (!path.startsWith(needle)) continue;
                        String rest = path.substring(needle.length());
                        int dot = rest.indexOf(46);
                        result.add(dot >= 0 ? rest.substring(0, dot) : rest);
                    }
                }
            }
            catch (SQLException e) {
                throw new RuntimeException("SQLite getKeys\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + pathPrefix, e);
            }
            return result;
        }

        void remove(String section, String path) {
            try (PreparedStatement ps = this.conn.prepareStatement("DELETE FROM plugin_data WHERE section=? AND path=?");){
                ps.setString(1, section);
                ps.setString(2, path);
                ps.executeUpdate();
            }
            catch (SQLException e) {
                throw new RuntimeException("SQLite\u524a\u9664\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + path, e);
            }
        }

        void removeSection(String section) {
            try (PreparedStatement ps = this.conn.prepareStatement("DELETE FROM plugin_data WHERE section=?");){
                ps.setString(1, section);
                ps.executeUpdate();
            }
            catch (SQLException e) {
                throw new RuntimeException("SQLite\u30bb\u30af\u30b7\u30e7\u30f3\u524a\u9664\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section, e);
            }
        }

        void removeByPathPrefix(String section, String pathPrefix) {
            try (PreparedStatement ps = this.conn.prepareStatement("DELETE FROM plugin_data WHERE section=? AND (path=? OR path LIKE ?)");){
                ps.setString(1, section);
                ps.setString(2, pathPrefix);
                ps.setString(3, pathPrefix + ".%");
                ps.executeUpdate();
            }
            catch (SQLException e) {
                throw new RuntimeException("SQLite\u90e8\u5206\u524a\u9664\u306b\u5931\u6557\u3057\u307e\u3057\u305f: " + section + "/" + pathPrefix, e);
            }
        }
    }

    public static class BankPlaceholderExpansion
    extends PlaceholderExpansion {
        private final MinecraftBank plugin;

        public BankPlaceholderExpansion(MinecraftBank plugin) {
            this.plugin = plugin;
        }

        public String getIdentifier() {
            return "bank";
        }

        public String getAuthor() {
            return "kot0328";
        }

        public String getVersion() {
            return "1.0";
        }

        public boolean persist() {
            return true;
        }

        public String onRequest(OfflinePlayer player, String params) {
            if (player == null) {
                return "";
            }
            UUID u = player.getUniqueId();
            switch (params) {
                case "pocket": {
                    if (player.isOnline() && player.getPlayer() != null) {
                        return String.valueOf((long)econ.getBalance((OfflinePlayer)player.getPlayer()));
                    }
                    return String.valueOf((long)econ.getBalance(player));
                }
                case "balance": {
                    return String.valueOf((long)this.plugin.personalBank.getOrDefault(u, 0.0).doubleValue());
                }
                case "total": {
                    double pocket = player.isOnline() && player.getPlayer() != null ? econ.getBalance((OfflinePlayer)player.getPlayer()) : econ.getBalance(player);
                    double bank = this.plugin.personalBank.getOrDefault(u, 0.0);
                    return String.valueOf((long)(pocket + bank));
                }
                case "credit_score": {
                    return String.valueOf(this.plugin.getScore(u));
                }
                case "gov_debt": {
                    return String.valueOf((long)this.plugin.govDebt.getOrDefault(u, 0.0).doubleValue());
                }
            }
            return null;
        }
    }

    private static class InstallmentPlan {
        UUID id;
        UUID owner;
        String description;
        double installmentAmount;
        int installmentsRemaining;
        long nextDueTime;

        private InstallmentPlan() {
        }
    }

    private static class WorldStockAlert {
        UUID id;
        String symbol;
        double baselinePrice;
        double thresholdPercent;

        private WorldStockAlert() {
        }
    }

    private static class MerchantDeal {
        UUID id;
        Material material;
        double normalPrice;
        double discountPercent;
        int stockTotal;
        int stockRemaining;

        private MerchantDeal() {
        }
    }

    private static class VipDeal {
        UUID id;
        Material material;
        double normalPrice;
        double discountPercent;
        int stockTotal;
        int stockRemaining;

        private VipDeal() {
        }
    }

    private static class Quest {
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
        HashSet<UUID> teamMembers = new HashSet();
        HashSet<UUID> teamArrived = new HashSet();

        private Quest() {
        }
    }

    private static enum QuestState {
        AVAILABLE,
        IN_PROGRESS,
        COOLDOWN;

    }

    private static class GroupAccount {
        UUID id;
        String name;
        UUID owner;
        HashSet<UUID> members = new HashSet();
        double balance = 0.0;

        private GroupAccount() {
        }
    }

    private static class InvestmentFund {
        UUID id;
        String name;
        UUID manager;
        HashMap<UUID, Double> contributions = new HashMap();
        double cashBalance = 0.0;

        private InvestmentFund() {
        }
    }

    private static class WorldStockQuote {
        final String symbol;
        final String exchangeName;
        final double price;
        final String currency;
        final long fetchedAt;

        WorldStockQuote(String symbol, String exchangeName, double price, String currency, long fetchedAt) {
            this.symbol = symbol;
            this.exchangeName = exchangeName;
            this.price = price;
            this.currency = currency;
            this.fetchedAt = fetchedAt;
        }
    }

    private static class TradeSession {
        UUID id;
        UUID playerA;
        UUID playerB;
        Inventory inventory;
        boolean confirmedA = false;
        boolean confirmedB = false;
        double moneyOfferedA = 0.0;
        double moneyOfferedB = 0.0;
        boolean finished = false;

        private TradeSession() {
        }
    }

    @FunctionalInterface
    private static interface DealFactory<T> {
        public T create(UUID var1, Material var2, double var3, double var5, int var7);
    }
}

