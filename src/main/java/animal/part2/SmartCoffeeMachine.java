package animal.part2;

public class SmartCoffeeMachine implements SmartDevice{

    private String coffeeType;

    private boolean currentState;
    @Override
    public void turnOn() {
        currentState = true;

    }

    @Override
    public void turnOff() {
        currentState=false;

    }

    @Override
    public  void setConfiguration() {

    }

    @Override
    public void getCurrentState() {
        System.out.println(currentState);
    }
}
