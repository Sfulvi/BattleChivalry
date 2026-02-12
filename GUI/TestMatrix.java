package GUI;

public class TestMatrix {
    private String[][] battlefield = {
        {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"},
        {"X", "cavalry", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "infantry", "cavalry", "X"},
        {"catapult", "X", "infantry", "X", "X", "X", "X", "X", "X", "X", "X", "X", "special", "archer", "X"},
        {"engineer", "archer", "special", "X", "X", "X", "X", "X", "X", "X", "X", "X", "special", "archer", "X"},
        {"X", "arthur", "infantry", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "leonidas", "catapult"},
        {"catapult", "archer", "special", "X", "X", "X", "X", "X", "X", "X", "X", "X", "special", "archer", "engineer"},
        {"engineer", "X", "infantry", "X", "X", "X", "X", "X", "X", "X", "X", "X", "special", "archer", "X"},
        {"X", "cavalry", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "infantry", "cavalry", "X"},
        {"X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X", "X"}

    };

    private String[][] test2 = {
        {"X","cavalry","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        {"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        {"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        {"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        {"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        {"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        {"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        {"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        {"X","X","X","X","X","X","X","X","X","X","X","X","X","X","X"},
        
    };



    public String[][] getUnitsMatrix() {
        return battlefield;
    }

    public String[][] getTest2() {
        return test2;
    }
}