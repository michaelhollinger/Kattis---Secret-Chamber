/* Michael Hollinger
Kattis - Secret Chamber at Mount Rushmore
COP 3503C
2/12/2018
*/

import java.io.*;
import java.util.*;

class secretchamber
{
	public static int numTranslations;
	public static int numPairs;
	
	
	public static void main(String[] args) throws FileNotFoundException
	{
		//scan everything in
		Scanner stdin = new Scanner(System.in);
		numTranslations = stdin.nextInt();
		numPairs = stdin.nextInt();
		stdin.nextLine();
		
		//arraylists for character pairs
		ArrayList<Character> trans1 = new ArrayList<Character>(numTranslations);
		ArrayList<Character> trans2 = new ArrayList<Character>(numTranslations);

		//scan in the character pairs
		for(int i = 0; i < numTranslations; i++)
		{
			String temp = stdin.nextLine();
			trans1.add(temp.charAt(0));
			trans2.add(temp.charAt(2));
		}
		
		//scan in the word pairs
		while(stdin.hasNext())
		{
			char[] a = stdin.next().toCharArray();
			char[] b = stdin.next().toCharArray();			

			if(translates(a, b, numTranslations, trans1, trans2))
			{
				System.out.println("yes");
			}

			else
			{
				System.out.println("no");
			}
		}
		stdin.close();
	}

	public static boolean translates(char[] a, char[] b, int numTranslations, ArrayList trans1, ArrayList trans2)
	{	

		boolean[] seen = new boolean[26];

		//false if strings are unequal in length
		if(a.length != b.length)
		{
			return false;
		}
				
		//true if strings are the same
		else if(Arrays.equals(a, b))
		{
			return true;
		}

		else
		{
			for(int i = 0; i < a.length; i++)
			{
				if((a[i]) == b[i])
				{
					continue;
				}
				
					char src = a[i];
					if(!trans1.contains(src))
					{
						return false;
					}
					
					char target = b[i];
								
				//if current characters are the same, continue
				if(src == target)
				{
					continue;
				}
				
				//if they are directly translatable
				else if(trans1.indexOf(src) == trans2.indexOf(target))
				{
					continue;
				}
				
				else if(canMap(src, target, trans1, trans2, seen))
				{					
					continue;
				}

				else
				{
					return false;
				}
			}
		}
		return true;
	}

	public static boolean canMap(char src, char target, ArrayList trans1, ArrayList trans2, boolean[] seen)
	{			
		seen[(int) src - 97] = true;

		for(int i = 0; i < numTranslations; i++)
		{		
			if(!seen[(int) ((char) trans2.get(i)) - 97])
			{
				if((char) trans1.get(i) != src)
				{
					continue;
				}
				
				char dest = (char) trans2.get(i);
				
				if(dest == target)
				{			
					Arrays.fill(seen, false);	
					return true;
				}

							if(trans1.contains(dest))
							{
								if(canMap(dest, target, trans1, trans2, seen))
								{
									return true;
								}
							}																			
			}																
		}
		return false;
	}
}