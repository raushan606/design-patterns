package com.raushan.state;

// Concrete State
public class IdleState implements MachineState {
    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Item Selected: " + itemCode);
        context.setSelectedItem(itemCode);
        context.setState(new ItemSelectedState());
    }

    @Override
    public void insertCoin(VendingMachine context, int amount) {
        System.out.println("Please select an item first.");
    }

    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("No item selected.");
    }
}
