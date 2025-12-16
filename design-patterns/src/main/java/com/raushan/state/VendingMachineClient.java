package com.raushan.state;

public class VendingMachineClient {
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();

        vm.selectItem("Candy");
        vm.insertCoin(10);
        vm.dispenseItem();
        
        vm.selectItem("Chips");
        vm.insertCoin(5);
        vm.dispenseItem();
    }
}
