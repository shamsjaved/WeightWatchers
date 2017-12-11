package mobileautomation;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class GeneralScripting {
	public static void main(String[] args) throws IOException{
		//System.out.println(args[0]);
		switch (args[0]) {
		case "reverseString":
			reverseEverything(args[1]);
			break;
			
		case "parseHtml":
			parseHtmlFile(new File(args[1]));
			break;
			
		case "parseProperties":
			parsePropertyFile(new File(args[1]));
			break;

		default:
			System.out.println("default loop");
			break;
		}
	}
	
	/**
	 * 1. This will take a sentence, find all words in it,
	 * 2. Reverse all works in the string,
	 * 3. Reverse all characters in each word of the string
	 * @throws IOException 
	 */
	public static void reverseEverything(String strToReverse) throws IOException
	{
		if(new File(strToReverse).exists())
			strToReverse= FileUtils.readFileToString(new File(strToReverse), "UTF-8"); 
			
		//Find all words
		Pattern wordPattern = Pattern.compile("\\s");
		String[] words = wordPattern.split(strToReverse);
		
		//Reverse all words
		List<String> wordsReversed= new LinkedList<>();
		for (int i = words.length-1; i >= 0; i--) {
			//Reverse all characters in each word
			wordsReversed.add(new StringBuilder(words[i]).reverse().toString());
		}
		
		//Print reversed string
		System.out.println(String.join(" ", wordsReversed));
	}
	
	public static void parseHtmlFile(File f)
	{
		By itemsLocator= By.cssSelector("div.itemContent span.title");
		ChromeDriverManager.getInstance().setup();
		ChromeDriver chrome= null;
		
		HashMap<String, String> foodMap= new LinkedHashMap<>();
		
		try {
			chrome= new ChromeDriver();
			chrome.get("file://" + f.getAbsolutePath());
			
			List<WebElement> foodItems= chrome.findElements(itemsLocator);
			for (WebElement foodItem : foodItems) {
				foodMap.put(foodItem.getText(),foodItem.findElement(By.xpath("following-sibling::span")).getText());
			}
			
			System.out.println("Third food item is " + foodMap.keySet().toArray()[2].toString());
			System.out.println("Fifth food item is " + foodMap.keySet().toArray()[4].toString());
			
			for (Entry<String, String> food : foodMap.entrySet()) {
				System.out.println(food.getKey());
				System.out.println(food.getValue());
			}
			
		} 
		finally {
			if(chrome != null)
				chrome.quit();
		}	
	}
	
	public static void parsePropertyFile(File f) throws IOException
	{
		List<String> propLines= FileUtils.readLines(f, "UTF-8");
		for (String propLine : propLines) {
			String propName= propLine.split("\\-")[0];
			String[] propValues= propLine.replace(propName + "-", "").trim().split("\\,");
			System.out.println(propName.trim());
			
			for (String propValue : propValues) {
				System.out.println(propValue.trim());
			}
		}
	}
	
	public static boolean doesFileExist(String path)
	{
		return new File(path).exists();
	}
}
