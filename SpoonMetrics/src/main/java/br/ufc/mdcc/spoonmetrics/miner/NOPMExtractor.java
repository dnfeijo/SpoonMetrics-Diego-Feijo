package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;

public class NOPMExtractor extends AbstractProcessor<CtClass<?>>{
	
	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			int publicMethodNumber = 0;
			for (CtMethod<?> method : element.getMethods()) {
				if(method.isPublic()) {
					publicMethodNumber += 1;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.NOPM, publicMethodNumber));
		}
	}
}
