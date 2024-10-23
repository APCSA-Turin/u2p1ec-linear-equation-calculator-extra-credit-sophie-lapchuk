package com.example.project;
public class LinearCalculator{
    //FOR EXTRA CREDIT 
    //you should copy and paste all of your code from the LinearCalculator class
    // but NOT printInfo(). Please update it below
        //INSTANCE VARIABLES 
    //4 INTEGER variables (name them: x1,x2,y1,y2) 
    int x1;
    int x2;
    int y1;
    int y2;


    //CONSTRUCTOR
    //1 constructor with 2 String parameters. Each parameter represents a coordinate. 
    //For example, "(1,2)" and "(3,4)" would be two parameter values 
    //You will have to parse the string into 4 integers, representing the 2 points.
    public LinearCalculator(String coordinate1, String coordinate2){ // <--add 2 string parameters to this constructor
        int indexOfComma1 = coordinate1.indexOf(","); 
        int indexOfComma2 = coordinate2.indexOf(","); //indexes of comma to seperate the two parts
        this.x1 = Integer.parseInt(coordinate1.substring(1,indexOfComma1)); 
        this.x2 = Integer.parseInt(coordinate2.substring(1,indexOfComma2)); //getting the x 
        this.y1 = Integer.parseInt(coordinate1.substring(indexOfComma1 + 1, coordinate1.length()-1));
        this.y2 = Integer.parseInt(coordinate2.substring(indexOfComma2 +1, coordinate2.length()-1)); //getting the y
    }

    //METHODS
    //getters and setters for the 4 instance variables (8 methods total) 
    //getters for first coordinate
    public int getX1(){
        return x1; 
    }
    public int getY1(){
        return y1;
    }
    //getters for second coordinate
    public int getX2(){
        return x2;
    }
    public int getY2(){
        return y2;
    }
    //setters for first coordinate
    public void setX1(int newX1){
        x1 = newX1;
    }
    public void setY1(int newY1){
        y1 = newY1;
    }
    //setters for second coordinate
    public void setX2(int newX2){
        x2 = newX2;
    }
    public void setY2(int newY2){
        y2 = newY2;
    }


    //distance() -> returns a double. 
    //calculates the distance between the two points to the nearest HUNDREDTH and returns the value.
    public double distance(){
        double distance = Math.sqrt(Math.pow(x2-x1,2) + Math.pow(y2-y1,2)); //calc the distance
        //("%.2f" to round
        String distanceString = "" + String.format("%.2f",distance);
        double distanceRounded = Double.parseDouble(distanceString); //turn rounded string into double
        return distanceRounded;
    }

    //yInt() -> returns a double.
    //calculates the y intercept of the equation and returns the value to the nearest HUNDREDTH
    //if y-int if undefined, should return -999.99
    public double yInt() {
        double firstPart = slope() * x1; 
        if (slope() == 0.00 && x1== 0 && x2== 0) { //if slope or any x coordinate is 0 it will be undefined
            return -999.99;
        } else if (x1 == 0 && x2 == 0) {
            return -999.99;
        }else if (x1 == 0) { //if x is 0 the y intercept is already there
            return (double)y1;
        } else if (x2 == 0) {
            return (double)y2;
        }else if (x1 == x2) { //if it is a vertical line
            return -999.99;
        }else {
            double b = y1 - firstPart;
            String bString = "" + String.format("%.2f",b);
            double bRounded = Double.parseDouble(bString); // string to double formatting
            return bRounded;
        }
    }

    //slope() -> returns a double. 
    //calculates the slope of the equations and returns the value to the nearest HUNDREDTH
    //if slope is undefined, should return -999.99
    public double slope(){
        int changeInY = y2-y1;
        int changeInX = x2-x1; //calc the changes in both
        if (changeInX == 0) { //if change in x is 0, error will occur because of division by 0
            return -999.99;
        } else {
            double slope = (double)changeInY/changeInX;
            String slopeString = "" + String.format("%.2f",slope);
            double slopeRounded = Double.parseDouble(slopeString); //double to string
            return slopeRounded;
            
        }
    }

    //equations() -> returns a String.
    //calculates the final equation in y=mx+b form and returns the string
    //if the equation has no slope, the equation should return -> "undefined"
    //HINT: You may need other custom methods to decrease the amount of code in the equations() method
    public String equation(){
        if (slope() == -999.99 && yInt() == -999.99){
            return "undefined"; //undefined slope and yInt
        } else if (slope() == -999.99) {
            return "undefined";
        } else if (yInt() == 0) { //vertical line no y int
            return "y=" + slope() + "x";
        } else if (slope() == 0) {
            return "y=" + 1.0; //hoirzontal line with y int
        } else if (yInt() < 0) {
            return "y=" + slope() + "x" + yInt(); //format the equation correctly if the y int is negative
        }
        return "y=" + slope() + "x+" + yInt(); //else if nothing is satasfied 
    }


    //roundedToHundredth(double x)-> returns double
    //calculates the input to the nearest hundredth and returns that value
    public double roundedToHundredth(double x){
        double y = (slope() * x) + yInt();
        String yString = "" + String.format("%.2f",y);
        double yRounded = Double.parseDouble(yString); // string to double formatting
        return yRounded;
    }

    //You will need to concatenate to the string 
    //the results from findSymmetry() and Midpoint()
    public String printInfo(){
        //returning using all of the methods
        String str = "The two points are: (" + x1 + "," +y1  + ")";
        str += " and " + "(" + x2 + "," + y2 + ")";
        str += "\nThe equation of the line between these points is: " + equation();
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        str += "\n"+findSymmetry();
        str += "\n"+Midpoint();
        return str;
    }

    //findSymmetry()-> returns a string 
    //the method should determine if there is symmetry between the two points
    // there should be  4 return statements 
    // return "Symmetric about the x-axis";
    // return "Symmetric about the y-axis";
    //return "Symmetric about the origin";
    //return "No symmetry";
    public String findSymmetry(){
        if (x1 == x2 && y1 == -y2) { //if xs are the same but the y points are reflected
            return "Symmetric about the x-axis";
        } else if (x1 == -x2 && y1 == y2) { //if ys are the same but x points are reflected
            return "Symmetric about the y-axis";
        } else if (x1 == -x2 && y1 == -y2) { //if both x and y are reflected 
            return "Symmetric about the origin";
        }
        return "No symmetry"; //if none of the above are satasfied
    }

    //Midpoint()->return a string 
    //the method should calculate the midpoint between the two points
    //it should return "The midpoint of this line is: (0,0)";
    //(x1 + x2)/2, (y1 + y2)/2.
    public String Midpoint(){
        double midX = (x1 + x2)/2; //formula for midpoint of x
        double midY = (y1 + y2)/2; //formula for midpoint of y
        return "The midpoint of this line is: (" + midX + "," + midY + ")"; //formatting both together
    }

}



