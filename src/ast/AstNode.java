package ast;

public abstract class AstNode
{
	/*******************************************/
	/* The serial number is for debug purposes */
	/* In particular, it can help in creating  */
	/* a graphviz dot format of the AST ...    */
	/*******************************************/
	public int serialNumber;

	/* line in the source file, needed for error messages in ex3 */
	public int line;

	/* sets the line and returns the node, used from the cup file */
	public static <T extends AstNode> T at(T node, int line)
	{
		if (node != null) node.line = line;
		return node;
	}

	/***********************************************/
	/* The default message for an unknown AST node */
	/***********************************************/
	public void printMe()
	{
		System.out.print("AST NODE UNKNOWN\n");
	}
}
