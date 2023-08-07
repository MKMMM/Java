import javax.print.Doc;

class Army {

    public static void createArmy() {
        // Create all objects here
        Unit myUnit = new Unit("Somename1");
        Unit myUnit1 = new Unit("Somename2");
        Unit myUnit2= new Unit("Somename3");
        Unit myUnit3 = new Unit("Somename3");
        Unit myUnit4 = new Unit("Somename4");

        Knight myKnight1 = new Knight("Mknight1");
        Knight myKnight2 = new Knight("Mknight2");
        Knight myKnight3 = new Knight("Mknight3");

        General myGeneral = new General("Zhukov");
        Doctor myDoctor = new Doctor("Zhibvago");

    }


    // Don't change the code below
    static class Unit {
        static String nameUnit;
        static int countUnit;

        public Unit(String name) {
            countUnit++;
            nameUnit = name;

        }
    }

    static class Knight {
        static String nameKnight;
        static int countKnight;

        public Knight(String name) {
            countKnight++;
            nameKnight = name;

        }
    }

    static class General {
        static String nameGeneral;
        static int countGeneral;

        public General(String name) {
            countGeneral++;
            nameGeneral = name;

        }
    }

    static class Doctor {
        static String nameDoctor;
        static int countDoctor;

        public Doctor(String name) {
            countDoctor++;
            nameDoctor = name;

        }
    }

    public static void main(String[] args) {
        createArmy();
        System.out.println(Unit.countUnit);
        System.out.println(Knight.countKnight);
        System.out.println(General.countGeneral);
        System.out.println(Doctor.countDoctor);
    }

}