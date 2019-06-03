import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class VendingMachineImpl implements VendingMachine {

    Inventory<Coin> cashInventory;
    Inventory<Item> itemInventory;
    long totalSales;
    Item currentItem;
    long currentBalance;

    /*public VendingMachineImpl(Inventory<Coin> cashInventory, Inventory<Item> itemInventory, long totalSales, Item currentItem, long currentBalance) {
        this.cashInventory = cashInventory;
        this.itemInventory = itemInventory;
        this.totalSales = totalSales;
        this.currentItem = currentItem;
        this.currentBalance = currentBalance;
    }*/

    public void initialize() {
        itemInventory = new Inventory<>();
        itemInventory.put(Item.BOUNTY, 4);
        itemInventory.put(Item.MARS, 6);
        itemInventory.put(Item.TWIX, 4);

        cashInventory = new Inventory<>();

        cashInventory.put(Coin.FIFTY, 4);
        cashInventory.put(Coin.FIVE, 4);
        cashInventory.put(Coin.TEN, 4);
        cashInventory.put(Coin.TWENTY, 4);
    }

    @Override
    public int selectItemAndGetPrice(Item item) throws SoldOutException {

        if (itemInventory.hasItem(item)) {
            currentItem = item;
            return item.getPrice();
        } else throw new SoldOutException("Sold out!");
    }

    /* public long selectItemAndGetPrice(Item item) throws SoldOutException{

        }
    */
    @Override
    public void insertCoin(Coin coin) {
        cashInventory.add(coin); // add coins
        currentBalance += coin.getValue();
    }

    @Override
    public List<Coin> refund() throws NotSufficientChangeException {
        return null;
    }

    void updateCashInventory(List<Coin> change) {
        for (Coin c : change) {
            cashInventory.decrease(c);
        }

    }

    boolean isFullyPaid() {
        return true;
    }

    List<Coin> getChange(long amount) {
        //calculate the amount of money for change
        List<Coin> changes = Collections.EMPTY_LIST;
        changes = new LinkedList<>();
        long balance = amount;
        while (balance > 0) {
            if (balance >= Coin.FIFTY.getValue() && cashInventory.hasItem(Coin.FIFTY)) {
                changes.add((Coin.FIFTY));
                balance = balance - Coin.FIFTY.getValue();
                continue;
            } else if (balance >= Coin.TWENTY.getValue() && cashInventory.hasItem((Coin.TWENTY))) {
                changes.add(Coin.TWENTY);
                balance = balance - Coin.TWENTY.getValue();
                continue;
            } else if (balance >= Coin.TEN.getValue() && cashInventory.hasItem((Coin.TEN))) {
                changes.add(Coin.TEN);
                balance = balance - Coin.TEN.getValue();
                continue;
            } else if (balance >= Coin.FIVE.getValue() && cashInventory.hasItem((Coin.FIVE))) {
                changes.add(Coin.FIVE);
                balance = balance - Coin.FIVE.getValue();
                continue;
            } else try {
                throw new NotSufficientChangeException(("Not Sufficient Change"));
            } catch (NotSufficientChangeException e) {
                e.printStackTrace();
            }
        }
        return changes;
        //throws NotSufficientChangeException

    }

    boolean hasSufficientChangeForAmount(long amount) {
// TRY to getChange() for the current amount
/* CATCH NotSufficientChangeException exception
--- return 'true' if the change exists, 'false' otherwise*/
        getChange(amount);
        return false;
    }

    boolean hasSufficientChange() {
       /* ---returns 'true' in case it
        has sufficient change for (currentBalance - currentItem_price)
            --Item collectItem()
                -- - check if it 's fully paid and if VM has enough change
                -- - decrease inventory for the 'currentItem'
                -- - return 'currentItem'
                -- - throws'NotSufficientChangeException' or 'NotFullyPaidException'
    */
        return currentBalance - currentItem.getPrice() > 0;
    }

    private Item collectItem() throws NotFullyPaidException {
        if (isFullyPaid() && hasSufficientChange()) {
            itemInventory.decrease(currentItem);
            return currentItem;
        } else
            throw new NotFullyPaidException(("Not fully paid! "), currentBalance - currentItem.getPrice());
    }

    List<Coin> collectChange() throws NotSufficientChangeException {
        List<Coin> refund = getChange(currentBalance);
        updateCashInventory(refund);
        currentBalance = 0;
        currentItem = null;

        return refund;
/*
--- calculate the amount of money to be returned
--- call getChange() method to fill the list of 'Coin' objects to be returned
--- updateCashInventory()
--- reset 'currentBalance'
            --- reset 'currentItem'
            --- return change as a List of 'Coin' objects
--- throws NotSufficientChangeException
*/
    }

    @Override
    public PurchaseAndCoins<Item, List<Coin>> collectItemAndChange() throws NotSufficientChangeException, NotFullyPaidException {

        totalSales += currentItem.getPrice();
        Item item = collectItem();
        List<Coin> listOfCoins = collectChange();
        return new PurchaseAndCoins<>(item, listOfCoins);
        /*---update 'totalSales' with the price of 'currentItem'
                -- - call 'collectItem()' and assign its returned value to an 'Item' object
                -- - call 'collectChange()' and assign its returned value to a List of 'Coin' objects
                -- - create a new 'PurchaseAndCoins' with 'item' and 'list_of_coins' and return it
                -- - throws'NotSufficientChangeException' and 'NotFullyPaidException'
                -- List<Coin> refund
    */
    }

    /*--- call getChange(currentBalance) and assign it to a List<Coin> refund{

        }
    --- update cash inventory
    --- reset 'currentBalance'
                --- reset 'currentItem'
                --- return refund
    --- throws NotSufficientChangeException
    --*/
    public void reset() {
        itemInventory.clear();
        cashInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }

    public void printStats() {
        System.out.println("total sales: " + totalSales);
        System.out.println("current item inv: " + itemInventory);
        System.out.println("current cash inv: " + cashInventory);
    }
}