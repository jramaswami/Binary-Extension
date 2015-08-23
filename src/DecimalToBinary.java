package org.nlogo.extensions.binarystrings;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

public class DecimalToBinary extends DefaultReporter 
{
	public Syntax getSyntax() 
	{
		return Syntax.reporterSyntax(
				new int[]{ Syntax.NumberType() }, Syntax.StringType()
			);
	}

	public Object report(Argument args[], Context context)
		throws ExtensionException, LogoException 
	{
		int i;
		try
		{
			i = args[0].getIntValue();
		}
		catch (LogoException e)
		{
			throw new ExtensionException(e.getMessage());
		}

		return Integer.toBinaryString(i);
	}
}
