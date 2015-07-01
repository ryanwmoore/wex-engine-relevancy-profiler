package com.ibm.wex.relevancyprofiler.filters;

import java.util.ArrayList;
import java.util.List;

import com.ibm.wex.relevancyprofiler.ProfilingSession;
import com.ibm.wex.relevancyprofiler.VelocityDocument;
import com.ibm.wex.relevancyprofiler.VelocityQuery;



public class ExpectedResultsFilter extends Filter implements IResultsFilter {

	/**
	 * Gets the list of only those results that were expected in a format suitable for display.
	 * The first item in the list is a header.
	 * @return The list of expected results.
	 */
	@Override
	public List<String> filterResults(ProfilingSession session) {
		List<String> lines = new ArrayList<String>();
		
		for (VelocityQuery q : session.getQueriesAndResults()) {
    		String query = q.getQuery();
    		String bundle = q.getBundle();
    		
    		for (VelocityDocument doc : q.getResults()) {
    			if (doc.isExpected()) {
    				lines.add(query + "," + bundle + "," + doc.toString());
    			}
    		}
    	}
		
		return lines;
	}
	
	


	@Override
	public String getFileName() {
		return "expected-results.csv";
	}
	
	
	public String getHeader() {
		StringBuilder line = new StringBuilder();
		line.append("query,");
		line.append("requested bundle,");
		line.append("document source,");
		line.append("url,");
		line.append("vse-key,");
		line.append("natural rank,");
		line.append("rank,");
		line.append("base score,");
		line.append("score,");
		line.append("la-score,");
		line.append("at least rank,");
		line.append("is expected?");
		
		return line.toString();	
	}

}
