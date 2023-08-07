
class Car {

    int yearModel;
    String make;
    int speed = 0;

    public void accelerate() {
        this.speed += 5;
    }

    public void brake() {
        this.speed -= 5;
        if (this.speed < 5) {
            this.speed = 0; // reset speed to 0 if it's negative because  of reasons wtf
        }
    }

}