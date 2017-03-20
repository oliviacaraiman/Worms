import java.lang.*;
import java.util.*;


public class Grenade {
	
	private double angle ;
	private double force ;
	private double g ;
	private ArrayList traj = new ArrayList() ;
	private double[] tabTraj ;
	private double x ;
	private double y ;


	public Grenade() {
	}
	

	public double[] lancerGrenade(double a, double f) {
		
		angle = a ;
		force = f ;
		
		x=0.5;
		y =((-10*Math.pow(x,2))/(2*Math.pow(force,2)))*(1+Math.pow(Math.tan(angle),2)) + x*Math.tan(angle);
		traj.add(y);
		x=x+0.5;
		int i =1;
		while(y > 0.0) {
			y =((-10*Math.pow(x,2))/(2*Math.pow(force,2)))*(1+Math.pow(Math.tan(angle),2)) + x*Math.tan(angle);
			traj.add(y);
			x=x+0.5;
			i++ ;
		}
		
		tabTraj=new double[traj.size()];
		for (int j=0;j<tabTraj.length;j++) {
			tabTraj[j]=(double)(traj.get(j));
		}
		return tabTraj ;
		
	}
	
	
	public String toString() {
		String s = "" ;
		for(int i=0; i<tabTraj.length; i++) {
			s = s + tabTraj[i] + " | ";
		}
		return "Le tableau de la trajectoire est : " + s ;
	}
			



}
