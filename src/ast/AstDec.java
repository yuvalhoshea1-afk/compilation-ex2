package ast;
import java.util.*;

public class AstDec extends AstNode
{
	public String label;
	public List<AstNode> children = new ArrayList<>();

	public AstDec(String label, AstNode... nodes)
	{
		serialNumber = AstNodeSerialNumber.getFresh();
		this.label = label;
		for (AstNode n : nodes)
		{
			if (n != null) children.add(n);
		}
	}

	public void printMe()
	{
		System.out.format("AST NODE %s\n", label);
		for (AstNode c : children) c.printMe();
		AstGraphviz.getInstance().logNode(serialNumber, label);
		for (AstNode c : children) AstGraphviz.getInstance().logEdge(serialNumber, c.serialNumber);
	}
}