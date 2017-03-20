import java.lang.*;


public class Grenade {
	
	private double angle ;
	private double force ;
	private double g ;
	private double[] traj=new double[10000] ;
	private double x ;
	private double y ;


	public Grenade() {
	}
	

	public double[] lancerGrenade(double a, double f) {
		
		angle = a ;
		force = f ;
		
		x=0 ;
		y=0;
		int i=0 ;

		while(y >= 0.0) {
			y = ((-0.5*10)/(force*force))*(x*x)*(1 + (Math.tan(angle))*(Math.tan(angle))) + x*Math.tan(angle) ;
			traj[i] = y ;
			x++ ;
			i++ ;
	
		}

		return traj ;
		
	}
	
	
	public String toString() {
		String s = "" ;
		for(int i=0; i<traj.length; i++) {
			s = s + traj[i] + " | ";
		}
		
		return "Le tableau de la trajectoire est : " + s ;
		
	}
			



}
