package com.epam.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.epam.ConstructionCost.HouseConstructionCost;
import com.epam.InterestCalculator.*;

public class App 
{
	static Scanner inputScanner = new Scanner(System.in);
	private static Logger LOGGER = LogManager.getLogger(App.class);
	static double principal;
	static double rateOfInterest;
	static double termOfTheLoanInYears;
	
    public static void main( String[] args )
    {
    	try {
	    	do {
	    		LOGGER.info("***     Started Application      ***");
		        LOGGER.info("\n\n		** Interest Calculator **\n"
		        		+ "1. Simple Interest\n"
		        		+ "2. Compound Interest\n\n"
		        		+ "	   ** Estimating House Construction Cost **\n"
		        		+ "3. Select A Construction Plan : \n");
		        
		        int choice = inputScanner.nextInt();
		        switch(choice) {
		        case 1:
		        	getInterestInputValues();
		        	SimpleInterest simpleInterest = new SimpleInterest(principal, rateOfInterest, termOfTheLoanInYears);
		        	LOGGER.info("\n=> Simple Interest = " + simpleInterest.getSimpleInterest());
		        	break;
		        case 2:
		        	getInterestInputValues();
		        	CompoundInterest compoundInterest = new CompoundInterest(principal, rateOfInterest, termOfTheLoanInYears);
		        	LOGGER.info("\n=> Compound Interest = " + compoundInterest.getCompountInterest());
		        	break;
		        case 3:
		        	houseConstructionEstimation();
		        	break;
		        default:
		        	LOGGER.info("\n=> Invalid Option Selected");
		        }
		        LOGGER.info("\n\n=> Would You Like To Continue (y/n) : ");
	    	} while(inputScanner.next().charAt(0)  == 'y');
	    	
	    	LOGGER.info("Program Terminated Successfully\n"
	    			+ "\n\n***    Application Closed    ***");
	        inputScanner.close();
    	}
    	catch(Exception e) {
    		LOGGER.info("\nError Occurred " + e);
    	}
    }
    
    public static void getInterestInputValues() {
    	LOGGER.info("\n=> Enter Principal, RateOfInterest, Time Period (in Years) :\n");
    	principal = inputScanner.nextDouble();
    	rateOfInterest = inputScanner.nextDouble();
    	termOfTheLoanInYears = inputScanner.nextDouble();
    }
    
    public static void houseConstructionEstimation() {
    	HouseConstructionCost houseConstructionObject = new HouseConstructionCost();
    	
        Set< Map.Entry< String,Integer> > constructionPlans = houseConstructionObject.getConstructionPlans().entrySet();
        int index = 1, length, breadth;
        String selectedPlan;
        ArrayList<String> options = new ArrayList<String>();
        LOGGER.info("\n=> List Of Construction Plans");
        for (Map.Entry< String,Integer> plan : constructionPlans) {
        	options.add(plan.getKey());
        	LOGGER.info("\n=> " + index + ". " + plan.getKey() + " Material Costs " + plan.getValue() + " per square feet");
            index++;
        }
        index = inputScanner.nextInt();
        if(index > 0 && index < 5) {
        	selectedPlan = options.get(index-1);
        	houseConstructionObject.setCostPerSquareFeet(selectedPlan);
        }
        else {
        	LOGGER.info("\n=> Invalid Option\n");
        	return ;
        }
        LOGGER.info("\n=> Enter Area Of The House (Length x breadth) : \n");
        length = inputScanner.nextInt();
        breadth = inputScanner.nextInt();
        houseConstructionObject.setAreaOfTheHouse(length, breadth);
        LOGGER.info("\n=> Cost for construction = " + houseConstructionObject.getEstimatedCost() + " INR");
    }
}

