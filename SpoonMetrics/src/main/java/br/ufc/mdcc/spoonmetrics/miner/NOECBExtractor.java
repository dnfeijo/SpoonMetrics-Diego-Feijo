package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class NOECBExtractor extends AbstractProcessor<CtClass<?>> {
	
	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			int emptyCatches = 0;
			for (CtCatch catchBlock : element.getElements(new TypeFilter<CtCatch>(CtCatch.class))) {
				if(catchBlock.getBody().getStatements().size() == 0) {
					emptyCatches += 1;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.NOECB, emptyCatches));
		}
	}
}
