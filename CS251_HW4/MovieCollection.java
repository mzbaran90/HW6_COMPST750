// Eddie Chapman and I discussed ways to simplify code by calling existing methods, ie calling getMovieAt
// and findMovie to avoid redundant "for loops". We also discussed the best way to implement the 
//shiftCollectionRight
package CS251_HW4;

public class MovieCollection {

	//TODO
	//Instance Variables
	private Movie[] movies;
	
	private int movieCount;

	/**
	 * Constructor.
	 * You are only allowed to hold 10 movies. 
	 * Initialize the array.
	 */
	public MovieCollection(){
		movies = new Movie[10];

	

	}


	/**
	 * Return the total number of movies.
	 * @return int
	 */
	public int getTotalMovies(){
		return movieCount;
		
		
		
	}


	/**
	 * Add the passed in Movie to your collection if there is space for it.
	 * You are not allowed duplicate copies of a movie in the array.
	 * Make sure to check movieCount to make sure it can be added.
	 * If the movie is added return true.  Else return false.
	 * 
	 * @param movie
	 * @return boolean
	 */
	public boolean addMovie(Movie movie){
		//TODO
		if (movieCount < 10 && findMovie(movie) == -1) {
			
			
			movies[movieCount] = movie;
			movieCount++;
			return true;
			}

				
		return false;
	}


	/**
	 * Add a movie at the specifed index if the index is valid.
	 * You must verify the index.  Remember, no duplicate movies are allowed.
	 * You will need to shift all your movies to the right to make room for the new movie.
	 * Return true if movie was added, false otherwise.
	 * 
	 * @param movie
	 * @param index
	 * @return boolean
	 */
	public boolean addMovieAt(Movie movie, int index){
	
		if(index > movies.length || index < 0 || index > movieCount
				|| movieCount > 9) {
			
			return false;
			
				
			}
		if (findMovie(movie) != -1) {
			return false;
		}
		
		shiftCollectionRight(index);
		movies[index] = movie;
		movieCount++;
		return true;
		}
		
	
		 
	/**
	 * Shift all movies to the right based on the index passed in.
	 * This will create 'space' for a new movie to be added.
	 * Should only be called by addMovieAt().
	 * Private methods of a class are considered helper methods.
	 * 
	 * @param index
	 */
	private void shiftCollectionRight(int index) {
		
		for(int i = movieCount; i > index; i--) {
			movies[i] = movies[i-1];
		}
		
	}

	/**
	 * Find location of the passed in Movie and return its location in the array.
	 * This means a value from [0, movieCount) could be returned if it is in the array.
	 * If the movie is not there, return -1 as a value.
	 * To compare movies, you only need to compare the name and runtime.
	 * Remember, you can use methods in Movie class to easily compare two movies now.
	 * 
	 * @param movie
	 * @return boolean
	 */
	public int findMovie(Movie movie){
		
		//TODO
		for(int m = 0; m < movieCount; m++) {
			if(movies[m].equals(movie)) {
				return m;
			}
		}
		return -1;
		
	}


	/**
	 * Get the movie at the specified index and return it.
	 * If the index passed in is invalid, return null.
	 * No shifting required.
	 * 
	 * @param index
	 * @return Movie
	 */
	public Movie getMovieAt(int index) {

		//TODO
		if(index >= movieCount || index < 0) {
			return null;
		}
		
		return movies[index];	
			
		

	}


	/**
	 * Remove the passed in Movie if it is there.
	 * If the movie is there, you will need to "shift" the array backwards one location to "remove" the movie.
	 * Remember, no null spaces are allowed in the array.
	 * You will need to call shiftCollectionLeft if you remove a movie.
	 * This method returns true if the movie is removed, false otherwise.
	 * 
	 * @param movie
	 * @return boolean
	 */
	public boolean removeMovie(Movie movie){
		
		//TODO
		if(getMovieAt(findMovie(movie)) == null){
			return false;
		}
		shiftCollectionLeft(findMovie(movie));
		movieCount--;
		return true;
		
		/*for(int i = 0; i < movieCount; i++) { // alternate method with redundant for loop.
			if(movies[i].equals(movie)) {
				shiftCollectionLeft(i);
				movieCount--;
				return true;
			}
		
		
		
	}*/
		
		
	}
	/**
	 * Remove the Movie at the specified index.
	 * Make sure the index is a valid index.
	 * You will need to call shiftCollectionLeft to remove the movie.
	 * Return the Movie that was at this location, or null if an invalid index was used.
	 * 
	 * @param index
	 * @return Movie
	 */
	public Movie removeMovieAt(int index) {
		if(index < movieCount && index >= 0) {
			Movie tempRemove = movies[index];
			shiftCollectionLeft(index);
			movieCount--;
			return tempRemove;
		}
		
		return null;
	
	}

	/**
	 * Move all elements after the index one location backwards in the array.
	 * This method is meant to be called by removeMovie() and removeMovieAt() and is private.
	 * Private methods of a class are considered helper methods.
	 * The parameter index is meant to be the location of the element to be removed.
	 * 
	 * @param index
	 */
	private void shiftCollectionLeft(int index){
		for(int i = index; i < movieCount; i++) {
			if(i == movieCount - 1 ) {
				movies[i] = null; // avoids iterator stepping out of index range 
				return;
			}
			movies[i] = movies[i +1];
		}
		
		
		
	}

	/**
	 * Find the best movie according to Rotten Tomato score and return it.
	 * If the array is empty, meaning there are no movies, it returns null.
	 * 
	 * @return Movie or null
	 */
	public Movie findBestMovie(){
		int highestScore = 0;
		int highScoreMovieIndex = 0;
		if(movies[0] == null) {
			return null;
		}
		for(int m = 0; m < movieCount; m++) {
			if(movies[m].getTomatoScore() > highestScore) {
				highestScore = movies[m].getTomatoScore();
				highScoreMovieIndex = m;
				
			}
			
		}
		return movies[highScoreMovieIndex];
		
		
		
	}

	/**
	 * Print out all movies that are considered rotten in the array.
	 * Remember you can use methods in the Movie class to determine this.
	 * Refer to driver and handout for output format.
	 * 
	 */
	public void moviesToAvoid(){
		
		int movieCounter = 1;
		for(int m = 0; m < movieCount; m++) {
			if(movies[m].getTomatoScore() < 60) {
				System.out.println("Movie " + movieCounter++ + ": ");
				System.out.println("Name: " + movies[m].getName());
				System.out.println("Length: " + movies[m].toString());
				System.out.println("Tomato Score: Rotten");
			}
		}
		
		
		
	}


	/**
	 * Print out all movies that are considered fresh in the array.
	 * Remember you can use methods in the Movie class to determine this.
	 * Refer to driver and handout for output format.
	 * 
	 */
	public void moviesToWatch(){

		//TODO
		int movieCounter = 1;
		for(int m = 0; m < movieCount; m++) {
			if(movies[m].getTomatoScore() > 60) {
				System.out.println("Movie " + movieCounter++ + ": ");
				System.out.println("Name: " + movies[m].getName());
				System.out.println("Length: " + movies[m].toString());
				System.out.println("Tomato Score: Fresh");
			}
		}
		
	}
	//helper method to grab first char of movies name 
	public char firstMovieChar(String movieName) {
		
	char [] charName = movieName.toLowerCase().toCharArray();
	return charName[0];
	
	}
	// helper method to swap movies found in sortByName
	public void swap (int earlyLetterIndex, int ai) {
		
		Movie temp = movies[ai];
		movies[ai] = movies[earlyLetterIndex];
		movies[earlyLetterIndex] = temp;
		
	}
	
	//selection sort
	public void sortByName() {
		for(int ai = 0; ai < movies.length; ai++) {
			int earlyLetterIndex = ai;
			for(int mi = ai + 1; mi < movies.length && movies[mi] != null; mi++) {
				if(firstMovieChar(movies[mi].getName()) < firstMovieChar(movies[ai].getName())) {
					earlyLetterIndex = mi;
				}
				
			}
			swap(earlyLetterIndex, ai);
		}
		
	}
	//insertion sort
	public void sortByTomatoScore() {
		Movie movieInsert;
		int mi; //declaring index within inner for loop so it can be referenced outside of loop
		for(int ai = 1; ai < movies.length && movies[ai] != null; ai++) {
			movieInsert = movies[ai];
			for(mi = ai; mi > 0 && movieInsert.getTomatoScore() > movies[mi -1].getTomatoScore(); mi--) {
				movies[mi] = movies[mi-1];
			}
			movies[mi] = movieInsert;
		}
		
	}
	//insertion sort
	public void sortByLength() {
		Movie movieInsert;
		int mi; //declaring index within inner for loop so it can be referenced outside of loop
		for(int ai = 1; ai < movies.length && movies[ai] != null; ai++) {
			movieInsert = movies[ai];
			for(mi = ai; mi > 0 && movieInsert.getMinutes() < movies[mi -1].getMinutes(); mi--) {
				movies[mi] = movies[mi-1];
			}
			movies[mi] = movieInsert;
		}
		
	}

	/**
	 * Print out all movies in the array.
	 * You only need to print out the name of the movie, nothing more.
	 * Refer to driver and handout for output format.
	 * 
	 */
	public void printOutMovieList(){
		
		//TODO
		int movieCounter = 1;
		for(int m = 0; m < movieCount; m++) {
			System.out.println("Movie " + movieCounter++ + ":" + movies[m].getName());
		}
		
	}

}
