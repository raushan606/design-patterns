package com.raushan.state;

// State Interface
public interface MachineState {
    // Methods for each state
    void selectItem(VendingMachine context, String itemCode);

    void insertCoin(VendingMachine context, int amount);

    void dispenseItem(VendingMachine context);
}
