package org.nlogo.extensions.binarystrings;

import org.nlogo.api.DefaultClassManager;
import org.nlogo.api.PrimitiveManager;

public class BinaryOpsExtension extends DefaultClassManager {
	public void load(PrimitiveManager pM) {
		pM.addPrimitive("decimal-to-binary", new DecimalToBinary());
		pM.addPrimitive("binary-to-decimal", new BinaryToDecimal());
		pM.addPrimitive("pad-bits", new PadBits());
	}
}

