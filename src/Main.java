import java.io.*;

import java_cup.runtime.Symbol;
import ast.*;

public class Main
{
	static public void main(String argv[])
	{
		Lexer l = null;
		Parser p;
		Symbol s;
		AstDec ast;
		FileReader fileReader;
		PrintWriter fileWriter;
		String inputFileName = argv[0];
		String outputFileName = argv[1];
		
		try
		{
			/********************************/
			/* [1] Initialize a file reader */
			/********************************/
			fileReader = new FileReader(inputFileName);

			/********************************/
			/* [2] Initialize a file writer */
			/********************************/
			fileWriter = new PrintWriter(outputFileName);
			
			/******************************/
			/* [3] Initialize a new lexer */
			/******************************/
			l = new Lexer(fileReader);
			
			/*******************************/
			/* [4] Initialize a new parser */
			/*******************************/
			p = new Parser(l);

			/***********************************/
			/* [5] 3 ... 2 ... 1 ... Parse !!! */
			/***********************************/
			ast = (AstDec) p.parse().value;
			
			/*************************/
			/* [6] Print the AST ... */
			/*************************/
			ast.printMe();
			
			/*************************/
			/* [7] Close output file */
			/*************************/
			fileWriter.print("OK");
			fileWriter.close();
			
			/*************************************/
			/* [8] Finalize AST GRAPHIZ DOT file */
			/*************************************/
			AstGraphviz.getInstance().finalizeFile();
    	}
			     
		catch (Throwable e)
		{
			try
			{
				fileWriter = new PrintWriter(outputFileName);
				String msg = e.getMessage();
				if (msg != null && msg.equals("LEXICAL_ERROR"))
				{
					fileWriter.print("ERROR");
				}
				else if (msg != null && msg.startsWith("SYNTAX_ERROR:"))
				{
					fileWriter.print("ERROR(" + msg.substring("SYNTAX_ERROR:".length()) + ")");
				}
				else
				{
					fileWriter.print("ERROR(" + (l != null ? l.getLine() : 0) + ")");
				}
				fileWriter.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}
}


