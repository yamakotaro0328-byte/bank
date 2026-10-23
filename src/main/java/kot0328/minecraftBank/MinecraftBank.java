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
import java.util.function.Consumer;
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
    private String activeEconomyEvent = "通常";
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
    private String cfgCurrencyUnit = "円";
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
    private final Component tMain = this.mm("<bold><gold>【総合金融センタートップ】</gold></bold>");
    private final Component tPersonal = this.mm("<bold><aqua>【個人口座・預金窓口】</aqua></bold>");
    private final Component tMarket = this.mm("<bold><green>【融資市場（ローンマーケット）】</green></bold>");
    private final Component tRepay = this.mm("<bold><yellow>【総合・借金返済窓口】</yellow></bold>");
    private final Component tEstablish = this.mm("<bold><dark_purple>【銀行設立オフィス】</dark_purple></bold>");
    private final Component tBanker = this.mm("<bold><red>【頭取コントロールパネル】</red></bold>");
    private final Component tPlan = this.mm("<bold><dark_red>【融資プラン設計室】</dark_red></bold>");
    private final Component tGovLoan = this.mm("<bold><aqua>【国営公庫・サーバーローン】</aqua></bold>");
    private final Component tFixedDepo = this.mm("<bold><gold>【高金利・定期預金窓口】</gold></bold>");
    private final Component tCredit = this.mm("<bold><green>【個人信用情報センター】</green></bold>");
    private final Component tQuestBoard = this.mm("<bold><dark_aqua>【依頼ボード・探索依頼】</dark_aqua></bold>");
    private final Component tWorldStock = this.mm("<bold><blue>【世界株式市場】</blue></bold>");
    private final Component tWorldStockDetail = this.mm("<bold><blue>【世界株式市場・銘柄詳細】</blue></bold>");
    private final Component tWorldStockLeaderboard = this.mm("<bold><blue>【世界株式市場・保有株ランキング】</blue></bold>");
    private final Component tResourceShop = this.mm("<bold><green>【資源相場ショップ】</green></bold>");
    private final Component tResourceShopList = this.mm("<bold><green>【資源相場ショップ・品目一覧】</green></bold>");
    private final Component tResourceShopDetail = this.mm("<bold><green>【資源相場ショップ・売買】</green></bold>");
    private final Component tTravelingMerchant = this.mm("<bold><gold>【巡回商人】</gold></bold>");
    private final Component tLeaderboard = this.mm("<bold><yellow>【資産ランキング】</yellow></bold>");
    private final Component tCollateral = this.mm("<bold><dark_purple>【質屋・担保付き融資】</dark_purple></bold>");
    private final Component tInsurance = this.mm("<bold><blue>【生命保険窓口】</blue></bold>");
    private final Component tAchievement = this.mm("<bold><gold>【実績・称号コレクション】</gold></bold>");
    private final Component tAuction = this.mm("<bold><green>【オークションハウス】</green></bold>");
    private final Component tHub = this.mm("<bold><white>【経済総合メニュー】</white></bold>");
    private final Component tBankHub = this.mm("<bold><aqua>【銀行窓口】</aqua></bold>");
    private final Component tMarketHub = this.mm("<bold><green>【マーケット】</green></bold>");
    private final Component tTodoHub = this.mm("<bold><yellow>【やること】</yellow></bold>");
    private final Component tMyPage = this.mm("<bold><light_purple>【マイページ】</light_purple></bold>");
    private final Component tInstallmentList = this.mm("<bold><light_purple>【分割払いの状況】</light_purple></bold>");
    private final Component tTutorial = this.mm("<bold><gold>【経済初心者ガイド】</gold></bold>");
    private final Component tAuctionSelect = this.mm("<bold><green>【オークション出品アイテム選択】</green></bold>");
    private final Component tAuctionCancelConfirm = this.mm("<bold><red>【オークション出品取消の確認】</red></bold>");
    private final Component tAuctionRanking = this.mm("<bold><green>【オークション・落札売上ランキング】</green></bold>");
    private final Component tCollateralSelect = this.mm("<bold><dark_purple>【担保アイテム選択】</dark_purple></bold>");
    private final Component tAdminMain = this.mm("<bold><red>【管理者パネル】</red></bold>");
    private final Component tAdminPlayerList = this.mm("<bold><red>【管理者：プレイヤー一覧】</red></bold>");
    private final Component tAdminPlayerDetail = this.mm("<bold><red>【管理者：プレイヤー詳細】</red></bold>");
    private final Component tAdminServer = this.mm("<bold><red>【管理者：サーバー管理】</red></bold>");
    private final Component tConfigEditor = this.mm("<bold><red>【管理者：config設定】</red></bold>");
    private final Component tLottery = this.mm("<bold><gold>【定期抽選・宝くじ】</gold></bold>");
    private final Component tStorageRent = this.mm("<bold><gold>【レンタル倉庫・契約窓口】</gold></bold>");
    private final Component tStorageLocker = this.mm("<bold><gray>【レンタル倉庫】</gray></bold>");
    private final Component tTradeSelect = this.mm("<bold><green>【アイテム交換・相手選択】</green></bold>");
    private final Component tTrade = this.mm("<bold><gold>【安全アイテム交換】</gold></bold>");
    private final Component tGroupList = this.mm("<bold><gold>【グループ貯金箱】</gold></bold>");
    private final Component tGroupAccount = this.mm("<bold><gold>【グループ貯金箱・詳細】</gold></bold>");
    private final Component tGroupMembers = this.mm("<bold><gold>【グループ貯金箱・メンバー管理】</gold></bold>");
    private final Component tFundList = this.mm("<bold><dark_green>【共同投資ファンド】</dark_green></bold>");
    private final Component tFundInfo = this.mm("<bold><dark_green>【共同投資ファンド・情報】</dark_green></bold>");
    private final Component tFundStock = this.mm("<bold><dark_green>【共同投資ファンド・運用】</dark_green></bold>");
    private final Component tFundStockDetail = this.mm("<bold><dark_green>【共同投資ファンド・銘柄詳細】</dark_green></bold>");
    private final Component tVipLounge = this.mm("<bold><light_purple>【💎 VIPラウンジ】</light_purple></bold>");
    private PluginDataStore db;
    private FileConfiguration messages;
    private File messagesFile;
    private static final Set<String> MANAGED_DATA_PREFIXES;
    private volatile String lastDashboardJson = "{}";
    private static final int DASHBOARD_HISTORY_LIMIT = 60;
    private final ArrayDeque<Long> dashboardHistoryTimestamps = new ArrayDeque();
    private final ArrayDeque<Long> dashboardHistoryTreasury = new ArrayDeque();
    private static final String MY_DASHBOARD_HTML = "<!doctype html>\n<html lang=\"ja\">\n<head>\n<meta charset=\"UTF-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n<title>個人ページ | MinecraftBank</title>\n<style>\n  :root { --bg:#0b0f19; --panel:#141b2c; --panel-border:#232c42; --text:#e7ecf7; --text-dim:#8a93ab; --accent:#5b8cff; --gold:#f4c542; --emerald:#34d399; --red:#f87171; }\n  * { box-sizing: border-box; }\n  body { margin:0; background:var(--bg); color:var(--text); font-family: 'Segoe UI', system-ui, sans-serif; }\n  .wrap { max-width: 720px; margin: 0 auto; padding: 20px 16px 40px; }\n  h1 { font-size: 20px; margin: 0 0 4px; }\n  .sub { color: var(--text-dim); font-size: 13px; margin-bottom: 18px; }\n  .grid { display:grid; grid-template-columns: repeat(auto-fit, minmax(140px,1fr)); gap:10px; margin-bottom:18px; }\n  .card { background:var(--panel); border:1px solid var(--panel-border); border-radius:12px; padding:14px; }\n  .card .label { color:var(--text-dim); font-size:12px; margin-bottom:6px; }\n  .card .value { font-size:20px; font-weight:700; }\n  .gold .value { color: var(--gold); }\n  .emerald .value { color: var(--emerald); }\n  .panel { background:var(--panel); border:1px solid var(--panel-border); border-radius:12px; padding:16px; margin-bottom:16px; }\n  .panel h2 { font-size:14px; margin:0 0 10px; color:var(--text-dim); font-weight:600; }\n  table { width:100%; border-collapse:collapse; font-size:13px; }\n  th, td { text-align:left; padding:6px 4px; border-bottom:1px solid var(--panel-border); }\n  th { color:var(--text-dim); font-weight:600; }\n  .log-line { font-size:12px; padding:5px 0; border-bottom:1px solid var(--panel-border); color: var(--text); }\n  .empty { color:var(--text-dim); font-size:13px; padding:10px 0; }\n  .err { color: var(--red); }\n  .btn { background:var(--accent); color:#fff; border:none; border-radius:8px; padding:8px 14px; font-size:13px; cursor:pointer; font-weight:600; }\n  .btn:hover { opacity:.9; }\n  .btn:disabled { opacity:.4; cursor:default; }\n  .btn.gold { background: var(--gold); color:#1a1300; }\n  .input { background:#0f1524; border:1px solid var(--panel-border); color:var(--text); border-radius:7px; padding:7px 10px; font-size:13px; width:100px; }\n  .row { display:flex; align-items:center; gap:8px; flex-wrap:wrap; margin-top:8px; }\n  .status-msg { font-size:12px; margin-top:8px; min-height:16px; }\n  .status-msg.ok { color: var(--emerald); }\n  .status-msg.err { color: var(--red); }\n  .auction-card { border-bottom:1px solid var(--panel-border); padding:10px 0; font-size:13px; }\n  .auction-card:last-child { border-bottom:none; }\n  .auction-card .title { font-weight:600; margin-bottom:4px; }\n  .auction-card .meta { color:var(--text-dim); font-size:12px; margin-bottom:6px; }\n</style>\n</head>\n<body>\n  <div class=\"wrap\">\n    <h1>👤 個人ページ</h1>\n    <div class=\"sub\" id=\"playerName\">読込中...</div>\n    <div class=\"sub\" id=\"onlineStatus\"></div>\n\n    <div class=\"panel\" id=\"passwordPanel\" hidden>\n      <h2>オフライン操作用パスワード</h2>\n      <div class=\"empty\">ログインしていない時にこのページから操作するには、パスワードを入力してください(ゲーム内の /meco webpage password で設定できます)。ログイン中は不要です。</div>\n      <div class=\"row\"><input class=\"input\" id=\"offlinePassword\" type=\"password\" placeholder=\"パスワード\" style=\"width:160px;\"></div>\n    </div>\n\n    <div class=\"grid\">\n      <div class=\"card gold\"><div class=\"label\">所持金</div><div class=\"value\" id=\"pocket\">-</div></div>\n      <div class=\"card gold\"><div class=\"label\">銀行残高</div><div class=\"value\" id=\"bank\">-</div></div>\n      <div class=\"card emerald\"><div class=\"label\">信用スコア</div><div class=\"value\" id=\"credit\">-</div></div>\n      <div class=\"card\"><div class=\"label\">実績解除数</div><div class=\"value\" id=\"achievements\">-</div></div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"row\" style=\"justify-content:space-between; margin-top:0;\">\n        <h2 style=\"margin:0;\">受取箱</h2>\n        <button class=\"btn\" id=\"collectBtn\">📦 受取箱を確認する</button>\n      </div>\n      <div class=\"status-msg\" id=\"collectMsg\"></div>\n      <div class=\"empty\">オークション落札品などが届いています。ログイン中にインベントリの空きが無くて受け取れなかった分もここで再取得できます。</div>\n    </div>\n\n    <div class=\"panel\">\n      <h2>保有中の世界株 / 購入</h2>\n      <div id=\"stocksArea\"><div class=\"empty\">読込中...</div></div>\n      <div class=\"row\">\n        <input class=\"input\" id=\"stockSymbol\" type=\"text\" placeholder=\"銘柄(例: AAPL)\" style=\"width:140px;\">\n        <input class=\"input\" id=\"stockQty\" type=\"number\" min=\"1\" value=\"1\" placeholder=\"株数\">\n        <button class=\"btn\" id=\"stockBuyBtn\">購入する</button>\n      </div>\n      <div class=\"status-msg\" id=\"stockMsg\"></div>\n    </div>\n\n    <div class=\"panel\">\n      <h2>オークション</h2>\n      <div id=\"auctionArea\"><div class=\"empty\">読込中...</div></div>\n      <div class=\"status-msg\" id=\"auctionMsg\"></div>\n    </div>\n\n    <div class=\"panel\">\n      <h2>レンタル倉庫</h2>\n      <div id=\"storageArea\"><div class=\"empty\">読込中...</div></div>\n      <div class=\"status-msg\" id=\"storageMsg\"></div>\n    </div>\n\n    <div class=\"panel\">\n      <h2>直近の取引履歴</h2>\n      <div id=\"logsArea\"><div class=\"empty\">読込中...</div></div>\n    </div>\n  </div>\n\n  <script>\n    function fmtYen(n) { return Math.round(n).toLocaleString('ja-JP') + '円'; }\n    const params = new URLSearchParams(location.search);\n    const token = params.get('token') || '';\n\n    async function load() {\n      try {\n        const res = await fetch('/me.json?token=' + encodeURIComponent(token), { cache: 'no-store' });\n        const data = await res.json();\n        if (data.error) {\n          document.getElementById('playerName').innerHTML = '<span class=\"err\">リンクが無効です。ゲーム内で /meco webpage を実行し直してください。</span>';\n          return;\n        }\n        document.getElementById('playerName').textContent = data.name + ' さんの個人ページ';\n        document.getElementById('onlineStatus').textContent = data.online ? '🟢 ログイン中' : '⚪ オフライン';\n        // ★修正: パスワード未設定でも入力欄自体は必ず表示する(管理者が発行したパスワードで\n        // 操作できる可能性があるため、「未設定」を理由に入力欄ごと消してはいけない)。\n        document.getElementById('passwordPanel').hidden = data.online;\n        document.getElementById('pocket').textContent = fmtYen(data.pocket || 0);\n        document.getElementById('bank').textContent = fmtYen(data.bank || 0);\n        document.getElementById('credit').textContent = data.credit_score;\n        document.getElementById('achievements').textContent = data.achievement_count;\n\n        const stocks = data.world_stocks || [];\n        const stocksArea = document.getElementById('stocksArea');\n        stocksArea.innerHTML = stocks.length === 0 ? '<div class=\"empty\">保有中の株はありません。</div>' :\n          '<table><thead><tr><th>銘柄</th><th>株数</th><th>平均取得単価</th><th>評価額</th></tr></thead><tbody>' +\n          stocks.map(s => '<tr><td>' + s.symbol + '</td><td>' + s.qty + '</td><td>' + fmtYen(s.avg_cost) + '</td><td>' + fmtYen(s.value) + '</td></tr>').join('') +\n          '</tbody></table>';\n\n        const logs = data.logs || [];\n        const logsArea = document.getElementById('logsArea');\n        logsArea.innerHTML = logs.length === 0 ? '<div class=\"empty\">取引履歴はありません。</div>' :\n          logs.map(l => '<div class=\"log-line\">' + l.replace(/</g, '&lt;') + '</div>').join('');\n\n        renderAuctions(data.auctions || []);\n        renderStorage(data.storage || {});\n      } catch (e) {\n        document.getElementById('playerName').innerHTML = '<span class=\"err\">読込に失敗しました。</span>';\n      }\n    }\n\n    function fmtRemain(ms) {\n      if (ms <= 0) return 'まもなく終了';\n      const s = Math.floor(ms / 1000);\n      return Math.floor(s / 60) + '分' + (s % 60) + '秒';\n    }\n\n    function renderAuctions(auctions) {\n      const area = document.getElementById('auctionArea');\n      if (auctions.length === 0) { area.innerHTML = '<div class=\"empty\">出品中のオークションはありません。</div>'; return; }\n      area.innerHTML = auctions.map(a => {\n        const disabled = a.is_own ? 'disabled' : '';\n        const buyoutBtn = a.buyout !== null ? '<button class=\"btn gold\" ' + disabled + ' onclick=\"bidAuction(\\'' + a.id + '\\', ' + a.buyout + ')\">即決 (' + fmtYen(a.buyout) + ')</button>' : '';\n        return '<div class=\"auction-card\">' +\n          '<div class=\"title\">' + a.item + (a.is_own ? '(自分の出品)' : '') + '</div>' +\n          '<div class=\"meta\">出品者: ' + a.seller + ' / 現在価格: ' + fmtYen(a.bid) + ' / 入札者: ' + (a.has_bidder ? 'あり' : 'なし') + ' / 残り: ' + fmtRemain(a.remain_ms) + '</div>' +\n          '<div class=\"row\" style=\"margin-top:0;\">' +\n            '<input class=\"input\" id=\"bidamt-' + a.id + '\" type=\"number\" placeholder=\"入札額\" ' + disabled + '>' +\n            '<button class=\"btn\" ' + disabled + ' onclick=\"bidAuctionFromInput(\\'' + a.id + '\\')\">入札する</button>' +\n            buyoutBtn +\n          '</div></div>';\n      }).join('');\n    }\n\n    function renderStorage(storage) {\n      const area = document.getElementById('storageArea');\n      if (!storage.rented) {\n        area.innerHTML = '<div class=\"empty\">未契約です。契約すると倉庫が使えるようになります(家賃 ' + fmtYen(storage.rent_amount) + ' / 定期支払い)。</div>' +\n          '<div class=\"row\"><button class=\"btn\" onclick=\"payStorageRent()\">契約する</button></div>';\n      } else {\n        area.innerHTML = '<div class=\"empty\">契約中。次回家賃支払い期限: ' + new Date(storage.due_at).toLocaleString('ja-JP') + '</div>' +\n          '<div class=\"row\"><button class=\"btn\" onclick=\"payStorageRent()\">家賃を今払って延長する (' + fmtYen(storage.rent_amount) + ')</button></div>';\n      }\n    }\n\n    async function postAction(path, params) {\n      const password = (document.getElementById('offlinePassword') || {}).value || '';\n      const body = new URLSearchParams(Object.assign({ token, password }, params));\n      const res = await fetch(path, { method: 'POST', headers: { 'Content-Type': 'application/x-www-form-urlencoded' }, body });\n      if (!res.ok) {\n        const err = await res.json().catch(() => ({}));\n        return { ok: false, message: err.message || ('HTTPエラー ' + res.status) };\n      }\n      return await res.json();\n    }\n\n    function showStatus(id, ok, text) {\n      const el = document.getElementById(id);\n      el.className = 'status-msg ' + (ok ? 'ok' : 'err');\n      el.textContent = text;\n    }\n\n    document.getElementById('collectBtn').addEventListener('click', async () => {\n      try {\n        const r = await postAction('/me/collect', {});\n        showStatus('collectMsg', r.ok, r.ok ? '受取箱を確認しました。' : (r.message || '失敗しました。'));\n        load();\n      } catch (e) { showStatus('collectMsg', false, 'ゲーム内にログインしている時だけ操作できます。'); }\n    });\n\n    document.getElementById('stockBuyBtn').addEventListener('click', async () => {\n      const symbol = document.getElementById('stockSymbol').value.trim();\n      const qty = document.getElementById('stockQty').value;\n      if (!symbol || !qty) { showStatus('stockMsg', false, '銘柄と株数を入力してください。'); return; }\n      try {\n        const r = await postAction('/me/worldstock/buy', { symbol, qty });\n        showStatus('stockMsg', r.ok, r.ok ? '購入処理を送信しました(結果はゲーム内チャットで確認してください)。' : (r.message || '失敗しました。'));\n        load();\n      } catch (e) { showStatus('stockMsg', false, 'ゲーム内にログインしている時だけ操作できます。'); }\n    });\n\n    async function payStorageRent() {\n      try {\n        const r = await postAction('/me/storage/pay-rent', {});\n        showStatus('storageMsg', r.ok, r.ok ? '支払いを送信しました。' : (r.message || '残高不足、またはログインしていません。'));\n        load();\n      } catch (e) { showStatus('storageMsg', false, 'ゲーム内にログインしている時だけ操作できます。'); }\n    }\n\n    async function bidAuction(id, amount) {\n      try {\n        const r = await postAction('/me/auction/bid', { auction_id: id, amount });\n        showStatus('auctionMsg', r.ok, r.ok ? '入札を送信しました(結果はゲーム内チャットで確認してください)。' : (r.message || '失敗しました。'));\n        load();\n      } catch (e) { showStatus('auctionMsg', false, 'ゲーム内にログインしている時だけ操作できます。'); }\n    }\n\n    function bidAuctionFromInput(id) {\n      const val = document.getElementById('bidamt-' + id).value;\n      if (!val) { showStatus('auctionMsg', false, '入札額を入力してください。'); return; }\n      bidAuction(id, val);\n    }\n\n    load();\n  </script>\n</body>\n</html>\n";
    private static final String DASHBOARD_HTML = "<!doctype html>\n<html lang=\"ja\">\n<head>\n<meta charset=\"UTF-8\">\n<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n<title>経済ダッシュボード | MinecraftBank</title>\n<style>\n  :root {\n    --bg: #0b0f19;\n    --panel: #141b2c;\n    --panel-alt: #101625;\n    --panel-border: #232c42;\n    --text: #eaeefb;\n    --text-dim: #8b93a7;\n    --gold: #f4c542;\n    --emerald: #4ade9b;\n    --red: #ff6b6b;\n    --accent: #5b8cff;\n    --purple: #b18cff;\n    --cyan: #43d9e0;\n\n    /* ★追加: データ可視化用の検証済みパレット(dataviz skillのcolor-formula.mdに準拠)\n       カテゴリカル8色は固定順で識別(系列/カテゴリ)専用、ステータス4色は状態専用、\n       シーケンシャル/ダイバージングは大小・極性の表現専用。色の役割を混同しないこと。 */\n    --cat-1: #3987e5; /* blue */\n    --cat-2: #d95926; /* orange */\n    --cat-3: #199e70; /* aqua */\n    --cat-4: #c98500; /* yellow */\n    --cat-5: #d55181; /* magenta */\n    --cat-6: #008300; /* green */\n    --cat-7: #9085e9; /* violet */\n    --cat-8: #e66767; /* red */\n    --status-good: #0ca30c;\n    --status-warning: #fab219;\n    --status-serious: #ec835a;\n    --status-critical: #d03b3b;\n    --seq-100: #cde2fb;\n    --seq-400: #3987e5;\n    --seq-700: #0d366b;\n    --div-neg: #3987e5;  /* 基準値より安い(マイナス側の極) */\n    --div-pos: #e66767;  /* 基準値より高い(プラス側の極) */\n    --div-mid: #383835;  /* 中立(基準値と同水準) */\n  }\n  * { box-sizing: border-box; }\n  ::-webkit-scrollbar { width: 10px; height: 10px; }\n  ::-webkit-scrollbar-thumb { background: #2a3350; border-radius: 6px; }\n  ::-webkit-scrollbar-track { background: transparent; }\n  body {\n    margin: 0;\n    font-family: 'Segoe UI', 'Hiragino Kaku Gothic ProN', 'Yu Gothic', sans-serif;\n    background:\n      radial-gradient(circle at 15% -10%, #24304f66 0%, transparent 45%),\n      radial-gradient(circle at 100% 0%, #1a2c2266 0%, transparent 40%),\n      var(--bg);\n    color: var(--text);\n    min-height: 100vh;\n    padding: 32px 20px 60px;\n  }\n  .wrap { max-width: 1180px; margin: 0 auto; }\n  @keyframes fadeInUp {\n    from { opacity: 0; transform: translateY(10px); }\n    to { opacity: 1; transform: translateY(0); }\n  }\n  header {\n    display: flex;\n    align-items: flex-start;\n    justify-content: space-between;\n    flex-wrap: wrap;\n    gap: 12px;\n    margin-bottom: 24px;\n    animation: fadeInUp .4s ease both;\n  }\n  header h1 {\n    font-size: 25px;\n    margin: 0 0 6px;\n    letter-spacing: 0.3px;\n  }\n  header h1 span { color: var(--gold); }\n  .sub-row { display: flex; align-items: center; gap: 10px; flex-wrap: wrap; }\n  .event-badge {\n    display: inline-flex;\n    align-items: center;\n    gap: 6px;\n    background: #1c2440;\n    border: 1px solid var(--panel-border);\n    color: var(--cyan);\n    font-size: 12px;\n    font-weight: 600;\n    padding: 5px 12px;\n    border-radius: 999px;\n  }\n  .event-badge::before { content: '●'; font-size: 8px; color: var(--cyan); }\n  #updated {\n    font-size: 12px;\n    color: var(--text-dim);\n    text-align: right;\n  }\n  #updated b { color: var(--emerald); }\n  #countdown { color: var(--text-dim); font-variant-numeric: tabular-nums; }\n  .grid {\n    display: grid;\n    grid-template-columns: repeat(auto-fit, minmax(170px, 1fr));\n    gap: 14px;\n    margin-bottom: 18px;\n  }\n  .stat-card {\n    background: linear-gradient(160deg, var(--panel), var(--panel-alt));\n    border: 1px solid var(--panel-border);\n    border-radius: 14px;\n    padding: 16px 18px;\n    animation: fadeInUp .45s ease both;\n    transition: transform .15s ease, border-color .15s ease;\n  }\n  .stat-card:hover { transform: translateY(-2px); border-color: #34406a; }\n  .stat-card .label {\n    font-size: 11.5px;\n    color: var(--text-dim);\n    margin-bottom: 6px;\n    white-space: nowrap;\n  }\n  .stat-card .value {\n    font-size: 23px;\n    font-weight: 700;\n    font-variant-numeric: tabular-nums;\n  }\n  .stat-card.gold .value { color: var(--gold); }\n  .stat-card.emerald .value { color: var(--emerald); }\n  .stat-card.accent .value { color: var(--accent); }\n  .stat-card.red .value { color: var(--red); }\n  .stat-card.purple .value { color: var(--purple); }\n  .stat-card.cyan .value { color: var(--cyan); }\n  .stat-card.status-good .value { color: var(--status-good); }\n  .stat-card.status-warning .value { color: var(--status-warning); }\n  .stat-card.status-critical .value { color: var(--status-critical); }\n\n  /* ★追加: 「マーケット概況」「やること概況」向けの新規コンポーネント */\n  .subpanel {\n    background: var(--panel-alt);\n    border: 1px solid var(--panel-border);\n    border-radius: 12px;\n    padding: 14px 16px;\n  }\n  .subpanel h3 {\n    font-size: 12.5px;\n    margin: 0 0 12px;\n    color: var(--text-dim);\n    text-transform: uppercase;\n    letter-spacing: .5px;\n  }\n  .rank-list { display: flex; flex-direction: column; gap: 9px; }\n  .rank-list-item { display: flex; align-items: center; gap: 8px; font-size: 13px; }\n  .cat-dot { width: 10px; height: 10px; border-radius: 50%; flex: none; }\n  .rank-list-name { font-weight: 600; font-variant-numeric: tabular-nums; }\n  .rank-list-meta { margin-left: auto; color: var(--text-dim); font-size: 11.5px; text-align: right; white-space: nowrap; }\n  .badge-grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(148px, 1fr)); gap: 8px; }\n  .badge {\n    display: flex;\n    align-items: center;\n    gap: 7px;\n    background: var(--panel);\n    border: 1px solid var(--panel-border);\n    border-radius: 8px;\n    padding: 7px 10px;\n    font-size: 12px;\n  }\n  .badge .dot { width: 9px; height: 9px; border-radius: 50%; flex: none; }\n  .badge-name { flex: 1; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; color: var(--text); }\n  .badge-trend { font-variant-numeric: tabular-nums; color: var(--text-dim); font-weight: 600; white-space: nowrap; }\n  .mini-legend { display: flex; gap: 14px; flex-wrap: wrap; font-size: 11.5px; color: var(--text-dim); margin-bottom: 10px; }\n  .mini-legend .dot { display: inline-block; width: 8px; height: 8px; border-radius: 50%; margin-right: 5px; }\n  .merchant-refresh { font-size: 13px; color: var(--text-dim); margin-bottom: 10px; }\n  .merchant-refresh b { color: var(--text); font-variant-numeric: tabular-nums; }\n  .deal-grid { display: flex; flex-direction: column; gap: 10px; }\n  .deal-card { background: var(--panel); border: 1px solid var(--panel-border); border-radius: 10px; padding: 10px 12px; }\n  .deal-card-head { display: flex; align-items: center; justify-content: space-between; gap: 8px; margin-bottom: 6px; }\n  .deal-name { font-weight: 600; font-size: 13px; }\n  .deal-discount { color: var(--emerald); font-weight: 700; font-size: 13px; font-variant-numeric: tabular-nums; white-space: nowrap; }\n  .meter-track { height: 7px; border-radius: 4px; background: var(--seq-100); overflow: hidden; }\n  .meter-fill { height: 100%; background: var(--seq-400); border-radius: 4px; transition: width .4s ease; }\n  .deal-stock { font-size: 11.5px; color: var(--text-dim); margin-top: 6px; }\n  .hero-tile { text-align: center; padding: 4px 0 16px; }\n  .hero-tile .hero-value {\n    font-size: 40px;\n    font-weight: 700;\n    color: var(--gold);\n    line-height: 1.15;\n    /* ★重要: 大きな単独の数値は比例数字を使う(等幅のtabular-numsは大きい文字だと間延びして見える) */\n  }\n  .hero-tile .hero-label {\n    font-size: 12px;\n    color: var(--text-dim);\n    margin-top: 6px;\n    text-transform: uppercase;\n    letter-spacing: .5px;\n  }\n  .mini-stats { display: flex; flex-direction: column; gap: 7px; font-size: 13px; color: var(--text-dim); }\n  .mini-stats b { color: var(--text); font-variant-numeric: tabular-nums; font-weight: 600; }\n  .panel {\n    background: var(--panel);\n    border: 1px solid var(--panel-border);\n    border-radius: 14px;\n    padding: 20px 22px;\n    margin-bottom: 16px;\n    animation: fadeInUp .5s ease both;\n  }\n  .panel-head {\n    display: flex;\n    align-items: center;\n    justify-content: space-between;\n    flex-wrap: wrap;\n    gap: 10px;\n    margin-bottom: 14px;\n  }\n  .panel h2 {\n    font-size: 14px;\n    margin: 0;\n    color: var(--text-dim);\n    text-transform: uppercase;\n    letter-spacing: 1px;\n  }\n  .legend { display: flex; gap: 14px; flex-wrap: wrap; font-size: 12px; color: var(--text-dim); }\n  .legend .dot { display: inline-block; width: 8px; height: 8px; border-radius: 50%; margin-right: 5px; }\n  .chart-box { position: relative; width: 100%; height: 220px; }\n  .chart-box canvas { width: 100%; height: 100%; display: block; }\n  .alert-box { border-radius: 10px; padding: 12px 16px; font-size: 13px; line-height: 1.7; }\n  .alert-box.ok { background: rgba(16,185,129,.12); border: 1px solid rgba(16,185,129,.4); color: var(--emerald); }\n  .alert-box.warn { background: rgba(239,68,68,.12); border: 1px solid rgba(239,68,68,.4); color: #f87171; }\n  .alert-box ul { margin: 6px 0 0 18px; padding: 0; }\n  .alert-box .alert-time { color: var(--text-dim); font-size: 11px; margin-top: 6px; }\n  .bars-box { position: relative; width: 100%; height: 160px; }\n  .panels-2col {\n    display: grid;\n    grid-template-columns: 1fr 1fr;\n    gap: 16px;\n  }\n  @media (max-width: 820px) {\n    .panels-2col { grid-template-columns: 1fr; }\n  }\n  .toolbar {\n    display: flex;\n    align-items: center;\n    gap: 10px;\n    flex-wrap: wrap;\n  }\n  .search-input {\n    background: var(--panel-alt);\n    border: 1px solid var(--panel-border);\n    color: var(--text);\n    font-size: 13px;\n    padding: 7px 12px;\n    border-radius: 8px;\n    outline: none;\n    min-width: 160px;\n  }\n  .search-input:focus { border-color: var(--accent); }\n  table { width: 100%; border-collapse: collapse; font-size: 13px; }\n  thead th {\n    position: sticky;\n    top: 0;\n    background: var(--panel);\n    text-align: left;\n    color: var(--text-dim);\n    font-weight: 600;\n    font-size: 11.5px;\n    text-transform: uppercase;\n    letter-spacing: .5px;\n    padding: 8px 10px;\n    border-bottom: 1px solid var(--panel-border);\n    cursor: pointer;\n    user-select: none;\n    white-space: nowrap;\n  }\n  thead th:hover { color: var(--text); }\n  thead th .arrow { opacity: .5; margin-left: 3px; font-size: 10px; }\n  tbody td {\n    padding: 8px 10px;\n    border-bottom: 1px solid #1c2438;\n    vertical-align: middle;\n  }\n  tbody tr { transition: background .12s ease; }\n  tbody tr:hover { background: #1a2136; }\n  tbody tr:nth-child(even) { background: #121a2b55; }\n  tbody tr:nth-child(even):hover { background: #1a2136; }\n  .rank-cell { width: 34px; font-weight: 700; color: var(--text-dim); }\n  .medal { font-size: 14px; }\n  .name-cell { font-weight: 600; max-width: 140px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; }\n  .bar-cell { min-width: 90px; }\n  .bar-track { height: 8px; background: #1c2338; border-radius: 5px; overflow: hidden; }\n  .bar-fill { height: 100%; background: linear-gradient(90deg, var(--accent), var(--emerald)); border-radius: 5px; transition: width .4s ease; }\n  .amount-cell { text-align: right; font-variant-numeric: tabular-nums; color: var(--gold); font-weight: 600; white-space: nowrap; }\n  .score-cell { text-align: right; font-variant-numeric: tabular-nums; color: var(--cyan); white-space: nowrap; }\n  .pagination {\n    display: flex;\n    align-items: center;\n    justify-content: flex-end;\n    gap: 10px;\n    margin-top: 12px;\n    font-size: 12px;\n    color: var(--text-dim);\n  }\n  .pagination button {\n    background: var(--panel-alt);\n    border: 1px solid var(--panel-border);\n    color: var(--text);\n    padding: 5px 12px;\n    border-radius: 7px;\n    cursor: pointer;\n    font-size: 12px;\n  }\n  .pagination button:disabled { opacity: .35; cursor: default; }\n  .pagination button:not(:disabled):hover { border-color: var(--accent); }\n  .empty { color: var(--text-dim); font-size: 13px; padding: 14px 0; text-align: center; }\n  footer {\n    text-align: center;\n    color: var(--text-dim);\n    font-size: 12px;\n    margin-top: 8px;\n  }\n</style>\n</head>\n<body>\n  <div class=\"wrap\">\n    <header>\n      <div>\n        <h1>💰 <span>MinecraftBank</span> 経済ダッシュボード</h1>\n        <div class=\"sub-row\">\n          <div class=\"event-badge\" id=\"eventBadge\">通常</div>\n        </div>\n      </div>\n      <div id=\"updated\">最終更新: <b id=\"updatedAt\">-</b><br><span id=\"countdown\"></span></div>\n    </header>\n\n    <div class=\"grid\" id=\"statGrid\">\n      <div class=\"stat-card gold\">\n        <div class=\"label\">国庫残高</div>\n        <div class=\"value\" id=\"treasury\">-</div>\n      </div>\n      <div class=\"stat-card red\">\n        <div class=\"label\">政府債務合計</div>\n        <div class=\"value\" id=\"totalGovDebt\">-</div>\n      </div>\n      <div class=\"stat-card gold\">\n        <div class=\"label\">今週の取引量</div>\n        <div class=\"value\" id=\"weeklyTrade\">-</div>\n      </div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>異常検知</h2>\n      </div>\n      <div class=\"alert-box ok\" id=\"selfcheckBox\">確認中...</div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>国庫残高の推移</h2>\n      </div>\n      <div class=\"chart-box\"><canvas id=\"treasuryChart\"></canvas></div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>資産ランキング</h2>\n        <div class=\"toolbar\">\n          <input class=\"search-input\" id=\"lbSearch\" type=\"text\" placeholder=\"プレイヤー名で検索...\">\n        </div>\n      </div>\n      <div style=\"overflow-x:auto\">\n        <table>\n          <thead>\n            <tr>\n              <th data-key=\"rank\">#<span class=\"arrow\"></span></th>\n              <th data-key=\"name\">プレイヤー<span class=\"arrow\"></span></th>\n              <th data-key=\"credit_score\">信用スコア<span class=\"arrow\"></span></th>\n              <th data-key=\"total_assets\">総資産<span class=\"arrow\"></span></th>\n            </tr>\n          </thead>\n          <tbody id=\"lbBody\"></tbody>\n        </table>\n      </div>\n      <div id=\"lbEmpty\" class=\"empty\" style=\"display:none\">データがありません</div>\n      <div class=\"pagination\">\n        <span id=\"lbPageInfo\"></span>\n        <button id=\"lbPrev\">← 前へ</button>\n        <button id=\"lbNext\">次へ →</button>\n      </div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>🛒 マーケット概況</h2>\n      </div>\n\n      <div class=\"grid\" id=\"marketStatGrid\"></div>\n\n      <div class=\"panels-2col\" style=\"margin-top:16px\">\n        <div class=\"subpanel\">\n          <h3>📈 世界株式市場 - 保有株数トップ5</h3>\n          <div class=\"rank-list\" id=\"worldStockTopList\"></div>\n        </div>\n        <div class=\"subpanel\">\n          <h3>🧳 巡回商人</h3>\n          <div id=\"merchantStatus\"></div>\n        </div>\n      </div>\n\n      <div class=\"subpanel\" style=\"margin-top:16px\">\n        <h3>⛏ 資源相場ショップ - 基準価格との乖離</h3>\n        <div class=\"mini-legend\">\n          <span><span class=\"dot\" style=\"background:var(--div-neg)\"></span>基準より安い</span>\n          <span><span class=\"dot\" style=\"background:var(--div-mid)\"></span>横ばい</span>\n          <span><span class=\"dot\" style=\"background:var(--div-pos)\"></span>基準より高い</span>\n        </div>\n        <div class=\"badge-grid\" id=\"resourceBadgeGrid\"></div>\n      </div>\n    </div>\n\n    <div class=\"panel\">\n      <div class=\"panel-head\">\n        <h2>🎯 やること概況</h2>\n      </div>\n\n      <div class=\"grid\" id=\"questStatGrid\"></div>\n\n      <div class=\"panels-2col\" style=\"margin-top:16px\">\n        <div class=\"subpanel\">\n          <h3>🎟 宝くじ</h3>\n          <div class=\"hero-tile\">\n            <div class=\"hero-value\" id=\"lotteryPool\">-</div>\n            <div class=\"hero-label\">次回抽選の賞金プール</div>\n          </div>\n          <div class=\"mini-stats\">\n            <div>次回抽選まで: <b id=\"lotteryCountdown\">-</b></div>\n            <div>販売チケット数: <b id=\"lotteryTickets\">-</b></div>\n            <div>前回の当せん: <b id=\"lotteryLastWinner\">-</b></div>\n          </div>\n        </div>\n        <div class=\"subpanel\">\n          <h3>📦 倉庫レンタル / 💳 分割払い / ⛏ 埋蔵金</h3>\n          <div class=\"grid\" id=\"miscStatGrid\"></div>\n        </div>\n      </div>\n    </div>\n\n    <footer>10秒ごとに自動更新されます</footer>\n  </div>\n\n  <script>\n    const PAGE_SIZE = 10;\n    let lastData = null;\n    const state = {\n      lb: { sortKey: 'rank', sortDir: 1, page: 1, search: '' }\n    };\n\n    function fmtYen(n) { return Math.round(n).toLocaleString('ja-JP') + '円'; }\n    function fmtNum(n) { return Math.round(n).toLocaleString('ja-JP'); }\n    function medal(rank) {\n      if (rank === 1) return '🥇';\n      if (rank === 2) return '🥈';\n      if (rank === 3) return '🥉';\n      return rank;\n    }\n\n    // ★追加: カテゴリカルパレット(固定8色・固定順。系列/カテゴリの識別専用。ステータス色とは混同しないこと)\n    const CAT_COLORS = ['var(--cat-1)', 'var(--cat-2)', 'var(--cat-3)', 'var(--cat-4)', 'var(--cat-5)', 'var(--cat-6)', 'var(--cat-7)', 'var(--cat-8)'];\n\n    // ★追加: ダイバージング配色(青⇔グレー⇔赤)。基準値からの乖離%を±30%でクランプして線形補間する。\n    function divergingColor(pct) {\n      const clamped = Math.max(-30, Math.min(30, pct || 0));\n      const t = clamped / 30; // -1..1\n      const neg = [0x39, 0x87, 0xe5];  // --cat-1 / --div-neg (blue)\n      const mid = [0x38, 0x38, 0x35];  // --div-mid (neutral gray)\n      const pos = [0xe6, 0x67, 0x67];  // --cat-8 / --div-pos (red)\n      const from = t < 0 ? neg : pos;\n      const amt = Math.abs(t);\n      const rgb = mid.map((m, i) => Math.round(m + (from[i] - m) * amt));\n      return `rgb(${rgb[0]},${rgb[1]},${rgb[2]})`;\n    }\n\n    // ★追加: 資源相場ショップ/巡回商人の素材名 日本語表示(未登録の素材は整形して表示)\n    const MATERIAL_JA = {\n      COAL: '石炭', IRON_INGOT: '鉄インゴット', GOLD_INGOT: '金インゴット', COPPER_INGOT: '銅インゴット',\n      REDSTONE: 'レッドストーン', LAPIS_LAZULI: 'ラピスラズリ', DIAMOND: 'ダイヤモンド', EMERALD: 'エメラルド',\n      NETHERITE_SCRAP: 'ネザライトの欠片', WHEAT: '小麦', CARROT: 'ニンジン', POTATO: 'ジャガイモ',\n      BEETROOT: 'ビートルート', MELON_SLICE: 'スイカ', PUMPKIN: 'カボチャ', SUGAR_CANE: 'サトウキビ',\n      NETHER_WART: 'ネザーウォート', COCOA_BEANS: 'カカオ豆', ROTTEN_FLESH: '腐った肉', BONE: '骨',\n      STRING: '糸', GUNPOWDER: '火薬', SPIDER_EYE: 'クモの目', SLIME_BALL: 'スライムボール',\n      ENDER_PEARL: 'エンダーパール', BLAZE_ROD: 'ブレイズロッド', GHAST_TEAR: 'ガストの涙',\n      OAK_LOG: 'オークの原木', SPRUCE_LOG: 'トウヒの原木', BIRCH_LOG: 'シラカバの原木', JUNGLE_LOG: 'ジャングルの原木',\n      ACACIA_LOG: 'アカシアの原木', DARK_OAK_LOG: 'ダークオークの原木', MANGROVE_LOG: 'マングローブの原木', CHERRY_LOG: 'サクラの原木',\n      ELYTRA: 'エリトラ', TOTEM_OF_UNDYING: '不死のトーテム', NETHERITE_INGOT: 'ネザライトインゴット',\n      NETHERITE_BLOCK: 'ネザライトブロック', ENCHANTED_GOLDEN_APPLE: 'エンチャントされた金のリンゴ',\n      SADDLE: 'サドル', NAME_TAG: '名札', SHULKER_BOX: 'シュルカーボックス', TRIDENT: 'トライデント',\n      NETHER_STAR: 'ネザースター', DRAGON_EGG: 'ドラゴンの卵', BEACON: 'ビーコン', DIAMOND_BLOCK: 'ダイヤモンドブロック',\n      EMERALD_BLOCK: 'エメラルドブロック', MUSIC_DISC_PIGSTEP: 'レコード(Pigstep)', MUSIC_DISC_OTHERSIDE: 'レコード(Otherside)',\n      HEART_OF_THE_SEA: '海の心', CONDUIT: 'コンジット', END_CRYSTAL: 'エンドクリスタル', GOLDEN_CARROT: '金のニンジン'\n    };\n    function materialLabel(name) {\n      if (MATERIAL_JA[name]) return MATERIAL_JA[name];\n      return String(name).toLowerCase().split('_').map(w => w.charAt(0).toUpperCase() + w.slice(1)).join(' ');\n    }\n\n    // ★追加: 残りミリ秒を「◯日◯時間」「◯時間◯分」「◯分」形式に整形\n    function fmtDuration(ms) {\n      if (!ms || ms <= 0) return 'まもなく';\n      const totalMin = Math.floor(ms / 60000);\n      const d = Math.floor(totalMin / 1440);\n      const h = Math.floor((totalMin % 1440) / 60);\n      const m = totalMin % 60;\n      if (d > 0) return d + '日' + h + '時間';\n      if (h > 0) return h + '時間' + m + '分';\n      return m + '分';\n    }\n\n    function sortRows(rows, key, dir) {\n      return [...rows].sort((a, b) => {\n        const av = a[key], bv = b[key];\n        if (typeof av === 'string') return av.localeCompare(bv, 'ja') * dir;\n        return ((av ?? 0) - (bv ?? 0)) * dir;\n      });\n    }\n\n    function bindSortableHeaders(tableSelector, sortState, renderFn) {\n      document.querySelectorAll(tableSelector + ' thead th[data-key]').forEach(th => {\n        th.addEventListener('click', () => {\n          const key = th.getAttribute('data-key');\n          if (sortState.sortKey === key) {\n            sortState.sortDir *= -1;\n          } else {\n            sortState.sortKey = key;\n            sortState.sortDir = 1;\n          }\n          sortState.page = 1;\n          renderFn();\n        });\n      });\n    }\n\n    function updateHeaderArrows(tableSelector, sortState) {\n      document.querySelectorAll(tableSelector + ' thead th[data-key]').forEach(th => {\n        const arrow = th.querySelector('.arrow');\n        if (th.getAttribute('data-key') === sortState.sortKey) {\n          arrow.textContent = sortState.sortDir === 1 ? '▲' : '▼';\n        } else {\n          arrow.textContent = '';\n        }\n      });\n    }\n\n    function drawLineChart(canvas, series, labels) {\n      if (!canvas) return;\n      const dpr = window.devicePixelRatio || 1;\n      const rect = canvas.parentElement.getBoundingClientRect();\n      const w = Math.max(rect.width, 100), h = Math.max(rect.height, 100);\n      canvas.width = w * dpr; canvas.height = h * dpr;\n      const ctx = canvas.getContext('2d');\n      ctx.scale(dpr, dpr);\n      ctx.clearRect(0, 0, w, h);\n\n      const padL = 54, padR = 12, padT = 12, padB = 24;\n      const plotW = w - padL - padR, plotH = h - padT - padB;\n\n      const allVals = series.flatMap(s => s.data);\n      if (allVals.length === 0) {\n        ctx.fillStyle = '#8b93a7';\n        ctx.font = '12px sans-serif';\n        ctx.fillText('データが蓄積されるとここに表示されます', padL, h / 2);\n        return;\n      }\n      let min = Math.min(...allVals), max = Math.max(...allVals);\n      if (min === max) { min -= 1; max += 1; }\n      const pad = (max - min) * 0.1;\n      min -= pad; max += pad;\n\n      ctx.strokeStyle = '#1f2740';\n      ctx.lineWidth = 1;\n      ctx.font = '10.5px sans-serif';\n      ctx.fillStyle = '#8b93a7';\n      const gridLines = 4;\n      for (let i = 0; i <= gridLines; i++) {\n        const y = padT + (plotH / gridLines) * i;\n        ctx.beginPath();\n        ctx.moveTo(padL, y);\n        ctx.lineTo(w - padR, y);\n        ctx.stroke();\n        const val = max - ((max - min) / gridLines) * i;\n        ctx.fillText(Math.round(val).toLocaleString('ja-JP'), 2, y + 3);\n      }\n\n      const n = Math.max(...series.map(s => s.data.length), 2);\n      series.forEach(s => {\n        if (s.data.length < 2) return;\n        ctx.beginPath();\n        s.data.forEach((v, i) => {\n          const x = padL + (i / (n - 1)) * plotW;\n          const y = padT + plotH - ((v - min) / (max - min)) * plotH;\n          if (i === 0) ctx.moveTo(x, y); else ctx.lineTo(x, y);\n        });\n        ctx.strokeStyle = s.color;\n        ctx.lineWidth = 2;\n        ctx.lineJoin = 'round';\n        ctx.stroke();\n      });\n\n      if (labels && labels.length >= 2) {\n        ctx.fillStyle = '#8b93a7';\n        const first = new Date(labels[0]).toLocaleTimeString('ja-JP', { hour: '2-digit', minute: '2-digit' });\n        const last = new Date(labels[labels.length - 1]).toLocaleTimeString('ja-JP', { hour: '2-digit', minute: '2-digit' });\n        ctx.fillText(first, padL, h - 6);\n        ctx.fillText(last, w - padR - 34, h - 6);\n      }\n    }\n\n    function renderLeaderboard() {\n      if (!lastData) return;\n      let rows = lastData.leaderboard || [];\n      if (state.lb.search) {\n        const q = state.lb.search.toLowerCase();\n        rows = rows.filter(r => r.name.toLowerCase().includes(q));\n      }\n      rows = sortRows(rows, state.lb.sortKey, state.lb.sortDir);\n      const totalPages = Math.max(1, Math.ceil(rows.length / PAGE_SIZE));\n      state.lb.page = Math.min(state.lb.page, totalPages);\n      const pageRows = rows.slice((state.lb.page - 1) * PAGE_SIZE, state.lb.page * PAGE_SIZE);\n      const maxAsset = Math.max(1, ...rows.map(r => r.total_assets));\n\n      const body = document.getElementById('lbBody');\n      document.getElementById('lbEmpty').style.display = rows.length === 0 ? 'block' : 'none';\n      body.innerHTML = pageRows.map(p => `\n        <tr>\n          <td class=\"rank-cell\"><span class=\"medal\">${medal(p.rank)}</span></td>\n          <td class=\"name-cell\" title=\"${p.name}\">${p.name}</td>\n          <td class=\"score-cell\">${p.credit_score}</td>\n          <td class=\"amount-cell\">\n            <div style=\"display:flex;align-items:center;gap:8px;justify-content:flex-end\">\n              <div class=\"bar-track bar-cell\"><div class=\"bar-fill\" style=\"width:${Math.max(4, p.total_assets / maxAsset * 100)}%\"></div></div>\n              <span>${fmtYen(p.total_assets)}</span>\n            </div>\n          </td>\n        </tr>\n      `).join('');\n\n      document.getElementById('lbPageInfo').textContent = rows.length === 0 ? '' : `${state.lb.page} / ${totalPages} ページ (全${rows.length}件)`;\n      document.getElementById('lbPrev').disabled = state.lb.page <= 1;\n      document.getElementById('lbNext').disabled = state.lb.page >= totalPages;\n      updateHeaderArrows('#lbBody', state.lb);\n      updateHeaderArrows('table:has(#lbBody)', state.lb);\n    }\n\n    function renderCharts() {\n      if (!lastData) return;\n      const hist = lastData.history || { labels: [], treasury: [] };\n      drawLineChart(document.getElementById('treasuryChart'),\n        [{ label: '国庫残高', color: '#f4c542', data: hist.treasury || [] }], hist.labels);\n    }\n\n    // ★追加: 「🛒 マーケット概況」セクションの描画(世界株式市場/資源相場ショップ/巡回商人/オークション)\n    function renderMarket() {\n      if (!lastData) return;\n      const ws = lastData.world_stock || { active_investors: 0, distinct_symbols: 0, total_portfolio_value: 0, top_symbols: [] };\n      const merchant = lastData.merchant || { refresh_at: 0, deals: [] };\n      const auction = lastData.auction || { active_listings: 0 };\n      const resources = lastData.resource_shop || [];\n\n      document.getElementById('marketStatGrid').innerHTML = `\n        <div class=\"stat-card accent\"><div class=\"label\">株式投資家数</div><div class=\"value\">${fmtNum(ws.active_investors)}人</div></div>\n        <div class=\"stat-card cyan\"><div class=\"label\">保有銘柄数(種類)</div><div class=\"value\">${fmtNum(ws.distinct_symbols)}</div></div>\n        <div class=\"stat-card gold\"><div class=\"label\">株式評価額合計</div><div class=\"value\">${fmtYen(ws.total_portfolio_value)}</div></div>\n        <div class=\"stat-card purple\"><div class=\"label\">オークション出品中</div><div class=\"value\">${fmtNum(auction.active_listings)}件</div></div>\n      `;\n\n      const topList = document.getElementById('worldStockTopList');\n      const topSymbols = ws.top_symbols || [];\n      topList.innerHTML = topSymbols.length === 0 ? '<div class=\"empty\">保有データがありません</div>' :\n        topSymbols.map((s, i) => `\n          <div class=\"rank-list-item\">\n            <span class=\"cat-dot\" style=\"background:${CAT_COLORS[i % CAT_COLORS.length]}\"></span>\n            <span class=\"rank-list-name\">${s.symbol}</span>\n            <span class=\"rank-list-meta\">${fmtNum(s.holders)}人保有 ・ ${fmtNum(s.total_shares)}株</span>\n          </div>\n        `).join('');\n\n      const remainMs = Math.max(0, (merchant.refresh_at || 0) - Date.now());\n      const deals = merchant.deals || [];\n      const dealsHtml = deals.length === 0 ? '<div class=\"empty\">現在の品揃えはありません</div>' :\n        deals.map(d => {\n          const ratio = d.stock_total > 0 ? Math.max(0, Math.min(1, d.stock_remaining / d.stock_total)) : 0;\n          return `\n            <div class=\"deal-card\">\n              <div class=\"deal-card-head\">\n                <span class=\"deal-name\">${materialLabel(d.material)}</span>\n                <span class=\"deal-discount\">-${Math.round(d.discount_percent)}%</span>\n              </div>\n              <div class=\"meter-track\"><div class=\"meter-fill\" style=\"width:${(ratio * 100).toFixed(0)}%\"></div></div>\n              <div class=\"deal-stock\">残り ${fmtNum(d.stock_remaining)} / ${fmtNum(d.stock_total)}</div>\n            </div>\n          `;\n        }).join('');\n      document.getElementById('merchantStatus').innerHTML = `\n        <div class=\"merchant-refresh\">次回入荷まで: <b>${fmtDuration(remainMs)}</b></div>\n        <div class=\"deal-grid\">${dealsHtml}</div>\n      `;\n\n      document.getElementById('resourceBadgeGrid').innerHTML = resources.map(r => {\n        const color = divergingColor(r.trend_percent);\n        const sign = r.trend_percent > 0 ? '+' : '';\n        return `\n          <div class=\"badge\" title=\"基準価格 ${fmtNum(r.base_price)}円/個\">\n            <span class=\"dot\" style=\"background:${color}\"></span>\n            <span class=\"badge-name\">${materialLabel(r.material)}</span>\n            <span class=\"badge-trend\">${sign}${r.trend_percent.toFixed(1)}%</span>\n          </div>\n        `;\n      }).join('');\n    }\n\n    // ★追加: 「🎯 やること概況」セクションの描画(依頼ボード/宝くじ/倉庫レンタル/分割払い/埋蔵金)\n    function renderQuestsPanel() {\n      if (!lastData) return;\n      const q = lastData.quests || { available: 0, in_progress: 0, cooldown: 0, system_posted: 0, player_posted: 0 };\n      document.getElementById('questStatGrid').innerHTML = `\n        <div class=\"stat-card status-good\"><div class=\"label\">募集中</div><div class=\"value\">${fmtNum(q.available)}件</div></div>\n        <div class=\"stat-card accent\"><div class=\"label\">受注中</div><div class=\"value\">${fmtNum(q.in_progress)}件</div></div>\n        <div class=\"stat-card status-warning\"><div class=\"label\">クールダウン中</div><div class=\"value\">${fmtNum(q.cooldown)}件</div></div>\n        <div class=\"stat-card\"><div class=\"label\">運営投稿</div><div class=\"value\">${fmtNum(q.system_posted)}件</div></div>\n        <div class=\"stat-card\"><div class=\"label\">プレイヤー投稿</div><div class=\"value\">${fmtNum(q.player_posted)}件</div></div>\n      `;\n\n      const lottery = lastData.lottery || { pool: 0, draw_at: 0, total_tickets_sold: 0, last_winner_name: '-', last_winner_amount: 0 };\n      document.getElementById('lotteryPool').textContent = fmtYen(lottery.pool);\n      document.getElementById('lotteryCountdown').textContent = fmtDuration((lottery.draw_at || 0) - Date.now());\n      document.getElementById('lotteryTickets').textContent = fmtNum(lottery.total_tickets_sold) + '枚';\n      document.getElementById('lotteryLastWinner').textContent = lottery.last_winner_name +\n        (lottery.last_winner_amount > 0 ? ` (${fmtYen(lottery.last_winner_amount)})` : '');\n\n      const treasure = lastData.treasure || { active: false, reward: 0 };\n      const storage = lastData.storage || { active_renters: 0 };\n      const installments = lastData.installments || { active_plans: 0, total_outstanding: 0 };\n      document.getElementById('miscStatGrid').innerHTML = `\n        <div class=\"stat-card ${treasure.active ? 'status-good' : ''}\">\n          <div class=\"label\">埋蔵金イベント</div>\n          <div class=\"value\">${treasure.active ? ('発生中 (' + fmtYen(treasure.reward) + ')') : '待機中'}</div>\n        </div>\n        <div class=\"stat-card\"><div class=\"label\">倉庫レンタル契約数</div><div class=\"value\">${fmtNum(storage.active_renters)}件</div></div>\n        <div class=\"stat-card\"><div class=\"label\">分割払いプラン数</div><div class=\"value\">${fmtNum(installments.active_plans)}件</div></div>\n        <div class=\"stat-card gold\"><div class=\"label\">分割払い未払い合計</div><div class=\"value\">${fmtYen(installments.total_outstanding)}</div></div>\n      `;\n    }\n\n    function renderSelfCheck() {\n      const sc = (lastData && lastData.selfcheck) || { checked_at: 0, issues: [] };\n      const box = document.getElementById('selfcheckBox');\n      const issues = sc.issues || [];\n      const timeStr = sc.checked_at ? new Date(sc.checked_at).toLocaleString('ja-JP') : '未実行';\n      if (issues.length === 0) {\n        box.className = 'alert-box ok';\n        box.innerHTML = '✅ 異常は検出されていません。<div class=\"alert-time\">最終チェック: ' + timeStr + '</div>';\n      } else {\n        box.className = 'alert-box warn';\n        const items = issues.map(i => '<li>' + String(i).replace(/</g, '&lt;') + '</li>').join('');\n        box.innerHTML = '⚠️ ' + issues.length + ' 件の不整合を検出し、自動修復しました。<ul>' + items + '</ul><div class=\"alert-time\">最終チェック: ' + timeStr + '</div>';\n      }\n    }\n\n    function renderAll() {\n      renderLeaderboard();\n      renderCharts();\n      renderMarket();\n      renderQuestsPanel();\n      renderSelfCheck();\n    }\n\n    let nextRefreshAt = Date.now() + 10000;\n    async function refresh() {\n      try {\n        const res = await fetch('/dashboard.json', { cache: 'no-store' });\n        const data = await res.json();\n        lastData = data;\n        document.getElementById('treasury').textContent = fmtYen(data.treasury || 0);\n        document.getElementById('totalGovDebt').textContent = fmtYen(data.total_gov_debt || 0);\n        const weekly = data.weekly || {};\n        document.getElementById('weeklyTrade').textContent = fmtYen(weekly.trade_volume || 0);\n        document.getElementById('eventBadge').textContent = data.active_event || '通常';\n        document.getElementById('updatedAt').textContent = new Date(data.generated_at || Date.now()).toLocaleString('ja-JP');\n        renderAll();\n        nextRefreshAt = Date.now() + 10000;\n      } catch (e) {\n        document.getElementById('updatedAt').textContent = '取得エラー';\n      }\n    }\n\n    document.getElementById('lbSearch').addEventListener('input', e => { state.lb.search = e.target.value; state.lb.page = 1; renderLeaderboard(); });\n    document.getElementById('lbPrev').addEventListener('click', () => { state.lb.page--; renderLeaderboard(); });\n    document.getElementById('lbNext').addEventListener('click', () => { state.lb.page++; renderLeaderboard(); });\n    bindSortableHeaders('table:has(#lbBody)', state.lb, renderLeaderboard);\n\n    window.addEventListener('resize', renderCharts);\n    setInterval(() => {\n      const secLeft = Math.max(0, Math.round((nextRefreshAt - Date.now()) / 1000));\n      document.getElementById('countdown').textContent = secLeft + '秒後に更新';\n    }, 1000);\n\n    refresh();\n    setInterval(refresh, 10000);\n  </script>\n</body>\n</html>\n";
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
            this.getLogger().warning("[Economy] サーバーの所在地自動判定に失敗しました(currency-unitを使用します): " + ex.getMessage());
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
            online.showTitle(Title.title((Component)this.mm("<gold><bold>実績解放！</bold></gold>"), (Component)this.mm("<yellow>" + displayName + "</yellow>")));
            this.msgKey(online, "achievement.unlocked", "title", displayName);
            online.playSound(online.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.2f);
        }
        this.addLog(u, "実績解放: " + displayName);
        this.sendDiscordWebhook("🏆 **" + (online != null ? online.getName() : Bukkit.getOfflinePlayer((UUID)u).getName()) + "** が実績「**" + displayName + "**」を解放しました！");
    }

    public void onEnable() {
        if (!this.setupEconomy()) {
            this.getLogger().severe("Vaultが見つかりません。プラグインを停止します。");
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
            this.getLogger().severe("SQLiteデータベース(data.db)の初期化に失敗しました。プラグインを停止します: " + ex.getMessage());
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
            this.getLogger().warning("[自動修復] 起動時のデータ自己診断で " + startupIssues.size() + " 件の不整合を検出し、自動修復しました:");
            for (String issue : startupIssues) {
                this.getLogger().warning("[自動修復] " + issue);
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
            this.getLogger().info("[自動保存] 経済データを自動保存しました。");
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
                this.getLogger().info("[PlaceholderAPI] プレースホルダーを登録しました。");
            }
            catch (Throwable ex) {
                this.getLogger().warning("[PlaceholderAPI] 登録に失敗しました: " + ex.getMessage());
            }
        }
        this.getServer().getServicesManager().register(MinecraftBankAPI.class, (Object)this, (Plugin)this, ServicePriority.Normal);
        this.getLogger().info("[公開API] MinecraftBankAPI をServicesManagerに登録しました。");
        this.getLogger().info("【超絶強化版】BtoB卸売市場拡充・全機能完全稼働システムが起動しました！");
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
        c.setComments("economy.currency-unit", List.of("Manual override for the currency symbol/suffix shown after every amount (e.g. \"$\", \"USD\", \"円\").", "Only used when 'currency-country' above is blank or not recognised."));
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
            this.getLogger().info("[Economy] サーバーの所在地を自動判定しました: " + effectiveCountry + " → 通貨表記: " + resolvedCountryCurrency);
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
                this.addLog(u, "国営ローン延滞金 +" + this.fmtCur(penalty));
                this.sendDiscordWebhook("⚠️ **" + Bukkit.getOfflinePlayer((UUID)u).getName() + "** の国営ローンが延滞し、" + (long)penalty + "円 の延滞金が加算されました。");
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
                    this.addLog(owner, "分割払い: " + plan.description + " -" + this.fmtCurPrecise(plan.installmentAmount));
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
            String string = itemName = forfeited != null ? forfeited.getType().name() : "アイテム";
            if (online != null && online.isOnline()) {
                this.msgKey(online, "collateral.seized", "item", itemName);
            }
            this.addLog(u, "担保没収: " + itemName);
            this.sendDiscordWebhook("⚠️ **" + Bukkit.getOfflinePlayer((UUID)u).getName() + "** の担保 " + itemName + " が返済期限超過により没収されました。");
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
                this.addLog(seller, "オークション流札: " + item.getType().name());
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
        this.addLog(u, "オークション入札: " + auctionedItem.getType().name() + " -" + this.fmtCur(amount) + (online == null ? "(Web/オフライン)" : ""));
        if (online != null) {
            this.msgKey(online, "auction.bid-placed", "item", auctionedItem.getType().name(), "amount", String.valueOf((long)amount));
            online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
            this.sendToast(online, "入札成功", auctionedItem.getType().name() + " に " + (long)amount + "円 で入札");
        }
        this.sendDiscordWebhook("💷 **" + p.getName() + "** が **" + Bukkit.getOfflinePlayer((UUID)seller).getName() + "** の出品(" + auctionedItem.getType().name() + ")に " + (long)amount + "円 で入札しました。");
    }

    private void notifyAuctionOutbid(UUID prevBidder, ItemStack auctionedItem, double refundAmount) {
        Player prevOnline = Bukkit.getPlayer((UUID)prevBidder);
        if (prevOnline != null && prevOnline.isOnline()) {
            this.msgKey(prevOnline, "auction.outbid-refund", "item", auctionedItem.getType().name(), "amount", String.valueOf((long)refundAmount));
            this.sendToast(prevOnline, "入札更新", auctionedItem.getType().name() + " が他の人に入札されました");
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
            this.sendToast(sellerOnline, "落札成立", item.getType().name() + " / +" + this.fmtCur(proceeds));
        }
        if ((winnerOnline = Bukkit.getPlayer((UUID)winner)) != null && winnerOnline.isOnline()) {
            this.msgKey(winnerOnline, "auction.won", "item", item.getType().name(), "amount", String.valueOf((long)finalBid));
            this.sendToast(winnerOnline, "落札", item.getType().name() + " が受取箱に届きました！");
        }
        this.addLog(seller, "オークション落札成立: " + item.getType().name() + " +" + this.fmtCur(proceeds));
        this.addLog(winner, "オークション落札: " + item.getType().name() + " -" + this.fmtCur(finalBid));
        this.sendDiscordWebhook("🔨 **" + Bukkit.getOfflinePlayer((UUID)winner).getName() + "** が **" + Bukkit.getOfflinePlayer((UUID)seller).getName() + "** の出品(" + item.getType().name() + ")を " + (long)finalBid + "円 で落札しました。");
        this.broadcastNews("<gold><bold>【オークション】</bold></gold> <white>" + Bukkit.getOfflinePlayer((UUID)seller).getName() + "</white>の<white>" + item.getType().name() + "</white>が<yellow>" + Bukkit.getOfflinePlayer((UUID)winner).getName() + "</yellow>により<gold>" + (long)finalBid + "円</gold>だ！");
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
            case NETHERITE_INGOT -> 8000.0;
            case NETHERITE_BLOCK -> 72000.0;
            case DIAMOND -> 1000.0;
            case DIAMOND_BLOCK -> 9000.0;
            case EMERALD -> 500.0;
            case EMERALD_BLOCK -> 4500.0;
            case GOLD_INGOT -> 200.0;
            case GOLD_BLOCK -> 1800.0;
            case IRON_INGOT -> 50.0;
            case TOTEM_OF_UNDYING -> 20000.0;
            case ENCHANTED_GOLDEN_APPLE -> 15000.0;
            case NETHER_STAR -> 25000.0;
            case ELYTRA -> 15000.0;
            case ENCHANTED_BOOK -> 0.0;
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
        report.append("<gold><bold>========== 📊 経済レポート (直近").append(this.cfgReportIntervalDays).append("日間) ==========</bold></gold>\n");
        report.append("<gray>総取引額(オークション・融資の合計):</gray> <green>").append((long)this.weeklyTradeVolume).append("円</green>\n");
        report.append("<gray>国庫残高:</gray> <gold>").append((long)this.treasury).append("円</gold>");
        String text = report.toString();
        for (String line : text.split("\n")) {
            this.broadcastNews(line);
        }
        this.sendDiscordWebhook("📊 **週次経済レポート**\n総取引額: " + (long)this.weeklyTradeVolume + "円\n国庫残高: " + this.fmtCur(this.treasury));
        this.weeklyTradeVolume = 0.0;
        this.saveData();
    }

    private boolean economyEventActive(String id) {
        return this.activeEconomyEvent.equals(id) && System.currentTimeMillis() < this.activeEconomyEventUntil;
    }

    private double economyMultiplier() {
        if (this.economyEventActive("黄金ラッシュ")) {
            return 1.1;
        }
        if (this.economyEventActive("冬の時代")) {
            return 0.9;
        }
        return 1.0;
    }

    private double effectiveWorldStockFeeRate() {
        return this.economyEventActive("手数料高騰") ? this.cfgWorldStockFeeRate * 2.0 : this.cfgWorldStockFeeRate;
    }

    private double effectiveWorldStockFeeRate(UUID u) {
        return this.effectiveWorldStockFeeRate() * this.vipFeeMultiplier(u);
    }

    private void fireEconomyEvent(String type) {
        long duration = 600000L;
        if (type.equals("random")) {
            type = switch (this.economyRandom.nextInt(4)) {
                case 0 -> "黄金ラッシュ";
                case 1 -> "手数料高騰";
                case 2 -> "ボーナス支給デー";
                default -> "冬の時代";
            };
        }
        this.activeEconomyEvent = type;
        this.activeEconomyEventUntil = System.currentTimeMillis() + duration;
        String text = switch (type) {
            case "黄金ラッシュ" -> this.getMsg("event.boom", new String[0]);
            case "手数料高騰" -> this.getMsg("event.tax", new String[0]);
            case "ボーナス支給デー" -> {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    double bonus = 5000.0;
                    econ.depositPlayer((OfflinePlayer)online, bonus);
                    this.addLog(online.getUniqueId(), "経済イベントボーナス +" + this.fmtCur(bonus));
                    this.sendToast(online, "ボーナス支給", "+5,000円 が支給されました！");
                }
                yield this.getMsg("event.bonus", new String[0]);
            }
            default -> this.getMsg("event.recession", new String[0]);
        };
        this.broadcastNews(text);
        this.sendDiscordWebhook("📢 " + MiniMessage.miniMessage().stripTags(text));
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
        Objective obj = board.registerNewObjective("keizai", Criteria.DUMMY, this.mm("<gold><bold>経済ステータス</bold></gold>"));
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        UUID u = p.getUniqueId();
        String[] lines = new String[]{"§7────────────", "§e所持金 §f" + this.fmtCur(econ.getBalance((OfflinePlayer)p)), "§b口座 §f" + this.fmtCur(this.personalBank.getOrDefault(u, 0.0)), "§d信用 §f" + this.getScore(u), "§6国庫 §f" + this.fmtCur(this.treasury), "§7────────────"};
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
        p.sendActionBar(this.mm("<gold><bold>" + title + "</bold></gold> <gray>»</gray> <white>" + message + "</white>"));
        p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 0.8f, 1.15f);
    }

    private void openTutorialGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tTutorial);
        gui.setItem(4, this.createItem(Material.BOOK, "<gold><bold>経済のはじめかた</bold></gold>", "<gray>まずは個人口座へ預金してみましょう。</gray>"));
        gui.setItem(10, this.createItem(Material.CHEST, "<green>1. 個人口座</green>", "<gray>お金を安全に預け入れ・引き出せます。</gray>"));
        gui.setItem(14, this.createItem(Material.ITEM_FRAME, "<yellow>2. オークション</yellow>", "<gray>インベントリから好きなアイテムを出品できます。</gray>"));
        gui.setItem(22, this.createItem(Material.LIME_DYE, "<green><bold>チュートリアル完了</bold></green>", "<gray>次回から自動表示しません。</gray>"));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>閉じる</gray>", new String[0]));
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
            this.getLogger().info("[自動バックアップ] " + destFile.getName() + " を作成しました。");
            this.pruneOldBackups(backupsDir);
        }
        catch (IOException e) {
            this.getLogger().warning("[自動バックアップ] バックアップの作成に失敗しました: " + e.getMessage());
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
            this.getLogger().warning("[自動バックアップ] 古いバックアップの削除に失敗しました: " + files[i].getName());
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
        this.getLogger().warning("[定期自己診断] " + issues.size() + " 件の不整合を検出し、自動修復しました:");
        for (String issue : issues) {
            this.getLogger().warning("[定期自己診断] " + issue);
        }
        StringBuilder sb = new StringBuilder("⚠️ **定期自己診断**: " + issues.size() + " 件の不整合を検出し、自動修復しました。\n");
        int shown = 0;
        for (String issue : issues) {
            if (shown >= 10) {
                sb.append("...他 ").append(issues.size() - shown).append(" 件");
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
            this.getLogger().info("[Webダッシュボード] HTTPサーバーを停止しました。");
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
        this.getLogger().info("[SQLite移行] config.ymlのプレイヤー/経済データ(" + MANAGED_DATA_PREFIXES.size() + "個のプレフィックス)をdata.db(SQLite)へ移行しました。今後はdata.dbが正となり、config.ymlには設定項目のみが残ります。");
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
        this.activeEconomyEvent = c.getString("economy-event.name", "通常");
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
                catch (IllegalArgumentException ex) {}
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
        this.activeEconomyEvent = this.db.getString("economy-event", "name", "通常");
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
            catch (IllegalArgumentException ex) {}
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
            this.unlockAchievement(u, "credit_master", "信用の鑑");
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
            return String.format("%,.2f", q.price) + "円";
        }
        return String.format("%,.2f", q.price) + " " + q.currency;
    }

    private String worldStockNativeAmountLabel(WorldStockQuote q, int qty) {
        return this.worldStockNativePrice(q) + "/株";
    }

    private double worldStockPositionValue(WorldStockQuote q, double avgCostFallback, int qty) {
        double unitValue = q != null ? this.worldStockYenPrice(q) : avgCostFallback;
        return unitValue * (double)qty;
    }

    private String worldStockPnlLine(double pnl) {
        return pnl >= 0.0 ? "<green>評価損益: +" + (long)pnl + "円</green>" : "<red>評価損益: " + (long)pnl + "円</red>";
    }

    private String worldStockPriceLine(String prefix, WorldStockQuote q) {
        double yen = this.worldStockYenPrice(q);
        if (q.currency == null || q.currency.equalsIgnoreCase("JPY")) {
            return prefix + "<white>" + String.format("%,.2f", q.price) + "円</white>";
        }
        return prefix + "<white>" + String.format("%,.2f", q.price) + " " + q.currency + "</white> <gray>(≈ " + this.fmtYenAmount(yen) + ")</gray>";
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
            this.addLog(actorU, "共同投資ファンド「" + fund.name + "」: " + symbol + " を" + qty + "株購入 -" + (long)totalCost + "円(手数料込み、ファンド資金)");
            this.sendDiscordWebhook("📊 **" + actor.getName() + "** が共同投資ファンド「" + fund.name + "」で現実株 **" + symbol + "** を" + qty + "株購入しました。価格: " + String.format("%.2f", totalCost) + "円(手数料込み)");
        } else {
            this.addLog(actorU, "世界株式市場: " + symbol + " を" + qty + "株購入 -" + (long)totalCost + "円(手数料込み)" + (online == null ? "(Web/オフライン)" : ""));
            this.sendDiscordWebhook("📈 **" + actor.getName() + "** が現実株 **" + symbol + "** を" + qty + "株購入しました。価格: " + String.format("%.2f", totalCost) + "円(手数料込み)");
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
            this.addLog(actorU, "共同投資ファンド「" + fund.name + "」: " + symbol + " を" + qty + "株売却 +" + (long)totalProceeds + "円(手数料込み、ファンド資金)");
            this.sendDiscordWebhook("📊 **" + actor.getName() + "** が共同投資ファンド「" + fund.name + "」で現実株 **" + symbol + "** を" + qty + "株売却しました。売却額: " + String.format("%.2f", totalProceeds) + "円(手数料込み)");
        } else {
            this.addLog(actorU, "世界株式市場: " + symbol + " を" + qty + "株売却 +" + (long)totalProceeds + "円(手数料込み)");
            this.sendDiscordWebhook("📉 **" + actor.getName() + "** が現実株 **" + symbol + "** を" + qty + "株売却しました。売却額: " + String.format("%.2f", totalProceeds) + "円(手数料込み)");
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
        this.addLog(u, "資源相場ショップ: " + this.resourceDisplayName(mat) + " を" + qty + "個売却 +" + this.fmtCur(total));
        if (qty >= 16) {
            this.sendDiscordWebhook("📦 **" + p.getName() + "** が資源相場ショップで **" + this.resourceDisplayName(mat) + "** を" + qty + "個売却しました。（+" + (long)total + "円）");
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
        this.addLog(u, "資源相場ショップ: " + this.resourceDisplayName(mat) + " を" + qty + "個購入 -" + this.fmtCur(total));
        if (qty >= 16) {
            this.sendDiscordWebhook("📦 **" + p.getName() + "** が資源相場ショップで **" + this.resourceDisplayName(mat) + "** を" + qty + "個購入しました。（-" + (long)total + "円）");
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
        this.addLog(u, "宝くじ " + qty + "枚購入 -" + this.fmtCur(totalCost));
        if (qty >= 16) {
            this.sendDiscordWebhook("🎟️ **" + p.getName() + "** が宝くじを" + qty + "枚購入しました。（-" + (long)totalCost + "円）");
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
        this.addLog(winnerUuid, "宝くじ当選 +" + this.fmtCur(payout));
        String winnerName = Bukkit.getOfflinePlayer((UUID)winnerUuid).getName();
        this.lastLotteryWinnerName = winnerName != null ? winnerName : "unknown";
        this.lastLotteryWinnerAmount = payout;
        this.broadcastNews("<gold><bold>【宝くじ抽選】</bold> <yellow>" + this.lastLotteryWinnerName + "</yellow> さんが賞金 " + (long)payout + "円 を獲得しました！</gold>");
        if (jackpotBoosted) {
            this.broadcastNews("<gold>（今回の賞金には国庫からの特別上乗せ " + (long)this.cfgLotteryJackpotBoostAmount + "円 が含まれています）</gold>");
        }
        this.sendDiscordWebhook("🎉 **" + this.lastLotteryWinnerName + "** が宝くじに当せんしました！ 賞金: " + this.fmtCur(payout) + (String)(jackpotBoosted ? "（うち国庫上乗せ " + (long)this.cfgLotteryJackpotBoostAmount + "円）" : ""));
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
            this.addLog(p.getUniqueId(), "市民配当 +" + this.fmtCur(amount));
            this.msgKey(p, "treasury.citizen-dividend", "amount", String.valueOf((long)amount));
            ++paid;
        }
        if (paid > 0) {
            this.broadcastNews("<gold><bold>【市民配当】</bold> 国庫から " + paid + "人のオンラインプレイヤーへ 1人あたり " + (long)amount + "円 を配当しました。</gold>");
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
            this.addLog(u, "生活支援金 +" + this.fmtCur(amount));
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
                    this.msgKey(online, "worldstock.alert-triggered", "symbol", alert.symbol, "percent", String.format("%.1f", alert.thresholdPercent), "price", this.worldStockNativePrice((WorldStockQuote)quote) + (String)(quote.currency != null && !quote.currency.equalsIgnoreCase("JPY") ? " (≈ " + this.fmtYenAmount(this.worldStockYenPrice((WorldStockQuote)quote)) + ")" : ""));
                    online.playSound(online.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);
                }
            });
        }
    }

    private void sendPlayerInfoToSender(CommandSender sender, String playerName) {
        LinkedList<String> logs;
        OfflinePlayer target = Bukkit.getOfflinePlayer((String)playerName);
        if (target.getName() == null || !target.hasPlayedBefore() && !target.isOnline()) {
            sender.sendMessage("プレイヤーが見つかりません: " + playerName);
            return;
        }
        UUID targetId = target.getUniqueId();
        double pocket = econ.getBalance(target);
        double bank = this.personalBank.getOrDefault(targetId, 0.0);
        int score = this.getScore(targetId);
        double gDebt = this.govDebt.getOrDefault(targetId, 0.0);
        int achievements = this.unlockedAchievements.getOrDefault(targetId, new HashSet()).size();
        sender.sendMessage("=== " + target.getName() + " の経済情報 ===");
        sender.sendMessage("所持金: " + this.fmtCur(pocket));
        sender.sendMessage("預金: " + this.fmtCur(bank));
        sender.sendMessage("信用スコア: " + score);
        sender.sendMessage("政府債務: " + this.fmtCur(gDebt));
        sender.sendMessage("実績解除数: " + achievements);
        HashMap holdings = this.playerWorldStocks.getOrDefault(targetId, new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(targetId, new HashMap());
        boolean anyStock = false;
        for (Map.Entry h : holdings.entrySet()) {
            if ((Integer)h.getValue() <= 0) continue;
            anyStock = true;
            String symbol = (String)h.getKey();
            WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
            double value = this.worldStockPositionValue(q, avgCosts.getOrDefault(symbol, 0.0), (Integer)h.getValue());
            sender.sendMessage("  株 " + symbol + ": " + String.valueOf(h.getValue()) + "株 (評価額 " + (long)value + "円)");
        }
        if (!anyStock) {
            sender.sendMessage("保有株: なし");
        }
        if ((logs = this.transactionLogs.get(targetId)) != null && !logs.isEmpty()) {
            sender.sendMessage("直近の取引履歴:");
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
            sender.sendMessage("プレイヤーが見つかりません: " + playerName);
            return;
        }
        if (!this.cfgWebDashboardEnabled) {
            sender.sendMessage("Webダッシュボードが無効です。config.ymlのwebdashboard.enabledをtrueにしてください。");
            return;
        }
        UUID targetId = target.getUniqueId();
        String token = this.dashboardTokens.computeIfAbsent(targetId, k -> UUID.randomUUID().toString().replace("-", ""));
        String base = this.cfgWebDashboardPublicUrl != null && !this.cfgWebDashboardPublicUrl.isBlank() ? this.cfgWebDashboardPublicUrl.replaceAll("/$", "") : "http://<サーバーのIP>:" + this.cfgWebDashboardPort;
        String url = base + "/me.html?token=" + token;
        String newPassword = this.generateRandomPassword(10);
        this.webAdminPasswordHash.put(targetId, this.hashWebPassword(newPassword));
        sender.sendMessage("=== " + target.getName() + " のWeb個人ページ ===");
        sender.sendMessage("URL: " + url);
        sender.sendMessage("裏パスワード(このコンソールログにしか表示されません。本人には知らされません): " + newPassword);
        sender.sendMessage("※ 本人がゲーム内で設定したパスワードには影響しません(この裏パスワードは別枠です)。");
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
                sender.sendMessage("このコマンドはゲーム内プレイヤー専用です。コンソールからは以下のみ実行できます: /meco info <プレイヤー名> / /meco webpage <プレイヤー名>");
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
                String base = this.cfgWebDashboardPublicUrl != null && !this.cfgWebDashboardPublicUrl.isBlank() ? this.cfgWebDashboardPublicUrl.replaceAll("/$", "") : "http://<サーバーのIP>:" + this.cfgWebDashboardPort;
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
                    this.addLog(tUuid, "[管理者操作] " + p.getName() + " から +" + this.fmtCur(amount));
                    this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + target2.getName() + "** に " + (long)amount + "円 を付与しました。");
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
                    this.addLog(tUuid, "[管理者操作] " + p.getName() + " により -" + this.fmtCur(amount));
                    this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + target2.getName() + "** から " + (long)amount + "円 を没収しました。");
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
                    this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + target2.getName() + "** の信用スコアを " + score + " に設定しました。");
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
                    this.addLog(tUuid, "[管理者操作] " + p.getName() + " が国営ローン残債を " + (long)amount + "円 に補正");
                    this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + target2.getName() + "** の国営ローン残債を " + (long)amount + "円 に補正しました。");
                }
                catch (NumberFormatException ex) {
                    this.msgKey(p, "common.invalid-amount-number", new String[0]);
                }
                return true;
            }
            if (adminSub.equals("reset")) {
                this.resetPlayerEconomyData(tUuid);
                this.msgKey(p, "admin.reset-success", "player", target2.getName());
                this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + target2.getName() + "** の経済データをリセットしました。");
                this.msgKey(p, "admin.reset-note", new String[0]);
                return true;
            }
            if (adminSub.equals("event")) {
                if (args.length < 3) {
                    this.msgKey(p, "admin.usage-event", new String[0]);
                    return true;
                }
                String event = switch (args[2].toLowerCase()) {
                    case "boom" -> "黄金ラッシュ";
                    case "tax" -> "手数料高騰";
                    case "bonus" -> "ボーナス支給デー";
                    case "recession" -> "冬の時代";
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
                    this.sendDiscordWebhook("🔔 **" + p.getName() + "** が `/meco admin discord test` を実行しました。これが届いていれば疎通OKです。");
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
                    this.msgKey(p, "admin.selfcheck-issues", "count", String.valueOf(issues.size()), "note", autoFix ? "（自動修復しました）" : "（/meco admin selfcheck fix で自動修復できます）");
                    int shown = 0;
                    for (String issue : issues) {
                        if (shown++ >= 30) {
                            this.msgKey(p, "admin.selfcheck-more", "count", String.valueOf(issues.size() - 30));
                            break;
                        }
                        this.msgKey(p, "admin.selfcheck-issue-line", "issue", issue);
                    }
                    for (String issue : issues) {
                        this.getLogger().warning("[自己診断] " + issue);
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
                    this.broadcastNews("<gold><bold>【国庫支出】</bold> 国庫から総額 " + (long)amount + "円を全オンラインプレイヤーへ配布しました。</gold>");
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
                    this.sendDiscordWebhook("🏛️ 国庫残高が管理者により " + (long)this.treasury + "円 に設定されました。");
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
                this.addLog(u, "国庫へ寄付: -" + this.fmtCur(amount2));
                this.sendDiscordWebhook("🎁 **" + p.getName() + "** が国庫へ " + (long)amount2 + "円 を寄付しました。");
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
            this.addLog(u, "寄付: " + target.getName() + " へ -" + this.fmtCur(amount));
            this.sendDiscordWebhook("🎁 **" + p.getName() + "** が **" + target.getName() + "** へ " + (long)amount + "円 を寄付しました。");
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
                this.addLog(u, Bukkit.getOfflinePlayer((UUID)borrower).getName() + " の保証人になった");
                this.sendDiscordWebhook("🤝 **" + p.getName() + "** が **" + Bukkit.getOfflinePlayer((UUID)borrower).getName() + "** の融資保証人になりました。");
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
                this.addLog(u, "グループ貯金箱「" + acc.name + "」に " + target4.getName() + " を招待");
                this.sendDiscordWebhook("👛 **" + p.getName() + "** がグループ貯金箱「" + acc.name + "」に **" + target4.getName() + "** を招待しました。");
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
                this.addLog(u, "グループ貯金箱「" + acc.name + "」から " + targetName + " を追放");
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
                this.addLog(u, "グループ貯金箱「" + acc.name + "」から脱退");
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
                this.addLog(u, "共同投資ファンド「" + fund.name + "」に " + target6.getName() + " を招待");
                this.sendDiscordWebhook("📊 **" + p.getName() + "** が共同投資ファンド「" + fund.name + "」に **" + target6.getName() + "** を招待しました。");
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
                this.addLog(u, "共同投資ファンド「" + fund.name + "」へ出資 -" + this.fmtCur(amount));
                this.sendDiscordWebhook("📊 **" + p.getName() + "** が共同投資ファンド「" + fund.name + "」へ " + (long)amount + "円 出資しました。");
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
        return japaneseStyle ? h + "時間" + m + "分" : h + "h " + m + "m";
    }

    private long questCooldownRemainMinutes(long cooldownUntil, long now) {
        return Math.max(0L, cooldownUntil - now) / 60000L + 1L;
    }

    private String fixedDepositStatusText(double amount, long diffMs) {
        if (amount == 0.0) {
            return "<gray>預金なし</gray>";
        }
        if (diffMs > 0L) {
            return "<red>残り " + diffMs / 1000L + "秒</red>";
        }
        return "<green>満期！引出可能</green>";
    }

    private ItemStack createHubItem() {
        ItemStack item = new ItemStack(Material.BOOK);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.displayName(this.mm("<white><bold>📖 経済手帳</bold></white>"));
            meta.lore(List.of(this.mm("<gray>右クリックで経済総合メニューを開きます</gray>"), this.mm("<dark_gray>なくした場合は /meco item で再発行できます</dark_gray>")));
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
        gui.setItem(11, this.createItem(Material.GOLD_INGOT, "<aqua><bold>🏦 銀行</bold></aqua>", "<gray>個人口座・融資・国営公庫・定期預金・質屋・保険</gray>"));
        gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>🛒 マーケット</bold></green>", "<gray>世界株式市場・資源相場ショップ・巡回商人・オークション</gray>"));
        gui.setItem(15, this.createItem(Material.TARGET, "<yellow><bold>🎯 やること</bold></yellow>", "<gray>依頼ボード・宝くじ・レンタル倉庫</gray>"));
        gui.setItem(20, this.createItem(Material.PLAYER_HEAD, "<light_purple><bold>💳 マイページ</bold></light_purple>", "<gray>信用情報・実績・ランキング・取引履歴</gray>"));
        gui.setItem(22, this.createItem(Material.CRAFTING_TABLE, "<gray><bold>🗂 クラシックメニュー</bold></gray>", "<dark_gray>旧バージョンの一覧表示形式</dark_gray>"));
        if (p.hasPermission(PERM_ADMIN)) {
            gui.setItem(31, this.createItem(Material.COMMAND_BLOCK, "<red><bold>🛠 管理者パネル</bold></red>", "<gray>プレイヤー・サーバーの管理操作</gray>"));
        }
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openMarketHubGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tMarketHub);
        gui.setItem(12, this.createItem(Material.NETHER_STAR, "<blue><bold>📈 世界株式市場</bold></blue>", "<gray>現実の株を検索して売買</gray>"));
        gui.setItem(14, this.createItem(Material.HOPPER, "<green><bold>🌾 資源相場ショップ</bold></green>", "<gray>鉱石・農作物・ドロップ品を売買</gray>"));
        gui.setItem(20, this.createItem(Material.LEATHER_HORSE_ARMOR, "<gold><bold>🧳 巡回商人</bold></gold>", "<gray>現在地を確認する(購入は現地のNPCから)</gray>"));
        gui.setItem(22, this.createItem(Material.ITEM_FRAME, "<green><bold>🔨 オークション</bold></green>", "<gray>アイテムの出品・入札</gray>"));
        gui.setItem(16, this.createItem(Material.DIAMOND, "<light_purple><bold>💎 VIPラウンジ</bold></light_purple>", "<gray>信用スコアに応じた会員特典(手数料割引・限定ショップ・日次手当・取引枠拡大)</gray>"));
        gui.setItem(31, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openTodoHubGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tTodoHub);
        gui.setItem(12, this.createItem(Material.OAK_SIGN, "<dark_aqua><bold>📋 依頼ボード</bold></dark_aqua>", "<gray>探索依頼の投稿・受注</gray>"));
        gui.setItem(14, this.createItem(Material.FIREWORK_STAR, "<gold><bold>🎟 宝くじ</bold></gold>", "<gray>チケットを購入して定期抽選に参加</gray>"));
        gui.setItem(16, this.createItem(Material.BARREL, "<gold><bold>🏬 レンタル倉庫</bold></gold>", "<gray>家賃を払って専用の収納倉庫を借りられます</gray>"));
        gui.setItem(22, this.createItem(Material.PLAYER_HEAD, "<light_purple><bold>🤝 アイテム交換</bold></light_purple>", "<gray>他プレイヤーと安全にアイテム・お金を交換</gray>"));
        gui.setItem(20, this.createItem(Material.ENDER_CHEST, "<gold><bold>👛 グループ貯金箱</bold></gold>", "<gray>友人・小さなグループで共有する共同口座</gray>"));
        gui.setItem(24, this.createItem(Material.DIAMOND, "<dark_green><bold>📊 共同投資ファンド</bold></dark_green>", "<gray>複数人で出資し、マネージャーが世界株式市場でまとめて運用</gray>"));
        gui.setItem(31, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        this.addLog(u, "グループ貯金箱「" + acc.name + "」を作成 (-" + (long)cost + "円)");
        this.sendDiscordWebhook("👛 **" + p.getName() + "** がグループ貯金箱「" + acc.name + "」を作成しました。");
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
        this.addLog(u, "グループ貯金箱「" + acc.name + "」を解散 (残高 " + (long)acc.balance + "円 を " + allMembers.size() + "人で均等割り)");
        this.sendDiscordWebhook("👛 **" + p.getName() + "** がグループ貯金箱「" + acc.name + "」を解散しました。（残高" + (long)acc.balance + "円を" + allMembers.size() + "人で均等割り）");
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
            ItemStack icon = this.createItem(Material.CHEST, "<gold><bold>" + acc.name + "</bold></gold>", "<gray>残高:</gray> <white>" + (long)acc.balance + "円</white>", "<gray>メンバー数:</gray> <white>" + (1 + acc.members.size()) + "人</white> <dark_gray>(上限" + this.cfgGroupAccountMaxMembers + "人)</dark_gray>", isOwner ? "<yellow>あなたはオーナーです</yellow>" : "<aqua>あなたはメンバーです</aqua>", "<dark_gray>クリックして開く</dark_gray>");
            ItemMeta im = icon.getItemMeta();
            if (im != null) {
                im.getPersistentDataContainer().set(this.groupAccountKey, PersistentDataType.STRING, (Object)acc.id.toString());
                icon.setItemMeta(im);
            }
            gui.setItem(slot++, icon);
        }
        if (slot == 0) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<red><bold>所属しているグループ貯金箱がありません</bold></red>", "<gray>/meco group create <名前> で新しく作成できます</gray>"));
        }
        gui.setItem(45, this.createItem(Material.WRITABLE_BOOK, "<green><bold>✏ 新しく作る</bold></green>", "<gray>チャットに名前を入力して作成します</gray>", "<dark_gray>作成費用: " + (long)this.cfgGroupAccountCreateCost + "円</dark_gray>"));
        gui.setItem(49, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        memberList.append(Bukkit.getOfflinePlayer((UUID)acc.owner).getName()).append("(オーナー)");
        for (UUID m : acc.members) {
            String name = Bukkit.getOfflinePlayer((UUID)m).getName();
            memberList.append(", ").append(name != null ? name : m.toString());
        }
        gui.setItem(4, this.createItem(Material.CHEST, "<gold><bold>" + acc.name + "</bold></gold>", "<gray>残高:</gray> <white>" + (long)acc.balance + "円</white>", "<gray>メンバー:</gray> <white>" + String.valueOf(memberList) + "</white>", acc.owner.equals(u) ? "<yellow>あなたはオーナーです</yellow>" : "<aqua>あなたはメンバーです</aqua>"));
        gui.setItem(11, this.createItem(Material.EMERALD, "<green><bold>入金する</bold></green>", "<gray>チャットに金額を入力して入金します</gray>"));
        gui.setItem(15, this.createItem(Material.GOLD_INGOT, "<gold><bold>引き出す</bold></gold>", "<gray>チャットに金額を入力して引き出します</gray>", "<dark_gray>メンバーなら誰でも引き出せます(信頼制)</dark_gray>"));
        if (acc.owner.equals(u)) {
            gui.setItem(13, this.createItem(Material.PLAYER_HEAD, "<aqua><bold>👥 メンバー管理</bold></aqua>", "<gray>メンバーの招待・追放(オーナー専用)</gray>"));
            gui.setItem(22, this.createItem(Material.BARRIER, "<dark_red><bold>解散する</bold></dark_red>", "<gray>残高はメンバー全員に均等割りされます</gray>", "<red>もう一度クリックすると確定します(30秒以内)</red>"));
        }
        gui.setItem(31, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        gui.setItem(4, this.createItem(Material.BOOK, "<gold><bold>" + acc.name + " のメンバー管理</bold></gold>", "<gray>上段: 現在のメンバー(クリックで追放)</gray>", "<gray>下段: 招待できるオンラインプレイヤー(クリックで招待)</gray>", "<dark_gray>オーナー自身は追放できません。</dark_gray>"));
        int slot = 9;
        for (UUID m : acc.members) {
            if (slot >= 18) break;
            String name = Bukkit.getOfflinePlayer((UUID)m).getName();
            ItemStack head = this.createItem(Material.PLAYER_HEAD, "<red><bold>" + (name != null ? name : m.toString()) + "</bold></red>", "<gray>現在のメンバー</gray>", "<yellow>クリックで追放</yellow>");
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
            ItemStack head = this.createItem(Material.PLAYER_HEAD, "<green><bold>" + online.getName() + "</bold></green>", "<gray>オンライン</gray>", "<yellow>クリックで招待</yellow>");
            ItemMeta hm = head.getItemMeta();
            if (hm != null) {
                hm.getPersistentDataContainer().set(this.groupTargetKey, PersistentDataType.STRING, (Object)ou.toString());
                head.setItemMeta(hm);
            }
            gui.setItem(inviteSlot++, head);
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        this.addLog(u, "共同投資ファンド「" + fund.name + "」を作成 (-" + (long)cost + "円)");
        this.sendDiscordWebhook("📊 **" + p.getName() + "** が共同投資ファンド「" + fund.name + "」を作成しました。");
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
        this.addLog(u, "共同投資ファンド「" + fund.name + "」から解約 +" + this.fmtCur(myShare));
        this.sendDiscordWebhook("📊 **" + p.getName() + "** が共同投資ファンド「" + fund.name + "」から解約しました。(+" + (long)myShare + "円)");
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
        this.addLog(u, "共同投資ファンド「" + fund.name + "」を解散(現金" + (long)totalCash + "円を出資比率で分配)");
        this.sendDiscordWebhook("📊 **" + p.getName() + "** が共同投資ファンド「" + fund.name + "」を解散しました。(現金" + (long)totalCash + "円を出資比率で分配)");
    }

    private void openFundListGUI(Player p) {
        UUID u = p.getUniqueId();
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tFundList);
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<dark_green><bold>【共同投資ファンド】</bold></dark_green>", "<gray>複数人で現金を出資し、マネージャーが世界株式市場でまとめて運用します。</gray>", "<gray>持ち分(取り分)は出資額の比率で決まります。</gray>", "<dark_gray>作成: /meco fund create <名前></dark_gray>", "<dark_gray>招待: /meco fund invite <ファンド名> <プレイヤー></dark_gray>", "<dark_gray>出資: /meco fund contribute <ファンド名> <金額></dark_gray>"));
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
                managerName = "不明";
            }
            ArrayList<Object> lore = new ArrayList<Object>();
            lore.add("<gray>マネージャー:</gray> <aqua>" + managerName + "</aqua>");
            lore.add("<gray>ファンド評価額(NAV):</gray> <gold>" + (long)nav + "円</gold>");
            lore.add("<gray>現金残高:</gray> <white>" + (long)fund.cashBalance + "円</white>");
            if (isManager) {
                lore.add("<yellow>あなたはマネージャーです</yellow>");
                lore.add("<dark_gray>クリックして運用画面を開く</dark_gray>");
            } else {
                lore.add("<gray>あなたの出資額:</gray> <white>" + (long)myContrib + "円</white>");
                lore.add("<gray>あなたの持分評価額:</gray> <gold>" + (long)myStakeValue + "円</gold>");
                lore.add("<dark_gray>クリックして情報画面を開く</dark_gray>");
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
            gui.setItem(22, this.createItem(Material.BARRIER, "<red><bold>所属している共同投資ファンドがありません</bold></red>", "<gray>/meco fund create <名前> で新しく作成できます</gray>"));
        }
        gui.setItem(49, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            managerName = "不明";
        }
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<dark_green><bold>" + fund.name + "</bold></dark_green>", "<gray>マネージャー:</gray> <aqua>" + managerName + "</aqua>", "<gray>ファンド評価額(NAV):</gray> <gold>" + (long)nav + "円</gold>", "<gray>現金残高:</gray> <white>" + (long)fund.cashBalance + "円</white>", "<gray>あなたの出資額:</gray> <white>" + (long)myContrib + "円</white>", "<gray>あなたの持分評価額:</gray> <gold>" + (long)myStakeValue + "円</gold>"));
        HashMap holdings = this.playerWorldStocks.getOrDefault(fund.id, new HashMap());
        HashMap avgCosts = this.playerWorldStockAvgCost.getOrDefault(fund.id, new HashMap());
        int slot = 9;
        for (Map.Entry h : holdings.entrySet()) {
            if ((Integer)h.getValue() <= 0 || slot >= 17) continue;
            String symbol = (String)h.getKey();
            WorldStockQuote q = this.worldStockQuoteCache.get(symbol);
            double avgCost = avgCosts.getOrDefault(symbol, 0.0);
            ArrayList<Object> hl = new ArrayList<Object>();
            hl.add("<gray>保有:</gray> <white>" + String.valueOf(h.getValue()) + "株</white>");
            hl.add("<gray>平均取得単価:</gray> <white>" + String.format("%.2f", avgCost) + "円</white>");
            if (q != null) {
                hl.add(this.worldStockPriceLine("<gray>現在値:</gray> ", q));
            }
            gui.setItem(slot++, this.createItem(Material.PAPER, "<aqua><bold>" + symbol + "</bold></aqua>", hl.toArray(new String[0])));
        }
        gui.setItem(31, this.createItem(Material.GOLD_INGOT, "<gold><bold>解約する(持分を全額現金化)</bold></gold>", "<gray>あなたの持分をすべて現金化してファンドから抜けます</gray>", "<dark_gray>ファンドの現金残高が足りない場合は失敗します(マネージャーに現物売却を依頼してください)</dark_gray>", "<red>もう一度クリックすると確定します(30秒以内)</red>"));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<dark_green><bold>【" + fund.name + "・運用】</bold></dark_green>", "<gray>ファンド評価額(NAV):</gray> <gold>" + (long)nav + "円</gold>", "<gray>現金残高:</gray> <white>" + (long)fund.cashBalance + "円</white>", "<gray>出資者数:</gray> <white>" + fund.contributions.size() + "人</white>", "<gray>実在する銘柄のティッカーシンボルを検索して、ファンドの資金で売買できます。</gray>", "<dark_gray>取引回数/取引金額の1日あたり上限は、あなた個人の上限とは別にこのファンド専用でカウントされます。</dark_gray>"));
        gui.setItem(49, this.createItem(Material.COMPASS, "<gold><bold>🔍 銘柄を検索して売買</bold></gold>", "<gray>クリックしてティッカーシンボルをチャット入力</gray>"));
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
            lore.add("<gray>保有:</gray> <white>" + qty + "株</white>");
            lore.add("<gray>平均取得単価:</gray> <white>" + String.format("%.2f", avgCost) + "円</white>");
            if (q != null) {
                double value = this.worldStockYenPrice(q) * (double)qty;
                double pnl = value - avgCost * (double)qty;
                lore.add(this.worldStockPriceLine("<gray>現在値:</gray> ", q));
                lore.add("<gray>評価額:</gray> <gold>" + (long)value + "円</gold>");
                lore.add(this.worldStockPnlLine(pnl));
            } else {
                lore.add("<gray>現在値: 取得中... クリックして更新</gray>");
            }
            lore.add("<yellow>クリックして売買画面へ</yellow>");
            ItemStack item = this.createItem(Material.PAPER, "<aqua><bold>" + symbol + "</bold></aqua>", lore.toArray(new String[0]));
            this.setWorldStockTag(item, symbol);
            gui.setItem(slot++, item);
            this.fetchWorldStockQuote(symbol, quote -> {});
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            infoLore.add(this.worldStockPriceLine("<gray>現在値: </gray>", q));
            if (q.currency != null && !q.currency.equalsIgnoreCase("JPY")) {
                infoLore.add("<dark_gray>為替: 1 " + q.currency + " ≈ " + String.format("%,.2f", this.fxRateToJpy(q.currency)) + "円</dark_gray>");
            }
            infoLore.add("<gray>取引所: </gray><white>" + q.exchangeName + "</white>");
        } else {
            infoLore.add("<red>現在値を取得できませんでした</red>");
            infoLore.add("<gray>存在しないティッカーか、通信エラーの可能性があります</gray>");
        }
        if (fund != null) {
            infoLore.add("<gray>ファンド現金残高: </gray><white>" + (long)fund.cashBalance + "円</white>");
        }
        infoLore.add("<gray>保有: </gray><white>" + qty + "株</white>");
        if (qty > 0) {
            infoLore.add("<gray>平均取得単価: </gray><white>" + String.format("%.2f", avgCost) + "円</white>");
        }
        if (this.getVipTier(feeContextUuid) >= 1) {
            String vipLabel = fund != null ? "マネージャーのVIP手数料割引 " : "VIP手数料割引: ";
            infoLore.add("<light_purple>💎 " + vipLabel + String.format("%.0f", (1.0 - this.vipFeeMultiplier(feeContextUuid)) * 100.0) + "%OFF 適用中</light_purple>");
        }
        ItemStack infoItem = this.createItem(Material.NETHER_STAR, "<aqua><bold>" + symbol + "</bold></aqua>", infoLore.toArray(new String[0]));
        this.setWorldStockTag(infoItem, symbol);
        gui.setItem(4, infoItem);
        String string = suffix = fund != null ? "(ファンド資金)" : "";
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
                ItemStack buyItem = this.createItem(Material.PAPER, "<green><bold>" + tq + "株購入" + suffix + "</bold></green>", "<gray>支払額: </gray><gold>" + String.format("%.2f", perBuy * (double)tq) + "円</gold> <dark_gray>(手数料込み)</dark_gray>", "<dark_gray>(現在値 " + String.format("%,.0f", yenPrice) + "円 × " + tq + "株 + 手数料 " + String.format("%.1f", feeRate * 100.0) + "%)</dark_gray>");
                this.setWorldStockTag(buyItem, symbol);
                this.setWorldStockActionTag(buyItem, "buy:" + tq);
                gui.setItem(buySlots[i], buyItem);
            }
            ItemStack buyQtyItem = this.createItem(Material.WRITABLE_BOOK, "<green><bold>📖 数量を指定して購入" + suffix + "</bold></green>", "<gray>クリックしてチャットに株数を入力</gray>");
            this.setWorldStockTag(buyQtyItem, symbol);
            this.setWorldStockActionTag(buyQtyItem, "buyqty");
            gui.setItem(12, buyQtyItem);
            if (qty > 0) {
                for (int i = 0; i < tiers.length; ++i) {
                    int tq = tiers[i];
                    ItemStack sellItem = this.createItem(Material.REDSTONE, "<red><bold>" + tq + "株売却" + suffix + "</bold></red>", "<gray>受取額: </gray><gold>" + String.format("%.2f", perSell * (double)tq) + "円</gold> <dark_gray>(手数料差引後)</dark_gray>", "<dark_gray>(現在値 " + String.format("%,.0f", yenPrice) + "円 × " + tq + "株 - 手数料 " + String.format("%.1f", feeRate * 100.0) + "%)</dark_gray>");
                    this.setWorldStockTag(sellItem, symbol);
                    this.setWorldStockActionTag(sellItem, "sell:" + tq);
                    gui.setItem(sellSlots[i], sellItem);
                }
                ItemStack sellQtyItem = this.createItem(Material.WRITABLE_BOOK, "<red><bold>📖 数量を指定して売却" + suffix + "</bold></red>", "<gray>クリックしてチャットに株数を入力</gray>");
                this.setWorldStockTag(sellQtyItem, symbol);
                this.setWorldStockActionTag(sellQtyItem, "sellqty");
                gui.setItem(14, sellQtyItem);
            } else {
                ItemStack noSell = this.createItem(Material.GRAY_DYE, "<dark_gray>売却</dark_gray>", "<dark_gray>保有していません</dark_gray>");
                this.setWorldStockTag(noSell, symbol);
                gui.setItem(16, noSell);
            }
        }
        ItemStack refreshItem = this.createItem(Material.CLOCK, "<yellow><bold>🔄 価格を再取得</bold></yellow>", "<gray>キャッシュを無視して最新値を取得</gray>");
        this.setWorldStockTag(refreshItem, symbol);
        gui.setItem(22, refreshItem);
        if (fund == null) {
            List myAlerts = this.worldStockAlerts.getOrDefault(actorU, new ArrayList());
            WorldStockAlert existingAlert = myAlerts.stream().filter(a -> a.symbol.equals(symbol)).findFirst().orElse(null);
            ItemStack bellItem = existingAlert != null ? this.createItem(Material.BELL, "<yellow><bold>🔔 値動きアラート設定中</bold></yellow>", "<gray>基準値: </gray><white>" + String.format("%.2f", existingAlert.baselinePrice) + "</white>", "<gray>閾値: </gray><white>±" + String.format("%.1f", existingAlert.thresholdPercent) + "%</white>", "<red>クリックして解除</red>") : this.createItem(Material.BELL, "<yellow><bold>🔔 値動きアラートを設定</bold></yellow>", "<gray>基準値から指定%以上変動したらチャットで通知します</gray>", "<gray>クリックしてチャットに閾値(%)を入力</gray>");
            this.setWorldStockTag(bellItem, symbol);
            gui.setItem(13, bellItem);
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            ItemStack head = this.createItem(Material.PLAYER_HEAD, "<yellow><bold>" + online.getName() + "</bold></yellow>", "<gray>クリックして交換を申し込む</gray>");
            ItemMeta hm = head.getItemMeta();
            if (hm != null) {
                hm.getPersistentDataContainer().set(this.tradeTargetKey, PersistentDataType.STRING, (Object)online.getUniqueId().toString());
                head.setItemMeta(hm);
            }
            gui.setItem(slot++, head);
        }
        if (slot == 0) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<red><bold>他にオンラインのプレイヤーがいません</bold></red>", new String[0]));
        }
        gui.setItem(49, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        inv.setItem(20, this.createItem(Material.GOLD_NUGGET, "<gold><bold>💰 " + nameA + " の提示金額</bold></gold>", "<gray>現在:</gray> <yellow>" + (long)session.moneyOfferedA + "円</yellow>", "<yellow>クリックしてチャットに金額を入力</yellow>"));
        inv.setItem(24, this.createItem(Material.GOLD_NUGGET, "<gold><bold>💰 " + nameB + " の提示金額</bold></gold>", "<gray>現在:</gray> <yellow>" + (long)session.moneyOfferedB + "円</yellow>", "<yellow>クリックしてチャットに金額を入力</yellow>"));
        inv.setItem(22, this.createItem(Material.PAPER, "<white><bold>📄 取引ステータス</bold></white>", "<gray>" + nameA + ":</gray> " + (session.confirmedA ? "<green>確認済み</green>" : "<red>未確認</red>"), "<gray>" + nameB + ":</gray> " + (session.confirmedB ? "<green>確認済み</green>" : "<red>未確認</red>"), "<dark_gray>双方が確認すると取引が成立します</dark_gray>", "<dark_gray>提示内容を変更すると確認は解除されます</dark_gray>"));
        inv.setItem(45, this.createItem(session.confirmedA ? Material.LIME_CONCRETE : Material.LIME_DYE, (String)(session.confirmedA ? "<green><bold>✔ " + nameA + ": 確認済み</bold></green>" : "<yellow><bold>クリックして確認する</bold></yellow>"), "<gray>(" + nameA + "用)</gray>"));
        inv.setItem(53, this.createItem(session.confirmedB ? Material.LIME_CONCRETE : Material.LIME_DYE, (String)(session.confirmedB ? "<green><bold>✔ " + nameB + ": 確認済み</bold></green>" : "<yellow><bold>クリックして確認する</bold></yellow>"), "<gray>(" + nameB + "用)</gray>"));
        inv.setItem(49, this.createItem(Material.BARRIER, "<red><bold>✖ 取引をキャンセル</bold></red>", "<gray>どちらかがクリックすると取引は中止されます</gray>"));
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
        ownRegion = isA ? rawSlot >= 0 && rawSlot <= 17 : rawSlot >= 27 && rawSlot <= 44;
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
            this.addLog(session.playerA, "アイテム交換キャンセル(" + reason + ")");
            if (a.getOpenInventory().getTopInventory().equals((Object)session.inventory)) {
                a.closeInventory();
            }
        }
        if (b != null && b.isOnline()) {
            this.msgKey(b, "trade.cancelled", new String[0]);
            this.addLog(session.playerB, "アイテム交換キャンセル(" + reason + ")");
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
        this.addLog(session.playerA, "アイテム交換成立: " + nameB + " との間で アイテム" + itemsFromA.size() + "点" + (String)(session.moneyOfferedA > 0.0 ? "+" + this.fmtCur(session.moneyOfferedA) : "") + " を渡し、アイテム" + itemsFromB.size() + "点" + (String)(session.moneyOfferedB > 0.0 ? "+" + this.fmtCur(session.moneyOfferedB) : "") + " を受け取った");
        this.addLog(session.playerB, "アイテム交換成立: " + nameA + " との間で アイテム" + itemsFromB.size() + "点" + (String)(session.moneyOfferedB > 0.0 ? "+" + this.fmtCur(session.moneyOfferedB) : "") + " を渡し、アイテム" + itemsFromA.size() + "点" + (String)(session.moneyOfferedA > 0.0 ? "+" + this.fmtCur(session.moneyOfferedA) : "") + " を受け取った");
        this.sendDiscordWebhook("🤝 **" + nameA + "** と **" + nameB + "** の間でアイテム交換が成立しました。(" + nameA + ": アイテム" + itemsFromA.size() + "点" + (String)(session.moneyOfferedA > 0.0 ? "+" + this.fmtCur(session.moneyOfferedA) : "") + " ⇔ " + nameB + ": アイテム" + itemsFromB.size() + "点" + (String)(session.moneyOfferedB > 0.0 ? "+" + this.fmtCur(session.moneyOfferedB) : "") + ")");
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
        gui.setItem(10, this.createItem(Material.CHEST, "<aqua><bold>個人口座・預金</bold></aqua>", new String[0]));
        gui.setItem(12, this.createItem(Material.EMERALD, "<green><bold>融資市場（借りる）</bold></green>", new String[0]));
        gui.setItem(14, this.createItem(Material.GOLD_INGOT, "<yellow><bold>借金返済窓口（返す）</bold></yellow>", new String[0]));
        if (this.bankers.contains(p.getUniqueId())) {
            gui.setItem(16, this.createItem(Material.NETHER_STAR, "<red><bold>頭取コントロールパネル</bold></red>", new String[0]));
        } else {
            gui.setItem(16, this.createItem(Material.ENDER_EYE, "<dark_purple><bold>銀行設立オフィス</bold></dark_purple>", new String[0]));
        }
        gui.setItem(19, this.createItem(Material.DIAMOND, "<aqua><bold>国営公庫 (サーバーローン)</bold></aqua>", new String[0]));
        gui.setItem(21, this.createItem(Material.GOLD_BLOCK, "<gold><bold>高金利・定期預金</bold></gold>", new String[0]));
        gui.setItem(23, this.createItem(Material.ANVIL, "<dark_purple><bold>🏺 質屋・担保付き融資</bold></dark_purple>", "<gray>レアアイテムを担保にお金を借りられます</gray>"));
        gui.setItem(25, this.createItem(Material.GOLDEN_APPLE, "<blue><bold>🛡 生命保険窓口</bold></blue>", "<gray>死亡時のロスに備えて加入できます</gray>"));
        gui.setItem(31, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openMyPageGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tMyPage);
        UUID u = p.getUniqueId();
        gui.setItem(10, this.createItem(Material.EMERALD_BLOCK, "<green><bold>個人信用情報センター</bold></green>", new String[0]));
        gui.setItem(12, this.createItem(Material.FIREWORK_ROCKET, "<gold><bold>🎖 実績・称号コレクション</bold></gold>", new String[0]));
        gui.setItem(14, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>🏆 資産ランキング</bold></yellow>", new String[0]));
        gui.setItem(16, this.createItem(Material.WRITTEN_BOOK, "<aqua><bold>📜 取引履歴を見る</bold></aqua>", new String[0]));
        boolean newsOff = this.newsBroadcastOff.contains(u);
        if (newsOff) {
            gui.setItem(19, this.createItem(Material.NOTE_BLOCK, "<gray><bold>🔕 経済ニュース放送: OFF</bold></gray>", "<gray>クリックでONに切り替え</gray>"));
        } else {
            gui.setItem(19, this.createItem(Material.BELL, "<green><bold>🔔 経済ニュース放送: ON</bold></green>", "<gray>クリックでOFFに切り替え</gray>"));
        }
        gui.setItem(21, this.createItem(Material.GOLD_BLOCK, "<gold><bold>🏛 国庫残高を見る</bold></gold>", "<gray>サーバー全体の税収プールを確認します</gray>"));
        gui.setItem(23, this.createItem(Material.KNOWLEDGE_BOOK, "<light_purple><bold>📘 初心者ガイドを開く</bold></light_purple>", new String[0]));
        long remain = this.hubItemReissueCooldown.getOrDefault(u, 0L) - System.currentTimeMillis();
        if (remain > 0L) {
            gui.setItem(25, this.createItem(Material.BARRIER, "<dark_red><bold>📖 経済手帳の再発行</bold></dark_red>", "<gray>クールダウン中: 残り" + (remain / 1000L + 1L) + "秒</gray>"));
        } else {
            gui.setItem(25, this.createItem(Material.BOOK, "<white><bold>📖 経済手帳の再発行</bold></white>", "<gray>なくした場合、手持ちに再発行します</gray>"));
        }
        gui.setItem(27, this.createItem(Material.SUNFLOWER, "<gold><bold>🎁 国庫へ寄付する</bold></gold>", "<gray>国庫へ任意の金額を寄付できます</gray>"));
        List<InstallmentPlan> myPlans = this.installmentPlans.get(u);
        int planCount = myPlans == null ? 0 : myPlans.size();
        gui.setItem(29, this.createItem(Material.CLOCK, "<light_purple><bold>📅 分割払いの状況</bold></light_purple>", "<gray>現在の分割払いプラン数:</gray> <yellow>" + planCount + "件</yellow>"));
        gui.setItem(31, this.createItem(Material.ENDER_CHEST, "<aqua><bold>📦 受取箱を確認</bold></aqua>", "<gray>オークション落札品などを再受取します</gray>"));
        boolean pwSet = this.webPasswordHash.containsKey(u);
        gui.setItem(33, this.createItem(Material.MAP, "<light_purple><bold>🌐 Web個人ページのリンク</bold></light_purple>", "<gray>クリックでリンクをチャットに表示</gray>"));
        gui.setItem(32, this.createItem(Material.TRIPWIRE_HOOK, "<light_purple><bold>🔑 オフライン操作用パスワード</bold></light_purple>", pwSet ? "<gray>現在: <green>設定済み</green></gray>" : "<gray>現在: <red>未設定</red></gray>", "<gray>クリックでチャット入力に切り替え</gray>"));
        gui.setItem(35, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openInstallmentListGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tInstallmentList);
        UUID u = p.getUniqueId();
        List<InstallmentPlan> plans = this.installmentPlans.get(u);
        long now = System.currentTimeMillis();
        if (plans == null || plans.isEmpty()) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<gray><bold>分割払いのプランはありません</bold></gray>", new String[0]));
        } else {
            int slot = 0;
            for (InstallmentPlan plan : plans) {
                if (slot >= 45) break;
                long remainMs = Math.max(0L, plan.nextDueTime - now);
                long remainH = remainMs / 3600000L;
                long remainM = remainMs / 60000L % 60L;
                String dueStr = new SimpleDateFormat("MM/dd HH:mm").format(new Date(plan.nextDueTime));
                gui.setItem(slot++, this.createItem(Material.PAPER, "<light_purple><bold>" + plan.description + "</bold></light_purple>", "<gray>1回あたりの支払額:</gray> <gold>" + String.format("%.2f", plan.installmentAmount) + "円</gold>", "<gray>残り回数:</gray> <yellow>" + plan.installmentsRemaining + "回</yellow>", "<gray>次回期日:</gray> <white>" + dueStr + "</white> <dark_gray>(あと " + remainH + "h" + remainM + "m)</dark_gray>"));
            }
        }
        gui.setItem(49, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openMainGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)36, (Component)this.tMain);
        gui.setItem(10, this.createItem(Material.CHEST, "<aqua><bold>個人口座・預金</bold></aqua>", new String[0]));
        gui.setItem(12, this.createItem(Material.EMERALD, "<green><bold>融資市場（借りる）</bold></green>", new String[0]));
        gui.setItem(14, this.createItem(Material.GOLD_INGOT, "<yellow><bold>借金返済窓口（返す）</bold></yellow>", new String[0]));
        if (this.bankers.contains(p.getUniqueId())) {
            gui.setItem(16, this.createItem(Material.NETHER_STAR, "<red><bold>頭取コントロールパネル</bold></red>", new String[0]));
        } else {
            gui.setItem(16, this.createItem(Material.ENDER_EYE, "<dark_purple><bold>銀行設立オフィス</bold></dark_purple>", new String[0]));
        }
        gui.setItem(19, this.createItem(Material.DIAMOND, "<aqua><bold>国営公庫 (サーバーローン)</bold></aqua>", new String[0]));
        gui.setItem(21, this.createItem(Material.GOLD_BLOCK, "<gold><bold>高金利・定期預金</bold></gold>", new String[0]));
        gui.setItem(23, this.createItem(Material.EMERALD_BLOCK, "<green><bold>個人信用情報センター</bold></green>", new String[0]));
        gui.setItem(31, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>🏆 資産ランキング</bold></yellow>", "<gray>サーバー内の資産家トップ10を表示</gray>"));
        gui.setItem(27, this.createItem(Material.ANVIL, "<dark_purple><bold>🏺 質屋・担保付き融資</bold></dark_purple>", "<gray>レアアイテムを担保にお金を借りられます</gray>"));
        gui.setItem(29, this.createItem(Material.GOLDEN_APPLE, "<blue><bold>🛡 生命保険窓口</bold></blue>", "<gray>死亡時のロスに備えて加入できます</gray>"));
        gui.setItem(33, this.createItem(Material.FIREWORK_ROCKET, "<gold><bold>🎖 実績・称号コレクション</bold></gold>", "<gray>解放した実績を確認できます</gray>"));
        gui.setItem(28, this.createItem(Material.ITEM_FRAME, "<green><bold>🔨 オークションハウス</bold></green>", "<gray>アイテムを出品・入札できます</gray>"));
        gui.setItem(25, this.createItem(Material.COMPASS, "<blue><bold>📈 世界株式市場</bold></blue>", "<gray>現実の株を検索して売買</gray>"));
        gui.setItem(17, this.createItem(Material.HOPPER, "<green><bold>🌾 資源相場ショップ</bold></green>", "<gray>鉱石・農作物・ドロップ品を売買</gray>"));
        gui.setItem(15, this.createItem(Material.LEATHER_HORSE_ARMOR, "<gold><bold>🧳 巡回商人</bold></gold>", "<gray>現在地を確認する(購入は現地のNPCから)</gray>"));
        gui.setItem(32, this.createItem(Material.FIREWORK_STAR, "<gold><bold>🎟 宝くじ</bold></gold>", "<gray>チケットを購入して定期抽選に参加</gray>"));
        gui.setItem(26, this.createItem(Material.BARREL, "<gold><bold>🏬 レンタル倉庫</bold></gold>", "<gray>家賃を払って専用の収納倉庫を借りられます</gray>"));
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
            gui.setItem(slot++, this.createItem(mat, "<gold><bold>#" + rank + " " + name + "</bold></gold>", "<gray>総資産:</gray> <green>" + (long)((Double)entry.getValue()).doubleValue() + "円</green>"));
            ++rank;
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        gui.setItem(49, this.createItem(Material.NETHER_STAR, "<green><bold>出品するアイテムを選んでください</bold></green>", "<gray>下に表示されているのはあなたの持ち物です。</gray>", "<gray>出品したいアイテムをクリックすると、</gray>", "<gray>そのアイテムが手元から取り出され、開始価格の入力に進みます。</gray>", "<dark_gray>(この画面自体には価格入力欄はありません。次はチャットに入力します)</dark_gray>"));
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            String timeStr = remain > 0L ? "<green>残り " + remain / 60000L + "分</green>" : "<red>期限切れ（まもなく没収）</red>";
            ItemStack display = item.clone();
            ItemMeta dm = display.getItemMeta();
            if (dm != null) {
                dm.displayName(this.mm("<light_purple><bold>【預けている担保】</bold></light_purple>"));
                dm.lore(List.of(this.mm("<gray>借入額:</gray> <aqua>" + (long)loan + "円</aqua>"), this.mm("<gray>返済総額:</gray> <gold>" + (long)totalRepay + "円</gold>"), this.mm("<gray>期限:</gray> " + timeStr)));
                display.setItemMeta(dm);
            }
            gui.setItem(11, display);
            gui.setItem(15, this.createItem(Material.GOLD_BLOCK, "<gold><bold>返済して担保を取り戻す</bold></gold>", "<gray>返済総額:</gray> <gold>" + (long)totalRepay + "円</gold>"));
        } else {
            ItemStack selected = this.collateralSelection.get(u);
            gui.setItem(11, selected == null ? this.createItem(Material.NAME_TAG, "<gray>担保アイテム未選択</gray>", "<gray>クリックしてインベントリから選択</gray>") : selected.clone());
            if (selected != null) {
                double value = this.evaluateItemValue(selected);
                double loan = value * this.cfgCollateralLtv;
                gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>選択したアイテムを担保に借りる</bold></green>", "<gray>アイテム:</gray> <white>" + selected.getType().name() + " ×" + selected.getAmount() + "</white>", "<gray>鑑定評価額:</gray> <gold>" + (long)value + "円</gold>", "<gray>借入可能額(LTV" + (int)(this.cfgCollateralLtv * 100.0) + "%):</gray> <aqua>" + (long)loan + "円</aqua>", "<gray>金利:</gray> <red>" + (int)this.cfgCollateralInterest + "%</red>", "<gray>返済期限:</gray> <yellow>" + this.cfgCollateralDurationMs / 60000L + "分</yellow>"));
            } else {
                gui.setItem(13, this.createItem(Material.ANVIL, "<yellow>担保を選択</yellow>", "<gray>インベントリから担保にするアイテムを選びます。</gray>"));
            }
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            String remainStr = remain > 0L ? remain / 60000L + "分" + remain / 1000L % 60L + "秒" : "まもなく終了";
            boolean endingSoon = remain > 0L && remain <= 60000L;
            String remainColor = endingSoon ? "red" : "yellow";
            ItemStack display = orig.clone();
            ItemMeta dm = display.getItemMeta();
            if (dm != null) {
                ArrayList<Component> lore = new ArrayList<Component>();
                lore.add(this.mm("<gray>出品者:</gray> <white>" + Bukkit.getOfflinePlayer((UUID)seller).getName() + "</white>"));
                lore.add(this.mm("<gray>現在価格:</gray> <gold>" + (long)bid + "円</gold>"));
                if (buyout != null) {
                    lore.add(this.mm("<gray>即決価格:</gray> <light_purple>" + (long)buyout.doubleValue() + "円</light_purple>"));
                }
                lore.add(this.mm("<gray>入札者:</gray> " + (String)(bidder != null ? "<aqua>" + Bukkit.getOfflinePlayer((UUID)bidder).getName() + "</aqua>" : "<gray>まだいません</gray>")));
                lore.add(this.mm("<gray>残り時間:</gray> <" + remainColor + ">" + remainStr + (endingSoon ? " ⚠終了間近" : "") + "</" + remainColor + ">"));
                if (!seller.equals(u)) {
                    lore.add(this.mm("<green>クリックして入札</green>"));
                    if (buyout != null) {
                        lore.add(this.mm("<light_purple>即決価格以上を入力すると即座に購入できます</light_purple>"));
                    }
                } else {
                    lore.add(this.mm("<dark_gray>(自分の出品)</dark_gray>"));
                }
                dm.lore(lore);
                dm.getPersistentDataContainer().set(this.auctionIdKey, PersistentDataType.STRING, (Object)id2.toString());
                display.setItemMeta(dm);
            }
            gui.setItem(slot++, display);
        }
        String sortModeName = switch (sortMode) {
            case 1 -> "価格が安い順";
            case 2 -> "価格が高い順";
            default -> "終了が近い順";
        };
        gui.setItem(45, this.createItem(Material.COMPASS, "<aqua><bold>並び替え</bold></aqua>", "<gray>現在:</gray> <yellow>" + sortModeName + "</yellow>", "<gray>クリックで切り替え</gray>"));
        if (query != null && !query.isEmpty()) {
            gui.setItem(46, this.createItem(Material.NAME_TAG, "<aqua><bold>検索</bold></aqua>", "<gray>現在の検索語:</gray> <white>" + query + "</white>", "<gray>クリックして検索語を再入力</gray>"));
        } else {
            gui.setItem(46, this.createItem(Material.NAME_TAG, "<aqua><bold>検索</bold></aqua>", "<gray>アイテム名で絞り込みます</gray>", "<gray>クリックしてチャットに入力</gray>"));
        }
        if (myOnly) {
            gui.setItem(47, this.createItem(Material.WRITTEN_BOOK, "<green><bold>自分の出品のみ表示: ON</bold></green>", "<gray>クリックですべて表示に戻します</gray>"));
        } else {
            gui.setItem(47, this.createItem(Material.BOOK, "<gray><bold>自分の出品のみ表示: OFF</bold></gray>", "<gray>クリックで自分の出品だけ表示します</gray>"));
        }
        gui.setItem(48, this.createItem(Material.PLAYER_HEAD, "<gold><bold>落札売上ランキング</bold></gold>", "<gray>クリックして表示</gray>"));
        if (query != null && !query.isEmpty()) {
            gui.setItem(50, this.createItem(Material.BARRIER, "<red>検索をクリア</red>", "<gray>現在の検索語:</gray> <white>" + query + "</white>"));
        }
        if (totalPages > 1) {
            if (page > 0) {
                gui.setItem(51, this.createItem(Material.ARROW, "<aqua><bold>◀ 前のページ</bold></aqua>", "<gray>" + page + " / " + totalPages + "ページ</gray>"));
            }
            if (page < totalPages - 1) {
                gui.setItem(52, this.createItem(Material.ARROW, "<aqua><bold>次のページ ▶</bold></aqua>", "<gray>" + (page + 2) + " / " + totalPages + "ページ</gray>"));
            }
        }
        if ((myListings = this.auctionSeller.values().stream().filter(s -> s.equals(u)).count()) >= (long)this.cfgAuctionMaxListingsPerPlayer) {
            gui.setItem(49, this.createItem(Material.BARRIER, "<red>同時出品数の上限です</red>", "<gray>上限: " + this.cfgAuctionMaxListingsPerPlayer + "件</gray>"));
        } else {
            gui.setItem(49, this.createItem(Material.EMERALD, "<green><bold>インベントリから出品する</bold></green>", "<gray>出品期間:</gray> <yellow>" + this.cfgAuctionDurationMs / 60000L + "分</yellow>", "<gray>落札時の手数料:</gray> <red>" + (int)(this.cfgAuctionFeeRate * 100.0) + "%</red>", "<yellow>クリックして出品アイテムを選択</yellow>"));
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        String remainStr = remain > 0L ? remain / 60000L + "分" + remain / 1000L % 60L + "秒" : "まもなく終了";
        ItemStack display = orig.clone();
        ItemMeta dm = display.getItemMeta();
        if (dm != null) {
            ArrayList<Component> lore = new ArrayList<Component>();
            lore.add(this.mm("<gray>開始/現在価格:</gray> <gold>" + (long)bid + "円</gold>"));
            lore.add(this.mm("<gray>入札者:</gray> <gray>まだいません</gray>"));
            lore.add(this.mm("<gray>残り時間:</gray> <yellow>" + remainStr + "</yellow>"));
            dm.lore(lore);
            display.setItemMeta(dm);
        }
        gui.setItem(13, display);
        ItemStack confirmBtn = this.createItem(Material.EMERALD, "<green><bold>はい、取り下げる</bold></green>", "<gray>出品を取り下げてアイテムを受取箱に返却します。</gray>");
        ItemMeta cm = confirmBtn.getItemMeta();
        if (cm != null) {
            cm.getPersistentDataContainer().set(this.auctionIdKey, PersistentDataType.STRING, (Object)auctionId.toString());
            confirmBtn.setItemMeta(cm);
        }
        gui.setItem(11, confirmBtn);
        gui.setItem(15, this.createItem(Material.BARRIER, "<red><bold>戻る</bold></red>", "<gray>出品を取り下げずに一覧へ戻ります。</gray>"));
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
                    Object object = "🥇";
                    break;
                }
                case 2: {
                    Object object = "🥈";
                    break;
                }
                case 3: {
                    Object object = "🥉";
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
            gui.setItem(slot++, this.createItem(Material.PLAYER_HEAD, openTag + "<bold>" + (String)medal + " " + name + "</bold>" + closeTag, "<gray>累計落札売上:</gray> <green>" + (long)((Double)entry.getValue()).doubleValue() + "円</green>"));
            ++rank;
        }
        if (ranking.isEmpty()) {
            gui.setItem(22, this.createItem(Material.BARRIER, "<gray>まだ落札実績がありません</gray>", new String[0]));
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            gui.setItem(13, this.createItem(Material.SHIELD, "<blue><bold>保険加入中</bold></blue>", "<gray>有効期限:</gray> <green>残り " + remain / 60000L + "分</green>", "<gray>死亡時の補填額:</gray> <gold>" + (long)this.cfgInsurancePayout + "円</gold>", "<gray>※死亡すると1回消費されます</gray>"));
        } else {
            gui.setItem(13, this.createItem(Material.GOLDEN_APPLE, "<green><bold>保険に加入する</bold></green>", "<gray>保険料:</gray> <red>" + (long)this.cfgInsurancePremium + "円</red>", "<gray>有効期間:</gray> <yellow>" + this.cfgInsuranceDurationMs / 60000L + "分</yellow>", "<gray>死亡時の補填額:</gray> <gold>" + (long)this.cfgInsurancePayout + "円</gold>"));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAchievementGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tAchievement);
        UUID u = p.getUniqueId();
        HashSet unlocked = this.unlockedAchievements.getOrDefault(u, new HashSet());
        String[][] defs = new String[][]{{"first_loan_repaid", "初めての完済", "プレイヤー間融資を完済した"}, {"first_gov_loan_repaid", "公庫の常連", "国営ローンを完済した"}, {"millionaire", "資産家の証", "総資産が100万円を超えた"}, {"first_fixed_deposit", "堅実な投資家", "定期預金の満期を迎えた"}, {"credit_master", "信用の鑑", "信用スコアが700に到達した"}};
        int slot = 0;
        for (String[] def : defs) {
            boolean has = unlocked.contains(def[0]);
            Material mat = has ? Material.NETHER_STAR : Material.GRAY_DYE;
            String nameColor = has ? "<gold><bold>🎖 " + def[1] + "</bold></gold>" : "<dark_gray>🔒 ???</dark_gray>";
            String desc = has ? "<gray>" + def[2] + "</gray>" : "<dark_gray>未解放</dark_gray>";
            gui.setItem(slot++, this.createItem(mat, nameColor, desc));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openPersonalGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tPersonal);
        UUID u = p.getUniqueId();
        double pocket = econ.getBalance((OfflinePlayer)p);
        double bank = this.personalBank.getOrDefault(u, 0.0);
        gui.setItem(4, this.createItem(Material.BOOK, "<yellow>【現在の財務状況】</yellow>", "<gray>手持ち: </gray><green>" + (long)pocket + "円</green>", "<gray>預金残高: </gray><aqua>" + (long)bank + "円</aqua>"));
        gui.setItem(11, this.createItem(Material.LIME_DYE, "<green>+" + (long)this.cfgDepositStep + "円 預金</green>", new String[0]));
        gui.setItem(12, this.createItem(Material.LIME_GLAZED_TERRACOTTA, "<green><bold>全額預金</bold></green>", new String[0]));
        gui.setItem(14, this.createItem(Material.RED_DYE, "<red>-" + (long)this.cfgDepositStep + "円 引出</red>", new String[0]));
        gui.setItem(15, this.createItem(Material.RED_GLAZED_TERRACOTTA, "<red><bold>全額引出</bold></red>", new String[0]));
        gui.setItem(20, this.createItem(Material.WRITABLE_BOOK, "<gold><bold>金額を指定して預金</bold></gold>", "<gray>クリック後、チャットに金額を入力</gray>"));
        gui.setItem(21, this.createItem(Material.PAPER, "<gold><bold>金額を指定して引出</bold></gold>", "<gray>クリック後、チャットに金額を入力</gray>"));
        gui.setItem(6, this.createItem(Material.WRITTEN_BOOK, "<aqua><bold>📜 取引履歴を見る</bold></aqua>", "<gray>直近の入出金・融資・投資履歴を表示</gray>"));
        boolean newsOff = this.newsBroadcastOff.contains(u);
        if (newsOff) {
            gui.setItem(7, this.createItem(Material.NOTE_BLOCK, "<gray><bold>🔕 経済ニュース放送: OFF</bold></gray>", "<gray>クリックでONに切り替え</gray>"));
        } else {
            gui.setItem(7, this.createItem(Material.BELL, "<green><bold>🔔 経済ニュース放送: ON</bold></green>", "<gray>クリックでOFFに切り替え</gray>"));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private ItemStack buildLoanMarketItem(UUID lenderId, String planData, int slotNum) {
        String[] data = planData.split(":");
        double amount = Double.parseDouble(data[0]);
        double interest = Double.parseDouble(data[1]);
        ItemStack item = this.createItem(Material.PAPER, "<green><bold>融資プラン: " + Bukkit.getOfflinePlayer((UUID)lenderId).getName() + "銀行 (" + slotNum + "枠目)</bold></green>", "<gray>融資額:</gray> <aqua>" + (long)amount + "円</aqua>", "<gray>利息:</gray> <red>" + (long)interest + "%</red>", "<gray>返済総額:</gray> <gold>" + (long)(amount * (1.0 + interest / 100.0)) + "円</gold>", "", "<yellow>クリックして契約成立</yellow>");
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
            gui.setItem(2, this.createItem(Material.SUNFLOWER, "<green>プレイヤー間の借金なし</green>", new String[0]));
        } else {
            String[] data = this.activeDebts.get(u).split(":");
            double pDebt = Double.parseDouble(data[1]);
            gui.setItem(2, this.createItem(Material.ANVIL, "<red>【対プレイヤー債務】</red>", "<gray>債権者:</gray> " + Bukkit.getOfflinePlayer((UUID)UUID.fromString(data[0])).getName(), "<gray>残り:</gray> <red>" + (long)pDebt + "円</red>"));
            gui.setItem(10, this.createItem(Material.GOLD_NUGGET, "<yellow>1,000円 返済</yellow>", new String[0]));
            gui.setItem(11, this.createItem(Material.GOLD_BLOCK, "<gold><bold>全額返済</bold></gold>", new String[0]));
            UUID guarantorId = this.loanGuarantor.get(u);
            if (guarantorId != null) {
                gui.setItem(13, this.createItem(Material.SHIELD, "<aqua><bold>保証人設定済み</bold></aqua>", "<gray>保証人:</gray> " + Bukkit.getOfflinePlayer((UUID)guarantorId).getName()));
            } else {
                gui.setItem(13, this.createItem(Material.IRON_HORSE_ARMOR, "<gray><bold>保証人なし</bold></gray>", "<gray>/meco guarantor request <プレイヤー></gray>", "<gray>で保証人を依頼できます</gray>"));
            }
        }
        double gDebt = this.govDebt.getOrDefault(u, 0.0);
        if (gDebt <= 0.0) {
            gui.setItem(6, this.createItem(Material.SUNFLOWER, "<green>国営公庫からの借金なし</green>", new String[0]));
        } else {
            gui.setItem(6, this.createItem(Material.IRON_BARS, "<dark_red>【国営公庫債務】</dark_red>", "<gray>残り:</gray> <red>" + (long)gDebt + "円</red>"));
            gui.setItem(15, this.createItem(Material.GOLD_NUGGET, "<yellow>1,000円 返済</yellow>", new String[0]));
            gui.setItem(16, this.createItem(Material.GOLD_BLOCK, "<gold><bold>全額返済</bold></gold>", new String[0]));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openEstablishGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tEstablish);
        gui.setItem(13, this.createItem(Material.DIAMOND, "<light_purple><bold>【銀行を設立する】</bold></light_purple>", "<gray>設立費用:</gray> <red>" + (long)this.cfgBankEstablishCost + "円</red>"));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openBankerGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tBanker);
        UUID u = p.getUniqueId();
        double capital = this.bankCapital.getOrDefault(u, 0.0);
        gui.setItem(4, this.createItem(Material.BEACON, "<red>【銀行ステータス】</red>", "<gray>資本金プール:</gray> <gold>" + (long)capital + "円</gold>"));
        gui.setItem(10, this.createItem(Material.DIAMOND, "<aqua>資本金へ +10,000円</aqua>", new String[0]));
        gui.setItem(11, this.createItem(Material.COAL, "<gray>資本金から -10,000円</gray>", new String[0]));
        if (this.publishedLoans.containsKey(u)) {
            gui.setItem(15, this.createItem(Material.BARRIER, "<red>プラン(1枠目)を取り下げる</red>", new String[0]));
        } else {
            gui.setItem(15, this.createItem(Material.WRITABLE_BOOK, "<gold><bold>融資プラン(1枠目)を設計</bold></gold>", new String[0]));
        }
        if (this.publishedLoans2.containsKey(u)) {
            gui.setItem(16, this.createItem(Material.BARRIER, "<red>プラン(2枠目)を取り下げる</red>", new String[0]));
        } else {
            gui.setItem(16, this.createItem(Material.BOOK, "<aqua><bold>融資プラン(2枠目)を設計</bold></aqua>", new String[0]));
        }
        if (this.publishedLoans3.containsKey(u)) {
            gui.setItem(17, this.createItem(Material.BARRIER, "<red>プラン(3枠目)を取り下げる</red>", new String[0]));
        } else {
            gui.setItem(17, this.createItem(Material.KNOWLEDGE_BOOK, "<light_purple><bold>融資プラン(3枠目)を設計</bold></light_purple>", new String[0]));
        }
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openPlanGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tPlan);
        UUID u = p.getUniqueId();
        double amount = this.tempLoanAmount.getOrDefault(u, 1000.0);
        double interest = this.tempInterestRate.getOrDefault(u, 10.0);
        int slot = this.planDesignSlot.getOrDefault(u, 1);
        gui.setItem(4, this.createItem(Material.OAK_SIGN, "<gold>【設計中: " + slot + "枠目】</gold>", "<gray>融資額:</gray> <aqua>" + (long)amount + "円</aqua>", "<gray>利息:</gray> <red>" + (long)interest + "%</red>"));
        gui.setItem(10, this.createItem(Material.SLIME_BALL, "<green>+1,000円</green>", new String[0]));
        gui.setItem(11, this.createItem(Material.SLIME_BLOCK, "<green>+10,000円</green>", new String[0]));
        gui.setItem(12, this.createItem(Material.REDSTONE, "<red>額リセット</red>", new String[0]));
        gui.setItem(14, this.createItem(Material.SUGAR, "<yellow>利息 +5%</yellow>", new String[0]));
        gui.setItem(15, this.createItem(Material.GLOWSTONE_DUST, "<yellow>利息 +10%</yellow>", new String[0]));
        gui.setItem(16, this.createItem(Material.REDSTONE, "<red>利息リセット</red>", new String[0]));
        gui.setItem(18, this.createItem(Material.WRITABLE_BOOK, "<gold><bold>融資額を直接入力</bold></gold>", "<gray>クリック後、チャットに金額を入力</gray>"));
        gui.setItem(19, this.createItem(Material.PAPER, "<gold><bold>利息(%)を直接入力</bold></gold>", "<gray>クリック後、チャットに数値を入力</gray>"));
        gui.setItem(22, this.createItem(Material.NETHER_STAR, "<gold><bold>市場に公開する</bold></gold>", new String[0]));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        ArrayList<String> lores = new ArrayList<String>();
        lores.add("<gray>適用金利:</gray> <red>" + interest + "%</red>");
        lores.add("<gray>借入上限(信用スコア連動):</gray> <gold>" + (long)cap + "円</gold>");
        lores.add("<gray>現在の債務残高:</gray> <yellow>" + (long)currentDebt + "円</yellow>");
        if (this.govDebtDueTime.containsKey(u) && currentDebt > 0.0) {
            long remain = this.govDebtDueTime.get(u) - System.currentTimeMillis();
            lores.add("<gray>返済期限:</gray> " + (remain > 0L ? "<green>残り " + remain / 60000L + "分</green>" : "<red>期限超過（延滞金加算中）</red>"));
        }
        gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>+" + (long)this.cfgGovLoanAmount + "円 国営ローンを組む</bold></green>", lores.toArray(new String[0])));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            case 3 -> "プラチナ";
            case 2 -> "ゴールド";
            case 1 -> "シルバー";
            default -> "通常";
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
        gui.setItem(4, this.createItem(Material.CLOCK, "<gold>【定期預金 1枠目】</gold>", "<gray>現在の預金額:</gray> <yellow>" + (long)amount + "円</yellow>", "<gray>状態:</gray> " + timeStr));
        gui.setItem(11, this.createItem(Material.GOLD_INGOT, "<yellow>手持ちから " + (long)this.cfgFixedDepositAmount + "円 預ける</yellow>", new String[0]));
        gui.setItem(15, this.createItem(Material.GOLD_BLOCK, "<gold><bold>満期引き出し (+" + (int)(this.cfgFixedDepositRate * 100.0) + "%)</bold></gold>", new String[0]));
        double amount2 = this.fixedDeposit2.getOrDefault(u, 0.0);
        long diff2 = this.fixedDepositUnlockTime2.getOrDefault(u, 0L) - System.currentTimeMillis();
        String timeStr2 = this.fixedDepositStatusText(amount2, diff2);
        gui.setItem(3, this.createItem(Material.CLOCK, "<aqua>【定期預金 2枠目】</aqua>", "<gray>現在の預金額:</gray> <yellow>" + (long)amount2 + "円</yellow>", "<gray>状態:</gray> " + timeStr2));
        gui.setItem(12, this.createItem(Material.IRON_INGOT, "<yellow>2枠目へ " + (long)this.cfgFixedDepositAmount + "円 預ける</yellow>", new String[0]));
        gui.setItem(16, this.createItem(Material.IRON_BLOCK, "<aqua><bold>2枠目 満期引き出し</bold></aqua>", new String[0]));
        double amount3 = this.fixedDeposit3.getOrDefault(u, 0.0);
        long diff3 = this.fixedDepositUnlockTime3.getOrDefault(u, 0L) - System.currentTimeMillis();
        String timeStr3 = this.fixedDepositStatusText(amount3, diff3);
        gui.setItem(5, this.createItem(Material.CLOCK, "<light_purple>【定期預金 3枠目】</light_purple>", "<gray>現在の預金額:</gray> <yellow>" + (long)amount3 + "円</yellow>", "<gray>状態:</gray> " + timeStr3));
        gui.setItem(13, this.createItem(Material.EMERALD, "<yellow>3枠目へ " + (long)this.cfgFixedDepositAmount + "円 預ける</yellow>", new String[0]));
        gui.setItem(17, this.createItem(Material.EMERALD_BLOCK, "<light_purple><bold>3枠目 満期引き出し</bold></light_purple>", new String[0]));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openCreditGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tCredit);
        int score = this.getScore(p.getUniqueId());
        gui.setItem(13, this.createItem(Material.NAME_TAG, "<green><bold>【信用情報】</bold></green>", "<gray>スコア:</gray> <aqua>" + score + " / 800</aqua>"));
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openWorldStockGUI(Player p) {
        UUID u = p.getUniqueId();
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tWorldStock);
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<blue><bold>【現実世界の株式市場】</bold></blue>", "<gray>実在する銘柄のティッカーシンボルを検索して売買できます。</gray>", "<gray>価格は現実の株価を、ライブの為替レートで円換算して扱います(1株単位)。</gray>", "<gray>例: AAPL(Apple) TSLA(Tesla) 7203.T(トヨタ) ^GSPC(S&P500)</gray>", "<dark_gray>同一銘柄の価格は約" + (long)this.cfgWorldStockCacheSeconds + "秒キャッシュされます(取引所によっては数分遅延あり)。</dark_gray>", "<gray>保有中の銘柄には年率" + this.cfgWorldStockDividendAnnualRate * 100.0 + "%相当の配当金が定期的に支払われます。</gray>"));
        gui.setItem(49, this.createItem(Material.COMPASS, "<gold><bold>🔍 銘柄を検索して売買</bold></gold>", "<gray>クリックしてティッカーシンボルをチャット入力</gray>"));
        gui.setItem(45, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>📊 保有株ランキング</bold></yellow>", "<gray>サーバー内の保有株評価額ランキングを表示</gray>"));
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
            lore.add("<gray>保有: </gray><white>" + qty + "株</white>");
            lore.add("<gray>平均取得単価: </gray><white>" + String.format("%.2f", avgCost) + "円</white>");
            if (q != null) {
                double value = this.worldStockYenPrice(q) * (double)qty;
                double pnl = value - avgCost * (double)qty;
                lore.add(this.worldStockPriceLine("<gray>現在値: </gray>", q));
                lore.add("<gray>評価額: </gray><gold>" + (long)value + "円</gold>");
                lore.add(this.worldStockPnlLine(pnl));
            } else {
                lore.add("<gray>現在値: 取得中... クリックして更新</gray>");
            }
            lore.add("<yellow>クリックして売買画面へ</yellow>");
            ItemStack item = this.createItem(Material.PAPER, "<aqua><bold>" + symbol + "</bold></aqua>", lore.toArray(new String[0]));
            this.setWorldStockTag(item, symbol);
            gui.setItem(slot++, item);
            this.fetchWorldStockQuote(symbol, quote -> {});
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
                this.addLog(fund.manager, "共同投資ファンド「" + fund.name + "」の世界株配当金 +" + (long)totalDividend + "円(ファンド現金へ計上)");
                continue;
            }
            econ.depositPlayer(Bukkit.getOfflinePlayer((UUID)owner), totalDividend);
            this.addLog(owner, "世界株の配当金 +" + this.fmtCur(totalDividend));
            Player online = Bukkit.getPlayer((UUID)owner);
            if (online == null) continue;
            this.msgKey(online, "worldstock.dividend-paid", "amount", String.format("%.2f", totalDividend));
        }
    }

    private void openWorldStockLeaderboardGUI(Player p) {
        int symbols;
        Inventory gui = Bukkit.createInventory(null, (int)54, (Component)this.tWorldStockLeaderboard);
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<blue><bold>【保有株評価額ランキング】</bold></blue>", "<gray>保有中の銘柄の評価額合計で順位付けしています。</gray>", "<dark_gray>価格キャッシュが無い銘柄は平均取得単価で概算します。</dark_gray>"));
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
            gui.setItem(slot++, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>#" + rank + " " + name + "</bold></yellow>", "<gray>評価額合計: </gray><gold>" + (long)((Double)entry.getValue()).doubleValue() + "円</gold>", "<gray>保有銘柄数: </gray><white>" + symbols + "</white>"));
            ++rank;
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private String resourceDisplayName(Material mat) {
        return switch (mat) {
            case COAL -> "石炭";
            case IRON_INGOT -> "鉄インゴット";
            case GOLD_INGOT -> "金インゴット";
            case COPPER_INGOT -> "銅インゴット";
            case REDSTONE -> "レッドストーン";
            case LAPIS_LAZULI -> "ラピスラズリ";
            case DIAMOND -> "ダイヤモンド";
            case EMERALD -> "エメラルド";
            case NETHERITE_SCRAP -> "ネザライトの欠片";
            case WHEAT -> "小麦";
            case CARROT -> "ニンジン";
            case POTATO -> "ジャガイモ";
            case BEETROOT -> "ビートルート";
            case MELON_SLICE -> "スイカ";
            case PUMPKIN -> "カボチャ";
            case SUGAR_CANE -> "サトウキビ";
            case NETHER_WART -> "ネザーウォート";
            case COCOA_BEANS -> "カカオ豆";
            case ROTTEN_FLESH -> "腐った肉";
            case BONE -> "骨";
            case STRING -> "糸";
            case GUNPOWDER -> "火薬";
            case SPIDER_EYE -> "クモの目";
            case SLIME_BALL -> "スライムボール";
            case ENDER_PEARL -> "エンダーパール";
            case BLAZE_ROD -> "ブレイズロッド";
            case GHAST_TEAR -> "ガストの涙";
            case OAK_LOG -> "オークの原木";
            case SPRUCE_LOG -> "トウヒの原木";
            case BIRCH_LOG -> "シラカバの原木";
            case JUNGLE_LOG -> "ジャングルの原木";
            case ACACIA_LOG -> "アカシアの原木";
            case DARK_OAK_LOG -> "ダークオークの原木";
            case MANGROVE_LOG -> "マングローブの原木";
            case CHERRY_LOG -> "サクラの原木";
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
            return "<green>▲</green>";
        }
        if (ratio < 0.999) {
            return "<red>▼</red>";
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
        gui.setItem(4, this.createItem(Material.EMERALD, "<green><bold>【資源相場ショップ】</bold></green>", "<gray>鉱石・農作物・モブドロップ・木材をサーバーに即時売買できます。</gray>", "<gray>価格は取引量に応じて上下し、時間経過で基準価格へ緩やかに戻ります。</gray>", "<dark_gray>売りすぎると価格が下がるため、売却益は自然と頭打ちになります。</dark_gray>"));
        gui.setItem(10, this.createItem(Material.IRON_PICKAXE, "<white><bold>⛏ 鉱石</bold></white>", "<gray>石炭・鉄・金・ダイヤモンドなど</gray>"));
        gui.setItem(12, this.createItem(Material.WHEAT, "<yellow><bold>🌾 農作物</bold></yellow>", "<gray>小麦・ニンジン・ジャガイモなど</gray>"));
        gui.setItem(14, this.createItem(Material.BONE, "<white><bold>🦴 モブドロップ</bold></white>", "<gray>骨・糸・エンダーパールなど</gray>"));
        gui.setItem(16, this.createItem(Material.OAK_LOG, "<gold><bold>🪵 木材</bold></gold>", "<gray>各種原木</gray>"));
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openResourceShopListGUI(Player p, String category) {
        UUID u = p.getUniqueId();
        this.resourceShopViewCategory.put(u, category);
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tResourceShopList);
        String categoryName = switch (category) {
            case "ore" -> "鉱石";
            case "crop" -> "農作物";
            case "drop" -> "モブドロップ";
            case "wood" -> "木材";
            default -> category;
        };
        gui.setItem(4, this.createItem(Material.PAPER, "<green><bold>【" + categoryName + "】</bold></green>", "<gray>クリックして各品目の売買画面を開きます。</gray>"));
        List mats = RESOURCE_CATEGORIES.getOrDefault(category, List.of());
        int slot = 9;
        for (Material mat : mats) {
            if (slot >= 18) break;
            double base = RESOURCE_BASE_PRICES.getOrDefault(mat, 0.0);
            double price = this.getResourceEffectivePrice(mat);
            ItemStack item = this.createItem(mat, "<aqua><bold>" + this.resourceDisplayName(mat) + "</bold></aqua>", "<gray>現在価格: </gray><gold>" + String.format("%.2f", price) + "円/個</gold> " + this.resourceTrendIndicator(mat), "<gray>基準価格: </gray><white>" + String.format("%.2f", base) + "円/個</white>", "<yellow>クリックして売買画面へ</yellow>");
            this.setResourceMaterialTag(item, mat);
            gui.setItem(slot++, item);
        }
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        ArrayList<String> infoLore = new ArrayList<String>(List.of("<gray>現在価格: </gray><gold>" + String.format("%.2f", price) + "円/個</gold> " + this.resourceTrendIndicator(mat), "<gray>基準価格: </gray><white>" + String.format("%.2f", base) + "円/個</white>", "<gray>所持数: </gray><white>" + holding + "個</white>"));
        if (personalMult > 1.01) {
            infoLore.add("<red>あなたは連続購入中のため、買値が割増(×" + String.format("%.2f", personalMult) + ")になっています</red>");
        }
        ItemStack infoItem = this.createItem(mat, "<aqua><bold>" + this.resourceDisplayName(mat) + "</bold></aqua>", infoLore.toArray(new String[0]));
        gui.setItem(4, infoItem);
        int[] qtys = new int[]{1, 16, 64};
        int[] sellSlots = new int[]{10, 11, 12};
        int[] buySlots = new int[]{14, 15, 16};
        for (int i = 0; i < qtys.length; ++i) {
            int qty = qtys[i];
            double sellTotal = price * (double)qty;
            ItemStack sellItem = this.createItem(mat, "<red><bold>売る x" + qty + "</bold></red>", "<gray>受取額: </gray><gold>" + String.format("%.2f", sellTotal) + "円</gold>", "<dark_gray>(単価 " + String.format("%.2f", price) + "円 × " + qty + "個)</dark_gray>");
            this.setResourceMaterialTag(sellItem, mat);
            this.setResourceActionTag(sellItem, "sell" + qty);
            gui.setItem(sellSlots[i], sellItem);
            double buyTotal = buyPrice * (double)qty;
            ItemStack buyItem = this.createItem(mat, "<green><bold>買う x" + qty + "</bold></green>", "<gray>支払額: </gray><gold>" + String.format("%.2f", buyTotal) + "円</gold>", "<dark_gray>(単価 " + String.format("%.2f", buyPrice) + "円 × " + qty + "個)</dark_gray>");
            this.setResourceMaterialTag(buyItem, mat);
            this.setResourceActionTag(buyItem, "buy" + qty);
            gui.setItem(buySlots[i], buyItem);
        }
        ItemStack sellQtyItem = this.createItem(Material.WRITABLE_BOOK, "<red><bold>📖 数量指定で売る</bold></red>", "<gray>クリックしてチャットに個数を入力</gray>");
        this.setResourceMaterialTag(sellQtyItem, mat);
        this.setResourceActionTag(sellQtyItem, "sellqty");
        gui.setItem(20, sellQtyItem);
        ItemStack buyQtyItem = this.createItem(Material.WRITABLE_BOOK, "<green><bold>📖 数量指定で買う</bold></green>", "<gray>クリックしてチャットに個数を入力</gray>");
        this.setResourceMaterialTag(buyQtyItem, mat);
        this.setResourceActionTag(buyQtyItem, "buyqty");
        gui.setItem(24, buyQtyItem);
        gui.setItem(26, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        gui.setItem(4, this.createItem(Material.NETHER_STAR, "<gold><bold>【定期抽選・宝くじ】</bold></gold>", "<gray>賞金プール:</gray> <gold>" + (long)this.lotteryPool + "円</gold>", "<gray>チケット価格:</gray> <white>" + (long)this.cfgLotteryTicketPrice + "円/枚</white>", "<gray>あなたの購入枚数:</gray> <yellow>" + myTickets + "枚</yellow>", "<gray>現在の当せん確率:</gray> <aqua>" + String.format("%.2f", winChance) + "%</aqua>", "<gray>次回抽選まで:</gray> <white>" + this.formatHoursMinutes(remainMs, false) + "</white>", "<dark_gray>前回の当せん者:</dark_gray> <light_purple>" + this.lastLotteryWinnerName + "</light_purple>" + (String)(this.lastLotteryWinnerAmount > 0.0 ? " <dark_gray>(" + (long)this.lastLotteryWinnerAmount + "円)</dark_gray>" : ""), "<dark_gray>賞金プールはチケット売上のみが原資です(国庫からの補填はありません)。</dark_gray>"));
        double price = this.cfgLotteryTicketPrice;
        gui.setItem(11, this.createItem(Material.PAPER, "<green><bold>🎟 1枚購入</bold></green>", "<gray>支払額:</gray> <gold>" + (long)(price * 1.0) + "円</gold>"));
        gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>🎟 5枚購入</bold></green>", "<gray>支払額:</gray> <gold>" + (long)(price * 5.0) + "円</gold>"));
        gui.setItem(15, this.createItem(Material.DIAMOND, "<green><bold>🎟 10枚購入</bold></green>", "<gray>支払額:</gray> <gold>" + (long)(price * 10.0) + "円</gold>"));
        gui.setItem(20, this.createItem(Material.WRITABLE_BOOK, "<aqua><bold>📖 数量を指定して購入</bold></aqua>", "<gray>クリックしてチャットに枚数を入力</gray>"));
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openStorageRentGUI(Player p) {
        UUID u = p.getUniqueId();
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tStorageRent);
        int clampedSize = Math.max(9, this.cfgStorageSize / 9 * 9);
        if (!this.storageRentDueTime.containsKey(u)) {
            gui.setItem(4, this.createItem(Material.BOOK, "<gold><bold>【レンタル倉庫】</bold></gold>", "<gray>家賃:</gray> <gold>" + (long)this.cfgStorageRentAmount + "円</gold> / " + this.cfgStorageRentIntervalHours + "時間ごと", "<gray>収納スロット数:</gray> <white>" + clampedSize + "</white>", "<gray>個人口座・エンダーチェストとは別の専用収納が使えます。</gray>", "<red>⚠ 家賃の支払いを1回でも滞納すると契約は失効し、</red>", "<red>⚠ 倉庫の中身は全て没収されますのでご注意ください。</red>"));
            gui.setItem(13, this.createItem(Material.EMERALD, "<green><bold>借りる</bold></green>", "<gray>今すぐ初回家賃 " + (long)this.cfgStorageRentAmount + "円 を支払って契約します。</gray>"));
        } else {
            long due = this.storageRentDueTime.get(u);
            long remainMs = Math.max(0L, due - System.currentTimeMillis());
            gui.setItem(4, this.createItem(Material.BOOK, "<gold><bold>【レンタル倉庫】契約中</bold></gold>", "<gray>家賃:</gray> <gold>" + (long)this.cfgStorageRentAmount + "円</gold> / " + this.cfgStorageRentIntervalHours + "時間ごと", "<gray>次回家賃の支払いまで:</gray> <white>" + this.formatHoursMinutes(remainMs, false) + "</white>", "<red>⚠ 支払いに失敗すると倉庫の中身は全て没収されます。</red>"));
            gui.setItem(11, this.createItem(Material.CHEST, "<aqua><bold>倉庫を開く</bold></aqua>", "<gray>専用のレンタル倉庫を開きます。</gray>"));
            gui.setItem(15, this.createItem(Material.DIAMOND, "<light_purple><bold>早めに更新する(次回期日を延長)</bold></light_purple>", "<gray>" + (long)this.cfgStorageRentAmount + "円を支払い、次回期日をそこから" + this.cfgStorageRentIntervalHours + "時間延長します。</gray>"));
        }
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            this.addLog(u, "レンタル倉庫: 契約開始 -" + this.fmtCur(this.cfgStorageRentAmount));
        } else {
            long current = this.storageRentDueTime.get(u);
            this.storageRentDueTime.put(u, current + (long)this.cfgStorageRentIntervalHours * 3600000L);
            if (online != null) {
                this.msgKey(online, "storage.rent-paid", "amount", String.valueOf((long)this.cfgStorageRentAmount));
            }
            this.addLog(u, "レンタル倉庫: 早期更新 -" + this.fmtCur(this.cfgStorageRentAmount));
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
                this.addLog(u, "レンタル倉庫: 家賃自動引落 -" + this.fmtCur(this.cfgStorageRentAmount));
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
            this.addLog(u, "レンタル倉庫: 家賃滞納により契約失効・中身没収");
            if (online == null || !online.isOnline()) continue;
            this.msgKey(online, "storage.forfeited", new String[0]);
        }
    }

    private String merchantDisplayName(Material mat) {
        return switch (mat) {
            case ELYTRA -> "エリトラ";
            case TOTEM_OF_UNDYING -> "不死のトーテム";
            case NETHERITE_INGOT -> "ネザライトインゴット";
            case NETHERITE_BLOCK -> "ネザライトブロック";
            case ENCHANTED_GOLDEN_APPLE -> "エンチャントされた金のリンゴ";
            case SADDLE -> "サドル";
            case NAME_TAG -> "名札";
            case SHULKER_BOX -> "シュルカーボックス";
            case TRIDENT -> "トライデント";
            case NETHER_STAR -> "ネザースター";
            case DRAGON_EGG -> "ドラゴンの卵";
            case BEACON -> "ビーコン";
            case DIAMOND_BLOCK -> "ダイヤモンドブロック";
            case EMERALD_BLOCK -> "エメラルドブロック";
            case MUSIC_DISC_PIGSTEP -> "レコード(Pigstep)";
            case MUSIC_DISC_OTHERSIDE -> "レコード(Otherside)";
            case HEART_OF_THE_SEA -> "海の心";
            case CONDUIT -> "コンジット";
            case END_CRYSTAL -> "エンドクリスタル";
            case GOLDEN_CARROT -> "金のニンジン";
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
            entity.customName(this.mm("<gold><bold>旅の行商人</bold></gold>"));
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
        this.broadcastNews("<gold><bold>【埋蔵金】</bold> " + this.treasureWorldName + " (" + this.treasureX + ", " + this.treasureY + ", " + this.treasureZ + ") 付近に埋蔵金の入ったチェストが出現しました！最初に発見した者が総取りです。</gold>");
    }

    private void openTravelingMerchantGUI(Player p) {
        this.ensureMerchantDealsCurrent();
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tTravelingMerchant);
        long remainMs = Math.max(0L, this.merchantRefreshAt - System.currentTimeMillis());
        gui.setItem(4, this.createItem(Material.EMERALD, "<green><bold>【巡回商人】</bold></green>", "<gray>日替わりで数量限定・割引価格の品を販売しています。</gray>", "<gray>次回の更新まで: </gray><gold>" + this.formatHoursMinutes(remainMs, false) + "</gold>"));
        int slot = 9;
        for (MerchantDeal deal : this.merchantDeals) {
            if (slot >= 18) break;
            if (deal.stockRemaining <= 0) {
                gui.setItem(slot, this.createItem(Material.GRAY_DYE, "<gray><bold>" + this.merchantDisplayName(deal.material) + "</bold></gray>", "<dark_gray>売り切れ</dark_gray>"));
            } else {
                double discounted = deal.normalPrice * (1.0 - deal.discountPercent / 100.0);
                ItemStack item = this.createItem(deal.material, "<aqua><bold>" + this.merchantDisplayName(deal.material) + "</bold></aqua>", "<gray>通常価格: </gray><strikethrough><white>" + (long)deal.normalPrice + "円</white></strikethrough>", "<gray>割引価格: </gray><gold><bold>" + (long)discounted + "円</bold></gold>", "<green>割引率: " + String.format("%.0f", deal.discountPercent) + "%OFF</green>", "<gray>在庫: </gray><white>" + deal.stockRemaining + " / " + deal.stockTotal + "</white>", "<yellow>クリックして購入</yellow>");
                this.setMerchantDealTag(item, deal.id);
                gui.setItem(slot, item);
            }
            ++slot;
        }
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            this.addLog(u, "巡回商人: " + this.merchantDisplayName(deal.material) + " を分割払いで購入 (初回" + (long)plan.installmentAmount + "円)");
            this.sendDiscordWebhook("🧳 **" + p.getName() + "** が巡回商人から **" + this.merchantDisplayName(deal.material) + "** を分割払いで購入しました。（初回 -" + (long)plan.installmentAmount + "円）");
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
        this.addLog(u, "巡回商人: " + this.merchantDisplayName(deal.material) + " を購入 -" + this.fmtCur(price));
        this.sendDiscordWebhook("🧳 **" + p.getName() + "** が巡回商人から **" + this.merchantDisplayName(deal.material) + "** を購入しました。（-" + (long)price + "円）");
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
        lore.add("<gray>現在のティア: </gray><white>" + this.vipTierName(tier) + "</white>");
        lore.add("<gray>信用スコア: </gray><white>" + score + " / 800</white>");
        if (tier >= 3) {
            lore.add("<green>最高ティアです</green>");
        } else {
            int need = tier == 0 ? this.cfgVipSilverScore : (tier == 1 ? this.cfgVipGoldScore : this.cfgVipPlatinumScore);
            lore.add("<gray>次のティア(" + this.vipTierName(tier + 1) + ")まで: </gray><gold>あと " + Math.max(0, need - score) + "</gold> <dark_gray>(必要スコア " + need + ")</dark_gray>");
        }
        lore.add("");
        if (tier >= 1) {
            lore.add("<light_purple>【適用中の特典】</light_purple>");
            lore.add("<gray>・手数料割引: </gray><white>" + String.format("%.0f", (1.0 - this.vipFeeMultiplier(u)) * 100.0) + "%OFF</white> <dark_gray>(世界株/ファンド/オークション)</dark_gray>");
            lore.add("<gray>・日次VIP手当: </gray><white>" + (long)this.vipStipendAmount(tier) + "円 / 24時間</white>");
            lore.add("<gray>・世界株の1日取引枠: </gray><white>x" + String.format("%.1f", this.vipTradeLimitMultiplier(u)) + "</white>");
            lore.add("<gray>・VIP限定ショップの利用</gray>");
        } else {
            lore.add("<yellow>信用スコアが " + this.cfgVipSilverScore + " 以上でシルバー会員になり、特典が解放されます。</yellow>");
            lore.add("<red><bold>現在は VIP対象外 です。</bold></red>");
        }
        gui.setItem(4, this.createItem(Material.DIAMOND, "<light_purple><bold>💎 VIP会員ステータス</bold></light_purple>", lore.toArray(new String[0])));
        if (tier == 0) {
            gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
                gui.setItem(2, this.createItem(Material.GOLD_INGOT, "<gold><bold>日次VIP手当を受け取る</bold></gold>", "<gray>受取額: </gray><gold>" + (long)amount + "円</gold>", "<dark_gray>国庫財源。24時間ごとに1回受け取れます。</dark_gray>", "<yellow>クリックして受け取る</yellow>"));
            } else {
                long remain = cooldownMs - (now - last);
                gui.setItem(2, this.createItem(Material.CLOCK, "<gray><bold>日次VIP手当(受取済み)</bold></gray>", "<gray>次回受取まで: </gray><white>" + this.formatHoursMinutes(remain, true) + "</white>"));
            }
        }
        this.ensureVipShopCurrent();
        long remainMs = Math.max(0L, this.vipShopRefreshAt - System.currentTimeMillis());
        gui.setItem(6, this.createItem(Material.NETHER_STAR, "<light_purple><bold>【VIP限定ショップ】</bold></light_purple>", "<gray>VIP会員だけが買える割引ラインナップ。数量限定です。</gray>", "<gray>次回更新まで: </gray><gold>" + this.formatHoursMinutes(remainMs, false) + "</gold>"));
        int slot = 9;
        for (VipDeal deal : this.vipShopDeals) {
            if (slot >= 18) break;
            if (deal.stockRemaining <= 0) {
                gui.setItem(slot, this.createItem(Material.GRAY_DYE, "<gray><bold>" + this.merchantDisplayName(deal.material) + "</bold></gray>", "<dark_gray>売り切れ</dark_gray>"));
            } else {
                double discounted = deal.normalPrice * (1.0 - deal.discountPercent / 100.0);
                ItemStack card = this.createItem(deal.material, "<light_purple><bold>" + this.merchantDisplayName(deal.material) + "</bold></light_purple>", "<gray>通常価格: </gray><strikethrough><white>" + (long)deal.normalPrice + "円</white></strikethrough>", "<gray>VIP割引価格: </gray><gold><bold>" + (long)discounted + "円</bold></gold>", "<green>割引率: " + String.format("%.0f", deal.discountPercent) + "%OFF</green>", "<gray>在庫: </gray><white>" + deal.stockRemaining + " / " + deal.stockTotal + "</white>", "<yellow>クリックして購入</yellow>");
                this.setVipDealTag(card, deal.id);
                gui.setItem(slot, card);
            }
            ++slot;
        }
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        this.addLog(u, "VIP手当(" + this.vipTierName(tier) + ") +" + this.fmtCur(amount));
        this.sendDiscordWebhook("💎 **" + p.getName() + "** がVIP手当(" + this.vipTierName(tier) + ")として " + (long)amount + "円 を受け取りました。");
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
        this.addLog(u, "VIP限定ショップ: " + this.merchantDisplayName(deal.material) + " を購入 -" + this.fmtCur(price));
        this.sendDiscordWebhook("💎 **" + p.getName() + "** がVIP限定ショップから **" + this.merchantDisplayName(deal.material) + "** を購入しました。（-" + (long)price + "円）");
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
                posterName = "運営";
            } else {
                posterName = Bukkit.getOfflinePlayer((UUID)q2.posterId).getName();
                if (posterName == null) {
                    posterName = "不明";
                }
            }
            Object distStr = "<gray>不明(別ワールド)</gray>";
            World w = Bukkit.getWorld((String)q2.worldName);
            if (w != null && w.equals((Object)p.getWorld())) {
                double dist = p.getLocation().distance(new Location(w, q2.x, q2.y, q2.z));
                distStr = "<yellow>" + (long)dist + "ブロック</yellow>";
            }
            boolean isSystemQuest = q2.posterId.equals(SYSTEM_QUEST_POSTER_ID);
            ArrayList<Object> lores = new ArrayList<Object>();
            lores.add("<gray>依頼主: </gray>" + (isSystemQuest ? "<gold>" : "<aqua>") + posterName + (isSystemQuest ? "</gold>" : "</aqua>"));
            if (isSystemQuest) {
                lores.add("<gold>★ 運営からの依頼</gold>");
            }
            lores.add("<gray>座標: </gray><white>" + q2.worldName + " (" + (long)q2.x + ", " + (long)q2.y + ", " + (long)q2.z + ")</white>");
            lores.add("<gray>現在地からの距離: </gray>" + (String)distStr);
            if (q2.requiredTeamSize > 1) {
                lores.add("<gray>一人あたり報酬: </gray><gold>" + (long)q2.reward + "円</gold>");
                lores.add("<gray>合計コスト: </gray><gold>" + (long)(q2.reward * (double)q2.requiredTeamSize) + "円</gold>");
                lores.add("");
                boolean isMember = q2.teamMembers.contains(u);
                if (isPoster) {
                    if (q2.state == QuestState.AVAILABLE) {
                        lores.add("<gray>募集状況: </gray><white>" + q2.teamMembers.size() + " / " + q2.requiredTeamSize + "人</white>");
                        if (q2.teamMembers.isEmpty()) {
                            mat = Material.MAP;
                            name = "<green><bold>🤝 あなたのチーム依頼(未参加) - クリックで取り下げ</bold></green>";
                            lores.add("<red>▶ クリックで取り下げて報酬を返金</red>");
                        } else {
                            mat = Material.MAP;
                            name = "<yellow><bold>🤝 あなたのチーム依頼(募集中)</bold></yellow>";
                            lores.add("<gray>参加者がいるため取り下げられません</gray>");
                        }
                    } else if (q2.state == QuestState.IN_PROGRESS) {
                        mat = Material.RECOVERY_COMPASS;
                        name = "<yellow><bold>🤝 あなたのチーム依頼(挑戦中)</bold></yellow>";
                        lores.add("<gray>到達状況: </gray><white>" + q2.teamArrived.size() + " / " + q2.teamMembers.size() + "人</white>");
                    } else {
                        long remain = this.questCooldownRemainMinutes(q2.cooldownUntil, now);
                        mat = Material.CLOCK;
                        name = "<gray><bold>🤝 あなたのチーム依頼(クールダウン中)</bold></gray>";
                        lores.add("<gray>再出品まで: </gray><white>" + remain + "分</white>");
                    }
                } else if (q2.state == QuestState.AVAILABLE) {
                    lores.add("<gray>募集状況: </gray><white>" + q2.teamMembers.size() + " / " + q2.requiredTeamSize + "人</white>");
                    mat = Material.MAP;
                    if (isMember) {
                        name = "<gold><bold>🤝 チーム依頼(参加中・募集中) - クリックで参加取消</bold></gold>";
                        lores.add("<red>▶ クリックで参加を取り消す</red>");
                    } else {
                        name = "<green><bold>🤝 チーム依頼(募集中) - クリックで参加</bold></green>";
                        lores.add("<yellow>▶ クリックで参加する</yellow>");
                    }
                } else if (q2.state == QuestState.IN_PROGRESS && isMember) {
                    mat = Material.RECOVERY_COMPASS;
                    name = "<gold><bold>🤝 チーム依頼(挑戦中) - クリックで放棄</bold></gold>";
                    lores.add("<gray>到達状況: </gray><white>" + q2.teamArrived.size() + " / " + q2.teamMembers.size() + "人</white>");
                    lores.add("<red>▶ クリックで放棄する(チーム全体が募集中に戻ります)</red>");
                } else if (q2.state == QuestState.IN_PROGRESS) {
                    mat = Material.GRAY_DYE;
                    name = "<dark_gray>🤝 他プレイヤーのチームが挑戦中</dark_gray>";
                    lores.add("<gray>到達状況: </gray><white>" + q2.teamArrived.size() + " / " + q2.teamMembers.size() + "人</white>");
                } else {
                    long remain = this.questCooldownRemainMinutes(q2.cooldownUntil, now);
                    mat = Material.CLOCK;
                    name = "<dark_gray>🤝 クールダウン中(残り" + remain + "分)</dark_gray>";
                }
            } else {
                lores.add("<gray>報酬: </gray><gold>" + (long)q2.reward + "円</gold>");
                lores.add("");
                if (isPoster) {
                    if (q2.state == QuestState.AVAILABLE) {
                        mat = Material.PAPER;
                        name = "<green><bold>あなたの依頼(未受注) - クリックで取り下げ</bold></green>";
                        lores.add("<red>▶ クリックで取り下げて報酬を返金</red>");
                    } else if (q2.state == QuestState.IN_PROGRESS) {
                        String accepterName = Bukkit.getOfflinePlayer((UUID)q2.acceptedBy).getName();
                        mat = Material.COMPASS;
                        name = "<yellow><bold>あなたの依頼(受注中)</bold></yellow>";
                        lores.add("<gray>受注者: </gray><aqua>" + (accepterName != null ? accepterName : "不明") + "</aqua>");
                    } else {
                        long remain = this.questCooldownRemainMinutes(q2.cooldownUntil, now);
                        mat = Material.CLOCK;
                        name = "<gray><bold>あなたの依頼(クールダウン中)</bold></gray>";
                        lores.add("<gray>再出品まで: </gray><white>" + remain + "分</white>");
                    }
                } else if (q2.state == QuestState.AVAILABLE) {
                    mat = Material.PAPER;
                    name = "<green><bold>探索依頼 - クリックで受注</bold></green>";
                    lores.add("<yellow>▶ クリックで受注する</yellow>");
                } else if (isAccepter) {
                    mat = Material.COMPASS;
                    name = "<gold><bold>受注中の依頼 - クリックで放棄</bold></gold>";
                    lores.add("<red>▶ クリックで放棄する</red>");
                } else if (q2.state == QuestState.IN_PROGRESS) {
                    mat = Material.GRAY_DYE;
                    name = "<dark_gray>他プレイヤーが受注中</dark_gray>";
                } else {
                    long remain = this.questCooldownRemainMinutes(q2.cooldownUntil, now);
                    mat = Material.CLOCK;
                    name = "<dark_gray>クールダウン中(残り" + remain + "分)</dark_gray>";
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
        gui.setItem(49, this.createItem(Material.EMERALD, "<green><bold>➕ 新しい依頼を出す</bold></green>", "<gray>あなたの現在地を目的地として、報酬を懸けて依頼を掲示します</gray>", "<gray>掲示中の依頼数: </gray><white>" + postedByMe + " / " + this.cfgQuestMaxPerPlayer + "</white>"));
        gui.setItem(48, this.createItem(Material.EMERALD_BLOCK, "<green><bold>🤝 チーム依頼を掲示</bold></green>", "<gray>あなたの現在地を目的地として、複数人で挑戦するチーム依頼を掲示します</gray>", "<gray>全員が同時に目的地へ到達すると、一人あたりの報酬が全員に支払われます</gray>", "<gray>掲示中の依頼数: </gray><white>" + postedByMe + " / " + this.cfgQuestMaxPerPlayer + "</white>"));
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAdminMainGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tAdminMain);
        gui.setItem(11, this.createItem(Material.PLAYER_HEAD, "<red><bold>👤 プレイヤー管理</bold></red>", "<gray>資産の付与/没収・信用スコア変更・データリセット</gray>"));
        gui.setItem(15, this.createItem(Material.COMMAND_BLOCK, "<red><bold>⚙ サーバー管理</bold></red>", "<gray>経済イベント発生・自己診断・reload/save</gray>"));
        gui.setItem(22, this.createItem(Material.BARRIER, "<gray>閉じる</gray>", new String[0]));
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
            ItemStack head = this.createItem(Material.PLAYER_HEAD, "<yellow><bold>" + online.getName() + "</bold></yellow>", "<gray>所持金:</gray> <green>" + (long)pocket + "円</green>", "<gray>預金:</gray> <aqua>" + (long)bank + "円</aqua>", "<gray>信用スコア:</gray> <light_purple>" + score + "</light_purple>", "<yellow>クリックで詳細を開く</yellow>");
            ItemMeta hm = head.getItemMeta();
            if (hm != null) {
                hm.getPersistentDataContainer().set(this.adminTargetKey, PersistentDataType.STRING, (Object)tid.toString());
                head.setItemMeta(hm);
            }
            gui.setItem(slot++, head);
        }
        gui.setItem(49, this.createItem(Material.NAME_TAG, "<white><bold>🔍 名前で検索（オフラインも可）</bold></white>", "<gray>クリックしてチャットにプレイヤー名を入力</gray>"));
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
        gui.setItem(4, this.createItem(Material.PLAYER_HEAD, "<yellow><bold>" + name + "</bold></yellow>", "<gray>所持金:</gray> <green>" + (long)pocket + "円</green>", "<gray>預金:</gray> <aqua>" + (long)bank + "円</aqua>", "<gray>信用スコア:</gray> <light_purple>" + score + "</light_purple>", "<gray>政府債務:</gray> <red>" + (long)gDebt + "円</red>"));
        gui.setItem(10, this.createItem(Material.GOLD_INGOT, "<green><bold>💰 お金を付与する</bold></green>", "<gray>クリックしてチャットに金額を入力</gray>"));
        gui.setItem(12, this.createItem(Material.REDSTONE, "<red><bold>💸 お金を没収する</bold></red>", "<gray>クリックしてチャットに金額を入力</gray>"));
        gui.setItem(14, this.createItem(Material.EMERALD, "<light_purple><bold>📊 信用スコアを設定</bold></light_purple>", "<gray>クリックしてチャットに0〜800の値を入力</gray>"));
        gui.setItem(16, this.createItem(Material.BARRIER, "<dark_red><bold>🗑 経済データをリセット</bold></dark_red>", "<gray>個人の口座・融資・実績などを全削除します</gray>", "<red><bold>もう一度クリックで確定（15秒以内）</bold></red>"));
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    private void openAdminServerGUI(Player p) {
        Inventory gui = Bukkit.createInventory(null, (int)27, (Component)this.tAdminServer);
        gui.setItem(10, this.createItem(Material.GOLD_BLOCK, "<gold><bold>🎲 ランダム経済イベント発生</bold></gold>", new String[0]));
        gui.setItem(11, this.createItem(Material.EMERALD_BLOCK, "<green><bold>📈 黄金ラッシュを発生</bold></green>", new String[0]));
        gui.setItem(12, this.createItem(Material.NETHERITE_INGOT, "<yellow><bold>💰 手数料高騰を発生</bold></yellow>", new String[0]));
        gui.setItem(13, this.createItem(Material.SUNFLOWER, "<aqua><bold>🎁 ボーナス支給デーを発生</bold></aqua>", new String[0]));
        gui.setItem(14, this.createItem(Material.REDSTONE_BLOCK, "<red><bold>📉 冬の時代を発生</bold></red>", new String[0]));
        gui.setItem(16, this.createItem(Material.KNOWLEDGE_BOOK, "<light_purple><bold>🔍 自己診断を実行</bold></light_purple>", "<gray>データ不整合を検出してチャットに表示</gray>"));
        gui.setItem(19, this.createItem(Material.HOPPER, "<white><bold>🔄 config/messagesを再読込</bold></white>", new String[0]));
        gui.setItem(20, this.createItem(Material.CHEST, "<white><bold>💾 経済データを保存</bold></white>", new String[0]));
        gui.setItem(21, this.createItem(Material.BOOK, "<white><bold>⚙ config設定を編集</bold></white>", "<gray>config.ymlの全設定をGUIから直接変更できます</gray>"));
        gui.setItem(22, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
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
            ItemStack item = this.createItem(Material.PAPER, "<aqua>" + key + "</aqua>", "<gray>現在値:</gray> <white>" + String.valueOf(value) + "</white>", "<dark_gray>型: " + typeName + "</dark_gray>", "<yellow>クリックしてチャットで新しい値を入力</yellow>");
            ItemMeta meta = item.getItemMeta();
            if (meta != null) {
                meta.getPersistentDataContainer().set(this.configKeyTag, PersistentDataType.STRING, (Object)key);
                item.setItemMeta(meta);
            }
            gui.setItem(slot++, item);
        }
        if (page > 0) {
            gui.setItem(51, this.createItem(Material.ARROW, "<aqua><bold>◀ 前のページ</bold></aqua>", "<gray>" + page + " / " + totalPages + "ページ</gray>"));
        }
        if (page < totalPages - 1) {
            gui.setItem(52, this.createItem(Material.ARROW, "<aqua><bold>次のページ ▶</bold></aqua>", "<gray>" + (page + 2) + " / " + totalPages + "ページ</gray>"));
        }
        gui.setItem(53, this.createItem(Material.IRON_DOOR, "<gray>戻る</gray>", new String[0]));
        this.fillGlass(gui);
        p.openInventory(gui);
    }

    /*
     * Unable to fully structure code
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player p;
        Component t;
        ItemStack item;
        UUID u;
        long nowClick;
        long lastClick;
        Material mat;
        double pocket;
        String symbol;
        String category;
        String matName;
        Material listedMat;
        String action;
        Material rmat;
        String dealIdStr;
        UUID dealId;
        MerchantDeal deal;
        String vipDealIdStr;
        UUID vipDealId;
        VipDeal vipDeal;
        long postedByMe;
        String qIdStr;
        Quest q;
        boolean isPoster;
        double refund;
        Player posterOnline;
        Player memberOnline;
        UUID adminTargetId;
        String confirmKey;
        long confirmedAt;
        boolean expired;
        String targetName;
        List<String> issues;
        int shown;
        int rawSlot;
        ItemMeta meta;
        String key;
        ItemStack selected;
        double value;
        double loan;
        ItemStack stored;
        double totalRepay;
        ItemStack returned;
        HashMap<Integer, ItemStack> leftover;
        Player target;
        UUID accountId;
        GroupAccount acc;
        String targetStr;
        UUID tu;
        HashSet<UUID> pset;
        String tn;
        Player to;
        String idStr;
        InvestmentFund fund;
        UUID fundId;
        LinkedList<String> logs;
        long remain;
        String token;
        String base;
        int mode;
        long myListings;
        ItemMeta clickedMeta;
        UUID auctionId;
        UUID seller;
        double currentBid;
        boolean hasBidder;
        double minNext;
        ItemMeta confirmMeta;
        ItemStack auctionedItem;
        long expiry;
        double bank;
        String lStr;
        UUID lenderId;
        Integer slotNumBoxed;
        int slotNum;
        HashMap<UUID, String> targetMap;
        String planData;
        String[] data;
        double amount;
        double interest;
        double capital;
        Player lender;
        double debt;
        double pay;
        double gDebt;
        int slot;
        int score;
        double currentGovDebt;
        double addDebt;
        double cap;
        long unlockTime;
        double reward;
        double a2;
        long t2;
        double a3;
        long t3;
        block550: {
            block551: {
                block552: {
                    if (!(e.getWhoClicked() instanceof Player)) {
                        return;
                    }
                    p = (Player)e.getWhoClicked();
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
                        switch (mat) {
                            case IRON_PICKAXE: {
                                category = "ore";
                                break;
                            }
                            case WHEAT: {
                                category = "crop";
                                break;
                            }
                            case BONE: {
                                category = "drop";
                                break;
                            }
                            case OAK_LOG: {
                                category = "wood";
                                break;
                            }
                            default: {
                                category = null;
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
                        switch (action) {
                            case "sell1": {
                                this.executeResourceSell(p, rmat, 1);
                                break;
                            }
                            case "sell16": {
                                this.executeResourceSell(p, rmat, 16);
                                break;
                            }
                            case "sell64": {
                                this.executeResourceSell(p, rmat, 64);
                                break;
                            }
                            case "buy1": {
                                this.executeResourceBuy(p, rmat, 1);
                                break;
                            }
                            case "buy16": {
                                this.executeResourceBuy(p, rmat, 16);
                                break;
                            }
                            case "buy64": {
                                this.executeResourceBuy(p, rmat, 64);
                                break;
                            }
                            case "sellqty": {
                                this.awaitingChatInput.put(u, "resource_sell_qty:" + rmat.name());
                                p.closeInventory();
                                this.msgKey(p, "resourceshop.sell-qty-prompt", new String[]{"material", this.resourceDisplayName(rmat)});
                                break;
                            }
                            case "buyqty": {
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
                        deal = this.merchantDeals.stream().filter(d -> d.id.equals(dealId)).findFirst().orElse(null);
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
                        vipDeal = this.vipShopDeals.stream().filter(d -> d.id.equals(vipDealId)).findFirst().orElse(null);
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
                            postedByMe = this.quests.values().stream().filter(q2 -> q2.posterId.equals(u)).count();
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
                            postedByMe = this.quests.values().stream().filter(q2 -> q2.posterId.equals(u)).count();
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
                            expired = System.currentTimeMillis() - confirmedAt > 15000L;
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
                            this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + targetName + "** の経済データをリセットしました。(GUI)");
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
                            this.fireEconomyEvent("黄金ラッシュ");
                            this.msgKey(p, "event.boom-triggered", new String[0]);
                        } else if (mat == Material.NETHERITE_INGOT) {
                            this.fireEconomyEvent("手数料高騰");
                            this.msgKey(p, "event.tax-triggered", new String[0]);
                        } else if (mat == Material.SUNFLOWER) {
                            this.fireEconomyEvent("ボーナス支給デー");
                            this.msgKey(p, "event.bonus-triggered", new String[0]);
                        } else if (mat == Material.REDSTONE_BLOCK) {
                            this.fireEconomyEvent("冬の時代");
                            this.msgKey(p, "event.recession-triggered", new String[0]);
                        } else {
                            if (mat == Material.KNOWLEDGE_BOOK) {
                                issues = this.runSelfCheck(false);
                                p.closeInventory();
                                if (issues.isEmpty()) {
                                    this.msgKey(p, "admin.selfcheck-ok", new String[0]);
                                } else {
                                    this.msgKey(p, "admin.selfcheck-issues", new String[]{"count", String.valueOf(issues.size()), "note", "（/meco admin selfcheck fix で自動修復できます）"});
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
                            key = meta != null ? (String)meta.getPersistentDataContainer().get(this.configKeyTag, PersistentDataType.STRING) : null;
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
                                this.addLog(u, "担保融資 +" + (long)loan + "円 (" + stored.getType().name() + ")");
                                this.sendDiscordWebhook("🏺 **" + p.getName() + "** が " + stored.getType().name() + " を担保に " + (long)loan + "円 を借りました。");
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
                                this.addLog(u, "担保融資完済 -" + this.fmtCur(totalRepay));
                                this.sendDiscordWebhook("🔓 **" + p.getName() + "** が担保融資を完済しました。返済額: " + this.fmtCur(totalRepay));
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
                        acc = accountId != null ? this.groupAccounts.get(accountId) : null;
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
                            expired = System.currentTimeMillis() - confirmedAt > 30000L;
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
                        acc = accountId != null ? this.groupAccounts.get(accountId) : null;
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
                                this.addLog(u, "グループ貯金箱「" + acc.name + "」から " + (tn != null ? tn : tu.toString()) + " を追放");
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
                                this.playerGroupAccounts.computeIfAbsent(tu, k -> new HashSet()).add(acc.id);
                                this.msgKey(p, "group.invited", new String[]{"player", to.getName()});
                                this.msgKey(to, "group.invite-received", new String[]{"group", acc.name, "player", p.getName()});
                                this.addLog(u, "グループ貯金箱「" + acc.name + "」に " + to.getName() + " を招待");
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
                        fund = fundId != null ? this.investmentFunds.get(fundId) : null;
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
                            expired = System.currentTimeMillis() - confirmedAt > 30000L;
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
                        fund = fundId != null ? this.investmentFunds.get(fundId) : null;
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
                        fund = fundId != null ? this.investmentFunds.get(fundId) : null;
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
                                        token = this.dashboardTokens.computeIfAbsent(u, k -> UUID.randomUUID().toString().replace("-", ""));
                                        base = this.cfgWebDashboardPublicUrl != null && this.cfgWebDashboardPublicUrl.isBlank() == false ? this.cfgWebDashboardPublicUrl.replaceAll("/$", "") : "http://<サーバーのIP>:" + this.cfgWebDashboardPort;
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
                        this.auctionPage.merge(u, 1, Integer::sum);
                        this.openAuctionGUI(p);
                        this.clickSound(p);
                        return;
                    }
                    if (mat == Material.EMERALD) {
                        myListings = this.auctionSeller.values().stream().filter(s -> s.equals(u)).count();
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
                if (seller.equals(u)) {
                    if (this.auctionBidder.containsKey(auctionId)) {
                        this.msgKey(p, "auction.cancel-has-bid", new String[0]);
                        this.errorSound(p);
                    } else {
                        this.openAuctionCancelConfirmGUI(p, auctionId);
                        this.clickSound(p);
                        return;
                    }
                } else {
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
                idStr = confirmMeta != null ? (String)confirmMeta.getPersistentDataContainer().get(this.auctionIdKey, PersistentDataType.STRING) : null;
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
                    this.addLog(seller, "オークション出品を自主取消: " + auctionedItem.getType().name());
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
                    this.addLog(u, "保険加入 -" + this.fmtCur(this.cfgInsurancePremium));
                    this.sendDiscordWebhook("🛡️ **" + p.getName() + "** が生命保険に加入しました。保険料: " + this.fmtCur(this.cfgInsurancePremium));
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
                this.sendDiscordWebhook("💰 **" + p.getName() + "** が個人口座へ " + (long)this.cfgDepositStep + "円 預金しました。");
            } else if (mat == Material.LIME_GLAZED_TERRACOTTA && pocket > 0.0) {
                MinecraftBank.econ.withdrawPlayer((OfflinePlayer)p, pocket);
                this.personalBank.put(u, bank + pocket);
                this.msgKey(p, "personal.deposit-full", new String[0]);
                this.sendDiscordWebhook("💰 **" + p.getName() + "** が個人口座へ全額預金しました。金額: " + this.fmtCur(pocket));
            } else if (mat == Material.RED_DYE && bank >= this.cfgDepositStep) {
                this.personalBank.put(u, bank - this.cfgDepositStep);
                MinecraftBank.econ.depositPlayer((OfflinePlayer)p, this.cfgDepositStep);
                this.msgKey(p, "personal.withdraw-step", new String[]{"amount", String.valueOf((long)this.cfgDepositStep)});
                this.sendDiscordWebhook("💸 **" + p.getName() + "** が個人口座から " + (long)this.cfgDepositStep + "円 引き出しました。");
            } else if (mat == Material.RED_GLAZED_TERRACOTTA && bank > 0.0) {
                confirmedAt = this.pendingConfirmationTime.getOrDefault(u, 0L);
                expired = System.currentTimeMillis() - confirmedAt > 30000L;
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
                this.sendDiscordWebhook("💸 **" + p.getName() + "** が個人口座から全額 " + (long)bank + "円 引き出しました。");
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
                slotNum = slotNumBoxed != null ? slotNumBoxed : 1;
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
                this.addLog(u, "融資契約成立 +" + (long)amount + "円 (返済総額" + (long)totalRepay + "円)");
                this.sendDiscordWebhook("💰 **" + p.getName() + "** が **" + Bukkit.getOfflinePlayer((UUID)lenderId).getName() + "** の融資プランから " + (long)amount + "円 を借りました。返済総額: " + this.fmtCur(totalRepay));
                this.addLog(lenderId, p.getName() + " へ融資 -" + this.fmtCur(amount));
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
                        this.unlockAchievement(u, "first_loan_repaid", "初めての完済");
                        this.sendDiscordWebhook("💳 **" + p.getName() + "** がプレイヤー間融資を完済しました。");
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
                    this.unlockAchievement(u, "first_loan_repaid", "初めての完済");
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
                        this.addLog(u, "国営ローン完済");
                        this.unlockAchievement(u, "first_gov_loan_repaid", "公庫の常連");
                        this.sendDiscordWebhook("🏛️ **" + p.getName() + "** が国営公庫ローンを完済しました。");
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
                    this.addLog(u, "国営ローン全額完済");
                    this.unlockAchievement(u, "first_gov_loan_repaid", "公庫の常連");
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
                    this.addLog(u, "銀行を設立 (-" + (long)this.cfgBankEstablishCost + "円)");
                    this.sendDiscordWebhook("🏦 **" + p.getName() + "** が銀行を設立しました。費用: " + this.fmtCur(this.cfgBankEstablishCost));
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
                    this.sendDiscordWebhook("🏦 **" + p.getName() + "** が銀行の資本金へ 10,000円 追加しました。");
                } else {
                    this.msgKey(p, "common.insufficient-funds-simple", new String[0]);
                    this.errorSound(p);
                }
            } else if (mat == Material.COAL) {
                if (capital >= 10000.0) {
                    this.bankCapital.put(u, capital - 10000.0);
                    MinecraftBank.econ.depositPlayer((OfflinePlayer)p, 10000.0);
                    this.msgKey(p, "bank.capital-withdrawn", new String[0]);
                    this.sendDiscordWebhook("🏦 **" + p.getName() + "** が銀行の資本金から 10,000円 引き出しました。");
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
                    this.addLog(u, "融資プラン(" + slot + "枠目)公開: " + (long)amount + "円/" + (long)interest + "%");
                    this.sendDiscordWebhook("📋 **" + p.getName() + "** が融資プラン(" + slot + "枠目)を公開しました。融資額: " + (long)amount + "円 / 利息: " + (long)interest + "%");
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
                this.addLog(u, "国営ローン借入 +" + (long)this.cfgGovLoanAmount + "円 (返済総額" + (long)addDebt + "円)");
                this.sendDiscordWebhook("🏛️ **" + p.getName() + "** が国営公庫から " + (long)this.cfgGovLoanAmount + "円 を借りました。返済総額: " + this.fmtCur(addDebt));
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
                    this.addLog(u, "定期預金(1枠) -" + this.fmtCur(this.cfgFixedDepositAmount));
                    this.sendDiscordWebhook("💵 **" + p.getName() + "** が定期預金1枠目に " + (long)this.cfgFixedDepositAmount + "円 を預けました。");
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
                    this.addLog(u, "定期預金(1枠)満期 +" + this.fmtCur(reward));
                    this.sendDiscordWebhook("🎉 **" + p.getName() + "** の定期預金1枠目が満期になりました。受取額: " + this.fmtCur(reward));
                    this.unlockAchievement(u, "first_fixed_deposit", "堅実な投資家");
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
                    this.addLog(u, "定期預金(2枠) -" + this.fmtCur(this.cfgFixedDepositAmount));
                    this.sendDiscordWebhook("💵 **" + p.getName() + "** が定期預金2枠目に " + (long)this.cfgFixedDepositAmount + "円 を預けました。");
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
                    this.addLog(u, "定期預金(2枠)満期 +" + this.fmtCur(reward));
                    this.sendDiscordWebhook("🎉 **" + p.getName() + "** の定期預金2枠目が満期になりました。受取額: " + this.fmtCur(reward));
                    this.unlockAchievement(u, "first_fixed_deposit", "堅実な投資家");
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
                    this.addLog(u, "定期預金(3枠) -" + this.fmtCur(this.cfgFixedDepositAmount));
                    this.sendDiscordWebhook("💵 **" + p.getName() + "** が定期預金3枠目に " + (long)this.cfgFixedDepositAmount + "円 を預けました。");
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
                    this.addLog(u, "定期預金(3枠)満期 +" + this.fmtCur(reward));
                    this.sendDiscordWebhook("🎉 **" + p.getName() + "** の定期預金3枠目が満期になりました。受取額: " + this.fmtCur(reward));
                    this.unlockAchievement(u, "first_fixed_deposit", "堅実な投資家");
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
        this.addLog(p.getUniqueId(), "埋蔵金を発見 +" + this.fmtCur(reward));
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        this.sendDiscordWebhook("💎 **" + p.getName() + "** が埋蔵金チェストを発見し、" + (long)reward + "円 を獲得しました。");
        this.broadcastNews("<gold><bold>【埋蔵金】</bold> <yellow>" + p.getName() + "</yellow> が埋蔵金 " + (long)reward + "円 を発見しました！</gold>");
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
                this.addLog(member, "チーム依頼達成 +" + this.fmtCur(q.reward));
                Player memberOnline = Bukkit.getPlayer((UUID)member);
                if (memberOnline != null) {
                    this.msgKey(memberOnline, "quest.team-completed", "amount", String.valueOf((long)q.reward));
                    this.sendToast(memberOnline, "チーム依頼達成", "+" + this.fmtCur(q.reward));
                    memberOnline.playSound(memberOnline.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
                }
                this.playerActiveQuest.remove(member);
            }
            Player posterOnline2 = Bukkit.getPlayer((UUID)q.posterId);
            if (posterOnline2 != null) {
                this.msgKey(posterOnline2, "quest.completed-notice", "player", p.getName());
            }
            this.sendDiscordWebhook("📋 **" + p.getName() + "** たちのチームが探索依頼を達成しました。一人あたり報酬: " + this.fmtCur(q.reward));
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
        this.sendToast(p, "依頼達成", "+" + this.fmtCur(q.reward));
        p.playSound(p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 1.0f, 1.0f);
        this.addLog(u, "依頼達成 +" + this.fmtCur(q.reward));
        Player posterOnline = Bukkit.getPlayer((UUID)q.posterId);
        if (posterOnline != null) {
            this.msgKey(posterOnline, "quest.completed-notice", "player", p.getName());
        }
        this.sendDiscordWebhook("📋 **" + p.getName() + "** が探索依頼を達成しました。報酬: " + this.fmtCur(q.reward));
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
            this.broadcastNews("<gray>【運営】新しい探索依頼を依頼ボードに掲示しました。</gray>");
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
            this.unlockAchievement(u, "millionaire", "資産家の証");
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
                this.unlockAchievement(u, "first_loan_repaid", "初めての完済");
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
                    this.addLog(guarantorId, "保証債務の履行: -" + (long)guarantorSeize + "円 (" + p.getName() + "の借金分)");
                    Player guarantorOnline = Bukkit.getPlayer((UUID)guarantorId);
                    if (guarantorOnline != null) {
                        this.msgKey(guarantorOnline, "loan.guarantor-seized-notice", "player", p.getName(), "amount", String.valueOf((long)guarantorSeize));
                    }
                    this.sendDiscordWebhook("⚠️ **保証債務執行**: " + Bukkit.getOfflinePlayer((UUID)guarantorId).getName() + " が " + p.getName() + " の借金 " + (long)guarantorSeize + "円 を代わりに支払いました。");
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
        this.sendToast(p, "保険金支払い", "+" + (long)this.cfgInsurancePayout + "円 が振り込まれました！");
        this.addLog(u, "保険金受給 +" + this.fmtCur(this.cfgInsurancePayout));
        this.sendDiscordWebhook("💰 **" + p.getName() + "** に生命保険金 " + (long)this.cfgInsurancePayout + "円 が支払われました。");
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
            this.getLogger().info("[Webダッシュボード] http://<サーバーIP>:" + this.cfgWebDashboardPort + "/ で配信を開始しました。(JSON APIは /dashboard.json)");
        }
        catch (Exception ex) {
            this.getLogger().warning("[Webダッシュボード] HTTPサーバーの起動に失敗しました(ポート " + this.cfgWebDashboardPort + " が使用中の可能性があります): " + ex.getMessage());
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
            this.getLogger().warning("[Webダッシュボード] リクエスト処理中にエラー: " + ex.getMessage());
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
            this.getLogger().warning("[Webダッシュボード] ページ配信中にエラー: " + ex.getMessage());
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
                    this.sendJsonResponse(exchange, 409, "{\"ok\":false,\"message\":\"受取箱の受取はログイン中のみ操作できます\"}");
                    return;
                }
            } else if (!isOnline) {
                String password = this.getFormParam(body, "password");
                boolean authorized = this.verifyWebPassword(this.webPasswordHash.get(owner), password) || this.verifyWebPassword(this.webAdminPasswordHash.get(owner), password);
                if (!authorized) {
                    this.sendJsonResponse(exchange, 403, "{\"ok\":false,\"message\":\"パスワードが違います(未設定の場合はゲーム内で /meco webpage password を設定してください)\"}");
                    return;
                }
            }
            Player actor = isOnline ? online : Bukkit.getOfflinePlayer((UUID)owner);
            String resultJson = this.runOnMainThreadSync(() -> this.handleMyActionOnMainThread(path, (OfflinePlayer)actor, body));
            this.sendJsonResponse(exchange, 200, resultJson);
        }
        catch (Exception ex) {
            this.getLogger().warning("[Webダッシュボード] 個人ページ操作リクエストの処理中にエラー: " + ex.getMessage());
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
            this.getLogger().warning("[Webダッシュボード] 個人ページ用リクエスト処理中にエラー: " + ex.getMessage());
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
            this.getLogger().warning("[Webダッシュボード] 個人ページ配信中にエラー: " + ex.getMessage());
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
            this.getLogger().warning("[Webダッシュボード] JSONファイルの書き出しに失敗しました: " + ex.getMessage());
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
            issues.add("担保融資データ不整合: " + String.valueOf(key) + " のデータが一部欠落しています");
        }
        for (UUID auctionId : new HashSet<UUID>(this.auctionSeller.keySet())) {
            if (this.auctionItem.containsKey(auctionId) && this.auctionEndTime.containsKey(auctionId)) continue;
            issues.add("オークションデータ不整合: " + String.valueOf(auctionId) + " のアイテムまたは終了時刻が欠落しています");
            if (!autoFix) continue;
            this.auctionSeller.remove(auctionId);
            this.auctionBid.remove(auctionId);
            this.auctionBidder.remove(auctionId);
        }
        for (UUID k : this.personalBank.keySet()) {
            if (!(this.personalBank.get(k) < 0.0)) continue;
            issues.add("personalBank: " + String.valueOf(k) + " の残高が負の値です(" + String.valueOf(this.personalBank.get(k)) + "円)");
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
            if (raw.equals("キャンセル") || raw.equalsIgnoreCase("cancel")) {
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
                if (q2.equalsIgnoreCase("クリア") || q2.equalsIgnoreCase("clear")) {
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
                this.addLog(u, "依頼ボードに掲示 -" + (long)amount + "円(エスクロー)");
                this.sendDiscordWebhook("📋 **" + p.getName() + "** が探索依頼を掲示しました。報酬: " + this.fmtCur(amount));
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
                this.addLog(u, "チーム依頼をボードに掲示 -" + (long)totalEscrow + "円(エスクロー、" + teamSize + "人×" + (long)teamReward + "円)");
                this.sendDiscordWebhook("📋 **" + p.getName() + "** がチーム探索依頼(" + teamSize + "人)を掲示しました。一人あたり報酬: " + this.fmtCur(teamReward));
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
                this.addLog(targetId, "[管理者操作] " + p.getName() + " から +" + this.fmtCur(amount));
                this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + targetName + "** に " + (long)amount + "円 を付与しました。(GUI)");
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
                this.addLog(targetId, "[管理者操作] " + p.getName() + " により -" + this.fmtCur(amount));
                this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + targetName + "** から " + (long)amount + "円 を没収しました。(GUI)");
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
                this.addLog(u, "グループ貯金箱「" + acc.name + "」へ入金 -" + this.fmtCur(amount));
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
                this.addLog(u, "グループ貯金箱「" + acc.name + "」から引出 +" + this.fmtCur(amount));
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
                this.sendDiscordWebhook("🛠️ 管理者 **" + p.getName() + "** が **" + targetName + "** の信用スコアを " + score + " に設定しました。(GUI)");
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
                    this.addLog(u, "個人口座 指定預金 -" + this.fmtCur(amount));
                    this.sendDiscordWebhook("💰 **" + p.getName() + "** が個人口座へ " + (long)amount + "円 預金しました。");
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
                    this.addLog(u, "個人口座 指定引出 +" + this.fmtCur(amount));
                    this.sendDiscordWebhook("💸 **" + p.getName() + "** が個人口座から " + (long)amount + "円 引き出しました。");
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
                    this.addLog(u, "国庫へ寄付: -" + this.fmtCur(amount));
                    this.sendDiscordWebhook("🎁 **" + p.getName() + "** が国庫へ " + (long)amount + "円 を寄付しました。");
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
                    String buyoutMsg = buyout > 0.0 ? " / 即決価格: " + this.fmtCur(buyout) : "";
                    this.msgKey(p, "auction.listed", "item", draft.getType().name(), "amount", String.valueOf((long)startPrice.doubleValue()), "buyoutMsg", buyoutMsg, "minutes", String.valueOf(this.cfgAuctionDurationMs / 60000L));
                    p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                    this.addLog(u, "オークション出品: " + draft.getType().name() + " (開始価格" + this.fmtCur(startPrice) + buyoutMsg + ")");
                    this.sendDiscordWebhook("🏷️ **" + p.getName() + "** が **" + draft.getType().name() + "** をオークションに出品しました。開始価格: " + this.fmtCur(startPrice) + buyoutMsg);
                    this.broadcastNews("<gold><bold>【オークション】</bold></gold> <yellow>" + p.getName() + "</yellow> が <white>" + draft.getType().name() + "</white> を出品した！");
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
                        body = errStream != null ? new String(errStream.readAllBytes(), StandardCharsets.UTF_8) : "(詳細なし)";
                    }
                    catch (Exception readEx) {
                        body = "(詳細取得失敗: " + readEx.getMessage() + ")";
                    }
                    this.getLogger().warning("[Discord Bot] 送信に失敗しました。HTTPステータス: " + code + " / 内容: " + (String)body);
                    if (code == 401) {
                        this.getLogger().warning("[Discord Bot] 401 = トークンが無効です。config.ymlのsystem.discord-bot-tokenを確認してください。");
                    } else if (code == 403) {
                        this.getLogger().warning("[Discord Bot] 403 = Botにこのチャンネルへの送信権限がありません。サーバーへの招待・権限設定を確認してください。");
                    } else if (code == 404) {
                        this.getLogger().warning("[Discord Bot] 404 = チャンネルIDが見つかりません。system.discord-channel-idを確認してください。");
                    }
                }
                catch (Exception ex) {
                    this.getLogger().warning("[Discord Bot] 送信中にエラーが発生しました: " + ex.getMessage());
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
                this.getLogger().warning("[世界株式市場] " + symbol + " の株価取得に失敗しました: " + ex.getMessage());
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
                this.getLogger().warning("[messages.yml] 保存に失敗しました: " + ex.getMessage());
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

    static {
        COUNTRY_CURRENCY_MAP.put("japan", "円");
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
        DEFAULT_MESSAGES.put("welcome.first-join", "<gold><bold>【経済手帳】</bold> あなたに経済手帳が配布されました。右クリックで経済総合メニューを開けます。</gold>");
        DEFAULT_MESSAGES.put("welcome.no-permission", "<red>このコマンドを使用する権限がありません。</red>");
        DEFAULT_MESSAGES.put("common.insufficient-funds", "<red>手持ち資金が足りません。（所持金: {amount}円）</red>");
        DEFAULT_MESSAGES.put("common.invalid-number", "<red>数値を入力してください。（例: 5000）</red>");
        DEFAULT_MESSAGES.put("common.cancelled", "<gray>入力をキャンセルしました。</gray>");
        DEFAULT_MESSAGES.put("insurance.payout", "<blue><bold>【保険金支払い】 加入していた生命保険から {amount}円 が振り込まれました。</bold></blue>");
        DEFAULT_MESSAGES.put("auction.won", "<green><bold>【落札】 {item} を {amount}円 で落札しました。（受取箱を確認してください）</bold></green>");
        DEFAULT_MESSAGES.put("auction.sold", "<green><bold>【落札成立】</bold> {item} が {amount}円 で落札されました。（手数料 {fee}円 差引後 {net}円 を入金）</green>");
        DEFAULT_MESSAGES.put("auction.outbid-refund", "<yellow>【オークション】 {item} へのあなたの入札は上回られました。入札金 {amount}円 を返金しました。</yellow>");
        DEFAULT_MESSAGES.put("loan.player-approved", "<green><bold>融資契約が成立しました！ {amount}円 を受領しました。</bold></green>");
        DEFAULT_MESSAGES.put("loan.gov-approved", "<green><bold>国営公庫から {amount}円 融資を受けました！ (適用金利: {rate}% / 総返済額: {total}円 / 期限: {minutes}分)</bold></green>");
        DEFAULT_MESSAGES.put("loan.repaid-full", "<green><bold>プレイヤー間の借金を全額完済しました！</bold></green>");
        DEFAULT_MESSAGES.put("achievement.unlocked", "<gold><bold>🎖 実績「{title}」を解放しました！</bold></gold>");
        DEFAULT_MESSAGES.put("event.boom", "<green><bold>【経済イベント】黄金ラッシュ！</bold> 資源相場ショップの取引価格が一時的に10%上昇します。</green>");
        DEFAULT_MESSAGES.put("event.tax", "<red><bold>【経済イベント】手数料高騰！</bold> 世界株式市場の売買手数料が一時的に2倍になります。</red>");
        DEFAULT_MESSAGES.put("event.bonus", "<gold><bold>【経済イベント】ボーナス支給デー！</bold> 全プレイヤーに5,000円を支給しました。</gold>");
        DEFAULT_MESSAGES.put("event.recession", "<dark_red><bold>【経済イベント】冬の時代！</bold> 資源相場ショップの取引価格が一時的に10%下落します。</dark_red>");
        DEFAULT_MESSAGES.put("confirm.dissolve-prompt", "<red><bold>会社を本当に解散しますか？</bold></red>");
        DEFAULT_MESSAGES.put("confirm.withdraw-prompt", "<red><bold>預金全額を引き出しますか？</bold></red> <yellow>30秒以内にもう一度クリックで確定します。</yellow>");
        DEFAULT_MESSAGES.put("confirm.expired", "<red>確認の有効期限が切れました。もう一度実行してください。</red>");
        DEFAULT_MESSAGES.put("fixed-deposit.matured", "<green><bold>定期預金が満期を迎えました！ 報酬 {amount}円 を受領しました。</bold></green>");
        DEFAULT_MESSAGES.put("fixed-deposit.matured-2", "<aqua><bold>2枠目の定期預金が満期を迎えました！ 報酬 {amount}円 を受領しました。</bold></aqua>");
        DEFAULT_MESSAGES.put("fixed-deposit.matured-3", "<light_purple><bold>3枠目の定期預金が満期を迎えました！ 報酬 {amount}円 を受領しました。</bold></light_purple>");
        DEFAULT_MESSAGES.put("collateral.seized", "<dark_red><bold>【担保没収】</bold> 担保付き融資の返済期限を過ぎたため、担保( {item} )は質屋に没収されました。</dark_red>");
        DEFAULT_MESSAGES.put("loan.gov-overdue-penalty", "<dark_red><bold>【延滞】</bold> 国営公庫ローンの返済期限を過ぎたため、延滞金 {amount}円 が加算されました。</dark_red>");
        DEFAULT_MESSAGES.put("loan.gov-overdue-ceiling", "<dark_red><bold>【延滞】</bold> 国営公庫ローンが延滞上限（{amount}円）に達しています。至急返済してください。</dark_red>");
        DEFAULT_MESSAGES.put("auction.no-bid-returned", "<gray>【オークション終了】 {item} の出品は入札者がおらず、手元に戻ります。（受取箱を確認してください）</gray>");
        DEFAULT_MESSAGES.put("auction.items-received", "<gold><bold>【オークションハウス】</bold> 落札品・返却品 {count}件 をインベントリに受け取りました。</gold>");
        DEFAULT_MESSAGES.put("item.reissue-cooldown", "<red>再発行のクールダウン中です。あと {seconds}秒 お待ちください。</red>");
        DEFAULT_MESSAGES.put("item.reissued", "<green><bold>経済手帳を再発行しました。</bold></green>");
        DEFAULT_MESSAGES.put("help.header", "<gold><bold>=== MinecraftBank コマンド一覧 ===</bold></gold>");
        DEFAULT_MESSAGES.put("help.menu", "<yellow>/meco</yellow> <gray>- 経済総合メニューを開く（📖経済手帳の右クリックでも開けます）</gray>");
        DEFAULT_MESSAGES.put("help.shortcuts", "<yellow>/meco <bank|market|auction|resource|worldstock|merchant|vip|todo|quest|lottery|storage|loan|govloan|insurance|collateral|credit|deposit|personal|installment|mypage|leaderboard|achievement|repay></yellow> <gray>- 各画面に直接アクセス</gray>");
        DEFAULT_MESSAGES.put("help.webpage", "<yellow>/meco webpage</yellow> <gray>- 自分専用のWeb個人ページのリンクを表示</gray>");
        DEFAULT_MESSAGES.put("help.collect", "<yellow>/meco collect</yellow> <gray>- オークション落札品などの受取箱をその場で再受取</gray>");
        DEFAULT_MESSAGES.put("help.item", "<yellow>/meco item</yellow> <gray>- 経済手帳をなくした場合に再発行する</gray>");
        DEFAULT_MESSAGES.put("help.log", "<yellow>/meco log</yellow> <gray>- 直近の取引履歴を表示</gray>");
        DEFAULT_MESSAGES.put("help.news", "<yellow>/meco news</yellow> <gray>- 経済ニュース放送(自分宛)のON/OFF切替</gray>");
        DEFAULT_MESSAGES.put("help.treasury", "<yellow>/meco treasury</yellow> <gray>- 国庫残高を確認</gray>");
        DEFAULT_MESSAGES.put("help.tutorial", "<yellow>/meco tutorial</yellow> <gray>- 初心者ガイドを開き直す</gray>");
        DEFAULT_MESSAGES.put("help.donate-treasury", "<yellow>/meco donate treasury <金額></yellow> <gray>- 国庫へ寄付(信用スコアが少し上がります)</gray>");
        DEFAULT_MESSAGES.put("help.donate-player", "<yellow>/meco donate <プレイヤー> <金額></yellow> <gray>- 他プレイヤーへ寄付</gray>");
        DEFAULT_MESSAGES.put("help.admin-header", "<red><bold>--- 管理者コマンド ---</bold></red>");
        DEFAULT_MESSAGES.put("help.admin-give", "<red>/meco admin give <プレイヤー> <金額></red> <gray>- 所持金を付与</gray>");
        DEFAULT_MESSAGES.put("help.admin-take", "<red>/meco admin take <プレイヤー> <金額></red> <gray>- 所持金を没収</gray>");
        DEFAULT_MESSAGES.put("help.admin-setcredit", "<red>/meco admin setcredit <プレイヤー> <スコア></red> <gray>- 信用スコアを設定</gray>");
        DEFAULT_MESSAGES.put("help.admin-reset", "<red>/meco admin reset <プレイヤー></red> <gray>- 経済データを全リセット</gray>");
        DEFAULT_MESSAGES.put("help.admin-discord", "<red>/meco admin discord test</red> <gray>- Discord Bot連携の疎通テスト送信</gray>");
        DEFAULT_MESSAGES.put("help.admin-selfcheck", "<red>/meco admin selfcheck [fix]</red> <gray>- データ不整合を検出(fixで自動修復)</gray>");
        DEFAULT_MESSAGES.put("help.admin-reload", "<red>/meco admin reload</red> <gray>- config.ymlを再読込</gray>");
        DEFAULT_MESSAGES.put("help.admin-save", "<red>/meco admin save</red> <gray>- 経済データを即時保存</gray>");
        DEFAULT_MESSAGES.put("help.admin-backup", "<red>/meco admin backup</red> <gray>- data.dbを今すぐバックアップ</gray>");
        DEFAULT_MESSAGES.put("help.admin-config", "<red>/meco admin config</red> <gray>- config設定をGUIで編集</gray>");
        DEFAULT_MESSAGES.put("admin.no-permission", "<red>管理者権限がありません。</red>");
        DEFAULT_MESSAGES.put("admin.usage", "<yellow>使用法: /meco admin <give|take|setcredit|reset|reload|save|backup|config|gui> ...</yellow>");
        DEFAULT_MESSAGES.put("admin.reloaded", "<green><bold>config.yml / messages.yml を再読込しました。</bold></green>");
        DEFAULT_MESSAGES.put("admin.reloaded-alt", "<green><bold>config.yml / messages.ymlを再読込しました。</bold></green>");
        DEFAULT_MESSAGES.put("admin.saved", "<green><bold>経済データを保存しました。</bold></green>");
        DEFAULT_MESSAGES.put("admin.backed-up", "<green><bold>data.dbのバックアップを作成しました。</bold></green>");
        DEFAULT_MESSAGES.put("config.edit-prompt", "<yellow>{key} の新しい値をチャットに入力してください。(キャンセル で中止)</yellow>");
        DEFAULT_MESSAGES.put("config.invalid-value", "<red>「{value}」はこの設定の型として解釈できませんでした。数値設定には数字のみ入力してください。</red>");
        DEFAULT_MESSAGES.put("config.updated", "<green><bold>{key} を {value} に変更しました。</bold></green>");
        DEFAULT_MESSAGES.put("webpage.disabled", "<red>Webダッシュボードが無効になっています。config.ymlのwebdashboard.enabledをtrueにしてください。</red>");
        DEFAULT_MESSAGES.put("webpage.link", "<green>あなたの個人ページ:</green> <click:open_url:'{url}'><underlined><aqua>{url}</aqua></underlined></click>");
        DEFAULT_MESSAGES.put("webpage.password-prompt", "<yellow>オフライン操作用のパスワードをチャットに入力してください(4文字以上)。「キャンセル」で中止。</yellow>");
        DEFAULT_MESSAGES.put("webpage.password-too-short", "<red>パスワードは4文字以上にしてください。</red>");
        DEFAULT_MESSAGES.put("webpage.password-set", "<green><bold>オフライン操作用パスワードを設定しました。これでログインしていない時もWebページから操作できます。</bold></green>");
        DEFAULT_MESSAGES.put("webpage.password-cleared", "<green>オフライン操作用パスワードを解除しました。以後はログイン中のみWebページから操作できます。</green>");
        DEFAULT_MESSAGES.put("webpage.password-status-set", "<gray>(オフライン操作用パスワード設定済み。変更/解除は /meco webpage password )</gray>");
        DEFAULT_MESSAGES.put("webpage.password-status-unset", "<gray>(ログイン中のみWebから操作できます。オフラインでも操作したい場合は /meco webpage password でパスワードを設定してください)</gray>");
        DEFAULT_MESSAGES.put("admin.need-playername", "<yellow>プレイヤー名を指定してください。</yellow>");
        DEFAULT_MESSAGES.put("admin.usage-give", "<yellow>使用法: /meco admin give <プレイヤー> <金額></yellow>");
        DEFAULT_MESSAGES.put("admin.give-success", "<green>{player} に {amount}円 を付与しました。</green>");
        DEFAULT_MESSAGES.put("common.invalid-amount-number", "<red>金額は数値で指定してください。</red>");
        DEFAULT_MESSAGES.put("admin.usage-take", "<yellow>使用法: /meco admin take <プレイヤー> <金額></yellow>");
        DEFAULT_MESSAGES.put("admin.take-success", "<green>{player} から {amount}円 を没収しました。</green>");
        DEFAULT_MESSAGES.put("admin.usage-setcredit", "<yellow>使用法: /meco admin setcredit <プレイヤー> <スコア></yellow>");
        DEFAULT_MESSAGES.put("admin.setcredit-success", "<green>{player} の信用スコアを {score} に設定しました。</green>");
        DEFAULT_MESSAGES.put("admin.invalid-score-number", "<red>スコアは数値で指定してください。</red>");
        DEFAULT_MESSAGES.put("admin.usage-setgovdebt", "<yellow>使用法: /meco admin setgovdebt <プレイヤー> <金額>（0で完済扱い）</yellow>");
        DEFAULT_MESSAGES.put("admin.setgovdebt-success", "<green>{player} の国営ローン残債を {amount}円 に設定しました。</green>");
        DEFAULT_MESSAGES.put("admin.reset-success", "<green><bold>{player} の経済データをリセットしました。</bold></green>");
        DEFAULT_MESSAGES.put("admin.reset-note", "<gray>(会社・連合データは影響が大きいため別途 /meco admin reset は個人データのみ対象です)</gray>");
        DEFAULT_MESSAGES.put("admin.usage-event", "<yellow>/meco admin event <random|boom|tax|bonus|recession></yellow>");
        DEFAULT_MESSAGES.put("admin.discord-not-configured", "<red>Botトークンまたはチャンネルidがconfig.ymlに設定されていません。</red>");
        DEFAULT_MESSAGES.put("admin.discord-config-hint", "<gray>system.discord-bot-token / system.discord-channel-id を確認してください。</gray>");
        DEFAULT_MESSAGES.put("admin.discord-test-sending", "<gray>Discordへテスト送信中...サーバーコンソールに結果ログが出ます。</gray>");
        DEFAULT_MESSAGES.put("admin.usage-discord-test", "<yellow>使用法: /meco admin discord test</yellow>");
        DEFAULT_MESSAGES.put("admin.selfcheck-ok", "<green><bold>【自己診断】</bold> データ不整合は見つかりませんでした。</green>");
        DEFAULT_MESSAGES.put("admin.selfcheck-issues", "<yellow><bold>【自己診断】{count}件の不整合を検出しました{note}</bold></yellow>");
        DEFAULT_MESSAGES.put("admin.selfcheck-more", "<gray>...他 {count}件（コンソールログを確認してください）</gray>");
        DEFAULT_MESSAGES.put("admin.selfcheck-issue-line", "<gray>- {issue}</gray>");
        DEFAULT_MESSAGES.put("admin.usage-full", "<yellow>使用法: /meco admin <give|take|setcredit|setgovdebt|reset|reload|save|event|discord|selfcheck|gui> ...</yellow>");
        DEFAULT_MESSAGES.put("log.empty", "<gray>取引履歴はまだありません。</gray>");
        DEFAULT_MESSAGES.put("log.header", "<gold><bold>=== 直近の取引履歴 ===</bold></gold>");
        DEFAULT_MESSAGES.put("log.entry", "<gray>{entry}</gray>");
        DEFAULT_MESSAGES.put("news.enabled", "<green>経済ニュース放送を【ON】にしました。</green>");
        DEFAULT_MESSAGES.put("news.disabled", "<yellow>経済ニュース放送を【OFF】にしました。</yellow>");
        DEFAULT_MESSAGES.put("treasury.balance", "<gold>【国庫】 <white>{amount}円</white></gold>");
        DEFAULT_MESSAGES.put("treasury.admin-required", "<red>管理者権限が必要です。</red>");
        DEFAULT_MESSAGES.put("treasury.insufficient", "<red>国庫残高が不足しています。</red>");
        DEFAULT_MESSAGES.put("common.invalid-amount", "<red>金額が不正です。</red>");
        DEFAULT_MESSAGES.put("treasury.usage", "<yellow>/meco treasury</yellow> / <yellow>/meco treasury bonus <金額></yellow> / <yellow>/meco treasury set <金額></yellow> / <yellow>/meco treasury donate <金額></yellow>");
        DEFAULT_MESSAGES.put("treasury.set", "<gold>国庫残高を <white>{amount}円</white> に設定しました。</gold>");
        DEFAULT_MESSAGES.put("treasury.citizen-dividend", "<gold><bold>【市民配当】</bold> 国庫から <white>{amount}円</white> が配当されました。</gold>");
        DEFAULT_MESSAGES.put("treasury.welfare", "<green><bold>【生活支援】</bold> 国庫から生活支援金 <white>{amount}円</white> が給付されました。</green>");
        DEFAULT_MESSAGES.put("donate.usage-treasury", "<yellow>/meco donate treasury <金額></yellow> <gray>- 国庫へ寄付(信用スコアが少し上がります)</gray>");
        DEFAULT_MESSAGES.put("donate.usage-player", "<yellow>/meco donate <プレイヤー> <金額></yellow> <gray>- 他プレイヤーへ寄付</gray>");
        DEFAULT_MESSAGES.put("donate.usage-treasury-amount", "<yellow>/meco donate treasury <金額></yellow>");
        DEFAULT_MESSAGES.put("common.amount-must-be-positive", "<red>0円より大きい金額を入力してください。</red>");
        DEFAULT_MESSAGES.put("common.insufficient-funds-simple", "<red>手持ち資金が足りません。</red>");
        DEFAULT_MESSAGES.put("donate.treasury-thanks", "<green><bold>国庫へ {amount}円 を寄付しました。ありがとうございます！</bold></green>");
        DEFAULT_MESSAGES.put("common.target-offline", "<red>対象プレイヤーがオンラインではありません。</red>");
        DEFAULT_MESSAGES.put("donate.cannot-self", "<red>自分自身には寄付できません。</red>");
        DEFAULT_MESSAGES.put("donate.usage-player-amount", "<yellow>/meco donate <プレイヤー> <金額></yellow>");
        DEFAULT_MESSAGES.put("donate.player-success", "<green><bold>{player} に {amount}円 を寄付しました。</bold></green>");
        DEFAULT_MESSAGES.put("donate.player-received", "<gold><bold>【寄付】</bold> {player} から {amount}円 の寄付を受け取りました。</gold>");
        DEFAULT_MESSAGES.put("guarantor.usage-request", "<yellow>/meco guarantor request <プレイヤー></yellow> <gray>- 保証人を依頼する</gray>");
        DEFAULT_MESSAGES.put("guarantor.usage-accept", "<yellow>/meco guarantor accept</yellow> <gray>- 依頼を承諾する</gray>");
        DEFAULT_MESSAGES.put("guarantor.usage-decline", "<yellow>/meco guarantor decline</yellow> <gray>- 依頼を拒否する</gray>");
        DEFAULT_MESSAGES.put("guarantor.no-proposal-expired", "<red>有効な保証人依頼がありません。（期限切れの可能性があります）</red>");
        DEFAULT_MESSAGES.put("guarantor.borrower-no-debt", "<red>依頼者にはもう対象の借金がありません。</red>");
        DEFAULT_MESSAGES.put("guarantor.became", "<green><bold>{player} の保証人になりました。</bold></green>");
        DEFAULT_MESSAGES.put("guarantor.became-notice", "<green>{player} があなたの保証人になりました。</green>");
        DEFAULT_MESSAGES.put("guarantor.declined-self", "<yellow>保証人依頼を拒否しました。</yellow>");
        DEFAULT_MESSAGES.put("guarantor.declined-notice", "<red>{player} は保証人依頼を拒否しました。</red>");
        DEFAULT_MESSAGES.put("guarantor.no-proposal", "<red>有効な保証人依頼がありません。</red>");
        DEFAULT_MESSAGES.put("guarantor.no-debt", "<red>あなたには現在、対象となる借金がありません。</red>");
        DEFAULT_MESSAGES.put("guarantor.already-set", "<red>すでに保証人が設定されています。</red>");
        DEFAULT_MESSAGES.put("guarantor.usage-request-arg", "<yellow>/meco guarantor request <プレイヤー></yellow>");
        DEFAULT_MESSAGES.put("guarantor.cannot-self", "<red>自分自身を保証人にはできません。</red>");
        DEFAULT_MESSAGES.put("guarantor.requested", "<green><bold>{player} に保証人を依頼しました。（5分以内に返答が必要）</bold></green>");
        DEFAULT_MESSAGES.put("guarantor.request-received", "<gold><bold>【保証人依頼】</bold> {player} があなたに融資の保証人を依頼しています。</gold>");
        DEFAULT_MESSAGES.put("guarantor.request-instructions", "<yellow>承諾: /meco guarantor accept ／ 拒否: /meco guarantor decline</yellow>");
        DEFAULT_MESSAGES.put("guarantor.request-warning", "<gray>※保証人になると、依頼者が返済不能になった際に信用スコアへ影響します。</gray>");
        DEFAULT_MESSAGES.put("guarantor.usage", "<yellow>使用法: /meco guarantor <request|accept|decline></yellow>");
        DEFAULT_MESSAGES.put("trade.usage", "<yellow>使用法: /meco trade <プレイヤー|accept|decline></yellow>");
        DEFAULT_MESSAGES.put("trade.request-sent", "<green><bold>{player} にアイテム交換を申し込みました。（60秒以内に返答が必要）</bold></green>");
        DEFAULT_MESSAGES.put("trade.request-received", "<gold><bold>【交換依頼】</bold> {player} があなたにアイテム交換を申し込んでいます。</gold>");
        DEFAULT_MESSAGES.put("trade.request-instructions", "<yellow>承諾: /meco trade accept ／ 拒否: /meco trade decline</yellow>");
        DEFAULT_MESSAGES.put("trade.no-request", "<red>有効な交換依頼がありません。</red>");
        DEFAULT_MESSAGES.put("trade.no-request-expired", "<red>有効な交換依頼がありません。（期限切れの可能性があります）</red>");
        DEFAULT_MESSAGES.put("trade.declined-self", "<yellow>交換依頼を拒否しました。</yellow>");
        DEFAULT_MESSAGES.put("trade.declined-notice", "<red>{player} は交換依頼を拒否しました。</red>");
        DEFAULT_MESSAGES.put("trade.cannot-self", "<red>自分自身とは交換できません。</red>");
        DEFAULT_MESSAGES.put("trade.already-in-progress", "<red>既に交換が進行中か、保留中の依頼があります。</red>");
        DEFAULT_MESSAGES.put("trade.money-prompt", "<gold><bold>チャットに提示する金額を入力してください。（0でキャンセル可）</bold></gold>");
        DEFAULT_MESSAGES.put("trade.money-set", "<green>提示金額を {amount}円 に設定しました。</green>");
        DEFAULT_MESSAGES.put("trade.money-insufficient", "<red>所持金が足りないため、その金額は提示できません。</red>");
        DEFAULT_MESSAGES.put("trade.cancelled", "<red><bold>取引はキャンセルされました。提示していたアイテムは返却されました。</bold></red>");
        DEFAULT_MESSAGES.put("trade.completed", "<green><bold>【成立】アイテム交換が完了しました！</bold></green>");
        DEFAULT_MESSAGES.put("help.trade", "<yellow>/meco trade <プレイヤー></yellow> <gray>- 安全にアイテム・お金を交換する</gray>");
        DEFAULT_MESSAGES.put("quest.max-reached", "<red>掲示できる依頼数の上限({count}件)に達しています。</red>");
        DEFAULT_MESSAGES.put("quest.post-prompt", "<gold><bold>チャットに依頼の報酬額を入力してください。（あなたの今の場所が目的地になります）</bold></gold>");
        DEFAULT_MESSAGES.put("quest.withdrawn", "<yellow>依頼を取り下げ、報酬 {amount}円 を返金しました。</yellow>");
        DEFAULT_MESSAGES.put("quest.cannot-withdraw", "<gray>受注中またはクールダウン中の依頼は取り下げられません。</gray>");
        DEFAULT_MESSAGES.put("quest.accepted", "<green><bold>依頼を受注しました！目的地({world} {x}, {y}, {z})まで移動してください。</bold></green>");
        DEFAULT_MESSAGES.put("quest.accepted-notice", "<aqua>{player} があなたの依頼を受注しました。</aqua>");
        DEFAULT_MESSAGES.put("quest.abandoned", "<yellow>依頼を放棄しました。</yellow>");
        DEFAULT_MESSAGES.put("quest.not-acceptable", "<gray>この依頼は現在受注できません。</gray>");
        DEFAULT_MESSAGES.put("quest.team-post-prompt-reward", "<gold><bold>チャットにチーム依頼の「一人あたり」の報酬額を入力してください。（あなたの今の場所が目的地になります）</bold></gold>");
        DEFAULT_MESSAGES.put("quest.team-post-prompt-size", "<gold><bold>次に、必要な人数（2〜{max}人）をチャットに入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("quest.team-size-too-small", "<red>チーム依頼の人数は2人以上を指定してください。1人の場合は通常の依頼掲示をご利用ください。</red>");
        DEFAULT_MESSAGES.put("quest.team-size-too-large", "<red>チーム依頼の人数は最大{max}人までです。</red>");
        DEFAULT_MESSAGES.put("quest.team-joined", "<green><bold>チーム依頼に参加しました！目的地({world} {x}, {y}, {z})まで移動してください。（{joined}/{required}人）</bold></green>");
        DEFAULT_MESSAGES.put("quest.team-join-notice", "<aqua>{player} があなたのチーム依頼に参加しました。（{joined}/{required}人）</aqua>");
        DEFAULT_MESSAGES.put("quest.team-staffed", "<green><bold>チームの人数が揃いました！全員で目的地まで移動してください。</bold></green>");
        DEFAULT_MESSAGES.put("quest.team-left-recruiting", "<yellow>チーム依頼の参加を取り消しました。</yellow>");
        DEFAULT_MESSAGES.put("quest.team-abandoned", "<red>チームメンバーが離脱したため、依頼は募集中に戻りました。</red>");
        DEFAULT_MESSAGES.put("quest.team-withdraw-blocked", "<gray>参加者がいるチーム依頼は取り下げられません。全員が離脱するのを待ってください。</gray>");
        DEFAULT_MESSAGES.put("quest.team-arrived-waiting", "<yellow>目的地に到達しました。他のメンバーの到着を待っています。（{arrived}/{required}人）</yellow>");
        DEFAULT_MESSAGES.put("quest.team-completed", "<green><bold>【チーム依頼達成】 探索依頼を達成し、報酬 {amount}円 を受け取りました！</bold></green>");
        DEFAULT_MESSAGES.put("admin.search-player-prompt", "<gold><bold>チャットに検索したいプレイヤー名を入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("admin.give-prompt", "<gold><bold>付与する金額をチャットに入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("admin.take-prompt", "<gold><bold>没収する金額をチャットに入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("admin.setcredit-prompt", "<gold><bold>設定する信用スコア(0〜800)をチャットに入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("admin.reset-confirm", "<red><bold>本当にリセットする場合はもう一度クリックしてください。（15秒以内）</bold></red>");
        DEFAULT_MESSAGES.put("event.random-triggered", "<green>ランダム経済イベントを発生させました。</green>");
        DEFAULT_MESSAGES.put("event.boom-triggered", "<green>黄金ラッシュを発生させました。</green>");
        DEFAULT_MESSAGES.put("event.tax-triggered", "<green>手数料高騰を発生させました。</green>");
        DEFAULT_MESSAGES.put("event.bonus-triggered", "<green>ボーナス支給デーを発生させました。</green>");
        DEFAULT_MESSAGES.put("event.recession-triggered", "<green>冬の時代を発生させました。</green>");
        DEFAULT_MESSAGES.put("collateral.select-item", "<red>担保アイテムを選択してください。</red>");
        DEFAULT_MESSAGES.put("collateral.borrowed", "<green><bold>{item} を担保に {amount}円 を借り入れました。返済期限は {minutes}分後です。</bold></green>");
        DEFAULT_MESSAGES.put("collateral.repaid", "<green><bold>担保融資を完済し、アイテムを取り戻しました！</bold></green>");
        DEFAULT_MESSAGES.put("collateral.repay-insufficient", "<red>返済資金が足りません。（必要額: {amount}円）</red>");
        DEFAULT_MESSAGES.put("treasury.balance-gui", "<gold><bold>🏛 国庫残高: {amount}円</bold></gold>");
        DEFAULT_MESSAGES.put("treasury.balance-note", "<gray>法人税・オークション手数料などが積み立てられています。</gray>");
        DEFAULT_MESSAGES.put("donate.treasury-prompt", "<gold><bold>チャットに国庫へ寄付する金額を入力してください。（キャンセルする場合は「キャンセル」と入力）</bold></gold>");
        DEFAULT_MESSAGES.put("tutorial.completed", "<green>チュートリアルを完了しました！</green>");
        DEFAULT_MESSAGES.put("auction.list-price-prompt", "<gold>開始価格をチャット入力してください。「キャンセル」で返却します。</gold>");
        DEFAULT_MESSAGES.put("collateral.item-not-valid", "<red>そのアイテムは担保として評価できません。</red>");
        DEFAULT_MESSAGES.put("auction.listing-limit", "<red>同時出品数の上限（{count}件）に達しています。</red>");
        DEFAULT_MESSAGES.put("auction.listing-ended", "<red>この出品はすでに終了しています。</red>");
        DEFAULT_MESSAGES.put("auction.cannot-bid-own", "<red>自分の出品には入札できません。</red>");
        DEFAULT_MESSAGES.put("auction.items-inventory-full", "<yellow>インベントリが満杯のため {count} 個を受け取れませんでした。空きを作ってから /meco collect でもう一度お試しください。</yellow>");
        DEFAULT_MESSAGES.put("auction.cancel-has-bid", "<red>すでに入札が入っているため、この出品は取り下げられません。</red>");
        DEFAULT_MESSAGES.put("auction.cancel-success", "<green><bold>{item} の出品を取り下げました。（受取箱を確認してください）</bold></green>");
        DEFAULT_MESSAGES.put("auction.search-prompt", "<gold>検索したいアイテム名（の一部）をチャットに入力してください。「クリア」で検索を解除、「キャンセル」で閉じます。</gold>");
        DEFAULT_MESSAGES.put("auction.search-set", "<green>検索語を「{query}」に設定しました。</green>");
        DEFAULT_MESSAGES.put("auction.search-cleared", "<gray>検索条件をクリアしました。</gray>");
        DEFAULT_MESSAGES.put("auction.bid-prompt", "<gold><bold>チャットに入札額を入力してください。（最低 {amount}円 / キャンセルする場合は「キャンセル」と入力）</bold></gold>");
        DEFAULT_MESSAGES.put("insurance.already-active", "<red>すでに保険に加入中です。</red>");
        DEFAULT_MESSAGES.put("insurance.joined", "<green><bold>生命保険に加入しました！有効期間: {minutes}分</bold></green>");
        DEFAULT_MESSAGES.put("insurance.premium-insufficient", "<red>保険料（{amount}円）が足りません。</red>");
        DEFAULT_MESSAGES.put("personal.deposit-step", "<green>{amount}円 預金。</green>");
        DEFAULT_MESSAGES.put("personal.deposit-full", "<green>全額預金。</green>");
        DEFAULT_MESSAGES.put("personal.withdraw-step", "<red>{amount}円 引出。</red>");
        DEFAULT_MESSAGES.put("personal.withdraw-full", "<red>全額引出。</red>");
        DEFAULT_MESSAGES.put("personal.deposit-prompt", "<gold><bold>チャットに預金したい金額を入力してください。（キャンセルする場合は「キャンセル」と入力）</bold></gold>");
        DEFAULT_MESSAGES.put("personal.withdraw-prompt", "<gold><bold>チャットに引き出したい金額を入力してください。（キャンセルする場合は「キャンセル」と入力）</bold></gold>");
        DEFAULT_MESSAGES.put("loan.cannot-borrow-self-bank", "<red>自分自身の銀行から借りることはできません。</red>");
        DEFAULT_MESSAGES.put("loan.already-has-debt", "<red>既に他のプレイヤーからの借金があります。</red>");
        DEFAULT_MESSAGES.put("loan.plan-not-exist", "<red>このプランはすでに存在しません。</red>");
        DEFAULT_MESSAGES.put("loan.bank-capital-insufficient", "<red>この銀行の資本金プールが不足しています。</red>");
        DEFAULT_MESSAGES.put("loan.player-approved-notice", "<green><bold>【融資成立】 {player} があなたのプランから {amount}円 を借りました！</bold></green>");
        DEFAULT_MESSAGES.put("loan.player-repaid-full-plain", "<green>プレイヤー間の借金を完済しました！</green>");
        DEFAULT_MESSAGES.put("loan.repay-partial", "<yellow>{amount}円 を返済しました。</yellow>");
        DEFAULT_MESSAGES.put("loan.repay-full-insufficient", "<red>全額返済する資金が足りません。</red>");
        DEFAULT_MESSAGES.put("loan.gov-repaid-full-plain", "<green>国営公庫のローンを完済しました！</green>");
        DEFAULT_MESSAGES.put("loan.gov-repaid-full-bold", "<green><bold>国営公庫のローンを全額完済しました！</bold></green>");
        DEFAULT_MESSAGES.put("bank.already-established", "<red>すでに銀行を設立しています。</red>");
        DEFAULT_MESSAGES.put("bank.established", "<green><bold>銀行を設立しました！頭取パネルが開放されました。</bold></green>");
        DEFAULT_MESSAGES.put("bank.establish-cost-insufficient", "<red>設立費用（{amount}円）が足りません。</red>");
        DEFAULT_MESSAGES.put("bank.capital-added", "<green>資本金プールに +10,000円 追加。</green>");
        DEFAULT_MESSAGES.put("bank.capital-withdrawn", "<red>資本金プールから -10,000円 引き出し。</red>");
        DEFAULT_MESSAGES.put("bank.capital-insufficient-pool", "<red>資本金プールにそれだけの資金がありません。</red>");
        DEFAULT_MESSAGES.put("bank.plan-withdrawn", "<yellow>プラン({slot}枠目)を取り下げました。</yellow>");
        DEFAULT_MESSAGES.put("bank.no-published-plan", "<red>公開中のプランはありません。</red>");
        DEFAULT_MESSAGES.put("bank.plan-amount-prompt", "<gold><bold>チャットに融資額を入力してください。（キャンセルする場合は「キャンセル」と入力）</bold></gold>");
        DEFAULT_MESSAGES.put("bank.plan-interest-prompt", "<gold><bold>チャットに利息(%)を入力してください。（キャンセルする場合は「キャンセル」と入力）</bold></gold>");
        DEFAULT_MESSAGES.put("bank.plan-published", "<green><bold>融資プラン({slot}枠目)を市場に公開しました！</bold></green>");
        DEFAULT_MESSAGES.put("loan.gov-cap-exceeded", "<red>借入上限（{amount}円）を超えるため、これ以上借りられません。信用スコアを上げるか返済してください。</red>");
        DEFAULT_MESSAGES.put("deposit.already-exists", "<red>すでに定期預金が存在します。</red>");
        DEFAULT_MESSAGES.put("deposit.slot1-created", "<green>{amount}円 を定期預金に預けました（満期: {seconds}秒後 / 利率: +{rate}%）</green>");
        DEFAULT_MESSAGES.put("deposit.funds-insufficient", "<red>手持ち資金が {amount}円 足りません。</red>");
        DEFAULT_MESSAGES.put("deposit.slot1-none", "<red>定期預金がありません。</red>");
        DEFAULT_MESSAGES.put("deposit.slot1-not-matured", "<red>まだ満期を迎えていません。</red>");
        DEFAULT_MESSAGES.put("deposit.slot2-exists", "<red>すでに2枠目に定期預金が存在します。</red>");
        DEFAULT_MESSAGES.put("deposit.slot2-created", "<aqua>{amount}円 を2枠目の定期預金に預けました。</aqua>");
        DEFAULT_MESSAGES.put("deposit.slot2-none", "<red>2枠目に定期預金がありません。</red>");
        DEFAULT_MESSAGES.put("deposit.slot2-not-matured", "<red>2枠目はまだ満期を迎えていません。</red>");
        DEFAULT_MESSAGES.put("deposit.slot3-exists", "<red>すでに3枠目に定期預金が存在します。</red>");
        DEFAULT_MESSAGES.put("deposit.slot3-created", "<light_purple>{amount}円 を3枠目の定期預金に預けました。</light_purple>");
        DEFAULT_MESSAGES.put("deposit.slot3-none", "<red>3枠目に定期預金がありません。</red>");
        DEFAULT_MESSAGES.put("deposit.slot3-not-matured", "<red>3枠目はまだ満期を迎えていません。</red>");
        DEFAULT_MESSAGES.put("quest.completed", "<green><bold>【依頼達成】 探索依頼を達成し、報酬 {amount}円 を受け取りました！</bold></green>");
        DEFAULT_MESSAGES.put("quest.completed-notice", "<aqua>{player} があなたの依頼を達成しました。</aqua>");
        DEFAULT_MESSAGES.put("quest.relisted", "<aqua>あなたの依頼が再出品されました。（報酬 {amount}円 を再徴収）</aqua>");
        DEFAULT_MESSAGES.put("quest.removed-insufficient-funds", "<red>資金不足のため、依頼ボードから依頼を取り下げました。</red>");
        DEFAULT_MESSAGES.put("auction.listing-cancelled-returned", "<gray>【オークションハウス】 出品手続き中だったアイテムを返却しました。</gray>");
        DEFAULT_MESSAGES.put("loan.login-seized-full", "<green><bold>[完済] ログイン時に借金がすべて徴収されました。</bold></green>");
        DEFAULT_MESSAGES.put("loan.login-seized-partial", "<red><bold>[執行]</bold> 借金 <yellow>{amount}円</yellow> 強制徴収。</red>");
        DEFAULT_MESSAGES.put("loan.guarantor-seized-notice", "<dark_red><bold>【保証債務】</bold> {player} の借金返済不能につき、保証人として {amount}円 が徴収され、信用スコアが低下しました。</dark_red>");
        DEFAULT_MESSAGES.put("loan.guarantor-repaid-full", "<green><bold>[完済] 保証人により借金が完済されました。</bold></green>");
        DEFAULT_MESSAGES.put("insurance.claim-cooldown", "<gray>[保険] クールダウン中のため、今回の死亡では保険金は支払われませんでした。</gray>");
        DEFAULT_MESSAGES.put("input.cancelled-auction-item-returned", "<gray>出品をキャンセルし、アイテムを返却しました。</gray>");
        DEFAULT_MESSAGES.put("input.cancelled", "<gray>入力をキャンセルしました。</gray>");
        DEFAULT_MESSAGES.put("admin.player-not-found", "<red>プレイヤー「{name}」が見つかりませんでした。</red>");
        DEFAULT_MESSAGES.put("quest.reward-insufficient", "<red>所持金が足りません。</red>");
        DEFAULT_MESSAGES.put("quest.posted", "<green><bold>依頼を掲示しました！報酬 {amount}円 をエスクローしました。</bold></green>");
        DEFAULT_MESSAGES.put("worldstock.search-prompt", "<gold><bold>チャットにティッカーシンボルを入力してください。（例: AAPL, TSLA, 7203.T）</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.invalid-symbol", "<red>ティッカーシンボルの形式が正しくありません。</red>");
        DEFAULT_MESSAGES.put("worldstock.searching", "<gray>{symbol} の株価を取得中...</gray>");
        DEFAULT_MESSAGES.put("worldstock.not-found", "<red>{symbol} が見つかりませんでした。ティッカーシンボルを確認するか、しばらく経ってから再度お試しください。</red>");
        DEFAULT_MESSAGES.put("worldstock.funds-insufficient", "<red>手持ち資金が足りません。</red>");
        DEFAULT_MESSAGES.put("worldstock.not-owned", "<red>この銘柄を保有していません。</red>");
        DEFAULT_MESSAGES.put("worldstock.bought", "<green><bold>{symbol} を {qty}株 購入しました。（{amount}円 / 単価 {native} / 手数料 {fee}円）</bold></green>");
        DEFAULT_MESSAGES.put("worldstock.sold", "<green><bold>{symbol} を {qty}株 売却しました。（{amount}円 / 単価 {native} / 損益: {pnl}円 / 手数料 {fee}円）</bold></green>");
        DEFAULT_MESSAGES.put("worldstock.sell-not-enough-shares", "<red>売却したい株数が保有数を超えています。（保有: {owned}株 / 指定: {requested}株）部分約定はしません。</red>");
        DEFAULT_MESSAGES.put("worldstock.bulk-qty-too-large", "<red>一度に取引できる株数の上限（{max}株）を超えています。</red>");
        DEFAULT_MESSAGES.put("worldstock.bulk-buy-prompt", "<gold><bold>{symbol} を購入する株数をチャットに入力してください。（例: 25）</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.bulk-sell-prompt", "<gold><bold>{symbol} を売却する株数をチャットに入力してください。（例: 25）</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.cooldown", "<red>この銘柄は取引後 {seconds}秒経過するまで再度取引できません。</red>");
        DEFAULT_MESSAGES.put("worldstock.daily-count-limit", "<red>本日の世界株取引回数の上限（{count}回）に達しました。また明日お試しください。</red>");
        DEFAULT_MESSAGES.put("worldstock.daily-amount-limit", "<red>本日の世界株取引金額の上限（{amount}円）に達するため、この取引はできません。</red>");
        DEFAULT_MESSAGES.put("worldstock.daily-profit-limit", "<red>本日の世界株実現利益の上限（{amount}円）に達したため、これ以上の利益確定売りはできません。</red>");
        DEFAULT_MESSAGES.put("worldstock.alert-prompt", "<gold><bold>{symbol} の値動きアラート閾値(%)をチャットに入力してください。（例: 5 で ±5% 変動時に通知）</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.alert-set", "<green><bold>{symbol} に値動きアラートを設定しました。（現在値から ±{percent}% 変動で通知）</bold></green>");
        DEFAULT_MESSAGES.put("worldstock.alert-cancelled", "<gray>{symbol} の値動きアラートを解除しました。</gray>");
        DEFAULT_MESSAGES.put("worldstock.alert-triggered", "<gold><bold>🔔 {symbol} が基準値から{percent}%以上変動しました！（現在値: {price}）</bold></gold>");
        DEFAULT_MESSAGES.put("worldstock.dividend-paid", "<green>世界株の配当金として {amount}円 を受け取りました。</green>");
        DEFAULT_MESSAGES.put("resourceshop.not-enough-items", "<red>{material}が足りません。（所持: {have}個 / 必要: {need}個）</red>");
        DEFAULT_MESSAGES.put("resourceshop.funds-insufficient", "<red>手持ち資金が足りません。</red>");
        DEFAULT_MESSAGES.put("resourceshop.sold", "<green><bold>{material}を{qty}個売却しました。（{amount}円）</bold></green>");
        DEFAULT_MESSAGES.put("resourceshop.bought", "<green><bold>{material}を{qty}個購入しました。（{amount}円）</bold></green>");
        DEFAULT_MESSAGES.put("resourceshop.sell-qty-prompt", "<gold><bold>{material}を売る個数をチャットに入力してください。（例: 40）</bold></gold>");
        DEFAULT_MESSAGES.put("resourceshop.buy-qty-prompt", "<gold><bold>{material}を買う個数をチャットに入力してください。（例: 40）</bold></gold>");
        DEFAULT_MESSAGES.put("lottery.funds-insufficient", "<red>手持ち資金が足りません。</red>");
        DEFAULT_MESSAGES.put("lottery.ticket-bought", "<green><bold>宝くじチケットを{count}枚購入しました。（{amount}円）</bold></green>");
        DEFAULT_MESSAGES.put("lottery.buy-qty-prompt", "<gold><bold>購入するチケットの枚数をチャットに入力してください。（例: 3）</bold></gold>");
        DEFAULT_MESSAGES.put("merchant.funds-insufficient", "<red>手持ち資金が足りません。</red>");
        DEFAULT_MESSAGES.put("merchant.bought", "<green><bold>{item} を購入しました。（{amount}円）</bold></green>");
        DEFAULT_MESSAGES.put("merchant.location-hint", "<gold><bold>巡回商人は現在 {world} ({x}, {y}, {z}) 付近にいます。</bold></gold>");
        DEFAULT_MESSAGES.put("treasure.found", "<gold><bold>【埋蔵金発見】 埋蔵金チェストを発見し、{amount}円 を手に入れました！</bold></gold>");
        DEFAULT_MESSAGES.put("merchant.installment-purchased", "<green><bold>{item} を分割払いで購入しました！初回 {first}円を支払いました。（残り{count}回中 {remaining}回、各{each}円）</bold></green>");
        DEFAULT_MESSAGES.put("installment.paid", "<green>分割払い: {item} の {amount}円 を引き落としました。（残り{remaining}回）</green>");
        DEFAULT_MESSAGES.put("installment.completed", "<green><bold>分割払い: {item} の支払いが完了しました！</bold></green>");
        DEFAULT_MESSAGES.put("installment.missed", "<red>分割払い: {item} の {amount}円 が引き落とせませんでした。信用スコアが減少します。</red>");
        DEFAULT_MESSAGES.put("auction.bid-too-low", "<red>入札額が低すぎます。（最低 {amount}円）</red>");
        DEFAULT_MESSAGES.put("auction.buyout-purchased", "<green><bold>即決価格で購入しました！</bold></green>");
        DEFAULT_MESSAGES.put("auction.bid-placed", "<green><bold>{item} に {amount}円 で入札しました。</bold></green>");
        DEFAULT_MESSAGES.put("personal.deposit-custom", "<green><bold>{amount}円 を預金しました。</bold></green>");
        DEFAULT_MESSAGES.put("personal.withdraw-insufficient", "<red>預金残高が足りません。（残高: {amount}円）</red>");
        DEFAULT_MESSAGES.put("personal.withdraw-custom", "<green><bold>{amount}円 を引き出しました。</bold></green>");
        DEFAULT_MESSAGES.put("bank.plan-amount-set", "<green>融資額を {amount}円 に設定しました。</green>");
        DEFAULT_MESSAGES.put("bank.plan-interest-set", "<green>利息を {amount}% に設定しました。</green>");
        DEFAULT_MESSAGES.put("auction.draft-not-found", "<red>出品するアイテムが見つかりませんでした。もう一度お試しください。</red>");
        DEFAULT_MESSAGES.put("auction.buyout-prompt", "<gold><bold>即決価格(Buy Now)を入力してください。設定しない場合は 0 と入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("auction.listing-info-not-found", "<red>出品情報が見つかりませんでした。もう一度お試しください。</red>");
        DEFAULT_MESSAGES.put("auction.buyout-too-low", "<red>即決価格は開始価格（{amount}円）より高く設定してください。出品をキャンセルします。</red>");
        DEFAULT_MESSAGES.put("auction.listed", "<green><bold>{item} をオークションに出品しました！（開始価格: {amount}円{buyoutMsg} / 期間: {minutes}分）</bold></green>");
        DEFAULT_MESSAGES.put("storage.funds-insufficient", "<red>手持ち資金が足りません。</red>");
        DEFAULT_MESSAGES.put("storage.rented", "<green><bold>レンタル倉庫を契約しました！（家賃 {amount}円 / {hours}時間ごと）</bold></green> <red>家賃の支払いに1回でも失敗すると、契約は失効し倉庫の中身は全て没収されますのでご注意ください。</red>");
        DEFAULT_MESSAGES.put("storage.rent-paid", "<green>レンタル倉庫の家賃 {amount}円 を支払いました。</green>");
        DEFAULT_MESSAGES.put("storage.forfeited", "<red><bold>【レンタル倉庫】家賃の支払いに失敗したため契約が失効し、倉庫の中身は全て没収されました。</bold></red>");
        DEFAULT_MESSAGES.put("storage.not-renting", "<red>レンタル倉庫を契約していません。</red>");
        DEFAULT_MESSAGES.put("group.created", "<green><bold>グループ貯金箱「{name}」を作成しました！（ID: {id}）</bold></green>");
        DEFAULT_MESSAGES.put("group.create-funds-insufficient", "<red>作成費用が不足しています。（必要額: {amount}円）</red>");
        DEFAULT_MESSAGES.put("group.create-name-prompt", "<gold><bold>作成するグループ貯金箱の名前をチャットに入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("group.not-found", "<red>指定したグループ貯金箱が見つかりません。</red>");
        DEFAULT_MESSAGES.put("group.not-owner", "<red>この操作はオーナーのみ実行できます。</red>");
        DEFAULT_MESSAGES.put("group.already-member", "<red>そのプレイヤーは既にメンバーです。</red>");
        DEFAULT_MESSAGES.put("group.full", "<red>メンバー数が上限に達しています。</red>");
        DEFAULT_MESSAGES.put("group.invited", "<green><bold>{player} をグループ貯金箱に招待しました。</bold></green>");
        DEFAULT_MESSAGES.put("group.invite-received", "<gold><bold>【グループ貯金箱】</bold> {player} があなたを「{group}」に招待しました。</gold>");
        DEFAULT_MESSAGES.put("group.kicked", "<yellow>{player} をグループ貯金箱から追放しました。</yellow>");
        DEFAULT_MESSAGES.put("group.kicked-notice", "<yellow>【グループ貯金箱】</yellow> 「{group}」のメンバー構成が変更されました。");
        DEFAULT_MESSAGES.put("group.owner-cannot-leave", "<red>オーナーは脱退できません。解散する場合は /meco group disband <名前> を使用してください。</red>");
        DEFAULT_MESSAGES.put("group.left", "<yellow>「{group}」から脱退しました。</yellow>");
        DEFAULT_MESSAGES.put("group.disbanded", "<gold><bold>グループ貯金箱が解散されました。残高から {amount}円 が振り込まれました。</bold></gold>");
        DEFAULT_MESSAGES.put("group.disband-confirm", "<red><bold>本当にグループ貯金箱を解散しますか？</bold></red> <yellow>残高はメンバー全員に均等割りされます。30秒以内にもう一度クリックで確定します。</yellow>");
        DEFAULT_MESSAGES.put("group.deposit-prompt", "<gold><bold>入金する金額をチャットに入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("group.deposited", "<green><bold>グループ貯金箱へ {amount}円 を入金しました。</bold></green>");
        DEFAULT_MESSAGES.put("group.withdraw-prompt", "<gold><bold>引き出す金額をチャットに入力してください。</bold></gold>");
        DEFAULT_MESSAGES.put("group.withdraw-insufficient", "<red>グループ貯金箱の残高が足りません。（残高: {amount}円）</red>");
        DEFAULT_MESSAGES.put("group.withdrawn", "<green><bold>グループ貯金箱から {amount}円 を引き出しました。</bold></green>");
        DEFAULT_MESSAGES.put("help.group", "<yellow>/meco group <create|invite|kick|leave|disband></yellow> <gray>- 複数人で共有するグループ貯金箱を管理する</gray>");
        DEFAULT_MESSAGES.put("fund.created", "<green><bold>共同投資ファンド「{name}」を作成しました！（ID: {id}）</bold></green>");
        DEFAULT_MESSAGES.put("fund.create-funds-insufficient", "<red>作成費用が不足しています。（必要額: {amount}円）</red>");
        DEFAULT_MESSAGES.put("fund.not-found", "<red>指定した共同投資ファンドが見つかりません。</red>");
        DEFAULT_MESSAGES.put("fund.not-manager", "<red>この操作はファンドのマネージャーのみ実行できます。</red>");
        DEFAULT_MESSAGES.put("fund.already-contributor", "<red>そのプレイヤーは既に出資者です。</red>");
        DEFAULT_MESSAGES.put("fund.full", "<red>出資者数が上限に達しています。</red>");
        DEFAULT_MESSAGES.put("fund.invited", "<green><bold>{player} を共同投資ファンドに招待しました。</bold></green>");
        DEFAULT_MESSAGES.put("fund.invite-received", "<gold><bold>【共同投資ファンド】</bold> {player} があなたを「{fund}」に招待しました。/meco fund contribute {fund} <金額> で出資できます。</gold>");
        DEFAULT_MESSAGES.put("fund.not-invited", "<red>そのファンドの出資者(またはマネージャー)ではありません。先に招待してもらう必要があります。</red>");
        DEFAULT_MESSAGES.put("fund.contributed", "<green><bold>共同投資ファンド「{fund}」へ {amount}円 出資しました。</bold></green>");
        DEFAULT_MESSAGES.put("fund.contribute-funds-insufficient", "<red>手持ち資金が足りません。（所持金: {amount}円）</red>");
        DEFAULT_MESSAGES.put("fund.redeemed", "<green><bold>共同投資ファンドから解約し、持分 {amount}円 を受け取りました。</bold></green>");
        DEFAULT_MESSAGES.put("fund.redeem-confirm", "<red><bold>本当に持分を全額解約しますか？</bold></red> <yellow>もう一度クリックすると確定します(30秒以内)。</yellow>");
        DEFAULT_MESSAGES.put("fund.redeem-insufficient-cash", "<red>ファンドの現金残高が不足しているため解約できません。マネージャーに保有銘柄の売却を依頼してください。</red>");
        DEFAULT_MESSAGES.put("fund.disband-has-holdings", "<red>保有銘柄が残っているため解散できません。先に運用画面ですべて売却してください。</red>");
        DEFAULT_MESSAGES.put("fund.disbanded", "<gold><bold>共同投資ファンドが解散されました。持分に応じて {amount}円 が振り込まれました。</bold></gold>");
        DEFAULT_MESSAGES.put("fund.trade-funds-insufficient", "<red>ファンドの現金残高が足りません。</red>");
        DEFAULT_MESSAGES.put("help.fund", "<yellow>/meco fund <create|invite|contribute|redeem|disband></yellow> <gray>- 複数人で出資し合う共同投資ファンドを管理する</gray>");
        DEFAULT_MESSAGES.put("vip.not-eligible", "<red>VIP限定です。信用スコアを上げてシルバー会員以上になると利用できます。</red>");
        DEFAULT_MESSAGES.put("vip.shop-bought", "<green><bold>VIP限定ショップで {item} を購入しました。（{amount}円）</bold></green>");
        DEFAULT_MESSAGES.put("vip.shop-funds-insufficient", "<red>手持ち資金が足りません。</red>");
        DEFAULT_MESSAGES.put("vip.stipend-claimed", "<green><bold>日次VIP手当（{tier}）として {amount}円 を受け取りました。</bold></green>");
        DEFAULT_MESSAGES.put("vip.stipend-cooldown", "<red>日次VIP手当は受取済みです。次回受取まであと {time}。</red>");
        DEFAULT_MESSAGES.put("vip.stipend-treasury-empty", "<red>国庫残高が不足しているため、現在は日次VIP手当を支給できません。しばらく経ってからお試しください。</red>");
        DEFAULT_MESSAGES.put("vip.stipend-disabled", "<red>日次VIP手当は現在無効化されています。</red>");
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
                throw new RuntimeException("SQLiteトランザクション開始に失敗しました", e);
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
                throw new RuntimeException("SQLiteへのコミットに失敗しました", e);
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
                throw new RuntimeException("SQLite書込に失敗しました: " + section + "/" + path, e);
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
                throw new RuntimeException("SQLite読込に失敗しました: " + section + "/" + path, e);
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
                throw new RuntimeException("ItemStackのシリアライズに失敗しました: " + section + "/" + path, e);
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
                    throw new RuntimeException("ItemStackのデシリアライズに失敗しました: " + section + "/" + path, e);
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
                throw new RuntimeException("ItemStackリストのシリアライズに失敗しました: " + section + "/" + path, e);
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
                throw new RuntimeException("ItemStackリストのデシリアライズに失敗しました: " + section + "/" + path, e);
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
                throw new RuntimeException("SQLite getKeysに失敗しました: " + section + "/" + pathPrefix, e);
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
                throw new RuntimeException("SQLite削除に失敗しました: " + section + "/" + path, e);
            }
        }

        void removeSection(String section) {
            try (PreparedStatement ps = this.conn.prepareStatement("DELETE FROM plugin_data WHERE section=?");){
                ps.setString(1, section);
                ps.executeUpdate();
            }
            catch (SQLException e) {
                throw new RuntimeException("SQLiteセクション削除に失敗しました: " + section, e);
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
                throw new RuntimeException("SQLite部分削除に失敗しました: " + section + "/" + pathPrefix, e);
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

