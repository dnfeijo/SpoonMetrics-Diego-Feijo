package br.ufc.mdcc.spoonmetrics.miner;

import java.util.Map;
import java.util.Set;

import br.ufc.mdcc.spoonmetrics.SpoonMetricsApi;
import br.ufc.mdcc.spoonmetrics.model.Dataset;
import br.ufc.mdcc.spoonmetrics.model.Measure;
import br.ufc.mdcc.spoonmetrics.model.Metric;
import br.ufc.mdcc.spoonmetrics.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;

public class NOPAExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			int publicFieldNumber = 0;
			for (CtField<?> field : element.getFields()) {
				if(field.isPublic()) {
					publicFieldNumber += 1;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.NOPA, publicFieldNumber));
		}
	}
	
}
