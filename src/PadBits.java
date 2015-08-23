package org.nlogo.extensions.binarystrings;

import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.DefaultReporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.Syntax;

public class PadBits extends DefaultReporter
{
	public Syntax getSyntax()
	{
		return Syntax.reporterSyntax(
				new int[] { Syntax.StringType(), Syntax.NumberType() }, Syntax.StringType()
			);
	}

	public Object report(Argument args[], Context context)
		throws ExtensionException, LogoException
	{
		String s;
		int padCount;
		try
		{
			s = args[0].getString();
			padCount = args[1].getIntValue();
		}
		catch (LogoException e) 
		{
			throw new ExtensionException(e.getMessage());
		}

		if (s.length() >= padCount) {
			return s;
		}

		int fillCount = padCount - s.length();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fillCount; i++) {
			sb.append("0");
		}
		sb.append(s);
		return sb.toString();
	}
}

