/**
 * Created: Nov 03, 2023
 * this class is used to program a bakery company which can have multiple locations and employees
 * as the company owner you can hire or fire employees and open new shops
 *
 * program used for task E3 (code review)
 */
public class BakeryCompany {

    public String name;
    public double numberOfEmployees;
    public float numberOfLocations;



    public BakeryCompany(String name) {
        this.name = name;
        this.numberOfLocations = 1;
    }


    public void openNewShop() {
        this.numberOfLocations++;
    }

    public double getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public void hireEmployee() {
        this.numberOfEmployees++;
    }

    public void fireEmployee() {
        this.numberOfEmployees--;
    }

    public static void main(String[] args) {

        BakeryCompany myBakery = new BakeryCompany("Don Pedro's Bakery");

        myBakery.hireEmployee();
        System.out.println(myBakery.getNumberOfEmployees());

        // open 10 new shops
        for (int i = 0; i < 10; i++) {
            myBakery.openNewShop();
        }

        System.out.println(myBakery.numberOfLocations);


    }
}

