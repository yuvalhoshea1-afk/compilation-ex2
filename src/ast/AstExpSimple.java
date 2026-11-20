package ast;
import java.util.*;

public class AstExpSimple extends AstExp
{
	public String label;
	public List<AstNode> children = new ArrayList<>();

	public AstExpSimple(String label, AstNode... nodes)
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
		for (AstNode c : children) c.printMe();
		AstGraphviz.getInstance().logNode(serialNumber, label);
		for (AstNode c : children) AstGraphviz.getInstance().logEdge(serialNumber, c.serialNumber);
	}
}