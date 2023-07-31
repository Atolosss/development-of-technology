package lesson_oop_homework.part2;

public class SmartLamp implements SmartDevice {

    private int brightness;
    private boolean currentState;

    public void setBrightness(int brightness) {
        if (brightness >= 0 && brightness <= 100) {
            this.brightness = brightness;
        }
    }

    @Override
    public void turnOn() {
        currentState = true;

    }

    @Override
    public void turnOff() {
        currentState = false;

    }

    @Override
    public <config> void setConfiguration() {

    }

    @Override
    public void getCurrentState() {

    }
}
