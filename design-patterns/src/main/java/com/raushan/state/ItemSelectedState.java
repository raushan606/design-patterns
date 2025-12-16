package com.raushan.state;

public class ItemSelectedState implements MachineState {
    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println("Item already selected: " + context.getSelectedItem());
    }

    @Override
    public void insertCoin(VendingMachine context, int amount) {
        System.out.println("Inserted: " + amount + "for item: " + context.getSelectedItem());
        context.setCoinsInserted(amount);
        context.setState(new HasMoneyState());
    }

    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println("Insert coin to dispense item.");
    }
}
