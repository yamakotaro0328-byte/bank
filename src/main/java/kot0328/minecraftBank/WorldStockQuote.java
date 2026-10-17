package kot0328.minecraftBank;

class WorldStockQuote {
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
