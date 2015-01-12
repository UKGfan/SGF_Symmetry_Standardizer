package Rotate;

import org.apache.commons.io.FileUtils;

import jgogears.*;

import java.io.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import jgogears.SGF.ParseException;
import junit.framework.TestCase;


public class testMaker {
	
	
public static void main(String[] args){

	
	/*
	Console console = System.console();
	String path = console.readLine("Enter scource directory path:");
	
	String outpath = console.readLine("Enter output directory path");
	
	
	*/
	
	SGFRotator rotator = new SGFRotator();
	
	rotator.createTestCases("C:\\sgfGame", "C:\\sgftests\\");
	
	
	
	
	
	}


}