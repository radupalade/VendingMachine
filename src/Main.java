

public class Main {

    public static void main(String[] args) throws SoldOutException, NotFullyPaidException, NotSufficientChangeException {

        VendingMachineImpl vm = new VendingMachineImpl();

        vm.printStats();

        vm.insertCoin(Coin.TEN);
        vm.insertCoin(Coin.FIFTY);
        vm.printStats();
        vm.insertCoin(Coin.FIFTY);
        vm.printStats();
        vm.collectItemAndChange();
        vm.printStats();


    }
}
