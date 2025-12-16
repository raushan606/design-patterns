package com.raushan.state;

// Context Class
public class VendingMachine {

    private MachineState state;
    private int coinsInserted;
    private String selectedItem;

    VendingMachine() {
        this.state = new IdleState();
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public int getCoinsInserted() {
        return coinsInserted;
    }

    public void setCoinsInserted(int coinsInserted) {
        this.coinsInserted = coinsInserted;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void selectItem(String item) {
        state.selectItem(this, item);
    }

    public void insertCoin(int coin) {
        state.insertCoin(this, coin);
    }

    public void dispenseItem() {
        state.dispenseItem(this);
    }

    void reset() {
        this.selectedItem = null;
        this.coinsInserted = 0;
        this.setState(new IdleState());
    }
}
