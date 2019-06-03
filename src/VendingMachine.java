import java.util.List;

public interface VendingMachine {

    //functiile se implementeaza in VendingMachineImpl class
    int selectItemAndGetPrice(Item item) throws SoldOutException;


    void insertCoin(Coin coin);

    List<Coin> refund() throws NotSufficientChangeException;

    // "NotSufficientChangeException"


    PurchaseAndCoins<Item, List<Coin>> collectItemAndChange() throws NotSufficientChangeException, NotFullyPaidException;
    //"NotSufficientChangeException""NotSufficientPayedEx"


    void reset();


}