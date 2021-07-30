package br.ufc.mdcc.spoonmetrics.model;

public enum Metric {

	// Chidamber and Kemerer (C&K) Metrics
	WMC("CK_WMC", "Weighted Methods per Class", ""), 
	DIT("CK_DIT", "Depth of Inheritance Tree", ""),
	NOC("CK_NOC", "Number of Children", ""), 
	CBO("CK_CBO", "Coupling Between Objects", ""),
	RFC("CK_RFC", "Response For Class", ""), 
	LCOM("CK_LCOM", "Lack of Cohesion of Methods v1", ""),

	// Other OO Metrics
	LoC("OO_LoC", "Lines of Code", ""), 
	LCOM2("OO_LCOM2", "Lack of Cohesion of Methods v2", ""),
	LCOM3("OO_LCOM3", "Lack of Cohesion of Methods v3", ""), 
	Ca("OO_Ca", "Fan in = Afferent Coupling", ""),
	Ce("OO_Ce", "Fan out = Efferent Coupling", ""),
	
	//Metrics made by me
	NODA("NODA", "Number of Declared Attributes", ""),
	NOPA("NOPA", "Number of Public Attributes", ""),
	NOPRA("NOPRA", "Number of Private Attributes", ""),
	NODM("NODM", "Number of Declared Methods", ""),
	NOPM("NOPM", "Number of Public Methods", ""),
	NOPRM("NOPRM", "Number of Private Attributes", ""),
	DNIF("DNIF", "Depth of Nested Ifs", ""),
	DNFOR("DNFOR", "Depth of Nested Fors", ""),
	NOECB("NOECB", "Number of Empty Catch Blocks", ""),
	NOSE("NOSE", "Number of Signaled Exceptions", ""),
	NORE("NORE", "Number of Raised Exceptions", "");
	
	private final String shortName;

	private final String fullName;

	private final String description;

	Metric(String shortName, String fullName, String description) {
		this.shortName = shortName;
		this.fullName = fullName;
		this.description = description;
	}

	public String getShortName() {
		return shortName;
	}

	public String getFullName() {
		return fullName;
	}

	public String getDescription() {
		return description;
	}
}
