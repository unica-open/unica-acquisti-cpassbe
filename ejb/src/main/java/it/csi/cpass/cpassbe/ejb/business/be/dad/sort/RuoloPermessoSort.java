package it.csi.cpass.cpassbe.ejb.business.be.dad.sort;

public enum RuoloPermessoSort implements JpaSort {
	MODULO("modulo", "rrp.cpassDPermesso.cpassDModulo.moduloCodice"),
	RUOLO("ruolo", "rrp.cpassDRuolo.ruoloCodice"),
	PERMESSO("permesso", "rrp.cpassDPermesso.permessoCodice")
	;

	/** The model name. */
	private final String modelName;

	/** The query name. */
	private final String queryName;

	/**
	 * Constructor.
	 *
	 * @param modelName the model name
	 * @param queryName the query name
	 */
	private RuoloPermessoSort(String modelName, String queryName) {
		this.modelName = modelName;
		this.queryName = queryName;
	}

	@Override
	public String getQueryName() {
		return queryName;
	}

	@Override
	public String getModelName() {
		return modelName;
	}

	/**
	 * Retrieves the Sort by its model name.
	 *
	 * @param modelName the model name
	 * @return the sort
	 */
	public static RuoloPermessoSort byModelName(String modelName) {
		for(final RuoloPermessoSort is : RuoloPermessoSort.values()) {
			if(is.modelName.equalsIgnoreCase(modelName)) {
				return is;
			}
		}
		return null;
	}

}
