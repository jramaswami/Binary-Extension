package org.nlogo.extensions.binarystrings;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

public class BinaryToDecimal extends DefaultReporter
{
	public Syntax getSyntax()
	{
		return Syntax.reporterSyntax(
				new int[] { Syntax.StringType() }, Syntax.NumberType()
			);
	}

	public Object report(Argument args[], Context context)
		throws ExtensionException, LogoException
	{
		String s;
		try
		{
			s = args[0].getString();
		}
		catch (LogoException e) 
		{
			throw new ExtensionException(e.getMessage());
		}

		return Integer.valueOf(s, 2).doubleValue();
	}
}
