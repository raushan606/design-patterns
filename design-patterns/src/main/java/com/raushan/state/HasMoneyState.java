package com.raushan.state;

public class HasMoneyState implements MachineState {

    @Override
    public void selectItem(VendingMachine context, String itemCode) {
        System.out.println( "Item Selected: " + itemCode);
    }

    @Override
    public void insertCoin(VendingMachine context, int amount) {
        System.out.println( "Inserted: " + amount);
    }

    @Override
    public void dispenseItem(VendingMachine context) {
        System.out.println( "Dispensing item... " + context.getSelectedItem());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Item Dispensed.");
        context.reset();
    }
}
