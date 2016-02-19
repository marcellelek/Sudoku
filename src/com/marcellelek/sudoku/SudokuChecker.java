package com.marcellelek.sudoku;

public class SudokuChecker {
	private static SudokuChecker instance;
	
	private SudokuChecker(){}
	
	public static SudokuChecker getInstance(){
		if( instance == null ){
			instance = new SudokuChecker();
		}
		return instance;
	}
	
	public boolean checkSudoku( int[][] Sudoku){
		return (checkHorizontal(Sudoku) || checkVertical(Sudoku) || checkRegions(Sudoku));
	}

	private boolean checkHorizontal(int[][] Sudoku) {
		for( int y = 0 ; y < 9 ; y++ ){
			for( int xPos = 0 ; xPos < 9 ; xPos++ ){
				
				if( Sudoku[xPos][y] == 0 ){
					return false;
				}
				for( int x = xPos + 1 ; x < 9 ; x++ ){
					if( Sudoku[xPos][y] == Sudoku[x][y] || Sudoku[x][y] == 0 ){
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkVertical(int[][] Sudoku) {
		for( int x = 0 ; x < 9 ; x++ ){
			for( int yPos = 0 ; yPos < 9 ; yPos++ ){
				
				if( Sudoku[x][yPos] == 0 ){
					return false;
				}
				for( int y = yPos + 1 ; y < 9 ; y++ ){
					if( Sudoku[x][yPos] == Sudoku[x][y] || Sudoku[x][y] == 0 ){
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	private boolean checkRegions(int[][] Sudoku) {
		for( int xRegion = 0; xRegion < 3; xRegion++ ){
			for( int yRegion = 0; yRegion < 3 ; yRegion++ ){
				if( !checkRegion(Sudoku, xRegion, yRegion) ){
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean checkRegion(int[][] Sudoku , int xRegion , int yRegion){
		for( int xPos = xRegion * 3; xPos < xRegion * 3 + 3 ; xPos++ ){
			for( int yPos = yRegion * 3 ; yPos < yRegion * 3 + 3 ; yPos++ ){
				for( int x = xPos ; x < xRegion * 3 + 3 ; x++ ){
					for( int y = yPos ; y < yRegion * 3 + 3 ; y++ ){
						if( (( x != xPos || y != yPos) && Sudoku[xPos][yPos] == Sudoku[x][y] ) || Sudoku[x][y] == 0 ){
							return false;
						}
					}
				}
			}
		}
		return true;
	}
}
