package br.ufc.mdcc.spoonmetrics.miner;

import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtIf;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

public class DNIFExtractor extends AbstractProcessor<CtClass<?>> {
	
	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			int depthIf = 0;
			for (CtIf firstIf : element.getElements(new TypeFilter<CtIf>(CtIf.class))) {
				if(depthOfNestedIf(firstIf) > depthIf) {
					depthIf = depthOfNestedIf(firstIf); 
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.DNIF, depthIf));
		}
	}
	
	private int depthOfNestedIf(CtIf ifstmt) {
	    int greaterDepthOfNested = 0;
	    for(CtIf element : ifstmt.getThenStatement().getElements(new TypeFilter<CtIf>(CtIf.class))) {
	    	int value = depthOfNestedIf(element) + 1;
	        if(value > greaterDepthOfNested) {
	           greaterDepthOfNested = value;
	        }
	    }
	    return greaterDepthOfNested;
	}
}
