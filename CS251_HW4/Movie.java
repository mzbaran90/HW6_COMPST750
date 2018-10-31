package CS251_HW4;

public class Movie {
	private String name;
	private int minutes;
	private int tomatoScore;

	//TODO
	//You may use your Movie class from lab, but a few small things have changed.
	//Look at the driver and UML diagram for changes.
	
	public Movie() {
		this("", 0, 0);
	}
	
	public Movie(String name, int minutes, int tomatoScore) {
		this.name = name;
		this.minutes = minutes;
		this.tomatoScore = tomatoScore;
	
		
	}
	public String getName() {
		return this.name;
		
	}
	public int getMinutes() {
		return this.minutes;
	}
	public int getTomatoScore(){
		return this.tomatoScore;
		
	}
	public void setTomatoScore(int score){
		if(score >= 0 && score <= 100);
		this.tomatoScore = score;
		
	}
	public boolean isFresh() {
		if(this.tomatoScore > 60) {
			return true;
		}
		return false;
		
	}
	
	private String minutesToHours(){
		int hours = this.minutes / 60;
		int minutes = this.minutes % 60;
		return Integer.toString(hours) + " hr " + Integer.toString(minutes) + " minutes ";
	}
	
	@Override
	public String toString() {
		return minutesToHours();
		
		
	}
	@Override
	public boolean equals(Object other) {
		
		if(other == this) {
			return true;
		}
		
		if(other instanceof Movie) {
			Movie tmpMovie = (Movie)other;
			return tmpMovie.name.equals(this.name) && tmpMovie.minutes == this.minutes;
			
			
		}
		return false;
		
		
	}
	
}
