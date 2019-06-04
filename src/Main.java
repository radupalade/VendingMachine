

public class Main {

    public static void main(String[] args) throws SoldOutException, NotFullyPaidException, NotSufficientChangeException {

        VendingMachineImpl vm = new VendingMachineImpl();

        vm.printStats();
        vm.selectItemAndGetPrice(Item.TWIX);
        vm.insertCoin(Coin.TEN);
        vm.insertCoin(Coin.FIFTY);
        vm.collectItemAndChange();
        vm.printStats();


    }
}
