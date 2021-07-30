package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtIf;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class DNFORExtractor extends AbstractProcessor<CtClass<?>> {
	
	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			int depthFor = 0;
			for (CtFor firstFor : element.getElements(new TypeFilter<CtFor>(CtFor.class))) {
				if(depthOfNestedFor(firstFor) > depthFor) {
					depthFor = depthOfNestedFor(firstFor); 
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.DNFOR, depthFor));
		}
	}
	
	private int depthOfNestedFor(CtFor forstmt) {
	    int greaterDepthOfNested = 0;
	    for(CtFor element : forstmt.getBody().getElements(new TypeFilter<CtFor>(CtFor.class))) {
	    	int value = depthOfNestedFor(element) + 1;
	        if(value > greaterDepthOfNested) {
	           greaterDepthOfNested = value;
	        }
	    }
	    return greaterDepthOfNested;
	}
}
